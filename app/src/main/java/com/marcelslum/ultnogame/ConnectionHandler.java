package com.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 09/10/2016.
 */

public class ConnectionHandler {

    public final static int INTERNET_STATE_CONNECTED = 1;
    public final static int INTERNET_STATE_NOT_CONNECTED = 2;
    public static int internetState = INTERNET_STATE_NOT_CONNECTED;
    public static boolean mGoogleApiClientConected = false;

    public static void connect() {
        mGoogleApiClientConected = false;
        new InternetConnectionAsyncTask().execute("-");
    }

    public static void handleInternetConnection(Integer result) {
        if (result == InternetConnectionAsyncTask.NOT_CONNECTED) {
            internetState = INTERNET_STATE_NOT_CONNECTED;
            if (Game.gameState != Game.GAME_STATE_INTRO) {
                Game.setGameState(Game.GAME_STATE_INTRO);
            } else {
                Splash.setSplashMessage(Splash.AGUARDA_MESSAGE_INTERNET_NAO_CONECTADA);
            }
        } else if (result == InternetConnectionAsyncTask.CONNECTED){
            if (Game.mainActivity.mGoogleApiClient != null && !Game.mainActivity.mGoogleApiClient.isConnected()){
                if (Game.gameState != Game.GAME_STATE_INTRO) {
                    Game.setGameState(Game.GAME_STATE_INTRO);
                }
            }
            internetState = INTERNET_STATE_CONNECTED;
        }
        /*
        if (result == InternetConnectionAsyncTask.ATTEMPT && internetState != INTERNET_STATE_CONNECTED){
            setBottomText(context.getResources().getString(R.string.conectando));
            return;
        }

        connectionAttempts += 1;
        final int fresult = result;

        final TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                setBottomText("");
            }
        };

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (fresult == InternetConnectionAsyncTask.CONNECTED){
                    if (internetState != INTERNET_STATE_CONNECTED) {
                        internetState = INTERNET_STATE_CONNECTED;
                        Log.e("game", "internetConnection connected");
                        setBottomText(context.getResources().getString(R.string.conexao));
                        timer.schedule(task2, 1500);
                    }
                } else if (fresult == InternetConnectionAsyncTask.NOT_CONNECTED){
                    Log.e("game", "internetConnection not connected");
                    internetState = INTERNET_STATE_NOT_CONNECTED;
                    setBottomText(context.getResources().getString(R.string.nao_conectado1));
                }
            }
        };
        timer.schedule(task, 2000);

        */
    }
}
