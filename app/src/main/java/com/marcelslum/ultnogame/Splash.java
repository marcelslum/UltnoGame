package com.marcelslum.ultnogame;

import android.util.Log;

import com.google.android.gms.games.Games;

import java.util.ArrayList;

/**
 * Created by marcel on 09/10/2016.
 */

public class Splash {

    static long timeInitIntro;
    private static long timeInitConectando;
    private static long timeInitCarregando;
    private static int googleConnectionAttempts = 0;

    private final static long INTRO_DURATION = 5000;
    private final static long INTRO_PARTIAL_DURATION = 1500;

    private static final int MESSAGE_INTERNET_NAO_CONECTADA = 31;
    private static final int MESSAGE_CONECTANDO = 32;
    private static final int MESSAGE_CARREGANDO = 33;
    static final int AGUARDA_MESSAGE_INTERNET_NAO_CONECTADA = 34;
    private static final int AGUARDA_MESSAGE_GOOGLE_NAO_CONECTADO = 35;
    private static final int MESSAGE_GOOGLE_NAO_CONECTADO = 36;

    private static int state;

    private static Image tittle;
    private static Text message1;
    private static Text message2;

    static boolean loaderConclude = false;
    private static boolean loadingSaveGame = false;

    static void init(){
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

        Log.e("splash", "init");

        if (loaderConclude){
            Log.e("splash", "loader já concluido, conectando");
            setSplashMessage(MESSAGE_CONECTANDO);
            ConnectionHandler.connect();
            timeInitConectando = Utils.getTime();
        } else {
            Log.e("splash", "ativando loader");
            AsyncTasks.initLoader = new InitLoaderAsyncTask().execute();
            setSplashMessage(MESSAGE_CARREGANDO);
            timeInitCarregando = Utils.getTime();
        }

        googleConnectionAttempts = 0;
        setGameEntities();
    }

    private static void setGameEntities() {
        Game.tittle = tittle;
        MessagesHandler.messageSplash1 = message1;
        if (message2 != null) {
            MessagesHandler.messageSplash2 = message2;
        } else {
            MessagesHandler.messageSplash2 = null;
        }
    }

