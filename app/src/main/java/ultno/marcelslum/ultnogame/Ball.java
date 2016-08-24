package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class Ball extends Circle{


    public static final int COLOR_BALL_BLACK = 26;
    public static final int COLOR_BALL_BLUE = 27;
    public static final int COLOR_BALL_GREEN = 28;
    public static final int COLOR_BALL_RED = 22;
    public static final int COLOR_BALL_YELLOW = 23;
    public static final int COLOR_BALL_ORANGE = 24;
    public static final int COLOR_BALL_PINK = 21;
    public static final int COLOR_BALL_PURPLE = 25;

    public float angleToRotate;
    public float velocityVariation;
    public float velocityMaxByInitialVelocity;
    public float velocityMinByInitialVelocity;
    public float maxAngle;
    public float minAngle;

    public float rotationAngle = 0;
    boolean isInvencible = false;

    private int textureMap = COLOR_BALL_BLACK;
    
    public ArrayList<float> historicPositionX;
    public ArrayList<float> historicPositionY;

    Color color;
    boolean isAlive = true;
    boolean listenForExplosion = false;
    ArrayList<Target> targetsAppend;
    boolean collisionBordaB = false;
    boolean collisionBar = false;
    boolean collisionTarget = false;
    int collisionBarNumber = -1;
    boolean verifyAppendsIsFreeBall = false;

    //todo ????_ball.lastResponseBall = V(0,0);
    //todo ????_ball.lastObjects = [];

    Ball(String name, Game game, float x, float y, float radium, int weight){
        super(name, game, x, y, radium, weight);
        textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
        textureMap = COLOR_BALL_BLACK;
        isMovable = true;
        historicPositionX = new ArrayList<float>();
        historicPositionY = new ArrayList<float>();
        setDrawInfo();
    }

    public void setInvencible() {

          ArrayList<float[]> valuesInvencible = new ArrayList<>();
                valuesInvencible.add(new float[]{0f,1f});
                valuesInvencible.add(new float[]{0.2f,2f});
                valuesInvencible.add(new float[]{0.4f,3f});
                valuesInvencible.add(new float[]{0.6f,4f});
                valuesInvencible.add(new float[]{0.8f,5f});
            final Ball innerBall = this;
            Animation animBallInvencible = new Animation(innerBall, "ballInvencible", "numberForAnimation", 600, valuesInvencible, true, false);
            animBallInvencible.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerBall.numberForAnimation == 1f){
                        setTextureUnitAndUvData(COLOR_BALL_YELLOW);
                    } else if (innerBall.numberForAnimation == 2f) {
                        setTextureUnitAndUvData(COLOR_BALL_PINK);
                    } else if (innerBall.numberForAnimation == 3f) {
                        setTextureUnitAndUvData(COLOR_BALL_BLUE);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setTextureUnitAndUvData(COLOR_BALL_GREEN);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setTextureUnitAndUvData(COLOR_BALL_RED);
                    }
                }
            });
            animBallInvencible.start();
    }

    public void setDrawInfo(){
        
        initializeData(12, 6, 8, 0);

        verticesData = new float[12];

        Log.e("ball", " "+verticesData.length);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f- radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvDataButtonsAndBalls(uvsData, 0, textureMap);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
    
    public void setTextureUnitAndUvData(int textureMap){
        this.textureMap = textureMap;
        Utils.insertRectangleUvDataButtonsAndBalls(uvsData, 0, textureMap);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
    
    @Override
    public void translate(float tx, float ty, boolean updatePrevious) {
        if (isMovable && isFree){
            if (historicPositionX.size() < 10){
                historicPositionX.add(this.x);
                historicPositionY.add(this.y);
            } else {
                historicPositionX.add(0, this.x);
                historicPositionX.remove(10);
                historicPositionY.add(0, this.y);
                historicPositionY.remove(10);
            }
            if (updatePrevious) {
                this.previousX = this.x;
                this.previousY = this.y;
            }
            this.x += tx;
            this.y += ty;
        }
    }

    public void onCollision(){
        // EXTRAI OS DADOS NECESSÁRIOS

        float lastResponseBallX = 0;
        float lastResponseBallY = 0;
        for (int i = 0; i < this.lastCollisionResponse.size(); i++){
            lastResponseBallX += this.lastCollisionResponse.get(i).x;
            lastResponseBallY += this.lastCollisionResponse.get(i).y;
        }


        ArrayList<PhysicalObject> lastObjects = this.lastCollisionObjects;

        // AJUSTA O CASO DE GAMEOVER

        this.collisionBordaB = false;
        this.collisionBar = false;
        this.collisionBarNumber = 0;
        this.collisionTarget = false;
        Log.e("ball", "objetos colididos:");
        for (int i = 0; i < lastObjects.size(); i++){
            Log.e("ball", " "+lastObjects.get(i).name);
            if (lastObjects.get(i).name == "bordaB"){
                this.collisionBordaB = true;
            } else if (lastObjects.get(i).name == "bar"){
                //Log.e("ball", " vx "+lastObjects.get(i).dvx);
                this.collisionBar = true;
                this.collisionBarNumber = i;
            } else if (lastObjects.get(i).name == "target"){
                this.collisionTarget = true;
            }

        }

        if ((this.collisionBordaB || (this.collisionBar && Math.abs(lastResponseBallX) > Math.abs(lastResponseBallY)))&&!this.isInvencible){
            this.setDead();
            this.game.ballFall = true;
        }

        if (this.lastCollisionResponse.size() == 1){
            if (Math.abs(lastResponseBallX) != 0 && Math.abs(lastResponseBallY) != 0){
                if (Math.abs(lastResponseBallX) > Math.abs(lastResponseBallY)){
                    lastResponseBallY = 0;
                } else {
                    lastResponseBallX = 0;
                }
            }
        }

        // AJUSTA A VELOCIDADE DA BOLA
        if (lastResponseBallX < 0 && this.dvx > 0){

            this.dvx *= -1;

            if (this.accelStarted == true){
                this.accelFinalVelocityX *= -1;
                this.accelInitialVelocityX *= -1;
            }
        }

        if (lastResponseBallX > 0 && this.dvx < 0){
            //console.log("velocity x trocada ", lastResponseBall);
            this.dvx *= -1;

            if (this.accelStarted == true){
                this.accelFinalVelocityX *= -1;
                this.accelInitialVelocityX *= -1;
            }
        }
        
        if (lastResponseBallY < 0 && this.dvy > 0){
            this.dvy *= -1;

            if (this.accelStarted == true){
                this.accelFinalVelocityY *= -1;
                this.accelInitialVelocityY *= -1;
            }
        }

        if (lastResponseBallY > 0 && this.dvy < 0){
            this.dvy *= -1;
            if (this.accelStarted == true){
                this.accelFinalVelocityY *= -1;
                this.accelInitialVelocityY *= -1;
            }

        }

        if(this.collisionBar){

            if (this.isAlive){
                // TODO lastObjects.get(this.collisionBarNumber).lineAlphaAnim.start();
                // TODO lastObjects.get(this.collisionBarNumber).lineColor = this.color;

            }

            float angleToRotate = 0;
            boolean velocityAdd = true;
            //Log.e("ball", "velocity add "+lastObjects.get(this.collisionBarNumber).vx);
            //console.log("this.velocityVariation", this.velocityVariation);
            if (this.dvx < 0 && lastObjects.get(this.collisionBarNumber).vx < 0){
                velocityAdd = false;
                angleToRotate = this.angleToRotate;
            } else if (this.dvx < 0 && lastObjects.get(this.collisionBarNumber).vx > 0){
                velocityAdd = true;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && lastObjects.get(this.collisionBarNumber).vx > 0){
                velocityAdd = false;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && lastObjects.get(this.collisionBarNumber).vx < 0){
                velocityAdd = true;
                angleToRotate = this.angleToRotate;
            }

            //Log.e("ball", "velocity add "+velocityAdd);
            //Log.e("ball", "angleToRotate "+angleToRotate);

            //console.log(velocityAdd);


            vx = this.dvx;
            vy = this.dvy;

            float initialLen = new Vector(this.initialDesireVelocityX, this.initialDesireVelocityY).len();
            float maxLen = initialLen * this.velocityMaxByInitialVelocity;
            float minLen = initialLen * this.velocityMinByInitialVelocity;
            float scalePorcentage = 1f;

            float final_vx  = vx;
            float final_vy  = vy;

            if (angleToRotate != 0){
                if (velocityAdd == true){
                    scalePorcentage +=this.velocityVariation;
                } else  {
                    scalePorcentage -=this.velocityVariation;
                }

                Vector possibleVelocity = new Vector(vx * scalePorcentage, vy * scalePorcentage);

                float possibleVelocityLen = possibleVelocity.len();

                if (possibleVelocityLen > minLen && possibleVelocityLen < maxLen){
                    vx = possibleVelocity.x;
                    vy = possibleVelocity.y;
                }

                Vector possibleVelocityRotate = new Vector(vx, vy).rotate(angleToRotate *((float)Math.PI/180f));

                float angle = (float)Math.atan2(Math.abs(possibleVelocityRotate.y), Math.abs(possibleVelocityRotate.x));
                angle = angle * 180f/(float)Math.PI;

                //Log.e("ball", "angle "+angle);

                if (angle < this.maxAngle && angle > this.minAngle){
                    final_vx =  possibleVelocityRotate.x;
                    final_vy =  possibleVelocityRotate.y;
                } else {
                    final_vx =  vx;
                    final_vy =  vy;
                }
            }
            this.accelerate(150, final_vx, final_vy);
        }

        // AJUSTA O ALVO ATINGIDO
        for (Entity e : lastObjects){
            //console.log(lastObjects[i].name, " "); //lastObjects.position.x, " ", lastObjects.position.y);
            if (e.name == "target"){
                game.ballCollidedFx = 40;
                Target target = (Target)e;
                target.onBallCollision();
                if (target.special == 1 && !listenForExplosion){
                    //Log.e("ball", " wait for explosion ativado");
                    waitForExplosion();
                }
            }
        }

        // TOCA O SOM ADEQUADO

        this.game.soundPool.play(this.game.soundBallHit, 0.01f* (float) game.volume, 0.01f* (float) game.volume, 0, 0, 1);

    }

    private void waitForExplosion() {
        listenForExplosion = true;
        setTextureUnitAndUvData(COLOR_BALL_RED);
        
        ArrayList<float[]> valuesAlphaRedBall = new ArrayList<>();
        valuesAlphaRedBall.add(new float[]{0f,1f});
        valuesAlphaRedBall.add(new float[]{0.5f,0.5f});
        valuesAlphaRedBall.add(new float[]{1f,1f});

        Animation anim = new Animation(this, "alphaExplode", "alpha", 4000, valuesAlphaRedBall, false, false);
        anim.start();
        
    }
    
    public void explode(){

        ParticleGenerator pg = new ParticleGenerator("explode", game, x, y);
        game.particleGenerator.add(pg);
        pg.activate();

        this.game.soundPool.play(this.game.soundExplosion1, 0.01f* (float) game.volume, 0.01f* (float) game.volume, 0, 0, 1);
        this.game.soundPool.play(this.game.soundExplosion2, 0.01f* (float) game.volume, 0.01f* (float) game.volume, 0, 0, 1);

        listenForExplosion = false;

        int quantityOfClones = 3;
        float distance = radius * 3;
        
        float initialDesiredVelocityLen = new Vector(initialDesireVelocityX, initialDesireVelocityY).len();
        float desiredVelocityLen = new Vector(dvx, dvy).len();
        
        float explodeLen = initialDesiredVelocityLen;
        if (desiredVelocityLen < initialDesiredVelocityLen) explodeLen = desiredVelocityLen;
        
        dvx = 0f;
        dvy = 0f;
        
        Vector rotateVelocity45 = new Vector(explodeLen, 0).rotate(45 *((float)Math.PI/180f));
        
        float rotateX = rotateVelocity45.x;
        float rotateY = rotateVelocity45.y;
        
        accelerate(500, rotateX, rotateY*-1);
        
        setTextureUnitAndUvData(COLOR_BALL_BLACK);
        
        float explodeX = 0;
        float explodeY = 0;
        int explodeColor = COLOR_BALL_BLACK;
        float explodeVelocityX = 0;
        float explodeVelocityY = 0;
        float explodeRadius = radius;






        int [] explosionColorsUsed = {-1,-1,-1};

        
        for (int i = 0; i < quantityOfClones; i++){
            if (i == 0){
                explodeX = x - distance;
                explodeY = y;
                explodeVelocityX = rotateX * -1;
                explodeVelocityY = rotateY * -1;
            } else if (i == 1){
                explodeX = x - distance;
                explodeY = y + distance;
                explodeVelocityX = rotateX * -1;
                explodeVelocityY = rotateY;
            } else if (i == 2){
                explodeX = x;
                explodeY = y + distance;
                explodeVelocityX = rotateX;
                explodeVelocityY = rotateY;
            }

            boolean sameColor;
            do {
                sameColor = false;
                explodeColor = getRandomColor();
                for (int i2 = 0; i2 < explosionColorsUsed.length; i2++){
                    if (explodeColor == explosionColorsUsed[i2]){
                        sameColor = true;
                        break;
                    }
                }
            } while (sameColor == true);

            explosionColorsUsed[i] = explodeColor;

            //Log.e("ball", "explodeColor "+i+" "+explodeColor);
            
            Ball ball = new Ball("ball"+i, this.game, explodeX, explodeY, explodeRadius, 8);
            ball.program = this.game.imageProgram;
            ball.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
            ball.setTextureUnitAndUvData(explodeColor);

            ball.dvx = 0f;
            ball.dvy = 0f;
            

            ball.angleToRotate = angleToRotate;
            ball.velocityVariation = velocityVariation;

            ball.velocityMaxByInitialVelocity = velocityMaxByInitialVelocity;
            ball.velocityMinByInitialVelocity = velocityMinByInitialVelocity;

            ball.maxAngle = maxAngle;
            ball.minAngle = minAngle;

            ball.initialDesireVelocityX = initialDesireVelocityX;
            ball.initialDesireVelocityY = initialDesireVelocityY;

            this.game.addBall(ball);

            //Log.e("ball", "explode x: "+explodeVelocityX+" y: "+explodeVelocityX);
            ball.accelerate(500, explodeVelocityX, explodeVelocityY);


        }
        
        for (Animation a : animations){
            if (a.name == "alphaExplode"){
                a.stop();
                break;
            }
        }
    }

    public int getRandomColor() {
        int color;
        float random = Utils.getRandonFloat(0f, 1f);
        if (random < (0.125 * 1)) {
            color = COLOR_BALL_BLUE;
        } else if (random < (0.125 * 2)) {
            color = COLOR_BALL_GREEN;
        } else if (random < (0.125 * 3)) {
            color = COLOR_BALL_RED;
        } else if (random < (0.125 * 4)) {
            color = COLOR_BALL_YELLOW;
        } else if (random < (0.125 * 5)) {
            color = COLOR_BALL_ORANGE;
        } else if (random < (0.125 * 6)) {
            color = COLOR_BALL_PINK;
        } else if (random < (0.125 * 7)) {
            color = COLOR_BALL_BLACK;
        } else {
            color = COLOR_BALL_PURPLE;
        }
        return color;
    }


    private void setDead() {
        this.isAlive = false;
        final Ball self = this;
        reduceAlpha(500, 0f,new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                self.isVisible = false;
            }
        });
        this.isSolid = false;
        this.isCollidable = false;
        this.isMovable = false;
    }
}
