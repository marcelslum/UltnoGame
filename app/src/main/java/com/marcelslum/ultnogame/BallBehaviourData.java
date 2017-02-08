package com.marcelslum.ultnogame;

/**
 * Created by marcel on 07/02/2017.
 */

public class BallBehaviourData {

    long timeOfCollision;
    Ball ball;
    boolean active;
    float previousVelocityPercent;
    float previousAnglePercent;
    boolean angleDecreasedWithBarMovement = false;
    boolean angleIncreasedWithBarMovement = false;
    boolean angleDecreasedWithBarInclination = false;
    boolean angleIncreasedWithBarInclination = false;


    public BallBehaviourData(Ball b){
        ball = b;
        timeOfCollision = Utils.getTime();
        active = true;



    }


    public void setPreviousVelocityPercent(float v) {
        previousVelocityPercent = v;
    }

    public void setPreviousAnglePercent(float v) {
        previousAnglePercent = v;
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
}
