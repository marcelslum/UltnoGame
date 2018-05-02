package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class SelectorHandler {

    static Selector selectorMusic;
    static Selector selectorSound;
    static Selector selectorVibration;
    static Selector selectorDifficulty;


    public static void repositionSelectors(int gameState){
        if (gameState == Game.GAME_STATE_OPCOES_JOGABILIDADE){
            MenuOption menuOptionMusicMain = MenuHandler.menuOptionsPlay.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicMain.x + (menuOptionMusicMain.width*1.5f), menuOptionMusicMain.y);
            MenuOption menuOptionSoundMain = MenuHandler.menuOptionsPlay.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundMain.x + (menuOptionSoundMain.width*2.2f), menuOptionSoundMain.y);
            MenuOption menuOptionVibrationMain = MenuHandler.menuOptionsPlay.getMenuOptionByName("vibration");
            selectorVibration.setPosition(menuOptionVibrationMain.x + (menuOptionVibrationMain.width*1.3f), menuOptionVibrationMain.y);
            MenuOption menuOptionBallVelocity = MenuHandler.menuOptionsPlay.getMenuOptionByName("difficulty");
            selectorDifficulty.setPosition(menuOptionBallVelocity.x + (menuOptionBallVelocity.width*0.9f), menuOptionBallVelocity.y);

        } else if (gameState == Game.GAME_STATE_OPCOES_GAME){
            MenuOption menuOptionMusicInGame = MenuHandler.menuInGameOptions.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicInGame.x + (menuOptionMusicInGame.width*1.5f), menuOptionMusicInGame.y);
            MenuOption menuOptionSoundInGame = MenuHandler.menuInGameOptions.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundInGame.x + (menuOptionSoundInGame.width*2.2f), menuOptionSoundInGame.y);
            MenuOption menuOptionVibrationInGame = MenuHandler.menuInGameOptions.getMenuOptionByName("vibration");
            selectorVibration.setPosition(menuOptionVibrationInGame.x + (menuOptionVibrationInGame.width*1.3f), menuOptionVibrationInGame.y);
            MenuOption menuOptionBallVelocity = MenuHandler.menuInGameOptions.getMenuOptionByName("difficulty");
            selectorDifficulty.setPosition(menuOptionBallVelocity.x + (menuOptionBallVelocity.width*0.9f), menuOptionBallVelocity.y);
        }
    }

    public static void backAllSelectors(){
        if (selectorMusic != null){
            selectorMusic.setNotVisible();
        }
        if (selectorSound != null){
            selectorSound.setNotVisible();
        }
        if (selectorVibration != null){
            selectorVibration.setNotVisible();
        }
        if (selectorDifficulty != null){
            selectorDifficulty.setNotVisible();
        }
    }
}
