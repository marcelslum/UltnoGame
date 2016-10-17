package com.marcelslum.ultnogame;


import android.util.Log;

/**
 * Created by marcel on 07/08/2016.
 */
public class Bar extends Rectangle{

    public static final String TAG = "Bar";

    long startTimeSpecialBallAnim = 0;
    long specialBallAnimDuration = 1000;
    boolean specialBallAnimActive = false;
    int textureMap = COLOR_BLACK;

    static final int [] UV_MAP = new int[]{0, 72, 144, 216, 288, 360, 432, 504, 576};
    static final int COLOR_RED = 1;
    static final int COLOR_BLUE = 2;
    static final int COLOR_GREEN = 3;
    static final int COLOR_YELLOW = 4;
    static final int COLOR_ORANGE = 5;
    static final int COLOR_PINK = 6;
    static final int COLOR_PURPLE = 7;
    static final int COLOR_BLACK = 8;


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
                break;
            case COLOR_BLUE:
                Utils.y1 = (UV_MAP[1] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[2] - 0.5f)/1024f;
                break;
            case COLOR_GREEN:
                Utils.y1 = (UV_MAP[2] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[3] - 0.5f)/1024f;
                break;
            case COLOR_YELLOW:
                Utils.y1 = (UV_MAP[3] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[4] - 0.5f)/1024f;
                break;
            case COLOR_ORANGE:
                Utils.y1 = (UV_MAP[4] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[5] - 0.5f)/1024f;
                break;
            case COLOR_PINK:
                Utils.y1 = (UV_MAP[5] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[6] - 0.5f)/1024f;
                break;
            case COLOR_PURPLE:
                Utils.y1 = (UV_MAP[6] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[7] - 0.5f)/1024f;
                break;
            case COLOR_BLACK:
                Utils.y1 = (UV_MAP[7] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[8] - 0.5f)/1024f;
                break;
            default:
                Utils.y1 = (UV_MAP[7] + 0.5f)/1024f;
                Utils.y2 = (UV_MAP[8] - 0.5f)/1024f;
                break;

        }

        Utils.insertRectangleUvData(uvsData, 0);
        uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }



    Bar(String name, float x, float y, float width, float height){
        super(name, x, y, width, height, Game.BAR_WEIGHT, new Color(0.0f, 0.0f, 0.0f, 1.0f));

        this.program = Game.imageColorizedProgram;
        this.textureId = Texture.TEXTURE_BARS;
        this.isCollidable = true;
        this.isSolid = true;
        this.setDrawInfo();
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
        }

        super.prepareRender(matrixView, matrixProjection);
    }

    public void specialBarScale() {
        scale(0.1f, 0.0f);
        startTimeSpecialBallAnim = Utils.getTime();
        specialBallAnimActive = true;
    }

    public void moveLeft(float timePercentage) {
        //Log.e(TAG, "moveLeft");
        if (!accelStarted || (accelStarted && (accelFinalVelocityX > 0f))) {
            //Log.e(TAG, "initAcceleration");
            accelerateFrom(PhysicalObject.ACCEL_TYPE_EXPONENTIAL, 2000, -initialDVX, 0f, -initialDVX * 2f, 0f);
        }
        verifyAcceleration();
        //Log.e(TAG, "dvx "+dvx);
        vx = dvx * timePercentage;
        translate(vx, 0f);
        verifyWind();
    }

    public void moveRight(float timePercentage) {
        //Log.e(TAG, "moveRight");
        if (!accelStarted || (accelStarted && (accelFinalVelocityX < 0f))) {
            //Log.e(TAG, "initAcceleration");
            accelerateFrom(PhysicalObject.ACCEL_TYPE_EXPONENTIAL, 2000, initialDVX, 0f, initialDVX * 2f, 0f);
        }
        verifyAcceleration();
        //Log.e(TAG, "dvx "+dvx);
        vx = dvx * timePercentage;
        translate(vx, 0f);
        verifyWind();
    }

    public void stop(){
        if (accelStarted){
            accelStarted = false;
        }
        vx = 0f;
    }
}
