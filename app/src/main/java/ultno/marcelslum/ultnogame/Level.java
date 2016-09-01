package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Level {

    ArrayList<Tutorial> tutorials;
    int showingTutorial = -1;
    public Game game;
    public int ballsQuantity;
    public int minBallsAlive;
    public float[] ballsRadius_BR;
    public float[] ballsX_BR;
    public float[] ballsY_BR;
    public float[] ballsVX_BR;
    public float[] ballsVY_BR;
    public int [] ballsTextureMap;
    public boolean[] ballsInvencible;
    public float[] ballsAngleToRotate;
    public float[] ballsMaxAngle;
    public float[] ballsMinAngle;
    public float[] ballsVelocityVariation;
    public float[] ballsVelocityMax_BI;
    public float[] ballsVelocityMin_BI;
    public ArrayList<ArrayList<Target>> ballsTargetsAppend;
    public boolean[] ballsFree;
    public int barsQuantity;
    public float[] barsWidth_BR;
    public float[] barsHeight_BR;
    public float[] barsX_BR;
    public float[] barsY_BR;
    public float[] barsVX_BR;
    public float[] barsVY_BR;
    public boolean [] barsChangeSize;
    public boolean [] barsIncreaseSizeX;
    public float [] barsMinSizeByInitial;
    public float [] barsMaxSizeByInitial;
    public float [] barsSizeVariationVelocityX;
    public float targetWidth_BR;
    public float targetHeight_BR;
    public float targetsDistance_BR;
    public float targetsPadding_BR;
    public int [][] targetsMap;
    public int [] targetsStates;
    public int obstaclesQuantity;
    public float[] obstaclesWidth_BR;
    public float[] obstaclesHeight_BR;
    public float[] obstaclesX_BR;
    public float[] obstaclesY_BR;
    public int windowsQuantity;
    public float[] windowsY;
    public float[] windowsHeight;
    public int[] windowsQuantityOfLines;
    public float[] windowsDistance;
    public float[] windowsVelocity;

    private Level(){
        this.game = LevelBuilder.game;
        this.ballsQuantity = LevelBuilder.ballsQuantity;
        this.minBallsAlive = LevelBuilder.minBallsAlive;
        this.ballsRadius_BR = LevelBuilder.ballsRadius_BR;
        this.ballsX_BR = LevelBuilder.ballsX_BR;
        this.ballsY_BR = LevelBuilder.ballsY_BR;
        this.ballsVX_BR = LevelBuilder.ballsVX_BR;
        this.ballsVY_BR = LevelBuilder.ballsVY_BR;
        this.ballsTextureMap = LevelBuilder.ballsTextureMap;
        this.ballsInvencible = LevelBuilder.ballsInvencible;
        this.ballsAngleToRotate = LevelBuilder.ballsAngleToRotate;
        this.ballsMaxAngle = LevelBuilder.ballsMaxAngle;
        this.ballsMinAngle = LevelBuilder.ballsMinAngle;
        this.ballsVelocityVariation = LevelBuilder.ballsVelocityVariation;
        this.ballsVelocityMax_BI = LevelBuilder.ballsVelocityMax_BI;
        this.ballsVelocityMin_BI = LevelBuilder.ballsVelocityMin_BI;
        this.ballsTargetsAppend = LevelBuilder.ballsTargetsAppend;
        this.ballsFree = LevelBuilder.ballsFree;
        this.barsQuantity = LevelBuilder.barsQuantity;
        this.barsWidth_BR = LevelBuilder.barsWidth_BR;
        this.barsHeight_BR = LevelBuilder.barsHeight_BR;
        this.barsX_BR = LevelBuilder.barsX_BR;
        this.barsY_BR = LevelBuilder.barsY_BR;
        this.barsVX_BR = LevelBuilder.barsVX_BR;
        this.barsVY_BR = LevelBuilder.barsVY_BR;
        this.barsChangeSize = LevelBuilder.barsChangeSize;
        this.barsIncreaseSizeX = LevelBuilder.barsIncreaseSizeX;
        this.barsMinSizeByInitial = LevelBuilder.barsMinSizeByInitial;
        this.barsMaxSizeByInitial = LevelBuilder.barsMaxSizeByInitial;
        this.barsSizeVariationVelocityX = LevelBuilder.barsSizeVariationVelocityX;
        this.targetWidth_BR = LevelBuilder.targetsWidht_BR;
        this.targetHeight_BR = LevelBuilder.targetsHeight_BR;
        this.targetsDistance_BR = LevelBuilder.targetsDistance_BXR;
        this.targetsPadding_BR = LevelBuilder.targetsPadding_BXR;
        this.targetsMap = LevelBuilder.targetsMap;
        this.targetsStates = LevelBuilder.targetsStates;
        this.obstaclesQuantity = LevelBuilder.obstaclesQuantity;
        this.obstaclesWidth_BR = LevelBuilder.obstaclesWidth_BR;
        this.obstaclesHeight_BR = LevelBuilder.obstaclesHeight_BR;
        this.obstaclesX_BR = LevelBuilder.obstaclesX_BR;
        this.obstaclesY_BR = LevelBuilder.obstaclesY_BR;
        this.windowsQuantity = LevelBuilder.windowsQuantity;
        this.windowsY = LevelBuilder.windowsY;
        this.windowsHeight = LevelBuilder.windowsHeight;
        this.windowsQuantityOfLines = LevelBuilder.windowsQuantityOfLines;
        this.windowsDistance = LevelBuilder.windowsDistance;
        this.windowsVelocity = LevelBuilder.windowsVelocity;
        this.tutorials = new ArrayList<>();
    }
    
    public void showFirstTutorial(){
        this.game.blockAndWaitTouchRelease();
        this.showingTutorial = 0;
        this.tutorials.get(0).textBox.alpha = 0f;
        this.tutorials.get(0).show(game.soundPool, game.soundTextBoxAppear, game.volume);
    }

    public void nextTutorial(){
        Log.e("level", "nextTutorial");
        Log.e("level", "showingTutorial "+showingTutorial);
        final Level innerLevel = this;
        this.game.blockAndWaitTouchRelease();
        if (this.tutorials.get(this.showingTutorial).isBlocked == false){
            game.soundPool.play(game.soundMenuSelectBig, 0.01f*(float)game.volume, 0.01f*(float)game.volume, 0, 0, 1);
            if (showingTutorial + 1 == this.tutorials.size()){

                Log.e("level", "ultimo tutorail, setando preparar");

                this.tutorials.get(this.showingTutorial).setOnUnshowAfterAnim2(new Tutorial.OnUnshowAfterAnim2() {
                    @Override
                    public void onUnshowAfterAnim2() {
                        innerLevel.game.setGameState(Game.GAME_STATE_PREPARAR);
                    }
                });
                this.tutorials.get(this.showingTutorial).unshow();
            } else {
                Log.e("level", "showingTutorial "+showingTutorial);
                tutorials.get(showingTutorial).setOnUnshowAfterAnim2(new Tutorial.OnUnshowAfterAnim2() {
                    @Override
                    public void onUnshowAfterAnim2() {
                        //Log.e("level", "onUnshowAfterAnim2");
                        innerLevel.showingTutorial = innerLevel.showingTutorial + 1;
                        innerLevel.tutorials.get(innerLevel.showingTutorial).show(innerLevel.game.soundPool, innerLevel.game.soundTextBoxAppear, game.volume);
                    }
                });
                tutorials.get(showingTutorial).unshow();

                //Log.e("level", "showingTutorial depois"+showingTutorial);
            }
        }
    }

    public void loadEntities() {
        this.game.eraseAllGameEntities();
        this.game.quad = new Quadtree(new RectangleM(0,0,this.game.gameAreaResolutionX,this.game.gameAreaResolutionY),5,5);


        this.game.scorePanel = new ScorePanel("scorePanel", this.game,
                this.game.gameAreaResolutionX * 0.5f, this.game.gameAreaResolutionY * 1.05f, this.game.resolutionY * 0.08f);

        this.game.objectivePanel = new ObjectivePanel("objectivePanel", this.game,
                this.game.gameAreaResolutionX * 0.5f, this.game.gameAreaResolutionY * 1.005f, this.game.resolutionY * 0.027f);
        this.game.objectivePanel.alpha = 0.9f;



        // escolhe o background de acordo com o número do nível
        int back;
        if (game.levelNumber < 10) {
            back = game.levelNumber;
        } else {
            back = game.levelNumber % 10;
        }
        Utils.setTexture("drawable/finalback1", game.texturenames, Game.TEXTURE_BACKGROUND, game.context); // background
        this.game.background = new Background("background", this.game, 0, 0, this.game.gameAreaResolutionX,this.game.resolutionY);

        //Log.e("Level loadEnt", "1");

        this.game.bordaE = new Rectangle("bordaE", this.game, -999, 0, 1000, this.game.gameAreaResolutionY*2, 10, new Color(0,0,0,1));
        this.game.bordaE.isMovable = false;
        this.game.bordaE.program = this.game.solidProgram;

        //Log.e("Level loadEnt", "1");
        this.game.bordaD = new Rectangle("bordaD", this.game, this.game.gameAreaResolutionX-2, 0, 1000, this.game.gameAreaResolutionY*2, 10, new Color(0,0,0,1));
        this.game.bordaD.isMovable = false;
        this.game.bordaD.program = this.game.solidProgram;

        //Log.e("Level loadEnt", "1");
        this.game.bordaC = new Rectangle("bordaC", this.game, 1, -1000, this.game.gameAreaResolutionX-4, 1001, 10, new Color(0,0,0,1));
        this.game.bordaC.isMovable = false;
        this.game.bordaC.program = this.game.solidProgram;

        //Log.e("Level loadEnt", "1");
        this.game.bordaB = new Rectangle("bordaB", this.game, -1000, this.game.gameAreaResolutionY-2, this.game.gameAreaResolutionX*3, 1000, 10, new Color(0,0,0,1));
        this.game.bordaB.isMovable = false;
        this.game.bordaB.program = this.game.solidProgram;

        //Log.e("Level loadEnt", "1");
        float y = this.game.resolutionY * 0.86f;
        float buttonSize = this.game.resolutionY * 0.13f;


        // BOTÃO 1 ESQUERDA
        float x = this.game.resolutionX * 0.01f;
        this.game.button1Left = new Button("button1Left", this.game, x, y, buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        this.game.button1Left.setTextureMap(19);
        this.game.button1Left.textureMapUnpressed = 19;
        this.game.button1Left.textureMapPressed = 18;
        this.game.button1Left.alpha = 0.7f;

        // BOTÃO 1 DIREITA
        x = this.game.resolutionX * 0.14f;
        this.game.button1Right = new Button("button1Right", this.game, x, y, buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        this.game.button1Right.setTextureMap(20);
        this.game.button1Right.textureMapUnpressed = 20;
        this.game.button1Right.textureMapPressed = 17;
        this.game.button1Right.alpha = 0.7f;

        //Log.e("Level loadEnt", "1");
        if (this.barsQuantity > 1) {
            // BOTÃO 2 ESQUERDA
            x = this.game.resolutionX * 0.66f;
            this.game.button2Left = new Button("button2Left", this.game, x, y, buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
            this.game.button2Left.setTextureMap(19);
            this.game.button2Left.textureMapUnpressed = 19;
            this.game.button2Left.textureMapPressed = 18;

            // BOTÃO 2 DIREITA
            x = this.game.resolutionX * 0.83f;
            this.game.button2Right = new Button("buttonRight", this.game, x, y, buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
            this.game.button2Right.setTextureMap(20);
            this.game.button2Right.textureMapUnpressed = 20;
            this.game.button2Right.textureMapPressed = 17;
        }

        // BOTÃO SOM
        this.game.buttonSound = new ButtonOnOff("buttonSound", this.game, this.game.gameAreaResolutionX * 0.35f,
                this.game.resolutionY * 0.89f, this.game.resolutionY * 0.06f, this.game.resolutionY * 0.06f, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        this.game.buttonSound.textureMapUnpressed = 9;
        this.game.buttonSound.textureMapPressed = 10;
        this.game.buttonSound.getListener().x = this.game.gameAreaResolutionX * 0.32f;
        this.game.buttonSound.getListener().y = this.game.resolutionY * 0.86f;
        this.game.buttonSound.getListener().width = this.game.gameAreaResolutionX * 0.12f;
        this.game.buttonSound.getListener().height = this.game.resolutionY * 0.12f;

        this.game.buttonSound.alpha = 0.5f;
        if (this.game.menuVolume == 0 || this.game.volume == 0) {
            this.game.buttonSound.setOff();
        } else {
            this.game.buttonSound.setOn();
        }

        final Game innerGame = game;
        this.game.buttonSound.setOnOffBehavior(new ButtonOnOff.OnOffBehavior() {
            @Override
            public void onBehavior() {
                if (innerGame.menuVolume == 0) {
                    innerGame.volume = 50;
                    innerGame.menuVolume = 50;
                } else {
                    innerGame.volume = innerGame.menuVolume;
                }
            }

            @Override
            public void offBehavior() {
                innerGame.volume = 0;
            }
        });

        // BOTÃO MUSICA
        this.game.buttonMusic = new ButtonOnOff("buttonMusic", this.game, this.game.gameAreaResolutionX * 0.61f,
                this.game.resolutionY * 0.89f, this.game.resolutionY * 0.06f, this.game.resolutionY * 0.06f, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        this.game.buttonMusic.textureMapUnpressed = 2;
        this.game.buttonMusic.textureMapPressed = 1;
        this.game.buttonMusic.alpha = 0.5f;
        this.game.buttonMusic.getListener().x = this.game.gameAreaResolutionX * 0.58f;
        this.game.buttonMusic.getListener().y = this.game.resolutionY * 0.86f;
        this.game.buttonMusic.getListener().width = this.game.gameAreaResolutionX * 0.12f;
        this.game.buttonMusic.getListener().height = this.game.resolutionY * 0.12f;
        if (!this.game.musicOn || game.menuVolume == 0) {
            this.game.buttonMusic.setOff();
        } else {
            this.game.buttonMusic.setOn();
        }

        this.game.buttonMusic.setOnOffBehavior(new ButtonOnOff.OnOffBehavior() {
            @Override
            public void onBehavior() {
                innerGame.musicOn = true;
                if (innerGame.menuVolume == 0) {
                    innerGame.volume = 50;
                    innerGame.menuVolume = 50;
                }
                if (innerGame.music != null){
                    innerGame.music.setVolume(0.006f* (float) 50, 0.006f* (float) 50);
                    innerGame.music.start();
                }
            }

            @Override
            public void offBehavior() {
                innerGame.musicOn = false;
                if (innerGame.music != null){
                    innerGame.music.pause();
                }
            }
        });

        InteractionListener gameAreaInteractionListener = new InteractionListener("gameArea111", 0f, 0f,
                game.gameAreaResolutionX, game.gameAreaResolutionY * 0.8f, 0, game.background, game);

        gameAreaInteractionListener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (innerGame.gameState == Game.GAME_STATE_JOGAR){
                    ////Log.e("level", "listener pause ativado");
                    innerGame.blockAndWaitTouchRelease();
                    innerGame.setGameState(Game.GAME_STATE_PAUSE);
                }
            }

            @Override
            public void onUnpress(){
            }
        });
        this.game.addInteracionListener(gameAreaInteractionListener);

        for (int i = 0; i < this.barsQuantity; i++){

            float barX = game.gameAreaResolutionX * barsX_BR[i];
            float barY = game.gameAreaResolutionY - (game.gameAreaResolutionY * barsY_BR[i]);

            float barWidth = game.gameAreaResolutionX * barsWidth_BR[i];
            float barHeight = game.gameAreaResolutionY * barsHeight_BR[i];

            float barVelocityX = game.gameAreaResolutionX * barsVX_BR[i];
            float barVelocityY = game.gameAreaResolutionY * barsVY_BR[i];

            game.barsDesiredVelocityX[i] = barVelocityX;
            game.barsDesiredVelocityY[i] = barVelocityY;
            game.barsInitialPositionX[i] = barX;
            game.barsInitialPositionY[i] = barY;

            Bar bar = new Bar("bar", game, barX, barY, barWidth, barHeight);
            game.addBar(bar);

            bar.initialX = barX;
            bar.initialY = barY;
            bar.initialDesireVelocityX = barVelocityX;
            bar.dvx = barVelocityX;
            if (barsChangeSize[i]) {
                bar.setSizeVariation(barsSizeVariationVelocityX[i], 0f, true, true, barsMinSizeByInitial[i], barsMaxSizeByInitial[i]);
            }
        }

        float targetWidth = game.gameAreaResolutionX * game.levelObject.targetWidth_BR;
        float targetHeight = game.gameAreaResolutionY * game.levelObject.targetHeight_BR;
        float targetX;
        float targetY;

        Log.e("level", "targetsMap.length "+targetsMap.length);
        for (int iY = 0; iY < targetsMap.length;iY++){
            for (int iX = 0; iX < targetsMap[iY].length; iX++) {
                if (targetsMap[iY][iX] != 0) {
                    targetX = (game.gameAreaResolutionX * targetsPadding_BR) +
                            (iX * ((game.gameAreaResolutionX * targetWidth_BR) +
                                    (game.gameAreaResolutionX * targetsDistance_BR)));
                    targetY = (game.gameAreaResolutionX * targetsPadding_BR) +
                            (iY * ((game.gameAreaResolutionY * targetHeight_BR) +
                                    (game.gameAreaResolutionX * targetsDistance_BR)));

                    game.addTarget(
                            new TargetBuilder()
                                    .name("target")
                                    .game(game)
                                    .x(targetX)
                                    .y(targetY)
                                    .width(targetWidth)
                                    .height(targetHeight)
                                    .weight(9)
                                    .type(targetsMap[iY][iX])
                                    .states(targetsStates)
                                    .build()
                    );
                }
            }
        }

        // adiciona os obstáculos
        for (int i = 0; i < this.obstaclesQuantity; i++){
            float obstacleX = game.gameAreaResolutionX * obstaclesX_BR[i];
            float obstacleY = game.gameAreaResolutionY * this.obstaclesY_BR[i];
            float obstacleWidth = game.gameAreaResolutionX * obstaclesWidth_BR[i];
            float obstacleHeight = game.gameAreaResolutionY * obstaclesHeight_BR[i];
            game.addObstacle(new Obstacle("obstacle", game, obstacleX, obstacleY, obstacleWidth, obstacleHeight));
        }
        
        for (int i = 0; i < this.windowsQuantity; i++){
            float wY = game.gameAreaResolutionY * windowsY[i];
            float wHeight = game.gameAreaResolutionY * windowsHeight[i];
            float wVelocity = game.gameAreaResolutionX * windowsVelocity[i];
            
            game.addWindow(new WindowGame("windows", game, wY, windowsQuantityOfLines[i], wHeight, windowsDistance[i], wVelocity));
        }
        
        int quantityOfSpecialTargets = 0;
        for (int i = 0; i < this.game.targets.size(); i++){
            if (this.game.targets.get(i).special == 1)
                quantityOfSpecialTargets += 1;
        }

        // TODO?????
        /*
        for (var i = 0; i < quantityOfSpecialTargets * 3; i++){
            this.game.preAllocateBalls[i] = BALL.createBall("ballexploded", this.game, V(0,0), 0, false);
            this.game.preAllocateBalls[i].preAllocateUsed = false;
        }

        for (var i = 0; i < quantityOfSpecialTargets; i++){
            this.game.preAllocateParticles[i] = PARTICLES.createParticles("ballExplode", this.game, 4, V(0,0), 2,1000, this.game.resolution, black);
            this.game.preAllocateParticles[i].preAllocateUsed = false;
        }
        */


        //Log.e("Level loadEnt", "2");
        int numberOfBallsInvencible = 0;

        for (int i = 0; i < this.ballsQuantity; i++){
            float ballX = this.game.gameAreaResolutionX * this.ballsX_BR[i];
            float ballY = this.game.gameAreaResolutionY * this.ballsY_BR[i];

            float radium = this.game.gameAreaResolutionY * this.ballsRadius_BR[i];

            float ballVelocityX = this.game.gameAreaResolutionX * this.ballsVX_BR[i];
            float ballVelocityY = this.game.gameAreaResolutionY * this.ballsVY_BR[i];

            //game.ballsDesiredVelocityX[i] = ballVelocityX;
            //game.ballsDesiredVelocityY[i] = ballVelocityY;
            //game.ballsInitialPositionX[i] = ballX;
            //game.ballsInitialPositionY[i] = ballY;

            if (this.ballsInvencible[i]){
                numberOfBallsInvencible += 1;
            }

            //Log.e("Level loadEnt", "3");

            Ball ball = new Ball("ball", this.game, ballX, ballY, radium, this.ballsTextureMap[i]);

            ball.angleToRotate = this.ballsAngleToRotate[i];
            ball.velocityVariation = this.ballsVelocityVariation[i];

            ball.velocityMax_BI = ballsVelocityMax_BI[i];
            ball.velocityMin_BI = ballsVelocityMin_BI[i];

            //Log.e("Level loadEnt", "4");

            ball.maxAngle = this.ballsMaxAngle[i];
            ball.minAngle = this.ballsMinAngle[i];

            ball.initialDesireVelocityX = ballVelocityX;
            ball.initialDesireVelocityY = ballVelocityY;

            ball.initialX = ballX;
            ball.initialY = ballY;


            ball.dvx = ballVelocityX;
            ball.dvy = ballVelocityY;

            for (int t = 0; t < this.ballsTargetsAppend.size(); t++){
                ball.targetsAppend = this.ballsTargetsAppend.get(i);
            }
            //console.log(ball.targetsAppend);

            //Log.e("Level loadEnt", "5");

            ball.isFree = this.ballsFree[i];

            //Log.e("Level loadEnt", "5.1");

            if (this.ballsInvencible[i]){
                ball.setInvencible();
            }

            this.game.addBall(ball);

        }
       //todo this.game.hudEntities.scorePanel.setValue(this.ballsQuantity - ballsInvencible, ballsInvencible, this.minBallsAlive);
    }

    static public class LevelBuilder {
        private final static int default_ballsQuantity = 1;
        private final static int default_minBallAlive = 1;
        private final static float default_ballsRadius_BR = 0.012f;
        private final static float default_ballsX_BR = 1f;
        private final static float default_ballsY_BR = 1f;
        private final static float default_ballsVX_BR = 0.0028f;
        private final static float default_ballsVY_BR = 0.004941176f;
        private final static int default_ballTextureMap = Ball.COLOR_BALL_BLACK;
        private final static float default_ballsAngleToRotate = 2f;
        private final static float default_ballsMaxAngle = 55f;
        private final static float default_ballsMinAngle = 35f;
        private final static float default_ballsVelocityVariation = 0.1f;
        private final static float default_ballsVelocityMax_BI = 1.5f;
        private final static float default_ballsVelocityMin_BI = 0.8f;
        private final static int default_barsQuantity = 1;
        private final static float default_barsWidth_BR = 0.22f;
        private final static float default_barsHeight_BR = 0.0175f;
        private final static float default_barsY_BR = 0.024f;
        private final static float default_barsVX_BR = 0.0045f;

        private static Game game;
        private static int ballsQuantity = default_ballsQuantity;
        private static int minBallsAlive = default_minBallAlive;
        private static float[] ballsRadius_BR = new float[]{default_ballsRadius_BR};
        private static float[] ballsX_BR = new float[]{default_ballsX_BR};
        private static float[] ballsY_BR = new float[]{default_ballsY_BR};
        private static float[] ballsVX_BR = new float[]{default_ballsVX_BR};
        private static float[] ballsVY_BR = new float[]{default_ballsVY_BR};
        private static int[] ballsTextureMap = new int []{default_ballTextureMap};
        private static boolean[] ballsInvencible = new boolean[]{false};
        private static float[] ballsAngleToRotate = new float[]{default_ballsAngleToRotate};
        private static float[] ballsMaxAngle = new float[]{default_ballsMaxAngle};
        private static float[] ballsMinAngle = new float[]{default_ballsMinAngle};
        private static float[] ballsVelocityVariation = new float[]{default_ballsVelocityVariation};
        private static float[] ballsVelocityMax_BI = new float[]{default_ballsVelocityMax_BI};
        private static float[] ballsVelocityMin_BI = new float[]{default_ballsVelocityMin_BI};
        private static ArrayList<ArrayList<Target>> ballsTargetsAppend = new ArrayList<ArrayList<Target>>();
        private static boolean[] ballsFree = new boolean[]{true};
        private static int barsQuantity = default_barsQuantity;
        private static float[] barsWidth_BR = new float[]{default_barsWidth_BR};
        private static float[] barsHeight_BR = new float[]{default_barsHeight_BR};
        private static float[] barsX_BR = new float[]{0.3f};
        private static float[] barsY_BR = new float[]{default_barsY_BR};
        private static float[] barsVX_BR = new float[]{default_barsVX_BR};
        private static float[] barsVY_BR = new float[]{0f};
        private static boolean[] barsChangeSize = new boolean[]{false};
        private static boolean[] barsIncreaseSizeX = new boolean[]{true};
        private static float[] barsMinSizeByInitial = new float[]{0f};
        private static float[] barsMaxSizeByInitial = new float[]{0f};
        private static float[] barsSizeVariationVelocityX = new float[]{0f};

        private static float targetsWidht_BR = 0.1f;
        private static float targetsHeight_BR = 0.1f;
        private static float targetsDistance_BXR = 0.01f;
        private static float targetsPadding_BXR = 0.01f;
        private static int [] targetsStates;
        private static int [][] targetsMap;
        private static int obstaclesQuantity = 0;
        private static float[] obstaclesWidth_BR;
        private static float[] obstaclesHeight_BR;
        private static float[] obstaclesX_BR;
        private static float[] obstaclesY_BR;
        private static int windowsQuantity;
        private static float[] windowsY;
        private static float[] windowsHeight;
        private static int[] windowsQuantityOfLines;
        private static float[] windowsDistance;
        private static float[] windowsVelocity;
        

        public LevelBuilder game(Game game) {
            this.game = game;
            return this;
        }

        public LevelBuilder setBallsQuantity(int ballsQuantity) {
            this.ballsQuantity = ballsQuantity;
            return this;
        }

        public LevelBuilder setBallsAlive(int minBallsAlive) {
            this.minBallsAlive = minBallsAlive;
            return this;
        }

        public LevelBuilder setBallsRadius_BD_0_01(float... ballsRadius_BR) {
            this.ballsRadius_BR = new float[ballsRadius_BR.length];
            for (int i = 0; i < ballsRadius_BR.length; i++){
                this.ballsRadius_BR[i] = ballsRadius_BR[i] * this.default_ballsRadius_BR;
            }
            return this;
        }

        public LevelBuilder setBallsX_B1(float... ballsInitialX_BR) {
            this.ballsX_BR = new float[ballsInitialX_BR.length];
            for (int i = 0; i < ballsInitialX_BR.length; i++){
                this.ballsX_BR[i] = ballsInitialX_BR[i] * this.default_ballsX_BR;
            }
            return this;
        }

        public LevelBuilder setBallsY_B1(float... ballsY_BR) {
            this.ballsY_BR = new float[ballsY_BR.length];
            for (int i = 0; i < ballsY_BR.length; i++){
                this.ballsY_BR[i] = ballsY_BR[i] * this.default_ballsY_BR;
            }
            return this;
        }


        public LevelBuilder setBallsVX(float... ballsDesiredVelocityX_BR) {
            this.ballsVX_BR = new float[ballsDesiredVelocityX_BR.length];
            for (int i = 0; i < ballsDesiredVelocityX_BR.length; i++){
                this.ballsVX_BR[i] = ballsDesiredVelocityX_BR[i] * this.default_ballsVX_BR;
            }
            return this;
        }

        public LevelBuilder setBallsVY(float... ballsDesiredVelocityY_BR) {
            this.ballsVY_BR = new float[ballsDesiredVelocityY_BR.length];
            for (int i = 0; i < ballsDesiredVelocityY_BR.length; i++){
                this.ballsVY_BR[i] = ballsDesiredVelocityY_BR[i] * this.default_ballsVY_BR;
            }
            return this;
        }

        public LevelBuilder setBallsTextureMap(int... ballsTextureMap) {
            this.ballsTextureMap = new int[ballsTextureMap.length];
            for (int i = 0; i < ballsTextureMap.length; i++){
                this.ballsTextureMap[i] = ballsTextureMap[i];
            }
            return this;
        }

        public LevelBuilder setBallsInvencible(boolean... ballsInvencible) {
            this.ballsInvencible = new boolean[ballsInvencible.length];
            for (int i = 0; i < ballsInvencible.length; i++){
                this.ballsInvencible[i] = ballsInvencible[i];
            }
            return this;
        }

        public LevelBuilder setBallsAngleToRotate_BD_2(float... ballsAngleToRotate) {
            this.ballsAngleToRotate = new float[ballsAngleToRotate.length];
            for (int i = 0; i < ballsAngleToRotate.length; i++){
                this.ballsAngleToRotate[i] = ballsAngleToRotate[i] * this.default_ballsAngleToRotate;
            }
            return this;
        }

        public LevelBuilder setBallsMaxAngle_BD_55(float... ballsMaxAngle) {
            this.ballsMaxAngle = new float[ballsMaxAngle.length];
            for (int i = 0; i < ballsMaxAngle.length; i++){
                this.ballsMaxAngle[i] = ballsMaxAngle[i] * this.default_ballsMaxAngle;
            }
            return this;
        }

        public LevelBuilder setBallsMinAngle_BD_35(float... ballsMinAngle) {
            this.ballsMinAngle = new float[ballsMinAngle.length];
            for (int i = 0; i < ballsMinAngle.length; i++){
                this.ballsMinAngle[i] = ballsMinAngle[i] * this.default_ballsMinAngle;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityVariation_BD_0_1(float... ballsVelocityVariation) {
            this.ballsVelocityVariation = new float[ballsVelocityVariation.length];
            for (int i = 0; i < ballsVelocityVariation.length; i++){
                this.ballsVelocityVariation[i] = ballsVelocityVariation[i] * this.default_ballsVelocityVariation;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMax_BD_1_5(float... ballsVelocityMax_BI) {
            this.ballsVelocityMax_BI = new float[ballsVelocityMax_BI.length];
            for (int i = 0; i < ballsVelocityMax_BI.length; i++){
                this.ballsVelocityMax_BI[i] = ballsVelocityMax_BI[i] * this.default_ballsVelocityMax_BI;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMin_BD_0_8(float... ballsVelocityMin_BI) {
            this.ballsVelocityMin_BI = new float[ballsVelocityMin_BI.length];
            for (int i = 0; i < ballsVelocityMin_BI.length; i++){
                this.ballsVelocityMin_BI[i] = ballsVelocityMin_BI[i] * this.default_ballsVelocityMin_BI;
            }
            return this;
        }

        public LevelBuilder setBallsTargetsAppend(ArrayList<ArrayList<Target>> ballsTargetsAppend) {
            this.ballsTargetsAppend = ballsTargetsAppend;
            return this;
        }

        public LevelBuilder setBallsFree(boolean... ballsFree) {
            this.ballsFree = new boolean[ballsFree.length];
            for (int i = 0; i < ballsFree.length; i++){
                this.ballsFree[i] = ballsFree[i];
            }
            return this;
        }

        public LevelBuilder setBarsQuantity(int barsQuantity) {
            this.barsQuantity = barsQuantity;
            return this;
        }

        public LevelBuilder setBarsWidth_BD_0_22(float... barsSizeX_BR) {
            for (int i = 0; i < barsSizeX_BR.length; i++){
                this.barsWidth_BR[i] = barsSizeX_BR[i] * default_barsWidth_BR;
            }
            return this;
        }

        public LevelBuilder setBarsHeight_BD_0_0175(float... barsSizeY_BR) {
            this.barsHeight_BR = new float[barsSizeY_BR.length];
            for (int i = 0; i < barsSizeY_BR.length; i++){
                this.barsHeight_BR[i] = barsSizeY_BR[i] * default_barsHeight_BR;
            }
            return this;
        }

        public LevelBuilder setBarsX_B1(float... barsX_BR) {
            this.barsX_BR = new float[barsX_BR.length];
            for (int i = 0; i < barsX_BR.length; i++){
                this.barsX_BR[i] = barsX_BR[i];
            }
            return this;
        }

        public LevelBuilder setBarsY_BD_0_024(float... barsY_BR) {
            this.barsY_BR = new float[barsY_BR.length];
            for (int i = 0; i < barsY_BR.length; i++){
                this.barsY_BR[i] = barsY_BR[i] * default_barsY_BR;
            }
            return this;
        }

        public LevelBuilder setBarsVX_BD_0_0045(float... barsVX_BR) {
            this.barsVX_BR = new float[barsVX_BR.length];
            for (int i = 0; i < barsVX_BR.length; i++){
                this.barsVX_BR[i] = barsVX_BR[i] * default_barsVX_BR;
            }
            return this;
        }

        public LevelBuilder setBarsVY(float... barsVY_BR) {
            this.barsVY_BR = new float[barsVY_BR.length];
            for (int i = 0; i < barsVY_BR.length; i++){
                this.barsVY_BR[i] = barsVY_BR[i];
            }
            return this;
        }

        public LevelBuilder setBarsChangeSize(boolean... barsChangeSize) {
            this.barsChangeSize = new boolean[barsChangeSize.length];
            for (int i = 0; i < barsChangeSize.length; i++){
                this.barsChangeSize[i] = barsChangeSize[i];
            }
            return this;
        }

        public LevelBuilder setBarsIncreaseSizeX(boolean... barsIncreaseSizeX) {
            this.barsIncreaseSizeX = new boolean[barsIncreaseSizeX.length];
            for (int i = 0; i < barsIncreaseSizeX.length; i++){
                this.barsIncreaseSizeX[i] = barsIncreaseSizeX[i];
            }
            return this;
        }

        public LevelBuilder setBarsMinSizeByInitial(float... barsMinSizeByInitial) {
            this.barsMinSizeByInitial = new float[barsMinSizeByInitial.length];
            for (int i = 0; i < barsMinSizeByInitial.length; i++){
                this.barsMinSizeByInitial[i] = barsMinSizeByInitial[i];
            }
            return this;
        }

        public LevelBuilder setBarsMaxSizeByInitial(float... barsMaxSizeByInitial) {
            this.barsMaxSizeByInitial = new float[barsMaxSizeByInitial.length];
            for (int i = 0; i < barsMaxSizeByInitial.length; i++){
                this.barsMaxSizeByInitial[i] = barsMaxSizeByInitial[i];
            }
            return this;
        }

        public LevelBuilder setBarsSizeVariationVelocityX(float... barsSizeVariationVelocityX) {
            this.barsSizeVariationVelocityX = new float[barsSizeVariationVelocityX.length];
            for (int i = 0; i < barsSizeVariationVelocityX.length; i++){
                this.barsSizeVariationVelocityX[i] = barsSizeVariationVelocityX[i];
            }
            return this;
        }

        public LevelBuilder setTargetsWidth(float targetsSizeX_BR) {
            this.targetsWidht_BR = targetsSizeX_BR;
            return this;
        }

        public LevelBuilder setTargetsHeight(float targetsSizeY_BR) {
            this.targetsHeight_BR = targetsSizeY_BR;
            return this;
        }

        public LevelBuilder setTargetsMap(int [][] targetsMap) {
            this.targetsMap = targetsMap;
            return this;
        }

        public LevelBuilder setTargetsStates(int [] targetsStates) {
            this.targetsStates = targetsStates;
            return this;
        }

        public LevelBuilder setTargetsDistance(float targetsDistance_BXR) {
            this.targetsDistance_BXR = targetsDistance_BXR;
            return this;
        }

        public LevelBuilder setTargetsPadding(float targetsPadding_BXR) {
            this.targetsPadding_BXR = targetsPadding_BXR;
            return this;
        }

        public LevelBuilder setObstaclesQuantity(int obstaclesQuantity) {
            this.obstaclesQuantity = obstaclesQuantity;
            return this;
        }

        public LevelBuilder setObstaclesWidth(float... obstaclesWidth_BR) {
            this.obstaclesWidth_BR = new float[obstaclesWidth_BR.length];
            for (int i = 0; i < obstaclesWidth_BR.length; i++){
                this.obstaclesWidth_BR[i] = obstaclesWidth_BR[i];
            }
            return this;
        }

        public LevelBuilder setObstaclesHeight(float... obstaclesHeight_BR) {
            this.obstaclesHeight_BR = new float[obstaclesHeight_BR.length];
            for (int i = 0; i < obstaclesHeight_BR.length; i++){
                this.obstaclesHeight_BR[i] = obstaclesHeight_BR[i];
            }
            return this;
        }

        public LevelBuilder setObstaclesX(float... obstaclesX_BR) {
            this.obstaclesX_BR = new float[obstaclesX_BR.length];
            for (int i = 0; i < obstaclesX_BR.length; i++){
                this.obstaclesX_BR[i] = obstaclesX_BR[i];
            }
            return this;
        }

        public LevelBuilder setObstaclesY(float... obstaclesY_BR) {
            this.obstaclesY_BR = new float[obstaclesY_BR.length];
            for (int i = 0; i < obstaclesY_BR.length; i++){
                this.obstaclesY_BR[i] = obstaclesY_BR[i];
            }
            return this;
        }
        
        public LevelBuilder setWindowsQuantity(int windowsQuantity) {
            this.windowsQuantity = windowsQuantity;
            return this;
        }
        
        public LevelBuilder setWindowsY(float... windowsY) {
            this.windowsY = new float[windowsY.length];
            for (int i = 0; i < windowsY.length; i++){
                this.windowsY[i] = windowsY[i];
            }
            return this;
        }
        
        public LevelBuilder setWindowsHeight(float... windowsHeight) {
            this.windowsHeight = new float[windowsHeight.length];
            for (int i = 0; i < windowsHeight.length; i++){
                this.windowsHeight[i] = windowsHeight[i];
            }
            return this;
        }
        
        public LevelBuilder setWindowsQuantityOfLines(int... windowsQuantityOfLines) {
            this.windowsQuantityOfLines = new int[windowsQuantityOfLines.length];
            for (int i = 0; i < windowsQuantityOfLines.length; i++){
                this.windowsQuantityOfLines[i] = windowsQuantityOfLines[i];
            }
            return this;
        }

        public LevelBuilder setWindowsDistance(float... windowsDistance) {
            this.windowsDistance = new float[windowsDistance.length];
            for (int i = 0; i < windowsDistance.length; i++){
                this.windowsDistance[i] = windowsDistance[i];
            }
            return this;
        }
        
        public LevelBuilder setWindowsVelocity(float... windowsVelocity) {
            this.windowsVelocity = new float[windowsVelocity.length];
            for (int i = 0; i < windowsVelocity.length; i++){
                this.windowsVelocity[i] = windowsVelocity[i];
            }
            return this;
        }

        public Level build() {
            return new Level();
        }
    }
}
