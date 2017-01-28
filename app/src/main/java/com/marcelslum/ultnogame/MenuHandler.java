package com.marcelslum.ultnogame;

import android.util.Log;

import static com.marcelslum.ultnogame.Game.GAME_STATE_PAUSE;
import static com.marcelslum.ultnogame.Game.font;

/**
 * Created by marcel on 26/01/2017.
 */

public class MenuHandler {

    static Menu menuMain;
    static Menu menuOptions;
    static Menu menuInGame;
    static Menu menuInGameOptions;
    static Menu menuGameOver;
    public static MenuIcon groupMenu;
    public static MenuIcon levelMenu;
    public static MenuIcon tutorialMenu;

    public static String TAG = "MenuHandler";

    public static void updateGroupMenu(){

        groupMenu.icons.clear();
        groupMenu.texts.clear();
        groupMenu.texts2.clear();
        groupMenu.innerTexts.clear();
        groupMenu.graph.clear();

        StarsHandler.updateConqueredStars();

        if (SaveGame.saveGame.newGroupsSeen){
            if (SaveGame.saveGame.lastStars != StarsHandler.conqueredStarsTotal){
                SaveGame.saveGame.newGroupsSeen = false;
            }
        }


        for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){

            final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);

            if (StarsHandler.conqueredStarsTotal >= lgd.starsToUnlock){
                groupMenu.addOption(i, lgd.textureUnit, lgd.textureMap, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {

                        if (!SaveGame.saveGame.newGroupsSeen){
                            SaveGame.saveGame.newGroupsSeen = true;
                            SaveGame.saveGame.lastStars = StarsHandler.conqueredStarsTotal;
                        }

                        Game.currentLevelsGroupDataSelected = lgd;
                        Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                    }
                }, false);

                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));


                int totalPoints = 0;
                for (int i2 = 0; i2 < lgd.levelsData.size(); i2++){
                    totalPoints += (int)SaveGame.saveGame.pointsLevels[lgd.levelsData.get(i2).number - 1];
                }


                groupMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_BAR);

                groupMenu.addText(2, lgd.name+"2",  String.valueOf(totalPoints)+" "+Game.getContext().getResources().getString(R.string.pontos),
                        Game.resolutionY * 0.03f, Game.resolutionY * 0.09f, new Color(0.35f, 0.35f, 0.35f, 1f));

                if (!SaveGame.saveGame.newGroupsSeen){
                    if (SaveGame.saveGame.lastStars < lgd.starsToUnlock){
                        groupMenu.addInnerText(lgd.name+"inner", Game.getContext().getResources().getString(R.string.novo), Game.resolutionY * 0.05f, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f));
                        groupMenu.iconNumberToShow = i;
                    }
                }

                int cStars = LevelsGroupData.getLevelsConqueredStars(lgd.firstLevel, lgd.finalLevel);
                Log.e(TAG, "cStars of world "+ 1 + ": "+ cStars);
                int totalStarsToConquer = (lgd.finalLevel - lgd.firstLevel + 1) * 5;
                Log.e(TAG, "totalStarsToConquer of world "+ 1 + ": "+ totalStarsToConquer);
                float percentage = (float)cStars/(float)totalStarsToConquer;

                Log.e(TAG, "percentage of world "+ 1 + ": "+ percentage);

                groupMenu.graph.get(groupMenu.graph.size() - 1).setPercentage(percentage);
            }

            if (StarsHandler.conqueredStarsTotal < lgd.starsToUnlock){

                groupMenu.addOption(i, lgd.textureUnit, lgd.textureMap, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.message_sem_estrelas), 2000);
                    }
                }, true);

                groupMenu.icons.get(i).alpha = 0.2f;
                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.7f, 0.7f, 0.7f, 1f));
                groupMenu.addText(2, lgd.name+"2", Game.getContext().getResources().getString(R.string.tenha) + " " + lgd.starsToUnlock + " " + Game.getContext().getResources().getString(R.string.estrelas), Game.resolutionY * 0.03f, Game.resolutionY * 0.07f, new Color(0.5f, 0.5f, 0.5f, 1f));
            }
        }
    }

    public static void updateLevelMenu(){
        levelMenu.icons.clear();
        levelMenu.texts.clear();
        levelMenu.texts2.clear();
        levelMenu.graph.clear();


        if (Game.currentLevelsGroupDataSelected == null){
            Game.currentLevelsGroupDataSelected = LevelsGroupData.levelsGroupData.get(0);
        }


        for (int i = 0; i < Game.currentLevelsGroupDataSelected.levelsData.size(); i++){
            final LevelsGroupData.LevelData ld = Game.currentLevelsGroupDataSelected.levelsData.get(i);
            levelMenu.addOption(i, ld.textureUnit, ld.textureMap, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    SaveGame.saveGame.currentLevelNumber = ld.number;
                    float size = Game.resolutionX * 0.21f;

                    Game.currentLevelIcon = new Image("Game.currentLevelIcon", (Game.resolutionX * 0.5f) - size * 0.5f,
                            Game.resolutionY * 0.2f,
                            size, size,
                            ld.textureUnit,Utils.getUvData256(ld.textureMap)
                    );
                    Game.currentLevelIcon.clearDisplay();
                    Game.setGameState(Game.GAME_STATE_OBJETIVO_LEVEL);
                }
            }, false);

            levelMenu.addText(1, ld.name, ld.name,Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));
            levelMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS);

            levelMenu.addText(2, ld.name+"2",  (int)SaveGame.saveGame.pointsLevels[ld.number - 1]+" "+Game.getContext().getResources().getString(R.string.pontos),
                    Game.resolutionY * 0.03f, Game.resolutionY * 0.12f, new Color(0.35f, 0.35f, 0.35f, 1f));

            float percentage = 0f;
            float starsOfLevel = SaveGame.saveGame.starsLevels[ld.number-1];
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

            levelMenu.graph.get(i).setPercentage(percentage);
        }
    }

    public static void updateTutorialMenu(){
        tutorialMenu.icons.clear();
        tutorialMenu.texts.clear();
        tutorialMenu.texts2.clear();
        tutorialMenu.graph.clear();

        if (StarsHandler.conqueredStarsTotal >= LevelsGroupData.levelsGroupData.get(0).starsToUnlock){
            tutorialMenu.addOption(0, Texture.TEXTURE_TUTORIAL_ICONS, 1, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_INSTRUCOES_INICIAIS;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "instruções", Game.getContext().getResources().getString(R.string.tutorial1Tittle),
                    Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));

            tutorialMenu.addOption(1, Texture.TEXTURE_TUTORIAL_ICONS, 2, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_INICIO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "jogar", Game.getContext().getResources().getString(R.string.tutorial2Tittle),
                    Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));
        }


        if (StarsHandler.conqueredStarsTotal >= LevelsGroupData.levelsGroupData.get(1).starsToUnlock){
            tutorialMenu.addOption(2, Texture.TEXTURE_TUTORIAL_ICONS, 3, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_OBSTACULO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "obstaculo", Game.getContext().getResources().getString(R.string.tutorial3Tittle),
                    Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));

        }

        if (StarsHandler.conqueredStarsTotal >= LevelsGroupData.levelsGroupData.get(2).starsToUnlock){
            tutorialMenu.addOption(3, Texture.TEXTURE_TUTORIAL_ICONS, 4, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_CORES;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "cores", Game.getContext().getResources().getString(R.string.tutorial4Tittle),
                    Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));

        }

        if (StarsHandler.conqueredStarsTotal >= LevelsGroupData.levelsGroupData.get(3).starsToUnlock){
            tutorialMenu.addOption(4, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_EXPLOSAO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false);

            tutorialMenu.addText(1, "explosao", Game.getContext().getResources().getString(R.string.tutorial5Tittle),
                    Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f));

        }
    }

    public static void initMenus(){

        float fontSize = Game.gameAreaResolutionY*0.08f;

        groupMenu = new MenuIcon("groupMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        updateGroupMenu();

        levelMenu = new MenuIcon("levelMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        updateLevelMenu();

        tutorialMenu = new MenuIcon("tutorialMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);

        // -------------------------------------------MENU OBJETIVOS
        //menuObjectives = new Menu("menuObjectives", Game.resolutionX*0.5f, Game.resolutionY*0.87f, fontSize, font);
        //menuObjectives.addMenuOption("jogar", Game.getContext().getResources().getString(R.string.iniciar_jogo), new MenuOption.OnChoice() {@Override public void onChoice() {}});

        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.55f, fontSize, font);

        // cria o seletor de musica
        SelectorHandle.selectorMusic = new Selector("Game.selectorMusic", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado), Game.getContext().getResources().getString(R.string.ligado)}, font);
        menuOptions.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandle.selectorMusic.fromMenu(menuOptions);
            }
        });

        if (SaveGame.saveGame.music) {
            SelectorHandle.selectorMusic.setSelectedValue(1);
        } else {
            SelectorHandle.selectorMusic.setSelectedValue(0);
        }

        SelectorHandle.selectorMusic.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandle.selectorMusic.selectedValue == 1){SaveGame.saveGame.music = true;} else {SaveGame.saveGame.music = false;}
            }
        });

        // cria o seletor de sons
        SelectorHandle.selectorSound = new Selector("Game.selectorSound", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado), Game.getContext().getResources().getString(R.string.ligado)}, font);

        menuOptions.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandle.selectorSound.fromMenu(menuOptions);
            }
        });

        if (SaveGame.saveGame.sound) {
            SelectorHandle.selectorSound.setSelectedValue(1);
        } else {
            SelectorHandle.selectorSound.setSelectedValue(0);
        }
        SelectorHandle.selectorSound.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandle.selectorSound.selectedValue == 1){
                    SaveGame.saveGame.sound = true;
                } else {
                    SaveGame.saveGame.sound = false;
                }
            }
        });

        menuOptions.addMenuOption("retornar", Game.getContext().getResources().getString(R.string.retornarAoMenuPrincipal), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Log.e("findStateMenu", "11");
                Game.setGameState(Game.GAME_STATE_MENU);
            }
        });

        // -------------------------------------------MENU MAIN
        menuMain = new Menu("menuMain", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.55f, fontSize, font);

        // adiciona a opção de iniciar o jogo
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", Game.getContext().getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();

                if (!SaveGame.saveGame.tutorialsViwed[0]){
                    Tutorial.currentTutorial = 0;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                } else {
                    Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
                }

            }
        });

        // adiciona a opção de acessar as opções do jogo
        menuMain.addMenuOption("options", Game.getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_OPCOES);
            }
        });

        menuMain.addMenuOption("conquistas", Game.getContext().getResources().getString(R.string.conquistas), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showAchievements(Game.mainActivity.mGoogleApiClient, Game.mainActivity);
            }
        });

        menuMain.addMenuOption("ranking", Game.getContext().getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                GooglePlayGames.showLeaderboards(Game.mainActivity.mGoogleApiClient, Game.mainActivity);
            }
        });

        menuMain.addMenuOption("tutorial", Game.getContext().getResources().getString(R.string.tutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);

            }
        });

        // ----------------------------------------------------MENU GAME OVER
        menuGameOver = new Menu("menuGameOver",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuGameOver.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuGameOver.block();
                Game.blockAndWaitTouchRelease();
                LevelLoader.loadLevel(SaveGame.saveGame.currentLevelNumber);
                menuInGame.clearDisplay();
                Game.setGameState(Game.GAME_STATE_PREPARAR);
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuGameOver.addMenuOption("RetornarAoMenuPrincipal", Game.getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
            }
        });


        // ----------------------------------------------------MENU IN GAME
        menuInGame = new Menu("menuInGame",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.5f, fontSize, font);

        // adiciona a opção continuar
        menuInGame.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_PAUSE){
                    Log.e("game", "menu continuar quando game state = GAME_STATE_PAUSE");
                    Game.increaseAllGameEntitiesAlpha(500);
                    MessagesHandler.messageInGame.reduceAlpha(500,0f);
                    menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            Game.setGameState(Game.GAME_STATE_JOGAR);
                        }
                    });
                }
            }
        });

        // adiciona a opção de mostrar objetivos
        menuInGame.addMenuOption("objetivos", Game.getContext().getResources().getString(R.string.objetivos), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == GAME_STATE_PAUSE){
                    Game.setGameState(Game.GAME_STATE_OBJETIVO_PAUSE);
                }
            }
        });

        // adiciona a opção de acessar as opções do jogo
        menuInGame.addMenuOption("options", Game.getContext().getResources().getString(R.string.options), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_OPCOES_GAME);
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuInGame.addMenuOption("RetornarAoMenuPrincipal", Game.getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
            }
        });

        // ----------------------------------------------------MENU IN GAME OPTIONS
        menuInGameOptions = new Menu("menuInGameOptions",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.5f, fontSize, font);

        menuInGameOptions.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandle.selectorMusic.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandle.selectorSound.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("retornar", Game.getContext().getResources().getString(R.string.retornar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_PAUSE);
            }
        });
    }





}
