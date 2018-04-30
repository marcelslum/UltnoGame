package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    private static int [] vboStatic;
    private static int [] iboStatic;

    private static int [] vboVariable;
    private static int [] iboVariable;

    private static int [] vboStaticFront;
    private static int [] iboStaticFront;

    static Text textAngle1;
    static Text textAngle2;
    static Text textVelocity1;
    static Text textVelocity2;

    Rectangle bordaB;
    Rectangle bordaE;
    Rectangle bordaC;
    Rectangle bordaD;


    Rectangle velocityRectangle;
    Rectangle angleRectangle;
    Rectangle velocityNewRectangle;
    Rectangle angleNewRectangle;
    Rectangle backVelocityRectangle;
    Rectangle backAngleRectangle;
    Rectangle endVelocity;
    Rectangle endAngle;
    Rectangle initRectangle;
    Rectangle finalRectangle;

    Rectangle bordaBmeio;
    Rectangle bordaBmeioEffect;
    Rectangle bordaBmeioE;
    Rectangle bordaBmeioD;
    /*
    Rectangle bordaBmeioEFront;
    Rectangle bordaBmeioDFront;
    Rectangle bordaBmeioEFront2;
    Rectangle bordaBmeioDFront2;

    */

    Rectangle bordaB3;
    Rectangle bordaB4;
    Rectangle bordaB5;
    Rectangle bordaB6;
    Rectangle bordaB7;

    Rectangle bordaBC1;
    Rectangle bordaBC2;
    Rectangle bordaBD1;
    Rectangle bordaBD2;
    Rectangle bordaBB1;
    Rectangle bordaBB2;
    Rectangle bordaBE1;
    Rectangle bordaBE2;

    public float[] verticesDataStatic;
    public short[] indicesDataStatic;
    public float[] colorsDataStatic;

    public float[] verticesDataStaticFront;
    public short[] indicesDataStaticFront;
    public float[] colorsDataStaticFront;

    public float[] verticesDataVariable;
    public short[] indicesDataVariable;
    public float[] colorsDataVariable;


    Rectangle [] rectanglesStatic = new Rectangle[12];
    Rectangle [] rectanglesVariable = new Rectangle[12];
    Rectangle [] rectanglesStaticFront = new Rectangle[5];

    Ball ballAnimating;

    public static final Color COLOR_BAR_GREEN_DARK = new Color (0.3f, 0.6f, 0.59f, 1f);
    public static final Color COLOR_BAR_GREEN_LIGHT = new Color (0.65f, 0.83f, 0.82f, 1f);
    public static final Color COLOR_BAR_BLUE_DARK = new Color (0.31f, 0.37f, 0.74f, 1f);//54 69 164
    private static final Color COLOR_BAR_BLUE_LIGHT = new Color (0.79f, 0.82f, 1f, 1f); //202 204 256
    private static final Color COLOR_BACK = new Color (0.28f, 0.28f, 0.28f, 0.8f);

    private static final Color COLOR_BAR_GREEN_LIGHT_EFFECT = new Color (0.65f, 0.83f, 0.82f, 1f);


    public static final Color COLOR_BORDER = new Color (0.11f, 0.25f, 0.25f, 1f);
    public static final Color COLOR_BORDER_TOP = new Color(0.2f, 0.2f, 0.2f, 1f);
    public static final Color COLOR_BORDER_TOP_INSIDE = new Color(0.5f, 0.5f, 0.46f, 1f);
    public static final Color COLOR_PANEL = new Color(0.16f, 0.14f, 0.13f, 1f); //ivoryblack

    public static final Color COLOR_PANEL_LIGHT_COLOR = new Color(0.55f, 0.55f, 0.51f, 1f); //ivory3


   
    
    float velocityPercent = 0f;
    float anglePercent = 0f;
    public static final String TAG = "BallDataPanel";

    float previousVelocityPercent = -1f;
    float previousAnglePercent = -1f;

    float width;
    float height;

    float ballDataPanelX;
    float ballDataPanelY;

    BallDataPanel(String name, float _x, float _y, float width, float height) {
        super(name, 0f, 0f, Entity.TYPE_PANEL);

        ballDataPanelX = _x;
        ballDataPanelY = _y;

        this.width = width;
        this.height = height;
        isCollidable = false;
        isMovable = false;
        isSolid = false;
        float  baseHeight = height/5f;

        this.program = Game.solidProgram;

        float initRectangleSize = width * 0.01f;

        if (textVelocity1 == null){
            textVelocity1 = new Text();
            textVelocity2 = new Text();
            textAngle1 = new Text();
            textAngle2 = new Text();
        }

        textVelocity1.setData("textVelocity1", ballDataPanelX + (initRectangleSize * 2), ballDataPanelY - (baseHeight * 14), Game.gameAreaResolutionY * 0.03f, "Vel", Game.font, COLOR_BAR_GREEN_DARK, Text.TEXT_ALIGN_CENTER);
        textVelocity2.setData("textVelocity2", ballDataPanelX + (initRectangleSize * 2), ballDataPanelY - (baseHeight * 8), Game.gameAreaResolutionY * 0.03f, "+", Game.font, COLOR_BAR_GREEN_LIGHT, Text.TEXT_ALIGN_CENTER);
        textAngle1.setData("textAngle1", ballDataPanelX + width - (initRectangleSize * 2), ballDataPanelY - (baseHeight * 14), Game.gameAreaResolutionY * 0.03f, "Ang", Game.font, COLOR_BAR_BLUE_DARK, Text.TEXT_ALIGN_CENTER);
        textAngle2.setData("textAngle2", ballDataPanelX + width - (initRectangleSize * 2), ballDataPanelY - (baseHeight * 8), Game.gameAreaResolutionY * 0.03f, "-", Game.font, COLOR_BAR_BLUE_LIGHT, Text.TEXT_ALIGN_CENTER);
        textVelocity1.alpha = 0f;
        textVelocity2.alpha = 0f;
        textAngle1.alpha = 0f;
        textAngle2.alpha = 0f;


        bordaE = new Rectangle("bordaEDataPanel", -999, 0, Entity.TYPE_OTHER, 1000, Game.resolutionY*2, -1, Color.pretoCheio);
        bordaD = new Rectangle("bordaDDataPanel", Game.resolutionX-2, 0,  Entity.TYPE_OTHER, 1000, Game.resolutionY*2,-1, Color.pretoCheio);
        bordaC = new Rectangle("bordaCDataPanel",  1, -1000,  Entity.TYPE_OTHER, Game.resolutionX-4, 1001,-1, Color.pretoCheio);
        bordaB = new Rectangle("bordaBDataPanel", -1000, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, Game.resolutionX*3, 1000,-1, Game.COLOR_BORDA_B);

        initRectangle = new Rectangle("initRectangle", ballDataPanelX - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1,  COLOR_PANEL);
        finalRectangle = new Rectangle("finalRectangle", ballDataPanelX + width - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1,  COLOR_PANEL);

        velocityRectangle = new Rectangle("velocityRectangle", ballDataPanelX, ballDataPanelY, Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BAR_GREEN_DARK);
        velocityNewRectangle = new Rectangle("velocityNewRectangle", ballDataPanelX, ballDataPanelY, Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BAR_GREEN_LIGHT);

        velocityRectangle.animScaleX = 0.008f;
        velocityNewRectangle.animScaleX = 0.008f;

        angleRectangle = new Rectangle("angleRectangle", ballDataPanelX, ballDataPanelY + (baseHeight * 3f), Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BAR_BLUE_DARK);
        angleNewRectangle = new Rectangle("angleNewRectangle", ballDataPanelX, ballDataPanelY + (baseHeight * 3f), Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BAR_BLUE_LIGHT);

        backVelocityRectangle = new Rectangle("backVelocityRectangle", ballDataPanelX, ballDataPanelY, Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BACK);
        backAngleRectangle = new Rectangle("backAngleRectangle", ballDataPanelX, ballDataPanelY + (baseHeight * 3f), Entity.TYPE_OTHER, width, baseHeight *2f, -1, COLOR_BACK);

        angleRectangle.animScaleX = 0.008f;
        angleNewRectangle.animScaleX = 0.008f;

        ////// BORDAS

        float bordaEsp = Game.gameAreaResolutionY * 0.008f;
        float bordaEsp_1_4 = bordaEsp / 4f;

        bordaBmeio = new Rectangle("bordaBmeio", Game.resolutionX * 0.32f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, Game.resolutionX*0.36f, Game.resolutionY - Game.gameAreaResolutionY, -1, COLOR_PANEL);

        bordaBmeioE = new Rectangle("bordaBmeioE", Game.resolutionX * 0.32f - (bordaEsp*0.5f), Game.gameAreaResolutionY + (bordaEsp*0.8f),  Entity.TYPE_OTHER, bordaEsp*1.05f, Game.resolutionY - Game.gameAreaResolutionY - (bordaEsp*1.6f), -1,
                COLOR_BORDER);//new Color(0.17f, 0.17f, 0.19f, 1f));

        bordaBmeioE.setMultiColor(
                Game.COLOR_BORDA_B, COLOR_BORDER_TOP_INSIDE, Game.COLOR_BORDA_B, COLOR_BORDER_TOP_INSIDE);

        bordaBmeioD = new Rectangle("bordaBmeioD", (Game.resolutionX * 0.68f) - (bordaEsp*.8f), Game.gameAreaResolutionY + (bordaEsp*0.8f),  Entity.TYPE_OTHER, bordaEsp, Game.resolutionY - Game.gameAreaResolutionY - (bordaEsp*1.6f), -1,
                COLOR_BORDER);//new Color(0.17f, 0.17f, 0.19f, 1f));

        bordaBmeioD.setMultiColor(
                COLOR_BORDER_TOP_INSIDE, Game.COLOR_BORDA_B, COLOR_BORDER_TOP_INSIDE, Game.COLOR_BORDA_B);


        /*
        // bordas final internas
        bordaBmeioEFront = new Rectangle("bordaBmeioEFront", Game.resolutionX * 0.32f + (bordaEsp), Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
                COLOR_BORDER_TOP_INSIDE);
        bordaBmeioDFront = new Rectangle("bordaBmeioDFront", (Game.resolutionX * 0.68f) - (bordaEsp), Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
                COLOR_BORDER_TOP_INSIDE);

        // bordar finas externas
        bordaBmeioEFront2 = new Rectangle("bordaBmeioEFront2", Game.resolutionX * 0.32f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
                COLOR_BORDER_TOP);
        bordaBmeioDFront2 = new Rectangle("bordaBmeioDFront2", Game.resolutionX * 0.68f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
                COLOR_BORDER_TOP);
                */


        bordaBmeioEffect = new Rectangle("bordaBmeioEffect", Game.resolutionX * 0.32f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, bordaEsp*3f, Game.resolutionY - Game.gameAreaResolutionY, -1,
                COLOR_BAR_GREEN_LIGHT_EFFECT);

        Utils.createAnimation4v(bordaBmeioEffect, "bordaBmeioEffectTranslate", "animTranslateX", 7000,
                0f, 0f,
                0.4f, 0f,
                0.43f, Game.resolutionX*0.36f,
                1f, Game.resolutionX*0.36f,
                true, true).start();

        Utils.createAnimation5v(bordaBmeioEffect, "bordaBmeioEffectAlpha", "alpha", 7000,
                0f, 0f,
                0.4f, 0f,
                0.415f, 0.4f,
                0.43f, 0f,
                1f, 0f,
                true, true).start();


        bordaB3 = new Rectangle("bordaB3", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.2f, bordaEsp*0.8f, -1, new Color(0.5f, 0.5f, 0.5f, 0.2f ));
        bordaB4 = new Rectangle("bordaB4", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.3f, bordaEsp*0.8f, -1, new Color(0.5f, 0.5f, 0.5f, 0.2f ));
        bordaB5 = new Rectangle("bordaB5", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.1f, bordaEsp*0.8f, -1, new Color(1f, 1f, 1f, 0.2f ));
        bordaB6 = new Rectangle("bordaB6", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.22f, bordaEsp*0.8f, -1, new Color(0.2f, 0.2f, 0.2f, 0.2f ));
        bordaB7 = new Rectangle("bordaB7", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.35f, bordaEsp*0.8f, -1, new Color(1f, 1f, 1f, 0.2f));


        Color colorBorda1 = new Color(0.3f, 0.3f, 0.3f, 0.8f);
        Color colorBorda1B = new Color(0.2f, 0.2f, 0.2f, 0.8f);
        Color colorBorda2 = new Color(0.3f, 0.3f, 0.31f, 1f);
        //colorBorda2 = Color.vermelhoCheio;

        bordaBC1 = new Rectangle("bordaBC1", 0f,Game.gameAreaResolutionY,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp*0.8f, -1, colorBorda1);
        bordaBB1 = new Rectangle("bordaBB1", 0f,Game.resolutionY - bordaEsp,Entity.TYPE_OTHER, Game.gameAreaResolutionX,    bordaEsp, -1, colorBorda1B);
        bordaBD1 = new Rectangle("bordaBD1", Game.gameAreaResolutionX - bordaEsp - (bordaEsp / 8f),Game.gameAreaResolutionY, Entity.TYPE_OTHER, bordaEsp*4f,  Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda1);
        bordaBE1 = new Rectangle("bordaBE1", bordaEsp / 8f,Game.gameAreaResolutionY,   Entity.TYPE_OTHER, bordaEsp,Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda1);

        bordaBC2 = new Rectangle("bordaBC2", 0f,Game.gameAreaResolutionY - (bordaEsp_1_4) /2f,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp_1_4, -1, colorBorda2);
        bordaBB2 = new Rectangle("bordaBB2", 0f, Game.resolutionY - bordaEsp_1_4,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp_1_4, -1, colorBorda2);
        bordaBD2 = new Rectangle("bordaBD2", Game.gameAreaResolutionX-(bordaEsp*0.75f),Game.gameAreaResolutionY, Entity.TYPE_OTHER, bordaEsp_1_4,  Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda2);
        bordaBE2 = new Rectangle("bordaBE2", bordaEsp / 8f,Game.gameAreaResolutionY,Entity.TYPE_OTHER, bordaEsp_1_4,Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda2);


        int numberStatic = 0;

        rectanglesStatic[numberStatic] = bordaB;
        numberStatic +=1;

        rectanglesStatic[numberStatic] = bordaBmeio;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = backVelocityRectangle;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = backAngleRectangle;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBC1;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBB1;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBD1;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBE1;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBC2;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBB2;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBE2;
        numberStatic +=1;
        rectanglesStatic[numberStatic] = bordaBD2;

        int numberVariable = 0;
        rectanglesVariable[numberVariable] = bordaB3;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaB4;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaB5;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaB6;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaB7;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaBmeioEffect;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = velocityNewRectangle;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = angleNewRectangle;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = velocityRectangle;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = angleRectangle;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaBmeioD;
        numberVariable +=1;
        rectanglesVariable[numberVariable] = bordaBmeioE;



        rectanglesStaticFront[0] = initRectangle;
        rectanglesStaticFront[1] = finalRectangle;
        rectanglesStaticFront[2] = bordaE;
        rectanglesStaticFront[3] = bordaD;
        rectanglesStaticFront[4] = bordaC;

        Utils.createAnimation4v(bordaB3, "b3", "animTranslateX", 4000,
                0f, -Game.gameAreaResolutionX,
                0.2f, -Game.gameAreaResolutionX,
                0.5f, Game.gameAreaResolutionX,
                1f, Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB4, "b4", "animTranslateX", 3750,
                0f, -Game.gameAreaResolutionX,
                0.2f, -Game.gameAreaResolutionX,
                0.5f, Game.gameAreaResolutionX,
                1f, Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB5, "b5", "animTranslateX", 4900,
                0f, -Game.gameAreaResolutionX,
                0.2f, -Game.gameAreaResolutionX,
                0.5f, Game.gameAreaResolutionX,
                1f, Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB6, "b6", "animTranslateX", 5500,
                0f, Game.gameAreaResolutionX,
                0.2f, Game.gameAreaResolutionX,
                0.5f, -Game.gameAreaResolutionX,
                1f, -Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB7, "b7", "animTranslateX", 4300,
                0f, Game.gameAreaResolutionX,
                0.2f, Game.gameAreaResolutionX,
                0.5f, -Game.gameAreaResolutionX,
                1f, -Game.gameAreaResolutionX,
                true, true).start();
        
        setDrawInfo();

    }


    public void checkDrawInfo(){

        for (int i = 0; i < rectanglesVariable.length; i++){

                //Log.e(TAG, "rectangles[i].positionX " + rectangles[i].positionX);
                //Log.e(TAG, "getTransformedWidth() " + rectangles[i].getTransformedWidth());
                //Log.e(TAG, "animScaleX " + rectangles[i].animScaleX);
                //Log.e(TAG, "positionY " + rectangles[i].positionY);
                //Log.e(TAG, "getTransformedHeight() " + rectangles[i].getTransformedHeight());
                //Log.e(TAG, "animScaleY " + rectangles[i].animScaleY);
                //Log.e(TAG, "rectangles[i].animTranslateX " + rectangles[i].animTranslateX);

                Utils.insertRectangleVerticesData(verticesDataVariable, i * 12,
                        rectanglesVariable[i].positionX + rectanglesVariable[i].animTranslateX,
                        rectanglesVariable[i].positionX + rectanglesVariable[i].animTranslateX + (rectanglesVariable[i].getTransformedWidth() * rectanglesVariable[i].animScaleX),
                        rectanglesVariable[i].positionY + rectanglesVariable[i].animTranslateY,
                        rectanglesVariable[i].positionY + rectanglesVariable[i].animTranslateY + (rectanglesVariable[i].getTransformedHeight() * rectanglesVariable[i].animScaleY), 0f);

                if (rectanglesVariable[i].multiColor){
                    rectanglesVariable[i].colorTopLeft.a *= alpha;
                    rectanglesVariable[i].colorTopRight.a *= alpha;
                    rectanglesVariable[i].colorBottomLeft.a *= alpha;
                    rectanglesVariable[i].colorBottomRight.a *= alpha;

                    Utils.insertRectangleColorsData(colorsDataVariable, i * 16, rectanglesVariable[i].colorTopLeft, rectanglesVariable[i].colorTopRight, rectanglesVariable[i].colorBottomLeft, rectanglesVariable[i].colorBottomRight);
                } else {
                    Utils.insertRectangleColorsData(colorsDataVariable, i * 16, rectanglesVariable[i].color.r, rectanglesVariable[i].color.g, rectanglesVariable[i].color.b, rectanglesVariable[i].color.a * rectanglesVariable[i].alpha * alpha);
                }

        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesDataVariable, verticesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsDataVariable, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        verticesBuffer.limit(0);
        verticesBuffer = null;

        colorsBuffer.limit(0);
        colorsBuffer = null;

    }

    public void setDrawInfo(){


        if (vboStatic == null || vboStatic.length == 0) {
            vboStatic = new int[2];
            GLES20.glGenBuffers(2, vboStatic, 0);
        }

        if (iboStatic == null || iboStatic.length == 0) {
            iboStatic = new int[1];
            GLES20.glGenBuffers(1, iboStatic, 0);
        }

        if (vboStaticFront == null || vboStaticFront.length == 0) {
            vboStaticFront = new int[2];
            GLES20.glGenBuffers(2, vboStaticFront, 0);
        }

        if (iboStaticFront == null || iboStaticFront.length == 0) {
            iboStaticFront = new int[1];
            GLES20.glGenBuffers(1, iboStaticFront, 0);
        }

        if (vboVariable == null || vboVariable.length == 0) {
            vboVariable = new int[2];
            GLES20.glGenBuffers(2, vboVariable, 0);
        }

        if (iboVariable == null || iboVariable.length == 0) {
            iboVariable = new int[1];
            GLES20.glGenBuffers(1, iboVariable, 0);
        }

        initializeDataStatic(12 * rectanglesStatic.length, 6 * rectanglesStatic.length, 0, 16 * rectanglesStatic.length);
        initializeDataStaticFront(12 * rectanglesStaticFront.length, 6 * rectanglesStaticFront.length, 0, 16 * rectanglesStaticFront.length);
        initializeDataVariable(12 * rectanglesVariable.length, 6 * rectanglesVariable.length, 0, 16 * rectanglesVariable.length);
        
        // static
        for (int i = 0; i < rectanglesStatic.length; i++){
                Utils.insertRectangleVerticesData(verticesDataStatic, i * 12, rectanglesStatic[i].x,
                        rectanglesStatic[i].x + rectanglesStatic[i].width, rectanglesStatic[i].y, rectanglesStatic[i].y + rectanglesStatic[i].height, 0f);

                Utils.insertRectangleIndicesData(indicesDataStatic, i * 6, i * 4);

                if (rectanglesStatic[i].multiColor){
                    Utils.insertRectangleColorsData(colorsDataStatic, i * 16, rectanglesStatic[i].colorTopLeft, rectanglesStatic[i].colorTopRight, rectanglesStatic[i].colorBottomLeft, rectanglesStatic[i].colorBottomRight);
                } else {
                    Utils.insertRectangleColorsData(colorsDataStatic, i * 16, rectanglesStatic[i].color.r, rectanglesStatic[i].color.g, rectanglesStatic[i].color.b, rectanglesStatic[i].color.a);
                }

        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesDataStatic, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesDataStatic, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsDataStatic, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStatic[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStatic[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboStatic[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        verticesBuffer.limit(0);
        verticesBuffer = null;
        colorsBuffer.limit(0);
        colorsBuffer = null;
        indicesBuffer.limit(0);
        indicesBuffer = null;

        // staticFront
        for (int i = 0; i < rectanglesStaticFront.length; i++){
            Utils.insertRectangleVerticesData(verticesDataStaticFront, i * 12, rectanglesStaticFront[i].x,
                    rectanglesStaticFront[i].x + rectanglesStaticFront[i].width, rectanglesStaticFront[i].y, rectanglesStaticFront[i].y + rectanglesStaticFront[i].height, 0f);

            Utils.insertRectangleIndicesData(indicesDataStaticFront, i * 6, i * 4);

            Utils.insertRectangleColorsData(colorsDataStaticFront, i * 16, rectanglesStaticFront[i].color.r, rectanglesStaticFront[i].color.g, rectanglesStaticFront[i].color.b, rectanglesStaticFront[i].color.a);
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesDataStaticFront, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesDataStaticFront, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsDataStaticFront, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStaticFront[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStaticFront[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboStaticFront[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        verticesBuffer.limit(0);
        verticesBuffer = null;
        colorsBuffer.limit(0);
        colorsBuffer = null;
        indicesBuffer.limit(0);
        indicesBuffer = null;


        // variable
        for (int i = 0; i < rectanglesVariable.length; i++){
            Utils.insertRectangleVerticesData(verticesDataVariable, i * 12, rectanglesVariable[i].x,
                    rectanglesVariable[i].x + rectanglesVariable[i].width, rectanglesVariable[i].y, rectanglesVariable[i].y + rectanglesVariable[i].height, 0f);

            Utils.insertRectangleIndicesData(indicesDataVariable, i * 6, i * 4);

            Utils.insertRectangleColorsData(colorsDataVariable, i * 16, rectanglesVariable[i].color.r, rectanglesVariable[i].color.g, rectanglesVariable[i].color.b, rectanglesVariable[i].color.a);
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesDataVariable, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesDataVariable, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsDataVariable, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboVariable[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        verticesBuffer.limit(0);
        verticesBuffer = null;
        colorsBuffer.limit(0);
        colorsBuffer = null;
        indicesBuffer.limit(0);
        indicesBuffer = null;
        
    }

    public void setColor(Color color){
        bordaBmeioE.setMultiColor(
                Game.COLOR_BORDA_B, Game.COLOR_BORDA_B, color, color);

        bordaBmeioD.setMultiColor(
                Game.COLOR_BORDA_B, Game.COLOR_BORDA_B, color, color);
    }

    public void initializeDataStatic(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        if (verticesSize > 0){
            if (verticesDataStatic == null || verticesDataStatic.length != verticesSize){
                verticesDataStatic = new float[verticesSize];
            }
        }
        if (indicesSize > 0){
            if (indicesDataStatic == null || indicesDataStatic.length != indicesSize){
                indicesDataStatic = new short[indicesSize];
            }

        }
        if (colorsSize > 0){
            if (colorsDataStatic == null || colorsDataStatic.length != colorsSize){
                colorsDataStatic = new float[colorsSize];
            }
        }
    }

    public void initializeDataStaticFront(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        if (verticesSize > 0){
            if (verticesDataStaticFront == null || verticesDataStaticFront.length != verticesSize){
                verticesDataStaticFront = new float[verticesSize];
            }
        }
        if (indicesSize > 0){
            if (indicesDataStaticFront == null || indicesDataStaticFront.length != indicesSize){
                indicesDataStaticFront = new short[indicesSize];
            }

        }
        if (colorsSize > 0){
            if (colorsDataStaticFront == null || colorsDataStaticFront.length != colorsSize){
                colorsDataStaticFront = new float[colorsSize];
            }
        }
    }

    public void initializeDataVariable(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        if (verticesSize > 0){
            if (verticesDataVariable == null || verticesDataVariable.length != verticesSize){
                verticesDataVariable = new float[verticesSize];
            }
        }
        if (indicesSize > 0){
            if (indicesDataVariable == null || indicesDataVariable.length != indicesSize){
                indicesDataVariable = new short[indicesSize];
            }

        }
        if (colorsSize > 0){
            if (colorsDataVariable == null || colorsDataVariable.length != colorsSize){
                colorsDataVariable = new float[colorsSize];
            }
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {

        if (!isVisible){
            return;
        }

        setMatrixModel();

        GLES20.glUseProgram(program.get());

        int av4_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av4_vertices");
        int av4_colorsHandle = GLES20.glGetAttribLocation(program.get(), "av4_colors" );

        int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
        int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
        int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");

        GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
        GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
        GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);


        int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");
        GLES20.glUniform1f(uf_alphaHandle, alpha);


        // static
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStatic[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStatic[1]);
        GLES20.glEnableVertexAttribArray(av4_colorsHandle);
        GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboStatic[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesDataStatic.length, GLES20.GL_UNSIGNED_SHORT, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);


        // variable
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboVariable[1]);
        GLES20.glEnableVertexAttribArray(av4_colorsHandle);
        GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboVariable[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesDataVariable.length, GLES20.GL_UNSIGNED_SHORT, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);


        // staticFront
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStaticFront[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboStaticFront[1]);
        GLES20.glEnableVertexAttribArray(av4_colorsHandle);
        GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, iboStaticFront[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesDataStaticFront.length, GLES20.GL_UNSIGNED_SHORT, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);


        // function for deleting buffers
        //final int[] buffersToDelete = new int[] { mCubePositionsBufferIdx, mCubeNormalsBufferIdx,
        //mCubeTexCoordsBufferIdx };
        //GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);

    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){

        checkAnimations();

        if (!MyGLRenderer.tick){
            checkDrawInfo();
        }

        render(matrixView, matrixProjection);

        if (isVisible) {
            textVelocity1.render(matrixView, matrixProjection);
            textVelocity2.render(matrixView, matrixProjection);
            textAngle1.render(matrixView, matrixProjection);
            textAngle2.render(matrixView, matrixProjection);
        }

    }

    @Override
    public void checkTransformations(boolean updatePrevious) {
        super.checkTransformations(updatePrevious);



        if (rectanglesVariable == null || !MyGLRenderer.tick){
            return;
        }
        for (int i = 0; i < rectanglesVariable.length; i++) {
            rectanglesVariable[i].checkTransformations(updatePrevious);
        }

        if (isVisible) {
            textVelocity1.checkTransformations(updatePrevious);
            textVelocity2.checkTransformations(updatePrevious);
            textAngle1.checkTransformations(updatePrevious);
            textAngle2.checkTransformations(updatePrevious);
        }

    }

    @Override
    public int checkAnimations() {

        super.checkAnimations();

        if (rectanglesVariable == null){
            return 0;
        }

        for (int i = 0; i < rectanglesVariable.length; i++) {
            rectanglesVariable[i].checkAnimations();
        }

        if (isVisible) {
            textVelocity1.checkAnimations();
            textVelocity2.checkAnimations();
            textAngle1.checkAnimations();
            textAngle2.checkAnimations();
        }

        return 0;
    }

    public void initAbdicateAngleAnim(){
        angleRectangle.animScaleX = 0.008f;
        angleNewRectangle.animScaleX = 0.008f;

        Utils.createAnimation3v(angleRectangle, "initAbdicateAngleAnim", "scaleX", 2000, 0f, 0.008f, 0.5f, 0.99f, 1f, 0.008f,true, true).start();
    }

    public void showVelocityMessage(String message){
        textVelocity1.cleanAnimations();
        textVelocity2.cleanAnimations();

        //Game.getContext().getResources().getString(R.string.mais)
        //textAngle2.setText(Game.getContext().getResources().getString(R.string.menos));

        textVelocity2.setText(message);
        Utils.createAnimation3v(textVelocity1, "alpha", "alpha", 2500, 0f, 0f, 0.12f, 1f, 1f, 0f, false, true).start();
        Utils.createAnimation3v(textVelocity2, "alpha", "alpha", 2500, 0f, 0f, 0.12f, 1f, 1f, 0f, false, true).start();

    }

    public void showAngleMessage(String message){

        textAngle1.cleanAnimations();
        textAngle2.cleanAnimations();

        textAngle2.setText(message);

        Utils.createAnimation3v(textAngle1, "alpha", "alpha", 2500, 0f, 0f, 0.12f, 1f, 1f, 0f, false, true).start();
        Utils.createAnimation3v(textAngle2, "alpha", "alpha", 2500, 0f, 0f, 0.12f, 1f, 1f, 0f, false, true).start();


    }

    public void setData(float velocityPercentage, float anglePercentage, boolean animationOn) {

        if (velocityPercentage < 0){
            velocityPercentage = 0f;
        }

        if (anglePercentage < 0){
            anglePercentage = 0f;
        }

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
                velocityRectangle.animScaleX = previousVelocityPercent;
                Utils.createSimpleAnimation(velocityRectangle, "velocityRectangle", "scaleX", 500, previousVelocityPercent, velocityPercent).start();

            } else if (velocityPercent < previousVelocityPercent){
                velocityRectangle.animScaleX = velocityPercent;
                velocityNewRectangle.animScaleX = previousVelocityPercent;
                Utils.createSimpleAnimation(velocityNewRectangle, "velocityRectangle", "scaleX", 500, previousVelocityPercent, velocityPercent).start();
            }
        } else {
            velocityNewRectangle.animScaleX = velocityPercent;
            velocityRectangle.animScaleX = velocityPercent;
        }

        if (!Game.abdicateAngle) {
            if (animationOn) {
                if (anglePercent > previousAnglePercent) {

                    angleNewRectangle.animScaleX = anglePercent;
                    angleRectangle.animScaleX = previousAnglePercent;
                    Utils.createSimpleAnimation(angleRectangle, "velocityRectangle", "scaleX", 500, previousAnglePercent, anglePercent).start();

                } else if (anglePercent < previousAnglePercent){

                    angleRectangle.animScaleX = anglePercent;
                    angleNewRectangle.animScaleX = previousAnglePercent;
                    Utils.createSimpleAnimation(angleNewRectangle, "velocityRectangle", "scaleX", 500, previousAnglePercent, anglePercent).start();
                }
            } else {
                angleNewRectangle.animScaleX = anglePercent;
                angleRectangle.animScaleX = anglePercent;
            }
        }
    }
}
