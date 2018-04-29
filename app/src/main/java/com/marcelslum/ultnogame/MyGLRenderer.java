package com.marcelslum.ultnogame;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    public static final String TAG = "MyGLRenderer";

    // Our matrices
    private final float[] matrixProjection = new float[16];
    private final float[] matrixView = new float[16];
    private final float[] matrixProjectionAndView = new float[16];

    // Our screenresolution
    float	mScreenWidth;
    float	mScreenHeight;

    float	effectiveScreenWidth;
    float	effectiveScreenHeight;

    ArrayList<Long> frameDurations;
    long longestFrame;

    float testeValue = 10f;

    public float fps = 60f;
    public float frameDuration = 1000f/fps;

    // Misc
    Context mContext;
    long mLastTime;
    long mLastNanoTime;

    public float screenOffSetX;
    public float screenOffSetY;


    public MyGLRenderer(Context c) {
        //Log.e(TAG, "create");
        mContext = c;
        mLastTime = System.currentTimeMillis() + 100;
    }

    public void onPause() {
        //Log.e(TAG, "onPause");
        if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_PREPARAR || Game.gameState == Game.GAME_STATE_TUTORIAL) {
            Game.setGameState(Game.GAME_STATE_MENU);
        }
    }

    public void onResume()
    {
        //Log.e(TAG, "onResume");
        mLastTime = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //Log.e(TAG, "onSurfaceCreated");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        //Log.e(TAG, "onSurfaceChanged");

        if (!Game.forInitGame && Game.bordaE != null){
            //Log.e("MyGLRenderer", "onSurfaceChanged - apenas resumindo jogo");
            return;
        }

        //Log.e("MyGLRenderer", "(re)criando o jogo do zero");

        Game.forInitGame = false;
        Splash.loaderConclude = false;

        GLES20.glClearColor(0.902f, 0.89f, 0.922f, 1.0f);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        
        Game.dpiClassification = metrics.densityDpi;

        //Log.e(TAG, "dpiClassification "+Game.dpiClassification);

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

        //Log.e("screenOffSetX ", " "+screenOffSetX);
        //Log.e("screenOffSetY ", " "+screenOffSetY);

        //Log.e("effectiveScreenHeight ", " "+effectiveScreenHeight);
        //Log.e("effectiveScreenWidth ", " "+effectiveScreenWidth);

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

    long lastInternetCheck = -1;

    public static boolean tick = false;

    /*
    tick = true
    BallDataPanel.checkTransformations();
    checkBufferChange.checkBufferChange();
    BallParticleGenerator.updateDrawInfo();
    TargetGroup.checkBufferChange()

    tick = false
    BallDataPanel.checkDrawInfo();
    ParticleGenerator.updateDrawInfo();
    BrickBackgroud.changeDrawInfo()

    */

    @Override
    public void onDrawFrame(GL10 unused) {


        if (tick){
            tick = false;
        } else {
            tick = true;
        }

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0.895f, 0.89f, 0.896f, 1.0f);

        //Log.e(TAG, "Game.returningFromInterstitialFlag "+ Game.returningFromInterstitialFlag);
        if (Game.returningFromInterstitialFlag) {
            Game.returningFromInterstitialFlag = false;
            Game.myGlSurface.onCloseAd();
        }

        if (Game.settingMessageForScore) {
            Game.settingMessageForScore = false;
            Game.myGlSurface.setScoreMessage();
        }

        if (Game.messagesToDisplay.size() > 0) {
            for (int i = 0; i < Game.messagesToDisplay.size(); i++) {
                Game.myGlSurface.showMessage(Game.messagesToDisplay.get(i));
            }
            Game.messagesToDisplay.clear();
        }
        
        if (Game.forBlueBallExplode){
            Game.forBlueBallExplode = false;
            Game.myGlSurface.explodeBlueBall();
        }


        // Get the current time
        Game.currentFrameMilliPrecision = System.currentTimeMillis();
        Game.currentFrameNanoPrecision = System.nanoTime();

        // We should make sure we are valid and sane
        if (mLastTime > Game.currentFrameMilliPrecision) return;

        // Get the amount of time the last frame took.
        Game.elapsedTimeSinceLastFrame = Game.currentFrameMilliPrecision - mLastTime;
        Game.elapsedNanoTimeSinceLastFrame = Game.currentFrameNanoPrecision - mLastNanoTime;

        if (Game.gameState == Game.GAME_STATE_INTRO){
            Game.verifyTouchBlock();
            Game.verifyListeners();
            Splash.render(matrixView, matrixProjection);
            Splash.verifySplashState();
        } else {

            if (lastInternetCheck < 0){
                lastInternetCheck = Utils.getTimeMilliPrecision();
            }


            // verificações periódicas
            if (!Game.paraGravacaoVideo) {
                if (Game.gameState != Game.GAME_STATE_JOGAR && Game.gameState != Game.GAME_STATE_VITORIA && Game.gameState != Game.GAME_STATE_VITORIA_COMPLEMENTACAO) {
                    if (Utils.getTimeMilliPrecision() - lastInternetCheck > 5000) {
                        //Log.e(TAG, "Verificando conexão");
                        ConnectionHandler.checkInternetConnection();
                        lastInternetCheck = Game.currentFrameMilliPrecision;
                    }
                } else {


                    if (Game.gameState == Game.GAME_STATE_JOGAR) {
                        if (Utils.getTimeMilliPrecision() - lastInternetCheck > 1000) {
                            if (TimeHandler.timeOfLevelPlay > 3000) {
                                Sound.checkLoopPlaying();
                            }
                            lastInternetCheck = Utils.getTimeMilliPrecision();
                        }
                    } else {
                        if (Utils.getTimeMilliPrecision() - lastInternetCheck > 300) {
                            Sound.checkLoopPlaying();
                            lastInternetCheck = Utils.getTimeMilliPrecision();
                            Sound.setMusicVolume(Sound.musicVolume - 0.02f);
                        }
                    }
                }
            }




            Game.verifyTouchBlock();
            Game.verifyListeners();

            if (Game.logFrame) {

                if (frameDurations == null) {
                    frameDurations = new ArrayList<>();
                }

                frameDurations.add(Game.elapsedTimeSinceLastFrame);


                //Log.e(TAG, "frame " + Game.elapsedTimeSinceLastFrame);


                if (Game.elapsedTimeSinceLastFrame > longestFrame) {
                    longestFrame = Game.elapsedTimeSinceLastFrame;
                }



                // LOG FRAMES

                if (frameDurations.size() == 60) {
                    float soma = 0;
                    for (int i = 0; i < frameDurations.size(); i++) {
                        soma += frameDurations.get(i);
                    }
                    Log.e("MyGLRenderer"," frame duration: "+(soma / frameDurations.size()));
                    Log.e("MyGLRenderer"," longestFrame: "+longestFrame);
                    frameDurations.clear();
                    longestFrame = 0;
                }

            }

            if (Game.elapsedTimeSinceLastFrame > (frameDuration*3) && Game.gameState == Game.GAME_STATE_JOGAR){
                Log.e("MyGLRenderer", "frame muito longo, reduzindo de " + Game.elapsedTimeSinceLastFrame + " para " + (frameDuration*3));
                Game.elapsedTimeSinceLastFrame = (long)frameDuration*3;
            }

            //Game.simulate(Game.elapsedTimeSinceLastFrame, frameDuration);
            Game.simulate(Game.elapsedNanoTimeSinceLastFrame, frameDuration);
            Game.render(matrixView, matrixProjection);
        }
        // Save the current time to see how long it took :).
        mLastTime = Game.currentFrameMilliPrecision;
        mLastNanoTime = Game.currentFrameNanoPrecision;
    }
}
