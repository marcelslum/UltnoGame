package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTasks {

    public static final String TAG = "AsyncTasks";
    public static AsyncTask initLoader;
    public static void cancelAll(){
        if (initLoader != null){
            Log.e(TAG, "initLoader not null");
            if (initLoader.getStatus() == AsyncTask.Status.RUNNING){
                initLoader.cancel(true);
            }
        }
    }
}
