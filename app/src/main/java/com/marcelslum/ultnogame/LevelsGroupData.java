package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 12/12/2016.
 */

public class LevelsGroupData {
    int textureUnit;
    int textureMap;
    int starsToUnlock;
    int conqueredStars;
    String name;
    int firstLevel;
    int finalLevel;
    boolean isLocked;
    ArrayList<LevelData> levelsData;

    public LevelsGroupData(String name, int firstLevel, int finalLevel, int starsToUnlock, int conqueredStars, int textureUnit, int textureMap) {
        this.starsToUnlock = starsToUnlock;
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
}
