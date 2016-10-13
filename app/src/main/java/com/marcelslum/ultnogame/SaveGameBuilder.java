package com.marcelslum.ultnogame;

public class SaveGameBuilder {

    public int maxNumberOfLevels;
    public int currentMaxLevel;
    public int currentLevelNumber;
    public int curretDifficulty;
    public int[] difficultyLevels;
    public int[] pointsLevels;
    public boolean music;
    public boolean sound;
    public long date;

    public SaveGameBuilder(){

    }

    public SaveGameBuilder setMaxNumberOfLevels(int maxNumberOfLevels) {
        this.maxNumberOfLevels = maxNumberOfLevels;
        return this;
    }
    
    public SaveGameBuilder setCurrentMaxLevel(int currentMaxLevel) {
        this.currentMaxLevel = currentMaxLevel;
        return this;
    }
    
    public SaveGameBuilder setCurrentLevelNumber(int currentLevelNumber) {
        this.currentLevelNumber = currentLevelNumber;
        return this;
    }
    
    public SaveGameBuilder setCurretDifficulty(int curretDifficulty) {
        this.curretDifficulty = curretDifficulty;
        return this;
    }
    
    public SaveGameBuilder setDifficultyLevels(int [] pointsLevels) {
        this.difficultyLevels = difficultyLevels;
        return this;
    }
    
    public SaveGameBuilder setPointsLevels(int [] pointsLevels) {
        this.pointsLevels = pointsLevels;
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

    public SaveGame build() {
        date = Calendar.getInstance().getTimeInMillis();
        if (maxNumberOfLevels == null|| currentMaxLevel == null || currentLevelNumber == null || 
            curretDifficulty == null || difficultyLevels == null || pointsLevels == null || 
            music == null || sound == null){
            Log.e(TAG, "Não foi possível criar o SaveGame, em razão de algum dos parâmetros não ter sido definido)/
            return null;   
        } else {
            return new SaveGame(this);
        }
    }
}
