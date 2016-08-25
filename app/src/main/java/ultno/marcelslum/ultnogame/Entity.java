package ultno.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Entity {


    final public static int SHOW_POINTS_ON = 1;
    final public static int SHOW_POINTS_OFF = 0;

    int testeValue = 0;
    public String name;
    public Game game;
    public float x;
    public float y;
    public float rotateAngle;
    public float scaleX;
    public float scaleY;

    public float numberForAnimation;

    public float time = 0f;
    public boolean timeVar = true;

    public Color color;
    public float animTranslateX;
    public float animTranslateY;
    public float animScaleX = 1f;
    public float animScaleY = 1f;
    public float dX;
    public float dY;
    public float previousX;
    public float previousY;
    public float previousDX;
    public float previousDY;
    public float alpha = 1;
    public boolean isCollidable;
    public boolean isVisible;
    public boolean isMovable;
    public boolean isSolid;
    public boolean isBlocked;
    public boolean isPressed;
    public ArrayList<Animation> animations;
    public ArrayList<InteractionListener> listeners;
    public boolean isFree;
    public ArrayList<Entity> childs;
    public Entity parent;
    public RectangleM bounds;
    public RectangleM quadtreeData;
    public int updateBoundsState;
    public RectangleM satData;

    public float pointsAlpha;
    public int showPointsState = SHOW_POINTS_OFF;

    public SatCircle circleData;
    public SatPolygon polygonData;


    public float[] verticesData;
    public short[] indicesData;
    public float[] uvsData;
    public float[] colorsData;
    public float[] alphaData;

    public boolean isLineGL = false;
    public int lineWidth = 1;


    public FloatBuffer verticesBuffer;
    public FloatBuffer uvsBuffer;
    public ShortBuffer indicesBuffer;
    public FloatBuffer colorsBuffer;
    public FloatBuffer alphaBuffer;

    public float[] matrixModel = new float[16];
    public float[] mRotationMatrix = new float[16];
    public float[] matrixTemp = new float[16];


    public Program program;
    public int textureUnit = -1;
    public int special;


    public void setProgram(Program program){
        this.program = program;
    }

    public void setTextureUnit(int textureUnit){
        this.textureUnit = textureUnit;
    }

    Entity(String name, Game game, float x, float y) {
        this.name = name;
        this.game = game;
        this.x = x;
        this.y = y;
        previousX = x;
        previousY = y;
        previousDX = 0;
        previousDY = 0;
        scaleX = 1f;
        scaleY = 1f;
        alpha = 1;
        isCollidable = false;
        isVisible = true;
        isSolid = false;
        isMovable = false;
        isBlocked = false;
        isPressed = false;
        isFree = true;
        bounds = new RectangleM(0, 0, 0, 0);
        quadtreeData = new RectangleM(0, 0, 0, 0);
        satData = new RectangleM(0, 0, 0, 0);
        updateBoundsState = 0;
        scaleX = 1;
        scaleY = 1;
        animTranslateX = 0;
        animTranslateY = 0;
        animations = new ArrayList<Animation>();
        listeners = new ArrayList<InteractionListener>();
        childs = new ArrayList<>();
    }
    
    public void initializeData(int verticesSize, int indicesSize, int uvsSize, int colorsSize){
        
        if (verticesSize > 0){
            if (this.verticesData == null || this.verticesData.length != verticesSize){
                //Log.e("entity", "criando vertices data de "+this.name + " com o tamanho "+verticesSize);
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
    
    
     public void initializeData(int verticesSize, int indicesSize, int uvsSize, int colorsSize, int alphaSize){
        if (verticesSize > 0){
            if (this.verticesData == null || this.verticesData.length != verticesSize){
                //Log.e("entity", "criando vertices data de "+this.name + " com o tamanho "+verticesSize);
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
        
        if (alphaSize > 0){
            if (this.alphaData == null || this.alphaData.length != alphaSize){
                this.alphaData = new float[alphaSize];                
            }
        }
    }   

    public RectangleM getQuadtreeData() {
        this.updateQuatreeData();
        return this.quadtreeData;
    }

    public void applyAnimation(String parameter, float value) {
        if (this.childs != null) {
            for (int i = 0; i < this.childs.size(); i++) {
                this.childs.get(i).applyAnimation(parameter, value);
            }
        }

        switch (parameter) {
            case "translateX":
                this.animTranslateX = value;
                break;
            case "translateY":
                this.animTranslateY = value;
                break;
            case "scaleX":
                //Log.e("Entity", "ativando animação scaleX "+value);
                this.animScaleX = value;
                break;
            case "scaleY":
                this.animScaleY = value;
                break;
            case "alpha":
                //Log.e("Entity", "ativando animação alpha reduzindo para "+value);
                this.alpha = value;
                break;
            case "numberForAnimation":
                this.numberForAnimation = value;
            case "showPointsState":
                if (value == 1f){
                    //Log.e("entity", "showPointsState ON");
                    this.showPointsState = SHOW_POINTS_ON;
                } else {

                    //Log.e("entity", "showPointsState OFF");
                    this.showPointsState = SHOW_POINTS_OFF;
                }
                break;
            case "pointsAlpha":
                //Log.e("entity", "pointsAlpha ");
                this.pointsAlpha = value;
                break;
            default:
                break;
        }

    }

    public void resetAnimations() {
        for (int i = 0; i < this.animations.size(); i++) {
            if (this.animations.get(i).started && this.animations.get(i).name != "ballInvencible") {
                this.animations.get(i).stopAndConclude();
            }
        }

        this.animTranslateX = 0;
        this.animTranslateY = 0;
        this.animScaleX = 1f;
        this.animScaleY = 1f;
        this.alpha = 1;
        this.resetSpecificData();
    }

    public void resetSpecificData() {

    }

    public void reduceAlpha(int duration, float finalValue){
        Animation anim = Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue);
        anim.start();
    }

    public void reduceAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Animation anim = Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue);
        anim.setAnimationListener(animationListener);
        anim.start();
    }

    public void increaseAlpha(int duration, float finalValue){
        Animation anim = Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue);
        anim.start();
    }

    public void increaseAlpha(int duration, float finalValue, Animation.AnimationListener animationListener){
        Animation anim = Utils.createSimpleAnimation(this, "reduceAlpha", "alpha", duration, alpha, finalValue);
        anim.setAnimationListener(animationListener);
        anim.start();
    }

    public void verifyAnimations() {
        if (this.parent != null) {
            return;
        }
        if (this.childs != null) {
            for (int i = 0; i < this.childs.size(); i++) {
                for (int a = 0; a < this.childs.get(i).animations.size(); a++) {
                    //console.log(child.animations[a].name, " ", child.animations[a].started);
                    if (this.childs.get(i).animations.get(a).started) {
                        this.display();
                        this.childs.get(i).animations.get(a).doAnimation();
                    }
                }
            }
        }

        for (int i = 0; i < this.animations.size(); i++) {
            //Log.e("entity", "animation started " + this.animations.get(i).started + " na entidade "+this.name);
            if (this.animations.get(i).started) {
                //Log.e("entity", "animation started na entidade "+this.name);
                this.animations.get(i).doAnimation();
            }
        }
    }

    public void translate(float tx, float ty, boolean updatePrevious) {
        //Log.e("entity", "translate "+this.name + " isMovable "+isMovable + " isFree "+isFree);
        if (isMovable && isFree){
            if (updatePrevious) {
                this.previousX = this.x;
                this.previousY = this.y;
            }
            this.x += tx;
            this.y += ty;
        }
    }

    public void rotate(float angle) {
        this.rotateAngle += angle;
    }

    public void scale(float sx, float sy) {
        this.scaleX += sx;
        this.scaleY += sy;
    }

    // mustBeOverrided
    public float getMiddlePointX() {
        return 0f;
    }

    // mustBeOverrided
    public float getMiddlePointY() {
        return 0f;
    }

    // mustBeOverrided
    public float getWidth() {
        return 0f;
    }

    // mustBeOverrided
    public float getHeight() {
        return 0f;
    }

    public void setMatrixModel(){
        Matrix.setIdentityM(this.matrixModel, 0); // initialize to identity matrix
        Matrix.translateM(this.matrixModel, 0, this.x + animTranslateX, this.y + animTranslateY, 0);
        if (this.rotateAngle != 0) {
            Matrix.translateM(this.matrixModel, 0, getMiddlePointX(), getMiddlePointY(), 0);
            Matrix.setRotateM(mRotationMatrix, 0, this.rotateAngle, 0f, 0f, 1f);
            matrixTemp = matrixModel.clone();
            Matrix.multiplyMM(matrixModel, 0, matrixTemp, 0, mRotationMatrix, 0);
            Matrix.translateM(this.matrixModel, 0, -getMiddlePointX(), -getMiddlePointY(), 0);
        }
        if (animScaleX != 1 || animScaleY != 1) {
            float width = getWidth();
            float height = getHeight();

            Matrix.translateM(this.matrixModel, 0, (width)/2, +(height)/2, 0);
            Matrix.scaleM(matrixModel, 0, animScaleX, animScaleY, 0);
            Matrix.translateM(this.matrixModel, 0, -(width)/2, -(height)/2, 0);
        }






    }

    public void prepareRender(float[] matrixView, float[] matrixProjection){
        verifyAnimations();
        if (isVisible){
            render(matrixView, matrixProjection);
        }
    }

    public void render(float[] matrixView, float[] matrixProjection) {



        setMatrixModel();

        GLES20.glUseProgram(this.program.get());

        // get handle to vertex shader's vPosition member and add vertices
        int av4_verticesHandle = GLES20.glGetAttribLocation(this.program.get(), "av4_vertices");
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, this.verticesBuffer);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        //Log.e("render", " ");

        int av2_uvHandle = -1;
        if (this.textureUnit != -1) {
            // Get handle to texture coordinates location and load the texture uvs
            av2_uvHandle = GLES20.glGetAttribLocation(this.program.get(), "av2_uv");
            GLES20.glVertexAttribPointer(av2_uvHandle, 2, GLES20.GL_FLOAT, false, 0, this.uvsBuffer);
            GLES20.glEnableVertexAttribArray(av2_uvHandle);
        }

        int av4_colorsHandle = -1;
        if (this.colorsBuffer != null){
            //Log.e("tag "+this.name, "tem cor");
            av4_colorsHandle = GLES20.glGetAttribLocation(this.program.get(), "av4_colors" );
            GLES20.glVertexAttribPointer ( av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, this.colorsBuffer);
            GLES20.glEnableVertexAttribArray ( av4_colorsHandle );
        }

        int uf_alphaHandle = GLES20.glGetUniformLocation(this.program.get(), "uf_alpha");
        GLES20.glUniform1f(uf_alphaHandle, this.alpha);
        
        // Get handle to shape's transformation matrix and add our matrix
        int um4_projectionHandle = GLES20.glGetUniformLocation(this.program.get(), "um4_projection");
        GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
        //Log.e("render", " ");

        // Get handle to shape's transformation matrix and add our matrix
        int um4_viewHandle = GLES20.glGetUniformLocation(this.program.get(), "um4_view");
        GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
        //Log.e("render", " ");

        // Get handle to shape's transformation matrix and add our matrix
        int um4_modelHandle = GLES20.glGetUniformLocation(this.program.get(), "um4_model");
        GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, this.matrixModel, 0);
        //Log.e("render", " ");


        if (this.program == game.imageColorizedFxProgram){
            int uv2_ballPosition = GLES20.glGetUniformLocation(this.program.get(), "uv2_ballPosition");
            GLES20.glUniform2f(uv2_ballPosition, game.balls.get(0).x + game.screenOffSetX, game.resolutionY - (game.balls.get(0).y+game.screenOffSetY));

            float variation;
            if (game.ballCollidedFx > 0) {

                if (game.ballCollidedFx > 30){
                    variation = 0.007f;
                } else if (game.ballCollidedFx > 20){
                    variation = 0.0055f;
                } else if (game.ballCollidedFx > 10){
                    variation = 0.004f;
                } else{
                    variation = 0.002f;
                }



                if (timeVar == true) {
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

        if (this.textureUnit != -1) {
            // Get handle to textures locations
            // Set the sampler texture unit to 0, where we have saved the texture.
            int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");
            GLES20.glUniform1i(us_textureHandle, this.textureUnit);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        }

        //Log.e("entidade "+this.name + " indices len"," "+ this.indicesData.length);
        //Log.e("entidade "+this.name + " indices len"," "+ this.indicesBuffer.toString());

        // No depth testing
        // Draw the triangle



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

        //if (name == "explode")
            Log.e("entity", GLES20.glGetProgramInfoLog(program.get()));
            //Log.e("entity2", GLES20.glGetProgram(program.get()));

    }

    public void updateListenersData(){


    }

    public void display(){
        this.isVisible = true;
    }

    public void verifyListeners(){
        this.updateListenersData();
        for (int i = 0; i < this.listeners.size(); i++){
            this.listeners.get(i).verify();
        }
        for (int i = 0; i < this.childs.size(); i++){
            this.childs.get(i).verifyListeners();
        }
    }

    public void addChild(Entity child){
        this.childs.add(child);
        child.parent = this;
    }

    public void block(){
        this.isBlocked = true;
    }

    public void setDrawInfo(){


    }

    public void unblock(){
        this.isBlocked = false;
    }

    public void clearDisplay(){
        this.isVisible = false;
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

    public void addListener(InteractionListener interactionListenerListener){
        this.listeners.add(interactionListenerListener);
    }

    public void updateQuatreeData() {
    }

    public void setSatData() {
    }
}
