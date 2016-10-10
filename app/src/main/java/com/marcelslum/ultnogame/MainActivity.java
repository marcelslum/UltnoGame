package com.marcelslum.ultnogame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class MainActivity extends FragmentActivity implements
        OnConnectionFailedListener{


    private GLSurfaceView glSurfaceView;
    private InterstitialAd interstitial;
    public GoogleApiClient mGoogleApiClient;
    AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("mainActivity", "create");
        super.onCreate(savedInstanceState);
        Game.mainActivity = this;
        Game.forInitGame = true;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();

        GooglePlayGames.getInstance().init(mGoogleApiClient, this);

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

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        ConnectionHandler.connect();
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
                .build();
        mAdView.loadAd(adRequestBanner);


		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-2413920269734587/2998542956");
	
		final AdRequest adRequest = new AdRequest.Builder()
		    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
		    .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
            .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
		    .build();
		interstitial.loadAd(adRequest);
		interstitial.setAdListener(new AdListener() {
		    @Override
		    public void onAdLoaded() {
			}

		    @Override
		    public void onAdClosed() {
                Game.setGameState(Game.GAME_STATE_MENU);
                interstitial.loadAd(adRequest);
		    }

		    @Override
		    public void onAdFailedToLoad(int errorCode) {
			    Game.setGameState(Game.GAME_STATE_MENU);
                interstitial.loadAd(adRequest);
		    }

		    @Override
		    public void onAdLeftApplication() {
		    }

		    @Override
		    public void onAdOpened() {
		    }
		});
		interstitial.loadAd(adRequest);
		
	}

	private void setFullScreen() {
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
    protected void onPause() {
        Log.e("MainActivity", "onPause()");
        super.onPause();
        glSurfaceView.onPause();
        if (mAdView != null) {
            mAdView.pause();
        }
    }

    @Override
    public void onBackPressed() {
        if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_MENU) {
            super.onPause();
            glSurfaceView.onPause();
            moveTaskToBack(true);
        } else {
            Game.setGameState(Game.GAME_STATE_MENU);
        }
    }

    public void showInterstitial() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (interstitial.isLoaded()) {
                    interstitial.show();
                } else {
                    Log.e("MainActivity", "Interstitial ad is not loaded yet");
                    Game.setGameState(Game.GAME_STATE_MENU);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        Log.e("MainActivity", "onResume()");
        super.onResume();
        setFullScreen();
        glSurfaceView.onResume();
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

    public void unlockAchievement() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Achievements.unlock(mGoogleApiClient, "CgkIjNyO58cTEAIQAQ");
        } else {
            // Alternative implementation (or warn user that they must sign in to use this feature)
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                           int resultCode,
                           Intent data){
        GooglePlayGames.getInstance().onRequestResult(requestCode, resultCode);

    }
}