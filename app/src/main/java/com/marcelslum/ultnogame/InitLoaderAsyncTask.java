package com.marcelslum.ultnogame;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class InitLoaderAsyncTask extends AsyncTask<Integer , Integer, Integer> {
    
    public static final String TAG = "InitLoaderAsyncTask";
    protected Integer doInBackground(Integer... i){
        Log.e(TAG, "doInBackground");
        try {
            Game.initData();
            //Storage.initializeStorage(Game.context, Levels.maxNumberOfLevels);
            //Levels.currentMaxLevel = Storage.getMaxLevel();
            //Game.currentDifficulty = Storage.getDificulty();
            //Game.changeDifficulty(Game.currentDifficulty);
            //Levels.currentLevelNumber = Storage.getActualLevel();
            if (isCancelled()){
                Log.e(TAG, "cancelado 1");
                Game.forInitGame = true;
                return 0;   
            }
            
            Game.initTime = Utils.getTime();
            
            //Texture.init();
            
            if (isCancelled()){
                Log.e(TAG, "cancelado 2");
                Game.forInitGame = true;
                return 0;   
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // TODO
                Sound.init();
            }

            if (isCancelled()){
                Log.e(TAG, "cancelado 3");
                Game.forInitGame = true;
                return 0;   
            }
        } catch (Exception e) {
            Log.e(TAG, "catch");
            Log.e(TAG, "error", e);
            return 0;
        }
        return 0;
    }

    protected void onProgressUpdate() {
    }
    
    @Override
    protected void onCancelled(Integer i) {
        Log.e(TAG, "onCancelled");
        Game.forInitGame = true;
        Splash.loaderConclude = false;
    }
    
    protected void onPostExecute(Integer i) {
        Log.e(TAG, "onPostExecute");
        Splash.loaderConclude = true;
     }
 }
