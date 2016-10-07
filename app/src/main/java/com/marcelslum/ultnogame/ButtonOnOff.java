package com.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class ButtonOnOff extends Button{

    public boolean on;
    private OnOffBehavior onOffBehavior;

    ButtonOnOff(String name, float x, float y, float width, float height, int textureUnit, float listenerScale){
        super(name, x, y, width, height, textureUnit, listenerScale);
        this.on = false;
    }

    public void setOn(){
        this.on = true;
        setTextureMap(textureMapPressed);
        if (onOffBehavior != null){
            onOffBehavior.onBehavior();
        }
    }

    public void setOff(){
        this.on = false;
        setTextureMap(textureMapUnpressed);
        if (onOffBehavior != null){
            onOffBehavior.offBehavior();
        }
    }



    @Override
    public void setPressed() {
        this.isPressed = true;
        if (this.on){
            setOff();
        } else {
            setOn();
        }

        if (this.onPress != null){
            this.onPress.onPress();
        }
    }

    @Override
    public void setUnpressed() {
        this.isPressed = false;
        if (this.onUnpress != null){
            this.onUnpress.onUnpress();
        }
    }

    public void setOnOffBehavior(OnOffBehavior onOffBehavior){
        this.onOffBehavior = onOffBehavior;
    }

    interface OnOffBehavior {
        public void onBehavior();
        public void offBehavior();
    }





}
