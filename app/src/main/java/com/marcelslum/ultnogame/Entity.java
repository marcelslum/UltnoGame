package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Entity{

    protected final int SIZEOF_FLOAT = 4;
    protected final int SIZEOF_SHORT = 2;

    public boolean inUse = false;
    
    final public static int ATTRIB_POS = 0;
    final public static int ATTRIB_UV = 1;
    final public static int ATTRIB_COLOR = 2;

    
    public static int currentBoundedTextureId;

    public TextureData textureData;

    protected int [] vbo;
    protected int [] ibo;

    final public static int TYPE_OTHER = 0;
    final public static int TYPE_BALL = 1;
    final public static int TYPE_BAR = 2;
    final public static int TYPE_TARGET = 3;
    final public static int TYPE_OBSTACLE = 4;
    final public static int TYPE_BACKGROUND = 5;
    final public static int TYPE_PANEL = 6;
    final public static int TYPE_PARTICLE = 7;
    final public static int TYPE_BUTTON = 8;
    final public static int TYPE_LEFT_BORDER = 9;
    final public static int TYPE_RIGHT_BORDER = 10;
    final public static int TYPE_TOP_BORDER = 11;
    final public static int TYPE_BOTTOM_BORDER = 12;
    final public static int TYPE_IMAGE = 13;
    final public static int TYPE_LINE = 14;
    final public static int TYPE_LIST = 15;
    final public static int TYPE_MENU = 16;
    final public static int TYPE_POINT = 17;
    final public static int TYPE_SELECTOR = 18;
    final public static int TYPE_SPECIAL_BALL = 19;
    final public static int TYPE_TEXT = 20;
    final public static int TYPE_TEXT_BOX = 21;
    final public static int TYPE_WIND = 22;
    final public static int TYPE_WINDOW_GAME = 23;
    final public static int TYPE_TARGET_GROUP = 24;
    final public static int TYPE_TEXT_VIEW = 25;
    
    

    public final static String TAG = "Entity";
    final public static int SHOW_POINTS_ON = 1;
    final public static int SHOW_POINTS_OFF = 0;

    //private static IntBuffer vao;

    
    public boolean uvChangeFlag = false;
    public boolean colorChangeFlag = false;
    public boolean verticesChangeFlag = false;
    
    public int type;
    public String name;
    public float centerX = 0f;
    public float centerY = 0f;
    public float maxWidth = 0f;
    public float maxHeight = 0f;
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

    public void setData(){
        previousPositionX = x;
        previousPositionY = y;
        animations = new ArrayList<>();
        childs = new ArrayList<>();
        checkTransformations(false);
    }

    public void updateTextureData(TextureData td){
        textureData = td;
        setUvData();
    }

    public void setUvData(){
        Utils.insertRectangleUvData(uvsData, 0, textureData);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }
        
    public void initializeData(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        if (verticesSize > 0){
            if (verticesData == null || verticesData.length != verticesSize){
                verticesData = new float[verticesSize];
            }
        }
        if (indicesSize > 0){
            if (indicesData == null || indicesData.length != indicesSize){
                indicesData = new short[indicesSize];
            } 
            
        }
        if (uvsSize > 0){
            if (uvsData == null || uvsData.length != uvsSize){
                uvsData = new float[uvsSize];
            }
        }
        if (colorsSize > 0){
            if (colorsData == null || colorsData.length != colorsSize){
                colorsData = new float[colorsSize];
            }
        }
    }    

    public void applyAnimation(String parameter, float value, boolean applyOnChilds) {
        if (childs != null) {
            for (int i = 0; i < childs.size(); i++) {
                childs.get(i).applyAnimation(parameter, value, applyOnChilds);
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
                colorChangeFlag = true;
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
            case "ghostAlpha":
                ghostAlpha = value;
                colorChangeFlag = true;
                break;
            default:
                break;
        }
    }

    // TODO trocar para cleanAnimations
    void cleanAnimations() {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).started && !this.animations.get(i).name.equals("ballInvencible")){
                animations.get(i).stopAndConclude();
            }
        }
        animTranslateX = 0;
        animTranslateY = 0;
        animScaleX = 1f;
        animScaleY = 1f;

        for (int i = 0; i < childs.size(); i++){
            childs.get(i).cleanAnimations();
        }
    }
    
    void clean(){
        inUse = false;
        textureData = null;
        uvChangeFlag = false;
        colorChangeFlag = false;
        verticesChangeFlag = false;
        type = -1;
        x = 0f;
        y = 0f;
        positionX = 0f;
        positionY = 0f;
        previousPositionX = 0f;
        previousPositionY = 0f;
        rotateAngle = 0f;
        translateX = 0f;
        translateY = 0f;
        scaleX = 0f;
        scaleY = 0f;
        accumulatedRotate = 0f;
        accumulatedTranslateX = 0f;
        accumulatedTranslateY = 0f;
        accumulatedScaleX = 1f;
        accumulatedScaleY = 1f;
        dX = 0f;
        dY = 0f;
        isCollidable = false;
        isVisible = true;
        isMovable = false;
        isSolid = false;
        isBlocked = false;
        isPressed = false;
        isFree = true;
        parent = null;
        childs.clear();
        animations.clear();
        listener = null;
        verticesData = null;
        indicesData = null;
        uvsData = null;
        colorsData = null;
        isLineGL = false;
        lineWidth = 1;
        verticesBuffer = null;
        uvsBuffer = null;
        indicesBuffer = null;
        colorsBuffer = null;
        program = null;
        textureId = -1;

        //Log.e(TAG, "cleaning "+ name);
        cleanAnimations();
        
        if (color != null) {
            color.r = 0;
            color.g = 0;
            color.b = 0;
            color.a = 0;
        }

        alpha = 1f;
        x = 0f;
        y = 0f;
        positionX = 0f;
        positionY = 0f;
        previousPositionX = 0f;
        previousPositionY = 0f;
        rotateAngle = 0f;
        translateX = 0f;
        translateY = 0f;
        scaleX = 0f;
        scaleY = 0f;
        accumulatedRotate = 0f;
        accumulatedTranslateX = 0f;
        accumulatedTranslateY = 0f;
        accumulatedScaleX = 1f;
        accumulatedScaleY = 1f;
    }

    void reduceAlpha(int duration, float finalValue){
        Utils.createSimpleAnimation(this, "reduceAlpha1", "alpha", duration, alpha, finalValue).start();
    }

    void reduceAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Utils.createSimpleAnimation(this, "reduceAlpha2", "alpha", duration, alpha, finalValue, animationListener).start();
    }

    void increaseAlpha(int duration, float finalValue){
        Utils.createSimpleAnimation(this, "increaseAlpha", "alpha", duration, alpha, finalValue).start();
    }

    void increaseAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Utils.createSimpleAnimation(this, "increaseAlpha", "alpha", duration, alpha, finalValue, animationListener).start();
    }

    public int checkAnimations() {

        int count = 0;

        if (parent != null) {
            return - 1;
        }
        if (childs != null) {
            for (int i = 0; i < childs.size(); i++) {
                for (int a = 0; a < childs.get(i).animations.size(); a++) {
                    if (childs.get(i).animations.get(a).started) {
                        childs.get(i).animations.get(a).doAnimation();
                    }
                }
            }
        }

        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).started) {
                count += 1;
                animations.get(i).doAnimation();
            }
        }

        return count;
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

        checkCenter();

    }

    public void checkCenter(){

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

        /*
        if (name == "bar"){
            //Log.e(TAG, "BAR-----------------------");
            //Log.e(TAG, "accumulatedScaleX " + accumulatedScaleX);
            //Log.e(TAG, "accumulatedScaleY " + accumulatedScaleY);
            //Log.e(TAG, "positionX " + positionX);
            //Log.e(TAG, "animTranslateX " + animTranslateX);
            //Log.e(TAG, "getTransformedWidth() " + getTransformedWidth());
            //Log.e(TAG, "getWidth() " + getWidth());
            //Log.e(TAG, "positionY " + positionY);
            //Log.e(TAG, "animTranslateY " + animTranslateY);
            //Log.e(TAG, "getTransformedHeight() " + getTransformedHeight());
            //Log.e(TAG, "getHeight() " + getHeight());
        }
        if (name == "shine"){
            //Log.e(TAG, "SHINE-----------------------");
            //Log.e(TAG, "accumulatedScaleX " + accumulatedScaleX);
            //Log.e(TAG, "accumulatedScaleY " + accumulatedScaleY);
            //Log.e(TAG, "positionX " + positionX);
            //Log.e(TAG, "animTranslateX " + animTranslateX);
            //Log.e(TAG, "getTransformedWidth() " + getTransformedWidth());
            //Log.e(TAG, "getWidth() " + getWidth());
            //Log.e(TAG, "positionY " + positionY);
            //Log.e(TAG, "animTranslateY " + animTranslateY);
            //Log.e(TAG, "getTransformedHeight() " + getTransformedHeight());
            //Log.e(TAG, "getHeight() " + getHeight());
        }
        */

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

        if (isVisible){
            checkAnimations();
            render(matrixView, matrixProjection);
        }
    }
    
    public static void createVao(){
        /*
        GLES30.glGenVertexArrays( 1, vao, 0 );
        GLES30.glBindVertexArray(vao[0]);

        // Set the vertex attributes as usual
        GLES30.glEnable(ATTRIB_POS);
        GLES30.glVertexAttribPointer(ATTRIB_POS, 3, GLES30.GL_FLOAT, false, 32, 0 );

        GLES30.glEnableVertexAttribArray(ATTRIB_UV);
        GLES30.glVertexAttribPointer(ATTRIB_UV, 2, GLES30.GL_FLOAT, false, 32, 12 );

        GLES30.glEnableVertexAttribArray(ATTRIB_COLOR);
        GLES30.glVertexAttribPointer(ATTRIB_COLOR, 3, GLES30.GL_FLOAT, false, 32, 20 );

        // Unbind the VAO to avoid accidentally overwriting the state
        // Skip this if you are confident your code will not do so
        GLES30.glBindVertexArray(0);

        GLES30.glGenBuffers ( 3, mVBOIds, 0 );
        */
        
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        
        if (!isVisible){
            return;
        }

        setMatrixModel();
        
       if (vbo == null || vbo.length == 0){
            
                    GLES20.glUseProgram(program.get());

                    // get handle to vertex shader's vPosition member and add vertices
                    int av4_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av4_vertices");
                    GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, verticesBuffer);
                    GLES20.glEnableVertexAttribArray(av4_verticesHandle);
                    //Log.e("render", " ");

                    int av2_uvHandle = -1;
                    if (this.textureId != -1) {
                        // Get handle to texture coordinates location and load the texture uvs
                        av2_uvHandle = GLES20.glGetAttribLocation(program.get(), "av2_uv");
                        GLES20.glVertexAttribPointer(av2_uvHandle, 2, GLES20.GL_FLOAT, false, 0, uvsBuffer);
                        GLES20.glEnableVertexAttribArray(av2_uvHandle);
                    }

                    int av4_colorsHandle = -1;
                    if (this.colorsBuffer != null){
                        //Log.e("tag "+this.name, "tem cor");
                        av4_colorsHandle = GLES20.glGetAttribLocation(program.get(), "av4_colors" );
                        GLES20.glVertexAttribPointer ( av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, colorsBuffer);
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


                    if (program == Game.specialBallProgram){
                        float time = ((Utils.getTime() - Game.initTime)) / 1000f;
                        int uf_time = GLES20.glGetUniformLocation(this.program.get(), "uf_time");
                        GLES20.glUniform1f(uf_time, time);
                    }

                    if (this.program == Game.imageColorizedFxProgram){
/*
                        float variation;
                        if (Brick.ballCollidedFx > 0) {
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
                        */
                        int uf_timeHandle = GLES20.glGetUniformLocation(this.program.get(), "uf_time");
                        GLES20.glUniform1f(uf_timeHandle, time);
                    }
                    // TODO verificar se a inversão do eixo y considera o offset



                    if (textureId != -1) {
                        //if (textureId != currentBoundedTextureId){
                            //currentBoundedTextureId = textureId;
                            // Get handle to textures locations
                            int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
                            GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
                        //}
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

        } else {

            if (program == Game.vertex_e_uv_com_alpha_program) {

                GLES20.glUseProgram(program.get());

                int av2_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av2_vertices");
                int av3_uvHandle = GLES20.glGetAttribLocation(program.get(), "av3_uv");

                int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
                int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
                int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");

                int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");

                GLES20.glUniform1f(uf_alphaHandle, alpha);
                GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
                GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
                GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);

                if (textureId != currentBoundedTextureId) {
                    currentBoundedTextureId = textureId;
                    int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
                    GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
                }

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
                GLES20.glEnableVertexAttribArray(av2_verticesHandle);
                GLES20.glVertexAttribPointer(av2_verticesHandle, 2, GLES20.GL_FLOAT, false, 0, 0);

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glEnableVertexAttribArray(av3_uvHandle);
                GLES20.glVertexAttribPointer(av3_uvHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

                GLES20.glDisableVertexAttribArray(av2_verticesHandle);
                GLES20.glDisableVertexAttribArray(av3_uvHandle);

                checkGLError();

            } else if (program == Game.vertex_e_color) {

                GLES20.glUseProgram(program.get());

                int av2_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av2_vertices");
                int av4_colorHandle = GLES20.glGetAttribLocation(program.get(), "av4_color");

                int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
                int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
                int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");

                int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");

                GLES20.glUniform1f(uf_alphaHandle, alpha);
                GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
                GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
                GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);


                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
                GLES20.glEnableVertexAttribArray(av2_verticesHandle);
                GLES20.glVertexAttribPointer(av2_verticesHandle, 2, GLES20.GL_FLOAT, false, 0, 0);

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glEnableVertexAttribArray(av4_colorHandle);
                GLES20.glVertexAttribPointer(av4_colorHandle, 4, GLES20.GL_FLOAT, false, 0, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

                GLES20.glDisableVertexAttribArray(av2_verticesHandle);
                GLES20.glDisableVertexAttribArray(av4_colorHandle);

                checkGLError();

            } else if (program == Game.instancing) {

                GLES20.glUseProgram(program.get());

                int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
                int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
                int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");

                int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");

                GLES20.glUniform1f(uf_alphaHandle, alpha);
                GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
                GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
                GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);

                if (textureId != currentBoundedTextureId) {
                    currentBoundedTextureId = textureId;
                    int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
                    GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
                }

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
                GLES20.glVertexAttribPointer( 0, 2, GLES20.GL_FLOAT, false, 0, 0 );

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glVertexAttribPointer( 1, 3, GLES20.GL_FLOAT, false, 0, 0 );

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

                //GLES20.glDisableVertexAttribArray(av2_verticesHandle);
                //GLES20.glDisableVertexAttribArray(av3_uvHandle);

                checkGLError();

            } else {
            
                GLES20.glUseProgram(program.get());

                int av4_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av4_vertices");
                int av2_uvHandle = GLES20.glGetAttribLocation(program.get(), "av2_uv");


                int av4_colorsHandle = -1;
                if (colorsBuffer != null){
                    av4_colorsHandle = GLES20.glGetAttribLocation(program.get(), "av4_colors" );
                }

                int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");
                int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
                int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
                int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");

                GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
                GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
                GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);
                GLES20.glUniform1f(uf_alphaHandle, alpha);

                 if (textureId != -1) {
                    if (textureId != currentBoundedTextureId){
                        currentBoundedTextureId = textureId;
                        // Get handle to textures locations
                        int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
                        GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
                    }
                }

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
                GLES20.glEnableVertexAttribArray(av4_verticesHandle);
                GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glEnableVertexAttribArray(av2_uvHandle);
                GLES20.glVertexAttribPointer(av2_uvHandle, 2, GLES20.GL_FLOAT, false, 0, 0);

                if (this.colorsBuffer != null){
                    GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
                    GLES20.glEnableVertexAttribArray(av4_colorsHandle);
                    GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);
                }

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);

                GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

                // Disable vertex array
                GLES20.glDisableVertexAttribArray(av4_verticesHandle);
                if (av2_uvHandle != -1) {
                    GLES20.glDisableVertexAttribArray(av2_uvHandle);
                }
                if (av4_colorsHandle != -1){
                    GLES20.glDisableVertexAttribArray(av4_colorsHandle);
                }
            }
        }
    }

    public void checkGLError() {
        int error;

        //Log.e(TAG, ": glError ");

        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            //Log.e(TAG, ": glError " + error);
        }
    }

    public void display(){
        isVisible = true;
        //for (int i = 0; i < childs.size(); i++){
        //    childs.get(i).display();
        //}
    }

    public void verifyListener(){
        if (isBlocked){
            return;
        }
        if (this.getListener() != null) {
            //if (name == "aboutTextView") {
            //    //Log.e("listener", "verificando listener " + name);
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
        //for (int i = 0; i < this.childs.size(); i++){
        //    this.childs.get(i).clearDisplay();
        //}
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
                cleanAnimations();
                alpha = 1f;
            }
        });
    }
}
