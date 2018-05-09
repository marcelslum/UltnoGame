package com.marcelslum.ultnogame;


import android.util.Log;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameStateHandler{

    public final static int GAME_STATE_INTRO =  100; // vai para MENU_INICIAL

    public final static int GAME_STATE_MENU_INICIAL =  110; // vai para MENU_JOGAR, OPCOES, MENU_EXPLICACAO_TREINAMENTO, GAME_STATE_CARREGAR_JOGO_SALVO_NUVEM

    public final static int GAME_STATE_MENU_JOGAR = 120; // vai para MENU_INICIAL, ESTATISTICAS, SELECAO_TUTORIAL, SELECAO_GRUPO

    public final static int GAME_STATE_OPCOES =  130; // vai para MENU_INICIAL, OPCOES_JOGABILIDADE, INTRO
    public final static int GAME_STATE_OPCOES_JOGABILIDADE =  131; // vai para OPCOES

    public final static int GAME_STATE_MENU_GOOGLE = 140;

    public final static int GAME_STATE_EXPLICACAO_TREINAMENTO = 150; // vai para MENU_JOGAR, PREPARAR_TREINAMENTO
    public final static int GAME_STATE_PREPARAR_TREINAMENTO = 151; // vai para MENU_DURANTE_TREINAMENTO
    public final static int GAME_STATE_MENU_DURANTE_TREINAMENTO = 152; // vai para JOGAR, INTERSTITIAL
    public final static int GAME_STATE_NOVA_TENTATIVA_TREINAMENTO = 153; // vai para JOGAR
    public final static int GAME_STATE_FINAL_TREINAMENTO = 154; // vai para INTERSTITIAL depois MENU_JOGAR

    public final static int GAME_STATE_MENU_SAVE_FIRST_TIME =  170; // vai para MENU_INICIAL
    public final static int GAME_STATE_MENU_CARREGAR_JOGO_SALVO_NUVEM = 171; // vai para MENU_INICIAL

    public final static int GAME_STATE_ESTATISTICAS = 160; // vai para INTERSTITIAL

    public final static int GAME_STATE_SELECAO_TUTORIAL =  180; // vai para MENU_JOGAR
    public final static int GAME_STATE_TUTORIAL =  181; // vai para SELECAO_TUTORIAL

    public final static int GAME_STATE_SOBRE =  190; // vai para MENU_OPCOES

    public final static int GAME_STATE_SELECAO_GRUPO =  200; // vai para MENU_JOGAR, SELECAO_LEVEL
    public final static int GAME_STATE_SELECAO_LEVEL =  201; // vai para MOSTRAR_OBJETIVOS, SELECAO_GRUPO
    public final static int GAME_STATE_MOSTRAR_OBJETIVOS =  202; // vai para PREPARAR, SELECAO_LEVEL

    public final static int GAME_STATE_PREPARAR = 210; // vai para PREJOGAR
    public final static int GAME_STATE_PRE_JOGAR = 211; // vai para JOGAR
    public final static int GAME_STATE_JOGAR = 212; // vai para PAUSE, VITORIA_1, DERROTA

    public final static int GAME_STATE_VITORIA_1 =  220; // vai para VITORIA_1
    public final static int GAME_STATE_VITORIA_2 =  221; // vai para INTERSTITIAL
    public final static int GAME_STATE_DERROTA =  222; // vai para INTERSTITIAL, SELECAO_LEVEL, PREPARAR

    public final static int GAME_STATE_PAUSE =  230; // vai para PAUSE_OPCOES, PAUSE_OBJETIVO, JOGAR, SELECAO_LEVEL
    public final static int GAME_STATE_PAUSE_OPCOES =  231; // vai para PAUSE
    public final static int GAME_STATE_PAUSE_OBJETIVO =  232; // vai para PAUSE

    public final static int GAME_STATE_INTERSTITIAL =  240; // vai para MENU_JOGAR, SELECAO_LEVEL

    public static int gameState;
    public static int previousState;
    public static boolean sameState;
    // game state


    public static void makeTransitionBetweenStates(int previousState, int newState){


        //toma as ações independentemente de qual seja o estado anterior


        if (previousState == GAME_STATE_INTRO){


        } else if (previousState == GAME_STATE_MENU_INICIAL){
            
                // PODE IR PARA OS SEGUINTES STATES:
                //  - GAME_STATE_OPCOES
                //  - GAME_STATE_MENU_SAVE_FIRST_TIME
                //  - GAME_STATE_MENU_JOGAR
                //  - GAME_STATE_CARREGAR_JOGO_SALVO_NUVEM

                MenuHandler.menuInicial.blockAndClearDisplay();
                MessagesHandler.messageMaxScoreTotal.clearDisplay();

                GoogleAPI.displayGoogleInfo();


                if (newState != GAME_STATE_OPCOES || newState != GAME_STATE_MENU_JOGAR){
                    Game.tittle.clearDisplay();
                    if (GoogleAPI.playerIconImage != null) GoogleAPI.playerIconImage.clearDisplay();
                }
            
        } else if (previousState == GAME_STATE_MENU_JOGAR) {

            // vai para MENU_INICIAL, ESTATISTICAS, SELECAO_TUTORIAL, SELECAO_GRUPO

            MenuHandler.menuPlay.clearDisplay();

            if (newState == GAME_STATE_MENU_INICIAL) {
                MessagesHandler.messageBack.clearDisplay();
                ButtonHandler.buttonReturn.blockAndClearDisplay();
            } else {
                Game.tittle.clearDisplay();
                MessagesHandler.messageGoogleLogged.clearDisplay();
                if (GoogleAPI.playerIconImage != null) GoogleAPI.playerIconImage.clearDisplay();
            }


        } else if (previousState == GAME_STATE_OPCOES){
            // vai para MENU_INICIAL, OPCOES_JOGABILIDADE, INTRO


            MenuHandler.menuOptions.blockAndClearDisplay();

            if (newState == GAME_STATE_SOBRE){
                Game.tittle.clearDisplay();
                MessagesHandler.messageBack.clearDisplay();
            }

            if (newState == GAME_STATE_INTRO){
                MessagesHandler.messageBack.clearDisplay();
                Game.tittle.clearDisplay();
                ButtonHandler.buttonReturn.blockAndClearDisplay();
                if (GoogleAPI.playerIconImage != null)GoogleAPI.playerIconImage.clearDisplay();
            }

            if (newState != GAME_STATE_MENU_INICIAL){
                ButtonHandler.buttonReturn.blockAndClearDisplay();
                MessagesHandler.messageBack.clearDisplay();
            }


        } else if (previousState == GAME_STATE_OPCOES_JOGABILIDADE){
            // vai para OPCOES

            MenuHandler.menuOpcoesJogabilidade.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_EXPLICACAO_TREINAMENTO){
            // vai para MENU_JOGAR, PREPARAR_TREINAMENTO

            MessagesHandler.messageExplicacaoTreinamento.clearDisplay();
            MessagesHandler.messageContinue.clearDisplay();
            ButtonHandler.buttonContinue.blockAndClearDisplay();

            if (newState == GAME_STATE_PREPARAR_TREINAMENTO){
                Game.mainActivity.hideAdView();
                MessagesHandler.messageBack.clearDisplay();
                ButtonHandler.buttonReturn.blockAndClearDisplay();
            }

        } else if (previousState == GAME_STATE_PREPARAR_TREINAMENTO){
            // vai para MENU_DURANTE_TREINAMENTO

        } else if (previousState == GAME_STATE_MENU_DURANTE_TREINAMENTO){
            // vai para JOGAR, INTERSTITIAL

            if (newState == GAME_STATE_JOGAR) {
                MenuHandler.menuDuranteTreinamento.block();
                for (int i = 0; i < MessagesHandler.messageExplicacaoDuranteTreinamento.texts.size(); i++) {
                    if (MessagesHandler.messageExplicacaoDuranteTreinamento.texts.get(i).color != Color.azul40) {
                        MessagesHandler.messageExplicacaoDuranteTreinamento.texts.get(i).setColor(Color.transparente);
                    } else {
                        MessagesHandler.messageExplicacaoDuranteTreinamento.texts.get(i).setColor(Color.azul40.changeAlpha(0.7f));
                    }
                }
            } else if (newState == GAME_STATE_INTERSTITIAL){
                Training.training = false;
                MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearDisplay();
            }

        } else if (previousState == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){
            // vai para JOGAR

        } else if (previousState == GAME_STATE_FINAL_TREINAMENTO){
            // vai para INTERSTITIAL depois MENU_JOGAR

            MessagesHandler.messageExplicacaoDuranteTreinamento.clearDisplay();
            ButtonHandler.buttonContinue.blockAndClearDisplay();
            MessagesHandler.messageContinue.clearDisplay();
            Training.training = false;
            MessagesHandler.messageExplicacaoDuranteTreinamento.clearDisplay();

        } else if (previousState == GAME_STATE_MENU_SAVE_FIRST_TIME){
            // vai para MENU_INICIAL
            MenuHandler.menuFirstSaveGame.blockAndClearDisplay();
            MessagesHandler.messageMenuSaveNotSeen.clearDisplay();

        } else if (previousState == GAME_STATE_MENU_CARREGAR_JOGO_SALVO_NUVEM){
            // vai para MENU_INICIAL

            MenuHandler.menuCarregar.blockAndClearDisplay();
            MessagesHandler.messageMenuCarregarJogo.display();

        } else if (previousState == GAME_STATE_ESTATISTICAS){
            // vai para INTERSTITIAL

            Game.statsGraphs.clear();
            MessagesHandler.messageStatTittle.clearDisplay();
            MessagesHandler.messageStatDescricao.clearDisplay();
            ButtonHandler.buttonContinue.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_SELECAO_TUTORIAL){
            // vai para MENU_JOGAR, TUTORIAL

            MessagesHandler.messageMenu.clearDisplay();
            MessagesHandler.messageSubMenu.clearDisplay();
            MenuHandler.tutorialMenu.blockAndClearDisplay();

            if (newState == GAME_STATE_TUTORIAL){
                Game.mainActivity.hideAdView();
            }


        } else if (previousState == GAME_STATE_TUTORIAL){
            // vai para SELECAO_TUTORIAL

            Tutorial.tutorialImage.clearDisplay();
            Tutorial.tutorialTextBox.clearDisplay();
            ButtonHandler.buttonContinue.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_SOBRE){
            // vai para MENU_OPCOES

            MessagesHandler.aboutTextView.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_SELECAO_GRUPO){
            // vai para MENU_JOGAR, SELECAO_LEVEL

            MenuHandler.groupMenu.blockAndClearDisplay();

            if (newState == GAME_STATE_MENU_JOGAR){

                MessagesHandler.messageMenu.clearDisplay();
                MessagesHandler.messageSubMenu.clearDisplay();
                MessagesHandler.starForMessage.clearDisplay();
                MessagesHandler.messageConqueredStarsTotal.clearDisplay();
                MenuHandler.menuTutorialUnvisited.blockAndClearDisplay();
            } 
            
            
                
                

        } else if (previousState == GAME_STATE_SELECAO_LEVEL){
            // vai para MOSTRAR_OBJETIVOS, SELECAO_GRUPO

            MenuHandler.groupMenu.blockAndClearDisplay();
            ButtonHandler.buttonGroupLeaderboard.blockAndClearDisplay();

            if (newState == GAME_STATE_MOSTRAR_OBJETIVOS){
                MessagesHandler.starForMessage.clearDisplay();
                MessagesHandler.messageConqueredStarsTotal.clearDisplay();
                MenuHandler.menuTutorialUnvisited.blockAndClearDisplay();
            }

        } else if (previousState == GAME_STATE_MOSTRAR_OBJETIVOS){
            // vai para PREPARAR, SELECAO_LEVEL

            if (Game.tipTextBox != null) Game.tipTextBox.blockAndClearDisplay();
            Game.levelGoalsPanel = null;

            ButtonHandler.buttonContinue.blockAndClearDisplay();
            MessagesHandler.messageContinue.clearDisplay();
            MessagesHandler.messageBack.clearDisplay();

            if (newState == GAME_STATE_PREPARAR){
                MessagesHandler.messageMenu.clearDisplay();
                MessagesHandler.messageSubMenu.clearDisplay();
                ButtonHandler.buttonReturn.blockAndClearDisplay();
                Game.mainActivity.hideAdView();
                MessagesHandler.notConnectedTextView.clearDisplay();
            }

        } else if (previousState == GAME_STATE_PREPARAR){
            // vai para PREJOGAR

            MessagesHandler.messagePreparation.clearDisplay();
            MessagesHandler.messagePreparation.alpha = 1f;

        } else if (previousState == GAME_STATE_PRE_JOGAR){
            // vai para JOGAR




        } else if (previousState == GAME_STATE_JOGAR){
            // vai para PAUSE, VITORIA_1, DERROTA

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            Sound.soundPool.autoPause();

            TimeHandler.stopTimeOfLevelPlay();

            Game.stopAllGameEntities();
            Game.reduceAllGameEntitiesAlpha(300);

            if (newState == GAME_STATE_VITORIA_1){

                MessagesHandler.messageCurrentLevel.reduceAlphaAndClearDisplay(500);

                Game.bordaB.setY(Game.resolutionY);

                if (ButtonHandler.button1Left != null) {
                    Utils.createSimpleAnimation(ButtonHandler.button1Left, "alphaVitoria", "alpha", 1000, ButtonHandler.button1Left.alpha, 0f).start();
                    ButtonHandler.button1Left.block();
                }
                if (ButtonHandler.button2Right != null) {
                    Utils.createSimpleAnimation(ButtonHandler.button2Right, "alphaVitoria", "alpha", 1000, ButtonHandler.button2Right.alpha, 0f).start();
                    ButtonHandler.button2Right.block();
                }
                if (ButtonHandler.button2Left != null) {
                    Utils.createSimpleAnimation(ButtonHandler.button2Left, "alphaVitoria", "alpha", 1000, ButtonHandler.button2Left.alpha, 0f).start();
                    ButtonHandler.button2Left.block();
                    Utils.createSimpleAnimation(ButtonHandler.button1Right, "alphaVitoria", "alpha", 1000, ButtonHandler.button1Right.alpha, 0f).start();
                    ButtonHandler.button1Right.block();
                }

                Utils.createSimpleAnimation(Game.ballDataPanel, "alphaVitoria", "alpha", 1000, Game.ballDataPanel.alpha, 0f).start();

            }

            if (newState == GAME_STATE_DERROTA){

                Sound.stopAndReleaseMusic();

            }

            if (newState == GAME_STATE_PAUSE){

                Sound.pauseMusic();

            }

            if (newState == GAME_STATE_MENU_DURANTE_TREINAMENTO){
                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                }
                if (MessagesHandler.messageTrainingState2 != null) {
                    MessagesHandler.messageTrainingState2.clearDisplay();
                }
            }

            if (newState == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){

                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                }
            }


        } else if (previousState == GAME_STATE_VITORIA_1){
            // vai para VITORIA_2
            Game.clearAllGameEntities();
            ButtonHandler.buttonContinue.blockAndClearDisplay();

            Game.ballGoalsPanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);
            MessagesHandler.messageTime.reduceAlphaAndClearDisplay(500);

            if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.clearDisplay();
            if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.clearDisplay();
            if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.clearDisplay();
            if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.clearDisplay();

            MessageStar.messageStars.clearDisplay();

        } else if (previousState == GAME_STATE_VITORIA_2){
            // vai para INTERSTITIAL
            Game.eraseAllGameEntities();
            MessageStarWin.messageStarsWin.clearDisplay();
            if (Game.groupsUnblocked != null) Game.groupsUnblocked.clear();
            if (Game.currentLevelIcon != null) Game.currentLevelIcon.clearDisplay();


        } else if (previousState == GAME_STATE_DERROTA){
            // vai para INTERSTITIAL, SELECAO_LEVEL, PREPARAR

            if (newState == GAME_STATE_SELECAO_LEVEL) {
                Game.eraseAllGameEntities();
            }
            
            if (newState == GAME_STATE_INTERSTITIAL || newState == GAME_STATE_PREPARAR){
                Game.mainActivity.hideAddView();
                MenuHandler.menuGameOver.blockAndClearDisplay();
                MessagesHandler.messageGameOver.clearDisplay();
            }

            MessageStar.messageStars.clearDisplay();


        } else if (previousState == GAME_STATE_PAUSE){
            // vai para PAUSE_OPCOES, PAUSE_OBJETIVO, JOGAR, SELECAO_LEVEL

            if (newState == GAME_STATE_JOGAR){
                MenuHandler.menuPause.block();
                Game.mainActivity.hideAdView();
                MessagesHandler.messageInGame.clearDisplay();
                Game.increaseAllGameEntitiesAlpha(500);
                MessagesHandler.messageInGame.reduceAlpha(500, 0f, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        MessagesHandler.messageInGame.clearDisplay();
                        MessagesHandler.messageInGame.alpha = 1f;
                    }
                });
            }
            if (newState == GAME_STATE_SELECAO_LEVEL){
                MessagesHandler.messageInGame.clearDisplay();
            }
            
            MenuHandler.menuPause.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_PAUSE_OPCOES){
            // vai para PAUSE



        } else if (previousState == GAME_STATE_PAUSE_OBJETIVO){
            // vai para PAUSE

            ButtonHandler.buttonReturnObjectivesPause.blockAndClearDisplay();

            MessagesHandler.messageMenu.clearDisplay();
            MessagesHandler.messageSubMenu.clearDisplay();

            ButtonHandler.buttonReturnObjectivesPause.blockAndClearDisplay();
            MessagesHandler.messageBack.clearDisplay();
            MessagesHandler.messageBack.setY(MessagesHandler.yOfMessageBackAndContinue);
            MessagesHandler.messageBack.setColor(Color.cinza50);


        } else if (previousState == GAME_STATE_INTERSTITIAL){
            // vai para MENU_JOGAR, SELECAO_LEVEL

        }
  }

    public static void setGameState(int newState){

        Log.e("game", "set game state "+newState);

        makeTransitionBetweenStates(gameState, newState);

        sameState = false;
        previousState = gameState;
        if (newState == gameState){
            sameState = true;
        }
        gameState = newState;

        Game.blockAndWaitTouchRelease();

        TimeHandler.timeOfLevelPlayBlocked = true;

        if (newState == GAME_STATE_INTERSTITIAL){

            Game.mainActivity.showInterstitial();

        } if (newState == GAME_STATE_MENU_INICIAL){

            if (previousState != GAME_STATE_OPCOES) {
                Game.showBlackFrameTransition(500);
            }

            if (previousState == GAME_STATE_INTRO){
                Game.initTittle();
                GoogleAPI.loadAchievements();
                Game.mainActivity.showAdView();
                Game.esconderEntidadesFixas();
                Game.bordaE.display();
                Game.bordaD.display();
                Game.bordaC.display();
                Game.bordaB.display();
            }

            Game.tittle.display();

            MenuHandler.menuInicial.unblockAndDisplay();

            GoogleAPI.displayGoogleInfo();

            MessagesHandler.messageMaxScoreTotal.setText(
                    Game.getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()));
            MessagesHandler.messageMaxScoreTotal.display();


            MessagesHandler.setBottomMessage("", 0);

            if (Game.showMessageNotConnectedOnGoogle){
                Game.showMessageNotConnectedOnGoogle = false;
                String message = Game.mainActivity.getApplicationContext().getResources().getString(R.string.nao_conectado_google);
                MessagesHandler.setBottomMessage(message, 2000);
            }

            if (Game.versaoBeta) {
                MessagesHandler.messageBeta.display();
            }

            SaveGame.saveGame.save();

        } else if (newState == GAME_STATE_SOBRE) {

            MessagesHandler.aboutTextView.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();
        }
        else if (newState == GAME_STATE_ESTATISTICAS){

            Stats.currentStatsSheet = Stats.TEMPO_JOGO;
            Stats.showCurrentStat();
            ButtonHandler.buttonContinue.unblockAndDisplay();

        } else if (newState == GAME_STATE_MOSTRAR_OBJETIVOS){

            Game.showBlackFrameTransition(500);

            Level.levelGoalsObject = new LevelGoals();
            Level.levelGoalsObject.levelGoals = LevelGoalsLoader.getLevelGoals(SaveGame.saveGame.currentLevelNumber);

            Game.showTip();

            Game.levelGoalsPanel = new LevelGoalsPanel("levelGoalsPanel", Game.resolutionX * 0.2f, Game.resolutionY * 0.2f, Game.resolutionX * 0.025f, Game.resolutionX * 0.79f);

            for (int i = 0; i < Level.levelGoalsObject.levelGoals.size(); i++){
                LevelGoal lg = Level.levelGoalsObject.levelGoals.get(i);
                Game.levelGoalsPanel.addLine(lg.numberOfStars, true, lg.text);
            }
            Game.levelGoalsPanel.appearGray(false);

            MessagesHandler.messageMenu.setText(Game.getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel) + " " + SaveGame.saveGame.currentLevelNumber);

            ButtonHandler.buttonContinue.unblockAndDisplay();
            MessagesHandler.messageContinue.display();
            MessagesHandler.messageBack.display();


        } else if (newState == GAME_STATE_PAUSE_OBJETIVO){

            Game.levelGoalsPanel.appearGrayAndShine(true);

            MessagesHandler.messageMenu.setText(Game.getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+ " " + SaveGame.saveGame.currentLevelNumber);
            MessagesHandler.messageSubMenu.display();

            ButtonHandler.buttonReturnObjectivesPause.unblockAndDisplay();
            MessagesHandler.messageBack.setY(Game.gameAreaResolutionY * 0.895f);
            MessagesHandler.messageBack.setColor(Color.pretoCheio);
            MessagesHandler.messageBack.display();

        } else if (newState == GAME_STATE_SELECAO_GRUPO) {

            Game.showBlackFrameTransition(500);

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");

            Game.sound.playMenuIconDrop();

            MenuHandler.updateGroupMenu();
            MenuHandler.groupMenu.appear();

            MessagesHandler.messageMenu.setText(Game.getContext().getResources().getString(R.string.messageMenuSelecaoMundo));

            if (previousState == GAME_STATE_MENU_JOGAR){
                StarsHandler.updateConqueredStars();
                MessagesHandler.messageMenu.display();
                MessagesHandler.starForMessage.alpha = 0f;
                MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
                MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
                MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
                MessagesHandler.starForMessage.display();
                MessagesHandler.messageConqueredStarsTotal.display();

                if (Tutorial.hasUnvisitedTutorial()){
                    MenuHandler.menuTutorialUnvisited.unblockAndDisplay();
                }
            }

        } else if (newState == GAME_STATE_SELECAO_LEVEL) {

            if (Game.apagarEstatisticasNoMenu){
                for (int i = 0; i < SaveGame.saveGame.stats.length; i++) {
                    SaveGame.saveGame.stats[i] = 0;
                }
            }

            Game.showBlackFrameTransition(500);

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");

            Game.sound.playMenuIconDrop();

            MenuHandler.updateLevelMenu();
            MenuHandler.levelMenu.appear();
            
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(Game.getContext().getResources().getString(R.string.messageMenuSelecaoLevel));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(Game.currentLevelsGroupDataSelected.name);

            if (previousState == GAME_STATE_MOSTRAR_OBJETIVOS){
                StarsHandler.updateConqueredStars();
                MessagesHandler.messageMenu.display();
                MessagesHandler.starForMessage.alpha = 0f;
                MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
                MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
                MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
                MessagesHandler.starForMessage.display();
                MessagesHandler.messageConqueredStarsTotal.display();

                if (Tutorial.hasUnvisitedTutorial()){
                    MenuHandler.menuTutorialUnvisited.unblockAndDisplay();
                }
            }

            ButtonHandler.buttonGroupLeaderboard.unblockAndDisplay();

        } else if (newState == GAME_STATE_SELECAO_TUTORIAL){

            Game.showBlackFrameTransition(500);

            Game.sound.playMenuIconDrop();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(Game.getContext().getResources().getString(R.string.messageMenuTutorial));
            MenuHandler.updateTutorialMenu();
            MenuHandler.tutorialMenu.appear();

        } else if (newState == GAME_STATE_MENU_JOGAR){

            if (!sameState) {
                Game.showBlackFrameTransition(500);
            }

            if (previousState != GAME_STATE_MENU_INICIAL){
                GoogleAPI.displayGoogleInfo();
                Game.tittle.display();
            }

            MessagesHandler.messageBack.display();
            MenuHandler.menuPlay.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (newState == GAME_STATE_EXPLICACAO_TREINAMENTO){

            Game.showBlackFrameTransition(500);
            MessagesHandler.messageExplicacaoTreinamento.display();
            MessagesHandler.messageContinue.display();
            ButtonHandler.buttonContinue.unblockAndDisplay();

        } else if (newState == GAME_STATE_MENU_DURANTE_TREINAMENTO){

            Game.showBlackFrameTransition(1000);

            Training.tentativaCertaTreinamento = 0;
            Training.treinamentoSucesso = false;
            Training.resetTrainingEntities();
            Training.setMenuDuranteTreinamentoMessage();

            MessagesHandler.messageExplicacaoDuranteTreinamento.display();
            MenuHandler.menuDuranteTreinamento.unblockAndDisplay();

        } else if (newState == GAME_STATE_FINAL_TREINAMENTO){

            Game.showBlackFrameTransition(1000);

            Training.tentativaCertaTreinamento = 0;
            Training.treinamentoSucesso = false;

            Training.resetTrainingEntities();

            MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento21), Color.azul40, 1.8f);
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento21a), Color.azul40, 1.2f);
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
            MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento21b), Color.cinza20);
            MessagesHandler.messageExplicacaoDuranteTreinamento.display();

            ButtonHandler.buttonContinue.unblockAndDisplay();
            MessagesHandler.messageContinue.display();

        } else if (newState == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){

            Training.resetTrainingEntities();

        }  else if (newState == GAME_STATE_INTRO) {

            Game.mainActivity.hideAdView();
            Log.e(Game.TAG, "init1");


            /* teste de conversão de milisegundos para tempo formatado
            Log.e(TAG, Utils.getTimeTextFromMiliSeconds(2000));
            Log.e(TAG, Utils.getTimeTextFromMiliSeconds(60000));
            Log.e(TAG, Utils.getTimeTextFromMiliSeconds(300000));
            Log.e(TAG, Utils.getTimeTextFromMiliSeconds(24020000));
            Log.e(TAG, Utils.getTimeTextFromMiliSeconds(324020000));
            Log.e(TAG, ""+Long.MAX_VALUE);
            Log.e(TAG, ""+Integer.MAX_VALUE);
            */

            Splash.init();

        } else if (newState == GAME_STATE_OPCOES){

            MessagesHandler.messageBack.display();

            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;

            Game.tittle.display();
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MenuHandler.menuOptions.unblockAndDisplay();

            SelectorHandler.repositionSelectors(newState);

            if (Game.mainActivity.isSignedIn()) {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(Game.getContext().getResources().getString(R.string.deslogarGoogle));
            } else {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(Game.getContext().getResources().getString(R.string.logarGoogle));
            }

        } else if (newState == GAME_STATE_OPCOES_JOGABILIDADE){

            SelectorHandler.repositionSelectors(newState);
            MenuHandler.menuOpcoesJogabilidade.unblockAndDisplay();

        } else if (newState == GAME_STATE_PAUSE_OPCOES){

            SelectorHandler.repositionSelectors(newState);
            Game.mainActivity.showAdView();
            MenuHandler.menuPauseOpcoes.unblockAndDisplay();
            MessagesHandler.messageInGame.y = Game.gameAreaResolutionY*0.15f;
            MessagesHandler.messageInGame.display();

        } else if (newState == GAME_STATE_MENU_SAVE_FIRST_TIME){

            MenuHandler.menuFirstSaveGame.unblockAndDisplay();
            MessagesHandler.messageMenuSaveNotSeen.display();

        } else if (newState == GAME_STATE_MENU_CARREGAR_JOGO_SALVO_NUVEM){

            MenuHandler.menuCarregar.unblockAndDisplay();

        } else if (newState == GAME_STATE_PREPARAR) {

            Game.showBlackFrameTransition(2500);

            Game.abdicateAngle = false;
            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;
            Sound.musicVolume = 1f;
            Sound.loadStaticGameAudioTracks();

            Stats.clearData();

            ParticleGenerator.loadParticleGenerators();

            //Game.eraseAllGameEntities();
            //Game.eraseAllHudEntities();

            if (Training.training) {
                LevelLoader.loadLevel(1);
                Level.levelGoalsObject = new LevelGoals();
            } else {
                LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
            }


            MessagesHandler.messageTime.cleanAnimations();

            if (!Training.training){
                MessagesHandler.messageTime.display();
                MessagesHandler.setMessageTime();
            } else {
                MessagesHandler.messageCurrentLevel.setText(Game.getContext().getResources().getString(R.string.mensagem_treinamento));
            }

            MessagesHandler.messageCurrentLevel.display();

            Level.levelGoalsObject.clearAchievements();

            TimeHandler.timeOfLevelPlay = 0;
            TimeHandler.secondsOfLevelPlay = 0;
            TimeHandler.lastSeconds = 0;


            Level.levelObject.loadEntities();

            ArrayList<float[]> values = new ArrayList<>();
            values.add(new float[]{0f, 6f});
            values.add(new float[]{0.14f, 5f});
            values.add(new float[]{0.28f, 4f});
            values.add(new float[]{0.4285f, 3f});
            values.add(new float[]{0.5714f, 2f});
            values.add(new float[]{0.7142f, 1f});
            values.add(new float[]{0.8571f, 0f});
            final Text innerMessagePreparation = MessagesHandler.messagePreparation;
            MessagesHandler.messagePreparation.setText("5");
            MessagesHandler.messagePreparation.setColor(Color.transparente);
            MessagesHandler.messagePreparation.display();

            Animation anim = new Animation(MessagesHandler.messagePreparation, "messagePreparation", "numberForAnimation", 7000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 4f) {
                        MessagesHandler.messagePreparation.setColor(Color.transparente);
                    } else if (innerMessagePreparation.numberForAnimation == 3f) {
                        Game.sound.playCounter();
                        MessagesHandler.messagePreparation.display();
                        MessagesHandler.messagePreparation.setColor(Color.vermelhoCheio);
                        innerMessagePreparation.setText("3");
                    } else if (innerMessagePreparation.numberForAnimation == 2f) {
                        Game.sound.playCounter();
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        Game.sound.playCounter();
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {

                        if (Training.training) {
                            innerMessagePreparation.setText(Game.getContext().getResources().getString(R.string.mensagem_treinar));
                        } else {
                            innerMessagePreparation.setText(Game.getContext().getResources().getString(R.string.mensagem_jogar));
                        }

                        if (Game.paraGravacaoVideo) {
                            MessagesHandler.messagePreparation.setColor(Color.transparente);
                        }


                        Animation anim = Utils.createSimpleAnimation(innerMessagePreparation, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                GameStateHandler.setGameState(GAME_STATE_PRE_JOGAR);
                            }
                        });
                        anim.start();
                    }
                }
            });
            anim.start();

            Game.checkIfDead();

        } else if (newState == GAME_STATE_PREPARAR_TREINAMENTO) {

            Game.showBlackFrameTransition(1500);

            Game.abdicateAngle = false;

            Sound.loadStaticGameAudioTracks();

            ParticleGenerator.loadParticleGenerators();

            //Game.eraseAllGameEntities();
            //Game.eraseAllHudEntities();

            LevelLoader.loadLevel(1);

            MessagesHandler.messageCurrentLevel.setText(Game.getContext().getResources().getString(R.string.mensagem_treinamento));
            MessagesHandler.messageCurrentLevel.display();

            Level.levelGoalsObject = new LevelGoals();

            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();

            Level.levelObject.loadEntities();

            Game.checkIfDead();

            Game.stopAllGameEntities();

            Game.reduceAllGameEntitiesAlpha(100);

            setGameState(GAME_STATE_MENU_DURANTE_TREINAMENTO);

        } else if (newState == GAME_STATE_PRE_JOGAR){

            Game.sound.playMusic();
            Game.updateNumberOfTargetsAlive();

            Game.timeOfPrePlay = Utils.getTimeMilliPrecision();

        } else if (newState == GAME_STATE_JOGAR){

            Game.bordaB.setY(Game.gameAreaResolutionY);

            for (int i = 0; i < Game.balls.size(); i++) {
                if (Game.balls.get(i).listenForExplosion){
                    Game.balls.get(i).replayAlarm();
                }
            }

            if (Game.initPausedFlag){
                Game.initPausedFlag = false;
                setGameState(GAME_STATE_PAUSE);
            } else {

                TimeHandler.resumeTimeOfLevelPlay();

                MessageStar.messageStars.reset();

                for (int i = 0; i < Game.bars.size(); i++) {
                    if (Game.bars.get(i).scaleVariationData != null) {
                        Game.bars.get(i).initScaleVariation();
                    }
                }
                for (int i = 0; i < Game.obstacles.size(); i++) {
                    if (Game.obstacles.get(i).scaleVariationData != null) {
                        Game.obstacles.get(i).initScaleVariation();
                    }
                    if (Game.obstacles.get(i).positionVariationData != null) {
                        Game.obstacles.get(i).initPositionVariation();
                    }
                }
                Game.resetTimeForPointsDecay();
                Game.freeAllGameEntities();
            }

        } else if (newState == GAME_STATE_DERROTA){

            Game.mainActivity.showAdView();
            Game.sound.playGameOver();
            SaveGame.addLevelPlayed();
            int totalStars = 0;
            for (int i = 0; i < Level.levelObject.levelGoalsObject.levelGoals.size(); i++){
                if (Level.levelObject.levelGoalsObject.levelGoals.get(i).achieved){
                    totalStars += Level.levelObject.levelGoalsObject.levelGoals.get(i).numberOfStars;
                }
            }
            MessageStar.messageStars.showAndGoAllGray(totalStars);
            MenuHandler.menuGameOver.appearAndUnblock(1000);
            MessagesHandler.messageGameOver.display();


            // ATUALIZA AS ESTATÍSTICAS
            Stats.totalPontosInclusiveRepetidosDerrota += (long)((float)ScoreHandler.scorePanel.value / 2f);
            Stats.numeroTotalLevelsFinalizadosDerrota += 1;
            int contador = 0;
            for (int i = 0; i < Game.targets.size(); i++) {
                if (!Game.targets.get(i).alive){
                    contador += 1;
                    break;
                }
            }
            Stats.numeroTotalAlvosAtingidosLevelsFinalizadosDerrota += contador;
            Stats.tempoJogadoDerrota += TimeHandler.timeOfLevelPlay;
            Stats.saveData();
            //^


            if (ScoreHandler.scorePanel.value > 0) {
                ScoreHandler.scorePanel.showMessage("-50%", 1000);
                int points = (int)((float)ScoreHandler.scorePanel.value / 2f);
                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                SaveGame.saveGame.setLevelPoints(SaveGame.saveGame.currentLevelNumber, points);
                ScoreHandler.setMaxScoreTotal();
                ScoreHandler.submitScores();
            }

            SaveGame.saveGame.save();

        } else if (newState == GAME_STATE_PAUSE){

            if (previousState == GAME_STATE_JOGAR) {
                Game.mainActivity.showAdView();
                MessagesHandler.messageInGame.display();
            }

            if (previousState == GAME_STATE_PAUSE_OPCOES) {

                MenuHandler.menuPause.getMenuOptionByName("Continuar").textObject.setText(Game.getContext().getResources().getString(R.string.continuarJogar));
                ArrayList<float[]> valuesAnimPause = new ArrayList<>();
                    valuesAnimPause.add(new float[]{0f, 1f});
                    valuesAnimPause.add(new float[]{0.25f, 2f});
                    valuesAnimPause.add(new float[]{0.7f, 3f});
                MessagesHandler.messageInGame.y = Game.gameAreaResolutionY*0.15f;
                Animation anim = new Animation(MessagesHandler.messageInGame, "messageInGameColor", "numberForAnimation", 4000, valuesAnimPause, true, false);
                anim.setOnChangeNotFluid(new Animation.OnChange() {
                    @Override
                    public void onChange() {
                        if (MessagesHandler.messageInGame.numberForAnimation == 1f) {
                            MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                        } else if (MessagesHandler.messageInGame.numberForAnimation == 2f) {
                            MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                        } else if (MessagesHandler.messageInGame.numberForAnimation == 3f) {
                            MessagesHandler.messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                        }
                    }
                });
                anim.start();
                MessagesHandler.messageInGame.setText(Game.getContext().getResources().getString(R.string.pause));
                MessagesHandler.messageInGame.increaseAlpha(100, 1f);
                MessagesHandler.messageInGame.y = Game.gameAreaResolutionY * 0.15f;

            }

            MenuHandler.menuPause.unblockAndDisplay();

        } else if (newState == GAME_STATE_VITORIA_1){


            Level.levelObject.levelGoalsObject.setFinish(TimeHandler.stopTimeOfLevelPlay());
            SaveGame.addLevelPlayed();

            Animation anim = Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, 0f, -Game.resolutionX*2f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    String previousText = MessagesHandler.messageTime.text;
                    MessagesHandler.messageTime = new Text("messageTime",
                            Game.resolutionX*0.01f, Game.resolutionY*0.93f, Game.resolutionY*0.04f, Game.getContext().getResources().getString(R.string.tempo_gasto) + " " + previousText, Game.font, new Color(0.1f, 0.1f, 0.1f, 1f));
                    Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, -Game.resolutionX, 0f).start();
                }
            });
            anim.start();

            Game.sound.playWin1();

            MessagesHandler.messageInGame.y = Game.gameAreaResolutionY*0.05f;

            Animation anim2 = Utils.createAnimation4v(MessagesHandler.messageInGame, "messageInGameColor", "numberForAnimation", 3000, 0f, 1f, 0.2f, 2f, 0.5f, 3f, 0.7f, 4f, true, false);
            anim2.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (MessagesHandler.messageInGame.numberForAnimation == 1f){
                        MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 1f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 2f) {
                        MessagesHandler.messageInGame.setColor(new Color(0f, 0f, 0f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 3f) {
                        MessagesHandler.messageInGame.setColor(new Color(1f, 1f, 0f, 1f));
                    } else if (MessagesHandler.messageInGame.numberForAnimation == 4f) {
                        MessagesHandler.messageInGame.setColor(new Color(1f, 0f, 0f, 1f));
                    }
                }
            });
            anim2.start();

            StarsHandler.previousStars = SaveGame.getLevelStars(SaveGame.saveGame.currentLevelNumber);
            StarsHandler.newStars = Level.levelGoalsObject.getStarsAchieved();

            // calcula a pontuação final, de acordo com a quantidade de bolas azuis e estrelas
            int pointsTotal = ScoreHandler.scorePanel.value;
            for (int i = 0; i < Game.ballGoalsPanel.blueBalls; i++) {
                pointsTotal *= 1.5;
            }
            pointsTotal *= 1f + (0.1f * (float) StarsHandler.newStars);

            // salva os dados dos pontos e estrelas

            SaveGame.setLevelPoints(SaveGame.saveGame.currentLevelNumber, pointsTotal);
            SaveGame.setLevelStars(SaveGame.saveGame.currentLevelNumber, StarsHandler.newStars);
            SaveGame.saveGame.save();
            ScoreHandler.setMaxScoreTotal();
            ScoreHandler.submitScores();


            // ATUALIZA AS ESTATÍSTICAS
            Stats.totalPontosInclusiveRepetidosVitoria += pointsTotal;
            Stats.totalEstrelasInclusiveRepetidos += StarsHandler.newStars;
            Stats.numeroTotalLevelsFinalizadosVitoria += 1;
            Stats.numeroTotalAlvosAtingidosLevelsFinalizadosVitoria += Level.numeroAlvosLevel;
            Stats.tempoJogadoVitoria += TimeHandler.timeOfLevelPlay;
            Stats.saveData();
            //^


            // verifica a quantidade de bolas azuis, e atualiza a pontuação
            Timer timer = new Timer();

            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (Game.ballGoalsPanel.blueBalls > 0){
                                int points = (int)((float) ScoreHandler.scorePanel.value * 1.5f);
                                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                                Game.ballGoalsPanel.explodeBlueBall();
                    } else if (!Game.levelGoalsPanel.isVisible){
                        Utils.createSimpleAnimation(Game.ballGoalsPanel, "translateX", "translateX", 2000, 0f, Game.gameAreaResolutionX*2f).start();
                        Game.ballGoalsPanel.clearExplosions();
                        Game.levelGoalsPanel.appearGray(true);
                    } else if (Game.levelGoalsPanel.gray) {
                        Game.levelGoalsPanel.shineLines(true);

                        if (StarsHandler.newStars > 0) {
                            int points = (int) ((float) ScoreHandler.scorePanel.value * (1f + (0.1f * (float) StarsHandler.newStars)));
                            ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                            if (StarsHandler.newStars == 1) Game.messageForScore = "+ 10%";
                            if (StarsHandler.newStars == 2) Game.messageForScore = "+ 20%";
                            if (StarsHandler.newStars == 3) Game.messageForScore = "+ 30%";
                            if (StarsHandler.newStars == 4) Game.messageForScore = "+ 40%";
                            if (StarsHandler.newStars == 5) Game.messageForScore = "+ 50%";
                            Game.settingMessageForScore = true;
                        }
                    } else{
                        ButtonHandler.buttonContinue.display();
                        ButtonHandler.buttonContinue.unblock();
                        MessagesHandler.messageContinue.display();
                        MessagesHandler.messageContinue.setColor(new Color(0f, 0f, 0f, 1f));
                        cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(timerTask, 2250, 2250);

            ArrayList<float[]> valuesAnimVitoriaTranslate = new ArrayList<>();
            valuesAnimVitoriaTranslate.add(new float[]{0f,-Game.gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{0.8f,-Game.gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{1f,0f});
            new Animation(MessagesHandler.messageInGame, "messageInGameTranslateX", "translateX", 2000, valuesAnimVitoriaTranslate, false, true).start();

            MessagesHandler.messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Game.sound.playTextBoxAppear();
                }
            });


            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageInGame.setText(Game.getContext().getResources().getString(R.string.nivelConcluido1) + " " + String.valueOf(SaveGame.saveGame.currentLevelNumber) + " " + Game.getContext().getResources().getString(R.string.nivelConcluido2));
            } else {
                MessagesHandler.messageInGame.setText(Game.getContext().getResources().getString(R.string.nivelConcluido1) + " " + Game.getContext().getResources().getString(R.string.nivelConcluido2));
            }
            MessagesHandler.messageInGame.display();

            Utils.createSimpleAnimation(Game.ballGoalsPanel, "translateX", "translateY", 2000, 0f, -Game.gameAreaResolutionY*0.1f).start();
            Utils.createSimpleAnimation(Game.ballGoalsPanel, "scaleX", "scaleX", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(Game.ballGoalsPanel, "scaleY", "scaleY", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleX", "scaleX", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleY", "scaleY", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "translateX", "translateY", 2000, 0f, -Game.gameAreaResolutionY * 0.05f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                float iY = - Game.gameAreaResolutionY * 0.05f;
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTX", "translateX", 30000, 0f, 0f, 0.3f, -10f, 0.7f, 20f, 1f, 0f, true, true).start();
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTY", "translateY", 12000, 0f,iY, 0.2f,iY + 5f, 0.7f,iY -20f, 1f,iY, true, true).start();
                }
                }
            ).start();
        } else if (newState == GAME_STATE_VITORIA_2) {

            Game.sound.playWin2();

            Game.groupsUnblocked.clear();

            final int starsDiference = StarsHandler.newStars - StarsHandler.previousStars;

            int newStarsTotal = StarsHandler.conqueredStarsTotal + (StarsHandler.newStars - StarsHandler.previousStars);

            //Log.e(TAG, "StarsHandler.newStars "+StarsHandler.newStars );
            //Log.e(TAG, "StarsHandler.previousStars "+StarsHandler.previousStars );
            //Log.e(TAG, "StarsHandler.conqueredStarsTotal "+StarsHandler.conqueredStarsTotal );
            //Log.e(TAG, "starsDiference "+starsDiference );
            //Log.e(TAG, "newStarsTotal "+newStarsTotal );


            if (starsDiference > 0) {
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mnima), starsDiference);
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mdia), starsDiference);
                GoogleAPI.increment(
                        Game.getContext().getResources().getString(R.string.achievement_coleta_mxima), starsDiference);
            }

            if (StarsHandler.newStars > StarsHandler.previousStars){
                float groupsUnblockedSize = Game.resolutionX * 0.16f;
                float groupsUnblockedPadd = Game.resolutionX * 0.02f;

                for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){
                   final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);
                   if (lgd.starsToUnlock >= StarsHandler.conqueredStarsTotal && lgd.starsToUnlock <= newStarsTotal && i != 0){
                           Game.groupsUnblocked.add(
                               new Image("groupsUnblocked"+i, 0f,
                               Game.resolutionY * 0.7f,
                               groupsUnblockedSize, groupsUnblockedSize,
                               lgd.textureUnit,
                               lgd.textureData)
                           );
                   }
                }

                int numberOfGroupsUnblocked = Game.groupsUnblocked.size();
                if (numberOfGroupsUnblocked > 0){

                    MessagesHandler.messageGroupsUnblocked.display();
                    Utils.createSimpleAnimation(MessagesHandler.messageGroupsUnblocked, "translateX", "translateX", 500, -Game.gameAreaResolutionX*1.5f, 0f).start();
                    Game.sound.playTextBoxAppear();


                    float initX = (Game.resolutionX * 0.5f) - (((numberOfGroupsUnblocked * groupsUnblockedSize) + ((numberOfGroupsUnblocked-1)*groupsUnblockedPadd))/2f);

                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        Game.groupsUnblocked.get(i).x = initX + (i * groupsUnblockedPadd) + (i * groupsUnblockedSize);
                    }


                    float halfDifference = ((groupsUnblockedSize * 1.2f) - groupsUnblockedSize)/2f;


                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        Image gu = Game.groupsUnblocked.get(i);
                        gu.display();
                        //Utils.createAnimation5v(gu, "translateX", "translateX", 800, 0f, groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, -halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        //Utils.createAnimation5v(gu, "translateY", "translateY", 800, 0f, -groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleX", "scaleX", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleY", "scaleY", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                    }
                }
            }

            if (Game.groupsUnblocked.size() > 0) {
                Game.currentLevelIcon.y = Game.resolutionY * 0.2f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.62f);
            } else {
                Game.currentLevelIcon.y = Game.resolutionY * 0.3f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.76f);

            }
            Game.currentLevelIcon.display();

            MessageStarWin.messageStarsWin.show(StarsHandler.newStars, StarsHandler.newStars - StarsHandler.previousStars, true);

            for (int i = 0; i < MessageStarWin.messageStarsWin.stars.size(); i++) {
                Utils.createAnimation5v(MessageStarWin.messageStarsWin.stars.get(i), "a"+i, "animTranslateY",
                        3000,
                        0f, 0f,
                        0.2f + (0.03f * i), 0f,
                        0.22f + (0.03f * i), -Game.resolutionY * 0.01f,
                        0.3f + (0.03f * i), 0f,
                        1f, 0f,
                        true, true
                ).start();
            }

            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(800, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(800, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();

            ArrayList<float[]> valuesAnim = new ArrayList<>();
            valuesAnim.add(new float[]{0f,0f});
            valuesAnim.add(new float[]{0.5f,1f});
            valuesAnim.add(new float[]{0.57f,2f});
            valuesAnim.add(new float[]{0.64f,3f});
            valuesAnim.add(new float[]{0.71f,4f});
            valuesAnim.add(new float[]{0.78f,5f});
            valuesAnim.add(new float[]{1f,6f});


            //Log.e(TAG, "StarsHandler.newStars "+StarsHandler.newStars );
            //Log.e(TAG, "StarsHandler.previousStars "+StarsHandler.previousStars );
            //Log.e(TAG, "StarsHandler.conqueredStarsTotal "+StarsHandler.conqueredStarsTotal );
            //Log.e(TAG, "starsDiference "+starsDiference );
            //Log.e(TAG, "newStarsTotal "+newStarsTotal );

            Animation animMessageConqueredStarsTotal = new Animation(MessagesHandler.messageConqueredStarsTotal, "numberForAnimation", "numberForAnimation", 3000, valuesAnim, false, false);
            animMessageConqueredStarsTotal.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 1f){
                        if (starsDiference > 0){
                            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 1));
                            Game.sound.playStarsUp();

                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 2f) {
                        if (starsDiference > 1){
                            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 2));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 3f) {
                        if (starsDiference > 2){
                            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 3));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 4f) {
                        if (starsDiference > 3){
                            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 4));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 5f) {
                        if (starsDiference > 4){
                            MessagesHandler.messageConqueredStarsTotal.setText(Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 5));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    }
                }
            });

            animMessageConqueredStarsTotal.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    ButtonHandler.buttonContinue.unblockAndDisplay();
                    MessagesHandler.messageContinue.display();
                    MessagesHandler.messageContinue.setColor(new Color(0f, 0f, 0f, 1f));
                    StarsHandler.updateConqueredStars();
                }
            });
            animMessageConqueredStarsTotal.start();

        } else if (newState == GAME_STATE_TUTORIAL) {

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");

            if (!sameState) {
                Game.showBlackFrameTransition(500);
            }

            Tutorial.loadTutorial();
            Tutorial.currentTutorialObject.showFirst();

        }
    }
}

