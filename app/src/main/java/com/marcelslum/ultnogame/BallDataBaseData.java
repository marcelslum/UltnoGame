package com.marcelslum.ultnogame;

public class BallDataBaseData{

    public float radius;
    public float x;
    public float y;
    public float vx;
    public float vy;
    public int textureMap;
    public int invencible;
    public int angleToRotate;
    public int maxAngle;
    public int minAngle;
    public float velocityVariation;
    public float maxVelocity;
    public float minVelocity;
    public int free;
    
    public final static String TAG = "BallDataBaseData";
    
    public void logData(){
        Log.e(TAG, "radius "+radius);   
        Log.e(TAG, "x "+x);   
        Log.e(TAG, "y "+y);   
        Log.e(TAG, "vx "+vx);   
        Log.e(TAG, "vy "+vy);   
        Log.e(TAG, "textureMap "+textureMap);   
        Log.e(TAG, "invencible "+invencible);   
        Log.e(TAG, "angleToRotate "+angleToRotate);   
        Log.e(TAG, "maxAngle "+maxAngle);   
        Log.e(TAG, "minAngle "+minAngle);   
        Log.e(TAG, "velocityVariation "+velocityVariation);   
        Log.e(TAG, "maxVelocity "+maxVelocity);   
        Log.e(TAG, "minVelocity "+minVelocity);   
        Log.e(TAG, "free "+free);   
    }
    
    
}
