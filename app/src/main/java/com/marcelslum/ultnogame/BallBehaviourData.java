package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 07/02/2017.
 */

public class BallBehaviourData {

    public static final String TAG = "BallBehaviourData";

    long timeOfCollision;
    Ball ball;
    boolean active = false;
    boolean angleDecreasedWithBarMovement = false;
    boolean angleIncreasedWithBarMovement = false;
    boolean angleDecreasedWithBarInclination = false;
    boolean angleIncreasedWithBarInclination = false;
    boolean velocityIncreased = false;
    boolean velocityDecreased = false;
    boolean velocityChanged = false;
     boolean angleChanged = false;
    boolean minVelocityReached = false;
    boolean maxVelocityReached = false;
    boolean minAngleReached = false;
    boolean maxAngleReached = false;


    boolean onMaxVelocity = false;
    boolean onMinVelocity = false;
    boolean onMaxAngle = false;
    boolean onMinAngle = false;

    boolean lastOnMaxVelocity = false;
    boolean lastOnMinVelocity = false;
    boolean lastOnMaxAngle = false;
    boolean lastOnMinAngle = false;



    float initialLen;
    float minLen;
    float maxLen;
    float finalLen;
    float initialAngle;
    float minAngle;
    float maxAngle;
    float finalAngle;



    public BallBehaviourData(Ball b) {
        ball = b;
        clear();
    }

    public void setAngleDecreaseWithBarMovement() {
        angleDecreasedWithBarMovement = true;
    }

    public void setAngleIncreaseWithBarMovement() {
        angleIncreasedWithBarMovement = true;
    }

    public void setAngleDecreaseWithBarInclination() {
        angleDecreasedWithBarInclination = true;
    }

    public void setAngleIncreaseWithBarInclination() {
        angleIncreasedWithBarInclination = true;
    }

    public void setInitialLen(float v) {
        initialLen = v;
    }
    
    public void setFinalLen(float v) {
        finalLen = v;
    }

    public void setMaxLen(float v) {
        maxLen = v;
    }

    public void setMinLen(float v) {
        minLen = v;
    }

    public void notifyNotSpeedChange() {
        velocityChanged = false;
    }

