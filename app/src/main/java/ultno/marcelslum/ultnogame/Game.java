package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


// TODO erro: jogo trava quando se aperta os dois botões de flecha ao mesmo tempo: resolvido
// TODO pausar a música quando minimiza o aplicativo: resolvido?
// TODO verificar arquivo targets.png se está atualizado
// TODO pausar todos os sons do sound pool quando o jogo fecha
// TODO voltar ao estado anterior quando o jogo fecha



/** * Created by marcel on 01/08/2016.
 */
public class Game {



    public static final int BALL_WEIGHT = 1;
    public static final int BORDA_WEIGHT = 10;
    public static final int OBSTACLES_WEIGHT = 7;
    public static final int TARGET_WEIGHT = 10;
    public static final int BAR_WEIGHT = 8;
    private static Game ourInstance = new Game();
    public static Context context;

    // entities
    public static Menu menuMain;
    public static Menu menuOptions;
    public static Menu menuInGame;
    public static Menu menuTutorial;
    public static Selector selectorLevel;
    public static Selector selectorDificulty;
    public static Selector selectorMusic;
    public static Selector selectorSound;
    //public static Selector selectorVolumn;

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

    public static Rectangle bordaC;
    public static Rectangle bordaE;
    public static Rectangle bordaD;
    public static Rectangle bordaB;

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
    public static int menuVolume = 100;
    public static  int volume = 100;
    public final static int [] possibleVolums = new int []{0,10,20,30,40,50,60,70,80,90,100};

    //  level
    public static Level levelObject;
    public final static int quantityOfLevels = 20;
    public static int levelNumber = 9;
    public static int maxLevel = 1;

    // game state
    public static int gameState;
    public final static int GAME_STATE_JOGAR = 10;
    public final static int GAME_STATE_PREPARAR = 11;
    public final static int GAME_STATE_MENU =  12;
    public final static int GAME_STATE_VITORIA =  13;
    public final static int GAME_STATE_DERROTA =  14;
    public final static int GAME_STATE_TUTORIAL =  15;
    //public final static int GAME_STATE_REINICIAR =  16;
    public final static int GAME_STATE_PAUSE =  16;
    public final static int GAME_STATE_OPCOES =  17;

    public final static int DIFICULDADE_FACIL = 0;
    public final static int DIFICULDADE_NORMAL = 1;
    public final static int DIFICULDADE_DIFÍCIL = 2;
    public final static int DIFICULDADE_INSANO = 3;

    public final static int TEXTURE_MAP_NUMBERS_SCORE1 = 1;
    public final static int TEXTURE_MAP_NUMBERS_SCORE2 = 2;
    public final static int TEXTURE_MAP_NUMBERS_SCORE3 = 3;
    public final static int TEXTURE_MAP_NUMBERS_SCORE4 = 4;
    public final static int TEXTURE_MAP_NUMBERS_SCORE5 = 5;
    public final static int TEXTURE_MAP_NUMBERS_SCORE6 = 6;
    public final static int TEXTURE_MAP_NUMBERS_SCORE7 = 7;
    public final static int TEXTURE_MAP_NUMBERS_SCORE8 = 8;
    public final static int TEXTURE_MAP_NUMBERS_SCORE9 = 9;
    public final static int TEXTURE_MAP_NUMBERS_SCORE0 = 10;
    public final static int TEXTURE_MAP_NUMBERS_POINT1 = 11;
    public final static int TEXTURE_MAP_NUMBERS_POINT2 = 12;
    public final static int TEXTURE_MAP_NUMBERS_POINT3 = 13;
    public final static int TEXTURE_MAP_NUMBERS_POINT4 = 14;
    public final static int TEXTURE_MAP_NUMBERS_POINT5 = 15;
    public final static int TEXTURE_MAP_NUMBERS_POINT6 = 16;
    public final static int TEXTURE_MAP_NUMBERS_POINT7 = 17;
    public final static int TEXTURE_MAP_NUMBERS_POINT8 = 18;
    public final static int TEXTURE_MAP_NUMBERS_POINT9 = 19;
    public final static int TEXTURE_MAP_NUMBERS_POINT0 = 20;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR1 = 21;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR2 = 22;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR3 = 23;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR4 = 24;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR5 = 25;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_COLOR6 = 26;
    public final static int TEXTURE_MAP_NUMBERS_EXPLODE_BALL = 27;
    
