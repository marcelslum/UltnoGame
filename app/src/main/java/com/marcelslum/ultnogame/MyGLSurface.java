package com.marcelslum.ultnogame;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import static com.marcelslum.ultnogame.GoogleAPI.playerIconImage;

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
    
    public void forUpdatePlayerData(){
        queueEvent(new Runnable() {
            // This method will be called on the rendering
            // thread:
            public void run() {

                Log.e(TAG, "forUpdatePlayerData");

                MyVIewModel model = Game.mainActivity.getModel();

                if (model != null) {

                    MyVIewModel.PlayerData playerData = model.playerData.getValue();

                    if (playerData != null){

                        if (playerIconImage == null) {
                            Log.e(TAG, "criando novo playerIconImage1");
                            playerIconImage = new ImageBitmap("playerIconImage1", Game.resolutionX * 0.862f, Game.resolutionY * 0.75f, Game.resolutionX * 0.12f, Game.resolutionX * 0.12f,
                                    Utils.drawableToBitmap(playerData.getIcon()));

                            Utils.createAnimation4v(playerIconImage, "animScaleX", "animScaleX", 2000, 0f, 1f, 0.8f, 1f, 0.95f, 0.95f, 1f, 1f, true, true).start();
                            Utils.createAnimation4v(playerIconImage, "animScaleY", "animScaleY", 2000, 0f, 1f, 0.8f, 1f, 0.95f, 0.95f, 1f, 1f, true, true).start();


                        } else {
                            playerIconImage.setBitmap(Utils.drawableToBitmap(playerData.getIcon()));
                        }

                        MessagesHandler.messageGoogleLogged.setText(Game.getContext().getResources().getString(R.string.googleLogado) + "\u0020" + playerData.getName());

                        if (MenuHandler.menuGoogleGeral != null){
                            MenuHandler.menuGoogleGeral.getMenuOptionByName("google").setText(getResources().getString(R.string.deslogarGoogle));
                        }

                    } else {

                        if (playerIconImage == null) {
                            Log.e(TAG, "criando novo playerIconImage2");
                            playerIconImage = new ImageBitmap("playerIconImage2", Game.resolutionX * 0.862f, Game.resolutionY * 0.75f, Game.resolutionX * 0.12f, Game.resolutionX * 0.12f,
                                    Utils.drawableToBitmap(Game.mainActivity.getResources().getDrawable(R.drawable.games_controller_grey)));

                            Utils.createAnimation4v(playerIconImage, "animScaleX", "animScaleX", 2000, 0f, 1f, 0.8f, 1f, 0.95f, 0.95f, 1f, 1f, true, true).start();
                            Utils.createAnimation4v(playerIconImage, "animScaleY", "animScaleY", 2000, 0f, 1f, 0.8f, 1f, 0.95f, 0.95f, 1f, 1f, true, true).start();

                        } else {
                            playerIconImage.setBitmap(Utils.drawableToBitmap(Game.mainActivity.getResources().getDrawable(R.drawable.games_controller_grey)));
                        }

                        MessagesHandler.messageGoogleLogged.setText(Game.getContext().getResources().getString(R.string.googleNaoLogado));

                        if (MenuHandler.menuGoogleGeral != null){
                            MenuHandler.menuGoogleGeral.getMenuOptionByName("google").setText(getResources().getString(R.string.logarGoogle));
                        }

                    }

                    Splash.signinConclude = true;
                    if (GoogleAPI.connecting || GoogleAPI.disconnecting){
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
                    }

                    playerIconImage.setListener(new InteractionListener("listenerplayerIconImage",
                            Game.resolutionX * 0.862f, Game.resolutionY * 0.75f, Game.resolutionX * 0.12f, Game.resolutionX * 0.12f,
                            5000, playerIconImage,
                            new InteractionListener.PressListener() {
                                @Override
                                public void onPress() {
                                    if (playerIconImage != null && !playerIconImage.isBlocked){
                                        playerIconImage.block();
                                        Animation anim = Utils.createAnimation3v(playerIconImage, "scaleX", "scaleX",
                                                400, 0f,  1f, 0.07f, 0.85f, 1f, 1f, false, true);
                                        anim.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationEnd() {
                                                if (GameStateHandler.gameState != GameStateHandler.GAME_STATE_MENU_GOOGLE) {
                                                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_GOOGLE);
                                                }
                                                playerIconImage.unblock();
                                            }
                                        });
                                        anim.start();

                                        Game.sound.playPlayMenuBig();

                                        Utils.createAnimation3v(playerIconImage, "scaleY", "scaleY",
                                                400, 0f,  1f, 0.07f, 0.85f, 1f, 1f, false, true).start();
                                    }
                                }

                                @Override
                                public void onUnpress() {

                                }
                            }
                    ));
                }
            }});
    }

    public void onBackPressed(){

        queueEvent(new Runnable() {

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

    public void setMessageScoreTotal(final String message){
        queueEvent(new Runnable() {
            public void run() {

                MessagesHandler.messageMaxScoreTotal.setText(message);
                MessagesHandler.messageMaxScoreTotal.display();

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