    public void processData() {

        if (!active) return;

        onMaxVelocity = false;
        onMinVelocity = false;
        onMaxAngle = false;
        onMinAngle = false;

        //Log.e(TAG, "lastOnMaxVel " + lastOnMaxVelocity);
        //Log.e(TAG, "lastOnMinVelocity " + lastOnMinVelocity);
        //Log.e(TAG, "lastOnMaxAngle " + lastOnMaxAngle);
        //Log.e(TAG, "lastOnMinAngle " + lastOnMinAngle);


        if (finalLen > maxLen*0.99f){
            finalLen = maxLen;
            onMaxVelocity = true;
        } else if (finalLen < minLen*1.01f){
            finalLen = minLen;
            onMinVelocity = true;
        }

        if (finalAngle > maxAngle*0.99f){
            finalAngle = maxAngle;
            onMaxAngle = true;
        } else if (finalAngle < minAngle*1.01f){
            finalAngle = minAngle;
            onMinAngle = true;
        }

        Game.ballDataPanel.previousAnglePercent = (initialAngle - minAngle) / (maxAngle - minAngle);
        //Log.e(TAG, "Game.ballDataPanel.previousAnglePercent "+ Game.ballDataPanel.previousAnglePercent);
        Game.ballDataPanel.previousVelocityPercent = (initialLen - minLen) / (maxLen - minLen);
        //Log.e(TAG, "Game.ballDataPanel.previousVelocityPercent "+ Game.ballDataPanel.previousVelocityPercent);

        //Log.e(TAG, "Game.ballDataPanel.newVelocityPercent "+ ((finalLen - minLen) / (maxLen - minLen)));
        //Log.e(TAG, "Game.ballDataPanel.newAnglePercent "+ ((finalAngle - minAngle) / (maxAngle - minAngle)));
        Game.ballDataPanel.setData((finalLen - minLen) / (maxLen - minLen), (finalAngle - minAngle) / (maxAngle - minAngle), true);

        Game.ballDataPanel.ballAnimating = ball;

        logData();

        if (finalLen != initialLen){
            velocityChanged = true;   
        }

        if (finalAngle != initialAngle){
            angleChanged = true;
        }

        //Log.e(TAG, "velocityChanged " + velocityChanged);
        //Log.e(TAG, "angleChanged " + angleChanged);
            
        if (!velocityChanged){
            Level.levelObject.levelGoalsObject.notifyNotSpeedChange();
            Stats.atingirBolaSemMudarVelocidade += 1;
        }

        if (finalLen > initialLen && !lastOnMaxVelocity){
            Level.levelObject.levelGoalsObject.accelerate();
            Stats.velocidadeAumentada += 1;
        } else if (initialLen > finalLen && !lastOnMinVelocity){
            Stats.velocidadeDiminuida += 1;
            Level.levelObject.levelGoalsObject.decelerate();
        }

        if ((finalLen > initialLen) && finalAngle != minAngle && !lastOnMaxVelocity){
            Level.levelObject.levelGoalsObject.accelerateWithoutReachMinAngle();
        } else if ((initialLen > finalLen) && finalAngle != maxAngle && !lastOnMinVelocity){
            Level.levelObject.levelGoalsObject.decelerateWithoutReachMaxAngle();
        }


        if (!ball.onMinAngle){

            if (angleDecreasedWithBarInclination && !Game.abdicateAngle && !lastOnMinAngle){
                Stats.anguloDiminuidoInclinacao += 1;
            }
            if (angleDecreasedWithBarMovement && !Game.abdicateAngle && !lastOnMinAngle){
                Stats.anguloDiminuidoMovimento += 1;
            }
            if (angleDecreasedWithBarMovement && angleDecreasedWithBarInclination && !Game.abdicateAngle && !lastOnMinAngle) {
                Stats.anguloDiminuidoMovimentoInclinacao += 1;
            }
            if ((angleDecreasedWithBarInclination || angleDecreasedWithBarMovement)&& !Game.abdicateAngle && !lastOnMinAngle){
                Stats.anguloDiminuido += 1;
            }

        }


        if (!ball.onMaxAngle){
            if (angleIncreasedWithBarInclination && !Game.abdicateAngle && !lastOnMaxAngle){
                Stats.anguloAumentadoInclinacao += 1;
            }
            if (angleIncreasedWithBarMovement && !Game.abdicateAngle && !lastOnMaxAngle){
                Stats.anguloAumentadoMovimento += 1;
            }
            if (angleIncreasedWithBarMovement && angleIncreasedWithBarInclination && !Game.abdicateAngle && !lastOnMaxAngle) {
                Stats.anguloAumentadoMovimentoInclinacao += 1;
            }

            if ((angleIncreasedWithBarInclination || angleIncreasedWithBarMovement) && !Game.abdicateAngle && !lastOnMaxAngle){
                Stats.anguloAumentado += 1;
            }
        }


        if (angleDecreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement && !ball.onMinAngle  && !lastOnMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarInclination();
        } else if (angleIncreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement&& !ball.onMaxAngle && !lastOnMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarInclination();
        } else if (!angleIncreasedWithBarInclination && !angleDecreasedWithBarInclination && angleDecreasedWithBarMovement  && !ball.onMinAngle  && !lastOnMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarMovement();
        } else if (!angleDecreasedWithBarInclination && !angleIncreasedWithBarInclination && angleIncreasedWithBarMovement  && !ball.onMaxAngle && !lastOnMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarMovement();
        } else if(angleIncreasedWithBarInclination && angleIncreasedWithBarMovement && !ball.onMaxAngle && !lastOnMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedWithBarMovementAndInclination();
        } else if (angleDecreasedWithBarInclination && angleDecreasedWithBarMovement && !ball.onMinAngle  && !lastOnMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedWithBarMovementAndInclination();
        }

        if ((finalLen > initialLen) && angleIncreasedWithBarInclination && !lastOnMaxVelocity){

            if (!Game.abdicateAngle) {
                Stats.velocidadeAumentadaAnguloAumentadoInclinacao += 1;
                Level.levelObject.levelGoalsObject.accelerateWithBarIncreasingAngle();
            }
            if (finalAngle > initialAngle && !lastOnMaxAngle){
                if (!Game.abdicateAngle) {
                    Stats.anguloAumentado += 1;
                    Level.levelObject.levelGoalsObject.increaseAngle();
                }

            } else if (finalAngle > initialAngle && !lastOnMinAngle){
                if (!Game.abdicateAngle) {
                    Stats.anguloDiminuido += 1;
                    Level.levelObject.levelGoalsObject.decreaseAngle();
                }

            }
        } else if ((initialLen > finalLen) && angleDecreasedWithBarInclination  && !lastOnMinVelocity){

            if (!Game.abdicateAngle) {
                Stats.velocidadeDiminuidaAnguloDiminuidoInclinacao += 1;
                Level.levelObject.levelGoalsObject.decelerateWithBarDecreasingAngle();
            }
            if (finalAngle > initialAngle  && !lastOnMaxAngle){
                if (!Game.abdicateAngle) {
                    Stats.anguloAumentado += 1;
                    Level.levelObject.levelGoalsObject.increaseAngle();
                }

            } else if (finalAngle > initialAngle && !lastOnMinAngle){
                if (!Game.abdicateAngle) {
                    Stats.anguloDiminuido += 1;
                    Level.levelObject.levelGoalsObject.decreaseAngle();
                }
            }
        }

        if (finalLen >= maxLen){
            ball.markMaxVelocity();
            if (velocityChanged && !lastOnMaxVelocity) {
                Level.levelObject.levelGoalsObject.notifyMaxVelocityReached();
            }
        } else if (finalLen <= minLen){
            if (velocityChanged && !lastOnMinVelocity) {
                Level.levelObject.levelGoalsObject.notifyMinVelocityReached();
            }
            ball.markMinVelocity();
        } else {
            ball.markNotMinOrMaxVelocity();
            if ((finalLen - minLen) > ((maxLen - minLen)/ 2f)){
                ball.markMediumHighVelocity();
            } else {
                ball.markMediumLowVelocity();
            }
        }

        if (finalAngle >= maxAngle){
            ball.markMaxAngle();

            if (angleChanged && !lastOnMaxAngle) {
                Level.levelObject.levelGoalsObject.notifyMaxAngleReached();
            }
        } else if (finalAngle <= minAngle){
            if (angleChanged && !lastOnMinAngle) {
                Level.levelObject.levelGoalsObject.notifyMinAngleReached();
            }
            ball.markMinAngle();
        } else {
            ball.markNotMinOrMaxAngle();
            if ((finalAngle - minAngle) > ((maxAngle - minAngle)/ 2f)){
                ball.markMediumHighAngle();
            } else {
                ball.markMediumLowAngle();
            }
        }

        lastOnMaxAngle = onMaxAngle;
        lastOnMaxVelocity = onMaxVelocity;
        lastOnMinAngle = onMinAngle;
        lastOnMinVelocity = onMinVelocity;

        active = false;
    }


