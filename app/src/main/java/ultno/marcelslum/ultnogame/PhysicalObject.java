package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class PhysicalObject extends Entity{

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

    PhysicalObject(String name, Game game, float x, float y, int weight){
        super(name, game, x, y);
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


    public RectangleM getQuadtreeData() {
        this.updateQuatreeData();
        return this.quadtreeData;
    }

    public void respondToCollision(PhysicalObject other, float responseX, float responseY, float ax, float ay, float bx, float by) {

        this.accumulatedTranslateX += ax - this.positionX;
        this.accumulatedTranslateY += ay - this.positionY;
        other.accumulatedTranslateX += bx - other.positionX;
        other.accumulatedTranslateY += by - other.positionY;

        if (name == "ball"){
            if (other.name == "obstacle"){
                Log.e("physicalObject", "objetos colididos com a bola que colide com o obstaculo");
                Log.e("physicalObject", "responseX "+responseX);
                Log.e("physicalObject", "responseY "+responseY);
                for (int i = 0; i < other.lastCollisionObjects.size(); i++){
                    Log.e("physicalObject", other.lastCollisionObjects.get(i).name);
                    Log.e("physicalObject", "response objeto x"+other.lastCollisionResponse.get(i).x);
                    Log.e("physicalObject", "response objeto y"+other.lastCollisionResponse.get(i).y);
                }
            }
        }

        
        if (this.isSolid && other.isSolid) {
            if (this.weight > other.weight) {// Move the other object out of us
                other.accumulatedTranslateX += -responseX;
                other.accumulatedTranslateY += -responseY;
                other.checkTransformations(false);
            } else if (other.weight > this.weight) {        // Move us out of the other object
                //Log.e("PhysicalObject", "outro "+other.name+" mais pesado "+responseX+ " "+responseY );
                this.accumulatedTranslateX += responseX;
                this.accumulatedTranslateY += responseY;
                this.checkTransformations(false);
            } else if (this.weight == other.weight){        // Move equally out of each other
                responseX *= 0.5;
                responseY *= 0.5;
                this.accumulatedTranslateX += responseX;
                this.accumulatedTranslateY += responseY;
                this.checkTransformations(false);
                other.accumulatedTranslateX += -responseX;
                other.accumulatedTranslateY += -responseY;
                other.checkTransformations(false);
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

                //Log.e("ball", "verify acceleration "+this.name + " dvx "+dvx);

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
        this.collisionsData.clear()
        this.isCollided = false;
    }
}
