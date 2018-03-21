package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTasks {

    public static final String TAG = "AsyncTasks";

    public static AsyncTask initLoader;
    public static AsyncTask save;

    public static AsyncTask asyncPlaySucces1;
    public static AsyncTask asyncPlayGameOver;
    public static AsyncTask asyncPlayStarsUp;
    public static AsyncTask asyncPlayTextBoxAppear;
    public static AsyncTask asyncPlayWin;
    public static AsyncTask asyncPlayWin2;
    public static AsyncTask asyncPlayBlueBallExplosion;
    public static AsyncTask asyncPlaySucces2;
    public static AsyncTask asyncPlayMenuIconDrop;
    public static AsyncTask asyncPlayMenuSmall;
    public static AsyncTask asyncPlayMenuBig;
    public static AsyncTask asyncPlayCounter;
    public static AsyncTask asyncPlayExplosion;

    public static void cancelAll(){
        if (initLoader != null){
            if (initLoader.getStatus() == AsyncTask.Status.RUNNING){
                cancelAsyncTask(initLoader);
            }
        }
        if (save != null){
            cancelAsyncTask(save);
        }

        cancelAsyncTask(asyncPlaySucces1);
        cancelAsyncTask(asyncPlayGameOver);
        cancelAsyncTask(asyncPlayStarsUp);
        cancelAsyncTask(asyncPlayTextBoxAppear);
        cancelAsyncTask(asyncPlayWin);
        cancelAsyncTask(asyncPlayWin2);
        cancelAsyncTask(asyncPlayBlueBallExplosion);
        cancelAsyncTask(asyncPlaySucces2);
        cancelAsyncTask(asyncPlayMenuIconDrop);
        cancelAsyncTask(asyncPlayMenuSmall);
        cancelAsyncTask(asyncPlayMenuBig);
        cancelAsyncTask(asyncPlayCounter);
        cancelAsyncTask(asyncPlayExplosion);
    }

    public static void cancelAsyncTask(AsyncTask t){
        if (t != null && (t.getStatus() == AsyncTask.Status.RUNNING || t.getStatus() == AsyncTask.Status.PENDING)){
            t.cancel(true);
        }
    }
}
