package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class Ball extends Circle{

    static final String TAG = "Ball";
    
    static final int COLOR_BALL_BLACK = 26;
    static final int COLOR_BALL_BLUE = 27;
    static final int COLOR_BALL_GREEN = 28;
    static final int COLOR_BALL_RED = 22;
    static final int COLOR_BALL_YELLOW = 23;
    static final int COLOR_BALL_ORANGE = 24;
    static final int COLOR_BALL_PINK = 21;
    static final int COLOR_BALL_PURPLE = 25;
    public ParticleGenerator particleGenerator;
    public BallParticleGenerator ballParticleGenerator;

    public float initialNormalDVX;
    public float initialNormalDVY;

    long startTimeFakeBallAnim = 0;
    long fakeBallAnimDuration = 3000;
    boolean fakeBallAnimActive = false;

    
    boolean onMinAngle = false;
    boolean onMaxAngle = false;
    boolean onMinVelocity = false;
    boolean onMaxVelocity = false;

    ArrayList<Ball> quarentineBalls;
    ArrayList<Integer> quarentineBallsState;

    float final_vx =  0f;
    float final_vy =  0f;
    BallBehaviourData ballBehaviourData;

    float angleToRotate;
    float velocityVariation;
    float velocityMax_BI;
    float velocityMin_BI;
    float maxAngle;
    float minAngle;

    final int timeForExplode = 500;
    long initialTimeWaitingExplosion = 0;

    public float rotationAngle = 0;
    boolean isInvencible = false;

    public int textureColorId = COLOR_BALL_BLACK;

    Color color;
    boolean isAlive = true;
    boolean listenForExplosion = false;
    ArrayList<Target> targetsAppend;
    boolean collisionBar = false;
    boolean collisionOtherBall = false;
    int collisionBarNumber = -1;
    public int alarmId;
    
    public float lastResponseBallX = 0f;
    public float lastResponseBallY = 0f;
    public float impulsion = 0f;

    public long lastBarCollisionTime = 0l;

    ArrayList<Ball> ballsCollidedProcessed;
    
    boolean historicOn = false;
    
    double mass = 0f;
    
    public boolean isFake = false;
    public boolean fakeOnTop = false;

    Ball(String name, float x, float y, float radius, int textureColorId){
        super(name, x, y, Entity.TYPE_BALL, radius, Game.BALL_WEIGHT);
        this.textureColorId = textureColorId;
        textureId = Texture.TEXTURES;
        program = Game.imageColorizedProgram;

        ballsCollidedProcessed = new ArrayList<>();
        color = Color.pretoCheio;

        maxWidth = radius * 2f;
        maxHeight = radius * 2f;
        
        // volume of sphere = (4/3) * PI * r^3
        // mass = density * volume
        // considera apenas o cubo do raio, já que o resto é igual para todas as esferas
       	mass = Math.pow(radius,3);

        isMovable = true;
        setDrawInfo();
        ballParticleGenerator = new BallParticleGenerator(name+"pg", 0f, 0f);

    }


    public void updateBaseVelocity(int newVelocity){

        //Log.e(TAG, "---------------------------- ");

        //Log.e(TAG, "newVelocity " + newVelocity);

        //Log.e(TAG, "ANTES ");

        //Log.e(TAG, "initialDVX " + initialDVX);
        //Log.e(TAG, "initialDVY " + initialDVY);

        //Log.e(TAG, "dvx " + dvx);
        //Log.e(TAG, "dvy " + dvy);

        float dvxMultiplier = 1.0f;
        if (dvx < 0){
            dvxMultiplier = -1.0f;
        }

        float dvyMultiplier = 1.0f;
        if (dvy < 0){
            dvyMultiplier = -1.0f;
        }


        float currentXPercentage = Math.abs(dvx) / initialDVX;
        float currentYPercentage = Math.abs(dvy) / initialDVY;

        //Log.e(TAG, "currentXPercentage " + currentXPercentage);
        //Log.e(TAG, "currentYPercentage " + currentYPercentage);

        //Log.e(TAG, "initialNormalDVX " + initialNormalDVX);
        //Log.e(TAG, "initialNormalDVX " + initialNormalDVX);

        initialDVX = initialNormalDVX * ((float)newVelocity / 100);
        initialDVY = initialNormalDVY * ((float)newVelocity / 100);

        dvx = initialDVX * currentXPercentage * dvxMultiplier;
        dvy = initialDVY * currentYPercentage * dvyMultiplier;

        //Log.e(TAG, "DEPOIS ");

        //Log.e(TAG, "initialDVX " + initialDVX);
        //Log.e(TAG, "initialDVY " + initialDVY);

        //Log.e(TAG, "dvx " + dvx);
        //Log.e(TAG, "dvy " + dvy);

    }

    
    public void markAsFakeBall(){
        isFake = true;

        if (Utils.getRandonFloat(0f, 1f) < 0.5f){
            fakeOnTop = true;
        } else {
            fakeOnTop = false;
        }

        weight = Game.FAKE_BALL_WEIGHT;
    }

    public void setInvencible() {
        
        if (isFake){
            return;
        }
        
          isInvencible = true;
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
                        setBallColor(COLOR_BALL_YELLOW);
                    } else if (innerBall.numberForAnimation == 2f) {
                        setBallColor(COLOR_BALL_PINK);
                    } else if (innerBall.numberForAnimation == 3f) {
                        setBallColor(COLOR_BALL_BLUE);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setBallColor(COLOR_BALL_GREEN);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setBallColor(COLOR_BALL_RED);
                    }
                }
            });
            animBallInvencible.start();
    }

    public void setDrawInfo(){
        
        initializeData(12, 6, 8, 0);

        verticesData = new float[12];

        Utils.insertRectangleVerticesData(verticesData, 0, 0f - radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        setBallColor(textureColorId);

        colorsData = new float[16];
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
    }
    
    public void setBallColor(int colorId){
        textureColorId = colorId;
        if (textureColorId == COLOR_BALL_BLACK){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_BLACK_ID));
        } else if (textureColorId == COLOR_BALL_BLUE){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_BLUE_ID));
        } else if (textureColorId == COLOR_BALL_GREEN){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_GREEN_ID));
        } else if (textureColorId == COLOR_BALL_ORANGE){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_ORANGE_ID));
        } else if (textureColorId == COLOR_BALL_PINK){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_PINK_ID));
        } else if (textureColorId == COLOR_BALL_PURPLE){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_PURPLE_ID));
        } else if (textureColorId == COLOR_BALL_RED){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_RED_ID));
        } else if (textureColorId == COLOR_BALL_YELLOW){
            updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BALL_YELLOW_ID));
        }
    }
    
    @Override
    public void translate(float tx, float ty) {
        super.translate(tx, ty);
        if (isMovable && isFree){

            float randon = Utils.getRandonFloat(0f, 1f);
            int numberOfParticles = 2;
            
            if (randon < 0.33f){
                numberOfParticles = 4;
            } else if (randon < 0.66f){
                numberOfParticles = 3;
            }

            if (    (dvx > 0 && dvy < 0)    ||
                    (dvx < 0 && dvy > 0)    ){
                ballParticleGenerator.addParticles(positionX - radius, positionY, radius, numberOfParticles);
                //Log.e(TAG, "1");
            } else {
                //Log.e(TAG, "2");
                ballParticleGenerator.addParticles(positionX, positionY, radius, numberOfParticles);
            }
        }
    }

    public void clearParticles(){
        if (ballParticleGenerator != null) {
            ballParticleGenerator.isActive = false;
        }
    }

    @Override
    public void checkCenter() {
        centerX = positionX;
        centerY = positionY;
    }

    @Override
    public void checkTransformations(boolean updatePrevious) {
        if (ballParticleGenerator != null && ballParticleGenerator.isActive){
            ballParticleGenerator.checkTransformations(updatePrevious);
        }

        if (particleGenerator != null && particleGenerator.isActive){
            particleGenerator.checkTransformations(updatePrevious);
        }
        super.checkTransformations(updatePrevious);
    }


    public void initFakeAnimation() {
        startTimeFakeBallAnim = Game.currentFrameTime;
        fakeBallAnimActive = true;
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        if (isAlive) {
            if (ballParticleGenerator != null && ballParticleGenerator.isActive) {
                ballParticleGenerator.prepareRender(matrixView, matrixProjection);
            }
            if (particleGenerator != null && particleGenerator.isActive) {
                particleGenerator.prepareRender(matrixView, matrixProjection);
            }
        }

        if (fakeBallAnimActive){
            long elapsedTime = Utils.getTime() - startTimeFakeBallAnim;
            if (elapsedTime < fakeBallAnimDuration){
                color.r = Utils.getRandonFloat(0f, 0.3f) * (1.0f - ((float)elapsedTime/(float)fakeBallAnimDuration));
                color.g = Utils.getRandonFloat(0f, 0.4f) * (1.0f - ((float)elapsedTime/(float)fakeBallAnimDuration));
                color.b = Utils.getRandonFloat(0f, 0.5f) * (1.0f - ((float)elapsedTime/(float)fakeBallAnimDuration));
            } else {
                fakeBallAnimActive = false;
                color.r = 0f;
                color.g = 0f;
                color.b = 0f;
            }

            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
        }

        super.prepareRender(matrixView, matrixProjection);
    }


    public void changeSpeedAndAngleAfterBallCollision(boolean angleAdd, boolean speedAdd){

        //Log.e(TAG, "changeSpeedAndAngleAfterBallCollision "+angleAdd+" "+speedAdd);

        float mAngleToRotate;
        if (angleAdd) {
            mAngleToRotate = angleToRotate;
        } else {
            mAngleToRotate = -angleToRotate;
        }

        //Log.e(TAG, "mAngleToRotate "+mAngleToRotate);

        vx = dvx;
        vy = dvy;

        float lenOfInitialVelocity = Utils.getVectorMagnitude(initialDVX, initialDVY);
        //Log.e(TAG, "lenOfInitialVelocity "+lenOfInitialVelocity);
        float maxLen = lenOfInitialVelocity * velocityMax_BI;
        //Log.e(TAG, "maxLen "+maxLen);
        float minLen = lenOfInitialVelocity * velocityMin_BI;
        //Log.e(TAG, "minLen "+minLen);

        final_vx  = vx;
        final_vy  = vy;


        float velocityScalePorcentage = ((maxLen - minLen) * velocityVariation)/(maxLen - minLen);

        //Log.e(TAG, "velocityScalePorcentage "+velocityScalePorcentage);

        float scalePorcentage = 1f;
        if (speedAdd){
            scalePorcentage += velocityScalePorcentage;
        } else  {
            scalePorcentage -= velocityScalePorcentage;
        }

        //Log.e(TAG, "scalePorcentage "+scalePorcentage);

        float possibleVelocityLen = Utils.getVectorMagnitude(vx * scalePorcentage, vy * scalePorcentage);

        //Log.e(TAG, "possibleVelocityLen "+possibleVelocityLen);

        if (possibleVelocityLen > minLen && possibleVelocityLen < maxLen){
            //Log.e(TAG, "possibleVelocityLen > minLen && possibleVelocityLen < maxLen ");
            final_vx = final_vx * scalePorcentage;
            final_vy = final_vy * scalePorcentage;
        } else {
            if (possibleVelocityLen <= minLen){
                //Log.e(TAG, "possibleVelocityLen <= minLen ");
                final_vx = final_vx * scalePorcentage;
                final_vy = final_vy * scalePorcentage;
                float lenAfter = Utils.getVectorMagnitude(final_vx, final_vy);
                float scaleToMin = minLen/lenAfter;
                final_vx = final_vx * scaleToMin;
                final_vy = final_vy * scaleToMin;
            } else if (possibleVelocityLen >= maxLen){
                //Log.e(TAG, "possibleVelocityLen >= maxLen ");
                final_vx = final_vx * scalePorcentage;
                final_vy = final_vy * scalePorcentage;
                float lenAfter = Utils.getVectorMagnitude(final_vx, final_vy);
                float scaleToMax = maxLen/lenAfter;
                final_vx = final_vx * scaleToMax;
                final_vy = final_vy * scaleToMax;
            }
        }

        // ROTAÇÃO
        rotateTestingAngle(final_vx, final_vy, mAngleToRotate, false);

        dvx = final_vx;
        dvy = final_vy;
    }


    public void onCollision(){

        lastResponseBallX = 0f;
        lastResponseBallY = 0f;
        impulsion = 0f;
        collisionBar = false;
        collisionBarNumber = 0;
        collisionOtherBall = false;

        checkCollisionsDataObjectRepetition();

        //Log.e("ball", "iniciando checagem de colisão---------------------------------"+ positionX + " " + positionY);

        boolean collidedProcessed = false;

        for (int i = 0; i < collisionsData.size(); i++){
            
            lastResponseBallX += collisionsData.get(i).responseX;
            lastResponseBallY += collisionsData.get(i).responseY;

            //Log.e("ball", collisionsData.get(i).object.name +" rX "+ collisionsData.get(i).responseX +" rY "+ collisionsData.get(i).responseY +" nX "+ collisionsData.get(i).normalX +" nY "+ collisionsData.get(i).normalY+" isRepeated "+collisionsData.get(i).isRepeated);

            if (collisionsData.get(i).object.type == Entity.TYPE_BOTTOM_BORDER && !collisionsData.get(i).isRepeated && !isInvencible){
                setDead();
                return;
            }

            if (collisionsData.get(i).object.type == Entity.TYPE_BAR && !collisionsData.get(i).isRepeated){
                collisionBar = true;
                collisionBarNumber = i;
            }
        
            // verifica se obstáculo esta crescendo e, se a velocidade for maior que a da bola, gera nela uma impulsão
            if (collisionsData.get(i).object.type == Entity.TYPE_OBSTACLE && !collisionsData.get(i).isRepeated){
                Obstacle o = (Obstacle)collisionsData.get(i).object;
                if (o.scaleVariationData != null){
                    if (o.scaleVariationData.isActive){
                        if (o.scaleVariationData.widthVelocity > 0 && (o.scaleVariationData.widthVelocity * o.getTransformedWidth())/2f > Math.abs(dvx) && collisionsData.get(i).responseX != 0f){
                            impulsion += o.scaleVariationData.widthVelocity/Math.abs(dvx*1f);
                        }
                        if (o.scaleVariationData.heightVelocity > 0 && (o.scaleVariationData.heightVelocity * o.getTransformedHeight())/2f > Math.abs(dvy) && collisionsData.get(i).responseY != 0f){
                            impulsion += o.scaleVariationData.heightVelocity/Math.abs(dvy*1f);
                        }
                    }
                }
                if (!isFake){
                    Level.levelObject.levelGoalsObject.hitObstacle();
                }

            }

            if (collisionsData.get(i).object.type == Entity.TYPE_BALL  && !collisionsData.get(i).isRepeated) {
                for (int i2 = 0; i2 < ballsCollidedProcessed.size(); i2++) {
                    if (ballsCollidedProcessed.get(i2) == collisionsData.get(i).object) {
                        //Log.e("ball", "collided processed");
                        collidedProcessed = true;
                    }
                }


                if (!collidedProcessed && !isFake) {

                    Level.levelObject.levelGoalsObject.hitAnotherBall();

                    collisionOtherBall = true;

                    Ball otherBall = (Ball) collisionsData.get(i).object;


                    //Log.e(TAG, "DADOS INICIAIS");


                    //Log.e(TAG, "------------------BOLA1");
                    //Log.e(TAG, "                  textureMap "+textureMap);
                    //Log.e(TAG, "                  positionX "+ (positionX));
                    //Log.e(TAG, "                  positionY "+ (positionY));
                    //Log.e(TAG, "                  dvx "+ (dvx));
                    //Log.e(TAG, "                  dvy "+ (dvy));
                    //Log.e(TAG, "                  moveAngle " + Math.toDegrees(Math.atan2(dvy, dvx)));


                    //Log.e(TAG, "------------------BOLA2");
                    //Log.e(TAG, "                  textureMap "+otherBall.textureMap);
                    //Log.e(TAG, "                  positionX "+ (otherBall.positionX));
                    //Log.e(TAG, "                  positionY "+ (otherBall.positionY));
                    //Log.e(TAG, "                  dvx "+ (otherBall.dvx));
                    //Log.e(TAG, "                  dvy "+ (otherBall.dvy));
                    //Log.e(TAG, "                  moveAngle " + Math.toDegrees(Math.atan2(otherBall.dvy, otherBall.dvx)));
                    
                    
                    float starX;
                    if (positionX > otherBall.positionX){
                        starX = positionX + ((positionX - otherBall.positionX)/2f);
                    } else {
                        starX = otherBall.positionX + ((otherBall.positionX - positionX)/2f);
                    }
                    
                    float starY;
                    if (positionY > otherBall.positionY){
                        starY = positionY + ((positionY - otherBall.positionY)/2f);
                    } else {
                        starY = otherBall.positionY + ((otherBall.positionY - positionY)/2f);
                    }

                    //Log.e(TAG, "incluindo estrela na posicao "+starX+" "+starY );


                    final Image star = new Image("ballCollisionStar", starX - radius*8f, starY - radius*8f, radius*16f, radius*16f, Texture.TEXTURES,
                        TextureData.getTextureDataById(TextureData.TEXTURE_BALL_COLLISION_ID));
                    star.display();
                    Animation anim = Utils.createAnimation3v(star, "star", "alpha", 800, 0f, 0f, 0.1f, 1f, 1f, 0f, false, true);

                    Utils.createAnimation3v(star, "scaleX", "scaleX", 800, 0f, 0.5f, 0.1f, 1f, 1f, 0.5f, false, true).start();
                    Utils.createAnimation3v(star, "scaleY", "scaleY", 800, 0f, 0.5f, 0.1f, 1f, 1f, 0.5f, false, true).start();
                    Utils.createAnimation3v(star, "translateX", "translateX", 800, 0f, radius*4f, 0.1f, 0f, 1f, radius*4f, false, true).start();
                    Utils.createAnimation3v(star, "translateY", "translateY", 800, 0f, radius*4f, 0.1f, 0f, 1f, radius*4f, false, true).start();

                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            star.clearDisplay();
                        }
                    });
                    anim.start();

                    Game.ballCollisionStars.add(star);

                    double collisionAngle = Math.atan2(positionY - otherBall.positionY, positionX - otherBall.positionX);

                    //Log.e(TAG, "collisionAngle " + Math.toDegrees(collisionAngle));

                    double magBall1 = Math.sqrt((dvx * dvx)+(dvy * dvy));
                    double magBall2 = Math.sqrt((otherBall.dvx * otherBall.dvx)+(otherBall.dvy * otherBall.dvy));

                    //Log.e(TAG, "magBall1 " + magBall1);
                    //Log.e(TAG, "magBall2 " + magBall2);

                    double angleBall1 = Math.atan2(dvy, dvx);
                    double angleBall2 = Math.atan2(otherBall.dvy, otherBall.dvx);

                    //Log.e(TAG, "angleBall1 " + angleBall1);
                    //Log.e(TAG, "angleBall2 " + angleBall2);

                    double xSpeedBall1 = magBall1 * Math.cos(angleBall1-collisionAngle);
                    double ySpeedBall1 = magBall1 * Math.sin(angleBall1-collisionAngle);
                    double xSpeedBall2 = magBall2 * Math.cos(angleBall2-collisionAngle);
                    double ySpeedBall2 = magBall2 * Math.sin(angleBall2-collisionAngle);

                    //Log.e(TAG, "xSpeedBall1 " + xSpeedBall1);
                    //Log.e(TAG, "ySpeedBall1 " + ySpeedBall1);
                    //Log.e(TAG, "xSpeedBall2 " + xSpeedBall2);
                    //Log.e(TAG, "ySpeedBall2 " + ySpeedBall2);

                    double finalxSpeedBall1 = ((mass-otherBall.mass)*xSpeedBall1+(otherBall.mass+otherBall.mass)*xSpeedBall2)/(mass+otherBall.mass);
                    double finalxSpeedBall2 = ((mass+mass)*xSpeedBall1+(otherBall.mass-mass)*xSpeedBall2)/(mass+otherBall.mass);

                    //Log.e(TAG, "finalxSpeedBall1 " + finalxSpeedBall1);
                    double finalySpeedBall1 = ySpeedBall1;

                    //Log.e(TAG, "finalxSpeedBall2 " + finalxSpeedBall2);
                    double finalySpeedBall2 = ySpeedBall2;
                    
                    //double v1x = Utils.getXRotatedFromRad(dvx, dvy, theta);
                    //double v1y = Utils.getYRotatedFromRad(dvx, dvy, theta);
                    //double v2x = Utils.getXRotatedFromRad(otherBall.dvx, otherBall.dvy, theta);
                    //double v2y = Utils.getYRotatedFromRad(otherBall.dvx, otherBall.dvy, theta);

                    //Log.e(TAG, "v1x " + v1x);
                    //Log.e(TAG, "v1y " + v1y);
                    //Log.e(TAG, "v2x " + v2x);
                    //Log.e(TAG, "v2y " + v2y);







                    float finalDvx = (float)(Math.cos(collisionAngle)*finalxSpeedBall1+Math.cos(collisionAngle+Math.PI/2)*finalySpeedBall1);
                    //Log.e(TAG, "finalDvx " + finalDvx);
                    float finalDvy = (float)(Math.sin(collisionAngle)*finalxSpeedBall1+Math.sin(collisionAngle+Math.PI/2)*finalySpeedBall1);
                    //Log.e(TAG, "finalDvy " + finalDvy);

                    if (finalDvx < 0){
                        if (dvx > 0){
                            dvx *= -1;
                        }
                    } else if (finalDvx > 0){
                        if (dvx < 0){
                            dvx *= -1;
                        }
                    }

                    if (finalDvy < 0){
                        if (dvy > 0){
                            dvy *= -1;
                        }
                    } else if (finalDvy > 0){
                        if (dvy < 0){
                            dvy *= -1;
                        }
                    }

                    float otherBallFinalDvx = (float)(Math.cos(collisionAngle)*finalxSpeedBall2+Math.cos(collisionAngle+Math.PI/2)*finalySpeedBall2);
                    //Log.e(TAG, "otherBallFinalDvx " + otherBallFinalDvx);
                    float otherBallFinalDvy = (float)(Math.sin(collisionAngle)*finalxSpeedBall2+Math.sin(collisionAngle+Math.PI/2)*finalySpeedBall2);
                    //Log.e(TAG, "otherBallFinalDvy " + otherBallFinalDvy);

                    if (otherBallFinalDvx < 0){
                        if (otherBall.dvx > 0){
                            otherBall.dvx *= -1;
                        }
                    } else if (otherBallFinalDvx > 0){
                        if (otherBall.dvx < 0){
                            otherBall.dvx *= -1;
                        }
                    }

                    if (otherBallFinalDvy < 0){
                        if (otherBall.dvy > 0){
                            otherBall.dvy *= -1;
                        }
                    } else if (otherBallFinalDvy > 0){
                        if (otherBall.dvy < 0){
                            otherBall.dvy *= -1;
                        }
                    }

                    float initialLen = Utils.getVectorMagnitude(dvx, dvy);
                    //Log.e(TAG, "initialLen " + initialLen);
                    float otherBallInitialLen = Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy);
                    //Log.e(TAG, "otherBallInitialLen " + otherBallInitialLen);

                    float finalLen = Utils.getVectorMagnitude(finalDvx, finalDvy);
                    //Log.e(TAG, "finalLen " + finalLen);
                    float otherBallFinalLen = Utils.getVectorMagnitude(otherBallFinalDvx, otherBallFinalDvy);
                    //Log.e(TAG, "otherBallFinalLen " + otherBallFinalLen);

                    float initialAngle =  (float) Math.toDegrees(Math.atan2(dvy, dvx));
                    //Log.e(TAG, "initialAngle " + initialAngle);
                    float otherBallInitialAngle =  (float) Math.toDegrees(Math.atan2(otherBall.dvy, otherBall.dvx));
                    //Log.e(TAG, "otherBallInitialAngle " + otherBallInitialAngle);

                    float finalAngle =  (float) Math.toDegrees(Math.atan2(finalDvy, finalDvx));
                    //Log.e(TAG, "finalAngle " + finalAngle);
                    float otherBallFinalAngle =  (float) Math.toDegrees(Math.atan2(otherBallFinalDvy, otherBallFinalDvx));
                    //Log.e(TAG, "otherBallFinalAngle " + otherBallFinalAngle);

                    changeSpeedAndAngleAfterBallCollision(finalAngle > initialAngle, finalLen > initialLen);
                    otherBall.changeSpeedAndAngleAfterBallCollision(otherBallFinalAngle > otherBallInitialAngle, otherBallFinalLen > otherBallInitialLen);

                    //dvx = (float)(Math.cos(collisionAngle)*finalxSpeedBall1+Math.cos(collisionAngle+Math.PI/2)*finalySpeedBall1);
                    //dvy = (float)(Math.sin(collisionAngle)*finalxSpeedBall1+Math.sin(collisionAngle+Math.PI/2)*finalySpeedBall1);
                    //otherBall.dvx = (float)(Math.cos(collisionAngle)*finalxSpeedBall2+Math.cos(collisionAngle+Math.PI/2)*finalySpeedBall2);
                    //otherBall.dvy = (float)(Math.sin(collisionAngle)*finalxSpeedBall2+Math.sin(collisionAngle+Math.PI/2)*finalySpeedBall2);
                    
                    //double magBall1After = Math.sqrt((dvx * dvx)+(dvy * dvy));
                    //verifyMagnitudeVariation((float)magBall1, (float)magBall1After, velocityVariation * 2);
                    //double magBall2After = Math.sqrt((otherBall.dvx * otherBall.dvx)+(otherBall.dvy * otherBall.dvy));
                    //otherBall.verifyMagnitudeVariation((float)magBall2, (float)magBall2After, otherBall.velocityVariation * 2);

                    //dvx = (float)Utils.getXRotatedFromRad(f1x, f1y, -theta);
                    //dvy = (float)Utils.getYRotatedFromRad(f1x, f1y, -theta);
                    //otherBall.dvx = (float)Utils.getXRotatedFromRad(f2x, f2y, -theta);
                    //otherBall.dvy = (float)Utils.getYRotatedFromRad(f2x, f2y, -theta);

                    //Log.e("ball", "dvx após colisão elastica bola 1 " + dvx + " " + dvy);
                    //Log.e("ball", "dvy após colisão elastica bola 2 " + otherBall.dvx + " " + otherBall.dvy);

                    /*
                    // calcula o angulo em que as bolas estão colidindo
                    double collisionAngle = Math.atan2(positionY - otherBall.positionY, positionX - otherBall.positionX);
                    //Log.e("ball", "collisionAngle " + Math.toDegrees(collisionAngle));

                    // calcula o angulo em que as bolas estão se movendo

                    //Log.e("ball", "dv inicial " + dvx + " dvy " + dvy);

                    double thisDirection = Math.atan2(dvy, dvx);
                    //Log.e("ball", "thisDirection " + Math.toDegrees(thisDirection));
                    double otherDirection = Math.atan2(otherBall.dvy, otherBall.dvx);
                    //Log.e("ball", "otherDirection " + Math.toDegrees(otherDirection));

                    // calcula a magnitude das velocidades
                    double thisVelocityLen = Utils.getVectorMagnitude(dvx, dvy);
                    //Log.e("ball", "thisVelocityLen " + thisVelocityLen);
                    double otherVelocityLen = Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy);
                    //Log.e("ball", "otherVelocityLen " + otherVelocityLen);

                    // rotaciona as velocidades, de modo que o ponto de colisão seja perpendicular ao eixo y
                    double v1x = thisVelocityLen * Math.cos(thisDirection - collisionAngle);
                    //Log.e("ball", "v1x " + v1x);
                    double v1y = thisVelocityLen * Math.sin(thisDirection - collisionAngle);
                    //Log.e("ball", "v1y " + v1y);
                    //Log.e("ball", "thisVelocityLen " + Utils.getVectorMagnitude(v1x, v1y));

                    double v2x = otherVelocityLen * Math.cos(otherDirection - collisionAngle);
                    //Log.e("ball", "v2x " + v2x);
                    double v2y = otherVelocityLen * Math.sin(otherDirection - collisionAngle);
                    //Log.e("ball", "v2y " + v2y);
                    //Log.e("ball", "otherVelocityLen " + Utils.getVectorMagnitude(v2x, v2y));


                    // calcula as velocidades resultantes da colisão, convervando a energia cinética
                    double f1x = ((v1x * (mass - otherBall.mass)) + (2 * otherBall.mass * v2x)) / (mass + otherBall.mass);
                    //Log.e("ball", "f1x " + f1x);
                    double f2x = ((v2x * (otherBall.mass - mass)) + (2 * mass * v1x)) / (mass + otherBall.mass);
                    //Log.e("ball", "f2x " + f2x);
    
                    dvx = (float)(Math.cos(collisionAngle)*f1x+Math.cos(collisionAngle+Math.PI/2)*v1y);
                    dvy = (float)(Math.sin(collisionAngle)*f1x+Math.sin(collisionAngle+Math.PI/2)*v1y);

                    //Log.e("ball", "dv antes da colisão " + dvx + " " + dvy);
                    //Log.e("ball", "dv initial len -----" + Utils.getVectorMagnitude(dvx, dvy));

                    otherBall.dvx = (float)(Math.cos(collisionAngle)*f2x+Math.cos(collisionAngle+Math.PI/2)*v2y);
                    otherBall.dvy = (float)(Math.sin(collisionAngle)*f2x+Math.sin(collisionAngle+Math.PI/2)*v2y);

                    //double v1 = Math.sqrt(Math.pow(f1x,2) * Math.pow(f1x,2) + v1y * Math.pow(v1y,2));
                    //Log.e("ball", "v1 " + v1);
                    //double v2 = Math.sqrt(Math.pow(f2x,2) * Math.pow(f2x,2) + v2y * Math.pow(v2y,2));
                    //Log.e("ball", "v2 " + v2);

                    // calcula a direção final
                    //double direction1 = Math.atan2(v1y, f1x) + collisionAngle;
                    //Log.e("ball", "direction1 " + Math.toDegrees(direction1));
                    //double direction2 = Math.atan2(v2y, f2x) + collisionAngle;
                    //Log.e("ball", "direction2 " + Math.toDegrees(direction2));

                    //dvy = (float)(Math.sin(direction1) * v1);
                    //dvx = (float)(Math.cos(direction1) * v1);
                    
                    
                    */
                    //??????????????????/////////////////////////////////////checkDataAfterAnotherBallCollision();

                    //Log.e("ball", "dv final " + dvx + " " + dvy);
                    //Log.e("ball", "dv final len --------" + Utils.getVectorMagnitude(dvx, dvy));

                    //otherBall.dvy = (float) (Math.sin(direction2) * v2);
                    //otherBall.dvx = (float) (Math.cos(direction2) * v2);

                    //Log.e("ball", "otherBall.dv initial" + otherBall.dvx + " " + otherBall.dvy);
                    //Log.e("ball", "otherBall.dv len  --------" + Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy));

                    //??????????????????/////////////////////////////////otherBall.checkDataAfterAnotherBallCollision();

                    //Log.e("ball", "otherBall.dv final" + otherBall.dvx + " " + otherBall.dvy);
                    //Log.e("ball", "otherBall.dv len  --------" + Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy));

                    otherBall.ballsCollidedProcessed.add(this);

                    addQuarantineBall(otherBall);
                    otherBall.addQuarantineBall(this);

                    //Log.e(TAG, "DADOS FINAIS");

                    //Log.e(TAG, "------------------BOLA1");
                    //Log.e(TAG, "                  textureMap "+textureMap);
                    //Log.e(TAG, "                  dvx "+ (dvx));
                    //Log.e(TAG, "                  dvy "+ (dvy));
                    //Log.e(TAG, "                  collisionAngle " + Math.toDegrees(Math.atan2(dvy, dvx)));


                    //Log.e(TAG, "------------------BOLA2");
                    //Log.e(TAG, "                  textureMap "+otherBall.textureMap);
                    //Log.e(TAG, "                  dvx "+ (otherBall.dvx));
                    //Log.e(TAG, "                  dvy "+ (otherBall.dvy));
                    //Log.e(TAG, "                  collisionAngle " + Math.toDegrees(Math.atan2(otherBall.dvy, otherBall.dvx)));
                    /*
                    float projectedDistanceBetweenBall = Vector.distanceBetweenTwoPoints(positionX + dvx, positionY + dvy, otherBall.positionX + dvx, otherBall.positionY + dvy);
                    float radiusOfTwoBalls = radius + otherBall.radius;

                    //Log.e(TAG, ">>>               projectedDistanceBetweenBall "+projectedDistanceBetweenBall);
                    //Log.e(TAG, ">>>               radiusOfTwoBalls "+radiusOfTwoBalls);

                    if (projectedDistanceBetweenBall <= radiusOfTwoBalls) {
                        //Log.e(TAG, ">>>           bolas irão colidir no proximo frame ");

                            if (dvx > 0 & otherBall.dvx < 0) {
                                if (positionX < otherBall.positionX) {
                                    //Log.e(TAG, ">>>positionX < otherBall.positionX");
                                    dvx *= -1;
                                } else {
                                    //Log.e(TAG, ">>>>positionX > otherBall.positionX");
                                    otherBall.dvx *= -1;
                                }
                            } else if (dvy > 0 & otherBall.dvy < 0) {
                                if (positionY < otherBall.positionY) {
                                    //Log.e(TAG, ">>>positionY < otherBall.positionY");
                                    dvy *= -1;
                                } else {
                                    //Log.e(TAG, ">>>>positionY > otherBall.positionY");
                                    otherBall.dvy *= -1;
                                }
                            }

                        projectedDistanceBetweenBall = Vector.distanceBetweenTwoPoints(positionX + dvx, positionY + dvy, otherBall.positionX + dvx, otherBall.positionY + dvy);

                        //Log.e(TAG, ">>>               NOVA projectedDistanceBetweenBall "+projectedDistanceBetweenBall);
                        //Log.e(TAG, ">>>               radiusOfTwoBalls "+radiusOfTwoBalls);
                    }
                    */

                }
            }
        }

        if (impulsion != 0f){
            //Log.e("ball", "impulsion "+impulsion);
            float iDvx = dvx;
            float iDvy = dvy;
            dvx *= (1+impulsion);
            dvy *= (1+impulsion);
            accelerate(500, iDvx, iDvy);
        }

        if (lastResponseBallX != 0f && lastResponseBallY != 0f && !collidedProcessed){
            if (collisionBar){
                //Log.e("ball", "colisão com barra, zera response X");
                lastResponseBallX = 0f;
                
                Bar barCollided = (Bar) collisionsData.get(collisionBarNumber).object;

                //Log.e(TAG, "ATENÇÃO ------------------------------------------------------");
                //Log.e(TAG, "colisão com a barra e com responseX e responseY diferentes de 0");
                //Log.e(TAG, "positionY + radius "+ (positionY + radius));
                //Log.e(TAG, "bar.positionY "+ barCollided.positionY);
                                
                if (positionY + radius > barCollided.positionY){
                    //Log.e(TAG, "ajustando positionY da bola");
                    //Log.e(TAG, "positionY antes " + positionY);
                    positionY -= (positionY + (radius*1.001)) - barCollided.positionY;
                    //Log.e(TAG, "positionY depois " + positionY);
                }
                
            } else {

                int numberOfCollisionsObjects = 0;
                boolean sameObject = false;
                for (int i = 0; i < collisionsData.size(); i++){
                    if (!collisionsData.get(i).isRepeated){
                        numberOfCollisionsObjects += 1;
                    }
                }


                if (numberOfCollisionsObjects == 1 && !collisionOtherBall){
                    if (Math.abs(lastResponseBallX) > Math.abs(lastResponseBallY)){
                        //Log.e("ball", "zera response Y");
                        lastResponseBallY = 0f;
                    } else {
                        //Log.e("ball", "zera response X");
                        lastResponseBallX = 0f;
                    }


                    Log.e("ball", "após analise colisão de um objeto" + "lastResponseBallX "+lastResponseBallX + " - lastResponseBallY "+lastResponseBallY);

                } else if(numberOfCollisionsObjects > 1 && !collisionOtherBall){
                    //Log.e("ball", "lidando com dois objetos colididos");

                    //Log.e("ball", "antes da analise");
                    //Log.e("ball", "lastResponseBallX "+lastResponseBallX);
                    //Log.e("ball", "lastResponseBallY "+lastResponseBallY);

                    boolean oppositeX = false;
                    boolean oppositeY = false;
                    int signalX = 0;
                    int signalY = 0;
                    for (int i = 0; i < collisionsData.size(); i++){
                        if (!collisionsData.get(i).isRepeated){
                            if (collisionsData.get(i).responseX > 0){
                                    //Log.e("ball", "collisionsData.get(i).responseX > 0");
                                if (signalX == 0){
                                    //Log.e("ball", "signalX == 0");
                                    signalX = 1;
                                } else if (signalX == -1 && !oppositeX){
                                    //Log.e("ball", "signalX == -1 && !oppositeY");
                                    oppositeX = true;
                                }
                            } else if (collisionsData.get(i).responseX < 0){
                                //Log.e("ball", "ollisionsData.get(i).responseX < 0");
                                if (signalX == 0){
                                    //Log.e("ball", "signalX == 0");
                                    signalX = -1;
                                } else if (signalX == 1 && !oppositeX){
                                    //Log.e("ball", "signalX == 1 && !opposite");
                                    oppositeX = true;
                                }
                            }
                            if (collisionsData.get(i).responseY < 0){
                                if (signalY == 0){
                                    signalY= 1;
                                } else if (signalY == -1 && !oppositeY){
                                    oppositeY = true;
                                }
                            } else if (collisionsData.get(i).responseY < 0){
                                if (signalY == 0){
                                    signalY= -1;
                                } else if (signalY == 1 && !oppositeY){
                                    oppositeY = true;
                                }
                            }
                        }
                    }

                    if (oppositeX){
                        lastResponseBallX = 0f;
                    }
                    if (oppositeY){
                        lastResponseBallY = 0f;
                    }

                    /*
                    if (lastResponseBallX != 0 && lastResponseBallY != 0){
                        if (Math.abs(lastResponseBallX) > Math.abs(lastResponseBallY)){
                            //Log.e(TAG, "zerando lastResponseBallY");
                            lastResponseBallY = 0;
                        } else if (Math.abs(lastResponseBallY) > Math.abs(lastResponseBallX)){
                            lastResponseBallX = 0;
                            //Log.e(TAG, "zerando lastResponseBallX");
                        }


                    }
                    */

                    Log.e("ball", "após analise colisão de mais de um objeto" + "lastResponseBallX "+lastResponseBallX + " - lastResponseBallY "+lastResponseBallY);


                }
            }
        }

        // AJUSTA A VELOCIDADE DA BOLA

        if (!collisionOtherBall && !collidedProcessed){
            if (lastResponseBallX < 0 && this.dvx > 0) {
                dvx *= -1;
                if (accelStarted) {
                    accelFinalVelocityX *= -1;
                    accelInitialVelocityX *= -1;
                }
            }

            if (lastResponseBallX > 0 && this.dvx < 0) {
                dvx *= -1;
                if (accelStarted) {
                    accelFinalVelocityX *= -1;
                    accelInitialVelocityX *= -1;
                }
            }

            if (lastResponseBallY < 0 && this.dvy > 0) {
                dvy *= -1;
                if (accelStarted) {
                    accelFinalVelocityY *= -1;
                    accelInitialVelocityY *= -1;
                }
            }

            if (lastResponseBallY > 0 && this.dvy < 0) {
                dvy *= -1;
                if (accelStarted) {
                    accelFinalVelocityY *= -1;
                    accelInitialVelocityY *= -1;
                }
            }
        }

        if(collisionBar){

            if (isFake){
                Level.levelObject.levelGoalsObject.notifyFakeBallHited();
            }

            lastBarCollisionTime = System.currentTimeMillis();
            //Log.e(TAG, " setting lastBarCollisionTime "+lastBarCollisionTime);

            if (ballBehaviourData == null){
                ballBehaviourData = new BallBehaviourData(this);
            } else {
                ballBehaviourData.clear();
            }

            ballBehaviourData.setMinAngle(minAngle);
            ballBehaviourData.setMaxAngle(maxAngle);

            float initialAngle = (float)Math.toDegrees(Math.atan2(Math.abs(dvy), Math.abs(dvx)));
            ballBehaviourData.setInitialAngle(initialAngle);

            Bar barCollided = (Bar) collisionsData.get(collisionBarNumber).object;
            barCollided.shineAfterBallCollision.values.get(0)[1] = barCollided.shine.numberForAnimation2;
            barCollided.shineAfterBallCollision.start();

            if (isAlive){
                if (textureColorId == COLOR_BALL_RED){
                    barCollided.setBarColor(Bar.COLOR_RED);
                } else if (textureColorId == COLOR_BALL_BLUE){
                    barCollided.setBarColor(Bar.COLOR_BLUE);
                } else if (textureColorId == COLOR_BALL_GREEN){
                    barCollided.setBarColor(Bar.COLOR_GREEN);
                } else if (textureColorId == COLOR_BALL_YELLOW){
                    barCollided.setBarColor(Bar.COLOR_YELLOW);
                } else if (textureColorId == COLOR_BALL_ORANGE){
                    barCollided.setBarColor(Bar.COLOR_ORANGE);
                } else if (textureColorId == COLOR_BALL_PINK){
                    barCollided.setBarColor(Bar.COLOR_PINK);
                } else if (textureColorId == COLOR_BALL_PURPLE){
                    barCollided.setBarColor(Bar.COLOR_PURPLE);
                } else{
                    barCollided.setBarColor(Bar.COLOR_BLACK);
                }
            }

            float mAngleToRotate = 0;
            boolean velocityAdd = true;
            //Log.e("ball", "velocity add "+lastObjects.get(this.collisionBarNumber).vx);



            //console.log("this.velocityVariation", this.velocityVariation);

            float angleToAdd = 0f;
            if (Acelerometer.moveStatus == Acelerometer.MOVE_LEFT || Acelerometer.moveStatus == Acelerometer.MOVE_RIGHT) {
                if (dvx > 0f) {
                    if (Acelerometer.moveStatus == Acelerometer.MOVE_LEFT) {
                        //Log.e(TAG, "move left 1");
                        angleToAdd = angleToRotate;
                        ballBehaviourData.setAngleDecreaseWithBarInclination();
                    } else if (Acelerometer.moveStatus == Acelerometer.MOVE_RIGHT) {
                        //Log.e(TAG, "move right 1");
                        angleToAdd = -angleToRotate;
                        ballBehaviourData.setAngleIncreaseWithBarInclination();
                    }
                } else if (dvx < 0f) {
                    if (Acelerometer.moveStatus == Acelerometer.MOVE_LEFT) {
                        //Log.e(TAG, "move left 2");
                        angleToAdd = angleToRotate;
                        ballBehaviourData.setAngleIncreaseWithBarInclination();
                    } else if (Acelerometer.moveStatus == Acelerometer.MOVE_RIGHT) {
                        //Log.e(TAG, "move right 2");
                        angleToAdd = -angleToRotate;
                        ballBehaviourData.setAngleDecreaseWithBarInclination();
                    }
                }
                //else if (dvx == 0f) {
                //if (Acelerometer.moveStatus == Acelerometer.MOVE_LEFT) {
                //    //Log.e(TAG, "move left 3");
                //    angleToAdd = angleToRotate;
                //} else if (Acelerometer.moveStatus == Acelerometer.MOVE_RIGHT) {
                //    //Log.e(TAG, "move right 3");
                //    angleToAdd = -angleToRotate;
                //}
                lastBarCollisionTime -= 600;
            }
            
            //Log.e(TAG, "angleToAdd "+ angleToAdd);

            float totalResponseX = 0;
            boolean collidedWithLeftBorder = false;
            boolean collidedWithRightBorder = false;
            for (int i = 0; i < collisionsData.get(collisionBarNumber).object.collisionsData.size(); i++) {
                //Log.e(TAG, i+" name  " + collisionsData.get(collisionBarNumber).object.collisionsData.get(i));
                totalResponseX += collisionsData.get(collisionBarNumber).object.collisionsData.get(i).responseX;
                if (collisionsData.get(collisionBarNumber).object.collisionsData.get(i).object.type == Entity.TYPE_LEFT_BORDER){
                    collidedWithLeftBorder = true;
                } else if (collisionsData.get(collisionBarNumber).object.collisionsData.get(i).object.type == Entity.TYPE_RIGHT_BORDER){
                    collidedWithRightBorder = true;
                }
            }

            //Log.e(TAG, "collidedWithLeftBorder " + collidedWithLeftBorder);
            //Log.e(TAG, "collidedWithRightBorder " + collidedWithRightBorder);

            //Log.e(TAG, "collisionsData.get(collisionBarNumber).object.vx " + collisionsData.get(collisionBarNumber).object.vx);
            //Log.e(TAG, "totalResponseX " + totalResponseX);

            float speedToConsider = collisionsData.get(collisionBarNumber).object.vx + totalResponseX;

            if (speedToConsider < 0 && collidedWithLeftBorder){
                speedToConsider = 0;
            } else if (speedToConsider > 0 && collidedWithRightBorder){
                speedToConsider = 0;
            }


            //Log.e(TAG, "speedToConsider " + speedToConsider);

            if (dvx < 0 && speedToConsider < 0){
                velocityAdd = false;
                mAngleToRotate = angleToRotate + angleToAdd;
                ballBehaviourData.setAngleIncreaseWithBarMovement();
            } else if (dvx < 0 && speedToConsider > 0){
                velocityAdd = true;
                mAngleToRotate = - (angleToRotate + angleToAdd);
                ballBehaviourData.setAngleDecreaseWithBarMovement();
            } else if (dvx > 0 && speedToConsider > 0){
                velocityAdd = false;
                mAngleToRotate = -(angleToRotate + angleToAdd);
                ballBehaviourData.setAngleIncreaseWithBarMovement();
            } else if (dvx > 0 && speedToConsider < 0){
                velocityAdd = true;
                mAngleToRotate = angleToRotate + angleToAdd;
                ballBehaviourData.setAngleDecreaseWithBarMovement();
            } else if (speedToConsider == 0){
                //Log.e(TAG, "barra sem velocidade, adicionando apenas o angleToAdd");
                mAngleToRotate = angleToAdd;
            }
            
            //Log.e(TAG, "mAngleToRotate após add "+ mAngleToRotate);

            float percentageOfBarAccelerationApplied = 0.75f;
            if (speedToConsider != 0) {
                percentageOfBarAccelerationApplied = 0.75f + (barCollided.accelPercentage * 0.5f);
                if (percentageOfBarAccelerationApplied >= 1.2 && !isFake) {
                    Level.levelObject.levelGoalsObject.ballReachedWithMaximunBarSpped();
                }
                mAngleToRotate = (mAngleToRotate) + (mAngleToRotate * 0.5f * collisionsData.get(this.collisionBarNumber).object.accelPercentage);
            }

            //Log.e(TAG, "mAngleToRotate após ajuste pela velocidade da barra "+ mAngleToRotate);

            vx = dvx;
            vy = dvy;

            float lenOfInitialVelocity = Utils.getVectorMagnitude(initialDVX, initialDVY);
            //Log.e("ball", "initialLen "+lenOfInitialVelocity);

            float maxLen = lenOfInitialVelocity * velocityMax_BI;
            //Log.e("ball", "maxLen "+maxLen);

            float minLen = lenOfInitialVelocity * velocityMin_BI;
            //Log.e("ball", "minLen "+minLen);

            float initialLen = Utils.getVectorMagnitude(dvx, dvy);

            ballBehaviourData.setInitialLen(initialLen);
            ballBehaviourData.setMaxLen(maxLen);
            ballBehaviourData.setMinLen(minLen);

            float scalePorcentage = 1f;

            final_vx  = vx;
            final_vy  = vy;

            if (speedToConsider == 0 && mAngleToRotate == 0) {

                ballBehaviourData.notifyNotSpeedChange();
                
                ballBehaviourData.setFinalLen(initialLen);
                ballBehaviourData.setFinalAngle(initialAngle);

                dvx = final_vx;
                dvy = final_vy;

                //Log.e(TAG, "mAngleToRotate após ajuste pela velocidade da barra "+ mAngleToRotate);
                //Log.e(TAG, "            final dv "+ final_vx + " " + final_vy);

            } else {

                // VELOCIDADELog.e("ball", "barra em movimento, alterando a velocidade ");
                
                if (speedToConsider != 0) {
                    
                    float velocityScalePorcentage = ((maxLen - minLen) * velocityVariation)/(maxLen - minLen);
                    //Log.e("ball", "velocityScalePorcentage antes "+velocityScalePorcentage);

                    velocityScalePorcentage *= 1 + (percentageOfBarAccelerationApplied * 0.5f);
                    //Log.e("ball", "percentageOfBarAccelerationApplied " + percentageOfBarAccelerationApplied);
                    //Log.e("ball", "velocityScalePorcentage depois " + velocityScalePorcentage);


                    if (velocityAdd == true){
                        scalePorcentage += velocityScalePorcentage;
                    } else  {
                        scalePorcentage -= velocityScalePorcentage;
                    }

                    float possibleVelocityLen = Utils.getVectorMagnitude(vx * scalePorcentage, vy * scalePorcentage);
                    //Log.e("ball", "possibleVelocityLen");

                    if (possibleVelocityLen > minLen && possibleVelocityLen < maxLen){
                        //Log.e("ball", "ajustando velocidade maior ou menor");
                        final_vx = final_vx * scalePorcentage;
                        final_vy = final_vy * scalePorcentage;
                        //Log.e(TAG, "            final dv "+ final_vx + " " + final_vy);
                    } else {
                        //Log.e("ball", "velocidade maior que o máximo ou menor que o mínimo");
                        if (possibleVelocityLen <= minLen){
                            //Log.e("ball", "velocidade menor que o mínimo");
                            final_vx = final_vx * scalePorcentage;
                            final_vy = final_vy * scalePorcentage;
                            float lenAfter = Utils.getVectorMagnitude(final_vx, final_vy);
                            //Log.e("ball", "Len se aplicado: "+lenAfter+ " minLen: "+minLen);
                            float scaleToMin = minLen/lenAfter;
                            //Log.e("ball", "scaleToMin: "+scaleToMin);
                            final_vx = final_vx * scaleToMin;
                            final_vy = final_vy * scaleToMin;
                            //Log.e("ball", "scaleFinal: "+Utils.getVectorMagnitude(final_vx, final_vy));
                        } else if (possibleVelocityLen >= maxLen){
                            //Log.e("ball", "velocidade maior que o máximo");
                            final_vx = final_vx * scalePorcentage;
                            final_vy = final_vy * scalePorcentage;
                            float lenAfter = Utils.getVectorMagnitude(final_vx, final_vy);
                            //Log.e("ball", "Len se aplicado: "+lenAfter+ " maxLen: "+maxLen);
                            float scaleToMax = maxLen/lenAfter;
                            //Log.e("ball", "scaleToMax: "+scaleToMax);
                            final_vx = final_vx * scaleToMax;
                            final_vy = final_vy * scaleToMax;
                            //Log.e("ball", "scaleFinal: "+Utils.getVectorMagnitude(vx, final_vy));
                        }
                        //Log.e(TAG, "            final dv "+ final_vx + " " + final_vy);
                    }

                }
                // ROTAÇÃO
                rotateTestingAngle(final_vx, final_vy, mAngleToRotate, true);
                ballBehaviourData.setFinalLen(Utils.getVectorMagnitude(final_vx, final_vy));
            }
            accelerate(150, final_vx, final_vy);
        }

        // AJUSTA O ALVO ATINGIDO
        
        boolean targetHitted = false;

        if (!isFake){
            for (int i = 0; i < collisionsData.size(); i++) {
                if (collisionsData.get(i).object.type == Entity.TYPE_TARGET && !collisionsData.get(i).isRepeated){
                    BrickBackground.ballCollidedFx = 40;
                    Target target = (Target)collisionsData.get(i).object;

                    target.onBallCollision();

                    targetHitted = true;

                    Game.resetTimeForPointsDecay();
                    if (target.special == 1 && !listenForExplosion){
                        waitForExplosion();
                    }
                    
                    verifyFakeBall();
                }
            }
        }


        // TOCA O SOM ADEQUADO

        if (!collidedProcessed) {
            float soundX = positionX / Game.gameAreaResolutionX;
            float volumeD = 0.5f;
            float volumeE = 0.5f;


            if (soundX < 0.1f){
                volumeE = 1f;
                volumeD = 0.55f;
            } else if (soundX >= 0.1f && soundX < 0.2f){
                volumeE = 0.95f;
                volumeD = 0.6f;
            } else if (soundX >= 0.2f && soundX < 0.3f){
                volumeE = 0.9f;
                volumeD = 0.65f;
            } else if (soundX >= 0.3f && soundX < 0.4f){
                volumeE = 0.85f;
                volumeD = 0.7f;
            } else if (soundX >= 0.4f && soundX < 0.5f){
                volumeE = 0.8f;
                volumeD = 0.75f;
            } else if (soundX >= 0.5f && soundX < 0.6f){
                volumeE = 0.75f;
                volumeD = 0.8f;
            } else if (soundX >= 0.6f && soundX < 0.7f){
                volumeE = 0.7f;
                volumeD = 0.85f;
            } else if (soundX >= 0.7f && soundX < 0.8f){
                volumeE = 0.65f;
                volumeD = 0.9f;
            } else if (soundX >= 0.8f && soundX < 0.9f){
                volumeE = 0.6f;
                volumeD = 0.95f;
            } else if (soundX >= 0.9f && soundX <= 1f){
                volumeE = 0.55f;
                volumeD = 1f;
            }
            
            //Log.e("ball", "volume E "+ volumeE + " volumeD "+ volumeD);
            if (!targetHitted) {
                Sound.playBallHit();
            }

            if (targetHitted){
                Game.vibrate(Game.VIBRATE_TARGET);
            } else if (collisionBar){
                Game.vibrate(Game.VIBRATE_BAR);
            } else {
                Game.vibrate(Game.VIBRATE_SMALL);
            }
        }
    }
    
    private void verifyMagnitudeVariation(float magBefore, float magAfter, float maxVelocityVariation){
            if (magAfter > magBefore){
                float magPercentage = magAfter / magBefore;
                float maxMagPercentage = 1 + maxVelocityVariation;
                if (magPercentage > maxMagPercentage){    
                    
                    //Log.e(TAG,"verifyMagnitudeVariation - ajustando velocidade da bola - DIMINUINDO");
                    
                    dvx *= (maxMagPercentage / magPercentage);
                    dvy *= (maxMagPercentage / magPercentage);
                }
            } else if (magBefore < magAfter){
                float magPercentage = magAfter / magBefore;
                float minMagPercentage = 1 - maxVelocityVariation;
                if (magPercentage < minMagPercentage){    
                    
                    //Log.e(TAG,"verifyMagnitudeVariation - ajustando velocidade da bola - AUMENTANDO");
                    
                    dvx *= (minMagPercentage / magPercentage);
                    dvy *= (minMagPercentage / magPercentage);
                }
            }
    }

    private void addQuarantineBall(Ball b) {
        if (quarentineBalls == null){
            quarentineBalls = new ArrayList<>();
            quarentineBallsState = new ArrayList<>();
        }
        quarentineBalls.add(b);
        quarentineBallsState.add(6);
    }

    public void checkQuarentineBall(){
        if (quarentineBalls == null){
            quarentineBalls = new ArrayList<>();
            quarentineBallsState = new ArrayList<>();
        }

        for (int i = quarentineBalls.size() - 1; i >= 0; i--){
            if (quarentineBallsState.get(i) > 0){
                quarentineBallsState.set(i, quarentineBallsState.get(i) - 1);
            } else {
                quarentineBalls.remove(i);
                quarentineBallsState.remove(i);
            }
        }
    }
    
    
    public void verifyFakeBall(){

        //Log.e(TAG, "Level.levelObject.fakeBallPercentage "+Level.levelObject.fakeBallPercentage);

        if (Level.levelObject.fakeBallPercentage > 0.01f && !fakeBallAnimActive && !isInvencible){
            float percentage = Level.levelObject.fakeBallPercentage;
            
            if (Utils.getRandonFloat(0.0f, 1.0f) < percentage){
    
                double angle = Math.toDegrees(Math.atan2(dvy, dvx));
                //Log.e("ball", "angle " + angle);

                float angleToRotate = 0;
                if (angle > 0 && angle <= 45){
                    angleToRotate = 4f;
                } else if (angle > 45 && angle <= 90){
                    angleToRotate =  -4f;
                } else if (angle > 90 && angle <= 135){
                    angleToRotate = 4f;
                } else if (angle > 135 && angle <= 180){
                    angleToRotate = -4f;
                } else if (angle < 0 && angle >= -45){
                    angleToRotate = -4f;
                } else if (angle < -45 && angle >= -90){
                    angleToRotate = 4f;
                } else if (angle < -90 && angle >= -135){
                    angleToRotate = -4f;
                } else if (angle > -135 && angle <= -180){
                    angleToRotate = 4f;
                }
                
                
                float newPositionX = positionX;
                float newPositionY = positionY;
                float newRadius = radius;
                
                Ball ball = new Ball("ball", newPositionX, newPositionY, newRadius, textureColorId);
                ball.markAsFakeBall();
                ball.program = Game.imageProgram;
                ball.textureId = Texture.TEXTURES;


                //Log.e(TAG, "fake ball dvx dvy antes "+dvx + " " + dvy);


                if (Utils.getRandonFloat(0.0f, 1.0f) > 0.5f) {

                    ball.dvx = (float) Utils.getXRotatedFromDegrees(dvx, dvy, angleToRotate);
                    ball.dvy = (float) Utils.getYRotatedFromDegrees(dvx, dvy, angleToRotate);

                } else {

                    ball.dvx = dvx;
                    ball.dvy = dvy;

                    dvx = (float) Utils.getXRotatedFromDegrees(dvx, dvy, angleToRotate);
                    dvy = (float) Utils.getYRotatedFromDegrees(dvx, dvy, angleToRotate);
                }

                //Log.e(TAG, "fake ball dvx dvy antes "+ball.dvx + " " + ball.dvy);
                
                ball.angleToRotate = angleToRotate;
                ball.velocityVariation = velocityVariation;

                ball.velocityMax_BI = velocityMax_BI;
                ball.velocityMin_BI = velocityMin_BI;
                
                float testAngleOriginal = (float) Math.toDegrees(Math.atan2(dvy, dvx));
                float testAngleFake = (float) Math.toDegrees(Math.atan2(ball.dvy, ball.dvx));
                
                //Log.e(TAG, "fake ball angle of original "+testAngleOriginal);
                //Log.e(TAG, "fake ball angle of fake "+testAngleFake);

                ball.maxAngle = this.maxAngle;
                ball.minAngle = this.minAngle;

                ball.initialDVX = initialDVX;
                ball.initialDVY = initialDVY;

                Game.addFakeBall(ball);

                Sound.playDuplicateBall(0.7f);
                initFakeAnimation();
                ball.initFakeAnimation();

            }
        }
    }

    public void rotateTestingAngle(float vx, float vy, float angleToRotateToTest, boolean notifyBehaviour) {

        float mAngleToRotate = angleToRotateToTest;

        float possibleVelocityRotateX = (float) Utils.getXRotatedFromDegrees(vx, vy, angleToRotateToTest);
        float possibleVelocityRotateY = (float) Utils.getYRotatedFromDegrees(vx, vy, angleToRotateToTest);

        float testAngle = (float) Math.toDegrees(Math.atan2(Math.abs(possibleVelocityRotateY), Math.abs(possibleVelocityRotateX)));
        //Log.e("ball", "possible angle " + testAngle);


        float maxAngleToApply = maxAngle;
        float minAngleToApply = minAngle;

        if (Game.abdicateAngle){
            maxAngleToApply += 10f;
            minAngleToApply -= 10f;
        }

        if (testAngle < maxAngleToApply && testAngle > minAngleToApply) {
            final_vx = possibleVelocityRotateX;
            final_vy = possibleVelocityRotateY;
            if (notifyBehaviour) {
                if (ballBehaviourData != null) {
                    ballBehaviourData.notifyNotMinOrMaxAngleReached();
                    ballBehaviourData.setFinalAngle(testAngle);
                }
            }
        } else {
            if (testAngle >= maxAngleToApply) {
                //Log.e("ball", "testAngle > maxAngle");
                if (mAngleToRotate < 0f) {
                    //Log.e("ball", "angleToRotate < 0f");
                    mAngleToRotate += testAngle - maxAngleToApply;
                } else {
                    //Log.e("ball", "angleToRotate > 0f");
                    mAngleToRotate -= testAngle - maxAngleToApply;
                }
            } else if (testAngle <= minAngleToApply) {
                //Log.e("ball", "testAngle < minAngle");
                if (mAngleToRotate > 0f) {
                    //Log.e("ball", "angleToRotate > 0f");
                    mAngleToRotate -= minAngleToApply - testAngle;
                } else {
                    //Log.e("ball", "angleToRotate < 0f");
                    mAngleToRotate += minAngleToApply - testAngle;
                }
            }

            //Log.e("ball", "angulo que a bola será rotacionada " + mAngleToRotate);

            final_vx = (float) Utils.getXRotatedFromDegrees(vx, vy, mAngleToRotate);
            final_vy = (float) Utils.getYRotatedFromDegrees(vx, vy, mAngleToRotate);


            if (notifyBehaviour) {
                if (ballBehaviourData != null) {
                    ballBehaviourData.setFinalAngle((float) Math.toDegrees(Math.atan2(Math.abs(final_vy), Math.abs(final_vx))));
                }
            }
        }
    }


    private float getVelocityPercentage() {
        // TODO otimizar salvado estas variaveis???
        float initialLen = Utils.getVectorMagnitude(initialDVX, initialDVY);
        float maxLen = initialLen * velocityMax_BI;
        float minLen = initialLen * velocityMin_BI;
        float len = Utils.getVectorMagnitude(dvx, dvy);
        return (len - minLen)/(maxLen - minLen);
    }

    private float getAnglePercentage() {
        // TODO otimizar salvado estas variaveis???
        float finalAngle = (float)Math.toDegrees(Math.atan2(Math.abs(dvy), Math.abs(dvx)));
        return (finalAngle - minAngle)/(maxAngle - minAngle);
    }
    
    public void markMinAngle(){
            onMinAngle = true;
            onMaxAngle = false;  
    }
    
    public void markMaxAngle(){
            onMaxAngle = true;
            onMinAngle = false;
    }
    
    public void markNotMinOrMaxAngle(){
            onMaxAngle = false;  
            onMinAngle = false;  
    }
    
    public void markMinVelocity(){
            onMinVelocity = true;
            onMaxVelocity = false;
    }
    
    public void markMaxVelocity(){
            onMaxVelocity = true;
            onMinVelocity = false;
    }
    
    public void markNotMinOrMaxVelocity(){
            onMaxVelocity = false;  
            onMinVelocity = false;  
    }


    public void checkDataAfterAnotherBallCollision(){
        // quadrantes invertidos no eixo y
        //Log.e("ball", "teste de angulo ---------------");
        double angle = Math.toDegrees(Math.atan2(dvy, dvx));
        //Log.e("ball", "angle " + angle);

        if (angle <0) {
            angle = 360d-(angle * -1d);
        }

        float thisMinAngle = minAngle;
        float thisMaxAngle = maxAngle;

        if (angle > 90d && angle < 180d){
            // 2o quadrante, invertendo os angulos maximo e minimo para o calculo
            thisMinAngle = 180 - maxAngle;
            thisMaxAngle = 180 - minAngle;
        } else if (angle > 180d && angle < 270d){
            thisMinAngle = minAngle + 180;
            thisMaxAngle = maxAngle + 180;
        } else if (angle > 270d && angle < 360d){
            // 4o quadrante, invertendo os angulos maximo e minimo para o calculo
            thisMinAngle = 360 - maxAngle;
            thisMaxAngle = 360 - minAngle;
        }

        //Log.e("ball", "this minAngle " + thisMinAngle + "this maxAngle "+thisMaxAngle);

        //Log.e("ball", "testAngle " + angle);

        double angleToRotate = 0d;
        if (angle <= thisMinAngle) {
            angleToRotate = thisMinAngle - angle;
            //Log.e("ball", "angulo menor que o esperado, rotacao de " + angleToRotate);
        } else if (angle >= thisMaxAngle) {
            angleToRotate = thisMaxAngle - angle;
            //Log.e("ball", "angulo maior que o esperado, rotacao de " + angleToRotate);
        }
            

        if (angleToRotate != 0d) {
            //Log.e("ball", "ajuste do angulo");
            //Log.e("ball", "v antes da rotacao " + dvx + " " + dvy);

            //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));

            dvx = (float)Utils.getXRotatedFromDegrees(dvx, dvy, angleToRotate);
            dvy = (float)Utils.getYRotatedFromDegrees(dvx, dvy, angleToRotate);
            //Log.e("ball", "v depois da rotacao " + dvx + " " + dvy);
            //Log.e("ball", "angulo depois"+Math.toDegrees(Math.atan2(dvy, dvx)));
            //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));
        }


        float initialLen = Utils.getVectorMagnitude(initialDVX, initialDVY);
        float maxLen = initialLen * this.velocityMax_BI;
        float minLen = initialLen * this.velocityMin_BI;

        float actualLen = Utils.getVectorMagnitude(dvx, dvy);

        if (actualLen >= maxLen){
            //Log.e("ball", "ajustando velocidade - diminuindo");
            dvx *= (maxLen/actualLen);
            dvy *= (maxLen/actualLen);

            //Log.e("ball", "v " + dvx + " " + dvy);
            //Log.e("ball", "angulo "+Math.toDegrees(Math.atan2(dvy, dvx)));
            //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));
        } else if (actualLen <= minLen){
            //Log.e("ball", "ajustando velocidade - aumentando");
            dvx *= (minLen/actualLen);
            dvy *= (minLen/actualLen);
            //Log.e("ball", "v " + dvx + " " + dvy);
        }

        //Log.e("ball", "angulo "+Math.toDegrees(Math.atan2(dvy, dvx)));
        //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));

    }

    public void replayAlarm(){
        alarmId = Sound.playSoundPool(Sound.soundAlarm, 1, 1, 100);
    }


    private void waitForExplosion() {
        listenForExplosion = true;
        alarmId = Sound.playSoundPool(Sound.soundAlarm, 1, 1, 100);
        initialTimeWaitingExplosion = Utils.getTime();

        setBallColor(COLOR_BALL_RED);
        
        ArrayList<float[]> valuesAlphaRedBall = new ArrayList<>();
        valuesAlphaRedBall.add(new float[]{0f,1f});
        valuesAlphaRedBall.add(new float[]{0.5f,0.5f});
        valuesAlphaRedBall.add(new float[]{1f,1f});

        Animation anim = new Animation(this, "alphaExplode", "alpha", 1000, valuesAlphaRedBall, true, true);
        anim.start();
    }
    
    public void explode(){

        listenForExplosion = false;
        if (Sound.soundPool != null) {
            Sound.soundPool.stop(alarmId);
        }

        Game.vibrate(Game.VIBRATE_HARD);
        
        cleanAnimations();
/*
        ParticleGenerator pg = new ParticleGenerator("explode", x + accumulatedTranslateX, y + accumulatedTranslateY,
                TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_1_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_2_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_3_ID));
        particleGenerator = pg;

        */

        particleGenerator = ParticleGenerator.getNew(x + accumulatedTranslateX, y + accumulatedTranslateY);
        particleGenerator.activate();

        Game.sound.playExplosion();

        //Sound.playSoundPool(Sound.soundExplosion, 1, 1, 0);

        int quantityOfClones = 3;
        float distance = radius * 3;
        
        float initialDesiredVelocityLen = Utils.getVectorMagnitude(initialDVX, initialDVY);

        float desiredVelocityLen = Utils.getVectorMagnitude(dvx,dvy);

        float explodeLen = initialDesiredVelocityLen;
        if (desiredVelocityLen < initialDesiredVelocityLen) explodeLen = desiredVelocityLen;
        
        dvx = 0f;
        dvy = 0f;
        
        Vector rotateVelocity45 = new Vector(explodeLen, 0).rotate(45 *((float)Math.PI/180f));
        
        float rotateX = rotateVelocity45.x;
        float rotateY = rotateVelocity45.y;
        
        accelerate(500, rotateX, rotateY*-1);
        
        setBallColor(COLOR_BALL_BLACK);
        
        float explodeX = 0;
        float explodeY = 0;
        int explodeColor = COLOR_BALL_BLACK;
        float explodeVelocityX = 0;
        float explodeVelocityY = 0;
        float explodeRadius = radius;

        int [] explosionColorsUsed = {-1,-1,-1};

        
        for (int i = 0; i < quantityOfClones; i++){
            if (i == 0){
                explodeX = x - distance + accumulatedTranslateX;
                explodeY = y + accumulatedTranslateY;
                explodeVelocityX = rotateX * -1;
                explodeVelocityY = rotateY * -1;
            } else if (i == 1){
                explodeX = x - distance + accumulatedTranslateX;
                explodeY = y + distance + accumulatedTranslateY;
                explodeVelocityX = rotateX * -1;
                explodeVelocityY = rotateY;
            } else if (i == 2){
                explodeX = x + accumulatedTranslateX;
                explodeY = y + distance + accumulatedTranslateY;
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
            
            Ball ball = new Ball("ball", explodeX, explodeY, explodeRadius, explodeColor);
            ball.program = Game.imageProgram;
            ball.textureId = Texture.TEXTURES;

            ball.dvx = 0f;
            ball.dvy = 0f;
            

            ball.angleToRotate = angleToRotate;
            ball.velocityVariation = velocityVariation;

            ball.velocityMax_BI = velocityMax_BI;
            ball.velocityMin_BI = velocityMin_BI;

            //Log.e("ball", "this.maxAngle "+maxAngle);
            //Log.e("ball", "this.minAngle "+minAngle);

            ball.maxAngle = this.maxAngle;
            ball.minAngle = this.minAngle;

            //Log.e("ball", "ball.maxAngle "+ball.maxAngle);
            //Log.e("ball", "ball.minAngle "+ball.minAngle);

            ball.initialDVX = initialDVX;
            ball.initialDVY = initialDVY;

            Game.addBall(ball);

            ball.accelerate(500, explodeVelocityX, explodeVelocityY);
        }
        
        for (Animation a : animations){
            if (a.name.equals("alphaExplode")){
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
            color = COLOR_BALL_BLACK;
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
        Game.sound.playBallFall();
        //Sound.playSoundPool(Sound.soundBallFall, 1, 1, 0);

        if (listenForExplosion){
            Sound.soundPool.stop(alarmId);
        }

        clearParticles();

        this.isSolid = false;
        this.isCollidable = false;
        this.isMovable = false;
    }

    public void changeAngleManualy(boolean left){

        if (!isAlive || isFake){
            return;
        }

        if (left){
            rotateTestingAngle(dvx, dvy, 5, true);
            Game.sound.playAngleChange();
        } else {
            rotateTestingAngle(dvx, dvy, -5, true);
            Game.sound.playAngleChange();
        }
        dvx = final_vx;
        dvy = final_vy;


    }

    public void notifyBarMovementAfterCollision(int v) {

        //double angle = Math.toDegrees(Math.atan2(dvy, dvx));
        //Log.e("ball", "BAR MOVE DEPOIS" + angle);

        if (accelStarted == true){

            //Log.e(TAG, "------------- ALTERANDO ACELERAÇÃO");
            if (v == Acelerometer.MOVE_LEFT) {
                //Log.e(TAG, "------------- ROTATE LEFT");
                rotateTestingAngle(accelFinalVelocityX, accelFinalVelocityY, angleToRotate, true);

                if (accelFinalVelocityX > 0) {
                    ballBehaviourData.setAngleDecreaseWithBarInclination();
                } else {
                    ballBehaviourData.setAngleIncreaseWithBarInclination();
                }


            } else if (v == Acelerometer.MOVE_RIGHT) {
                //Log.e(TAG, "------------- ROTATE RIGHT");
                rotateTestingAngle(accelFinalVelocityX, accelFinalVelocityY, -angleToRotate, true);

                if (accelFinalVelocityX > 0) {
                    ballBehaviourData.setAngleIncreaseWithBarInclination();
                } else {
                    ballBehaviourData.setAngleDecreaseWithBarInclination();
                }

            }

            accelFinalVelocityX = final_vx;
            accelFinalVelocityY = final_vy;

        } else {
            //Log.e(TAG, "------------- ALTERANDO ROTAÇÃO");
            if (v == Acelerometer.MOVE_LEFT) {
                //Log.e(TAG, "------------- ROTATE LEFT");
                rotateTestingAngle(accelFinalVelocityX, accelFinalVelocityY, angleToRotate, true);

                if (accelFinalVelocityX > 0) {
                    ballBehaviourData.setAngleDecreaseWithBarInclination();
                } else {
                    ballBehaviourData.setAngleIncreaseWithBarInclination();
                }

            } else if (v == Acelerometer.MOVE_RIGHT) {
                //Log.e(TAG, "------------- ROTATE RIGHT");
                rotateTestingAngle(accelFinalVelocityX, accelFinalVelocityY, -angleToRotate, true);

                if (accelFinalVelocityX > 0) {
                    ballBehaviourData.setAngleIncreaseWithBarInclination();
                } else {
                    ballBehaviourData.setAngleDecreaseWithBarInclination();
                }

            }
            dvx = final_vx;
            dvy = final_vy;

        }
    }
}
