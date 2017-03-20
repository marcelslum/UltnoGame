package com.marcelslum.ultnogame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import android.os.Vibrator;

public class MainActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener,
        SensorEventListener{


    public static SensorManager mSensorManager;
    public static Sensor mAccelerometer;

    private GLSurfaceView glSurfaceView;
    private InterstitialAd interstitialWithVideo;
    private InterstitialAd interstitialNoVideo;
    private int interstitialActualMode = 0;
    public final static int INTERSTITIAL_MODE_WITH_VIDEO = 1;
    public final static int INTERSTITIAL_MODE_NO_VIDEO = 2;


    public GoogleApiClient mGoogleApiClient;
    AdView mAdView;
    private final static String TAG = "MainActivity";
    public boolean isPaused = false;

    // Request code to use when launching the resolution activity
    private static final int REQUEST_RESOLVE_ERROR = 1001;
    // Unique tag for the error dialog fragment
    private static final String DIALOG_ERROR = "dialog_error";
    // Bool to track whether the app is already resolving an error
    private boolean mResolvingError = false;

    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("mainActivity", "create");
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	    
        Log.e(TAG, "maxMemory "+maxMemory);
	    
	    Game.vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

        Game.mainActivity = this;
        Game.forInitGame = true;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .addApi(Drive.API).addScope(Drive.SCOPE_APPFOLDER)
                .build();

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

        // Create and load the AdView.
        mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-2413920269734587/4375714557");
        mAdView.setAdSize(AdSize.SMART_BANNER);

        glSurfaceView = new GLSurf(this);
        glSurfaceView.setPreserveEGLContextOnPause(true);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.gamelayout);
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(glSurfaceView, glParams);


        // Add adView to the bottom of the screen.
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.addView(mAdView, adParams);

        initAds();
    }
		
		
	private boolean detectOpenGLES30()
	{
	      ActivityManager am =
		 ( ActivityManager ) getSystemService ( Context.ACTIVITY_SERVICE );
	      ConfigurationInfo info = am.getDeviceConfigurationInfo();
	      return ( info.reqGlEsVersion >= 0x30000 );
	}

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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdView.resume();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
    }


	private void initAds(){
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
	
		final AdRequest adRequest = new AdRequest.Builder()
		    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		    .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
            .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
            .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
            .build();

        interstitialWithVideo.setAdListener(new AdListener() {
		    @Override
		    public void onAdLoaded() {
			}

		    @Override
		    public void onAdClosed() {

                //ConnectionHandler.menuConnectionAttempts = 0;

                Game.bordaB.y = Game.resolutionY;

                Game.stopAndReleaseMusic();
                Game.eraseAllGameEntities();
                Game.eraseAllHudEntities();

                //ConnectionHandler.verify();

                if (Game.interstitialNextPreparar){
                    Game.interstitialNextPreparar = false;
                    LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                    Game.setGameState(Game.GAME_STATE_PREPARAR);
                } else if (SaveGame.saveGame.currentLevelNumber < 1000){
                    Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                } else {
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                }

                loadInterstitialAd();
		    }

		    @Override
		    public void onAdFailedToLoad(int errorCode) {
                Log.e("findStateMenu", "2" + "loader conclude "+Splash.loaderConclude);
                //if (Splash.loaderConclude && Game.gameState != Game.GAME_STATE_INTRO) {
                //    Game.setGameState(Game.GAME_STATE_MENU);
                //    return;
                //}
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
		});

        interstitialNoVideo.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {

                //ConnectionHandler.menuConnectionAttempts = 0;

                Game.bordaB.y = Game.resolutionY;

                Game.stopAndReleaseMusic();
                Game.eraseAllGameEntities();
                Game.eraseAllHudEntities();

                //ConnectionHandler.verify();

                if (SaveGame.saveGame.currentLevelNumber < 1000){
                    Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                } else {
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                }
                loadInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("findStateMenu", "2" + "loader conclude "+Splash.loaderConclude);
                //if (Splash.loaderConclude && Game.gameState != Game.GAME_STATE_INTRO) {
                //    Game.setGameState(Game.GAME_STATE_MENU);
                //    return;
                //}
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
        });
        loadInterstitialAd();
	}

	public void loadInterstitialAd(){

        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
                .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
                .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
                .build();
        if (ConnectionHandler.isConnectedWifi()) {
            Log.e(TAG, "Conectado ao Wiji - carregando interstitialWithVideo");
            interstitialWithVideo.loadAd(adRequest);
            interstitialActualMode = INTERSTITIAL_MODE_WITH_VIDEO;
        } else {
            Log.e(TAG, "Conectado ao Wiji - carregando interstitialNoVideo");
            interstitialActualMode = INTERSTITIAL_MODE_NO_VIDEO;
            interstitialNoVideo.loadAd(adRequest);
        }
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
            if (Build.VERSION.SDK_INT >= 14) {
                uiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
            }
            if (Build.VERSION.SDK_INT >= 18) {
                uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            fa.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
	}

    @Override
    protected void onPause() {
        isPaused = true;
        Log.e("MainActivity", "onPause()");
        SaveGame.save();
        glSurfaceView.onPause();
        if (mAdView != null) {
            mAdView.pause();
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
        } else if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_MENU) {
            onPause();
            moveTaskToBack(true);
         
        } else if (Game.gameState == Game.GAME_STATE_PAUSE){
            Game.setGameState(Game.GAME_STATE_INTERSTITIAL);//TODO continuar a jogar
        } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA){
            Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
            Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
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

    public void showInterstitial() {
        runOnUiThread(new Runnable() {
            public void run() {

            InterstitialAd interstitialAd;


            if (interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO) {
                Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO");
                interstitialAd = interstitialWithVideo;
            } else if (interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO){
                Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO");
                interstitialAd = interstitialNoVideo;
            } else {
                interstitialAd = interstitialNoVideo;
            }
            if (interstitialAd != null) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                } else {
                    Log.e("MainActivity", "Interstitial ad is not loaded yet");
                    if (Game.gameState != Game.GAME_STATE_INTRO) {
                        Log.e("findStateMenu", "4");
                        if (SaveGame.saveGame.currentLevelNumber < 1000) {
                            Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                        } else {
                            Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                        }
                    }
                }
            }
            }
        });
    }

    @Override
    protected void onResume() {
        Log.e("MainActivity", "onResume()");
        super.onResume();
        isPaused = false;
        setFullScreen();
        glSurfaceView.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

        if (mAdView != null) {
            mAdView.resume();
        }
        if (!mGoogleApiClient.isConnecting() || !mGoogleApiClient.isConnected()){
            mGoogleApiClient.connect();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    ConnectionHandler.connect();
                }
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            showErrorDialog(result.getErrorCode());
            mResolvingError = true;

            if (Game.gameState != Game.GAME_STATE_INTRO){
                Game.setGameState(Game.GAME_STATE_INTRO);
            }
        }
    }

    /* Creates a dialog for an error message */
    private void showErrorDialog(int errorCode) {
        // Create a fragment for the error dialog
        ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
        // Pass the error that should be displayed
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, errorCode);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "errordialog");
    }

    /* Called from ErrorDialogFragment when the dialog is dismissed. */
    public void onDialogDismissed() {
        mResolvingError = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Acelerometer.updateValue(event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /* A fragment to display an error dialog */
    public static class ErrorDialogFragment extends DialogFragment {
        public ErrorDialogFragment() { }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Get the error code and retrieve the appropriate dialog
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);
            return GoogleApiAvailability.getInstance().getErrorDialog(
                    this.getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        @Override
        public void onDismiss(DialogInterface dialog) {
            ((MainActivity) getActivity()).onDialogDismissed();
        }
    }
}