    public void logData() {
        
        //Log.e(TAG, "active " + active);
        //Log.e(TAG, "angleDecreasedWithBarMovement " + angleDecreasedWithBarMovement);
        //Log.e(TAG, "angleIncreasedWithBarMovement " + angleIncreasedWithBarMovement);
        //Log.e(TAG, "angleDecreasedWithBarInclination " + angleDecreasedWithBarInclination);
        //Log.e(TAG, "angleIncreasedWithBarInclination " + angleIncreasedWithBarInclination);
        //Log.e(TAG, "minVelocityReached " + minVelocityReached);
        //Log.e(TAG, "maxVelocityReached " + maxVelocityReached);
        //Log.e(TAG, "minAngleReached " + minAngleReached);
        //Log.e(TAG, "maxAngleReached " + maxAngleReached);
        //Log.e(TAG, "initialLen " + initialLen);
        //Log.e(TAG, "minLen " + minLen);
        //Log.e(TAG, "maxLen " + maxLen);
        //Log.e(TAG, "finalLen " + finalLen);
        //Log.e(TAG, "initialAngle " + initialAngle);
        //Log.e(TAG, "minAngle " + minAngle);
        //Log.e(TAG, "maxAngle " + maxAngle);
        //Log.e(TAG, "finalAngle " + finalAngle);
    }

    public void setFinalAngle(float v) {
        finalAngle = v;
    }

    public void setInitialAngle(float v) {
        initialAngle = v;
    }

    public void setMaxAngle(float v) {
        maxAngle = v;
    }

    public void setMinAngle(float v) {
        minAngle = v;
    }


    public void clear() {
        timeOfCollision = Utils.getTimeMilliPrecision();
        active = true;
        angleDecreasedWithBarMovement = false;
        angleIncreasedWithBarMovement = false;
        angleDecreasedWithBarInclination = false;
        angleIncreasedWithBarInclination = false;
        velocityIncreased = false;
        velocityDecreased = false;
        minAngleReached = false;
        maxAngleReached = false;
        velocityChanged = false;
        angleChanged = false;
    }
}
