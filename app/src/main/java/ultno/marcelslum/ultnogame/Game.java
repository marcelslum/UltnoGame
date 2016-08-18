package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Game {
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
    public static ArrayList<Menu> menus;
    public static ArrayList<Selector> selectors;
    public static ArrayList<InteractionListener> interactionListeners;
    public static ArrayList<TextBox> textBoxes;
    
    
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
    public ButtonOnOff soundButton;
    public ButtonOnOff musicButton;

    public Image tittle;

    public Text messageGameOver;
    public Text messagePreparation;
    public Text messageInGame;
    public Text messageCurrentLevel;
    //public Text currentLevel;
    public Text messageMaxScoreLevel;
    //public Text maxScoreLevel;
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
    int soundMusic;
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
    MediaPlayer music;

    // scree properties
    public float gameAreaResolutionX;
    public float gameAreaResolutionY;
    public float resolutionX;
    public float resolutionY;

    // options
    boolean soundOn;
    boolean musicOn;
    boolean isBlocked;
    public int volumn;
    public final static int [] possibleVolums = new int []{0,10,20,30,40,50,60,70,80,90,100};

    //  level
    Level levelObject;
    public final static int quantityOfLevels = 20;
    int levelNumber = 1;
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
    public final static int TEXTURE_NUMBERS = 5;
    public final static int TEXTURE_TITTLE = 6;
    
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
    public Program textProgram;
    public Program solidProgram;

    public ArrayList<Line> lines;

    int ballsNotInvencibleAlive;
    int ballsInvencible;

    public static Game getInstance() {
        return ourInstance;
    }

    private Game() {
        // initialize data
        targets = new ArrayList<Target>();
        balls = new ArrayList<Ball>();
        touchEvents = new ArrayList<TouchEvent>();
        texts = new ArrayList<Text>();
        interactionListeners = new ArrayList<>();
        bars = new ArrayList<Bar>();
        menus = new ArrayList<>();
        selectors = new ArrayList<>();
        textBoxes = new ArrayList<>();
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

    public void addTarget(Target target){
        this.targets.add(target);
    }

    public void addBall(Ball ball){
        this.balls.add(ball);
    }

    public void addText(Text text){
        this.texts.add(text);
    }

    public void addBar(Bar bar){
        this.bars.add(bar);
    }

    public void addInteracionListener(InteractionListener listener) {
        if (this.interactionListeners == null){
            this.interactionListeners = new ArrayList<InteractionListener>();
        }
        for (int i = 0; i < this.interactionListeners.size(); i++){
            if (this.interactionListeners.get(i).name == listener.name){
                this.interactionListeners.set(i, listener);
                return;
            }
        }
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
            menuMain.isBlocked = false;
            menuMain.display();
            tittle.display();
            messageCurrentLevel.display();
            //currentLevel.display();
            messageMaxScoreLevel.display();
            //maxScoreLevel.display();
            messageMaxScoreTotal.display();
        } else if (state == GAME_STATE_PREPARAR){
            music = MediaPlayer.create(context, R.raw.music);
            // cria a animação de preparação;
            ArrayList<float[]> values = new ArrayList<>();
                values.add(new float[]{0f,3f});
                values.add(new float[]{0.25f,2f});
                values.add(new float[]{0.5f,1f});
                values.add(new float[]{0.75f,0f});
            final Text innerMessagePreparation = messagePreparation;
            messagePreparation.setText("3");
            messagePreparation.display();
            soundPool.play(soundCounter, 1, 1, 0, 0, 1);
            Animation anim = new Animation(messagePreparation, "messagePreparation", "numberForAnimation", 4000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 2f){
                        soundPool.play(soundCounter, 1, 1, 0, 0, 1);
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        soundPool.play(soundCounter, 1, 1, 0, 0, 1);
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

        } else if (state == GAME_STATE_JOGAR){

            music.start();

            freeAllGameEntities();
            //soundPool.play(soundMusic, 1, 1, 0, 0, 1);
        } else if (state == GAME_STATE_DERROTA){
            stopAndReleaseMusic();
            soundPool.play(soundGameOver, 1, 1, 0, 0, 1);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuInGame.appearAndUnblock(300);
            messageGameOver.display();
        } else if (state == GAME_STATE_PAUSE){
            music.pause();
            Log.e("game", "ativando game_state_pause");
            //soundPool.stop(soundMusic);
            soundPool.play(soundMenuSelectBig, 1, 1, 0, 0, 1);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            menuInGame.appearAndUnblock(300);
            messageInGame.setText(context.getResources().getString(R.string.pause));
            messageInGame.increaseAlpha(100, 1f);
            messageInGame.display();
        } else if (state == GAME_STATE_VITORIA){
            stopAndReleaseMusic();
            soundPool.play(soundWin, 1, 1, 0, 0, 1);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
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
            e.reduceAlpha(duration, 0.4f);
        }
    }


    public void eraseAllGameEntities() {
        balls.clear();
        bars.clear();
        targets.clear();
        //windows.clear();
        //obstacles.clear();
        //particles.clear();
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
        background = null;
    }

    public void init(){
        Storage.initializeStorage(context, quantityOfLevels);
            levelNumber = Storage.getActualLevel();
            maxLevel = Storage.getMaxLevel();
        initSounds();
        initPrograms();
        initFont();
        createMenus();
        createTexts();
    }

    public void createTexts(){
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


        /*
        currentLevel = new Text("currentLevel",
                this, resolutionX*0.85f, resolutionY*0.9f, resolutionY*0.5f,
                , font, new Color(0f, 0f, 0f, 1f));

        maxScoreLevel = new Text("maxScoreLevel",
            this, resolutionX*0.85f, resolutionY*0.9f, resolutionY*0.5f,
            ), font, new Color(0f, 0f, 0f, 1f));

        */
        
        //TextBox tb = new TextBox("textBox", this, 50f, 50f, 600f, 40f, "Atinja o alvo com a bola para destruir o alvo que desaparecerá após ser atingido!!!");
        //textBoxes.add(tb);
        //Utils.createSimpleAnimation(tb, "translateX", "translateX", 1000, -800f, 0f).start();
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
        //list.add(currentLevel);
        list.add(messageMaxScoreLevel);
        //list.add(maxScoreLevel);
        list.add(messageMaxScoreTotal);
        return list;
    }

    public ArrayList<Entity> collectAllHudEntities(){
        ArrayList<Entity> list = new ArrayList<>();
        list.add(button1Left);
        list.add(button1Right);
        list.add(button2Left);
        list.add(button2Right);
        list.add(soundButton);
        list.add(musicButton);
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
        // TODO list.addAll(obstacles);
        // TODO list.addAll(windows);
        // TODO list.addAll(particles);
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


    public void createMenus(){
        
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

                if (innerGame.levelObject.tutorials != null) {
                    if (innerGame.levelObject.tutorials.size() > 0) {
                        if (Storage.getLevelTutorialSaw(innerGame.levelNumber)) {
                            Storage.setLevelTutorialSaw(innerGame.levelNumber, true);
                            innerGame.setGameState(GAME_STATE_TUTORIAL);
                            innerGame.levelObject.showFirstTutorial();
                        } else {
                            innerGame.menuTutorial.getMenuOptionByName("exibirTutorial").setText = context.getResources().getString(R.string.menuTutorialExibirTutorial) + innerGame.levelNumber;
                            innerGame.menuTutorial.unblock();
                        }
                    } else {
                        innerGame.levelObject.loadEntities();
                        innerGame.setGameState(GAME_STATE_PREPARAR);
                    }
                } else {
                    innerGame.levelObject.loadEntities();
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
                innerGame.volumn = innerGame.possibleVolums[innerSelectorVolumn.selectedValue];
            }
        });

        // adiciona a opção de alterar volume
        menuMain.addMenuOption("AlterarVolume", context.getResources().getString(R.string.menuPrincipalAlterarVolume), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerSelectorVolumn.fromMenu(innerMenu);
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
                    innerGame.levelObject.loadEntities();
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
        menuTutorial.addMenuOption("ExibirTutorial", context.getResources().getString(R.string.menuTutorialExibirTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
               innerGame.blockAndWaitTouchRelease();
               innerGame.levelObject.loadEntities();
               innerGame.setGameState(GAME_STATE_TUTORIAL);
               innerGame.levelObject.showingTutorial = 0;
               innerGame.levelObject.tutorials.get(0).show();
               innerGame.menuTutorial.block();
               innerGame.menuTutorial.clearDisplay();
            }
        });
        
        // adiciona a opção pular tutorial
        menuTutorial.addMenuOption("PularTutorial", context.getResources().getString(R.string.menuTutorialPularTutorial), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
               innerGame.blockAndWaitTouchRelease();
               innerGame.levelObject.loadEntities();
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
        // TODO alterar texto que mostra o level
        // TODO alterar texto que mostra a pontuação
    }

    public void initPrograms(){
        imageProgram = new Program(GraphicTools.vs_Image, GraphicTools.fs_Image);
        imageColorizedProgram = new Program(GraphicTools.vs_Image_Colorized, GraphicTools.fs_Image_Colorized);
        textProgram = new Program(GraphicTools.vs_Text, GraphicTools.fs_Text);
        solidProgram = new Program(GraphicTools.vs_SolidColor, GraphicTools.fs_SolidColor);
    }

    public void initFont(){
        font = new Font(1,textProgram);
    }

    public void initSounds(){

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(5).build();
        soundBallHit = soundPool.load(context, R.raw.ballhit, 1);
        soundCounter = soundPool.load(context, R.raw.counter, 1);
        soundDestroyTarget = soundPool.load(context, R.raw.destroytarget, 1);
        soundAlarm = soundPool.load(context, R.raw.alarm, 1);
        soundBallFall = soundPool.load(context, R.raw.ballfall, 1);
        soundBlueBallExplosion1 = soundPool.load(context, R.raw.blueballexplosion1, 1);
        soundBlueBallExplosion2 = soundPool.load(context, R.raw.blueballexplosion2, 1);
        soundExplosion1 = soundPool.load(context, R.raw.explosion1, 1);
        soundExplosion2 = soundPool.load(context, R.raw.explosion2, 1);
        soundGameOver = soundPool.load(context, R.raw.gameover, 1);
        soundMenuSelectBig = soundPool.load(context, R.raw.menuselectbig, 1);
        soundMenuSelectSmall = soundPool.load(context, R.raw.menuselectsmall, 1);
        soundMusic = soundPool.load(context, R.raw.music, 1);
        soundScore = soundPool.load(context, R.raw.score, 1);
        soundWin = soundPool.load(context, R.raw.win, 1);

    }
    
    

    
    public void simulate(long elapsed, float frameDuration){
        if (this.gameState == GAME_STATE_JOGAR) {
            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                ball.clearCollisionData();
                quad.insert(ball);
            }

            if (bars != null) {
                if (bars.size() > 0) {
                    if (button1Left.isPressed) {
                        bars.get(0).vx = -(bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else if (button1Right.isPressed) {
                        bars.get(0).vx = (bars.get(0).dvx * (float) elapsed) / frameDuration;
                    } else {
                        bars.get(0).vx = 0f;
                    }
                    if (bars.size() == 2) {
                        if (button2Left.isPressed) {
                            bars.get(1).vx = -(bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else if (button2Right.isPressed) {
                            bars.get(1).vx = (bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else {
                            bars.get(1).vx = 0f;
                        }
                    }
                }
            }

            quad.insert(bordaE);
            quad.insert(bordaD);
            quad.insert(bordaC);
            quad.insert(bordaB);

            for (int i = 0; i < targets.size(); i++) {
                quad.insert(targets.get(i));
            }

            for (int i = 0; i < bars.size(); i++) {
                quad.insert(bars.get(i));
            }

            //Log.e("gl renderer", "onDrawFrame4");

            boolean isHaveCollision;
            isHaveCollision = checkCollision(balls, true, true);
            isHaveCollision = checkCollision(bars, true, true);

            quad.clear();

            //Log.e("gl renderer", "onDrawFrame5");

            for (int i = 0; i < balls.size(); i++) {

                if (balls.get(i).isCollided) {
                    balls.get(i).onCollision();
                }
            }

            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                ball.verifyAcceleration();
                ball.vx = (ball.dvx * (float) elapsed) / frameDuration;
                ball.vy = (ball.dvy * (float) elapsed) / frameDuration;
                ball.translate(ball.vx, ball.vy, true);
            }

            for (Bar b : bars){
                b.translate(b.vx, 0, true);
            }





            if (gameState == GAME_STATE_JOGAR) {
                background.move(1);
                verifyDead();
            }
        }
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
        //Log.e("game", " minBallsNotInvencibleAlive " + levelObject.minBallsNotInvencibleAlive);
        //Log.e("game", " ballsNotInvencibleAlive " + ballsNotInvencibleAlive);
        if (levelObject.minBallsNotInvencibleAlive > ballsNotInvencibleAlive)
            setGameState(GAME_STATE_DERROTA);
    }

    public void render(float[] matrixView, float[] matrixProjection){
        if (background != null) {
            background.prepareRender(matrixView, matrixProjection);
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

        if (bordaE != null)bordaE.prepareRender(matrixView, matrixProjection);
        if (bordaD != null)bordaD.prepareRender(matrixView, matrixProjection);
        if (bordaC != null)bordaC.prepareRender(matrixView, matrixProjection);
        if (bordaB != null)bordaB.prepareRender(matrixView, matrixProjection);

        if (button1Left != null) button1Left.prepareRender(matrixView, matrixProjection);
        if (button1Right != null) button1Right.prepareRender(matrixView, matrixProjection);
        if (button2Left != null) button1Left.prepareRender(matrixView, matrixProjection);
        if (button2Right != null) button1Right.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < targets.size(); i++){
            if (targets.get(i).showPointsState == Entity.SHOW_POINTS_ON){
                targets.get(i).renderPoints(matrixView, matrixProjection);
            }
        }

        if (scorePanel != null) scorePanel.prepareRender(matrixView, matrixProjection);
        if (objectivePanel != null) objectivePanel.prepareRender(matrixView, matrixProjection);

        if (menuMain != null) menuMain.prepareRender(matrixView, matrixProjection);
        if (menuInGame != null) menuInGame.prepareRender(matrixView, matrixProjection);
        if (menuTutorial != null) menuTutorial.prepareRender(matrixView, matrixProjection);
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);
        if (selectorVolumn != null) selectorVolumn.prepareRender(matrixView, matrixProjection);
        if (tittle != null) tittle.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < textBoxes.size(); i++){
            textBoxes.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < lines.size(); i++){
            lines.get(i).prepareRender(matrixView, matrixProjection);
        }

        messageGameOver.prepareRender(matrixView, matrixProjection);
        messagePreparation.prepareRender(matrixView, matrixProjection);
        messageInGame.prepareRender(matrixView, matrixProjection);
        messageCurrentLevel.prepareRender(matrixView, matrixProjection);
        //currentLevel.prepareRender(matrixView, matrixProjection);
        messageMaxScoreLevel.prepareRender(matrixView, matrixProjection);
        //maxScoreLevel.prepareRender(matrixView, matrixProjection);
        messageMaxScoreTotal.prepareRender(matrixView, matrixProjection);
    }

    public boolean checkCollision(ArrayList<? extends Entity> aEntities, boolean respondToCollision, boolean updateLastCollisionResponse){

        boolean collided = false;
        boolean isHadCollision = false;
        ArrayList<Entity> out;
        SatResponse response = new SatResponse();

        for (int iLoop = 0; iLoop < 1; iLoop++){

            // entidades a
            for (int aCount = 0; aCount < aEntities.size(); aCount++){
                PhysicalObject a =  (PhysicalObject) aEntities.get(aCount);

                //Log.e("game ", "começando a verficiar  "+a.isMovable);


                if (a.isMovable) {
                    out = null;
                    collided = false;

                    out = this.quad.retrieve(a);

                    //Log.e("game ", "quad size "+out.size());


                    //String texto = " ";
                    //for (int i = 0; i < out.size(); i++){
                    //    texto = texto + out.get(i).name + " - ";
                    //}




                    //if (b.name == "bordaB"){
                    //    Log.e("posicao bola x Borda B", " "+ a.y + " - "+ b.y);
                    //}
                    //Log.e("quantity of outs", " "+ out.size());

                    // roda pelas entidades extraidas e verifica a colisão
                    for (int bCount = 0; bCount < out.size(); bCount++){
                        PhysicalObject b = (PhysicalObject)out.get(bCount);

                        if (b.name == "bordaB"){
                        //    Log.e("posicao bola x Borda B", " "+ a.y + " - "+ b.y);
                        }


                        //Log.e("game ", "testando "+b.name);

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

                            //Log.e("pos bola sat cc", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

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

                            //Log.e("pos bola sat cc2", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

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

                            //Log.e("Game", " a.previousX 2"+aPreviousX);
                            //Log.e("Game", " a.previousY 2"+aPreviousY);
                            //Log.e("Game", " b.previousX 2"+bPreviousX);
                            //Log.e("Game", " b.previousY 2"+bPreviousY);

                            // Log.e("pos bola sat cc3", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);
                            
                            // calcula a diferença entre as posições
                            float aDiferencaPosicaoX = a.x - aPreviousX;
                            float aDiferencaPosicaoY = a.y - aPreviousY;

                            float bDiferencaPosicaoX = b.x - bPreviousX;
                            float bDiferencaPosicaoY = b.y - bPreviousY;

                            // calcula a porcentagem de cada passada;
                            float porcentagem = (100f/quantityPassagens)/100f;
                            //Log.e("Game", " quantityPassagens "+quantityPassagens+" porcentagem "+porcentagem);
                            float porcentagemAplicadaNaPassagem;
                            float aPosAConsiderarX = -1000f;
                            float aPosAConsiderarY = -1000f;
                            float bPosAConsiderarX = -1000f;
                            float bPosAConsiderarY = -1000f;

                            //Log.e("pos bola sat cc4", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

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


                                //Log.e("pos bola sat cc5", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

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

    public void createEntities() {
        LevelLoader.loadLevel(this, this.levelNumber);
        this.levelObject.loadEntities();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void verifyTouchBlock() {
        if (isBlocked) {
            if (touchEvents.size() == 0){
                isBlocked = false;
                Log.e("game", "desbloqueia game");
            }
        }
    }
}
