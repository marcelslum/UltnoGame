package com.marcelslum.ultnogame;

public class Image extends Entity{

    float width;
    float height;
    float x1, x2, y1, y2;

    Image(String name, float x, float y, float width, float height, int textureUnit, float x1, float x2, float y1, float y2){
        super(name, x, y);
        this.width = width;
        this.height = height;
        this.textureId = textureUnit;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.program = Game.imageProgram;
        setDrawInfo();
    }
    
        Image(String name, float x, float y, float width, float height, int textureUnit, float[] uvData){
        super(name, x, y);
        this.width = width;
        this.height = height;
        this.textureId = textureUnit;
        this.x1 = uvData[0]
        this.x2 = uvData[1];
        this.y1 = uvData[2];
        this.y2 = uvData[3];
        this.program = Game.imageProgram;
        setDrawInfo();
    }

    Image(String name, float x, float y, float width, float height, int textureId, float x1, float x2, float y1, float y2, Color color){
        super(name, x, y);
        this.width = width;
        this.height = height;
        this.textureId = textureId;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.program = Game.imageColorizedProgram;
        setDrawInfo();
    }
    
    public void setColor(Color color){
        this.color = color;
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }

    public void setUvData(float x1, float x2, float y1, float y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        Utils.insertRectangleUvData(uvsData, 0, x1, x2, y1, y2);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    public void setDrawInfo(){
        if (this.color == null) {
            initializeData(12, 6, 8, 0);
        } else {
            initializeData(12, 6, 8, 16);
        }

        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvData(uvsData, 0, x1, x2, y1, y2);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);

        if (color != null){
            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateFloatBuffer(colorsData);
        }
    }
}
