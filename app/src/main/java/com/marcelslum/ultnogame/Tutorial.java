package com.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    public ArrayList<Frame> frames;
    int currentFrame;
    boolean showingFromMenu;


    public Tutorial(){
        frames = new ArrayList<>();
    }

    public void addFrame(Image image, String text, float y, float size){
        frames.add(new Frame(image, text, y, size));
    }

    public void clear(){
        frames.clear();
    }

    public void showFirst(boolean fromMenu){
        showingFromMenu = fromMenu;
        currentFrame = 0;
        show();
    }

    public void next(){
        currentFrame += 1;
        show();
    }

    public void previous(){
        currentFrame -= 1;
        if (currentFrame >= 0) {
            show();
        } else {
            if (showingFromMenu){
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
            } else {
                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
            }
        }
    }

    public void show(){
        if (frames.size() > currentFrame){

            if (currentFrame != 0) {
                if (frames.get(currentFrame).image != frames.get(currentFrame-1).image) {
                    Game.tutorialImage = frames.get(currentFrame).image;
                    Game.tutorialImage.alpha = 0;
                    Game.tutorialImage.display();
                    Game.tutorialImage.increaseAlpha(500, 1f);
                }
            } else {
                Game.tutorialImage = frames.get(currentFrame).image;
                Game.tutorialImage.alpha = 0;
                Game.tutorialImage.display();
                Game.tutorialImage.increaseAlpha(500, 1f);
            }

            Game.tutorialTextBox = frames.get(currentFrame).textBox;
            Game.tutorialTextBox.alpha = 0;
            Game.tutorialTextBox.display();
            Game.tutorialTextBox.clearAnimations();
            Utils.createSimpleAnimation(Game.tutorialTextBox, "translateX", "translateX", 500, Game.resolutionX*2f, 0f).start();

            Game.buttonContinue.display();
            Game.buttonContinue.unblock();
            Game.buttonReturn.display();
            Game.buttonReturn.unblock();

            Sound.play(Sound.soundTextBoxAppear, 0.5f, 0.5f, 0);

        } else {

            Game.buttonContinue.clearDisplay();
            Game.buttonContinue.block();
            Game.buttonReturn.clearDisplay();
            Game.buttonReturn.block();
            Game.tutorialImage.clearDisplay();
            Game.tutorialTextBox.clearDisplay();

            if (showingFromMenu){
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
            } else {
                if (Game.currentTutorial == 0) {
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                } else {
                    Game.setGameState(Game.GAME_STATE_PREPARAR);
                }
            }


        }
    }

    public class Frame{
        Text text;
        Image image;
        TextBox textBox;
        Frame(Image image, String text, float y, float size){
            textBox = new TextBoxBuilder("textBox")
                    .position(Game.resolutionX * 0.1f, y)
                    .width(Game.resolutionX * 0.8f)
                    .size(size)
                    .text(text)
                    .withoutArrow()
                    .isHaveFrame(false)
                    .isHaveArrowContinue(false)
                    .build();

            this.image = image;
        }
    }
}
