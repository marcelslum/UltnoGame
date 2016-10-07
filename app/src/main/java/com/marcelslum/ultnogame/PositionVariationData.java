package com.marcelslum.ultnogame;

/**
 * Created by marcel on 07/09/2016.
 */
public class PositionVariationData {

    public boolean isActive;
    public boolean increaseX;
    public boolean increaseY;
    public float minX;
    public float maxX;
    public float minY;
    public float maxY;
    public float xVelocity;
    public float yVelocity;

    public PositionVariationData(PositionVariationDataBuilder builder) {
        this.isActive = builder.isActive;
        this.increaseX = builder.increaseX;
        this.increaseY = builder.increaseY;
        this.minX = builder.minX;
        this.maxX = builder.maxX;
        this.minY = builder.minY;
        this.maxY = builder.maxY;
        this.xVelocity = builder.xVelocity;
        this.yVelocity = builder.yVelocity;
    }
}
