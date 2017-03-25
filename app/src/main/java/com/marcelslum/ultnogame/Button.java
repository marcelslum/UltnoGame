package com.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Button extends Entity{

    public float height;
    public float width;
    OnPress onPress;
    OnUnpress onUnpress;
    TextureData textureDataPressed;
    TextureData textureDataUnpressed;


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
        setDrawInfo();
    }

    public void setUnpressed() {
        isPressed = false;
        setDrawInfo();
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



    }

    public void setDrawInfo(){
        initializeData(12, 6, 12, 0);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        setUvInfo();
    }
}
