package com.marcelslum.ultnogame;

import android.util.Log;

public class BrickBackground extends Entity {
    float width;
    float height;
    
    
    
    
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

        brickSize = width/30f;
        
        numberOfBricksOnX = Math.round(width / brickSize);
        numberOfBricksOnY = Math.round(height / brickSize);
        numberOfBricks =  numberOfBricksOnX * numberOfBricksOnY;
        
        bricksX = new float[numberOfBricks];
        bricksY = new float[numberOfBricks];
        
        
        int i = 0;
        
        for (int iy = 0; iy < numberOfBricksOnY; iy++){
            for (int ix = 0; ix < numberOfBricksOnY; ix++){
                bricksX[i] = ix * brickSize;
                bricksY[i] = iy * brickSize;
                
                int texture = Util.getRandonFloat(0f, 1f);
                if (texture < 0.2f){
                    bricksTextureData[i] = 0;
                } else if (texture < 0.2f) {
                    bricksTextureData[i] = 0;
                } else if (texture < 0.4f) {
                    bricksTextureData[i] = 0;
                } else if (texture < 0.6f) {
                    bricksTextureData[i] = 0;
                } else if (texture < 0.7f){
                    bricksTextureData[i] = 0;
                } else {
                    bricksTextureData[i] = 0;
                }   
                i += 1;
            }    
        }
        
        setDrawInfo();
    }


    public void setDrawInfo() {
        
        if (vbo == null || vbo.length == 0){
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
        
        initializeData(8 * numberOfBricks, 6 * numberOfBricks, 12 * numberOfBricks, 0);
        
        for (int i = 0; i < numberOfBricks; i++){
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                bricksX[i]
                                                bricksX[i] + brickSize
                                                bricksY[i], 
                                                bricksY[i] + brickSize,
                                                );
            
            
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
            
            Utils.insertRectangleUvAndAlphaData(uvData, i * 12, bricksTextureData[i], 1f){
            
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
