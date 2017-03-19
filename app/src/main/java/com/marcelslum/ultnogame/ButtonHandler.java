package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class ButtonHandler {

    static Button buttonReturn;
    static Button buttonReturnObjectivesPause;
    static Button buttonContinue;
    static Button buttonGroupLeaderboard;
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

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                } else if (Game.gameState == Game.GAME_STATE_SELECAO_GRUPO){
                    Game.setGameState(Game.GAME_STATE_MENU);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
                    if (SaveGame.saveGame.currentLevelNumber < 1000){
                        Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                    } else {
                        Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                    }
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

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                }
            }
        });

        buttonGroupLeaderboard = new Button("buttonGroupLeaderboard", Game.resolutionX - buttonSize*1.5f,
                        Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize,
                        Texture.TEXTURE_BUTTONS_BALLS_STARS, 1.2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);

        buttonGroupLeaderboard.setTextureMap(14);
        buttonGroupLeaderboard.textureMapUnpressed = 14;
        buttonGroupLeaderboard.textureMapPressed = 6;
        buttonGroupLeaderboard.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){

                    String id;
                    switch (Game.currentLevelsGroupDataSelected.number){
                        case 1:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_1);
                            break;
                        case 2:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_2);
                            break;
                        case 3:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_3);
                            break;
                        case 4:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_4);
                            break;
                        case 5:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_5);
                            break;
                        case 6:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_6);
                            break;
                        case 7:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_7);
                            break;
                        case 8:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_8);
                            break;
                        case 9:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_9);
                            break;
                        case 10:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_10);
                            break;
                        case 11:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_11);
                            break;
                        case 12:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_12);
                            break;
                        case 13:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_13);
                            break;
                        case 14:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_14);
                            break;
                        case 15:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_15);
                            break;
                        case 16:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_16);
                            break;
                        case 17:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_17);
                            break;
                        case 18:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_18);
                            break;
                        case 19:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_19);
                            break;
                        case 20:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_20);
                            break;
                        default:
                            id = Game.mainActivity.getResources().getString(R.string.leaderboard_1);
                            break;
                    }

                    GooglePlayGames.showLeaderboards(Game.mainActivity.mGoogleApiClient, Game.mainActivity, id);

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

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_VITORIA){
                    Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
                } else   if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
                    Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
                    LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                        Game.setGameState(Game.GAME_STATE_PREPARAR);
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL){
                        Tutorial.currentTutorialObject.next();
                }
            }
        });
    }

    public static void createGameButtons(int barsQuantity, boolean invertedButtons) {
        float y = Game.resolutionY * 0.86f;
        float buttonSize = Game.resolutionY * 0.13f;

        // BOTÃO 1 ESQUERDA
        float x;
        if (invertedButtons){
            x = Game.resolutionX * 0.87f;
            if (barsQuantity > 1) {
                x = Game.resolutionX * 0.68f;
            }
        } else {
            x = Game.resolutionX * 0.03f;
        }

        ButtonHandler.button1Left = new Button("button1Left", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        ButtonHandler.button1Left.setTextureMap(19);
        ButtonHandler.button1Left.textureMapUnpressed = 19;
        ButtonHandler.button1Left.textureMapPressed = 18;
        ButtonHandler.button1Left.alpha = 0.7f;

        // BOTÃO 2 DIREITA
        if (invertedButtons){
            x = Game.resolutionX * 0.03f;
            if (barsQuantity > 1) {
                x = Game.resolutionX * 0.22f;
            }
        } else {
            x = Game.resolutionX * 0.87f;
        }

        ButtonHandler.button2Right = new Button("buttonRight", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
        ButtonHandler.button2Right.setTextureMap(20);
        ButtonHandler.button2Right.textureMapUnpressed = 20;
        ButtonHandler.button2Right.textureMapPressed = 17;

        if (barsQuantity > 1) {
            // BOTÃO 1 DIREITA
            if (invertedButtons) {
                x = Game.resolutionX * 0.87f;
            } else {
                x = Game.resolutionX * 0.22f;
            }
            ButtonHandler.button1Right = new Button("button1Right",x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
            ButtonHandler.button1Right.setTextureMap(20);
            ButtonHandler.button1Right.textureMapUnpressed = 20;
            ButtonHandler.button1Right.textureMapPressed = 17;
            ButtonHandler.button1Right.alpha = 0.7f;

            // BOTÃO 2 ESQUERDA
            if (invertedButtons) {
                x = Game.resolutionX * 0.03f;
            } else {
                x = Game.resolutionX * 0.68f;
            }
            ButtonHandler.button2Left = new Button("button2Left", x, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_BALLS_STARS, 2f, Button.BUTTON_TYPE_BUTTONS_AND_BALLS);
            ButtonHandler.button2Left.setTextureMap(19);
            ButtonHandler.button2Left.textureMapUnpressed = 19;
            ButtonHandler.button2Left.textureMapPressed = 18;
        }
    }
}
