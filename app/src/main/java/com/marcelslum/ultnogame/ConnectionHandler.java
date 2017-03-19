package com.marcelslum.ultnogame;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by marcel on 09/10/2016.
 */

public class ConnectionHandler {

    public final static int INTERNET_STATE_CONNECTED = 1;
    public final static int INTERNET_STATE_NOT_CONNECTED = 2;
    private static final int TOTAL_MENU_CONNECTION_ATTEMPTS = 10;
    public static int internetState = INTERNET_STATE_NOT_CONNECTED;
    public static boolean mGoogleApiClientConected = false;
    public static boolean verifying = false;
    public static int menuConnectionAttempts = 0;



    public static void connect() {
        mGoogleApiClientConected = false;
        new InternetConnectionAsyncTask().execute("-");
    }

    public static void handleInternetConnection(Integer result) {

        if (verifying){
            verifying = false;
        }

        if (result == InternetConnectionAsyncTask.NOT_CONNECTED) {
            internetState = INTERNET_STATE_NOT_CONNECTED;
            if (Game.gameState != Game.GAME_STATE_INTRO) {
                Game.setGameState(Game.GAME_STATE_INTRO);
            } else {
                Splash.setSplashMessage(Splash.AGUARDA_MESSAGE_INTERNET_NAO_CONECTADA);
            }
        } else if (result == InternetConnectionAsyncTask.CONNECTED){
            Log.e("ConnectionHandler", "verificando conexão - conectado");

            if (Game.mainActivity.mGoogleApiClient != null && !Game.mainActivity.mGoogleApiClient.isConnected() && !Game.mainActivity.mGoogleApiClient.isConnecting()){
                Log.e("ConnectionHandler", "verificando conexão - google naõ conectado e não conectando");
                if (Game.gameState == Game.GAME_STATE_MENU && menuConnectionAttempts < TOTAL_MENU_CONNECTION_ATTEMPTS){
                    Log.e("ConnectionHandler", "menuConnectionAttempts "+menuConnectionAttempts);
                    new InternetConnectionAsyncTask().execute("");
                    menuConnectionAttempts += 1;
                } else if (Game.gameState != Game.GAME_STATE_INTRO) {
                    menuConnectionAttempts = 0;
                    Game.setGameState(Game.GAME_STATE_INTRO);
                }
            }
            internetState = INTERNET_STATE_CONNECTED;
        }
        /*
        if (result == InternetConnectionAsyncTask.ATTEMPT && internetState != INTERNET_STATE_CONNECTED){
            setBottomMessage(context.getResources().getString(R.string.conectando));
            return;
        }

        connectionAttempts += 1;
        final int fresult = result;

        final TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                setBottomMessage("");
            }
        };

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (fresult == InternetConnectionAsyncTask.CONNECTED){
                    if (internetState != INTERNET_STATE_CONNECTED) {
                        internetState = INTERNET_STATE_CONNECTED;
                        Log.e("game", "internetConnection connected");
                        setBottomMessage(context.getResources().getString(R.string.conexao));
                        timer.schedule(task2, 1500);
                    }
                } else if (fresult == InternetConnectionAsyncTask.NOT_CONNECTED){
                    Log.e("game", "internetConnection not connected");
                    internetState = INTERNET_STATE_NOT_CONNECTED;
                    setBottomMessage(context.getResources().getString(R.string.nao_conectado1));
                }
            }
        };
        timer.schedule(task, 2000);

        */
    }

    public static void verify() {
        verifying = true;
        new InternetConnectionAsyncTask().execute("-");
    }

    public static boolean isConnectedWifi(){
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
