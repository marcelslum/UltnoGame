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
    boolean minVelocityReached = false;
    boolean maxVelocityReached = false;
    boolean minAngleReached = false;
    boolean maxAngleReached = false;

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

        if (finalLen > maxLen*0.99f){
            finalLen = maxLen;
        } else if (finalLen < minLen*1.01f){
            finalLen = minLen;
        }

        if (finalAngle > maxAngle*0.99f){
            finalAngle = maxAngle;
        } else if (finalAngle < minAngle*1.01f){
            finalAngle = minAngle;
        }

        Game.ballDataPanel.previousAnglePercent = (initialAngle - minAngle) / (maxAngle - minAngle);
        Game.ballDataPanel.previousVelocityPercent = (initialLen - minLen) / (maxLen - minLen);
        Game.ballDataPanel.setData((finalLen - minLen) / (maxLen - minLen), (finalAngle - minAngle) / (maxAngle - minAngle), true);
        Game.ballDataPanel.ballAnimating = ball;

        logData();


        
        if (finalLen != initialLen){
            velocityChanged = true;   
        }
            
        if (!velocityChanged){
            Level.levelObject.levelGoalsObject.notifyNotSpeedChange();
        }

        if (finalLen > initialLen){
            Level.levelObject.levelGoalsObject.accelerate();
        } else if (initialLen > finalLen){
            Level.levelObject.levelGoalsObject.decelerate();
        }

        if ((finalLen > initialLen) && finalAngle != minAngle){
            Level.levelObject.levelGoalsObject.accelerateWithoutReachMinAngle();
        } else if ((initialLen > finalLen) && finalAngle != maxAngle){
            Level.levelObject.levelGoalsObject.decelerateWithoutReachMaxAngle();
        }

        if (angleDecreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement && !ball.onMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarInclination();
        } else if (angleIncreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement&& !ball.onMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarInclination();
        } else if (!angleIncreasedWithBarInclination && !angleDecreasedWithBarInclination && angleDecreasedWithBarMovement  && !ball.onMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarMovement();
        } else if (!angleDecreasedWithBarInclination && !angleIncreasedWithBarInclination && angleIncreasedWithBarMovement  && !ball.onMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarMovement();
        } else if(angleIncreasedWithBarInclination && angleIncreasedWithBarMovement && !ball.onMaxAngle){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedWithBarMovementAndInclination();
        } else if (angleDecreasedWithBarInclination && angleDecreasedWithBarMovement && !ball.onMinAngle){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedWithBarMovementAndInclination();
        }

        if ((finalLen > initialLen) && angleIncreasedWithBarInclination){
            Level.levelObject.levelGoalsObject.accelerateWithBarIncreasingAngle();
            if (finalAngle > initialAngle){
                    Level.levelObject.levelGoalsObject.increaseAngle();
            } else if (finalAngle > initialAngle){
                    Level.levelObject.levelGoalsObject.decreaseAngle();
            }
        } else if ((initialLen > finalLen) && angleDecreasedWithBarInclination){
            Level.levelObject.levelGoalsObject.decelerateWithBarDecreasingAngle();
            if (finalAngle > initialAngle){
                    Level.levelObject.levelGoalsObject.increaseAngle();
            } else if (finalAngle > initialAngle){
                    Level.levelObject.levelGoalsObject.decreaseAngle();
            }

        }

        if (finalLen >= maxLen){
            ball.markMaxVelocity();
            Level.levelObject.levelGoalsObject.notifyMaxVelocityReached();
        } else if (finalLen <= minLen){
            Level.levelObject.levelGoalsObject.notifyMinVelocityReached();
            ball.markMinVelocity();
        } else {
            ball.markNotMinOrMaxVelocity();
        }

        if (finalAngle >= maxAngle){
            ball.markMaxAngle();
            Level.levelObject.levelGoalsObject.notifyMaxAngleReached();
        } else if (finalAngle <= minAngle){
            Level.levelObject.levelGoalsObject.notifyMinAngleReached();
            ball.markMinAngle();
        } else {
            ball.markNotMinOrMaxAngle();
        }

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

    public void notifyMinVelocityReached() {
        minVelocityReached = true;
    }

    public void notifyMaxVelocityReached() {
        maxVelocityReached = true;
    }

    public void notifyMaxAngleReached() {
        maxAngleReached = true;
    }

    public void notifyMinAngleReached() {
        minAngleReached = true;
    }

    public void notifyNotMinOrMaxAngleReached() {
        maxAngleReached = false;
        minAngleReached = false;
    }

    public void clear() {
        timeOfCollision = Utils.getTime();
        active = true;
        angleDecreasedWithBarMovement = false;
        angleIncreasedWithBarMovement = false;
        angleDecreasedWithBarInclination = false;
        angleIncreasedWithBarInclination = false;
        velocityIncreased = false;
        velocityDecreased = false;
        minVelocityReached = false;
        maxVelocityReached = false;
        minAngleReached = false;
        maxAngleReached = false;
        velocityChanged = false;
    }
}
