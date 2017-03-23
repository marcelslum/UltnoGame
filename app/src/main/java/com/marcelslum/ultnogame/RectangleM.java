package com.marcelslum.ultnogame;


/**
 * Created by marcel on 01/08/2016.
 */
public class RectangleM implements Poolable<RectangleM>{
    float x, y, width, height;

    private int poolID;

    public RectangleM(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RectangleM(){

    }

    public void setData(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setPoolID(int id) {
        poolID = id;
    }

    @Override
    public int getPoolID() {
        return poolID;
    }

    @Override
    public RectangleM get() {
        return this;
    }

    @Override
    public void clean() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
