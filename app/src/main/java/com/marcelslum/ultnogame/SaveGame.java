package com.marcelslum.ultnogame;

import android.os.AsyncTask;
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

    public int[] levelsPoints;
    public int[] levelsStars;
    public boolean[] levelsUnlocked;
    public boolean[] levelsSeen;
    public boolean[] tutorialsSeen;
    public boolean[] groupsSeen;
    public long date;
    public int currentLevelNumber;
    public int currentGroupNumber;
    public boolean music;
    public boolean sound;
    public boolean vibration;
    public int googleOption;
    public int ballVelocity;
    public float currentGroupMenuTranslateX;
    public float currentLevelMenuTranslateX;
    public float currentTutorialMenuTranslateX;
    public int lastStars;
    public int levelsPlayed;
    public boolean orientationInverted;

    public static boolean loaded = false;

    public SaveGame(SaveGameBuilder builder) {
        levelsPoints = builder.levelsPoints;
        levelsStars = builder.levelsStars;
        levelsUnlocked = builder.levelsUnlocked;
        levelsSeen = builder.levelsSeen;
        tutorialsSeen = builder.tutorialsSeen;
        groupsSeen = builder.groupsSeen;
        date = builder.date;
        currentLevelNumber = builder.currentLevelNumber;
        currentGroupNumber = builder.currentGroupNumber;
        music = builder.music;
        sound = builder.sound;
        vibration = builder.vibration;
        googleOption = builder.googleOption;
        ballVelocity = builder.ballVelocity;
        currentGroupMenuTranslateX = builder.currentGroupMenuTranslateX;
        currentLevelMenuTranslateX = builder.currentLevelMenuTranslateX;
        currentTutorialMenuTranslateX = builder.currentTutorialMenuTranslateX;
        lastStars = builder.lastStars;
        levelsPlayed = builder.levelsPlayed;
        orientationInverted = builder.orientationInverted;
    }

    public static void load() {
        loaded = false;

        if (!DataBaseSaveDataHelper.getInstance(Game.mainActivity).isNew()
                || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(1) != 0
                || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(2) != 0
                || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(3) != 0
                || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(4) != 0
                ){
            //Log.e(TAG, "Carregando Save Game");

            SaveGame saveGame1 = DataBaseSaveDataHelper.getInstance(Game.mainActivity).getSaveGame();

            SaveGame saveGame2 = getSaveGameFromJson(Storage.getString(Storage.STORAGE_SAVE_NAME));

            if (Game.forDebugClearAllLevelPoints) {
                for (int i = 0; i < 100; i++) {
                    saveGame1.levelsPoints[i] = 0;
                }
                for (int i = 0; i < 100; i++) {
                    saveGame2.levelsPoints[i] = 0;
                }
            }

            if (saveGame1 == null){
                saveGame = saveGame2;
            } else if (saveGame2 == null){
                saveGame = saveGame1;
            } else {
                saveGame = mergeSaveGames(saveGame1, saveGame2);
            }




            //log(saveGame);
        } else {
            Log.e(TAG, "Não existe ainda nenhum Save Game, criando novo");
            int[] _levelsPoints = new int[Level.NUMBER_OF_LEVELS];
            int[] _levelsStars = new int[Level.NUMBER_OF_LEVELS];
            boolean[] _levelsUnlocked = new boolean[Level.NUMBER_OF_LEVELS];
            boolean[] _levelsSeen = new boolean[Level.NUMBER_OF_LEVELS];
            boolean[] _tutorialsSeen = new boolean[Tutorial.NUMBER_OF_TUTORIALS];
            boolean[] _groupsSeen = new boolean[Level.NUMBER_OF_GROUPS];
            _groupsSeen[0] = true;

            saveGame = new SaveGameBuilder()
                    .setLevelsPoints(_levelsPoints)
                    .setLevelsStars(_levelsStars)
                    .setLevelsUnlocked(_levelsUnlocked)
                    .setLevelsSeen(_levelsSeen)
                    .setTutorialsSeen(_tutorialsSeen)
                    .setGroupsSeen(_groupsSeen)
                    .setDate()
                    .setMusic(true)
                    .setSound(true)
                    .setVibration(true)
                    .setGoogleOption(-1)
                    .setBallVelocity(100)
                    .setCurrentLevelNumber(1)
                    .setCurrentGroupNumber(1)
                    .setCurrentGroupMenuTranslateX(0)
                    .setCurrentLevelMenuTranslateX(0)
                    .setCurrentTutorialMenuTranslateX(0)
                    .setLastStars(0)
                    .setLevelsPlayed(0)
                    .setOrientationInverted(false)
                    .build();
        }

        for (int i = 0; i < 99; i++) {
            setLevelStars(i + 1, 0);
        }

        //Log.e(TAG, "GoogleOption "+ SaveGame.saveGame.googleOption);
        loaded = true;
    }

    public void save(){

        if (Utils.getTimeMilliPrecision() - lastSave < MIN_TIME_BEFORE_RESAVE) {
            //Log.e(TAG, "Jogo não foi salvo porque foi salvo recentemente.");
            return;
        }
        //Log.e(TAG, "save()");
        AsyncTasks.save = new SaveAsyncTask().execute();
    }

    private class SaveAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... i) {

            if (SaveGame.saveGame == null){
                //Log.e(TAG, "Erro ao salvar o jogo. Objeto SaveGame nulo.");
                return -1;
            }

            lastSave = Utils.getTimeMilliPrecision();


            try {
                DataBaseSaveDataHelper.getInstance(Game.mainActivity).saveDataFromSaveGame(saveGame);
            } catch (Exception e) {
                //Log.e(TAG, "Erro ao salvar no banco.");
            }



            Storage.setString(Storage.STORAGE_SAVE_NAME, getStringFromSaveGame(saveGame));

            return 1;
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(Integer result) {
            //Log.e(TAG, "Jogo salvo");
        }
    }



    public static void log(SaveGame s){
        //Log.e(TAG, "Log save game >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //Log.e(TAG, "pontos --------------- ");

        String log= " ";
        for (int i = 0; i < s.levelsPoints.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.levelsPoints[i];
        }
        //Log.e(TAG, "level "+ log);

        log= " ";
        //Log.e(TAG, "estrelas --------------- ");
        for (int i = 0; i < s.levelsStars.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.levelsStars[i];
        }
        //Log.e(TAG, "level "+ log);

        log= " ";
        //Log.e(TAG, "desbloqueados --------------- ");
        for (int i = 0; i < s.levelsUnlocked.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.levelsUnlocked[i];
        }
        //Log.e(TAG, "level "+ log);

        log= " ";
        //Log.e(TAG, "vistos --------------- ");
        for (int i = 0; i < s.levelsSeen.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.levelsSeen[i];
        }
        //Log.e(TAG, "level "+ log);

        log= " ";
        //Log.e(TAG, "tutoriais vistos --------------- ");
        for (int i = 0; i < s.tutorialsSeen.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.tutorialsSeen[i];
        }
        //Log.e(TAG, "level "+ log);

        log= " ";
        //Log.e(TAG, "grupos vistos --------------- ");
        for (int i = 0; i < s.groupsSeen.length; i++){
            log = log + " " + "level "+ (i + 1) +" -> " + s.groupsSeen[i];
        }
        //Log.e(TAG, "level "+ log);
              
        //Log.e(TAG, "date -> " + s.date)   ;
        //Log.e(TAG, "currentLevelNumber -> " + s.currentLevelNumber);
        //Log.e(TAG, "currentGroupNumber -> " + s.currentGroupNumber);
        //Log.e(TAG, "music  -> " + s.music );
        //Log.e(TAG, "sound  -> " + s.sound );
        //Log.e(TAG, "vibration  -> " + s.vibration );
        //Log.e(TAG, "googleOption  -> " + s.googleOption );
        //Log.e(TAG, "ballVelocity  -> " + s.ballVelocity );
        //Log.e(TAG, "currentGroupMenuTranslateX -> " + s.currentGroupMenuTranslateX);
        //Log.e(TAG, "currentLevelMenuTranslateX -> " + s.currentLevelMenuTranslateX);
        //Log.e(TAG, "currentTutorialMenuTranslateX -> " + s.currentTutorialMenuTranslateX);
        //Log.e(TAG, "lastStars -> " + s.lastStars);
        //Log.e(TAG, "levelsPlayed -> " + s.levelsPlayed);
        //Log.e(TAG, "orientationInverted -> " + s.orientationInverted);

    }

    public static SaveGame mergeSaveGames(SaveGame saveGame1, SaveGame saveGame2) {
        int[] flevelsPoints;
        int[] flevelsStars;
        boolean[] flevelsUnlocked;
        boolean[] flevelsSeen;
        boolean[] ftutorialsSeen;
        boolean[] fgroupsSeen;
        long fdate;
        int fcurrentLevelNumber;
        int fcurrentGroupNumber;
        boolean fmusic;
        boolean fsound;
        boolean fvibration;
        int fgoogleOption;
        int fBallVelocity;
        float fcurrentGroupMenuTranslateX;
        float fcurrentLevelMenuTranslateX;
        float fcurrentTutorialMenuTranslateX;
        int flastStars;
        boolean fnewGroupsSeen;
        int flevelsPlayed;
        boolean fOrientationInverted;

        flevelsPoints = Utils.getHigher(saveGame1.levelsPoints, saveGame2.levelsPoints);
        flevelsStars = Utils.getHigher(saveGame1.levelsStars, saveGame2.levelsStars);
        flevelsUnlocked = Utils.getHigher(saveGame1.levelsUnlocked, saveGame2.levelsUnlocked);
        flevelsSeen = Utils.getHigher(saveGame1.levelsSeen, saveGame2.levelsSeen);
        ftutorialsSeen = Utils.getHigher(saveGame1.tutorialsSeen, saveGame2.tutorialsSeen);
        fgroupsSeen = Utils.getHigher(saveGame1.groupsSeen, saveGame2.groupsSeen);
        fdate = Utils.getHigher(saveGame1.date, saveGame2.date);
        fcurrentLevelNumber = Utils.getHigher(saveGame1.currentLevelNumber, saveGame2.currentLevelNumber);
        fcurrentGroupNumber = Utils.getHigher(saveGame1.currentGroupNumber, saveGame2.currentGroupNumber);
        fmusic = Utils.getHigher(saveGame1.music, saveGame2.music);
        fsound = Utils.getHigher(saveGame1.sound, saveGame2.sound);
        fvibration = Utils.getHigher(saveGame1.vibration, saveGame2.vibration);
        fgoogleOption = Utils.getHigher(saveGame1.googleOption, saveGame2.googleOption);
        fOrientationInverted = Utils.getHigher(saveGame1.orientationInverted, saveGame2.orientationInverted);


        //Log.e(TAG, "merge google option saveGame1.googleOption "+ saveGame1.googleOption);
        //Log.e(TAG, "merge google option saveGame2.googleOption "+ saveGame2.googleOption);
        //Log.e(TAG, "merge google option fgoogleOption "+ fgoogleOption);

        fBallVelocity = Utils.getHigher(saveGame1.ballVelocity, saveGame2.ballVelocity);
        fcurrentGroupMenuTranslateX = Utils.getHigher(saveGame1.currentGroupMenuTranslateX, saveGame2.currentGroupMenuTranslateX);
        fcurrentLevelMenuTranslateX = Utils.getHigher(saveGame1.currentLevelMenuTranslateX, saveGame2.currentLevelMenuTranslateX);
        fcurrentTutorialMenuTranslateX = Utils.getHigher(saveGame1.currentTutorialMenuTranslateX, saveGame2.currentTutorialMenuTranslateX);
        flastStars = Utils.getHigher(saveGame1.lastStars, saveGame2.lastStars);
        flevelsPlayed = Utils.getHigher(saveGame1.levelsPlayed, saveGame2.levelsPlayed);

        return new SaveGameBuilder()
                .setLevelsPoints(flevelsPoints)
                .setLevelsStars(flevelsStars)
                .setLevelsUnlocked(flevelsUnlocked)
                .setLevelsSeen(flevelsSeen)
                .setTutorialsSeen(ftutorialsSeen)
                .setGroupsSeen(fgroupsSeen)
                .setGroupsSeen(fgroupsSeen)
                .setDate(fdate)
                .setMusic(fmusic)
                .setSound(fsound)
                .setVibration(fvibration)
                .setBallVelocity(fBallVelocity)
                .setGoogleOption(fgoogleOption)
                .setCurrentLevelNumber(fcurrentLevelNumber)
                .setCurrentGroupNumber(fcurrentGroupNumber)
                .setCurrentGroupMenuTranslateX(fcurrentGroupMenuTranslateX)
                .setCurrentLevelMenuTranslateX(fcurrentLevelMenuTranslateX)
                .setCurrentTutorialMenuTranslateX(fcurrentTutorialMenuTranslateX)
                .setLastStars(flastStars)
                .setLevelsPlayed(flevelsPlayed)
                .setOrientationInverted(fOrientationInverted)
                .build();
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

            int[] flevelsPoints = new int[Level.NUMBER_OF_LEVELS];
            try {
                JSONArray array = obj.getJSONArray("levelsPoints");
                for (int i = 0; i < flevelsPoints.length; i++) {
                    flevelsPoints[i] = array.getInt(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < flevelsPoints.length; i++) {
                    flevelsPoints[i] = 0;
                }
            }
            saveGameBuilder.setLevelsPoints(flevelsPoints);

            int[] flevelsStars = new int[Level.NUMBER_OF_LEVELS];
            try {
                JSONArray array = obj.getJSONArray("levelsStars");
                for (int i = 0; i < flevelsStars.length; i++) {
                    flevelsStars[i] = array.getInt(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < flevelsStars.length; i++) {
                    flevelsStars[i] = 0;
                }
            }
            saveGameBuilder.setLevelsStars(flevelsStars);

            boolean[] flevelsUnlocked = new boolean[Level.NUMBER_OF_LEVELS];
            try {
                JSONArray array = obj.getJSONArray("levelsUnlocked");
                for (int i = 0; i < flevelsUnlocked.length; i++) {
                    flevelsUnlocked[i] = array.getBoolean(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < flevelsUnlocked.length; i++) {
                    flevelsUnlocked[i] = false;
                }
            }
            saveGameBuilder.setLevelsUnlocked(flevelsUnlocked);

            boolean[] flevelsSeen = new boolean[Level.NUMBER_OF_LEVELS];
            try {
                JSONArray array = obj.getJSONArray("levelsSeen");
                for (int i = 0; i < flevelsSeen.length; i++) {
                    flevelsSeen[i] = array.getBoolean(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < flevelsSeen.length; i++) {
                    flevelsSeen[i] = false;
                }
            }
            saveGameBuilder.setLevelsSeen(flevelsSeen);

            boolean[] fTutorialsSeen = new boolean[Tutorial.NUMBER_OF_TUTORIALS];
            try {
                JSONArray array = obj.getJSONArray("tutorialsSeen");
                for (int i = 0; i < fTutorialsSeen.length; i++) {
                    fTutorialsSeen[i] = array.getBoolean(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < fTutorialsSeen.length; i++) {
                    fTutorialsSeen[i] = false;
                }
            }
            saveGameBuilder.setTutorialsSeen(fTutorialsSeen);
            
            boolean[] fGroupsSeen = new boolean[Level.NUMBER_OF_GROUPS];
            try {
                JSONArray array = obj.getJSONArray("groupsSeen");
                for (int i = 0; i < fGroupsSeen.length; i++) {
                    fGroupsSeen[i] = array.getBoolean(i);
                }
            } catch(JSONException e) {
                for (int i = 0; i < fGroupsSeen.length; i++) {
                    fGroupsSeen[i] = false;
                }
                fGroupsSeen[0] = true;
            }
            saveGameBuilder.setGroupsSeen(fGroupsSeen);

            try {
                saveGameBuilder.setDate(obj.getLong("date"));
            } catch(JSONException e) {
                saveGameBuilder.setDate(0);
            }

            try {
                saveGameBuilder.setCurrentLevelNumber(obj.getInt("currentLevelNumber"));
            } catch(JSONException e) {
                saveGameBuilder.setCurrentLevelNumber(1);
            }

            try {
                saveGameBuilder.setCurrentGroupNumber(obj.getInt("currentGroupNumber"));
            } catch(JSONException e) {
                saveGameBuilder.setCurrentGroupNumber(1);
            }

            try {
                saveGameBuilder.setMusic(obj.getBoolean("music"));
            } catch(JSONException e) {
                saveGameBuilder.setMusic(true);
            }

            try {
                saveGameBuilder.setSound(obj.getBoolean("sound"));
            } catch(JSONException e) {
                saveGameBuilder.setSound(true);
            }

            try {
                saveGameBuilder.setVibration(obj.getBoolean("vibration"));
            } catch(JSONException e) {
                saveGameBuilder.setVibration(true);
            }

            try {
                saveGameBuilder.setBallVelocity(obj.getInt("ballVelocity"));
            } catch(JSONException e) {
                saveGameBuilder.setBallVelocity(100);
            }

            try {
                saveGameBuilder.setGoogleOption(obj.getInt("googleOption"));
            } catch(JSONException e) {
                saveGameBuilder.setGoogleOption(-1);
            }

            try {
                saveGameBuilder.setCurrentGroupMenuTranslateX((float)obj.getDouble("currentGroupMenuTranslateX"));
            } catch(JSONException e) {
                saveGameBuilder.setCurrentGroupMenuTranslateX(0);
            }

            try {
                saveGameBuilder.setCurrentLevelMenuTranslateX((float)obj.getDouble("currentLevelMenuTranslateX"));
            } catch(JSONException e) {
                saveGameBuilder.setCurrentLevelMenuTranslateX(0);
            }

            try {
                saveGameBuilder.setCurrentTutorialMenuTranslateX((float)obj.getDouble("currentTutorialMenuTranslateX"));
            } catch(JSONException e) {
                saveGameBuilder.setCurrentTutorialMenuTranslateX(0);
            }

            try {
                saveGameBuilder.setLastStars(obj.getInt("lastStars"));
            } catch(JSONException e) {
                saveGameBuilder.setLastStars(0);
            }

            try {
                saveGameBuilder.setLevelsPlayed(obj.getInt("levelsPlayed"));
            } catch(JSONException e){
                saveGameBuilder.setLevelsPlayed(0);
            }

            try {
                saveGameBuilder.setOrientationInverted(obj.getBoolean("orientationInverted"));
            } catch(JSONException e) {
                saveGameBuilder.setOrientationInverted(false);
            }

            return saveGameBuilder.build();

        } catch (JSONException ex) {
            ex.printStackTrace();
            //Log.e(TAG, "Save data has a syntax error: " + json, ex);
            // NOTE: In your game, you want to try recovering from the snapshot payload.
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

        if (MenuHandler.groupMenu != null) {
            saveGame.currentGroupMenuTranslateX = MenuHandler.groupMenu.currentTranslateX;
        }

        if (MenuHandler.tutorialMenu != null) {
            saveGame.currentTutorialMenuTranslateX = MenuHandler.tutorialMenu.currentTranslateX;
        }

        if (MenuHandler.levelMenu != null) {
            saveGame.currentLevelMenuTranslateX = MenuHandler.levelMenu.currentTranslateX;
        }

        try {
            JSONObject obj = new JSONObject();
            obj.put("version", SERIAL_VERSION);
            obj.put("levelsPoints", new JSONArray(saveGame.levelsPoints));
            obj.put("levelsStars", new JSONArray(saveGame.levelsStars));
            obj.put("levelsUnlocked", new JSONArray(saveGame.levelsUnlocked));
            obj.put("levelsSeen", new JSONArray(saveGame.levelsSeen));
            obj.put("tutorialsSeen", new JSONArray(saveGame.tutorialsSeen));
            obj.put("groupsSeen", new JSONArray(saveGame.groupsSeen));
            obj.put("date", saveGame.date);
            obj.put("music", saveGame.music);
            obj.put("sound", saveGame.sound);
            obj.put("vibration", saveGame.vibration);
            obj.put("ballVelocity", saveGame.ballVelocity);
            obj.put("googleOption", saveGame.googleOption);
            obj.put("levelsPlayed", saveGame.levelsPlayed);
            obj.put("currentLevelNumber", saveGame.currentLevelNumber);
            obj.put("currentGroupNumber", saveGame.currentGroupNumber);
            obj.put("currentGroupMenuTranslateX", (double)saveGame.currentGroupMenuTranslateX);
            obj.put("currentLevelMenuTranslateX", (double)saveGame.currentLevelMenuTranslateX);
            obj.put("currentTutorialMenuTranslateX", (double)saveGame.currentTutorialMenuTranslateX);
            obj.put("lastStars", saveGame.lastStars);
            obj.put("levelsPlayed", saveGame.levelsPlayed);
            obj.put("orientationInverted", saveGame.orientationInverted);
            return obj.toString();
        } catch (JSONException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error converting save data to JSON.", ex);
        }
    }

    public static void setAllSecretSeen() {
        for (int i = 100; i < saveGame.levelsUnlocked.length; i++){
            if (saveGame.levelsUnlocked[i]){
                saveGame.levelsSeen[i] = true;
            }
        }
    }

    public static void addLevelPlayed() {
        saveGame.levelsPlayed += 1;
        GoogleAPI.increment(
                Game.getContext().getResources().getString(R.string.achievement_iniciante), 1);
        GoogleAPI.increment(
                Game.getContext().getResources().getString(R.string.achievement_aprendiz), 1);
        GoogleAPI.increment(
                Game.getContext().getResources().getString(R.string.achievement_mestre), 1);
    }
    
    public static void setLevelPoints(int number, int points){
        if (saveGame.levelsPoints[number - 1] < points){
            saveGame.levelsPoints[number - 1] = points;
            DataBaseSaveDataHelper.getInstance(Game.mainActivity).setLevelPoints(number, points);
        }
    }
    
    public static void setLevelStars(int number, int stars){
        if (saveGame.levelsStars[number - 1] < stars){
            saveGame.levelsStars[number - 1] = stars;
            DataBaseSaveDataHelper.getInstance(Game.mainActivity).setLevelStars(number, stars);
        }
    }
    
    public static void setLevelUnblocked(int number){
        saveGame.levelsUnlocked[number - 1]  = true;
        DataBaseSaveDataHelper.getInstance(Game.mainActivity).setLevelUnlocked(number, true);
    }
    
    public static void setLevelSeen(int number){
        saveGame.levelsSeen[number - 1]  = true;
        DataBaseSaveDataHelper.getInstance(Game.mainActivity).setLevelSeen(number, true);
    }
              
    public static void setGroupSeen(int number){

        saveGame.groupsSeen[number - 1] = true;
        DataBaseSaveDataHelper.getInstance(Game.mainActivity).setGroupSeen(number, true);
    }
              
    public static int getLevelStars(int number){
        return saveGame.levelsStars[number - 1];
    }


    /*

    public static void onFailLoadFromSnapshot() {

        if (!DataBaseSaveDataHelper.getInstance(Game.mainActivity).isNew()
            || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(1) != 0
            || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(2) != 0
            || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(3) != 0
            || DataBaseSaveDataHelper.getInstance(Game.mainActivity).getLevelPoints(4) != 0
           ){
            //Log.e(TAG, "Carregando apenas localmente.");
            saveGame = DataBaseSaveDataHelper.getInstance(Game.mainActivity).getSaveGame();

            log(saveGame);

            //saveGame = getSaveGameFromJson(getStringFromLocal());
        } else {
            //Log.e(TAG, "Não existe ainda nenhum dado, criando novo");
            int[] _levelsPoints = new int[Level.NUMBER_OF_LEVELS];
            int[] _levelsStars = new int[Level.NUMBER_OF_LEVELS];
            boolean[] _levelsUnlocked = new boolean[Level.NUMBER_OF_LEVELS];
            boolean[] _levelsSeen = new boolean[Level.NUMBER_OF_LEVELS];
            boolean[] _tutorialsSeen = new boolean[Tutorial.NUMBER_OF_TUTORIALS];
            boolean[] _groupsSeen = new boolean[Level.NUMBER_OF_GROUPS];
                _groupsSeen[0] = true;

            saveGame = new SaveGameBuilder()
                    .setLevelsPoints(_levelsPoints)
                    .setLevelsStars(_levelsStars)
                    .setLevelsUnlocked(_levelsUnlocked)
                    .setLevelsSeen(_levelsSeen)
                    .setTutorialsSeen(_tutorialsSeen)
                    .setGroupsSeen(_groupsSeen)
                    .setDate()
                    .setMusic(true)
                    .setSound(true)
                    .setVibration(true)
                    .setCurrentLevelNumber(1)
                    .setCurrentGroupNumber(1)
                    .setCurrentGroupMenuTranslateX(0)
                    .setCurrentLevelMenuTranslateX(0)
                    .setCurrentTutorialMenuTranslateX(0)
                    .setLastStars(0)
                    .setLevelsPlayed(0)
                    .build();
        }
        loaded = true;
        //log(saveGame);
        SaveGame.save();
    }

    public static void onLoadFromSnapshot(String data) {

        //Log.e(TAG, "onLoadFromSnapshot");

        //Log.e(TAG, "local");
        SaveGame localSaveGame = DataBaseSaveDataHelper.getInstance(Game.mainActivity).getSaveGame();
        log(localSaveGame);

        //Log.e(TAG, "cloud");
        SaveGame cloudSaveGame = getSaveGameFromJson(data);
        log(cloudSaveGame);

        //Log.e(TAG, "merged");
        saveGame = mergeSaveGames(localSaveGame, cloudSaveGame);
        log(saveGame);

        loaded = true;
    }



    static Task<byte[]> loadSnapshot() {

        // Get the SnapshotsClient from the signed in account.
        SnapshotsClient snapshotsClient =
                Games.getSnapshotsClient(Game.mainActivity, GoogleSignIn.getLastSignedInAccount(Game.mainActivity));

        // In the case of a conflict, the most recently modified version of this snapshot will be used.
        int conflictResolutionPolicy = SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED;

        // Open the saved game using its name.
        return snapshotsClient.open(SNAPSHOT_FILE_NAME, true, conflictResolutionPolicy)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.e(TAG, "Error while opening Snapshot.", e);
                        snapshotLoaded = null;
                    }
                }).continueWith(new Continuation<SnapshotsClient.DataOrConflict<Snapshot>, byte[]>() {
                    @Override
                    public byte[] then(@NonNull Task<SnapshotsClient.DataOrConflict<Snapshot>> task) throws Exception {
                        Snapshot snapshot = task.getResult().getData();

                        // Opening the snapshot was a success and any conflicts have been resolved.
                        try {
                            // Extract the raw data from the snapshot.
                            snapshotLoaded = snapshot.getSnapshotContents().readFully();
                            return snapshotLoaded;
                        } catch (IOException e) {
                            //Log.e(TAG, "Error while reading Snapshot.", e);
                        }
                        snapshotLoaded = null;
                        return null;
                    }
                }).addOnCompleteListener(new OnCompleteListener<byte[]>() {
                    @Override
                    public void onComplete(@NonNull Task<byte[]> task) {
                        // Dismiss progress dialog and reflect the changes in the UI when complete.
                        // ...
                    }
                });
    }


    private Task<SnapshotsClient.DataOrConflict<Snapshot>> waitForClosedAndOpen(final SnapshotMetadata snapshotMetadata) {

        final boolean useMetadata = snapshotMetadata != null && snapshotMetadata.getUniqueName() != null;
        if (useMetadata) {
            Log.i(TAG, "Opening snapshot using metadata: " + snapshotMetadata);
        } else {
            Log.i(TAG, "Opening snapshot using currentSaveName: " + SHARED_PREFERENCES_KEY_NAME);
        }

        final String filename = useMetadata ? snapshotMetadata.getUniqueName() : SHARED_PREFERENCES_KEY_NAME;

        return SnapshotCoordinator.getInstance()
                .waitForClosed(filename)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.e(TAG, "There was a problem waiting for the file to close!");
                    }
                })
                .continueWithTask(new Continuation<Result, Task<SnapshotsClient.DataOrConflict<Snapshot>>>() {
                    @Override
                    public Task<SnapshotsClient.DataOrConflict<Snapshot>> then(@NonNull Task<Result> task) throws Exception {
                        Task<SnapshotsClient.DataOrConflict<Snapshot>> openTask = useMetadata
                                ? SnapshotCoordinator.getInstance().open(Games.getSnapshotsClient(Game.mainActivity, GoogleSignIn.getLastSignedInAccount(Game.mainActivity)), snapshotMetadata)
                                : SnapshotCoordinator.getInstance().open(Games.getSnapshotsClient(Game.mainActivity, GoogleSignIn.getLastSignedInAccount(Game.mainActivity)), filename, true);
                        return openTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.e(TAG, "Houve um erro ao carregar o Snapshot");
                            }
                        });
                    }
                });
    }

    */

}
  

