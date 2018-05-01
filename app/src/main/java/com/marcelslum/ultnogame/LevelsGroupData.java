package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 12/12/2016.
 */

public class LevelsGroupData {


    public static ArrayList<LevelsGroupData> levelsGroupData;

    int textureUnit;
    TextureData textureData;
    int starsToUnlock;
    int conqueredStars;
    String name;
    public int firstLevel;
    public int finalLevel;
    boolean isLocked;
    ArrayList<LevelData> levelsData;
    int number;

    public LevelsGroupData(String name, int number, int firstLevel, int finalLevel, int starsToUnlock, int conqueredStars, int textureUnit, TextureData textureData) {
        this.starsToUnlock = starsToUnlock;
        this.number = number;
        this.conqueredStars = conqueredStars;
        this.name = name;
        this.firstLevel = firstLevel;
        this.finalLevel = finalLevel;
        this.textureUnit = textureUnit;
        this.textureData = textureData;
        isLocked = true;
    }

    public void addLevel(String name, int number, int textureUnit, TextureData textureData){
        if (levelsData == null){
            levelsData = new ArrayList<>();
        }
        levelsData.add(new LevelData(name, number, textureUnit, textureData));
    }

    class LevelData {
        String name;
        int number;
        int textureUnit;
        TextureData textureData;

        LevelData(String name, int number, int textureUnit, TextureData textureData) {
            this.name = name;
            this.number = number;
            this.textureUnit = textureUnit;
            this.textureData = textureData;
        }
    }

    public static int getLevelsConqueredStars(int minLevel, int maxLevel){
        int numberOfStars = 0;
        for (int i = 0; i < Level.NUMBER_OF_LEVELS; i++){
            if (i + 1 >= minLevel && i + 1 <= maxLevel) {
                numberOfStars += SaveGame.saveGame.levelsStars[i];
            }
        }
        return numberOfStars;
    }
}
