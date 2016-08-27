package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Level {

    ArrayList<Tutorial> tutorials;
    Game game;
    int number;
    int maxScore;
    int showingTutorial = -1;
    
    int ballsQuantity;
    int minBallsNotInvencibleAlive;
    float[] ballsRadiusByResolution;
    float[] ballsInitialXByResolution;
    float[] ballsInitialYByResolution;
    float[] ballsDesiredVelocityXByResolution;
    float[] ballsDesiredVelocityYByResolution;
    Color [] ballsColor;
    boolean[] ballsInvencible;
    float[] ballsAngleToRotate;
    float[] ballsMaxAngle;
    float[] ballsMinAngle;
    float[] ballsVelocityVariation;
    float[] ballsVelocityMaxByInitialVelocity;
    float[] ballsVelocityMinByInitialVelocity;
    ArrayList<ArrayList<Target>> ballsTargetsAppend;
    boolean[] ballsFree;
    int barsQuantity;
    float[] barsSizeXByResolution;
    float[] barsSizeYByResolution;
    float[] barsInitialXByResolution;
    float[] barsInitialYByResolution;
    float[] barsDesiredVelocityXByResolution;
    float[] barsDesiredVelocityYByResolution;
    int quantityTargetsX;
    int quantityTargetsY;
    float targetSizeXByResolution;
    float targetSizeYByResolution;
    float targetsDistanceByXResolution;
    float targetsPaddingByXResolution;
    
    EntitiesCreator entitiesCreator;

    Level(int number,Game game){
        this.number = number;
        this.game = game;
        this.tutorials = new ArrayList<>();
    }
    
    
    Level(  int number,
            Game game,
            int ballsQuantity,
            int minBallsNotInvencibleAlive,
            float[] ballsRadiusByResolution,
            float[] ballsInitialXByResolution,
            float[] ballsInitialYByResolution,
            float[] ballsDesiredVelocityXByResolution,
            float[] ballsDesiredVelocityYByResolution,
            Color [] ballsColor,
            boolean[] ballsInvencible,
            float[] ballsAngleToRotate,
            float[] ballsMaxAngle,
            float[] ballsMinAngle,
            float[] ballsVelocityVariation,
            float[] ballsVelocityMaxByInitialVelocity,
            float[] ballsVelocityMinByInitialVelocity,
            ArrayList<ArrayList<Target>> ballsTargetsAppend,
            boolean[] ballsFree,
            int barsQuantity,
            float[] barsSizeXByResolution,
            float[] barsSizeYByResolution,
            float[] barsInitialXByResolution,
            float[] barsInitialYByResolution,
            float[] barsDesiredVelocityXByResolution,
            float[] barsDesiredVelocityYByResolution,
            int quantityTargetsX,
            int quantityTargetsY,
            float targetSizeXByResolution,
            float targetSizeYByResolution,
            float targetsDistanceByXResolution,
            float targetsPaddingByXResolution
        ){
        
            this.number = number;
            this.game = game;
            this.ballsQuantity = ballsQuantity;
            this.minBallsNotInvencibleAlive = minBallsNotInvencibleAlive;
            this.ballsRadiusByResolution = ballsRadiusByResolution;
            this.ballsInitialXByResolution = ballsInitialXByResolution;
            this.ballsInitialYByResolution = ballsInitialYByResolution;
            this.ballsDesiredVelocityXByResolution = ballsDesiredVelocityXByResolution;
            this.ballsDesiredVelocityYByResolution = ballsDesiredVelocityYByResolution;
            this.ballsColor = ballsColor;
            this.ballsInvencible = ballsInvencible;
            this.ballsAngleToRotate = ballsAngleToRotate;
            this.ballsMaxAngle = ballsMaxAngle;
            this.ballsMinAngle = ballsMinAngle;
            this.ballsVelocityVariation = ballsVelocityVariation;
            this.ballsVelocityMaxByInitialVelocity = ballsVelocityMaxByInitialVelocity;
            this.ballsVelocityMinByInitialVelocity = ballsVelocityMinByInitialVelocity;
            this.ballsTargetsAppend = ballsTargetsAppend;
            this.ballsFree = ballsFree;
            this.barsQuantity = barsQuantity;
            this.barsSizeXByResolution = barsSizeXByResolution;
            this.barsSizeYByResolution = barsSizeYByResolution;
            this.barsInitialXByResolution = barsInitialXByResolution;
            this.barsInitialYByResolution = barsInitialYByResolution;
            this.barsDesiredVelocityXByResolution = barsDesiredVelocityXByResolution;
            this.barsDesiredVelocityYByResolution = barsDesiredVelocityYByResolution;
            this.quantityTargetsX = quantityTargetsX;
            this.quantityTargetsY = quantityTargetsY;
            this.targetSizeXByResolution = targetSizeXByResolution;
            this.targetSizeYByResolution = targetSizeYByResolution;
            this.targetsDistanceByXResolution = targetsDistanceByXResolution;
            this.targetsPaddingByXResolution = targetsPaddingByXResolution;

            this.tutorials = new ArrayList<>();
    }
    

    public interface EntitiesCreator{
        public void createTargets();
        public void createObstacles();
        public void createWindows();
    }

    public void setEntitiesCreator(EntitiesCreator creator){
        this.entitiesCreator = creator;
    }

    public void showFirstTutorial(){
        //Log.e("level", "showFirstTutorial");
        this.game.blockAndWaitTouchRelease();
        this.showingTutorial = 0;
        this.tutorials.get(0).show(game.soundPool, game.soundTextBoxAppear);
    }

    public void nextTutorial(){
        Log.e("level", "nextTutorial");
        Log.e("level", "showingTutorial "+showingTutorial);
        final Level innerLevel = this;
        this.game.blockAndWaitTouchRelease();
        if (this.tutorials.get(this.showingTutorial).isBlocked == false){
            game.soundPool.play(game.soundMenuSelectBig, 1, 1, 0, 0, 1);
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
                        innerLevel.tutorials.get(innerLevel.showingTutorial).show(innerLevel.game.soundPool, innerLevel.game.soundTextBoxAppear);
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
        this.game.buttonSound.listener.x = this.game.gameAreaResolutionX * 0.32f;
        this.game.buttonSound.listener.y = this.game.resolutionY * 0.86f;
        this.game.buttonSound.listener.width = this.game.gameAreaResolutionX * 0.12f;
        this.game.buttonSound.listener.height = this.game.resolutionY * 0.12f;

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
        this.game.buttonMusic.listener.x = this.game.gameAreaResolutionX * 0.58f;
        this.game.buttonMusic.listener.y = this.game.resolutionY * 0.86f;
        this.game.buttonMusic.listener.width = this.game.gameAreaResolutionX * 0.12f;
        this.game.buttonMusic.listener.height = this.game.resolutionY * 0.12f;
        if (this.game.musicOn) {
            this.game.buttonMusic.setOn();
        } else {
            this.game.buttonMusic.setOff();
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

            float barX = this.game.gameAreaResolutionX * this.barsInitialXByResolution[i];
            float barY = this.game.gameAreaResolutionY - (this.game.gameAreaResolutionY * this.barsInitialYByResolution[i]);

            float barWidth = this.game.gameAreaResolutionX * this.barsSizeXByResolution[i];
            float barHeight = this.game.gameAreaResolutionY * this.barsSizeYByResolution[i];

            float barVelocityX = this.game.gameAreaResolutionX * this.barsDesiredVelocityXByResolution[i];
            float barVelocityY = this.game.gameAreaResolutionY * this.barsDesiredVelocityYByResolution[i];

            game.barsDesiredVelocityX[i] = barVelocityX;
            game.barsDesiredVelocityY[i] = barVelocityY;
            game.barsInitialPositionX[i] = barX;
            game.barsInitialPositionY[i] = barY;

            Bar bar = new Bar("bar", this.game, barX, barY, barWidth, barHeight, 9);
            this.game.addBar(bar);

            bar.initialX = barX;
            bar.initialY = barY;
            bar.initialDesireVelocityX = barVelocityX;
            bar.dvx = barVelocityX;
        }



        if (this.entitiesCreator != null){
            this.entitiesCreator.createTargets();
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
            float ballX = this.game.gameAreaResolutionX * this.ballsInitialXByResolution[i];
            float ballY = this.game.gameAreaResolutionY * this.ballsInitialYByResolution[i];

            float radium = this.game.gameAreaResolutionY * this.ballsRadiusByResolution[i];

            float ballVelocityX = this.game.gameAreaResolutionX * this.ballsDesiredVelocityXByResolution[i];
            float ballVelocityY = this.game.gameAreaResolutionY * this.ballsDesiredVelocityYByResolution[i];

            //game.ballsDesiredVelocityX[i] = ballVelocityX;
            //game.ballsDesiredVelocityY[i] = ballVelocityY;
            //game.ballsInitialPositionX[i] = ballX;
            //game.ballsInitialPositionY[i] = ballY;

            if (this.ballsInvencible[i]){
                numberOfBallsInvencible += 1;
            }

            //Log.e("Level loadEnt", "3");

            Ball ball = new Ball("ball", this.game, ballX, ballY, radium, 8);
            ball.program = this.game.imageProgram;
            ball.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;

            ball.angleToRotate = this.ballsAngleToRotate[i];
            ball.velocityVariation = this.ballsVelocityVariation[i];

            ball.velocityMaxByInitialVelocity = this.ballsVelocityMaxByInitialVelocity[i];
            ball.velocityMinByInitialVelocity = this.ballsVelocityMinByInitialVelocity[i];

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

        if (this.entitiesCreator != null){
            this.entitiesCreator.createObstacles();
        }

        if (this.entitiesCreator != null){
            this.entitiesCreator.createWindows();
        }

        //Log.e("Level loadEnt", "6");

       //todo this.game.hudEntities.scorePanel.setValue(this.ballsQuantity - ballsInvencible, ballsInvencible, this.minBallsNotInvencibleAlive);
    }

}
