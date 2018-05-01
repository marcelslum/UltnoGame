package com.marcelslum.ultnogame;

/**
 * Created by marcel on 22/09/2016.
 */
public class TextBoxBuilder {

    public float width;
    public float size;
    public String text;
    public float x;
    public float y;
    public final String name;
    public boolean isHaveArrow;
    public boolean isHaveMiniArrow;
    public boolean isHaveFrame;
    public boolean isHaveArrowContinue;
    public float arrowX;
    public float arrowY;
    public int frameType;
    public Color shadowColor;
    public Color frameColor;
    public Color textColor;
    public Color borderColor;

    public TextBoxBuilder(String name) {
        this.name = name;
        width = 0f;
        size = 0f;
        text = "";
        textColor = Color.cinza20;
        frameColor = new Color(0.7f, 0.7f, 0.7f, 1.0f);
        x = 0f;
        y = 0f;
        isHaveArrow = false;
        isHaveFrame = true;
        isHaveArrowContinue = true;
        arrowX = 0f;
        arrowY = 0f;
    }

    public TextBoxBuilder position(float x, float y){
        this.x = x;
        this.y = y;
        return this;
    }

    public TextBoxBuilder setTextColor(Color color){
        this.textColor = color;
        return this;
    }

    public TextBoxBuilder setShadowColor(Color color){
        shadowColor = color;
        return this;
    }


    public TextBoxBuilder size(float size){
        this.size = size;
        return this;
    }

    public TextBoxBuilder text(String text){
        this.text = text;
        return this;
    }

    public TextBoxBuilder width(float width){
        this.width = width;
        return this;
    }

    public TextBoxBuilder withArrow(float arrowX, float arrowY){
        this.isHaveArrow = true;
        this.arrowX = arrowX;
        this.arrowY = arrowY;
        return this;
    }

    public TextBoxBuilder withMiniArrow(float arrowX, float arrowY){
        this.isHaveMiniArrow = true;
        this.arrowX = arrowX;
        this.arrowY = arrowY;
        return this;
    }

    public TextBoxBuilder withoutArrow(){
        this.isHaveArrow = false;
        return this;
    }

    public TextBoxBuilder isHaveFrame(boolean isHaveFrame, Color frameColor){
        this.isHaveFrame = isHaveFrame;
        this.frameColor = frameColor;
        return this;
    }

    public TextBoxBuilder isHaveFrame(boolean isHaveFrame){
        this.isHaveFrame = isHaveFrame;;
        return this;
    }

    public TextBoxBuilder setBorderColor(Color borderColor){
        this.borderColor = borderColor;
        return this;
    }

    public TextBoxBuilder isHaveArrowContinue(boolean isHaveArrowContinue){
        this.isHaveArrowContinue = isHaveArrowContinue;
        return this;
    }

    public TextBox build(){
        return new TextBox(this);
    }

}
