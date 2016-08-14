package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Target extends Rectangle {



    public int special;
    public int type; // 0->preto; 1->azul; 2->verde; 3->vermelho;
    public int [] states;
    public int currentState;
    private int pointsToShow;
    private int posYVariation;

    Animation showPointsStateAnim;
    Animation showPointsAlphaAnim;
    Animation ghostAlphaAnim;
    Animation desapearAnim;
    private boolean isGhost;

    Point pointsObject;

    Target(String name, Game game, float x, float y, float width, float height, int weight){
        super(name, game, x, y, width, height, weight, new Color(0,0,0,1));
        this.type = 0;
        this.textureUnit = 2;
        this.program = this.game.imageProgram;
        this.setDrawInfo();
        this.isCollidable = true;
        this.isSolid = true;

        ArrayList<float[]> valuesAnimationShowPoints = new ArrayList<>();
        valuesAnimationShowPoints.add(new float[]{0f,1f});
        valuesAnimationShowPoints.add(new float[]{1f,0f});
        showPointsStateAnim = new Animation(this, "showPointsState", "showPointsState", 1000, valuesAnimationShowPoints, false, false);
        this.addAnimation(showPointsStateAnim);

        ArrayList<float[]> valuesAnimationShowPointsAlpha = new ArrayList<>();
        valuesAnimationShowPointsAlpha.add(new float[]{0f,1f});
        valuesAnimationShowPointsAlpha.add(new float[]{1f,0f});
        showPointsAlphaAnim = new Animation(this, "pointsAlpha", "pointsAlpha", 1000, valuesAnimationShowPointsAlpha, false, true);
        this.addAnimation(showPointsAlphaAnim);

        ArrayList<float[]> valuesAnimationGhostAlpha = new ArrayList<>();
        valuesAnimationGhostAlpha.add(new float[]{0f,1f});
        valuesAnimationGhostAlpha.add(new float[]{1f,0f});
        ghostAlphaAnim = new Animation(this, "ghostAlpha", "ghostAlpha", 1000, valuesAnimationGhostAlpha, false, true);
        this.addAnimation(ghostAlphaAnim);

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
        this.addAnimation(desapearAnim);
    }


    public void onBallCollision(){
        this.decayState(100);

    };

    public void renderPoints(float[] matrixView, float[] matrixProjection){

        //Log.e("target", "render points ");
        if (this.pointsObject != null) {
            this.pointsObject.alpha = this.pointsAlpha;
            this.pointsObject.render(matrixView, matrixProjection);
        }

    }

    public void decayState(int points){

        this.game.soundPool.play(this.game.soundDestroyTarget, 1, 1, 0, 0, 1);

        this.game.scorePanel.setValue(this.game.scorePanel.value + points,  true, 500, false);

        this.currentState -= 1;
        this.pointsToShow = points;
        this.pointsObject = new Point("points", this.game, x + (width/2f),y + (height/2f) ,height * 1.5f);
        pointsObject.setValue(points);

        this.posYVariation = 0;
        if (this.showPointsStateAnim != null) {
            //Log.e("target", "showPointsStateAnim ");
            this.showPointsStateAnim.start();
        }

        //Log.e("target", "3");
        if (this.showPointsAlphaAnim != null) {
            //Log.e("target", "showPointsAlphaAnim ");
            this.showPointsAlphaAnim.start();
        }

        // O alvo especial não tem estado, uma vez atingido ele ativa sua habilidade especial.
        if (this.special != 0){
            this.currentState = 0;
        }
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

    public void setType(int type){
        this.type = type;

        //0 - 206
        // 208 -414
        // 416 - 622
        // 624 - 830


        if (type == 2){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 624f/1024f, 830f/1024f);
        } else if (type == 1){
            Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 208f/1024f, 414f/1024f);
        } else if (type == 0){
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

        setType(type);
    }
}
