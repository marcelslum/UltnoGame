package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    Rectangle velocityRectangle;
    Rectangle angleRectangle;
    float velocityPercent = 0f;
    float anglePercent = 0f;
    public static final String TAG = "BallDataPanel";

    float width;
    float height;

    BallDataPanel(String name, float x, float y, float width, float height) {
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

        velocityRectangle = new Rectangle("velocityRectangle", x, y, width * velocityPercent, baseHeight *2f, -1, new Color(0.14f, 0.14f, 0.56f, 0.7f));
        angleRectangle = new Rectangle("velocityRectangle", x, y + (baseHeight * 3f), width * anglePercent, baseHeight *2f, -1, new Color(0.14f, 0.56f, 0.14f, 0.7f));

    }

    void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void render(float[] matrixView, float[] matrixProjection){


        velocityRectangle.alpha = this.alpha;
        velocityRectangle.render(matrixView, matrixProjection);
        angleRectangle.alpha = this.alpha;
        angleRectangle.render(matrixView, matrixProjection);

    }

    public void setData(float velocityPercentage, float anglePercentage) {
        Log.e(TAG, "velocityPercentage "+velocityPercentage);
        Log.e(TAG, "anglePercentage "+anglePercentage);

        if (velocityPercentage < 0.005f){
            velocityPercentage = 0.005f;
        }

        if (anglePercentage < 0.005f){
            anglePercentage = 0.005f;
        }

        velocityPercent = velocityPercentage;
        anglePercent = anglePercentage;
        generateRectangles();
    }
}
