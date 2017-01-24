package com.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    public ArrayList<Frame> frames;
    int currentFrame;
    boolean showingFromMenu;

    static final int TUTORIAL_INSTRUCOES_INICIAIS = 0;
    static final int TUTORIAL_INICIO = 1;
    static final int TUTORIAL_OBSTACULO = 2;
    static final int TUTORIAL_CORES = 3;
    static final int TUTORIAL_EXPLOSAO = 4;


    public Tutorial(){
        frames = new ArrayList<>();
    }

    public void addFrame(Image image, String text, float y, float size){
        frames.add(new Frame(image, text, y, size, -1f, -1f));
    }

    public void addFrame(Image image, String text, float y, float size, float arrowX, float arrowY){
        frames.add(new Frame(image, text, y, size, arrowX, arrowY));
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
            
            Game.tutorialTextBox.animateMiniArrow(Game.resolutionX*1.5f, Game.tutorialTextBox.size * 0.5f);

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
                if (Game.currentTutorial == TUTORIAL_INSTRUCOES_INICIAIS) {
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
        Frame(Image image, String text, float y, float size, float arrowX, float arrowY){
            TextBoxBuilder b = new TextBoxBuilder("textBox")
                .position(Game.resolutionX * 0.1f, y)
                .width(Game.resolutionX * 0.8f)
                .size(size)
                .text(text)
                .isHaveFrame(false)
                .isHaveArrowContinue(false);

            if (arrowX == -1f) {
                b.withoutArrow();
            } else {
                b.withMiniArrow(arrowX, arrowY);
            }
            textBox = b.build();
            this.image = image;
        }
    }
}
