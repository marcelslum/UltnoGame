package com.marcelslum.ultnogame;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.util.Log;

public class MenuHandler {

    static Menu menuInicial;
    static Menu menuOptions;
    static Menu menuOpcoesJogabilidade;
    static Menu menuPause;
    static Menu menuPauseOpcoes;
    static Menu menuGameOver;
    static Menu menuTutorialUnvisited;
    static Menu menuConnect;
    static MenuIcon groupMenu;
    static MenuIcon levelMenu;
    static MenuIcon tutorialMenu;
    static Menu menuPlay;
    static Menu menuDuranteTreinamento;
    static Menu menuGoogleGeral;

    public static String TAG = "MenuHandler";

    public static void initMenus(){

        float fontSize = Game.gameAreaResolutionY*0.08f;

        groupMenu = new MenuIcon("groupMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        Game.adicionarEntidadeFixa(groupMenu);

        levelMenu = new MenuIcon("levelMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        Game.adicionarEntidadeFixa(levelMenu);

        tutorialMenu = new MenuIcon("tutorialMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        Game.adicionarEntidadeFixa(tutorialMenu);

        if (SaveGame.saveGame != null) {
            levelMenu.currentTranslateX = 0;
            groupMenu.currentTranslateX = SaveGame.saveGame.currentGroupMenuTranslateX;
            tutorialMenu.currentTranslateX = SaveGame.saveGame.currentTutorialMenuTranslateX;
        }



        /*
        // MENU RANKING

        menuRanking = new Menu("menuRanking",
                                           Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuRanking);


        menuRanking.addMenuOption("menu_ranking_1 1", Game.getContext().getResources().getString(R.string.menu_ranking_1), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_0));
                }

            }
        });
        
        menuRanking.addMenuOption("menu_ranking_2", Game.getContext().getResources().getString(R.string.menu_ranking_2), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_pontuao_vitoriosa));
                }
            }
        });

        menuRanking.addMenuOption("menu_ranking_ajuda", Game.getContext().getResources().getString(R.string.menu_ranking_ajuda), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_RANKING_AJUDA);
            }
        });

        // MENU OUTROS RANKINGS
        menuOutrosRankings = new Menu("menuOutrosRankings",
                Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuOutrosRankings);

        menuOutrosRankings.addMenuOption("menu_ranking_1 1", Game.getContext().getResources().getString(R.string.menu_ranking_1), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_0));
                }
            }
        });

        menuOutrosRankings.addMenuOption("menu_ranking_2", Game.getContext().getResources().getString(R.string.menu_ranking_2), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_0));
                }
            }
        });

        */


        // ----------------------------------------MENU JOGAR
        menuPlay = new Menu("menuPlay", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuPlay);
        menuPlay.addMenuOption("jogar", Game.getContext().getResources().getString(R.string.jogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_GRUPO);
            }
        });

        menuPlay.addMenuOption("tutoriais", Game.getContext().getResources().getString(R.string.tutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_TUTORIAL);
            }
        });