    public final static float [] textButtonsAndBallsColumnsAndLines = new float[]{0f, 128f, 256f, 384f, 512f, 640f, 768f, 896f, 1024f};
    public static long initTime;
    public static float effectiveScreenHeight;
    public static float effectiveScreenWidth;
    public static int dificulty;

    // bars and balls data
    //public float [] barsInitialPositionX = new float[10];
    //public float [] barsInitialPositionY = new float[10];
    //public float [] barsDesiredVelocityX = new float[10];
    //public float [] barsDesiredVelocityY = new float[10];
    //public float[] ballsInitialPositionX = new float[10];
    //public float[] ballsInitialPositionY = new float[10];
    //public float[] ballsDesiredVelocityX = new float[10];
    //public float[] ballsDesiredVelocityY = new float[10];


    public boolean ballFall;

    // programs
    public static Program imageProgram;
    public static Program imageColorizedProgram;
    public static Program lineProgram;
    public static Program textProgram;
    public static Program solidProgram;
    public static Program imageColorizedFxProgram;
    public static Program windProgram;
    public static Program specialBallProgram;

    public static int ballsNotInvencibleAlive;
    public static int ballsInvencible;
    //int ballsAlive;

    
    public static long initialTimePointsDecay;
    public static final long TIME_FOR_POINTS_DECAY = 3000;
    public static final int POINTS_DECAY = 10;

