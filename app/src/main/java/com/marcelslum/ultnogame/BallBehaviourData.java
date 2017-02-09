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

    public void setMaxLen(float v) {
        maxLen = v;
    }

    public void setMinLen(float v) {
        minLen = v;
    }

    public void notifyNotSpeedChange() {
        velocityIncreased = false;
        velocityDecreased = false;
    }

    public void setFinalLen(float v) {
        finalLen = v;
    }


    public void processData() {

        if (!active) return;

        Game.ballDataPanel.previousAnglePercent = (initialAngle - minAngle) / (maxAngle - minAngle);
        Game.ballDataPanel.previousVelocityPercent = (initialLen - minLen) / (maxLen - minLen);
        Game.ballDataPanel.setData((finalLen - minLen) / (maxLen - minLen), (finalAngle - minAngle) / (maxAngle - minAngle), true);
        Game.ballDataPanel.ballAnimating = ball;

        logData();

        if (velocityIncreased) {
            if (!lastMaxVelocityReached) {
                MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_acelerando, 1);
            }
        } else if (velocityDecreased) {
            if (!lastMinVelocityReached) {
                MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_pisando_no_freio, 1);
            }
        }

        if (velocityIncreased){
            Level.levelObject.levelGoalsObject.accelerate();
        } else if (velocityDecreased){
            Level.levelObject.levelGoalsObject.decelerate();
        }

        if (velocityIncreased && !minAngleReached){
            Level.levelObject.levelGoalsObject.accelerateWithoutReachMinAngle();
        } else if (velocityDecreased && !maxAngleReached){
            Level.levelObject.levelGoalsObject.decelerateWithoutReachMaxAngle();
        }

        if (minVelocityReached){
            Level.levelObject.levelGoalsObject.notifyMinVelocityReached();
            lastMinVelocityReached = true;
            lastMaxVelocityReached = false;
        } else if (maxVelocityReached){
            Level.levelObject.levelGoalsObject.notifyMaxVelocityReached();
            lastMinVelocityReached = false;
            lastMaxVelocityReached = true;
        } else {
            lastMinVelocityReached = false;
            lastMaxVelocityReached = false;
        }

        if (minAngleReached){
            Level.levelObject.levelGoalsObject.notifyMinAngleReached();
            lastMinAngleReached = true;
            lastMaxAngleReached = false;
        } else if (maxAngleReached){
            Level.levelObject.levelGoalsObject.notifyMaxAngleReached();
            lastMinAngleReached = false;
            lastMaxAngleReached = true;
        } else {
            lastMinAngleReached = false;
            lastMaxAngleReached = false;
        }

        if (angleDecreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement){
            if (!lastMinAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarInclination();
            }
        } else if (angleIncreasedWithBarInclination && !angleIncreasedWithBarMovement && !angleDecreasedWithBarMovement){
            if (!lastMaxAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarInclination();
            }
        } else if (!angleIncreasedWithBarInclination && !angleDecreasedWithBarInclination && angleDecreasedWithBarMovement){
            if (!lastMinAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleDecreasedOnlyWithBarMovement();
            }
        } else if (!angleDecreasedWithBarInclination && !angleIncreasedWithBarInclination && angleIncreasedWithBarMovement){
            if (!lastMaxAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleIncreasedOnlyWithBarMovement();
            }
        } else if(angleIncreasedWithBarInclination && angleIncreasedWithBarMovement){
            if (!lastMaxAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleIncreasedWithBarMovementAndInclination();
            }
        } else if (angleDecreasedWithBarInclination && angleDecreasedWithBarMovement){
            if (!lastMinAngleReached) {
                Level.levelObject.levelGoalsObject.notifyAngleDecreasedWithBarMovementAndInclination();
            }
        }

        if (velocityIncreased && angleIncreasedWithBarInclination){
            if (!lastMaxVelocityReached) {
                Level.levelObject.levelGoalsObject.accelerateWithBarIncreasingAngle();
            }

            if (finalAngle > initialAngle){
                if (!lastMaxAngleReached) {
                    Level.levelObject.levelGoalsObject.increaseAngle();
                }
            } else if (finalAngle > initialAngle){
                if (!lastMinAngleReached) {
                    Level.levelObject.levelGoalsObject.decreaseAngle();
                }
            }


        } else if (velocityDecreased && angleDecreasedWithBarInclination){
            if (!lastMinVelocityReached) {
                Level.levelObject.levelGoalsObject.decelerateWithBarDecreasingAngle();
            }

            if (finalAngle > initialAngle){
                if (!lastMaxAngleReached) {
                    Level.levelObject.levelGoalsObject.increaseAngle();
                }
            } else if (finalAngle > initialAngle){
                if (!lastMinAngleReached) {
                    Level.levelObject.levelGoalsObject.decreaseAngle();
                }
            }

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

    public void notifyVelocityIncreased() {
        velocityIncreased = true;
    }

    public void notifyVelocityDecreased() {
        velocityDecreased = true;
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
    }
}
