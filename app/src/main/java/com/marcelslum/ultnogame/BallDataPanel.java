package com.marcelslum.ultnogame;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    Rectangle velocityRectangle;
    Rectangle angleRectangle;
    float velocityPercent = 1f;
    float anglePercent = 1f;

    float width;
    float height;

    BallDataPanel(String name, float x, float y, int width, int height) {
        super(name, x, y);
        this.width = width;
        this.height = height;
        isCollidable = false;
        isMovable = false;
        isSolid = false;
        generateRectangles();
    }

    public void generateRectangles(){
        float  baseHeight = height/5f;
        velocityRectangle = new Rectangle("velocityRectangle", x, y, width * velocityPercent, baseHeight *2f, -1, new Color(1.0f, 0f, 0f, 1.0f));
        velocityRectangle = new Rectangle("velocityRectangle", x, y + (baseHeight * 3f), width * velocityPercent, baseHeight *2f, -1, new Color(0.0f, 0f, 1.0f, 1.0f));

    }

    void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }
}
