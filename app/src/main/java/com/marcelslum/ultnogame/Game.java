package com.marcelslum.ultnogame;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

import android.os.Vibrator;
import android.util.Log;

import static android.content.Context.ACTIVITY_SERVICE;

// TODO Resetar ranking geral
// TODO fechar banco de dados após uso



public class Game {

    public static final int NUMBERO_DE_ENTIDADES_FIXAS = 300;
    public static int entidadesFixasInseridas = 0;
    public static Entity [] entidadesFixas = new Entity[NUMBERO_DE_ENTIDADES_FIXAS];

    public static Sound sound = new Sound();

    public static boolean forDebugDeleteDatabaseAndStorage = false;
    public static boolean ganharTodasAsEstrelas = false;
    public static boolean paraGravacaoVideo = false;
    public static boolean ganharComMetadeDosAlvos = true;
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
    public static boolean apagarEstatisticasNoInicio = false;
    public static boolean apagarEstatisticasNoMenu = false;
    public static boolean logNotificacaoLevelGoals = false;


    public static MyGLSurface myGlSurface;

    public static Pool<Vector> vectorPool;
    public static Pool<Text> textPool;
    public static Pool<Button> buttonPool;

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
    public static ArrayList<StatsGraph> statsGraphs;
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


    static Image tittle;
    static ArrayList<Image> groupsUnblocked;
    static Image currentLevelIcon;
    static LevelsGroupData currentLevelsGroupDataSelected;
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
    

