package com.marcelslum.ultnogame;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ImageBitmap extends Entity{

    float width;
    float height;
    Bitmap bitmap;

    ImageBitmap(String name, float x, float y, float width, float height, Bitmap bitmap){
        super(name, x, y, Entity.TYPE_IMAGE);
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
        program = Game.imageProgram;
        textureId = Texture.TEXTURE_PLAYER_ICON;

        Texture.textures.add(new Texture(textureId, bitmap));
        textureData = new TextureData(99999, "textureData"+name, 0f, 0f, 1f, 1f);
        setDrawInfo();
    }

    public void setBitmap(Bitmap bitmap){
        if (this.bitmap.equals(bitmap)){
            Log.e(TAG, "this.bitmap.equals(bitmap)");
            return;
        }
        this.bitmap = bitmap;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
    
    public void setColor(Color color){
        this.color = color;
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
    }

    @Override
    public float getTransformedWidth() {
        return width * accumulatedScaleX;
    }

    @Override
    public float getTransformedHeight() {
        return height * accumulatedScaleY;
    }

    public void setDrawInfo(){

        if (this.color == null) {
            initializeData(12, 6, 8, 0);
        } else {
            initializeData(12, 6, 8, 16);
        }

        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        Utils.insertRectangleUvData(uvsData, 0, textureData);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        if (color != null){
            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
        }
    }
}
