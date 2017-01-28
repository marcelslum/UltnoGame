package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class ButtonHandler {

    static Button buttonReturn;
    static Button buttonReturnObjectivesPause;
    static Button buttonContinue;
    static Button button1Left;
    static Button button1Right;
    static Button button2Left;
    static Button button2Right;

    public static void initButtons(){
        float buttonSize = Game.resolutionX * 0.05f;
        buttonReturn = new Button("buttonReturn", buttonSize*0.5f, Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonReturn.setTextureMap(13);
        buttonReturn.textureMapUnpressed = 13;
        buttonReturn.textureMapPressed = 5;
        buttonReturn.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                } else if (Game.gameState == Game.GAME_STATE_SELECAO_GRUPO){
                    Game.setGameState(Game.GAME_STATE_MENU);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
                    Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                } else if (Game.gameState == Game.GAME_STATE_MENU_TUTORIAL){
                    Game.setGameState(Game.GAME_STATE_MENU);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL){
                    Tutorial.currentTutorialObject.previous();}
            }
        });

        buttonReturnObjectivesPause = new Button("buttonReturnObjectivesPause", buttonSize*0.5f, Game.gameAreaResolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonReturnObjectivesPause.setTextureMap(13);
        buttonReturnObjectivesPause.textureMapUnpressed = 13;
        buttonReturnObjectivesPause.textureMapPressed = 5;
        buttonReturnObjectivesPause.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                }
            }
        });

        buttonContinue = new Button("buttonContinue", Game.resolutionX - buttonSize*1.5f, Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        buttonContinue.setTextureMap(14);
        buttonContinue.textureMapUnpressed = 14;
        buttonContinue.textureMapPressed = 6;
        buttonContinue.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_VITORIA){
                    Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
                } else   if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
                    Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
                    LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                    if (!SaveGame.saveGame.tutorialsViwed[Level.levelObject.tutorialAttached]){
                        Tutorial.currentTutorial = Level.levelObject.tutorialAttached;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    } else {
                        Game.setGameState(Game.GAME_STATE_PREPARAR);
                    }
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL){
                        Tutorial.currentTutorialObject.next();
                }
            }
        });
    }
}
