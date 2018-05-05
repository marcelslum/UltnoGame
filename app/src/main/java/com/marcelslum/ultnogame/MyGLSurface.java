package com.marcelslum.ultnogame;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MyGLSurface extends GLSurfaceView {

    private final MyGLRenderer mRenderer;
    private final static String TAG = "GLSurf";

    public MyGLSurface(Context context) {
        super(context);
        //Log.e(TAG, "createGlSurf");

        Game.myGlSurface = this;

        mRenderer = new MyGLRenderer(context);

        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        getHolder().setFormat(PixelFormat.RGBA_8888);
        setRenderer(mRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        //Log.e("GLSURF", "onPause");
        super.onPause();
        mRenderer.onPause();
    }

    @Override
    public void onResume() {
        //Log.e("GLSURF", "onResume");
        super.onResume();
        mRenderer.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Game.touchEvents == null) Game.touchEvents = new ArrayList<>();

        float screenOffSetX = mRenderer.screenOffSetX;
        float screenOffSetY = mRenderer.screenOffSetY;

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();
        int pointerIndex;
        int pointerId;


        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                pointerIndex = event.getActionIndex();
                pointerId = event.getPointerId(pointerIndex);

                Game.touchEvents.add(new TouchEvent(pointerId, event.getX(pointerIndex)-screenOffSetX,event.getY(pointerIndex)-screenOffSetY));
                break;

            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < Game.touchEvents.size();i++) {
                    if (Game.touchEvents.get(i).type != TouchEvent.TOUCH_TYPE_UP) {
                        pointerIndex = event.findPointerIndex(Game.touchEvents.get(i).id);
                        float x = event.getX(pointerIndex) - screenOffSetX;
                        float y = event.getY(pointerIndex) - screenOffSetY;
                        if (Game.touchEvents.get(i).x != x || Game.touchEvents.get(i).y != y) {
                            Game.touchEvents.get(i).move(event.getX(pointerIndex) - screenOffSetX, event.getY(pointerIndex) - screenOffSetY);
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                pointerIndex = event.getActionIndex();
                for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
                    if (Game.touchEvents.get(i2).id == event.getPointerId(pointerIndex)) {
                        Game.touchEvents.get(i2).setType(TouchEvent.TOUCH_TYPE_UP);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                pointerIndex = event.getActionIndex();
                for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
                    if (Game.touchEvents.get(i2).id == event.getPointerId(pointerIndex)) {
                        Game.touchEvents.get(i2).setType(TouchEvent.TOUCH_TYPE_UP);
                    }
                }
                break;


            case MotionEvent.ACTION_CANCEL:
                Game.touchEvents.clear();
                break;
        }
        return true;
    }

    public void onCloseAd(){
        queueEvent(new Runnable() {
            // This method will be called on the rendering
            // thread:
            public void run() {
                Game.bordaB.setY(Game.resolutionY);

                Game.eraseAllGameEntities();
                Game.eraseAllHudEntities();



                if (Game.gameState == Game.GAME_STATE_MENU_PRINCIPAL){
                    Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
                    Game.prepareAfterInterstitialFlag = false;
                    return;
                }

                if (Game.returningFromTraining) {
                    Game.returningFromTraining = false;
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);

                } else {
                    if (Game.prepareAfterInterstitialFlag) {
                        Game.prepareAfterInterstitialFlag = false;
                        LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                        Game.setGameState(Game.GAME_STATE_PREPARAR);
                    } else {
                        Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                    }
                }

            }});
    }

    public void setScoreMessage(){
        queueEvent(new Runnable() {
            public void run() {
                ScoreHandler.scorePanel.showMessage(Game.messageForScore, 2000);
            }});
    }

    public void setMenuCarregarMessage(){
        queueEvent(new Runnable() {
            public void run() {

                if (MainActivity.saveGameFromCloud == null){
                    Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.erro_ao_carregar), 4000);

                } else {

                    int localStars = SaveGame.getTotalStars(SaveGame.saveGame);

                    int cloudStars = SaveGame.getTotalStars(MainActivity.saveGameFromCloud);

                    int localPoints = SaveGame.getTotalPoints(SaveGame.saveGame);
                    int cloudPoints = SaveGame.getTotalPoints(MainActivity.saveGameFromCloud);


                    MessagesHandler.messageMenuCarregarJogo.clearTexts();

                    MessagesHandler.messageMenuCarregarJogo.addText(
                            Game.getContext().getResources().getString(R.string.messageCarregarJogo1), Color.azulClaro);
                    MessagesHandler.messageMenuCarregarJogo.addText(
                            NumberFormat.getInstance().format(cloudStars) + " " +
                                    Game.getContext().getResources().getString(R.string.messageCarregarJogo2) +
                                    " - " +
                                    NumberFormat.getInstance().format(cloudPoints) + " " +
                                    Game.getContext().getResources().getString(R.string.messageCarregarJogo3), Color.cinza50);


                    MessagesHandler.messageMenuCarregarJogo.addText(".", Color.transparente);


                    MessagesHandler.messageMenuCarregarJogo.addText(
                            Game.getContext().getResources().getString(R.string.messageCarregarJogo4), Color.azulClaro);
                    MessagesHandler.messageMenuCarregarJogo.addText(
                            NumberFormat.getInstance().format(localStars) + " " +
                                    Game.getContext().getResources().getString(R.string.messageCarregarJogo2) +
                                    " - " +
                                    NumberFormat.getInstance().format(localPoints) + " " +
                                    Game.getContext().getResources().getString(R.string.messageCarregarJogo3), Color.cinza50);
                    MessagesHandler.messageMenuCarregarJogo.addText(".", Color.transparente);

                    MessagesHandler.messageMenuCarregarJogo.addText(
                            Game.getContext().getResources().getString(R.string.messageCarregarJogo5),
                            Color.pretoCheio);
                    MessagesHandler.messageMenuCarregarJogo.addText(".", Color.transparente);
                    MessagesHandler.messageMenuCarregarJogo.addText(
                            Game.getContext().getResources().getString(R.string.messageCarregarJogo6),
                            Color.cinza70);

                    Game.setGameState(Game.GAME_STATE_MENU_CARREGAR_JOGO);
                }
            }});
    }

    public void showMessage(final String message){
        queueEvent(new Runnable() {
            public void run() {
                Game.messages.showMessage(message);
            }});

    }

    public void showBottomMessage(final String message, final int duration){
        queueEvent(new Runnable() {
            public void run() {
                MessagesHandler.setBottomMessage(message, duration);
            }});

    }

    
    public void explodeBlueBall(){
        queueEvent(new Runnable() {
            public void run() {
                if (Game.ballGoalsPanel != null) {

                    ParticleGenerator pg = ParticleGenerator.getNew(
                        Game.blueBallExplodeX,
                        Game.blueBallExplodeY);

                    pg.setTexturesData(
                        TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_BLUE_1_ID),
                        TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_BLUE_2_ID),
                        TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_BLUE_3_ID));


                    //Log.e(TAG, "explodeBlueBall() queueEvent(new Runnable() ");
                    ScoreHandler.scorePanel.showMessage("+ 50%", 500);

                    Game.ballGoalsPanel.particleGenerators.add(pg);
                    pg.activate();



                }

            }});
    }


}
