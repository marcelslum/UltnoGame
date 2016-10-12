package com.marcelslum.ultnogame;
import android.os.AsyncTask;
import android.util.Log;

public class InitLoaderAyncTask extends AsyncTask<Integer , Integer, Integer> {

    protected Integer doInBackground(Integer... i){
        Log.e("InitLoaderAyncTask", "doInBackground");


        Game.initData();
        Storage.initializeStorage(Game.context, Game.quantityOfLevels);
        Game.maxLevel = Storage.getMaxLevel();
        Game.difficulty = Storage.getDificulty();
        Game.changeDifficulty(Game.difficulty);
        Game.levelNumber = Storage.getActualLevel();
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
