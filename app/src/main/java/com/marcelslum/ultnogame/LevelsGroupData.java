package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 12/12/2016.
 */

public class LevelsGroupData {


    public static ArrayList<LevelsGroupData> levelsGroupData;

    int textureUnit;
    int textureMap;
    int starsToUnlock;
    int conqueredStars;
    String name;
    int firstLevel;
    int finalLevel;
    boolean isLocked;
    ArrayList<LevelData> levelsData;
    int number;

    public LevelsGroupData(String name, int number, int firstLevel, int finalLevel, int starsToUnlock, int conqueredStars, int textureUnit, int textureMap) {
        this.starsToUnlock = starsToUnlock;
        this.number = number;
        this.conqueredStars = conqueredStars;
        this.name = name;
        this.firstLevel = firstLevel;
        this.finalLevel = finalLevel;
        this.textureUnit = textureUnit;
        this.textureMap = textureMap;
        isLocked = true;
    }

    public void addLevel(String name, int number, int textureUnit, int textureMap){
        if (levelsData == null){
            levelsData = new ArrayList<>();
        }
        levelsData.add(new LevelData(name, number, textureUnit, textureMap));
    }

    class LevelData {
        String name;
        int number;
        int textureUnit;
        int textureMap;

        LevelData(String name, int number, int textureUnit, int textureMap) {
            this.name=name;
            this.number=number;
            this.textureUnit=textureUnit;
            this.textureMap=textureMap;
        }
    }

    public static int getLevelsConqueredStars(int minLevel, int maxLevel){
        int numberOfStars = 0;
        for (int i = 0; i < SaveGame.saveGame.maxNumberOfLevels; i++){
            if (i + 1 >= minLevel && i + 1 <= maxLevel) {
                numberOfStars += SaveGame.saveGame.starsLevels[i];
            }
        }
        return numberOfStars;
    }
}
