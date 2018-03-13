package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTasks {

    public static final String TAG = "AsyncTasks";
    public static AsyncTask initLoader;
    public static AsyncTask saveSnapshot;
    public static AsyncTask save;
    public static void cancelAll(){
        if (initLoader != null){
            if (initLoader.getStatus() == AsyncTask.Status.RUNNING){
                //Log.e(TAG, "cancelando init loader");
                initLoader.cancel(true);
            }
        }
        if (save != null){
            if (save.getStatus() == AsyncTask.Status.RUNNING){
                //Log.e(TAG, "cancelando saveSnapshot");
                save.cancel(false);
            }
        }
    }
}
