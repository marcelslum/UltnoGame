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

    int[] texturenames;

    public static final int BALL_WEIGHT = 1;
    public static final int BORDA_WEIGHT = 10;
    public static final int OBSTACLES_WEIGHT = 9;
    public static final int TARGET_WEIGHT = 8;
    public static final int BAR_WEIGHT = 2;
    private static Game ourInstance = new Game();
    Context context;

    // entities
    public static Menu menuMain;
    public static Menu menuInGame;
    public static Menu menuTutorial;
    public static Selector selectorLevel;
    public static Selector selectorVolumn;

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
    public ArrayList<Line> lines;
    
    public Background background;

    public static ScorePanel scorePanel;
    public ObjectivePanel objectivePanel;

    public Rectangle bordaC;
    public Rectangle bordaE;
    public Rectangle bordaD;
    public Rectangle bordaB;

    public Button button1Left;
    public Button button1Right;
    public Button button2Left;
    public Button button2Right;
    public ButtonOnOff buttonSound;
    public ButtonOnOff buttonMusic;

    public Image tittle;

    public Text messageGameOver;
    public Text messagePreparation;
    public Text messageInGame;
    public Text messageCurrentLevel;
    public Text messageMaxScoreLevel;
    public Text messageMaxScoreTotal;

    // quadtree objects
    public static Quadtree quad;

    // font
    public static Font font;

    // sounds
    SoundPool soundPool;
    int soundBallHit;
    int soundCounter;
    int soundDestroyTarget;
    int soundScore;
    int soundAlarm;
    int soundBallFall;
    int soundBlueBallExplosion1;
    int soundBlueBallExplosion2;
    int soundExplosion1;
    int soundExplosion2;
    int soundGameOver;
    int soundMenuSelectBig;
    int soundMenuSelectSmall;
    int soundWin;
    int soundTextBoxAppear;
    MediaPlayer music;

    // scree properties
    public float gameAreaResolutionX;
    public float gameAreaResolutionY;
    public float resolutionX;
    public float resolutionY;
    public float screenOffSetX;
    public float screenOffSetY;

    int ballCollidedFx = 0;

    // options
    boolean musicOn = true;
    boolean isBlocked;
    public int menuVolume = 100;
    public int volume = 100;
    public final static int [] possibleVolums = new int []{0,10,20,30,40,50,60,70,80,90,100};

    //  level
    Level levelObject;
    public final static int quantityOfLevels = 20;
    int levelNumber = 9;
    int maxLevel = 1;

    // game state
    public int gameState;
    public final static int GAME_STATE_JOGAR = 10;
    public final static int GAME_STATE_PREPARAR = 11;
    public final static int GAME_STATE_MENU =  12;
    public final static int GAME_STATE_VITORIA =  13;
    public final static int GAME_STATE_DERROTA =  14;
    public final static int GAME_STATE_TUTORIAL =  15;
    public final static int GAME_STATE_REINICIAR =  16;
    public final static int GAME_STATE_PAUSE =  16;


    public final static int TEXTURE_BUTTONS_AND_BALLS = 0;
    public final static int TEXTURE_FONT = 1;
    public final static int TEXTURE_TARGETS = 2;
    public final static int TEXTURE_BARS = 3;
    public final static int TEXTURE_BACKGROUND = 4;
    public final static int TEXTURE_NUMBERS_EXPLOSION_OBSTACLE = 5;
    public final static int TEXTURE_TITTLE = 6;

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

    // bars and balls data
    public float [] barsInitialPositionX = new float[10];
    public float [] barsInitialPositionY = new float[10];
    public float [] barsDesiredVelocityX = new float[10];
    public float [] barsDesiredVelocityY = new float[10];
    public float[] ballsInitialPositionX = new float[10];
    public float[] ballsInitialPositionY = new float[10];
    public float[] ballsDesiredVelocityX = new float[10];
    public float[] ballsDesiredVelocityY = new float[10];

    // sat data
    SatPolygon polygon1;
    SatPolygon polygon2;
    SatCircle circle1;
    SatCircle circle2;
    public boolean ballFall;

    // programs
    public Program imageProgram;
    public Program imageColorizedProgram;
    public Program lineProgram;
    public Program textProgram;
    public Program solidProgram;
    public Program imageColorizedFxProgram;

    int ballsNotInvencibleAlive;
    int ballsInvencible;
    int ballsAlive;
    
    private int streamIdSoundAlarm;
    
    long initialTimePointsDecay;
    static final long TIME_FOR_POINTS_DECAY = 3000l;
    static final int POINTS_DECAY = 10;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        // initialize data
        targets = new ArrayList<Target>();
        balls = new ArrayList<Ball>();
        obstacles = new ArrayList<Obstacle>();
        windows = new ArrayList<WindowGame>();
        touchEvents = new ArrayList<TouchEvent>();
        texts = new ArrayList<Text>();
        interactionListeners = new ArrayList<>();
        particleGenerator = new ArrayList<>();
        ballParticleGenerator = new ArrayList<>();
        bars = new ArrayList<Bar>();
        menus = new ArrayList<>();
        selectors = new ArrayList<>();
        textBoxes = new ArrayList<>();
        messages = new ArrayList<>();
        lines = new ArrayList<>();

        barsInitialPositionX = new float[10];
        barsInitialPositionY = new float[10];
        barsDesiredVelocityX = new float[10];
        barsDesiredVelocityY = new float[10];

        ArrayList<Vector> points = new ArrayList<>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        polygon1 = new SatPolygon(new Vector(0, 0), points);

        ArrayList<Vector> points2 = new ArrayList<>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        polygon2 = new SatPolygon(new Vector(0, 0), points2);

        circle1 = new SatCircle(new Vector(0,0),0);
        circle2 = new SatCircle(new Vector(0,0),0);
   }
   
      public void addBall(Ball ball){
        this.balls.add(ball);
    }
    
    public void addBar(Bar bar){
        this.bars.add(bar);
    }

    public void addTarget(Target target){
        this.targets.add(target);
    }

    public void addObstacle(Obstacle obstacle){
        this.obstacles.add(obstacle);
    }

    public void addWindow(WindowGame window){
        this.windows.add(window);
    }

    public void addText(Text text){
        this.texts.add(text);
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void addInteracionListener(InteractionListener listener) {
        if (this.interactionListeners == null){
            this.interactionListeners = new ArrayList<InteractionListener>();
        }
        for (int i = 0; i < this.interactionListeners.size(); i++){

            if (this.interactionListeners.get(i).name == listener.name){
                //Log.e("game", " subtituindo listener "+listener.name);
                this.interactionListeners.set(i, listener);
                return;
            }
        }
        //Log.e("game", " adicionando listener "+listener.name);
        this.interactionListeners.add(listener);
    }

    public void blockAndWaitTouchRelease(){
        isBlocked = true;
    }

    public void setGameState(int state){
        this.gameState = state;
        clearAllMenuEntities();
        final Game innerGame = this;
        if (state == GAME_STATE_MENU){
            stopAndReleaseMusic();
            eraseAllGameEntities();
            eraseAllHudEntities();
            eraseAllTutorials();
            menuMain.isBlocked = false;
            menuMain.display();
            tittle.display();
            messageCurrentLevel.display();
            messageMaxScoreLevel.display();
            messageMaxScoreTotal.display();

            messageMaxScoreLevel.setText(
                    context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+
                            Integer.toString(Storage.getLevelMaxScore(levelNumber))
            );

            messageMaxScoreTotal.setText(
                    context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal());

        } else if (state == GAME_STATE_PREPARAR){
            eraseAllTutorials();
            levelObject.loadEntities();
            music = MediaPlayer.create(context, R.raw.musicgroove90);
            music.setVolume(0.006f* (float) volume, 0.006f* (float) volume);
            music.setLooping(true);
            // cria a animação de preparação;
            ArrayList<float[]> values = new ArrayList<>();
                values.add(new float[]{0f,3f});
                values.add(new float[]{0.25f,2f});
                values.add(new float[]{0.5f,1f});
                values.add(new float[]{0.75f,0f});
            final Text innerMessagePreparation = messagePreparation;
            messagePreparation.setText("3");
            messagePreparation.display();
            soundPool.play(soundCounter, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
            Animation anim = new Animation(messagePreparation, "messagePreparation", "numberForAnimation", 4000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 2f){
                        soundPool.play(soundCounter, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        soundPool.play(soundCounter, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {
                        innerMessagePreparation.setText("GO!");
                        Animation anim = Utils.createSimpleAnimation(innerMessagePreparation, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                innerMessagePreparation.clearDisplay();
                                innerMessagePreparation.alpha = 1f;
                                innerGame.setGameState(GAME_STATE_JOGAR);
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
                music.start();
            }

            for (int i = 0; i < bars.size(); i++) {
                if (bars.get(i).changeSize){
                    bars.get(i).initSizeVariation();
                }

            }

            resetTimeForPointsDecay();

            freeAllGameEntities();
        } else if (state == GAME_STATE_DERROTA){
            stopAndReleaseMusic();
            soundPool.play(soundGameOver, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuInGame.appearAndUnblock(300);
            messageGameOver.display();

            if (scorePanel.value > 0) {
                scorePanel.showMessage("-50%", 1000);
                int points = scorePanel.value / 2;
                scorePanel.setValue(points, true, 1000, true);
                if (Storage.getLevelMaxScore(innerGame.levelNumber) < points) {
                    Storage.setLevelMaxScore(innerGame.levelNumber, points);
                }
            }

        } else if (state == GAME_STATE_PAUSE){
            music.pause();
            Log.e("game", "ativando game_state_pause");
            soundPool.play(soundMenuSelectBig, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
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
            soundPool.play(soundWin, 0.01f* (float) volume, 0.01f* (float) volume, 0, 0, 1);
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
                    if (innerGame.objectivePanel.blueBalls > 0){
                        int points = (int)((float)innerGame.scorePanel.value * 1.5f);
                        innerGame.scorePanel.setValue(points, true, 1000, true);
                        innerGame.scorePanel.showMessage("x2", 800);
                        innerGame.objectivePanel.explodeBlueBall();

                    } else {
                        if (Storage.getLevelMaxScore(innerGame.levelNumber) < innerGame.scorePanel.value){
                            Storage.setLevelMaxScore(innerGame.levelNumber, innerGame.scorePanel.value);
                        }
                        cancel();
                    }
                };
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
                    innerGame.soundPool.play(innerGame.soundTextBoxAppear, 1, 1, 0, 0, 1);
                    innerGame.menuInGame.appearAndUnblock(800);
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

                            float initialTranslateY = - innerGame.gameAreaResolutionY * 0.1f;

                            ArrayList<float[]> valuesAnimScoreTX = new ArrayList<>();
                            valuesAnimScoreTX.add(new float[]{0f,0f});
                            valuesAnimScoreTX.add(new float[]{0.3f,-10f});
                            valuesAnimScoreTX.add(new float[]{0.7f,20f});
                            valuesAnimScoreTX.add(new float[]{1f,0f});
                            new Animation(innerGame.scorePanel, "animScoreTX", "translateX", 30000, valuesAnimScoreTX, true, true).start();

                            ArrayList<float[]> valuesAnimScoreTY = new ArrayList<>();
                            valuesAnimScoreTY.add(new float[]{0f,initialTranslateY});
                            valuesAnimScoreTY.add(new float[]{0.2f,initialTranslateY + 5f});
                            valuesAnimScoreTY.add(new float[]{0.7f,initialTranslateY -20f});
                            valuesAnimScoreTY.add(new float[]{1f,initialTranslateY});
                            new Animation(innerGame.scorePanel, "animScoreTY", "translateY", 12000, valuesAnimScoreTY, true, true).start();
                        }
                    }
            ).start();



            // TODO se for o último level
            levelNumber += 1;

        } else if (state == GAME_STATE_TUTORIAL) {
            innerGame.levelObject.loadEntities();
            verifyDead();
        }

    }

    public void stopAndReleaseMusic(){
        if (music != null) {
            music.stop();
            music.release();
            music = null;
        }
    }

    private void freeAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = true;
        }
        for (Bar b : bars){
            b.isMovable = true;
        }
    }

    private void stopAllGameEntities() {
        for (Ball b : balls){
            b.isMovable = false;
            b.clearParticles();
        }
        for (Bar b : bars){
            b.isMovable = false;
        }
    }

    private void increaseAllGameEntitiesAlpha(int duration){
        for (Entity e : collectAllGameEntities()){
            e.increaseAlpha(duration, 1f);
        }
    }

    private void reduceAllGameEntitiesAlpha(int duration){
        for (Entity e : collectAllGameEntities()){
            e.reduceAlpha(duration, 0.2f);
        }
    }

    public void eraseAllGameEntities() {
        balls.clear();
        bars.clear();
        targets.clear();
        windows.clear();
        obstacles.clear();
    }

    public void eraseAllHudEntities() {
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

    public void eraseAllTutorials() {
        if (this.levelObject != null) {
            for (int i = 0; i < levelObject.tutorials.size(); i++) {
                levelObject.tutorials.get(i).textBox = null;
                levelObject.tutorials.clear();
            }
        }
    }

    public void init(){
        Storage.initializeStorage(context, quantityOfLevels);
            levelNumber = Storage.getActualLevel();
            //Log.e("game ", "levelNumber "+levelNumber);
            maxLevel = Storage.getMaxLevel();
            //Log.e("game ", "maxLevel "+maxLevel);

        levelNumber = Storage.getActualLevel();
        initSounds();
        initPrograms();
        initFont();
        initMenus();
        initTexts();
    }

    public void initTexts(){
        tittle = new Image("tittle", this,
                gameAreaResolutionX * 0.25f, gameAreaResolutionY * 0.1f,
                gameAreaResolutionX * 0.5f, gameAreaResolutionX * 0.5f * 0.3671875f,
                TEXTURE_TITTLE, 0f, 1f, 0.6328125f, 1f, new Color(0.5f, 0.2f, 0.8f, 1f));
                
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
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.2f, gameAreaResolutionY*0.2f,
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
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.3f, gameAreaResolutionY*0.4f,
                " ", font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageInGame = new Text("messageInGame",
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.25f, gameAreaResolutionY*0.2f,
                context.getResources().getString(R.string.pause), font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);
            
        float size = resolutionY * 0.07f;
        
        messageCurrentLevel = new Text("messageCurrentLevel", 
            this, resolutionX*0.05f, resolutionY*0.78f, resolutionY*0.05f,
                context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(levelNumber), font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreLevel = new Text("messageMaxScoreLevel", 
            this, resolutionX*0.05f, resolutionY*0.84f, resolutionY*0.05f,
                context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(levelNumber)), font, new Color(0f, 0f, 0f,0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                this, resolutionX*0.05f, resolutionY*0.9f, resolutionY*0.05f,
                context.getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ getMaxScoreTotal(), font, new Color(0f, 0f, 0f, 0.5f));
    }

    public ArrayList<Entity> collectAllMenuEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(menuMain);
        list.add(selectorLevel);
        list.add(selectorVolumn);
        list.add(menuInGame);
        list.add(menuTutorial);
        list.add(tittle);
        list.add(messageGameOver);
        list.add(messagePreparation);
        list.add(messageInGame);
        list.add(messageCurrentLevel);
        list.add(messageMaxScoreLevel);
        list.add(messageMaxScoreTotal);
        return list;
    }

    public ArrayList<Entity> collectAllHudEntities(){
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

    public ArrayList<Entity> collectAllGameEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.addAll(balls);
        list.addAll(bars);
        list.addAll(targets);
        list.addAll(obstacles);
        list.addAll(windows);

        return list;
    }

    public void clearAllMenuEntities(){
        ArrayList<Entity> list = collectAllMenuEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    public void clearAllHudEntities(){
        ArrayList<Entity> list = collectAllHudEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    public void clearAllGameEntities(){
        ArrayList<Entity> list = collectAllGameEntities();
        for(Entity e : list){
            if (e != null) {
                e.clearDisplay();
            }
        }
    }

    public int getMaxScoreTotal(){
        int scoreTotal = 0;
        for (int i = 0; i < quantityOfLevels; i++){
            scoreTotal += Storage.getLevelMaxScore(i+1);
        }
        return scoreTotal;
    }


    public void initMenus(){
        
        // cria o menu principal
        menuMain = new Menu("menuMain", this, gameAreaResolutionX/2, gameAreaResolutionY/2, 40f, font);

        // adiciona a opção de iniciar o jogo
        final Game innerGame = this;
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", context.getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                innerGame.blockAndWaitTouchRelease();
                innerGame.clearAllMenuEntities();
                LevelLoader.loadLevel(innerGame, innerGame.levelNumber);
                TutorialLoader.loadTutorial(innerGame, innerGame.levelNumber);

                //Log.e("game", "tutorials size: "+ innerGame.levelObject.tutorials.size());
                if (innerGame.levelObject.tutorials.size() > 0) {

                    //Log.e("game ", "Storage.getLevelTutorialSaw(innerGame.levelNumber) "+Storage.getLevelTutorialSaw(innerGame.levelNumber));

                    if (Storage.getLevelTutorialSaw(innerGame.levelNumber) == false) {
                        //Log.e("game", "tutorial ainda não visto");
                        Storage.setLevelTutorialSaw(innerGame.levelNumber, true);
                        innerGame.levelObject.loadEntities();
                        innerGame.setGameState(GAME_STATE_TUTORIAL);
                        innerGame.levelObject.showFirstTutorial();
                    } else {
                        //Log.e("game", "tutorial já visto");
                        //Log.e("game", "game blocked "+isBlocked);
                        innerGame.menuTutorial.getMenuOptionByName("exibirTutorial").setText = context.getResources().getString(R.string.menuTutorialExibirTutorial) + innerGame.levelNumber;
                        innerGame.menuTutorial.unblock();
                        innerGame.menuTutorial.display();
                        tittle.display();
                    }
                } else {
                    //Log.e("game", "load Entities");
                    innerGame.setGameState(GAME_STATE_PREPARAR);
                }
            }
        });

        // prepara os valores para o seletor de nível
        String [] levels = new String [quantityOfLevels-1];
        for (int i = 0; i < quantityOfLevels-1; i++){
            levels[i] = Integer.toString(i+1);
        }

        // cria o seletor de nível
        selectorLevel = new Selector("selectorLevel", this, 0f,0f, 40f, "", levels, font);
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
                innerGame.changeLevel(innerSelectorLevel.selectedValue+1);
            }
        });

        // prepara os valores para o seletor de volume
        String [] volumns = new String [11];
        for (int i = 0; i < 11; i++){
            volumns[i] = Integer.toString(possibleVolums[i]) + "%";
        }

        // cria o seletor de volume
        selectorVolumn = new Selector("selectorVolumn", this, 0f,0f, 40f, "", volumns, font);
        final Selector innerSelectorVolumn = selectorVolumn;
        selectorVolumn.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                innerGame.volume = innerGame.possibleVolums[innerSelectorVolumn.selectedValue];
                Storage.setVolume(innerGame.volume);
            }
        });

        // adiciona a opção de alterar volume
        menuMain.addMenuOption("AlterarVolume", context.getResources().getString(R.string.menuPrincipalAlterarVolume), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerSelectorVolumn.fromMenu(innerMenu);
                int volumeToSelector = volume / 10;
                innerSelectorVolumn.setSelectedValue(volumeToSelector);
            }
        });

        // ajusta a posição do seletor de nível
        MenuOption menuOptionSelectVolumn = menuMain.getMenuOptionByName("AlterarVolume");
        selectorVolumn.setPosition(menuOptionSelectLevel.x + (menuOptionSelectVolumn.width), menuOptionSelectVolumn.y);
        
        // cria o menu in game
        menuInGame = new Menu("menuInGame", this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, 40f, font);

        // adiciona a opção continuar
        final Menu innerMenuInGame = menuInGame;
        menuInGame.addMenuOption("Continuar", context.getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenuInGame.block();
                innerGame.blockAndWaitTouchRelease();
                if (innerGame.gameState == GAME_STATE_DERROTA){
                    LevelLoader.loadLevel(innerGame, innerGame.levelNumber);
                    innerGame.menuInGame.clearDisplay();
                    innerGame.setGameState(GAME_STATE_PREPARAR);
                } else if (innerGame.gameState == GAME_STATE_VITORIA){
                    innerGame.menuMain.getMenuOptionByName("IniciarJogo").fireOnChoice();
                } else if (innerGame.gameState == GAME_STATE_PAUSE){
                    Log.e("game", "menu continuar quando game state = GAME_STATE_PAUSE");
                    innerGame.increaseAllGameEntitiesAlpha(500);
                    innerGame.messageInGame.reduceAlpha(500,0f);
                    innerGame.menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Log.e("game", "ativando callback GAME_STATE_JOGAR");
                            innerGame.setGameState(GAME_STATE_JOGAR);

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
                innerGame.blockAndWaitTouchRelease();
                innerGame.setGameState(GAME_STATE_MENU);
            }
        });

        // cria o menu tutorial
        menuTutorial = new Menu("menuTutorial", this, gameAreaResolutionX/2, gameAreaResolutionY/2, 40f, font);

        // adiciona a opção exibir tutorial
        menuTutorial.addMenuOption("exibirTutorial", context.getResources().getString(R.string.menuTutorialExibirTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
               innerGame.blockAndWaitTouchRelease();
               innerGame.setGameState(GAME_STATE_TUTORIAL);
               innerGame.levelObject.showingTutorial = 0;
               innerGame.levelObject.tutorials.get(0).show(innerGame.soundPool, innerGame.soundTextBoxAppear, 0.01f* (float) volume);
               innerGame.menuTutorial.block();
               innerGame.menuTutorial.clearDisplay();
            }
        });
        
        // adiciona a opção pular tutorial
        menuTutorial.addMenuOption("PularTutorial", context.getResources().getString(R.string.menuTutorialPularTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
               innerGame.blockAndWaitTouchRelease();
               innerGame.setGameState(GAME_STATE_PREPARAR);
               innerGame.menuTutorial.block();
               innerGame.menuTutorial.clearDisplay();
            }
        });
        
        // adiciona a opção de voltar ao menu principal
        menuTutorial.addMenuOption("Retornar", context.getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerGame.blockAndWaitTouchRelease();
                innerGame.setGameState(GAME_STATE_MENU);
                
            }
        });
    }

    private void changeLevel(int level) {
        this.levelNumber = level;
        
        // alterar texto que mostra o level
        messageCurrentLevel.setText(
            context.getResources().getString(R.string.messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(levelNumber)
        );

        // alterar texto que mostra a pontuação
        messageMaxScoreLevel.setText(
            context.getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ Integer.toString(Storage.getLevelMaxScore(levelNumber))
        );

        Storage.setActualLevel(level);
    }

    public void initPrograms(){
        imageProgram = new Program(GraphicTools.vs_Image, GraphicTools.fs_Image);
        imageColorizedProgram = new Program(GraphicTools.vs_Image_Colorized, GraphicTools.fs_Image_Colorized);
        imageColorizedFxProgram = new Program(GraphicTools.vs_Image_Colorized_fx, GraphicTools.fs_Image_Colorized_fx);
        textProgram = new Program(GraphicTools.vs_Text, GraphicTools.fs_Text);
        solidProgram = new Program(GraphicTools.vs_SolidColor, GraphicTools.fs_SolidColor);
        lineProgram = new Program(GraphicTools.vs_Line, GraphicTools.fs_Line);

    }

    public void initFont(){
        font = new Font(1,textProgram);
    }

    public void initSounds(){

        volume = Storage.getVolume();

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(8).build();
        soundBallHit = soundPool.load(context, R.raw.ballhit, 1);
        soundCounter = soundPool.load(context, R.raw.counter, 1);
        soundDestroyTarget = soundPool.load(context, R.raw.destroytarget, 1);
        soundAlarm = soundPool.load(context, R.raw.alarm, 1);
        soundBallFall = soundPool.load(context, R.raw.ballfall, 1);
        soundBlueBallExplosion1 = soundPool.load(context, R.raw.blueballexplosion1, 1);
        soundBlueBallExplosion2 = soundPool.load(context, R.raw.blueballexplosion2, 1);
        soundExplosion1 = soundPool.load(context, R.raw.explosion1, 1);
        soundExplosion2 = soundPool.load(context, R.raw.explosion21, 1);
        soundGameOver = soundPool.load(context, R.raw.gameover2, 1);
        soundMenuSelectBig = soundPool.load(context, R.raw.menuselectbig, 1);
        soundMenuSelectSmall = soundPool.load(context, R.raw.menuselectsmall, 1);
        soundScore = soundPool.load(context, R.raw.score, 1);
        soundWin = soundPool.load(context, R.raw.win, 1);
        soundTextBoxAppear = soundPool.load(context, R.raw.textboxappear, 1);
    }

    public void simulate(long elapsed, float frameDuration){
        
        ballCollidedFx -= 1;
        
        // atualiza posição da bola
        if (this.gameState == GAME_STATE_JOGAR) {
             for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                ball.verifyAcceleration();
                ball.vx = (ball.dvx * (float) elapsed) / frameDuration;
                ball.vy = (ball.dvy * (float) elapsed) / frameDuration;
                ball.translate(ball.vx, ball.vy);
                ball.clearCollisionData();
            }
        }

        // atualiza posição da barra
        if (this.gameState == GAME_STATE_JOGAR || this.gameState == GAME_STATE_TUTORIAL) {
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

                    if (bars.size() == 2) {
                        if (button2Left.isPressed) {
                            bars.get(1).vx = -(bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else if (button2Right.isPressed) {
                            bars.get(1).vx = (bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else {
                            bars.get(1).vx = 0f;
                        }

                        bars.get(1).translate(bars.get(1).vx, 0);
                    }
                }
            }

            // atualiza posição e tamanho dos obstáculos
        }

        // atualiza os dados da entidade, aplicando todas as transformações setadas
        checkTransformations();

        if (this.gameState == GAME_STATE_JOGAR || this.gameState == GAME_STATE_TUTORIAL) {
            // atualiza posição das windows
            for (int i = 0; i < windows.size(); i++){
                if (windows.get(i).isMovable){
                    windows.get(i).vx = (windows.get(i).dvx * (float) elapsed) / frameDuration;
                    windows.get(i).move();
                }
            }

            // insere as entidades no quadtree
            for (int i = 0; i < balls.size(); i++) {
                quad.insert(balls.get(i));
            }
            
            for (int i = 0; i < bars.size(); i++) {
                quad.insert(bars.get(i));
            }
            
            for (int i = 0; i < targets.size(); i++) {
                quad.insert(targets.get(i));
            }

            for (int i = 0; i < obstacles.size(); i++) {
                quad.insert(obstacles.get(i));
            }
            
            quad.insert(bordaE);
            quad.insert(bordaD);
            quad.insert(bordaC);
            quad.insert(bordaB);
        }

        // verifica a colisão da bola
        if (this.gameState == GAME_STATE_JOGAR) {
            for (int i = 0; i < balls.size(); i++) {
                if (balls.get(i).listenForExplosion) {
                    if ((int) (Utils.getTime() - balls.get(i).initialTimeWaitingExplosion) > balls.get(i).timeForExplode
                            && balls.get(i).y < gameAreaResolutionY * 0.8f) {

                        balls.get(i).radius *= 5;
                        ArrayList<Entity> ball = new ArrayList<>();
                        ball.add(balls.get(i));
                        boolean collision = checkCollision(ball, false, false);
                        balls.get(i).radius /= 5;
                        if (!collision){
                            balls.get(i).explode();
                        }
                    }
                }
            }
            checkCollision(balls, true, true);
        }
        
        // verifica a colisão da barra
        if (this.gameState == GAME_STATE_JOGAR || this.gameState == GAME_STATE_TUTORIAL) {
            checkCollision(bars, true, true);
            quad.clear();
        }
        
        // limpa o quadtree, preparando o próximo frame
        if (this.gameState == GAME_STATE_JOGAR || this.gameState == GAME_STATE_TUTORIAL) {
            quad.clear();
        }

        // se a bola colidiu, faz o necessário
        if (this.gameState == GAME_STATE_JOGAR) {
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
        if (this.gameState == GAME_STATE_JOGAR || this.gameState == GAME_STATE_TUTORIAL) {
            verifyWin();
        }
    }

    public void verifyPointsDecay(){
        long time = Utils.getTime();
        if ((time - initialTimePointsDecay)>TIME_FOR_POINTS_DECAY){
            if (scorePanel.value > POINTS_DECAY) {
                initialTimePointsDecay = time;
                int value = scorePanel.value - POINTS_DECAY;
                scorePanel.setValue(value, false, 0, false);
            }
        }
    }

    public void resetTimeForPointsDecay(){
        initialTimePointsDecay = Utils.getTime();
    }

    public void verifyWin() {
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

    public void verifyDead() {
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
    
    public void checkTransformations(){

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

        if (menuMain != null) menuMain.checkTransformations(true);
        if (menuInGame != null) menuInGame.checkTransformations(true);
        if (menuTutorial != null) menuTutorial.checkTransformations(true);
        if (selectorLevel != null) selectorLevel.checkTransformations(true);
        if (selectorVolumn != null) selectorVolumn.checkTransformations(true);
        if (tittle != null) tittle.checkTransformations(true);

        if (this.gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  this.levelObject.showingTutorial){
                levelObject.tutorials.get(this.levelObject.showingTutorial).textBox.checkTransformations(true);
            }
        }

        messageGameOver.checkTransformations(true);
        messagePreparation.checkTransformations(true);
        messageInGame.checkTransformations(true);
        messageCurrentLevel.checkTransformations(true);
        messageMaxScoreLevel.checkTransformations(true);
        messageMaxScoreTotal.checkTransformations(true);

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

    public void render(float[] matrixView, float[] matrixProjection){
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
        
        if (menuMain != null) menuMain.prepareRender(matrixView, matrixProjection);
        if (menuInGame != null) menuInGame.prepareRender(matrixView, matrixProjection);
        if (menuTutorial != null) menuTutorial.prepareRender(matrixView, matrixProjection);
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);
        if (selectorVolumn != null) selectorVolumn.prepareRender(matrixView, matrixProjection);
        if (tittle != null) tittle.prepareRender(matrixView, matrixProjection);

        if (this.gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  this.levelObject.showingTutorial){
                levelObject.tutorials.get(this.levelObject.showingTutorial).textBox.prepareRender(matrixView, matrixProjection);
            }
        }

        messageGameOver.prepareRender(matrixView, matrixProjection);
        messagePreparation.prepareRender(matrixView, matrixProjection);
        messageInGame.prepareRender(matrixView, matrixProjection);
        messageCurrentLevel.prepareRender(matrixView, matrixProjection);
        messageMaxScoreLevel.prepareRender(matrixView, matrixProjection);
        messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);

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

    public boolean checkCollision(ArrayList<? extends Entity> aEntities, boolean respondToCollision, boolean updateLastCollisionResponse){

        boolean collided = false;
        boolean isHadCollision = false;
        ArrayList<Entity> out;
        SatResponse response = new SatResponse();

        for (int iLoop = 0; iLoop < 2; iLoop++){

            // entidades a
            for (int aCount = 0; aCount < aEntities.size(); aCount++){
                PhysicalObject a =  (PhysicalObject) aEntities.get(aCount);
                if (a.isMovable) {
                    out = null;
                    collided = false;
                    out = this.quad.retrieve(a);

                    // roda pelas entidades extraidas e verifica a colisão
                    for (int bCount = 0; bCount < out.size(); bCount++){
                        PhysicalObject b = (PhysicalObject)out.get(bCount);

                        // verifica se a entidade não é a mesma e se elas são colidíveis
                        if ((a.isSolid && b.isSolid) && (a.isMovable || b.isMovable) && (b != a)){

                            // seta os dados da entidade 'a' e da entidade 'b'
                            a.setSatData();
                            b.setSatData();

                            // zera os dados a serem usados nesta passagem
                            // transfere os dados da entidade para o objeto do game

                            //type false: polygon true: circle
                            boolean aType = false;
                            boolean bType = false;

                            if (a.circleData != null){
                                this.circle1.pos.x = a.circleData.pos.x;
                                this.circle1.pos.y = a.circleData.pos.y;
                                this.circle1.r = a.circleData.r;
                                aType = true;
                            } else {
                                this.polygon1.pos.x = a.polygonData.pos.x;
                                this.polygon1.pos.y = a.polygonData.pos.y;
                                this.polygon1.setPoints(a.polygonData.points);
                            }

                            if (b.circleData != null){
                                this.circle2.pos.x = b.circleData.pos.x;
                                this.circle2.pos.y = b.circleData.pos.y;
                                this.circle2.r = b.circleData.r;
                                bType = true;
                            } else {
                                this.polygon2.pos.x = b.polygonData.pos.x;
                                this.polygon2.pos.y = b.polygonData.pos.y;
                                this.polygon2.setPoints(b.polygonData.points);
                            }

                            float [] velocities = new float[4];

                            //Log.e("pos bola sat cc2", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                            velocities[0] = Math.abs(a.vx);
                            velocities[1] = Math.abs(a.vy);
                            velocities[2] = Math.abs(b.vx);
                            velocities[3] = Math.abs(b.vy);

                            float max = -100000;
                            int maxIndex = -1;
                            for (int n = 0; n < 4; n++){
                                if (velocities[n] > max){
                                    maxIndex = n;
                                    max = velocities[n];
                                }
                            }

                            int quantityPassagens;

                            // defina quantas passagens serão realidades, com base na maior velocidade
                            quantityPassagens = Math.round(velocities[maxIndex]/2) ;
                            if (quantityPassagens == 0){
                                quantityPassagens = 1;
                            }

                            //Log.e("Game", " a.previousX "+a.previousX);
                            //Log.e("Game", " a.previousY "+a.previousY);
                            //Log.e("Game", " b.previousX "+b.previousX);
                            //Log.e("Game", " b.previousY "+b.previousY);

                            float aPreviousX = a.previousX;
                            float aPreviousY = a.previousY;
                            float bPreviousX = b.previousX;
                            float bPreviousY = b.previousY;

                            //if (b.name == "obstacle"){
                            //    Log.e("game", " x"+ this.polygon2.pos.x);
                            //}

                            //Log.e("Game", " a.previousX 2"+aPreviousX);
                            //Log.e("Game", " a.previousY 2"+aPreviousY);
                            //Log.e("Game", " b.previousX 2"+bPreviousX);
                            //Log.e("Game", " b.previousY 2"+bPreviousY);

                            // Log.e("pos bola sat cc3", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);
                            
                            // calcula a diferença entre as posições
                            float aDiferencaPosicaoX = a.positionX - aPreviousX;
                            float aDiferencaPosicaoY = a.positionY - aPreviousY;

                            float bDiferencaPosicaoX = b.positionX - bPreviousX;
                            float bDiferencaPosicaoY = b.positionY - bPreviousY;

                            // calcula a porcentagem de cada passada;
                            float porcentagem = (100f/quantityPassagens)/100f;
                            //Log.e("Game", " quantityPassagens "+quantityPassagens+" porcentagem "+porcentagem);
                            float porcentagemAplicadaNaPassagem;
                            float aPosAConsiderarX = -1000f;
                            float aPosAConsiderarY = -1000f;
                            float bPosAConsiderarX = -1000f;
                            float bPosAConsiderarY = -1000f;

                            //if (b.name == "obstacle"){
                            //    Log.e("game", " x"+ this.polygon2.pos.x);
                            //}

                            //Log.e("pos bola sat cc4", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                            // itera pelas passagens, chegando se há colisão

                            for (int ip = 0; ip < quantityPassagens; ip++){

                                response.clear();
                                porcentagemAplicadaNaPassagem = porcentagem * (float)(ip+1);
                                //Log.e("Game", "passagem "+ip+" porcentagem "+porcentagem);

                                aPosAConsiderarX = aPreviousX + (aDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                aPosAConsiderarY = aPreviousY + (aDiferencaPosicaoY * porcentagemAplicadaNaPassagem);
                                //Log.e("Game", "passagem "+ip+" a.previousY "+ aPreviousY +" aDiferencaPosicaoX "+aDiferencaPosicaoX +  " porcentagemAplicadaNaPassagem "+porcentagemAplicadaNaPassagem);
                                //Log.e("Game", "passagem "+ip+" aPosAConsiderarX "+ aPosAConsiderarX +" aPosAConsiderarY "+aPosAConsiderarY);
                                //Log.e("Game", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);

                                bPosAConsiderarX = bPreviousX + (bDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                bPosAConsiderarY = bPreviousY + (bDiferencaPosicaoY * porcentagemAplicadaNaPassagem);

                                //Log.e("test posicoes", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);


                                if (aType == false){
                                    this.polygon1.pos.x = aPosAConsiderarX;
                                    this.polygon1.pos.y = aPosAConsiderarY;
                                } else {
                                    this.circle1.pos.x = aPosAConsiderarX;
                                    this.circle1.pos.y = aPosAConsiderarY;
                                }

                                if (bType == false){
                                    this.polygon2.pos.x = bPosAConsiderarX;
                                    this.polygon2.pos.y = bPosAConsiderarY;
                                } else {
                                    this.circle2.pos.x = bPosAConsiderarX;
                                    this.circle2.pos.y = bPosAConsiderarY;
                                }


                                //Log.e("pos bola sat cc5", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                                if (aType == false){
                                    if (bType == false) {
                                        collided = Sat.getInstance().testPolygonPolygon(polygon1, polygon2, response);
                                    } else {
                                        collided = Sat.getInstance().testPolygonCircle(polygon1, circle2, response);
                                    }
                                } else {
                                    if (bType == false) {
                                        collided = Sat.getInstance().testCirclePolygon(circle1, polygon2, response);
                                    } else {
                                        collided = Sat.getInstance().testCircleCircle(circle1, circle2, response);
                                    }

                                }

                                // se houver registro de colisão, mas se a resposta foi zerada, retorna para uma próxima verificação
                                if (collided) {
                                    if (response.overlapV.x == 0 &&  response.overlapV.y == 0){
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                            }

                            //if (b.name == "obstacle"){
                            //    Log.e("game", " x"+ this.polygon2.pos.x);
                            //}

                            if (collided && !(response.overlapV.x == 0 && response.overlapV.y == 0)){

                                isHadCollision = true;

                                if (respondToCollision){
                                    a.isCollided = true;
                                    b.isCollided = true;
                                    a.respondToCollision(b, -response.overlapV.x, -response.overlapV.y, aPosAConsiderarX, aPosAConsiderarY, bPosAConsiderarX, bPosAConsiderarY);
                                }

                                if (updateLastCollisionResponse){
                                    a.lastCollisionResponse.add(new Vector(-response.overlapV.x,-response.overlapV.y));

                                    boolean exists = false;
                                    for (int i = 0; i < a.lastCollisionObjects.size(); i++){
                                        if (a.lastCollisionObjects.get(i) == b){
                                            exists = true;
                                            break;
                                        }
                                    }

                                    if (!exists){
                                        //console.log("inserindo entidade ", b.name, " na entidade ", a.name);
                                        a.lastCollisionObjects.add(b);
                                    }

                                    b.lastCollisionResponse.add(new Vector(response.overlapV.x,response.overlapV.y));

                                    exists = false;
                                    for (int i = 0; i < b.lastCollisionObjects.size(); i++){
                                        if (b.lastCollisionObjects.get(i) == a){
                                            exists = true;
                                            break;
                                        }
                                    }

                                    if (!exists){
                                        b.lastCollisionObjects.add(a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return isHadCollision;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents.size() == 0){
                isBlocked = false;
            }
       }
    }

    public void verifyListeners() {
        if (!isBlocked) {
            for (int i = 0; i < interactionListeners.size(); i++) {
                interactionListeners.get(i).verify();
            }
        }

        if (menuMain != null) menuMain.verifyListener();
        if (menuInGame != null) menuInGame.verifyListener();
        if (menuTutorial != null) menuTutorial.verifyListener();
        if (selectorLevel != null) selectorLevel.verifyListener();
        if (selectorVolumn != null) selectorVolumn.verifyListener();

        if (this.gameState == GAME_STATE_TUTORIAL){
            if (levelObject.tutorials.size() >  this.levelObject.showingTutorial){
                levelObject.tutorials.get(this.levelObject.showingTutorial).textBox.verifyListener();
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
