package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Target extends Rectangle {

    static final String TAG = "Target";

    private int [] states;
    private int currentState;
    int special;

    float posYVariation;
    public int type;
    public static final int TARGET_BLACK = 0;
    public static final int TARGET_GREEN = 1;
    public static final int TARGET_BLUE = 2;
    public static final int TARGET_RED = 3;
    private Animation ghostAlphaAnim;
    private Animation desapearAnim;
    private boolean isGhost;
    private Point pointsObject;
    private final static int POINTS_DURATION = 1000;
    boolean alive = true;
    public long timeOfLastDecay = 0;
    public float percentageOfDecay = 0f;
    
    int pointsToShow = -1;
    float pointAlpha = 1f;
    float pointY;
    float pointX;
    float pointSize;


    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        float normalAlpha = alpha;
        alpha = normalAlpha * ghostAlpha;
        render(matrixView, matrixProjection);
        alpha = normalAlpha;
    }

    Target(String name, float x, float y, float width, float height, int [] states, int currentState, int special, boolean ghost){
        super(name, x, y, Entity.TYPE_TARGET, width, height, Game.TARGET_WEIGHT, new Color(0,0,0,1));
        this.states = states;
        this.currentState = currentState;
        this.special = special;
        colorChangeFlag = true;

        setType();
        textureId = Texture.TEXTURES;
        program = Game.imageProgram;
        isMovable = false;
        isGhost = ghost;
        alpha = 1;

        pointSize = Game.gameAreaResolutionY * 0.07f;

        if (isGhost){
            ghostAlpha = 0f;
        } else {
            ghostAlpha = 1f;
        }

        setDrawInfo();

        final Target self = this;

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
        int points = Game.BASE_POINTS;
        if (SaveGame.saveGame.currentLevelNumber > 999){
            points *= 2;
        }
        if (Game.ballGoalsPanel.blueBalls > 0) {
            for (int i = 0; i < Game.ballGoalsPanel.blueBalls; i++) {
                points *= 2;
            }
        }
        
        if (type == TARGET_BLACK){
            BrickBackground.ballCollidedBlack = 2000;
        } else if (type == TARGET_BLUE){
            BrickBackground.ballCollidedBlue = 2000;
        } else if (type == TARGET_GREEN){
            BrickBackground.ballCollidedGreen = 2000;
        } else if (type == TARGET_RED){
            BrickBackground.ballCollidedRed = 2000;
        }

        decayState(points);
        verifySpecialBall();
    }

    public void verifySpecialBall(){
        if (Level.levelObject.specialBallPercentage > 0f){
            float percentage = Level.levelObject.specialBallPercentage;
            int difference = TimeHandler.secondsOfLevelPlay - SpecialBall.timeOfLastSpecialBall;
            
            if (difference > 7 && difference < 12){
                percentage *= 1.2;
            } else if (difference >= 12){
                percentage *= 1.5;
            }
            
            if (Utils.getRandonFloat(0.0f, 1.0f) < percentage){
                if (Game.specialBalls.size()<2) {
                    
                    SpecialBall.timeOfLastSpecialBall = TimeHandler.secondsOfLevelPlay;
                    
                    SpecialBall sb = new SpecialBall("specialBall", positionX + (width/2f), positionY + (height/2f), (height/2f));
                    sb.dvy = Math.abs(Game.bars.get(0).dvx *0.4f);
                    Game.specialBalls.add(sb);
                }
            }
        }
    }
    
    public void animatePoints(){

        //if (pointsToShow > 0) {
        //    Log.e(TAG, "pointsToShow " + pointsToShow + " pointsAlpha " + pointsAlpha);
        //}

        if (pointsToShow > 0){
                pointX += pointSize * 0.01f;
                pointY -= pointSize * 0.01f;
                pointAlpha -= 0.01f;
            if (pointsAlpha <= 0f){
                //Log.e(TAG, "zerando pointsToShow");
                pointsToShow = -1;
            }
        }
    }

    public void showPoints(int points){

        pointsToShow = points;
        //Log.e(TAG, "show points -------------------- pointsToShow "+pointsToShow);
        if ((x + (width * 1.5f))> Game.gameAreaResolutionX){
            pointX = x;
        } else {
            pointX = x + (width/2f);
        }
        pointY = y + (height/2f);
        pointsAlpha = 1f;
    }

    public void decayState(int points){
        timeOfLastDecay = Utils.getTime();
        Sound.play(Sound.soundDestroyTarget, 1, 1, 0);
        ScoreHandler.scorePanel.setValue(ScoreHandler.scorePanel.value + points,  true, 500, false);

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
                //Log.e("target", "ghostanimstarted");
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
       
        uvChangeFlag = true;
        
    }

    public void setUvInfo(int type){
        if (type == TARGET_RED){
            Utils.insertRectangleUvData(uvsData, 0, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_RED_ID));
        } else if (type == TARGET_BLUE){
            Utils.insertRectangleUvData(uvsData, 0, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_BLUE_ID));
        } else if (type == TARGET_GREEN){
            Utils.insertRectangleUvData(uvsData, 0, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_GREEN_ID));
        } else if (type == TARGET_BLACK){
            Utils.insertRectangleUvData(uvsData, 0, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_BLACK_ID));
        }
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 16);
        Utils.insertRectangleVerticesData(verticesData,0, 0f, width, 0f, height, 0f);
        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        Utils.insertRectangleColorsData(colorsData,0 , 0f, 0f, 0f, 1f);
        setUvInfo(type);
    }
}
