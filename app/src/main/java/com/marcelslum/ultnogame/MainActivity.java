package com.marcelslum.ultnogame;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity {

    // Our OpenGL Surfaceview
    private GLSurfaceView glSurfaceView;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    
        interstitial = new InterstitialAd(MainActivity.this);
        Game.interstitial = interstitial;
        interstitial.setAdUnitId("ca-app-pub-2413920269734587/2998542956");
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
            .build();

        interstitial.loadAd(adRequest);
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
	        }
		
            @Override
            public void onAdClosed() {
                Game.setGameState(Game.GAME_STATE_MENU);
            }
 
            @Override
            public void onAdFailedToLoad(int errorCode) {
		Game.setGameState(Game.GAME_STATE_MENU);
	    }
 
            @Override
            public void onAdLeftApplication() {
	    }
 
            @Override
            public void onAdOpened() {
	    }
        });
        interstitial.loadAd(adRequest);
	    
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Game.mGoogleApiClient = new GoogleApiClient.Builder(this)
        //        .addConnectionCallbacks(this)
        //        .addOnConnectionFailedListener(this)
        //        .addApi(Games.API).addScope(Games.SCOPE_GAMES)
        //        .build();


        // Super
        super.onCreate(savedInstanceState);

        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            // TODO: The system bars are visible. Make any desired
                            // adjustments to your UI, such as showing the action bar or
                            // other navigational controls.

                            if (Game.gameState == Game.GAME_STATE_JOGAR){
                                Game.setGameState(Game.GAME_STATE_PAUSE);
                            }

                        } else {
                            // TODO: The system bars are NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.
                        }
                    }
                });


        // We create our Surfaceview for our OpenGL here.
        glSurfaceView = new GLSurf(this);

        glSurfaceView.setPreserveEGLContextOnPause(true);

        // Set our view.
        setContentView(R.layout.activity_main);

        // Retrieve our Relative layout from our main layout we just set to our view.
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.gamelayout);

        // Attach our surfaceview to our relative layout from our main layout.
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        layout.addView(glSurfaceView, glParams);
    }

    private void setFullScreen()
    {
        int uiOptions = this.getWindow().getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 14) {
            uiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            uiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        this.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Game.mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //if (Game.mGoogleApiClient.isConnected()) {
        //    Game.mGoogleApiClient.disconnect();
        //}
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    public void onBackPressed() {
        if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_MENU) {
            super.onPause();
            glSurfaceView.onPause();
            moveTaskToBack(true);
            //minimizeApp();
        } else {
            Game.setGameState(Game.GAME_STATE_MENU);
        }
    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }



    @Override
    protected void onResume() {
        super.onResume();
        setFullScreen();
        glSurfaceView.onResume();
    }


}