    public static void adicionarEntidadeFixa(Entity entity){
        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null){
                if (entity.name.equals(entidadesFixas[i].name)) {
                    entidadesFixas[i] = entity;
                    return;
                }
            }
        }

        entidadesFixas[entidadesFixasInseridas] = entity;
        entidadesFixasInseridas += 1;
    }


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

        entidadesFixas = new Entity[NUMBERO_DE_ENTIDADES_FIXAS];

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

        frame = new Rectangle("topBlackFrame", 0f, 0f, Entity.TYPE_OTHER, Game.resolutionX, Game.resolutionY, -1, new Color(0f, 0f, 0f, 1f));
        adicionarEntidadeFixa(frame);
        frame.layer = Layer.LAYER10;
        frame.clearDisplay();
        frame.alpha = 0f;
        TextureData.getTextureData();

        Log.e(TAG, "setGameState(Game.GAME_STATE_INTRO)1");
        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTRO);

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
        statsGraphs = new ArrayList<>();
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
        adicionarEntidadeFixa(bordaE);
        bordaD = new Edge("bordaD", resolutionX-2, 0,  Entity.TYPE_RIGHT_BORDER, 2000, resolutionY*2, Color.pretoCheio);
        adicionarEntidadeFixa(bordaD);
        bordaC = new Edge("bordaC",  1, -1000,  Entity.TYPE_TOP_BORDER, resolutionX-4, 1001, Color.pretoCheio);
        adicionarEntidadeFixa(bordaC);
        bordaB = new Edge("bordaB", -1000, resolutionY,  Entity.TYPE_BOTTOM_BORDER, resolutionX*3, 1000, COLOR_BORDA_B);
        adicionarEntidadeFixa(bordaB);

    }

    public static void initTittle(){

        tittle = new Image("tittle",
                gameAreaResolutionX * 0.3f, gameAreaResolutionY * 0.13f,
                gameAreaResolutionX * 0.4f, gameAreaResolutionX * 0.4f * 0.3671875f,
                Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_TITTLE_ID),
                new Color(0.5f, 0.2f, 0.8f, 1f));
        adicionarEntidadeFixa(tittle);


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

        if (GameStateHandler.gameState != GameStateHandler.GAME_STATE_MOSTRAR_OBJETIVOS) return;

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
                                    if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MOSTRAR_OBJETIVOS) {
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


    public static void freeAllGameEntities() {
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

        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){
            if (Utils.getTimeMilliPrecision() - Training.trainingBarCollisionInit > 4200){
                Training.trainingBarCollisionInit  = Long.MAX_VALUE;
                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                }
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_JOGAR);



            }
        }


        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MENU_INICIAL && GoogleAPI.playerIconImage == null && GoogleAPI.playerIcon != null){
            mainActivity.updatePlayerInfo();
        }

        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PRE_JOGAR) {
            if (Utils.getTimeMilliPrecision() - timeOfPrePlay > 500){
                timeOfPrePlay = 0;
                for (int i = 0; i < balls.size(); i++) {
                    balls.get(i).initializeVelocityAngleTimeRecord();
                }
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_JOGAR);
            }
        }

        if (GameStateHandler.gameState != GameStateHandler.GAME_STATE_VITORIA_1) {
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
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {

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
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
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

        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {

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
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
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
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
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
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {

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
        
        
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
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
        } else if(GameStateHandler.gameState == GameStateHandler.GAME_STATE_VITORIA_1 || GameStateHandler.gameState == GameStateHandler.GAME_STATE_VITORIA_2){
            if (brickBackground != null){
                brickBackground.changeDrawInfo();
                brickBackground.move();
            }
            //brickBackground.animate();
            //if (background != null){
            //    background.move(3);
            //}
        }
       
        
        
        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR) {
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
                            ball.freeBall();
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

        if (numberOfTargetsAlives == 1 && GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR){
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
                    Game.sound.playGameOver();
                } else {
                    MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.sucesso));
                    MessagesHandler.messageTrainingState.setColor(Color.verde40);
                    Game.sound.playSuccess1();

                    if (MessagesHandler.messageTrainingState2 != null) {
                        MessagesHandler.messageTrainingState2.setText(Game.getContext().getResources().getString(R.string.tentativa) + " " + (Training.tentativaCertaTreinamento + 1) + " " + Game.getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
                    }
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

            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_VITORIA_1);


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


            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_DERROTA);
        }
        
        for (int i = 0; i < ballCollisionStars.size(); i++){
            if (!ballCollisionStars.get(i).isVisible){
                ballCollisionStars.remove(i);
                break;
            }
        }
    }

    static void checkTransformations(){


        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null){
                entidadesFixas[i].checkTransformations(false);
            }
        }

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

        for (int i = 0; i < statsGraphs.size(); i++){
            statsGraphs.get(i).checkTransformations(true);
        }

        if (levelGoalsPanel != null) levelGoalsPanel.checkTransformations(true);

        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.checkTransformations(true);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.checkTransformations(true);

        if (currentLevelIcon != null) currentLevelIcon.checkTransformations(true);

        if (groupsUnblocked != null) {
            for (int i = 0; i < groupsUnblocked.size(); i++) {
                groupsUnblocked.get(i).checkTransformations(true);
            }
        }

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.checkTransformations(true);

        if (ballGoalsPanel != null) ballGoalsPanel.checkTransformations(true);

        if (messages != null) messages.checkTransformations(true);

    }

    static void render(float[] matrixView, float[] matrixProjection){

        initSimulateTime = Utils.getTimeMilliPrecision();

        if (brickBackground != null) brickBackground.prepareRender(matrixView, matrixProjection); // 761

        for (int i = 0; i < fakeBalls.size(); i++){
            if (!fakeBalls.get(i).fakeOnTop) {
                fakeBalls.get(i).prepareRender(matrixView, matrixProjection);
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

        for (int i = 0; i < bars.size(); i++){
            bars.get(i).prepareRender(matrixView, matrixProjection); // TODO otimizar????? 3ms
        }

        for (int i = 0; i < obstacles.size(); i++){
            obstacles.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < ballCollisionStars.size(); i++){
            ballCollisionStars.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < specialBalls.size(); i++){
            specialBalls.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < statsGraphs.size(); i++){
            statsGraphs.get(i).prepareRender(matrixView, matrixProjection);
        }

        if (wind != null) {
            wind.prepareRender(matrixView, matrixProjection);
        }

        if (pointsGroup != null) {
            pointsGroup.render(matrixView, matrixProjection);
        }

        if (levelGoalsPanel != null) levelGoalsPanel.prepareRender(matrixView, matrixProjection);

        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.prepareRender(matrixView, matrixProjection);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null && entidadesFixas[i].layer == LAYER1){
                if (Game.paraGravacaoVideo){
                    if (entidadesFixas[i] != MessagesHandler.messageTime &&
                        entidadesFixas[i] != MessagesHandler.messageCurrentLevel &&
                            (MessagesHandler.messageBeta != null  && entidadesFixas[i] != MessagesHandler.messageBeta)
                    ){
                        entidadesFixas[i].prepareRender(matrixView, matrixProjection);
                    }
                } else {
                    entidadesFixas[i].prepareRender(matrixView, matrixProjection);
                }
            }
        }



        if (currentLevelIcon != null) currentLevelIcon.prepareRender(matrixView, matrixProjection);

        if (groupsUnblocked != null) {
            for (int i = 0; i < groupsUnblocked.size(); i++) {
                groupsUnblocked.get(i).prepareRender(matrixView, matrixProjection);
            }
        }

        if (ballDataPanel != null) ballDataPanel.prepareRender(matrixView, matrixProjection);

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.prepareRender(matrixView, matrixProjection);
        if (ballGoalsPanel != null) ballGoalsPanel.prepareRender(matrixView, matrixProjection);

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.prepareRender(matrixView, matrixProjection);
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.prepareRender(matrixView, matrixProjection);

        if (!Game.paraGravacaoVideo) {
            if (messages != null) messages.prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null && entidadesFixas[i].layer == LAYER10){
                entidadesFixas[i].prepareRender(matrixView, matrixProjection);
            }
        }
        
        //processSimulateDurationTest(2);
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

        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null){
                entidadesFixas[i].verifyListener();
            }
        }

        if (Splash.menuGoogle != null) Splash.menuGoogle.verifyListener();
        if (Splash.menuVelocity != null) Splash.menuVelocity.verifyListener();
        if (Splash.selectorDifficultyInitMenu != null) Splash.selectorDifficultyInitMenu.verifyListener();

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.verifyListener();
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.verifyListener();
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.verifyListener();
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.verifyListener();

        // elimina os touchevents que tiverem o UP ativado,
        // ou seja, que já foram considerados nesta passagem
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
        list.add(MenuHandler.menuInicial);
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
        list.add(MenuHandler.menuPauseOpcoes);
        list.add(SelectorHandler.selectorVibration);
        list.add(SelectorHandler.selectorMusic);
        list.add(SelectorHandler.selectorSound);
        list.add(MenuHandler.menuPause);
        list.add(MenuHandler.menuGameOver);
        list.add(tittle);
        list.add(MessagesHandler.aboutTextView);
        list.add(MessagesHandler.notConnectedTextView);
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

    public static void esconderEntidadesFixas() {
        for (int i = 0; i < entidadesFixas.length; i++) {
            if (entidadesFixas[i] != null) {
                Log.e(TAG, "escondendo " + entidadesFixas[i].name);
                entidadesFixas[i].clearDisplay();
            }
        }
    }
}

