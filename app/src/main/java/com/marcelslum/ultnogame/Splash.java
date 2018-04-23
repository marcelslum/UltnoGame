package com.marcelslum.ultnogame;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;

import java.util.ArrayList;

/**
 * Created by marcel on 09/10/2016.
 */

public class Splash {

    private final static String TAG = "Splash";

    static Text messageSplash1;
    static Menu menuGoogle;
    static Menu menuVelocity;
    static Selector selectorDifficultyInitMenu;
    static TextView messageGoogle1;
    static TextView messageGoogle2;
    static TextView messageVelocity1;
    static TextView messageVelocity2;


    static Image tittle;

    static long timeInitIntro;
    private static long timeInitConectando;
    private static long timeInitCarregando;

    private final static long INTRO_PARTIAL_DURATION = 2000;

    private static final int SPLASH_CARREGANDO = 33;
    private static final int SPLASH_CONECTANDO_INTERNET = 32;
    private static final int SPLASH_CONECTANDO_GOOGLE = 34;
    private static final int SPLASH_CARREGANDO_SAVE_GAME = 35;
    private static final int SPLASH_FINISHED = 36;
    private static final int SPLASH_MENU_GOOGLE = 37;
    private static final int SPLASH_SIGNIN = 38;
    private static final int SPLASH_MENU_VELOCITY = 39;

    private static int state;

    static boolean loaderConclude = false;

    public static boolean forSignin = false;
    public static boolean forJumpGoogle = false;

