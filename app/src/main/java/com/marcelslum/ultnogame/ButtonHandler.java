package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class ButtonHandler {

    static Button buttonFinalTargetLeft;
    static Button buttonFinalTargetRight;

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
        float framePadd = buttonSize*0.15f;

        buttonFinalTargetLeft = Game.buttonPool.get();
        buttonFinalTargetLeft.setData("buttonFinalTargetLeft", Game.resolutionX - buttonSize*1.5f,
                Game.gameAreaResolutionY * 0.6f, buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_RIGHT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_RIGHT_PRESS_ID));
        buttonFinalTargetLeft.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (Game.gameState == Game.GAME_STATE_JOGAR) {

                    if (!Game.abdicateAngle){
                        if (Game.ballDataPanel != null) {
                            Game.ballDataPanel.initAbdicateAngleAnim();
                        }

                        if (SaveGame.saveGame.currentLevelNumber > 3 && SaveGame.saveGame.currentLevelNumber < 8) {
                            Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelMessageAbdicate));
                        }
                        Game.abdicateAngle = true;

                        for (int i = 0; i < Game.balls.size(); i++) {

                            if (Game.balls.get(i).isAlive && !Game.balls.get(i).isFake){
                                if (Game.balls.get(i).initTempoAnguloMedio != -1) {
                                    Game.balls.get(i).tempoAnguloMedio += (TimeHandler.timeOfLevelPlay - Game.balls.get(i).initTempoAnguloMedio);
                                }
                                if (Game.balls.get(i).initTempoAnguloMaximo != -1) {
                                    Game.balls.get(i).tempoAnguloMaximo += (TimeHandler.timeOfLevelPlay - Game.balls.get(i).initTempoAnguloMaximo);
                                }
                                if (Game.balls.get(i).initTempoAnguloMinimo != -1) {
                                    Game.balls.get(i).tempoAnguloMinimo += (TimeHandler.timeOfLevelPlay - Game.balls.get(i).initTempoAnguloMinimo);
                                }

                            }

                        }
                    }

                    for (int i = 0; i < Game.balls.size(); i++) {
                        if (Game.balls.get(i) != null){
                            Game.balls.get(i).changeAngleManualy(true);
                        }
                    }
                }
            }
        });

        Rectangle frame4 = new Rectangle("frameButton", Game.resolutionX - buttonSize*1.5f - framePadd,
                Game.gameAreaResolutionY * 0.6f - framePadd,  Entity.TYPE_OTHER, buttonSize + (framePadd * 2f), buttonSize + (framePadd * 2f), -1, BallDataPanel.COLOR_BAR_GREEN_DARK);

        frame4.setMultiColor(Color.cinza60.changeAlpha(0.6f), Color.branco.changeAlpha(0.6f), Color.cinza60.changeAlpha(0.6f), Color.cinza60.changeAlpha(0.6f));
        frame4.addTopRectangle(0.9f, BallDataPanel.COLOR_BAR_GREEN_LIGHT.changeAlpha(0.1f).changeAlpha(0.6f), Color.zero, Color.zero, Color.zero,
                0.05f, 0f, 1000f, Color.cinza40);

        buttonFinalTargetLeft.addFrame(frame4);


        //-------------------

        buttonFinalTargetRight = Game.buttonPool.get();
        buttonFinalTargetRight.setData("buttonFinalTargetLeft", buttonSize*0.5f,
                Game.gameAreaResolutionY * 0.6f, buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
        buttonFinalTargetRight.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (Game.gameState == Game.GAME_STATE_JOGAR) {

                    if (!Game.abdicateAngle){
                        if (Game.ballDataPanel != null) {
                            Game.ballDataPanel.initAbdicateAngleAnim();
                        }

                        if (SaveGame.saveGame.currentLevelNumber > 3 && SaveGame.saveGame.currentLevelNumber < 8) {
                            Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelMessageAbdicate));
                        }

                        Game.abdicateAngle = true;
                    }

                    for (int i = 0; i < Game.balls.size(); i++) {
                        if (Game.balls.get(i) != null){
                            Game.balls.get(i).changeAngleManualy(false);
                        }
                    }
                }
            }
        });

        Rectangle frame5 = new Rectangle("frameButton", buttonSize*0.5f - framePadd,
                Game.gameAreaResolutionY * 0.6f - framePadd,  Entity.TYPE_OTHER, buttonSize + (framePadd * 2f), buttonSize + (framePadd * 2f), -1, BallDataPanel.COLOR_BAR_GREEN_DARK);
        frame5.setMultiColor(Color.cinza60.changeAlpha(0.6f), Color.branco.changeAlpha(0.6f), Color.cinza60.changeAlpha(0.6f), Color.cinza60.changeAlpha(0.6f));
        frame5.addTopRectangle(0.9f, BallDataPanel.COLOR_BAR_GREEN_LIGHT.changeAlpha(0.1f).changeAlpha(0.6f), Color.zero, Color.zero, Color.zero,
                0.05f, 0f, 1000f, Color.cinza40.changeAlpha(0.6f));
        buttonFinalTargetRight.addFrame(frame5);



        ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
        ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

        buttonReturn = Game.buttonPool.get();

        buttonReturn.setData("buttonReturn", buttonSize*0.5f,
                Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
        buttonReturn.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Game.vibrate(Game.VIBRATE_SMALL);
                Game.sound.playPlayMenuBig();
                //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                } else if (Game.gameState == Game.GAME_STATE_SELECAO_GRUPO){
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
                    if (SaveGame.saveGame.currentLevelNumber < 1000){
                        Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                    } else {
                        Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                    }
                } else if (Game.gameState == Game.GAME_STATE_MENU_TUTORIAL) {
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);
                }else if (Game.gameState == Game.GAME_STATE_MENU_JOGAR){
                        Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
                }else if (Game.gameState == Game.GAME_STATE_MENU_EXPLICACAO_TREINAMENTO){
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);
                }else if (Game.gameState == Game.GAME_STATE_MENU_TUTORIAL){
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL){
                    Tutorial.currentTutorialObject.previous();
                } else if (Game.gameState == Game.GAME_STATE_SOBRE){
                    Game.setGameState(Game.GAME_STATE_OPCOES);
                } else if (Game.gameState == Game.GAME_STATE_ESTATISTICAS){
                    MessagesHandler.statsTextView.blockAndClearDisplay();
                    Game.setGameState(Game.GAME_STATE_MENU_JOGAR);
                } else if (Game.gameState == Game.GAME_STATE_OPCOES){
                    Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
                } else if (Game.gameState == Game.GAME_STATE_OPCOES_JOGABILIDADE){
                    MenuHandler.menuOptionsPlay.clearDisplay();
                    MenuHandler.menuOptionsPlay.block();
                    SelectorHandler.backAllSelectors();
                    Game.setGameState(Game.GAME_STATE_OPCOES);
                }
            }
        });


        Rectangle frame1 = new Rectangle("frameButtonReturn", buttonSize*0.5f - framePadd,
                Game.resolutionY - (buttonSize*1.5f) - framePadd,  Entity.TYPE_OTHER, buttonSize + (framePadd * 2f), buttonSize + (framePadd * 2f), -1, BallDataPanel.COLOR_BAR_GREEN_DARK);
        frame1.setMultiColor(Color.cinza60, Color.branco, Color.cinza60, Color.cinza60);
        frame1.addTopRectangle(0.9f, BallDataPanel.COLOR_BAR_GREEN_LIGHT.changeAlpha(0.1f), Color.zero, Color.zero, Color.zero,
                0.05f, 0f, 1000f, Color.cinza40);

        buttonReturn.addFrame(frame1);


        //-------------

        buttonReturnObjectivesPause = Game.buttonPool.get();
        buttonReturnObjectivesPause.setData("buttonReturnObjectivesPause", buttonSize*0.5f, Game.gameAreaResolutionY - (buttonSize*1.5f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
        buttonReturnObjectivesPause.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {

                Game.vibrate(Game.VIBRATE_SMALL);
                Game.sound.playPlayMenuBig();
                //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
                    Game.setGameState(Game.GAME_STATE_PAUSE);
                    MessagesHandler.messageBack.clearDisplay();
                    MessagesHandler.messageBack.setY(MessagesHandler.yOfMessageBackAndContinue);
                    MessagesHandler.messageBack.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
                }
            }
        });

        Rectangle frame2 = new Rectangle("frameButtonReturn", buttonSize*0.5f - framePadd,
                Game.gameAreaResolutionY - (buttonSize*1.5f) - framePadd,  Entity.TYPE_OTHER, buttonSize + (framePadd * 2f), buttonSize + (framePadd * 2f), -1, BallDataPanel.COLOR_BAR_GREEN_DARK);

        frame2.setMultiColor(Color.cinza60, Color.branco, Color.cinza60, Color.cinza60);
        frame2.addTopRectangle(0.9f, BallDataPanel.COLOR_BAR_GREEN_LIGHT.changeAlpha(0.1f), Color.zero, Color.zero, Color.zero,
                0.05f, 0f, 1000f, Color.cinza40);
        buttonReturnObjectivesPause.addFrame(frame2);


        buttonGroupLeaderboard = Game.buttonPool.get();
        buttonGroupLeaderboard.setData("buttonGroupLeaderboard", Game.resolutionX - buttonSize*1.5f,
                        Game.resolutionY - (buttonSize*1.5f), buttonSize, buttonSize,
                        Texture.TEXTURES, 1.2f,
                        TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_GROUP_LEADERBOARD_ID),
                        TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_GROUP_LEADERBOARD_PRESS_ID));
        buttonGroupLeaderboard.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    Game.vibrate(Game.VIBRATE_SMALL);
                    Game.sound.playPlayMenuBig();
                    //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
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

                        GoogleAPI.showLeaderboards(id);

                    }

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
                Game.sound.playPlayMenuBig();
                //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
                if (Game.gameState == Game.GAME_STATE_VITORIA) {
                    Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
                } else if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO) {
                    Sound.stopAndReleaseMusic();
                    Game.prepareAfterInterstitialFlag = false;
                    Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
                } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL) {
                    Game.setGameState(Game.GAME_STATE_PREPARAR);
                } else if (Game.gameState == Game.GAME_STATE_TUTORIAL) {
                    Tutorial.currentTutorialObject.next();
                }
            }
        });

        Rectangle frame3 = new Rectangle("frameButtonReturn", Game.resolutionX - buttonSize*1.5f - framePadd,
                Game.resolutionY - (buttonSize*1.5f) - framePadd,  Entity.TYPE_OTHER, buttonSize + (framePadd * 2f), buttonSize + (framePadd * 2f), -1, BallDataPanel.COLOR_BAR_GREEN_DARK);

        frame3.setMultiColor(Color.cinza60, Color.branco, Color.cinza60, Color.cinza60);
        frame3.addTopRectangle(0.9f, BallDataPanel.COLOR_BAR_GREEN_LIGHT.changeAlpha(0.1f), Color.zero, Color.zero, Color.zero,
                0.05f, 0f, 1000f, Color.cinza40);

        buttonContinue.addFrame(frame3);

    }


    public static void createGameButtons(int barsQuantity, boolean invertedButtons) {
        float y = Game.resolutionY * 0.86f;
        float buttonSize = Game.resolutionY * 0.13f;

        // BOTÃO 1 ESQUERDA
        float x;
        if (invertedButtons){
            x = Game.resolutionX * 0.85f;// se tiver somente um botão, fica vai um pouco mais para a esquerda
            if (barsQuantity > 1) {
                x = Game.resolutionX * 0.7f;
            }
        } else {

            x = Game.resolutionX * 0.06f;// se tiver somente um botão, fica vai um pouco mais para a direita
        }

        button1Left = Game.buttonPool.get();
        button1Left.setData("button1Left", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_PRESS_ID));
        button1Left.alpha = 0.7f;

        // BOTÃO 2 DIREITA
        if (invertedButtons){
            x = Game.resolutionX * 0.06f;// se tiver somente um botão, fica vai um pouco mais para a direita
            if (barsQuantity > 1) {
                x = Game.resolutionX * 0.21f;
            }
        } else {
            x = Game.resolutionX * 0.85f;// se tiver somente um botão, fica vai um pouco mais para a esquerda
        }

        button2Right = Game.buttonPool.get();
        button2Right.setData("buttonRight", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID));

        button2Right.alpha = 0.7f;

        if (barsQuantity > 1) {
            // BOTÃO 1 DIREITA
            if (invertedButtons) {
                x = Game.resolutionX * 0.88f;
            } else {
                x = Game.resolutionX * 0.21f;
            }
            button1Right = Game.buttonPool.get();
            button1Right.setData("button1Right",x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID));
            button1Right.alpha = 0.7f;

            // BOTÃO 2 ESQUERDA
            if (invertedButtons) {
                x = Game.resolutionX * 0.03f;
            } else {
                x = Game.resolutionX * 0.7f;
            }
            button2Left = Game.buttonPool.get();
            button2Left.setData("button2Left", x, y, buttonSize, buttonSize, Texture.TEXTURES, 2f,
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_BUTTON_BAR_LEFT_PRESS_ID));
            button2Left.alpha = 0.7f;
        }
    }
}
