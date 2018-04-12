package com.marcelslum.ultnogame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;

/**
 * Created by marcel on 09/10/2016.
 */

public class ConnectionHandler {

    public final static int INTERNET_STATE_CONNECTED = 1;
    public final static int INTERNET_STATE_NOT_CONNECTED = 2;
    public static int internetState = INTERNET_STATE_NOT_CONNECTED;

    public static void connect() {
        ConnectivityManager cm = (ConnectivityManager)
                Game.mainActivity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            if (internetState == INTERNET_STATE_NOT_CONNECTED){
                Game.mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Game.mainActivity.loadBannerAd();
                        Game.mainActivity.loadInterstitialAd();
                    }
                });
                
                GoogleAPI.loadAchievements();
            }
            internetState = INTERNET_STATE_CONNECTED;
        } else {
            internetState = INTERNET_STATE_NOT_CONNECTED;
        }
    }

    public static boolean isConnectionWifi(){
        ConnectivityManager cm = (ConnectivityManager)
                Game.mainActivity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable() && info.isConnected()){
            if (info.getType() == ConnectivityManager.TYPE_WIFI){
                return true;
            }
        }
        return false;
    }
}
