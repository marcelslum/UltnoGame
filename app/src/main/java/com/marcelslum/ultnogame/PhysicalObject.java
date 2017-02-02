package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class PhysicalObject extends Entity implements Weight{

    public int weight;
    public float vx;
    public float vy;
    public float dvx;
    public float dvy;
    public float initialDVX;
    public float initialDVY;
    public float initialX;
    public float initialY;
    public boolean isCollided;
    public ArrayList<CollisionData> collisionsData;
    public boolean accelStarted;
    public float accelInitialVelocityX;
    public float accelInitialVelocityY;
    public float accelFinalVelocityX;
    public float accelFinalVelocityY;
    public int accelType;
    public float accelPercentage;
    public int accelDuration;
    public long accelInitialTime;
    public boolean accelFinish;

    public static final int ACCEL_TYPE_LINEAR = 0;
    public static final int ACCEL_TYPE_EXPONENTIAL = 1;

    public RectangleM bounds;
    public RectangleM quadtreeData;
    public int updateBoundsState;
    public RectangleM satData;

    public void checkCollisionsDataObjectRepetition(){
        for (int i = 0; i < collisionsData.size(); i++) {
            for (int ii = 0; ii < i; ii++){
                if (collisionsData.get(i).object == collisionsData.get(ii).object){
                    collisionsData.get(i).isRepeated = true;
                }
            }
        }
    }

    PhysicalObject(String name, float x, float y, int weight){
        super(name, x, y);
        this.weight = weight;

        vx = 0.0f;
        vy = 0.0f;
        dvx = 0.0f;
        dvy = 0.0f;
        isCollided = false;
        isCollidable = true;
        isSolid = true;
        collisionsData = new ArrayList<CollisionData>();
        accelStarted = false;
        accelInitialVelocityX = 0.0f;
        accelInitialVelocityY = 0.0f;
        accelFinalVelocityX = 0.0f;
        accelFinalVelocityY = 0.0f;
        accelDuration = 0;
        accelInitialTime = 0;
        bounds = new RectangleM(0, 0, 0, 0);
        quadtreeData = new RectangleM(0, 0, 0, 0);
        satData = new RectangleM(0, 0, 0, 0);
        updateBoundsState = 0;
    }

    public void respondToCollision(float responseX, float responseY){
        //Log.e("physical", "respond to collision " +responseX);
        this.accumulatedTranslateX += responseX;
        this.accumulatedTranslateY += responseY;
        this.checkTransformations(false);
    }


    public RectangleM getQuadtreeData() {
        this.updateQuatreeData();
        return this.quadtreeData;
    }
    
    public void verifyWind(){
        Wind w = Game.wind;
        float windForce = 0.18f;
        if (w != null){
            if (w.isActive){
                if (translateX != 0f) {
                    if (w.rightDirection) {
                        //Log.e("physical", "right " + translateX);
                        if (translateX > 0) {
                            translateX *= 1.0f + windForce;
                        } else if (translateX < 0) {
                            translateX *= 1.0f - windForce;
                        }
                    } else {
                        //Log.e("physical", "not right" + translateX);
                        if (translateX < 0) {
                            translateX *= 1.0f + windForce;
                        } else if (translateX > 0) {
                            translateX *= 1.0f - windForce;
                        }
                    }
                } else {

                    //Log.e("PhysicalObject","nome "+name+"   - dvx "+dvx );

                    if (w.rightDirection) {
                        translateX = Math.abs(dvx) * windForce;
                    } else {
                        translateX = -Math.abs(dvx) * windForce;
                    }
                }
            }
        }
    }

    public void verifyAcceleration(){
        if (this.accelStarted){
            long elapsedTime = Utils.getTime() - accelInitialTime;
            accelPercentage = (float)elapsedTime / (float)accelDuration;

            if (accelPercentage > 1){
                accelStarted = false;
                dvx = this.accelFinalVelocityX;
                dvy = this.accelFinalVelocityY;
            } else {
                if (accelType == ACCEL_TYPE_EXPONENTIAL) {
                    accelPercentage = (float) Math.pow(accelPercentage, 2 * 1.1);
                }
                dvx = accelInitialVelocityX + ((accelFinalVelocityX - accelInitialVelocityX) * accelPercentage);
                dvy = accelInitialVelocityY + ((accelFinalVelocityY - accelInitialVelocityY) * accelPercentage);
            }
        }
    }

    public void accelerate(int duration, float x, float y){
        accelType = ACCEL_TYPE_LINEAR;
        if (this.accelStarted == false){
            accelInitialVelocityX = accelFinalVelocityX;
            accelInitialVelocityY = accelFinalVelocityY;
        }
        accelFinalVelocityX = x;
        accelFinalVelocityY = y;
        accelStarted = true;
        accelDuration = duration;
        accelInitialVelocityX = this.dvx;
        accelInitialVelocityY = this.dvy;
        accelInitialTime = Utils.getTime();
    }

    public void accelerateFrom(int type, int duration, float initialVX, float initialVY, float finalVX, float finalVY){
        accelType = type;
        accelInitialVelocityX = initialVX;
        accelInitialVelocityY = initialVY;
        accelFinalVelocityX = finalVX;
        accelFinalVelocityY = finalVY;
        accelStarted = true;
        accelDuration = duration;
        accelInitialTime = Utils.getTime();
    }

    public void clearCollisionData() {
        this.collisionsData.clear();
        this.isCollided = false;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}
