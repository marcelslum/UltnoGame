package com.marcelslum.ultnogame;
import android.util.Log;
public class InteractionListener {

    public String name;
    public float x;
    public float y;
    public float width;
    public float height;
    public int frequency;
    public int frequencyPersistent = 0;
    public boolean persistentActivated = false;
    public Entity objectAppend;
    public boolean pressedOnVerify;
    public boolean active;
    public boolean onPress;
    public long startTime;
    public PressListener myPressListener;

    InteractionListener(String name, float x, float y, float width, float height, int frequency, Entity objectAppend) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        this.objectAppend = objectAppend;
        this.pressedOnVerify = false;
        this.active = false;
    }

    public void setFrequencyPersistent(int frequencyPersistent){
        this.frequencyPersistent = frequencyPersistent;
    }

    InteractionListener(String name, float x, float y, float width, float height, int frequency, Entity objectAppend, PressListener pressListener){
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        this.objectAppend = objectAppend;
        this.pressedOnVerify = false;
        this.active = false;
        this.myPressListener = pressListener;
    }

    public void verify() {

        // verifica se o game estiver bloqueado, caso o listener esteja na mesma passagem do listener que bloqueia o game
        if (Game.isBlocked){
            return;
        }

        if (objectAppend == null){
            return;
        }

        if (objectAppend.isBlocked) {
            return;
        }

        pressedOnVerify = false;

        //if(objectAppend.name=="bottomTextBox")

        String parentName = "";
        if (objectAppend.parent != null){
            parentName = objectAppend.parent.name;
        }


         //Log.e("listener", "verificando listener de "+objectAppend.name + " de " + parentName);


        for (int i = 0; i < Game.touchEvents.size(); i++) {
            pressedOnVerify = Utils.isPointInsideBounds(
                    Game.touchEvents.get(i).x,
                    Game.touchEvents.get(i).y,
                    x, y, width, height);
            if (pressedOnVerify) {
                //Log.e("interaction", " pressed "+ this.name);
                break;
            }
        }

        if (pressedOnVerify) {
            if (!active) {
                active = true;

                objectAppend.isPressed = true;
                startTime = Utils.getTime();
                //console.log("onPress ", this.name);
                if (myPressListener != null) {
                    myPressListener.onPress();
                }
            } else {
                long actualTime = Utils.getTime();
                long timeElapsed = actualTime - this.startTime;
                //Log.e("listener", "timeElapsed "+timeElapsed);



                if (timeElapsed > (long) frequency || (persistentActivated && timeElapsed > (long) frequencyPersistent)) {
                    if (frequencyPersistent != 0){
                        persistentActivated = true;
                    }
                    if (myPressListener != null) {
                        //Log.e("listener", "ativando onpress no listener "+name +" após testar o tempo");
                        myPressListener.onPress();
                    }
                    //console.log("onPress");
                    startTime = actualTime;
                }
            }
        }

        if (!pressedOnVerify) {

            if (active) {
                if (myPressListener != null) {
                    //Log.e("listener", "ativando onunpress no listener "+name);
                    myPressListener.onUnpress();
                }
                persistentActivated = false;
                objectAppend.isPressed = false;
                active = false;
            }
        }

    }

    public void setPressListener(PressListener listener) {
        myPressListener = listener;
    }

    public void setPositionAndSize(float x, float y, float frameWidth, float height) {
        this.x = x;
        this.y = y;
        this.width = frameWidth;
        this.height = height;
    }

    public interface PressListener {
        void onPress();
        void onUnpress();
    }

}



