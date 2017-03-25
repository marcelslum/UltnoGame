package com.marcelslum.ultnogame;

import android.util.Log;

public class BarDataBaseData{
    public float width;
    public float height;
    public float x;

    public BarDataBaseData(float width, float height, float x, float y, float vx) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.vx = vx;
        logData();
    }

    public float y;
    public float vx;
    public final static String TAG = "BarDataBaseData";
    
    public void logData(){
        Log.e(TAG, "width "+width);
        Log.e(TAG, "height "+height);   
        Log.e(TAG, "x "+x);   
        Log.e(TAG, "y "+y);   
        Log.e(TAG, "vx "+vx);   
    }
}
