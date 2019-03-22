package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 09/10/2016.
 */

public class Splash {

    private final static String TAG = "Splash";

    static Text messageSplash1;
    static Menu menuVelocity;
    static Selector selectorDifficultyInitMenu;
    static MyTextView messageVelocity1;
    static MyTextView messageVelocity2;
    static Image tittle;
    static long timeInitIntro;

    private static long timeInitCarregando;

    private final static long INTRO_PARTIAL_DURATION = 2000;
    private static final int SPLASH_CARREGANDO = 33;
    private static final int SPLASH_CONECTANDO_INTERNET = 32;
    private static final int SPLASH_CARREGANDO_SAVE_GAME = 35;
    private static final int SPLASH_FINISHED = 36;
    private static final int SPLASH_MENU_VELOCITY = 39;


    private static int state;

    static boolean loaderConclude = false;

    static boolean loaderConclude2 = false;

    static boolean signinConclude = false;

    static void configSplash(){

        MessagesHandler.messageGoogleLogged = new Text("messageGoogleLogged",
                Game.resolutionX*0.98f, Game.resolutionY - (Game.resolutionY * 0.06f), Game.resolutionY*0.03f,
                ".", Game.font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_RIGHT);
        Game.adicionarEntidadeFixa(MessagesHandler.messageGoogleLogged);

        tittle = new Image("tittle",
                Game.resolutionX * 0.2f, Game.resolutionY * 0.25f,
                Game.resolutionX * 0.6f, Game.resolutionX * 0.6f * 0.3671875f,
                Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_TITTLE_ID),
                new Color(0.0f, 0.0f, 0.0f, 1f));

