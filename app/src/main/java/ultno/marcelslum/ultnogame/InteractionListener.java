package ultno.marcelslum.ultnogame;
import android.util.Log;
public class InteractionListener {

    public String name;
    public float x;
    public float y;
    public float width;
    public float height;
    public int frequency;
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
          //Log.e("listener", "verificando listener "+x +" "+ y+" "+ width+" "+ height);

        for (int i = 0; i < Game.touchEvents.size(); i++) {
            pressedOnVerify = Utils.isPointInsideBounds(
                    Game.touchEvents.get(i).x,
                    Game.touchEvents.get(i).y,
                    x, y, width, height);
            if (pressedOnVerify) {
                Log.e("interaction", " pressed "+ this.name);
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
                    Log.e("listener", "ativando onpress no listener "+name);
                    myPressListener.onPress();
                }
            } else {
                long actualTime = Utils.getTime();
                long timeElapsed = actualTime - this.startTime;
                if (timeElapsed > (long) frequency) {
                    if (myPressListener != null) {
                        Log.e("listener", "ativando onpress no listener "+name);
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

                objectAppend.isPressed = false;
                active = false;
            }
        }
    }

    public void setPressListener(PressListener listener) {
        myPressListener = listener;
    }

    public interface PressListener {
        void onPress();
        void onUnpress();
    }

}



