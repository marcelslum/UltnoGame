package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.Calendar;

public class SaveGameBuilder {

    private static final String TAG = "SaveGameBuilder";

    public int maxNumberOfLevels;
    public int currentLevelNumber;
    public boolean[] tutorialsViwed;
    public long[] pointsLevels;
    public int[] starsLevels;
    public boolean music;
    public boolean sound;
    public boolean vibration;
    public long date;
    public boolean newGroupsSeen;
    public int lastStars;
    public float currentGroupMenuTranslateX;
    public float currentLevelMenuTranslateX;
    public float currentTutorialMenuTranslateX;
    public long[]pointsSecretLevels;
    public int[]starsSecretLevels;
    public boolean[] secretLevelsUnlocked;
    public boolean[] secretLevelsSeen;
    public int levelsPlayed;


    public SaveGameBuilder(){
    }

    public SaveGameBuilder setLevelsPlayed(int v){
        this.levelsPlayed = v;
        return this;
    }
    
    public SaveGameBuilder setPointsSecretLevels(long [] v){
        this.pointsSecretLevels = v;
        return this;
    }
    
    public SaveGameBuilder setStarsSecretLevels(int [] v){
        this.starsSecretLevels = v;
        return this;
    }
    
    public SaveGameBuilder setSecretLevelsUnlocked(boolean [] v){
        this.secretLevelsUnlocked = v;
        return this;
    }
    
    public SaveGameBuilder setSecretLevelsSeen(boolean [] v){
        this.secretLevelsSeen = v;
        return this;
    }
    
    public SaveGameBuilder setCurrentGroupMenuTranslateX(float v){
        this.currentGroupMenuTranslateX = v;
        return this;
    }
    
    public SaveGameBuilder setCurrentLevelMenuTranslateX(float v){
        this.currentLevelMenuTranslateX = v;
        return this;
    }
    
    public SaveGameBuilder setCurrentTutorialMenuTranslateX(float v){
        this.currentTutorialMenuTranslateX = v;
        return this;
    }

    public SaveGameBuilder setLastStars(int lastStars) {
        this.lastStars = lastStars;
        return this;
    }

    public SaveGameBuilder setNewGroupsSeen(boolean newGroupsSeen) {
        this.newGroupsSeen = newGroupsSeen;
        return this;
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
    
    public SaveGameBuilder setTutorialsViwed(boolean [] tutorialsViwed) {
        this.tutorialsViwed = tutorialsViwed;
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

    public SaveGameBuilder setVibration(boolean v) {
        vibration = v;
        return this;
    }

    public SaveGame build() {
        return new SaveGame(this);
    }


}
