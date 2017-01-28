package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Level {

    public static Game game;
    public int tutorialAttached;
    public static final int WIND_TYPE_NO = 0;
    public static final int WIND_TYPE_RIGHT = 1;
    public static final int WIND_TYPE_LEFT = 2;
    public int ballsQuantity;
    public int minBallsAlive;
    public float[] ballsRadius;
    public float[] ballsX;
    public float[] ballsY;
    public float[] ballsVX;
    public float[] ballsVY;
    public int [] ballsTextureMap;
    public boolean[] ballsInvencible;
    public float[] ballsAngleToRotate;
    public float[] ballsMaxAngle;
    public float[] ballsMinAngle;
    public float[] ballsVelocityVariation;
    public float[] ballsMaxVel;
    public float[] ballsMinVel;
    public ArrayList<int[]> ballsTargetsAppend;
    public boolean[] ballsFree;
    public int barsQuantity;
    public float[] barsWidth;
    public float[] barsHeight;
    public float[] barsX;
    public float[] barsY;
    public float[] barsVX;
    public float[] barsVY;
    public ScaleVariationDataBuilder[] barsScaleVariationData;
    public float targetWidth;
    public float targetHeight;
    public float targetDistance;
    public float targetPadding;
    public int [][] targetsMap;
    public int [] targetsStates;
    public int obstaclesQuantity;
    public float[] obstaclesWidth;
    public float[] obstaclesHeight;
    public float[] obstaclesX;
    public float[] obstaclesY;
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


    public static Level levelObject;
    public static LevelGoals levelGoalsObject;

    public static final int maxNumberOfLevels = 100;

    private Level(){
        ballsQuantity = LevelBuilder.ballsQuantity;
        minBallsAlive = LevelBuilder.minBallsAlive;
        ballsRadius = LevelBuilder.ballsRadius;
        ballsX = LevelBuilder.ballsX;
        ballsY = LevelBuilder.ballsY;
        ballsVX = LevelBuilder.ballsVX;
        ballsVY = LevelBuilder.ballsVY;
        ballsTextureMap = LevelBuilder.ballsTextureMap;
        ballsInvencible = LevelBuilder.ballsInvencible;
        ballsAngleToRotate = LevelBuilder.ballsAngleToRotate;
        ballsMaxAngle = LevelBuilder.ballsMaxAngle;
        ballsMinAngle = LevelBuilder.ballsMinAngle;
        ballsVelocityVariation = LevelBuilder.ballsVelocityVariation;
        ballsMaxVel = LevelBuilder.ballsMaxVel;
        ballsMinVel = LevelBuilder.ballsMinVel;
        ballsTargetsAppend = LevelBuilder.ballsTargetsAppend;
        ballsFree = LevelBuilder.ballsFree;
        barsQuantity = LevelBuilder.barsQuantity;
        barsWidth = LevelBuilder.barsWidth;
        barsHeight = LevelBuilder.barsHeight;
        barsX = LevelBuilder.barsX;
        barsY = LevelBuilder.barsY;
        barsVX = LevelBuilder.barsVX;
        barsVY = LevelBuilder.barsVY;
        barsScaleVariationData = LevelBuilder.barsScaleVariationData;
        targetWidth = LevelBuilder.targetsWidht;
        targetHeight = LevelBuilder.targetsHeight;
        targetDistance = LevelBuilder.targetsDistance;
        targetPadding = LevelBuilder.targetsPadding;
        targetsMap = LevelBuilder.targetsMap;
        targetsStates = LevelBuilder.targetsStates;
        obstaclesQuantity = LevelBuilder.obstaclesQuantity;
        obstaclesWidth = LevelBuilder.obstaclesWidth;
        obstaclesHeight = LevelBuilder.obstaclesHeight;
        obstaclesX = LevelBuilder.obstaclesX;
        obstaclesY = LevelBuilder.obstaclesY;
        obstaclesScaleVariationData = LevelBuilder.obstaclesScaleVariationData;
        obstaclesPositionVariationData = LevelBuilder.obstaclesPositionVariationData;
        windowsQuantity = LevelBuilder.windowsQuantity;
        windowsY = LevelBuilder.windowsY;
        windowsHeight = LevelBuilder.windowsHeight;
        windowsQuantityOfLines = LevelBuilder.windowsQuantityOfLines;
        windowsDistance = LevelBuilder.windowsDistance;
        windowsVelocity = LevelBuilder.windowsVelocity;
        specialBallPercentage = LevelBuilder.specialBallPercentage;
        windType = LevelBuilder.windType;
        tutorialAttached = LevelBuilder.tutorialAttached;
    }

    public void loadEntities() {
        Game.eraseAllGameEntities();
        Game.quad = new Quadtree(new RectangleM(0,0,Game.gameAreaResolutionX,Game.gameAreaResolutionY),5,5);

        MessagesHandler.createMessageTime();
        ScoreHandler.createScorePanel();

        Game.ballDataPanel = new BallDataPanel("ballDataPanel",
            ScoreHandler.getScorePanelX(),
            Game.gameAreaResolutionY * 1.05f + Game.resolutionY * 0.08f,
            ScoreHandler.getScorePanelWidth(),
            Game.resolutionY * 0.0175f);

        Game.ballGoalsPanel = new BallGoalsPanel("ballGoalsPanel", this.game,
                Game.gameAreaResolutionX * 0.5f, Game.gameAreaResolutionY * 1.005f, Game.resolutionY * 0.027f);
        Game.ballGoalsPanel.alpha = 0.9f;

        // escolhe o background de acordo com o número do nível
        int back;
        if (SaveGame.saveGame.currentLevelNumber < 9) {
            back = SaveGame.saveGame.currentLevelNumber;
        } else {
            back = SaveGame.saveGame.currentLevelNumber % 9;
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

        ButtonHandler.createGameButtons(barsQuantity);

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

            float barX = Game.gameAreaResolutionX * barsX[i];
            float barY = Game.gameAreaResolutionY - (Game.gameAreaResolutionY * barsY[i]);

            float barWidth = Game.gameAreaResolutionX * barsWidth[i];
            float barHeight = Game.gameAreaResolutionY * barsHeight[i];

            float barVelocityX = Game.gameAreaResolutionX * barsVX[i];

            Bar bar = new Bar("bar", barX, barY, barWidth, barHeight);
            Game.addBar(bar);

            bar.initialX = barX;
            bar.initialY = barY;
            bar.initialDVX = barVelocityX;
            bar.dvx = barVelocityX;
            if (barsScaleVariationData != null) {
                bar.setScaleVariation(barsScaleVariationData[i]);
            }
        }

        float targetWidth = Game.gameAreaResolutionX * levelObject.targetWidth;
        float targetHeight = Game.gameAreaResolutionY * levelObject.targetHeight;
        float targetX;
        float targetY;

        Log.e("level", "targetsMap.length "+targetsMap.length);
        int contador = 0;
        for (int iY = 0; iY < targetsMap.length;iY++){
            for (int iX = 0; iX < targetsMap[iY].length; iX++) {
                if (targetsMap[iY][iX] != 0) {
                    targetX = (Game.gameAreaResolutionX * targetPadding) +
                            (iX * ((Game.gameAreaResolutionX * this.targetWidth) +
                                    (Game.gameAreaResolutionX * targetDistance)));
                    targetY = (Game.gameAreaResolutionX * targetPadding) +
                            (iY * ((Game.gameAreaResolutionY * this.targetHeight) +
                                    (Game.gameAreaResolutionX * targetDistance)));

                    Target t = new TargetBuilder()
                            .name("target")
                            .game(game)
                            .x(targetX)
                            .y(targetY)
                            .width(targetWidth)
                            .height(targetHeight)
                            .weight(Game.TARGET_WEIGHT)
                            .type(targetsMap[iY][iX])
                            .states(targetsStates)
                            .build();

                    Log.e("Game", "target "+contador +": "+t.x + " " + t.y);

                    Game.addTarget(t);
                    contador += 1;
                }
            }
        }

        // adiciona os obstáculos
        for (int i = 0; i < this.obstaclesQuantity; i++){
            float obstacleX = Game.gameAreaResolutionX * obstaclesX[i];
            float obstacleY = Game.gameAreaResolutionY * this.obstaclesY[i];
            float obstacleWidth = Game.gameAreaResolutionX * obstaclesWidth[i];
            float obstacleHeight = Game.gameAreaResolutionY * obstaclesHeight[i];
            Obstacle obstacle = new Obstacle("obstacle", obstacleX, obstacleY, obstacleWidth, obstacleHeight);
            obstacle.addBorder(0.05f, Game.gameAreaResolutionX * 0.003f, Game.gameAreaResolutionX * 0.003f, new Color(0.6f, 0.605f, 0.6f, 1.0f));
            if (obstaclesScaleVariationData != null) {
                if (obstaclesScaleVariationData.length > i) {
                    if (obstaclesScaleVariationData[i] != null) {
                        obstacle.setScaleVariation(obstaclesScaleVariationData[i]);
                    }
                }
                obstacle.stopScaleVariation();
            }
            if (obstaclesPositionVariationData != null) {
                if (obstaclesPositionVariationData.length > i) {
                    if (obstaclesPositionVariationData[i] != null) {
                        obstacle.setPositionVariation(obstaclesPositionVariationData[i]);
                    }
                }
                obstacle.stopPositionVariation();
            }

            Game.addObstacle(obstacle);
        }
        
        for (int i = 0; i < this.windowsQuantity; i++){
            float wY = Game.gameAreaResolutionY * windowsY[i];
            float wHeight = Game.gameAreaResolutionY * windowsHeight[i];
            float wVelocity = Game.gameAreaResolutionX * windowsVelocity[i];
            
            Game.addWindow(new WindowGame("windows", wY, windowsQuantityOfLines[i], wHeight, windowsDistance[i], wVelocity));
        }

        for (int i = 0; i < this.ballsQuantity; i++){

            float ballX = Game.gameAreaResolutionX * this.ballsX[i];
            float ballY = Game.gameAreaResolutionY * this.ballsY[i];
            float radius = Game.gameAreaResolutionY * this.ballsRadius[i];
            float ballVelocityX = Game.gameAreaResolutionX * this.ballsVX[i];
            float ballVelocityY = Game.gameAreaResolutionY * this.ballsVY[i]; //* Game.difficultyVelocityBallMultiplicator;

            Ball ball = new Ball("ball", ballX, ballY, radius, this.ballsTextureMap[i]);
            ball.angleToRotate = this.ballsAngleToRotate[i];
            ball.velocityVariation = this.ballsVelocityVariation[i];
            ball.velocityMax_BI = ballsMaxVel[i];
            ball.velocityMin_BI = ballsMinVel[i];
            ball.maxAngle = this.ballsMaxAngle[i];
            ball.minAngle = this.ballsMinAngle[i];
            ball.initialDVX = ballVelocityX;
            ball.initialDVY = ballVelocityY;
            ball.initialX = ballX;
            ball.initialY = ballY;
            ball.dvx = ballVelocityX;
            ball.dvy = ballVelocityY;

            if (ball.targetsAppend == null){
                ball.targetsAppend = new ArrayList<>();
            } else {
                ball.targetsAppend.clear();
            }

            if (ballsTargetsAppend != null && ballsTargetsAppend.size() > 0) {
                for (int ta = 0; ta < ballsTargetsAppend.get(i).length; ta++) {
                    Log.e("Level", "adicionando target " + ballsTargetsAppend.get(i)[ta] + " à bola " + i);
                    Target t = game.targets.get(ballsTargetsAppend.get(i)[ta]);
                    Log.e("Level", t.x + " - " + t.y);
                    ball.targetsAppend.add(t);
                }
            }

            ball.isFree = this.ballsFree[i];

            if (this.ballsInvencible[i]){
                ball.setInvencible();
            }

            Game.addBall(ball);

        }
    }

    static public class LevelBuilder {
        private static int ballsQuantity = 1;
        private static int minBallsAlive = 1;
        private static float[] ballsRadius = new float[]{0f};
        private static float[] ballsX = new float[]{0f};
        private static float[] ballsY = new float[]{0f};
        private static float[] ballsVX = new float[]{0f};
        private static float[] ballsVY = new float[]{0f};
        private static int[] ballsTextureMap = new int []{Ball.COLOR_BALL_BLACK};
        private static boolean[] ballsInvencible = new boolean[]{false};
        private static float[] ballsAngleToRotate = new float[]{0f};
        private static float[] ballsMaxAngle = new float[]{0f};
        private static float[] ballsMinAngle = new float[]{0f};
        private static float[] ballsVelocityVariation = new float[]{0f};
        private static float[] ballsMaxVel = new float[]{0f};
        private static float[] ballsMinVel = new float[]{0f};
        private static ArrayList<int[]> ballsTargetsAppend = new ArrayList<int[]>();
        private static boolean[] ballsFree = new boolean[]{true};
        private static int barsQuantity = 1;
        private static float[] barsWidth = new float[]{0f};
        private static float[] barsHeight = new float[]{0f};
        private static float[] barsX = new float[]{0.3f};
        private static float[] barsY = new float[]{0f};
        private static float[] barsVX = new float[]{0f};
        private static float[] barsVY = new float[]{0f};
        private static ScaleVariationDataBuilder[] barsScaleVariationData;
        private static float targetsWidht = 0.1f;
        private static float targetsHeight = 0.1f;
        private static float targetsDistance = 0.01f;
        private static float targetsPadding = 0.01f;
        private static int [] targetsStates;
        private static int [][] targetsMap;
        private static int obstaclesQuantity = 0;
        private static float[] obstaclesWidth;
        private static float[] obstaclesHeight;
        private static float[] obstaclesX;
        private static float[] obstaclesY;
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
        private static ArrayList<LevelGoal> levelGoals;
        public static int tutorialAttached;

        public LevelBuilder setTutorialAttached(int v) {
            tutorialAttached = v;
            return this;
        }

        public LevelBuilder setBallsQuantity(int v) {
            ballsQuantity = v;
            return this;
        }

        public LevelBuilder setMinBallsAlive(int v) {
            minBallsAlive = v;
            return this;
        }

        public LevelBuilder setBallsRadius(float... v) {
            ballsRadius = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsRadius[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsX(float... v) {
            ballsX = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsX[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsY(float... v) {
            ballsY = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsY[i] = v[i];
            }
            return this;
        }


        public LevelBuilder setBallsVX(float... v) {
            ballsVX = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsVX[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsVY(float... v) {
            ballsVY = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsVY[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsTextureMap(int... v) {
            ballsTextureMap = new int[v.length];
            System.arraycopy(v, 0, ballsTextureMap, 0, v.length);
            return this;
        }

        public LevelBuilder setBallsInvencible(boolean... v) {
            ballsInvencible = new boolean[v.length];
            System.arraycopy(v, 0, ballsInvencible, 0, v.length);
            return this;
        }

        public LevelBuilder setBallsAngleToRotate(float... v) {
            ballsAngleToRotate = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsAngleToRotate[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsMaxAngle(float... v) {
            ballsMaxAngle = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsMaxAngle[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsMinAngle(float... v) {
            ballsMinAngle = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsMinAngle[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsVelocityVariation(float... v) {
            ballsVelocityVariation = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsVelocityVariation[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMax(float... v) {
            ballsMaxVel = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsMaxVel[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsVelocityMin(float... v) {
            ballsMinVel = new float[v.length];
            for (int i = 0; i < v.length; i++){
                ballsMinVel[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBallsTargetsAppend(ArrayList<int[]> _ballsTargetsAppend) {
            ballsTargetsAppend = _ballsTargetsAppend;
            return this;
        }

        public LevelBuilder setBallsFree(boolean... v) {
            ballsFree = new boolean[v.length];
            System.arraycopy(v, 0, ballsFree, 0, v.length);
            return this;
        }

        public LevelBuilder setBarsQuantity(int v) {
            barsQuantity = v;
            return this;
        }

        public LevelBuilder setBarsWidth(float... v) {
            barsWidth = new float[v.length];
            for (int i = 0; i < v.length; i++){
                barsWidth[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBarsHeight(float... v){
            barsHeight = new float[v.length];
            for (int i = 0; i < v.length; i++){
                barsHeight[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBarsX(float... v) {
            barsX = new float[v.length];
            System.arraycopy(v, 0, barsX, 0, v.length);
            return this;
        }

        public LevelBuilder setBarsY(float... v) {
            barsY = new float[v.length];
            for (int i = 0; i < v.length; i++){
                barsY[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBarsVX(float... v) {
            barsVX = new float[v.length];
            for (int i = 0; i < v.length; i++){
                barsVX[i] = v[i];
            }
            return this;
        }

        public LevelBuilder setBarsVY(float... v) {
            barsVY = new float[v.length];
            System.arraycopy(v, 0, barsVY, 0, v.length);
            return this;
        }

        public LevelBuilder setBarsScaleVariation(ScaleVariationDataBuilder... v){
            barsScaleVariationData = new ScaleVariationDataBuilder[v.length];
            System.arraycopy(v, 0, barsScaleVariationData, 0, v.length);
            return this;
        }

        public LevelBuilder setBarsScaleVariationOff(){
            barsScaleVariationData = null;
            return this;
        }

        public LevelBuilder setTargetWidth(float v) {
            targetsWidht = v;
            return this;
        }

        public LevelBuilder setTargetHeight(float v) {
            targetsHeight = v;
            return this;
        }

        public LevelBuilder setTargetsMap(int [][] v) {
            targetsMap = v;
            return this;
        }

        public LevelBuilder setTargetsStates(int [] v) {
            targetsStates = v;
            return this;
        }

        public LevelBuilder setTargetDistance(float v) {
            targetsDistance = v;
            return this;
        }

        public LevelBuilder setTargetPadd(float v) {
            targetsPadding = v;
            return this;
        }

        public LevelBuilder setObstaclesQuantity(int v) {
            obstaclesQuantity = v;
            return this;
        }

        public LevelBuilder setObstaclesWidth(float... v) {
            obstaclesWidth = new float[v.length];
            System.arraycopy(v, 0, obstaclesWidth, 0, v.length);
            return this;
        }

        public LevelBuilder setObstaclesHeight(float... v) {
            obstaclesHeight = new float[v.length];
            System.arraycopy(v, 0, obstaclesHeight, 0, v.length);
            return this;
        }

        public LevelBuilder setObstaclesX(float... v) {
            obstaclesX = new float[v.length];
            System.arraycopy(v, 0, obstaclesX, 0, v.length);
            return this;
        }

        public LevelBuilder setObstaclesY(float... v) {
            obstaclesY = new float[v.length];
            System.arraycopy(v, 0, obstaclesY, 0, v.length);
            return this;
        }

        public LevelBuilder setObstaclesScaleVariation(ScaleVariationDataBuilder... v){
            obstaclesScaleVariationData = new ScaleVariationDataBuilder[v.length];
            System.arraycopy(v, 0, obstaclesScaleVariationData, 0, v.length);
            return this;
        }

        public LevelBuilder setObstaclesScaleVariationOff(){
            obstaclesScaleVariationData = null;
            return this;
        }

        public LevelBuilder setObstaclesPositionVariation(PositionVariationDataBuilder... v){
            obstaclesPositionVariationData = new PositionVariationDataBuilder[v.length];
            System.arraycopy(v, 0, obstaclesPositionVariationData, 0, v.length);
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
        
        public LevelBuilder setWindowsY(float... v) {
            windowsY = new float[v.length];
            System.arraycopy(v, 0, windowsY, 0, v.length);
            return this;
        }
        
        public LevelBuilder setWindowsHeight(float... v) {
            windowsHeight = new float[v.length];
            System.arraycopy(v, 0, windowsHeight, 0, v.length);
            return this;
        }
        
        public LevelBuilder setWindowsQuantityOfLines(int... v) {
            windowsQuantityOfLines = new int[v.length];
            System.arraycopy(v, 0, windowsQuantityOfLines, 0, v.length);
            return this;
        }

        public LevelBuilder setWindowsDistance(float... v) {
            windowsDistance = new float[v.length];
            System.arraycopy(v, 0, windowsDistance, 0, v.length);
            return this;
        }
        
        public LevelBuilder setWindowsVelocity(float... v) {
            windowsVelocity = new float[v.length];
            System.arraycopy(v, 0, windowsVelocity, 0, v.length);
            return this;
        }

        public LevelBuilder setSpecialBallPercentage0_1(float v){
            specialBallPercentage = v;
            return this;
        }

        public LevelBuilder setWindType(int v){
            windType = v;
            return this;
        }

        public Level build() {
            return new Level();
        }
    }
}
