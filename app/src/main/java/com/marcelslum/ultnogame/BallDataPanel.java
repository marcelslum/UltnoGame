package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by marcel on 14/10/2016.
 */

public class BallDataPanel extends Entity{


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

    Rectangle [] rectangles = new Rectangle[27];

    Ball ballAnimating;
    
    private static final Color COLOR_BAR_GREEN_DARK = new Color (0.3f, 0.6f, 0.59f, 1f);
    private static final Color COLOR_BAR_GREEN_LIGHT = new Color (0.65f, 0.83f, 0.82f, 1f);
    private static final Color COLOR_BAR_BLUE_DARK = new Color (0.31f, 0.37f, 0.74f, 1f);//54 69 164
    private static final Color COLOR_BAR_BLUE_LIGHT = new Color (0.79f, 0.82f, 1f, 1f); //202 204 256
    private static final Color COLOR_BACK = new Color (0.25f, 0.25f, 0.25f, 0.7f);
   
    
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


        vbo = new int[3];
        ibo = new int[1];

        this.width = width;
        this.height = height;
        isCollidable = false;
        isMovable = false;
        isSolid = false;
        float  baseHeight = height/5f;

        this.program = Game.solidProgram;

        float initRectangleSize = width * 0.01f;

        initRectangle = new Rectangle("initRectangle", ballDataPanelX - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1, Color.pretoCheio);
        finalRectangle = new Rectangle("finalRectangle", ballDataPanelX + width - initRectangleSize, ballDataPanelY - initRectangleSize/4f, Entity.TYPE_OTHER, initRectangleSize * 2f, (baseHeight * 5) + initRectangleSize/4f, -1, Color.pretoCheio);

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

        bordaBmeio = new Rectangle("bordaBmeio", Game.resolutionX * 0.35f, Game.gameAreaResolutionY,  Entity.TYPE_OTHER, Game.resolutionX*0.3f, Game.resolutionY - Game.gameAreaResolutionY, -1, new Color(0.018f, 0.018f, 0.2f, 1f));

        bordaB3 = new Rectangle("bordaB3", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.2f, Game.gameAreaResolutionY * 0.008f, -1, new Color(0.5f, 0.5f, 0.5f, 0.4f ));
        bordaB4 = new Rectangle("bordaB4", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.3f, Game.gameAreaResolutionY * 0.008f, -1, new Color(0.5f, 0.5f, 0.5f, 0.4f ));
        bordaB5 = new Rectangle("bordaB5", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.1f, Game.gameAreaResolutionY * 0.008f, -1, new Color(1f, 1f, 1f, 0.4f ));
        bordaB6 = new Rectangle("bordaB6", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.22f, Game.gameAreaResolutionY * 0.008f, -1, new Color(0.2f, 0.2f, 0.2f, 0.4f ));
        bordaB7 = new Rectangle("bordaB7", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.35f, Game.gameAreaResolutionY * 0.008f, -1, new Color(1f, 1f, 1f, 0.4f));
        bordaB8 = new Rectangle("bordaB8", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.13f, Game.gameAreaResolutionY * 0.008f, -1, new Color(0.8f, 0.8f, 0.8f, 0.3f ));
        bordaB9 = new Rectangle("bordaB9", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.05f, Game.gameAreaResolutionY * 0.008f, -1, new Color(0.1f, 0.1f, 0.1f, 0.4f ));
        bordaB10 = new Rectangle("bordaB10", 0f, Game.gameAreaResolutionX, Entity.TYPE_OTHER, Game.gameAreaResolutionX * 0.18f, Game.gameAreaResolutionY * 0.008f, -1, new Color(1f, 1f, 1f, 0.2f ));



        float bordaEsp = Game.gameAreaResolutionY * 0.008f;
        float bordaEsp_1_4 = bordaEsp / 4f;

        Color colorBorda1 = new Color(0.3f, 0.3f, 0.3f, 0.8f);
        Color colorBorda1B = new Color(0.2f, 0.2f, 0.2f, 0.8f);
        Color colorBorda2 = new Color(0.3f, 0.3f, 0.6f, 0.6f);

        bordaBC1 = new Rectangle("bordaBC1", 0f,Game.gameAreaResolutionY,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp, -1, colorBorda1);
        bordaBB1 = new Rectangle("bordaBB1", 0f,Game.resolutionY - bordaEsp,Entity.TYPE_OTHER, Game.gameAreaResolutionX,    bordaEsp, -1, colorBorda1B);
        bordaBD1 = new Rectangle("bordaBD1", Game.gameAreaResolutionX - bordaEsp,Game.gameAreaResolutionY, Entity.TYPE_OTHER, bordaEsp,  Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda1);
        bordaBE1 = new Rectangle("bordaBE1", 0f,Game.gameAreaResolutionY,   Entity.TYPE_OTHER, bordaEsp,Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda1);

        bordaBC2 = new Rectangle("bordaBC2", 0f,Game.gameAreaResolutionY,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp_1_4, -1, colorBorda2);
        bordaBB2 = new Rectangle("bordaBB2", 0f, Game.resolutionY - bordaEsp_1_4,Entity.TYPE_OTHER, Game.gameAreaResolutionX, bordaEsp_1_4, -1, colorBorda2);
        bordaBD2 = new Rectangle("bordaBD2", Game.gameAreaResolutionX-(bordaEsp_1_4),Game.gameAreaResolutionY, Entity.TYPE_OTHER, bordaEsp_1_4,  Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda2);
        bordaBE2 = new Rectangle("bordaBE2", 0f,Game.gameAreaResolutionY,Entity.TYPE_OTHER, bordaEsp_1_4,Game.resolutionY - Game.gameAreaResolutionY, -1, colorBorda2);


        rectangles[0] = bordaBmeio;

        rectangles[1] = backVelocityRectangle;
        rectangles[2] = backAngleRectangle;
        rectangles[3] = velocityNewRectangle;
        rectangles[4] = angleNewRectangle;
        rectangles[5] = velocityRectangle;
        rectangles[6] = angleRectangle;
        rectangles[7] = endVelocity;
        rectangles[8] = endAngle;
        rectangles[9] = initRectangle;
        rectangles[10] = finalRectangle;

        rectangles[11] = bordaBC1;
        rectangles[12] = bordaBB1;
        rectangles[13] = bordaBD1;
        rectangles[14] = bordaBE1;

        rectangles[15] = bordaB3;
        rectangles[16] = bordaB4;
        rectangles[17] = bordaB5;
        rectangles[18] = bordaB6;
        rectangles[19] = bordaB7;
        rectangles[20] = bordaB8;
        rectangles[21] = bordaB9;
        rectangles[22] = bordaB10;

        rectangles[23] = bordaBC2;
        rectangles[24] = bordaBB2;
        rectangles[25] = bordaBE2;
        rectangles[26] = bordaBD2;



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

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        verticesBuffer.limit(0);
        verticesBuffer = null;

        colorsBuffer.limit(0);
        colorsBuffer = null;

    }

    public void setDrawInfo(){

        GLES20.glGenBuffers(3, vbo, 0);
        GLES20.glGenBuffers(1, ibo, 0);

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

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
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

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
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

        return 0;
    }

    public void initAbdicateAngleAnim(){
        angleRectangle.animScaleX = 0.008f;
        angleNewRectangle.animScaleX = 0.008f;

        Utils.createAnimation3v(angleRectangle, "initAbdicateAngleAnim", "scaleX", 2000, 0f, 0.008f, 0.5f, 0.99f, 1f, 0.008f,true, true).start();
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
            } else {
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
                } else {
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
