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

    /*
    float uvx1;
    float uvx2;
    float uvy1;
    float uvy2;
    float uvxMin;
    float uvxMax;
    float uvyMin;
    float uvyMax;
    float uvWidth;
    float uvHeight;
    boolean uvXUp;
    boolean uvYUp;
    */
    
    boolean lastMovePositive;
    
    float brickSize;
    float [] bricksX;
    float [] bricksY;
    TextureData [] bricksTextureData; 
    
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

        brickSize = width/30f;//width/30f;
        
        numberOfBricksOnX = Math.round(width / brickSize) + 2;
        numberOfBricksOnY = Math.round(height / brickSize) + 2;
        numberOfBricks =  numberOfBricksOnX * numberOfBricksOnY;

        Log.e(TAG, "brickSize "+brickSize);
        Log.e(TAG, "width "+width);
        Log.e(TAG, "numberOfBricksOnX "+numberOfBricksOnX);
        Log.e(TAG, "numberOfBricksOnY "+numberOfBricksOnY);
        Log.e(TAG, "numberOfBricks "+numberOfBricks);
        
        bricksX = new float[numberOfBricks];
        bricksY = new float[numberOfBricks];
        bricksTextureData = new TextureData[numberOfBricks];
        
        int i = 0;
        
        float initX = -brickSize;
        float initY = -brickSize;
        
        for (int iy = 0; iy < numberOfBricksOnY; iy++){
            for (int ix = 0; ix < numberOfBricksOnX; ix++){
                bricksX[i] = initX + (ix * brickSize); //+ (ix * brickSize * 0.1f);
                bricksY[i] = initY + (iy * brickSize);
                
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
            accumulatedTranslateX = ballCollidedFx/4;
            lastMovePositive = false;
        } else {
            accumulatedTranslateX = -ballCollidedFx/4;
            lastMovePositive = true;
        }  
    }

    public void changeDrawInfo(){

        //Log.e(TAG, "ballCollidedGreen "+ballCollidedGreen);
        //Log.e(TAG, "ballCollidedBlue "+ballCollidedBlue);
        //Log.e(TAG, "ballCollidedRed "+ballCollidedRed);
        //Log.e(TAG, "ballCollidedBlack "+ballCollidedBlack);


         for (int i = 0; i < numberOfBricks; i++){
             
            float alpha = 1f;
             
            float randonColor = Utils.getRandonFloat(0f, 1f);
            float randonGray = Utils.getRandonFloat(0f, 1f);
            if (ballCollidedBlue == 2000 && randonColor < 0.07f){
                //Log.e(TAG, "1");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_BLUE);
                alpha = 0.5f;
            } else if (ballCollidedBlack == 2000 && randonColor > 0.07f && randonColor < 0.14f){
                //Log.e(TAG, "2");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_BLACK);
                alpha = 0.5f;
            } else if (ballCollidedGreen == 2000 && randonColor > 0.14f && randonColor < 0.21f){
                //Log.e(TAG, "3");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_GREEN);
                alpha = 0.5f;
            } else if (ballCollidedRed == 2000 && randonColor > 0.21f && randonColor < 0.28f){
                //Log.e(TAG, "4");
                bricksTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_BACK_RED);
                alpha = 0.5f;
            } else if (
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_BLUE && randonColor >= (float)ballCollidedBlue/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_BLACK && randonColor >= (float)ballCollidedBlack/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_GREEN && randonColor >= (float)ballCollidedGreen/2000f) ||
                (bricksTextureData[i].id == TextureData.TEXTURE_BACK_RED && randonColor >= (float)ballCollidedRed/2000f)
            ){
                //Log.e(TAG, "5");

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

            } else if (Utils.getRandonFloat(0f, 1f) > 0.9995f) {
                //Log.e(TAG, "6");
                alpha = 0.5f + (randonColor * 0.5f);
                
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

            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, bricksTextureData[i], alpha);
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                uvsBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        
        
        if (ballCollidedBlue > 0){
            ballCollidedBlue -= 1;
        }
        if (ballCollidedBlack > 0){
            ballCollidedBlack -= 1;
        }
        if (ballCollidedRed > 0){
            ballCollidedRed -= 1;
        }
        if (ballCollidedGreen > 0){
            ballCollidedGreen -= 1;
        }
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

            Log.e(TAG, "inserindo data "+ i + " -> "+bricksX[i]+ " "+bricksY[i]+ " "+ brickSize);

            
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            Log.e(TAG, "inserindo textureData "+i + " -> "+bricksTextureData[i].x);

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

        verticesBuffer.limit(0);
        verticesBuffer = null;
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        super.render(matrixView, matrixProjection);
    }
}
