package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 15/08/2016.
 */
public class Line extends Entity{
    float x2, y2;
    Line(String name, float x1, float y1, float x2, float y2, Color color){
        super(name, x1, y1, Entity.TYPE_LINE);
        this.color = color;
        this.x2 = x2;
        this.y2 = y2;
        isLineGL = true;
        program = Game.lineProgram;
        lineWidth = 4;
        setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(6, 2, 0, 8);
        Log.e("line set draw info", " "+x+" "+y+" "+x2+" "+y2);
        Utils.insertLineVerticesData(verticesData, 0,  0f, 0f, x2-x, y2-y, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertLineIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertLineColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }
}
