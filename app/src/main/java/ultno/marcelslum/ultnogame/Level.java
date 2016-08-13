package ultno.marcelslum.ultno;

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
    int minBallsNotInvencibleAlive;
    int ballsQuantity;
    float[] ballsInitialXByResolution = new float[10];
    float[] ballsInitialYByResolution = new float[10];
    float[] ballsDesiredVelocityXByResolution = new float[10];
    float[] ballsDesiredVelocityYByResolution = new float[10];
    float[] ballsRadiusByResolution;
    Color [] ballsColor;
    boolean[] ballsInvencible;
    float[] ballsAngleToRotate = new float[10];
    float[] ballsVelocityVariation = new float[10];
    float[] ballsVelocityMaxByInitialVelocity = new float[10];
    float[] ballsVelocityMinByInitialVelocity = new float[10];
    float[] ballsMaxAngle;
    float[] ballsMinAngle;
    ArrayList<Target>[] ballsTargetsAppend = (ArrayList<Target>[]) new ArrayList[10];
    boolean[] ballsFree = new boolean[10];

    int barsQuantity;
    float[] barsSizeXByResolution = new float[10];
    float[] barsSizeYByResolution = new float[10];
    float[] barsInitialXByResolution = new float[10];
    float[] barsInitialYByResolution = new float[10];
    float[] barsDesiredVelocityXByResolution = new float[10];
    float[] barsDesiredVelocityYByResolution = new float[10];

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
        this.game.blockAndWaitTouchRelease();
        this.tutorials.get(0).show();
        this.showingTutorial = 0;
    }

    public void nextTutorial(){
        final int nextTutorial = this.showingTutorial + 1;

        if (this.tutorials.get(this.showingTutorial).isBlocked == false){
            //TODO this.game.getObjectByName(this.game.sounds, 'menuSelectBig').play();
            final Level self = this;
            if (nextTutorial == this.tutorials.size()){
                this.tutorials.get(this.showingTutorial).setOnUnshowAfterAnim2(new Tutorial.OnUnshowAfterAnim2() {
                    @Override
                    public void onUnshowAfterAnim2() {
                        self.game.setGameState("preparar");
                    }
                });
                this.tutorials.get(this.showingTutorial).unshow();
            } else {
                this.tutorials.get(this.showingTutorial).setOnUnshowAfterAnim2(new Tutorial.OnUnshowAfterAnim2() {
                    @Override
                    public void onUnshowAfterAnim2() {
                        self.tutorials.get(nextTutorial).show();
                    }
                });
                this.tutorials.get(this.showingTutorial).unshow();
                this.showingTutorial = nextTutorial;
            }
        }
    }

    public void loadEntities() {

        Log.e("Level loadEnt", "1");
        this.game.clearGameEntities();
        this.game.quad = new Quadtree(new RectangleM(0,0,this.game.gameAreaResolutionX,this.game.gameAreaResolutionY),5,5);


        this.game.scorePanel = new ScorePanel("scorePanel", this.game,
                this.game.gameAreaResolutionX * 0.4f, this.game.gameAreaResolutionY * 1.015f, this.game.resolutionY * 0.14f); // TODO acertar coordenada x
        //this.game.scorePanel.setValue(100);

        this.game.background = new Background("background", this.game, 0, 0, this.game.gameAreaResolutionX,this.game.gameAreaResolutionY);

        Log.e("Level loadEnt", "1");

        this.game.bordaE = new Rectangle("bordaE", this.game, -999, 0, 1000, this.game.gameAreaResolutionY, 10, new Color(0,0,0,1));
        this.game.bordaE.isMovable = false;
        this.game.bordaE.program = this.game.solidProgram;

        Log.e("Level loadEnt", "1");
        this.game.bordaD = new Rectangle("bordaD", this.game, this.game.gameAreaResolutionX-2, 0, 1000, this.game.gameAreaResolutionY, 10, new Color(0,0,0,1));
        this.game.bordaD.isMovable = false;
        this.game.bordaD.program = this.game.solidProgram;

        Log.e("Level loadEnt", "1");
        this.game.bordaC = new Rectangle("bordaC", this.game, 1, -1000, this.game.gameAreaResolutionX-4, 1001, 10, new Color(0,0,0,1));
        this.game.bordaC.isMovable = false;
        this.game.bordaC.program = this.game.solidProgram;

        Log.e("Level loadEnt", "1");
        this.game.bordaB = new Rectangle("bordaB", this.game, -1000, this.game.gameAreaResolutionY-2, this.game.gameAreaResolutionX*3, 1000, 10, new Color(0,0,0,1));
        this.game.bordaB.isMovable = false;
        this.game.bordaB.program = this.game.solidProgram;

        Log.e("Level loadEnt", "1");
        float y = this.game.gameAreaResolutionY * 1.015f;
        float buttonSize = this.game.resolutionY * 0.14f;


        // BOTÃO 1 ESQUERDA
        float x = this.game.resolutionX * 0.01f;
        this.game.button1Left = new Button("button1Left", this.game, x, y, buttonSize, buttonSize);
        this.game.button1Left.setTextureMap(9);
        this.game.button1Left.textureMapUnpressed = 9;
        this.game.button1Left.textureMapPressed = 10;
        this.game.button1Left.alpha = 0.7f;

        // BOTÃO 1 DIREITA
        x = this.game.resolutionX * 0.14f;
        this.game.button1Right = new Button("button1Right", this.game, x, y, buttonSize, buttonSize);
        this.game.button1Right.setTextureMap(7);
        this.game.button1Right.textureMapUnpressed = 7;
        this.game.button1Right.textureMapPressed = 11;
        this.game.button1Right.alpha = 0.7f;

        Log.e("Level loadEnt", "1");
        if (this.barsQuantity > 1) {
            // BOTÃO 2 ESQUERDA
            x = this.game.resolutionX * 0.66f;
            this.game.button2Left = new Button("button2Left", this.game, x, y, buttonSize, buttonSize);
            this.game.button2Left.setTextureMap(9);
            this.game.button2Left.textureMapUnpressed = 9;
            this.game.button2Left.textureMapPressed = 10;

            // BOTÃO 2 DIREITA
            x = this.game.resolutionX * 0.83f;
            this.game.button2Right = new Button("buttonRight", this.game, x, y, buttonSize, buttonSize);
            this.game.button2Right.setTextureMap(7);
            this.game.button2Right.textureMapUnpressed = 7;
            this.game.button2Right.textureMapPressed = 11;
        }

        /*
        Log.e("Level loadEnt", "1");

        // BOTÃO SOM
        this.game.soundButton = new ButtonOnOff("buttonSound", this.game, this.game.gameAreaResolutionX * 0.45f,
                this.game.gameAreaResolutionY * 0.95f, this.game.gameAreaResolutionX * 0.028f, this.game.gameAreaResolutionX * 0.028f);

        //console.log("this.game.soundOn ", this.game.soundOn);
        if (this.game.soundOn){
            this.game.soundButton.on = true;
        }

        // TODO this.game.buttons.buttonSound.imageOn = this.game.icons.soundOn;
        // TODO this.game.buttons.buttonSound.imageOff = this.game.icons.soundOff;

        Log.e("Level loadEnt", "1");
        final Level self = this;
        this.game.soundButton.listener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                self.game.soundButton.setPressed();
                if (self.game.soundOn){
                    self.game.soundOn = false;
                } else {
                    if (self.game.volume == 0){
                        self.game.volume = 10;
                    }
                    self.game.soundOn = true;
                }
            }
            @Override
            public void onUnpress() {
                self.game.soundButton.setUnpressed();
            }
        });

        // BOTÃO SOM
        this.game.musicButton = new ButtonOnOff("musicButton", this.game, this.game.gameAreaResolutionX * 0.53f,
                this.game.gameAreaResolutionY * 0.95f, this.game.gameAreaResolutionX * 0.028f, this.game.gameAreaResolutionX * 0.028f);

        //console.log("this.game.soundOn ", this.game.soundOn);
        if (this.game.musicOn){
            this.game.musicButton.on = true;
        }

        // TODO this.game.musicButton.imageOn = this.game.icons.soundOn;
        // TODO this.game.musicButton.imageOff = this.game.icons.soundOff;

        this.game.musicButton.listener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (self.game.isBlocked){
                    return;
                }

                self.game.musicButton.setPressed();

                if (self.game.musicOn){
                    self.game.musicOn = false;
                } else {
                    self.game.musicOn = true;
                    if (self.game.volume == 0){
                        self.game.volume = 10;
                    }
                }
                if (self.game.gameState == "iniciar"){
                    if (self.game.musicOn){
                        self.game.music.play();
                    } else {
                        self.game.music.pause();
                    }
                }
            }
            @Override
            public void onUnpress() {
                self.game.soundButton.setUnpressed();
            }
        });

        this.game.addInteracionListener(new InteractionListener("gameArea", 0, 0,
                this.game.gameAreaResolutionX, this.game.gameAreaResolutionY, 0, this.game.gameArea, this.game));
         */

        for (int i = 0; i < this.barsQuantity; i++){

            float barX = this.game.gameAreaResolutionX * this.barsInitialXByResolution[i];
            float barY = this.game.gameAreaResolutionY - (this.game.gameAreaResolutionY * this.barsInitialYByResolution[i]);

            float barWidth = this.game.gameAreaResolutionX * this.barsSizeXByResolution[i];
            float barHeight = this.game.gameAreaResolutionY * this.barsSizeYByResolution[i];

            float barVelocityX = this.game.gameAreaResolutionX * this.barsDesiredVelocityXByResolution[i];
            float barVelocityY = this.game.gameAreaResolutionY * this.barsDesiredVelocityYByResolution[i];

            Bar bar = new Bar("bar", this.game, barX, barY, barWidth, barHeight, 9);
            this.game.addBar(bar);
            this.game.barsInitialPositionX[i] = barX;
            this.game.barsInitialPositionY[i] = barY;
            this.game.barsDesiredVelocityX[i] = barVelocityX;
            this.game.barsDesiredVelocityY[i] = barVelocityY;
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


        Log.e("Level loadEnt", "2");
        int ballsInvencible = 0;

        for (int i = 0; i < this.ballsQuantity; i++){
            float ballX = this.game.gameAreaResolutionX * this.ballsInitialXByResolution[i];
            float ballY = this.game.gameAreaResolutionX * this.ballsInitialYByResolution[i];

            float radium = this.game.gameAreaResolutionY * this.ballsRadiusByResolution[i];

            float ballVelocityX = this.game.gameAreaResolutionX * this.ballsDesiredVelocityXByResolution[i];
            float ballVelocityY = this.game.gameAreaResolutionX * this.ballsDesiredVelocityYByResolution[i];

            if (this.ballsInvencible[i]){
                ballsInvencible += 1;
            }

            Log.e("Level loadEnt", "3");

            Ball ball = new Ball("ball", this.game, ballX, ballY, radium, 8);
            ball.program = this.game.imageProgram;
            ball.textureUnit = 0;
            // todo ball.color = this.ballsColor[i];

            ball.angleToRotate = this.ballsAngleToRotate[i];
            ball.velocityVariation = this.ballsVelocityVariation[i];

            ball.velocityMaxByInitialVelocity = this.ballsVelocityMaxByInitialVelocity[i];
            ball.velocityMinByInitialVelocity = this.ballsVelocityMinByInitialVelocity[i];

            Log.e("Level loadEnt", "4");

            ball.maxAngle = this.ballsMaxAngle[i];
            ball.minAngle = this.ballsMinAngle[i];

            ball.initialDesireVelocityX = ballVelocityX;
            ball.initialDesireVelocityY = ballVelocityY;

            ball.dvx = ballVelocityX;
            ball.dvy = ballVelocityY;

            for (int t = 0; t < this.ballsTargetsAppend.length; t++){
                ball.targetsAppend = this.ballsTargetsAppend[i];
            }
            //console.log(ball.targetsAppend);

            Log.e("Level loadEnt", "5");

            ball.isFree = this.ballsFree[i];

            Log.e("Level loadEnt", "5.1");
            this.game.addBall(ball);

            Log.e("Level loadEnt", "5.2");
            this.game.ballsInitialPositionX[i] = ballX;
            this.game.ballsInitialPositionY[i] = ballY;
            this.game.ballsDesiredVelocityX[i] = ballVelocityX;
            this.game.ballsDesiredVelocityY[i] = ballVelocityY;
            Log.e("Level loadEnt", "5.3");
        }

        if (this.entitiesCreator != null){
            this.entitiesCreator.createObstacles();
        }

        if (this.entitiesCreator != null){
            this.entitiesCreator.createWindows();
        }

        Log.e("Level loadEnt", "6");

       //todo this.game.hudEntities.scorePanel.setValue(this.ballsQuantity - ballsInvencible, ballsInvencible, this.minBallsNotInvencibleAlive);
    }

}