package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    Rectangle velocityRectangle;
    Rectangle angleRectangle;
    Rectangle velocityNewRectangle;
    Rectangle angleNewRectangle;

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
        float  baseHeight = height/5f;
        velocityRectangle = new Rectangle("velocityRectangle", x, y, width, baseHeight *2f, -1, new Color(0.14f, 0.14f, 0.56f, 0.7f));
        velocityNewRectangle = new Rectangle("velocityNewRectangle", x, y, width, baseHeight *2f, -1, new Color(0.64f, 0.64f, 0.96f, 0.7f));

        velocityRectangle.animScaleX = 0;
        velocityNewRectangle.animScaleX = 0;

        angleRectangle = new Rectangle("velocityRectangle", x, y + (baseHeight * 3f), width, baseHeight *2f, -1, new Color(0.14f, 0.56f, 0.14f, 0.7f));
        angleNewRectangle = new Rectangle("angleNewRectangle", x, y + (baseHeight * 3f), width, baseHeight *2f, -1, new Color(0.64f, 0.96f, 0.64f, 0.7f));

        angleRectangle.animScaleX = 0;
        angleNewRectangle.animScaleX = 0;
    }


    public void render(float[] matrixView, float[] matrixProjection){

        velocityNewRectangle.alpha = this.alpha;
        velocityNewRectangle.render(matrixView, matrixProjection);
        angleNewRectangle.alpha = this.alpha;
        angleNewRectangle.render(matrixView, matrixProjection);

        velocityRectangle.alpha = this.alpha;
        velocityRectangle.render(matrixView, matrixProjection);
        angleRectangle.alpha = this.alpha;
        angleRectangle.render(matrixView, matrixProjection);


    }

    @Override
    public void verifyAnimations() {
        super.verifyAnimations();

        velocityRectangle.verifyAnimations();
        velocityNewRectangle.verifyAnimations();

        angleRectangle.verifyAnimations();
        angleNewRectangle.verifyAnimations();

        velocityRectangle.animTranslateX = -(width - (width * velocityRectangle.animScaleX))/2f;
        velocityNewRectangle.animTranslateX = -(width - (width * velocityNewRectangle.animScaleX))/2f;
        angleRectangle.animTranslateX = -(width - (width * angleRectangle.animScaleX))/2f;
        angleNewRectangle.animTranslateX = -(width - (width * angleNewRectangle.animScaleX))/2f;
    }

    public void setData(float velocityPercentage, float anglePercentage) {

        float previousVelocityPercent = velocityPercent;
        float previousAnglePercent = anglePercent;

        Log.e(TAG, "previousVelocityPercent "+previousVelocityPercent);
        Log.e(TAG, "previousAnglePercent "+previousAnglePercent);

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

        velocityNewRectangle.animScaleX = velocityPercent;
        angleNewRectangle.animScaleX = anglePercent;

        Utils.createSimpleAnimation(velocityRectangle, "velocityRectangle", "scaleX", 500, previousVelocityPercent, velocityPercent).start();
        Utils.createSimpleAnimation(angleRectangle, "velocityRectangle", "scaleX", 500, previousAnglePercent, anglePercent).start();
    }
}
