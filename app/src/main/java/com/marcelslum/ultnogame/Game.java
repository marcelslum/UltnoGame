package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Vibrator;

// TODO verificar os listeners ativos
// TODO colocar timeout em todos os awaits...
// TODO colocar uma barra ao final do data panel

public class Game {
    
    
    static Vibrator vibrator;

    static final String TAG = "Game";

    static MainActivity mainActivity;

    static boolean forInitGame;

    static int basePoints = 10;

    static final int BALL_WEIGHT = 1;
    static final int BORDA_WEIGHT = 10;
    static final int OBSTACLES_WEIGHT = 7;
    static final int TARGET_WEIGHT = 10;
    static final int BAR_WEIGHT = 8;

    static LevelGoalsPanel levelGoalsPanel;

    static ArrayList<Target> targets;
    static ArrayList<Ball> balls;
    static ArrayList<Text> texts;
    static ArrayList<TouchEvent> touchEvents;
    static ArrayList<Bar> bars;
    static ArrayList<Obstacle> obstacles;
    static ArrayList<WindowGame> windows;
    static ArrayList<Menu> menus;
    static ArrayList<InteractionListener> interactionListeners;
    static ArrayList<TextBox> textBoxes;
    static Messages messages;
    static ArrayList<Line> lines;
    public static Background background;
    static Wind wind;
    static ArrayList<SpecialBall> specialBalls;
    static ArrayList<Image> ballCollisionStars;

    static BallDataPanel ballDataPanel;
    static BallGoalsPanel ballGoalsPanel;

    private static Edge bordaC;
    static Edge bordaE;
    private static Edge bordaD;
    static Edge bordaB;

    private static Rectangle frame;

    static Image tittle;

    public static ArrayList<Image> groupsUnblocked;
    public static Image currentLevelIcon;

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
    public final static int GAME_STATE_VITORIA_COMPLEMENTACAO =  27;

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

    public static String currentPlayerId;

    private Game() {}
    
    
    static final int VIBRATE_SMALL = 0;
    static final int VIBRATE_BAR = 4;
    static final int VIBRATE_TARGET = 1;
    static final int VIBRATE_HARD = 2;
    
    
    public static void vibrate(int intensity){

        if (!SaveGame.saveGame.vibration){
            return;
        }

        if (vibrator.hasVibrator()){
            long[] pattern;
            if (intensity == VIBRATE_SMALL){
                pattern = new long[]{0,20};
            } else if (intensity == VIBRATE_TARGET){
                pattern = new long[]{0,25,10,18, 15, 10};
            } else if (intensity == VIBRATE_HARD){
                pattern = new long[]{0,70,10,50, 10, 30, 10, 15, 10, 5};
            } else if (intensity == VIBRATE_BAR){
                pattern = new long[]{0,23,7,15, 17, 9};
            } else {
                pattern = new long[]{0};
            }
            vibrator.vibrate(pattern, -1);
        }
    }
    

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
        ballCollisionStars = new ArrayList<>();
        balls = new ArrayList<>();
        obstacles = new ArrayList<>();
        windows = new ArrayList<>();
        specialBalls = new ArrayList<>();
        touchEvents = new ArrayList<>();
        texts = new ArrayList<>();
        interactionListeners = new ArrayList<>();
        bars = new ArrayList<>();
        menus = new ArrayList<>();
        textBoxes = new ArrayList<>();
        messages = new Messages();
        lines = new ArrayList<> ();
        groupsUnblocked = new ArrayList<>();
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

