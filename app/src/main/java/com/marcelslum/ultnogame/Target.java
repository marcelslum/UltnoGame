package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Target extends Rectangle {

    private int [] states;
    private int currentState;
    int special;
    int pointsToShow;
    float posYVariation;
    public int type;
    static final int TARGET_BLACK = 0;
    static final int TARGET_GREEN = 1;



    static final int TARGET_BLUE = 2;
    private static final int TARGET_RED = 3;
    private Animation showPointsStateAnim;
    private Animation showPointsAlphaAnim;
    private Animation ghostAlphaAnim;
    private Animation desapearAnim;
    private boolean isGhost;
    private Point pointsObject;
    private final static int POINTS_DURATION = 1000;
    boolean alive = true;

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        float normalAlpha = alpha;
        alpha = normalAlpha * ghostAlpha;
        super.render(matrixView, matrixProjection);
        alpha = normalAlpha;
    }

    Target(String name, float x, float y, float width, float height, int [] states, int currentState, int special, boolean ghost){
        super(name, x, y, width, height, Game.TARGET_WEIGHT, new Color(0,0,0,1));
        this.states = states;
        this.currentState = currentState;
        this.special = special;
        setType();
        textureId = Texture.TEXTURE_TARGETS;
        program = Game.imageProgram;
        isMovable = false;
        isGhost = ghost;

        if (isGhost){
            ghostAlpha = 0f;
        } else {
            ghostAlpha = 1f;
        }

        setDrawInfo();

        final Target self = this;

        ArrayList<float[]> valuesAnimationShowPoints = new ArrayList<>();
        valuesAnimationShowPoints.add(new float[]{0f,1f});
        valuesAnimationShowPoints.add(new float[]{1f,0f});
        showPointsStateAnim = new Animation(this, "showPointsState", "showPointsState", POINTS_DURATION, valuesAnimationShowPoints, false, false);

        ArrayList<float[]> valuesAnimationShowPointsAlpha = new ArrayList<>();
        valuesAnimationShowPointsAlpha.add(new float[]{0f,1f});
        valuesAnimationShowPointsAlpha.add(new float[]{1f,0f});
        showPointsAlphaAnim = new Animation(this, "pointsAlpha", "pointsAlpha", POINTS_DURATION, valuesAnimationShowPointsAlpha, false, true);

        ArrayList<float[]> valuesAnimationGhostAlpha = new ArrayList<>();
        valuesAnimationGhostAlpha.add(new float[]{0f,1f});
        valuesAnimationGhostAlpha.add(new float[]{1f,0f});
        ghostAlphaAnim = new Animation(this, "ghostAlpha", "ghostAlpha", 1000, valuesAnimationGhostAlpha, false, true);

        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0f,1f});
        valuesAnimation.add(new float[]{0.3f,0.10f});
        valuesAnimation.add(new float[]{0.4f,0f});
        valuesAnimation.add(new float[]{1f,0f});
        desapearAnim = new Animation(this, "desapear", "alpha", 1000, valuesAnimation, false, true);
        desapearAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                self.isVisible = false;
            }
        });
    }

    public void onBallCollision(){
        int points = Game.basePoints;
        if (Game.ballGoalsPanel.blueBalls > 0) {
            for (int i = 0; i < Game.ballGoalsPanel.blueBalls; i++) {
                points *= 2;
            }
        }
        decayState(points);
        verifySpecialBall();
    }

    public void verifySpecialBall(){
        if (Level.levelObject.specialBallPercentage > 0f){
            if (Utils.getRandonFloat(0.0f, 1.0f) < Level.levelObject.specialBallPercentage){
                if (Game.specialBalls.size()<2) {
                    SpecialBall sb = new SpecialBall("specialBall", positionX + (width/2f), positionY + (height/2f), (height/2f));
                    sb.dvy = Game.bars.get(0).dvx *0.4f;
                    Game.specialBalls.add(sb);
                }
            }
        }
    }

    public void renderPoints(float[] matrixView, float[] matrixProjection){
        if (pointsObject != null) {
            pointsObject.alpha = pointsAlpha;
            pointsObject.render(matrixView, matrixProjection);
        }
    }

    public void showPoints(int points){

        pointsToShow = points;

        float pointsX;
        if ((x + (width * 1.5f))> Game.gameAreaResolutionX){
            pointsX = x;
        } else {
            pointsX = x + (width/2f);
        }

        pointsObject = new Point("points", pointsX,y + (height/2f) ,height * 1.5f);



        addChild(pointsObject);
        pointsObject.setValue(points);

        this.posYVariation = 0;
        if (this.showPointsStateAnim != null) {
            this.showPointsStateAnim.start();
        }

        //Log.e("target", "3");
        if (this.showPointsAlphaAnim != null) {
            this.showPointsAlphaAnim.start();
            Utils.createSimpleAnimation(pointsObject, "translateX", "translateX", POINTS_DURATION, 0f, Game.gameAreaResolutionX*0.025f).start();
            Utils.createSimpleAnimation(pointsObject, "translateY", "translateY", POINTS_DURATION, 0f, -Game.gameAreaResolutionX*0.02f).start();
        }
    }

    public void decayState(int points){

        Sound.play(Sound.soundDestroyTarget, 1, 1, 0);

        Game.scorePanel.setValue(Game.scorePanel.value + points,  true, 500, false);

        this.currentState -= 1;

        // O alvo especial não tem estado, uma vez atingido ele ativa sua habilidade especial.
        if (special != 0){
            currentState = 0;
        }

        if (currentState == 0){
            alive = false;
        }

        setType();

        showPoints(points);

        if (isGhost){
            if (ghostAlphaAnim != null) {
                Log.e("target", "ghostanimstarted");
                ghostAlphaAnim.start();
            }
            if (states[this.currentState] == 0){
                isCollidable = false;
                isMovable = false;
                isSolid = false;
            }
        } else if (this.currentState != -1){
            if (states[this.currentState] == 0){
                isCollidable = false;
                isMovable = false;
                isSolid = false;
                desapearAnim.start();
            }
        }
    }



    public void setType(){
        if (currentState == -1){
            return;
        }

        if (special == 1){
            type = TARGET_RED;
        } else if (states[currentState] != 0){
            if (this.states[currentState] == 3){
                type = TARGET_GREEN;
            } else if (this.states[currentState] == 2){
                type = TARGET_BLUE;
            } else if (this.states[currentState] == 1){
                type = TARGET_BLACK;
            }
        }

        setUvInfo(type);
    }

    public void setUvInfo(int type){
        if (type == TARGET_RED){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 1f/1024f, 206f/1024f);
        } else if (type == TARGET_BLUE){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 624f/1024f, 830f/1024f);
        } else if (type == TARGET_GREEN){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 208f/1024f, 414f/1024f);
        } else if (type == TARGET_BLACK){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 416f/1024f, 622f/1024f);
        }
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);
        
        Utils.insertRectangleVerticesData(verticesData,0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        
        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        setUvInfo(type);

    }
}
