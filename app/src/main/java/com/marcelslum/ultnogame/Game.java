package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

//import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// TODO erro: jogo trava quando se aperta os dois botões de flecha ao mesmo tempo: resolvido
// TODO pausar a música quando minimiza o aplicativo: resolvido?
// TODO verificar arquivo targets.png se está atualizado
// TODO pausar todos os sons do sound pool quando o jogo fecha
// TODO voltar ao estado anterior quando o jogo fecha

// TODO verificar os listeners ativos


/** * Created by marcel on 01/08/2016.
 */
public class Game {

    public static MainActivity mainActivity;

    public static boolean forInitGame;

    public static int basePoints;
    public static float difficultyVelocityBarMultiplicator;
    public static float difficultyVelocityObstacleMultiplicator;
    public static float difficultyVelocityBallMultiplicator;
    public static final float BAR_EASY = 0.9f;
    public static final float BALL_EASY = 0.9f;
    public static final float OBSTACLE_EASY = 0.9f;
    public static final float BAR_NORMAL = 1f;
    public static final float BALL_NORMAL = 1f;
    public static final float OBSTACLE_NORMAL = 1f;
    public static final float BAR_HARD = 1.3f;
    public static final float BALL_HARD = 1.3f;
    public static final float OBSTACLE_HARD = 1.2f;
    public static final float BAR_INSANE = 1.6f;
    public static final float BALL_INSANE = 1.6f;
    public static final float OBSTACLE_INSANE = 1.4f;

    public static final int BALL_WEIGHT = 1;
    public static final int BORDA_WEIGHT = 10;
    public static final int OBSTACLES_WEIGHT = 7;
    public static final int TARGET_WEIGHT = 10;
    public static final int BAR_WEIGHT = 8;
    public static Context context;

    // entities
    public static Menu menuMain;
    public static Menu menuOptions;
    public static Menu menuInGame;
    public static Menu menuTutorial;
    public static Menu menuWin;
    public static Selector selectorLevel;
    public static Selector selectorDificulty;
    public static Selector selectorMusic;
    public static Selector selectorSound;

    public static ArrayList<Target> targets;
    public static ArrayList<Ball> balls;
    public static ArrayList<Text> texts;
    public static ArrayList<TouchEvent> touchEvents;
    public static ArrayList<Bar> bars;
    public static ArrayList<Obstacle> obstacles;
    public static ArrayList<WindowGame> windows;
    public static ArrayList<Menu> menus;
    public static ArrayList<Selector> selectors;
    public static ArrayList<InteractionListener> interactionListeners;
    public static ArrayList<TextBox> textBoxes;
    public static ArrayList<ParticleGenerator> particleGenerator;
    public static ArrayList<BallParticleGenerator> ballParticleGenerator;
    public static ArrayList<Message> messages;
    public static ArrayList<Line> lines;
    public static Background background;
    public static Wind wind;
    public static ArrayList<SpecialBall> specialBalls;

    public static ScorePanel scorePanel;
    public static ObjectivePanel objectivePanel;

    public static Edge bordaC;
    public static Edge bordaE;
    public static Edge bordaD;
    public static Edge bordaB;

    public static Rectangle frame;

    public static Button button1Left;
    public static Button button1Right;
    public static Button button2Left;
    public static Button button2Right;
    public static ButtonOnOff buttonSound;
    public static ButtonOnOff buttonMusic;

    public static Image tittle;

    public static Text messageGameOver;
    public static Text messagePreparation;
    public static Text messageInGame;
    public static Text messageCurrentLevel;
    public static Text messageMaxScoreTotal;
    public static Text messageSplash1;
    public static Text messageSplash2;
    public static TextBox bottomTextBox;

    // quadtree objects
    public static Quadtree quad;

    // font
    public static Font font;

    // scree properties
    public static float gameAreaResolutionX;
    public static float gameAreaResolutionY;
    public static float resolutionX;
    public static float resolutionY;
    public static float screenOffSetX;
    public static float screenOffSetY;

    public static int ballCollidedFx = 0;

    // options
    public static boolean musicOn = true;
    public static boolean isBlocked;
    public static  int volume = 100;
    
    // game state
    public static int gameState;
    public final static int GAME_STATE_JOGAR = 10;
    public final static int GAME_STATE_PREPARAR = 11;
    public final static int GAME_STATE_MENU =  12;
    public final static int GAME_STATE_VITORIA =  13;
    public final static int GAME_STATE_DERROTA =  14;
    public final static int GAME_STATE_TUTORIAL =  15;
    public final static int GAME_STATE_PAUSE =  16;
    public final static int GAME_STATE_OPCOES =  17;
    public final static int GAME_STATE_INTRO =  18;

    public final static int DIFFICULTY_EASY = 0;
    public final static int DIFFICULTY_NORMAL = 1;
    public final static int DIFFICULTY_HARD = 2;
    public final static int DIFFICULTY_INSANE = 3;

    final static int TEXTURE_MAP_NUMBERS_SCORE1 = 1;
    final static int TEXTURE_MAP_NUMBERS_SCORE2 = 2;
    final static int TEXTURE_MAP_NUMBERS_SCORE3 = 3;
    final static int TEXTURE_MAP_NUMBERS_SCORE4 = 4;
    final static int TEXTURE_MAP_NUMBERS_SCORE5 = 5;
    final static int TEXTURE_MAP_NUMBERS_SCORE6 = 6;
    final static int TEXTURE_MAP_NUMBERS_SCORE7 = 7;
    final static int TEXTURE_MAP_NUMBERS_SCORE8 = 8;
    final static int TEXTURE_MAP_NUMBERS_SCORE9 = 9;
    final static int TEXTURE_MAP_NUMBERS_SCORE0 = 10;
    final static int TEXTURE_MAP_NUMBERS_POINT1 = 11;
    final static int TEXTURE_MAP_NUMBERS_POINT2 = 12;
    final static int TEXTURE_MAP_NUMBERS_POINT3 = 13;
    final static int TEXTURE_MAP_NUMBERS_POINT4 = 14;
    final static int TEXTURE_MAP_NUMBERS_POINT5 = 15;
    final static int TEXTURE_MAP_NUMBERS_POINT6 = 16;
    final static int TEXTURE_MAP_NUMBERS_POINT7 = 17;
    final static int TEXTURE_MAP_NUMBERS_POINT8 = 18;
    final static int TEXTURE_MAP_NUMBERS_POINT9 = 19;
    final static int TEXTURE_MAP_NUMBERS_POINT0 = 20;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR1 = 21;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR2 = 22;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR3 = 23;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR4 = 24;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR5 = 25;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR6 = 26;
    final static int TEXTURE_MAP_NUMBERS_EXPLODE_BALL = 27;
    
    final static float [] textButtonsAndBallsColumnsAndLines = new float[]{0f, 128f, 256f, 384f, 512f, 640f, 768f, 896f, 1024f};
    
    static long initTime;
    static float effectiveScreenHeight;
    static float effectiveScreenWidth;
    static int currentDifficulty;
    
    // programs
    static Program imageProgram;
    static Program imageColorizedProgram;
    static Program lineProgram;
    static Program textProgram;
    static Program solidProgram;
    static Program imageColorizedFxProgram;
    static Program windProgram;
    static Program specialBallProgram;
    static int ballsNotInvencibleAlive;
    static int ballsInvencible;
    static long initialTimePointsDecay;

