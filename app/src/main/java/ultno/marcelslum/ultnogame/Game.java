package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.media.AudioAttributes;
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
    public Program imageAlphaProgram;
    public Program textProgram;
    public Program solidProgram;

    public ArrayList<Line> lines;

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
    }

    public void setGameState(int state){
        this.gameState = state;
        if (state == GAME_STATE_MENU){
            Log.e("game", "game state GAME_STATE_MENU");
            menuMain.isBlocked = false;
            menuMain.isVisible = true;
            tittle.isVisible = true;
        }
    }

    public void clearGameEntities() {
    }

    public void init(){
        Storage.initializeStorage();
            levelNumber = Storage.getActualLevel();
            maxLevel = Storage.setMaxLevel();
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
                7, 0f, 1f, 0.6328125f, 1f);
                
        messageGameOver = new Text("messageGameOver", 
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.4f, gameAreaResolutionY*0.11f, 
            getResources().getString(R.string.messageGameOver), font, new Color(0f, 0f, 0f, 1f));
            
        messagePreparation = new Text("messagePreparation", 
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.5f, gameAreaResolutionY*0.3f, 
            getResources().getString(R.string.messagePreparation), font, new Color(0f, 0f, 0f, 1f));
        
        messagePreparation = new Text("messageInGame", 
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.4f, gameAreaResolutionY*0.11f, 
            getResources().getString(R.string.messageInGame), font, new Color(0f, 0f, 0f, 1f));
            
        messagePreparation = new Text("messageInGame", 
            this, gameAreaResolutionX*0.5f, gameAreaResolutionY*0.4f, gameAreaResolutionY*0.11f, 
            getResources().getString(R.string.messageInGame), font, new Color(0f, 0f, 0f, 1f));
            
        size = resolutionY * 0.07f;
        
        messageCurrentLevel = new Text("messageCurrentLevel", 
            this, resolutionX*0.83f, resolutionY*0.90f, size*0.4f, 
            getResources().getString(R.string.messageCurrentLevel), font, new Color(0f, 0f, 0f, 1f));
            
        currentLevel = new Text("currentLevel", 
            this, resolutionX*0.85f, resolutionY*0.9f, size*0.4f,
            Integer.toString(levelNumber), font, new Color(0f, 0f, 0f, 1f));
        
        messageMaxScoreLevel = new Text("messageMaxScoreLevel", 
            this, resolutionX*0.83f, resolutionY*0.95f, size*0.4f,
            getResources().getString(R.string.messageMaxScoreLevel), font, new Color(0f, 0f, 0f, 1f));
            
        maxScoreLevel = new Text("maxScoreLevel", 
            this, resolutionX*0.85f, resolutionY*0.9f, size*0.4f,
            Integer.toString(Storage.getLevelMaxScore(levelNumber)), font, new Color(0f, 0f, 0f, 1f));
        
        messageMaxScoreTotal = new Text("messageMaxScoreTotal", 
            this, resolutionX*0.06f, resolutionY*0.90f, size*0.4f,
            getResources().getString(R.string.messageMaxScoreTotal + getMaxScoreTotal()), font, new Color(0f, 0f, 0f, 1f));
        
        TextBox tb = new TextBox("textBox", this, 50f, 50f, 600f, 40f, "Atinja o alvo com a bola para destruir o alvo que desaparecerá após ser atingido!!!");

        textBoxes.add(tb);
        Utils.createSimpleAnimation(tb, "translateX", "translateX", 1000, -800f, 0f).start();
    }
    
    public void clearAllMenuEntities(){
        menuMain.clearDisplay();
        selectorLevel.clearDisplay();
        selectorVolumn.clearDisplay();
    }
    

    public void createMenus(){
        
        // cria o menu principal
        menuMain = new Menu("menuMain", this, gameAreaResolutionX/2, gameAreaResolutionY/2, 40f, font);

        // adiciona a opção de iniciar o jogo
        final Game innerGame = this;
        menuMain.addMenuOption("IniciarJogo", getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerGame.blockAndWaitTouchRelease();
                innerGame.clearAllMenuEntities;
                innerGame.loadLevel(innerGame.levelNumber);
                innerGame.loadTutorials();
                if (innerGame.levelObject.tutorials.length > 0){
                    if (Storage.getLevelTutorialSaw(innerGame.levelNumber)){
                        Storage.setLevelTutorialSaw(innerGame.levelNumber, true);
                        innerGame.setGameState(GAME_STATE_TUTORIAL);
                        innerGame.levelObject.showFirstTutorial();
                    } else {
                        innerGame.menuTutorial.getMenuOptionByName("exibirTutorial").setText  = getResources().getString(R.string.menuTutorialExibirTutorial) + self.level;
                        innerGame.menuTutorial.unblock();
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
        final Menu innerMenu = menuMain;

        // adiciona a opção de selecionar nível
        menuMain.addMenuOption("SelecionarNivel", getResources().getString(R.string.menuPrincipalAlterarNivel), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerSelectorLevel.fromMenu(innerMenu);
            }
        });

        final Game innerGame = this;

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
        menuMain.addMenuOption("AlterarVolume", getResources().getString(R.string.menuPrincipalAlterarVolume), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerSelectorVolumn.fromMenu(innerMenu);
            }
        });

        // ajusta a posição do seletor de nível
        MenuOption menuOptionSelectVolumn = menuMain.getMenuOptionByName("AlterarVolume");
        selectorVolumn.setPosition(menuOptionSelectLevel.x + (menuOptionSelectVolumn.width), menuOptionSelectVolumn.y);
        
        // cria o menu in game
        menuInGame = new Menu("menuInGame", this, gameAreaResolutionX/2, gameAreaResolutionY/2, 40f, font);

        // adiciona a opção continuar
        menuInGame.addMenuOption("Continuar", getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerGame.blockAndWaitTouchRelease();
                
                        public int gameState;
                if (innerGame.gameState == GAME_STATE_DERROTA){
                    innerGame.loadLevel(innerGame.levelNumber);
                    innerGame.menuInGame.clearDisplay();
                    innerGame.setGameState(GAME_STATE_PREPARAR);
                } else if (innerGame.gameState == GAME_STATE_VITORIA){
                    innerGame.loadLevel(innerGame.levelNumber);
                    innerGame.menuMain.getMenuOptionByName("iniciar").onChoice();
                } else if (innerGame.gameState == GAME_STATE_VITORIA){
                    innerGame.increaseAlphaAndFreeGameEntities();
                    innerGame.menuInGame.reduceAlpha(500,0, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            innerGame.setGameState(GAME_STATE_REINICIAR);
                        }
                    });
                    innerGame.messageInGame.reduceAlpha(500,0);
                }
            }
        });
        
        // adiciona a opção de voltar ao menu principal
        menuInGame.addMenuOption("Retornar", getResources().getString(R.string.retornarAoMenu), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerGame.blockAndWaitTouchRelease();
                innerGame.setGameState(GAME_STATE_MENU);
                
            }
        });
        
     
        
        // cria o menu tutorial
        menuTutorial = new Menu("menuTutorial", this, gameAreaResolutionX/2, gameAreaResolutionY/2, 40f, font);

        // adiciona a opção exibir tutorial
        menuInGame.addMenuOption("ExibirTutorial", getResources().getString(R.string.menuTutorialExibirTutorial), new MenuOption.OnChoice() {
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
        menuInGame.addMenuOption("PularTutorial", getResources().getString(R.string.menuTutorialPularTutorial), new MenuOption.OnChoice() {
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
        menuInGame.addMenuOption("Retornar", getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
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
        imageAlphaProgram = new Program(GraphicTools.vs_Image, GraphicTools.fs_Image_Alpha);
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
        soundMusic = soundPool.load(context, R.raw.music, 1);
        soundScore = soundPool.load(context, R.raw.score, 1);
    }
    
    

    
    public void simulate(long elapsed, float frameDuration){
        if (this.gameState == GAME_STATE_JOGAR) {


            for (int i = 0; i < balls.size(); i++) {
                Ball ball = balls.get(i);
                // TODO this.balls[i].verifyAcceleration();
                ball.clearCollisionData();
                ball.vx = (ball.dvx * (float) elapsed) / frameDuration;
                ball.vy = (ball.dvy * (float) elapsed) / frameDuration;
                ball.translate(ball.vx, ball.vy, true);
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

                    bars.get(0).translate(bars.get(0).vx, 0, true);

                    if (bars.size() == 2) {
                        if (button2Left.isPressed) {
                            bars.get(1).vx = -(bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else if (button2Right.isPressed) {
                            bars.get(1).vx = (bars.get(1).dvx * (float) elapsed) / frameDuration;
                        } else {
                            bars.get(1).vx = 0f;
                        }
                        bars.get(0).translate(bars.get(0).vx, 0, true);
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

            //Log.e("render ball", " ");

            background.move(1);
        }
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
        if (selectorLevel != null) selectorLevel.prepareRender(matrixView, matrixProjection);
        if (selectorVolumn != null) selectorVolumn.prepareRender(matrixView, matrixProjection);
        if (tittle != null) tittle.prepareRender(matrixView, matrixProjection);

        for (int i = 0; i < textBoxes.size(); i++){
            textBoxes.get(i).prepareRender(matrixView, matrixProjection);
        }

        for (int i = 0; i < lines.size(); i++){
            lines.get(i).prepareRender(matrixView, matrixProjection);
        }




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
        Log.e("game", "1");
        loadLevel(this.levelNumber);
        this.levelObject.loadEntities();
    }

    public void loadLevel(int levelNumber){
        switch (levelNumber){
            case 1:
                levelObject = new Level(levelNumber, this);
                levelObject.ballsQuantity = 1;
                levelObject.minBallsNotInvencibleAlive = 1;
                levelObject.ballsRadiusByResolution = new float[]{0.010f, 0.010f};
                levelObject.ballsInitialXByResolution = new float[]{0.3f, 0.5f};
                levelObject.ballsInitialYByResolution = new float[]{0.5f, 0.5f};
                levelObject.ballsDesiredVelocityXByResolution = new float[]{0.003f, 0.003f};
                levelObject.ballsDesiredVelocityYByResolution = new float[]{0.00529412f, 0.00529412f};
                levelObject.ballsColor = new Color[] {new Color(1f, 1f, 1f, 1f),new Color(1f, 1f, 1f, 1f)};
                levelObject.ballsInvencible = new boolean[]{false, false};
                levelObject.ballsAngleToRotate = new float[]{2f, 2f};
                levelObject.ballsMaxAngle = new float[]{55f, 55f};
                levelObject.ballsMinAngle = new float[]{35f, 35f};
                levelObject.ballsVelocityVariation = new float[]{0.1f, 0.1f};
                levelObject.ballsVelocityMaxByInitialVelocity = new float[]{1.5f, 1.5f};
                levelObject.ballsVelocityMinByInitialVelocity = new float[]{0.8f, 0.8f};
                levelObject.ballsTargetsAppend = (ArrayList<Target>[]) new ArrayList[10];
                levelObject.ballsFree = new boolean[]{true, true};
                levelObject.barsQuantity = 1;
                levelObject.barsSizeXByResolution = new float[]{0.22f};//0.22f};//
                levelObject.barsSizeYByResolution = new float[]{0.0175f};//0.0125f};//
                levelObject.barsInitialXByResolution = new float[]{0.3f};//0.3f
                levelObject.barsInitialYByResolution = new float[]{0.024f};//0.014f};
                levelObject.barsDesiredVelocityXByResolution = new float[]{0.0045f};
                levelObject.barsDesiredVelocityYByResolution = new float[]{0f};

                levelObject.quantityTargetsX = 10;//10ocupa 11 espaços
                levelObject.quantityTargetsY = 2;
                levelObject.targetSizeXByResolution = 0.0895f;
                levelObject.targetSizeYByResolution = 0.04f;
                levelObject.targetsDistanceByXResolution = 0.001f;
                levelObject.targetsPaddingByXResolution = 0.00225f;

                final float quantityTargetsY = levelObject.quantityTargetsY;
                final float quantityTargetsX = levelObject.quantityTargetsX;
                final float targetsDistanceByXResolution = levelObject.targetsDistanceByXResolution;
                final float targetsPaddingByXResolution = levelObject.targetsPaddingByXResolution;
                final float targetSizeXByResolution = levelObject.targetSizeXByResolution;
                final float targetSizeYByResolution = levelObject.targetSizeYByResolution;
                final float gameAreaResolutionX = this.gameAreaResolutionX;
                final float gameAreaResolutionY = this.gameAreaResolutionY;
                final Game game = this;

                Log.e("game", "2");
                levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {

                        Log.e("game", "p4");
                        for (int iY = 0; iY < quantityTargetsY;iY++){
                            for (int iX = 0; iX < quantityTargetsX; iX++) {
                                if (!(iY == 0 && iX == 0)){

                                    float xInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iX * ((gameAreaResolutionX * targetSizeXByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));
                                    float yInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iY * ((gameAreaResolutionY * targetSizeYByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));

                                    Target target = new Target("target", game, xInitial, yInitial,
                                            gameAreaResolutionX * targetSizeXByResolution,
                                            gameAreaResolutionY * targetSizeYByResolution, 9
                                            );
                                    target.isMovable = false;
                                    target.alpha = 1;
                                    target.states = new int[]{0,1};
                                    target.currentState = 1;
                                    game.addTarget(target);
                                    Log.e("game", "p5");
                                }
                            }
                        }
                    }

                    @Override
                    public void createObstacles() {

                    }

                    @Override
                    public void createWindows() {

                    }
                });

                Log.e("game", "3");
                break;
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
