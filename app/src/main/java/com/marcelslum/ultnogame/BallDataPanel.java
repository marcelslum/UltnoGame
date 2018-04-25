package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{

    private static int [] vbo;
    private static int [] ibo;

    static Text textAngle1;
    static Text textAngle2;
    static Text textVelocity1;
    static Text textVelocity2;

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
    Rectangle bordaBmeioEFront;
    Rectangle bordaBmeioDFront;

    Rectangle bordaB3;
    Rectangle bordaB4;
    Rectangle bordaB5;
    Rectangle bordaB6;
    Rectangle bordaB7;
    Rectangle bordaB8;
    Rectangle bordaB9;
    Rectangle bordaB10;

    Rectangle bordaBC1;
    Rectangle bordaBC2;
    Rectangle bordaBD1;
    Rectangle bordaBD2;
    Rectangle bordaBB1;
    Rectangle bordaBB2;
    Rectangle bordaBE1;
    Rectangle bordaBE2;

    Rectangle [] rectangles = new Rectangle[32];

    Ball ballAnimating;
    
    private static final Color COLOR_BAR_GREEN_DARK = new Color (0.3f, 0.6f, 0.59f, 1f);
    private static final Color COLOR_BAR_GREEN_LIGHT = new Color (0.65f, 0.83f, 0.82f, 1f);
    private static final Color COLOR_BAR_BLUE_DARK = new Color (0.31f, 0.37f, 0.74f, 1f);//54 69 164
    private static final Color COLOR_BAR_BLUE_LIGHT = new Color (0.79f, 0.82f, 1f, 1f); //202 204 256
    private static final Color COLOR_BACK = new Color (0.28f, 0.28f, 0.28f, 0.8f);
    private static final Color COLOR_BAR_GREEN_LIGHT_EFFECT = new Color (0.65f, 0.83f, 0.82f, 1f);
    private static final Color COLOR_BAR_BLUE_LIGHT_BORDER = new Color (0.79f, 0.82f, 1f, 0.15f);
    private static final Color COLOR_BAR_BLUE_DARK_BORDER = new Color (0.31f, 0.37f, 0.43f, 0.17f);

   
    
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


        initRectangle = new Rectangle("initRectangle", ballDataPanelX - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1, new Color(0.19f, 0.19f, 0.19f, 1f));
        finalRectangle = new Rectangle("finalRectangle", ballDataPanelX + width - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1, new Color(0.19f, 0.19f, 0.19f, 1f));

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

        float markSize =  width*0.008f;
        endVelocity = new Rectangle("velocityRectangle", ballDataPanelX + width - markSize, ballDataPanelY, Entity.TYPE_OTHER, markSize, baseHeight *2f, -1, COLOR_BAR_GREEN_DARK);
        endAngle = new Rectangle("velocityRectangle", ballDataPanelX + width - markSize, ballDataPanelY + (baseHeight * 3f), Entity.TYPE_OTHER, markSize, baseHeight *2f, -1, COLOR_BAR_BLUE_DARK);

        ////// BORDAS

        float bordaEsp = Game.gameAreaResolutionY * 0.008f;
        float bordaEsp_1_4 = bordaEsp / 4f;

        bordaBmeio = new Rectangle("bordaBmeio", Game.resolutionX * 0.32f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, Game.resolutionX*0.36f, Game.resolutionY - Game.gameAreaResolutionY, -1, new Color(0.2f, 0.21f, 0.2f, 1f));
        bordaBmeioE = new Rectangle("bordaBmeioE", Game.resolutionX * 0.32f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, bordaEsp*2f, Game.resolutionY - Game.gameAreaResolutionY, -1,
               COLOR_BAR_BLUE_DARK_BORDER);//new Color(0.17f, 0.17f, 0.19f, 1f));
        bordaBmeioD = new Rectangle("bordaBmeioD", (Game.resolutionX * 0.68f) - (bordaEsp*1.5f), Game.gameAreaResolutionY,  Entity.TYPE_OTHER, bordaEsp*2f, Game.resolutionY - Game.gameAreaResolutionY, -1,
               COLOR_BAR_BLUE_DARK_BORDER);//new Color(0.17f, 0.17f, 0.19f, 1f));
        bordaBmeioEFront = new Rectangle("bordaBmeioEFront", Game.resolutionX * 0.32f + (bordaEsp*2f), Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
               COLOR_BAR_BLUE_LIGHT_BORDER);
        bordaBmeioDFront = new Rectangle("bordaBmeioDFront", (Game.resolutionX * 0.68f) - (bordaEsp*2f), Game.gameAreaResolutionY,  Entity.TYPE_OTHER, (bordaEsp*2f)/5f, Game.resolutionY - Game.gameAreaResolutionY, -1,
               COLOR_BAR_BLUE_LIGHT_BORDER);
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
        bordaB8 = new Rectangle("bordaB8", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.13f, bordaEsp*0.8f, -1, new Color(0.8f, 0.8f, 0.8f, 0.2f ));
        bordaB9 = new Rectangle("bordaB9", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.05f, bordaEsp*0.8f, -1, new Color(0.1f, 0.1f, 0.1f, 0.2f ));
        bordaB10 = new Rectangle("bordaB10", 0f, Game.gameAreaResolutionY, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.18f, bordaEsp*0.8f, -1, new Color(1f, 1f, 1f, 0.2f ));
        

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


        int number = 0;
        rectangles[number] = bordaBmeio;
        number +=1;
        rectangles[number] = bordaBmeioEffect;
        number +=1;
        rectangles[number] = bordaBmeioD;
        number +=1;
        rectangles[number] = bordaBmeioE;

        number +=1;
        rectangles[number] = bordaBmeioDFront;
        number +=1;
        rectangles[number] = bordaBmeioEFront;

        number +=1;
        rectangles[number] = backVelocityRectangle;
        number +=1;
        rectangles[number] = backAngleRectangle;
        number +=1;
        rectangles[number] = velocityNewRectangle;
        number +=1;
        rectangles[number] = angleNewRectangle;
        number +=1;
        rectangles[number] = velocityRectangle;
        number +=1;
        rectangles[number] = angleRectangle;
        number +=1;
        rectangles[number] = endVelocity;
        number +=1;
        rectangles[number] = endAngle;
        number +=1;
        rectangles[number] = initRectangle;
        number +=1;
        rectangles[number] = finalRectangle;

        number +=1;
        rectangles[number] = bordaBC1;
        number +=1;
        rectangles[number] = bordaBB1;
        number +=1;
        rectangles[number] = bordaBD1;
        number +=1;
        rectangles[number] = bordaBE1;

        number +=1;
        rectangles[number] = bordaBC2;
        number +=1;
        rectangles[number] = bordaBB2;
        number +=1;
        rectangles[number] = bordaBE2;
        number +=1;
        rectangles[number] = bordaBD2;

        number +=1;
        rectangles[number] = bordaB3;
        number +=1;
        rectangles[number] = bordaB4;
        number +=1;
        rectangles[number] = bordaB5;
        number +=1;
        rectangles[number] = bordaB6;
        number +=1;
        rectangles[number] = bordaB7;
        number +=1;
        rectangles[number] = bordaB8;
        number +=1;
        rectangles[number] = bordaB9;
        number +=1;
        rectangles[number] = bordaB10;


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

        Utils.createAnimation4v(bordaB8, "b8", "animTranslateX", 4800,
                0f, Game.gameAreaResolutionX,
                0.2f, Game.gameAreaResolutionX,
                0.5f, -Game.gameAreaResolutionX,
                1f, -Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB9, "b9", "animTranslateX", 5500,
                0f, -Game.gameAreaResolutionX,
                0.2f, -Game.gameAreaResolutionX,
                0.5f, Game.gameAreaResolutionX,
                1f, Game.gameAreaResolutionX,
                true, true).start();

        Utils.createAnimation4v(bordaB10, "b10", "animTranslateX", 3800,
                0f, Game.gameAreaResolutionX,
                0.2f, Game.gameAreaResolutionX,
                0.5f, -Game.gameAreaResolutionX,
                1f, -Game.gameAreaResolutionX,
                true, true).start();


        setDrawInfo();

    }


    public void checkDrawInfo(){

        for (int i = 0; i < rectangles.length; i++){

                //Log.e(TAG, "rectangles[i].positionX " + rectangles[i].positionX);
                //Log.e(TAG, "getTransformedWidth() " + rectangles[i].getTransformedWidth());
                //Log.e(TAG, "animScaleX " + rectangles[i].animScaleX);
                //Log.e(TAG, "positionY " + rectangles[i].positionY);
                //Log.e(TAG, "getTransformedHeight() " + rectangles[i].getTransformedHeight());
                //Log.e(TAG, "animScaleY " + rectangles[i].animScaleY);
                //Log.e(TAG, "rectangles[i].animTranslateX " + rectangles[i].animTranslateX);

                Utils.insertRectangleVerticesData(verticesData, i * 12,
                        rectangles[i].positionX + rectangles[i].animTranslateX,
                        rectangles[i].positionX + rectangles[i].animTranslateX + (rectangles[i].getTransformedWidth() * rectangles[i].animScaleX),
                        rectangles[i].positionY + rectangles[i].animTranslateY,
                        rectangles[i].positionY + rectangles[i].animTranslateY + (rectangles[i].getTransformedHeight() * rectangles[i].animScaleY), 0f);

                Utils.insertRectangleColorsData(colorsData, i * 16, rectangles[i].color.r, rectangles[i].color.g, rectangles[i].color.b, rectangles[i].color.a * rectangles[i].alpha * alpha);
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        verticesBuffer.limit(0);
        verticesBuffer = null;

        colorsBuffer.limit(0);
        colorsBuffer = null;

    }

    public void setDrawInfo(){


        if (vbo == null || vbo.length == 0) {
            vbo = new int[2];
            GLES20.glGenBuffers(2, vbo, 0);
            Log.e(TAG, " ballDataPanel " + "vbo create " + " vbo " + vbo[0] + " " + vbo[1]);
        }

        if (ibo == null || ibo.length == 0) {
            ibo = new int[1];
            GLES20.glGenBuffers(1, ibo, 0);
            Log.e(TAG, " ballDataPanel " + " ibo create " + ibo[0]);
        }

        initializeData(12 * rectangles.length, 6 * rectangles.length, 0, 16 * rectangles.length);

        for (int i = 0; i < rectangles.length; i++){

                Utils.insertRectangleVerticesData(verticesData, i * 12, rectangles[i].x,
                        rectangles[i].x + rectangles[i].width, rectangles[i].y, rectangles[i].y + rectangles[i].height, 0f);

                Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

                Utils.insertRectangleColorsData(colorsData, i * 16, rectangles[i].color.r, rectangles[i].color.g, rectangles[i].color.b, rectangles[i].color.a);

        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
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
        GLES20.glUniform1f(uf_alphaHandle, 1f);


        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glEnableVertexAttribArray(av4_colorsHandle);
        GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);

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

        if (rectangles == null){
            return;
        }
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].checkTransformations(updatePrevious);
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

        if (rectangles == null){
            return 0;
        }

        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].checkAnimations();
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
