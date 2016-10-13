package com.marcelslum.ultnogame;
import android.os.AsyncTask;
import android.util.Log;

public class InitLoaderAyncTask extends AsyncTask<Integer , Integer, Integer> {
    protected Integer doInBackground(Integer... i){
        Log.e("InitLoaderAyncTask", "doInBackground");
        Game.initData();
        Storage.initializeStorage(Game.context, Levels.maxNumberOfLevels);
        Levels.currentMaxLevel = Storage.getMaxLevel();
        Game.currentDifficulty = Storage.getDificulty();
        Game.changeDifficulty(Game.currentDifficulty);
        Levels.currentLevelNumber = Storage.getActualLevel();
        Game.initTime = Utils.getTime();
        Game.initTextures();
        Sound.init();
        Game.initMenus();
        Game.initTexts();
        Game.initEdges();
        return 0;
    }

    protected void onProgressUpdate() {
    }

    protected void onPostExecute(Integer i) {
        Log.e("InitLoaderAyncTask", "onPostExecute Splash.loaderConclude = true");
        Splash.loaderConclude = true;
     }
 }
