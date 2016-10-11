package com.marcelslum.ultnogame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

/**
 * Created by marcel on 23/09/2016.
 */
public class LoadAchievementsAsyncTask extends AsyncTask<String,Integer,Integer> {

    @Override
    protected void onPreExecute(){
        ConnectionHandler.handleInternetConnection(ATTEMPT);
    }
    @Override
    protected Integer doInBackground(String... params) {
    
         boolean fullLoad = false;  // set to 'true' to reload all achievements (ignoring cache)
         float waitTime = 60.0f;    // seconds to wait for achievements to load before timing out

         // load achievements
         PendingResult p = Games.Achievements.load(Game.mGoogleApiClient, fullLoad);
         Achievements.LoadAchievementsResult r = (Achievements.LoadAchievementsResult)p.await( waitTime, TimeUnit.SECONDS );
         int status = r.getStatus().getStatusCode();
         if ( status != GamesStatusCodes.STATUS_OK )  {
            r.release();
            return;           // Error Occured
         }

         // cache the loaded achievements
         AchievementBuffer buf = r.getAchievements();
         int bufSize = buf.getCount();
         for ( int i = 0; i < bufSize; i++ )  {
            Achievement ach = buf.get( i );
            Achievements.add(ach);
         }
         buf.close();
         r.release();
    }
    @Override
    protected void onPostExecute(Integer result){
        ConnectionHandler.handleInternetConnection(result);
    }
    protected void onProgressUpdate(){
        //Codigo
    }

}
