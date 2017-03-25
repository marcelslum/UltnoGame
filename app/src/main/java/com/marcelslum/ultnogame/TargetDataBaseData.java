package com.marcelslum.ultnogame;

import android.util.Log;

public class TargetDataBaseData{
    public float width;

    public TargetDataBaseData(float width, float height, float distance, float padd) {
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.padd = padd;
        logData();
    }

    public float height;
    public float distance;
    public float padd;
    public final static String TAG = "TargetDataBaseData";
    
    public void logData(){
        Log.e(TAG, "width "+width);
        Log.e(TAG, "height "+height);   
        Log.e(TAG, "distance "+distance);   
        Log.e(TAG, "padd "+padd);    
    }
}
