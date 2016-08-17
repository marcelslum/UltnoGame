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
    public float initialDesireVelocityX;
    public float initialDesireVelocityY;
    public float rotationAngle = 0;
    boolean isInvencible = false;
    int textureUnit = COLOR_BALL_BLACK;

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

    private final static float [] columns = new float [] {341.333f,682.666f};
    private final static float [] lines = new float [] {341.333f,682.666f};
    private final static float textureSize = 1024f;


    Ball(String name, Game game, float x, float y, float radium, int weight){
        super(name, game, x, y, radium, weight);
        setDrawInfo();
    }

    public void setInvencible() {
    /*TODO
        if (invencible){
            ANIM.createAnimation(_ball, "ballInvencible", "color",500, [
            [0,orange],
            [0.2,blue],
            [0.4,red],
            [0.6,green],
            [0.8,yellow]
            ], true, false, 0).start();
        }

        */
    }

    public void setDrawInfo(){
        
        initializeData(12, 6, 8, 0);

        this.verticesData = new float[12];


        Log.e("ball", " "+this.verticesData.length);

        Utils.insertRectangleVerticesData(this.verticesData, 0, 0f-radium, radium, 0f - radium, radium, 0f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        insertRectangleUvDataButtonsAndBalls(this.uvsData, 0, textureUnit);
        uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
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
        for (int i = 0; i < lastObjects.size(); i++){
            if (lastObjects.get(i).name == "bordaB"){
                this.collisionBordaB = true;
            } else if (lastObjects.get(i).name == "bar"){
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
            //console.log("this.velocityVariation", this.velocityVariation);
            if (this.dvx < 0 && lastObjects.get(this.collisionBarNumber).dvx < 0){
                velocityAdd = false;
                angleToRotate = this.angleToRotate;
            } else if (this.dvx < 0 && lastObjects.get(this.collisionBarNumber).dvx > 0){
                velocityAdd = true;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && lastObjects.get(this.collisionBarNumber).dvx > 0){
                velocityAdd = false;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && lastObjects.get(this.collisionBarNumber).dvx < 0){
                velocityAdd = true;
                angleToRotate = this.angleToRotate;
            }

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
        for (int i = 0; i < lastObjects.size(); i++){
            //console.log(lastObjects[i].name, " "); //lastObjects.position.x, " ", lastObjects.position.y);
            if (lastObjects.get(i).name == "target"){
                Target target = (Target)lastObjects.get(i);
                target.onBallCollision();
                if (lastObjects.get(i).special == 1 && !this.listenForExplosion){
                    this.waitForExplosion();
                }
            }
        }

        // TOCA O SOM ADEQUADO

        this.game.soundPool.play(this.game.soundBallHit, 1, 1, 0, 0, 1);

    }

    private void waitForExplosion() {




    }

    private void setDead() {
        this.isAlive = false;
        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0,1});
        valuesAnimation.add(new float[]{1,0});
        Animation animation = new Animation(this, "alpha", "alpha", 500, valuesAnimation, false, true);
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