    static final long TIME_FOR_POINTS_DECAY = 3000;
    public static final int POINTS_DECAY = 10;
    public static final int POINTS_EASY = 100;
    public static final int POINTS_NORMAL = 200;
    public static final int POINTS_HARD = 300;
    public static final int POINTS_INSANE = 500;

    public static int maxScoreTotal;

    private Game() {}

    public static void init(){
        Log.e("game", "init()");

        Splash.loaderConclude = false;
        MyAchievements.loaded = false;

        initPrograms();
        initFont();

        Texture.textures = new ArrayList<>();
        Texture.textures.add(new Texture(Texture.TEXTURE_TITTLE, "drawable/tittle"));
        Texture.textures.add(new Texture(Texture.TEXTURE_FONT, "drawable/jetset"));

        Game.frame = new Rectangle("frame", 0f, 0f, Game.resolutionX, Game.resolutionY, -1, new Color(0f, 0f, 0f, 1f));
        Game.frame.clearDisplay();
        Game.frame.alpha = 0f;

        setGameState(Game.GAME_STATE_INTRO);
    }

    public static void activateFrame(int duration){
        frame.display();
        frame.alpha = 1f;
        frame.reduceAlpha(duration, 0f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                frame.clearDisplay();
            }
        });
    }

    public static void initData(){
        targets = new ArrayList<>();
        balls = new ArrayList<>();
        obstacles = new ArrayList<>();
        windows = new ArrayList<>();
        specialBalls = new ArrayList<>();
        touchEvents = new ArrayList<>();
        texts = new ArrayList<>();
        interactionListeners = new ArrayList<>();
        particleGenerator = new ArrayList<>();
        ballParticleGenerator = new ArrayList<>();
        bars = new ArrayList<>();
        menus = new ArrayList<>();
        selectors = new ArrayList<>();
        textBoxes = new ArrayList<>();
        messages = new ArrayList<>();
        lines = new ArrayList<> ();
    }

    public static void initEdges(){
        Game.bordaE = new Edge("bordaE", -999, 0, 1000, Game.resolutionY*2);
        Game.bordaD = new Edge("bordaD", Game.resolutionX-2, 0, 2000, Game.resolutionY*2);
        Game.bordaC = new Edge("bordaC",  1, -1000, Game.resolutionX-4, 1001);
        Game.bordaB = new Edge("bordaB", -1000, Game.resolutionY, Game.resolutionX*3, 1000);
    }

    public static void initTextures() {
        if (Texture.textures == null) {
            Texture.textures = new ArrayList<>();
        }
        Texture.textures.add(new Texture(Texture.TEXTURE_BUTTONS_AND_BALLS, "drawable/botoesebolas2"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TARGETS, "drawable/targets"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BARS, "drawable/bars"));
        Texture.textures.add(new Texture(Texture.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE, "drawable/numbers_explosion5"));
        Texture.textures.add(new Texture(Texture.TEXTURE_SPECIAL_BALL, "drawable/bolaespecial2"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BACKGROUND, "drawable/finalback1"));



    }

    public static void initTittle(){
        tittle = new Image("tittle",
                gameAreaResolutionX * 0.3f, gameAreaResolutionY * 0.2f,
                gameAreaResolutionX * 0.4f, gameAreaResolutionX * 0.35f * 0.3671875f,
                Texture.TEXTURE_TITTLE, 0f, 1f, 0.6328125f, 1f, new Color(0.5f, 0.2f, 0.8f, 1f));

        ArrayList<float[]> valuesAnimationTittle = new ArrayList<>();
        valuesAnimationTittle.add(new float[]{0f,1f});
        valuesAnimationTittle.add(new float[]{0.15f,2f});
        valuesAnimationTittle.add(new float[]{0.45f,3f});
        valuesAnimationTittle.add(new float[]{0.6f,4f});
        valuesAnimationTittle.add(new float[]{0.85f,5f});
        final Image innerImage = tittle;
        Animation animTittle = new Animation(innerImage, "numberForAnimation", "numberForAnimation", 5000, valuesAnimationTittle, true, false);
        animTittle.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (innerImage.numberForAnimation == 1f){
                    innerImage.setColor(new Color(0f, 0f, 0f, 1f));
                } else if (innerImage.numberForAnimation == 2f) {
                    innerImage.setColor(new Color(1f, 0f, 0f, 1f));
                } else if (innerImage.numberForAnimation == 3f) {
                    innerImage.setColor(new Color(0f, 0f, 1f, 1f));
                } else if (innerImage.numberForAnimation == 4f) {
                    innerImage.setColor(new Color(0f, 1f, 0f, 1f));
                } else if (innerImage.numberForAnimation == 5f) {
                    innerImage.setColor(new Color(1f, 1f, 0f, 1f));
                }
            }
        });
        animTittle.start();

    }


    public static void initTexts(){
        initTittle();
        messageGameOver = new Text("messageGameOver",
                gameAreaResolutionX*0.5f, gameAreaResolutionY*0.2f, gameAreaResolutionY*0.17f,
                context.getResources().getString(R.string.messageGameOver), font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        ArrayList<float[]> valuesAnimationGameOver = new ArrayList<>();
        valuesAnimationGameOver.add(new float[]{0f,1f});
        valuesAnimationGameOver.add(new float[]{0.55f,2f});
        valuesAnimationGameOver.add(new float[]{0.85f,3f});
        Animation animMessageGameOver = new Animation(messageGameOver, "numberForAnimation", "numberForAnimation", 4000, valuesAnimationGameOver, true, false);
        animMessageGameOver.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (messageGameOver.numberForAnimation == 1f){
                    messageGameOver.setColor(new Color(0f, 0f, 0f, 1f));
                } else if (messageGameOver.numberForAnimation == 2f) {
                    messageGameOver.setColor(new Color(1f, 0f, 0f, 1f));
                } else if (messageGameOver.numberForAnimation == 3f) {
                    messageGameOver.setColor(new Color(0f, 0f, 1f, 1f));
                }
            }
        });
        animMessageGameOver.start();


        messagePreparation = new Text("messagePreparation",
                gameAreaResolutionX*0.5f, gameAreaResolutionY*0.3f, gameAreaResolutionY*0.4f,
                " ", font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageInGame = new Text("messageInGame",
                gameAreaResolutionX*0.5f, gameAreaResolutionY*0.25f, gameAreaResolutionY*0.14f,
                context.getResources().getString(R.string.pause), font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);

        messageCurrentLevel = new Text("messageCurrentLevel",
                resolutionX*0.05f, resolutionY*0.72f, resolutionY*0.036f,
                context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(Levels.currentLevelNumber) + " - " + context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(Levels.currentLevelNumber)), font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                resolutionX*0.05f, resolutionY*0.84f, resolutionY*0.036f,
                context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal(), font, new Color(0f, 0f, 0f, 0.5f));

        bottomTextBox = new TextBoxBuilder("bottomTextBox")
                .position(resolutionX*0.05f, resolutionY*0.9f)
                .width(resolutionX*0.9f)
                .size(resolutionY*0.032f)
                .text("...")
                .withoutArrow()
                .isHaveFrame(true)
                .isHaveArrowContinue(false)
                .frameType(TextBoxBuilder.FRAME_TYPE_SOLID)
                .build();
        setBottomText("");

    }

    public static void initMenus(){
        float fontSize = gameAreaResolutionY*0.08f;

        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", gameAreaResolutionX/2, gameAreaResolutionY*0.43f, fontSize, font);

        // cria o seletor de dificuldade
        selectorDificulty = new Selector("selectorDificulty", 0f,0f, fontSize, "",
                new String[]{context.getResources().getString(R.string.facil),
                        context.getResources().getString(R.string.normal),
                        context.getResources().getString(R.string.dificil),
                        context.getResources().getString(R.string.insano)
                }, font);
        menuOptions.addMenuOption("dificuldade", context.getResources().getString(R.string.dificuldade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorDificulty.fromMenu(Game.menuOptions);
                if (currentDifficulty == DIFFICULTY_EASY){
                    selectorDificulty.setSelectedValue(0);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeFacil));
                } else if (currentDifficulty == DIFFICULTY_NORMAL){
                    selectorDificulty.setSelectedValue(1);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeNormal));
                } else if (currentDifficulty == DIFFICULTY_HARD){
                    selectorDificulty.setSelectedValue(2);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeDificil));
                } else if (currentDifficulty == DIFFICULTY_INSANE){
                    selectorDificulty.setSelectedValue(3);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeInsano));
                }
            }
        });
        final MenuOption menuOptionDificuldade = menuOptions.getMenuOptionByName("dificuldade");
        selectorDificulty.setPosition(menuOptionDificuldade.x + (menuOptionDificuldade.width), menuOptionDificuldade.y);

        selectorDificulty.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                Game.changeDifficulty(selectorDificulty.selectedValue);
                if (selectorDificulty.selectedValue == 0){
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeFacil));
                } else if (selectorDificulty.selectedValue == 1){
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeNormal));
                } else if (selectorDificulty.selectedValue == 2){
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeDificil));
                } else if (selectorDificulty.selectedValue == 3){
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeInsano));
                }
            }
        });

        selectorDificulty.setOnConclude(new Selector.OnConclude() {
            @Override
            public void onConclude() {
                setBottomText("");
            }
        });

        // cria o seletor de musica
        selectorMusic = new Selector("selectorMusic", 0f,0f, fontSize, "",
                new String[]{
                        context.getResources().getString(R.string.nao),
                        context.getResources().getString(R.string.sim)}, font);
        menuOptions.addMenuOption("musica", context.getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorMusic.fromMenu(Game.menuOptions);
            }
        });
        MenuOption menuOptionMusic = menuOptions.getMenuOptionByName("musica");
        selectorMusic.setPosition(menuOptionMusic.x + ((menuOptionMusic.width)*2.0f), menuOptionMusic.y);
        if (musicOn) {
            selectorMusic.setSelectedValue(1);
        } else {
            selectorMusic.setSelectedValue(0);
        }
        selectorMusic.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (selectorMusic.selectedValue == 1){
                    musicOn = true;
                } else {
                    musicOn = false;
                }
            }
        });

        // cria o seletor de sons
        selectorSound = new Selector("selectorSound", 0f,0f, fontSize, "",
                new String[]{
                        context.getResources().getString(R.string.nao),
                        context.getResources().getString(R.string.sim)}, font);
        menuOptions.addMenuOption("sound", context.getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorSound.fromMenu(Game.menuOptions);
            }
        });
        MenuOption menuOptionSound = menuOptions.getMenuOptionByName("sound");
        selectorSound.setPosition(menuOptionSound.x + (menuOptionMusic.width*2.0f), menuOptionSound.y);
        if (volume == 100) {
            selectorSound.setSelectedValue(1);
        } else {
            selectorSound.setSelectedValue(0);
        }
        selectorSound.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (selectorSound.selectedValue == 1){
                    volume = 100;
                } else {
                    volume = 0;
                }
            }
        });

        menuOptions.addMenuOption("retornar", context.getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Log.e("findStateMenu", "11");
                setGameState(GAME_STATE_MENU);
            }
        });

        // -------------------------------------------MENU MAIN
        menuMain = new Menu("menuMain", gameAreaResolutionX/2, gameAreaResolutionY*0.43f, fontSize, font);

        // adiciona a opção de iniciar o jogo
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", context.getResources().getString(R.string.menuPrincipalIniciar) + " "+Levels.currentLevelNumber, new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                LevelLoader.loadLevel(Levels.currentLevelNumber);
                TutorialLoader.loadTutorial(Levels.currentLevelNumber);
                if (Levels.levelObject.tutorials.size() > 0) {
                    if (!Storage.getLevelTutorialSaw(Levels.currentLevelNumber)) {
                        Storage.setLevelTutorialSaw(Levels.currentLevelNumber, true);
                        Levels.levelObject.loadEntities();
                        Levels.levelObject.tutorials.get(0).textBox.alpha = 0f;
                        Game.setGameState(GAME_STATE_TUTORIAL);
                        Levels.levelObject.showFirstTutorial();
                    } else {
                        Game.menuTutorial.getMenuOptionByName("exibirTutorial").setText(context.getResources().getString(R.string.menuTutorialExibirTutorial) + " "+Levels.currentLevelNumber);
                        Game.menuTutorial.unblock();
                        Game.menuTutorial.display();
                        activateFrame(200);
                        tittle.display();
                    }
                } else {
                    Game.setGameState(GAME_STATE_PREPARAR);
                }
            }
        });

        // prepara os valores para o seletor de nível
        String [] levels = new String [Levels.maxNumberOfLevels-1];
        for (int i = 0; i < Levels.maxNumberOfLevels-1; i++){
            levels[i] = Integer.toString(i+1);
        }

        // cria o seletor de nível
        selectorLevel = new Selector("selectorLevel", 0f,0f, fontSize, "", levels, font);
        selectorLevel.setSelectedValue(Levels.currentLevelNumber - 1);

        // adiciona a opção de selecionar nível
        menuMain.addMenuOption("SelecionarNivel", context.getResources().getString(R.string.menuPrincipalAlterarNivel), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                Log.e("Game", SaveGame.getString());
                SaveGame.saveLocal();
                new OpenSnapshotAsyncTask().execute("");
                Game.selectorLevel.fromMenu(innerMenu);
            }
        });

        // ajusta a posição do seletor de nível
        MenuOption menuOptionSelectLevel = menuMain.getMenuOptionByName("SelecionarNivel");
        selectorLevel.setPosition(menuOptionSelectLevel.x + (menuOptionSelectLevel.width), menuOptionSelectLevel.y);
        selectorLevel.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                Game.changeLevel(Game.selectorLevel.selectedValue+1);
            }
        });

        // adiciona a opção de acessar as opções do jogo
        menuMain.addMenuOption("options", context.getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Log.e("Game", SaveGame.getLocalSave());
                new LoadFromSnapshotAsyncTask().execute("");
                setGameState(GAME_STATE_OPCOES);
            }
        });

        menuMain.addMenuOption("conquistas", context.getResources().getString(R.string.conquistas), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showAchievements(mainActivity.mGoogleApiClient, mainActivity);

                //setGameState(GAME_STATE_RANKING);
            }
        });

        menuMain.addMenuOption("ranking", context.getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                //setGameState(GAME_STATE_RANKING);
                GooglePlayGames.showLeaderboards(mainActivity.mGoogleApiClient, mainActivity);
            }
        });


        // ----------------------------------------------------MENU WIN
        menuWin = new Menu("menuWin",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuWin.addMenuOption("Continuar", context.getResources().getString(R.string.continuarDepoisVitoria), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuWin.block();
                Game.blockAndWaitTouchRelease();
                mainActivity.showInterstitial();
            }
        });

        menuWin.addMenuOption("ranking", context.getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showLeaderboards(mainActivity.mGoogleApiClient, mainActivity);
            }
        });


        // ----------------------------------------------------MENU IN GAME
        menuInGame = new Menu("menuInGame",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuInGame.addMenuOption("Continuar", context.getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_DERROTA){
                    LevelLoader.loadLevel(Levels.currentLevelNumber);
                    Game.menuInGame.clearDisplay();
                    Game.setGameState(GAME_STATE_PREPARAR);
                } else if (Game.gameState == GAME_STATE_PAUSE){
                    Log.e("game", "menu continuar quando game state = GAME_STATE_PAUSE");
                    Game.increaseAllGameEntitiesAlpha(500);
                    Game.messageInGame.reduceAlpha(500,0f);
                    Game.menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Log.e("game", "ativando callback GAME_STATE_JOGAR");
                            Game.setGameState(GAME_STATE_JOGAR);
                        }
                    });
                }
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuInGame.addMenuOption("RetornarAoMenuPrincipal", context.getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuInGame.block();
                Game.blockAndWaitTouchRelease();
                mainActivity.showInterstitial();
            }
        });

        // cria o menu tutorial
        menuTutorial = new Menu("menuTutorial", gameAreaResolutionX/2, gameAreaResolutionY/2, fontSize, font);

        // adiciona a opção exibir tutorial
        menuTutorial.addMenuOption("exibirTutorial", context.getResources().getString(R.string.menuTutorialExibirTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                Game.setGameState(GAME_STATE_TUTORIAL);
                Levels.levelObject.tutorials.get(0).textBox.alpha = 0f;
                Levels.levelObject.showingTutorial = 0;
                Levels.levelObject.tutorials.get(0).show(Sound.soundTextBoxAppear, 0.01f* (float) volume);
                Game.menuTutorial.block();
                Game.menuTutorial.clearDisplay();
            }
        });

        // adiciona a opção pular tutorial
        menuTutorial.addMenuOption("PularTutorial", context.getResources().getString(R.string.menuTutorialPularTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                Game.setGameState(GAME_STATE_PREPARAR);
                Game.menuTutorial.block();
                Game.menuTutorial.clearDisplay();
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuTutorial.addMenuOption("Retornar", context.getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                menuTutorial.block();
                menuTutorial.isVisible = false;
                Log.e("findStateMenu", "12");
                Game.setGameState(GAME_STATE_MENU);
            }
        });
    }

    public static void initPrograms(){
        imageProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_text),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_text));
        textProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_imagecolorized),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_imagecolorized));
        imageColorizedProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_imagecolorized),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_imagecolorized));
        imageColorizedFxProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_imagecolorizedfx),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_imagecolorizedfx));
        solidProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_solidcolor),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_solidcolor));
        lineProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_line),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_line));
        windProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_wind),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_wind3));
        specialBallProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_specialball),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_specialball));
    }

    public static void initFont(){
        font = new Font(Texture.TEXTURE_FONT,textProgram);
    }

    public static void initSounds(){

        volume = Storage.getVolume();


    }
   
    public static void addBall(Ball ball){
        balls.add(ball);
    }
    
    public static void addBar(Bar bar){
        bars.add(bar);
    }

    public static void addTarget(Target target){
        targets.add(target);
    }

    public static void addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
    }

    public static void addWindow(WindowGame window){
        windows.add(window);
    }

    public static void addSpecialBall(SpecialBall sb){
        specialBalls.add(sb);
    }

    public static void addText(Text text){
        texts.add(text);
    }

    public static void addMessage(Message message){
        messages.add(message);
    }

    public static void addInteracionListener(InteractionListener listener) {
        if (interactionListeners == null){
            interactionListeners = new ArrayList<InteractionListener>();
        }
        for (int i = 0; i < interactionListeners.size(); i++){
            if (interactionListeners.get(i).name == listener.name){
                //Log.e("game", " subtituindo listener "+listener.name);
                interactionListeners.set(i, listener);
                return;
            }
        }
        //Log.e("game", " adicionando listener "+listener.name);
        interactionListeners.add(listener);
    }

    public static void blockAndWaitTouchRelease(){
        isBlocked = true;
    }

    public static void setGameState(int state){
        Log.e("game", "set game state "+state);

        gameState = state;
        clearAllMenuEntities();
        if (state == GAME_STATE_INTRO) {
            mainActivity.hideAdView();
            ConnectionHandler.internetState = ConnectionHandler.INTERNET_STATE_NOT_CONNECTED;
            Splash.timeInitIntro = Utils.getTime();
            Splash.init();
            Splash.display();
        } else if (state == GAME_STATE_OPCOES){
            activateFrame(200);
            tittle.display();
            menuMain.isBlocked = true;
            menuOptions.isBlocked = false;
            menuOptions.display();
            setBottomText("");
        } else if (state == GAME_STATE_MENU){
            initTittle();
            mainActivity.showAdView();
            activateFrame(200);
            Game.bordaB.y = Game.resolutionY;
            menuOptions.block();
            menuInGame.block();
            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();
            Levels.eraseAllTutorials();
            messageSplash1.clearDisplay();
            if (messageSplash2 != null) {
                messageSplash2.clearDisplay();
            }
            menuMain.unblock();
            menuMain.display();
            tittle.display();
            messageCurrentLevel.display();
            messageMaxScoreTotal.display();
            bottomTextBox.display();
            messageMaxScoreTotal.setText(
                    context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal());

            ConnectionHandler.verify();

        } else if (state == GAME_STATE_PREPARAR){
            mainActivity.hideAdView();
            activateFrame(500);
            Levels.eraseAllTutorials();
            Levels.levelObject.loadEntities();
            int musicNumber = Levels.currentLevelNumber - ((int)Math.floor(Levels.currentLevelNumber / 7)*Levels.currentLevelNumber);
            if (musicNumber == 1){
                Sound.music = MediaPlayer.create(context, R.raw.music1);
            } else if (musicNumber == 2){
                Sound.music = MediaPlayer.create(context, R.raw.music2);
            } else if (musicNumber == 3){
                Sound.music = MediaPlayer.create(context, R.raw.music3);
            } else if (musicNumber == 4){
                Sound.music = MediaPlayer.create(context, R.raw.music4);
            } else if (musicNumber == 5){
                Sound.music = MediaPlayer.create(context, R.raw.music5);
            } else if (musicNumber == 6){
                Sound.music = MediaPlayer.create(context, R.raw.music6);
            } else{
                Sound.music = MediaPlayer.create(context, R.raw.music7);
            }

            Sound.music.setVolume(0.006f* (float) volume, 0.006f* (float) volume);
            Sound.music.setLooping(true);
            // cria a animação de preparação;
            ArrayList<float[]> values = new ArrayList<>();
                values.add(new float[]{0f,3f});
                values.add(new float[]{0.25f,2f});
                values.add(new float[]{0.5f,1f});
                values.add(new float[]{0.75f,0f});
            final Text innerMessagePreparation = messagePreparation;
            messagePreparation.setText("3");
            messagePreparation.display();
            Sound.play(Sound.soundCounter, 1, 1, 0);

            Animation anim = new Animation(messagePreparation, "messagePreparation", "numberForAnimation", 4000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 2f){
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {
                        innerMessagePreparation.setText("GO!");
                        Animation anim = Utils.createSimpleAnimation(innerMessagePreparation, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                innerMessagePreparation.clearDisplay();
                                innerMessagePreparation.alpha = 1f;
                                Game.setGameState(GAME_STATE_JOGAR);
                            }
                        });
                        anim.start();
                    }
                }
            });
            anim.start();
            verifyDead();

        } else if (state == GAME_STATE_JOGAR){
            if (musicOn) {
                Log.e("game", "musicOn");
                Sound.music.start();
            }

            for (int i = 0; i < bars.size(); i++) {
                if (bars.get(i).scaleVariationData != null){
                    bars.get(i).initScaleVariation();
                }
            }

            for (int i = 0; i < obstacles.size(); i++) {
                if (obstacles.get(i).scaleVariationData != null){
                    obstacles.get(i).initScaleVariation();
                }
                if (obstacles.get(i).positionVariationData != null){
                    obstacles.get(i).initPositionVariation();
                }
            }

            resetTimeForPointsDecay();

            freeAllGameEntities();
        } else if (state == GAME_STATE_DERROTA){
            stopAndReleaseMusic();
            Sound.play(Sound.soundGameOver, 1, 1, 0);

            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuInGame.appearAndUnblock(1000);
            messageGameOver.display();

            if (scorePanel.value > 0) {
                scorePanel.showMessage("-50%", 1000);
                int points = scorePanel.value / 2;
                scorePanel.setValue(points, true, 1000, true);
                if (Storage.getLevelMaxScore(Levels.currentLevelNumber) < points) {
                    Storage.setLevelMaxScore(Levels.currentLevelNumber, points);
                    setMaxScoreTotal();
                }

            }

        } else if (state == GAME_STATE_PAUSE){
            Sound.music.pause();
            Log.e("game", "ativando game_state_pause");
            Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuInGame.appearAndUnblock(300);
            menuInGame.getMenuOptionByName("Continuar").textObject.setText(context.getResources().getString(R.string.continuarJogar));

            ArrayList<float[]> valuesAnimPause = new ArrayList<>();
            valuesAnimPause.add(new float[]{0f,1f});
            valuesAnimPause.add(new float[]{0.25f,2f});
            valuesAnimPause.add(new float[]{0.7f,3f});
            Animation anim = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 4000, valuesAnimPause, true, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (messageInGame.numberForAnimation == 1f){
                        messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (messageInGame.numberForAnimation == 2f) {
                        messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (messageInGame.numberForAnimation == 3f) {
                        messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    }
                }
            });
            anim.start();

            messageInGame.setText(context.getResources().getString(R.string.pause));
            messageInGame.increaseAlpha(100, 1f);
            messageInGame.y = gameAreaResolutionY*0.25f;
            messageInGame.display();
        } else if (state == GAME_STATE_VITORIA){
            stopAndReleaseMusic();
            Sound.play(Sound.soundWin, 1, 1, 0);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);

            Utils.createSimpleAnimation(bordaB, "translateVitoria", "translateY", 2000, 0f, resolutionY - gameAreaResolutionY).start();
            if (button1Left != null) {
                Utils.createSimpleAnimation(button1Left, "alphaVitoria", "alpha", 1000, button1Left.alpha, 0f).start();
                button1Left.block();
            }
            if (button2Right != null) {
                Utils.createSimpleAnimation(button2Right, "alphaVitoria", "alpha", 1000, button2Right.alpha, 0f).start();
                button2Right.block();
            }
            if (button2Left != null) {
                Utils.createSimpleAnimation(button2Left, "alphaVitoria", "alpha", 1000, button2Left.alpha, 0f).start();
                button2Left.block();
                Utils.createSimpleAnimation(button1Right, "alphaVitoria", "alpha", 1000, button1Right.alpha, 0f).start();
                button1Right.block();
            }
            Utils.createSimpleAnimation(buttonMusic, "alphaVitoria", "alpha", 1000, buttonMusic.alpha,0f).start();
            buttonMusic.block();

            Utils.createSimpleAnimation(buttonSound, "alphaVitoria", "alpha", 1000, buttonSound.alpha, 0f).start();
            buttonSound.block();

            ArrayList<float[]> valuesAnimVitoria = new ArrayList<>();
            valuesAnimVitoria.add(new float[]{0f,1f});
            valuesAnimVitoria.add(new float[]{0.2f,2f});
            valuesAnimVitoria.add(new float[]{0.5f,3f});
            valuesAnimVitoria.add(new float[]{0.7f,4f});
            Animation anim = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 3000, valuesAnimVitoria, true, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (messageInGame.numberForAnimation == 1f){
                        messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (messageInGame.numberForAnimation == 2f) {
                        messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (messageInGame.numberForAnimation == 3f) {
                        messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    } else if (messageInGame.numberForAnimation == 4f) {
                        messageInGame.setColor(new Color(1f, 0f, 0f, 1f));
                    }
                }
            });
            anim.start();



            // verifica a quantidade de bolas azuis, e atualiza a pontuação
            final Timer timer = new Timer();
            final TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (objectivePanel.blueBalls > 0){
                        int points = (int)((float)scorePanel.value * 1.5f);
                        scorePanel.setValue(points, true, 1000, true);
                        scorePanel.showMessage("x2", 800);
                        objectivePanel.explodeBlueBall();

                    } else {
                        if (Storage.getLevelMaxScore(Levels.currentLevelNumber) < scorePanel.value){
                            Storage.setLevelMaxScore(Levels.currentLevelNumber, scorePanel.value);
                            setMaxScoreTotal();
                            // TODO se for o último level não aumentar o nível
                            changeLevel(Levels.currentLevelNumber + 1);
                        }
                        if (Game.menuWin.isBlocked) {
                            Game.menuWin.appearAndUnblock(800);
                        }

                        cancel();
                    }
                }
            };

            timer.scheduleAtFixedRate(timerTask, 2000, 2000);

            ArrayList<float[]> valuesAnimVitoriaTranslate = new ArrayList<>();
            valuesAnimVitoriaTranslate.add(new float[]{0f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{0.8f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{1f,0f});
            new Animation(messageInGame, "messageInGameTranslateX", "translateX", 2000, valuesAnimVitoriaTranslate, false, true).start();

            //messageRankingWin.setText(context.getResources().getString(R.string.rankingObtidoAposVitoria)+ " 2222");
            menuWin.alpha = 0f;
            messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Sound.play(Sound.soundTextBoxAppear, 1, 1, 0);
                    if (objectivePanel.blueBalls == 0){
                        Game.menuWin.appearAndUnblock(800);
                    }
                }
            });

            messageInGame.setText(context.getResources().getString(R.string.nivelConcluido1)+ " "+Levels.currentLevelNumber+ " "+context.getResources().getString(R.string.nivelConcluido2));
            messageInGame.y = gameAreaResolutionY*0.25f;
            messageInGame.display();

            Utils.createSimpleAnimation(objectivePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY*0.2f).start();
            Utils.createSimpleAnimation(objectivePanel, "scaleX", "scaleX", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(objectivePanel, "scaleY", "scaleY", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(scorePanel, "scaleX", "scaleX", 2000, 1f, 2f).start();
            Utils.createSimpleAnimation(scorePanel, "scaleY", "scaleY", 2000, 1f, 2f).start();
            Utils.createSimpleAnimation(scorePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY * 0.1f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                    float initialTranslateY = - Game.gameAreaResolutionY * 0.1f;

                    ArrayList<float[]> valuesAnimScoreTX = new ArrayList<>();
                    valuesAnimScoreTX.add(new float[]{0f,0f});
                    valuesAnimScoreTX.add(new float[]{0.3f,-10f});
                    valuesAnimScoreTX.add(new float[]{0.7f,20f});
                    valuesAnimScoreTX.add(new float[]{1f,0f});
                    new Animation(Game.scorePanel, "animScoreTX", "translateX", 30000, valuesAnimScoreTX, true, true).start();

                    ArrayList<float[]> valuesAnimScoreTY = new ArrayList<>();
                    valuesAnimScoreTY.add(new float[]{0f,initialTranslateY});
                    valuesAnimScoreTY.add(new float[]{0.2f,initialTranslateY + 5f});
                    valuesAnimScoreTY.add(new float[]{0.7f,initialTranslateY -20f});
                    valuesAnimScoreTY.add(new float[]{1f,initialTranslateY});
                    new Animation(Game.scorePanel, "animScoreTY", "translateY", 12000, valuesAnimScoreTY, true, true).start();
                }
                }
            ).start();
        } else if (state == GAME_STATE_TUTORIAL) {
            mainActivity.hideAdView();
            activateFrame(500);
            Levels.levelObject.loadEntities();
            verifyDead();
        }
    }

    public static void stopAndReleaseMusic(){
        if (Sound.music != null) {
            Sound.music.stop();
            Sound.music.release();
            Sound.music = null;
        }
    }

    private static void freeAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = true;
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

    private static void stopAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = false;
            b.clearParticles();
        }
        for (Bar b : bars){
            b.isMovable = false;
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

    private static void increaseAllGameEntitiesAlpha(int duration){
        for (Entity e : collectAllGameEntities()){
            e.increaseAlpha(duration, 1f);
        }
    }

    private static void reduceAllGameEntitiesAlpha(int duration){
        for (Entity e : collectAllGameEntities()){
            e.reduceAlpha(duration, 0.2f);
        }
    }

    public static void eraseAllGameEntities() {
        balls.clear();
        bars.clear();
        targets.clear();
        windows.clear();
        specialBalls.clear();
        obstacles.clear();
        wind = null;
    }

    public static void eraseAllHudEntities() {
        objectivePanel = null;
        scorePanel = null;
        button1Left = null;
        button1Right = null;
        button2Left = null;
        button2Right = null;
        buttonSound = null;
        buttonMusic = null;
        background = null;
    }



    public static void setBottomText(String text){

        float previousPosition = bottomTextBox.y;
        String previousText = bottomTextBox.text;

        if (previousText.equals("...")|| previousText.equals("")){
            previousPosition = resolutionY * 2;
        }

        boolean appearOrDesapear = false;
        if (!text.equals("")){
            if (bottomTextBox.y == resolutionY*2 || !bottomTextBox.isVisible){
                appearOrDesapear = true;
            }

            bottomTextBox.setText(text);
            bottomTextBox.display();
            bottomTextBox.setPositionY(resolutionY - bottomTextBox.height);
            bottomTextBox.isBlocked = false;

            messageCurrentLevel.y = resolutionY - bottomTextBox.height - (resolutionY * 0.14f);
            messageMaxScoreTotal.y = resolutionY - bottomTextBox.height - (resolutionY * 0.08f);
        } else {
            if (!previousText.equals("...")){
                appearOrDesapear = true;
            }
            bottomTextBox.setText("...");
            bottomTextBox.isBlocked = true;
            bottomTextBox.setPositionY(resolutionY*2);
            messageCurrentLevel.y = resolutionY - (resolutionY * 0.14f);
            messageMaxScoreTotal.y = resolutionY - (resolutionY * 0.08f);
        }

        float difference = previousPosition - bottomTextBox.y;
        if (difference != 0) {
            Utils.createSimpleAnimation(bottomTextBox, "translateY", "translateY", 200, difference, 0.0f).start();
        }

        if (appearOrDesapear){
            //Sound.play(Sound.soundTextBoxAppear, 0.8f, 0.8f, 0);
        }



    }

    public static ArrayList<Entity> collectAllMenuEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(menuMain);
        list.add(menuOptions);
        list.add(selectorLevel);
        list.add(selectorDificulty);
        list.add(selectorMusic);
        list.add(selectorSound);
        list.add(selectorLevel);
        list.add(menuInGame);
        list.add(menuWin);
        list.add(menuTutorial);
        list.add(tittle);
        list.add(messageGameOver);
        list.add(messagePreparation);
        list.add(messageInGame);
        list.add(messageCurrentLevel);
        list.add(messageMaxScoreTotal);
        list.add(bottomTextBox);
        return list;
    }

    public static ArrayList<Entity> collectAllHudEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(button1Left);
        list.add(button1Right);
        list.add(buttonSound);
        list.add(buttonMusic);
        list.add(button2Left);
        list.add(button2Right);
        list.add(scorePanel);
        list.add(objectivePanel);
        return list;
    }

    public static ArrayList<Entity> collectAllGameEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.addAll(balls);
        list.addAll(bars);
        list.addAll(targets);
        list.addAll(obstacles);
        list.addAll(windows);
        list.addAll(specialBalls);
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
    }

    public static void clearAllHudEntities(){
        ArrayList<Entity> list = collectAllHudEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    public static void clearAllGameEntities(){
        ArrayList<Entity> list = collectAllGameEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    public static void setMaxScoreTotal(){
        int scoreTotal = getMaxScoreTotal();
        GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), scoreTotal);
        maxScoreTotal = scoreTotal;
    }

    public static int getMaxScoreTotal(){
        int scoreTotal = 0;
        for (int i = 0; i < Levels.maxNumberOfLevels; i++){
            scoreTotal += Storage.getLevelMaxScore(i+1);
        }
        maxScoreTotal = scoreTotal;
        Log.e("Game", "score total retornando após calculo "+ scoreTotal);
        return scoreTotal;
    }


    public static void changeDifficulty(int selectedValue) {
        if (selectedValue == 0){
            basePoints = POINTS_EASY;
            currentDifficulty = DIFFICULTY_EASY;
            difficultyVelocityBarMultiplicator = BAR_EASY;
            difficultyVelocityObstacleMultiplicator = OBSTACLE_EASY;
            difficultyVelocityBallMultiplicator = BALL_EASY;
        } else if (selectedValue == 1){
            basePoints = POINTS_NORMAL;
            currentDifficulty = DIFFICULTY_NORMAL;
            difficultyVelocityBarMultiplicator = BAR_NORMAL;
            difficultyVelocityObstacleMultiplicator = OBSTACLE_NORMAL;
            difficultyVelocityBallMultiplicator = BALL_NORMAL;
        } else if (selectedValue == 2){
            basePoints = POINTS_HARD;
            currentDifficulty = DIFFICULTY_HARD;
            difficultyVelocityBarMultiplicator = BAR_HARD;
            difficultyVelocityObstacleMultiplicator = OBSTACLE_HARD;
            difficultyVelocityBallMultiplicator = BALL_HARD;
        } else if (selectedValue == 3){
            basePoints = POINTS_INSANE;
            currentDifficulty = DIFFICULTY_INSANE;
            difficultyVelocityBarMultiplicator = BAR_INSANE;
            difficultyVelocityObstacleMultiplicator = OBSTACLE_INSANE;
            difficultyVelocityBallMultiplicator = BALL_INSANE;
        }
        Storage.setDificulty(selectedValue);
    }

    private static void changeLevel(int level) {
        Levels.currentLevelNumber = level;
        // alterar texto que mostra o level
        messageCurrentLevel.setText(
            context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(Levels.currentLevelNumber) + " - "+
                    context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(Levels.currentLevelNumber))
        );
        menuMain.getMenuOptionByName("IniciarJogo").setText(context.getResources().getString(R.string.menuPrincipalIniciar) + " "+Levels.currentLevelNumber);
        Storage.setActualLevel(level);
    }

    public static void simulate(long elapsed, float frameDuration){
        
        ballCollidedFx -= 1;
        
        // atualiza posição da bola
        if (gameState == GAME_STATE_JOGAR) {
             for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                ball.verifyAcceleration();
                ball.vx = (ball.dvx * (float) elapsed) / frameDuration;
                ball.vy = (ball.dvy * (float) elapsed) / frameDuration;

                ball.translate(ball.vx, ball.vy);
                
                ball.verifyWind();
                 //Log.e("game", "ballv "+ ball.vx+" "+ball.vy);
            }

            for (int i = 0; i < specialBalls.size(); i++) {
                SpecialBall specialBall = specialBalls.get(i);
                specialBall.vx = (specialBall.dvx * (float) elapsed) / frameDuration;
                specialBall.vy = (specialBall.dvy * (float) elapsed) / frameDuration;

                //Log.e("game", "specialBall "+ specialBall.vx+" "+specialBall.vy);
                specialBall.translate(specialBall.vx, specialBall.vy);
            }
        }

        // atualiza posição da barra
        if (gameState == GAME_STATE_JOGAR || gameState == GAME_STATE_TUTORIAL) {
            if (bars != null) {
                if (bars.size() == 1) {
                    if (button1Left.isPressed && !button2Right.isPressed) {
                        bars.get(0).vx = -(bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else if (button2Right.isPressed && !button1Left.isPressed) {
                        bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).vx = 0f;
                    }
                    bars.get(0).translate(bars.get(0).vx, 0);
                    bars.get(0).verifyWind();
                }
                if (bars.size() == 2) {
                    if (button1Left.isPressed && !button1Right.isPressed) {
                        bars.get(0).vx = -(bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else if (button1Right.isPressed && !button1Left.isPressed) {
                        bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).vx = 0f;
                    }
                    bars.get(0).translate(bars.get(0).vx, 0);
                    bars.get(0).verifyWind();

                    if (button2Left.isPressed) {
                        bars.get(1).vx = -(bars.get(1).dvx * (float) elapsed) / frameDuration;
                    } else if (button2Right.isPressed) {
                        bars.get(1).vx = (bars.get(1).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(1).vx = 0f;
                    }

                    bars.get(1).translate(bars.get(0).vx, 0);
                    bars.get(1).verifyWind();
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
        verifyTransformations();

        if (gameState == GAME_STATE_JOGAR || gameState == GAME_STATE_TUTORIAL) {
            // atualiza posição das windows
            for (int i = 0; i < windows.size(); i++){
                if (windows.get(i).isActive){
                    windows.get(i).vx = (windows.get(i).dvx * (float) elapsed) / frameDuration;
                    windows.get(i).move();
                }
            }

            // insere as entidades no quadtree
            for (int i = 0; i < balls.size(); i++) {
                quad.insert(balls.get(i));
                balls.get(i).clearCollisionData();
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
                    if ((int) (Utils.getTime() - balls.get(i).initialTimeWaitingExplosion) > balls.get(i).timeForExplode
                            && balls.get(i).y < gameAreaResolutionY * 0.8f) {

                        balls.get(i).radius *= 5;
                        ArrayList<PhysicalObject> ball = new ArrayList<>();
                        ball.add(balls.get(i));
                        boolean collision = Collision.checkCollision(ball, quad, 0, false, false);
                        balls.get(i).radius /= 5;
                        if (!collision){
                            balls.get(i).explode();
                        }
                        balls.get(i).clearCollisionData();
                    }
                }
            }

        }
        
        // verifica a colisão da barra
        if (gameState == GAME_STATE_JOGAR || gameState == GAME_STATE_TUTORIAL) {
            for (int i = 0; i < 2; i++) {
                Collision.checkCollision(bars, quad, Game.BORDA_WEIGHT, true, true);
                Collision.checkCollision(bars, quad, Game.BAR_WEIGHT, true, true);
                Collision.checkCollision(obstacles, quad, Game.BORDA_WEIGHT, true, true);
                Collision.checkCollision(obstacles, quad, Game.BAR_WEIGHT, true, true);
                Collision.checkCollision(obstacles, quad, Game.OBSTACLES_WEIGHT, true, true);
                Collision.checkCollision(balls, quad, Game.BORDA_WEIGHT, true, true);
                Collision.checkCollision(balls, quad, Game.BAR_WEIGHT, true, true);
                Collision.checkCollision(balls, quad, Game.OBSTACLES_WEIGHT, true, true);
                Collision.checkCollision(balls, quad, Game.BALL_WEIGHT, true, true);
            }
            quad.clear();
        }
        
        // limpa o quadtree, preparando o próximo frame
        if (gameState == GAME_STATE_JOGAR || gameState == GAME_STATE_TUTORIAL) {
            quad.clear();
        }

        // se a bola colidiu, faz o necessário
        if (gameState == GAME_STATE_JOGAR) {
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).isCollided) {
                    balls.get(i).onCollision();
                }
            }
        }

        // toma as medidas finais
        // move o back
        // verifica se morreu
        // verifica se está vivo
        // verifica se o score deve ser reduzido
        if (gameState == GAME_STATE_JOGAR) {
            background.move(1);
            verifyDead();
            verifyPointsDecay();
        } else if(gameState == GAME_STATE_VITORIA){
            background.move(3);
        }
        if (gameState == GAME_STATE_JOGAR || gameState == GAME_STATE_TUTORIAL) {
            verifyWin();
        }
    }

    public static void verifyPointsDecay(){
        long time = Utils.getTime();
        if ((time - initialTimePointsDecay)>TIME_FOR_POINTS_DECAY){
            if (scorePanel.value > POINTS_DECAY) {
                initialTimePointsDecay = time;
                int value = scorePanel.value - POINTS_DECAY;
                scorePanel.setValue(value, false, 0, false);
            }
        }
    }

    public static void resetTimeForPointsDecay(){
        initialTimePointsDecay = Utils.getTime();
    }

    public static void verifyWin() {
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
        if (win) setGameState(GAME_STATE_VITORIA);
    }

    public static void verifyDead() {
        ballsNotInvencibleAlive = 0;
        ballsInvencible = 0;
        for (Ball b : balls){
            if (b.isAlive){
                if (!b.isInvencible){
                    ballsNotInvencibleAlive += 1;
                } else {
                    ballsInvencible += 1;
                }
            }
        }
        objectivePanel.setValues(ballsNotInvencibleAlive + ballsInvencible, Levels.levelObject.minBallsAlive, ballsInvencible);
        if (Levels.levelObject.minBallsAlive > ballsNotInvencibleAlive){
            setGameState(GAME_STATE_DERROTA);
        }
    }
    
    public static void verifyTransformations(){

        if (wind != null){
            wind.checkTransformations(true);
        }

        for (int i = 0; i < balls.size(); i++){
            if (balls.get(i).ballParticleGenerator != null){
                balls.get(i).ballParticleGenerator.checkTransformations(true);
            }
        }

        for (int i = 0; i < balls.size(); i++){
            balls.get(i).checkTransformations(true);
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

        for (int i = 0; i < targets.size(); i++){
            if (targets.get(i).showPointsState == Entity.SHOW_POINTS_ON){
                targets.get(i).checkTransformations(true);
            }
        }

        for (int i = 0; i < particleGenerator.size(); i++){
            particleGenerator.get(i).checkTransformations(true);
        }

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).checkTransformations(true);
        }

        for (int i = 0; i < specialBalls.size(); i++){
            specialBalls.get(i).checkTransformations(true);
        }

        if (menuMain != null) menuMain.checkTransformations(true);
        if (menuInGame != null) menuInGame.checkTransformations(true);
        if (menuWin != null) menuWin.checkTransformations(true);
        if (menuTutorial != null) menuTutorial.checkTransformations(true);
        if (selectorLevel != null) selectorLevel.checkTransformations(true);

        if (menuOptions != null)menuOptions.checkTransformations(true);
        if (selectorDificulty != null)selectorDificulty.checkTransformations(true);
        if (selectorMusic != null)selectorMusic.checkTransformations(true);
        if (selectorSound != null)selectorSound.checkTransformations(true);


        if (tittle != null) tittle.checkTransformations(true);

        if (gameState == GAME_STATE_TUTORIAL){
            if (Levels.levelObject.tutorials.size() >  Levels.levelObject.showingTutorial){
                Levels.levelObject.tutorials.get(Levels.levelObject.showingTutorial).textBox.checkTransformations(true);
            }
        }

        messageGameOver.checkTransformations(true);
        messagePreparation.checkTransformations(true);
        messageInGame.checkTransformations(true);
        messageCurrentLevel.checkTransformations(true);
        messageMaxScoreTotal.checkTransformations(true);
        bottomTextBox.checkTransformations(true);

        if (bordaE != null)bordaE.checkTransformations(true);
        if (bordaD != null)bordaD.checkTransformations(true);
        if (bordaC != null)bordaC.checkTransformations(true);
        if (bordaB != null)bordaB.checkTransformations(true);

        if (frame != null)frame.checkTransformations(true);

        if (scorePanel != null) scorePanel.checkTransformations(true);
        if (objectivePanel != null) objectivePanel.checkTransformations(true);

        if (button1Left != null) button1Left.checkTransformations(true);
        if (button1Right != null) button1Right.checkTransformations(true);
        if (button2Left != null) button2Left.checkTransformations(true);
        if (button2Right != null) button2Right.checkTransformations(true);

        if (buttonMusic != null) buttonMusic.checkTransformations(true);
        if (buttonSound != null) buttonSound.checkTransformations(true);

        for (int i = 0; i < messages.size(); i++){
            messages.get(i).checkTransformations(true);
        }
    }

    public static void render(float[] matrixView, float[] matrixProjection){
        if (background != null) {
            background.prepareRender(matrixView, matrixProjection);
        }
        
        for (int i = 0; i < balls.size(); i++){
            if (balls.get(i).ballParticleGenerator != null){
                balls.get(i).ballParticleGenerator.prepareRender(matrixView, matrixProjection);
            }
        }
    
        for (int i = 0; i < balls.size(); i++){
            balls.get(i).prepareRender(matrixView, matrixProjection);
        }
    
        for (int i = 0; i < targets.size(); i++){
            targets.get(i).prepareRender(matrixView, matrixProjection);
        }
    
        for (int i = 0; i < bars.size(); i++){
            bars.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < obstacles.size(); i++){
            obstacles.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < targets.size(); i++){
            if (targets.get(i).showPointsState == Entity.SHOW_POINTS_ON){
                targets.get(i).renderPoints(matrixView, matrixProjection);
            }
        }

        for (int i = 0; i < particleGenerator.size(); i++){
            particleGenerator.get(i).prepareRender(matrixView, matrixProjection);
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
        
        if (menuMain != null) menuMain.prepareRender(matrixView, matrixProjection);
        if (menuInGame != null) menuInGame.prepareRender(matrixView, matrixProjection);
        if (menuWin != null) menuWin.prepareRender(matrixView, matrixProjection);
        if (menuTutorial != null) menuTutorial.prepareRender(matrixView, matrixProjection);
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);

        if (menuOptions != null)menuOptions.prepareRender(matrixView, matrixProjection);
        if (selectorDificulty != null)selectorDificulty.prepareRender(matrixView, matrixProjection);
        if (selectorMusic != null)selectorMusic.prepareRender(matrixView, matrixProjection);
        if (selectorSound != null)selectorSound.prepareRender(matrixView, matrixProjection);


        if (tittle != null) {
            //Log.e("game", "render tittle");
            tittle.prepareRender(matrixView, matrixProjection);
        }

        if (gameState == GAME_STATE_TUTORIAL){
            if (Levels.levelObject.tutorials.size() >  Levels.levelObject.showingTutorial){
                Levels.levelObject.tutorials.get(Levels.levelObject.showingTutorial).textBox.prepareRender(matrixView, matrixProjection);
            }
        }

        messageGameOver.prepareRender(matrixView, matrixProjection);
        messagePreparation.prepareRender(matrixView, matrixProjection);
        messageInGame.prepareRender(matrixView, matrixProjection);
        messageCurrentLevel.prepareRender(matrixView, matrixProjection);
        messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);
        bottomTextBox.prepareRender(matrixView, matrixProjection);

        if (bordaE != null)bordaE.prepareRender(matrixView, matrixProjection);
        if (bordaD != null)bordaD.prepareRender(matrixView, matrixProjection);
        if (bordaC != null)bordaC.prepareRender(matrixView, matrixProjection);
        if (bordaB != null)bordaB.prepareRender(matrixView, matrixProjection);

        if (scorePanel != null) scorePanel.prepareRender(matrixView, matrixProjection);
        if (objectivePanel != null) objectivePanel.prepareRender(matrixView, matrixProjection);

        if (button1Left != null) button1Left.prepareRender(matrixView, matrixProjection);
        if (button1Right != null) button1Right.prepareRender(matrixView, matrixProjection);
        if (button2Left != null) button2Left.prepareRender(matrixView, matrixProjection);
        if (button2Right != null) button2Right.prepareRender(matrixView, matrixProjection);

        if (buttonMusic != null) buttonMusic.prepareRender(matrixView, matrixProjection);
        if (buttonSound != null) buttonSound.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < messages.size(); i++){
            messages.get(i).prepareRender(matrixView, matrixProjection);
        }

        if (frame != null)frame.prepareRender(matrixView, matrixProjection);
    }

    public static void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents.size() == 0){
                isBlocked = false;
            }
       }
    }

    public static void verifyListeners() {
        if (!isBlocked) {
            for (int i = 0; i < interactionListeners.size(); i++) {
                interactionListeners.get(i).verify();
            }
        }
        if (menuMain != null) menuMain.verifyListener();
        if (menuInGame != null) menuInGame.verifyListener();
        if (menuWin != null) menuWin.verifyListener();
        if (menuTutorial != null) menuTutorial.verifyListener();
        if (selectorLevel != null) selectorLevel.verifyListener();
        if (menuOptions != null)menuOptions.verifyListener();
        if (selectorDificulty != null)selectorDificulty.verifyListener();
        if (selectorMusic != null)selectorMusic.verifyListener();
        if (selectorSound != null)selectorSound.verifyListener();
        if (gameState == GAME_STATE_TUTORIAL){
            if (Levels.levelObject.tutorials.size() >  Levels.levelObject.showingTutorial){
                Levels.levelObject.tutorials.get(Levels.levelObject.showingTutorial).textBox.verifyListener();
            }
        }
        if (button1Left != null) button1Left.verifyListener();;
        if (button1Right != null) button1Right.verifyListener();
        if (button2Left != null) button2Left.verifyListener();
        if (button2Right != null) button2Right.verifyListener();
        if (buttonMusic != null) buttonMusic.verifyListener();
        if (buttonSound != null) buttonSound.verifyListener();
        if (bottomTextBox != null) bottomTextBox.verifyListener();
    }
}