package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer {

    public Game gi;
    int[] texturenames;

    // Our matrices
    private final float[] matrixProjection = new float[16];
    private final float[] matrixView = new float[16];
    private final float[] matrixProjectionAndView = new float[16];

    // Our screenresolution
    float	mScreenWidth;
    float	mScreenHeight;

    float	effectiveScreenWidth;
    float	effectiveScreenHeight;

    float 	ssu = 1.0f;
    float 	ssx = 1.0f;
    float 	ssy = 1.0f;
    float 	swp = 320.0f;
    float 	shp = 480.0f;

    float testeValue = 10f;

    public float fps = 60f;
    public float frameDuration = 1000f/fps;

    // Misc
    Context mContext;
    long mLastTime;

    public float screenOffSetX;
    public float screenOffSetY;


    public GLRenderer(Context c)
    {
        mContext = c;
        mLastTime = System.currentTimeMillis() + 100;
        this.gi = Game.getInstance();
        this.gi.setContext(mContext);
    }

    public void onPause()
    {
		/* Do stuff to pause the renderer */
    }

    public void onResume()
    {
		/* Do stuff to resume the renderer */
        mLastTime = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        GLES20.glClearColor(0.902f, 0.89f, 0.922f, 1.0f);

        // Setup our scaling system
        //SetupScaling();

        GLES20.glEnable(GLES20.GL_BLEND);
        //GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);


        SetupTextures();


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // We need to know the current width and height.
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, (int)mScreenWidth, (int)mScreenHeight);

        effectiveScreenWidth = (int)mScreenWidth;
        effectiveScreenHeight = (int)mScreenHeight;

        this.screenOffSetX = 0;
        this.screenOffSetY = 0;

        if (mScreenWidth/mScreenHeight > 1.5){
            effectiveScreenWidth = (effectiveScreenHeight/2)*3;
            screenOffSetX = (mScreenWidth - effectiveScreenWidth)/2;
        } else if (mScreenWidth/mScreenHeight < 1.5){
            effectiveScreenHeight = (effectiveScreenWidth/3)*2;
            screenOffSetY = (mScreenHeight - effectiveScreenHeight)/2;
        }

        Log.e("screenOffSetX ", " "+screenOffSetX);
        Log.e("screenOffSetY ", " "+screenOffSetY);

        Log.e("effectiveScreenHeight ", " "+effectiveScreenHeight);
        Log.e("effectiveScreenWidth ", " "+effectiveScreenWidth);

        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            matrixProjection[i] = 0.0f;
            matrixView[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM(matrixProjection, 0, 0f, mScreenWidth, mScreenHeight, 0.0f, 0.1f, 50f);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(matrixView, 0, -screenOffSetX, 0f, 1f, -screenOffSetX, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        //Matrix.multiplyMM(matrixProjectionAndView, 0, matrixProjection, 0, matrixView, 0);

        // Setup our scaling system
        //SetupScaling();

        //this.gi.addText(new Text("titulo", this.gi, 0f, 0f, 300f, "ULTNO", this.gi.font));

        this.gi.gameAreaResolutionX = this.effectiveScreenWidth;
        this.gi.gameAreaResolutionY = this.effectiveScreenHeight * 0.85f;

        this.gi.resolutionX = this.effectiveScreenWidth;
        this.gi.resolutionY = this.effectiveScreenHeight;

        this.gi.init();

        this.gi.setGameState(Game.GAME_STATE_MENU);
    }



    @Override
    public void onDrawFrame(GL10 unused) {

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.902f, 0.89f, 0.922f, 1.0f);

        // Get the current time
        long now = System.currentTimeMillis();

        // We should make sure we are valid and sane
        if (mLastTime > now) return;

        // Get the amount of time the last frame took.
        long elapsed = now - mLastTime;

        this.gi.verifyTouchBlock();

        if (!this.gi.isBlocked) {
            for (int i = 0; i < this.gi.interactionListeners.size(); i++) {
                this.gi.interactionListeners.get(i).verify();
            }
        }

        this.gi.simulate(elapsed, frameDuration);

        this.gi.render(matrixView, matrixProjection);

        // Save the current time to see how long it took :).
        mLastTime = now;
    }

    public void SetupScaling()
    {
        // The screen resolutions
        swp = (int) (mContext.getResources().getDisplayMetrics().widthPixels);
        Log.e("swp", " "+swp);

        shp = (int) (mContext.getResources().getDisplayMetrics().heightPixels);
        Log.e("shp", " "+shp);

        // Orientation is assumed portrait
        ssx = swp / 480.0f;
        ssy = shp / 320.0f;

        // Get our uniform scaler
        if(ssx > ssy)
            ssu = ssy;
        else
            ssu = ssx;
        Log.e("ssu", " "+ssu);
    }


    public void SetupTextures()
    {


        texturenames = new int[2];
        GLES20.glGenTextures(2, texturenames, 0);
        
        /*
        Utils.setBitmap("drawable/balls", Game.bmpBalls, mContext); // balls
        Utils.setBitmap("drawable/jetset", Game.bmpFont, mContext); // fonte
        Utils.setBitmap("drawable/targets", Game.bmpTargets, mContext); // targets
        Utils.setBitmap("drawable/bars", Game.bmpBars, mContext); // bars
        Utils.setBitmap("drawable/buttons", Game.bmpButtons, mContext); // botões
        Utils.setBitmap("drawable/background", Game.bmpBackground, mContext); // background
        Utils.setBitmap("drawable/numbers", Game.bmpNumbers, mContext); // numeros
        Utils.setBitmap("drawable/tittle", Game.bmpTittle, mContext); // titulo e caixa de testo
        Utils.setBitmap("drawable/arrows", Game.bmpArrows, mContext); // flechas
        
        // texture 1024
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, arrayOfNames[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
	GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
	GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, Game.bmpTittle, 0);

	// texture 512
        GLES20.glActiveTexture(GLES20.GL_TEXTURE01);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, arrayOfNames[1]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
	GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
	GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, Game.bmpFont, 0);

        */
        Utils.setTexture("drawable/botoesebolas", texturenames, Game.TEXTURE_BUTTONS_AND_BALLS, mContext); // balls
        Utils.setTexture("drawable/jetset", texturenames, Game.TEXTURE_FONT, mContext); // fonte
        Utils.setTexture("drawable/targets", texturenames, Game.TEXTURE_TARGETS, mContext); // targets
        Utils.setTexture("drawable/bars", texturenames, Game.TEXTURE_BARS, mContext); // bars
        Utils.setTexture("drawable/background", texturenames, Game.TEXTURE_BACKGROUND, mContext); // background
        Utils.setTexture("drawable/numbers", texturenames, Game.TEXTURE_NUMBERS, mContext); // numeros
        Utils.setTexture("drawable/tittle", texturenames, Game.TEXTURE_TITTLE, mContext); // titulo e caixa de testo
        
    }
}
