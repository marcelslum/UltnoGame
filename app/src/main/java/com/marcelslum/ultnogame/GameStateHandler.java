

    public static int gameState;
    public final static int GAME_STATE_PREPARAR_TREINAMENTO = 2;
    public final static int GAME_STATE_MENU_DURANTE_TREINAMENTO = 3;
    public final static int GAME_STATE_MENU_EXPLICACAO_TREINAMENTO = 4;
    public final static int GAME_STATE_MENU_JOGAR = 5;
    public final static int GAME_STATE_OPCOES_JOGABILIDADE =  6;
    public final static int GAME_STATE_MENU_SAVE_FIRST_TIME =  8;
    public final static int GAME_STATE_MENU_CARREGAR_JOGO = 7;
    public final static int GAME_STATE_PRE_JOGAR = 9;
    public final static int GAME_STATE_JOGAR = 10;
    public final static int GAME_STATE_PREPARAR = 11;
    public final static int GAME_STATE_MENU_PRINCIPAL =  12;
    public final static int GAME_STATE_VITORIA =  13;
    public final static int GAME_STATE_DERROTA =  14;
    public final static int GAME_STATE_TUTORIAL =  15;
    public final static int GAME_STATE_PAUSE =  16;
    public final static int GAME_STATE_OPCOES =  17;
    public final static int GAME_STATE_INTRO =  18;
    public final static int GAME_STATE_OPCOES_GAME =  19;
    public final static int GAME_STATE_SELECAO_GRUPO =  20;
    public final static int GAME_STATE_SELECAO_LEVEL =  21;
    public final static int GAME_STATE_OBJETIVO_LEVEL =  22;
    public final static int GAME_STATE_MENU_TUTORIAL =  24;
    public final static int GAME_STATE_INTERSTITIAL =  25;
    public final static int GAME_STATE_OBJETIVO_PAUSE =  26;
    public final static int GAME_STATE_VITORIA_COMPLEMENTACAO =  27;
    public final static int GAME_STATE_SOBRE = 28;
    public final static int GAME_STATE_MENU_FINAL_TREINAMENTO = 29;
    public final static int GAME_STATE_ESTATISTICAS = 30;
    public final static int GAME_STATE_NOVA_TENTATIVA_TREINAMENTO = 31;
    
    public static int gameState;
    public static int previousState;
    public static boolean sameState;


public class GameStateHandler(){




public static void makeTransitionBetweenStates(int previousState, int newState){


        //toma as ações independentemente de qual seja o estado anterior
        MessagesHandler.setBottomMessage("", 0);
           
        if (previousState == GAME_STATE_MENU){
            
                // PODE IR PARA OS SEGUINTES STATES:
                //  - GAME_STATE_OPCOES
                //  - GAME_STATE_MENU_SAVE_FIRST_TIME
                //  - GAME_STATE_MENU_JOGAR

                MenuHandler.menuMain.blocAndClearDisplay();
                MessagesHandler.messageMaxScoreTotal.clearDisplay();
                tittle.clearDisplay();
                GoogleAPI.displayGoogleInfo();
            
        } else if (previousState == GAME_STATE_SOBRE){
        
                // PODE IR PARA OS SEGUINTES STATES:
                //  - GAME_STATE_OPCOES

                MessagesHandler.messageMenu.clearDisplay;
                aboutTextView.blockAndClearDisplay();
        
        } else if (previousState = GAME_STATE_ESTATISTICAS){
        
                // PODE IR PARA OS SEGUINTES STATES:
                //  - GAME_STATE_MENU_JOGAR

                Game.statsGraphs.clear();
                MessagesHandler.messageStatTittle.clearDisplay();
                MessagesHandler.messageStatDescricao.clearDisplay();
                ButtonHandler.buttonContinue.blockAndClearDisplay();

        } else if (previousState == GAME_STATE_OBJETIVO_LEVEL){
      
                // PODE IR PARA OS SEGUINTES STATES:
                //    - GAME_STATE_SELECAO_LEVEL
                //    - GAME_STATE_PREPARAR
                
                if (tipTextBox != null) tipTextBox.clearDisplay();
                ButtonHandler.buttonContinue.blockAndClearDisplay();
                
                if (newState == GAME_STATE_PREPARAR){
                        mainActivity.hideAdView();
                        ButtonHandler.buttonReturn.blockAndClearDisplay();
                }
        } else if (previousState == GAME_STATE_OBJETIVO_PAUSE){
      
                // PODE IR PARA OS SEGUINTES STATES:
                //    - GAME_STATE_PAUSE
                
                ButtonHandler.buttonReturnObjectivesPause.blockAndClearDisplay();
                
        } else if (previousState == GAME_STATE_SELECAO_GRUPO){
                // PODE IR PARA OS SEGUINTES STATES:
                //   - GAME_STATE_SELECAO_LEVEL
                //   - GAME_STATE_MENU_JOGAR
                
                MenuHandler.groupMenu.blockAndDesappear();
                
                if (newState == GAME_STATE_MENU_JOGAR){
                        
                        MenuHandler.menuTutorialUnvisited.blockAndDessapear(500);
                        MessagesHandler.messageMenu.clearDisplay();
                        
                        MessagesHandler.starForMessage.clearDisplay();
                        MessagesHandler.messageConqueredStarsTotal.clearDisplay();
                        
                }
                
        } else if (previousState == GAME_STATE_MENU_JOGAR) {
        
                // PODE IR PARA OS SEGUINTES STATES:
                //   - GAME_STATE_MENU
                //   - GAME_STATE_SELECAO_GRUPO
                //   - GAME_STATE_MENU_EXPLICACAO_TREINAMENTO
                //   - GAME_STATE_ESTATISTICAS
                //   - GAME_STATE_MENU_TUTORIAIS
                
                
                MenuHandler.menuPlay.blockAndClearDisplay();
                
                if (newState == GAME_STATE_ESTATISTICAS){
                        MessagesHandler.messageBack.clearDisplay();
                }
                
                if (newState == GAME_STATE_SELECAO_GRUPO 
                        || newState == GAME_STATE_MENU_EXPLICACAO_TREINAMENTO
                        || newState == GAME_STATE_ESTATISTICAS
                        || newState == GAME_STATE_MENU_TUTORIAIS)
                {
                        tittle.clearDisplay(); 
                }
        
        } else if (previousState == GAME_STATE_MENU_EXPLICACAO_TREINAMENTO){
                // PODE IR PARA OS SEGUINTES STATES:
                //   - GAME_STATE_MENU_JOGAR
                //   - GAME_STATE_PREPARAR_TREINAMENTO
                
                
        
        }


         


            



         
  
  
  }





}



