package com.marcelslum.ultnogame;


/**
 * Created by marcel on 11/08/2016.
 */
public class TouchEvent {
    public float x;
    public float y;
    public float initialX;
    public float initialY;
    public float previousX;
    public float previousY;
    public int id;
    public static final int TOUCH_TYPE_DOWN = 0;
    public static final int TOUCH_TYPE_MOVE = 1;
    public static final int TOUCH_TYPE_UP = 2;
    public int type;
    public boolean moved;




    public TouchEvent(int id, float x, float y) {
        this.x = x;
        this.y = y;
        initialX = x;
        initialY = y;
        this.id = id;
        this.type = TOUCH_TYPE_DOWN;
        this.moved = false;
    }

    public void setType(int type) {
        this.type = type;
    }


    public void move(float newX, float newY) {
        previousX = x;
        previousY = y;
        x = newX;
        y = newY;
        moved = true;
        setType(TouchEvent.TOUCH_TYPE_MOVE);
    }
}
