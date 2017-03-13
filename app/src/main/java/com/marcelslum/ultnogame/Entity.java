package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Entity{
    
    
    
    final public static int TYPE_OTHER = 0;
    final public static int TYPE_BALL = 1;
    final public static int TYPE_BAR = 2;
    final public static int TYPE_TARGET = 3;
    final public static int TYPE_OBSTACLE = 4;
    

    public final static String TAG = "Entity";
    final public static int SHOW_POINTS_ON = 1;
    final public static int SHOW_POINTS_OFF = 0;

    
    public int type;
    public String name;
    public float x;
    public float y;
    public float positionX;
    public float positionY;
    public float previousPositionX;
    public float previousPositionY;
    public float rotateAngle = 0f;
    public float translateX = 0f;
    public float translateY = 0f;
    public float scaleX = 0f;
    public float scaleY = 0f;
    public float accumulatedRotate = 0f;
    public float accumulatedTranslateX = 0f;
    public float accumulatedTranslateY = 0f;
    public float accumulatedScaleX = 1f;
    public float accumulatedScaleY = 1f;

    public float numberForAnimation;
    public float numberForAnimation2;
    public float numberForAnimation3;

    public float time = 0f;
    public boolean timeVar = true;

    public Color color;
    
    public float animTranslateX = 0f;
    public float animTranslateY = 0f;
    public float animScaleX = 1f;
    public float animScaleY = 1f;
    public float animRotateAngle = 0f;
    
    public float dX;
    public float dY;
    
    public float alpha = 1f;
    
    public boolean isCollidable = false;
    public boolean isVisible = true;
    public boolean isMovable = false;
    public boolean isSolid = false;
    public boolean isBlocked = false;
    public boolean isPressed = false;
    public boolean isFree = true;
    
    public Entity parent;
    public ArrayList<Entity> childs;

    public ArrayList<Animation> animations;
    public InteractionListener listener;

    public float pointsAlpha;
    public float ghostAlpha;
    public int showPointsState = SHOW_POINTS_OFF;

    public SatCircle circleData;
    public SatPolygon polygonData;

    public float[] verticesData;
    public short[] indicesData;
    public float[] uvsData;
    public float[] colorsData;

    public boolean isLineGL = false;
    public int lineWidth = 1;

    public FloatBuffer verticesBuffer;
    public FloatBuffer uvsBuffer;
    public ShortBuffer indicesBuffer;
    public FloatBuffer colorsBuffer;

    public float[] matrixModel = new float[16];
    public float[] mRotationMatrix = new float[16];
    public float[] matrixTemp = new float[16];

    public Program program;
    public int textureId = -1;
    public int special;

    public void setProgram(Program program){
        this.program = program;
    }

    public void setTextureId(int textureId){
        this.textureId = textureId;
    }

    Entity(String name, float x, float y, int type) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.type = type;
        previousPositionX = x;
        previousPositionY = y;
        animations = new ArrayList<>();
        childs = new ArrayList<>();
        checkTransformations(false);
    }
    
    public void initializeData(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        if (verticesSize > 0){
            if (this.verticesData == null || this.verticesData.length != verticesSize){
                this.verticesData = new float[verticesSize];                
            }
        }
        if (indicesSize > 0){
            if (this.indicesData == null || this.indicesData.length != indicesSize){
                this.indicesData = new short[indicesSize];                
            } 
            
        }
        if (uvsSize > 0){
            if (this.uvsData == null || this.uvsData.length != uvsSize){
                this.uvsData = new float[uvsSize];                
            }
        }
        if (colorsSize > 0){
            if (this.colorsData == null || this.colorsData.length != colorsSize){
                this.colorsData = new float[colorsSize];                
            }
        }
    }    
    
    
    
    public void applyAnimation(String parameter, float value) {
        if (this.childs != null) {
            for (int i = 0; i < this.childs.size(); i++) {
                this.childs.get(i).applyAnimation(parameter, value);
            }
        }
        switch (parameter) {
            case "rotate":
                animRotateAngle = value;
            case "rotateAngle":
                animRotateAngle = value;
            case "translateX":
                animTranslateX = value;
                break;
            case "animTranslateX":
                animTranslateX = value;
                break;
            case "translateY":
                animTranslateY = value;
                break;
            case "animTranslateY":
                animTranslateY = value;
                break;
            case "scaleX":
                animScaleX = value;
                break;
            case "animScaleX":
                animScaleX = value;
                break;
            case "scaleY":
                animScaleY = value;
                break;
            case "animScaleY":
                animScaleY = value;
                break;
            case "alpha":
                alpha = value;
                break;
            case "numberForAnimation":
                numberForAnimation = value;
                break;
            case "numberForAnimation2":
                numberForAnimation2 = value;
                break;
            case "numberForAnimation3":
                numberForAnimation3 = value;
                break;
            case "showPointsState":
                if (value == 1f){
                    showPointsState = SHOW_POINTS_ON;
                } else {
                    showPointsState = SHOW_POINTS_OFF;
                }
                break;
            case "pointsAlpha":
                pointsAlpha = value;
                break;
            case "ghostAlpha":
                ghostAlpha = value;
                break;
            default:
                break;
        }
    }

    void clearAnimations() {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).started && !this.animations.get(i).name.equals("ballInvencible")){
                animations.get(i).stopAndConclude();
            }
        }
        animTranslateX = 0;
        animTranslateY = 0;
        animScaleX = 1f;
        animScaleY = 1f;
        alpha = 1;

        for (int i = 0; i < childs.size(); i++){
            childs.get(i).clearAnimations();
        }
    }



    void reduceAlpha(int duration, float finalValue){
        Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue).start();
    }

    void reduceAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue, animationListener).start();
    }

    void increaseAlpha(int duration, float finalValue){

        Utils.createSimpleAnimation(this, "increaseAlpha", "alpha", duration, alpha, finalValue).start();
    }

    void increaseAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Utils.createSimpleAnimation(this, "increaseAlpha", "alpha", duration, alpha, finalValue, animationListener).start();
    }

    public void checkAnimations() {
        if (parent != null) {
            return;
        }
        if (childs != null) {
            for (int i = 0; i < childs.size(); i++) {
                for (int a = 0; a < childs.get(i).animations.size(); a++) {
                    //console.log(child.animations[a].name, " ", child.animations[a].started);
                    if (childs.get(i).animations.get(a).started) {
                        childs.get(i).animations.get(a).doAnimation();
                    }
                }
            }
        }

        for (int i = 0; i < animations.size(); i++) {
            //Log.e("entity", "animation started " + this.animations.get(i).started + " na entidade "+this.name);
            if (animations.get(i).started) {
                //Log.e("entity", "animation started na entidade "+this.name);
                animations.get(i).doAnimation();
            }
        }
    }

    public void translate(float translateX, float translateY) {
        this.translateX = translateX;
        this.translateY = translateY;
    }
    
    public void rotate(float rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public void scale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    
    public void checkTransformations(boolean updatePrevious){
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).checkTransformations(updatePrevious);
        }

        if (isFree){
            if (scaleX != 0 || scaleY != 0){
                if (polygonData != null){
                    polygonData = null;
                }
            }
            accumulatedTranslateX += translateX;
            accumulatedTranslateY += translateY;
            accumulatedRotate += rotateAngle;    
            accumulatedScaleX += scaleX;
            accumulatedScaleY += scaleY;
            translateX = 0f;
            translateY = 0f;
            rotateAngle = 0f;
            scaleX = 0f;
            scaleY = 0f;
        }

        if (updatePrevious) {
            previousPositionX = positionX;
            previousPositionY = positionY;
        }
        
        // TODO considerar rotação?
        if (accumulatedScaleX != 1f){
            positionX = x + accumulatedTranslateX - (getTransformedWidth()-getWidth())/2f;
        } else {
            positionX = x + accumulatedTranslateX;
        }
        
        if (accumulatedScaleY != 1f){
            positionY = y + accumulatedTranslateY - (getTransformedHeight()-getHeight())/2f;
        } else {
            positionY = y + accumulatedTranslateY;
        }
    }

    public float getTransformedWidth() {
        return 0f;
    }

    public float getTransformedHeight() {
        return 0f;
    }
    
    public float getWidth() {
        return 0f;
    }

    public float getHeight() {
        return 0f;
    }

    public void setMatrixModel(){
        Matrix.setIdentityM(matrixModel, 0);
        if (accumulatedScaleX != 1f || accumulatedScaleY != 1f) {
            Matrix.translateM(matrixModel, 0, positionX + animTranslateX + (getTransformedWidth() - getWidth()) / 2f,
                    positionY + animTranslateY  + (getTransformedHeight() - getHeight()) / 2f, 0);
        } else {
            Matrix.translateM(matrixModel, 0, positionX + animTranslateX,
                    positionY + animTranslateY, 0);
        }
        if (accumulatedRotate != 0 || animRotateAngle != 0) {
            float width = getWidth();
            float height = getHeight();
            Matrix.translateM(matrixModel, 0, width/2f, height/2f, 0);
            Matrix.setRotateM(mRotationMatrix, 0, accumulatedRotate + animRotateAngle, 0f, 0f, 1f);
            matrixTemp = matrixModel.clone();
            Matrix.multiplyMM(matrixModel, 0, matrixTemp, 0, mRotationMatrix, 0);
            Matrix.translateM(matrixModel, 0, -width/2f, -height/2f, 0);
        }

        //if (name == "velocityRectangle"){

            //Log.e("Entity", "animScaleX "+animScaleX);
        //}

        if (animScaleX != 1f || animScaleY != 1f || accumulatedScaleX != 1f || accumulatedScaleY != 1f) {
            float width = getWidth();
            float height = getHeight();
            Matrix.translateM(this.matrixModel, 0, (width)/2, (height)/2, 0);
            Matrix.scaleM(matrixModel, 0, accumulatedScaleX * animScaleX, accumulatedScaleY * animScaleY, 0);
            Matrix.translateM(this.matrixModel, 0, -(width)/2, -(height)/2, 0);
        }
    }

    public void prepareRender(float[] matrixView, float[] matrixProjection){
        checkAnimations();
        if (isVisible){
            render(matrixView, matrixProjection);
        }
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        //if (name == "messageStars" && MessagesHandler.messageStars != null) {
        //Log.e("entity", "messageStars "+ MessagesHandler.messageStars.isVisible);
        //}
        //Log.e("entity", "rendering wind");}

        if (!isVisible){
            return;
        }

        setMatrixModel();

        GLES20.glUseProgram(program.get());

        // get handle to vertex shader's vPosition member and add vertices
        int av4_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av4_vertices");
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, this.verticesBuffer);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        //Log.e("render", " ");

        int av2_uvHandle = -1;
        if (this.textureId != -1) {
            // Get handle to texture coordinates location and load the texture uvs
            av2_uvHandle = GLES20.glGetAttribLocation(program.get(), "av2_uv");
            GLES20.glVertexAttribPointer(av2_uvHandle, 2, GLES20.GL_FLOAT, false, 0, this.uvsBuffer);
            GLES20.glEnableVertexAttribArray(av2_uvHandle);
        }

        int av4_colorsHandle = -1;
        if (this.colorsBuffer != null){
            //Log.e("tag "+this.name, "tem cor");
            av4_colorsHandle = GLES20.glGetAttribLocation(program.get(), "av4_colors" );
            GLES20.glVertexAttribPointer ( av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, this.colorsBuffer);
            GLES20.glEnableVertexAttribArray ( av4_colorsHandle );
        }

        int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");
        GLES20.glUniform1f(uf_alphaHandle, alpha);
        
        // Get handle to shape's transformation matrix and add our matrix
        int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
        GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
        //Log.e("render", " ");

        // Get handle to shape's transformation matrix and add our matrix
        int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
        GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
        //Log.e("render", " ");

        // Get handle to shape's transformation matrix and add our matrix
        int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");
        GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, this.matrixModel, 0);
        //Log.e("render", " ");

        // TODO verificar se a inversão do eixo y considera o offset

        if (program == Game.windProgram || program == Game.specialBallProgram){
            float time = ((Utils.getTime() - Game.initTime)) / 1000f;
            if (program == Game.windProgram) {
               time = (time - (((float)Math.floor(time/color.b)) * color.b))/color.b;//Log.e("entity", "time " + time);
            }


            int uf_time = GLES20.glGetUniformLocation(this.program.get(), "uf_time");
            GLES20.glUniform1f(uf_time, (float)time);
            int uv2_resolution = GLES20.glGetUniformLocation(this.program.get(), "uv2_resolution");
            GLES20.glUniform2f(uv2_resolution, Game.effectiveScreenWidth, Game.effectiveScreenHeight);
            // TODO adicionar screen offset ao wind
        }

        if (this.program == Game.imageColorizedFxProgram){

            float variation;
            if (Game.ballCollidedFx > 0) {
                if (Game.ballCollidedFx > 30){
                    variation = 0.007f;
                } else if (Game.ballCollidedFx > 20){
                    variation = 0.0055f;
                } else if (Game.ballCollidedFx > 10){
                    variation = 0.004f;
                } else{
                    variation = 0.002f;
                }

                if (timeVar) {
                    time += variation;
                    if (time > 0.001f) {
                        timeVar = false;
                    }
                } else {
                    time -= variation;
                    if (time < -0.001f) {
                        timeVar = true;
                    }
                }
            }
            int uf_timeHandle = GLES20.glGetUniformLocation(this.program.get(), "uf_time");
            GLES20.glUniform1f(uf_timeHandle, time);
        }
        // TODO verificar se a inversão do eixo y considera o offset



        if (textureId != -1) {
            // Get handle to textures locations
            int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
            GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
        }

        if (isLineGL) {
            GLES20.glLineWidth(lineWidth);
            GLES20.glDrawElements(GLES20.GL_LINES, this.indicesData.length, GLES20.GL_UNSIGNED_SHORT, this.indicesBuffer);
        } else {
            GLES20.glDrawElements(GLES20.GL_TRIANGLES, this.indicesData.length, GLES20.GL_UNSIGNED_SHORT, this.indicesBuffer);
        }

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(av4_verticesHandle);
        if (av2_uvHandle != -1) {
            GLES20.glDisableVertexAttribArray(av2_uvHandle);
        }
        if (av4_colorsHandle != -1){
            GLES20.glDisableVertexAttribArray(av4_colorsHandle);
        }

        //if (name == "specialBall")
            //Log.e("entity", GLES20.glGetProgramInfoLog(program.get()));
            //Log.e("entity2", GLES20.glGetProgram(program.get()));
    }

    public void display(){
        this.isVisible = true;
    }

    public void verifyListener(){
        if (isBlocked){
            return;
        }
        if (this.getListener() != null) {
            //if (name == "bottomTextBox") {
                //Log.e("listener", "verificando listener " + name);
            //}
            this.getListener().verify();
        }
        for (int i = 0; i < this.childs.size(); i++){
            this.childs.get(i).verifyListener();
        }
    }

    public void addChild(Entity child){
        for (int i = 0; i < childs.size(); i++) {
            if(this.childs.get(i).name == child.name){
                this.childs.set(i, child);
                return;
            }
        }
        this.childs.add(child);
        child.parent = this;
    }

    public void block(){
        this.isBlocked = true;
    }

    public void setDrawInfo(){


    }

    public void unblockAndDisplay(){
        unblock();
        display();
    }

    public void blockAndClearDisplay(){
        block();
        clearDisplay();
    }


    public void unblock(){
        this.isBlocked = false;
    }

    public void clearDisplay(){
        isVisible = false;
    }

    public void addAnimation(Animation animation){
        for (int i = 0; i < this.animations.size(); i++){
            if (this.animations.get(i).name == animation.name){
                this.animations.set(i, animation);
                return;
            }
        }
        this.animations.add(animation);
    }

    public Animation getAnimationByName(String name){
        for (int i = 0; i < animations.size(); i++){
            if (animations.get(i).name == name){
                return animations.get(i);
            }
        }
        return null;
    }

    public void setListener(InteractionListener interactionListenerListener){
        listener = interactionListenerListener;
    }

    public void updateQuatreeData() {
    }

    public void setSatData() {
    }

    public InteractionListener getListener() {
        return listener;
    }

    public void reduceAlphaAndClearDisplay(int duration) {
        reduceAlpha(duration, 0f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                clearDisplay();
                clearAnimations();
                alpha = 1f;
            }
        });
    }
}
