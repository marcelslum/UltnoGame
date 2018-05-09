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
        if (gameState == GameStateHandler.GAME_STATE_OPCOES_JOGABILIDADE){
            MenuOption menuOptionMusicMain = MenuHandler.menuOpcoesJogabilidade.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicMain.x + (menuOptionMusicMain.width*1.5f), menuOptionMusicMain.y);
            MenuOption menuOptionSoundMain = MenuHandler.menuOpcoesJogabilidade.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundMain.x + (menuOptionSoundMain.width*2.2f), menuOptionSoundMain.y);
            MenuOption menuOptionVibrationMain = MenuHandler.menuOpcoesJogabilidade.getMenuOptionByName("vibration");
            selectorVibration.setPosition(menuOptionVibrationMain.x + (menuOptionVibrationMain.width*1.3f), menuOptionVibrationMain.y);
            MenuOption menuOptionBallVelocity = MenuHandler.menuOpcoesJogabilidade.getMenuOptionByName("difficulty");
            selectorDifficulty.setPosition(menuOptionBallVelocity.x + (menuOptionBallVelocity.width*0.9f), menuOptionBallVelocity.y);

        } else if (gameState == GameStateHandler.GAME_STATE_PAUSE_OPCOES){
            MenuOption menuOptionMusicInGame = MenuHandler.menuPauseOpcoes.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicInGame.x + (menuOptionMusicInGame.width*1.5f), menuOptionMusicInGame.y);
            MenuOption menuOptionSoundInGame = MenuHandler.menuPauseOpcoes.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundInGame.x + (menuOptionSoundInGame.width*2.2f), menuOptionSoundInGame.y);
            MenuOption menuOptionVibrationInGame = MenuHandler.menuPauseOpcoes.getMenuOptionByName("vibration");
            selectorVibration.setPosition(menuOptionVibrationInGame.x + (menuOptionVibrationInGame.width*1.3f), menuOptionVibrationInGame.y);
            MenuOption menuOptionBallVelocity = MenuHandler.menuPauseOpcoes.getMenuOptionByName("difficulty");
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