        ArrayList<float[]> valuesAnimationTittleSplash = new ArrayList<>();
        valuesAnimationTittleSplash.add(new float[]{0f,1f});
        valuesAnimationTittleSplash.add(new float[]{0.68f,2f});
        Animation animationTittleSplash = new Animation(tittle, "numberForAnimation", "numberForAnimation", 2000, valuesAnimationTittleSplash, true, false);
        animationTittleSplash.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (tittle.numberForAnimation == 1f){
                    tittle.setColor(new Color(0f, 0f, 0f, 1f));
                } else if (tittle.numberForAnimation == 2f) {
                    tittle.setColor(new Color(0f, 0f, 1f, 1f));
                }
            }
        });
        animationTittleSplash.start();

        if (Game.versaoBeta) {
            MessagesHandler.messageBeta = new Text("messageBeta",
                    Game.resolutionX * 0.99f, Game.resolutionY * 0.25f, Game.resolutionY * 0.02f, "Versão beta.", Game.font, new Color(1f, 0.2f, 0.2f, 1f), Text.TEXT_ALIGN_RIGHT);
            MessagesHandler.messageBeta.setAlpha(0.7f);
            Utils.createAnimation3v(MessagesHandler.messageBeta, "alphaBeta","alpha", 3000, 0f, 0f, .5f, 0.3f, 1f, 0f, true, true).start();
            MessagesHandler.messageBeta.display();

        }

        tittle.display();

    }

    static void init(){


        if (Sound.soundPool != null){
            loaderConclude = true;
        } else {
            loaderConclude = false;
        }

        if (ButtonHandler.buttonContinue != null){
            loaderConclude2 = true;
        } else {
            loaderConclude2 = false;
        }

        signinConclude = false;

        Splash.timeInitIntro = Utils.getTimeMilliPrecision();
        configSplash();
        setSplashState(SPLASH_CARREGANDO);
    }

    static void setMessageCarregando(){

        messageSplash1 = new Text("messageLoading",
                0f, Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

        float w = messageSplash1.width;

        messageSplash1 = new Text("messageLoading",
                Game.resolutionX * 0.5f - (w / 2), Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

        ArrayList<float[]> valuesAnimationMessageLoading = new ArrayList<>();
        valuesAnimationMessageLoading.add(new float[]{0f, 1f});
        valuesAnimationMessageLoading.add(new float[]{0.33f, 2f});
        valuesAnimationMessageLoading.add(new float[]{0.66f, 3f});
        Animation animationMessageLoading = new Animation(messageSplash1, "numberForAnimation", "numberForAnimation", 1800, valuesAnimationMessageLoading, true, false);
        animationMessageLoading.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (messageSplash1.numberForAnimation == 1f) {
                    messageSplash1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + ".");
                } else if (messageSplash1.numberForAnimation == 2f) {
                    messageSplash1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..");
                } else if (messageSplash1.numberForAnimation == 3f) {
                    messageSplash1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "...");
                }
            }
        });
        animationMessageLoading.start();
    }

    static void setSplashState(int id){

        //Log.e(TAG, "set State Splash"+id);

        state = id;

        if (id == SPLASH_CARREGANDO) {

            if (loaderConclude == false) AsyncTasks.initLoader = new InitLoaderAsyncTask().execute();
            timeInitCarregando = Utils.getTimeMilliPrecision();
            setMessageCarregando();
            tittle.display();
            messageSplash1.display();

        } else if (id == SPLASH_CONECTANDO_INTERNET) {

            Game.sound.playPlayMenuBigTest();

            ConnectionHandler.checkInternetConnection();
            timeInitCarregando = Utils.getTimeMilliPrecision();
            tittle.display();
            messageSplash1.display();

            MessagesHandler.initMessages();

            Game.mainActivity.signInSilently();

            LevelDataLoader.initLevelsData();
            MenuHandler.initMenus();
            Game.initTittle();
            Game.initEdges();
            ButtonHandler.initButtons();

            if (MenuHandler.groupMenu != null) {
                MenuHandler.groupMenu.currentTranslateX =
                        SaveGame.saveGame.currentGroupMenuTranslateX;
            }

            if (MenuHandler.tutorialMenu != null) {
                MenuHandler.tutorialMenu.currentTranslateX =
                        SaveGame.saveGame.currentTutorialMenuTranslateX;
            }

            MenuHandler.levelMenu.currentTranslateX = 0;

            loaderConclude2 = true;

        } else if (id == SPLASH_MENU_VELOCITY) {

            selectorDifficultyInitMenu.setSelectedValue(1);
            menuVelocity.appearAndUnblock(100);
            messageVelocity1.display();
            messageVelocity2.display();
            tittle.clearDisplay();
            messageSplash1.clearDisplay();

        }
    }

    static void render(float[] matrixView, float[] matrixProjection) {
        if (tittle == null){
            return;
        }

        if (menuVelocity != null) menuVelocity.checkTransformations(true);
        if (messageVelocity1 != null) messageVelocity1.checkTransformations(true);
        if (messageVelocity2 != null) messageVelocity2.checkTransformations(true);
        if (selectorDifficultyInitMenu != null) selectorDifficultyInitMenu.checkTransformations(true);

        if (Game.versaoBeta && MessagesHandler.messageBeta != null){
            MessagesHandler.messageBeta.prepareRender(matrixView, matrixProjection);
        }

        if (tittle != null) tittle.prepareRender(matrixView, matrixProjection);
        if (messageSplash1 != null) messageSplash1.prepareRender(matrixView, matrixProjection);

        if (menuVelocity != null) menuVelocity.prepareRender(matrixView, matrixProjection);
        if (messageVelocity1 != null) messageVelocity1.prepareRender(matrixView, matrixProjection);
        if (messageVelocity2 != null) messageVelocity2.prepareRender(matrixView, matrixProjection);
        if (selectorDifficultyInitMenu != null) selectorDifficultyInitMenu.prepareRender(matrixView, matrixProjection);
    }

    static void finishSplashLoad() {
        state = SPLASH_FINISHED;
        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_INICIAL);
    }

    static void verifySplashState() {

        if (state == SPLASH_CARREGANDO) {

            if (Game.paraGravacaoVideo){
                if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION*0.25F && loaderConclude) {
                    if (SaveGame.saveGame.ballVelocity < 0) {
                        if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION * 2f) {
                            setSplashState(SPLASH_MENU_VELOCITY);
                        }
                    } else {
                        setSplashState(SPLASH_CONECTANDO_INTERNET);
                    }
                }

            } else {
                if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION && loaderConclude) {
                    if (SaveGame.saveGame.ballVelocity < 0) {
                        if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION * 2f) {
                            setSplashState(SPLASH_MENU_VELOCITY);
                        }
                    } else {
                        setSplashState(SPLASH_CONECTANDO_INTERNET);
                    }
                }

            }
        } else if (state == SPLASH_CONECTANDO_INTERNET){
            if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION*0.25f && (signinConclude || Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION*10f) && loaderConclude2) {
                signinConclude = false;
                loaderConclude2 = true;
                setSplashState(SPLASH_CARREGANDO_SAVE_GAME);
            }
        } else if (state == SPLASH_CARREGANDO_SAVE_GAME){
            if (Utils.getTimeMilliPrecision() - timeInitCarregando > INTRO_PARTIAL_DURATION && SaveGame.loaded) {
                finishSplashLoad();
            }
        }

    }
}
