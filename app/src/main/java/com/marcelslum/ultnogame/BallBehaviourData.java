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

    boolean lastMaxAngleReached = false;
    boolean lastMinAngleReached = false;
    boolean lastMaxVelocityReached = false;
    boolean lastMinVelocityReached = false;

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
        velocityChanged = true;
    }

    public void processData() {

        if (!active) return;

        Game.ballDataPanel.previousAnglePercent = (initialAngle - minAngle) / (maxAngle - minAngle);
        Game.ballDataPanel.previousVelocityPercent = (initialLen - minLen) / (maxLen - minLen);
        Game.ballDataPanel.setData((finalLen - minLen) / (maxLen - minLen), (finalAngle - minAngle) / (maxAngle - minAngle), true);
        Game.ballDataPanel.ballAnimating = ball;

        //logData();
        
        if (finalLen != initialLen){
            velocityChanged = true;   
        }
            

        if (finalLen > initialLen) {
                MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_acelerando, 1);
        } else if (initialLen > finalLen) {
                MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_pisando_no_freio, 1);
        }

        if (!velocityChanged){
            Level.levelObject.levelGoalsObject.notifyNotSpeedChange();
        }

        if (finalLen > initialLen){
            Level.levelObject.levelGoalsObject.accelerate();
        } else if (initialLen > finalLen){
            Level.levelObject.levelGoalsObject.decelerate();
        }

        if ((finalLen > initialLen) && !minAngleReached){
            Level.levelObject.levelGoalsObject.accelerateWithoutReachMinAngle();
        } else if ((initialLen > finalLen) && !maxAngleReached){
            Level.levelObject.levelGoalsObject.decelerateWithoutReachMaxAngle();
        }

        if (angleDecreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarInclination();
        } else if (angleIncreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarInclination();
        } else if (!angleIncreasedWithBarInclination && !angleDecreasedWithBarInclination && angleDecreasedWithBarMovement){
            Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarMovement();
        } else if (!angleDecreasedWithBarInclination && !angleIncreasedWithBarInclination && angleIncreasedWithBarMovement){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarMovement();
        } else if(angleIncreasedWithBarInclination && angleIncreasedWithBarMovement){
            Level.levelObject.levelGoalsObject.notifyAngleIncreasedWithBarMovementAndInclination();
        } else if (angleDecreasedWithBarInclination && angleDecreasedWithBarMovement){
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

        if (minAngleReached){
            Level.levelObject.levelGoalsObject.notifyMinAngleReached();
        } else if (maxAngleReached){
            Level.levelObject.levelGoalsObject.notifyMaxAngleReached();
        }

        if (minVelocityReached){
            Level.levelObject.levelGoalsObject.notifyMinVelocityReached();
        } else if (maxVelocityReached){
            Level.levelObject.levelGoalsObject.notifyMaxVelocityReached();
        }

        active = false;
    }


    public void logData() {
        
        Log.e(TAG, "active " + active);
        Log.e(TAG, "angleDecreasedWithBarMovement " + angleDecreasedWithBarMovement);
        Log.e(TAG, "angleIncreasedWithBarMovement " + angleIncreasedWithBarMovement);
        Log.e(TAG, "angleDecreasedWithBarInclination " + angleDecreasedWithBarInclination);
        Log.e(TAG, "angleIncreasedWithBarInclination " + angleIncreasedWithBarInclination);
        Log.e(TAG, "velocityIncreased " + velocityIncreased);
        Log.e(TAG, "velocityDecreased " + velocityDecreased);
        Log.e(TAG, "minVelocityReached " + minVelocityReached);
        Log.e(TAG, "maxVelocityReached " + maxVelocityReached);
        Log.e(TAG, "minAngleReached " + minAngleReached);
        Log.e(TAG, "maxAngleReached " + maxAngleReached);
        Log.e(TAG, "initialLen " + initialLen);
        Log.e(TAG, "minLen " + minLen);
        Log.e(TAG, "maxLen " + maxLen);
        Log.e(TAG, "finalLen " + finalLen);
        Log.e(TAG, "initialAngle " + initialAngle);
        Log.e(TAG, "minAngle " + minAngle);
        Log.e(TAG, "maxAngle " + maxAngle);
        Log.e(TAG, "finalAngle " + finalAngle);
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
