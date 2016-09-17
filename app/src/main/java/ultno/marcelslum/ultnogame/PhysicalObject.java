package ultno.marcelslum.ultnogame;


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
    public boolean isCollided;
    public ArrayList<CollisionData> collisionsData;
    public boolean accelStarted;
    public float accelInitialVelocityX;
    public float accelInitialVelocityY;
    public float accelFinalVelocityX;
    public float accelFinalVelocityY;
    public int accelDuration;
    public long accelInitialTime;
    public float initialDesireVelocityX;
    public float initialDesireVelocityY;
    public float initialX;
    public float initialY;

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
        float windForce = 0.15f;
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
                    if (w.rightDirection) {
                        translateX = dvx*0.2f;
                    } else {
                        translateX = -dvx*0.2f;
                    }
                }
            }
        }
    }

    public void verifyAcceleration(){
        if (this.accelStarted){
            long elapsedTime = Utils.getTime() - this.accelInitialTime;
            float porcentage = (float)elapsedTime / (float)this.accelDuration;

            if (porcentage > 1){
                this.accelStarted = false;
                this.dvx = this.accelFinalVelocityX;
                this.dvy = this.accelFinalVelocityY;
            } else {
                this.dvx = this.accelInitialVelocityX + ((this.accelFinalVelocityX - this.accelInitialVelocityX) * porcentage);
                this.dvy = this.accelInitialVelocityY + ((this.accelFinalVelocityY - this.accelInitialVelocityY) * porcentage);
            }
        }
    }

    public void accelerate(int duration, float x, float y){
        if (this.accelStarted == false){
            this.accelInitialVelocityX = this.accelFinalVelocityX;
            this.accelInitialVelocityY = this.accelFinalVelocityY;
        }
        this.accelFinalVelocityX = x;
        this.accelFinalVelocityY = y;
        this.accelStarted = true;
        this.accelDuration = duration;
        this.accelInitialVelocityX = this.dvx;
        this.accelInitialVelocityY = this.dvy;
        this.accelInitialTime = Utils.getTime();
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
