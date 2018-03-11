package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;

public class BrickBackground extends Entity {
    float width;
    float height;

    public final String TAG = "BrickBackground";
    
    static int ballCollidedFx;
    static int ballCollidedBlue;
    static int ballCollidedBlack;
    static int ballCollidedRed;
    static int ballCollidedGreen;

    boolean lastMovePositive;
    
    float brickSize;
    float [] bricksX;
    float [] bricksY;
    TextureData [] bricksTextureData;
    float [] bricksAlpha;


    float [] bricksVx;
    float [] bricksVy;
    float [] bricksTranslateX;
    float [] bricksTranslateY;

    boolean drawBack = false;

    int numberOfBricksOnX;
    int numberOfBricksOnY;
    int numberOfBricks;
    
    BrickBackground(String name, float x, float y, float width, float height) {
        super(name, x, y, Entity.TYPE_BACKGROUND);
        this.width = width;
        this.height = height;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        textureId = Texture.TEXTURES;
        program = Game.vertex_e_uv_com_alpha_program;


        brickSize = width/20f;//width/30f;
        
        numberOfBricksOnX = Math.round(width / brickSize) + 2;
        numberOfBricksOnY = Math.round(height / brickSize) + 2;
        numberOfBricks =  numberOfBricksOnX * numberOfBricksOnY;

        //Log.e(TAG, "brickSize "+brickSize);
        //Log.e(TAG, "width "+width);
        //Log.e(TAG, "numberOfBricksOnX "+numberOfBricksOnX);
        //Log.e(TAG, "numberOfBricksOnY "+numberOfBricksOnY);
        //Log.e(TAG, "numberOfBricks "+numberOfBricks);
        
        bricksX = new float[numberOfBricks];
        bricksY = new float[numberOfBricks];
        bricksTextureData = new TextureData[numberOfBricks];
        bricksAlpha = new float[numberOfBricks];

        bricksVx = new float[numberOfBricks];
        bricksVy = new float[numberOfBricks];
        bricksTranslateX = new float[numberOfBricks];
        bricksTranslateY = new float[numberOfBricks];

        int i = 0;
        
        float initX = -brickSize;
        float initY = -brickSize;
        
        for (int iy = 0; iy < numberOfBricksOnY; iy++){
            for (int ix = 0; ix < numberOfBricksOnX; ix++){
                bricksX[i] = initX + (ix * brickSize); //+ (ix * brickSize * 0.1f);
                bricksY[i] = initY + (iy * brickSize);
                bricksAlpha[i] = 1f;

                bricksVx[i] = (Game.resolutionX * Utils.getRandonFloat(0f, 0.0007f)) - (Game.resolutionX * 0.00035f);
                bricksVy[i] = (Game.resolutionX * Utils.getRandonFloat(0f, 0.0007f)) - (Game.resolutionX * 0.00035f);
                
                float texture = Utils.getRandonFloat(0f, 1f);
                if (texture < 0.2f){
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY1);
                } else if (texture < 0.4f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY2);
                } else if (texture < 0.6f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY3);
                } else if (texture < 0.8f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY4);
                } else {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY5);
                }
                i += 1;
            }    
        }
        
        setDrawInfo();
    }
    
    public void move(){
        
        if (ballCollidedFx > 0){
            ballCollidedFx -= 1;
        }
        
        if (lastMovePositive){
            accumulatedTranslateX = ballCollidedFx/12;
            lastMovePositive = false;
        } else {
            accumulatedTranslateX = -ballCollidedFx/12;
            lastMovePositive = true;
        }  
    }

    @Override
    public float getHeight(){
        return this.height;
    }

    @Override
    public float getWidth(){
        return this.width;
    }




    public void changeDrawInfo(){

        //Log.e(TAG, "ballCollidedGreen "+ballCollidedGreen);
        //Log.e(TAG, "ballCollidedBlue "+ballCollidedBlue);
        //Log.e(TAG, "ballCollidedRed "+ballCollidedRed);
        //Log.e(TAG, "ballCollidedBlack "+ballCollidedBlack);


        int totalBalls = Game.ballsNotInvencibleAlive + Game.ballsInvencible;

        float percentage = 0.045f - (0.01f * totalBalls);
        if (percentage < 0.005f){
            percentage = 0.005f;
        }


        //Log.e(TAG, " percentage "+percentage);

        float percentageGray = 0.9997f;
        if (Game.gameState == Game.GAME_STATE_VITORIA || Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
            percentageGray = 0.995f;
        }


         for (int i = 0; i < numberOfBricks; i++){

            float randonColor = Utils.getRandonFloat(0f, 1f);
            float randonGray = Utils.getRandonFloat(0f, 1f);


            if (ballCollidedBlue == 2000 && randonColor < percentage){
                //Log.e(TAG, "1");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_BLUE);
                bricksAlpha[i] = 0.3f;
            } else if (ballCollidedBlack == 2000 && randonColor > percentage && randonColor < percentage * 2f){
                //Log.e(TAG, "2");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_BLACK);
                bricksAlpha[i] = 0.3f;
            } else if (ballCollidedGreen == 2000 && randonColor > percentage * 2f && randonColor < percentage * 3f){
                //Log.e(TAG, "3");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GREEN);
                bricksAlpha[i] = 0.3f;
            } else if (ballCollidedRed == 2000 && randonColor > percentage * 3f && randonColor < percentage * 4f){
                //Log.e(TAG, "4");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_RED);
                bricksAlpha[i] = 0.3f;
            } else if (
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_BLUE && randonColor >= (float)ballCollidedBlue/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_BLACK && randonColor >= (float)ballCollidedBlack/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_GREEN && randonColor >= (float)ballCollidedGreen/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_RED && randonColor >= (float)ballCollidedRed/2000f)
            ){
                //Log.e(TAG, "5");
                bricksAlpha[i] = 0.5f;

                if (randonGray < 0.2f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY1);
                } else if (randonGray < 0.4f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY2);
                } else if (randonGray < 0.6f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY3);
                } else if (randonGray < 0.8f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY4);
                } else {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY5);
                }

            } else if (Utils.getRandonFloat(0f, 1f) > percentageGray) {
                //Log.e(TAG, "6");
                bricksAlpha[i] = 0.5f;
                
                if (randonGray < 0.2f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY1);
                } else if (randonGray < 0.4f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY2);
                } else if (randonGray < 0.6f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY3);
                } else if (randonGray < 0.8f) {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY4);
                } else {
                    bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GRAY5);
                }
            }

            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, bricksTextureData[i], 1f);
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                uvsBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        if (ballCollidedBlue > 0) {
            ballCollidedBlue -= 1;
        }

        if (ballCollidedRed > 0) {
            ballCollidedRed -= 1;
        }
        if (ballCollidedGreen > 0) {
            ballCollidedGreen -= 1;
        }

        if (ballCollidedBlack > 0) {
            ballCollidedBlack -= 1;
        }

    }

    public void animate(){

        drawBack = true;

        for (int i = 0; i < numberOfBricks; i++){

            if (bricksTranslateX[i] > Game.resolutionX * 0.2f || bricksTranslateX[i] < -Game.resolutionX * 0.2f){
                bricksVx[i] *= -1;
            }

            if (bricksTranslateY[i] > Game.resolutionX * 0.2f || bricksTranslateY[i] < -Game.resolutionX * 0.2f){
                bricksVy[i] *= -1;
            }

            bricksTranslateX[i] += bricksVx[i];
            bricksTranslateY[i] += bricksVy[i];

            Utils.insertRectangleVerticesDataXY(verticesData, i * 8,
                    bricksX[i] + bricksTranslateX[i],
                    bricksX[i] + bricksTranslateX[i] + brickSize,
                    bricksY[i] + bricksTranslateY[i],
                    bricksY[i] + bricksTranslateY[i] + brickSize
            );
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

    }



    public void setDrawInfo() {
        
        if (vbo == null || vbo.length == 0){
            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }

        initializeData(8 * numberOfBricks, 6 * numberOfBricks, 12 * numberOfBricks, 0);
        
        for (int i = 0; i < numberOfBricks; i++){

            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                bricksX[i],
                                                bricksX[i] + brickSize,
                                                bricksY[i], 
                                                bricksY[i] + brickSize
                                                );

            //Log.e(TAG, "inserindo data "+ i + " -> "+bricksX[i]+ " "+bricksY[i]+ " "+ brickSize);

            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            //Log.e(TAG, "inserindo textureData "+i + " -> "+bricksTextureData[i].x);

            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, bricksTextureData[i], 1f);
        }
            
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                        indicesBuffer, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        super.render(matrixView, matrixProjection);
    }
}