    private Game() {
        // initialize data
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
        lines = new ArrayList<>();

        //barsInitialPositionX = new float[10];
        //barsInitialPositionY = new float[10];
        //barsDesiredVelocityX = new float[10];
        //barsDesiredVelocityY = new float[10];
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
        gameState = state;
        clearAllMenuEntities();

        if (state == GAME_STATE_OPCOES){
            tittle.display();
            menuMain.isBlocked = true;
            menuOptions.isBlocked = false;
            menuOptions.display();
            setBottomText("");
        } else if (state == GAME_STATE_MENU){
            menuOptions.isBlocked = true;
            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();
            eraseAllTutorials();
            menuMain.isBlocked = false;
            menuMain.display();
            tittle.display();
            messageCurrentLevel.display();
            messageMaxScoreTotal.display();
            setBottomText("");
            // TODO verificar conexão
            setBottomText("Não conectado");
            bottomTextBox.display();
            messageMaxScoreTotal.setText(
                    context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal());

        } else if (state == GAME_STATE_PREPARAR){
            eraseAllTutorials();
            levelObject.loadEntities();
            Sound.music = MediaPlayer.create(context, R.raw.musicgroove90);
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
            menuInGame.appearAndUnblock(300);
            messageGameOver.display();

            if (scorePanel.value > 0) {
                scorePanel.showMessage("-50%", 1000);
                int points = scorePanel.value / 2;
                scorePanel.setValue(points, true, 1000, true);
                if (Storage.getLevelMaxScore(Game.levelNumber) < points) {
                    Storage.setLevelMaxScore(Game.levelNumber, points);
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
            final Text innerMessageInGame = messageInGame;
            Animation anim = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 4000, valuesAnimPause, true, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessageInGame.numberForAnimation == 1f){
                        innerMessageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (innerMessageInGame.numberForAnimation == 2f) {
                        innerMessageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (innerMessageInGame.numberForAnimation == 3f) {
                        innerMessageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    }
                }
            });
            anim.start();

            messageInGame.setText(context.getResources().getString(R.string.pause));
            messageInGame.increaseAlpha(100, 1f);
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
                Utils.createSimpleAnimation(button1Right, "alphaVitoria", "alpha", 1000, button1Right.alpha, 0f).start();
                button1Right.block();
            }
            if (button2Left != null) {
                Utils.createSimpleAnimation(button2Left, "alphaVitoria", "alpha", 1000, button2Left.alpha, 0f).start();
                button2Left.block();
                Utils.createSimpleAnimation(button2Right, "alphaVitoria", "alpha", 1000, button2Right.alpha, 0f).start();
                button2Right.block();
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
            final Text innerMessageInGame = messageInGame;
            Animation anim = new Animation(messageInGame, "messageInGameColor", "numberForAnimation", 3000, valuesAnimVitoria, true, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessageInGame.numberForAnimation == 1f){
                        innerMessageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (innerMessageInGame.numberForAnimation == 2f) {
                        innerMessageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (innerMessageInGame.numberForAnimation == 3f) {
                        innerMessageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    } else if (innerMessageInGame.numberForAnimation == 4f) {
                        innerMessageInGame.setColor(new Color(1f, 0f, 0f, 1f));
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
                        if (Storage.getLevelMaxScore(levelNumber) < scorePanel.value){
                            Storage.setLevelMaxScore(levelNumber, scorePanel.value);
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

            menuInGame.getMenuOptionByName("Continuar").textObject.setText(context.getResources().getString(R.string.menuInGameNivelConcluido1)+" "+(levelNumber+1));
            menuInGame.alpha = 0f;
            messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Sound.play(Sound.soundTextBoxAppear, 1, 1, 0);
                    Game.menuInGame.appearAndUnblock(800);
                }
            });

            messageInGame.setText(context.getResources().getString(R.string.nivelConcluido1)+ " "+levelNumber+ " "+context.getResources().getString(R.string.nivelConcluido2));
            messageInGame.display();

            Utils.createSimpleAnimation(objectivePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY*0.2f).start();
            Utils.createSimpleAnimation(objectivePanel, "scaleX", "scaleX", 2000, 1f, 2.5f).start();
            Utils.createSimpleAnimation(objectivePanel, "scaleY", "scaleY", 2000, 1f, 2.5f).start();
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



            // TODO se for o último level
            levelNumber += 1;

        } else if (state == GAME_STATE_TUTORIAL) {
            Game.levelObject.loadEntities();
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
        bordaB = null;
        bordaC = null;
        bordaD = null;
        bordaE = null;
        button1Left = null;
        button1Right = null;
        button2Left = null;
        button2Right = null;
        buttonSound = null;
        buttonMusic = null;
        background = null;
    }

    public static void eraseAllTutorials() {
        if (levelObject != null) {
            for (int i = 0; i < levelObject.tutorials.size(); i++) {
                levelObject.tutorials.get(i).textBox = null;
                levelObject.tutorials.clear();
            }
        }
    }

    public static void init(){
        Storage.initializeStorage(context, quantityOfLevels);
            levelNumber = Storage.getActualLevel();
            //Log.e("game ", "levelNumber "+levelNumber);
            maxLevel = Storage.getMaxLevel();
            //Log.e("game ", "maxLevel "+maxLevel);

            dificulty = Storage.getDificulty();

        levelNumber = Storage.getActualLevel();
        
        initTime = Utils.getTime();
        initTextures();
        Sound.init();
        initPrograms();
        initFont();
        initMenus();
        initTexts();
    }

    private static void initTextures() {
        Texture.textures = new ArrayList<>();
        Texture.textures.add(new Texture(Texture.TEXTURE_BUTTONS_AND_BALLS, "drawable/botoesebolas2"));
        Texture.textures.add(new Texture(Texture.TEXTURE_FONT, "drawable/jetset"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TARGETS, "drawable/targets"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BARS, "drawable/bars"));
        Texture.textures.add(new Texture(Texture.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE, "drawable/numbers_explosion5"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TITTLE, "drawable/tittle"));
        Texture.textures.add(new Texture(Texture.TEXTURE_SPECIAL_BALL, "drawable/bolaespecial2"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BACKGROUND, "drawable/finalback1"));
        //Texture.textures.add(new Texture(Texture.TEXTURE_OBSTACLE, "drawable/obstacle"));
    }

    public static void initTexts(){
        tittle = new Image("tittle",
                gameAreaResolutionX * 0.3f, gameAreaResolutionY * 0.07f,
                gameAreaResolutionX * 0.4f, gameAreaResolutionX * 0.4f * 0.3671875f,
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


        messageGameOver = new Text("messageGameOver",
            gameAreaResolutionX*0.5f, gameAreaResolutionY*0.2f, gameAreaResolutionY*0.2f,
            context.getResources().getString(R.string.messageGameOver), font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

            final Text innerMessageGameOver = messageGameOver;
            ArrayList<float[]> valuesAnimationGameOver = new ArrayList<>();
                valuesAnimationGameOver.add(new float[]{0f,1f});
                valuesAnimationGameOver.add(new float[]{0.55f,2f});
                valuesAnimationGameOver.add(new float[]{0.85f,3f});
            Animation animMessageGameOver = new Animation(innerMessageGameOver, "numberForAnimation", "numberForAnimation", 4000, valuesAnimationGameOver, true, false);
            animMessageGameOver.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessageGameOver.numberForAnimation == 1f){
                        innerMessageGameOver.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (innerMessageGameOver.numberForAnimation == 2f) {
                        innerMessageGameOver.setColor(new Color(1f, 0f, 0f, 1f));
                    } else if (innerMessageGameOver.numberForAnimation == 3f) {
                        innerMessageGameOver.setColor(new Color(0f, 0f, 1f, 1f));
                    }
                }
            });
            animMessageGameOver.start();


        messagePreparation = new Text("messagePreparation",
            gameAreaResolutionX*0.5f, gameAreaResolutionY*0.3f, gameAreaResolutionY*0.4f,
                " ", font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageInGame = new Text("messageInGame",
            gameAreaResolutionX*0.5f, gameAreaResolutionY*0.25f, gameAreaResolutionY*0.2f,
                context.getResources().getString(R.string.pause), font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);

        messageCurrentLevel = new Text("messageCurrentLevel",
             resolutionX*0.05f, resolutionY*0.72f, resolutionY*0.04f,
                context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(levelNumber) + " - " + context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(levelNumber)), font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                resolutionX*0.05f, resolutionY*0.84f, resolutionY*0.04f,
                context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal(), font, new Color(0f, 0f, 0f, 0.5f));

        bottomTextBox = new TextBox.TextBoxBuilder("bottomTextBox")
                            .position(resolutionX*0.05f, resolutionY*0.9f)
                            .width(resolutionX*0.9f)
                            .size(resolutionY*0.04f)
                            .text("...")
                            .withoutArrow()
                            .isHaveFrame(true)
                            .isHaveArrowContinue(false)
                            .build();
        
        setBottomText("");
        
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
            messageCurrentLevel.y = resolutionY - bottomTextBox.height - (resolutionY * 0.14f);
            messageMaxScoreTotal.y = resolutionY - bottomTextBox.height - (resolutionY * 0.08f);
        } else {
            if (!previousText.equals("...")){
                appearOrDesapear = true;
            }
            bottomTextBox.setText("...");
            bottomTextBox.setPositionY(resolutionY*2);
            messageCurrentLevel.y = resolutionY - (resolutionY * 0.14f);
            messageMaxScoreTotal.y = resolutionY - (resolutionY * 0.08f);
        }

        float difference = previousPosition - bottomTextBox.y;
        if (difference != 0) {
            Utils.createSimpleAnimation(bottomTextBox, "translateY", "translateY", 200, difference, 0.0f).start();
        }

        if (appearOrDesapear){
            Sound.play(Sound.soundTextBoxAppear, 0.8f, 0.8f, 0);
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
        list.add(bordaE);
        list.add(bordaD);
        list.add(bordaC);
        list.add(bordaB);
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

    public static int getMaxScoreTotal(){
        int scoreTotal = 0;
        for (int i = 0; i < quantityOfLevels; i++){
            scoreTotal += Storage.getLevelMaxScore(i+1);
        }
        return scoreTotal;
    }


    public static void initMenus(){

        float fontSize = gameAreaResolutionY*0.09f;


        // cria o menu options
        menuOptions = new Menu("menuOptions", gameAreaResolutionX/2, gameAreaResolutionY*0.4f, fontSize, font);

        // cria o seletor de dificuldade -------------------------------------------------------------------------------------
        selectorDificulty = new Selector("selectorDificulty", 0f,0f, fontSize, "",
            new String[]{
                    context.getResources().getString(R.string.facil),
                    context.getResources().getString(R.string.normal),
                    context.getResources().getString(R.string.dificil),
                    context.getResources().getString(R.string.insano)

            }, font);
        menuOptions.addMenuOption("dificuldade", context.getResources().getString(R.string.dificuldade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.selectorDificulty.fromMenu(Game.menuOptions);
                if (dificulty == DIFICULDADE_FACIL){
                    selectorDificulty.setSelectedValue(0);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeFacil));
                } else if (dificulty == DIFICULDADE_NORMAL){
                    selectorDificulty.setSelectedValue(1);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeNormal));
                } else if (dificulty == DIFICULDADE_DIFÍCIL){
                    selectorDificulty.setSelectedValue(2);
                    setBottomText(context.getResources().getString(R.string.mensagemDificuldadeDificil));
                } else if (dificulty == DIFICULDADE_INSANO){
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
                Game.changeDificulty(selectorDificulty.selectedValue);
            }
        });

        selectorDificulty.setOnConclude(new Selector.OnConclude() {
            @Override
            public void onConclude() {
                setBottomText("");
            }
        });

        // cria o seletor de musica -------------------------------------------------------------------------------------
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

        // cria o seletor de sons -------------------------------------------------------------------------------------
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
                setGameState(GAME_STATE_MENU);
            }
        });


        
        // cria o menu principal
        menuMain = new Menu("menuMain", gameAreaResolutionX/2, gameAreaResolutionY*0.4f, fontSize, font);

        // adiciona a opção de iniciar o jogo
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", context.getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                LevelLoader.loadLevel(Game.levelNumber);
                TutorialLoader.loadTutorial(Game.levelNumber);

                //Log.e("game", "tutorials size: "+ Game.levelObject.tutorials.size());
                if (Game.levelObject.tutorials.size() > 0) {

                    //Log.e("game ", "Storage.getLevelTutorialSaw(Game.levelNumber) "+Storage.getLevelTutorialSaw(Game.levelNumber));

                    if (!Storage.getLevelTutorialSaw(Game.levelNumber)) {
                        //Log.e("game", "tutorial ainda não visto");
                        Storage.setLevelTutorialSaw(Game.levelNumber, true);
                        Game.levelObject.loadEntities();
                        Game.setGameState(GAME_STATE_TUTORIAL);
                        Game.levelObject.showFirstTutorial();
                    } else {
                        //Log.e("game", "tutorial já visto");
                        //Log.e("game", "game blocked "+isBlocked);
                        Game.menuTutorial.getMenuOptionByName("exibirTutorial").setText = context.getResources().getString(R.string.menuTutorialExibirTutorial) + Game.levelNumber;
                        Game.menuTutorial.unblock();
                        Game.menuTutorial.display();
                        tittle.display();
                    }
                } else {
                    //Log.e("game", "load Entities");
                    Game.setGameState(GAME_STATE_PREPARAR);
                }
            }
        });

        // prepara os valores para o seletor de nível
        String [] levels = new String [quantityOfLevels-1];
        for (int i = 0; i < quantityOfLevels-1; i++){
            levels[i] = Integer.toString(i+1);
        }

        // cria o seletor de nível
        selectorLevel = new Selector("selectorLevel", 0f,0f, fontSize, "", levels, font);
        final Selector innerSelectorLevel = selectorLevel;
        // adiciona a opção de selecionar nível
        menuMain.addMenuOption("SelecionarNivel", context.getResources().getString(R.string.menuPrincipalAlterarNivel), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerSelectorLevel.fromMenu(innerMenu);
            }
        });

        // ajusta a posição do seletor de nível
        MenuOption menuOptionSelectLevel = menuMain.getMenuOptionByName("SelecionarNivel");
        selectorLevel.setPosition(menuOptionSelectLevel.x + (menuOptionSelectLevel.width), menuOptionSelectLevel.y);
        selectorLevel.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                Game.changeLevel(innerSelectorLevel.selectedValue+1);
            }
        });


        // adiciona a opção de acessar as opções do jogo
        menuMain.addMenuOption("options", context.getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                setGameState(GAME_STATE_OPCOES);

            }
        });

        
        // cria o menu in game
        menuInGame = new Menu("menuInGame",gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        final Menu innerMenuInGame = menuInGame;
        menuInGame.addMenuOption("Continuar", context.getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_DERROTA){
                    LevelLoader.loadLevel(Game.levelNumber);
                    Game.menuInGame.clearDisplay();
                    Game.setGameState(GAME_STATE_PREPARAR);
                } else if (Game.gameState == GAME_STATE_VITORIA){
                    Game.menuMain.getMenuOptionByName("IniciarJogo").fireOnChoice();
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
                innerMenuInGame.block();
                Game.blockAndWaitTouchRelease();
                Game.setGameState(GAME_STATE_MENU);
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
               Game.levelObject.showingTutorial = 0;
               Game.levelObject.tutorials.get(0).show(Sound.soundTextBoxAppear, 0.01f* (float) volume);
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
                Game.setGameState(GAME_STATE_MENU);
                
            }
        });
    }

    private static void changeDificulty(int selectedValue) {
        if (selectedValue == 0){
            dificulty = DIFICULDADE_FACIL;
            setBottomText(context.getResources().getString(R.string.mensagemDificuldadeFacil));
        } else if (selectedValue == 1){
            dificulty = DIFICULDADE_NORMAL;
            setBottomText(context.getResources().getString(R.string.mensagemDificuldadeNormal));
        } else if (selectedValue == 2){
            dificulty = DIFICULDADE_DIFÍCIL;
            setBottomText(context.getResources().getString(R.string.mensagemDificuldadeDificil));
        } else if (selectedValue == 3){
            dificulty = DIFICULDADE_INSANO;
            setBottomText(context.getResources().getString(R.string.mensagemDificuldadeInsano));
        }
        Storage.setDificulty(selectedValue);

    }

    private static void changeLevel(int level) {
        levelNumber = level;
        
        // alterar texto que mostra o level
        messageCurrentLevel.setText(
            context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(levelNumber) + " - "+
                    context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(levelNumber))
        );

        Storage.setActualLevel(level);
    }

    public static void initPrograms(){

        imageProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_image),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_image));

        imageColorizedProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_imagecolorized),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_imagecolorized));

        imageColorizedFxProgram = new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_imagecolorizedfx),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_imagecolorizedfx));

        textProgram  =new Program(Utils.readRawTextFile(Game.context, R.raw.shader_vertex_text),
                Utils.readRawTextFile(Game.context, R.raw.shader_frag_text));

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
        font = new Font(1,textProgram);
    }

    public static void initSounds(){

        volume = Storage.getVolume();


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
                if (bars.size() > 0) {
                    if (button1Left.isPressed) {
                        bars.get(0).vx = -(bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else if (button1Right.isPressed) {
                        bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).vx = 0f;
                    }

                    bars.get(0).translate(bars.get(0).vx, 0);
                    bars.get(0).verifyWind();

                    if (bars.size() == 2) {
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
        objectivePanel.setValues(ballsNotInvencibleAlive + ballsInvencible, levelObject.minBallsAlive, ballsInvencible);
        if (levelObject.minBallsAlive > ballsNotInvencibleAlive){
            setGameState(GAME_STATE_DERROTA);
        }
    }
    
    public static void checkTransformations(){

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
        if (menuTutorial != null) menuTutorial.checkTransformations(true);
        if (selectorLevel != null) selectorLevel.checkTransformations(true);

        if (menuOptions != null)menuOptions.checkTransformations(true);
        if (selectorDificulty != null)selectorDificulty.checkTransformations(true);
        if (selectorMusic != null)selectorMusic.checkTransformations(true);
        if (selectorSound != null)selectorSound.checkTransformations(true);



        if (tittle != null) tittle.checkTransformations(true);

        if (gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  levelObject.showingTutorial){
                levelObject.tutorials.get(levelObject.showingTutorial).textBox.checkTransformations(true);
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

        if (scorePanel != null) scorePanel.checkTransformations(true);
        if (objectivePanel != null) objectivePanel.checkTransformations(true);

        if (button1Left != null) button1Left.checkTransformations(true);
        if (button1Right != null) button1Right.checkTransformations(true);
        if (button2Left != null) button1Left.checkTransformations(true);
        if (button2Right != null) button1Right.checkTransformations(true);

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
        if (menuTutorial != null) menuTutorial.prepareRender(matrixView, matrixProjection);
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);

        if (menuOptions != null)menuOptions.prepareRender(matrixView, matrixProjection);
        if (selectorDificulty != null)selectorDificulty.prepareRender(matrixView, matrixProjection);
        if (selectorMusic != null)selectorMusic.prepareRender(matrixView, matrixProjection);
        if (selectorSound != null)selectorSound.prepareRender(matrixView, matrixProjection);

        if (tittle != null) tittle.prepareRender(matrixView, matrixProjection);

        if (gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  levelObject.showingTutorial){
                levelObject.tutorials.get(levelObject.showingTutorial).textBox.prepareRender(matrixView, matrixProjection);
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
        if (button2Left != null) button1Left.prepareRender(matrixView, matrixProjection);
        if (button2Right != null) button1Right.prepareRender(matrixView, matrixProjection);

        if (buttonMusic != null) buttonMusic.prepareRender(matrixView, matrixProjection);
        if (buttonSound != null) buttonSound.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < messages.size(); i++){
            messages.get(i).prepareRender(matrixView, matrixProjection);
        }
    }

    public static void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents.size() == 0){
                isBlocked = false;
            }
       }
    }

    public static void verifyListeners() {

        //Log.e("game", ""+touchEvents.size());

        if (!isBlocked) {
            for (int i = 0; i < interactionListeners.size(); i++) {
                interactionListeners.get(i).verify();
            }
        }

        if (menuMain != null) menuMain.verifyListener();
        if (menuInGame != null) menuInGame.verifyListener();
        if (menuTutorial != null) menuTutorial.verifyListener();
        if (selectorLevel != null) selectorLevel.verifyListener();
        if (menuOptions != null)menuOptions.verifyListener();
        if (selectorDificulty != null)selectorDificulty.verifyListener();
        if (selectorMusic != null)selectorMusic.verifyListener();
        if (selectorSound != null)selectorSound.verifyListener();

        if (gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  levelObject.showingTutorial){
                levelObject.tutorials.get(levelObject.showingTutorial).textBox.verifyListener();
            }
        }

        if (button1Left != null) button1Left.verifyListener();;
        if (button1Right != null) button1Right.verifyListener();
        if (button2Left != null) button1Left.verifyListener();
        if (button2Right != null) button1Right.verifyListener();
        if (buttonMusic != null) buttonMusic.verifyListener();
        if (buttonSound != null) buttonSound.verifyListener();
    }
}
