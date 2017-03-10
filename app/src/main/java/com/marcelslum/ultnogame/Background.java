package com.marcelslum.ultnogame;

import android.util.Log;

public class Background extends Entity {
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

    Background(String name, float x, float y, float width, float height, int variationNumber) {
        super(name, x, y);
        this.width = width;
        this.height = height;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        textureId = Texture.TEXTURE_BACKGROUND;
        String bitmap;

        switch (variationNumber){
            case 1:
                bitmap = "drawable/finalback1c";
                break;
            case 12:
                bitmap = "drawable/finalback2";
                break;
            case 13:
                bitmap = "drawable/finalback3";
                break;
            case 14:
                bitmap = "drawable/finalback4";
                break;
            case 15:
                bitmap = "drawable/finalback5";
                break;
            case 16:
                bitmap = "drawable/finalback6";
                break;
            case 17:
                bitmap = "drawable/finalback7";
                break;
            case 18:
                bitmap = "drawable/finalback8";
                break;
            default:
                bitmap = "drawable/finalback1c";
        }


        Log.e("background", "change bitmap of texture "+textureId);

        Texture.getTextureById(textureId).changeBitmap(bitmap);

        program = Game.imageColorizedFxProgram;
        this.alpha = 0.8f;

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

        Utils.insertRectangleUvData(this.uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
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

        Utils.insertRectangleVerticesData(this.verticesData, 0, -10f, width+20, -10f, height+20, 0f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        Utils.insertRectangleUvData(this.uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        super.render(matrixView, matrixProjection);
    }
}