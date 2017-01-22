package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    public ArrayList<Frame> frames;
    int currentFrame;


    public Tutorial(){
        frames = new ArrayList<>();
    }

    public void addFrame(Image image, String text, float y, float size){
        frames.add(new Frame(image, text, y, size));
    }

    public void clear(){
        frames.clear();
    }

    public void showFirst(){
        currentFrame = 0;
        show();
    }

    public void next(){
        currentFrame += 1;
        show();
    }

    public void show(){
        if (frames.size() > currentFrame){
            Game.tutorialImage = frames.get(currentFrame).image;
            Game.tutorialTextBox = frames.get(currentFrame).textBox;
            Game.tutorialImage.display();
            Game.tutorialTextBox.display();
        } else {
            Game.tutorialImage.clearDisplay();
            Game.tutorialTextBox.clearDisplay();
            Game.setGameState(Game.GAME_STATE_PREPARAR);
        }
    }

    public class Frame{
        Text text;
        Image image;
        TextBox textBox;
        Frame(Image image, String text, float y, float size){
            textBox = new TextBoxBuilder("textBox")
                    .position(Game.resolutionX * 0.5f, y)
                    .width(Game.resolutionX * 0.9f)
                    .size(size)
                    .text(text)
                    .withoutArrow()
                    .isHaveFrame(false)
                    .build();

            this.image = image;
        }
    }
}
