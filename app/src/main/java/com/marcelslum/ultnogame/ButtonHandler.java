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
        buttonReturn = Game.buttonPool.get();


        buttonReturn.setData("buttonReturn", buttonSize*0.5f,
                Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
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
                    Tutorial.currentTutorialObject.previous();
                } else if (Game.gameState == Game.GAME_STATE_SOBRE){
                    Game.setGameState(Game.GAME_STATE_OPCOES);
                }
            }
        });

        buttonReturnObjectivesPause = Game.buttonPool.get();
        buttonReturnObjectivesPause.setData("buttonReturnObjectivesPause", buttonSize*0.5f, Game.gameAreaResolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
        buttonReturnObjectivesPause.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                    MessagesHandler.messageBack.clearDisplay();
                    MessagesHandler.messageBack.setY(MessagesHandler.yOfMessageBackAndContinue);
                    MessagesHandler.messageBack.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
                }
            }
        });

        buttonGroupLeaderboard = Game.buttonPool.get();
        buttonGroupLeaderboard.setData("buttonGroupLeaderboard", Game.resolutionX - buttonSize*1.5f,
                        Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize,
                        Texture.TEXTURES, 1.2f,
                        TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_GROUP_LEADERBOARD_ID),
                        TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_GROUP_LEADERBOARD_PRESS_ID));
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

        buttonContinue = Game.buttonPool.get();
        buttonContinue.setData("buttonContinue", Game.resolutionX - buttonSize*1.5f, Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_RIGHT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_RIGHT_PRESS_ID));
        buttonContinue.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {

                Game.vibrate(Game.VIBRATE_SMALL);
                Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_VITORIA) {
                    Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
                } else if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO) {
                    Game.prepareAfterInterstitialFlag = false;
                    Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL) {
                    Game.setGameState(Game.GAME_STATE_PREPARAR);
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL) {
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

        button1Left = Game.buttonPool.get();
        button1Left.setData("button1Left", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_PRESS_ID));
        button1Left.alpha = 0.7f;

        // BOTÃO 2 DIREITA
        if (invertedButtons){
            x = Game.resolutionX * 0.03f;
            if (barsQuantity > 1) {
                x = Game.resolutionX * 0.22f;
            }
        } else {
            x = Game.resolutionX * 0.87f;
        }

        button2Right = Game.buttonPool.get();
        button2Right.setData("buttonRight", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID));

        if (barsQuantity > 1) {
            // BOTÃO 1 DIREITA
            if (invertedButtons) {
                x = Game.resolutionX * 0.87f;
            } else {
                x = Game.resolutionX * 0.22f;
            }
            button1Right = Game.buttonPool.get();
            button1Right.setData("button1Right",x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID));
            ButtonHandler.button1Right.alpha = 0.7f;

            // BOTÃO 2 ESQUERDA
            if (invertedButtons) {
                x = Game.resolutionX * 0.03f;
            } else {
                x = Game.resolutionX * 0.68f;
            }
            button2Left = Game.buttonPool.get();
            button2Left.setData("button2Left", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_PRESS_ID));
        }
    }
}
