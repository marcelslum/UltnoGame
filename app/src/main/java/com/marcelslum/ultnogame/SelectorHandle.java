package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class SelectorHandle {


    // entities
    static Selector selectorLevel;
    static Selector selectorDificulty;
    static Selector selectorMusic;
    static Selector selectorSound;


    public static void repositionSelectors(int gameState){
        if (gameState == Game.GAME_STATE_OPCOES){
            MenuOption menuOptionMusicMain = MenuHandle.menuOptions.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicMain.x + (menuOptionMusicMain.width*1.5f), menuOptionMusicMain.y);
            MenuOption menuOptionSoundMain = MenuHandle.menuOptions.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundMain.x + (menuOptionSoundMain.width*2.2f), menuOptionSoundMain.y);

        } else if (gameState == Game.GAME_STATE_OPCOES_GAME){
            MenuOption menuOptionMusicInGame = MenuHandle.menuInGameOptions.getMenuOptionByName("music");
            selectorMusic.setPosition(menuOptionMusicInGame.x + ((menuOptionMusicInGame.width)*1.5f), menuOptionMusicInGame.y);
            MenuOption menuOptionSoundInGame = MenuHandle.menuInGameOptions.getMenuOptionByName("sound");
            selectorSound.setPosition(menuOptionSoundInGame.x + (menuOptionSoundInGame.width*2.2f), menuOptionSoundInGame.y);
        }
    }
}
