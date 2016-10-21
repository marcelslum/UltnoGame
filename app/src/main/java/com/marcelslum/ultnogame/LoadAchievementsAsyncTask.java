package com.marcelslum.ultnogame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;


import java.util.concurrent.TimeUnit;

/**
 * Created by marcel on 23/09/2016.
 */
public class LoadAchievementsAsyncTask extends AsyncTask<String,Integer,Integer> {

    private static final String TAG = "LoadAchievementsAsync";

    @Override
    protected void onPreExecute(){

    }
    @Override
    protected Integer doInBackground(String... params) {
        Log.e(TAG, "doInBackground");
         boolean fullLoad = false;  // set to 'true' to reload all achievements (ignoring cache)
         long waitTime = 60l;    // seconds to wait for achievements to load before timing out

         // load achievements
         PendingResult<LoadAchievementsResult> p = Games.Achievements.load(Game.mainActivity.mGoogleApiClient, fullLoad);
         LoadAchievementsResult r = p.await( waitTime, TimeUnit.SECONDS);

         int status = r.getStatus().getStatusCode();
         if ( status != GamesStatusCodes.STATUS_OK )  {
            r.release();
            return -1;
         }

         AchievementBuffer buf = r.getAchievements();
         int bufSize = buf.getCount();
         for ( int i = 0; i < bufSize; i++ )  {
             if (isCancelled()){
                 break;
             }
            Achievement ach = buf.get( i );
            MyAchievements.add(ach);
         }
         r.release();
         return 0;
    }

    @Override
    protected void onCancelled(Integer i) {
        Log.e(TAG, "onCancelled");
        MyAchievements.loaded = false;
    }

    @Override
    protected void onPostExecute(Integer result){
        if (result == -1){
            MyAchievements.loaded = false;
        } else {
            MyAchievements.loaded = true;
        }
    }
    protected void onProgressUpdate(){
        //Codigo
    }
}
