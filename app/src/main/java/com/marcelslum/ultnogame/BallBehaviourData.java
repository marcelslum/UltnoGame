package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 07/02/2017.
 */

public class BallBehaviourData {

    public static final String TAG = "BallBehaviourData";

    long timeOfCollision;
    Ball ball;
    boolean active;
    boolean angleDecreasedWithBarMovement;
    boolean angleIncreasedWithBarMovement ;
    boolean angleDecreasedWithBarInclination;
    boolean angleIncreasedWithBarInclination;
    boolean velocityIncreased;
    boolean velocityDecreased;
    boolean minVelocityReached;
    boolean maxVelocityReached;
    boolean minAngleReached;
    boolean maxAngleReached;
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
            MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_acelerando, 1);
        } else if (velocityDecreased) {
            MyAchievements.increment(Game.mainActivity.mGoogleApiClient, R.string.achievement_pisando_no_freio, 1);
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
