package com.marcelslum.ultnogame;


import android.util.Log;

/**
 * Created by marcel on 07/08/2016.
 */
public class Bar extends Rectangle{

    long timeOfWindMove = 0;

    public static final String TAG = "Bar";

    long startTimeSpecialBallAnim = 0;
    long specialBallAnimDuration = 1000;
    boolean specialBallAnimActive = false;
    int textureMap = COLOR_BLACK;
    boolean leftPress = false;

    boolean rightPress = false;

    static final int [] UV_MAP = new int[]{0, 72, 144, 216, 288, 360, 432, 504, 576};
    static final int COLOR_RED = 1;
    static final int COLOR_BLUE = 2;
    static final int COLOR_GREEN = 3;
    static final int COLOR_YELLOW = 4;
    static final int COLOR_ORANGE = 5;
    static final int COLOR_PINK = 6;
    static final int COLOR_PURPLE = 7;
    static final int COLOR_BLACK = 8;

    int secretLevel1Steps = 0;//L R R L R R L L

    Image shine;
    Animation shineDecreaseAfterAccelerate;
    Animation shineAfterBallCollision;

    public void changeTextureMap(int textureMap){
        if (textureMap == this.textureMap){
            return;
        }
        setTextureMap(textureMap);
    }

    public void setTextureMap(int textureMap){
        this.textureMap = textureMap;
        Utils.x1 = 0f;
        Utils.x2 = 1f;
        switch (textureMap){
            case COLOR_RED:
                Utils.y1 = (UV_MAP[0] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[1] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.65f, 0f, 0f, 1f);
                }
                
                break;
            case COLOR_BLUE:
                Utils.y1 = (UV_MAP[1] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[2] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0f, 0.04f, 0.69f, 1f);
                }
                break;
            case COLOR_GREEN:
                Utils.y1 = (UV_MAP[2] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[3] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.02f, 0.41f, 0.10f, 1f);
                }
                break;
            case COLOR_YELLOW:
                Utils.y1 = (UV_MAP[3] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[4] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.74f, 0.73f, 0.33f, 1f);
                }
                break;
            case COLOR_ORANGE:
                Utils.y1 = (UV_MAP[4] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[5] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.61f, 0.43f, 0.06f, 1f);
                }
                break;
            case COLOR_PINK:
                Utils.y1 = (UV_MAP[5] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[6] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.54f, 0.14f, 0.48f, 1f);
                }
                break;
            case COLOR_PURPLE:
                Utils.y1 = (UV_MAP[6] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[7] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(0.31f, 0.04f, 0.69f, 1f);
                }
                break;
            case COLOR_BLACK:
                Utils.y1 = (UV_MAP[7] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[8] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(1f, 1f, 1f, 1f);
                }
                break;
            default:
                Utils.y1 = (UV_MAP[7] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[8] - 0.5f)/1024f;
                if (shine != null) {
                    shine.color = new Color(1f, 1f, 1f, 1f);
                }
                break;
        }
        Utils.insertRectangleUvData(uvsData, 0);
        uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }



    Bar(String name, float x, float y, float width, float height){
        super(name, x, y, width, height, Game.BAR_WEIGHT, new Color(0.0f, 0.0f, 0.0f, 1.0f));
        program = Game.imageColorizedProgram;
        textureId = Texture.TEXTURE_BARS;
        isCollidable = true;
        isSolid = true;
        shine = new Image("shine", x, y, width, height, Texture.TEXTURE_BARS, 0f, 1f, 576f/1024f, 648f/1024f, new Color(1f, 1f, 1f, 1f));
        shine.alpha = 0f;
        shineDecreaseAfterAccelerate = Utils.createSimpleAnimation(shine, "shineDecreaseAfterAccelerate", "numberForAnimation", 500, 0f, 0f);
        shineAfterBallCollision = Utils.createAnimation3v(shine, "shineAfterBallCollision", "numberForAnimation2", 1000, 0f, 0, 0.2f, 1f, 1f, 0f, false, true);
        setDrawInfo();


    }


    public void setDrawInfo(){
        verticesData = new float[12];
        insertVerticesData(this.verticesData,0);
        verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        indicesData = new short[6];
        insertIndicesData(this.indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        uvsData = new float[12];
        setTextureMap(textureMap);

        colorsData = new float[16];
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }


    public void insertVerticesData(float[] array, int startIndex){
        float x1 = 0f;
        float x2 = this.width;
        float y1 = 0f;
        float y2 = this.height;

        array[startIndex] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = 0.0f;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = 0.0f;
        array[6 + (startIndex)] = x2;
        array[7 + (startIndex)] = y1;
        array[8 + (startIndex)] = 0.0f;
        array[9 + (startIndex)] = x1;
        array[10 + (startIndex)] = y1;
        array[11 + (startIndex)] = 0.0f;
    }

    public void insertIndicesData(short[] array, int startIndex, int startValue){
        array[startIndex] = (short)(startValue);
        array[1 + (startIndex)] = (short)(1 + (startValue));
        array[2 + (startIndex)] = (short)(2 + (startValue));
        array[3 + (startIndex)] = (short)(startValue);
        array[4 + (startIndex)] = (short)(2 + (startValue));
        array[5 + (startIndex)] = (short)(3 + (startValue));
    }
    
    public void checkTransformations(boolean updatePrevious){

        super.checkTransformations(updatePrevious);

        if (shine != null) {
            shine.accumulatedTranslateX = accumulatedTranslateX;
            shine.accumulatedTranslateY = accumulatedTranslateY;
            shine.accumulatedRotate = accumulatedRotate;
            shine.accumulatedScaleX = accumulatedScaleX;
            shine.accumulatedScaleY = accumulatedScaleY;

            shine.positionX = positionX;
            shine.positionY = positionY;
        }
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        if (specialBallAnimActive){
            long elapsedTime = Utils.getTime() - startTimeSpecialBallAnim;
            if (elapsedTime < specialBallAnimDuration){
                color.r = 0.8f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
                color.g = 0.4f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
                color.b = 0.5f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
            } else {
                specialBallAnimActive = false;
                color.r = 0f;
                color.g = 0f;
                color.b = 0f;
            }
            Log.e("bar", "color r "+color.r+ " g "+color.g+ " b "+color.b);
            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateFloatBuffer(colorsData);
            shine.setColor(new Color(color.r, color.g, color.b, 1.0f));
        }
        super.prepareRender(matrixView, matrixProjection);
        shine.prepareRender(matrixView, matrixProjection);
    }

    public void specialBarScale() {


        scale(0.1f * (1 / accumulatedScaleX), 0.0f);
        startTimeSpecialBallAnim = Utils.getTime();
        specialBallAnimActive = true;
    }

    public void moveLeft(float timePercentage) {
        if (!isMovable){
            return;
        }

        rightPress = false;

        if (!leftPress){
            leftPress = true;
            if (secretLevel1Steps == 0 || secretLevel1Steps == 3 || secretLevel1Steps == 6 || secretLevel1Steps == 7){

                Level.levelGoalsObject.notifySecretStepsToConquer(7 - secretLevel1Steps);

                secretLevel1Steps += 1;
                if (secretLevel1Steps == 8){
                    Level.levelGoalsObject.notifySecretLevelUnblocked(1);
                }
                Log.e(TAG, "secretLevel1Steps "+secretLevel1Steps);
            } else {
                secretLevel1Steps = 0;
            }//0 L 1 R 2 R 3 L 4 R 5 R 6 L 7 L

        }

        //Log.e(TAG, "moveLeft");
        if (!accelStarted || (accelStarted && (accelFinalVelocityX > 0f))) {
            //Log.e(TAG, "initAcceleration");
            if  (dvx > -initialDVX * 2f){
                accelerateFrom(PhysicalObject.ACCEL_TYPE_EXPONENTIAL, 2000, -initialDVX, 0f, -initialDVX * 2f, 0f);
            }

        }
        verifyAcceleration();
        //Log.e(TAG, "dvx "+dvx);
        vx = dvx * timePercentage;
        translate(vx, 0f);
        verifyWind();
        timeOfWindMove = 0;


    }

    public void moveRight(float timePercentage) {
        if (!isMovable){
            return;
        }

        leftPress = false;

        if (!rightPress){
            rightPress = true;

            if (secretLevel1Steps == 1 || secretLevel1Steps == 2 || secretLevel1Steps == 4 || secretLevel1Steps == 5){
                Level.levelGoalsObject.notifySecretStepsToConquer(7 - secretLevel1Steps);

                secretLevel1Steps += 1;
                Log.e(TAG, "secretLevel1Steps "+secretLevel1Steps);
            } else {
                secretLevel1Steps = 0;
            }
        }
        //Log.e(TAG, "moveRight");
        if (!accelStarted || (accelStarted && (accelFinalVelocityX < 0f))) {
           if  (dvx < initialDVX * 2f){
                accelerateFrom(PhysicalObject.ACCEL_TYPE_EXPONENTIAL, 2000, initialDVX, 0f, initialDVX * 2f, 0f);
           }
        }
        verifyAcceleration();

        //Log.e(TAG, "dvx "+dvx);
        vx = dvx * timePercentage;
        translate(vx, 0f);
        timeOfWindMove = 0;
        verifyWind();

    }

    public void stop(){
        rightPress = false;
        leftPress = false;

        if (accelStarted){
            accelStarted = false;
            accelPercentage = 0f;
            shineDecreaseAfterAccelerate.values.get(0)[1] = shine.numberForAnimation;
            shineDecreaseAfterAccelerate.start();
        }
        if (accelFinish){
            accelFinish = false;
        }
        vx = 0f;

        if (Game.wind != null){
            if (timeOfWindMove == 0) {
                timeOfWindMove = Utils.getTime();
            }
            Level.levelObject.levelGoalsObject.notifyBarMoveByWind(Utils.getTime() - timeOfWindMove);
        }

        verifyWind();
    }
    
    @Override
    public void checkAnimations(){
        super.checkAnimations();


        boolean edgeCollision = false;

        for (int i = 0; i < collisionsData.size(); i++) {
            if (collisionsData.get(i).object.name.equals("bordaD") || collisionsData.get(i).object.name.equals("bordaE")) {
                edgeCollision = true;
            }
        }

        if (edgeCollision) {
            if (!shineDecreaseAfterAccelerate.started) {
                shineDecreaseAfterAccelerate.values.get(0)[1] = shine.numberForAnimation;
                shineDecreaseAfterAccelerate.start();
            }
        }

        if (!shineDecreaseAfterAccelerate.started) {
            shine.alpha = accelPercentage*0.5f;
            shine.numberForAnimation = accelPercentage;
        } else {
            shine.alpha = shine.numberForAnimation *0.5f;
        }
        //Log.e(TAG, "shine numberForAnimation "+ shine.numberForAnimation);
        //Log.e(TAG, "shine numberForAnimation2 "+ shine.numberForAnimation2);
        shine.alpha += shine.numberForAnimation2 * 0.5f;

        //Log.e(TAG, "shine alpha "+ shine.alpha);

    }

    public void onCollision() {
        for (int i = 0; i < collisionsData.size(); i++){
            if (collisionsData.get(i).object.name.equals("bordaE")){
                Level.levelObject.levelGoalsObject.notifyLeftBorderTouch();
            }
            if (collisionsData.get(i).object.name.equals("bordaD")){
                Level.levelObject.levelGoalsObject.notifyRightBorderTouch();
            }

        }
    }
}
