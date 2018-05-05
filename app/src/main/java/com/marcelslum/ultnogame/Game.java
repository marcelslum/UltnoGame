package com.marcelslum.ultnogame;

import android.app.ActivityManager;
import android.content.Context;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.res.Resources;
import android.os.Vibrator;
import android.util.Log;

import static android.content.Context.ACTIVITY_SERVICE;

// TODO Resetar ranking geral
// TODO fechar banco de dados após uso



public class Game {

    public static Sound sound = new Sound();

    public static boolean forDebugDeleteDatabaseAndStorage = false;
    public static boolean ganharTodasAsEstrelas = true;
    public static boolean paraGravacaoVideo = false;
    public static boolean ganharComMetadeDosAlvos = false;
    public static boolean sempreGanharTodasEstrelas = false;
    public static boolean forDebugClearAllLevelPoints = false;
    public static boolean showMessageNotConnectedOnGoogle = false;
    public static boolean logFrame = false;
    public static boolean logDeTempoDeDuracaoDaChecagemDeColisao = false;
    public static boolean versaoBeta = true;
    public static boolean logInteractionListener = false;
    public static boolean logCollisionEscape = false;
    public static boolean logMenuIconMoveAndTranslateX = false;
    public static boolean sempreVerSaveMenu = false;
    public static boolean apagarEstatisticas = true;


    public static MyGLSurface myGlSurface;

    public static Pool<Vector> vectorPool;
    public static Pool<Text> textPool;
    public static Pool<Button> buttonPool;

    public static ArrayList<Text> textsForTest;

    public static final long TIME_OF_BALL_LISTENER = 250;

    public static long currentFrameMilliPrecision = -1;
    public static long elapsedMiliTimeSinceLastFrame = -1;

    public static long currentFrameNanoPrecision = -1;
    public static long elapsedNanoTimeSinceLastFrame = -1;

    public static float nanoPrecisionElapsed = 0f;

    public static long timeOfPrePlay = 0;

    public static ArrayList<String> messagesToDisplay = new ArrayList<>();
    static Vibrator vibrator;

    static final String TAG = "Game";

    static MainActivity mainActivity;

    static boolean forInitGame;

    static final int BASE_POINTS = 10;

    static final int FAKE_BALL_WEIGHT = 1;
    static final int BALL_WEIGHT = 2;
    static final int BORDA_WEIGHT = 10;
    static final int OBSTACLES_WEIGHT = 9;
    static final int BAR_WEIGHT = 5;

    static LevelGoalsPanel levelGoalsPanel;

    static TargetGroup targetGroup;
    static PointsGroup pointsGroup;
    static ArrayList<Target> targets;
    static ArrayList<Ball> balls;
    static ArrayList<Ball> fakeBalls;
    static ArrayList<Text> texts;
    static ArrayList<TouchEvent> touchEvents;
    static ArrayList<Bar> bars;
    static ArrayList<Obstacle> obstacles;
    static ArrayList<WindowGame> windows;
    static ArrayList<Menu> menus;
    static ArrayList<InteractionListener> interactionListeners;
    static ArrayList<TextBox> textBoxes;
    static ArrayList<BallBehaviourData> ballBehaviourDatas;
    static Messages messages;
    static ArrayList<Line> lines;
    static BrickBackground brickBackground;
    static WindNoShader wind;
    static ArrayList<SpecialBall> specialBalls;
    static ArrayList<Image> ballCollisionStars;
    static BallDataPanel ballDataPanel;
    static BallGoalsPanel ballGoalsPanel;
    static Edge bordaC;
    static Edge bordaE;
    static Edge bordaD;
    static Edge bordaB;


    static Rectangle frame;
    static Rectangle topFrame;

    static Image tittle;
    static ArrayList<Image> groupsUnblocked;
    static Image currentLevelIcon;
    static LevelsGroupData currentLevelsGroupDataSelected;
    static Image imageTutorialTop;
    static Image imageTutorialDown;
    static TextView aboutTextView;
    static TextView statsTextView;
    static TextView notConnectedTextView;
    static TextBox tipTextBox;

    // quadtree objects
    static Quadtree quad;

    // font
    public static Font font;

    // scree properties
    static float gameAreaResolutionX;
    static float gameAreaResolutionY;
    static float resolutionX;
    static float resolutionY;
    static float screenOffSetX;
    static float screenOffSetY;

    static int timesInterstitialOnGameOver = 0;
    static boolean prepareAfterInterstitialFlag = false;
    static boolean returningFromTraining = false;

    // options
    public static boolean isBlocked;
    
    // game state
    public static int gameState;
    public final static int GAME_STATE_ESTATISTICAS = 70;
    public final static int GAME_STATE_NOVA_TENTATIVA_TREINAMENTO = 70;
    public final static int GAME_STATE_PREPARAR_TREINAMENTO = 2;
    public final static int GAME_STATE_MENU_DURANTE_TREINAMENTO = 3;
    public final static int GAME_STATE_MENU_EXPLICACAO_TREINAMENTO = 4;
    public final static int GAME_STATE_MENU_JOGAR = 5;
    public final static int GAME_STATE_OPCOES_JOGABILIDADE =  6;
    public final static int GAME_STATE_MENU_SAVE_FIRST_TIME =  8;
    public final static int GAME_STATE_MENU_CARREGAR_JOGO = 7;
    public final static int GAME_STATE_PRE_JOGAR = 9;
    public final static int GAME_STATE_JOGAR = 10;
    public final static int GAME_STATE_PREPARAR = 11;
    public final static int GAME_STATE_MENU_PRINCIPAL =  12;
    public final static int GAME_STATE_VITORIA =  13;
    public final static int GAME_STATE_DERROTA =  14;
    public final static int GAME_STATE_TUTORIAL =  15;
    public final static int GAME_STATE_PAUSE =  16;
    public final static int GAME_STATE_OPCOES =  17;
    public final static int GAME_STATE_INTRO =  18;
    public final static int GAME_STATE_OPCOES_GAME =  19;
    public final static int GAME_STATE_SELECAO_GRUPO =  20;
    public final static int GAME_STATE_SELECAO_LEVEL =  21;
    public final static int GAME_STATE_OBJETIVO_LEVEL =  22;
    public final static int GAME_STATE_MENU_TUTORIAL =  24;
    public final static int GAME_STATE_INTERSTITIAL =  25;
    public final static int GAME_STATE_OBJETIVO_PAUSE =  26;
    public final static int GAME_STATE_VITORIA_COMPLEMENTACAO =  27;
    public final static int GAME_STATE_SOBRE =  28;

    public static ArrayList<GroupDataBaseData> groupsDataBaseData;
    public static ArrayList<LevelDataBaseData> levelsDataBaseData;

    static long initTime;
    static float effectiveScreenHeight;
    static float effectiveScreenWidth;
    
    // programs
    static Program imageProgram;
    static Program targetProgram;
    static Program imageColorizedProgram;
    static Program lineProgram;
    static Program textProgram;
    static Program solidProgram;
    static Program imageColorizedFxProgram;
    static Program windProgram;
    static Program specialBallProgram;
    static Program vertex_e_uv_com_alpha_program;
    static Program vertex_e_uv_com_alpha_program_e_color;
    static Program instancing;
    static Program vertex_e_color;
    
    static int ballsNotInvencibleAlive;
    static int ballsInvencible;
    static long initialTimePointsDecay;
    

    static boolean initPausedFlag;
    public static int dpiClassification;
    public static boolean returningFromInterstitialFlag = false;
    public static boolean settingMessageForScore = false;
    public static String messageForScore = ".";
    public static boolean forBlueBallExplode = false;
    public static float blueBallExplodeX = 0;
    public static float blueBallExplodeY = 0;
    public static boolean abdicateAngle = false;
    

    private Game() {}

    public static void returnFromAd(){

    }
    
    static final int VIBRATE_SMALL = 0;
    static final int VIBRATE_BAR = 4;
    static final int VIBRATE_TARGET = 1;
    static final int VIBRATE_HARD = 2;
    
    
    public static void vibrate(int intensity){

        // todo carregar save game no splash para ativar a opção de vibrar no menu
        if (SaveGame.saveGame == null){
            return;
        }

        if (!SaveGame.saveGame.vibration){
            return;
        }
        if (vibrator.hasVibrator()){
            long[] pattern;
            if (intensity == VIBRATE_SMALL){
                pattern = new long[]{0,20};
            } else if (intensity == VIBRATE_TARGET){
                pattern = new long[]{0,25,10,15, 10, 5};
            } else if (intensity == VIBRATE_HARD){
                pattern = new long[]{0,70,10,50, 8, 25, 7, 15};
            } else if (intensity == VIBRATE_BAR){
                pattern = new long[]{0,23,7,10, 5, 5};
            } else {
                pattern = new long[]{0};
            }
            vibrator.vibrate(pattern, -1);
        }
    }
    

    public static void init(){

        Log.e(TAG, "init");

        Splash.loaderConclude = false;

        initPrograms();
        initFont();

        if (Texture.textures == null) {
            Texture.textures = new ArrayList<>();
        } else {
            Texture.textures.clear();
        }

        //getContext().getSystemService(Context.AUDIO_SERVICE).getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)

        Texture.clear();
        Texture.textures.add(new Texture(Texture.TEXTURES, "drawable/textures1"));
        Texture.init();

        Game.frame = new Rectangle("frame", 0f, 0f, Entity.TYPE_OTHER, Game.resolutionX, Game.resolutionY, -1, new Color(0f, 0f, 0f, 1f));
        Game.frame.clearDisplay();
        Game.frame.alpha = 0f;
        TextureData.getTextureData();

        Game.topFrame = new Rectangle("frame", 0f, 0f, Entity.TYPE_OTHER, Game.resolutionX, Game.resolutionY * 0.12f, -1, new Color(0.2f, 0.2f, 0.2f, 1f));
        Game.topFrame.clearDisplay();

        Log.e(TAG, "setGameState(Game.GAME_STATE_INTRO)1");
        setGameState(Game.GAME_STATE_INTRO);
    }

