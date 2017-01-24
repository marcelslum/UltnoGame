package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

//import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

// TODO verificar os listeners ativos
// TODO colocar timeout em todos os awaits...
// TODO colocar uma barra ao final do data panel


/** * Created by marcel on 01/08/2016.
 */
public class Game {

    static final String TAG = "Game";

    static MainActivity mainActivity;

    static boolean forInitGame;

    static int basePoints = 10;

    static int numberOfTutorials = 20;
    /*
    static float difficultyVelocityBarMultiplicator;
    static float difficultyVelocityObstacleMultiplicator;
    static float difficultyVelocityBallMultiplicator;
    static final float BAR_EASY = 0.9f;
    static final float BALL_EASY = 0.8f;
    static final float OBSTACLE_EASY = 0.8f;
    static final float BAR_NORMAL = 1f;
    static final float BALL_NORMAL = 1f;
    static final float OBSTACLE_NORMAL = 1f;
    static final float BAR_HARD = 1.3f;
    static final float BALL_HARD = 1.3f;
    static final float OBSTACLE_HARD = 1.2f;
    static final float BAR_INSANE = 1.5f;
    static final float BALL_INSANE = 1.5f;
    static final float OBSTACLE_INSANE = 1.4f;
    */

    static final int BALL_WEIGHT = 1;
    static final int BORDA_WEIGHT = 10;
    static final int OBSTACLES_WEIGHT = 7;
    static final int TARGET_WEIGHT = 10;
    static final int BAR_WEIGHT = 8;

    static int currentTutorial;
    static Tutorial currentTutorialObject;
    static Image tutorialImage;
    static TextBox tutorialTextBox;

    // entities
    static Menu menuMain;
    static Menu menuOptions;
    static Menu menuInGame;
    static Menu menuInGameOptions;
    static Menu menuGameOver;
    static Menu menuTutorial;
    static Menu menuObjectives;
    static Selector selectorLevel;
    static Selector selectorDificulty;
    static Selector selectorMusic;
    static Selector selectorSound;
    static Button buttonReturn;
    static Button buttonReturnObjectivesPause;
    static Button buttonContinue;
    static LevelGoalsPanel levelGoalsPanel;

    public static ArrayList<LevelsGroupData> levelsGroupData;

    static ArrayList<Target> targets;
    static ArrayList<Ball> balls;
    static ArrayList<Text> texts;
    static ArrayList<TouchEvent> touchEvents;
    static ArrayList<Bar> bars;
    static ArrayList<Obstacle> obstacles;
    static ArrayList<WindowGame> windows;
    static ArrayList<Menu> menus;
    static ArrayList<Selector> selectors;
    static ArrayList<InteractionListener> interactionListeners;
    static ArrayList<TextBox> textBoxes;
    static Messages messages;
    static ArrayList<Line> lines;
    static MessageStar messageStars;
    static MessageStarWin messageStarsWin;
    public static Background background;
    static Wind wind;
    static ArrayList<SpecialBall> specialBalls;

    static ScorePanel scorePanel;
    static BallDataPanel ballDataPanel;
    static BallGoalsPanel ballGoalsPanel;

    private static Edge bordaC;
    static Edge bordaE;
    private static Edge bordaD;
    static Edge bordaB;

    private static Rectangle frame;

    static Button button1Left;
    static Button button1Right;
    static Button button2Left;
    static Button button2Right;

    static Image tittle;

    private static Text messageMenu;
    private static Text messageGameOver;
    private static Text messagePreparation;
    private static Text messageInGame;
    private static Text messageMaxScoreTotal;
    private static Text messageConqueredStarsTotal;
    private static Image starForMessage;
    static Text messageSplash1;
    static Text messageSplash2;
    static Text messageTime;
    private static TextBox bottomTextBox;

    static long timeOfLevelPlay = 0;
    static int secondsOfLevelPlay = 0;
    static boolean timeOfLevelPlayBlocked = true;
    static long lastSeconds = 0;

    public static MenuIcon groupMenu;
    public static MenuIcon levelMenu;
    public static MenuIcon tutorialMenu;

    public static LevelsGroupData currentLevelsGroupDataSelected;
    
    static Image imageTutorialTop;
    static Image imageTutorialDown;

    // quadtree objects
    static Quadtree quad;

    // font
    public static Font font;

    // scree properties
    public static float gameAreaResolutionX;
    static float gameAreaResolutionY;
    static float resolutionX;
    static float resolutionY;
    static float screenOffSetX;
    static float screenOffSetY;

    static int ballCollidedFx = 0;

    // options
    public static boolean isBlocked;
    
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
    public final static int GAME_STATE_OPCOES_GAME =  19;
    public final static int GAME_STATE_SELECAO_GRUPO =  20;
    public final static int GAME_STATE_SELECAO_LEVEL =  21;
    public final static int GAME_STATE_OBJETIVO_LEVEL =  22;
    public final static int GAME_STATE_MENU_TUTORIAL =  24;
    public final static int GAME_STATE_INTERSTITIAL =  25;
    public final static int GAME_STATE_OBJETIVO_PAUSE =  26;

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
    public static final int POINTS_DECAY = 1;

    public static long maxScoreTotal;
    public static String currentPlayerId;
    public static int conqueredStarsTotal;

    private Game() {}

    public static void init(){
        Log.e("game", "init()");

        Splash.loaderConclude = false;
        MyAchievements.loaded = false;

        initPrograms();
        initFont();

        if (Texture.textures == null) {
            Texture.textures = new ArrayList<>();
        } else {
            Texture.textures.clear();
        }

        Texture.clear();
        Texture.textures.add(new Texture(Texture.TEXTURE_TITTLE, "drawable/tittle"));
        Texture.textures.add(new Texture(Texture.TEXTURE_FONT, "drawable/jetset"));

        Texture.init();

        Game.frame = new Rectangle("frame", 0f, 0f, Game.resolutionX, Game.resolutionY, -1, new Color(0f, 0f, 0f, 1f));
        Game.frame.clearDisplay();
        Game.frame.alpha = 0f;

        setGameState(Game.GAME_STATE_INTRO);
    }

    public static void activateFrame(int duration){
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
        balls = new ArrayList<>();
        obstacles = new ArrayList<>();
        windows = new ArrayList<>();
        specialBalls = new ArrayList<>();
        touchEvents = new ArrayList<>();
        texts = new ArrayList<>();
        interactionListeners = new ArrayList<>();
        bars = new ArrayList<>();
        menus = new ArrayList<>();
        selectors = new ArrayList<>();
        textBoxes = new ArrayList<>();
        messages = new Messages();
        lines = new ArrayList<> ();
    }

    public static void initEdges(){
        Game.bordaE = new Edge("bordaE", -999, 0, 1000, Game.resolutionY*2);
        Game.bordaD = new Edge("bordaD", Game.resolutionX-2, 0, 2000, Game.resolutionY*2);
        Game.bordaC = new Edge("bordaC",  1, -1000, Game.resolutionX-4, 1001);
        Game.bordaB = new Edge("bordaB", -1000, Game.resolutionY, Game.resolutionX*3, 1000);
    }

    public static void initTittle(){
        tittle = new Image("tittle",
                gameAreaResolutionX * 0.25f, gameAreaResolutionY * 0.2f,
                gameAreaResolutionX * 0.5f, gameAreaResolutionX * 0.47f * 0.3671875f,
                Texture.TEXTURE_TITTLE, 0f, 1f, 0.6228125f, 1f, new Color(0.5f, 0.2f, 0.8f, 1f));

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

        float messageStarsSize = gameAreaResolutionY*0.05f;
        messageStars = new MessageStar("messageStar", messageStarsSize, Game.resolutionX - (messageStarsSize * 1.4f), Game.resolutionX * 0.05f);

        messageStarsWin = new MessageStarWin("messageStar", messageStarsSize, Game.resolutionX * 0.5f, gameAreaResolutionY*0.25f);

        messageGameOver = new Text("messageGameOver",
                gameAreaResolutionX*0.5f, gameAreaResolutionY*0.2f, gameAreaResolutionY*0.17f,
                getContext().getResources().getString(R.string.messageGameOver), font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageMenu = new Text("messageMenu",
                gameAreaResolutionX*0.05f, gameAreaResolutionY*0.145f, gameAreaResolutionY*0.1f, ".", font, new Color(0.2f, 0.2f, 0.2f, 1f));

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
                getContext().getResources().getString(R.string.pause), font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);

        //messageCurrentLevel = new Text("messageCurrentLevel", resolutionX*0.05f, resolutionY*0.72f, resolutionY*0.036f, getContext().getResources().getString(messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(SaveGame.saveGame.currentLevelNumber) + " - " + getContext().getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1], font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                resolutionX*0.05f, resolutionY*0.84f, resolutionY*0.036f,
                getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(getMaxScoreTotal()), font, new Color(0f, 0f, 0f, 0.5f));

