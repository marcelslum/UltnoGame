package com.marcelslum.ultnogame;

import android.content.pm.ActivityInfo;

import java.util.ArrayList;

public class MenuHandler {

    static Menu menuMain;

    static Menu menuOptions;
    static Menu menuInGame;
    static Menu menuInGameOptions;
    static Menu menuGameOver;
    static Menu menuTutorialUnvisited;
    static Menu menuConnect;

    static MenuIcon groupMenu;
    static MenuIcon levelMenu;
    static MenuIcon tutorialMenu;

    public static String TAG = "MenuHandler";

    public static void updateGroupMenu(){

        groupMenu.clear();
        
        StarsHandler.updateConqueredStars();
        
        int lastId = 0;
        for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++){
            
            final LevelsGroupData lgd = LevelsGroupData.levelsGroupData.get(i);
            
            if (StarsHandler.conqueredStarsTotal >= lgd.starsToUnlock){
                groupMenu.addOption(i, lgd.textureUnit, lgd.textureData, new Animation.AnimationListener() {
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
                        
                        Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
                    }
                }, false, false);

                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                int totalPoints = 0;
                for (int i2 = 0; i2 < lgd.levelsData.size(); i2++){
                        totalPoints += (int) SaveGame.saveGame.levelsPoints[lgd.levelsData.get(i2).number - 1];
                }

                groupMenu.addGraph("graph "+i, Game.resolutionY * 0.07f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_BAR, false);

                groupMenu.addText(2, lgd.name+"2",  String.valueOf(totalPoints)+" "+Game.getContext().getResources().getString(R.string.pontos),
                        Game.resolutionY * 0.03f, Game.resolutionY * 0.09f, new Color(0.35f, 0.35f, 0.35f, 1f), false);

                //Log.e(TAG, " grupo " + lgd.number + " -> " + SaveGame.saveGame.groupsSeen[lgd.number - 1]);


                //Log.e(TAG, "group i = "+ i + " seen "+SaveGame.saveGame.groupsSeen[i]);
                if (!SaveGame.saveGame.groupsSeen[i]){

                    groupMenu.addInnerText(lgd.name+"inner", Game.getContext().getResources().getString(R.string.novo), Game.resolutionY * 0.05f, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                    groupMenu.iconNumberToShow = i;
                }

                int cStars = LevelsGroupData.getLevelsConqueredStars(lgd.firstLevel, lgd.finalLevel);
                int totalStarsToConquer = (lgd.finalLevel - lgd.firstLevel + 1) * 5;
                float percentage = (float)cStars/(float)totalStarsToConquer;
                groupMenu.graph.get(groupMenu.graph.size() - 1).setPercentage(percentage);
            }

            if (StarsHandler.conqueredStarsTotal < lgd.starsToUnlock){
                groupMenu.addOption(i, lgd.textureUnit, lgd.textureData, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.message_sem_estrelas), 2000);
                    }
                }, true, false);

                groupMenu.icons.get(i).alpha = 0.2f;
                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.7f, 0.7f, 0.7f, 1f), false);
                groupMenu.addText(2, lgd.name+"2", Game.getContext().getResources().getString(R.string.tenha) + " " + lgd.starsToUnlock + " " + Game.getContext().getResources().getString(R.string.estrelas), Game.resolutionY * 0.03f, Game.resolutionY * 0.07f, new Color(0.5f, 0.5f, 0.5f, 1f), false);
            }
            lastId = i;
        }

        lastId += 1;
        
        int firstSecretLevelOnArray = 100;
        for (int i = 0; i < Level.NUMBER_OF_SECRET; i++){
            
               final int numberOfCurrentLevelNumber = firstSecretLevelOnArray + 1 + i;
            
               if (SaveGame.saveGame.levelsUnlocked[firstSecretLevelOnArray + i]){


                   int textureId;
                   if (i == 0){
                       textureId = TextureData.TEXTURE_l101;
                   } else if (i == 1){
                       textureId = TextureData.TEXTURE_l102;
                   } else if (i == 2){
                       textureId = TextureData.TEXTURE_l103;
                   } else {
                       textureId = TextureData.TEXTURE_l104;
                   }
                   final int ftextureId = textureId;

                   groupMenu.addOption(lastId+i, Texture.TEXTURE_ICONS_CHANGE_TUTORIALS, TextureData.getTextureDataById(textureId), new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        SaveGame.saveGame.currentLevelNumber = numberOfCurrentLevelNumber;
                        float size = Game.resolutionX * 0.21f;

                        Game.currentLevelIcon = new Image("Game.currentLevelIcon", (Game.resolutionX * 0.5f) - size * 0.5f,
                                Game.resolutionY * 0.2f,
                                size, size,
                                Texture.TEXTURE_ICONS_CHANGE_TUTORIALS, TextureData.getTextureDataById(ftextureId)
                        );
                        Game.currentLevelIcon.clearDisplay();
                        Game.setGameState(Game.GAME_STATE_OBJETIVO_LEVEL);
                        }
                    }, false, !SaveGame.saveGame.levelsSeen[firstSecretLevelOnArray + i]);
                   
                   groupMenu.addText(1, "Level secreto " + (i + 1), "Level secreto " + (i + 1), Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), !SaveGame.saveGame.levelsSeen[firstSecretLevelOnArray + i]);
                   MenuIconGraph menuGraph = groupMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS, !SaveGame.saveGame.levelsSeen[firstSecretLevelOnArray + i]);

                   groupMenu.addText(2, "Level secreto " + (i + 1) + "2",  (int)SaveGame.saveGame.levelsPoints[firstSecretLevelOnArray + i]+" "+Game.getContext().getResources().getString(R.string.pontos),
                            Game.resolutionY * 0.03f, Game.resolutionY * 0.12f, new Color(0.35f, 0.35f, 0.35f, 1f), !SaveGame.saveGame.levelsSeen[firstSecretLevelOnArray + i]);

                    float percentage = 0f;
                    float starsOfLevel = SaveGame.saveGame.levelsStars[firstSecretLevelOnArray + i];
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
                    menuGraph.setPercentage(percentage); 
               }
        }
    }

    public static void updateLevelMenu(){

        levelMenu.clear();

        levelMenu.currentTranslateX = SaveGame.saveGame.currentLevelMenuTranslateX;

        for (int i = 0; i < Game.currentLevelsGroupDataSelected.levelsData.size(); i++){
            final LevelsGroupData.LevelData ld = Game.currentLevelsGroupDataSelected.levelsData.get(i);

            //Log.e(TAG, "ld.number "+ ld.number);
            if (ld.textureData == null){
                //Log.e(TAG, "ld.textureData nulo");
            }
            levelMenu.addOption(i, ld.textureUnit, ld.textureData, new Animation.AnimationListener() {
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
                    Game.setGameState(Game.GAME_STATE_OBJETIVO_LEVEL);
                }
            }, false, false);

            levelMenu.addText(1, ld.name, ld.name,Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            levelMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS, false);

            levelMenu.addText(2, ld.name+"2",  (int)SaveGame.saveGame.levelsPoints[ld.number - 1]+" "+Game.getContext().getResources().getString(R.string.pontos),
                    Game.resolutionY * 0.03f, Game.resolutionY * 0.12f, new Color(0.35f, 0.35f, 0.35f, 1f), false);

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

            levelMenu.graph.get(i).setPercentage(percentage);
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
                tutorialMenu.addOption(i, Texture.TEXTURE_ICONS_CHANGE_TUTORIALS, textureData, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = tutorialNumber;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t"+i, text,
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsSeen[i]){
                    tutorialMenu.addInnerText("ti"+i, Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
   
        }
    }


    public static void initMenus(){

        float fontSize = Game.gameAreaResolutionY*0.08f;

        groupMenu = new MenuIcon("groupMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);

        levelMenu = new MenuIcon("levelMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);

        tutorialMenu = new MenuIcon("tutorialMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);

        if (SaveGame.saveGame != null) {
            levelMenu.currentTranslateX = 0;
            groupMenu.currentTranslateX = SaveGame.saveGame.currentGroupMenuTranslateX;
            tutorialMenu.currentTranslateX = SaveGame.saveGame.currentTutorialMenuTranslateX;
        }

        // -------------------------------------------MENU OBJETIVOS
        //menuObjectives = new Menu("menuObjectives", Game.resolutionX*0.5f, Game.resolutionY*0.87f, fontSize, font);
        //menuObjectives.addMenuOption("jogar", Game.getContext().getResources().getString(R.string.iniciar_jogo), new MenuOption.OnChoice() {@Override public void onChoice() {}});

        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.35f, fontSize, Game.font);

        menuOptions.addMenuOption("sobre", Game.getContext().getResources().getString(R.string.lerSobre), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_SOBRE);
            }
        });


        // SELETOR DIFICULDADE
        SelectorHandler.selectorDifficulty = new Selector("Game.selectorDifficulty", 0f,0f, fontSize, "",
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

        menuOptions.addMenuOption("difficulty", Game.getContext().getResources().getString(R.string.velocidade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorDifficulty.fromMenu(menuOptions);
            }
        });

        //if (SaveGame.saveGame.ballVelocity == 70) {
        //    SelectorHandler.selectorDifficulty.setSelectedValue(0);
        //} else if (SaveGame.saveGame.ballVelocity == 80) {
        //    SelectorHandler.selectorDifficulty.setSelectedValue(1);
        //} else
        
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
                //if (SelectorHandler.selectorDifficulty.selectedValue == 0) {
                //    SaveGame.saveGame.ballVelocity = 70;
                //} else if (SelectorHandler.selectorDifficulty.selectedValue == 1) {
                //    SaveGame.saveGame.ballVelocity = 80;
                //} else 
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

                //Log.e(TAG, "SaveGame.saveGame.ballVelocity "+SaveGame.saveGame.ballVelocity);

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

        menuOptions.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorMusic.fromMenu(menuOptions);
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

        menuOptions.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorSound.fromMenu(menuOptions);
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

        menuOptions.addMenuOption("vibration", Game.getContext().getResources().getString(R.string.vibracao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorVibration.fromMenu(menuOptions);
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

        menuOptions.addMenuOption("google", Game.getContext().getResources().getString(R.string.logarGoogle), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (Game.mainActivity.isSignedIn()){
                    SaveGame.saveGame.googleOption = 0;
                    Game.mainActivity.signOut();
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.message_google_desconectado), 4000);
                } else {
                    Splash.forSignin = true;
                    SaveGame.saveGame.googleOption = 1;
                    Game.setGameState(Game.GAME_STATE_INTRO);
                }
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

        // adiciona a opção de visualizar tutoriais
        menuConnect.addMenuOption("conectar", Game.getContext().getResources().getString(R.string.menuConectar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_INTRO);
            }
        });

        menuConnect.getMenuOptionByName("conectar").textObject.setColor(new Color(1f, 1f, 1f, 1f));

        // -------------------------------------------MENU Tutorial.TUTORIAL
        menuTutorialUnvisited = new Menu("menuTutorialUnvisited", Game.gameAreaResolutionX/2, Game.resolutionY*0.9f, fontSize*0.6f, Game.font);

        // adiciona a opção de visualizar tutoriais
        menuTutorialUnvisited.addMenuOption("verTutoriais", Game.getContext().getResources().getString(R.string.menuTutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
            }
        });

        final Text optionTutorialUnvisite = menuTutorialUnvisited.getMenuOptionByName("verTutoriais").textObject;
        ArrayList<float[]> valuesAnim = new ArrayList<>();
        valuesAnim.add(new float[]{0f,1f});
        valuesAnim.add(new float[]{0.6f,2f});
        Animation animOption = new Animation(optionTutorialUnvisite, "numberForAnimation", "numberForAnimation", 2000, valuesAnim, true, false);
        animOption.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (optionTutorialUnvisite.numberForAnimation == 1f){
                    if (Game.gameState != Game.GAME_STATE_JOGAR) {
                        optionTutorialUnvisite.setColor(Color.cinza2);
                    }
                } else if (optionTutorialUnvisite.numberForAnimation == 2f) {
                    if (Game.gameState != Game.GAME_STATE_JOGAR) {
                        optionTutorialUnvisite.setColor(Color.vermelhoCheio);
                    }
                }
            }
        });
        animOption.start();



        // -------------------------------------------MENU MAIN
        menuMain = new Menu("menuMain", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.45f, fontSize, Game.font);

        // adiciona a opção de iniciar o jogo
        final Menu innerMenu = menuMain;
        menuMain.addMenuOption("IniciarJogo", Game.getContext().getResources().getString(R.string.menuPrincipalIniciar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                innerMenu.block();
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
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
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mAchievementsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showAchievements();
                }

            }
        });

        menuMain.addMenuOption("ranking", Game.getContext().getResources().getString(R.string.ranking), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                if (!Game.mainActivity.isSignedIn() || GoogleAPI.mLeaderboardsClient == null){
                    MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.precisa_google), 4000);
                } else {
                    GoogleAPI.showLeaderboards(Game.mainActivity.getResources().getString(R.string.leaderboard_geral));
                }
            }
        });

        menuMain.addMenuOption("tutorial", Game.getContext().getResources().getString(R.string.tutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
            }
        });

        /*
        menuMain.addMenuOption("insterstitial", "Mostrar propaganda", new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.mainActivity.showInterstitial();
            }
        });
        */

        // ----------------------------------------------------MENU GAME OVER
        menuGameOver = new Menu("menuGameOver",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.5f, fontSize, Game.font);

        // adiciona a opção continuar
        menuGameOver.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.tentarNovamente), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {

                Game.timesInterstitialOnGameOver += 1;

                if (Game.timesInterstitialOnGameOver >= 3) {
                    Game.timesInterstitialOnGameOver = 0;
                    menuGameOver.block();
                    Game.blockAndWaitTouchRelease();
                    menuInGame.clearDisplay();
                    Game.prepareAfterInterstitialFlag = true;
                    Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
                } else {
                    menuGameOver.block();
                    Game.blockAndWaitTouchRelease();
                    menuInGame.clearDisplay();
                    Game.prepareAfterInterstitialFlag = false;
                    Game.setGameState(Game.GAME_STATE_PREPARAR);
                }
            }
        });

        // adiciona a opção de voltar ao menu principal
        menuGameOver.addMenuOption("RetornarAoMenuPrincipal", Game.getContext().getResources().getString(R.string.sairDoJogo), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.timesInterstitialOnGameOver = 0;
                Game.prepareAfterInterstitialFlag = false;
                Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
            }
        });


        // ----------------------------------------------------MENU IN GAME
        menuInGame = new Menu("menuInGame",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.4f, fontSize, Game.font);

        // adiciona a opção continuar
        menuInGame.addMenuOption("Continuar", Game.getContext().getResources().getString(R.string.continuarJogar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                menuInGame.block();
                Game.blockAndWaitTouchRelease();
                if (Game.gameState == Game.GAME_STATE_PAUSE){
                    //Log.e("game", "menu continuar quando game state = GAME_STATE_PAUSE");
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
                if (Game.gameState == Game.GAME_STATE_PAUSE){
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
                Game.timesInterstitialOnGameOver = 0;
                Game.prepareAfterInterstitialFlag = false;
                Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
            }
        });

        // ----------------------------------------------------MENU IN GAME OPTIONS
        menuInGameOptions = new Menu("menuInGameOptions",Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.37f, fontSize, Game.font);

        menuInGameOptions.addMenuOption("difficulty", Game.getContext().getResources().getString(R.string.velocidade), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorDifficulty.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("music", Game.getContext().getResources().getString(R.string.musica), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorMusic.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("sound", Game.getContext().getResources().getString(R.string.sons), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorSound.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("vibration", Game.getContext().getResources().getString(R.string.vibracao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.selectorVibration.fromMenu(menuInGameOptions);
            }
        });

        menuInGameOptions.addMenuOption("retornar", Game.getContext().getResources().getString(R.string.retornar), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandler.backAllSelectors();
                Game.setGameState(Game.GAME_STATE_PAUSE);
            }
        });
    }
}
