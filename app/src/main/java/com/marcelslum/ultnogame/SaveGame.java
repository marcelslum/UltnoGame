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
    public static final String SHARED_PREFERENCES_KEY_NAME = "saveGame";

    public static SaveGame saveGame;
    public static long lastSave;
    public static final int MIN_TIME_BEFORE_RESAVE = 2000;

    public int maxNumberOfLevels;
    public int currentLevelNumber;
    public long[] pointsLevels;
    public int[] starsLevels;
    public boolean[] tutorialLevels;
    public boolean music;
    public boolean sound;
    public long date;
    public int lastStars;
    public boolean newGroupsSeen;


    public static boolean loaded = false;

    public SaveGame(SaveGameBuilder builder) {
        maxNumberOfLevels = builder.maxNumberOfLevels;
        currentLevelNumber = builder.currentLevelNumber;
        pointsLevels = builder.pointsLevels;
        starsLevels = builder.starsLevels;
        tutorialLevels = builder.tutorialLevels;

        music = builder.music;
        sound = builder.sound;
        date = builder.date;
        lastStars = builder.lastStars;
        newGroupsSeen = builder.newGroupsSeen;
    }

    public static void load() {
        loaded = false;
        new LoadFromSnapshotAsyncTask().execute();
    }

    public static void saveOnlyLocally(){
        if (SaveGame.saveGame == null){
            return;
        }

        if (Utils.getTime() - lastSave < MIN_TIME_BEFORE_RESAVE) {
            return;
        }
        lastSave = Utils.getTime();

        String saveString = getStringFromSaveGame(saveGame);
        Log.e(TAG, "Salvando localmente "+saveString);
        Storage.setString(SHARED_PREFERENCES_KEY_NAME, saveString);
    }

    public static void save(){

        if (SaveGame.saveGame == null){
            return;
        }

        if (Utils.getTime() - lastSave < MIN_TIME_BEFORE_RESAVE) {
            return;
        }
        lastSave = Utils.getTime();

        String saveString = getStringFromSaveGame(saveGame);
        Log.e(TAG, "Salvando localmente "+saveString);
        Storage.setString(SHARED_PREFERENCES_KEY_NAME, saveString);
        AsyncTasks.saveSnapshot = new SaveSnapshotAsyncTask().execute(saveString);
    }

    public static void onLoadFromSnapshot(String data) {

        Log.e(TAG, "Snapshot aberto:  " + data);

        String dataLocal;
        if (Storage.contains(SHARED_PREFERENCES_KEY_NAME)){
            Log.e(TAG, "Pegando dados salvos localmente aberto");
            dataLocal = getStringFromLocal();
        } else {
            Log.e(TAG, "Não existem dados salvos localmente");
            dataLocal = null;
        }

        if (dataLocal == null){
            Log.e(TAG, "Criando saveGame com dados do snapshot");
            saveGame = getSaveGameFromJson(data);
        } else if (!dataLocal.equals(data)) {
            Log.e(TAG, "Criando saveGame unindo os dois dados");
            Log.e(TAG, "dado1 snaps "+data);
            Log.e(TAG, "dado2 local "+dataLocal);
            saveGame = mergeReturningHigher(getSaveGameFromJson(data), getSaveGameFromJson(dataLocal));
            Log.e(TAG, "resultado   "+getStringFromSaveGame(saveGame));
        } else {
            Log.e(TAG, "Criando saveGame com dados do snapshot, pois ele é igual aos dados locais");
            saveGame = getSaveGameFromJson(data);
        }
        loaded = true;
    }

    public static void onFailLoadFromSnapshot() {
        Log.e(TAG, "Não carregou Snapshot");
        if (Storage.contains(SHARED_PREFERENCES_KEY_NAME)) {
            Log.e(TAG, "Carregando apenas localmente");
            saveGame = getSaveGameFromJson(getStringFromLocal());
        } else {
            Log.e(TAG, "Não existe ainda nenhum dado, criando novo");
            long[] _pointsLevels = new long[Level.maxNumberOfLevels];
            int[] _starsLevels = new int[Level.maxNumberOfLevels];
            //int[] _difficultyLevels = new int[Level.maxNumberOfLevels];
            boolean[] _tutorialLevels = new boolean[Level.maxNumberOfLevels];

            saveGame = new SaveGameBuilder()
                    .setMaxNumberOfLevels(Level.maxNumberOfLevels)
                    //.setCurrentMaxLevel(1)
                    .setCurrentLevelNumber(1)
                    //.setCurretDifficulty(Game.DIFFICULTY_EASY)
                    //.setDifficultyLevels(_difficultyLevels)
                    .setPointsLevels(_pointsLevels)
                    .setStarsLevels(_starsLevels)
                    .setTutorialLevels(_tutorialLevels)
                    .setNewGroupsSeen(true)
                    .setLastStars(0)
                    .setMusic(true)
                    .setSound(true)
                    .setDate()
                    .build();
        }
        loaded = true;
    }

    public static SaveGame mergeReturningHigher(SaveGame sg1, SaveGame sgLocal) {
        int fmaxNumberOfLevels;
        //int fcurrentMaxLevel;
        int fcurrentLevelNumber;
        //int fcurretDifficulty;
        //int[] fdifficultyLevels;
        long[] fpointsLevels;
        int[] fstarsLevels;
        boolean[] ftutorialLevels;
        boolean fmusic;
        boolean fsound;
        long fdate;
        boolean fnewGroupsSeen;
        int flastStars;

        fmaxNumberOfLevels = getHigher(sg1.maxNumberOfLevels, sgLocal.maxNumberOfLevels);
        //fcurrentMaxLevel = getHigher(sg1.currentMaxLevel, sgLocal.currentMaxLevel);
        fcurrentLevelNumber = sgLocal.currentLevelNumber;
        //fcurretDifficulty = sgLocal.currentDifficulty;
        //fdifficultyLevels = getHigher(sg1.difficultyLevels, sgLocal.difficultyLevels);
        ftutorialLevels = getHigher(sg1.tutorialLevels, sgLocal.tutorialLevels);
        fpointsLevels = getHigher(sg1.pointsLevels, sgLocal.pointsLevels);
        fstarsLevels = getHigher(sg1.starsLevels, sgLocal.starsLevels);
        flastStars = getHigher(sg1.lastStars, sgLocal.lastStars);
        fnewGroupsSeen = sg1.newGroupsSeen || sgLocal.newGroupsSeen;

        fmusic = sgLocal.music;
        fsound = sgLocal.sound;
        fdate = getHigher(sg1.date, sgLocal.date);

        return new SaveGameBuilder()
                .setMaxNumberOfLevels(fmaxNumberOfLevels)
                .setCurrentLevelNumber(fcurrentLevelNumber)
                .setTutorialLevels(ftutorialLevels)
                .setPointsLevels(fpointsLevels)
                .setStarsLevels(fstarsLevels)
                .setMusic(fmusic)
                .setSound(fsound)
                .setDate(fdate)
                .setLastStars(flastStars)
                .setNewGroupsSeen(fnewGroupsSeen)
                .build();
    }


    public static boolean[] getHigher(boolean[] array1, boolean[] array2) {
        int size = getHigher(array1.length, array2.length);
        boolean[] result = new boolean[size];
        boolean v1;
        boolean v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = false;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = false;
            }

            result[i] = v1 || v2;
        }
        return result;
    }

    public static int[] getHigher(int[] array1, int[] array2) {
        int size = getHigher(array1.length, array2.length);
        int[] result = new int[size];
        int v1;
        int v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = 0;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = 0;
            }

            result[i] = getHigher(v1, v2);
        }
        return result;
    }

    public static long[] getHigher(long[] array1, long[] array2) {
        int size = getHigher(array1.length, array2.length);
        long[] result = new long[size];
        long v1;
        long v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = 0;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = 0;
            }

            result[i] = getHigher(v1, v2);
        }
        return result;
    }


    public static int getHigher(int value1, int value2) {
        if (value1 == value2 || value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }

    public static long getHigher(long value1, long value2) {
        if (value1 == value2 || value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }


    public static SaveGame getSaveGameFromJson(String json) {
        if (json == null || json.trim().equals("")) return null;
        SaveGameBuilder saveGameBuilder = new SaveGameBuilder();
        try {

            JSONObject obj = new JSONObject(json);
            String format = obj.getString("version");
            if (!format.equals(SERIAL_VERSION)) {
                throw new RuntimeException("Unexpected loot format " + format);
            }

            saveGameBuilder.setMaxNumberOfLevels(obj.getInt("maxNumberOfLevels"));
            saveGameBuilder.setCurrentLevelNumber(obj.getInt("currentLevelNumber"));

            // pontuação dos levels
            long[] pointsLevels = new long[saveGameBuilder.maxNumberOfLevels];
            JSONArray array = obj.getJSONArray("pointsLevels");
            for (int i = 0; i < pointsLevels.length; i++) {
                pointsLevels[i] = array.getLong(i);
            }
            saveGameBuilder.setPointsLevels(pointsLevels);

            // stars dos levels
            int[] starsLevels = new int[saveGameBuilder.maxNumberOfLevels];

            try {
                JSONArray arrayStars = obj.getJSONArray("starsLevels");
                for (int i = 0; i < starsLevels.length; i++) {
                    starsLevels[i] = arrayStars.getInt(i);
                }
            } catch(JSONException e)
            {
                for (int i = 0; i < starsLevels.length; i++) {
                    starsLevels[i] = 0;
                }
            }

            try {
                saveGameBuilder.setLastStars(obj.getInt("lastStars"));
            } catch(JSONException e) {
                saveGameBuilder.setLastStars(0);
            }

            try {
                saveGameBuilder.setNewGroupsSeen(obj.getBoolean("newGroupsSeen"));
            } catch(JSONException e) {
                saveGameBuilder.setNewGroupsSeen(true);
            }


            saveGameBuilder.setStarsLevels(starsLevels);

            boolean[] tutorialLevels = new boolean[saveGameBuilder.maxNumberOfLevels];
            array = obj.getJSONArray("tutorialLevels");
            for (int i = 0; i < tutorialLevels.length; i++) {
                tutorialLevels[i] = array.getBoolean(i);
            }
            saveGameBuilder.setTutorialLevels(tutorialLevels);

            saveGameBuilder.setMusic(obj.getBoolean("music"));
            saveGameBuilder.setSound(obj.getBoolean("sound"));

            saveGameBuilder.setDate(obj.getLong("date"));

            return saveGameBuilder.build();

        } catch (JSONException ex) {
            ex.printStackTrace();
            Log.e(TAG, "Save data has a syntax error: " + json, ex);
            // Initializing with empty stars if the game file is corrupt.
            // NOTE: In your game, you want to try recovering from the snapshot payload.
            // TODO RECUPERAR DADOS DE SAHRED PREFERENCES
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Save data has an invalid number in it: " + json, ex);
        }

        return null;
    }

    public static String getStringFromLocal() {
        return Storage.getString(SHARED_PREFERENCES_KEY_NAME);
    }

    public static String getStringFromSaveGame(SaveGame saveGame) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("version", SERIAL_VERSION);
            obj.put("maxNumberOfLevels", saveGame.maxNumberOfLevels);
            obj.put("currentLevelNumber", saveGame.currentLevelNumber);
            obj.put("pointsLevels", new JSONArray(saveGame.pointsLevels));
            obj.put("starsLevels", new JSONArray(saveGame.starsLevels));
            obj.put("tutorialLevels", new JSONArray(saveGame.tutorialLevels));
            obj.put("lastStars", saveGame.lastStars);
            obj.put("newGroupsSeen", saveGame.newGroupsSeen);
            obj.put("music", saveGame.music);
            obj.put("sound", saveGame.sound);
            obj.put("date", saveGame.date);
            return obj.toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error converting save data to JSON.", ex);
        }
    }
}