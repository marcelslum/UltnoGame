package com.marcelslum.ultnogame;


public class TargetGroupData{
    int id;
    float x;
    float y;
    float width;
    float height;
    float alpha;
    int type;
    float lastDecayPercentage;
    
    TargetGroupData(int id, float x, float y, float width, float height, float alpha, int type, float lastDecayPercentage){
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.type = type;
        this.lastDecayPercentage = lastDecayPercentage;
    }
}
