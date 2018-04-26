package com.marcelslum.ultnogame;


import java.util.ArrayList;



public class Rectangle_Multi_Color extends Rectangle {

    Color colorBottomRight;
    Color colorBottomLeft;
    Color colorTopRight;
    Color colorTopLeft;

    Rectangle_Multi_Color(String name, float x, float y, int type, float width, float height, int weight, Color colorTopLeft, Color colorTopRight, Color colorBottomLeft, Color colorBottomRight){
        super(name, x, y, type, width, height, weight, colorTopLeft);
        this.colorBottomLeft = colorBottomLeft;
        this.colorBottomRight = colorBottomRight;
        this.colorTopLeft = colorTopLeft;
        this.colorTopRight = colorTopRight;
    }


    @Override
    public void setDrawInfo(){

        if (borderPercentage == 0f) {
            initializeData(12, 6, 0, 16);
        } else {
            initializeData(12*5, 6*5, 0, 16*5);
        }

        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, 0f);
        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        Utils.insertRectangleColorsData(colorsData, 0, colorTopLeft, colorTopRight, colorBottomLeft, colorBottomRight);

        if (borderPercentage != 0.0f) {
            for (int i = 0; i < 4; i++){
                if (i == 0){
                    Utils.insertRectangleVerticesData(verticesData, (i+1)*12,  0f, width, 0f, borderThicknes, 0f);
                } else if (i == 1){
                    Utils.insertRectangleVerticesData(verticesData, (i+1)*12,  0f, width, height - borderThicknes, height, 0f);
                } else if (i == 2){
                    Utils.insertRectangleVerticesData(verticesData, (i+1)*12,  0f, borderThicknes, 0f, height, 0f);
                } else {
                    Utils.insertRectangleVerticesData(verticesData, (i+1)*12,  width - borderThicknes, width, 0f, height, 0f);
                }
                Utils.insertRectangleIndicesData(indicesData, (i+1)*6, (i+1)*4);
                Utils.insertRectangleColorsData(colorsData, (i+1)*16, borderColor);
            }
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
    }
}
