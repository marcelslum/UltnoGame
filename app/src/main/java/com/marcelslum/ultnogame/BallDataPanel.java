package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    Rectangle velocityRectangle;
    Rectangle angleRectangle;
    float velocityPercent = 1f;
    float anglePercent = 1f;
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

        velocityRectangle = new Rectangle("velocityRectangle", x, y, width * velocityPercent, baseHeight *2f, -1, new Color(0.52f, 0.88f, 0.52f, 1f));
        velocityRectangle.alpha = 0.3f;
        angleRectangle = new Rectangle("velocityRectangle", x, y + (baseHeight * 3f), width * anglePercent, baseHeight *2f, -1, new Color(0.14f, 0.56f, 0.14f, 1f));
        angleRectangle.alpha = 0.3f;
    }

    void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void render(float[] matrixView, float[] matrixProjection){
        velocityRectangle.render(matrixView, matrixProjection);
        angleRectangle.render(matrixView, matrixProjection);

    }

    public void setData(float velocityPercentage, float anglePercentage) {
        Log.e(TAG, "velocityPercentage "+velocityPercentage);
        Log.e(TAG, "anglePercentage "+anglePercentage);
        velocityPercent = velocityPercentage;
        anglePercent = anglePercentage;
        generateRectangles();
    }
}
