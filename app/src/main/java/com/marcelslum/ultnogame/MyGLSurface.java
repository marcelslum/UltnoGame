package com.marcelslum.ultnogame;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.Log;
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



                if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MENU_INICIAL){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
                    Game.prepareAfterInterstitialFlag = false;
                    return;
                }

                if (Game.returningFromTraining) {
                    Game.returningFromTraining = false;
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_JOGAR);

                } else {
                    if (Game.prepareAfterInterstitialFlag) {
                        Game.prepareAfterInterstitialFlag = false;
                        LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PREPARAR);
                    } else {
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_LEVEL);
                    }
                }

            }});
    }
    
    public void forUpdateNamePlayer(){
        queueEvent(new Runnable() {
            // This method will be called on the rendering
            // thread:
            public void run() {

                Log.e(TAG, "forUpdateNamePlayer");

                MyVIewModel model = Game.mainActivity.getModel();

                if (model != null) {

                    Log.e(TAG, "forUpdateNamePlayer3");

                    MyVIewModel.PlayerData playerData = model.playerData.getValue();
                    GoogleAPI.configureGoogleInfo(playerData);

                    if (MenuHandler.menuOptions != null){
                        MenuHandler.menuOptions.getMenuOptionByName("google").setText(getResources().getString(R.string.logarGoogle));
                    }

                }
            }});
    }

    public void onBackPressed(){

        queueEvent(new Runnable() {
            // This method will be called on the rendering
            // thread:
            public void run() {


                if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PAUSE_OPCOES) {
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PAUSE);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_SOBRE){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_OPCOES);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PAUSE);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MENU_INICIAL) {
                    Game.mainActivity.onPause();
                    Game.mainActivity.moveTaskToBack(true);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PAUSE){
                    Game.increaseAllGameEntitiesAlpha(500);
                    MessagesHandler.messageInGame.reduceAlpha(500,0f);
                    MenuHandler.menuPause.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PRE_JOGAR);
                        }
                    });
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PAUSE_OBJETIVO){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PAUSE);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_VITORIA_1){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_VITORIA_2);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_VITORIA_2){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTERSTITIAL);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_SELECAO_LEVEL){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_GRUPO);
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_SELECAO_GRUPO) {
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
                }else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PREPARAR){
                    Game.initPausedFlag = true;
                }else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_DERROTA){
                    if (SaveGame.saveGame.currentLevelNumber < 1000){
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_LEVEL);
                    } else {
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_GRUPO);
                    }
                } else if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MOSTRAR_OBJETIVOS){
                    if (SaveGame.saveGame.currentLevelNumber < 1000){
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_LEVEL);
                    } else {
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_GRUPO);
                    }
                } else if (GameStateHandler.gameState != GameStateHandler.GAME_STATE_INTRO){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
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
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
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

                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_CARREGAR_JOGO_SALVO_NUVEM);
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