    static void configSplash(){

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
                    Game.resolutionX * 0.99f, Game.resolutionY * 0.25f, Game.resolutionY * 0.035f, "Versão beta.", Game.font, new Color(1f, 0.2f, 0.2f, 1f), Text.TEXT_ALIGN_RIGHT);
            MessagesHandler.messageBeta.setAlpha(0.7f);
            Utils.createAnimation3v(MessagesHandler.messageBeta, "alphaBeta","alpha", 3000, 0f, 0.3f, .5f, 0.6f, 1f, 0.3f, true, true).start();
            MessagesHandler.messageBeta.display();

        }

        tittle.display();

        // -------------------------------------------MENU CONECTAR GOOGLE
        float fontSize = Game.gameAreaResolutionY*0.08f;

        menuGoogle = new Menu("menuGoogle", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.45f, fontSize, Game.font);
        menuGoogle.addMenuOption("Sim", Game.getContext().getResources().getString(R.string.menuGoogleSim), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                timesGoogle = 0;
                SaveGame.saveGame.googleOption = 1;
                SaveGame.saveGame.save();
                forSignin = true;
                init();

            }
        });

        menuGoogle.addMenuOption("Nao", Game.getContext().getResources().getString(R.string.menuGoogleNao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                timesGoogle = 0;
                SaveGame.saveGame.googleOption = 0;
                forSignin = false;
                SaveGame.saveGame.save();
                init();
            }
        });

        menuGoogle.addMenuOption("MaisTarde", Game.getContext().getResources().getString(R.string.menuGoogleMaisTarde), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                timesGoogle = 0;
                SaveGame.saveGame.googleOption = -1;
                forSignin = false;
                SaveGame.saveGame.save();
                forJumpGoogle = true;
                init();
            }
        });

        menuGoogle.blockAndClearDisplay();

        messageGoogle1 = new TextView("messageGoogle1", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.1f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.8f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageGoogle1.addText(Game.getContext().getResources().getString(R.string.messageGoogle1), new Color(0f, 0f, 0f, 1f));
        messageGoogle1.addText(Game.getContext().getResources().getString(R.string.messageGoogle1b), new Color(0f, 0f, 0f, 1f));

        messageGoogle1.clearDisplay();

        messageGoogle2 = new TextView("messageGoogle2", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.8f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.55f,
                Game.font, new Color(0.6f, 0.6f, 0.7f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageGoogle2.addText(Game.getContext().getResources().getString(R.string.messageGoogle2), new Color(0f, 0f, 0f, 1f));

        messageGoogle2.clearDisplay();

        // -------------------------------------------MENU ALTERAR VELOCIDADE


        // SELETOR DIFICULDADE
        selectorDifficultyInitMenu = new Selector("Game.selectorDifficultyInitMenu", 0f,0f, fontSize, "",
                new String[]{
                        Game.getContext().getResources().getString(R.string.v0),
                        Game.getContext().getResources().getString(R.string.v1),
                        Game.getContext().getResources().getString(R.string.v2),
                        Game.getContext().getResources().getString(R.string.v3),
                        Game.getContext().getResources().getString(R.string.v4),
                        Game.getContext().getResources().getString(R.string.v5),
                        Game.getContext().getResources().getString(R.string.v6)
                },
                Game.font);

        selectorDifficultyInitMenu.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (selectorDifficultyInitMenu.selectedValue == 0) {
                    SaveGame.saveGame.ballVelocity = 90;
                } else if (selectorDifficultyInitMenu.selectedValue == 1) {
                    SaveGame.saveGame.ballVelocity = 100;
                } else if (selectorDifficultyInitMenu.selectedValue == 2) {
                    SaveGame.saveGame.ballVelocity = 110;
                } else if (selectorDifficultyInitMenu.selectedValue == 3) {
                    SaveGame.saveGame.ballVelocity = 120;
                } else if (selectorDifficultyInitMenu.selectedValue == 4) {
                    SaveGame.saveGame.ballVelocity = 130;
                } else if (selectorDifficultyInitMenu.selectedValue == 5) {
                    SaveGame.saveGame.ballVelocity = 140;
                } else if (selectorDifficultyInitMenu.selectedValue == 6) {
                    SaveGame.saveGame.ballVelocity = 150;
                }
            }
        });

        menuVelocity = new Menu("menuVelocity", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        menuVelocity.addMenuOption("Alterar", Game.getContext().getResources().getString(R.string.menuVelocityAlterar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                selectorDifficultyInitMenu.fromMenu(menuVelocity);
            }
        });

        MenuOption menuOptionBallVelocity = menuVelocity.getMenuOptionByName("Alterar");
        selectorDifficultyInitMenu.setPosition(menuOptionBallVelocity.x + (menuOptionBallVelocity.width*0.9f), menuOptionBallVelocity.y);

        menuVelocity.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.menuVelocityContinuar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuVelocity.blockAndClearDisplay();
                messageVelocity1.clearDisplay();
                messageVelocity2.clearDisplay();
                setSplashState(SPLASH_CONECTANDO_INTERNET);
            }
        });

        menuVelocity.blockAndClearDisplay();

        messageVelocity1 = new TextView("messageVelocity1", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.2f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.8f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageVelocity1.addText(Game.getContext().getResources().getString(R.string.messageVelocity1), new Color(0f, 0f, 0f, 1f));
        messageVelocity1.addText(Game.getContext().getResources().getString(R.string.messageVelocity1b), new Color(0f, 0f, 0f, 1f));

        messageVelocity1.clearDisplay();

        messageVelocity2 = new TextView("messageVelocity1", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.8f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.55f,
                Game.font, new Color(0.6f, 0.6f, 0.7f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageVelocity2.addText(Game.getContext().getResources().getString(R.string.messageVelocity2), Color.cinza3);

        messageVelocity2.clearDisplay();

    }

    static void init(){

        //Log.e(TAG, "init Splash");
        if (forSignin) {
            //Log.e(TAG, "forSignin");
            loaderConclude = false;
        } else {
            //Log.e(TAG, "not forSignin");
            if (Sound.soundPool != null){
                loaderConclude = true;
            } else {
                loaderConclude = false;
            }
        }
        Splash.timeInitIntro = Utils.getTime();
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

    static void setMessageConectando(){
        messageSplash1 = new Text("messageConnecting",
                0f, Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

        float w = messageSplash1.width;

        messageSplash1 = new Text("messageConnecting",
                Game.resolutionX * 0.5f - (w / 2), Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

        ArrayList<float[]> valuesAnimationMessageLoading = new ArrayList<>();
        valuesAnimationMessageLoading.add(new float[]{0f, 1f});
        valuesAnimationMessageLoading.add(new float[]{0.33f, 2f});
        valuesAnimationMessageLoading.add(new float[]{0.66f, 3f});
        Animation animationMessageLoading = new Animation(messageSplash1, "numberForAnimation", "numberForAnimation", 1200, valuesAnimationMessageLoading, true, false);
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

            if (!forSignin && loaderConclude == false) {
                AsyncTasks.initLoader = new InitLoaderAsyncTask().execute();
            } else {
                loaderConclude = true;
            }
            timeInitCarregando = Utils.getTime();
            setMessageCarregando();
            tittle.display();
            messageSplash1.display();
            messageGoogle1.clearDisplay();
            messageGoogle2.clearDisplay();
            menuGoogle.blockAndClearDisplay();
        } if (id == SPLASH_SIGNIN) {
            tittle.display();
            timeInitConectando = Utils.getTime();
            messageSplash1.clearDisplay();
            messageGoogle1.clearDisplay();
            messageGoogle2.clearDisplay();
            menuGoogle.blockAndClearDisplay();
        } else if (id == SPLASH_CONECTANDO_INTERNET) {
            ConnectionHandler.checkInternetConnection();
            timeInitConectando = Utils.getTime();
            //setMessageConectando();
            tittle.display();
            messageSplash1.display();
        }
         else if (id == SPLASH_MENU_VELOCITY) {
            selectorDifficultyInitMenu.setSelectedValue(1);
            SaveGame.saveGame.ballVelocity = 100;
            menuVelocity.appearAndUnblock(100);
            messageVelocity1.display();
            messageVelocity2.display();
            tittle.clearDisplay();
            messageSplash1.clearDisplay();
        } else if (id == SPLASH_MENU_GOOGLE){
            timeInitConectando = Utils.getTime();
            menuGoogle.appearAndUnblock(100);
            messageGoogle1.display();
            messageGoogle2.display();
            tittle.clearDisplay();
            messageSplash1.clearDisplay();
        } else if (id == SPLASH_CONECTANDO_GOOGLE){
            timeInitConectando = Utils.getTime();
        } else if (id == SPLASH_CARREGANDO_SAVE_GAME) {
            timeInitConectando = Utils.getTime();
        }
    }

    static void render(float[] matrixView, float[] matrixProjection) {
        if (tittle == null){
            return;
        }

        if (menuGoogle != null) menuGoogle.checkTransformations(true);
        if (messageGoogle1 != null) messageGoogle1.checkTransformations(true);
        if (messageGoogle2 != null) messageGoogle2.checkTransformations(true);

        if (menuVelocity != null) menuVelocity.checkTransformations(true);
        if (messageVelocity1 != null) messageVelocity1.checkTransformations(true);
        if (messageVelocity2 != null) messageVelocity2.checkTransformations(true);
        if (selectorDifficultyInitMenu != null) selectorDifficultyInitMenu.checkTransformations(true);

        if (Game.versaoBeta && MessagesHandler.messageBeta != null){
            MessagesHandler.messageBeta.prepareRender(matrixView, matrixProjection);
        }

        tittle.prepareRender(matrixView, matrixProjection);
        messageSplash1.prepareRender(matrixView, matrixProjection);
        menuGoogle.prepareRender(matrixView, matrixProjection);
        messageGoogle1.prepareRender(matrixView, matrixProjection);
        if (messageGoogle2 != null) messageGoogle2.prepareRender(matrixView, matrixProjection);

        menuVelocity.prepareRender(matrixView, matrixProjection);
        messageVelocity1.prepareRender(matrixView, matrixProjection);
        if (messageVelocity2 != null) messageVelocity2.prepareRender(matrixView, matrixProjection);
        if (selectorDifficultyInitMenu != null) selectorDifficultyInitMenu.prepareRender(matrixView, matrixProjection);
    }

    static int timesGoogle = 0;

    static void finishSplashLoad() {

        if (SaveGame.saveGame.googleOption == 1) {
            if (!Game.mainActivity.checkGoogleConnection()) {
                timesGoogle += 1;
                //Log.e(TAG, "timesGoogle "+timesGoogle);
                if (timesGoogle < 3) {
                    forSignin = true;
                    init();
                } else {
                    forSignin = false;
                    SaveGame.saveGame.googleOption = -1;
                    SaveGame.saveGame.save();
                    init();
                }
                setSplashState(SPLASH_CARREGANDO);
            }
        }

        state = SPLASH_FINISHED;
        LevelDataLoader.initLevelsData();
        MenuHandler.initMenus();
        Game.initTittle();
        MessagesHandler.initMessages();
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
        Game.setGameState(Game.GAME_STATE_MENU);
    }

    static void verifySplashState() {

        if (state == SPLASH_CARREGANDO) {

            if (Game.paraGravacaoVideo){
                if (Utils.getTime() - timeInitCarregando > INTRO_PARTIAL_DURATION/6f && loaderConclude) {
                    if (SaveGame.saveGame.ballVelocity < 0) {
                        setSplashState(SPLASH_MENU_VELOCITY);
                    } else {
                        setSplashState(SPLASH_CONECTANDO_INTERNET);
                    }
                }

            } else {
                if (Utils.getTime() - timeInitCarregando > INTRO_PARTIAL_DURATION/6f && loaderConclude) {
                    if (SaveGame.saveGame.ballVelocity < 0) {
                        setSplashState(SPLASH_MENU_VELOCITY);
                    } else {
                        setSplashState(SPLASH_CONECTANDO_INTERNET);
                    }
                }

            }


        } else if (state == SPLASH_CONECTANDO_INTERNET) {
            if (forSignin){
                //Log.e(TAG, "forSignin startintent ");
                forSignin = false;
                Game.mainActivity.startSignInIntent();
            } else if (Utils.getTime() - timeInitConectando > (INTRO_PARTIAL_DURATION / 2f)) {
                //Log.e(TAG, "GoogleOption "+ SaveGame.saveGame.googleOption);
                int googlePlayOption = SaveGame.saveGame.googleOption;

                //googlePlayOption = 1;

                //Log.e(TAG, "googlePlayOption "+ googlePlayOption);
                if (googlePlayOption == -1){
                    if (!forJumpGoogle) {
                        setSplashState(SPLASH_MENU_GOOGLE);
                    } else {
                        setSplashState(SPLASH_CARREGANDO_SAVE_GAME);
                    }
                } else if (googlePlayOption == 1){

                    if (!Game.mainActivity.isGooglePlayAvailable()){
                        Game.mainActivity.startSignInIntent();
                    }
                    setSplashState(SPLASH_CONECTANDO_GOOGLE);
                } else if (googlePlayOption == 0){
                    setSplashState(SPLASH_CARREGANDO_SAVE_GAME);
                }
            }
        } else if (state == SPLASH_CONECTANDO_GOOGLE) {
            if (Game.mainActivity.isGooglePlayAvailable() && ((Game.mainActivity.isSignedIn()))) {
                setSplashState(SPLASH_CARREGANDO_SAVE_GAME);
            } else if (Utils.getTime() - timeInitConectando > INTRO_PARTIAL_DURATION * 2 && (!Game.mainActivity.isSignedIn()) || (!Game.mainActivity.isGooglePlayAvailable())){
                    //timesGoogle += 1;
                    //Log.e(TAG, "timesGoogle "+timesGoogle);
                    //if (timesGoogle < 3) {
                    //    forSignin = true;
                    //    init();
                    //} else {
                
                        // se não conseguiu conectar ao google, volta ao início
                        forSignin = false;
                        SaveGame.saveGame.googleOption = -1;
                        SaveGame.saveGame.save();
                        init();
                        setSplashState(SPLASH_CONECTANDO_INTERNET);
                
                    //}
            }
        } else if (state == SPLASH_CARREGANDO_SAVE_GAME){
            if (Utils.getTime() - timeInitConectando > INTRO_PARTIAL_DURATION / 3f) {
                finishSplashLoad();
            }
        }

    }
}
