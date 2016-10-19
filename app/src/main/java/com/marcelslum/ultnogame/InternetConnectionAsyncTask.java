package com.marcelslum.ultnogame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by marcel on 23/09/2016.
 */
public class InternetConnectionAsyncTask extends AsyncTask<String,Integer,Integer> {

    public static final int ATTEMPT = 2;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 0;

    @Override
    protected void onPreExecute(){
        ConnectionHandler.handleInternetConnection(ATTEMPT);
    }
    @Override
    protected Integer doInBackground(String... params) {
        ConnectivityManager cm = (ConnectivityManager)
                Game.mainActivity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return CONNECTED;
        } else {
            return NOT_CONNECTED;
        }
    }

    @Override
    protected void onCancelled(Integer result) {
        ConnectionHandler.handleInternetConnection(result);
    }



    @Override
    protected void onPostExecute(Integer result){
        ConnectionHandler.handleInternetConnection(result);
    }
    protected void onProgressUpdate(){
        //Codigo
    }

}