public static void setGameState(int state){
        
        // define as variáveis do estado
        sameState = false;
        previousState = gameState;
        if (state == gameState) sameState = true;
        gameState = state;
        
        // bloqueia a interação até que ela seja zerada
        Game.blockAndWaitTouchRelease();
        
        clearAllMenuEntities();
        
        
        if (MessageStar.messageStars != null) {
            MessageStar.messageStars.clearDisplay();
        }

        if (MessageStarWin.messageStarsWin != null) {
            MessageStarWin.messageStarsWin.clearDisplay();
        }
        
        if (currentLevelIcon != null)currentLevelIcon.clearDisplay();
        if (groupsUnblocked != null) groupsUnblocked.clear();

        TimeHandler.timeOfLevelPlayBlocked = true;

        if (GoogleAPI.playerIconImage != null){
            GoogleAPI.playerIconImage.clearDisplay();
        }

        if (state == GAME_STATE_INTERSTITIAL){
            eraseAllGameEntities();
            mainActivity.showInterstitial();
            
        } else if (state == GAME_STATE_SOBRE) {

            mainActivity.showAdView();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuAbout));
            aboutTextView.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();
        }
        else if (state == GAME_STATE_ESTATISTICAS){

            Log.e(TAG, "setGameState estatisticas");

                mainActivity.showAdView();
                //MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuAbout));

            //MessagesHandler.initStatsTextBox();
            //MessagesHandler.statsTextView.unblockAndDisplay();

            Stats.currentStatsSheet = Stats.TEMPO_JOGO;

            Stats.showCurrentStat();

            ButtonHandler.buttonReturn.unblockAndDisplay();
            ButtonHandler.buttonContinue.unblockAndDisplay();

        } else if (state == GAME_STATE_OBJETIVO_LEVEL){


            if (!sameState) {
                showBlackFrameTransition(500);
            }

            Sound.stopAndReleaseMusic();

            mainActivity.showAdView();
            Level.levelGoalsObject = new LevelGoals();
            Level.levelGoalsObject.levelGoals = LevelGoalsLoader.getLevelGoals(SaveGame.saveGame.currentLevelNumber);

            showTip();
            
            levelGoalsPanel = new LevelGoalsPanel("levelGoalsPanel", resolutionX * 0.2f, resolutionY * 0.2f, resolutionX * 0.025f, resolutionX * 0.79f);

            for (int i = 0; i < Level.levelGoalsObject.levelGoals.size(); i++){
                LevelGoal lg = Level.levelGoalsObject.levelGoals.get(i);
                levelGoalsPanel.addLine(lg.numberOfStars, true, lg.text);
            }
            levelGoalsPanel.appearGray(false);
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();

            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel) + " " + SaveGame.saveGame.currentLevelNumber);
            } else {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevelSecret) + " " + (SaveGame.saveGame.currentLevelNumber - 100));
            }

            ButtonHandler.buttonContinue.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();

            MessagesHandler.messageContinue.display();
            MessagesHandler.messageBack.display();

            MessagesHandler.messageContinue.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));
            MessagesHandler.messageBack.setColor(new Color(0.5f, 0.5f, 0.5f, 1f));

        } else if (state == GAME_STATE_OBJETIVO_PAUSE){

            mainActivity.showAdView();

            levelGoalsPanel.appearGrayAndShine(true);
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuObjetivo));
            MessagesHandler.messageSubMenu.display();
            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+ " " + SaveGame.saveGame.currentLevelNumber);
            } else {
                MessagesHandler.messageSubMenu.setText(Game.getContext().getResources().getString(R.string.messageCurrentLevelSecret) + " " + (SaveGame.saveGame.currentLevelNumber - 100));
            }
            ButtonHandler.buttonReturnObjectivesPause.unblockAndDisplay();
            MessagesHandler.messageBack.setY(Game.gameAreaResolutionY * 0.895f);
            MessagesHandler.messageBack.setColor(new Color(0f, 0f, 0f, 1f));
            MessagesHandler.messageBack.display();

        } else if (state == GAME_STATE_SELECAO_GRUPO) {

            eraseAllGameEntities();

            if (!sameState) {
                    showBlackFrameTransition(500);
            }
            
            Sound.stopAndReleaseMusic();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");

            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(500);
            }

            Game.sound.playMenuIconDrop();
            //Sound.playSoundPool(Sound.soundMenuIconDrop2, 0.15f, 0.15f, 0);

            mainActivity.showAdView();

            Game.bordaB.setY(Game.resolutionY);
            MenuHandler.updateGroupMenu();
            MenuHandler.groupMenu.appear();
            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoMundo));
            ButtonHandler.buttonReturn.unblockAndDisplay();


            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();


            
        } else if (state == GAME_STATE_MENU_JOGAR){

            if (!sameState) {
                showBlackFrameTransition(500);
            }

            GoogleAPI.displayGoogleInfo();

            mainActivity.showAdView();

            tittle.display();

            MessagesHandler.messageBack.display();

            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();

            MenuHandler.menuOptions.blockAndClearDisplay();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.groupMenu.blockAndClearDisplay();
            MenuHandler.levelMenu.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();

            MessagesHandler.messageMenuSaveNotSeen.clearDisplay();
            MessagesHandler.messageMenuCarregarJogo.clearDisplay();
            if (MessagesHandler.messageExplicacaoTreinamento != null) MessagesHandler.messageExplicacaoTreinamento.clearDisplay();
            if (MessagesHandler.messageTrainingState != null) MessagesHandler.messageTrainingState.clearDisplay();
            if (MessagesHandler.messageTrainingState2 != null) MessagesHandler.messageTrainingState2.clearDisplay();

            MenuHandler.menuPlay.unblockAndDisplay();

            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_MENU_EXPLICACAO_TREINAMENTO){
            if (!sameState) {
                showBlackFrameTransition(500);
            }

            MessagesHandler.messageExplicacaoTreinamento.display();
            MessagesHandler.messageBack.display();

            MenuHandler.menuExplicacaoAntesDoTreinamento.unblockAndDisplay();
            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_MENU_DURANTE_TREINAMENTO){

            if (!sameState) {
                showBlackFrameTransition(1000);
            }

            Training.tentativaCertaTreinamento = 0;
            Training.treinamentoSucesso = false;

            Training.resetTrainingEntities();

            Training.setMenuDuranteTreinamentoMessage();
            MessagesHandler.messageExplicacaoDuranteTreinamento.display();

            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
            }
            if (MessagesHandler.messageTrainingState2 != null) {
                MessagesHandler.messageTrainingState2.clearDisplay();
                //MessagesHandler.messageTrainingState2.setText(getContext().getResources().getString(R.string.tentativa) + " " + (Game.tentativaCertaTreinamento + 1) + " " +getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
                //MessagesHandler.messageTrainingState2.display();
            }

            MenuHandler.menuDuranteTreinamento.unblockAndDisplay();
            MenuHandler.menuFimTreinamento.blockAndClearDisplay();

        } else if (state == GAME_STATE_MENU_FINAL_TREINAMENTO){

            if (!sameState) {
                showBlackFrameTransition(1000);
            }

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

            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
            }
            if (MessagesHandler.messageTrainingState2 != null) {
                MessagesHandler.messageTrainingState2.clearDisplay();
            }

            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
            MenuHandler.menuFimTreinamento.unblockAndDisplay();

        } else if (state == GAME_STATE_NOVA_TENTATIVA_TREINAMENTO){

            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
            }

            Training.resetTrainingEntities();

        } else if (state == GAME_STATE_MENU_TUTORIAL){

            if (!sameState) {
                showBlackFrameTransition(500);
            }

            Game.sound.playMenuIconDrop();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");

            mainActivity.showAdView();

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuTutorial));
            MenuHandler.updateTutorialMenu();
            MenuHandler.tutorialMenu.appear();
            ButtonHandler.buttonReturn.unblockAndDisplay();

        } else if (state == GAME_STATE_SELECAO_LEVEL) {

            if (Game.apagarEstatisticasNoMenu){
                for (int i = 0; i < SaveGame.saveGame.stats.length; i++) {
                    SaveGame.saveGame.stats[i] = 0;
                }
            }

            if (!sameState) {
                showBlackFrameTransition(500);
            }

            mainActivity.showAdView();

            Sound.stopAndReleaseMusic();

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/icons");
            
            if (tipTextBox != null){
                tipTextBox.clearDisplay();
            }

            Game.sound.playMenuIconDrop();
            //Sound.playSoundPool(Sound.soundMenuIconDrop2, 0.15f, 0.15f, 0);

            MenuHandler.groupMenu.clearDisplay();
            MenuHandler.groupMenu.block();
            
            MenuHandler.updateLevelMenu();
            MenuHandler.levelMenu.appear();

            if (Tutorial.hasUnvisitedTutorial()){
                MenuHandler.menuTutorialUnvisited.appearAndUnblock(500);
            }

            MessagesHandler.messageMenu.display();
            MessagesHandler.messageMenu.setText(getContext().getResources().getString(R.string.messageMenuSelecaoLevel));
            MessagesHandler.messageSubMenu.display();
            MessagesHandler.messageSubMenu.setText(currentLevelsGroupDataSelected.name);
            ButtonHandler.buttonReturn.unblockAndDisplay();

            ButtonHandler.buttonGroupLeaderboard.unblockAndDisplay();


            MessagesHandler.starForMessage.alpha = 0f;
            MessagesHandler.messageConqueredStarsTotal.alpha = 0f;
            MessagesHandler.starForMessage.increaseAlpha(1500, 1f);
            MessagesHandler.messageConqueredStarsTotal.increaseAlpha(1500, 1f);
            MessagesHandler.starForMessage.display();
            MessagesHandler.messageConqueredStarsTotal.display();

        } else if (state == GAME_STATE_INTRO) {

            mainActivity.hideAdView();
            Log.e(TAG, "init1");



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
        } else if (state == GAME_STATE_OPCOES){
            MessagesHandler.messageBack.display();
            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;
            //Game.sound.playMusic();

            if (previousState == GAME_STATE_SOBRE){
                Game.aboutTextView.blockAndClearDisplay();
            }

            mainActivity.showAdView();
            tittle.display();
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MenuHandler.menuOptions.appearAndUnblock(500);

            if (mainActivity.isSignedIn()) {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(getContext().getResources().getString(R.string.deslogarGoogle));
            } else {
                MenuHandler.menuOptions.getMenuOptionByName("google").setText(getContext().getResources().getString(R.string.logarGoogle));
            }

        } else if (state == GAME_STATE_OPCOES_JOGABILIDADE){
            MessagesHandler.messageBack.display();
            MenuHandler.menuOptions.block();
            MenuHandler.menuOptions.clearDisplay();

            SelectorHandler.repositionSelectors(state);
            mainActivity.showAdView();
            tittle.display();
            ButtonHandler.buttonReturn.unblockAndDisplay();
            MenuHandler.menuOptionsPlay.appearAndUnblock(500);


        } else if (state == GAME_STATE_OPCOES_GAME){

            SelectorHandler.repositionSelectors(state);
            mainActivity.showAdView();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.menuInGameOptions.appearAndUnblock(500);
            MessagesHandler.messageInGame.y = gameAreaResolutionY*0.15f;
            MessagesHandler.messageInGame.display();

        } else if (state == GAME_STATE_MENU_SAVE_FIRST_TIME){

            MenuHandler.menuMain.blockAndClearDisplay();
            MenuHandler.menuFirstSaveGame.appearAndUnblock(500);

            MessagesHandler.messageMenuSaveNotSeen.display();

        } else if (state == GAME_STATE_MENU_CARREGAR_JOGO){

            MenuHandler.menuMain.blockAndClearDisplay();
            MenuHandler.menuCarregar.appearAndUnblock(500);

            MessagesHandler.messageMenuCarregarJogo.display();

        } else if (state == GAME_STATE_MENU_PRINCIPAL){

            //mainActivity.getScreenShot();

            /*
            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.05f, Game.gameAreaResolutionY * 0.2f, Game.resolutionX * 0.95f, Game.gameAreaResolutionY * 0.75f);
            statsGraph.addData("valor 1", 1f);
            statsGraph.addData("valor 2", 2f);
            statsGraph.addData("valor 3", 3f);
            statsGraph.addData("valor 4", 4f);
            statsGraph.addData("valor 5", 20f);
            statsGraph.make(true, false);
            statsGraphs.add(statsGraph);
            */

            MessagesHandler.setBottomMessage("", 0);

            if (showMessageNotConnectedOnGoogle){
                showMessageNotConnectedOnGoogle = false;
                String message = Game.mainActivity.getApplicationContext().getResources().getString(R.string.nao_conectado_google);
                MessagesHandler.setBottomMessage(message, 2000);
            }

            Game.sound.stopAndReleaseMusic();

            if (Game.versaoBeta) {
                MessagesHandler.messageBeta.display();
            }

            //messages.showMessage("teste");

            GoogleAPI.loadAchievements();

            SaveGame.saveGame.save();

            StarsHandler.updateConqueredStars();

            if (!sameState) {
                if (previousState != GAME_STATE_OPCOES) {
                    showBlackFrameTransition(500);
                }
            }

            //if (Sound.loop != null) Sound.loop.stopAndRelease();

            SelectorHandler.repositionSelectors(state);
            initTittle();
            mainActivity.showAdView();
            Game.bordaB.setY(Game.resolutionY);

            MenuHandler.menuOptions.blockAndClearDisplay();
            MenuHandler.menuInGame.blockAndClearDisplay();
            MenuHandler.groupMenu.blockAndClearDisplay();
            MenuHandler.levelMenu.blockAndClearDisplay();
            MenuHandler.menuDuranteTreinamento.blockAndClearDisplay();
            MenuHandler.menuPlay.blockAndClearDisplay();
            MenuHandler.menuExplicacaoAntesDoTreinamento.blockAndClearDisplay();

            MessagesHandler.messageMenuSaveNotSeen.clearDisplay();
            MessagesHandler.messageMenuCarregarJogo.clearDisplay();
            if (MessagesHandler.messageTrainingState != null) MessagesHandler.messageTrainingState.clearDisplay();
            if (MessagesHandler.messageTrainingState2 != null) MessagesHandler.messageTrainingState2.clearDisplay();

            eraseAllGameEntities();
            eraseAllHudEntities();
            MenuHandler.menuMain.unblockAndDisplay();
            tittle.display();
            MessagesHandler.messageMaxScoreTotal.display();

            GoogleAPI.displayGoogleInfo();

            MessagesHandler.bottomTextBox.display();

            MessagesHandler.messageMaxScoreTotal.setText(
                    getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()));

        } else if (state == GAME_STATE_PREPARAR) {

            abdicateAngle = false;
            Sound.musicCurrentPart = Sound.MUSIC_PRE_INTRO;
            Sound.musicCurrentGlobalPart = Sound.MUSIC_GLOBAL_PART_A;
            Sound.musicCurrentSubPart = Sound.MUSIC_SUB_PART_A_A1;
            Sound.musicVolume = 1f;
            Sound.loadStaticGameAudioTracks();

            Stats.clearData();

            //Sound.loadMusic();

            if (tipTextBox != null) {
                tipTextBox.clearDisplay();
            }

            ParticleGenerator.loadParticleGenerators();

            eraseAllGameEntities();
            eraseAllHudEntities();

            if (Training.training) {
                LevelLoader.loadLevel(1);
            } else {
                LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
            }
            mainActivity.hideAdView();
            MessagesHandler.messageTime.cleanAnimations();

            if (!Training.training){
                MessagesHandler.messageTime.display();
                MessagesHandler.setMessageTime();
            } else {
                MessagesHandler.messageCurrentLevel.setText(getContext().getResources().getString(R.string.mensagem_treinamento));
            }

            MessagesHandler.messageCurrentLevel.display();

            if (Training.training) {
                Level.levelGoalsObject = new LevelGoals();
            }

            Level.levelGoalsObject.clearAchievements();


            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();
            TimeHandler.timeOfLevelPlay = 0;
            TimeHandler.secondsOfLevelPlay = 0;
            TimeHandler.lastSeconds = 0;
            mainActivity.hideAdView();
            if (!sameState) {
                showBlackFrameTransition(2500);
            }
            Level.levelObject.loadEntities();

            // cria a animação de preparação;
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
            //Sound.playCounter();
            //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
            //Sound.playCounter();

            Animation anim = new Animation(MessagesHandler.messagePreparation, "messagePreparation", "numberForAnimation", 7000, values, false, false);
            anim.setOnChangeNotFluid(new Animation.OnChange() {
                @Override
                public void onChange() {
                    if (innerMessagePreparation.numberForAnimation == 4f) {
                        //Sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        MessagesHandler.messagePreparation.setColor(Color.transparente);
                        //innerMessagePreparation.setText("4");
                    } else if (innerMessagePreparation.numberForAnimation == 3f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        MessagesHandler.messagePreparation.display();
                        MessagesHandler.messagePreparation.setColor(Color.vermelhoCheio);
                        innerMessagePreparation.setText("3");
                    } else if (innerMessagePreparation.numberForAnimation == 2f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        innerMessagePreparation.setText("2");
                    } else if (innerMessagePreparation.numberForAnimation == 1f) {
                        Game.sound.playCounter();
                        //Sound.playSoundPool(Sound.soundCounter, 1, 1, 0);
                        //Sound.playCounter();
                        innerMessagePreparation.setText("1");
                    } else if (innerMessagePreparation.numberForAnimation == 0f) {

                        if (Training.training) {
                            innerMessagePreparation.setText(getContext().getResources().getString(R.string.mensagem_treinar));
                        } else {
                            innerMessagePreparation.setText(getContext().getResources().getString(R.string.mensagem_jogar));
                        }

                        if (paraGravacaoVideo) {
                            MessagesHandler.messagePreparation.setColor(Color.transparente);
                        }


                        Animation anim = Utils.createSimpleAnimation(innerMessagePreparation, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
                            @Override
                            public void onAnimationEnd() {
                                innerMessagePreparation.clearDisplay();
                                innerMessagePreparation.alpha = 1f;
                                Game.setGameState(GAME_STATE_PRE_JOGAR);
                            }
                        });
                        anim.start();
                    }
                }
            });
            anim.start();
            checkIfDead();

        } else if (state == GAME_STATE_PREPARAR_TREINAMENTO) {

            mainActivity.hideAdView();
            if (!sameState) {
                showBlackFrameTransition(1500);
            }

            abdicateAngle = false;
            Sound.loadStaticGameAudioTracks();

            ParticleGenerator.loadParticleGenerators();

            eraseAllGameEntities();
            eraseAllHudEntities();

            LevelLoader.loadLevel(1);

            mainActivity.hideAdView();

            MessagesHandler.messageCurrentLevel.setText(getContext().getResources().getString(R.string.mensagem_treinamento));

            MessagesHandler.messageCurrentLevel.display();

            Level.levelGoalsObject = new LevelGoals();

            ButtonHandler.buttonContinue.blockAndClearDisplay();
            ButtonHandler.buttonReturn.blockAndClearDisplay();

            Level.levelObject.loadEntities();

            checkIfDead();

            stopAllGameEntities();

            reduceAllGameEntitiesAlpha(100);

            setGameState(GAME_STATE_MENU_DURANTE_TREINAMENTO);

        } else if (state == GAME_STATE_PRE_JOGAR){

            Game.sound.playMusic();
            updateNumberOfTargetsAlive();
            Game.notConnectedTextView.clearDisplay();
            Game.topFrame.clearDisplay();

            timeOfPrePlay = Utils.getTimeMilliPrecision();




        } else if (state == GAME_STATE_JOGAR){

            Game.bordaB.setY(Game.gameAreaResolutionY);

            for (int i = 0; i < Game.balls.size(); i++) {
                if (Game.balls.get(i).listenForExplosion){
                    Game.balls.get(i).replayAlarm();
                }
            }

            if (initPausedFlag){
                initPausedFlag = false;
                setGameState(GAME_STATE_PAUSE);
            } else {
                mainActivity.hideAdView();
                TimeHandler.resumeTimeOfLevelPlay();

                //if (SaveGame.saveGame.music) {
                    //if (Sound.loop != null) {
                    //    Sound.loop.play();
                    //}
                //}

                MessageStar.messageStars.reset();

                for (int i = 0; i < bars.size(); i++) {
                    if (bars.get(i).scaleVariationData != null) {
                        bars.get(i).initScaleVariation();
                    }
                }
                for (int i = 0; i < obstacles.size(); i++) {
                    if (obstacles.get(i).scaleVariationData != null) {
                        obstacles.get(i).initScaleVariation();
                    }
                    if (obstacles.get(i).positionVariationData != null) {
                        obstacles.get(i).initPositionVariation();
                    }
                }
                resetTimeForPointsDecay();
                freeAllGameEntities();
            }

        } else if (state == GAME_STATE_DERROTA){

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            Sound.stopAndReleaseMusic();

            TimeHandler.stopTimeOfLevelPlay();



            Sound.soundPool.autoPause();

            mainActivity.showAdView();
            Game.sound.playGameOver();
            SaveGame.addLevelPlayed();
            int totalStars = 0;
            for (int i = 0; i < Level.levelObject.levelGoalsObject.levelGoals.size(); i++){
                if (Level.levelObject.levelGoalsObject.levelGoals.get(i).achieved){
                    totalStars += Level.levelObject.levelGoalsObject.levelGoals.get(i).numberOfStars;
                }
            }
            MessageStar.messageStars.showAndGoAllGray(totalStars);
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);
            MenuHandler.menuGameOver.appearAndUnblock(1000);
            MessagesHandler.messageGameOver.display();


            // ATUALIZA AS ESTATÍSTICAS
            Stats.totalPontosInclusiveRepetidosDerrota += (long)((float)ScoreHandler.scorePanel.value / 2f);
            Stats.numeroTotalLevelsFinalizadosDerrota += 1;
            int contador = 0;
            for (int i = 0; i < targets.size(); i++) {
                if (!targets.get(i).alive){
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
            
        } else if (state == GAME_STATE_PAUSE){

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            Sound.soundPool.autoPause();

            Sound.pauseMusic();

            mainActivity.showAdView();
            ButtonHandler.buttonReturnObjectivesPause.block();
            ButtonHandler.buttonReturnObjectivesPause.clearDisplay();
            TimeHandler.stopTimeOfLevelPlay();

            //Log.e("game", "ativando game_state_pause");
            if (previousState != GAME_STATE_OPCOES_GAME) {
                //if (Sound.loop != null) {
                //    Sound.loop.pause();
                //}

                //Sound.playPlayMenuBig();
                stopAllGameEntities();
                reduceAllGameEntitiesAlpha(300);
                MenuHandler.menuInGame.getMenuOptionByName("Continuar").textObject.setText(getContext().getResources().getString(R.string.continuarJogar));
                ArrayList<float[]> valuesAnimPause = new ArrayList<>();
                    valuesAnimPause.add(new float[]{0f, 1f});
                    valuesAnimPause.add(new float[]{0.25f, 2f});
                    valuesAnimPause.add(new float[]{0.7f, 3f});
                MessagesHandler.messageInGame.y = gameAreaResolutionY*0.15f;
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
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.pause));
                MessagesHandler.messageInGame.increaseAlpha(100, 1f);
                MessagesHandler.messageInGame.y = gameAreaResolutionY * 0.15f;

            }
            MessagesHandler.messageInGame.display();
            MenuHandler.menuInGame.appearAndUnblock(500);

        } else if (state == GAME_STATE_VITORIA){

            Game.bordaB.setY(Game.resolutionY);

            ButtonHandler.buttonFinalTargetLeft.blockAndClearDisplay();
            ButtonHandler.buttonFinalTargetRight.blockAndClearDisplay();

            //Sound.stopAndReleaseMusic();

            Sound.soundPool.autoPause();


            Level.levelObject.levelGoalsObject.setFinish(TimeHandler.stopTimeOfLevelPlay());
            SaveGame.addLevelPlayed();

            Animation anim = Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, 0f, -resolutionX*2f);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    String previousText = MessagesHandler.messageTime.text;
                    MessagesHandler.messageTime = new Text("messageTime",
                            Game.resolutionX*0.01f, Game.resolutionY*0.93f, Game.resolutionY*0.04f,getContext().getResources().getString(R.string.tempo_gasto) + " " + previousText, Game.font, new Color(0.1f, 0.1f, 0.1f, 1f));
                    Utils.createSimpleAnimation(MessagesHandler.messageTime, "translateX", "translateX", 800, -resolutionX, 0f).start();
                }
            });
            anim.start();

            MessagesHandler.messageCurrentLevel.reduceAlphaAndClearDisplay(500);

            // TODO o que fazer com a animação quando for pausado

            Game.sound.playWin1();
            stopAllGameEntities();
            reduceAllGameEntitiesAlpha(300);

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

            Utils.createSimpleAnimation(ballDataPanel, "alphaVitoria", "alpha", 1000, ballDataPanel.alpha, 0f).start();

            MessagesHandler.messageInGame.y = gameAreaResolutionY*0.05f;

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
            for (int i = 0; i < ballGoalsPanel.blueBalls; i++) {
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
                    if (ballGoalsPanel.blueBalls > 0){
                                int points = (int)((float) ScoreHandler.scorePanel.value * 1.5f);
                                ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                                ballGoalsPanel.explodeBlueBall();
                    } else if (!levelGoalsPanel.isVisible){
                        Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateX", 2000, 0f, gameAreaResolutionX*2f).start();
                        ballGoalsPanel.clearExplosions();
                        levelGoalsPanel.appearGray(true);
                    } else if (levelGoalsPanel.gray) {
                        levelGoalsPanel.shineLines(true);

                        if (StarsHandler.newStars > 0) {
                            int points = (int) ((float) ScoreHandler.scorePanel.value * (1f + (0.1f * (float) StarsHandler.newStars)));
                            ScoreHandler.scorePanel.setValue(points, true, 1000, true);
                            if (StarsHandler.newStars == 1) messageForScore = "+ 10%";
                            if (StarsHandler.newStars == 2) messageForScore = "+ 20%";
                            if (StarsHandler.newStars == 3) messageForScore = "+ 30%";
                            if (StarsHandler.newStars == 4) messageForScore = "+ 40%";
                            if (StarsHandler.newStars == 5) messageForScore = "+ 50%";
                            settingMessageForScore = true;
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
            valuesAnimVitoriaTranslate.add(new float[]{0f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{0.8f,-gameAreaResolutionY*3});
            valuesAnimVitoriaTranslate.add(new float[]{1f,0f});
            new Animation(MessagesHandler.messageInGame, "messageInGameTranslateX", "translateX", 2000, valuesAnimVitoriaTranslate, false, true).start();

            MessagesHandler.messageInGame.increaseAlpha(1600, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Game.sound.playTextBoxAppear();
                }
            });


            if (SaveGame.saveGame.currentLevelNumber < 101) {
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1) + " " + String.valueOf(SaveGame.saveGame.currentLevelNumber) + " " + getContext().getResources().getString(R.string.nivelConcluido2));
            } else {
                MessagesHandler.messageInGame.setText(getContext().getResources().getString(R.string.nivelConcluido1) + " " + getContext().getResources().getString(R.string.nivelConcluido2));
            }
            MessagesHandler.messageInGame.display();

            Utils.createSimpleAnimation(ballGoalsPanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY*0.1f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleX", "scaleX", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ballGoalsPanel, "scaleY", "scaleY", 2000, 1f, 1.8f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleX", "scaleX", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "scaleY", "scaleY", 2000, 1f, 1.5f).start();
            Utils.createSimpleAnimation(ScoreHandler.scorePanel, "translateX", "translateY", 2000, 0f, -gameAreaResolutionY * 0.05f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                float iY = - Game.gameAreaResolutionY * 0.05f;
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTX", "translateX", 30000, 0f, 0f, 0.3f, -10f, 0.7f, 20f, 1f, 0f, true, true).start();
                Utils.createAnimation4v(ScoreHandler.scorePanel, "animScoreTY", "translateY", 12000, 0f,iY, 0.2f,iY + 5f, 0.7f,iY -20f, 1f,iY, true, true).start();
                }
                }
            ).start();
        } else if (state == GAME_STATE_VITORIA_COMPLEMENTACAO) {

            clearAllGameEntities();

            Game.sound.playWin2();

            ButtonHandler.buttonContinue.clearDisplay();
            ButtonHandler.buttonContinue.block();

            ballGoalsPanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);
            ScoreHandler.scorePanel.reduceAlphaAndClearDisplay(500);

            MessagesHandler.messageTime.reduceAlphaAndClearDisplay(500);
            
            if (ButtonHandler.button1Left != null) ButtonHandler.button1Left.clearDisplay();
            if (ButtonHandler.button2Left != null) ButtonHandler.button2Left.clearDisplay();
            if (ButtonHandler.button1Right != null) ButtonHandler.button1Right.clearDisplay();
            if (ButtonHandler.button2Right != null) ButtonHandler.button2Right.clearDisplay();
           
            MessagesHandler.messageInGame.display();

            groupsUnblocked.clear();

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
                float groupsUnblockedSize = resolutionX * 0.16f;
                float groupsUnblockedPadd = resolutionX * 0.02f;

                for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){
                   final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);
                   if (lgd.starsToUnlock >= StarsHandler.conqueredStarsTotal && lgd.starsToUnlock <= newStarsTotal && i != 0){
                           groupsUnblocked.add(
                               new Image("groupsUnblocked"+i, 0f,
                               resolutionY * 0.7f,
                               groupsUnblockedSize, groupsUnblockedSize,
                               lgd.textureUnit,
                               lgd.textureData)
                           );
                   }
                }

                int numberOfGroupsUnblocked = groupsUnblocked.size();
                if (numberOfGroupsUnblocked > 0){
                    
                    MessagesHandler.messageGroupsUnblocked.display();
                    Utils.createSimpleAnimation(MessagesHandler.messageGroupsUnblocked, "translateX", "translateX", 500, -gameAreaResolutionX*1.5f, 0f).start();
                    Game.sound.playTextBoxAppear();


                    float initX = (resolutionX * 0.5f) - (((numberOfGroupsUnblocked * groupsUnblockedSize) + ((numberOfGroupsUnblocked-1)*groupsUnblockedPadd))/2f);

                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        groupsUnblocked.get(i).x = initX + (i * groupsUnblockedPadd) + (i * groupsUnblockedSize);
                    }


                    float halfDifference = ((groupsUnblockedSize * 1.2f) - groupsUnblockedSize)/2f;


                    for (int i = 0; i < numberOfGroupsUnblocked; i ++){
                        Image gu = groupsUnblocked.get(i);
                        gu.display();
                        //Utils.createAnimation5v(gu, "translateX", "translateX", 800, 0f, groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, -halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        //Utils.createAnimation5v(gu, "translateY", "translateY", 800, 0f, -groupsUnblockedSize/2f, 0.5f, 0f, 0.6f, halfDifference, 0.75f, 0f, 1f, 0f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleX", "scaleX", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                        Utils.createAnimation5v(gu, "scaleY", "scaleY", 800, 0f, 0f, 0.5f, 1f, 0.6f, 1.2f, 0.75f, 1f, 1f, 1f, false, true).start();
                    }
                }
            }

            if (groupsUnblocked.size() > 0) {
                currentLevelIcon.y = Game.resolutionY * 0.2f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.62f);
            } else {
                currentLevelIcon.y = Game.resolutionY * 0.3f;
                MessageStarWin.messageStarsWin.setY(Game.gameAreaResolutionY*0.76f);

            }
            currentLevelIcon.display();

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
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 1));
                            Game.sound.playStarsUp();

                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 2f) {
                        if (starsDiference > 1){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 2));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 3f) {
                        if (starsDiference > 2){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 3));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 4f) {
                        if (starsDiference > 3){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
                                    "\u0020" + NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal + 4));
                            Game.sound.playStarsUp();
                            //Sound.playSoundPool(Sound.soundStarsUp, 0.5f, 0.5f, 0);
                        }
                    } else if (MessagesHandler.messageConqueredStarsTotal.numberForAnimation == 5f) {
                        if (starsDiference > 4){
                            MessagesHandler.messageConqueredStarsTotal.setText(getContext().getResources().getString(R.string.messageConqueredStarsTotal) +
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
                    ButtonHandler.buttonContinue.display();
                    ButtonHandler.buttonContinue.unblock();
                    MessagesHandler.messageContinue.display();
                    MessagesHandler.messageContinue.setColor(new Color(0f, 0f, 0f, 1f));
                    StarsHandler.updateConqueredStars();
                }
            });
            animMessageConqueredStarsTotal.start();

        } else if (state == GAME_STATE_TUTORIAL) {

            Texture.getTextureById(Texture.TEXTURE_ICONS_CHANGE_TUTORIALS).changeBitmap("drawable/tutorials");
            MessagesHandler.messageMenu.clearDisplay();
            MessagesHandler.messageSubMenu.clearDisplay();
            MenuHandler.tutorialMenu.clearDisplay();
            MenuHandler.tutorialMenu.block();
            if (!sameState) {
                showBlackFrameTransition(500);
            }
            mainActivity.hideAdView();
            Tutorial.loadTutorial();
            Tutorial.currentTutorialObject.showFirst();
            
        }
}
























}

