package com.marcelslum.ultnogame;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.os.Vibrator;
import android.widget.Switch;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity implements
        SensorEventListener
        {


    public static SensorManager mSensorManager;
    public static Sensor mAccelerometer;

    public MyGLSurface myGlSurface;
    private InterstitialAd interstitialWithVideo;
    private InterstitialAd interstitialNoVideo;
    private int interstitialActualMode = 0;
    public final static int INTERSTITIAL_MODE_WITH_VIDEO = 1;
    public final static int INTERSTITIAL_MODE_NO_VIDEO = 2;

    AdView mAdView;
    private final static String TAG = "MainActivity";
    public boolean isPaused = false;

    private boolean mResolvingError = false;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         /*Inicia o hardware*/
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Game.vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Splash.timesGoogle = 0;

        /*Inicia o banco de Level Data*/
        try {
            DataBaseLevelDataHelper.getInstance(this).prepareDatabase();
            if (!Game.forDebugDeleteDatabaseAndStorage) {
                Game.groupsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getGroupsDataBaseData();
                Game.levelsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getLevelsDataBaseData();
            }
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        /*Inicia o banco de Save Data*/
        try {
            DataBaseSaveDataHelper.getInstance(this).prepareDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        Storage.init(this);

        SaveGame.load();

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //Log.e(TAG, "maxMemory "+maxMemory);

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

        Game.mainActivity = this;
        Game.forInitGame = true;

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Create the client used to sign in to Google services.

        GoogleSignInOptions signInOption =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                        // Add the APPFOLDER scope for Snapshot support.
                        //.requestScopes(Drive.SCOPE_APPFOLDER)
                        .build();

        GoogleAPI.mGoogleSignInClient = GoogleSignIn.getClient(this,
                new GoogleSignInOptions.Builder(signInOption).build());

        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    // TODO: The system bars are visible. Make any desired
                    if (Game.gameState == Game.GAME_STATE_JOGAR) {
                        Game.setGameState(Game.GAME_STATE_PAUSE);
                    }
                } else {
                    // TODO: The system bars are NOT visible. Make any desired
                }
            }
        });

        // SURFACE VIEW
        myGlSurface = new MyGLSurface(this);
        myGlSurface.setPreserveEGLContextOnPause(true);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.gamelayout);
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(myGlSurface, glParams);

        // INICIA A PROPAGANDA
        mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-2413920269734587/4375714557");
        mAdView.setAdSize(AdSize.SMART_BANNER);

        // ADICIONA NO LAYOUT A PROPAGANDA
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.addView(mAdView, adParams);

        // INICIA A PROPAGANDA
        initAds();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    public boolean isSignedIn() {
        if (GoogleSignIn.getLastSignedInAccount(this) != null && SaveGame.saveGame.googleOption == 1){
            return true;
        } else {
            return false;
        }
    }

    public boolean isGooglePlayAvailable() {
        boolean googlePlayStoreInstalled;
        int val = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        googlePlayStoreInstalled = val == ConnectionResult.SUCCESS;


        //Log.e(TAG, "googlePlayStoreInstalled val "+ val);
        //Log.e(TAG, "googlePlayStoreInstalled "+ googlePlayStoreInstalled);

        return googlePlayStoreInstalled;
    }

    public void signInSilently() {
        //Log.e(TAG, "signInSilently()");

        GoogleAPI.mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            //Log.e(TAG, "signInSilently(): success");
                            onConnected(task.getResult());
                        } else {
                            //Log.e(TAG, "signInSilently(): failure", task.getException());
                            onDisconnected();
                        }
                    }
                });
    }

    public void startSignInIntent() {
        startActivityForResult(GoogleAPI.mGoogleSignInClient.getSignInIntent(), GoogleAPI.RC_SIGN_IN);
    }

    public void signOut() {
        Log.d(TAG, "signOut()");

        if (MenuHandler.menuOptions != null){
            MenuHandler.menuOptions.getMenuOptionByName("google").setText(getResources().getString(R.string.logarGoogle));
        }

        if (!isSignedIn()) {
            Log.w(TAG, "signOut() called, but was not signed in!");
            return;
        }

        GoogleAPI.mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        boolean successful = task.isSuccessful();
                        Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));
                        onDisconnected();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == GoogleAPI.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task =
                    GoogleSignIn.getSignedInAccountFromIntent(intent);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                onConnected(account);
            } catch (ApiException apiException) {
                String message = apiException.getMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }

                onDisconnected();

                // TODO erro ao logar no google - exibir mensagem

            }
        }
    }

    private void onConnected(final GoogleSignInAccount googleSignInAccount) {

        //Log.e(TAG, "onConnected");

        GoogleAPI.mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
        GoogleAPI.mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
        GoogleAPI.mEventsClient = Games.getEventsClient(this, googleSignInAccount);
        GoogleAPI.mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

        GoogleAPI.mPlayersClient.getCurrentPlayer()
                .addOnCompleteListener(new OnCompleteListener<Player>() {
                    @Override
                    public void onComplete(@NonNull Task<Player> task) {
                        if (task.isSuccessful()) {
                            //Log.e(TAG, "player name atualizado para " + task.getResult().getDisplayName());
                            Game.playerName = task.getResult().getDisplayName();
                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleLogado) + "\u0020" + Game.playerName);
                            }
                        } else {
                            //Log.e(TAG, "Não foi possível carregar o nome do jogador");
                            Game.playerName = ".";
                            signOut();
                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleErroLogar));
                            }

                        }
                    }
                });
    }

    public boolean checkGoogleConnection(){
        //Log.e(TAG, "checkGoogleConnection");
        if (isGooglePlayAvailable() && isSignedIn()){
            //Log.e(TAG, "checking");
            if (Game.playerName.equals(".") || Game.playerName.equals("-")){
                //Log.e(TAG, "possible not connected - erro");
                signOut();
                if (MessagesHandler.messageGoogleLogged != null) {
                    MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleErroLogar));
                }
                return false;
            }
        }
        return true;
    }

    private void onDisconnected() {

        Log.d(TAG, "onDisconnected()");

        GoogleAPI.mAchievementsClient = null;
        GoogleAPI.mLeaderboardsClient = null;
        GoogleAPI.mPlayersClient = null;

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

	public void setFullScreen() {
        final Activity fa = this;
        runOnUiThread(new Runnable() {
            public void run() {
            int uiOptions = fa.getWindow().getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 18) {
                uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            fa.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
	}

    @Override
    protected void onPause() {

        DataBaseLevelDataHelper.getInstance(this).close();
        DataBaseSaveDataHelper.getInstance(this).close();

        isPaused = true;
        //Log.e("MainActivity", "onPause()");
        //if (Game.gameState != Game.GAME_STATE_INTERSTITIAL) {
        //    SaveGame.saveGame.save();
        //}

        if (myGlSurface != null){
            myGlSurface.onPause();
        }

        if (mAdView != null) {
            mAdView.pause();
        }

        if (Game.gameState == Game.GAME_STATE_PREPARAR){
            Game.initPausedFlag = true;
        }

        mSensorManager.unregisterListener(this);

        Sound.pauseAll();
	    AsyncTasks.cancelAll();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (Game.gameState == Game.GAME_STATE_OPCOES_GAME) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_SOBRE){
                    Game.setGameState(Game.GAME_STATE_OPCOES);
	} else if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_MENU) {
            onPause();
            moveTaskToBack(true);
        } else if (Game.gameState == Game.GAME_STATE_PAUSE){
            Game.increaseAllGameEntitiesAlpha(500);
            MessagesHandler.messageInGame.reduceAlpha(500,0f);
            MenuHandler.menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Game.setGameState(Game.GAME_STATE_JOGAR);
                }
            });
        } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA){
            Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
            Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
        } else if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){
            Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
        } else if (Game.gameState == Game.GAME_STATE_SELECAO_GRUPO) {
            Game.setGameState(Game.GAME_STATE_MENU);
        }else if (Game.gameState == Game.GAME_STATE_PREPARAR){
            Game.initPausedFlag = true;
        }else if (Game.gameState == Game.GAME_STATE_DERROTA){
            if (SaveGame.saveGame.currentLevelNumber < 1000){
                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
            } else {
                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
            }
        } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
            if (SaveGame.saveGame.currentLevelNumber < 1000){
                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
            } else {
                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
            }
	    } else if (Game.gameState != Game.GAME_STATE_INTRO){
            Game.setGameState(Game.GAME_STATE_MENU);
	    }
    }

    @Override
    protected void onResume() {

        //Log.e("MainActivity", "onResume()");

        super.onResume();

        isPaused = false;

        setFullScreen();

        if (SaveGame.saveGame != null && SaveGame.saveGame.googleOption == 1) {
            signInSilently();
        }

        if (myGlSurface != null){
            myGlSurface.onResume();
        }

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        Acelerometer.updateValue(event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    public void hideAdView(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdView.pause();
                mAdView.setVisibility(View.GONE);
            }
        });
    }

    public void showAdView(){
        ConnectionHandler.connect();

        if (Game.notConnectedTextView != null) {
            if (ConnectionHandler.internetState == ConnectionHandler.INTERNET_STATE_NOT_CONNECTED) {
                Game.notConnectedTextView.display();
                Game.topFrame.display();
            } else {
                Game.notConnectedTextView.clearDisplay();
                Game.topFrame.clearDisplay();
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdView.resume();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void initAds(){
        AdRequest adRequestBanner = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
                .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
                .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
                .build();
        mAdView.loadAd(adRequestBanner);

        interstitialWithVideo = new InterstitialAd(MainActivity.this);
        interstitialWithVideo.setAdUnitId("ca-app-pub-2413920269734587/2998542956");

        interstitialNoVideo = new InterstitialAd(MainActivity.this);
        interstitialNoVideo.setAdUnitId("ca-app-pub-2413920269734587/7806070555");

        AdListener adListener = new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Game.returningFromInterstitialFlag = true;
                loadInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                if (ConnectionHandler.internetState != ConnectionHandler.INTERNET_STATE_NOT_CONNECTED){
                    loadInterstitialAd();
                }
            }
            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdOpened() {
            }
        };

        interstitialWithVideo.setAdListener(adListener);
        interstitialNoVideo.setAdListener(adListener);

        loadInterstitialAd();
    }

    public void loadInterstitialAd(){

        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
                .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
                .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
                .build();
        if (ConnectionHandler.isConnectionWifi()) {
            //Log.e(TAG, "Conectado ao Wiji - carregando interstitialWithVideo");
            interstitialActualMode = INTERSTITIAL_MODE_WITH_VIDEO;
            interstitialWithVideo.loadAd(adRequest);

        } else {
            //Log.e(TAG, "Conectado ao Wiji - carregando interstitialNoVideo");
            interstitialActualMode = INTERSTITIAL_MODE_NO_VIDEO;
            interstitialNoVideo.loadAd(adRequest);
        }
    }

    public void showInterstitial() {
        runOnUiThread(new Runnable() {
            public void run() {

                InterstitialAd interstitialAd;

                if (interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO) {
                    //Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO");
                    interstitialAd = interstitialWithVideo;
                } else if (interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO){
                    //Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO");
                    interstitialAd = interstitialNoVideo;
                } else {
                    interstitialAd = interstitialNoVideo;
                }

    /*Verifica se a propaganda existe e foi carregada e direciona ao ponto correto em cada caso*/
                if (interstitialAd != null) {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        //Log.e(getLocalClassName(), "Propaganda não carregada");
                        if (Game.gameState != Game.GAME_STATE_INTRO) {
                            if (SaveGame.saveGame.currentLevelNumber < 1000) {
                                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                            } else {
                                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                            }
                        }
                    }
                } else {
                    if (Game.gameState != Game.GAME_STATE_INTRO) {
                        if (SaveGame.saveGame.currentLevelNumber < 1000) {
                            Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                        } else {
                            Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                        }
                    }
                }
            }
        });
    }
}
