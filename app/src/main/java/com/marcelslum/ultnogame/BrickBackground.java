package com.marcelslum.ultnogame;

import android.util.Log;

public class BrickBackground extends Entity {
    float width;
    float height;
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
    float brickSize;
    float [] bricksX;
    float [] bricksY;
    float [] bricksUvMap;
    
    int numberOfBricksOnX;
    int numberOfBricksOnY;
    int numberOfBricks;
    
    

    Background(String name, float x, float y, float width, float height, int variationNumber) {
        super(name, x, y, Entity.TYPE_BACKGROUND);
        this.width = width;
        this.height = height;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        textureId = Texture.TEXTURE1;

        brickSize = width/30f;
        
        
        numberOfBricksOnX = Math.round(width / brickSize);
        numberOfBricksOnY = Math.round(height / brickSize);
        numberOfBricks =  numberOfBricksOnX * numberOfBricksOnY;
        
        
        

        Texture.getTextureById(textureId).changeBitmap(bitmap);

        program = Game.imageColorizedFxProgram;
        alpha = 0.8f;

        uvXUp = true;
        uvYUp = true;

        uvxMin = 0.001f;
        uvxMax = 0.999f;
        uvyMin = 0.001f;
        uvyMax = 0.585937f;

        uvWidth = 0.5f;
        uvHeight = 0.4f;
        uvx1 = 0.401f;
        uvx2 = uvx1 + uvWidth;
        uvy1 = 0.2f;
        uvy2 = uvy1 + uvHeight;

        setDrawInfo();
    }
    
    

    public void move(int velocity) {

        float vel = (float)velocity/10000;
        
        if (uvXUp){
            uvx1 += vel;
            uvx2 = uvx1 + uvWidth;
            if (uvx2 >= uvxMax){
                uvXUp = false;
                uvx1 -= vel*2;
                uvx2 = uvx1 + uvWidth;
            }
        } else {
            uvx1 -= vel;
            uvx2 = uvx1 + uvWidth;
            if (uvx1 <= uvxMin){
                uvXUp = true;
                uvx1 += vel*2;
                uvx2 = uvx1 + uvWidth;
            }
        }

        if (uvYUp){
            uvy1 += vel;
            uvy2 = uvy1 + uvHeight;
            if (uvy2 >= uvyMax){
                uvYUp = false;
                uvy1 -= vel*2;
                uvy2 = uvy1 + uvHeight;
            }
        } else {
            uvy1 -= vel;
            uvy2 = uvy1 + uvHeight;

            if (uvy1 <= uvyMin){
                uvYUp = true;
                uvy1 += vel*2;
                uvy2 = uvy1 + uvHeight;
            }
        }

        Utils.insertRectangleUvData(uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setDrawInfo() {
        initializeData(12, 6, 8, 0);

        Utils.insertRectangleVerticesData(verticesData, 0, -10f, width+20, -10f, height+20, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        Utils.insertRectangleUvData(uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        //Utils.insertRectangleColorsData(colorsData, 0, Utils.getRandonFloat(-0.1f, 0.1f),
        //        0f, Utils.getRandonFloat(-0.1f, 0.1f), 1f);
        //colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        super.render(matrixView, matrixProjection);
    }
}
