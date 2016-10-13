package com.marcelslum.ultnogame;

/**
 * Created by marcel on 13/10/2016.
 */

public class Levels {

    public static final int LEVEL_NOT_COMPLETED = 0;
    public static final int LEVEL_COMPLETE_EASY = 1;
    public static final int LEVEL_COMPLETE_NORMAL = 2;
    public static final int LEVEL_COMPLETE_HARD = 3;
    public static final int LEVEL_COMPLETE_INSANE = 4;

    public static int maxNumberOfLevels = 100;
    public static int currentMaxLevel;
    public static int [] pointsLevels;
    public static int [] difficultyLevels;
    public static int currentLevelNumber;

    public static Level levelObject;

    public static void eraseAllTutorials() {
        if (levelObject != null) {
            for (int i = 0; i < levelObject.tutorials.size(); i++) {
                levelObject.tutorials.get(i).textBox = null;
                levelObject.tutorials.clear();
            }
        }
    }
}
