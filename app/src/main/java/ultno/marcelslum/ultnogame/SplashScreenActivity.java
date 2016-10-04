package ultno.marcelslum.ultnogame;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

	private static final int SPLASH_SHOW_TIME = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
       	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash_screen);
		new BackgroundSplashTask().execute();

	}

	/**
	 * Async Task: can be used to load DB, images during which the splash screen
	 * is shown to user
	 */
	private class BackgroundSplashTask extends AsyncTask {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// We create our Surfaceview for our OpenGL here.
			Game.glSurfaceView = new GLSurf(this);
			Game.glSurfaceView.setPreserveEGLContextOnPause(true);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent i = new Intent(SplashScreenActivity.this,
					AppMainActivity.class);
			// any info loaded can during splash_show
			// can be passed to main activity using
			// below
			i.putExtra("loaded_info", " ");
			startActivity(i);
			finish();
		}

	}
}