        menuPlay.addMenuOption("treinamento", Game.getContext().getResources().getString(R.string.sessao_treinamento), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_EXPLICACAO_TREINAMENTO);
            }
        });

        menuPlay.addMenuOption("estatisticas", Game.getContext().getResources().getString(R.string.estatisticasDeJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_ESTATISTICAS);
            }
        });

        // ----------------------------------------MENU DURANTE TREINAMENTO
        menuDuranteTreinamento = new Menu("menuDuranteTreinamento", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.65f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuDuranteTreinamento);
        menuDuranteTreinamento.addMenuOption("fazerTentativa", Game.getContext().getResources().getString(R.string.fazerTentativa), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_MENU_DURANTE_TREINAMENTO){
                    Game.increaseAllGameEntitiesAlpha(300);
                    menuDuranteTreinamento.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            MessagesHandler.messageTrainingState2.setText(Game.getContext().getResources().getString(R.string.tentativa) + " " + (Training.tentativaCertaTreinamento) + " " +Game.getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
                            MessagesHandler.messageTrainingState2.display();
                            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_JOGAR);
                        }
                    });
                }
            }
        });
        menuDuranteTreinamento.addMenuOption("sairDoTreinamento", Game.getContext().getResources().getString(R.string.sairTreinamento), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.timesInterstitialOnGameOver = 0;
                Game.prepareAfterInterstitialFlag = false;
                Game.returningFromTraining = true;
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTERSTITIAL);

            }
        });


        // -------------------------------------------MENU OPTIONS PLAY
        menuOpcoesJogabilidade = new Menu("menuOpcoesJogabilidade", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuOpcoesJogabilidade);
        // SELETOR DIFICULDADE
        SelectorHandler.selectorDifficulty = new Selector("selectorDifficulty", 0f,0f, fontSize, "",
                new String[]{   //Game.getContext().getResources().getString(R.string.v0),
                        //Game.getContext().getResources().getString(R.string.v1),
                        Game.getContext().getResources().getString(R.string.v0),
                        Game.getContext().getResources().getString(R.string.v1),
                        Game.getContext().getResources().getString(R.string.v2),
                        Game.getContext().getResources().getString(R.string.v3),
                        Game.getContext().getResources().getString(R.string.v4),
                        Game.getContext().getResources().getString(R.string.v5),
                        Game.getContext().getResources().getString(R.string.v6)
                },
                Game.font);
        Game.adicionarEntidadeFixa(SelectorHandler.selectorDifficulty);

        menuOpcoesJogabilidade.addMenuOption("difficulty", Game.getContext().getResources().getString(R.string.velocidade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorDifficulty.fromMenu(menuOpcoesJogabilidade);
            }
        });

        if (SaveGame.saveGame.ballVelocity == 90) {
            SelectorHandler.selectorDifficulty.setSelectedValue(0);
        } else if (SaveGame.saveGame.ballVelocity == 100) {
            SelectorHandler.selectorDifficulty.setSelectedValue(1);
        } else if (SaveGame.saveGame.ballVelocity == 110) {
            SelectorHandler.selectorDifficulty.setSelectedValue(2);
        } else if (SaveGame.saveGame.ballVelocity == 120) {
            SelectorHandler.selectorDifficulty.setSelectedValue(3);
        } else if (SaveGame.saveGame.ballVelocity == 130) {
            SelectorHandler.selectorDifficulty.setSelectedValue(4);
        } else if (SaveGame.saveGame.ballVelocity == 140) {
            SelectorHandler.selectorDifficulty.setSelectedValue(5);
        }  else if (SaveGame.saveGame.ballVelocity == 150) {
            SelectorHandler.selectorDifficulty.setSelectedValue(6);
        }

        SelectorHandler.selectorDifficulty.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandler.selectorDifficulty.selectedValue == 0) {
                    SaveGame.saveGame.ballVelocity = 90;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 1) {
                    SaveGame.saveGame.ballVelocity = 100;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 2) {
                    SaveGame.saveGame.ballVelocity = 110;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 3) {
                    SaveGame.saveGame.ballVelocity = 120;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 4) {
                    SaveGame.saveGame.ballVelocity = 130;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 5) {
                    SaveGame.saveGame.ballVelocity = 140;
                } else if (SelectorHandler.selectorDifficulty.selectedValue == 6) {
                    SaveGame.saveGame.ballVelocity = 150;
                }

                if (Game.balls != null){
                    for (int i = 0; i < Game.balls.size(); i++) {
                        Game.balls.get(i).updateBaseVelocity(SaveGame.saveGame.ballVelocity);
                    }
                }

                if (Game.bars != null){
                    for (int i = 0; i < Game.bars.size(); i++) {
                        Game.bars.get(i).updateBaseVelocity(SaveGame.saveGame.ballVelocity);
                    }
                }
            }
        });



        // SELETOR MUSICA
        SelectorHandler.selectorMusic = new Selector("Game.selectorMusic", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado), Game.getContext().getResources().getString(R.string.ligado)}, Game.font);
        Game.adicionarEntidadeFixa(SelectorHandler.selectorMusic);
        menuOpcoesJogabilidade.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorMusic.fromMenu(menuOpcoesJogabilidade);
            }
        });

        if (SaveGame.saveGame.music) {
            SelectorHandler.selectorMusic.setSelectedValue(1);
        } else {
            SelectorHandler.selectorMusic.setSelectedValue(0);
        }

        SelectorHandler.selectorMusic.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandler.selectorMusic.selectedValue == 1){SaveGame.saveGame.music = true;} else {SaveGame.saveGame.music = false;}
            }
        });

        // SELETOR SOM
        SelectorHandler.selectorSound = new Selector("Game.selectorSound", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado), Game.getContext().getResources().getString(R.string.ligado)}, Game.font);
        Game.adicionarEntidadeFixa(SelectorHandler.selectorSound);
        menuOpcoesJogabilidade.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorSound.fromMenu(menuOpcoesJogabilidade);
            }
        });

        if (SaveGame.saveGame.sound) {
            SelectorHandler.selectorSound.setSelectedValue(1);
        } else {
            SelectorHandler.selectorSound.setSelectedValue(0);
        }
        SelectorHandler.selectorSound.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandler.selectorSound.selectedValue == 1){
                    SaveGame.saveGame.sound = true;
                } else {
                    SaveGame.saveGame.sound = false;
                }
            }
        });

        // SELETOR VIBRAÇÃO
        SelectorHandler.selectorVibration = new Selector("Game.selectorVibration", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado),
                        Game.getContext().getResources().getString(R.string.ligado)},
                Game.font);
        Game.adicionarEntidadeFixa(SelectorHandler.selectorVibration);
        menuOpcoesJogabilidade.addMenuOption("vibration", Game.getContext().getResources().getString(R.string.vibracao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorVibration.fromMenu(menuOpcoesJogabilidade);
            }
        });

        if (SaveGame.saveGame.vibration) {
            SelectorHandler.selectorVibration.setSelectedValue(1);
        } else {
            SelectorHandler.selectorVibration.setSelectedValue(0);
        }

        SelectorHandler.selectorVibration.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandler.selectorVibration.selectedValue == 1){
                    SaveGame.saveGame.vibration = true;
                } else {
                    SaveGame.saveGame.vibration = false;
                }
            }
        });


        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuOptions);
        menuOptions.addMenuOption("sobre", Game.getContext().getResources().getString(R.string.lerSobre), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SOBRE);
            }
        });


        menuOptions.addMenuOption("jogabilidade", Game.getContext().getResources().getString(R.string.optionsPlay), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_OPCOES_JOGABILIDADE);
            }
        });

        menuOptions.addMenuOption("orientation", Game.getContext().getResources().getString(R.string.orientation), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (SaveGame.saveGame.orientationInverted) {
                    SaveGame.saveGame.orientationInverted = false;
                    Game.mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    SaveGame.saveGame.orientationInverted = true;
                    Game.mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                }
            }
        });

        // -------------------------------------------MENU CONECTAR
        menuConnect = new Menu(",menuConnect", Game.gameAreaResolutionX/2, Game.resolutionY*0.085f, fontSize*0.8f, Game.font);
        Game.adicionarEntidadeFixa(menuConnect);
        menuConnect.addMenuOption("conectar", Game.getContext().getResources().getString(R.string.menuConectar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Log.e(TAG, "setGameState(Game.GAME_STATE_INTRO)3");
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTRO);
            }
        });
        menuConnect.getMenuOptionByName("conectar").textObject.setColor(new Color(1f, 1f, 1f, 1f));

        // -------------------------------------------MENU Tutorial.TUTORIAL
        menuTutorialUnvisited = new Menu("menuTutorialUnvisited", Game.gameAreaResolutionX/2, Game.resolutionY*0.9f, fontSize*0.5f, Game.font);
        Game.adicionarEntidadeFixa(menuTutorialUnvisited);
        // adiciona a opção de visualizar tutoriais
        menuTutorialUnvisited.addMenuOption("verTutoriais", Game.getContext().getResources().getString(R.string.menuTutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_TUTORIAL);
            }
        });
        
        
        // -------------------------------------------MENU GOOGLE_GERAL
        menuGoogleGeral = new Menu("menuGoogleGeral", 
                                   Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.65f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuGoogleGeral);

        menuGoogleGeral.addMenuOption("google", Game.getContext().getResources().getString(R.string.logarGoogle), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (Game.mainActivity.isSignedIn()){
                    GoogleAPI.disconnecting = true;
                    GoogleAPI.disconnectGoogle();
                    menuGoogleGeral.block();
                } else {
                    GoogleAPI.connecting = true;
                    GoogleAPI.connectGoogle();
                    menuGoogleGeral.block();
                }
            }
        });
        
        // -------------------------------------------MENU MAIN
        menuInicial = new Menu("menuInicial", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.45f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuInicial);

        menuInicial.addMenuOption("jogar", Game.getContext().getResources().getString(R.string.jogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuInicial.block();
                Game.blockAndWaitTouchRelease();
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_JOGAR);
            }
        });

        menuInicial.addMenuOption("options", Game.getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_OPCOES);
            }
        });

        menuInicial.addMenuOption("conquistas", Game.getContext().getResources().getString(R.string.conquistas), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mAchievementsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showAchievements();
                }

            }
        });

        menuInicial.addMenuOption("ranking", Game.getContext().getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_0));
                }
            }
        });

        menuInicial.addMenuOption("salvar", Game.getContext().getResources().getString(R.string.salvar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {

                    boolean mostrarMenuSave = false;
                    if (Game.sempreVerSaveMenu){
                        mostrarMenuSave = true;
                    } else {
                        if (SaveGame.saveGame.saveMenuSeen) {
                            GoogleAPI.showSnapshots();
                        } else {
                            mostrarMenuSave = true;
                        }
                    }

                    if (mostrarMenuSave){
                        final MainActivity m = Game.mainActivity;
                        if (m != null) {
                            m.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    m.createMessageDialogBuilder(m.getResources().getString(R.string.salvarNuvemTitulo),
                                            m.getResources().getString(R.string.salvarNuvemMensagem))
                                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    SaveGame.saveGame.saveMenuSeen = true;
                                                    GoogleAPI.showSnapshots();
                                                }
                                            })
                                            .setNegativeButton(R.string.botaoCancelar, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            })
                                            .show();

                                }
                            });
                        }


                    }

                }
            }
        });

        // ----------------------------------------------------MENU GAME OVER
        menuGameOver = new Menu("menuGameOver",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuGameOver);

        // adiciona a opção continuar
        menuGameOver.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.tentarNovamente), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                Game.timesInterstitialOnGameOver += 1;

                if (Game.timesInterstitialOnGameOver >= 3) {
                    Game.timesInterstitialOnGameOver = 0;
                    Game.prepareAfterInterstitialFlag = true;
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTERSTITIAL);
                } else {
                    Game.prepareAfterInterstitialFlag = false;
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PREPARAR);
                }
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuGameOver.addMenuOption("RetornarAoMenuPrincipal", Game.getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.timesInterstitialOnGameOver = 0;
                Game.prepareAfterInterstitialFlag = false;
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTERSTITIAL);
            }
        });


        // ----------------------------------------------------MENU_PAUSE
        menuPause = new Menu("menuPause",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.45f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuPause);

        menuPause.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PAUSE){
                    menuPause.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_JOGAR);
                        }
                    });
                }
            }
        });
        menuPause.addMenuOption("objetivos", Game.getContext().getResources().getString(R.string.objetivos), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_PAUSE){
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PAUSE_OBJETIVO);
                }
            }
        });
        menuPause.addMenuOption("options", Game.getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_PAUSE_OPCOES);
            }
        });
        menuPause.addMenuOption("RetornarAoMenuPrincipal", Game.getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                Stats.tempoJogadoNaoCompletado += TimeHandler.timeOfLevelPlay;

                for (int i = 0; i < Game.balls.size(); i++) {
                    Stats.collectBallData(Game.balls.get(i));
                }

                switch (Stats.ultimoNumeroBolasVivas){
                    case 1:
                        Stats.tempo1Bola += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 2:
                        Stats.tempo2Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 3:
                        Stats.tempo3Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 4:
                        Stats.tempo4Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 5:
                        Stats.tempo5Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 6:
                        Stats.tempo6Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 7:
                        Stats.tempo7Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 8:
                        Stats.tempo8Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                    case 9:
                        Stats.tempo9Bolas += TimeHandler.timeOfLevelPlay - Stats.ultimoNumeroBolasVivasTempoDeRegistro;
                        break;
                }


                Stats.saveData();
                SaveGame.saveGame.save();

                Game.timesInterstitialOnGameOver = 0;
                Game.prepareAfterInterstitialFlag = false;
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_INTERSTITIAL);
            }
        });

        // ----------------------------------------------------MENU IN GAME OPTIONS
        menuPauseOpcoes = new Menu("menuPauseOpcoes",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.42f, fontSize, Game.font);
        Game.adicionarEntidadeFixa(menuPauseOpcoes);

        menuPauseOpcoes.addMenuOption("difficulty", Game.getContext().getResources().getString(R.string.velocidade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorDifficulty.fromMenu(menuPauseOpcoes);
            }
        });

        menuPauseOpcoes.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorMusic.fromMenu(menuPauseOpcoes);
            }
        });

        menuPauseOpcoes.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorSound.fromMenu(menuPauseOpcoes);
            }
        });

        menuPauseOpcoes.addMenuOption("vibration", Game.getContext().getResources().getString(R.string.vibracao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorVibration.fromMenu(menuPauseOpcoes);
            }
        });
    }

    public static void updateGroupMenu(){

        if (Game.logMenuIconMoveAndTranslateX) Log.e(TAG, "groupMenu.currentTranslateX " +groupMenu.currentTranslateX);
        groupMenu.clear();
        if (Game.logMenuIconMoveAndTranslateX) Log.e(TAG, "groupMenu.currentTranslateX2 " +groupMenu.currentTranslateX);
        groupMenu.currentTranslateX = SaveGame.saveGame.currentGroupMenuTranslateX;
        if (Game.logMenuIconMoveAndTranslateX) Log.e(TAG, "groupMenu.currentTranslateX3 " +groupMenu.currentTranslateX);

        StarsHandler.updateConqueredStars();

        for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){

            final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);

            if (StarsHandler.conqueredStarsTotal >= lgd.starsToUnlock){
                groupMenu.addOption(i, i, lgd.textureUnit, lgd.textureData, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Game.currentLevelsGroupDataSelected = lgd;

                        if (SaveGame.saveGame.currentGroupNumber != lgd.number){
                            //Log.e(TAG, "alterando currentGroupNumber de " + SaveGame.saveGame.currentGroupNumber + " para " + lgd.number);
                            SaveGame.saveGame.currentLevelMenuTranslateX = 0;
                            SaveGame.saveGame.currentGroupNumber = lgd.number;
                        } else {
                            SaveGame.saveGame.currentLevelMenuTranslateX = levelMenu.currentTranslateX;
                        }

                        if (!SaveGame.saveGame.groupsSeen[lgd.number - 1]){
                            SaveGame.setGroupSeen(lgd.number);
                        }

                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_SELECAO_LEVEL);
                    }
                }, false);

                groupMenu.addText(i,1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.006f, Color.cinza70, Color.cinza20.changeAlpha(0.8f));

                int totalPoints = 0;
                for (int i2 = 0; i2 < lgd.levelsData.size(); i2++){
                    totalPoints += SaveGame.saveGame.levelsPoints[lgd.levelsData.get(i2).number - 1];
                }

                groupMenu.addGraph(i,"graph "+i, Game.resolutionY * 0.07f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_BAR);

                groupMenu.addText(i,2, lgd.name+"2",  String.valueOf(totalPoints)+" "+Game.getContext().getResources().getString(R.string.pontos),
                        Game.resolutionY * 0.028f, Game.resolutionY * 0.095f, Color.pretoCheio.changeAlpha(0.4f), Color.zero);

                //Log.e(TAG, " grupo " + lgd.number + " -> " + SaveGame.saveGame.groupsSeen[lgd.number - 1]);


                //Log.e(TAG, "group i = "+ i + " seen "+SaveGame.saveGame.groupsSeen[i]);
                if (!SaveGame.saveGame.groupsSeen[i]){

                    groupMenu.addInnerText(i,lgd.name+"inner", Game.getContext().getResources().getString(R.string.novo), Game.resolutionY * 0.05f, Game.resolutionY * 0.05f, new Color(0.1f, 0.1f, 0.9f, 1f));
                    groupMenu.iconNumberToShow = i;
                }

                int cStars = LevelsGroupData.getLevelsConqueredStars(lgd.firstLevel, lgd.finalLevel);
                int totalStarsToConquer = (lgd.finalLevel - lgd.firstLevel + 1) * 5;
                float percentage = (float)cStars/(float)totalStarsToConquer;
                groupMenu.graph[i].setPercentage(percentage, i);
            }

            if (StarsHandler.conqueredStarsTotal < lgd.starsToUnlock){
                groupMenu.addOption(i, i, lgd.textureUnit, lgd.textureData, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.message_sem_estrelas), 2000);
                    }
                }, true);

                groupMenu.icons[i].alpha = 0.2f;
                groupMenu.addText(i,1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, Color.cinza80, Color.cinza40.changeAlpha(0.6f));
                groupMenu.addText(i,2, lgd.name+"2", Game.getContext().getResources().getString(R.string.tenha) + " " + lgd.starsToUnlock + " " + Game.getContext().getResources().getString(R.string.estrelas), Game.resolutionY * 0.028f, Game.resolutionY * 0.07f, Color.pretoCheio.changeAlpha(0.25f), Color.zero);
            }


            /*
            if (groupMenu.graph[i] != null){
                Log.e(TAG, "frontRectangle.animTranslateX "+groupMenu.graph[i].frontRectangle.animTranslateX);
                Log.e(TAG, "frontRectangle.animScaleX "+groupMenu.graph[i].frontRectangle.animScaleX);
            }
            */
        }
    }

    public static void updateLevelMenu(){

        levelMenu.clear();

        levelMenu.currentTranslateX = SaveGame.saveGame.currentLevelMenuTranslateX;

        for (int i = 0; i < Game.currentLevelsGroupDataSelected.levelsData.size(); i++){
            final LevelsGroupData.LevelData ld = Game.currentLevelsGroupDataSelected.levelsData.get(i);

            //Log.e(TAG, "ld.number "+ ld.number);
            if (ld.textureData == null){
                Log.e(TAG, "ld.textureData nulo");
            }
            levelMenu.addOption(i, i, ld.textureUnit, ld.textureData, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    SaveGame.saveGame.currentLevelNumber = ld.number;
                    float size = Game.resolutionX * 0.21f;

                    Game.currentLevelIcon = new Image("Game.currentLevelIcon", (Game.resolutionX * 0.5f) - size * 0.5f,
                            Game.resolutionY * 0.2f,
                            size, size,
                            ld.textureUnit,ld.textureData
                    );
                    Game.currentLevelIcon.clearDisplay();
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MOSTRAR_OBJETIVOS);
                }
            }, false);

            levelMenu.addText(i,1, ld.name, ld.name,Game.resolutionY * 0.04f, Game.resolutionY * 0.006f, Color.cinza70, Color.cinza20.changeAlpha(0.8f));
            levelMenu.addGraph(i,"graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS);

            levelMenu.addText(i,2, ld.name+"2",  (int)SaveGame.saveGame.levelsPoints[ld.number - 1]+" "+Game.getContext().getResources().getString(R.string.pontos),
                    Game.resolutionY * 0.03f, Game.resolutionY * 0.125f, Color.pretoCheio.changeAlpha(0.4f), Color.zero);

            float percentage = 0f;
            float starsOfLevel = SaveGame.saveGame.levelsStars[ld.number-1];
            if (starsOfLevel == 1){
                percentage = 0.2f;
            } else if (starsOfLevel == 2){
                percentage = 0.4f;
            } else if (starsOfLevel == 3){
                percentage = 0.6f;
            } else if (starsOfLevel == 4){
                percentage = 0.8f;
            } else if (starsOfLevel == 5){
                percentage = 1f;
            }

            levelMenu.graph[i].setPercentage(percentage, i);
        }
    }

    public static void updateTutorialMenu(){
        tutorialMenu.clear();

        float innerTextSize = Game.resolutionY * 0.08f;
        float textSize = Game.resolutionY * 0.032f;

        TextureData textureData;
        String text;

        for (int i = 0; i < Tutorial.NUMBER_OF_TUTORIALS; i++){
            switch (i){
                case Tutorial.TUTORIAL_INSTRUCOES_INICIAIS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l101);
                    text = Game.getContext().getResources().getString(R.string.tutorial1Tittle);
                    break;
                case Tutorial.TUTORIAL_INICIO:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial2Tittle);
                    break;
                case Tutorial.TUTORIAL_MOVIMENTO_BARRA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial3Tittle);
                    break;
                case Tutorial.TUTORIAL_INCLINACAO_BARRA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l104);
                    text = Game.getContext().getResources().getString(R.string.tutorial4Tittle);
                    break;
                case Tutorial.TUTORIAL_OBSTACULO:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial5Tittle);
                    break;
                case Tutorial.TUTORIAL_CORES:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial6Tittle);
                    break;
                case Tutorial.TUTORIAL_EXPLOSAO:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l101);
                    text = Game.getContext().getResources().getString(R.string.tutorial7Tittle);
                    break;
                case Tutorial.TUTORIAL_ALVO_FANTASMA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial8Tittle);
                    break;
                case Tutorial.TUTORIAL_OBSTACULOS_DINAMICOS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial9Tittle);
                    break;
                case Tutorial.TUTORIAL_BOLAS_INVENCIVEIS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l104);
                    text = Game.getContext().getResources().getString(R.string.tutorial10Tittle);
                    break;
                case Tutorial.TUTORIAL_BOLAS_PRESAS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial11Tittle);
                    break;
                case Tutorial.TUTORIAL_VENTO:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial12Tittle);
                    break;
                case Tutorial.TUTORIAL_BARRA_DINAMICA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l101);
                    text = Game.getContext().getResources().getString(R.string.tutorial13Tittle);
                    break;
                case Tutorial.TUTORIAL_COMIDA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l104);
                    text = Game.getContext().getResources().getString(R.string.tutorial14Tittle);
                    break;
                case Tutorial.TUTORIAL_BOLA_FALSA:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial15Tittle);
                    break;
                case Tutorial.TUTORIAL_BOTAO_INVERTIDO:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial16Tittle);
                    break;
                case Tutorial.TUTORIAL_DUAS_BARRAS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l101);
                    text = Game.getContext().getResources().getString(R.string.tutorial17Tittle);
                    break;
                case Tutorial.TUTORIAL_DUAS_BOLAS:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l104);
                    text = Game.getContext().getResources().getString(R.string.tutorial18Tittle);
                    break;
                case Tutorial.TUTORIAL_GRADE:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l103);
                    text = Game.getContext().getResources().getString(R.string.tutorial19Tittle);
                    break;
                default:
                    textureData = TextureData.getTextureDataById(TextureData.TEXTURE_l102);
                    text = Game.getContext().getResources().getString(R.string.tutorial19Tittle);
                    break;
            }

            final int tutorialNumber = i;
            if (Tutorial.isTutorialUnblocked(i)){
                tutorialMenu.addOption(i, i, Texture.TEXTURE_ICONS_CHANGE_TUTORIALS, textureData, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = tutorialNumber;
                        GameStateHandler.setGameState(GameStateHandler.GAME_STATE_TUTORIAL);
                    }
                }, false);

                tutorialMenu.addText(i,1, "t"+i, text,
                        textSize, Game.resolutionY * 0.01f, Color.cinza70, Color.cinza20.changeAlpha(0.8f));

                if (!SaveGame.saveGame.tutorialsSeen[i]){
                    tutorialMenu.addInnerText(i,"ti"+i, Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.05f, new Color(0.1f, 0.1f, 0.9f, 1f));
                }
            }

        }
    }


}
