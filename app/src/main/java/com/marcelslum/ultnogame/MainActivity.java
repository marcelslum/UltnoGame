package com.marcelslum.ultnogame;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {


	private GLSurfaceView glSurfaceView;

    private InterstitialAd interstitial;

	public GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;
    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;

    private static String STATE_RESOLVING_ERROR = "re";

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingConnectionFailure);
	}

    @Override
    public void onConnected(Bundle connectionHint) {
        // The player is signed in. Hide the sign-in button and allow the
        // player to proceed.
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInflow) {
            mAutoStartSignInflow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."

            //if (!BaseGameUtils.resolveConnectionFailure(this,
            //        mGoogleApiClient, connectionResult,
            //        RC_SIGN_IN, getResources().getString(R.string.signin_other_error))) {
            //    mResolvingConnectionFailure = false;
            //}
        }

        // Put code here to display the sign-in button
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                mGoogleApiClient.connect();
            } else {
                // Bring up an error dialog to alert the user that sign-in
                // failed. The R.string.signin_failure should reference an error
                // string in your strings.xml file that tells the user they
                // could not be signed in, such as "Unable to sign in."


                //BaseGameUtils.showActivityResultError(this,
                //        requestCode, resultCode, R.string.signin_failure);
            }
        }
    }
	
	private void initAds(){    
		interstitial = new InterstitialAd(MainActivity.this);
		interstitial.setAdUnitId("ca-app-pub-2413920269734587/2998542956");
	
		AdRequest adRequest = new AdRequest.Builder()
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
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Game.mainActivity = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        initAds();
        mResolvingConnectionFailure = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //.addConnectionCallbacks(this)
                //.addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();
        /*
        */

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

                            if (Game.gameState == Game.GAME_STATE_JOGAR) {
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
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null) {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }
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
                    //Log.d(TAG, "Interstitial ad is not loaded yet");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFullScreen();
        glSurfaceView.onResume();
    }
}