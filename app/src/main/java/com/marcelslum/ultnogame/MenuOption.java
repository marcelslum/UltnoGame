package com.marcelslum.ultnogame;

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
    public Game game;
    public Color textColor;
    public Color textShadowColor;

    public MenuOption(int id, String name, String text, Font font, float size, float x, float y, Color textColor, Color textShadowColor) {

        this.id = id;
        this.name = name;
        this.text = text;
        this.font = font;
        this.size = size;
        this.isSelected = false;
        this.x = x;
        this.y = y;
        this.textColor = textColor;
        this.textShadowColor = textShadowColor;
        setText(text);

    }

    public void setText(String text){
        if (textObject == null) {
            textObject = new Text("menuOptions" + name + "text", x, y, this.size, text, this.font, textColor);
            textObject.addShadow(textShadowColor);
        } else {
            textObject.setText(text);
        }
        width = textObject.width;
        //Log.e("MenuOption", "width "+width);
        textObject.setX(this.x - (this.width/2f));
        //Log.e("MenuOption", "x "+textObject.x);
    }

    public void setOnChoice(OnChoice onChoice) {
        myOnChoice = onChoice;
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
