package ultno.marcelslum.ultno;

import android.util.Log;

/**
 * Created by marcel on 02/08/2016.
 */
public class InteractionListener {

    public String name;
    public float x;
    public float y;
    public float width;
    public float height;
    public int frequency;
    public Entity objectAppend;
    public Game gameObject;
    public boolean pressedOnVerify;
    public boolean active;
    public boolean onPress;
    public long startTime;
    public PressListener myPressListener;

    InteractionListener(String name, float x, float y, float width, float height, int frequency, Entity objectAppend, Game gameObject) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        this.objectAppend = objectAppend;
        this.gameObject = gameObject;
        this.pressedOnVerify = false;
        this.active = false;
    }

    public void verify() {
        if (objectAppend.isBlocked) {
            return;
        }

        this.pressedOnVerify = false;

        for (int i = 0; i < this.gameObject.touchEvents.size(); i++) {
            this.pressedOnVerify = Utils.isInsideBounds(
                    this.gameObject.touchEvents.get(i).x,
                    this.gameObject.touchEvents.get(i).y,
                    x, y, width, height);
            if (this.pressedOnVerify) {
                //Log.e("interaction", " pressed "+ this.name);
                break;
            }
        }

        if (this.pressedOnVerify) {
            if (this.active == false) {
                this.active = true;
                this.objectAppend.isPressed = true;
                this.startTime = Utils.getTime();
                //console.log("onPress ", this.name);
                if (this.myPressListener != null) {
                    this.myPressListener.onPress();
                }
            } else {
                long actualTime = Utils.getTime();
                long timeElapsed = actualTime - this.startTime;
                if (timeElapsed > (long) frequency) {
                    if (this.myPressListener != null) {
                        this.myPressListener.onPress();
                    }
                    //console.log("onPress");
                    this.startTime = actualTime;
                }
            }
        }

        if (!this.pressedOnVerify) {
            if (this.active == true) {
                if (this.myPressListener != null) {
                    this.myPressListener.onUnpress();
                }

                this.objectAppend.isPressed = false;
                this.active = false;
            }
        }
    }

    public void setPressListener(PressListener listener) {
        this.myPressListener = listener;
    }

    public static interface PressListener {
        void onPress();
        void onUnpress();
    }

}



