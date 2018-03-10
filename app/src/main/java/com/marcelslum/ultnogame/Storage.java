package com.marcelslum.ultnogame;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Storage {
    public static SharedPreferences storage;
    final static String STORAGE_FILE_NAME = "com.marcelslum.ultnogame.storage.";
    final static String TAG = "STORAGE";

    private Storage() {
    }

    public static void setInt(String key, int value){
          SharedPreferences.Editor editor = storage.edit();
          editor.putInt(key, value);
          editor.apply();
    }
    
    public static int getInt(String key){
        return storage.getInt(key, -1);
    }

    public static void setString(String key, String value){
        SharedPreferences.Editor editor = storage.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key){
        return storage.getString(key, "");
    }
    
    public static void setBoolean(String key, boolean value){
          SharedPreferences.Editor editor = storage.edit();
          editor.putBoolean(key, value);
          editor.apply();
    }
    
    public static boolean getBoolean(String key){
        return storage.getBoolean(key, false);
    }
    
    public static boolean contains(String key){
        return storage.contains(key);
    }
    
    public static void init(Context context, String playerId) {
        Log.e(TAG, "playerId "+playerId);

        if (!playerId.equals("temp") && context.getSharedPreferences(STORAGE_FILE_NAME+"temp", 0) != null){
            // todo lidar com a transição do storage temporario
        }

        storage = context.getSharedPreferences(STORAGE_FILE_NAME+playerId, 0);
    }

        /*
        for (int i = 0; i < NUMBER_OF_LEVELS; i++){
            int levelToTest = i + 1;
            if (!Storage.contains("tutorial"+ levelToTest +"visto")) {
                Log.e("Storage", "not contains tutorial visto level " + levelToTest);
                Storage.setBoolean("tutorial" + levelToTest + "visto", false);
            }
            if (!Storage.contains("score"+levelToTest)) {
                Log.e("Storage", "score level " + levelToTest);
                Storage.setInt("score" + levelToTest, 0);
            }
        }

        if (!Storage.contains("actualLevel"))
                Storage.setInt("actualLevel", 1);
        
        if (!Storage.contains("maxLevel"))
                Storage.setInt("maxLevel", 1);

        if (!Storage.contains("volume"))
            Storage.setInt("volume", 100);

        if (!Storage.contains("currentDifficulty"))
            Storage.setInt("currentDifficulty", 0);


        Levels.levelsPoints = new int[NUMBER_OF_LEVELS];
        Levels.difficultyLevels = new int[NUMBER_OF_LEVELS];

        for (int i = 0; i < NUMBER_OF_LEVELS; i++){
            Levels.levelsPoints[i] = getLevelMaxScore(i+1);
            if (Levels.levelsPoints[i] > 0){
                Levels.difficultyLevels[i] = Levels.LEVEL_COMPLETE_EASY;
            } else {
                Levels.difficultyLevels[i] = Levels.LEVEL_NOT_COMPLETED;
            }
        }

    }
    
    public static int getMaxLevel(){
        return getInt("maxLevel");
    }
    
    public static void setMaxLevel(int value){
        setInt("maxLevel", value);
    }

    public static int getDificulty(){
        return getInt("currentDifficulty");
    }

    public static void setDificulty(int value){
        setInt("currentDifficulty", value);
    }
    
    public static int getActualLevel(){
        return  getInt("actualLevel");
    }
    
    public static void setActualLevel(int value){
        setInt("actualLevel", value);
    }
    
    public static int getLevelMaxScore(int levelNumber){
        return  getInt("score"+levelNumber);
    }
    
    public static void setLevelMaxScore(int levelNumber, int value){
        setInt("score"+levelNumber, value);
    }

    public static int getVolume(){
        return  getInt("volume");
    }

    public static void setVolume(int volume){
        setInt("volume", volume);
    }
    
    public static boolean getLevelTutorialSaw(int levelNumber){
        Log.e("Storage", "tutorial saw level "+levelNumber+ ": " +(getBoolean("tutorial"+ levelNumber +"visto")));
        return  getBoolean("tutorial"+ levelNumber +"visto");
    }
    
    public static void setLevelTutorialSaw(int levelNumber, boolean value){
        Storage.setBoolean("tutorial"+ levelNumber +"visto", value);
    }

    */
    
}
