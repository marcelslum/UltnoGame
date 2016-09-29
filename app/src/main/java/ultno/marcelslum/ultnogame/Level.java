package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Level {

    public static final int WIND_TYPE_NO = 0;
    public static final int WIND_TYPE_RIGHT = 1;
    public static final int WIND_TYPE_LEFT = 2;
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
    public ScaleVariationDataBuilder[] barsScaleVariationData;
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
    public ScaleVariationDataBuilder[] obstaclesScaleVariationData;
    public PositionVariationDataBuilder[] obstaclesPositionVariationData;
    public int windowsQuantity;
    public float[] windowsY;
    public float[] windowsHeight;
    public int[] windowsQuantityOfLines;
    public float[] windowsDistance;
    public float[] windowsVelocity;
    public boolean isHaveSpecialBall = true;
    public float specialBallPercentage = 0.0f;
    public float windType;

    private Level(){
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
        this.barsScaleVariationData = LevelBuilder.barsScaleVariationData;
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
        this.obstaclesScaleVariationData = LevelBuilder.obstaclesScaleVariationData;
        this.obstaclesPositionVariationData = LevelBuilder.obstaclesPositionVariationData;
        this.windowsQuantity = LevelBuilder.windowsQuantity;
        this.windowsY = LevelBuilder.windowsY;
        this.windowsHeight = LevelBuilder.windowsHeight;
        this.windowsQuantityOfLines = LevelBuilder.windowsQuantityOfLines;
        this.windowsDistance = LevelBuilder.windowsDistance;
        this.windowsVelocity = LevelBuilder.windowsVelocity;
        this.specialBallPercentage = LevelBuilder.specialBallPercentage;
        this.windType = LevelBuilder.windType;
        this.tutorials = new ArrayList<>();

    }
    
    public void showFirstTutorial(){
        Game.blockAndWaitTouchRelease();
        this.showingTutorial = 0;
        this.tutorials.get(0).textBox.alpha = 0f;
        this.tutorials.get(0).show(Sound.soundTextBoxAppear, Game.volume);
    }

    public void nextTutorial(){
        Log.e("level", "nextTutorial");
        Log.e("level", "showingTutorial "+showingTutorial);
        final Level innerLevel = this;
        Game.blockAndWaitTouchRelease();
        if (this.tutorials.get(this.showingTutorial).isBlocked == false){
            Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
            if (showingTutorial + 1 == this.tutorials.size()){

                Log.e("level", "ultimo tutorail, setando preparar");

                this.tutorials.get(this.showingTutorial).setOnUnshowAfterAnim2(new Tutorial.OnUnshowAfterAnim2() {
                    @Override
                    public void onUnshowAfterAnim2() {
                        Game.setGameState(Game.GAME_STATE_PREPARAR);
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
                        innerLevel.tutorials.get(innerLevel.showingTutorial).show(Sound.soundTextBoxAppear, Game.volume);
                    }
                });
                tutorials.get(showingTutorial).unshow();

                //Log.e("level", "showingTutorial depois"+showingTutorial);
            }
        }
    }

    public void loadEntities() {
        Game.eraseAllGameEntities();
        Game.quad = new Quadtree(new RectangleM(0,0,Game.gameAreaResolutionX,Game.gameAreaResolutionY),5,5);


        Game.scorePanel = new ScorePanel("scorePanel",
                Game.gameAreaResolutionX * 0.5f, Game.gameAreaResolutionY * 1.05f, Game.resolutionY * 0.08f);

        Game.objectivePanel = new ObjectivePanel("objectivePanel", this.game,
                Game.gameAreaResolutionX * 0.5f, Game.gameAreaResolutionY * 1.005f, Game.resolutionY * 0.027f);
        Game.objectivePanel.alpha = 0.9f;



        // escolhe o background de acordo com o número do nível
        int back;
        if (Game.levelNumber < 9) {
            back = Game.levelNumber;
        } else {
            back = Game.levelNumber % 9;
        }

        Game.background = new Background("background", 0, 0, Game.gameAreaResolutionX,Game.resolutionY, back);

        if (windType == Level.WIND_TYPE_NO){
            Game.wind = null;
        } else if (windType == Level.WIND_TYPE_RIGHT){
            Game.wind = new Wind("wind", 0f, 0f, Game.gameAreaResolutionY, true);
        } else if (windType == Level.WIND_TYPE_LEFT){
            Game.wind = new Wind("wind", 0f, 0f, Game.gameAreaResolutionY, false);
        }

        Game.bordaB.y = Game.gameAreaResolutionY-2;
        Game.bordaB.clearAnimations();

        //Log.e("Level loadEnt", "1");
        float y = Game.resolutionY * 0.86f;
        float buttonSize = Game.resolutionY * 0.13f;

        // BOTÃO 1 ESQUERDA
        float x = Game.resolutionX * 0.03f;
        Game.button1Left = new Button("button1Left", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        Game.button1Left.setTextureMap(19);
        Game.button1Left.textureMapUnpressed = 19;
        Game.button1Left.textureMapPressed = 18;
        Game.button1Left.alpha = 0.7f;

        // BOTÃO 2 DIREITA
        x = Game.resolutionX * 0.87f;
        Game.button2Right = new Button("buttonRight", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        Game.button2Right.setTextureMap(20);
        Game.button2Right.textureMapUnpressed = 20;
        Game.button2Right.textureMapPressed = 17;

        if (this.barsQuantity > 1) {
            // BOTÃO 1 DIREITA
            x = Game.resolutionX * 0.18f;
            Game.button1Right = new Button("button1Right",x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
            Game.button1Right.setTextureMap(20);
            Game.button1Right.textureMapUnpressed = 20;
            Game.button1Right.textureMapPressed = 17;
            Game.button1Right.alpha = 0.7f;

            // BOTÃO 2 ESQUERDA
            x = Game.resolutionX * 0.72f;
            Game.button2Left = new Button("button2Left", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
            Game.button2Left.setTextureMap(19);
            Game.button2Left.textureMapUnpressed = 19;
            Game.button2Left.textureMapPressed = 18;
        }

        // BOTÃO SOM
        Game.buttonSound = new ButtonOnOff("buttonSound", Game.gameAreaResolutionX * 0.34f,
                Game.resolutionY * 0.89f, Game.resolutionY * 0.06f, Game.resolutionY * 0.06f, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        Game.buttonSound.textureMapUnpressed = 9;
        Game.buttonSound.textureMapPressed = 10;
        Game.buttonSound.getListener().x = Game.gameAreaResolutionX * 0.32f;
        Game.buttonSound.getListener().y = Game.resolutionY * 0.86f;
        Game.buttonSound.getListener().width = Game.gameAreaResolutionX * 0.12f;
        Game.buttonSound.getListener().height = Game.resolutionY * 0.12f;

        Game.buttonSound.alpha = 0.5f;
        if (Game.menuVolume == 0 || Game.volume == 0) {
            Game.buttonSound.setOff();
        } else {
            Game.buttonSound.setOn();
        }

        Game.buttonSound.setOnOffBehavior(new ButtonOnOff.OnOffBehavior() {
            @Override
            public void onBehavior() {
                Game.volume = 100;
            }

            @Override
            public void offBehavior() {
                Game.volume = 0;
            }
        });

        // BOTÃO MUSICA
        Game.buttonMusic = new ButtonOnOff("buttonMusic",Game.gameAreaResolutionX * 0.61f,
                Game.resolutionY * 0.89f, Game.resolutionY * 0.06f, Game.resolutionY * 0.06f, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        Game.buttonMusic.textureMapUnpressed = 2;
        Game.buttonMusic.textureMapPressed = 1;
        Game.buttonMusic.alpha = 0.5f;
        Game.buttonMusic.getListener().x = Game.gameAreaResolutionX * 0.58f;
        Game.buttonMusic.getListener().y = Game.resolutionY * 0.86f;
        Game.buttonMusic.getListener().width = Game.gameAreaResolutionX * 0.12f;
        Game.buttonMusic.getListener().height = Game.resolutionY * 0.12f;
        if (!Game.musicOn || Game.menuVolume == 0) {
            Game.buttonMusic.setOff();
        } else {
            Game.buttonMusic.setOn();
        }

        Game.buttonMusic.setOnOffBehavior(new ButtonOnOff.OnOffBehavior() {
            @Override
            public void onBehavior() {
                Game.musicOn = true;
                if (Game.menuVolume == 0) {
                    Game.volume = 50;
                    Game.menuVolume = 50;
                }
                if (Sound.music != null){
                    Sound.music.setVolume(0.006f* (float) 50, 0.006f* (float) 50);
                    Sound.music.start();
                }
            }

            @Override
            public void offBehavior() {
                Game.musicOn = false;
                if (Sound.music != null){
                    Sound.music.pause();
                }
            }
        });

        InteractionListener gameAreaInteractionListener = new InteractionListener("gameArea111", 0f, 0f,
                Game.gameAreaResolutionX, Game.gameAreaResolutionY * 0.8f, 0, Game.background);

        gameAreaInteractionListener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (Game.gameState == Game.GAME_STATE_JOGAR){
                    Log.e("level", "listener pause ativado");
                    Game.blockAndWaitTouchRelease();
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                }
            }

            @Override
            public void onUnpress(){
            }
        });
        Game.addInteracionListener(gameAreaInteractionListener);

        for (int i = 0; i < this.barsQuantity; i++){

            float barX = Game.gameAreaResolutionX * barsX_BR[i];
            float barY = Game.gameAreaResolutionY - (Game.gameAreaResolutionY * barsY_BR[i]);

            float barWidth = Game.gameAreaResolutionX * barsWidth_BR[i];
            float barHeight = Game.gameAreaResolutionY * barsHeight_BR[i];

            float barVelocityX = Game.gameAreaResolutionX * barsVX_BR[i] * Game.difficultyVelocityBarMultiplicator;
            float barVelocityY = Game.gameAreaResolutionY * barsVY_BR[i] * Game.difficultyVelocityBarMultiplicator;

            //Game.barsDesiredVelocityX[i] = barVelocityX;
            //Game.barsDesiredVelocityY[i] = barVelocityY;
            //Game.barsInitialPositionX[i] = barX;
            //Game.barsInitialPositionY[i] = barY;

            Bar bar = new Bar("bar", barX, barY, barWidth, barHeight);
            Game.addBar(bar);

            bar.initialX = barX;
            bar.initialY = barY;
            bar.initialDesireVelocityX = barVelocityX;
            bar.dvx = barVelocityX;
            if (barsScaleVariationData != null) {
                bar.setScaleVariation(barsScaleVariationData[i]);
            }
        }

        float targetWidth = Game.gameAreaResolutionX * Game.levelObject.targetWidth_BR;
        float targetHeight = Game.gameAreaResolutionY * Game.levelObject.targetHeight_BR;
        float targetX;
        float targetY;

        Log.e("level", "targetsMap.length "+targetsMap.length);
        for (int iY = 0; iY < targetsMap.length;iY++){
            for (int iX = 0; iX < targetsMap[iY].length; iX++) {
                if (targetsMap[iY][iX] != 0) {
                    targetX = (Game.gameAreaResolutionX * targetsPadding_BR) +
                            (iX * ((Game.gameAreaResolutionX * targetWidth_BR) +
                                    (Game.gameAreaResolutionX * targetsDistance_BR)));
                    targetY = (Game.gameAreaResolutionX * targetsPadding_BR) +
                            (iY * ((Game.gameAreaResolutionY * targetHeight_BR) +
                                    (Game.gameAreaResolutionX * targetsDistance_BR)));

                    Game.addTarget(
                            new TargetBuilder()
                                    .name("target")
                                    .game(game)
                                    .x(targetX)
                                    .y(targetY)
                                    .width(targetWidth)
                                    .height(targetHeight)
                                    .weight(Game.TARGET_WEIGHT)
                                    .type(targetsMap[iY][iX])
                                    .states(targetsStates)
                                    .build()
                    );
                }
            }
        }

        // adiciona os obstáculos
        for (int i = 0; i < this.obstaclesQuantity; i++){
            float obstacleX = Game.gameAreaResolutionX * obstaclesX_BR[i];
            float obstacleY = Game.gameAreaResolutionY * this.obstaclesY_BR[i];
            float obstacleWidth = Game.gameAreaResolutionX * obstaclesWidth_BR[i];
            float obstacleHeight = Game.gameAreaResolutionY * obstaclesHeight_BR[i];
            Obstacle obstacle = new Obstacle("obstacle", obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            obstacle.addBorder(0.05f, Game.gameAreaResolutionX * 0.003f, Game.gameAreaResolutionX * 0.003f, new Color(0.6f, 0.605f, 0.6f, 1.0f));
            if (obstaclesScaleVariationData != null) {
                //Log.e("level", "setting obstacle scale variation data");
                if (obstaclesScaleVariationData.length > i) {
                    obstacle.setScaleVariation(obstaclesScaleVariationData[i]);
                    obstacle.scaleVariationData.heightVelocity *= Game.difficultyVelocityObstacleMultiplicator;
                    obstacle.scaleVariationData.heightVelocity *= Game.difficultyVelocityObstacleMultiplicator;
                }
            }
            if (obstaclesPositionVariationData != null) {
                //Log.e("level", "setting obstacle scale variation data");
                if (obstaclesPositionVariationData.length > i) {
                    obstacle.setPositionVariation(obstaclesPositionVariationData[i]);
                    obstacle.positionVariationData.xVelocity *= Game.difficultyVelocityObstacleMultiplicator;
                    obstacle.positionVariationData.yVelocity *= Game.difficultyVelocityObstacleMultiplicator;
                }
            }
            Game.addObstacle(obstacle);
        }
        
        for (int i = 0; i < this.windowsQuantity; i++){
            float wY = Game.gameAreaResolutionY * windowsY[i];
            float wHeight = Game.gameAreaResolutionY * windowsHeight[i];
            float wVelocity = Game.gameAreaResolutionX * windowsVelocity[i];
            
            Game.addWindow(new WindowGame("windows", wY, windowsQuantityOfLines[i], wHeight, windowsDistance[i], wVelocity));
        }
        
        int quantityOfSpecialTargets = 0;
        for (int i = 0; i < Game.targets.size(); i++){
            if (Game.targets.get(i).special == 1)
                quantityOfSpecialTargets += 1;
        }

        int numberOfBallsInvencible = 0;
        for (int i = 0; i < this.ballsQuantity; i++){
            int ic = i; if (i > this.ballsX_BR.length - 1) {ic = 0;}
            float ballX = Game.gameAreaResolutionX * this.ballsX_BR[ic];
            ic = i; if (i > this.ballsY_BR.length - 1) {ic = 0;}
            float ballY = Game.gameAreaResolutionY * this.ballsY_BR[ic];

            ic = i; if (i > this.ballsRadius_BR.length - 1) {ic = 0;}
            float radius = Game.gameAreaResolutionY * this.ballsRadius_BR[ic];

            ic = i; if (i > this.ballsVX_BR.length - 1) {ic = 0;}
            float ballVelocityX = Game.gameAreaResolutionX * this.ballsVX_BR[ic] * Game.difficultyVelocityBallMultiplicator;

            ic = i; if (i > this.ballsVY_BR.length - 1) {ic = 0;}
            float ballVelocityY = Game.gameAreaResolutionY * this.ballsVY_BR[ic] * Game.difficultyVelocityBallMultiplicator;

            ic = i; if (i > this.ballsInvencible.length - 1) {ic = 0;}
            if (this.ballsInvencible[ic]){
                numberOfBallsInvencible += 1;
            }

            ic = i; if (i > this.ballsTextureMap.length - 1) {ic = 0;}
            Ball ball = new Ball("ball", ballX, ballY, radius, this.ballsTextureMap[ic]);

            ic = i; if (i > this.ballsAngleToRotate.length - 1) {ic = 0;}
            ball.angleToRotate = this.ballsAngleToRotate[ic];
            ic = i; if (i > this.ballsVelocityVariation.length - 1) {ic = 0;}
            ball.velocityVariation = this.ballsVelocityVariation[ic];

            ic = i; if (i > this.ballsVelocityMax_BI.length - 1) {ic = 0;}
            ball.velocityMax_BI = ballsVelocityMax_BI[ic];
            ic = i; if (i > this.ballsVelocityMin_BI.length - 1) {ic = 0;}
            ball.velocityMin_BI = ballsVelocityMin_BI[ic];

            ic = i; if (i > this.ballsMaxAngle.length - 1) {ic = 0;}
            ball.maxAngle = this.ballsMaxAngle[ic];
            ic = i; if (i > this.ballsMinAngle.length - 1) {ic = 0;}
            ball.minAngle = this.ballsMinAngle[ic];

            ball.initialDesireVelocityX = ballVelocityX;
            ball.initialDesireVelocityY = ballVelocityY;

            ball.initialX = ballX;
            ball.initialY = ballY;

            ball.dvx = ballVelocityX;
            ball.dvy = ballVelocityY;

            ic = i; if (i > this.ballsTargetsAppend.size() - 1) {ic = 0;}
            for (int t = 0; t < this.ballsTargetsAppend.size(); t++){
                ball.targetsAppend = this.ballsTargetsAppend.get(ic);
            }

            ic = i; if (i > this.ballsFree.length - 1) {ic = 0;}
            ball.isFree = this.ballsFree[ic];

            ic = i; if (i > this.ballsInvencible.length - 1) {ic = 0;}
            if (this.ballsInvencible[ic]){
                ball.setInvencible();
            }

            Game.addBall(ball);

        }
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
        private static ScaleVariationDataBuilder[] barsScaleVariationData;
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
        private static ScaleVariationDataBuilder[] obstaclesScaleVariationData;
        public static PositionVariationDataBuilder[] obstaclesPositionVariationData;
        private static int windowsQuantity;
        private static float[] windowsY;
        private static float[] windowsHeight;
        private static int[] windowsQuantityOfLines;
        private static float[] windowsDistance;
        private static float[] windowsVelocity;
        private static float specialBallPercentage;
        private static int windType = Level.WIND_TYPE_NO;

        public LevelBuilder setBallsQuantity(int _ballsQuantity) {
            ballsQuantity = _ballsQuantity;
            return this;
        }

        public LevelBuilder setBallsAlive(int _minBallsAlive) {
            minBallsAlive = _minBallsAlive;
            return this;
        }

        public LevelBuilder setBallsRadius_BD_0_01(float... _ballsRadius_BR) {
            ballsRadius_BR = new float[_ballsRadius_BR.length];
            Log.e("level", "ballsRadius_BR"+ballsRadius_BR[0]);
            for (int i = 0; i < _ballsRadius_BR.length; i++){
                ballsRadius_BR[i] = _ballsRadius_BR[i] * default_ballsRadius_BR;
            }
            return this;
        }

        public LevelBuilder setBallsX_B1(float... _ballsInitialX_BR) {
            ballsX_BR = new float[_ballsInitialX_BR.length];
            for (int i = 0; i < _ballsInitialX_BR.length; i++){
                ballsX_BR[i] = _ballsInitialX_BR[i] * default_ballsX_BR;
            }
            return this;
        }

        public LevelBuilder setBallsY_B1(float... _ballsY_BR) {
            ballsY_BR = new float[_ballsY_BR.length];
            for (int i = 0; i < _ballsY_BR.length; i++){
                ballsY_BR[i] = _ballsY_BR[i] * default_ballsY_BR;
            }
            return this;
        }


        public LevelBuilder setBallsVX(float... _ballsDesiredVelocityX_BR) {
            ballsVX_BR = new float[_ballsDesiredVelocityX_BR.length];
            for (int i = 0; i < _ballsDesiredVelocityX_BR.length; i++){
                ballsVX_BR[i] = _ballsDesiredVelocityX_BR[i] * default_ballsVX_BR;
            }
            return this;
        }

        public LevelBuilder setBallsVY(float... _ballsDesiredVelocityY_BR) {
            ballsVY_BR = new float[_ballsDesiredVelocityY_BR.length];
            for (int i = 0; i < _ballsDesiredVelocityY_BR.length; i++){
                ballsVY_BR[i] = _ballsDesiredVelocityY_BR[i] * default_ballsVY_BR;
            }
            return this;
        }

        public LevelBuilder setBallsTextureMap(int... _ballsTextureMap) {
            ballsTextureMap = new int[_ballsTextureMap.length];
            System.arraycopy(_ballsTextureMap, 0, ballsTextureMap, 0, _ballsTextureMap.length);
            return this;
        }

        public LevelBuilder setBallsInvencible(boolean... _ballsInvencible) {
            ballsInvencible = new boolean[_ballsInvencible.length];
            System.arraycopy(_ballsInvencible, 0, ballsInvencible, 0, _ballsInvencible.length);
            return this;
        }

        public LevelBuilder setBallsAngleToRotate_BD_2(float... _ballsAngleToRotate) {
            ballsAngleToRotate = new float[_ballsAngleToRotate.length];
            for (int i = 0; i < _ballsAngleToRotate.length; i++){
                ballsAngleToRotate[i] = _ballsAngleToRotate[i] * default_ballsAngleToRotate;
            }
            return this;
        }

        public LevelBuilder setBallsMaxAngle_BD_55(float... _ballsMaxAngle) {
            ballsMaxAngle = new float[_ballsMaxAngle.length];
            for (int i = 0; i < _ballsMaxAngle.length; i++){
                ballsMaxAngle[i] = _ballsMaxAngle[i] * default_ballsMaxAngle;
            }
            return this;
        }

        public LevelBuilder setBallsMinAngle_BD_35(float... _ballsMinAngle) {
            ballsMinAngle = new float[_ballsMinAngle.length];
            for (int i = 0; i < _ballsMinAngle.length; i++){
                ballsMinAngle[i] = _ballsMinAngle[i] * default_ballsMinAngle;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityVariation_BD_0_1(float... _ballsVelocityVariation) {
            ballsVelocityVariation = new float[_ballsVelocityVariation.length];
            for (int i = 0; i < _ballsVelocityVariation.length; i++){
                ballsVelocityVariation[i] = _ballsVelocityVariation[i] * default_ballsVelocityVariation;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMax_BD_1_5(float... _ballsVelocityMax_BI) {
            ballsVelocityMax_BI = new float[_ballsVelocityMax_BI.length];
            for (int i = 0; i < _ballsVelocityMax_BI.length; i++){
                ballsVelocityMax_BI[i] = _ballsVelocityMax_BI[i] * default_ballsVelocityMax_BI;
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMin_BD_0_8(float... _ballsVelocityMin_BI) {
            ballsVelocityMin_BI = new float[_ballsVelocityMin_BI.length];
            for (int i = 0; i < _ballsVelocityMin_BI.length; i++){
                ballsVelocityMin_BI[i] = _ballsVelocityMin_BI[i] * default_ballsVelocityMin_BI;
            }
            return this;
        }

        public LevelBuilder setBallsTargetsAppend(ArrayList<ArrayList<Target>> _ballsTargetsAppend) {
            ballsTargetsAppend = _ballsTargetsAppend;
            return this;
        }

        public LevelBuilder setBallsFree(boolean... _ballsFree) {
            ballsFree = new boolean[_ballsFree.length];
            System.arraycopy(_ballsFree, 0, ballsFree, 0, _ballsFree.length);
            return this;
        }

        public LevelBuilder setBarsQuantity(int _barsQuantity) {
            barsQuantity = _barsQuantity;
            return this;
        }

        public LevelBuilder setBarsWidth_BD_0_22(float... _barsWidth_BR) {
            barsWidth_BR = new float[_barsWidth_BR.length];
            for (int i = 0; i < _barsWidth_BR.length; i++){
                barsWidth_BR[i] = _barsWidth_BR[i] * default_barsWidth_BR;
            }
            return this;
        }

        public LevelBuilder setBarsHeight_BD_0_0175(float... _barsHeight_BR){
            barsHeight_BR = new float[_barsHeight_BR.length];
            for (int i = 0; i < _barsHeight_BR.length; i++){
                barsHeight_BR[i] = _barsHeight_BR[i] * default_barsHeight_BR;
            }
            return this;
        }

        public LevelBuilder setBarsX_B1(float... _barsX_BR) {
            barsX_BR = new float[_barsX_BR.length];
            System.arraycopy(_barsX_BR, 0, barsX_BR, 0, _barsX_BR.length);
            return this;
        }

        public LevelBuilder setBarsY_BD_0_024(float... _barsY_BR) {
            barsY_BR = new float[_barsY_BR.length];
            for (int i = 0; i < _barsY_BR.length; i++){
                barsY_BR[i] = _barsY_BR[i] * default_barsY_BR;
            }
            return this;
        }

        public LevelBuilder setBarsVX_BD_0_0045(float... _barsVX_BR) {
            barsVX_BR = new float[_barsVX_BR.length];
            for (int i = 0; i < _barsVX_BR.length; i++){
                barsVX_BR[i] = _barsVX_BR[i] * default_barsVX_BR;
            }
            return this;
        }

        public LevelBuilder setBarsVY(float... _barsVY_BR) {
            barsVY_BR = new float[_barsVY_BR.length];
            System.arraycopy(_barsVY_BR, 0, barsVY_BR, 0, _barsVY_BR.length);
            return this;
        }

        public LevelBuilder setBarsScaleVariation(ScaleVariationDataBuilder... _data){
            barsScaleVariationData = new ScaleVariationDataBuilder[_data.length];
            System.arraycopy(_data, 0, barsScaleVariationData, 0, _data.length);
            return this;
        }

        public LevelBuilder setBarsScaleVariationOff(){
            barsScaleVariationData = null;
            return this;
        }

        public LevelBuilder setTargetsWidth(float _targetsWidht_BR) {
            targetsWidht_BR = _targetsWidht_BR;
            return this;
        }

        public LevelBuilder setTargetsHeight(float _targetsHeight_BR) {
            targetsHeight_BR = _targetsHeight_BR;
            return this;
        }

        public LevelBuilder setTargetsMap(int [][] _targetsMap) {
            targetsMap = _targetsMap;
            return this;
        }

        public LevelBuilder setTargetsStates(int [] _targetsStates) {
            targetsStates = _targetsStates;
            return this;
        }

        public LevelBuilder setTargetsDistance(float _targetsDistance_BXR) {
            targetsDistance_BXR = _targetsDistance_BXR;
            return this;
        }

        public LevelBuilder setTargetsPadding(float _targetsPadding_BXR) {
            targetsPadding_BXR = _targetsPadding_BXR;
            return this;
        }

        public LevelBuilder setObstaclesQuantity(int _obstaclesQuantity) {
            obstaclesQuantity = _obstaclesQuantity;
            return this;
        }

        public LevelBuilder setObstaclesWidth(float... _obstaclesWidth_BR) {
            obstaclesWidth_BR = new float[_obstaclesWidth_BR.length];
            System.arraycopy(_obstaclesWidth_BR, 0, obstaclesWidth_BR, 0, _obstaclesWidth_BR.length);
            return this;
        }

        public LevelBuilder setObstaclesHeight(float... _obstaclesHeight_BR) {
            obstaclesHeight_BR = new float[_obstaclesHeight_BR.length];
            System.arraycopy(_obstaclesHeight_BR, 0, obstaclesHeight_BR, 0, _obstaclesHeight_BR.length);
            return this;
        }

        public LevelBuilder setObstaclesX(float... _obstaclesX_BR) {
            obstaclesX_BR = new float[_obstaclesX_BR.length];
            System.arraycopy(_obstaclesX_BR, 0, obstaclesX_BR, 0, _obstaclesX_BR.length);
            return this;
        }

        public LevelBuilder setObstaclesY(float... _obstaclesY_BR) {
            obstaclesY_BR = new float[_obstaclesY_BR.length];
            System.arraycopy(_obstaclesY_BR, 0, obstaclesY_BR, 0, _obstaclesY_BR.length);
            return this;
        }

        public LevelBuilder setObstaclesScaleVariation(ScaleVariationDataBuilder... _data){
            obstaclesScaleVariationData = new ScaleVariationDataBuilder[_data.length];
            System.arraycopy(_data, 0, obstaclesScaleVariationData, 0, _data.length);
            return this;
        }

        public LevelBuilder setObstaclesScaleVariationOff(){
            obstaclesScaleVariationData = null;
            return this;
        }

        public LevelBuilder setObstaclesPositionVariation(PositionVariationDataBuilder... _data){
            obstaclesPositionVariationData = new PositionVariationDataBuilder[_data.length];
            System.arraycopy(_data, 0, obstaclesPositionVariationData, 0, _data.length);
            return this;
        }

        public LevelBuilder setObstaclesPositionVariationOff(){
            obstaclesPositionVariationData = null;
            return this;
        }
        
        public LevelBuilder setWindowsQuantity(int _windowsQuantity) {
            windowsQuantity = _windowsQuantity;
            return this;
        }
        
        public LevelBuilder setWindowsY(float... _windowsY) {
            windowsY = new float[_windowsY.length];
            System.arraycopy(_windowsY, 0, windowsY, 0, _windowsY.length);
            return this;
        }
        
        public LevelBuilder setWindowsHeight(float... _windowsHeight) {
            windowsHeight = new float[_windowsHeight.length];
            System.arraycopy(_windowsHeight, 0, windowsHeight, 0, _windowsHeight.length);
            return this;
        }
        
        public LevelBuilder setWindowsQuantityOfLines(int... _windowsQuantityOfLines) {
            windowsQuantityOfLines = new int[_windowsQuantityOfLines.length];
            System.arraycopy(_windowsQuantityOfLines, 0, windowsQuantityOfLines, 0, _windowsQuantityOfLines.length);
            return this;
        }

        public LevelBuilder setWindowsDistance(float... _windowsDistance) {
            windowsDistance = new float[_windowsDistance.length];
            System.arraycopy(_windowsDistance, 0, windowsDistance, 0, _windowsDistance.length);
            return this;
        }
        
        public LevelBuilder setWindowsVelocity(float... _windowsVelocity) {
            windowsVelocity = new float[_windowsVelocity.length];
            System.arraycopy(_windowsVelocity, 0, windowsVelocity, 0, _windowsVelocity.length);
            return this;
        }

        public LevelBuilder setSpecialBallPercentage0_1(float percentage){
            specialBallPercentage = percentage;
            return this;
        }

        public LevelBuilder setWindType(int type){
            windType = type;
            return this;
        }

        public Level build() {
            return new Level();
        }
    }
}
