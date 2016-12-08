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
    public boolean active;
    public long startTime;
    public PressListener myPressListener;
    public MoveListener myMoveListener;

    public int mode;

    public static final int MODE_EMPTY = 0;
    public static final int MODE_PRESS = 1;
    public static final int MODE_MOVE_DOWN = 2;
    public static final int MODE_MOVE = 3;
    public static final int MODE_MOVE_UP = 4;

    public static final String TAG = "InteractionListener";

    InteractionListener(String name, float x, float y, float width, float height, int frequency, Entity objectAppend) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        this.objectAppend = objectAppend;
        active = false;
        mode = MODE_EMPTY;
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
        active = false;
        mode = MODE_EMPTY;
        setPressListener(pressListener);
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

        boolean pressedOnVerify = false;
        TouchEvent touch = null;

        for (int i = 0; i < Game.touchEvents.size(); i++) {
             touch = Game.touchEvents.get(i);

            pressedOnVerify = Utils.isPointInsideBounds(
                    touch.x,
                    touch.y,
                    x, y, width, height);

            if (pressedOnVerify) {
                if (myMoveListener == null){
                    if (!touch.moved){
                        Log.e(TAG, "sem movimento - pressionado " + objectAppend.name);
                        mode = MODE_PRESS;
                    }
                } else {
                    // se está escutando o movimento mas o botão foi solto sem se movimentar
                    if (touch.type == TouchEvent.TOUCH_TYPE_UP && !touch.moved) {
                        mode = MODE_PRESS;
                    } else if (touch.type == TouchEvent.TOUCH_TYPE_DOWN) {
                        mode = MODE_MOVE_DOWN;
                    } else if (touch.type == TouchEvent.TOUCH_TYPE_MOVE) {
                        mode = MODE_MOVE;
                    } else if (touch.type == TouchEvent.TOUCH_TYPE_UP && touch.moved) {
                        mode = MODE_MOVE_UP;
                    }
                }
                break;
            }
        }

        if (mode == MODE_PRESS) {
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
        } else if (mode == MODE_MOVE_DOWN) {
            objectAppend.isPressed = true;
            startTime = Utils.getTime();
            myMoveListener.onMoveDown();
        } else if (mode == MODE_MOVE) {
            myMoveListener.onMove(touch, startTime);
        } else if (mode == MODE_MOVE_UP) {
            myMoveListener.onMoveUp(touch, startTime);
        }

        if (!pressedOnVerify) {
            if (active) {
                persistentActivated = false;
                objectAppend.isPressed = false;
                active = false;
                if (myPressListener != null) {
                    myPressListener.onUnpress();
                }
            }
        }

    }

    public void setPressListener(PressListener listener) {
        myPressListener = listener;
    }

    public void setMoveListener(MoveListener listener) {
        myMoveListener = listener;
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

    public interface MoveListener {
        void onMoveDown();
        void onMove(TouchEvent touch, long startTime);
        void onMoveUp(TouchEvent touch, long startTime);
    }
}



