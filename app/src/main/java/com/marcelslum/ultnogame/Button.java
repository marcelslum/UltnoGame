package com.marcelslum.ultnogame;


import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by marcel on 07/08/2016.
 */
public class Button extends Entity implements Poolable<Button>{


    Rectangle frame;

    public float height;
    public float width;
    OnPress onPress;
    OnUnpress onUnpress;
    TextureData textureDataPressed;
    TextureData textureDataUnpressed;
    private int poolID;

    public final String TAG = "Button";


    @Override
    public void setPoolID(int id) {
        poolID = id;
    }

    @Override
    public int getPoolID() {
        return poolID;
    }

    @Override
    public Button get() {
        return this;
    }

    @Override
    public void clean() {
        //Log.e(TAG, "cleaning button "+ name);
        super.clean();
        height = 0f;
        width = 0f;
        onPress = null;
        onUnpress = null;
        textureDataPressed = null;
        textureDataUnpressed = null;
        listener = null;
    }
    
    public Button(){
        super("buttonEmpty", 0f, 0f, Entity.TYPE_BUTTON);
    }
    
    public void setData(String name, float x, float y, float width, float height, int textureUnit, float listenerScale,
           TextureData textureDataUnpressed, TextureData textureDataPressed) {

        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        isCollidable = false;
        isVisible = true;
        isMovable = false;
        isSolid =  false;

        this.textureDataUnpressed = textureDataUnpressed;
        this.textureDataPressed = textureDataPressed;
        this.textureId = textureUnit;

        //program = Game.vertex_e_uv_com_alpha_program;
        program = Game.imageProgram;

        float lw = width * listenerScale;
        float lh = height * listenerScale;
        float lx = x - ((lw - width)/2f);
        float ly = y - ((lh - height)/2f);

        final Button finalButton =  this;
        setListener(new InteractionListener("listenerButton"+this.name, lx, ly, lw, lh, 5000, this,
            new InteractionListener.PressListener() {
                @Override
                public void onPress() {
                    finalButton.setPressed();
                    if (finalButton.onPress != null){
                        finalButton.onPress.onPress();
                    }
                }

                @Override
                public void onUnpress() {
                    finalButton.setUnpressed();
                    if (finalButton.onUnpress != null){
                        finalButton.onUnpress.onUnpress();
                    }
                }
            }
        ));

        super.setData();
        setDrawInfo();
    }

    Button(String name, float x, float y, float width, float height, int textureUnit, float listenerScale,
           TextureData textureDataUnpressed, TextureData textureDataPressed) {
        super(name, x, y, Entity.TYPE_BUTTON);
        this.height = height;
        this.width = width;
        isCollidable = false;
        isVisible = true;
        isMovable = false;
        isSolid =  false;

        this.textureDataUnpressed = textureDataUnpressed;
        this.textureDataPressed = textureDataPressed;
        this.textureId = textureUnit;

        //program = Game.vertex_e_uv_com_alpha_program;
        program = Game.imageProgram;

        float lw = width * listenerScale;
        float lh = height * listenerScale;
        float lx = x - ((lw - width)/2f);
        float ly = y - ((lh - height)/2f);

        final Button finalButton =  this;
        setListener(new InteractionListener("listenerButton"+this.name, lx, ly, lw, lh, 5000, this,
            new InteractionListener.PressListener() {
                @Override
                public void onPress() {
                    finalButton.setPressed();
                    if (finalButton.onPress != null){
                        finalButton.onPress.onPress();
                    }
                }

                @Override
                public void onUnpress() {
                    finalButton.setUnpressed();
                    if (finalButton.onUnpress != null){
                        finalButton.onUnpress.onUnpress();
                    }
                }
            }
        ));

        setDrawInfo();
    }

    public void addFrame(Rectangle rectangle){
        frame = rectangle;
        addChild(frame);
    }

    public void setMoveListener(InteractionListener.MoveListener moveListener){
        if (listener != null){
            listener.setMoveListener(moveListener);
        }
    }

    @Override
    public void translate(float translateX, float translateY) {
        super.translate(translateX, translateY);
        if (listener != null){
            listener.translate(translateX, translateY);
        }
    }

    public void setPressed() {
        isPressed = true;
        setUvInfo();
    }

    public void setUnpressed() {
        isPressed = false;
        setUvInfo();
    }

    public void setPersistent(int frequencyPersistent){
        if (getListener()!= null){
            InteractionListener listener = getListener();
            listener.setFrequencyPersistent(frequencyPersistent);
            listener.frequency = 1000;
        }
    }

    public void setOnPress(OnPress _onPress){
        onPress = _onPress;
    }

    public void setOnUnpress(OnUnpress _onUnpress){
        onUnpress = _onUnpress;
    }

    public interface OnPress{
        void onPress();
    }

    public interface OnUnpress{
        void onUnpress();
    }

    public void setUvInfo(){

        if (isPressed) {
            Utils.insertRectangleUvData(uvsData, 0,
                    textureDataPressed);
        } else {
            Utils.insertRectangleUvData(uvsData, 0,
                   textureDataUnpressed);
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);


        /*
        if (isPressed) {
            Utils.insertRectangleUvAndAlphaData(uvsData, 0, textureDataPressed, 1f);
        } else {
            Utils.insertRectangleUvAndAlphaData(uvsData, 0, textureDataUnpressed, 1f);
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                uvsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
         */

    }

    public void setDrawInfo(){

        initializeData(12, 6, 12, 0);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        setUvInfo();


        /*
        if (vbo == null || vbo.length == 0){


            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);

            //Log.e(TAG, "creating vbo button "+name);
            //Log.e(TAG, "vbo[0] "+vbo[0]);
            //Log.e(TAG, "vbo[1] "+vbo[1]);
        }
        initializeData(8, 6, 12, 0);
        
        Utils.insertRectangleVerticesDataXY(verticesData, 0, 0f, width, 0f, height);
        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
            verticesBuffer, GLES20.GL_STATIC_DRAW);
        
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
            indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        
        setUvInfo();
        */

    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        if (!isVisible) return;


        checkAnimations();

        if (frame != null) {
            frame.render(matrixView, matrixProjection);
        }

        render(matrixView, matrixProjection);

    }

    public void setDrawInfoVbo(){
        if (vbo == null || vbo.length == 0){


            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);

            //Log.e(TAG, "creating vbo button "+name);
            //Log.e(TAG, "vbo[0] "+vbo[0]);
            //Log.e(TAG, "vbo[1] "+vbo[1]);

            initializeData(8, 6, 12, 0);

        }


        Utils.insertRectangleVerticesDataXY(verticesData, 0, 0f, width, 0f, height);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                indicesBuffer, GLES20.GL_STATIC_DRAW);



        if (isPressed) {
            Utils.insertRectangleUvAndAlphaData(uvsData, 0,
                    textureDataPressed, 1f);
        } else {
            Utils.insertRectangleUvAndAlphaData(uvsData, 0,
                    textureDataUnpressed, 1f);
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                uvsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

    }
}
