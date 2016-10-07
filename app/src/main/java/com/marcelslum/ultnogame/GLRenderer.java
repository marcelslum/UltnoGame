package com.marcelslum.ultnogame;

import android.content.Context;
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

    // Our matrices
    private final float[] matrixProjection = new float[16];
    private final float[] matrixView = new float[16];
    private final float[] matrixProjectionAndView = new float[16];

    // Our screenresolution
    float	mScreenWidth;
    float	mScreenHeight;

    float	effectiveScreenWidth;
    float	effectiveScreenHeight;

    float testeValue = 10f;

    public float fps = 60f;
    public float frameDuration = 1000f/fps;

    // Misc
    Context mContext;
    long mLastTime;

    public float screenOffSetX;
    public float screenOffSetY;


    public GLRenderer(Context c) {
        mContext = c;
        mLastTime = System.currentTimeMillis() + 100;
        Game.context = mContext;
    }

    public void onPause() {
        if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_PREPARAR || Game.gameState == Game.GAME_STATE_TUTORIAL) {
            Game.setGameState(Game.GAME_STATE_MENU);
        }
    }

    public void onResume()
    {
        mLastTime = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (Game.texts != null ){
            return;
        }

        GLES20.glClearColor(0.902f, 0.89f, 0.922f, 1.0f);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        //display.getMetrics(metrics);

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

        Game.screenOffSetX = screenOffSetX;
        Game.screenOffSetY = screenOffSetY;

        Game.effectiveScreenHeight = effectiveScreenHeight;
        Game.effectiveScreenWidth = effectiveScreenWidth;

        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            matrixProjection[i] = 0.0f;
            matrixView[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM(matrixProjection, 0, 0f, mScreenWidth, mScreenHeight, 0.0f, 0f, 100f);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(matrixView, 0, -screenOffSetX, 0f, 100f, -screenOffSetX, 0f, 0f, 0f, 1.0f, 0.0f);

        Game.gameAreaResolutionX = this.effectiveScreenWidth;
        Game.gameAreaResolutionY = this.effectiveScreenHeight * 0.85f;
        Game.resolutionX = this.effectiveScreenWidth;
        Game.resolutionY = this.effectiveScreenHeight;
        Game.screenOffSetX = screenOffSetX;
        Game.screenOffSetY = screenOffSetY;

        Game.init();
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

        if (Game.gameState == Game.GAME_STATE_INTRO){
            if (Game.tittle != null){
                Game.tittle.prepareRender(matrixView, matrixProjection);
                Game.messageLoading.prepareRender(matrixView, matrixProjection);
            }

            if ((Utils.getTime() - Game.timeIntro)>Game.INTRO_DURATION && Game.loaderConclude){
                Game.setGameState(Game.GAME_STATE_MENU);
            }
        } else {
            Game.verifyTouchBlock();
            Game.verifyListeners();
            Game.simulate(elapsed, frameDuration);
            Game.render(matrixView, matrixProjection);
        }
        // Save the current time to see how long it took :).
        mLastTime = now;
    }

}
