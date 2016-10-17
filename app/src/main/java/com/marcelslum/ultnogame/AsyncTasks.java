package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTasks {

    public static final String TAG = "AsyncTasks";
    public static AsyncTask initLoader;
    public static AsyncTask saveSnapshot;
    public static void cancelAll(){
        if (initLoader != null){
            if (initLoader.getStatus() == AsyncTask.Status.RUNNING){
                Log.e(TAG, "cancelando init loader");
                initLoader.cancel(true);
            }
        }
        if (saveSnapshot != null){
            if (saveSnapshot.getStatus() == AsyncTask.Status.RUNNING){
                Log.e(TAG, "cancelando saveSnapshot");
                saveSnapshot.cancel(false);
            }
        }
    }
}
