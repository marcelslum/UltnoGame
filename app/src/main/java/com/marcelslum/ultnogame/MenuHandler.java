package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

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
    static Menu menuTutorialUnvisited;
    public static MenuIcon groupMenu;
    public static MenuIcon levelMenu;
    public static MenuIcon tutorialMenu;

    public static String TAG = "MenuHandler";

    public static void updateGroupMenu(){

        groupMenu.clear();
        
        StarsHandler.updateConqueredStars();

        if (SaveGame.saveGame.newGroupsSeen){
            if (SaveGame.saveGame.lastStars != StarsHandler.conqueredStarsTotal){
                SaveGame.saveGame.newGroupsSeen = false;
            }
        }

        int lastId = 0;
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
                }, false, false);

                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), false);


                int totalPoints = 0;
                for (int i2 = 0; i2 < lgd.levelsData.size(); i2++){
                    totalPoints += (int)SaveGame.saveGame.pointsLevels[lgd.levelsData.get(i2).number - 1];
                }


                groupMenu.addGraph("graph "+i, Game.resolutionY * 0.07f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_BAR, false);

                groupMenu.addText(2, lgd.name+"2",  String.valueOf(totalPoints)+" "+Game.getContext().getResources().getString(R.string.pontos),
                        Game.resolutionY * 0.03f, Game.resolutionY * 0.09f, new Color(0.35f, 0.35f, 0.35f, 1f), false);

                if (!SaveGame.saveGame.newGroupsSeen){
                    if (SaveGame.saveGame.lastStars < lgd.starsToUnlock){
                        groupMenu.addInnerText(lgd.name+"inner", Game.getContext().getResources().getString(R.string.novo), Game.resolutionY * 0.05f, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
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
                }, true, false);

                groupMenu.icons.get(i).alpha = 0.2f;
                groupMenu.addText(1, lgd.name, lgd.name, Game.resolutionY * 0.04f, Game.resolutionY * 0.01f, new Color(0.7f, 0.7f, 0.7f, 1f), false);
                groupMenu.addText(2, lgd.name+"2", Game.getContext().getResources().getString(R.string.tenha) + " " + lgd.starsToUnlock + " " + Game.getContext().getResources().getString(R.string.estrelas), Game.resolutionY * 0.03f, Game.resolutionY * 0.07f, new Color(0.5f, 0.5f, 0.5f, 1f), false);
            }
            lastId = i;
        }

        lastId += 1;
        
        for (int i = 0; i < Level.numberOfSecretLevels; i++){

                final int numberOfCurrentLevelNumber = 1000 + i;

               if (SaveGame.saveGame.secretLevelsUnlocked[i]){

                   groupMenu.addOption(lastId+i, Texture.TEXTURE_LEVEL_ICONS, 2, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        SaveGame.saveGame.currentLevelNumber = numberOfCurrentLevelNumber;
                        float size = Game.resolutionX * 0.21f;
                        Game.currentLevelIcon = new Image("Game.currentLevelIcon", (Game.resolutionX * 0.5f) - size * 0.5f,
                                Game.resolutionY * 0.2f,
                                size, size,
                                Texture.TEXTURE_LEVEL_ICONS,Utils.getUvData256(2)
                        );
                        Game.currentLevelIcon.clearDisplay();
                        Game.setGameState(Game.GAME_STATE_OBJETIVO_LEVEL);
                        }
                    }, false, !SaveGame.saveGame.secretLevelsSeen[i]);
                   
                   groupMenu.addText(1, "Level secreto " + (i + 1), "Level secreto " + (i + 1), Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), !SaveGame.saveGame.secretLevelsSeen[i]);
                   MenuIconGraph menuGraph = groupMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS, !SaveGame.saveGame.secretLevelsSeen[i]);

                   groupMenu.addText(2, "Level secreto " + (i + 1) + "2",  (int)SaveGame.saveGame.pointsSecretLevels[0]+" "+Game.getContext().getResources().getString(R.string.pontos),
                            Game.resolutionY * 0.03f, Game.resolutionY * 0.12f, new Color(0.35f, 0.35f, 0.35f, 1f), !SaveGame.saveGame.secretLevelsSeen[i]);

                    float percentage = 0f;
                    float starsOfLevel = SaveGame.saveGame.starsSecretLevels[i];
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
        
        levelMenu.currentTranslateX = 0;

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
            }, false, false);

            levelMenu.addText(1, ld.name, ld.name,Game.resolutionY * 0.04f, Game.resolutionY * 0.008f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            levelMenu.addGraph("graph "+i, Game.resolutionY * 0.06f, Game.resolutionY * 0.015f, MenuIconGraph.TYPE_STARS, false);

            levelMenu.addText(2, ld.name+"2",  (int)SaveGame.saveGame.pointsLevels[ld.number - 1]+" "+Game.getContext().getResources().getString(R.string.pontos),
                    Game.resolutionY * 0.03f, Game.resolutionY * 0.12f, new Color(0.35f, 0.35f, 0.35f, 1f), false);

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
        tutorialMenu.clear();
        
        float innerTextSize = Game.resolutionY * 0.08f;
        float textSize = Game.resolutionY * 0.032f;

        //TUTORIAL1
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_INSTRUCOES_INICIAIS)){
            tutorialMenu.addOption(0, Texture.TEXTURE_TUTORIAL_ICONS, 1, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_INSTRUCOES_INICIAIS;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

                tutorialMenu.addText(1, "t1", Game.getContext().getResources().getString(R.string.tutorial1Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            
            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_INSTRUCOES_INICIAIS]){
                tutorialMenu.addInnerText("t1i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }

        //TUTORIAL2
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_INICIO)) {
            tutorialMenu.addOption(1, Texture.TEXTURE_TUTORIAL_ICONS, 2, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_INICIO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

            tutorialMenu.addText(1, "t2", Game.getContext().getResources().getString(R.string.tutorial2Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_INICIO]) {
                tutorialMenu.addInnerText("t2i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }


        //TUTORIAL3
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_MOVIMENTO_BARRA)) {
            tutorialMenu.addOption(2, Texture.TEXTURE_TUTORIAL_ICONS, 2, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_MOVIMENTO_BARRA;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

            tutorialMenu.addText(1, "t3", Game.getContext().getResources().getString(R.string.tutorial3Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_MOVIMENTO_BARRA]) {
                tutorialMenu.addInnerText("t3i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }

        //TUTORIAL4
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_INCLINACAO_BARRA)) {
            tutorialMenu.addOption(3, Texture.TEXTURE_TUTORIAL_ICONS, 2, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_INCLINACAO_BARRA;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

            tutorialMenu.addText(1, "t4", Game.getContext().getResources().getString(R.string.tutorial4Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_INCLINACAO_BARRA]) {
                tutorialMenu.addInnerText("t4i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }

        //TUTORIAL5
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_OBSTACULO)){
            tutorialMenu.addOption(4, Texture.TEXTURE_TUTORIAL_ICONS, 3, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_OBSTACULO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);
            
            tutorialMenu.addText(1, "t5", Game.getContext().getResources().getString(R.string.tutorial5Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            
            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_OBSTACULO]){
                tutorialMenu.addInnerText("t5i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }

        //TUTORIAL6
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_CORES)){
            tutorialMenu.addOption(5, Texture.TEXTURE_TUTORIAL_ICONS, 4, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_CORES;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

            tutorialMenu.addText(1, "t6", Game.getContext().getResources().getString(R.string.tutorial6Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            
            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_CORES]){
                tutorialMenu.addInnerText("t6i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }

        }

        //TUTORIAL7
        if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_EXPLOSAO)){
            tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Tutorial.currentTutorial = Tutorial.TUTORIAL_EXPLOSAO;
                    Game.setGameState(Game.GAME_STATE_TUTORIAL);
                }
            }, false, false);

            tutorialMenu.addText(1, "t7", Game.getContext().getResources().getString(R.string.tutorial7Tittle),
                    textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);
            
            if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_EXPLOSAO]){
                tutorialMenu.addInnerText("t7i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
            }
        }

        //TUTORIAL8
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_ALVO_FANTASMA)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_ALVO_FANTASMA;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t8", Game.getContext().getResources().getString(R.string.tutorial8Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_ALVO_FANTASMA]){
                    tutorialMenu.addInnerText("t8i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL9
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_OBSTACULOS_DINAMICOS)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_OBSTACULOS_DINAMICOS;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t9", Game.getContext().getResources().getString(R.string.tutorial9Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_OBSTACULOS_DINAMICOS]){
                    tutorialMenu.addInnerText("t9i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL10
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_BOLAS_INVENCIVEIS)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_BOLAS_INVENCIVEIS;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t10", Game.getContext().getResources().getString(R.string.tutorial10Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_BOLAS_INVENCIVEIS]){
                    tutorialMenu.addInnerText("t10i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL11
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_BOLAS_PRESAS)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_BOLAS_PRESAS;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t11", Game.getContext().getResources().getString(R.string.tutorial11Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_BOLAS_PRESAS]){
                    tutorialMenu.addInnerText("t11i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL12
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_VENTO)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_TUTORIAL_VENTOEXPLOSAO;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t12", Game.getContext().getResources().getString(R.string.tutorial12Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_VENTO]){
                    tutorialMenu.addInnerText("t12i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL13
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_BARRA_DINAMICA)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_BARRA_DINAMICA;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t13", Game.getContext().getResources().getString(R.string.tutorial13Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_BARRA_DINAMICA]){
                    tutorialMenu.addInnerText("t13i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL14
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_COMIDA)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_COMIDA;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t14", Game.getContext().getResources().getString(R.string.tutorial14Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_COMIDA]){
                    tutorialMenu.addInnerText("t14i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }

        //TUTORIAL15
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_BOLA_FALSA)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_BOLA_FALSA;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t15", Game.getContext().getResources().getString(R.string.tutorial15Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_BOLA_FALSA]){
                    tutorialMenu.addInnerText("t15i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }
        //TUTORIAL16
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_BOTAO_INVERTIDO)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_BOTAO_INVERTIDO;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t16", Game.getContext().getResources().getString(R.string.tutorial16Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_BOTAO_INVERTIDO]){
                    tutorialMenu.addInnerText("t16i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }
        //TUTORIAL17
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_DUAS_BARRAS)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_DUAS_BARRAS;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t17", Game.getContext().getResources().getString(R.string.tutorial17Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_DUAS_BARRAS]){
                    tutorialMenu.addInnerText("t17i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }
        //TUTORIAL18
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_DUAS_BOLAS)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_DUAS_BOLAS;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t18", Game.getContext().getResources().getString(R.string.tutorial18Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_DUAS_BOLAS]){
                    tutorialMenu.addInnerText("t18i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }
        //TUTORIAL19
        
       if (Tutorial.isTutorialUnblocked(Tutorial.TUTORIAL_GRADE)){
                tutorialMenu.addOption(6, Texture.TEXTURE_TUTORIAL_ICONS, 5, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Tutorial.currentTutorial = Tutorial.TUTORIAL_GRADE;
                        Game.setGameState(Game.GAME_STATE_TUTORIAL);
                    }
                }, false, false);

                tutorialMenu.addText(1, "t19", Game.getContext().getResources().getString(R.string.tutorial19Tittle),
                        textSize, Game.resolutionY * 0.01f, new Color(0.1f, 0.1f, 0.1f, 1f), false);

                if (!SaveGame.saveGame.tutorialsViwed[Tutorial.TUTORIAL_GRADE]){
                    tutorialMenu.addInnerText("t19i", Game.getContext().getResources().getString(R.string.novo), innerTextSize, Game.resolutionY * 0.025f, new Color(0.1f, 0.1f, 0.9f, 1f), false);
                }
            }
        }
}

    public static void initMenus(){

        float fontSize = Game.gameAreaResolutionY*0.08f;

        groupMenu = new MenuIcon("groupMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        updateGroupMenu();

        levelMenu = new MenuIcon("levelMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);
        updateLevelMenu();

        tutorialMenu = new MenuIcon("tutorialMenu", 0f, Game.resolutionY * 0.3f, Game.resolutionY * 0.4f);

        if (SaveGame.saveGame != null) {
            groupMenu.currentTranslateX = SaveGame.saveGame.currentGroupMenuTranslateX;
            tutorialMenu.currentTranslateX = SaveGame.saveGame.currentTutorialMenuTranslateX;
        }

        // -------------------------------------------MENU OBJETIVOS
        //menuObjectives = new Menu("menuObjectives", Game.resolutionX*0.5f, Game.resolutionY*0.87f, fontSize, font);
        //menuObjectives.addMenuOption("jogar", Game.getContext().getResources().getString(R.string.iniciar_jogo), new MenuOption.OnChoice() {@Override public void onChoice() {}});

        // -------------------------------------------MENU OPTIONS
        menuOptions = new Menu("menuOptions", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.56f, fontSize, font);

        // SELETOR MUSICA
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

        // SELETOR SOM
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

        // SELETOR VIBRAÇÃO
        SelectorHandle.selectorVibration = new Selector("Game.selectorVibration", 0f,0f, fontSize, "",
                new String[]{Game.getContext().getResources().getString(R.string.desligado),
                        Game.getContext().getResources().getString(R.string.ligado)},
                font);

        menuOptions.addMenuOption("vibration", Game.getContext().getResources().getString(R.string.vibracao), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                SelectorHandle.selectorVibration.fromMenu(menuOptions);
            }
        });

        if (SaveGame.saveGame.vibration) {
            SelectorHandle.selectorVibration.setSelectedValue(1);
        } else {
            SelectorHandle.selectorVibration.setSelectedValue(0);
        }

        SelectorHandle.selectorVibration.setOnChange(new Selector.OnChange() {
            @Override
            public void onChange() {
                if (SelectorHandle.selectorVibration.selectedValue == 1){
                    SaveGame.saveGame.vibration = true;
                } else {
                    SaveGame.saveGame.vibration = false;
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


        // -------------------------------------------MENU TUTORIAL
        menuTutorialUnvisited = new Menu("menuTutorialUnvisited", Game.gameAreaResolutionX/2, Game.resolutionY*0.9f, fontSize*0.6f, font);

        // adiciona a opção de visualizar tutoriais
        menuTutorialUnvisited.addMenuOption("verTutoriais", Game.getContext().getResources().getString(R.string.menuTutoriais), new MenuOption.OnChoice() {
            @Override
            public void onChoice() {
                Game.blockAndWaitTouchRelease();
                Game.clearAllMenuEntities();
                Game.setGameState(Game.GAME_STATE_MENU_TUTORIAL);
            }
        });

        final Text option = menuTutorialUnvisited.getMenuOptionByName("verTutoriais").textObject;
        ArrayList<float[]> valuesAnim = new ArrayList<>();
        valuesAnim.add(new float[]{0f,1f});
        valuesAnim.add(new float[]{0.6f,2f});
        Animation animOption = new Animation(option, "numberForAnimation", "numberForAnimation", 2000, valuesAnim, true, false);
        animOption.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (option.numberForAnimation == 1f){
                    option.setColor(new Color(0.3f, 0.3f, 0.3f, 1f));
                } else if (option.numberForAnimation == 2f) {
                    option.setColor(new Color(1f, 0f, 0f, 1f));
                }
            }
        });
        animOption.start();

        // -------------------------------------------MENU MAIN
        menuMain = new Menu("menuMain", Game.gameAreaResolutionX/2, Game.gameAreaResolutionY*0.53f, fontSize, font);

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
