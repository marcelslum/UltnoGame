package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.Calendar;

public class SaveGameBuilder {

    private static final String TAG = "SaveGameBuilder";

    public int[] levelsPoints;
    public int[] levelsStars;
    public boolean[] levelsUnlocked;
    public boolean[] levelsSeen;
    public boolean[] groupsSeen;
    public boolean[] tutorialsSeen;
    public long date;
    public int currentLevelNumber;
    public int currentGroupNumber;
    public boolean music;
    public boolean sound;
    public boolean vibration;
    public float currentGroupMenuTranslateX;
    public float currentLevelMenuTranslateX;
    public float currentTutorialMenuTranslateX;
    public int lastStars;
    public int levelsPlayed;
    public int googleOption;
    public int ballVelocity;
    public boolean orientationInverted;
    public boolean saveMenuSeen;
    public int lastLevelPlayed;
    public long[] stats;


    public SaveGameBuilder(){
    }

    public SaveGameBuilder setLevelsPlayed(int v){
        this.levelsPlayed = v;
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

    public SaveGameBuilder setGroupsSeen(boolean [] groupsSeen) {
        this.groupsSeen = groupsSeen;
        return this;
    }


    public SaveGameBuilder setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
        return this;
    }

    public SaveGameBuilder setCurrentGroupNumber(int currentGroupNumber) {
        this.currentGroupNumber = currentGroupNumber;
        return this;
    }
    
    public SaveGameBuilder setTutorialsSeen(boolean [] tutorialsSeen) {
        this.tutorialsSeen = tutorialsSeen;
        return this;
    }

    public SaveGameBuilder setLevelStars(int [] levelStars) {
        this.levelsStars = levelStars;
        return this;
    }

    public SaveGameBuilder setLevelsPoints(int [] levelsPoints) {
        this.levelsPoints = levelsPoints;
        return this;
    }

    public SaveGameBuilder setLevelsStars(int [] levelsStars) {
        this.levelsStars = levelsStars;
        return this;
    }

    public SaveGameBuilder setLevelsUnlocked(boolean [] levelsUnlocked) {
        this.levelsUnlocked = levelsUnlocked;
        return this;
    }

    public SaveGameBuilder setLevelsSeen(boolean [] levelsSeen) {
        this.levelsSeen = levelsSeen;
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

    public SaveGameBuilder setGoogleOption(int v) {
        googleOption = v;
        return this;
    }

    public SaveGameBuilder setBallVelocity(int v) {
        ballVelocity = v;
        return this;
    }

    public SaveGameBuilder setOrientationInverted(boolean orientationInverted) {
        this.orientationInverted = orientationInverted;
        return this;
    }

    public SaveGameBuilder setSaveMenuSeen(boolean saveMenuSeen) {
        this.saveMenuSeen = saveMenuSeen;
        return this;
    }

    public SaveGameBuilder setLastLevelPlayed(int lastLevelPlayed) {
        this.lastLevelPlayed = lastLevelPlayed;
        return this;
    }

    public SaveGameBuilder setStats(long [] stats) {
        this.stats = stats;
        return this;
    }

    public SaveGame build() {
        return new SaveGame(this);
    }


}
