package ultno.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 13/08/2016.
 */
class MenuOption {
    public int id;
    public String name;
    public String text;
    public OnChoice myOnChoice;
    public float x;
    public float y;
    public boolean isSelected;
    public float height;
    public float width;
    public Text textObject;
    public Font font;
    public float size;
    public String setText;
    public Game game;

    public MenuOption(int id, String name, String text, Font font, float size, float x, float y) {

        this.id = id;
        this.name = name;
        this.text = text;
        this.font = font;
        this.size = size;
        this.isSelected = false;
        this.x = x;
        this.y = y;

        setText(text);
    }

    public void setText(String text){
        textObject = new Text("menuOptions"+name+"text", 0f, this.y, this.size, text, this.font);
        this.width = textObject.calculateWidth();
        textObject.setX(this.x - (this.width/2));
    }

    public void setOnChoice(OnChoice onChoice) {
        this.myOnChoice = onChoice;
    }

    public void fireOnChoice() {
        if (this.myOnChoice != null) {
            this.myOnChoice.onChoice();
        }
    }

    interface OnChoice {
        void onChoice();
    }
}
