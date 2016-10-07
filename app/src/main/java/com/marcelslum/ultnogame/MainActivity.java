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


public class MainActivity extends Activity implements OnConnectionFailedListener{

	// Our OpenGL Surfaceview
	private GLSurfaceView glSurfaceView;
	private InterstitialAd interstitial;

	private GoogleApiClient mGoogleApiClient;
	private boolean mResolvingError = false;
	// Request code to use when launching the resolution activity
	private static final int REQUEST_RESOLVE_ERROR = 1001;
	// Unique tag for the error dialog fragment
	private static final String DIALOG_ERROR = "dialog_error";
	private static final String STATE_RESOLVING_ERROR = "resolving_error";

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
			} catch (SendIntentException e) {
				// There was an error with the resolution intent. Try again.
				mGoogleApiClient.connect();
			}
		} else {
			// Show dialog using GoogleApiAvailability.getErrorDialog()
			showErrorDialog(result.getErrorCode());
			mResolvingError = true;
			//TODO informar erro ao usuario

		}
	}
	
	@Override
	public void onConnected(Bundle connectionHint){}
	
	@Override
	public void onConnectionSspended(int cause){}
		
	

	// The rest of this code is all about building the error dialog

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
		    ((MyActivity) getActivity()).onDialogDismissed();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_RESOLVE_ERROR) {
		mResolvingError = false;
		if (resultCode == RESULT_OK) {
		    // Make sure the app is not already connected or attempting to connect
		    if (!mGoogleApiClient.isConnecting() &&
			    !mGoogleApiClient.isConnected()) {
			mGoogleApiClient.connect();
		    }
		}
	    }
	}
	
	private void initAds(){    
		interstitial = new InterstitialAd(MainActivity.this);
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
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Game.mainActivity = this;

		initAds();
		
		mResolvingError = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

		
		mGoogleApiClient = new GoogleApiClient.Builder(this)
			.enableAutoManage(this /* FragmentActivity */,
				      this /* OnConnectionFailedListener */)
			.addApi(Games.API)
			.addScope(Games.SCOPE)
			.addConnectionCallbacks(this)
			.addOnConnectionFailerListener(this)
			.build();


		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	    
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
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Game.mGoogleApiClient.isConnected()) {
            Game.mGoogleApiClient.disconnect();
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
