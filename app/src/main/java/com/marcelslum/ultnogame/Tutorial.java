package com.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    static int currentTutorial;
    static Tutorial currentTutorialObject;
    static Image tutorialImage;
    static TextBox tutorialTextBox;
    static int numberOfTutorials = 20;

    public ArrayList<Frame> frames;
    int currentFrame;

    static final int TUTORIAL_INSTRUCOES_INICIAIS = 0;
    static final int TUTORIAL_INICIO = 1;
    static final int TUTORIAL_MOVIMENTO_BARRA = 2;
    static final int TUTORIAL_INCLINACAO_BARRA = 3;
    static final int TUTORIAL_OBSTACULO = 4;
    static final int TUTORIAL_CORES = 5;
    static final int TUTORIAL_EXPLOSAO = 6;


    public static boolean isTutorialUnblocked(int tutorialNumber){
        int starsToUnlock = 10000;
        if (tutorialNumber == TUTORIAL_INSTRUCOES_INICIAIS){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(0).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_INICIO && SaveGame.saveGame.tutorialsViwed[TUTORIAL_INSTRUCOES_INICIAIS]){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(0).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_MOVIMENTO_BARRA && SaveGame.saveGame.tutorialsViwed[TUTORIAL_INICIO]){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(0).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_INCLINACAO_BARRA && SaveGame.saveGame.tutorialsViwed[TUTORIAL_MOVIMENTO_BARRA]){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(0).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_OBSTACULO){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(1).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_CORES){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(2).starsToUnlock;
        } else if (tutorialNumber == TUTORIAL_EXPLOSAO){
            starsToUnlock = LevelsGroupData.levelsGroupData.get(3).starsToUnlock;
        }

        if (StarsHandler.conqueredStarsTotal >= starsToUnlock) {
            return true;
        }
        return false;
    }

    public static boolean hasUnvisitedTutorial(){

        int test = TUTORIAL_INSTRUCOES_INICIAIS;
        if (isTutorialUnblocked(test) && !SaveGame.saveGame.tutorialsViwed[test]){return true;}

        test = TUTORIAL_INICIO;
        if (isTutorialUnblocked(test) && !SaveGame.saveGame.tutorialsViwed[test]){return true;}

        test = TUTORIAL_OBSTACULO;
        if (isTutorialUnblocked(test) && !SaveGame.saveGame.tutorialsViwed[test]){return true;}

        test = TUTORIAL_CORES;
        if (isTutorialUnblocked(test) && !SaveGame.saveGame.tutorialsViwed[test]){return true;}

        test = TUTORIAL_EXPLOSAO;
        if (isTutorialUnblocked(test) && !SaveGame.saveGame.tutorialsViwed[test]){return true;}

        return false;

    }


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

    public void showFirst(){
        currentFrame = 0;
        show(true);
    }

    public void next(){
        currentFrame += 1;
        show(true);
    }

    public void previous(){
        currentFrame -= 1;
        if (currentFrame >= 0) {
            show(false);
        } else {
            Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);

        }
    }

    public void show(boolean forward){
        if (frames.size() > currentFrame){
            if (currentFrame != 0) {
                if (forward) {
                    if (frames.get(currentFrame).image != frames.get(currentFrame - 1).image) {
                        tutorialImage = frames.get(currentFrame).image;
                        tutorialImage.alpha = 0;
                        tutorialImage.display();
                        tutorialImage.increaseAlpha(500, 1f);
                    }
                } else {
                    if (frames.size() >= currentFrame + 1) {
                        if (frames.get(currentFrame).image != frames.get(currentFrame + 1).image) {
                            tutorialImage = frames.get(currentFrame).image;
                            tutorialImage.alpha = 0;
                            tutorialImage.display();
                            tutorialImage.increaseAlpha(500, 1f);
                        }
                    }
                }
            } else {
                tutorialImage = frames.get(currentFrame).image;
                tutorialImage.alpha = 0;
                tutorialImage.display();
                tutorialImage.increaseAlpha(500, 1f);
            }

            tutorialTextBox = frames.get(currentFrame).textBox;
            tutorialTextBox.alpha = 0;
            tutorialTextBox.display();
            tutorialTextBox.clearAnimations();
            Utils.createSimpleAnimation(tutorialTextBox, "translateX", "translateX", 500, Game.resolutionX*2f, 0f).start();
            
            tutorialTextBox.animateMiniArrow(Game.resolutionX*1.5f, tutorialTextBox.size * 0.5f);

            ButtonHandler.buttonContinue.display();
            ButtonHandler.buttonContinue.unblock();
            ButtonHandler.buttonReturn.display();
            ButtonHandler.buttonReturn.unblock();

            Sound.play(Sound.soundTextBoxAppear, 0.5f, 0.5f, 0);

        } else {

            ButtonHandler.buttonContinue.clearDisplay();
            ButtonHandler.buttonContinue.block();
            ButtonHandler.buttonReturn.clearDisplay();
            ButtonHandler.buttonReturn.block();
            tutorialImage.clearDisplay();
            tutorialTextBox.clearDisplay();
            
            SaveGame.saveGame.tutorialsViwed[currentTutorial] = true;
            Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
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

    public static void loadTutorial(){
        
        
        float textBoxY = Game.resolutionX * 0.48f;
        float textBoxSize = Game.resolutionX * 0.03f;

        if (Tutorial.currentTutorial == Tutorial.TUTORIAL_INSTRUCOES_INICIAIS) {
            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i2 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i3 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            Tutorial.currentTutorialObject = new Tutorial();

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t1),
                    textBoxY, textBoxSize);

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t2),
                    textBoxY, textBoxSize);

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t3),
                    textBoxY, textBoxSize);

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t4),
                    textBoxY, textBoxSize);

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t5),
                    textBoxY, textBoxSize, Game.resolutionX * 0.2f, Game.resolutionY * 0.5f);

            Tutorial.currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t6),
                    textBoxY, textBoxSize, Game.resolutionX * 0.5f, Game.resolutionY * 0.5f);

            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t7),
                    textBoxY, textBoxSize, Game.resolutionX * 0.8f, Game.resolutionY * 0.14f);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t8),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t9),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t10),
                    textBoxY, textBoxSize, Game.resolutionX * 0.2f, Game.resolutionY * 0.6f);

            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t1t11),
                    textBoxY, textBoxSize, Game.resolutionX * 0.2f, Game.resolutionY * 0.6f);

            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t1t12),
                    textBoxY, textBoxSize, Game.resolutionX * 0.2f, Game.resolutionY * 0.4f);

            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t1t13),
                    textBoxY, textBoxSize, Game.resolutionX * 0.65f, Game.resolutionY * 0.65f);

            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t1t14),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t1t15),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t16),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t17),
                    textBoxY, textBoxSize);

            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t1t18),
                    textBoxY, textBoxSize);


        } else if (currentTutorial == Tutorial.TUTORIAL_INICIO) {


            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            Image i2 = new Image("i2", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            Image i3 = new Image("i3", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t2t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t2t2), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t2t3), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t2t4), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t2t5), textBoxY, textBoxSize);


        } else if (currentTutorial == Tutorial.TUTORIAL_MOVIMENTO_BARRA) {

            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);

            Image i2 = new Image("i2", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS1,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);
            
            
            Image i3 = new Image("i3", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t2), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t3), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t4), textBoxY, textBoxSize, Game.resolutionX * 0.5f, Game.resolutionY * 0.68f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t5), textBoxY, textBoxSize, Game.resolutionX * 0.43f, Game.resolutionY * 0.62f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t6), textBoxY, textBoxSize, Game.resolutionX * 0.43f, Game.resolutionY * 0.66f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t3t7), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t3t8), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t3t9), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t3t10), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t3t11), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t3t12), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t3t13), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t3t14), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t3t15), textBoxY, textBoxSize);

        } else if (currentTutorial == Tutorial.TUTORIAL_INCLINACAO_BARRA) {

            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i2 = new Image("i2", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            Image i3 = new Image("i3", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);

            Image i4 = new Image("i4", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            Image i5 = new Image("i5", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (512f + 1.5f) / 1024f, (768f - 1.5f) / 1024f);

            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t4t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t4t2), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t4t3), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t4t4), textBoxY, textBoxSize, Game.resolutionX * 0.25f, Game.resolutionY * 0.5f);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t4t5), textBoxY, textBoxSize, Game.resolutionX * 0.75f, Game.resolutionY * 0.5f);
            currentTutorialObject.addFrame(i2, Game.getContext().getResources().getString(R.string.t4t6), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i3, Game.getContext().getResources().getString(R.string.t4t7), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i4, Game.getContext().getResources().getString(R.string.t4t8), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, Game.getContext().getResources().getString(R.string.t4t9), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, Game.getContext().getResources().getString(R.string.t4t10), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i5, Game.getContext().getResources().getString(R.string.t4t11), textBoxY, textBoxSize);

        }  else if (currentTutorial == Tutorial.TUTORIAL_OBSTACULO) {

            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);
            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t5t1), textBoxY, textBoxSize, Game.resolutionX * 0.46f, Game.resolutionY * 0.46f);

        } else if (currentTutorial == Tutorial.TUTORIAL_CORES) {

            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (768f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f);
            currentTutorialObject = new Tutorial();//
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t6t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t6t2), textBoxY, textBoxSize, Game.resolutionX * 0.15f, Game.resolutionY * 0.2f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t6t3), textBoxY, textBoxSize, Game.resolutionX * 0.46f, Game.resolutionY * 0.2f);

        } else if (currentTutorial == Tutorial.TUTORIAL_EXPLOSAO) {

            Image i1 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS3,
                    (0f + 1.5f) / 1024f, (512f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);

            Image i2 = new Image("i1", Game.resolutionX * 0.05f, Game.resolutionX * 0.025f,
                    Game.resolutionX * 0.9f, Game.resolutionX * 0.45f, Texture.TEXTURE_TUTORIALS2,
                    (512f + 1.5f) / 1024f, (1024f - 1.5f) / 1024f, (256f + 1.5f) / 1024f, (512f - 1.5f) / 1024f);
            currentTutorialObject = new Tutorial();
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t1), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t2), textBoxY, textBoxSize, Game.resolutionX * 0.15f, Game.resolutionY * 0.15f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t3), textBoxY, textBoxSize, Game.resolutionX * 0.78f, Game.resolutionY * 0.35f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t4), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t5), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t6), textBoxY, textBoxSize, Game.resolutionX * 0.17f, Game.resolutionY * 0.6f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t7), textBoxY, textBoxSize, Game.resolutionX * 0.8f, Game.resolutionY * 0.6f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t8), textBoxY, textBoxSize, Game.resolutionX * 0.85f, Game.resolutionY * 0.6f);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t9), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t10), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t11), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t12), textBoxY, textBoxSize);
            currentTutorialObject.addFrame(i1, Game.getContext().getResources().getString(R.string.t7t13), textBoxY, textBoxSize);
        }
    }
}
