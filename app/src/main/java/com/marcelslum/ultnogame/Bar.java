package com.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Bar extends Rectangle{

    long timeOfWindMove = 0;

    public static final String TAG = "Bar";

    long startTimeSpecialBallAnim = 0;
    long specialBallAnimDuration = 1000;
    boolean specialBallAnimActive = false;

    boolean leftPress = false;
    boolean rightPress = false;
    
    public float initialNormalDVX;
    public float initialNormalDVY;

    public int textureColorId = COLOR_BLACK;

    static final int COLOR_RED = 1;
    static final int COLOR_BLUE = 2;
    static final int COLOR_GREEN = 3;
    static final int COLOR_YELLOW = 4;
    static final int COLOR_ORANGE = 5;
    static final int COLOR_PINK = 6;
    static final int COLOR_PURPLE = 7;
    static final int COLOR_BLACK = 8;

    int secretLevel1Steps = 0;//0 L 1 R 2 R 3 L 4 R 5 R 6 L 7 L 8 R 9 R bar
    int secretLevel2Steps = 0; //0 L 1 R 2 L 3 L 4 L 5 R 6 R 7 L 8 R 9 L bar touch border
    //0 R 1 R 2 R 3 L 4 L 5 L 6 R 7 R 8 R 9 L bar inclination
    boolean secretLevel2LockStep = false;

    Image shine;
    Animation shineDecreaseAfterAccelerate;
    Animation shineAfterBallCollision;
    
    
     public void updateBaseVelocity(int newVelocity){ 
        float percentageOfVelocity = (float) SaveGame.saveGame.ballVelocity / 100f;
        if (percentageOfVelocity != 1f){
            if (percentageOfVelocity < 1f){
                percentageOfVelocity = 1f - ((1f - percentageOfVelocity)/2f);   
            } else if (percentageOfVelocity > 1f){
                percentageOfVelocity = 1f + ((percentageOfVelocity - 1f)/2f);   
            }
        }
        initialDVX = initialNormalDVX * percentageOfVelocity;
    }
    


    public void setBarColor(int textureColorId){
        this.textureColorId = textureColorId;
        switch (textureColorId){
            case COLOR_RED:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_RED_ID));
                if (shine != null) {
                    shine.color = new Color(0.65f, 0f, 0f, 1f);
                }
                break;
            case COLOR_BLUE:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_BLUE_ID));
                if (shine != null) {
                    shine.color = new Color(0f, 0.04f, 0.69f, 1f);
                }
                break;
            case COLOR_GREEN:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_GREEN_ID));
                if (shine != null) {
                    shine.color = new Color(0.02f, 0.41f, 0.10f, 1f);
                }
                break;
            case COLOR_YELLOW:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_YELLOW_ID));
                if (shine != null) {
                    shine.color = new Color(0.74f, 0.73f, 0.33f, 1f);
                }
                break;
            case COLOR_ORANGE:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_ORANGE_ID));
                if (shine != null) {
                    shine.color = new Color(0.61f, 0.43f, 0.06f, 1f);
                }
                break;
            case COLOR_PINK:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_PINK_ID));;
                if (shine != null) {
                    shine.color = new Color(0.54f, 0.14f, 0.48f, 1f);
                }
                break;
            case COLOR_PURPLE:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_PURPLE_ID));
                if (shine != null) {
                    shine.color = new Color(0.31f, 0.04f, 0.69f, 1f);
                }
                break;
            case COLOR_BLACK:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_BLACK_ID));
                if (shine != null) {
                    shine.color = new Color(1f, 1f, 1f, 1f);
                }
                break;
            default:
                updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BAR_BLACK_ID));
                if (shine != null) {
                    shine.color = new Color(1f, 1f, 1f, 1f);
                }
                break;
        }
    }



    Bar(String name, float x, float y, float width, float height){
        super(name, x, y, Entity.TYPE_BAR, width, height, Game.BAR_WEIGHT, new Color(0.0f, 0.0f, 0.0f, 1.0f));
        program = Game.imageColorizedProgram;
        textureId = Texture.TEXTURES;
        isCollidable = true;
        isSolid = true;
        shine = new Image("shine", x, y, width, height, Texture.TEXTURES, TextureData.getTextureDataById(TextureData.TEXTURE_BAR_TOP_ID), new Color(1f, 1f, 1f, 1f));
        shine.alpha = 0f;
        shineDecreaseAfterAccelerate = Utils.createSimpleAnimation(shine, "shineDecreaseAfterAccelerate", "numberForAnimation", 500, 0f, 0f);
        shineAfterBallCollision = Utils.createAnimation3v(shine, "shineAfterBallCollision", "numberForAnimation2", 1000, 0f, 0, 0.2f, 1f, 1f, 0f, false, true);
        setDrawInfo();


    }

    public void setDrawInfo(){
        verticesData = new float[12];
        insertVerticesData(this.verticesData,0);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        indicesData = new short[6];
        insertIndicesData(this.indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        uvsData = new float[12];
        setBarColor(textureColorId);

        colorsData = new float[16];
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
    }


    public void insertVerticesData(float[] array, int startIndex){
        float x1 = 0f;
        float x2 = width;
        float y1 = 0f;
        float y2 = height;

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
    
    public void checkTransformations(boolean updatePrevious) {
        super.checkTransformations(updatePrevious);
        if (shine != null) {
            shine.accumulatedTranslateX = accumulatedTranslateX;
            shine.accumulatedTranslateY = accumulatedTranslateY;
            shine.accumulatedRotate = accumulatedRotate;
            shine.accumulatedScaleX = accumulatedScaleX;
            shine.accumulatedScaleY = accumulatedScaleY;
            shine.checkTransformations(updatePrevious);
            //Log.e(TAG, "positionY bar x shine " + accumulatedTranslateX + " - " + shine.accumulatedTranslateX);
            //Log.e(TAG, "positionY bar x shine " + accumulatedTranslateY + " - " + shine.accumulatedTranslateY);
            //Log.e(TAG, "positionY bar x shine " + accumulatedRotate + " - " + shine.accumulatedRotate);
            //Log.e(TAG, "positionY bar x shine " + accumulatedScaleX + " - " + shine.accumulatedScaleX);
            //Log.e(TAG, "positionY bar x shine " + accumulatedScaleY + " - " + shine.accumulatedScaleY);
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
            //Log.e("bar", "color r "+color.r+ " g "+color.g+ " b "+color.b);
            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
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
    public int checkAnimations(){
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

        return 0;

    }

    public void onCollision() {

        for (int i = 0; i < collisionsData.size(); i++){

            if (collisionsData.get(i).object.type == Entity.TYPE_LEFT_BORDER) {
                Level.levelObject.levelGoalsObject.notifyLeftBorderTouch();

            }

            if (collisionsData.get(i).object.type == Entity.TYPE_RIGHT_BORDER){
                Level.levelObject.levelGoalsObject.notifyRightBorderTouch();
            }
        }
    }
}