    public static void showBlackFrameTransition(int duration){
        frame.display();
        frame.alpha = 1f;

        Animation anim = Utils.createAnimation4v(frame, "alpha", "alpha", duration, 0f, 1f, 0.25f, 1f, 0.5f, 0.7f, 1f, 0f, false, true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                frame.clearDisplay();
            }
        });
        anim.start();
    }

    public static void initData(){
        targets = new ArrayList<>();
        ballCollisionStars = new ArrayList<>();
        balls = new ArrayList<>();
        fakeBalls = new ArrayList<>();
        obstacles = new ArrayList<>();
        windows = new ArrayList<>();
        specialBalls = new ArrayList<>();
        touchEvents = new ArrayList<>();
        texts = new ArrayList<>();
        interactionListeners = new ArrayList<>();
        bars = new ArrayList<>();
        menus = new ArrayList<>();
        textBoxes = new ArrayList<>();
        ballBehaviourDatas = new ArrayList<>();
        messages = new Messages();
        lines = new ArrayList<> ();
        groupsUnblocked = new ArrayList<>();

        vectorPool = new ObjectPool<Vector>();
        vectorPool.setFactory(new VectorFactory());

        textPool = new ObjectPool<Text>();
        textPool.setFactory(new TextFactory());
        
        buttonPool = new ObjectPool<Button>();
        buttonPool.setFactory(new ButtonFactory());

    }

    public static final Color COLOR_BORDA_B = new Color(0.17f, 0.17f, 0.17f, 1f);

    public static void initEdges(){
        bordaE = new Edge("bordaE", -999, 0, Entity.TYPE_LEFT_BORDER, 1000, resolutionY*2, Color.pretoCheio);
        bordaD = new Edge("bordaD", resolutionX-2, 0,  Entity.TYPE_RIGHT_BORDER, 2000, resolutionY*2, Color.pretoCheio);
        bordaC = new Edge("bordaC",  1, -1000,  Entity.TYPE_TOP_BORDER, resolutionX-4, 1001, Color.pretoCheio);
        bordaB = new Edge("bordaB", -1000, resolutionY,  Entity.TYPE_BOTTOM_BORDER, resolutionX*3, 1000, COLOR_BORDA_B);

    }

    public static void initTittle(){

        tittle = new Image("tittle",
                gameAreaResolutionX * 0.3f, gameAreaResolutionY * 0.13f,
                gameAreaResolutionX * 0.4f, gameAreaResolutionX * 0.4f * 0.3671875f,
                Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_TITTLE_ID),
                new Color(0.5f, 0.2f, 0.8f, 1f));


        Animation animTittle = Utils.createAnimation5v(tittle, "numberForAnimation", "numberForAnimation", 5000, 0f, 1f, 0.15f, 2f, 0.45f, 3f, 0.6f, 4f, 0.85f, 5f, true, false);
        animTittle.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (tittle.numberForAnimation == 1f){
                    tittle.setColor(Color.pretoCheio);
                } else if (tittle.numberForAnimation == 2f) {
                } else if (tittle.numberForAnimation == 3f) {
                    tittle.setColor(Color.azulCheio);
                } else if (tittle.numberForAnimation == 4f) {
                    tittle.setColor(Color.verdeCheio);
                } else if (tittle.numberForAnimation == 5f) {
                    tittle.setColor(Color.amareloCheio);
                }
            }
        });
        animTittle.start();
    }


    public static void initPrograms(){

        vertex_e_uv_com_alpha_program = new Program(
            Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_vertex_e_uv_com_alpha),
            Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_vertex_e_uv_com_alpha)
        );

        vertex_e_uv_com_alpha_program_e_color = new Program(
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_vertex_e_uv_com_alpha_e_color),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_vertex_e_uv_com_alpha_e_color)
        );

        instancing = new Program(
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_vertex_e_uv_com_alpha),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_vertex_e_uv_com_alpha)
        );

        vertex_e_color = new Program(
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_vertex_e_color),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_vertex_e_color)
        );
        imageProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_text),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_text));
        targetProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_target),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_target));
        textProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_imagecolorized),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_imagecolorized));
        imageColorizedProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_imagecolorized),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_imagecolorized));
        imageColorizedFxProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_imagecolorizedfx),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_imagecolorizedfx));
        solidProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_solidcolor),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_solidcolor));
        lineProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_line),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_line));
        windProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_wind),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_wind3));
        specialBallProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_specialball),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_specialball));
    }

    static void initFont(){
        font = new Font(Texture.TEXTURES, textProgram);
    }
    static void addBall(Ball ball){
        balls.add(ball);
    }
    
    static void addFakeBall(Ball ball){
        ball.markAsFakeBall();
        fakeBalls.add(ball);
    }
    
    static void addBar(Bar bar){
        bars.add(bar);
    }
    static void addTarget(Target target){
        targets.add(target);
    }
    static void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }
    static void addWindow(WindowGame window){
        windows.add(window);
    }
    static void addSpecialBall(SpecialBall sb){
        specialBalls.add(sb);
    }
    static void addText(Text text){
        texts.add(text);
    }
    static void addInteracionListener(InteractionListener listener) {
        if (interactionListeners == null){
            interactionListeners = new ArrayList<InteractionListener>();
        }
        for (int i = 0; i < interactionListeners.size(); i++){
            if (interactionListeners.get(i).name == listener.name){
                interactionListeners.set(i, listener);
                return;
            }
        }
        interactionListeners.add(listener);
    }

    public static void blockAndWaitTouchRelease(){isBlocked = true;}


    public static void showTip(){

        if (gameState != GAME_STATE_OBJETIVO_LEVEL) return;

        String tip = LevelGoalsLoader.getLevelTip(SaveGame.saveGame.currentLevelNumber);

        if(!tip.equals("")){
            tipTextBox = new TextBoxBuilder("tipTextBox")
                    .position(Game.resolutionX * 0.19f, Game.resolutionY * 0.73f)
                    .width(Game.resolutionX * 0.55f)
                    .size(Game.gameAreaResolutionY*0.045f)
                    .text(tip)
                    .isHaveFrame(true, new Color(0.3f, 0.3f, 0.5f, 1f))
                    .isHaveArrowContinue(false)
                    .setTextColor(Color.branco)
                    .setShadowColor(Color.cinza20)
                    .build();



            float random = Utils.getRandonFloat(0f, 1f);

            if (random < 0.33f) {
                tipTextBox.frame.setMultiColor(
                        Color.verde40,
                        Color.branco,
                        Color.verde40,
                        Color.verde40
                );

                tipTextBox.frame.addTopRectangle(
                        0.9f,
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza40.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        .05f,
                        Game.gameAreaResolutionX * 0.003f,
                        Game.gameAreaResolutionX * 0.003f,
                        Color.verde40
                );

            } else if (random < 0.66f){
                tipTextBox.frame.setMultiColor(
                        Color.vermelhoCheio,
                        Color.branco,
                        Color.vermelhoCheio,
                        Color.vermelhoCheio
                );

                tipTextBox.frame.addTopRectangle(
                        0.9f,
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza40.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        .05f,
                        Game.gameAreaResolutionX * 0.003f,
                        Game.gameAreaResolutionX * 0.003f,
                        Color.vermelho40
                );

            } else {
                tipTextBox.frame.setMultiColor(
                        Color.azulCheio,
                        Color.branco,
                        Color.azulCheio,
                        Color.azulCheio
                );

                tipTextBox.frame.addTopRectangle(
                        0.9f,
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza40.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        Color.cinza20.changeAlpha(0.4f),
                        .05f,
                        Game.gameAreaResolutionX * 0.003f,
                        Game.gameAreaResolutionX * 0.003f,
                        Color.azul40
                );
            }


            tipTextBox.animTranslateX = Game.resolutionX * 2f;

            tipTextBox.setOnPress(new TextBox.OnPress() {
                @Override
                public void onPress() {

                    tipTextBox.cleanAnimations();

                    tipTextBox.reduceAlpha(500, 0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Game.showTip();
                            Game.sound.playPlayMenuBig();
                        }
                    });
                }
            });

            Game.sound.playTextBoxAppear();


            Animation anim1 = Utils.createAnimation4v(tipTextBox, "translateX", "translateX", 5000,
                    0f, Game.resolutionX * 2, 0.05f, 0f, 0.85f, 0f, 1f, 0, false, true);
            anim1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Animation a2 = Utils.createAnimation2v(tipTextBox, "translateX2", "translateX", 225,
                            0f, 0, 1,  -Game.resolutionX*2, false, true);
                    a2.addAttachedEntities(tipTextBox);
                    a2.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Animation a3 = Utils.createAnimation2v(tipTextBox, "translateX2", "translateX", 1200,
                                    0f, -Game.resolutionX*2, 1,  -Game.resolutionX*2, false, true);
                            a3.addAttachedEntities(tipTextBox);
                            a3.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationEnd() {
                                    if (gameState == GAME_STATE_OBJETIVO_LEVEL) {
                                        Game.showTip();
                                    }

                                }
                            });
                            a3.start();
                        }
                    });
                    a2.start();
                }
            });
            anim1.start();
        } else {
            if (tipTextBox != null){
                tipTextBox.clearDisplay();
            }
            tipTextBox = null;
        }


    }


    public static void setGameState(int state){
        Log.e("game", "set game state "+state);
        boolean sameState = false;
        int previousState = gameState;
        if (state == gameState){
            sameState = true;
        }

        gameState = state;
        Game.blockAndWaitTouchRelease();
        clearAllMenuEntities();
        if (MessageStar.messageStars != null) {
            MessageStar.messageStars.clearDisplay();
        }

        if (MessageStarWin.messageStarsWin != null) {
            MessageStarWin.messageStarsWin.clearDisplay();
        }
        
        if (currentLevelIcon != null)currentLevelIcon.clearDisplay();
        if (groupsUnblocked != null) groupsUnblocked.clear();

        TimeHandler.timeOfLevelPlayBlocked = true;

        if (GoogleAPI.playerIconImage != null){
            GoogleAPI.playerIconImage.clearDisplay();
        }

        if (state == GAME_STATE_INTERSTITIAL){
            eraseAllGameEntities();
            mainActivity.showInterstitial();
            
        } else if (state == GAME_STATE_SOBRE) {

            mainActivity.showAdView();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuAbout));
            aboutTextView.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();
        }
        else if (state == GAME_STATE_ESTATISTICAS){
                mainActivity.showAdView();
                MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuAbout));

            Game.statsTextView = new TextView("statsTextView", Game.resolutionX * 0.1f,
                    Game.resolutionY * 0.2f,
                    Game.resolutionX * 0.8f,
                    Game.resolutionY * 0.8f,
                    Game.gameAreaResolutionY*0.05f,
                    Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);

            Resources resources = Game.getContext().getResources();
            Game.statsTextView.addText(resources.getString(R.string.estatisticaTitulo), Color.azul);
            Game.statsTextView.addText(".", Color.transparente);
            Game.statsTextView.addText(resources.getString(R.string.stat0) + " " + SaveGame.saveGame.stats[0], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat1)+ " " + SaveGame.saveGame.stats[1], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat2)+ " " + SaveGame.saveGame.stats[2], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat3)+ " " + SaveGame.saveGame.stats[3], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat4)+ " " + SaveGame.saveGame.stats[4], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat5)+ " " + SaveGame.saveGame.stats[5], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat6)+ " " + SaveGame.saveGame.stats[6], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat7)+ " " + SaveGame.saveGame.stats[7], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat8)+ " " + SaveGame.saveGame.stats[8], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat9)+ " " + SaveGame.saveGame.stats[9], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat10)+ " " + SaveGame.saveGame.stats[10], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat11)+ " " + SaveGame.saveGame.stats[11], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat12)+ " " + SaveGame.saveGame.stats[12], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat13)+ " " + SaveGame.saveGame.stats[13], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat14)+ " " + SaveGame.saveGame.stats[14], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat15)+ " " + SaveGame.saveGame.stats[15], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat16)+ " " + SaveGame.saveGame.stats[16], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat17)+ " " + SaveGame.saveGame.stats[17], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat18)+ " " + SaveGame.saveGame.stats[18], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat19)+ " " + SaveGame.saveGame.stats[19], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat20)+ " " + SaveGame.saveGame.stats[20], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat21)+ " " + SaveGame.saveGame.stats[21], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat22)+ " " + SaveGame.saveGame.stats[22], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat23)+ " " + SaveGame.saveGame.stats[23], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat24)+ " " + SaveGame.saveGame.stats[24], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat25)+ " " + SaveGame.saveGame.stats[25], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat26)+ " " + SaveGame.saveGame.stats[26], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat27)+ " " + SaveGame.saveGame.stats[27], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat28)+ " " + SaveGame.saveGame.stats[28], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat29)+ " " + SaveGame.saveGame.stats[29], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat30)+ " " + SaveGame.saveGame.stats[30], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat31)+ " " + SaveGame.saveGame.stats[31], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat32)+ " " + SaveGame.saveGame.stats[32], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat33)+ " " + SaveGame.saveGame.stats[33], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat34)+ " " + SaveGame.saveGame.stats[34], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat35)+ " " + SaveGame.saveGame.stats[35], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat36)+ " " + SaveGame.saveGame.stats[36], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat37)+ " " + SaveGame.saveGame.stats[37], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat38)+ " " + SaveGame.saveGame.stats[38], Color.cinza20);
            Game.statsTextView.addText(resources.getString(R.string.stat39)+ " " + SaveGame.saveGame.stats[39], Color.cinza20);
            statsTextView.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();


        } else if (state == GAME_STATE_OBJETIVO_LEVEL){


            if (!sameState) {
                showBlackFrameTransition(500);
            }

            Sound.stopAndReleaseMusic();

            mainActivity.showAdView();
            Level.levelGoalsObject = new LevelGoals();
            Level.levelGoalsObject.levelGoals = LevelGoalsLoader.getLevelGoals(SaveGame.saveGame.currentLevelNumber);

            showTip();
            
            levelGoalsPanel = new LevelGoalsPanel("levelGoalsPanel", resolutionX * 0.2f, resolutionY * 0.2f, resolutionX * 0.025f, resolutionX * 0.79f);

            for (int i = 0; i < Level.levelGoalsObject.levelGoals.size(); i++){
                LevelGoal lg = Level.levelGoalsObject.levelGoals.get(i);
                levelGoalsPanel.addLine(lg.numberOfStars, true, lg.text);
            }
            levelGoalsPanel.appearGray(false);
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();

            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel) + " " + SaveGame.saveGame.currentLevelNumber);
            } else {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevelSecret) + " " + (SaveGame.saveGame.currentLevelNumber - 100));
            }

            ButtonHandler.buttonContinue.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();

            MessagesHandler.messageContinue.display();
            MessagesHandler.messageBack.display();

            MessagesHandler.messageContinue.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
            MessagesHandler.messageBack.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));

        } else if (state == GAME_STATE_OBJETIVO_PAUSE){

            mainActivity.showAdView();

            levelGoalsPanel.appearGrayAndShine(true);
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();
            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+ " " + SaveGame.saveGame.currentLevelNumber);
            } else {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevelSecret) + " " + (SaveGame.saveGame.currentLevelNumber - 100));
            }
            ButtonHandler.buttonReturnObjectivesPause.unblockAndDisplay();
            MessagesHandler.messageBack.setY(Game.gameAreaResolutionY * 0.895f);
            MessagesHandler.messageBack.setColor(new Color(0f, 0f, 0f, 1f));
            MessagesHandler.messageBack.display();

        } else if (state == GAME_STATE_SELECAO_GRUPO) {

            eraseAllGameEntities();

            if (!sameState) {
                    showBlackFrameTransition(500);
            }
            
            Sound.stopAndReleaseMusic();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");

            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(500);
            }

            Game.sound.playMenuIconDrop();
            //Sound.playSoundPool(Sound.soundMenuIconDrop2, 0.15f, 0.15f, 0);

            mainActivity.showAdView();

            Game.bordaB.setY(Game.resolutionY);
            MenuHandler.updateGroupMenu();
            MenuHandler.groupMenu.appear();
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoMundo));
            ButtonHandler.buttonReturn.unblockAndDisplay();


            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();


            
        } else if (state == GAME_STATE_MENU_JOGAR){

            if (!sameState) {
                showBlackFrameTransition(500);
            }

            GoogleAPI.displayGoogleInfo();

            mainActivity.showAdView();

            tittle.display();

            MessagesHandler.messageBack.display();

            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();

            MenuHandler.menuOptions.blockAndClearDisplay();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.groupMenu.blockAndClearDisplay();
            MenuHandler.levelMenu.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();

            MessagesHandler.messageMenuSaveNotSeen.clearDisplay();
            MessagesHandler.messageMenuCarregarJogo.clearDisplay();
            if (MessagesHandler.messageExplicacaoTreinamento != null) MessagesHandler.messageExplicacaoTreinamento.clearDisplay();
            if (MessagesHandler.messageTrainingState != null) MessagesHandler.messageTrainingState.clearDisplay();
            if (MessagesHandler.messageTrainingState2 != null) MessagesHandler.messageTrainingState2.clearDisplay();

            MenuHandler.menuPlay.unblockAndDisplay();

            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_MENU_EXPLICACAO_TREINAMENTO){
            if (!sameState) {
                showBlackFrameTransition(500);
            }

            MessagesHandler.messageExplicacaoTreinamento.display();
            MessagesHandler.messageBack.display();

            MenuHandler.menuExplicacaoAntesDoTreinamento.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_MENU_DURANTE_TREINAMENTO){

            if (!sameState) {
                showBlackFrameTransition(1000);
            }

            Training.tentativaCertaTreinamento = 0;
            Training.treinamentoSucesso = false;

            Training.resetTrainingEntities();

            Training.setMenuDuranteTreinamentoMessage();
            MessagesHandler.messageExplicacaoDuranteTreinamento.display();

            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
            }
            if (MessagesHandler.messageTrainingState2 != null) {
                MessagesHandler.messageTrainingState2.clearDisplay();
                //MessagesHandler.messageTrainingState2.setText(getContext().getResources().getString(R.string.tentativa) + " " + (Game.tentativaCertaTreinamento + 1) + " " +getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
                //MessagesHandler.messageTrainingState2.display();
            }

            MenuHandler.menuDuranteTreinamento.unblockAndDisplay();

        } else if (state == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){

            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
            }

            Training.resetTrainingEntities();

        } else if (state == GAME_STATE_MENU_TUTORIAL){

            if (!sameState) {
                showBlackFrameTransition(500);
            }

            Game.sound.playMenuIconDrop();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");

            mainActivity.showAdView();

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuTutorial));
            MenuHandler.updateTutorialMenu();
            MenuHandler.tutorialMenu.appear();
            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_SELECAO_LEVEL) {


            if (!sameState) {
                showBlackFrameTransition(500);
            }

            mainActivity.showAdView();

            Sound.stopAndReleaseMusic();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");
            
            if (tipTextBox != null){
                tipTextBox.clearDisplay();
            }

            Game.sound.playMenuIconDrop();
            //Sound.playSoundPool(Sound.soundMenuIconDrop2, 0.15f, 0.15f, 0);

            MenuHandler.groupMenu.clearDisplay();
            MenuHandler.groupMenu.block();
            
            MenuHandler.updateLevelMenu();
            MenuHandler.levelMenu.appear();

            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(500);
            }

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoLevel));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(currentLevelsGroupDataSelected.name);
            ButtonHandler.buttonReturn.unblockAndDisplay();

            ButtonHandler.buttonGroupLeaderboard.unblockAndDisplay();


            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();

        } else if (state == GAME_STATE_INTRO) {

            mainActivity.hideAdView();
            Log.e(TAG, "init1");
            Splash.init();
        } else if (state == GAME_STATE_OPCOES){
            MessagesHandler.messageBack.display();
            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;
            //Game.sound.playMusic();

            if (previousState == GAME_STATE_SOBRE){
                Game.aboutTextView.blockAndClearDisplay();
            }

            mainActivity.showAdView();
            tittle.display();
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MenuHandler.menuOptions.appearAndUnblock(500);

            if (mainActivity.isSignedIn()) {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(getContext().getResources().getString(R.string.deslogarGoogle));
            } else {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(getContext().getResources().getString(R.string.logarGoogle));
            }

        } else if (state == GAME_STATE_OPCOES_JOGABILIDADE){
            MessagesHandler.messageBack.display();
            MenuHandler.menuOptions.block();
            MenuHandler.menuOptions.clearDisplay();

            SelectorHandler.repositionSelectors(state);
            mainActivity.showAdView();
            tittle.display();
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MenuHandler.menuOptionsPlay.appearAndUnblock(500);


        } else if (state == GAME_STATE_OPCOES_GAME){

            SelectorHandler.repositionSelectors(state);
            mainActivity.showAdView();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.menuInGameOptions.appearAndUnblock(500);
            MessagesHandler.messageInGame.y = gameAreaResolutionY*0.15f;
            MessagesHandler.messageInGame.display();

        } else if (state == GAME_STATE_MENU_SAVE_FIRST_TIME){

            MenuHandler.menuMain.blockAndClearDisplay();
            MenuHandler.menuFirstSaveGame.appearAndUnblock(500);

            MessagesHandler.messageMenuSaveNotSeen.display();

        } else if (state == GAME_STATE_MENU_CARREGAR_JOGO){

            MenuHandler.menuMain.blockAndClearDisplay();
            MenuHandler.menuCarregar.appearAndUnblock(500);

            MessagesHandler.messageMenuCarregarJogo.display();

        } else if (state == GAME_STATE_MENU_PRINCIPAL){

            //mainActivity.getScreenShot();

            MessagesHandler.setBottomMessage("", 0);

            if (showMessageNotConnectedOnGoogle){
                showMessageNotConnectedOnGoogle = false;
                String message = Game.mainActivity.getApplicationContext().getResources().getString(R.string.nao_conectado_google);
                MessagesHandler.setBottomMessage(message, 2000);
            }

            Game.sound.stopAndReleaseMusic();

            if (Game.versaoBeta) {
                MessagesHandler.messageBeta.display();
            }

            //messages.showMessage("teste");

            GoogleAPI.loadAchievements();

            SaveGame.saveGame.save();

            StarsHandler.updateConqueredStars();

            if (!sameState) {
                if (previousState != GAME_STATE_OPCOES) {
                    showBlackFrameTransition(500);
                }
            }

            //if (Sound.loop != null) Sound.loop.stopAndRelease();

            SelectorHandler.repositionSelectors(state);
            initTittle();
            mainActivity.showAdView();
            Game.bordaB.setY(Game.resolutionY);

            MenuHandler.menuOptions.blockAndClearDisplay();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.groupMenu.blockAndClearDisplay();
            MenuHandler.levelMenu.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
            MenuHandler.menuPlay.blockAndClearDisplay();
            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();

            MessagesHandler.messageMenuSaveNotSeen.clearDisplay();
            MessagesHandler.messageMenuCarregarJogo.clearDisplay();
            if (MessagesHandler.messageTrainingState != null) MessagesHandler.messageTrainingState.clearDisplay();
            if (MessagesHandler.messageTrainingState2 != null) MessagesHandler.messageTrainingState2.clearDisplay();

            eraseAllGameEntities();
            eraseAllHudEntities();
            MenuHandler.menuMain.unblockAndDisplay();
            tittle.display();
            MessagesHandler.messageMaxScoreTotal.display();

            GoogleAPI.displayGoogleInfo();

            MessagesHandler.bottomTextBox.display();

            MessagesHandler.messageMaxScoreTotal.setText(
                    getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()));

        } else if (state == GAME_STATE_PREPARAR) {

            abdicateAngle = false;
            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;
            Sound.musicVolume = 1f;
            Sound.loadStaticGameAudioTracks();

            Stats.clearData();

            //Sound.loadMusic();

            if (tipTextBox != null) {
                tipTextBox.clearDisplay();
            }

            ParticleGenerator.loadParticleGenerators();

            eraseAllGameEntities();
            eraseAllHudEntities();

            if (Training.training) {
                LevelLoader.loadLevel(1);
            } else {
                LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
            }
            mainActivity.hideAdView();
            MessagesHandler.messageTime.cleanAnimations();

            if (!Training.training){
                MessagesHandler.messageTime.display();
                MessagesHandler.setMessageTime();
            } else {
                MessagesHandler.messageCurrentLevel.setText(getContext().getResources().getString(R.string.mensagem_treinamento));
            }

            MessagesHandler.messageCurrentLevel.display();

            if (Training.training) {
                Level.levelGoalsObject = new LevelGoals();
            }

            Level.levelGoalsObject.clearAchievements();


            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();
            TimeHandler.timeOfLevelPlay = 0;
            TimeHandler.secondsOfLevelPlay = 0;
            TimeHandler.lastSeconds = 0;
            mainActivity.hideAdView();
            if (!sameState) {
                showBlackFrameTransition(2500);
            }
            Level.levelObject.loadEntities();

            // cria a animação de preparação;
            ArrayList<float[]> values = new ArrayList<>();
            values.add(new float[]{0f, 6f});
            values.add(new float[]{0.14f, 5f});
            values.add(new float[]{0.28f, 4f});
            values.add(new float[]{0.4285f, 3f});
            values.add(new float[]{0.5714f, 2f});
            values.add(new float[]{0.7142f, 1f});
            values.add(new float[]{0.8571f, 0f});
            final Text innerMessagePreparation = MessagesHandler.messagePreparation;
            MessagesHandler.messagePreparation.setText("5");
            MessagesHandler.messagePreparation.setColor(Color.transparente);
            MessagesHandler.messagePreparation.display();
            //Sound.playCounter();
            //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
            //Sound.playCounter();

            Animation anim = new Animation(MessagesHandler.messagePreparation, "messagePreparation", "numberForAnimation", 7000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 4f) {
                        //Sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        MessagesHandler.messagePreparation.setColor(Color.transparente);
                        //innerMessagePreparation.setText("4");
                    } else if (innerMessagePreparation.numberForAnimation == 3f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        MessagesHandler.messagePreparation.display();
                        MessagesHandler.messagePreparation.setColor(Color.vermelhoCheio);
                        innerMessagePreparation.setText("3");
                    } else if (innerMessagePreparation.numberForAnimation == 2f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {

                        if (Training.training) {
                            innerMessagePreparation.setText(getContext().getResources().getString(R.string.mensagem_treinar));
                        } else {
                            innerMessagePreparation.setText(getContext().getResources().getString(R.string.mensagem_jogar));
                        }

                        if (paraGravacaoVideo) {
                            MessagesHandler.messagePreparation.setColor(Color.transparente);
                        }


                        Animation anim = Utils.createSimpleAnimation(innerMessagePreparation, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                innerMessagePreparation.clearDisplay();
                                innerMessagePreparation.alpha = 1f;
                                Game.setGameState(GAME_STATE_PRE_JOGAR);
                            }
                        });
                        anim.start();
                    }
                }
            });
            anim.start();
            checkIfDead();

        } else if (state == GAME_STATE_PREPARAR_TREINAMENTO) {

            mainActivity.hideAdView();
            if (!sameState) {
                showBlackFrameTransition(1500);
            }

            abdicateAngle = false;
            Sound.loadStaticGameAudioTracks();

            ParticleGenerator.loadParticleGenerators();

            eraseAllGameEntities();
            eraseAllHudEntities();

            LevelLoader.loadLevel(1);

            mainActivity.hideAdView();

            MessagesHandler.messageCurrentLevel.setText(getContext().getResources().getString(R.string.mensagem_treinamento));

            MessagesHandler.messageCurrentLevel.display();

            Level.levelGoalsObject = new LevelGoals();

            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();

            Level.levelObject.loadEntities();

            checkIfDead();

            stopAllGameEntities();

            reduceAllGameEntitiesAlpha(100);

            setGameState(GAME_STATE_MENU_DURANTE_TREINAMENTO);

        } else if (state == GAME_STATE_PRE_JOGAR){

            Game.sound.playMusic();
            updateNumberOfTargetsAlive();
            Game.notConnectedTextView.clearDisplay();
            Game.topFrame.clearDisplay();

            timeOfPrePlay = Utils.getTimeMilliPrecision();




        } else if (state == GAME_STATE_JOGAR){

            Game.bordaB.setY(Game.gameAreaResolutionY);

            for (int i = 0; i < Game.balls.size(); i++) {
                if (Game.balls.get(i).listenForExplosion){
                    Game.balls.get(i).replayAlarm();
                }
            }

            if (initPausedFlag){
                initPausedFlag = false;
                setGameState(GAME_STATE_PAUSE);
            } else {
                mainActivity.hideAdView();
                TimeHandler.resumeTimeOfLevelPlay();

                //if (SaveGame.saveGame.music) {
                    //if (Sound.loop != null) {
                    //    Sound.loop.play();
                    //}
                //}

                MessageStar.messageStars.reset();

                for (int i = 0; i < bars.size(); i++) {
                    if (bars.get(i).scaleVariationData != null) {
                        bars.get(i).initScaleVariation();
                    }
                }
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).scaleVariationData != null) {
                        obstacles.get(i).initScaleVariation();
                    }
                    if (obstacles.get(i).positionVariationData != null) {
                        obstacles.get(i).initPositionVariation();
                    }
                }
                resetTimeForPointsDecay();
                freeAllGameEntities();
            }

        } else if (state == GAME_STATE_DERROTA){

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            Sound.stopAndReleaseMusic();

            TimeHandler.stopTimeOfLevelPlay();
            Stats.tempoJogadoDerrota += TimeHandler.timeOfLevelPlay;
            Stats.saveData();


            Sound.soundPool.autoPause();

            mainActivity.showAdView();
            Game.sound.playGameOver();
            SaveGame.addLevelPlayed();
            int totalStars = 0;
            for (int i = 0; i < Level.levelObject.levelGoalsObject.levelGoals.size(); i++){
                if (Level.levelObject.levelGoalsObject.levelGoals.get(i).achieved){
                    totalStars += Level.levelObject.levelGoalsObject.levelGoals.get(i).numberOfStars;
                }
            }
            MessageStar.messageStars.showAndGoAllGray(totalStars);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            MenuHandler.menuGameOver.appearAndUnblock(1000);
            MessagesHandler.messageGameOver.display();
            if (ScoreHandler.scorePanel.value > 0) {
                ScoreHandler.scorePanel.showMessage("-50%", 1000);
                int points = ScoreHandler.scorePanel.value / 2;
                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                SaveGame.saveGame.setLevelPoints(SaveGame.saveGame.currentLevelNumber, points);
                ScoreHandler.setMaxScoreTotal();
                ScoreHandler.submitScores();
            }

            SaveGame.saveGame.save();
            
        } else if (state == GAME_STATE_PAUSE){

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            Sound.soundPool.autoPause();

            Sound.pauseMusic();

            mainActivity.showAdView();
            ButtonHandler.buttonReturnObjectivesPause.block();
            ButtonHandler.buttonReturnObjectivesPause.clearDisplay();
            TimeHandler.stopTimeOfLevelPlay();

            //Log.e("game", "ativando game_state_pause");
            if (previousState != GAME_STATE_OPCOES_GAME) {
                //if (Sound.loop != null) {
                //    Sound.loop.pause();
                //}

                //Sound.playPlayMenuBig();
                stopAllGameEntities();
                reduceAllGameEntitiesAlpha(300);
                MenuHandler.menuInGame.getMenuOptionByName("Continuar").textObject.setText(getContext().getResources().getString(R.string.continuarJogar));
                ArrayList<float[]> valuesAnimPause = new ArrayList<>();
                    valuesAnimPause.add(new float[]{0f, 1f});
                    valuesAnimPause.add(new float[]{0.25f, 2f});
                    valuesAnimPause.add(new float[]{0.7f, 3f});
                MessagesHandler.messageInGame.y = gameAreaResolutionY*0.15f;
                Animation anim = new Animation(MessagesHandler.messageInGame, "messageInGameColor", "numberForAnimation", 4000, valuesAnimPause, true, false);
                anim.setOnChangeNotFluid(new Animation.OnChange() {
                    @Override
                    public void onChange() {
                        if (MessagesHandler.messageInGame.numberForAnimation == 1f) {
                            MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                        } else if (MessagesHandler.messageInGame.numberForAnimation == 2f) {
                            MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                        } else if (MessagesHandler.messageInGame.numberForAnimation == 3f) {
                            MessagesHandler.messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                        }
                    }
                });
                anim.start();
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.pause));
                MessagesHandler.messageInGame.increaseAlpha(100, 1f);
                MessagesHandler.messageInGame.y = gameAreaResolutionY * 0.15f;

            }
            MessagesHandler.messageInGame.display();
            MenuHandler.menuInGame.appearAndUnblock(500);

        } else if (state == GAME_STATE_VITORIA){

            Game.bordaB.setY(Game.resolutionY);

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            //Sound.stopAndReleaseMusic();

            Sound.soundPool.autoPause();


            Level.levelObject.levelGoalsObject.setFinish(TimeHandler.stopTimeOfLevelPlay());
            SaveGame.addLevelPlayed();

            Animation anim = Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, 0f, -resolutionX*2f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    String previousText = MessagesHandler.messageTime.text;
                    MessagesHandler.messageTime = new Text("messageTime",
                            Game.resolutionX*0.01f, Game.resolutionY*0.93f, Game.resolutionY*0.04f,getContext().getResources().getString(R.string.tempo_gasto) + " " + previousText, Game.font, new Color(0.1f, 0.1f, 0.1f, 1f));
                    Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, -resolutionX, 0f).start();
                }
            });
            anim.start();

            MessagesHandler.messageCurrentLevel.reduceAlphaAndClearDisplay(500);

            // TODO o que fazer com a animação quando for pausado

            Game.sound.playWin1();
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);

            if (ButtonHandler.button1Left != null) {
                Utils.createSimpleAnimation(ButtonHandler.button1Left, "alphaVitoria", "alpha", 1000, ButtonHandler.button1Left.alpha, 0f).start();
                ButtonHandler.button1Left.block();
            }
            if (ButtonHandler.button2Right != null) {
                Utils.createSimpleAnimation(ButtonHandler.button2Right, "alphaVitoria", "alpha", 1000, ButtonHandler.button2Right.alpha, 0f).start();
                ButtonHandler.button2Right.block();
            }
            if (ButtonHandler.button2Left != null) {
                Utils.createSimpleAnimation(ButtonHandler.button2Left, "alphaVitoria", "alpha", 1000, ButtonHandler.button2Left.alpha, 0f).start();
                ButtonHandler.button2Left.block();
                Utils.createSimpleAnimation(ButtonHandler.button1Right, "alphaVitoria", "alpha", 1000, ButtonHandler.button1Right.alpha, 0f).start();
                ButtonHandler.button1Right.block();
            }

            Utils.createSimpleAnimation(ballDataPanel, "alphaVitoria", "alpha", 1000, ballDataPanel.alpha, 0f).start();

            MessagesHandler.messageInGame.y = gameAreaResolutionY*0.05f;

            Animation anim2 = Utils.createAnimation4v(MessagesHandler.messageInGame, "messageInGameColor", "numberForAnimation", 3000, 0f, 1f, 0.2f, 2f, 0.5f, 3f, 0.7f, 4f, true, false);
            anim2.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (MessagesHandler.messageInGame.numberForAnimation == 1f){
                        MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 2f) {
                        MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 3f) {
                        MessagesHandler.messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 4f) {
                        MessagesHandler.messageInGame.setColor(new Color(1f, 0f, 0f, 1f));
                    }
                }
            });
            anim2.start();

            StarsHandler.previousStars = SaveGame.getLevelStars(SaveGame.saveGame.currentLevelNumber);
            StarsHandler.newStars = Level.levelGoalsObject.getStarsAchieved();

            // calcula a pontuação final, de acordo com a quantidade de bolas azuis e estrelas
            int pointsTotal = ScoreHandler.scorePanel.value;
            for (int i = 0; i < ballGoalsPanel.blueBalls; i++) {
                pointsTotal *= 1.5;
            }
            pointsTotal *= 1f + (0.1f * (float) StarsHandler.newStars);

            // salva os dados dos pontos e estrelas
            
            SaveGame.setLevelPoints(SaveGame.saveGame.currentLevelNumber, pointsTotal);
            SaveGame.setLevelStars(SaveGame.saveGame.currentLevelNumber, StarsHandler.newStars);
            SaveGame.saveGame.save();
            ScoreHandler.setMaxScoreTotal();
            ScoreHandler.submitScores();

            // verifica a quantidade de bolas azuis, e atualiza a pontuação
            Timer timer = new Timer();

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (ballGoalsPanel.blueBalls > 0){
                                int points = (int)((float) ScoreHandler.scorePanel.value * 1.5f);
                                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                                ballGoalsPanel.explodeBlueBall();
                    } else if (!levelGoalsPanel.isVisible){
                        Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateX", 2000, 0f, gameAreaResolutionX*2f).start();
                        ballGoalsPanel.clearExplosions();
                        levelGoalsPanel.appearGray(true);
                    } else if (levelGoalsPanel.gray) {
                        levelGoalsPanel.shineLines(true);

                        if (StarsHandler.newStars > 0) {
                            int points = (int) ((float) ScoreHandler.scorePanel.value * (1f + (0.1f * (float) StarsHandler.newStars)));
                            ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                            if (StarsHandler.newStars == 1) messageForScore = "+ 10%";
                            if (StarsHandler.newStars == 2) messageForScore = "+ 20%";
                            if (StarsHandler.newStars == 3) messageForScore = "+ 30%";
                            if (StarsHandler.newStars == 4) messageForScore = "+ 40%";
                            if (StarsHandler.newStars == 5) messageForScore = "+ 50%";
                            settingMessageForScore = true;
                        }
                    } else{
                        ButtonHandler.buttonContinue.display();
                        ButtonHandler.buttonContinue.unblock();
                        MessagesHandler.messageContinue.display();
                        MessagesHandler.messageContinue.setColor(new Color(0f, 0f, 0f, 1f));
                        cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 2250, 2250);

            ArrayList<float[]> valuesAnimVitoriaTranslate = new ArrayList<>();
            valuesAnimVitoriaTranslate.add(new float[]{0f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{0.8f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{1f,0f});
            new Animation(MessagesHandler.messageInGame, "messageInGameTranslateX", "translateX", 2000, valuesAnimVitoriaTranslate, false, true).start();

            MessagesHandler.messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Game.sound.playTextBoxAppear();
                }
            });


            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1) + " " + String.valueOf(SaveGame.saveGame.currentLevelNumber) + " " + getContext().getResources().getString(R.string.nivelConcluido2));
            } else {
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1) + " " + getContext().getResources().getString(R.string.nivelConcluido2));
            }
            MessagesHandler.messageInGame.display();

            Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY*0.1f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleX", "scaleX", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleY", "scaleY", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleX", "scaleX", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleY", "scaleY", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY * 0.05f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                float iY = - Game.gameAreaResolutionY * 0.05f;
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTX", "translateX", 30000, 0f, 0f, 0.3f, -10f, 0.7f, 20f, 1f, 0f, true, true).start();
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTY", "translateY", 12000, 0f,iY, 0.2f,iY + 5f, 0.7f,iY -20f, 1f,iY, true, true).start();
                }
                }
            ).start();
        } else if (state == GAME_STATE_VITORIA_COMPLEMENTACAO) {

            clearAllGameEntities();

            Game.sound.playWin2();

            ButtonHandler.buttonContinue.clearDisplay();
            ButtonHandler.buttonContinue.block();

            ballGoalsPanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);

            MessagesHandler.messageTime.reduceAlphaAndClearDisplay(500);
            
            if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.clearDisplay();
            if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.clearDisplay();
            if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.clearDisplay();
            if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.clearDisplay();
           
            MessagesHandler.messageInGame.display();

            groupsUnblocked.clear();

            final int starsDiference = StarsHandler.newStars - StarsHandler.previousStars;

            int newStarsTotal = StarsHandler.conqueredStarsTotal + (StarsHandler.newStars - StarsHandler.previousStars);

            //Log.e(TAG, "StarsHandler.newStars "+StarsHandler.newStars );
            //Log.e(TAG, "StarsHandler.previousStars "+StarsHandler.previousStars );
            //Log.e(TAG, "StarsHandler.conqueredStarsTotal "+StarsHandler.conqueredStarsTotal );
            //Log.e(TAG, "starsDiference "+starsDiference );
            //Log.e(TAG, "newStarsTotal "+newStarsTotal );


            if (starsDiference > 0) {
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mnima), starsDiference);
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mdia), starsDiference);
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mxima), starsDiference);
            }

            if (StarsHandler.newStars > StarsHandler.previousStars){
                float groupsUnblockedSize = resolutionX * 0.16f;
                float groupsUnblockedPadd = resolutionX * 0.02f;

                for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){
                   final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);
                   if (lgd.starsToUnlock >= StarsHandler.conqueredStarsTotal && lgd.starsToUnlock <= newStarsTotal && i != 0){
                           groupsUnblocked.add(
                               new Image("groupsUnblocked"+i, 0f,
                               resolutionY * 0.7f,
                               groupsUnblockedSize, groupsUnblockedSize,
                               lgd.textureUnit,
                               lgd.textureData)
                           );
                   }
                }

                int numberOfGroupsUnblocked = groupsUnblocked.size();
                if (numberOfGroupsUnblocked > 0){
                    
                    MessagesHandler.messageGroupsUnblocked.display();
                    Utils.createSimpleAnimation(MessagesHandler.messageGroupsUnblocked, "translateX", "translateX", 500, -gameAreaResolutionX*1.5f, 0f).start();
                    Game.sound.playTextBoxAppear();


                    float initX = (resolutionX * 0.5f) - (((numberOfGroupsUnblocked * groupsUnblockedSize) + ((numberOfGroupsUnblocked-1)*groupsUnblockedPadd))/2f);

                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        groupsUnblocked.get(i).x = initX + (i * groupsUnblockedPadd) + (i * groupsUnblockedSize);
                    }


                    float halfDifference = ((groupsUnblockedSize * 1.2f) - groupsUnblockedSize)/2f;


                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        Image gu = groupsUnblocked.get(i);
                        gu.display();
                        //Utils.createAnimation5v(gu, "translateX", "translateX", 800, 0f, groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, -halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        //Utils.createAnimation5v(gu, "translateY", "translateY", 800, 0f, -groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleX", "scaleX", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleY", "scaleY", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                    }
                }
            }

            if (groupsUnblocked.size() > 0) {
                currentLevelIcon.y = Game.resolutionY * 0.2f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.62f);
            } else {
                currentLevelIcon.y = Game.resolutionY * 0.3f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.76f);

            }
            currentLevelIcon.display();

            MessageStarWin.messageStarsWin.show(StarsHandler.newStars, StarsHandler.newStars - StarsHandler.previousStars, true);

            for (int i = 0; i < MessageStarWin.messageStarsWin.stars.size(); i++) {
                Utils.createAnimation5v(MessageStarWin.messageStarsWin.stars.get(i), "a"+i, "animTranslateY",
                        3000,
                        0f, 0f,
                        0.2f + (0.03f * i), 0f,
                        0.22f + (0.03f * i), -Game.resolutionY * 0.01f,
                        0.3f + (0.03f * i), 0f,
                        1f, 0f,
                        true, true
                ).start();
            }

            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(800, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(800, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();

            ArrayList<float[]> valuesAnim = new ArrayList<>();
            valuesAnim.add(new float[]{0f,0f});
            valuesAnim.add(new float[]{0.5f,1f});
            valuesAnim.add(new float[]{0.57f,2f});
            valuesAnim.add(new float[]{0.64f,3f});
            valuesAnim.add(new float[]{0.71f,4f});
            valuesAnim.add(new float[]{0.78f,5f});
            valuesAnim.add(new float[]{1f,6f});


            //Log.e(TAG, "StarsHandler.newStars "+StarsHandler.newStars );
            //Log.e(TAG, "StarsHandler.previousStars "+StarsHandler.previousStars );
            //Log.e(TAG, "StarsHandler.conqueredStarsTotal "+StarsHandler.conqueredStarsTotal );
            //Log.e(TAG, "starsDiference "+starsDiference );
            //Log.e(TAG, "newStarsTotal "+newStarsTotal );

            Animation animMessageConqueredStarsTotal = new Animation(MessagesHandler.messageConqueredStarsTotal, "numberForAnimation", "numberForAnimation", 3000, valuesAnim, false, false);
            animMessageConqueredStarsTotal.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 1f){
                        if (starsDiference > 0){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 1));
                            Game.sound.playStarsUp();

                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 2f) {
                        if (starsDiference > 1){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 2));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 3f) {
                        if (starsDiference > 2){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 3));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 4f) {
                        if (starsDiference > 3){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 4));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 5f) {
                        if (starsDiference > 4){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 5));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    }
                }
            });

            animMessageConqueredStarsTotal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    ButtonHandler.buttonContinue.display();
                    ButtonHandler.buttonContinue.unblock();
                    MessagesHandler.messageContinue.display();
                    MessagesHandler.messageContinue.setColor(new Color(0f, 0f, 0f, 1f));
                    StarsHandler.updateConqueredStars();
                }
            });
            animMessageConqueredStarsTotal.start();

        } else if (state == GAME_STATE_TUTORIAL) {

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");
            MessagesHandler.messageMenu.clearDisplay();
            MessagesHandler.messageSubMenu.clearDisplay();
            MenuHandler.tutorialMenu.clearDisplay();
            MenuHandler.tutorialMenu.block();
            if (!sameState) {
                showBlackFrameTransition(500);
            }
            mainActivity.hideAdView();
            Tutorial.loadTutorial();
            Tutorial.currentTutorialObject.showFirst();
            
        }
    }

    private static void freeAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = true;
            if (b.ballParticleGenerator != null) {
                b.ballParticleGenerator.isActive = true;
            }
        }
        
        for (Ball b : fakeBalls){
            b.isMovable = true;
            if (b.ballParticleGenerator != null) {
                b.ballParticleGenerator.isActive = true;
            }
        }
        
        for (Bar b : bars){
            b.isMovable = true;
        }

        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).scaleVariationData != null){
                obstacles.get(i).initScaleVariation();
            }
            if (obstacles.get(i).positionVariationData != null){
                obstacles.get(i).initPositionVariation();
            }
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).isActive = true;
        }

        if (wind != null){
            wind.init();
        }

    }

    public static void stopAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = false;
            b.clearParticles();
        }
        
        for (Ball b : fakeBalls){
            b.isMovable = false;
            b.clearParticles();
        }
        
        for (Bar b : bars){
            b.isMovable = false;
            if (b.scaleVariationData != null){
              b.stopScaleVariation();
            }
        }

        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).scaleVariationData != null){
                obstacles.get(i).stopScaleVariation();
            }
            if (obstacles.get(i).positionVariationData != null){
                obstacles.get(i).stopPositionVariation();
            }
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).isActive = false;
        }

        if (wind != null){
            wind.stop();
        }
    }

    public static void increaseAllGameEntitiesAlpha(int duration){
        for (Entity e : collectAllGameEntities()){
            e.increaseAlpha(duration, 1f);
        }
        if (brickBackground != null){
            brickBackground.increaseAlpha(duration, 1f);
        }

    }

    public static void reduceAllGameEntitiesAlpha(int duration){

        //Log.e(TAG, "reduceAllGameEntitiesAlpha");

        for (Entity e : collectAllGameEntities()){
            e.reduceAlpha(duration, 0.2f);
        }

    }

    public static void eraseAllGameEntities() {
        balls.clear();
        fakeBalls.clear();
        bars.clear();
        targets.clear();
        windows.clear();
        specialBalls.clear();
        obstacles.clear();
        ballCollisionStars.clear();
        targetGroup = null;
        pointsGroup = null;

        wind = null;
    }

    public static void eraseAllHudEntities() {
        ballGoalsPanel = null;
        ballDataPanel = null;
        ScoreHandler.scorePanel = null;
        ButtonHandler.button1Left = null;
        ButtonHandler.button1Right = null;
        ButtonHandler.button2Left = null;
        ButtonHandler.button2Right = null;

        brickBackground = null;
        MessagesHandler.messageTime.clearDisplay();
        MessagesHandler.messageCurrentLevel.clearDisplay();
    }

    public static ArrayList<Entity> collectAllHudEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(ButtonHandler.button1Left);
        list.add(ButtonHandler.button1Right);
        list.add(ButtonHandler.button2Left);
        list.add(ButtonHandler.button2Right);
        list.add(ScoreHandler.scorePanel);
        list.add(ballDataPanel);
        list.add(ballGoalsPanel);
        list.add(MessagesHandler.messageTime);
        list.add(MessagesHandler.messageCurrentLevel);
        return list;
    }

    static ArrayList<Entity> collectAllGameEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.addAll(balls);
        list.addAll(fakeBalls);
        list.addAll(bars);
        list.addAll(targets);
        list.addAll(obstacles);
        list.addAll(windows);
        list.addAll(specialBalls);
        list.addAll(ballCollisionStars);
        if (wind != null) {
            list.add(wind);
        }
        return list;
    }

    public static void clearAllMenuEntities(){
        ArrayList<Entity> list = collectAllMenuEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }

        for(Entity e : list){
            if (e != null) {
                e.block();
            }
        }
    }

    static void clearAllHudEntities(){
        ArrayList<Entity> list = collectAllHudEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    static void clearAllGameEntities(){
        ArrayList<Entity> list = collectAllGameEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    // Get a MemoryInfo object for the device's current memory status.
    static private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) mainActivity.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    public static long initSimulateTime;
    public static ArrayList<Long> frameSimulateDurations1;
    public static ArrayList<Long> frameSimulateDurations2;

    static boolean leftTouch = false;
    static boolean rightTouch = false;

    static long [] timesOfCollisionCheck = new long[50];
    static int lastCollisiontDebugCheck = 0;
    static long timeInitCheckCollision;



    static boolean atrasarPasso = false;
    static int simulatePasso = 0;

    static void simulate(long elapsed, float frameDuration){

        simulatePasso += 1;

        if (atrasarPasso) {
            if (simulatePasso < 10) {
                return;
            } else {
                simulatePasso = 0;
            }
        }


        nanoPrecisionElapsed = ((float)elapsed/1000000f);

        //Log.e(TAG, "elapsed " + elapsed);
        //Log.e(TAG, "nanoPrecisionElapsed " + nanoPrecisionElapsed);
        //Log.e(TAG, "frameDuration " + frameDuration);

        if (gameState == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){
            if (Utils.getTimeMilliPrecision() - Training.trainingBarCollisionInit > 4200){
                Training.trainingBarCollisionInit  = Long.MAX_VALUE;
                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                }
                setGameState(GAME_STATE_JOGAR);



            }
        }


        if (gameState == GAME_STATE_MENU_PRINCIPAL && GoogleAPI.playerIconImage == null && GoogleAPI.playerIcon != null){
            mainActivity.updatePlayerInfo();
        }

        if (gameState == GAME_STATE_PRE_JOGAR) {
            if (Utils.getTimeMilliPrecision() - timeOfPrePlay > 500){
                timeOfPrePlay = 0;
                for (int i = 0; i < balls.size(); i++) {
                    balls.get(i).initTempoVelocidadeMedia = 0;
                    balls.get(i).initTempoAnguloMedio = 0;
                }
                setGameState(GAME_STATE_JOGAR);
            }
        }

        if (gameState != GAME_STATE_VITORIA) {
            TimeHandler.updateTimeOfLevelPlay(elapsedMiliTimeSinceLastFrame);
        }

        //Log.e(TAG, "StarsHandler.conqueredStarsTotal "+StarsHandler.conqueredStarsTotal );

        if (ButtonHandler.buttonReturn != null){
            //Log.e(TAG, "buttonReturn ");
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.x);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.y);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.positionX);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.positionY);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.accumulatedTranslateX);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.accumulatedTranslateY);
            //Log.e(TAG, " " + ButtonHandler.buttonReturn.isVisible);
        }

        // Before doing something that requires a lot of memory,
        // check to see whether the device is in a low memory state.
        //ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();

        //if (memoryInfo.lowMemory) {
        //    //Log.e(TAG, "lowMemory");
        //}


        // atualiza posição da bola
        if (gameState == GAME_STATE_JOGAR) {

             for (int i = 0; i < balls.size(); i++) {
                 if (balls.get(i).isAlive) {
                     //Log.e(TAG, "                  textureMap "+ balls.get(i).textureMap);
                     //Log.e(TAG, "                  positionX "+ (balls.get(i).positionX));
                     //Log.e(TAG, "                  positionY "+ (balls.get(i).positionY));
                     //Log.e(TAG, "                  dvx "+ (balls.get(i).dvx));
                     //Log.e(TAG, "                  dvy "+ (balls.get(i).dvy));
                     //Log.e(TAG, "                  moveAngle " + Math.toDegrees(Math.atan2(balls.get(i).dvy, balls.get(i).dvx)));


                     Ball ball = balls.get(i);
                     ball.verifyAcceleration();
                     ball.vx = (ball.dvx * (float) nanoPrecisionElapsed) / frameDuration;
                     ball.vy = (ball.dvy * (float) nanoPrecisionElapsed) / frameDuration;

                     ball.translate(ball.vx, ball.vy);

                     ball.verifyWind();
                     //Log.e("game", "ballv "+ ball.vx+" "+ball.vy);
                 }
            }
            
            for (int i = 0; i < fakeBalls.size(); i++) {
                 if (fakeBalls.get(i).isAlive) {
                     
                     Ball ball = fakeBalls.get(i);
                     ball.verifyAcceleration();
                     ball.vx = (ball.dvx * (float) nanoPrecisionElapsed) / frameDuration;
                     ball.vy = (ball.dvy * (float) nanoPrecisionElapsed) / frameDuration;

                     ball.translate(ball.vx, ball.vy);

                     ball.verifyWind();
                 }
            }

            for (int i = 0; i < specialBalls.size(); i++) {
                SpecialBall specialBall = specialBalls.get(i);
                specialBall.vx = (specialBall.dvx * (float) nanoPrecisionElapsed) / frameDuration;
                specialBall.vy = (specialBall.dvy * (float) nanoPrecisionElapsed) / frameDuration;

                //Log.e("game", "specialBall "+ specialBall.vx+" "+specialBall.vy);
                specialBall.translate(specialBall.vx, specialBall.vy);
            }
        }

        // atualiza posição da barra
        if (gameState == GAME_STATE_JOGAR) {
                float timePercentage = (float) nanoPrecisionElapsed / frameDuration;
            if (bars != null) {
                if (bars.size() == 1) {
                    if (ButtonHandler.button1Left.isPressed && !ButtonHandler.button2Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (ButtonHandler.button2Right.isPressed && !ButtonHandler.button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                        //bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).stop((long)nanoPrecisionElapsed);
                    }
                }
                if (bars.size() == 2) {
                    if (ButtonHandler.button1Left.isPressed && !ButtonHandler.button1Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (ButtonHandler.button1Right.isPressed && !ButtonHandler.button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                    } else {
                        bars.get(0).stop((long)nanoPrecisionElapsed);
                    }

                    if (ButtonHandler.button2Left.isPressed) {
                        bars.get(1).moveLeft(timePercentage);
                    } else if (ButtonHandler.button2Right.isPressed) {
                        bars.get(1).moveRight(timePercentage);
                    } else {
                        bars.get(1).stop((long)nanoPrecisionElapsed);
                    }
                }
            }

            // verifica se a bola especial tocou a barra
            for (int i = 0; i < specialBalls.size(); i++) {
                specialBalls.get(i).verifyBars();
            }

            for (int i = 0; i < specialBalls.size(); i++) {
                if (specialBalls.get(i).isDead){
                    specialBalls.remove(i);
                }
            }
        }



        // atualiza os dados da entidade, aplicando todas as transformações setadas
        checkTransformations();


        //initSimulateTime = Utils.getTimeMilliPrecision();

        if (gameState == GAME_STATE_JOGAR) {

            // atualiza posição das windows
            for (int i = 0; i < windows.size(); i++){
                if (windows.get(i).isActive){
                    windows.get(i).vx = (windows.get(i).dvx * (float) nanoPrecisionElapsed) / frameDuration;
                    windows.get(i).move();
                }
            }

            // insere as entidades no quadtree
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isAlive) {
                    quad.insert(balls.get(i));
                    balls.get(i).clearCollisionData();
                }
            }
            
            // insere as entidades no quadtree
            for (int i = 0; i < fakeBalls.size(); i++) {
                if (fakeBalls.get(i).isAlive) {
                    quad.insert(fakeBalls.get(i));
                    fakeBalls.get(i).clearCollisionData();
                }
            }

            for (int i = 0; i < bars.size(); i++) {
                quad.insert(bars.get(i));
                bars.get(i).clearCollisionData();
            }

            for (int i = 0; i < targets.size(); i++) {
                quad.insert(targets.get(i));
                targets.get(i).clearCollisionData();
            }

            for (int i = 0; i < obstacles.size(); i++) {
                quad.insert(obstacles.get(i));
                obstacles.get(i).clearCollisionData();
            }

            quad.insert(bordaE);
            quad.insert(bordaD);
            quad.insert(bordaC);
            quad.insert(bordaB);
        }

        // verifica a colisão da bola
        if (gameState == GAME_STATE_JOGAR) {
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).listenForExplosion) {
                    //Log.e(TAG, "verificando explosão da bola "+i);
                    if ((int) (Utils.getTimeMilliPrecision() - balls.get(i).initialTimeWaitingExplosion) > balls.get(i).timeForExplode) {

                        balls.get(i).radius *= 4;
                        ArrayList<PhysicalObject> ball = new ArrayList<>();
                        ball.add(balls.get(i));
                        boolean collision = Collision.checkCollision(ball, quad, 0, false, false, false, false);
                        balls.get(i).radius /= 4;
                        if (!collision){
                            balls.get(i).explode();
                        }
                        balls.get(i).clearCollisionData();
                    }
                }
            }

        }

        timeInitCheckCollision = 0;


        if (logDeTempoDeDuracaoDaChecagemDeColisao) {
            timeInitCheckCollision = System.nanoTime();
        }

        // verifica as demais colisões
        if (gameState == GAME_STATE_JOGAR) {
            for (int i = 0; i < 1; i++) {

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------bars, quad, Game.BORDA_WEIGHT");
                Collision.checkCollision(bars, quad, Game.BORDA_WEIGHT, true, true, false, true);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------bars, quad, Game.BAR_WEIGHT");
                Collision.checkCollision(bars, quad, Game.BAR_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------bars, quad, Game.OBSTACLES_WEIGHT");
                Collision.checkCollision(bars, quad, Game.OBSTACLES_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------obstacles, quad, Game.BORDA_WEIGHT");
                Collision.checkCollision(obstacles, quad, Game.BORDA_WEIGHT, true, true, true, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------obstacles, quad, Game.BAR_WEIGHT");
                Collision.checkCollision(obstacles, quad, Game.BAR_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------obstacles, quad, Game.OBSTACLES_WEIGHT");
                Collision.checkCollision(obstacles, quad, Game.OBSTACLES_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------balls, quad, Game.BORDA_WEIGHT");
                Collision.checkCollision(balls, quad, Game.BORDA_WEIGHT, true, true, true, true);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------balls, quad, Game.BAR_WEIGHT");
                Collision.checkCollision(balls, quad, Game.BAR_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------balls, quad, Game.OBSTACLES_WEIGHT");
                Collision.checkCollision(balls, quad, Game.OBSTACLES_WEIGHT, true, true, true, true);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------balls, quad, Game.BALL_WEIGHT");
                Collision.checkCollision(balls, quad, Game.BALL_WEIGHT, true, true, true, true);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------fakeBalls, quad, Game.BORDA_WEIGHT");
                Collision.checkCollision(fakeBalls, quad, Game.BORDA_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------fakeBalls, quad, Game.BAR_WEIGHT");
                Collision.checkCollision(fakeBalls, quad, Game.BAR_WEIGHT, true, true, false, false);

                if (logCollisionEscape)Log.e(TAG, "init CheckCollisiont --------------------------------------------------fakeBalls, quad, Game.OBSTACLES_WEIGHT");
                Collision.checkCollision(fakeBalls, quad, Game.OBSTACLES_WEIGHT, true, true, false, false);

            }

            if (logDeTempoDeDuracaoDaChecagemDeColisao) {
                timesOfCollisionCheck[lastCollisiontDebugCheck] = System.nanoTime() - timeInitCheckCollision;
                if (lastCollisiontDebugCheck == 49){
                    long soma = 0;
                    for (int i = 0; i < timesOfCollisionCheck.length; i++) {
                        soma += timesOfCollisionCheck[i];
                    }
                    Log.e(TAG, "checkCollisionTime " + (soma / (long)50));
                    lastCollisiontDebugCheck = 0;
                } else {
                    lastCollisiontDebugCheck += 1;
                }
            }


            quad.clear();
        }


        /*
        for (int i = 0; i < bars.size(); i++){
            if (bars.get(i).collisionsData.size() == 0){

                //Log.e(TAG, "secret 1 step: "+bars.get(i).secretLevel1Steps);

                bars.get(i).secretLevel2LockStep = false;
            }
        }
        */

        // se a bola colidiu, faz o necessário
        if (gameState == GAME_STATE_JOGAR) {

            // tomas as medidas no caso de colisão das bolas falsas
            for (int i = 0; i < fakeBalls.size(); i++) {
                if (fakeBalls.get(i).isAlive) {
                    if (fakeBalls.get(i).isCollided) {
                        fakeBalls.get(i).onCollision();
                    }

                }
            }

            // tomas as medidas no caso de colisão das bolas
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isAlive) {
                    balls.get(i).checkQuarentineBall();
                    if (balls.get(i).isCollided) {
                        balls.get(i).onCollision();
                    }
                }
            }

            // tomas as medidas no caso de colisão das barras




            leftTouch = false;
            rightTouch = false;
            for (int i = 0; i < bars.size(); i++) {
                if (bars.get(i).positionX - (Game.bordaE.positionX + Game.bordaE.width) < 0.1f){
                    //Log.e(TAG, "left touch");
                    leftTouch = true;

                }
            }

            for (int i = 0; i < bars.size(); i++) {
                if (Game.bordaD.positionX - (bars.get(i).positionX + bars.get(i).getTransformedWidth()) < 0.1f){
                    //Log.e(TAG, "right touch");
                    rightTouch = true;
                }
            }

            if (leftTouch){
                Level.levelObject.levelGoalsObject.notifyLeftBorderTouch((long)nanoPrecisionElapsed);
            } else {
                Level.levelObject.levelGoalsObject.notifyNotLeftBorderTouch();
            }

            if (rightTouch){
                Level.levelObject.levelGoalsObject.notifyRightBorderTouch((long)nanoPrecisionElapsed);
            } else {
                Level.levelObject.levelGoalsObject.notifyNotRightBorderTouch();
            }

        }

        // toma as medidas finais
        // move o back
        // verifica se morreu
        // verifica se está vivo
        // verifica se o score deve ser reduzido
        
        
        if (gameState == GAME_STATE_JOGAR) {
            //if (background != null){
            //    background.move(1);
            //}
            //if (brickBackground != null){
            //    
            //}

            checkIfDead();
            ScoreHandler.checkIfScoreHasToDecay();
            verifyTargetsAppend();
            if (brickBackground != null){
                brickBackground.changeDrawInfo();
                brickBackground.move();

            }
        } else if(gameState == GAME_STATE_VITORIA || gameState == GAME_STATE_VITORIA_COMPLEMENTACAO){
            if (brickBackground != null){
                brickBackground.changeDrawInfo();
                brickBackground.move();
            }
            //brickBackground.animate();
            //if (background != null){
            //    background.move(3);
            //}
        }
       
        
        
        if (gameState == GAME_STATE_JOGAR) {
            checkIfWinLevel();
            verifyBallBehaviourData();
        }

        if (Quadtree.nodePool != null){
            //Log.e(TAG, "Debug Data BEFORE reset: " + Quadtree.nodePool.debug());
            // reset the vectorPool, it is considered a temporary pool, all objects will become reusable
            Quadtree.nodePool.reset();
        }

        if (Quadtree.rectangleMPool != null){
            Quadtree.rectangleMPool.reset();
        }


        if (vectorPool != null){
            //Log.e(TAG, "Debug Data BEFORE reset: " + vectorPool.debug());
            // reset the vectorPool, it is considered a temporary pool, all objects will become reusable
            vectorPool.reset();
        }



        //processSimulateDurationTest(1);




    }


    static void processSimulateDurationTest(int number){

        if (number == 1) {
            if (frameSimulateDurations1 == null) {
                frameSimulateDurations1 = new ArrayList<>();
            }
            frameSimulateDurations1.add(Utils.getTimeMilliPrecision() - initSimulateTime);
            if (frameSimulateDurations1.size() == 30) {
                long soma = 0;
                for (int i = 0; i < frameSimulateDurations1.size(); i++) {
                    soma += frameSimulateDurations1.get(i);
                }
                //Log.e(TAG, " simulate duration 1 : " + (soma / frameSimulateDurations1.size()));
                frameSimulateDurations1.clear();
            }
        } else if (number == 2){
            if (frameSimulateDurations2 == null) {
                frameSimulateDurations2 = new ArrayList<>();
            }
            frameSimulateDurations2.add(Utils.getTimeMilliPrecision() - initSimulateTime);
            if (frameSimulateDurations2.size() == 30) {
                long soma = 0;
                for (int i = 0; i < frameSimulateDurations2.size(); i++) {
                    soma += frameSimulateDurations2.get(i);
                }
                //Log.e(TAG, " simulate duration 2 : " + (soma / frameSimulateDurations2.size()));
                frameSimulateDurations2.clear();
            }
        }
    }

    static void verifyBallBehaviourData(){
        for (int i = 0; i < balls.size(); i ++){
            if (balls.get(i).ballBehaviourData != null && balls.get(i).ballBehaviourData.active &&
                    (Utils.getTimeMilliPrecision() - balls.get(i).ballBehaviourData.timeOfCollision > TIME_OF_BALL_LISTENER * 1.2f))
                    balls.get(i).ballBehaviourData.processData();
            }
    }

    static void verifyTargetsAppend(){
        for (int b = 0; b < balls.size(); b++){
            Ball ball = balls.get(b);
            if (!ball.isFree){
                if (ball.targetsAppend != null) {
                    for (int ia = 0; ia < ball.targetsAppend.size(); ia++) {
                        if (!ball.targetsAppend.get(ia).alive) {
                            ball.isFree = true;
                            return;
                        }
                    }
                }
            }
        }
    }

    static void resetTimeForPointsDecay(){
        initialTimePointsDecay = Utils.getTimeMilliPrecision();
    }


    static int numberOfTargets = -1;
    static int numberOfTargetsAlives = -1;


    static void updateNumberOfTargetsAlive(){
        numberOfTargetsAlives = 0;
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i).alive) {
                numberOfTargetsAlives += 1;
            }
        }

        if (numberOfTargetsAlives == 1 && gameState == GAME_STATE_JOGAR){
            if (SaveGame.saveGame.currentLevelNumber < 8) {
                Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelMessageLastTarget1));
            }
            ButtonHandler.buttonFinalTargetLeft.unblockAndDisplay();
            ButtonHandler.buttonFinalTargetRight.unblockAndDisplay();
        }
    }


    static boolean resultadoTreinamentoAnotado = false;


    static void checkIfWinLevel() {
        if (Training.training){
            if (Utils.getTimeMilliPrecision() - Training.trainingBarCollisionInit > 3000){
                Training.checkTrainingFinished();
            } else if (Utils.getTimeMilliPrecision() - Training.trainingBarCollisionInit > 800 && !resultadoTreinamentoAnotado){
                resultadoTreinamentoAnotado = true;
                if (!Training.treinamentoSucesso){
                    MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.errou));
                    MessagesHandler.messageTrainingState.setColor(Color.vermelhoCheio);
                } else {
                    MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.sucesso));
                    MessagesHandler.messageTrainingState.setColor(Color.verde40);
                }
                MessagesHandler.messageTrainingState.alpha = 0.2f;
                MessagesHandler.messageTrainingState.display();
                Utils.createAnimation3v(MessagesHandler.messageTrainingState, "alpha","alpha", 500, 0f, 0.2f, 0.5f, 1f, 1f, 0.2f, true, true).start();
            }
            return;
        }








        boolean win = true;
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i).alive){
                win = false;
                break;
            }
        }
        if (win) {
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).listenForExplosion) {
                    win = false;
                    break;
                }
            }
        }

        // for debug
        if (Game.ganharComMetadeDosAlvos) {
            if (numberOfTargetsAlives < numberOfTargets / 2) {
                win = true;
            }
        }

        if (win) {

            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isAlive){
                    Stats.collectBallData(balls.get(i));
                }
            }

            switch (Stats.ultimoNumeroBolasVivas){
                case 1:
                    Stats.tempo1Bola += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 2:
                    Stats.tempo2Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 3:
                    Stats.tempo3Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 4:
                    Stats.tempo4Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 5:
                    Stats.tempo5Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 6:
                    Stats.tempo6Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 7:
                    Stats.tempo7Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 8:
                    Stats.tempo8Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 9:
                    Stats.tempo9Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
            }

            setGameState(GAME_STATE_VITORIA);


        } else {
            int ballsAlive = 0;
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isAlive && !balls.get(i).isInvencible){
                    ballsAlive += 1;
                }
            }

            //Log.e(TAG, "Stats.ultimoNumeroBolasVivas " + Stats.ultimoNumeroBolasVivas);
            //Log.e(TAG, "Stats.ultimoNumeroBolasVivasTempoDeRegistro " + Stats.ultimoNumeroBolasVivasTempoDeRegistro);

            if (ballsAlive != Stats.ultimoNumeroBolasVivas){
                switch (Stats.ultimoNumeroBolasVivas){
                    case 1:
                        Stats.tempo1Bola += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 2:
                        Stats.tempo2Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 3:
                        Stats.tempo3Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 4:
                        Stats.tempo4Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 5:
                        Stats.tempo5Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 6:
                        Stats.tempo6Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 7:
                        Stats.tempo7Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 8:
                        Stats.tempo8Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 9:
                        Stats.tempo9Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                }

                if (Stats.ultimoNumeroBolasVivas >= 10){
                    Stats.tempo10OuMaisBolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                }

                Stats.ultimoNumeroBolasVivasTempoDeRegistro = TimeHandler.timeOfLevelPlay;
                Stats.ultimoNumeroBolasVivas = ballsAlive;

            }

        }
    }

    static void checkIfDead() {

        //Log.e(TAG, "checkIfDead");

        ballsNotInvencibleAlive = 0;
        ballsInvencible = 0;
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).isAlive){
                if (!balls.get(i).isInvencible){
                    ballsNotInvencibleAlive += 1;
                } else {
                    ballsInvencible += 1;
                }
            }
        }

        //Log.e(TAG, "ballsNotInvencibleAlive "+ballsNotInvencibleAlive);

        //Log.e(TAG, "Level.levelObject.minBallsAlive "+Level.levelObject.minBallsAlive);

        Level.levelObject.levelGoalsObject.notifyBallsAlive(ballsNotInvencibleAlive + ballsInvencible, TimeHandler.secondsOfLevelPlay);

        ballGoalsPanel.setValues(ballsNotInvencibleAlive + ballsInvencible, Level.levelObject.minBallsAlive, ballsInvencible);
        if (Level.levelObject.minBallsAlive > ballsNotInvencibleAlive && !Training.training){

            switch (Stats.ultimoNumeroBolasVivas){
                case 1:
                    Stats.tempo1Bola += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 2:
                    Stats.tempo2Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 3:
                    Stats.tempo3Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 4:
                    Stats.tempo4Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 5:
                    Stats.tempo5Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 6:
                    Stats.tempo6Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 7:
                    Stats.tempo7Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 8:
                    Stats.tempo8Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
                case 9:
                    Stats.tempo9Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                    break;
            }


            setGameState(GAME_STATE_DERROTA);
        }
        
        for (int i = 0; i < ballCollisionStars.size(); i++){
            if (!ballCollisionStars.get(i).isVisible){
                ballCollisionStars.remove(i);
                break;
            }
        }
    }

    static void checkTransformations(){

        if (wind != null){
            wind.checkTransformations(true);
        }

        for (int i = 0; i < balls.size(); i++){
            balls.get(i).checkTransformations(true);
        }
        
        for (int i = 0; i < fakeBalls.size(); i++){
            fakeBalls.get(i).checkTransformations(true);
        }

        for (int i = 0; i < targets.size(); i++){
            targets.get(i).checkTransformations(true);
        }

        for (int i = 0; i < bars.size(); i++){
            bars.get(i).checkTransformations(true);
        }

        for (int i = 0; i < obstacles.size(); i++){
            obstacles.get(i).checkTransformations(true);
        }
        
        for (int i = 0; i < ballCollisionStars.size(); i++){
            ballCollisionStars.get(i).checkTransformations(true);
        }
        
        for (int i = 0; i < targets.size(); i++){
            if (targets.get(i).showPointsState == Entity.SHOW_POINTS_ON){
                targets.get(i).checkTransformations(true);
            }
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).checkTransformations(true);
        }

        for (int i = 0; i < specialBalls.size(); i++){
            specialBalls.get(i).checkTransformations(true);
        }

        if (MenuHandler.menuMain != null) MenuHandler.menuMain.checkTransformations(true);
        if (MenuHandler.menuInGame != null) MenuHandler.menuInGame.checkTransformations(true);
        if (MenuHandler.menuGameOver != null) MenuHandler.menuGameOver.checkTransformations(true);
        if (MenuHandler.menuTutorialUnvisited != null) MenuHandler.menuTutorialUnvisited.checkTransformations(true);
        if (MenuHandler.menuConnect != null) MenuHandler.menuConnect.checkTransformations(true);

        if (MenuHandler.menuPlay != null) MenuHandler.menuPlay.checkTransformations(true);
        if (MenuHandler.menuExplicacaoAntesDoTreinamento != null) MenuHandler.menuExplicacaoAntesDoTreinamento.checkTransformations(true);
        if (MenuHandler.menuDuranteTreinamento != null) MenuHandler.menuDuranteTreinamento.checkTransformations(true);

        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.checkTransformations(true);
        if (MenuHandler.menuOptionsPlay != null) MenuHandler.menuOptionsPlay.checkTransformations(true);
        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.checkTransformations(true);
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.checkTransformations(true);
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.checkTransformations(true);
        if (levelGoalsPanel != null) levelGoalsPanel.checkTransformations(true);
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.checkTransformations(true);
        if (MenuHandler.menuFirstSaveGame != null) MenuHandler.menuFirstSaveGame.checkTransformations(true);
        if (MenuHandler.menuCarregar != null) MenuHandler.menuCarregar.checkTransformations(true);
        if (SelectorHandler.selectorMusic != null) SelectorHandler.selectorMusic.checkTransformations(true);
        if (SelectorHandler.selectorDifficulty != null) SelectorHandler.selectorDifficulty.checkTransformations(true);
        if (SelectorHandler.selectorSound != null) SelectorHandler.selectorSound.checkTransformations(true);
        if (SelectorHandler.selectorVibration != null) SelectorHandler.selectorVibration.checkTransformations(true);

        if (tittle != null) tittle.checkTransformations(true);


        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.checkTransformations(true);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.checkTransformations(true);

        if (currentLevelIcon != null) currentLevelIcon.checkTransformations(true);

        if (groupsUnblocked != null) {
            for (int i = 0; i < groupsUnblocked.size(); i++) {
                groupsUnblocked.get(i).checkTransformations(true);
            }
        }

        if (aboutTextView != null) aboutTextView.checkTransformations(true);
        if (statsTextView != null) statsTextView.checkTransformations(true);
        if (notConnectedTextView != null) notConnectedTextView.checkTransformations(true);
        if (tipTextBox != null) tipTextBox.checkTransformations(true);
        
        MessagesHandler.messageGameOver.checkTransformations(true);
        MessagesHandler.messagePreparation.checkTransformations(true);
        MessagesHandler.messageInGame.checkTransformations(true);
        MessagesHandler.messageMaxScoreTotal.checkTransformations(true);
        MessagesHandler.messageGoogleLogged.checkTransformations(true);
        MessagesHandler.messageConqueredStarsTotal.checkTransformations(true);
        MessagesHandler.starForMessage.checkTransformations(true);
        MessagesHandler.bottomTextBox.checkTransformations(true);
        MessagesHandler.messageGroupsUnblocked.checkTransformations(true);
        MessagesHandler.messageBack.checkTransformations(true);
        MessagesHandler.messageContinue.checkTransformations(true);
        if (MessagesHandler.messageMenuSaveNotSeen != null) MessagesHandler.messageMenuSaveNotSeen.checkTransformations(true);
        if (MessagesHandler.messageMenuCarregarJogo != null) MessagesHandler.messageMenuCarregarJogo.checkTransformations(true);
        if (MessagesHandler.messageExplicacaoTreinamento != null) MessagesHandler.messageExplicacaoTreinamento.checkTransformations(true);
        if (MessagesHandler.messageExplicacaoDuranteTreinamento != null) MessagesHandler.messageExplicacaoDuranteTreinamento.checkTransformations(true);

        if (MessageStarWin.messageStarsWin != null) MessageStarWin.messageStarsWin.checkTransformations(true);
        if (MessageStar.messageStars != null) MessageStar.messageStars.checkTransformations(true);


        //otimização if (bordaE != null)bordaE.checkTransformations(true);
        //otimização if (bordaD != null)bordaD.checkTransformations(true);
        //otimização if (bordaC != null)bordaC.checkTransformations(true);
        if (bordaB != null)bordaB.checkTransformations(true);

        if (GoogleAPI.playerIconImage != null) GoogleAPI.playerIconImage.checkTransformations(true);


        //otimização if (frame != null)frame.checkTransformations(true);
        //otimização if (frame != null)topFrame.checkTransformations(true);

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.checkTransformations(true);
        //otimização if (ballDataPanel != null) ballDataPanel.checkTransformations(true);
        if (ballGoalsPanel != null) ballGoalsPanel.checkTransformations(true);

        //otimização if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.checkTransformations(true);
        //otimização if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.checkTransformations(true);
        //otimização if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.checkTransformations(true);
        //otimização if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.checkTransformations(true);

        if (imageTutorialDown != null) imageTutorialDown.checkTransformations(true);
        if (imageTutorialTop != null) imageTutorialTop.checkTransformations(true);

        if (messages != null) messages.checkTransformations(true);
        
        //otimização if (brickBackground != null) brickBackground.checkTransformations(true);
    }

    static void render(float[] matrixView, float[] matrixProjection){

        initSimulateTime = Utils.getTimeMilliPrecision();


        if (brickBackground != null) brickBackground.prepareRender(matrixView, matrixProjection); // 761

        if (!Game.paraGravacaoVideo) {
            if (MessagesHandler.messageCurrentLevel != null)
                MessagesHandler.messageCurrentLevel.prepareRender(matrixView, matrixProjection);
        }

        if (MessagesHandler.messageTrainingState != null)
            MessagesHandler.messageTrainingState.prepareRender(matrixView, matrixProjection);

        if (MessagesHandler.messageTrainingState2 != null)
            MessagesHandler.messageTrainingState2.prepareRender(matrixView, matrixProjection);

        if (Game.versaoBeta && !Game.paraGravacaoVideo) {
            if (MessagesHandler.messageBeta != null)
                MessagesHandler.messageBeta.prepareRender(matrixView, matrixProjection);
        }

        if (imageTutorialDown != null) imageTutorialDown.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < fakeBalls.size(); i++){
            if (!fakeBalls.get(i).fakeOnTop) {
                fakeBalls.get(i).prepareRender(matrixView, matrixProjection);
            }
        }

        if (textsForTest != null) {
            for (int i = 0; i < textsForTest.size(); i++) {
                textsForTest.get(i).prepareRender(matrixView, matrixProjection);
            }
        }


        for (int i = 0; i < balls.size(); i++){
            balls.get(i).prepareRender(matrixView, matrixProjection);
        }
        
        for (int i = 0; i < fakeBalls.size(); i++){
            if (fakeBalls.get(i).fakeOnTop) {
                fakeBalls.get(i).prepareRender(matrixView, matrixProjection);
            }
        }

        if (targetGroup != null && targets.size() > 0) {
            targetGroup.render(matrixView, matrixProjection);
        }


        //for (int i = 0; i < targets.size(); i++){
        //    targets.get(i).prepareRender(matrixView, matrixProjection);
        //}

        for (int i = 0; i < bars.size(); i++){
            bars.get(i).prepareRender(matrixView, matrixProjection); // TODO otimizar????? 3ms
        }

        for (int i = 0; i < obstacles.size(); i++){
            obstacles.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < ballCollisionStars.size(); i++){
                //Log.e(TAG, "rendering ball collision star "+i);
                ballCollisionStars.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < specialBalls.size(); i++){
            specialBalls.get(i).prepareRender(matrixView, matrixProjection);
        }

        if (wind != null) {
            wind.prepareRender(matrixView, matrixProjection);
        }

        if (pointsGroup != null) {
            pointsGroup.render(matrixView, matrixProjection);
        }

        if (topFrame != null)topFrame.prepareRender(matrixView, matrixProjection);
        
        if (MenuHandler.menuMain != null) MenuHandler.menuMain.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuCarregar != null) MenuHandler.menuCarregar.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuFirstSaveGame != null) MenuHandler.menuFirstSaveGame.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuInGame != null) MenuHandler.menuInGame.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuGameOver != null) MenuHandler.menuGameOver.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuTutorialUnvisited != null) MenuHandler.menuTutorialUnvisited.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuConnect != null) MenuHandler.menuConnect.prepareRender(matrixView, matrixProjection);

        if (MenuHandler.menuPlay != null) MenuHandler.menuPlay.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuExplicacaoAntesDoTreinamento != null) MenuHandler.menuExplicacaoAntesDoTreinamento.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuDuranteTreinamento != null) MenuHandler.menuDuranteTreinamento.prepareRender(matrixView, matrixProjection);

        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuOptionsPlay != null) MenuHandler.menuOptionsPlay.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.prepareRender(matrixView, matrixProjection);
        if (levelGoalsPanel != null) levelGoalsPanel.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.prepareRender(matrixView, matrixProjection);
        if (SelectorHandler.selectorVibration != null) SelectorHandler.selectorVibration.prepareRender(matrixView, matrixProjection);
        if (SelectorHandler.selectorDifficulty != null) SelectorHandler.selectorDifficulty.prepareRender(matrixView, matrixProjection);
        if (SelectorHandler.selectorMusic != null) SelectorHandler.selectorMusic.prepareRender(matrixView, matrixProjection);
        if (SelectorHandler.selectorSound != null) SelectorHandler.selectorSound.prepareRender(matrixView, matrixProjection);
        if (tittle != null) {tittle.prepareRender(matrixView, matrixProjection);}

        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.prepareRender(matrixView, matrixProjection);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.prepareRender(matrixView, matrixProjection);

        if (currentLevelIcon != null) currentLevelIcon.prepareRender(matrixView, matrixProjection);

        if (GoogleAPI.playerIconImage != null) GoogleAPI.playerIconImage.prepareRender(matrixView, matrixProjection);

        if (groupsUnblocked != null) {
            for (int i = 0; i < groupsUnblocked.size(); i++) {
                groupsUnblocked.get(i).prepareRender(matrixView, matrixProjection);
            }
        }

        if (aboutTextView != null) aboutTextView.prepareRender(matrixView, matrixProjection);
        if (statsTextView != null) statsTextView.prepareRender(matrixView, matrixProjection);

        MessagesHandler.messageGameOver.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messagePreparation.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageInGame.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageMenu.prepareRender(matrixView, matrixProjection);

        if (MessagesHandler.messageMenuSaveNotSeen != null) MessagesHandler.messageMenuSaveNotSeen.prepareRender(matrixView, matrixProjection);
        if (MessagesHandler.messageMenuCarregarJogo != null) MessagesHandler.messageMenuCarregarJogo.prepareRender(matrixView, matrixProjection);
        if (MessagesHandler.messageExplicacaoTreinamento != null) MessagesHandler.messageExplicacaoTreinamento.prepareRender(matrixView, matrixProjection);


        MessagesHandler.messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageGoogleLogged.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageConqueredStarsTotal.prepareRender(matrixView, matrixProjection);
        MessagesHandler.starForMessage.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageGroupsUnblocked.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageBack.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageContinue.prepareRender(matrixView, matrixProjection);
        if (MessageStar.messageStars != null) MessageStar.messageStars.prepareRender(matrixView, matrixProjection);
        if (MessageStarWin.messageStarsWin != null) MessageStarWin.messageStarsWin.prepareRender(matrixView, matrixProjection);

        if (!Game.paraGravacaoVideo) {
            if (MessagesHandler.messageTime != null)
                MessagesHandler.messageTime.prepareRender(matrixView, matrixProjection);
        }


        if (ballDataPanel != null) ballDataPanel.prepareRender(matrixView, matrixProjection);

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.prepareRender(matrixView, matrixProjection);
        if (ballGoalsPanel != null) ballGoalsPanel.prepareRender(matrixView, matrixProjection);

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.prepareRender(matrixView, matrixProjection);

        if (ButtonHandler.buttonReturn != null) ButtonHandler.buttonReturn.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.buttonReturnObjectivesPause != null)
            ButtonHandler.buttonReturnObjectivesPause.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.buttonContinue != null) ButtonHandler.buttonContinue.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.buttonGroupLeaderboard != null) ButtonHandler.buttonGroupLeaderboard.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.buttonFinalTargetLeft != null) ButtonHandler.buttonFinalTargetLeft.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.buttonFinalTargetRight != null) ButtonHandler.buttonFinalTargetRight.prepareRender(matrixView, matrixProjection);

        if (tipTextBox != null) tipTextBox.prepareRender(matrixView, matrixProjection);

        MessagesHandler.messageSubMenu.prepareRender(matrixView, matrixProjection);

        MessagesHandler.bottomTextBox.prepareRender(matrixView, matrixProjection);

        if (imageTutorialTop != null) imageTutorialTop.prepareRender(matrixView, matrixProjection);

        if (notConnectedTextView != null) notConnectedTextView.prepareRender(matrixView, matrixProjection);

        if (!Game.paraGravacaoVideo) {
            if (messages != null) messages.prepareRender(matrixView, matrixProjection);
        }



        if (gameState == GAME_STATE_MENU_PRINCIPAL || gameState == GAME_STATE_MENU_TUTORIAL || gameState == GAME_STATE_OBJETIVO_LEVEL || gameState == GAME_STATE_OPCOES || gameState == GAME_STATE_SELECAO_GRUPO || gameState == GAME_STATE_SELECAO_LEVEL || gameState == GAME_STATE_SOBRE || gameState == GAME_STATE_TUTORIAL
                || gameState == GAME_STATE_VITORIA || gameState == GAME_STATE_VITORIA_COMPLEMENTACAO || gameState == GAME_STATE_OPCOES_JOGABILIDADE || gameState == GAME_STATE_MENU_EXPLICACAO_TREINAMENTO || gameState == GAME_STATE_MENU_JOGAR || gameState == GAME_STATE_ESTATISTICAS){
            if (bordaB != null)bordaB.prepareRender(matrixView, matrixProjection);
            if (bordaE != null)bordaE.prepareRender(matrixView, matrixProjection);
            if (bordaD != null)bordaD.prepareRender(matrixView, matrixProjection);
            if (bordaC != null)bordaC.prepareRender(matrixView, matrixProjection);
        }


        if (MessagesHandler.messageExplicacaoDuranteTreinamento != null) MessagesHandler.messageExplicacaoDuranteTreinamento.prepareRender(matrixView, matrixProjection);


        if (frame != null)frame.prepareRender(matrixView, matrixProjection);

        processSimulateDurationTest(2);
    }

    static void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents != null && touchEvents.size() == 0){
                isBlocked = false;
            }
       }
    }

    static void verifyListeners() {

        if (!isBlocked) {
            for (int i = 0; i < interactionListeners.size(); i++) {
                interactionListeners.get(i).verify();
            }
        }

        if (Splash.menuGoogle != null) Splash.menuGoogle.verifyListener();
        if (Splash.menuVelocity != null) Splash.menuVelocity.verifyListener();
        if (MenuHandler.menuMain != null) MenuHandler.menuMain.verifyListener();
        if (MenuHandler.menuCarregar != null) MenuHandler.menuCarregar.verifyListener();
        if (MenuHandler.menuFirstSaveGame != null) MenuHandler.menuFirstSaveGame.verifyListener();
        if (MenuHandler.menuGameOver != null) MenuHandler.menuGameOver.verifyListener();
        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.verifyListener();
        if (MenuHandler.menuOptionsPlay != null) MenuHandler.menuOptionsPlay.verifyListener();
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.verifyListener();
        if (MenuHandler.menuInGame != null) MenuHandler.menuInGame.verifyListener();
        if (MenuHandler.menuTutorialUnvisited != null) MenuHandler.menuTutorialUnvisited.verifyListener();
        if (MenuHandler.menuConnect != null) MenuHandler.menuConnect.verifyListener();

        if (MenuHandler.menuPlay != null) MenuHandler.menuPlay.verifyListener();
        if (MenuHandler.menuExplicacaoAntesDoTreinamento != null) MenuHandler.menuExplicacaoAntesDoTreinamento.verifyListener();
        if (MenuHandler.menuDuranteTreinamento != null) MenuHandler.menuDuranteTreinamento.verifyListener();

        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.verifyListener();
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.verifyListener();
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.verifyListener();

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.verifyListener();

        if (ButtonHandler.buttonReturn != null) ButtonHandler.buttonReturn.verifyListener();
        if (ButtonHandler.buttonReturnObjectivesPause != null)
            ButtonHandler.buttonReturnObjectivesPause.verifyListener();
        if (ButtonHandler.buttonContinue != null) ButtonHandler.buttonContinue.verifyListener();
        if (ButtonHandler.buttonGroupLeaderboard != null) ButtonHandler.buttonGroupLeaderboard.verifyListener();
        if (ButtonHandler.buttonFinalTargetLeft != null) ButtonHandler.buttonFinalTargetLeft.verifyListener();
        if (ButtonHandler.buttonFinalTargetRight != null) ButtonHandler.buttonFinalTargetRight.verifyListener();

        if (statsTextView != null) statsTextView.verifyListener();
        if (aboutTextView != null) aboutTextView.verifyListener();
        if (notConnectedTextView != null) notConnectedTextView.verifyListener();
        if (tipTextBox != null) tipTextBox.verifyListener();

        if (SelectorHandler.selectorVibration != null) SelectorHandler.selectorVibration.verifyListener();
        if (SelectorHandler.selectorDifficulty != null) SelectorHandler.selectorDifficulty.verifyListener();
        if (SelectorHandler.selectorMusic != null) SelectorHandler.selectorMusic.verifyListener();
        if (SelectorHandler.selectorSound != null) SelectorHandler.selectorSound.verifyListener();
        if (Splash.selectorDifficultyInitMenu != null) Splash.selectorDifficultyInitMenu.verifyListener();

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.verifyListener();
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.verifyListener();
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.verifyListener();
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.verifyListener();
        if (MessagesHandler.bottomTextBox != null) MessagesHandler.bottomTextBox.verifyListener();


        // elimina os touchevents que tiverem o UP ativado, ou seja, que já foram considerados nesta passagem

        if (touchEvents != null) {

            for (int i2 = 0; i2 < Game.touchEvents.size(); i2++) {
                if (Game.touchEvents.get(i2).type == TouchEvent.TOUCH_TYPE_UP) {
                    Game.touchEvents.remove(i2);
                }
            }
        }
    }

    public static ArrayList<Entity> collectAllMenuEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(MenuHandler.menuMain);
        list.add(MenuHandler.menuOptions);
        list.add(MenuHandler.menuTutorialUnvisited);
        list.add(MenuHandler.menuConnect);
        list.add(MenuHandler.groupMenu);
        list.add(MenuHandler.levelMenu);
        list.add(MenuHandler.tutorialMenu);
        list.add(levelGoalsPanel);
        list.add(Tutorial.tutorialImage);// TODO ????
        list.add(Tutorial.tutorialTextBox);// TODO ????
        list.add(ButtonHandler.buttonReturn);
        list.add(ButtonHandler.buttonReturnObjectivesPause);
        list.add(ButtonHandler.buttonContinue);
        list.add(ButtonHandler.buttonGroupLeaderboard);
        list.add(MenuHandler.menuInGameOptions);
        list.add(SelectorHandler.selectorVibration);
        list.add(SelectorHandler.selectorMusic);
        list.add(SelectorHandler.selectorSound);
        list.add(MenuHandler.menuInGame);
        list.add(MenuHandler.menuGameOver);
        list.add(tittle);
        list.add(aboutTextView);
        list.add(notConnectedTextView);
        list.add(tipTextBox);
        list.add(MessagesHandler.messageGameOver);
        list.add(MessagesHandler.messagePreparation);
        list.add(MessagesHandler.messageInGame);
        list.add(MessagesHandler.messageMenu);
        list.add(MessagesHandler.messageSubMenu);
        list.add(MessagesHandler.messageGroupsUnblocked);
        list.add(MessagesHandler.messageMaxScoreTotal);
        list.add(MessagesHandler.messageGoogleLogged);
        list.add(MessagesHandler.messageConqueredStarsTotal);
        list.add(MessagesHandler.starForMessage);
        list.add(MessagesHandler.messageBack);
        list.add(MessagesHandler.messageContinue);
        list.add(MessagesHandler.bottomTextBox);
        
        return list;
    }

    static public Context getContext(){
        return mainActivity.getApplicationContext();
    }

}
