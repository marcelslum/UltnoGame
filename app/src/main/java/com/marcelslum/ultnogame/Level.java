package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Level {

    public static Game game;
    public static final int WIND_TYPE_NO = 0;
    public static final int WIND_TYPE_RIGHT = 1;
    public static final int WIND_TYPE_LEFT = 2;
    public ArrayList<BallDataBaseData> ballDataBaseData;
    public ArrayList<BarDataBaseData> barDataBaseData;
    public ArrayList<TargetDataBaseData> targetDataBaseData;
    public int minBallsAlive;
    public ArrayList<int[]> ballsTargetsAppend;
    public ScaleVariationDataBuilder[] barsScaleVariationData;
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
    public float specialBallPercentage = 0f;
    public float fakeBallPercentage = 0f;
    public boolean invertedButtons = false;
    public float windType;

    public static Level levelObject;
    public static LevelGoals levelGoalsObject;

    public static final int maxNumberOfLevels = 100;
    public static final int numberOfSecretLevels = 10;

    private Level(){

        ballDataBaseData = LevelBuilder.ballDataBaseData;
        barDataBaseData = LevelBuilder.barDataBaseData;
        targetDataBaseData = LevelBuilder.targetDataBaseData;
        minBallsAlive = LevelBuilder.minBallsAlive;
        ballsTargetsAppend = LevelBuilder.ballsTargetsAppend;
        barsScaleVariationData = LevelBuilder.barsScaleVariationData;
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
        fakeBallPercentage = LevelBuilder.fakeBallPercentage;
        windType = LevelBuilder.windType;
        invertedButtons = LevelBuilder.invertedButtons;
    }

    public void loadEntities() {
        Game.eraseAllGameEntities();
        Game.quad = new Quadtree(0,0,Game.gameAreaResolutionX,Game.gameAreaResolutionY,2,8);

        MessagesHandler.messageTime.setText("00:00");
        if (SaveGame.saveGame.currentLevelNumber < 1000) {
            MessagesHandler.messageCurrentLevel.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel) + " " + String.valueOf(SaveGame.saveGame.currentLevelNumber));
        } else {
            MessagesHandler.messageCurrentLevel.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevelSecret) + " " + String.valueOf(SaveGame.saveGame.currentLevelNumber - 999));
        }

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

        ButtonHandler.createGameButtons(barDataBaseData.size(), invertedButtons);

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

        for (int i = 0; i < barDataBaseData.size(); i++){

            float barX = Game.gameAreaResolutionX * barDataBaseData.get(i).x;
            float barY = Game.gameAreaResolutionY - (Game.gameAreaResolutionY * barDataBaseData.get(i).y);

            float barWidth = Game.gameAreaResolutionX * barDataBaseData.get(i).width;
            float barHeight = Game.gameAreaResolutionY * barDataBaseData.get(i).height;

            float barVelocityX = Game.gameAreaResolutionX * barDataBaseData.get(i).vx;

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

        float targetWidth = Game.gameAreaResolutionX * targetDataBaseData.get(0).width;
        float targetHeight = Game.gameAreaResolutionY * targetDataBaseData.get(0).height;
        float targetX;
        float targetY;

        Log.e("level", "targetsMap.length "+targetsMap.length);
        int contador = 0;
        for (int iY = 0; iY < targetsMap.length;iY++){
            for (int iX = 0; iX < targetsMap[iY].length; iX++) {
                if (targetsMap[iY][iX] != 0) {
                    targetX = (Game.gameAreaResolutionX * targetDataBaseData.get(0).padd) +
                            (iX * ((Game.gameAreaResolutionX * targetDataBaseData.get(0).width) +
                                    (Game.gameAreaResolutionX * targetDataBaseData.get(0).distance)));
                    targetY = (Game.gameAreaResolutionX * targetDataBaseData.get(0).padd) +
                            (iY * ((Game.gameAreaResolutionY * targetDataBaseData.get(0).height) +
                                    (Game.gameAreaResolutionX * targetDataBaseData.get(0).distance)));

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

        Game.targetGroup = new TargetGroup();
        Game.targetGroup.setDrawInfo();

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

        for (int i = 0; i < ballDataBaseData.size(); i++){

            float ballX = Game.gameAreaResolutionX * ballDataBaseData.get(i).x;
            float ballY = Game.gameAreaResolutionY * ballDataBaseData.get(i).y;
            float radius = Game.gameAreaResolutionY * ballDataBaseData.get(i).radius;
            float ballVelocityX = Game.gameAreaResolutionX * ballDataBaseData.get(i).vx;
            float ballVelocityY = Game.gameAreaResolutionY * ballDataBaseData.get(i).vy; //* Game.difficultyVelocityBallMultiplicator;

            Ball ball = new Ball("ball", ballX, ballY, radius, ballDataBaseData.get(i).textureMap);
            ball.angleToRotate = ballDataBaseData.get(i).angleToRotate;
            ball.velocityVariation = ballDataBaseData.get(i).velocityVariation;
            ball.velocityMax_BI = ballDataBaseData.get(i).maxVelocity;
            ball.velocityMin_BI = ballDataBaseData.get(i).minVelocity;
            ball.maxAngle = ballDataBaseData.get(i).maxAngle;
            ball.minAngle = ballDataBaseData.get(i).minAngle;
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

            ball.isFree = ballDataBaseData.get(i).free == 1 ? true : false;

            if (ballDataBaseData.get(i).invencible == 1 ? true : false){
                ball.setInvencible();
            }

            Game.addBall(ball);

        }
    }

    static public class LevelBuilder {
        private static ArrayList<BallDataBaseData> ballDataBaseData;
        private static ArrayList<BarDataBaseData> barDataBaseData;
        private static ArrayList<TargetDataBaseData> targetDataBaseData;
        private static int minBallsAlive = 1;
        private static ArrayList<int[]> ballsTargetsAppend = new ArrayList<int[]>();
        private static ScaleVariationDataBuilder[] barsScaleVariationData;
        private static int [] targetsStates;
        private static int [][] targetsMap;
        private static int obstaclesQuantity = 0;
        private static float[] obstaclesWidth;
        private static float[] obstaclesHeight;
        private static float[] obstaclesX;
        private static float[] obstaclesY;
        private static ScaleVariationDataBuilder[] obstaclesScaleVariationData;
        private static PositionVariationDataBuilder[] obstaclesPositionVariationData;
        private static int windowsQuantity;
        private static float[] windowsY;
        private static float[] windowsHeight;
        private static int[] windowsQuantityOfLines;
        private static float[] windowsDistance;
        private static float[] windowsVelocity;
        private static float specialBallPercentage = 0f;
        private static float fakeBallPercentage = 0f;
        private static int windType = Level.WIND_TYPE_NO;
        private static boolean invertedButtons = false;

        public LevelBuilder setBallDataBaseData(ArrayList<BallDataBaseData> v) {
            ballDataBaseData = v;
            return this;
        }

        public LevelBuilder setBarDataBaseData(ArrayList<BarDataBaseData> v) {
            barDataBaseData = v;
            return this;
        }

        public LevelBuilder setTargetDataBaseData(ArrayList<TargetDataBaseData> v) {
            targetDataBaseData = v;
            return this;
        }

        public LevelBuilder setInvertedButtons(boolean v) {
            invertedButtons = v;
            return this;
        }

        public LevelBuilder setMinBallsAlive(int v) {
            minBallsAlive = v;
            return this;
        }

        public LevelBuilder setBallsTargetsAppend(ArrayList<int[]> _ballsTargetsAppend) {
            ballsTargetsAppend = _ballsTargetsAppend;
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

        public LevelBuilder setTargetsMap(int [][] v) {
            targetsMap = v;
            return this;
        }

        public LevelBuilder setTargetsStates(int [] v) {
            targetsStates = v;
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

        public LevelBuilder setSpecialBallPercentage(float v){
            specialBallPercentage = v;
            return this;
        }
        
        public LevelBuilder setFakeBallPercentage(float v){
            fakeBallPercentage = v;
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