    static void setSplashMessage(int id){
        state = id;
        if (id == MESSAGE_CARREGANDO) {
            message1 = new Text("messageLoading",
                    0f, Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

            float w = message1.width;

            message1 = new Text("messageLoading",
                    Game.resolutionX * 0.5f - (w / 2), Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

            ArrayList<float[]> valuesAnimationMessageLoading = new ArrayList<>();
            valuesAnimationMessageLoading.add(new float[]{0f, 1f});
            valuesAnimationMessageLoading.add(new float[]{0.33f, 2f});
            valuesAnimationMessageLoading.add(new float[]{0.66f, 3f});
            Animation animationMessageLoading = new Animation(message1, "numberForAnimation", "numberForAnimation", 1800, valuesAnimationMessageLoading, true, false);
            animationMessageLoading.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (message1.numberForAnimation == 1f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + ".");
                    } else if (message1.numberForAnimation == 2f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "..");
                    } else if (message1.numberForAnimation == 3f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_carregando) + "...");
                    }
                }
            });
            animationMessageLoading.start();
            message2 = null;
        } else  if (id == MESSAGE_CONECTANDO) {
            message1 = new Text("messageConnecting",
                    0f, Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_conectando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

            float w = message1.width;

            message1 = new Text("messageConnecting",
                    Game.resolutionX * 0.5f - (w / 2), Game.resolutionY * 0.8f, Game.resolutionY * 0.08f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_conectando) + "..", Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_LEFT);

            ArrayList<float[]> valuesAnimationMessageLoading = new ArrayList<>();
            valuesAnimationMessageLoading.add(new float[]{0f, 1f});
            valuesAnimationMessageLoading.add(new float[]{0.33f, 2f});
            valuesAnimationMessageLoading.add(new float[]{0.66f, 3f});
            Animation animationMessageLoading = new Animation(message1, "numberForAnimation", "numberForAnimation", 1800, valuesAnimationMessageLoading, true, false);
            animationMessageLoading.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (message1.numberForAnimation == 1f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_conectando) + ".");
                    } else if (message1.numberForAnimation == 2f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_conectando) + "..");
                    } else if (message1.numberForAnimation == 3f) {
                        message1.setText(Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_conectando) + "...");
                    }
                }
            });
            animationMessageLoading.start();
            message2 = null;
        } else if (id == MESSAGE_INTERNET_NAO_CONECTADA){

            timeInitConectando = Utils.getTime();
            googleConnectionAttempts = 0;

            Log.e("setSplashState", "MESSAGE_INTERNET_NAO_CONECTADA");
            message1.cleanAnimations();
            message1 = new Text("messageSplash1",
                    Game.resolutionX* 0.5f, Game.resolutionY  * 0.75f, Game.resolutionY * 0.04f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_nao_foi_possivel_conectar1), Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_CENTER);

            message2 = new Text("messageSplash2",
                    Game.resolutionX* 0.5f, Game.resolutionY * 0.85f, Game.resolutionY * 0.035f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_clique_aqui), Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_CENTER);

            message1.display();
            message2.display();
        } else if (id == MESSAGE_GOOGLE_NAO_CONECTADO){

            timeInitConectando = Utils.getTime();
            googleConnectionAttempts = 0;

            Log.e("setSplashState", "MESSAGE_GOOGLE_NAO_CONECTADO");
            message1.cleanAnimations();
            message1 = new Text("messageSplash1",
                    Game.resolutionX* 0.5f, Game.resolutionY * 0.75f, Game.resolutionY * 0.04f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_nao_foi_possivel_conectar_ao_google), Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_CENTER);

            message2 = new Text("messageSplash2",
                    Game.resolutionX* 0.5f, Game.resolutionY * 0.85f, Game.resolutionY * 0.035f,
                    Game.mainActivity.getApplicationContext().getResources().getString(R.string.splash_clique_aqui), Game.font, new Color(0f, 0f, 0f, 0.6f), Text.TEXT_ALIGN_CENTER);

            message1.display();
            message2.display();
        }
        setGameEntities();
    }

    static void display() {
        tittle.display();
        message1.display();
        if (message2 != null){
            message1.display();
        }
    }

    static void render(float[] matrixView, float[] matrixProjection) {
        tittle.prepareRender(matrixView, matrixProjection);
        message1.prepareRender(matrixView, matrixProjection);
        if (message2 != null){
            message2.prepareRender(matrixView, matrixProjection);
        }
    }

    static void verifySplashState() {
        if (state == MESSAGE_CARREGANDO){
            if (Utils.getTime() - timeInitCarregando > INTRO_PARTIAL_DURATION
            && loaderConclude) {
                Log.e("splash", "carregado - ativando conexao");
                timeInitConectando = Utils.getTime();
                ConnectionHandler.connect();
                setSplashMessage(MESSAGE_CONECTANDO);
            }
        } else if (state == MESSAGE_CONECTANDO){
            if ((Utils.getTime() - timeInitIntro) > INTRO_DURATION
                && Utils.getTime() - timeInitConectando > INTRO_PARTIAL_DURATION
                && ConnectionHandler.internetState == ConnectionHandler.INTERNET_STATE_CONNECTED) {
                Log.e("splash", "conectado - verificando conexao com o google");
                if (Game.mainActivity.mGoogleApiClient != null && Game.mainActivity.mGoogleApiClient.isConnected()) {
                    if (Game.currentPlayerId == null) {
                        Log.e("splash", "conectado ao google");
                        Game.currentPlayerId = Games.Players.getCurrentPlayerId(Game.mainActivity.mGoogleApiClient);
                        Storage.init(Game.mainActivity.getApplicationContext(), Game.currentPlayerId);
                    }

                        if (SaveGame.loaded){
                            Log.e("splash", "TUDO CARRREGADO - ativando game state menu");
                            loadingSaveGame = false;
                            LevelDataLoader.initLevelsData();
                            MenuHandler.initMenus();
                            Game.initTittle();
                            MessagesHandler.initMessages();
                            Game.initEdges();
                            ButtonHandler.initButtons();

                            //SaveGame.saveGame.currentGroupMenuTranslateX = 0;

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
                        } else {
                            if (!loadingSaveGame){
                                loadingSaveGame = true;
                                Log.e("splash", "iniciando carregamento do SaveGame");
                                SaveGame.load();

                                

                            }

                            Log.e("splash", "ainda carregando SaveGame");

                        }
                } else {
                    Log.e("splash", "ainda não conectado ao google");
                    if (googleConnectionAttempts < 6) {
                        Log.e("splash", "fazendo nova tentativa");
                        timeInitConectando = Utils.getTime();
                        googleConnectionAttempts += 1;
                        Game.mainActivity.mGoogleApiClient.connect();
                    } else {
                        Log.e("splash", "falhou ao conectar ao google");
                        setSplashMessage(AGUARDA_MESSAGE_GOOGLE_NAO_CONECTADO);
                        googleConnectionAttempts = 0;
                    }
                }
            }
        } else if (state == AGUARDA_MESSAGE_INTERNET_NAO_CONECTADA
                && Utils.getTime() - timeInitConectando > INTRO_PARTIAL_DURATION){
            setSplashMessage(MESSAGE_INTERNET_NAO_CONECTADA);
        } else if (state == AGUARDA_MESSAGE_GOOGLE_NAO_CONECTADO
                && Utils.getTime() - timeInitConectando > INTRO_PARTIAL_DURATION){
            setSplashMessage(MESSAGE_GOOGLE_NAO_CONECTADO);
        }
    }

    static void notifyClick() {
        if (state == MESSAGE_INTERNET_NAO_CONECTADA || state == MESSAGE_GOOGLE_NAO_CONECTADO) {
            timeInitConectando = Utils.getTime();
            setSplashMessage(MESSAGE_CONECTANDO);
            ConnectionHandler.connect();
            Game.mainActivity.mGoogleApiClient.connect();
        }
    }
}
