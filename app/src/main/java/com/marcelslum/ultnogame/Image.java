package com.marcelslum.ultnogame;

public class Image extends Entity{

    float width;
    float height;

    Image(String name, float x, float y, float width, float height, int textureUnit, TextureData textureData){
        super(name, x, y, Entity.TYPE_IMAGE);
        this.width = width;
        this.height = height;
        this.textureId = textureUnit;
        program = Game.imageProgram;
        this.textureData = textureData;
        setDrawInfo();
    }

    Image(String name, float x, float y, float width, float height, int textureUnit, TextureData textureData, Color color){
        super(name, x, y, Entity.TYPE_IMAGE);
        this.width = width;
        this.height = height;
        this.textureId = textureUnit;
        this.color = color;
        program = Game.imageProgram;
        this.textureData = textureData;
        setDrawInfo();
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