        messageConqueredStarsTotal = new Text("messageConqueredStarsTotal",
                resolutionX*0.9f, resolutionY*0.15f, resolutionY*0.05f,
                getContext().getResources().getString(R.string.messageConqueredStarsTotal) +"\u0020"+ NumberFormat.getInstance().format(conqueredStarsTotal), font, new Color(0f, 0f, 0f, 0.5f));

        starForMessage = new Image("frame", resolutionX*0.85f, resolutionY*0.15f, resolutionY*0.05f, resolutionY*0.05f, Texture.TEXTURE_BUTTONS_AND_BALLS, (0f + 1.5f)/1024f, (128f - 1.5f)/1024f, (0f + 1.5f)/1024f, (128f - 1.5f)/1024f);

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
    }

    public static void repositionSelectors(int gameState){
        if (gameState == GAME_STATE_OPCOES){
            MenuOption menuOptionMusicMain = menuOptions.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicMain.x + (menuOptionMusicMain.width*1.5f), menuOptionMusicMain.y);
            MenuOption menuOptionSoundMain = menuOptions.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundMain.x + (menuOptionSoundMain.width*2.2f), menuOptionSoundMain.y);

        } else if (gameState == GAME_STATE_OPCOES_GAME){
            MenuOption menuOptionMusicInGame = menuInGameOptions.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicInGame.x + ((menuOptionMusicInGame.width)*1.5f), menuOptionMusicInGame.y);
            MenuOption menuOptionSoundInGame = menuInGameOptions.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundInGame.x + (menuOptionSoundInGame.width*2.2f), menuOptionSoundInGame.y);
        }
    }

    public static void initButtons(){
        float buttonSize = resolutionX * 0.05f;
        buttonReturn = new Button("buttonReturn", buttonSize*0.5f, resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonReturn.setTextureMap(13);
        buttonReturn.textureMapUnpressed = 13;
        buttonReturn.textureMapPressed = 5;
        buttonReturn.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == GAME_STATE_SELECAO_LEVEL){setGameState(GAME_STATE_SELECAO_GRUPO);
                } else if (Game.gameState == GAME_STATE_SELECAO_GRUPO){setGameState(GAME_STATE_MENU);
                } else if (Game.gameState == GAME_STATE_OBJETIVO_LEVEL){setGameState(GAME_STATE_SELECAO_LEVEL);
                } else if (Game.gameState == GAME_STATE_MENU_TUTORIAL){setGameState(GAME_STATE_MENU);
                } else if (Game.gameState == GAME_STATE_OBJETIVO_PAUSE){setGameState(GAME_STATE_PAUSE);
                } else if (Game.gameState == GAME_STATE_TUTORIAL){currentTutorialObject.previous();}
            }
        });

        buttonReturnObjectivesPause = new Button("buttonReturnObjectivesPause", buttonSize*0.5f, gameAreaResolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonReturnObjectivesPause.setTextureMap(13);
        buttonReturnObjectivesPause.textureMapUnpressed = 13;
        buttonReturnObjectivesPause.textureMapPressed = 5;
        buttonReturnObjectivesPause.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == GAME_STATE_OBJETIVO_PAUSE){
                    setGameState(GAME_STATE_PAUSE);
                }
            }
        });

        buttonContinue = new Button("buttonContinue", resolutionX - buttonSize*1.5f, resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonContinue.setTextureMap(14);
        buttonContinue.textureMapUnpressed = 14;
        buttonContinue.textureMapPressed = 6;
        buttonContinue.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == GAME_STATE_VITORIA){
                    setGameState(GAME_STATE_INTERSTITIAL);
                } else if (Game.gameState == GAME_STATE_OBJETIVO_LEVEL){
                    LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                    if (!SaveGame.saveGame.tutorialsViwed[Level.levelObject.tutorialAttached]){
                        currentTutorial = Level.levelObject.tutorialAttached;
                        Game.setGameState(GAME_STATE_TUTORIAL);
                    } else {
                        Game.setGameState(GAME_STATE_PREPARAR);
                    }
                } else if (Game.gameState == GAME_STATE_TUTORIAL){
                        currentTutorialObject.next();
                }
            }
        });
    }

    public static int updateConqueredStars(){
        int numberOfStars = 0;
        for (int i = 0; i < SaveGame.saveGame.maxNumberOfLevels; i++){
            numberOfStars += SaveGame.saveGame.starsLevels[i];
        }
        conqueredStarsTotal = numberOfStars;
        if (messageConqueredStarsTotal != null) {
            messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                    "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal));
        }
        return numberOfStars;
    }

    public static int getLevelsConqueredStars(int minLevel, int maxLevel){
        int numberOfStars = 0;
        for (int i = 0; i < SaveGame.saveGame.maxNumberOfLevels; i++){
            if (i + 1 >= minLevel && i + 1 <= maxLevel) {
                numberOfStars += SaveGame.saveGame.starsLevels[i];
            }
        }
        return numberOfStars;
    }

    public static void initLevelsData(){

        levelsGroupData = new ArrayList<>();
        LevelsGroupData l = new LevelsGroupData("Início", 1, 3, 0, getLevelsConqueredStars(1, 3), Texture.TEXTURE_GROUP_ICONS, 1);
        l.addLevel("Nível 1", 1, Texture.TEXTURE_LEVEL_ICONS, 1);
        l.addLevel("Nível 2", 2, Texture.TEXTURE_LEVEL_ICONS, 2);
        l.addLevel("Nível 3", 3, Texture.TEXTURE_LEVEL_ICONS, 3);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Obstáculos", 4, 6, 7, getLevelsConqueredStars(4, 8), Texture.TEXTURE_GROUP_ICONS, 2);
        l.addLevel("Nível 4", 4, Texture.TEXTURE_LEVEL_ICONS, 4);
        l.addLevel("Nível 5", 5, Texture.TEXTURE_LEVEL_ICONS, 5);
        l.addLevel("Nível 6", 6, Texture.TEXTURE_LEVEL_ICONS, 6);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Cores", 7, 8, 10, getLevelsConqueredStars(4, 8), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 7", 7, Texture.TEXTURE_LEVEL_ICONS, 7);
        l.addLevel("Nível 8", 8, Texture.TEXTURE_LEVEL_ICONS, 8);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Explosão", 9, 11, 15, getLevelsConqueredStars(9, 11), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 9", 9, Texture.TEXTURE_LEVEL_ICONS, 9);
        l.addLevel("Nível 10", 10, Texture.TEXTURE_LEVEL_ICONS, 10);
        l.addLevel("Nível 11", 11, Texture.TEXTURE_LEVEL_ICONS, 11);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Roda", 12, 15, 20, getLevelsConqueredStars(12, 15), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 12", 12, Texture.TEXTURE_LEVEL_ICONS, 12);
        l.addLevel("Nível 13", 13, Texture.TEXTURE_LEVEL_ICONS, 13);
        l.addLevel("Nível 14", 14, Texture.TEXTURE_LEVEL_ICONS, 14);
        l.addLevel("Nível 15", 15, Texture.TEXTURE_LEVEL_ICONS, 15);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Elástico", 16, 18, 20, getLevelsConqueredStars(16, 18), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 16", 16, Texture.TEXTURE_LEVEL_ICONS, 1);
        l.addLevel("Nível 17", 17, Texture.TEXTURE_LEVEL_ICONS, 2);
        l.addLevel("Nível 18", 18, Texture.TEXTURE_LEVEL_ICONS, 3);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Vento", 19, 20, 25, getLevelsConqueredStars(19, 20), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 19", 19, Texture.TEXTURE_LEVEL_ICONS, 1);
        l.addLevel("Nível 20", 20, Texture.TEXTURE_LEVEL_ICONS, 2);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Fantasma", 21, 23, 30, getLevelsConqueredStars(21, 23), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 21", 21, Texture.TEXTURE_LEVEL_ICONS, 1);
        l.addLevel("Nível 22", 22, Texture.TEXTURE_LEVEL_ICONS, 2);
        l.addLevel("Nível 23", 23, Texture.TEXTURE_LEVEL_ICONS, 3);
        levelsGroupData.add(l);

        l = new LevelsGroupData("Invencibilidade", 24, 25, 35, getLevelsConqueredStars(24, 25), Texture.TEXTURE_GROUP_ICONS, 3);
        l.addLevel("Nível 24", 24, Texture.TEXTURE_LEVEL_ICONS, 1);
        l.addLevel("Nível 25", 25, Texture.TEXTURE_LEVEL_ICONS, 2);
        levelsGroupData.add(l);
    }

    public static void updateGroupMenu(){

        groupMenu.icons.clear();
        groupMenu.texts.clear();
        groupMenu.texts2.clear();
        groupMenu.innerTexts.clear();
        groupMenu.graph.clear();

        updateConqueredStars();

        if (SaveGame.saveGame.newGroupsSeen){
            if (SaveGame.saveGame.lastStars != conqueredStarsTotal){
                SaveGame.saveGame.newGroupsSeen = false;
            }
        }

        for (int i = 0; i < levelsGroupData.size(); i++){

            final LevelsGroupData lgd = levelsGroupData.get(i);

            if (conqueredStarsTotal >= lgd.starsToUnlock){
                groupMenu.addOption(i, lgd.textureUnit, lgd.textureMap, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {

                        if (!SaveGame.saveGame.newGroupsSeen){
                            SaveGame.saveGame.newGroupsSeen = true;
                            SaveGame.saveGame.lastStars = conqueredStarsTotal;
                        }

                        currentLevelsGroupDataSelected = lgd;
                        setGameState(GAME_STATE_SELECAO_LEVEL);
                    }
                }, false);

                groupMenu.addText(1, lgd.name, lgd.name, resolutionY * 0.04f, resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));
                groupMenu.addGraph("graph "+i, resolutionY * 0.06f, resolutionY * 0.015f, MenuIconGraph.TYPE_BAR);

                if (!SaveGame.saveGame.newGroupsSeen){
                    if (SaveGame.saveGame.lastStars < lgd.starsToUnlock){
                        groupMenu.addInnerText(lgd.name+"inner", Game.getContext().getResources().getString(R.string.novo), resolutionY * 0.035f, resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f));
                    }
                }

                int cStars = getLevelsConqueredStars(lgd.firstLevel, lgd.finalLevel);
                Log.e(TAG, "cStars of world "+ 1 + ": "+ cStars);
                int totalStarsToConquer = (lgd.finalLevel - lgd.firstLevel + 1) * 5;
                Log.e(TAG, "totalStarsToConquer of world "+ 1 + ": "+ totalStarsToConquer);
                float percentage = (float)cStars/(float)totalStarsToConquer;

                Log.e(TAG, "percentage of world "+ 1 + ": "+ percentage);

                groupMenu.graph.get(groupMenu.graph.size() - 1).setPercentage(percentage);
            }

            if (conqueredStarsTotal < lgd.starsToUnlock){

                groupMenu.addOption(i, lgd.textureUnit, lgd.textureMap, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        setBottomText(getContext().getResources().getString(R.string.message_sem_estrelas), 2000);
                    }
                }, true);

                groupMenu.icons.get(i).alpha = 0.2f;
                groupMenu.addText(1, lgd.name, lgd.name, resolutionY * 0.04f, resolutionY * 0.01f, new Color(0.7f, 0.7f, 0.7f, 1f));
                groupMenu.addText(2, lgd.name+"2", getContext().getResources().getString(R.string.tenha) + " " + lgd.starsToUnlock + " " + getContext().getResources().getString(R.string.estrelas), resolutionY * 0.03f, resolutionY * 0.07f, new Color(0.5f, 0.5f, 0.5f, 1f));
            }
        }
    }

    public static void updateLevelMenu(){
        levelMenu.icons.clear();
        levelMenu.texts.clear();
        levelMenu.texts2.clear();
        levelMenu.graph.clear();


        if (currentLevelsGroupDataSelected == null){
            currentLevelsGroupDataSelected = levelsGroupData.get(0);
        }


        for (int i = 0; i < currentLevelsGroupDataSelected.levelsData.size(); i++){
            final LevelsGroupData.LevelData ld = currentLevelsGroupDataSelected.levelsData.get(i);
            levelMenu.addOption(i, ld.textureUnit, ld.textureMap, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    SaveGame.saveGame.currentLevelNumber = ld.number;
                    setGameState(GAME_STATE_OBJETIVO_LEVEL);
                }
            }, false);

            levelMenu.addText(1, ld.name, ld.name,resolutionY * 0.04f, resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));
            levelMenu.addGraph("graph "+i, resolutionY * 0.06f, resolutionY * 0.015f, MenuIconGraph.TYPE_STARS);

            float percentage = 0f;
            float starsOfLevel = SaveGame.saveGame.starsLevels[ld.number-1];
            if (starsOfLevel == 1){
                percentage = 0.2f;
            } else if (starsOfLevel == 2){
                percentage = 0.4f;
            } else if (starsOfLevel == 3){
                percentage = 0.6f;
            } else if (starsOfLevel == 4){
                percentage = 0.8f;
            } else if (starsOfLevel == 5){
                percentage = 1f;
            }

            levelMenu.graph.get(i).setPercentage(percentage);
        }
    }

    public static void updateTutorialMenu(){
        tutorialMenu.icons.clear();
        tutorialMenu.texts.clear();
        tutorialMenu.texts2.clear();
        tutorialMenu.graph.clear();

        if (conqueredStarsTotal >= levelsGroupData.get(0).starsToUnlock){
            tutorialMenu.addOption(0, Texture.TEXTURE_TUTORIAL_ICONS, 1, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    currentTutorial = Tutorial.TUTORIAL_INSTRUCOES_INICIAIS;
                    setGameState(GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "instruções", getContext().getResources().getString(R.string.tutorial1Tittle),
                    resolutionY * 0.04f, resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));

            tutorialMenu.addOption(1, Texture.TEXTURE_TUTORIAL_ICONS, 2, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    currentTutorial = Tutorial.TUTORIAL_INICIO;
                    setGameState(GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "jogo", getContext().getResources().getString(R.string.tutorial2Tittle),
                    resolutionY * 0.04f, resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));
        }

        if (currentLevelsGroupDataSelected == null){
            currentLevelsGroupDataSelected = levelsGroupData.get(0);
        }


        for (int i = 0; i < currentLevelsGroupDataSelected.levelsData.size(); i++){
            final LevelsGroupData.LevelData ld = currentLevelsGroupDataSelected.levelsData.get(i);

            levelMenu.addGraph("graph "+i, resolutionY * 0.06f, resolutionY * 0.015f, MenuIconGraph.TYPE_STARS);

            float percentage = 0f;
            float starsOfLevel = SaveGame.saveGame.starsLevels[ld.number-1];
            if (starsOfLevel == 1){
                percentage = 0.2f;
            } else if (starsOfLevel == 2){
                percentage = 0.4f;
            } else if (starsOfLevel == 3){
                percentage = 0.6f;
            } else if (starsOfLevel == 4){
                percentage = 0.8f;
            } else if (starsOfLevel == 5){
                percentage = 1f;
            }

            levelMenu.graph.get(i).setPercentage(percentage);
        }
    }

    public static void initMenus(){

        float fontSize = gameAreaResolutionY*0.08f;

        groupMenu = new MenuIcon("groupMenu", 0f, resolutionY * 0.3f, resolutionY * 0.4f);
        updateGroupMenu();

        levelMenu = new MenuIcon("levelMenu", 0f, resolutionY * 0.3f, resolutionY * 0.4f);
        updateLevelMenu();

        tutorialMenu = new MenuIcon("tutorialMenu", 0f, resolutionY * 0.3f, resolutionY * 0.4f);

        // -------------------------------------------MENU OBJETIVOS
        //menuObjectives = new Menu("menuObjectives", resolutionX*0.5f, resolutionY*0.87f, fontSize, font);
        //menuObjectives.addMenuOption("jogar", getContext().getResources().getString(R.string.iniciar_jogo), new MenuOption.OnChoice() {@Override public void onChoice() {}});

        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", gameAreaResolutionX/2, gameAreaResolutionY*0.55f, fontSize, font);

        // cria o seletor de musica
        selectorMusic = new Selector("selectorMusic", 0f,0f, fontSize, "",
                new String[]{getContext().getResources().getString(R.string.desligado), getContext().getResources().getString(R.string.ligado)}, font);
        menuOptions.addMenuOption("music", getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorMusic.fromMenu(Game.menuOptions);
            }
        });

        if (SaveGame.saveGame.music) {
            selectorMusic.setSelectedValue(1);
        } else {
            selectorMusic.setSelectedValue(0);
        }

        selectorMusic.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (selectorMusic.selectedValue == 1){SaveGame.saveGame.music = true;} else {SaveGame.saveGame.music = false;}
            }
        });

        // cria o seletor de sons
        selectorSound = new Selector("selectorSound", 0f,0f, fontSize, "",
                new String[]{getContext().getResources().getString(R.string.desligado), getContext().getResources().getString(R.string.ligado)}, font);

        menuOptions.addMenuOption("sound", getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorSound.fromMenu(Game.menuOptions);
            }
        });

        if (SaveGame.saveGame.sound) {
            selectorSound.setSelectedValue(1);
        } else {
            selectorSound.setSelectedValue(0);
        }
        selectorSound.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (selectorSound.selectedValue == 1){
                    SaveGame.saveGame.sound = true;
                } else {
                    SaveGame.saveGame.sound = false;
                }
            }
        });

        menuOptions.addMenuOption("retornar", getContext().getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Log.e("findStateMenu", "11");
                setGameState(GAME_STATE_MENU);
            }
        });

        // -------------------------------------------MENU MAIN
        menuMain = new Menu("menuMain", gameAreaResolutionX/2, gameAreaResolutionY*0.55f, fontSize, font);

        // adiciona a opção de iniciar o jogo
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", getContext().getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();

                if (!SaveGame.saveGame.tutorialsViwed[0]){
                    currentTutorial = 0;
                    setGameState(GAME_STATE_TUTORIAL);
                } else {
                    setGameState(GAME_STATE_SELECAO_GRUPO);
                }

            }
        });

        // adiciona a opção de acessar as opções do jogo
        menuMain.addMenuOption("options", getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_OPCOES);
            }
        });

        menuMain.addMenuOption("conquistas", getContext().getResources().getString(R.string.conquistas), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showAchievements(mainActivity.mGoogleApiClient, mainActivity);
            }
        });

        menuMain.addMenuOption("ranking", getContext().getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showLeaderboards(mainActivity.mGoogleApiClient, mainActivity);
            }
        });

        menuMain.addMenuOption("tutorial", getContext().getResources().getString(R.string.tutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_MENU_TUTORIAL);

            }
        });

        // ----------------------------------------------------MENU GAME OVER
        menuGameOver = new Menu("menuGameOver",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuGameOver.addMenuOption("Continuar", getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuGameOver.block();
                Game.blockAndWaitTouchRelease();
                LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                Game.menuInGame.clearDisplay();
                Game.setGameState(GAME_STATE_PREPARAR);
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuGameOver.addMenuOption("RetornarAoMenuPrincipal", getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_INTERSTITIAL);
            }
        });


        // ----------------------------------------------------MENU IN GAME
        menuInGame = new Menu("menuInGame",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuInGame.addMenuOption("Continuar", getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_PAUSE){
                    Log.e("game", "menu continuar quando game state = GAME_STATE_PAUSE");
                    Game.increaseAllGameEntitiesAlpha(500);
                    Game.messageInGame.reduceAlpha(500,0f);
                    Game.menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Game.setGameState(GAME_STATE_JOGAR);
                        }
                    });
                }
            }
        });

        // adiciona a opção de mostrar objetivos
        menuInGame.addMenuOption("objetivos", getContext().getResources().getString(R.string.objetivos), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_PAUSE){
                    Game.setGameState(GAME_STATE_OBJETIVO_PAUSE);
                }
            }
        });

        // adiciona a opção de acessar as opções do jogo
        menuInGame.addMenuOption("options", getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_OPCOES_GAME);
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuInGame.addMenuOption("RetornarAoMenuPrincipal", getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_INTERSTITIAL);
            }
        });

        // ----------------------------------------------------MENU IN GAME OPTIONS
        menuInGameOptions = new Menu("menuInGameOptions",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        menuInGameOptions.addMenuOption("music", getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorMusic.fromMenu(Game.menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("sound", getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorSound.fromMenu(Game.menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("retornar", getContext().getResources().getString(R.string.retornar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_PAUSE);
            }
        });


    }

    public static void initPrograms(){
        imageProgram = new Program(Utils.readRawTextFile(Game.getContext(), R.raw.shader_vertex_text),
                Utils.readRawTextFile(Game.getContext(), R.raw.shader_frag_text));
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
        font = new Font(Texture.TEXTURE_FONT,textProgram);
    }
    static void addBall(Ball ball){
        balls.add(ball);
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
                //Log.e("game", " subtituindo listener "+listener.name);
                interactionListeners.set(i, listener);
                return;
            }
        }
        //Log.e("game", " adicionando listener "+listener.name);
        interactionListeners.add(listener);
    }
    public static int stopTimeOfLevelPlay(){
        timeOfLevelPlayBlocked = true;
        return secondsOfLevelPlay;
    }
    public static void resumeTimeOfLevelPlay(){
        timeOfLevelPlayBlocked = false;
    }
    public static void updateTimeOfLevelPlay(long timeElapsed){
        if (timeOfLevelPlayBlocked){
            return;
        }

        timeOfLevelPlay += timeElapsed;

        secondsOfLevelPlay = (int)((float)timeOfLevelPlay / 1000f);

        if (lastSeconds != secondsOfLevelPlay){
            lastSeconds = secondsOfLevelPlay;

            long displaySeconds;
            long displayMinutes = 0;

            if (secondsOfLevelPlay > 59) {
                displayMinutes = (long)((float)secondsOfLevelPlay/60f);
                displaySeconds = secondsOfLevelPlay % 60;

            } else {
                displaySeconds = secondsOfLevelPlay;
            }
            
            String displaySecondsString;
            String displayMinutesString;
            
            if (displayMinutes < 10){
                displayMinutesString = "0"+String.valueOf(displayMinutes);
            } else {
                displayMinutesString = String.valueOf(displayMinutes);
            }

            if (displaySeconds < 10){
                displaySecondsString = "0"+String.valueOf(displaySeconds);
            } else {
                displaySecondsString = String.valueOf(displaySeconds);
            }

            messageTime.setText(displayMinutesString+":"+displaySecondsString);
            if (messageTime.animTranslateX != 0){
                messageTime.clearAnimations();
            }
        }
    }
    public static void blockAndWaitTouchRelease(){isBlocked = true;}

    public static void setGameState(int state){
        Log.e("game", "set game state "+state);
        boolean sameState = false;
        int previousState = gameState;
        if (state == gameState){
            sameState = true;
        }

        repositionSelectors(state);
        gameState = state;
        Game.blockAndWaitTouchRelease();
        clearAllMenuEntities();
        if (messageStars != null) {
            messageStars.clearDisplay();
        }

        if (messageStarsWin != null) {
            messageStarsWin.clearDisplay();
        }


        timeOfLevelPlayBlocked = true;

        if (state == GAME_STATE_INTERSTITIAL){
            mainActivity.showInterstitial();
        } else if (state == GAME_STATE_OBJETIVO_LEVEL){
            Level.levelGoalsObject = new LevelGoals();
            Level.levelGoalsObject.levelGoals = LevelGoalsLoader.getLevelGoals(SaveGame.saveGame.currentLevelNumber);
            levelGoalsPanel = new LevelGoalsPanel("levelGoalsPanel", resolutionX * 0.24f, resolutionY * 0.2f, resolutionX * 0.025f, resolutionX * 0.79f);

            for (int i = 0; i < Level.levelGoalsObject.levelGoals.size(); i++){
                LevelGoal lg = Level.levelGoalsObject.levelGoals.get(i);
                levelGoalsPanel.addLine(lg.numberOfStars, true, lg.text);
            }
            levelGoalsPanel.appearGray();
            messageMenu.display();
            messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            buttonContinue.display();
            buttonContinue.unblock();
            buttonReturn.unblock();
            buttonReturn.display();
        } else if (state == GAME_STATE_OBJETIVO_PAUSE){
            levelGoalsPanel.appearGrayAndShine();
            messageMenu.display();
            messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            buttonReturnObjectivesPause.unblock();
            buttonReturnObjectivesPause.display();
        } else if (state == GAME_STATE_SELECAO_GRUPO) {
            updateGroupMenu();
            groupMenu.appear();
            messageMenu.display();
            messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoMundo));
            buttonReturn.display();
            buttonReturn.unblock();
            starForMessage.display();
            messageConqueredStarsTotal.display();
            messageConqueredStarsTotal.y = resolutionY * 0.15f;
            starForMessage.y = resolutionY * 0.15f;
        } else if (state == GAME_STATE_MENU_TUTORIAL){
            messageMenu.display();
            messageMenu.setText(getContext().getResources().getString(R.string.messageMenuTutorial));
            updateTutorialMenu();
            tutorialMenu.appear();
            buttonReturn.display();
            buttonReturn.unblock();
        } else if (state == GAME_STATE_SELECAO_LEVEL) {
            updateLevelMenu();
            levelMenu.appear();
            messageMenu.display();
            messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoLevel));
            buttonReturn.display();
            buttonReturn.unblock();
            starForMessage.display();
            messageConqueredStarsTotal.display();

            messageConqueredStarsTotal.y =  resolutionY*0.15f;
            starForMessage.y = resolutionY*0.15f;

        } else if (state == GAME_STATE_INTRO) {
            mainActivity.hideAdView();
            ConnectionHandler.internetState = ConnectionHandler.INTERNET_STATE_NOT_CONNECTED;
            Splash.timeInitIntro = Utils.getTime();
            Splash.init();
            Splash.display();
        } else if (state == GAME_STATE_OPCOES){
            tittle.display();
            menuOptions.appearAndUnblock(50);
        } else if (state == GAME_STATE_OPCOES_GAME){
            menuInGame.block();
            menuInGame.clearDisplay();
            menuInGameOptions.appearAndUnblock(100);
            messageInGame.y = gameAreaResolutionY*0.25f;
            messageInGame.display();
        } else if (state == GAME_STATE_MENU){
            ConnectionHandler.menuConnectionAttempts = 0;
            if (!sameState) {
                if (previousState != GAME_STATE_OPCOES) {
                    activateFrame(500);
                }
            }
            repositionSelectors(state);
            initTittle();
            mainActivity.showAdView();
            Game.bordaB.y = Game.resolutionY;
            menuOptions.block();
            menuInGame.block();
            groupMenu.block();
            levelMenu.block();

            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();
            messageSplash1.clearDisplay();
            if (messageSplash2 != null) {
                messageSplash2.clearDisplay();
            }
            menuMain.unblock();
            menuMain.display();
            tittle.display();
            messageMaxScoreTotal.display();
            bottomTextBox.display();
            setBottomText("", 0);
            messageMaxScoreTotal.setText(
                    getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(getMaxScoreTotal()));

            ConnectionHandler.verify();

        } else if (state == GAME_STATE_PREPARAR){

            Level.levelGoalsObject.clearAchievements();

            buttonContinue.clearDisplay();
            buttonContinue.block();
            buttonReturn.clearDisplay();
            buttonReturn.block();

            timeOfLevelPlay = 0;
            secondsOfLevelPlay = 0;
            lastSeconds = 0;

            mainActivity.hideAdView();
            if (!sameState) {
                activateFrame(2500);
            }


            LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
            Level.levelObject.loadEntities();
            int musicNumber = SaveGame.saveGame.currentLevelNumber - ((int)Math.floor(SaveGame.saveGame.currentLevelNumber / 7)*SaveGame.saveGame.currentLevelNumber);
            //if (musicNumber == 1){
            Sound.music = MediaPlayer.create(getContext(), R.raw.music1);
            //}
            Sound.music.setVolume(0.5f, 0.5f);
            Sound.music.setLooping(true);
            // cria a animação de preparação;
            ArrayList<float[]> values = new ArrayList<>();
                values.add(new float[]{0f,5f});
                values.add(new float[]{0.1666f,4f});
                values.add(new float[]{0.3333f,3f});
                values.add(new float[]{0.5f,2f});
                values.add(new float[]{0.6666f,1f});
                values.add(new float[]{0.8333f,0f});
            final Text innerMessagePreparation = messagePreparation;
            messagePreparation.setText("5");
            messagePreparation.display();
            Sound.play(Sound.soundCounter, 1, 1, 0);

            Animation anim = new Animation(messagePreparation, "messagePreparation", "numberForAnimation", 6000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 4f){
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("4");
                    } else if (innerMessagePreparation.numberForAnimation == 3f){
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("3");
                    } else if (innerMessagePreparation.numberForAnimation == 2f){
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        Sound.play(Sound.soundCounter, 1, 1, 0);
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {
                        innerMessagePreparation.setText(getContext().getResources().getString(R.string.mensagem_jogar));
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
            resumeTimeOfLevelPlay();
            if (SaveGame.saveGame.music) {
                Sound.music.start();
            }


            messageStars.reset();

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


            int totalStars = 0;
            for (int i = 0; i < Level.levelObject.levelGoalsObject.levelGoals.size(); i++){
                if (Level.levelObject.levelGoalsObject.levelGoals.get(i).achieved){
                    totalStars += Level.levelObject.levelGoalsObject.levelGoals.get(i).numberOfStars;
                }
            }

            messageStars.showAndGoAllGray(totalStars);

            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuGameOver.appearAndUnblock(1000);
            messageGameOver.display();

            if (scorePanel.value > 0) {
                scorePanel.showMessage("-50%", 1000);
                int points = scorePanel.value / 2;
                scorePanel.setValue(points, true, 1000, true);
                if (SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1] < points) {
                    SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1] = points;
                    setMaxScoreTotal();
                    SaveGame.save();
                    GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), points);
                }
            }
        } else if (state == GAME_STATE_PAUSE){
            buttonReturnObjectivesPause.block();
            buttonReturnObjectivesPause.clearDisplay();


            stopTimeOfLevelPlay();

            Log.e("game", "ativando game_state_pause");
            if (previousState != GAME_STATE_OPCOES_GAME) {
                Sound.music.pause();
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                stopAllGameEntities();
                reduceAllGameEntitiesAlpha(300);
                menuInGame.getMenuOptionByName("Continuar").textObject.setText(getContext().getResources().getString(R.string.continuarJogar));

                ArrayList<float[]> valuesAnimPause = new ArrayList<>();
                valuesAnimPause.add(new float[]{0f, 1f});
                valuesAnimPause.add(new float[]{0.25f, 2f});
                valuesAnimPause.add(new float[]{0.7f, 3f});

                messageInGame.y = gameAreaResolutionY*0.25f;

                Animation anim = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 4000, valuesAnimPause, true, false);
                anim.setOnChangeNotFluid(new Animation.OnChange() {
                    @Override
                    public void onChange() {
                        if (messageInGame.numberForAnimation == 1f) {
                            messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                        } else if (messageInGame.numberForAnimation == 2f) {
                            messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                        } else if (messageInGame.numberForAnimation == 3f) {
                            messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                        }
                    }
                });
                anim.start();

                messageInGame.setText(getContext().getResources().getString(R.string.pause));
                messageInGame.increaseAlpha(100, 1f);
                messageInGame.y = gameAreaResolutionY * 0.25f;

            }
                messageInGame.display();
                menuInGame.appearAndUnblock(300);

        } else if (state == GAME_STATE_VITORIA){

            Level.levelObject.levelGoalsObject.setFinish(stopTimeOfLevelPlay());

            messageConqueredStarsTotal.y =  resolutionY*0.21f;
            starForMessage.y = resolutionY*0.21f;

            Animation anim = Utils.createSimpleAnimation(messageTime, "translateX", "translateX", 800, 0f, -resolutionX*2f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    String previousText = messageTime.text;
                    messageTime = new Text("messageTime",
                            Game.resolutionX*0.01f, Game.resolutionY*0.95f, Game.resolutionY*0.04f,getContext().getResources().getString(R.string.tempo_gasto) + " " + previousText, Game.font, new Color(0.1f, 0.1f, 0.1f, 1f));
                    Utils.createSimpleAnimation(messageTime, "translateX", "translateX", 800, -resolutionX, 0f).start();
                }
            });
            anim.start();

            // TODO o que fazer com a animação quando for pausado
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

            Utils.createSimpleAnimation(ballDataPanel, "alphaVitoria", "alpha", 1000, ballDataPanel.alpha, 0f).start();

            messageInGame.y = gameAreaResolutionY*0.05f;

            ArrayList<float[]> valuesAnimVitoria = new ArrayList<>();
            valuesAnimVitoria.add(new float[]{0f,1f});
            valuesAnimVitoria.add(new float[]{0.2f,2f});
            valuesAnimVitoria.add(new float[]{0.5f,3f});
            valuesAnimVitoria.add(new float[]{0.7f,4f});
            Animation anim2 = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 3000, valuesAnimVitoria, true, false);
            anim2.setOnChangeNotFluid(new Animation.OnChange() {
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
            anim2.start();

            final int previousStars = SaveGame.saveGame.starsLevels[SaveGame.saveGame.currentLevelNumber - 1];
            final int newStars = Level.levelGoalsObject.getStarsAchieved();

            // calcula a pontuação final, de acordo com a quantidade de bolas azuis e estrelas
            int pointsTotal = scorePanel.value;
            for (int i = 0; i < ballGoalsPanel.blueBalls; i++) {
                pointsTotal *= 1.5;
            }
            pointsTotal *= 1f + (0.1f * (float)newStars);

            // salva os dados dos pontos e estrelas
            final long previousPoints = SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber - 1];
            if (previousPoints < pointsTotal) {
                SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber - 1] = pointsTotal;
                setMaxScoreTotal();
            }
            if (previousStars < newStars) {
                SaveGame.saveGame.starsLevels[SaveGame.saveGame.currentLevelNumber - 1] = newStars;
            }

            GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), pointsTotal);
            SaveGame.save();

            // verifica a quantidade de bolas azuis, e atualiza a pontuação
            Timer timer = new Timer();

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (ballGoalsPanel.blueBalls > 0){
                        int points = (int)((float)scorePanel.value * 1.5f);
                        scorePanel.setValue(points, true, 1000, true);
                        scorePanel.showMessage("+ 50%", 800);
                        ballGoalsPanel.explodeBlueBall();
                    } else if (!levelGoalsPanel.isVisible){
                        Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateX", 2000, 0f, gameAreaResolutionX*2f).start();
                        ballGoalsPanel.clearExplosions();
                        levelGoalsPanel.appearGray();
                    } else if (levelGoalsPanel.gray) {
                        levelGoalsPanel.shineLines(true);
                        if (newStars > previousStars) {
                            messageStarsWin.show(newStars, newStars - previousStars, true);
                        }

                        if (newStars > 0) {
                            int points = (int) ((float) scorePanel.value * (1f + (0.1f * (float)newStars)));
                            scorePanel.setValue(points, true, 1000, true);
                            if (newStars == 1) scorePanel.showMessage("+ 10%", 800);
                            if (newStars == 2) scorePanel.showMessage("+ 20%", 800);
                            if (newStars == 3) scorePanel.showMessage("+ 30%", 800);
                            if (newStars == 4) scorePanel.showMessage("+ 40%", 800);
                            if (newStars == 5) scorePanel.showMessage("+ 50%", 800);
                        }


                    } else{


                        starForMessage.alpha = 0f;
                        messageConqueredStarsTotal.alpha = 0f;
                        starForMessage.increaseAlpha(500, 1f);
                        messageConqueredStarsTotal.increaseAlpha(500, 1f);
                        starForMessage.display();
                        messageConqueredStarsTotal.display();

                        final int starsDiference = newStars - previousStars;

                        ArrayList<float[]> valuesAnim = new ArrayList<>();
                        valuesAnim.add(new float[]{0f,0f});
                        valuesAnim.add(new float[]{0.5f,1f});
                        valuesAnim.add(new float[]{0.57f,2f});
                        valuesAnim.add(new float[]{0.64f,3f});
                        valuesAnim.add(new float[]{0.71f,4f});
                        valuesAnim.add(new float[]{0.78f,5f});
                        valuesAnim.add(new float[]{1f,6f});

                        Animation animMessageConqueredStarsTotal = new Animation(messageConqueredStarsTotal, "numberForAnimation", "numberForAnimation", 2500, valuesAnim, false, false);
                        animMessageConqueredStarsTotal.setOnChangeNotFluid(new Animation.OnChange() {
                            @Override
                            public void onChange() {
                                if (messageConqueredStarsTotal.numberForAnimation == 1f){
                                    if (starsDiference > 0){
                                        messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                                "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal + 1));
                                        Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                                    }
                                } else if (messageConqueredStarsTotal.numberForAnimation == 2f) {
                                    if (starsDiference > 1){
                                        messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                                "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal + 2));
                                        Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                                    }
                                } else if (messageConqueredStarsTotal.numberForAnimation == 3f) {
                                    if (starsDiference > 2){
                                        messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                                "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal + 3));
                                        Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                                    }
                                } else if (messageConqueredStarsTotal.numberForAnimation == 4f) {
                                    if (starsDiference > 3){
                                        messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                                "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal + 4));
                                        Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                                    }
                                } else if (messageConqueredStarsTotal.numberForAnimation == 5f) {
                                    if (starsDiference > 4){
                                        messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                                "\u0020" + NumberFormat.getInstance().format(conqueredStarsTotal + 5));
                                        Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                                    }
                                }
                            }
                        });
                        animMessageConqueredStarsTotal.start();

                        buttonContinue.display();
                        buttonContinue.unblock();
                        /*
                        if (previousPoints != 0){
                            String textToShow;
                            if (actualPoints < previousPoints){
                                textToShow = mainActivity.getResources().getString(R.string.pontuacaoMenor1)
                                        + " " + NumberFormat.getInstance().format(previousPoints) + " " + mainActivity.getResources().getString(R.string.pontuacaoMenor2);
                            } else if (actualPoints > previousPoints) {
                                textToShow = mainActivity.getResources().getString(R.string.pontuacaoMaior1)
                                        + " " + NumberFormat.getInstance().format(previousPoints) + " " + mainActivity.getResources().getString(R.string.pontuacaoMaior2);

                            } else {
                                textToShow = mainActivity.getResources().getString(R.string.pontuacaoIgual);
                            }
                            setBottomText(textToShow);
                        }
                        */
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

            messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {Sound.play(Sound.soundTextBoxAppear, 1, 1, 0);}
            });
            messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1)+ " " + getContext().getResources().getString(R.string.nivelConcluido2));
            messageInGame.display();

            Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY*0.1f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleX", "scaleX", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleY", "scaleY", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(scorePanel, "scaleX", "scaleX", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(scorePanel, "scaleY", "scaleY", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(scorePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY * 0.05f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                float iY = - Game.gameAreaResolutionY * 0.05f;
                Utils.createAnimation4v(scorePanel, "animScoreTX", "translateX", 30000, 0f, 0f, 0.3f, -10f, 0.7f, 20f, 1f, 0f, true, true).start();
                Utils.createAnimation4v(scorePanel, "animScoreTY", "translateY", 12000, 0f,iY, 0.2f,iY + 5f, 0.7f,iY -20f, 1f,iY, true, true).start();
                }
                }
            ).start();
        } else if (state == GAME_STATE_TUTORIAL) {
            if (previousState == GAME_STATE_MENU_TUTORIAL) {
                Log.e(TAG, "limpando menu tutorial");
                messageMenu.clearDisplay();
                tutorialMenu.clearDisplay();
                tutorialMenu.block();
            }
            if (!sameState) {
                activateFrame(500);
            }

            mainActivity.hideAdView();

            loadTutorial();
            if (previousState == GAME_STATE_MENU_TUTORIAL) {
                currentTutorialObject.showFirst(true);
            } else {
                currentTutorialObject.showFirst(false);
            }
        }
    }

    public static void loadTutorial(){

        float textBoxY = resolutionX * 0.48f;
        float textBoxSize = resolutionX * 0.03f;

        if (currentTutorial == Tutorial.TUTORIAL_INSTRUCOES_INICIAIS) {
            Image i1 = new Image("i1", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i2 = new Image("i1", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i3 = new Image("i1", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            currentTutorialObject = new Tutorial();

            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t1),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t2),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t3),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t4),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t5),
                    textBoxY, textBoxSize, resolutionX * 0.2f, resolutionY * 0.5f);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t6),
                    textBoxY, textBoxSize, resolutionX * 0.5f, resolutionY * 0.5f);
            
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t7),
                    textBoxY, textBoxSize, resolutionX * 0.8f, resolutionY * 0.14f);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t8),
                    textBoxY, textBoxSize);          
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t9),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t10),
                    textBoxY, textBoxSize, resolutionX * 0.2f, resolutionY * 0.6f);

            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t1t11),
                    textBoxY, textBoxSize, resolutionX * 0.2f, resolutionY * 0.6f);

            currentTutorialObject.addFrame(i3, getContext().getResources().getString(R.string.t1t12),
                    textBoxY, textBoxSize, resolutionX * 0.2f, resolutionY * 0.4f);

            currentTutorialObject.addFrame(i3, getContext().getResources().getString(R.string.t1t13),
                    textBoxY, textBoxSize, resolutionX * 0.65f, resolutionY * 0.65f);

            currentTutorialObject.addFrame(i3, getContext().getResources().getString(R.string.t1t14),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i3, getContext().getResources().getString(R.string.t1t15),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t16),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t17),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t18),
                    textBoxY, textBoxSize);
            
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t1t19),
                    textBoxY, textBoxSize);

        } else if (currentTutorial == Tutorial.TUTORIAL_INICIO) {


            Image i1 = new Image("i1", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            Image i2 = new Image("i2", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            Image i3 = new Image("i3", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            Image i4 = new Image("i4", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);

            Image i5 = new Image("i5", resolutionX * 0.05f, resolutionX * 0.025f,
                    resolutionX * 0.9f, resolutionX * 0.45f, Texture.TEXTURE_TUTORIAL1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);

            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t2t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, getContext().getResources().getString(R.string.t2t2), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, getContext().getResources().getString(R.string.t2t3), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t2t4), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, getContext().getResources().getString(R.string.t2t5), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t6), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t7), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t8), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t9), textBoxY, textBoxSize, resolutionX * 0.5f, resolutionY * 0.68f);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t10), textBoxY, textBoxSize, resolutionX * 0.43f, resolutionY * 0.62f);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t11), textBoxY, textBoxSize, resolutionX * 0.43f, resolutionY * 0.66f);
            currentTutorialObject.addFrame(i4, getContext().getResources().getString(R.string.t2t12), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t13), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t14), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t15), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t16), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t17), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, getContext().getResources().getString(R.string.t2t18), textBoxY, textBoxSize);
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

    private static void stopAllGameEntities() {
        for (Ball b : balls){
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
        ballGoalsPanel = null;
        ballDataPanel = null;
        scorePanel = null;
        button1Left = null;
        button1Right = null;
        button2Left = null;
        button2Right = null;
        background = null;
        messageTime = null;
    }

    public static void setBottomText(String text, int duration){
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
            messageMaxScoreTotal.y = resolutionY - bottomTextBox.height - (resolutionY * 0.08f);
        } else {
            if (!previousText.equals("...")){
                appearOrDesapear = true;
            }
            bottomTextBox.setText("...");
            bottomTextBox.isBlocked = true;
            bottomTextBox.setPositionY(resolutionY*2);
            messageMaxScoreTotal.y = resolutionY - (resolutionY * 0.08f);
        }

        float difference = previousPosition - bottomTextBox.y;
        if (difference != 0) {
            Utils.createSimpleAnimation(bottomTextBox, "translateY", "translateY", 200, difference, 0.0f).start();
        }

        if (appearOrDesapear){
            //Sound.play(Sound.soundTextBoxAppear, 0.8f, 0.8f, 0);
        }

        if (duration > 100){
            Utils.createSimpleAnimation(bottomTextBox, "alpha", "alpha", duration, 1f, 0.8f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    setBottomText("", 0);
                }
            }).start();
        }
    }

    public static ArrayList<Entity> collectAllHudEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(button1Left);
        list.add(button1Right);
        list.add(button2Left);
        list.add(button2Right);
        list.add(scorePanel);
        list.add(ballDataPanel);
        list.add(ballGoalsPanel);
        list.add(messageTime);
        return list;
    }

    static ArrayList<Entity> collectAllGameEntities(){
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

    static void setMaxScoreTotal(){
        long scoreTotal = getMaxScoreTotal();
        GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), scoreTotal);
        maxScoreTotal = scoreTotal;
    }

    static long getMaxScoreTotal(){
        //Log.e("Game", "getMaxScoreTotal");
        long scoreTotal = 0;
        for (int i = 0; i < Level.maxNumberOfLevels; i++){
            scoreTotal += SaveGame.saveGame.pointsLevels[i];
            //Log.e("Game", "level "+(i+1)+ " pontos "+SaveGame.saveGame.pointsLevels[i]);
            //Log.e("Game", "scoreTotal "+scoreTotal);

        }
        maxScoreTotal = scoreTotal;
        //Log.e("Game", "score total retornando após calculo "+ scoreTotal);
        return scoreTotal;
    }

    private static void changeLevel(int level) {
        SaveGame.saveGame.currentLevelNumber = level;
    }

    static void simulate(long elapsed, float frameDuration){

        if (gameState != GAME_STATE_VITORIA) {
            updateTimeOfLevelPlay(elapsed);
        }
        
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
        if (gameState == GAME_STATE_JOGAR) {
                float timePercentage = (float) elapsed / frameDuration;
            if (bars != null) {
                if (bars.size() == 1) {
                    if (button1Left.isPressed && !button2Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (button2Right.isPressed && !button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                        //bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).stop();
                    }
                }
                if (bars.size() == 2) {
                    if (button1Left.isPressed && !button1Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (button1Right.isPressed && !button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                    } else {
                        bars.get(0).stop();
                    }

                    if (button2Left.isPressed) {
                        bars.get(1).moveLeft(timePercentage);
                    } else if (button2Right.isPressed) {
                        bars.get(1).moveRight(timePercentage);
                    } else {
                        bars.get(1).stop();
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

        if (gameState == GAME_STATE_JOGAR) {
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
        if (gameState == GAME_STATE_JOGAR) {
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
            verifyTargetsAppend();
        } else if(gameState == GAME_STATE_VITORIA){
            background.move(3);
        }
        if (gameState == GAME_STATE_JOGAR) {
            verifyWin();
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

    static void verifyPointsDecay(){
        long time = Utils.getTime();
        if ((time - initialTimePointsDecay)>TIME_FOR_POINTS_DECAY){
            if (scorePanel.value > POINTS_DECAY) {
                initialTimePointsDecay = time;
                int value = scorePanel.value - POINTS_DECAY;
                scorePanel.setValue(value, false, 0, false);
            }
        }
    }

    static void resetTimeForPointsDecay(){
        initialTimePointsDecay = Utils.getTime();
    }

    static void verifyWin() {
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

    static void verifyDead() {
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

        Level.levelObject.levelGoalsObject.notifyBallsAlive(ballsNotInvencibleAlive + ballsInvencible, secondsOfLevelPlay);

        ballGoalsPanel.setValues(ballsNotInvencibleAlive + ballsInvencible, Level.levelObject.minBallsAlive, ballsInvencible);
        if (Level.levelObject.minBallsAlive > ballsNotInvencibleAlive){
            setGameState(GAME_STATE_DERROTA);
        }
    }
    
    static void checkTransformations(){

        if (wind != null){
            wind.checkTransformations(true);
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

        for (int i = 0; i < windows.size(); i++){
            windows.get(i).checkTransformations(true);
        }

        for (int i = 0; i < specialBalls.size(); i++){
            specialBalls.get(i).checkTransformations(true);
        }

        if (menuMain != null) menuMain.checkTransformations(true);
        if (menuInGame != null) menuInGame.checkTransformations(true);
        if (menuGameOver != null) menuGameOver.checkTransformations(true);
        if (menuTutorial != null) menuTutorial.checkTransformations(true);
        if (menuObjectives != null) menuObjectives.checkTransformations(true);
        if (selectorLevel != null) selectorLevel.checkTransformations(true);

        if (menuOptions != null)menuOptions.checkTransformations(true);
        if (groupMenu != null) groupMenu.checkTransformations(true);
        if (levelMenu != null)levelMenu.checkTransformations(true);
        if (tutorialMenu != null)tutorialMenu.checkTransformations(true);
        if (levelGoalsPanel != null) levelGoalsPanel.checkTransformations(true);
        if (menuInGameOptions != null)menuInGameOptions.checkTransformations(true);
        if (selectorDificulty != null)selectorDificulty.checkTransformations(true);
        if (selectorMusic != null)selectorMusic.checkTransformations(true);
        if (selectorSound != null)selectorSound.checkTransformations(true);

        if (tittle != null) tittle.checkTransformations(true);

        if (tutorialImage != null) tutorialImage.checkTransformations(true);
        if (tutorialTextBox != null) tutorialTextBox.checkTransformations(true);


        messageGameOver.checkTransformations(true);
        messagePreparation.checkTransformations(true);
        messageInGame.checkTransformations(true);
        messageMaxScoreTotal.checkTransformations(true);
        messageConqueredStarsTotal.checkTransformations(true);
        starForMessage.checkTransformations(true);
        bottomTextBox.checkTransformations(true);

        if (bordaE != null)bordaE.checkTransformations(true);
        if (bordaD != null)bordaD.checkTransformations(true);
        if (bordaC != null)bordaC.checkTransformations(true);
        if (bordaB != null)bordaB.checkTransformations(true);

        if (frame != null)frame.checkTransformations(true);

        if (scorePanel != null) scorePanel.checkTransformations(true);
        if (ballDataPanel != null) ballDataPanel.checkTransformations(true);
        if (ballGoalsPanel != null) ballGoalsPanel.checkTransformations(true);

        if (button1Left != null) button1Left.checkTransformations(true);
        if (button1Right != null) button1Right.checkTransformations(true);
        if (button2Left != null) button2Left.checkTransformations(true);
        if (button2Right != null) button2Right.checkTransformations(true);
        
        if (imageTutorialDown != null) imageTutorialDown.checkTransformations(true);
        if (imageTutorialTop != null) imageTutorialTop.checkTransformations(true);

        if (messages != null) messages.checkTransformations(true);
    }

    static void render(float[] matrixView, float[] matrixProjection){
        if (background != null) {
            background.prepareRender(matrixView, matrixProjection);
        }
        
        if (imageTutorialDown != null) imageTutorialDown.prepareRender(matrixView, matrixProjection);
    
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
        if (menuGameOver != null) menuGameOver.prepareRender(matrixView, matrixProjection);
        if (menuTutorial != null) menuTutorial.prepareRender(matrixView, matrixProjection);
        if (menuObjectives != null) menuObjectives.prepareRender(matrixView, matrixProjection);
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);

        if (menuOptions != null)menuOptions.prepareRender(matrixView, matrixProjection);
        if (groupMenu != null) groupMenu.prepareRender(matrixView, matrixProjection);
        if (levelMenu != null)levelMenu.prepareRender(matrixView, matrixProjection);
        if (tutorialMenu != null)tutorialMenu.prepareRender(matrixView, matrixProjection);
        if (levelGoalsPanel != null) levelGoalsPanel.prepareRender(matrixView, matrixProjection);
        if (menuInGameOptions != null)menuInGameOptions.prepareRender(matrixView, matrixProjection);
        if (selectorDificulty != null)selectorDificulty.prepareRender(matrixView, matrixProjection);
        if (selectorMusic != null)selectorMusic.prepareRender(matrixView, matrixProjection);
        if (selectorSound != null)selectorSound.prepareRender(matrixView, matrixProjection);
        if (tittle != null) {tittle.prepareRender(matrixView, matrixProjection);}

        if (tutorialImage != null) tutorialImage.prepareRender(matrixView, matrixProjection);
        if (tutorialTextBox != null) tutorialTextBox.prepareRender(matrixView, matrixProjection);

        messageGameOver.prepareRender(matrixView, matrixProjection);
        messagePreparation.prepareRender(matrixView, matrixProjection);
        messageInGame.prepareRender(matrixView, matrixProjection);
        messageMenu.prepareRender(matrixView, matrixProjection);
        messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);
        messageConqueredStarsTotal.prepareRender(matrixView, matrixProjection);
        starForMessage.prepareRender(matrixView, matrixProjection);
        if (messageStars != null) messageStars.prepareRender(matrixView, matrixProjection);
        if (messageStarsWin != null) messageStarsWin.prepareRender(matrixView, matrixProjection);

        if (messageTime != null) messageTime.prepareRender(matrixView, matrixProjection);
        if (bordaE != null)bordaE.prepareRender(matrixView, matrixProjection);
        if (bordaD != null)bordaD.prepareRender(matrixView, matrixProjection);
        if (bordaC != null)bordaC.prepareRender(matrixView, matrixProjection);
        if (bordaB != null)bordaB.prepareRender(matrixView, matrixProjection);

        if (ballDataPanel != null) ballDataPanel.prepareRender(matrixView, matrixProjection);
        if (scorePanel != null) scorePanel.prepareRender(matrixView, matrixProjection);
        if (ballGoalsPanel != null) ballGoalsPanel.prepareRender(matrixView, matrixProjection);

        if (button1Left != null) button1Left.prepareRender(matrixView, matrixProjection);
        if (button1Right != null) button1Right.prepareRender(matrixView, matrixProjection);
        if (button2Left != null) button2Left.prepareRender(matrixView, matrixProjection);
        if (button2Right != null) button2Right.prepareRender(matrixView, matrixProjection);

        if (buttonReturn != null)buttonReturn.prepareRender(matrixView, matrixProjection);
        if (buttonReturnObjectivesPause != null)buttonReturnObjectivesPause.prepareRender(matrixView, matrixProjection);
        if (buttonContinue != null)buttonContinue.prepareRender(matrixView, matrixProjection);

        bottomTextBox.prepareRender(matrixView, matrixProjection);
        
        if (imageTutorialTop != null) imageTutorialTop.prepareRender(matrixView, matrixProjection);

        if (messages != null) messages.prepareRender(matrixView, matrixProjection);
        if (frame != null)frame.prepareRender(matrixView, matrixProjection);
    }

    static void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents.size() == 0){
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
        if (menuMain != null) menuMain.verifyListener();
        if (menuInGame != null) menuInGame.verifyListener();
        if (menuGameOver != null) menuGameOver.verifyListener();
        if (menuTutorial != null) menuTutorial.verifyListener();
        if (menuObjectives != null) menuObjectives.verifyListener();
        if (selectorLevel != null) selectorLevel.verifyListener();
        if (menuOptions != null)menuOptions.verifyListener();
        if (groupMenu != null) groupMenu.verifyListener();
        if (levelMenu != null)levelMenu.verifyListener();
        if (tutorialMenu != null)tutorialMenu.verifyListener();
        // levelGoalsPanel não precisa de listener???
        if (buttonReturn != null)buttonReturn.verifyListener();
        if (buttonReturnObjectivesPause != null)buttonReturnObjectivesPause.verifyListener();
        if (buttonContinue != null)buttonContinue.verifyListener();
        if (menuInGameOptions != null)menuInGameOptions.verifyListener();
        if (selectorDificulty != null)selectorDificulty.verifyListener();
        if (selectorMusic != null)selectorMusic.verifyListener();
        if (selectorSound != null)selectorSound.verifyListener();

        if (button1Left != null) button1Left.verifyListener();
        if (button1Right != null) button1Right.verifyListener();
        if (button2Left != null) button2Left.verifyListener();
        if (button2Right != null) button2Right.verifyListener();
        if (bottomTextBox != null) bottomTextBox.verifyListener();

        // elimina os touchevents que tiverem o UP ativado, ou seja, que já foram considerados nesta passagem
        for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
            if (Game.touchEvents.get(i2).type == TouchEvent.TOUCH_TYPE_UP) {
                Game.touchEvents.remove(i2);
            }
        }
    }

    public static ArrayList<Entity> collectAllMenuEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(menuMain);
        list.add(menuOptions);
        list.add(groupMenu);
        list.add(levelMenu);
        list.add(tutorialMenu);
        list.add(levelGoalsPanel);
        list.add(tutorialImage);// TODO ????
        list.add(tutorialTextBox);// TODO ????
        list.add(buttonReturn);
        list.add(buttonReturnObjectivesPause);
        list.add(buttonContinue);
        list.add(menuInGameOptions);
        list.add(selectorLevel);
        list.add(selectorDificulty);
        list.add(selectorMusic);
        list.add(selectorSound);
        list.add(selectorLevel);
        list.add(menuInGame);
        list.add(menuGameOver);
        list.add(menuTutorial);
        list.add(menuObjectives);
        list.add(tittle);
        list.add(messageGameOver);
        list.add(messagePreparation);
        list.add(messageInGame);
        list.add(messageMenu);
        list.add(messageMaxScoreTotal);
        list.add(messageConqueredStarsTotal);
        list.add(starForMessage);
        list.add(bottomTextBox);
        return list;
    }

    static public Context getContext(){
        return mainActivity.getApplicationContext();
    }
}
