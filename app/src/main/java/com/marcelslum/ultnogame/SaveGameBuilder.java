package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.Calendar;

public class SaveGameBuilder {

    private static final String TAG = "SaveGameBuilder";

    public int maxNumberOfLevels;
    public int currentMaxLevel;
    public int currentLevelNumber;
    public int currentDifficulty;
    public int[] difficultyLevels;
    public boolean[] tutorialLevels;
    public long[] pointsLevels;
    public int[] starsLevels;
    public boolean music;
    public boolean sound;
    public long date;


    public SaveGameBuilder(){
    }

    public SaveGameBuilder setMaxNumberOfLevels(int maxNumberOfLevels) {
        this.maxNumberOfLevels = maxNumberOfLevels;
        return this;
    }
    
    //public SaveGameBuilder setCurrentMaxLevel(int currentMaxLevel) {this.currentMaxLevel = currentMaxLevel;return this;}
    //public SaveGameBuilder setCurretDifficulty(int currentDifficulty) {this.currentDifficulty = currentDifficulty;return this;}
    //public SaveGameBuilder setDifficultyLevels(int [] difficultyLevels) {this.difficultyLevels = difficultyLevels;return this;}
    
    public SaveGameBuilder setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
        return this;
    }
    
    public SaveGameBuilder setTutorialLevels(boolean [] tutorialLevels) {
        this.tutorialLevels = tutorialLevels;
        return this;
    }

    public SaveGameBuilder setPointsLevels(long [] pointsLevels) {
        this.pointsLevels = pointsLevels;
        return this;
    }

    public SaveGameBuilder setStarsLevels(int [] starsLevels) {
        this.starsLevels = starsLevels;
        return this;
    }
    
    public SaveGameBuilder setMusic(boolean music) {
        this.music = music;
        return this;
    }
    
    public SaveGameBuilder setSound(boolean sound) {
        this.sound = sound;
        return this;
    }
    
    public SaveGameBuilder setDate(long date){
        this.date = date;
        return this;
    }
    
    public SaveGameBuilder setDate(){
        this.date = Calendar.getInstance().getTimeInMillis();
        return this;
    }

    public SaveGame build() {
        return new SaveGame(this);
    }
}
