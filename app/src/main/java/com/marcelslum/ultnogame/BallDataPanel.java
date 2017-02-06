package com.marcelslum.ultnogame;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    Rectangle velocityRectangle;
    Rectangle backVelocityRectangle;

    Rectangle angleRectangle;
    Rectangle velocityNewRectangle;
    Rectangle angleNewRectangle;
    Rectangle endVelocity;
    Rectangle endAngle;


    Rectangle backAngleRectangle;

    Ball ballAnimating;
    
    private static final Color COLOR_BAR_GREEN_DARK = new Color (0.2f, 0.5f, 0.49f, 1f);
    private static final Color COLOR_BAR_GREEN_LIGHT = new Color (0.65f, 0.83f, 0.82f, 1f);
    private static final Color COLOR_BAR_BLUE_DARK = new Color (0.21f, 0.27f, 0.64f, 1f);//54 69 164
    private static final Color COLOR_BAR_BLUE_LIGHT = new Color (0.79f, 0.82f, 1f, 1f); //202 204 256
    private static final Color COLOR_BACK = new Color (0.2f, 0.2f, 0.2f, 0.5f);
   
    
    float velocityPercent = 0f;
    float anglePercent = 0f;
    public static final String TAG = "BallDataPanel";

    float previousVelocityPercent = -1f;
    float previousAnglePercent = -1f;

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
        velocityRectangle = new Rectangle("velocityRectangle", x, y, width, baseHeight *2f, -1, COLOR_BAR_GREEN_DARK);
        velocityNewRectangle = new Rectangle("velocityNewRectangle", x, y, width, baseHeight *2f, -1, COLOR_BAR_GREEN_LIGHT);



        velocityRectangle.animScaleX = 0.008f;
        velocityNewRectangle.animScaleX = 0.008f;

        angleRectangle = new Rectangle("angleRectangle", x, y + (baseHeight * 3f), width, baseHeight *2f, -1, COLOR_BAR_BLUE_DARK);
        angleNewRectangle = new Rectangle("angleNewRectangle", x, y + (baseHeight * 3f), width, baseHeight *2f, -1, COLOR_BAR_BLUE_LIGHT);

        backVelocityRectangle = new Rectangle("backVelocityRectangle", x, y, width, baseHeight *2f, -1, COLOR_BACK);
        backAngleRectangle = new Rectangle("backAngleRectangle", x, y + (baseHeight * 3f), width, baseHeight *2f, -1, COLOR_BACK);


        angleRectangle.animScaleX = 0.008f;
        angleNewRectangle.animScaleX = 0.008f;

        float markSize =  width*0.008f;
        endVelocity = new Rectangle("velocityRectangle", x + width - markSize, y, markSize, baseHeight *2f, -1, COLOR_BAR_GREEN_DARK);
        endAngle = new Rectangle("velocityRectangle", x + width - markSize, y + (baseHeight * 3f), markSize, baseHeight *2f, -1, COLOR_BAR_BLUE_DARK);

    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){

        checkAnimations();

        velocityNewRectangle.alpha = alpha;
        velocityNewRectangle.prepareRender(matrixView, matrixProjection);
        velocityRectangle.alpha = alpha;
        velocityRectangle.prepareRender(matrixView, matrixProjection);

        angleNewRectangle.alpha = alpha;
        angleNewRectangle.prepareRender(matrixView, matrixProjection);
        angleRectangle.alpha = alpha;
        angleRectangle.prepareRender(matrixView, matrixProjection);

        endVelocity.alpha = alpha;
        endVelocity.prepareRender(matrixView, matrixProjection);
        endAngle.alpha = alpha;
        endAngle.prepareRender(matrixView, matrixProjection);

        backVelocityRectangle.alpha = alpha;
        backVelocityRectangle.prepareRender(matrixView, matrixProjection);
        backAngleRectangle.alpha = alpha;
        backAngleRectangle.prepareRender(matrixView, matrixProjection);

    }

    @Override
    public void checkAnimations() {
        super.checkAnimations();

        velocityRectangle.animTranslateX = -(width - (width * velocityRectangle.animScaleX))/2f;
        velocityNewRectangle.animTranslateX = -(width - (width * velocityNewRectangle.animScaleX))/2f;
        angleRectangle.animTranslateX = -(width - (width * angleRectangle.animScaleX))/2f;
        angleNewRectangle.animTranslateX = -(width - (width * angleNewRectangle.animScaleX))/2f;
    }

    public void setData(float velocityPercentage, float anglePercentage, boolean animationOn) {

        if (previousVelocityPercent == -1f) {
            previousVelocityPercent = velocityPercent;
            previousAnglePercent = anglePercent;
        }

        //Log.e(TAG, "previousVelocityPercent "+previousVelocityPercent);
        //Log.e(TAG, "previousAnglePercent "+previousAnglePercent);

        //Log.e(TAG, "velocityPercentage "+velocityPercentage);
        //Log.e(TAG, "anglePercentage "+anglePercentage);

        if (velocityPercentage < 0.005f){
            velocityPercentage = 0.005f;
        }

        if (anglePercentage < 0.005f){
            anglePercentage = 0.005f;
        }

        velocityPercent = velocityPercentage;
        anglePercent = anglePercentage;


        if (animationOn) {
            if (velocityPercent > previousVelocityPercent) {
                velocityNewRectangle.animScaleX = velocityPercent;
                Utils.createSimpleAnimation(velocityRectangle, "velocityRectangle", "scaleX", 500, previousVelocityPercent, velocityPercent).start();
            } else {
                velocityRectangle.animScaleX = velocityPercent;
                Utils.createSimpleAnimation(velocityNewRectangle, "velocityRectangle", "scaleX", 500, previousVelocityPercent, velocityPercent).start();
            }
        } else {
            velocityNewRectangle.animScaleX = velocityPercent;
            velocityRectangle.animScaleX = velocityPercent;
        }

        if (animationOn) {
            if (anglePercent > previousAnglePercent) {
                angleNewRectangle.animScaleX = anglePercent;
                Utils.createSimpleAnimation(angleRectangle, "velocityRectangle", "scaleX", 500, previousAnglePercent, anglePercent).start();
            } else {
                angleRectangle.animScaleX = anglePercent;
                Utils.createSimpleAnimation(angleNewRectangle, "velocityRectangle", "scaleX", 500, previousAnglePercent, anglePercent).start();
            }
        } else {
            angleNewRectangle.animScaleX = anglePercent;
            angleRectangle.animScaleX = anglePercent;
        }
    }
}