        Animation animTittle = Utils.createAnimation5v(tittle, "numberForAnimation", "numberForAnimation", 5000, 0f, 1f, 0.15f, 2f, 0.45f, 3f, 0.6f, 4f, 0.85f, 5f, true, false);
        animTittle.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (tittle.numberForAnimation == 1f){
                    tittle.setColor(new Color(0f, 0f, 0f, 1f));
                } else if (tittle.numberForAnimation == 2f) {
                } else if (tittle.numberForAnimation == 3f) {
                    tittle.setColor(new Color(0f, 0f, 1f, 1f));
                } else if (tittle.numberForAnimation == 4f) {
                    tittle.setColor(new Color(0f, 1f, 0f, 1f));
                } else if (tittle.numberForAnimation == 5f) {
                    tittle.setColor(new Color(1f, 1f, 0f, 1f));
                }
            }
        });
        animTittle.start();

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
                interactionListeners.set(i, listener);
                return;
            }
        }
        interactionListeners.add(listener);
    }

    public static void blockAndWaitTouchRelease(){isBlocked = true;}

    public static void setGameState(int state){
        Log.e("game", "set game state "+state);
        boolean sameState = false;
        int previousState = gameState;
        if (state == gameState){
            sameState = true;
        }

        SelectorHandle.repositionSelectors(state);
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
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+ " " + SaveGame.saveGame.currentLevelNumber);

            ButtonHandler.buttonContinue.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();
        } else if (state == GAME_STATE_OBJETIVO_PAUSE){
            levelGoalsPanel.appearGrayAndShine();
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+ " " + SaveGame.saveGame.currentLevelNumber);
            ButtonHandler.buttonReturnObjectivesPause.unblockAndDisplay();
        } else if (state == GAME_STATE_SELECAO_GRUPO) {

            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();


            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(100);
            }

            mainActivity.showAdView();
            Game.bordaB.y = Game.resolutionY;

            MenuHandler.updateGroupMenu();
            MenuHandler.groupMenu.appear();
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoMundo));
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();
            MessagesHandler.messageConqueredStarsTotal.y = resolutionY * 0.15f;
            MessagesHandler.starForMessage.y = resolutionY * 0.15f;
        } else if (state == GAME_STATE_MENU_TUTORIAL){
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuTutorial));
            MenuHandler.updateTutorialMenu();
            MenuHandler.tutorialMenu.appear();
            ButtonHandler.buttonReturn.unblockAndDisplay();
        } else if (state == GAME_STATE_SELECAO_LEVEL) {
            MenuHandler.updateLevelMenu();
            MenuHandler.levelMenu.appear();

            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(100);
            }

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoLevel));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(currentLevelsGroupDataSelected.name);
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();

            MessagesHandler.messageConqueredStarsTotal.y =  resolutionY*0.15f;
            MessagesHandler.starForMessage.y = resolutionY*0.15f;

        } else if (state == GAME_STATE_INTRO) {
            mainActivity.hideAdView();
            ConnectionHandler.internetState = ConnectionHandler.INTERNET_STATE_NOT_CONNECTED;
            Splash.timeInitIntro = Utils.getTime();
            Splash.init();
            Splash.display();
        } else if (state == GAME_STATE_OPCOES){
            tittle.display();
            MenuHandler.menuOptions.appearAndUnblock(50);
        } else if (state == GAME_STATE_OPCOES_GAME){
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.menuInGameOptions.appearAndUnblock(100);
            MessagesHandler.messageInGame.y = gameAreaResolutionY*0.25f;
            MessagesHandler.messageInGame.display();
        } else if (state == GAME_STATE_MENU){
            ConnectionHandler.menuConnectionAttempts = 0;
            if (!sameState) {
                if (previousState != GAME_STATE_OPCOES) {
                    activateFrame(500);
                }
            }
            SelectorHandle.repositionSelectors(state);
            initTittle();
            mainActivity.showAdView();
            Game.bordaB.y = Game.resolutionY;
            MenuHandler.menuOptions.block();
            MenuHandler.menuInGame.block();
            MenuHandler.groupMenu.block();
            MenuHandler.levelMenu.block();

            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();
            MessagesHandler.messageSplash1.clearDisplay();
            if (MessagesHandler.messageSplash2 != null) {
                MessagesHandler.messageSplash2.clearDisplay();
            }
            MenuHandler.menuMain.unblockAndDisplay();
            tittle.display();
            MessagesHandler.messageMaxScoreTotal.display();
            MessagesHandler.bottomTextBox.display();
            MessagesHandler.setBottomMessage("", 0);
            MessagesHandler.messageMaxScoreTotal.setText(
                    getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()));

            ConnectionHandler.verify();

        } else if (state == GAME_STATE_PREPARAR){


            MessagesHandler.messageTime.display();
            MessagesHandler.messageCurrentLevel.display();


            Level.levelGoalsObject.clearAchievements();

            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();

            TimeHandler.timeOfLevelPlay = 0;
            TimeHandler.secondsOfLevelPlay = 0;
            TimeHandler.lastSeconds = 0;

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
            final Text innerMessagePreparation = MessagesHandler.messagePreparation;
            MessagesHandler.messagePreparation.setText("5");
            MessagesHandler.messagePreparation.display();
            Sound.play(Sound.soundCounter, 1, 1, 0);

            Animation anim = new Animation(MessagesHandler.messagePreparation, "messagePreparation", "numberForAnimation", 6000, values, false, false);
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
            TimeHandler.resumeTimeOfLevelPlay();
            if (SaveGame.saveGame.music) {
                Sound.music.start();
            }


            MessageStar.messageStars.reset();

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

            MessageStar.messageStars.showAndGoAllGray(totalStars);

            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            MenuHandler.menuGameOver.appearAndUnblock(1000);
            MessagesHandler.messageGameOver.display();

            if (ScoreHandler.scorePanel.value > 0) {
                ScoreHandler.scorePanel.showMessage("-50%", 1000);
                int points = ScoreHandler.scorePanel.value / 2;
                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                if (SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1] < points) {
                    SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1] = points;
                    ScoreHandler.setMaxScoreTotal();
                    SaveGame.save();
                    GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), points);
                }
            }
        } else if (state == GAME_STATE_PAUSE){
            ButtonHandler.buttonReturnObjectivesPause.block();
            ButtonHandler.buttonReturnObjectivesPause.clearDisplay();


            TimeHandler.stopTimeOfLevelPlay();

            Log.e("game", "ativando game_state_pause");
            if (previousState != GAME_STATE_OPCOES_GAME) {
                Sound.music.pause();
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                stopAllGameEntities();
                reduceAllGameEntitiesAlpha(300);
                MenuHandler.menuInGame.getMenuOptionByName("Continuar").textObject.setText(getContext().getResources().getString(R.string.continuarJogar));

                ArrayList<float[]> valuesAnimPause = new ArrayList<>();
                valuesAnimPause.add(new float[]{0f, 1f});
                valuesAnimPause.add(new float[]{0.25f, 2f});
                valuesAnimPause.add(new float[]{0.7f, 3f});

                MessagesHandler.messageInGame.y = gameAreaResolutionY*0.25f;

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
                MessagesHandler.messageInGame.y = gameAreaResolutionY * 0.25f;

            }
                MessagesHandler.messageInGame.display();
                MenuHandler.menuInGame.appearAndUnblock(300);

        } else if (state == GAME_STATE_VITORIA){

            Level.levelObject.levelGoalsObject.setFinish(TimeHandler.stopTimeOfLevelPlay());

            //MessagesHandler.messageConqueredStarsTotal.y =  resolutionY*0.21f;
            //MessagesHandler.starForMessage.y = resolutionY*0.21f;

            Animation anim = Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, 0f, -resolutionX*2f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    String previousText = MessagesHandler.messageTime.text;
                    MessagesHandler.messageTime = new Text("messageTime",
                            Game.resolutionX*0.01f, Game.resolutionY*0.95f, Game.resolutionY*0.04f,getContext().getResources().getString(R.string.tempo_gasto) + " " + previousText, Game.font, new Color(0.1f, 0.1f, 0.1f, 1f));
                    Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, -resolutionX, 0f).start();
                }
            });
            anim.start();

            MessagesHandler.messageCurrentLevel.reduceAlphaAndClearDisplay(500);

            // TODO o que fazer com a animação quando for pausado
            stopAndReleaseMusic();
            Sound.play(Sound.soundWin, 1, 1, 0);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);

            Utils.createSimpleAnimation(bordaB, "translateVitoria", "translateY", 2000, 0f, resolutionY - gameAreaResolutionY).start();
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

            StarsHandler.previousStars = SaveGame.saveGame.starsLevels[SaveGame.saveGame.currentLevelNumber - 1];
            StarsHandler.newStars = Level.levelGoalsObject.getStarsAchieved();

            // calcula a pontuação final, de acordo com a quantidade de bolas azuis e estrelas
            int pointsTotal = ScoreHandler.scorePanel.value;
            for (int i = 0; i < ballGoalsPanel.blueBalls; i++) {
                pointsTotal *= 1.5;
            }
            pointsTotal *= 1f + (0.1f * (float) StarsHandler.newStars);

            // salva os dados dos pontos e estrelas
            final long previousPoints = SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber - 1];
            if (previousPoints < pointsTotal) {
                SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber - 1] = pointsTotal;
                ScoreHandler.setMaxScoreTotal();
            }
            if (StarsHandler.previousStars < StarsHandler.newStars) {
                SaveGame.saveGame.starsLevels[SaveGame.saveGame.currentLevelNumber - 1] = StarsHandler.newStars;
            }

            GooglePlayGames.submitScore(mainActivity.mGoogleApiClient, mainActivity.getResources().getString(R.string.leaderboard_ranking), pointsTotal);
            SaveGame.save();

            // verifica a quantidade de bolas azuis, e atualiza a pontuação
            Timer timer = new Timer();

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (ballGoalsPanel.blueBalls > 0){
                        int points = (int)((float) ScoreHandler.scorePanel.value * 1.5f);
                        ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                        ScoreHandler.scorePanel.showMessage("+ 50%", 800);
                        ballGoalsPanel.explodeBlueBall();
                    } else if (!levelGoalsPanel.isVisible){
                        Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateX", 2000, 0f, gameAreaResolutionX*2f).start();
                        ballGoalsPanel.clearExplosions();
                        levelGoalsPanel.appearGray();
                    } else if (levelGoalsPanel.gray) {
                        levelGoalsPanel.shineLines(true);


                        if (StarsHandler.newStars > 0) {
                            int points = (int) ((float) ScoreHandler.scorePanel.value * (1f + (0.1f * (float) StarsHandler.newStars)));
                            ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                            if (StarsHandler.newStars == 1) ScoreHandler.scorePanel.showMessage("+ 10%", 2000);
                            if (StarsHandler.newStars == 2) ScoreHandler.scorePanel.showMessage("+ 20%", 2000);
                            if (StarsHandler.newStars == 3) ScoreHandler.scorePanel.showMessage("+ 30%", 2000);
                            if (StarsHandler.newStars == 4) ScoreHandler.scorePanel.showMessage("+ 40%", 2000);
                            if (StarsHandler.newStars == 5) ScoreHandler.scorePanel.showMessage("+ 50%", 2000);
                        }
                    } else{
                        ButtonHandler.buttonContinue.display();
                        ButtonHandler.buttonContinue.unblock();
                        cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 2000, 2000);

            ArrayList<float[]> valuesAnimVitoriaTranslate = new ArrayList<>();
            valuesAnimVitoriaTranslate.add(new float[]{0f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{0.8f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{1f,0f});
            new Animation(MessagesHandler.messageInGame, "messageInGameTranslateX", "translateX", 2000, valuesAnimVitoriaTranslate, false, true).start();

            MessagesHandler.messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {Sound.play(Sound.soundTextBoxAppear, 1, 1, 0);}
            });
            MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1)+ " " +String.valueOf(SaveGame.saveGame.currentLevelNumber)+ " " + getContext().getResources().getString(R.string.nivelConcluido2));
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

            if (StarsHandler.newStars > StarsHandler.previousStars){
                float groupsUnblockedSize = resolutionX * 0.16f;
                float groupsUnblockedPadd = resolutionX * 0.02f;

                for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){
                   final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);
                   if (lgd.starsToUnlock > StarsHandler.conqueredStarsTotal && lgd.starsToUnlock <= newStarsTotal){
                           groupsUnblocked.add(
                               new Image("groupsUnblocked"+i, 0f,
                               resolutionY * 0.7f,
                               groupsUnblockedSize, groupsUnblockedSize,
                               lgd.textureUnit,Utils.getUvData256(lgd.textureMap))
                           );
                   }
                }

                int numberOfGroupsUnblocked = groupsUnblocked.size();
                if (numberOfGroupsUnblocked > 0){
                    
                    MessagesHandler.messageGroupsUnblocked.display();
                    Utils.createSimpleAnimation(MessagesHandler.messageGroupsUnblocked, "translateX", "translateX", 500, -gameAreaResolutionX*1.5f, 0f).start();
                    Sound.play(Sound.soundTextBoxAppear, 1, 1, 0);


                    float initX = (resolutionX * 0.5f) - (((numberOfGroupsUnblocked * groupsUnblockedSize) + ((numberOfGroupsUnblocked-1)*groupsUnblockedPadd))/2f);

                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        groupsUnblocked.get(i).x = initX + (i * groupsUnblockedPadd) + (i * groupsUnblockedSize);
                    }


                    float halfDifference = ((groupsUnblockedSize * 1.2f) - groupsUnblockedSize)/2f;


                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        Image gu = groupsUnblocked.get(i);
                        gu.display();
                        Utils.createAnimation5v(gu, "translateX", "translateX", 800, 0f, groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, -halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        Utils.createAnimation5v(gu, "translateY", "translateY", 800, 0f, -groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
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

            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(500, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(500, 1f);
            MessagesHandler.messageConqueredStarsTotal.y = resolutionY * 0.2f;
            MessagesHandler.starForMessage.y = resolutionY * 0.2f;
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

            Animation animMessageConqueredStarsTotal = new Animation(MessagesHandler.messageConqueredStarsTotal, "numberForAnimation", "numberForAnimation", 2500, valuesAnim, false, false);
            animMessageConqueredStarsTotal.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 1f){
                        if (starsDiference > 0){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 1));
                            Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 2f) {
                        if (starsDiference > 1){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 2));
                            Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 3f) {
                        if (starsDiference > 2){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 3));
                            Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 4f) {
                        if (starsDiference > 3){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 4));
                            Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 5f) {
                        if (starsDiference > 4){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 5));
                            Sound.play(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    }
                }
            });

            animMessageConqueredStarsTotal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    ButtonHandler.buttonContinue.display();
                    ButtonHandler.buttonContinue.unblock();
                }
            });
            animMessageConqueredStarsTotal.start();

        } else if (state == GAME_STATE_TUTORIAL) {
            
            MessagesHandler.messageMenu.clearDisplay();
            MessagesHandler.messageSubMenu.clearDisplay();
            MenuHandler.tutorialMenu.clearDisplay();
            MenuHandler.tutorialMenu.block();
            
            if (!sameState) {
                activateFrame(500);
            }

            mainActivity.hideAdView();

            Tutorial.loadTutorial();
            
            Tutorial.currentTutorialObject.showFirst();
            
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

    public static void increaseAllGameEntitiesAlpha(int duration){
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
        ballCollisionStars.clear();
        
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
        background = null;
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

    static void simulate(long elapsed, float frameDuration){

        if (gameState != GAME_STATE_VITORIA) {
            TimeHandler.updateTimeOfLevelPlay(elapsed);
        }

        ballCollidedFx -= 1;

        // atualiza posição da bola
        if (gameState == GAME_STATE_JOGAR) {
             for (int i = 0; i < balls.size(); i++) {

                 if (balls.get(i).isAlive) {

                     Ball ball = balls.get(i);
                     ball.verifyAcceleration();
                     ball.vx = (ball.dvx * (float) elapsed) / frameDuration;
                     ball.vy = (ball.dvy * (float) elapsed) / frameDuration;

                     ball.translate(ball.vx, ball.vy);

                     ball.verifyWind();
                     //Log.e("game", "ballv "+ ball.vx+" "+ball.vy);
                 }
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
                    if (ButtonHandler.button1Left.isPressed && !ButtonHandler.button2Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (ButtonHandler.button2Right.isPressed && !ButtonHandler.button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                        //bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).stop();
                    }
                }
                if (bars.size() == 2) {
                    if (ButtonHandler.button1Left.isPressed && !ButtonHandler.button1Right.isPressed) {
                        bars.get(0).moveLeft(timePercentage);
                    } else if (ButtonHandler.button1Right.isPressed && !ButtonHandler.button1Left.isPressed) {
                        bars.get(0).moveRight(timePercentage);
                    } else {
                        bars.get(0).stop();
                    }

                    if (ButtonHandler.button2Left.isPressed) {
                        bars.get(1).moveLeft(timePercentage);
                    } else if (ButtonHandler.button2Right.isPressed) {
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
                if (balls.get(i).isAlive) {
                    quad.insert(balls.get(i));
                    balls.get(i).clearCollisionData();
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
                if (balls.get(i).isAlive) {
                    if (balls.get(i).isCollided) {
                        balls.get(i).onCollision();
                    }

                    double angle = Math.toDegrees(Math.atan2(balls.get(i).dvy, balls.get(i).dvx));
                    if (balls.get(i).isAlive) {
                        Log.e(TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + angle);
                        Log.e(TAG, "                        textureMap " + balls.get(i).textureMap);
                        Log.e(TAG, "                        pos        " + balls.get(i).positionX + " - " + balls.get(i).positionY);
                        Log.e(TAG, "                        dv         " + balls.get(i).dvx + " - " + balls.get(i).dvy);
                    }
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
            ScoreHandler.verifyScoreDecay();
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

        Level.levelObject.levelGoalsObject.notifyBallsAlive(ballsNotInvencibleAlive + ballsInvencible, TimeHandler.secondsOfLevelPlay);

        ballGoalsPanel.setValues(ballsNotInvencibleAlive + ballsInvencible, Level.levelObject.minBallsAlive, ballsInvencible);
        if (Level.levelObject.minBallsAlive > ballsNotInvencibleAlive){
            setGameState(GAME_STATE_DERROTA);
        }
        
        for (int i = 0; i < ballCollisionStars.size(); i++){
            if (!ballCollisionStars.get(i).isVisible){
                //Log.e(TAG, "removing starball "+i);
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

        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.checkTransformations(true);
        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.checkTransformations(true);
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.checkTransformations(true);
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.checkTransformations(true);
        if (levelGoalsPanel != null) levelGoalsPanel.checkTransformations(true);
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.checkTransformations(true);
        if (SelectorHandle.selectorMusic != null) SelectorHandle.selectorMusic.checkTransformations(true);
        if (SelectorHandle.selectorSound != null) SelectorHandle.selectorSound.checkTransformations(true);
        if (SelectorHandle.selectorVibration != null) SelectorHandle.selectorVibration.checkTransformations(true);

        if (tittle != null) tittle.checkTransformations(true);


        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.checkTransformations(true);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.checkTransformations(true);

        if (currentLevelIcon != null) currentLevelIcon.checkTransformations(true);

        if (groupsUnblocked != null) {
            for (int i = 0; i < groupsUnblocked.size(); i++) {
                groupsUnblocked.get(i).checkTransformations(true);
            }
        }

        MessagesHandler.messageGameOver.checkTransformations(true);
        MessagesHandler.messagePreparation.checkTransformations(true);
        MessagesHandler.messageInGame.checkTransformations(true);
        MessagesHandler.messageMaxScoreTotal.checkTransformations(true);
        MessagesHandler.messageConqueredStarsTotal.checkTransformations(true);
        MessagesHandler.starForMessage.checkTransformations(true);
        MessagesHandler.bottomTextBox.checkTransformations(true);
        MessagesHandler.messageGroupsUnblocked.checkTransformations(true);
        if (MessageStarWin.messageStarsWin != null) MessageStarWin.messageStarsWin.checkTransformations(true);
        if (MessageStar.messageStars != null) MessageStar.messageStars.checkTransformations(true);

        if (bordaE != null)bordaE.checkTransformations(true);
        if (bordaD != null)bordaD.checkTransformations(true);
        if (bordaC != null)bordaC.checkTransformations(true);
        if (bordaB != null)bordaB.checkTransformations(true);

        if (frame != null)frame.checkTransformations(true);

        if (ScoreHandler.scorePanel != null) ScoreHandler.scorePanel.checkTransformations(true);
        if (ballDataPanel != null) ballDataPanel.checkTransformations(true);
        if (ballGoalsPanel != null) ballGoalsPanel.checkTransformations(true);

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.checkTransformations(true);
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.checkTransformations(true);
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.checkTransformations(true);
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.checkTransformations(true);

        if (imageTutorialDown != null) imageTutorialDown.checkTransformations(true);
        if (imageTutorialTop != null) imageTutorialTop.checkTransformations(true);

        if (messages != null) messages.checkTransformations(true);
    }

    static void render(float[] matrixView, float[] matrixProjection){
        if (background != null) {
            background.prepareRender(matrixView, matrixProjection);
        }

        if (MessagesHandler.messageCurrentLevel != null) MessagesHandler.messageCurrentLevel.prepareRender(matrixView, matrixProjection);

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

        if (MenuHandler.menuMain != null) MenuHandler.menuMain.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuInGame != null) MenuHandler.menuInGame.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuGameOver != null) MenuHandler.menuGameOver.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuTutorialUnvisited != null) MenuHandler.menuTutorialUnvisited.prepareRender(matrixView, matrixProjection);

        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.prepareRender(matrixView, matrixProjection);
        if (levelGoalsPanel != null) levelGoalsPanel.prepareRender(matrixView, matrixProjection);
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.prepareRender(matrixView, matrixProjection);
        if (SelectorHandle.selectorVibration != null) SelectorHandle.selectorVibration.prepareRender(matrixView, matrixProjection);
        if (SelectorHandle.selectorMusic != null) SelectorHandle.selectorMusic.prepareRender(matrixView, matrixProjection);
        if (SelectorHandle.selectorSound != null) SelectorHandle.selectorSound.prepareRender(matrixView, matrixProjection);
        if (tittle != null) {tittle.prepareRender(matrixView, matrixProjection);}

        if (Tutorial.tutorialImage != null) Tutorial.tutorialImage.prepareRender(matrixView, matrixProjection);
        if (Tutorial.tutorialTextBox != null) Tutorial.tutorialTextBox.prepareRender(matrixView, matrixProjection);

        if (currentLevelIcon != null) currentLevelIcon.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < groupsUnblocked.size(); i++) {
            groupsUnblocked.get(i).prepareRender(matrixView, matrixProjection);
        }



        MessagesHandler.messageGameOver.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messagePreparation.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageInGame.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageMenu.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageSubMenu.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageConqueredStarsTotal.prepareRender(matrixView, matrixProjection);
        MessagesHandler.starForMessage.prepareRender(matrixView, matrixProjection);
        MessagesHandler.messageGroupsUnblocked.prepareRender(matrixView, matrixProjection);
        if (MessageStar.messageStars != null) MessageStar.messageStars.prepareRender(matrixView, matrixProjection);
        if (MessageStarWin.messageStarsWin != null) MessageStarWin.messageStarsWin.prepareRender(matrixView, matrixProjection);

        if (MessagesHandler.messageTime != null) MessagesHandler.messageTime.prepareRender(matrixView, matrixProjection);
        if (bordaE != null)bordaE.prepareRender(matrixView, matrixProjection);
        if (bordaD != null)bordaD.prepareRender(matrixView, matrixProjection);
        if (bordaC != null)bordaC.prepareRender(matrixView, matrixProjection);
        if (bordaB != null)bordaB.prepareRender(matrixView, matrixProjection);

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

        MessagesHandler.bottomTextBox.prepareRender(matrixView, matrixProjection);

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
        if (MenuHandler.menuMain != null) MenuHandler.menuMain.verifyListener();
        if (MenuHandler.menuInGame != null) MenuHandler.menuInGame.verifyListener();
        if (MenuHandler.menuGameOver != null) MenuHandler.menuGameOver.verifyListener();
        if (MenuHandler.menuOptions != null) MenuHandler.menuOptions.verifyListener();
        if (MenuHandler.menuTutorialUnvisited != null) MenuHandler.menuTutorialUnvisited.verifyListener();

        if (MenuHandler.groupMenu != null) MenuHandler.groupMenu.verifyListener();
        if (MenuHandler.levelMenu != null) MenuHandler.levelMenu.verifyListener();
        if (MenuHandler.tutorialMenu != null) MenuHandler.tutorialMenu.verifyListener();
        // levelGoalsPanel não precisa de listener???
        if (ButtonHandler.buttonReturn != null) ButtonHandler.buttonReturn.verifyListener();
        if (ButtonHandler.buttonReturnObjectivesPause != null)
            ButtonHandler.buttonReturnObjectivesPause.verifyListener();
        if (ButtonHandler.buttonContinue != null) ButtonHandler.buttonContinue.verifyListener();
        if (MenuHandler.menuInGameOptions != null) MenuHandler.menuInGameOptions.verifyListener();
        if (SelectorHandle.selectorVibration != null) SelectorHandle.selectorVibration.verifyListener();
        if (SelectorHandle.selectorMusic != null) SelectorHandle.selectorMusic.verifyListener();
        if (SelectorHandle.selectorSound != null) SelectorHandle.selectorSound.verifyListener();

        if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.verifyListener();
        if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.verifyListener();
        if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.verifyListener();
        if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.verifyListener();
        if (MessagesHandler.bottomTextBox != null) MessagesHandler.bottomTextBox.verifyListener();

        // elimina os touchevents que tiverem o UP ativado, ou seja, que já foram considerados nesta passagem
        for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
            if (Game.touchEvents.get(i2).type == TouchEvent.TOUCH_TYPE_UP) {
                Game.touchEvents.remove(i2);
            }
        }
    }

    public static ArrayList<Entity> collectAllMenuEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(MenuHandler.menuMain);
        list.add(MenuHandler.menuOptions);
        list.add(MenuHandler.menuTutorialUnvisited);
        list.add(MenuHandler.groupMenu);
        list.add(MenuHandler.levelMenu);
        list.add(MenuHandler.tutorialMenu);
        list.add(levelGoalsPanel);
        list.add(Tutorial.tutorialImage);// TODO ????
        list.add(Tutorial.tutorialTextBox);// TODO ????
        list.add(ButtonHandler.buttonReturn);
        list.add(ButtonHandler.buttonReturnObjectivesPause);
        list.add(ButtonHandler.buttonContinue);
        list.add(MenuHandler.menuInGameOptions);
        list.add(SelectorHandle.selectorVibration);
        list.add(SelectorHandle.selectorMusic);
        list.add(SelectorHandle.selectorSound);
        list.add(MenuHandler.menuInGame);
        list.add(MenuHandler.menuGameOver);
        list.add(tittle);
        list.add(MessagesHandler.messageGameOver);
        list.add(MessagesHandler.messagePreparation);
        list.add(MessagesHandler.messageInGame);
        list.add(MessagesHandler.messageMenu);
        list.add(MessagesHandler.messageSubMenu);
        list.add(MessagesHandler.messageGroupsUnblocked);
        list.add(MessagesHandler.messageMaxScoreTotal);
        list.add(MessagesHandler.messageConqueredStarsTotal);
        list.add(MessagesHandler.starForMessage);
        list.add(MessagesHandler.bottomTextBox);
        
        return list;
    }

    static public Context getContext(){
        return mainActivity.getApplicationContext();
    }

}
