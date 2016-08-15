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

    public Color color;
    public float animTranslateX;
    public float animTranslateY;
    public float animScaleX;
    public float animScaleY;
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
    public Layer layer;
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

    private float[] vecs;
    private float[] uvs;
    private short[] indices;

    public boolean isLineGL = false;
    public int lineWidth = 1;


    public FloatBuffer verticesBuffer;
    public FloatBuffer uvsBuffer;
    public ShortBuffer indicesBuffer;
    public FloatBuffer colorsBuffer;

    public float[] matrixProjectionAndView = new float[16];
    public float[] matrixViewModel = new float[16];
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
        this.dX = 0;
        this.dY = 0;
        this.previousX = x;
        this.previousY = y;
        this.previousDX = 0;
        this.previousDY = 0;
        this.scaleX = 1f;
        this.scaleY = 1f;
        this.alpha = 1;
        this.isCollidable = true;
        this.isVisible = true;
        this.isSolid = true;
        this.isMovable = true;
        this.isBlocked = false;
        this.isPressed = false;
        this.isFree = true;
        this.bounds = new RectangleM(0, 0, 0, 0);
        this.quadtreeData = new RectangleM(0, 0, 0, 0);
        this.satData = new RectangleM(0, 0, 0, 0);
        this.updateBoundsState = 0;
        this.scaleX = 1;
        this.scaleY = 1;
        this.animTranslateX = 0;
        this.animTranslateY = 0;
        this.animations = new ArrayList<Animation>();
        this.listeners = new ArrayList<InteractionListener>();
        this.childs = new ArrayList<>();
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
                this.scaleX = value;
                break;
            case "scaleY":
                this.scaleY = value;
                break;
            case "alpha":
                //Log.e("Entity", "ativando animação alpha reduzindo para "+value);
                this.alpha = value;
                break;


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
        this.scaleX = 1;
        this.scaleY = 1;
        this.alpha = 1;
        this.resetSpecificData();
    }

    public void resetSpecificData() {

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
        if (updatePrevious) {
            this.previousX = this.x;
            this.previousY = this.y;
        }

        this.x += tx;
        this.y += ty;
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

    public void setMatrixModel(){
        Matrix.setIdentityM(this.matrixModel, 0); // initialize to identity matrix

        //if (this.name == "frame")Log.e("entity", " "+animTranslateX);

        Matrix.translateM(this.matrixModel, 0, this.x + animTranslateX, this.y + animTranslateY, 0);

        if (this.rotateAngle != 0) {
            Matrix.translateM(this.matrixModel, 0, getMiddlePointX(), getMiddlePointY(), 0);
            Matrix.setRotateM(mRotationMatrix, 0, this.rotateAngle, 0f, 0f, 1f);
            matrixTemp = matrixModel.clone();
            Matrix.multiplyMM(matrixModel, 0, matrixTemp, 0, mRotationMatrix, 0);
            Matrix.translateM(this.matrixModel, 0, -getMiddlePointX(), -getMiddlePointY(), 0);
        }

        if (this.scaleX != 1 || this.scaleX != 1)
        Matrix.scaleM(this.matrixModel, 0, this.scaleX , this.scaleY, 0);
    }

    public void prepareRender(float[] matrixView, float[] matrixProjection){
        verifyAnimations();
        if (isVisible){
            render(matrixView, matrixProjection);
        }
    }

    public void render(float[] matrixView, float[] matrixProjection) {

        //if (this.name == "tittle"){
            //Log.e("entity", "render tittle");
        //}


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

        //Log.e("render", " ");

        int av4_colorsHandle = -1;
        if (this.colorsBuffer != null){
            //Log.e("tag "+this.name, "tem cor");
            av4_colorsHandle = GLES20.glGetAttribLocation(this.program.get(), "av4_colors" );
            GLES20.glVertexAttribPointer ( av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, this.colorsBuffer);
            GLES20.glEnableVertexAttribArray ( av4_colorsHandle );
        }

        int uf_alphaHandle = GLES20.glGetUniformLocation(this.program.get(), "uf_alpha");
        GLES20.glUniform1f(uf_alphaHandle, this.alpha);
        //Log.e("render", " ");

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
            GLES20.glDrawArrays(GLES20.GL_LINES, 0, 3);
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
