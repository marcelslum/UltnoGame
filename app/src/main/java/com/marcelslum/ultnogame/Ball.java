package com.marcelslum.ultnogame;
import android.util.Log;

import java.util.ArrayList;

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
    public float velocityMax_BI;
    public float velocityMin_BI;
    public float maxAngle;
    public float minAngle;

    public final int timeForExplode = 750;
    public long initialTimeWaitingExplosion = 0;

    public float rotationAngle = 0;
    boolean isInvencible = false;

    private int textureMap = COLOR_BALL_BLACK;
    
    public ArrayList<Float> historicPositionX;
    public ArrayList<Float> historicPositionY;

    Color color;
    boolean isAlive = true;
    boolean listenForExplosion = false;
    ArrayList<Target> targetsAppend;
    boolean collisionBordaB = false;
    boolean collisionBar = false;
    boolean collisionTarget = false;
    boolean collisionOtherBall = false;
    int collisionBarNumber = -1;
    boolean verifyAppendsIsFreeBall = false;
    BallParticleGenerator ballParticleGenerator;
    private int alarmId;
    
    public float lastResponseBallX = 0f;
    public float lastResponseBallY = 0f;
    public float impulsion = 0f;

    ArrayList<Ball> ballsCollidedProcessed;
    
    double mass = 0f;
    

    //todo ????_ball.lastResponseBall = V(0,0);
    //todo ????_ball.lastObjects = [];

    Ball(String name, float x, float y, float radius, int textureMap){
        super(name, x, y, radius, Game.BALL_WEIGHT);
        textureId = Texture.TEXTURE_BUTTONS_AND_BALLS;
        program = Game.imageProgram;
        this.textureMap = textureMap;
        ballsCollidedProcessed = new ArrayList<>();
        
        // volume of sphere = (4/3) * PI * r^3
        // mass = density * volume
        // considera apenas o cubo do raio, já que o resto é igual para todas as esferas
       	mass = Math.pow(radius,3);

        isMovable = true;
        historicPositionX = new ArrayList<>();
        historicPositionY = new ArrayList<>();
        setDrawInfo();
        ballParticleGenerator = new BallParticleGenerator(name+"pg", 0f, 0f);
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
                        setTextureMapAndUvData(COLOR_BALL_YELLOW);
                    } else if (innerBall.numberForAnimation == 2f) {
                        setTextureMapAndUvData(COLOR_BALL_PINK);
                    } else if (innerBall.numberForAnimation == 3f) {
                        setTextureMapAndUvData(COLOR_BALL_BLUE);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setTextureMapAndUvData(COLOR_BALL_GREEN);
                    } else if (innerBall.numberForAnimation == 4f) {
                        setTextureMapAndUvData(COLOR_BALL_RED);
                    }
                }
            });
            animBallInvencible.start();
    }

    public void setDrawInfo(){
        
        initializeData(12, 6, 8, 0);

        verticesData = new float[12];

        //Log.e("ball", " "+verticesData.length);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f - radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvDataButtonsAndBalls(uvsData, 0, textureMap);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
    
    public void setTextureMapAndUvData(int textureMap){
        this.textureMap = textureMap;
        Utils.insertRectangleUvDataButtonsAndBalls(uvsData, 0, textureMap);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
    
    @Override
    public void translate(float tx, float ty) {
        super.translate(tx, ty);
        if (isMovable && isFree){
            if (historicPositionX.size() < 20){
                historicPositionX.add(x + accumulatedTranslateX + tx);
                historicPositionY.add(y + accumulatedTranslateY + ty);
            } else {
                historicPositionX.add(0, x + accumulatedTranslateX + tx);
                historicPositionX.remove(20);
                historicPositionY.add(0, y + accumulatedTranslateY + ty);
                historicPositionY.remove(20);
            }


            int numberOfParticles;
            for (int i = 0; i < historicPositionX.size(); i++){
                if (i == 0){
                    numberOfParticles = 5;
                } else if (i == 0){
                    numberOfParticles = 4;
                } else if (i == 2){
                    numberOfParticles = 3;
                } else if (i == 4){
                    numberOfParticles = 2;
                } else if (i == 6){
                    numberOfParticles = 1;
                } else {
                    numberOfParticles = 0;
                }
                ballParticleGenerator.generate(historicPositionX.get(i), historicPositionY.get(i), radius, numberOfParticles);
            }
        }
    }

    public void clearParticles(){
        historicPositionX.clear();
        historicPositionY.clear();
    }

    public void onCollision(){

        lastResponseBallX = 0f;
        lastResponseBallY = 0f;
        impulsion = 0f;
        collisionBordaB = false;
        collisionBar = false;
        collisionBarNumber = 0;
        collisionTarget = false;
        collisionOtherBall = false;

        checkCollisionsDataObjectRepetition();

        Log.e("ball", "iniciando checagem de colisão---------------------------------");

        boolean collidedProcessed = false;

        for (int i = 0; i < collisionsData.size(); i++){
            
            lastResponseBallX += collisionsData.get(i).responseX;
            lastResponseBallY += collisionsData.get(i).responseY;

            Log.e("ball", collisionsData.get(i).object.name +" rX "+ collisionsData.get(i).responseX +" rY "+ collisionsData.get(i).responseY +" nX "+ collisionsData.get(i).normalX +" nY "+ collisionsData.get(i).normalY+" isRepeated "+collisionsData.get(i).isRepeated);

            if (collisionsData.get(i).object.name.equals("bordaB") && !collisionsData.get(i).isRepeated){
                this.collisionBordaB = true;
            }
            
            if (collisionsData.get(i).object.name.equals("bar") && !collisionsData.get(i).isRepeated){
                this.collisionBar = true;
                this.collisionBarNumber = i;
            }
            
            if (collisionsData.get(i).object.name.equals("target") && !collisionsData.get(i).isRepeated){
                this.collisionTarget = true;
            }
        
            // verifica se obstáculo esta crescendo e, se a velocidade for maior que a da bola, impulsiona-a
            if (collisionsData.get(i).object.name.equals("obstacle") && !collisionsData.get(i).isRepeated){
                Obstacle o = (Obstacle)collisionsData.get(i).object;
                if (o.scaleVariationData != null){
                    if (o.scaleVariationData.isActive){
                        if (o.scaleVariationData.widthVelocity > 0 && (o.scaleVariationData.widthVelocity * o.getTransformedWidth())/2f > Math.abs(dvx) && collisionsData.get(i).responseX != 0f){
                            impulsion += o.scaleVariationData.widthVelocity/Math.abs(dvx*0.9f);
                        }
                        if (o.scaleVariationData.heightVelocity > 0 && (o.scaleVariationData.heightVelocity * o.getTransformedHeight())/2f > Math.abs(dvy) && collisionsData.get(i).responseY != 0f){
                            impulsion += o.scaleVariationData.heightVelocity/Math.abs(dvy*0.9f);
                        }
                    }
                }
            }

            if (collisionsData.get(i).object.name == "ball" && !collisionsData.get(i).isRepeated) {
                for (int i2 = 0; i2 < ballsCollidedProcessed.size(); i2++) {
                    if (ballsCollidedProcessed.get(i2) == collisionsData.get(i).object) {
                        Log.e("ball", "collided processed");
                        collidedProcessed = true;
                    }
                }

                if (!collidedProcessed) {
                    collisionOtherBall = true;

                    Ball otherBall = (Ball) collisionsData.get(i).object;

                    // calcula o angulo em que as bolas estão colidindo
                    double collisionAngle = Math.atan2(positionY - otherBall.positionY, positionX - otherBall.positionX);
                    Log.e("ball", "collisionAngle " + Math.toDegrees(collisionAngle));

                    // calcula o angulo em que as bolas estão se movendo

                    Log.e("ball", "dv inicial " + dvx + " dvy " + dvy);

                    double thisDirection = Math.atan2(dvy, dvx);
                    Log.e("ball", "thisDirection " + Math.toDegrees(thisDirection));
                    double otherDirection = Math.atan2(otherBall.dvy, otherBall.dvx);
                    Log.e("ball", "otherDirection " + Math.toDegrees(otherDirection));

                    // calcula a magnitude das velocidades
                    double thisVelocityLen = Utils.getVectorMagnitude(dvx, dvy);
                    Log.e("ball", "thisVelocityLen " + thisVelocityLen);
                    double otherVelocityLen = Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy);
                    Log.e("ball", "otherVelocityLen " + otherVelocityLen);

                    // rotaciona as velocidades, de modo que o ponto de colisão seja perpendicular ao eixo y
                    double v1x = thisVelocityLen * Math.cos(thisDirection - collisionAngle);
                    Log.e("ball", "v1x " + v1x);
                    double v1y = thisVelocityLen * Math.sin(thisDirection - collisionAngle);
                    Log.e("ball", "v1y " + v1y);
                    Log.e("ball", "thisVelocityLen " + Utils.getVectorMagnitude(v1x, v1y));

                    double v2x = otherVelocityLen * Math.cos(otherDirection - collisionAngle);
                    Log.e("ball", "v2x " + v2x);
                    double v2y = otherVelocityLen * Math.sin(otherDirection - collisionAngle);
                    Log.e("ball", "v2y " + v2y);
                    Log.e("ball", "otherVelocityLen " + Utils.getVectorMagnitude(v2x, v2y));


                    // calcula as velocidades resultantes da colisão, convervando a energia cinética
                    double f1x = ((v1x * (mass - otherBall.mass)) + (2 * otherBall.mass * v2x)) / (mass + otherBall.mass);
                    Log.e("ball", "f1x " + f1x);
                    double f2x = ((v2x * (otherBall.mass - mass)) + (2 * mass * v1x)) / (mass + otherBall.mass);
                    Log.e("ball", "f2x " + f2x);
    
                    dvx = (float)(Math.cos(collisionAngle)*f1x+Math.cos(collisionAngle+Math.PI/2)*v1y);
                    dvy = (float)(Math.sin(collisionAngle)*f1x+Math.sin(collisionAngle+Math.PI/2)*v1y);

                    Log.e("ball", "dv antes da colisão " + dvx + " " + dvy);
                    Log.e("ball", "dv initial len -----" + Utils.getVectorMagnitude(dvx, dvy));

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

                    checkDesireVelocity();

                    Log.e("ball", "dv final " + dvx + " " + dvy);
                    Log.e("ball", "dv final len --------" + Utils.getVectorMagnitude(dvx, dvy));

                    //otherBall.dvy = (float) (Math.sin(direction2) * v2);
                    //otherBall.dvx = (float) (Math.cos(direction2) * v2);

                    Log.e("ball", "otherBall.dv initial" + otherBall.dvx + " " + otherBall.dvy);
                    Log.e("ball", "otherBall.dv len  --------" + Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy));

                    otherBall.checkDesireVelocity();

                    Log.e("ball", "otherBall.dv final" + otherBall.dvx + " " + otherBall.dvy);
                    Log.e("ball", "otherBall.dv len  --------" + Utils.getVectorMagnitude(otherBall.dvx, otherBall.dvy));

                    otherBall.ballsCollidedProcessed.add(this);
                }
            }
        }

        if (impulsion != 0f){
            Log.e("ball", "impulsion "+impulsion);
            float iDvx = dvx;
            float iDvy = dvy;
            dvx *= (1+impulsion);
            dvy *= (1+impulsion);
            this.accelerate(500, iDvx, iDvy);
        }

        
        if (lastResponseBallX != 0f && lastResponseBallY != 0f && !collidedProcessed){
            if (collisionBar){
                lastResponseBallX = 0f;
            } else {
                if (collisionsData.size() == 1 && !collisionOtherBall){
                    if (Math.abs(lastResponseBallX) > Math.abs(lastResponseBallY)){
                        Log.e("ball", "zera response Y");
                        lastResponseBallY = 0f;
                    } else {
                        Log.e("ball", "zera response X");
                        lastResponseBallX = 0f;
                    }
                } else if(collisionsData.size() > 1 && !collisionOtherBall){
                    boolean oppositeX = false;
                    boolean oppositeY = false;
                    int signalX = 0;
                    int signalY = 0;
                    for (int i = 0; i < collisionsData.size(); i++){
                        if (collisionsData.get(i).responseX > 0){
                            if (signalX == 0){
                                signalX = 1;
                            } else if (signalX == -1 && !oppositeY){
                                oppositeX = true;
                            }
                        } else if (collisionsData.get(i).responseX < 0){
                            if (signalX == 0){
                                signalX = -1;
                            } else if (signalX == 1 && !oppositeY){
                                oppositeX = true;
                            }
                        }
                        if (collisionsData.get(i).responseX < 0){
                            if (signalY == 0){
                                signalY= 1;
                            } else if (signalY == -1 && !oppositeY){
                                oppositeY = true;
                            }
                        } else if (collisionsData.get(i).responseX < 0){
                            if (signalY == 0){
                                signalY= -1;
                            } else if (signalY == 1 && !oppositeY){
                                oppositeY = true;
                            }
                        }
                    }
                    
                    if (oppositeX){
                        lastResponseBallX = 0f;
                    }
                    if (oppositeY){
                        lastResponseBallY = 0f;
                    }
                }
            }
        }

        // AJUSTA A VELOCIDADE DA BOLA

        if (!collisionOtherBall && !collidedProcessed){
            if (lastResponseBallX < 0 && this.dvx > 0) {
                this.dvx *= -1;
                if (this.accelStarted) {
                    this.accelFinalVelocityX *= -1;
                    this.accelInitialVelocityX *= -1;
                }
            }

            if (lastResponseBallX > 0 && this.dvx < 0) {
                this.dvx *= -1;
                if (this.accelStarted) {
                    this.accelFinalVelocityX *= -1;
                    this.accelInitialVelocityX *= -1;
                }
            }

            if (lastResponseBallY < 0 && this.dvy > 0) {
                this.dvy *= -1;
                if (this.accelStarted) {
                    this.accelFinalVelocityY *= -1;
                    this.accelInitialVelocityY *= -1;
                }
            }

            if (lastResponseBallY > 0 && this.dvy < 0) {
                this.dvy *= -1;
                if (this.accelStarted) {
                    this.accelFinalVelocityY *= -1;
                    this.accelInitialVelocityY *= -1;
                }
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
            if (this.dvx < 0 && collisionsData.get(this.collisionBarNumber).object.vx < 0){
                velocityAdd = false;
                angleToRotate = this.angleToRotate;
            } else if (this.dvx < 0 && collisionsData.get(this.collisionBarNumber).object.vx > 0){
                velocityAdd = true;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && collisionsData.get(this.collisionBarNumber).object.vx > 0){
                velocityAdd = false;
                angleToRotate = -this.angleToRotate;
            } else if (this.dvx > 0 && collisionsData.get(this.collisionBarNumber).object.vx < 0){
                velocityAdd = true;
                angleToRotate = this.angleToRotate;
            }

            Log.e("ball", "velocity add "+velocityAdd);
            Log.e("ball", "angleToRotate "+angleToRotate);
            
            vx = this.dvx;
            vy = this.dvy;

            float initialLen = Utils.getVectorMagnitude(initialDesireVelocityX, initialDesireVelocityY);
            Log.e("ball", "initialLen "+initialLen);

            float maxLen = initialLen * this.velocityMax_BI;
            Log.e("ball", "maxLen "+maxLen);

            float minLen = initialLen * this.velocityMin_BI;
            Log.e("ball", "minLen "+minLen);
            float scalePorcentage = 1f;

            float final_vx  = vx;
            float final_vy  = vy;

            if (angleToRotate != 0){
                if (velocityAdd == true){
                    scalePorcentage +=this.velocityVariation;
                } else  {
                    scalePorcentage -=this.velocityVariation;
                }

                float possibleVelocityLen = Utils.getVectorMagnitude(vx * scalePorcentage, vy * scalePorcentage);
                Log.e("ball", "possibleVelocityLen");

                if (possibleVelocityLen > minLen && possibleVelocityLen < maxLen){
                    Log.e("ball", "ajustando velocidade maior ou menor");
                    vx = vx * scalePorcentage;
                    vy = vy * scalePorcentage;

                    // achievemntAcelerador
                    if (velocityAdd) {
                        Achievements.getById(id do achievement).increment(1);
                    }

                } else {
                    Log.e("ball", "velocidade excede");
                }

                Vector possibleVelocityRotate = new Vector(vx, vy).rotate(angleToRotate *((float)Math.PI/180f));

                float testAngle = (float)Math.toDegrees(Math.atan2(Math.abs(possibleVelocityRotate.y), Math.abs(possibleVelocityRotate.x)));
                Log.e("ball", "possible angle "+testAngle);

                if (testAngle < maxAngle && testAngle > minAngle){
                    Log.e("ball", "rotacionando ");
                    final_vx =  possibleVelocityRotate.x;
                    final_vy =  possibleVelocityRotate.y;
                } else {
                    Log.e("ball", " não rotacionando ");
                    final_vx =  vx;
                    final_vy =  vy;
                }


                Log.e("ball", "finalLen "+Utils.getVectorMagnitude(final_vx, final_vy));


            }
            this.accelerate(150, final_vx, final_vy);
        }

        // AJUSTA O ALVO ATINGIDO

        for (int i = 0; i < collisionsData.size(); i++) {
            if (collisionsData.get(i).object.name == "target" && !collisionsData.get(i).isRepeated){
                Game.ballCollidedFx = 40;
                Target target = (Target)collisionsData.get(i).object;
                target.onBallCollision();
                Game.resetTimeForPointsDecay();
                if (target.special == 1 && !listenForExplosion){
                    waitForExplosion();
                }
            }
        }

        // TOCA O SOM ADEQUADO

        if (!collidedProcessed) {
            float soundX = positionX / Game.gameAreaResolutionX;
            float volumeD = 0.5f;
            float volumeE = 0.5f;


            if (soundX < 0.5f) {
                volumeE = (0.5f + (0.5f - soundX));
                volumeD = (0.5f - (0.5f - soundX));
            }
            if (soundX > 0.5f) {
                volumeD = (0.5f + (soundX - 0.5f));
                volumeE = (0.5f - (soundX - 0.5f));
            }

            //Log.e("ball", "volume E "+ volumeE + " volumeD "+ volumeD);
            Sound.play(Sound.soundBallHit, volumeE, volumeD, 0);
        }
    }

    public void checkDesireVelocity(){

        //Log.e("ball", "teste de angulo ---------------");
        double angle = Math.toDegrees(Math.atan2(dvy, dvx));
        //Log.e("ball", "angle " + angle);

        if (angle < 0) {
            angle = 360d-(angle * -1d);
        }

        double testAngle = angle;
        if (testAngle > 90d) {
            testAngle %= 90d;
        }

        //Log.e("ball", "testAngle " + testAngle);
        //Log.e("ball", "minAngle " + minAngle);
        //Log.e("ball", "maxAngle " + maxAngle);


        double angleToRotate = 0d;
        if (testAngle < minAngle) {
            angleToRotate = minAngle - testAngle;
            //Log.e("ball", "angulo menor que o esperado, rotacao de " + angleToRotate);

        } else if (testAngle > maxAngle) {
            angleToRotate = maxAngle - testAngle;
            //Log.e("ball", "angulo maior que o esperado, rotacao de " + angleToRotate);
        }

        if (angleToRotate != 0d) {
            //Log.e("ball", "ajuste do angulo");
            //Log.e("ball", "v antes da rotacao " + dvx + " " + dvy);

            Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));

            dvx = (float)Utils.getXRotated(dvx, dvy, angleToRotate);
            dvy = (float) Utils.getYRotated(dvx, dvy, angleToRotate);
            //Log.e("ball", "v depois da rotacao " + dvx + " " + dvy);
            //Log.e("ball", "angulo depois"+Math.toDegrees(Math.atan2(dvy, dvx)));
            //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));
        }


        float initialLen = Utils.getVectorMagnitude(initialDesireVelocityX, initialDesireVelocityY);
        float maxLen = initialLen * this.velocityMax_BI;
        float minLen = initialLen * this.velocityMin_BI;

        float actualLen = Utils.getVectorMagnitude(dvx, dvy);


        if (actualLen > maxLen){
            Log.e("ball", "ajustando velocidade - diminuindo");

            dvx *= (maxLen/actualLen);
            dvy *= (maxLen/actualLen);

            //Log.e("ball", "v " + dvx + " " + dvy);
            //Log.e("ball", "angulo "+Math.toDegrees(Math.atan2(dvy, dvx)));
            //Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));

        } else if (actualLen < minLen){
            Log.e("ball", "ajustando velocidade - aumentando");
            dvx *= (minLen/actualLen);
            dvy *= (minLen/actualLen);
            //Log.e("ball", "v " + dvx + " " + dvy);
        }

        Log.e("ball", "angulo "+Math.toDegrees(Math.atan2(dvy, dvx)));
        Log.e("ball", "len " + Utils.getVectorMagnitude(dvx, dvy));

    }

    private void waitForExplosion() {
        alarmId = Sound.play(Sound.soundAlarm, 1, 1, 100);
        initialTimeWaitingExplosion = Utils.getTime();
        listenForExplosion = true;
        setTextureMapAndUvData(COLOR_BALL_RED);
        
        ArrayList<float[]> valuesAlphaRedBall = new ArrayList<>();
        valuesAlphaRedBall.add(new float[]{0f,1f});
        valuesAlphaRedBall.add(new float[]{0.5f,0.5f});
        valuesAlphaRedBall.add(new float[]{1f,1f});

        Animation anim = new Animation(this, "alphaExplode", "alpha", 3000, valuesAlphaRedBall, true, true);
        anim.start();
    }
    
    public void explode(){
        clearAnimations();
        ParticleGenerator pg = new ParticleGenerator("explode", x + accumulatedTranslateX, y + accumulatedTranslateY,
                Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR1, Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR2, Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR3);
        Game.particleGenerator.add(pg);
        pg.activate();

        Sound.soundPool.stop(alarmId);

        Sound.play(Sound.soundExplosion1, 1, 1, 0);
        Sound.play(Sound.soundExplosion2, 1, 1, 0);

        listenForExplosion = false;

        int quantityOfClones = 3;
        float distance = radius * 3;
        
        float initialDesiredVelocityLen = Utils.getVectorMagnitude(initialDesireVelocityX,initialDesireVelocityY);

        float desiredVelocityLen = Utils.getVectorMagnitude(dvx,dvy);

        float explodeLen = initialDesiredVelocityLen;
        if (desiredVelocityLen < initialDesiredVelocityLen) explodeLen = desiredVelocityLen;
        
        dvx = 0f;
        dvy = 0f;
        
        Vector rotateVelocity45 = new Vector(explodeLen, 0).rotate(45 *((float)Math.PI/180f));
        
        float rotateX = rotateVelocity45.x;
        float rotateY = rotateVelocity45.y;
        
        accelerate(500, rotateX, rotateY*-1);
        
        setTextureMapAndUvData(COLOR_BALL_BLACK);
        
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

            //Log.e("ball", "explodeColor "+i+" "+explodeColor);
            
            Ball ball = new Ball("ball"+i, explodeX, explodeY, explodeRadius, explodeColor);
            ball.program = Game.imageProgram;
            ball.textureId = Texture.TEXTURE_BUTTONS_AND_BALLS;

            ball.dvx = 0f;
            ball.dvy = 0f;
            

            ball.angleToRotate = angleToRotate;
            ball.velocityVariation = velocityVariation;

            ball.velocityMax_BI = velocityMax_BI;
            ball.velocityMin_BI = velocityMin_BI;

            ball.maxAngle = maxAngle;
            ball.minAngle = minAngle;

            ball.initialDesireVelocityX = initialDesireVelocityX;
            ball.initialDesireVelocityY = initialDesireVelocityY;

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
