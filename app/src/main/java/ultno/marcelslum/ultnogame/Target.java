package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Target extends Rectangle {

    public int [] states;
    public int currentState;
    public int special;
    public int type;
    private int pointsToShow;
    private int posYVariation;
    public static final int TARGET_BLACK = 0;
    public static final int TARGET_GREEN = 1;
    public static final int TARGET_BLUE = 2;
    public static final int TARGET_RED = 3;
    Animation showPointsStateAnim;
    Animation showPointsAlphaAnim;
    Animation ghostAlphaAnim;
    Animation desapearAnim;
    private boolean isGhost;
    Point pointsObject;
    public final static int POINTS_DURATION = 1000;
    boolean alive = true;

    Target(String name, Game game, float x, float y, float width, float height, int [] states, int currentState, int special, boolean ghost){
        super(name, game, x, y, width, height, Game.OBSTACLES_WEIGHT, new Color(0,0,0,1));
        this.states = states;
        this.currentState = currentState;
        this.special = special;
        setType();
        textureUnit = Game.TEXTURE_TARGETS;
        program = this.game.imageProgram;
        isMovable = false;
        isGhost = ghost;

        this.setDrawInfo();

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
        final Target self = this;
        desapearAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                self.isVisible = false;
            }
        });
    }


    public void onBallCollision(){

        int points = 100;
        for (int i = 0; i < game.objectivePanel.blueBalls+1; i++){
            points *= 2;
        }

        this.decayState(points);

    };

    public void renderPoints(float[] matrixView, float[] matrixProjection){
        //Log.e("target", "render points ");
        if (this.pointsObject != null) {
            this.pointsObject.alpha = this.pointsAlpha;
            this.pointsObject.render(matrixView, matrixProjection);
        }
    }

    public void showPoints(int points){

        this.pointsToShow = points;
        this.pointsObject = new Point("points", this.game, x + (width/2f),y + (height/2f) ,height * 1.5f);
        addChild(pointsObject);
        pointsObject.setValue(points);

        this.posYVariation = 0;
        if (this.showPointsStateAnim != null) {
            this.showPointsStateAnim.start();
        }

        //Log.e("target", "3");
        if (this.showPointsAlphaAnim != null) {
            this.showPointsAlphaAnim.start();
            Utils.createSimpleAnimation(pointsObject, "translateX", "translateX", POINTS_DURATION, 0f, game.gameAreaResolutionX*0.025f).start();
            Utils.createSimpleAnimation(pointsObject, "translateY", "translateY", POINTS_DURATION, 0f, -game.gameAreaResolutionX*0.02f).start();
        }
    }

    public void decayState(int points){

        this.game.soundPool.play(this.game.soundDestroyTarget, 1, 1, 0, 0, 1);

        this.game.scorePanel.setValue(this.game.scorePanel.value + points,  true, 500, false);

        this.currentState -= 1;

        // O alvo especial não tem estado, uma vez atingido ele ativa sua habilidade especial.
        if (this.special != 0){
            this.currentState = 0;
        }

        if (currentState == 0){
            alive = false;
        }

        setType();

        showPoints(points);


        //Log.e("target", "4");
        if (this.isGhost){
            if (this.ghostAlphaAnim != null) {
                this.ghostAlphaAnim.start();
            }

            if (this.states[this.currentState] == 0){
                this.isCollidable = false;
                this.isMovable = false;
                this.isSolid = false;
            }
        } else {
            if (this.states[this.currentState] == 0){
                this.isCollidable = false;
                this.isMovable = false;
                this.isSolid = false;

                //Log.e("target", "5");
                this.desapearAnim.start();
                //Log.e("target", "6");
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
        //0 - 206
        // 208 -414
        // 416 - 622
        // 624 - 830

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
