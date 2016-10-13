package com.marcelslum.ultnogame;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marcel on 12/10/2016.
 */

public class SaveGame {
    private static final String TAG = "SaveGame";
    private static final String SERIAL_VERSION = "1.0";
    
    public int maxNumberOfLevels;
    public int currentMaxLevel;
    public int currentLevelNumber;
    public int curretDifficulty;
    public int[] difficultyLevels;
    public int[] pointsLevels;
    public boolean music;
    public boolean sound;
    public long date;
    
    
    public SaveGame(SaveGameBuilder builder){
            maxNumberOfLevels = builder.maxNumberOfLevels;
            currentMaxLevel = builder.currentMaxLevel;
            currentLevelNumber = builder.currentLevelNumber;
            curretDifficulty = builder.curretDifficulty;
            difficultyLevels = builder.difficultyLevels;
            pointsLevels = builder.pointsLevels;
            music = builder.music;
            sound = builder.sound;
            date = builder.date;
    }
    
    
    public static SaveGame mergeReturningHigher(SaveGame sg1, SaveGame sg2){
        int fmaxNumberOfLevels;
        int fcurrentMaxLevel;
        int fcurrentLevelNumber;
        int fcurretDifficulty;
        int[] fdifficultyLevels;
        int[] fpointsLevels;
        boolean fmusic;
        boolean fsound;
        long fdate;
        
        fmaxNumberOfLevels = getHigher(sg1.maxNumberOfLevels, sg2.maxNumberOfLevels);
        fcurrentMaxLevel = getHigher(sg1.currentMaxLevel, sg2.currentMaxLevel);
        fcurrentLevelNumber = getHigher(sg1.currentLevelNumber, sg2.currentLevelNumber);
        fcurretDifficulty = getHigher(sg1.curretDifficulty, sg2.curretDifficulty);
        fdifficultyLevels = getHiger(sg1.difficultyLevels, sg2.difficultyLevels);
        fpointsLevels = getHiger(sg1.pointsLevels, sg2.pointsLevels);
        
        fmusic = sg2.music || sg2.music;
        fmusic = sg2.sound || sg2.sound;
        fdate = getHigher(sg1.date, sg2.date);
        
        return new SaveGameBuilder()
            .
        
        
        

    }
    
    
    public static int[] getHiger(int[] array1, int[] array2){
        int size = getHigher(array1.length(), array2.length());
        int[] result = new int [size];
        int v1;
        int v2;
        for (int i = 0; i < result.length; i++){
            if (array1.length > i){
                v1 = array1[i];
            } else {
                v1 = 0;
            }
            
            if (array2.length > i){
                v2 = array2[i];
            } else {
                v2 = 0;
            }
            
            result[i] = getHigher(v1, v2);
        }
        return result;
    }
    
    
    public static int getHigher(int value1, int value2){
         if (value1 == value2 || value1 > value2){
                return value1;
        } else {
                return value2;
        }
    }
    
    public static long getHigher(long value1, long value2){
         if (value1 == value2 || value1 > value2){
                return value1;
        } else {
                return value2;
        }
    }
    
    
    
    public static void loadFromJson(String json) {
        if (json == null || json.trim().equals("")) return;

        try {
            JSONObject obj = new JSONObject(json);
            String format = obj.getString("version");
            if (!format.equals(SERIAL_VERSION)) {
                throw new RuntimeException("Unexpected loot format " + format);
            }

            Levels.maxNumberOfLevels = obj.getInt("maxNumberOfLevels");
            Levels.currentMaxLevel = obj.getInt("currentMaxLevel");
            // pontuação dos levels
            if (Levels.pointsLevels == null){
                Levels.pointsLevels = new int[Levels.maxNumberOfLevels];
            } else if (Levels.pointsLevels.length  != Levels.maxNumberOfLevels){
                Levels.pointsLevels = new int[Levels.maxNumberOfLevels];
            }
            JSONArray array = obj.getJSONArray("pointsLevels");
            for (int i = 0; i < Levels.maxNumberOfLevels; i++){
                Levels.pointsLevels[i] = array.getInt(i);
            }
            // maxima dificuldade dos levels
            if (Levels.difficultyLevels == null){
                Levels.difficultyLevels = new int[Levels.maxNumberOfLevels];
            } else if (Levels.difficultyLevels.length  != Levels.maxNumberOfLevels){
                Levels.difficultyLevels = new int[Levels.maxNumberOfLevels];
            }
            array = obj.getJSONArray("difficultyLevels");
            for (int i = 0; i < Levels.maxNumberOfLevels; i++){
                Levels.difficultyLevels[i] = array.getInt(i);
            }

            Levels.currentLevelNumber = obj.getInt("currentLevelNumber");
            Game.musicOn = obj.getBoolean("musicOn");
            Game.volume = obj.getInt("volume");
            Game.currentDifficulty = obj.getInt("currentDifficulty");
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            Log.e(TAG, "Save data has a syntax error: " + json, ex);
            // Initializing with empty stars if the game file is corrupt.
            // NOTE: In your game, you want to try recovering from the snapshot payload.
            // TODO RECUPERAR DADOS DE SAHRED PREFERENCES
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Save data has an invalid number in it: " + json, ex);
        }
    }

    public static void saveLocal(){
        Storage.setString("saveGame", getString());
    }

    public static String getLocalSave(){
        return Storage.getString("saveGame");
    }

    public static String getString() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("version", SERIAL_VERSION);
            obj.put("maxNumberOfLevels", Levels.maxNumberOfLevels);
            obj.put("currentMaxLevel", Levels.currentMaxLevel);
            obj.put("pointsLevels", new JSONArray(Levels.pointsLevels));
            obj.put("difficultyLevels", new JSONArray(Levels.difficultyLevels));
            obj.put("currentLevelNumber", Levels.currentLevelNumber);
            obj.put("musicOn", Game.musicOn);
            obj.put("volume", Game.volume);
            obj.put("currentDifficulty", Game.currentDifficulty);
            return obj.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error converting save data to JSON.", ex);
        }
    }

    /*
    public SaveGame unionWith(SaveGame other) {
    // TODO - para uso em caso de atualizações, etc
        SaveGame result = clone();
        for (String levelName : other.mLevelStars.keySet()) {
            int existingStars = result.getLevelStars(levelName);
            int newStars = other.getLevelStars(levelName);

            // only overwrite if number of stars is greater
            if (newStars > existingStars) {
                result.setLevelStars(levelName, newStars);
            }

            // note that this code doesn't preserve mappings from a level to the value 0,
            // but that is not a problem because, in our semantics, the absence of a mapping
            // is equivalent to mapping to 0 stars.
        }

        return result;
    }
    */
}
