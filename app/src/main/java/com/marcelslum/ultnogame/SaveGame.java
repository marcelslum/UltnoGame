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
    public static final SHARED_PREFERENCES_FILE_NAME = "saveGame";
    
    public int maxNumberOfLevels;
    public int currentMaxLevel;
    public int currentLevelNumber;
    public int curretDifficulty;
    public int[] difficultyLevels;
    public int[] pointsLevels;
    public boolean music;
    public boolean sound;
    public long date;

    public loaded = false;
    
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
    
    public static load(){
        loaded = false;
        new LoadFromSnapshotAsyncTask("").execute();
    }
    
    public static onLoadFromSnapshot(String data){
        String data2 = loadStringFromLocal();
        
        if (!data2.equal(data)){
            saveGame = mergeReturningHigher(getSaveGameFromJson(data), getSaveGameFromJson(data2))          
        } else {
            saveGame = getSaveGameFromJson(data);
        }
        loaded = true;
    }
    
    public static onFailLoadFromSnapshot(String data){
        saveGame = getSaveGameFromJson(loadStringFromLocal());
        loaded = true;
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
            .setMaxNumberOfLevels(fmaxNumberOfLevels)
            .setCurrentMaxLevel(fcurrentMaxLevel)
            .setCurrentLevelNumber(fcurrentLevelNumber)
            .setCurretDifficulty(fcurretDifficulty)
            .setDifficultyLevels(fdifficultyLevels)
            .setPointsLevels(fpointsLevels)
            .setMusic(fmusic)
            .setSound(fsound)
            .setDate(fdate)
            .build();
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
    
    
    
    public static void getSaveGameFromJson(String json) {
        if (json == null || json.trim().equals("")) return;
        SaveGameBuilder saveGameBuilder = new SaveGameBuilder();
        try {
            
            JSONObject obj = new JSONObject(json);
            String format = obj.getString("version");
            if (!format.equals(SERIAL_VERSION)) {
                throw new RuntimeException("Unexpected loot format " + format);
            }

            saveGameBuilder.setMaxNumberOfLevels(obj.getInt("maxNumberOfLevels"));
            saveGameBuilder.setCurrentMaxLevel(obj.getInt("currentMaxLevel"));
            saveGameBuilder.setCurrentLevelNumber(obj.getInt("currentLevelNumber"));
            saveGameBuilder.setCurretDifficulty(obj.getInt("curretDifficulty"));
            
            // pontuação dos levels
            int [] pointsLevels = new int[saveGameBuilder.maxNumberOfLevels];
            JSONArray array = obj.getJSONArray("pointsLevels");
            for (int i = 0; i < pointsLevels.length; i++){
                pointsLevels[i] = array.getInt(i);
            }
            saveGameBuilder.setPointsLevels(pointsLevels);
            
            // maxima dificuldade dos levels
            int [] difficultyLevels = new int[saveGameBuilder.maxNumberOfLevels];
            array = obj.getJSONArray("difficultyLevels");
            for (int i = 0; i < difficultyLevels.length; i++){
                difficultyLevels[i] = array.getInt(i);
            }
            saveGameBuilder.setDifficultyLevels(difficultyLevels);
            
            saveGameBuilder.setMusic(obj.getBoolean("music"));
            saveGameBuilder.setSound(obj.getBoolean("sound"));
            
            saveGameBuilder.setDate(obj.getLong("date"));
            
            return saveGameBuilder.build();
            
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

    public static void saveStringOnLocal(){
        Storage.setString(SHARED_PREFERENCES_FILE_NAME, getStringFromSaveGame(saveGame));
    }
 
    public static String getStringFromLocal(){
        return Storage.getString(SHARED_PREFERENCES_FILE_NAME);
    }

    public static String getStringFromSaveGame(SaveGame saveGame) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("version", SERIAL_VERSION);
            obj.put("maxNumberOfLevels", saveGame.maxNumberOfLevels);
            obj.put("currentMaxLevel", saveGame.currentMaxLevel);
            obj.put("currentLevelNumber", saveGame.currentLevelNumber);
            obj.put("currentDifficulty", saveGame.currentDifficulty);
            obj.put("pointsLevels", new JSONArray(saveGame.pointsLevels));
            obj.put("difficultyLevels", new JSONArray(saveGame.difficultyLevels));
            obj.put("musicOn", saveGame.music);
            obj.put("volume", saveGame.sound);
            obj.put("date", saveGame.date);
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
