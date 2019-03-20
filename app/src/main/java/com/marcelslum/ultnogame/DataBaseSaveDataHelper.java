package com.marcelslum.ultnogame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DataBaseSaveDataHelper extends DataBaseHelper {

    private static DataBaseSaveDataHelper mInstance = null;

    private final String TAG = "DataBaseSaveDataHelper";
    //private String DB_NAME = "save.db";

    private DataBaseSaveDataHelper(Context context, String dbName) {
        super(context, dbName, Integer.valueOf(context.getResources().getString(R.string.databaseSaveDataVersion)));
        this.DB_NAME = dbName;
    }

     public static DataBaseSaveDataHelper getInstance(Context ctx){
        if (mInstance == null){
            mInstance = new DataBaseSaveDataHelper(ctx, "save_db.db");
        }
        return mInstance;
    }

    @Override
    public void upgradeVersion() {
        //Log.e(TAG, "Upgrade version");
        SaveGame saveGame = getSaveGame();
        deleteDataBase();
        try {
            copyDataBase();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        setNotNew();
        saveDataFromSaveGame(saveGame);
    }

    public int getLevelPoints(int l) {
        openDataBase();

        String[] projection = {
                DataBaseContract.DataLevels.COLUMN_POINTS
        };

        String selection =
                DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = " + Game.playerId;

        Cursor cursor = myDataBase.query(
                DataBaseContract.DataLevels.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );


       cursor.moveToFirst();

       return cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_POINTS));
   }

   public boolean saveGameExists(String playerId){

       //Log.e(TAG, "saveGameExists");
       //logAllDatabase();

       openDataBase();

       String[] projection = {
               DataBaseContract.DataLevels.COLUMN_POINTS
       };

       String selection =
               DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ?";

       String[] selectionArgs = new String[]{playerId};

       Cursor cursor = myDataBase.query(
               DataBaseContract.DataLevels.TABLE_NAME,        // The table to query
               projection,                               // The columns to return
               selection,                                // The columns for the WHERE clause
               selectionArgs,                            // The values for the WHERE clause
               null,                                     // don't group the rows
               null,                                     // don't filter by row groups
               null                                      // don't sort
       );

       if (cursor.getCount() == 0){
          // Log.e(TAG, "saveGameExists = false " + cursor.getCount());

           if (Storage.contains(Storage.STORAGE_SAVE_NAME+Game.playerId)){

              // Log.e(TAG, "Storage.contains(Storage.STORAGE_SAVE_NAME+Game.playerId)");

               return true;
           } else {

               //Log.e(TAG, "!Storage.contains(Storage.STORAGE_SAVE_NAME+Game.playerId)");

               return false;
           }

       } else {
           //Log.e(TAG, "saveGameExists = true " + cursor.getCount());
           return true;
       }


   }

   public void logAllDatabase(){
        /*
       openDataBase();

       Log.e(TAG, "Log Database=================================");

       String[] projection = {
               DataBaseContract.DataLevels.COLUMN_PLAYER_ID,
               DataBaseContract.DataLevels.COLUMN_NUMBER,
               DataBaseContract.DataLevels.COLUMN_POINTS,
               DataBaseContract.DataLevels.COLUMN_STARS,
               DataBaseContract.DataLevels.COLUMN_UNLOCKED,
               DataBaseContract.DataLevels.COLUMN_SEEN};

               Cursor cursor = myDataBase.query(
                       DataBaseContract.DataLevels.TABLE_NAME,        // The table to query
                       projection,                               // The columns to return
                       null,                                // The columns for the WHERE clause
                       null,                            // The values for the WHERE clause
                       null,                                     // don't group the rows
                       null,                                     // don't filter by row groups
                       null                                      // don't sort
               );

               int i = 0;
               while(cursor.moveToNext()){

                   Log.e(TAG, " player " + cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_PLAYER_ID)) +
                           " number " + cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_NUMBER)) +
                           " points " + cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_POINTS)) +
                           " stars " + cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_STARS)) +
                           " unlocked " + cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_UNLOCKED)) +
                           " seen " + cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_SEEN)));

                   i += 1;
               }

       Log.e(TAG, "Log Database fim =================================");
       close();
       */

   }

    public SaveGame getSaveGame(){

        //logAllDatabase();

        openDataBase();

        SaveGameBuilder saveGameBuilder = new SaveGameBuilder();

        saveGameBuilder.setPlayerId(Game.playerId);
        
        // DADOS LEVELS ----------------------------------------------------
       String[] projection = {
                 DataBaseContract.DataLevels.COLUMN_NUMBER,
                 DataBaseContract.DataLevels.COLUMN_POINTS,
                 DataBaseContract.DataLevels.COLUMN_STARS,
                 DataBaseContract.DataLevels.COLUMN_UNLOCKED,
                 DataBaseContract.DataLevels.COLUMN_SEEN,
             };

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ?";
        String[] selectionArgs = new String[]{Game.playerId};

         Cursor cursor = myDataBase.query(
                 DataBaseContract.DataLevels.TABLE_NAME,        // The table to query
                 projection,                               // The columns to return
                 selection,                                // The columns for the WHERE clause
                 selectionArgs,                            // The values for the WHERE clause
                 null,                                     // don't group the rows
                 null,                                     // don't filter by row groups
                 null                                      // don't sort
        );

         //Log.e(TAG, "cursor level save data size " + cursor.getCount());
       

        int[] levelsPoints = new int[Level.NUMBER_OF_LEVELS];
        int[] levelsStars = new int[Level.NUMBER_OF_LEVELS];
        boolean[] levelsUnlocked = new boolean[Level.NUMBER_OF_LEVELS];
        boolean[] levelsSeen = new boolean[Level.NUMBER_OF_LEVELS];

        int i = 0;
        while(cursor.moveToNext()){
            if (i == levelsPoints.length){
                break;
            }
            if (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_NUMBER)) == i + 1){
               levelsPoints[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_POINTS));
               levelsStars[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_STARS));
               levelsUnlocked[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_UNLOCKED)) == 1 ? true : false;
               levelsSeen[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_SEEN)) == 1 ? true : false;
            }
            i += 1;
        }
        
                    saveGameBuilder
                        .setLevelsPoints(levelsPoints)
                        .setLevelsStars(levelsStars)
                        .setLevelsUnlocked(levelsUnlocked)
                        .setLevelsSeen(levelsSeen);



        // DADOS TUTORIALS ---------------------------------
        projection = new String [] {
                 DataBaseContract.DataTutorials.COLUMN_NUMBER,
                 DataBaseContract.DataTutorials.COLUMN_SEEN
             };


            cursor = myDataBase.query(
                 DataBaseContract.DataTutorials.TABLE_NAME,        // The table to query
                 projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                 null,                                     // don't group the rows
                 null,                                     // don't filter by row groups
                 null                                      // don't sort
        );

        boolean[] tutorialsSeen = new boolean[Tutorial.NUMBER_OF_TUTORIALS];
 
        i = 0;
        while(cursor.moveToNext()){
             if (i == tutorialsSeen.length){
                 break;
             }
             if (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_NUMBER)) == i + 1){
                tutorialsSeen[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_SEEN)) == 1 ? true : false;
             }
             i += 1;
         }
        saveGameBuilder.setTutorialsSeen(tutorialsSeen);



        // DADOS TUTORIALS -----------------------------------------------------
        projection = new String[] {
                DataBaseContract.DataGroups.COLUMN_NUMBER,
                DataBaseContract.DataGroups.COLUMN_SEEN
        };

        cursor = myDataBase.query(
                DataBaseContract.DataGroups.TABLE_NAME,        // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        boolean[] groupsSeen = new boolean[Level.NUMBER_OF_GROUPS];

        i = 0;
        while(cursor.moveToNext()){
            if (i == groupsSeen.length){
                break;
            }
            if (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataGroups.COLUMN_NUMBER)) == i + 1){
                groupsSeen[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataGroups.COLUMN_SEEN)) == 1 ? true : false;
            }
            i += 1;
        }
        saveGameBuilder.setGroupsSeen(groupsSeen);
        
        
        // DADOS GERAIS -------------------------------------------------------
        
        projection = new String[]{
            DataBaseContract.Data.COLUMN_DATE,
            DataBaseContract.Data.COLUMN_DATE,
            DataBaseContract.Data.COLUMN_CURRENT_LEVEL,
            DataBaseContract.Data.COLUMN_CURRENT_GROUP,
            DataBaseContract.Data.COLUMN_MUSIC,
            DataBaseContract.Data.COLUMN_SOUND,
            DataBaseContract.Data.COLUMN_VIBRATION,
            DataBaseContract.Data.COLUMN_GROUP_MENU_TRANSLATE_X,
            DataBaseContract.Data.COLUMN_LEVEL_MENU_TRANSLATE_X,
            DataBaseContract.Data.COLUMN_TUTORIAL_MENU_TRANSLATE_X,
            DataBaseContract.Data.COLUMN_LAST_STARS,
            DataBaseContract.Data.COLUMN_NEW_GROUPS_SEEN,
            DataBaseContract.Data.COLUMN_LEVELS_PLAYED,
            DataBaseContract.Data.COLUMN_GOOGLE_OPTION,
            DataBaseContract.Data.COLUMN_BALL_VELOCITY,
            DataBaseContract.Data.COLUMN_ORIENTATION_INVERTED
        };

         cursor = myDataBase.query(
                 DataBaseContract.Data.TABLE_NAME,          // The table to query
                 projection,                                // The columns to return
                 selection,                                 // The columns for the WHERE clause
                 selectionArgs,                                      // The values for the WHERE clause
                 null,                                      // don't group the rows
                 null,                                      // don't filter by row groups
                 null                                       // don't sort
        );
        
        while(cursor.moveToNext()){
            saveGameBuilder
                .setDate(cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_DATE)))
                .setMusic(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_MUSIC)) == 1 ? true : false)
                .setSound(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_SOUND)) == 1 ? true : false)
                .setVibration(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_VIBRATION)) == 1 ? true : false)
                .setCurrentLevelNumber(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_CURRENT_LEVEL)))
                .setCurrentGroupNumber(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_CURRENT_GROUP)))
                .setCurrentGroupMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_GROUP_MENU_TRANSLATE_X)))
                .setCurrentLevelMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_LEVEL_MENU_TRANSLATE_X)))
                .setCurrentTutorialMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_TUTORIAL_MENU_TRANSLATE_X)))
                .setLastStars(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_LAST_STARS)))
                .setLevelsPlayed(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_LEVELS_PLAYED)))
                .setGoogleOption(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_GOOGLE_OPTION)))
                .setBallVelocity(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_BALL_VELOCITY)));

            try{
                saveGameBuilder.setOrientationInverted(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_ORIENTATION_INVERTED)) == 1 ? true : false);
            } catch(Exception e){
                Log.e(TAG, "Campo orientation inverted não existente no banco de dados, setando como falso -> exception");
                Log.e(TAG, e.getMessage());
                saveGameBuilder.setOrientationInverted(false);
            }


             break;
        }

        // DADOS GERAIS, CAMPOS NOVOS -----------------------------------

        projection = new String[]{
                DataBaseContract.Data.COLUMN_SAVE_MENU_SEEN
        };

        try {

            cursor = myDataBase.query(
                    DataBaseContract.Data.TABLE_NAME,          // The table to query
                    projection,                                // The columns to return
                    selection,                                 // The columns for the WHERE clause
                    selectionArgs,                                      // The values for the WHERE clause
                    null,                                      // don't group the rows
                    null,                                      // don't filter by row groups
                    null                                       // don't sort
            );

            while (cursor.moveToNext()) {
                try {
                    saveGameBuilder.setSaveMenuSeen(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_SAVE_MENU_SEEN)) == 1 ? true : false);
                } catch (Exception e) {
                    Log.e(TAG, "Campo COLUMN_SAVE_MENU_SEEN não existente no banco de dados, setando como falso -> exception");
                    Log.e(TAG, e.getMessage());
                    saveGameBuilder.setSaveMenuSeen(false);
                }

                break;
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }


        // CAMPO LAST LEVEL PLAYED ----------------------------------------------

        projection = new String[]{
                DataBaseContract.Data.COLUMN_LAST_LEVEL_PLAYED
        };

        try {

            cursor = myDataBase.query(
                    DataBaseContract.Data.TABLE_NAME,          // The table to query
                    projection,                                // The columns to return
                    selection,                                 // The columns for the WHERE clause
                    selectionArgs,                                      // The values for the WHERE clause
                    null,                                      // don't group the rows
                    null,                                      // don't filter by row groups
                    null                                       // don't sort
            );

            while (cursor.moveToNext()) {
                try {
                    saveGameBuilder.setLastLevelPlayed(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_LAST_LEVEL_PLAYED)));
                } catch (Exception e) {
                    Log.e(TAG, "Campo COLUMN_LAST_LEVEL_PLAYED");
                    Log.e(TAG, e.getMessage());
                }
                break;
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }



        // ---------------------STATS

        long [] stats = new long [Stats.STATS_DATABASE_SIZE];


        projection = new String[]{
                DataBaseContract.DataStats.STAT0,
                DataBaseContract.DataStats.STAT1,
                DataBaseContract.DataStats.STAT2,
                DataBaseContract.DataStats.STAT3,
                DataBaseContract.DataStats.STAT4,
                DataBaseContract.DataStats.STAT5,
                DataBaseContract.DataStats.STAT6,
                DataBaseContract.DataStats.STAT7,
                DataBaseContract.DataStats.STAT8,
                DataBaseContract.DataStats.STAT9,
                DataBaseContract.DataStats.STAT10,
                DataBaseContract.DataStats.STAT11,
                DataBaseContract.DataStats.STAT12,
                DataBaseContract.DataStats.STAT13,
                DataBaseContract.DataStats.STAT14,
                DataBaseContract.DataStats.STAT15,
                DataBaseContract.DataStats.STAT16,
                DataBaseContract.DataStats.STAT17,
                DataBaseContract.DataStats.STAT18,
                DataBaseContract.DataStats.STAT19,
                DataBaseContract.DataStats.STAT20,
                DataBaseContract.DataStats.STAT21,
                DataBaseContract.DataStats.STAT22,
                DataBaseContract.DataStats.STAT23,
                DataBaseContract.DataStats.STAT24,
                DataBaseContract.DataStats.STAT25,
                DataBaseContract.DataStats.STAT26,
                DataBaseContract.DataStats.STAT27,
                DataBaseContract.DataStats.STAT28,
                DataBaseContract.DataStats.STAT29,
                DataBaseContract.DataStats.STAT30,
                DataBaseContract.DataStats.STAT31,
                DataBaseContract.DataStats.STAT32,
                DataBaseContract.DataStats.STAT33,
                DataBaseContract.DataStats.STAT34,
                DataBaseContract.DataStats.STAT35,
                DataBaseContract.DataStats.STAT36,
                DataBaseContract.DataStats.STAT37,
                DataBaseContract.DataStats.STAT38,
                DataBaseContract.DataStats.STAT39,
                DataBaseContract.DataStats.STAT40,
                DataBaseContract.DataStats.STAT41,
                DataBaseContract.DataStats.STAT42,
                DataBaseContract.DataStats.STAT43,
                DataBaseContract.DataStats.STAT44,
                DataBaseContract.DataStats.STAT45,
                DataBaseContract.DataStats.STAT46,
                DataBaseContract.DataStats.STAT47,
                DataBaseContract.DataStats.STAT48,
                DataBaseContract.DataStats.STAT49,
                DataBaseContract.DataStats.STAT50,
                DataBaseContract.DataStats.STAT51,
                DataBaseContract.DataStats.STAT52,
                DataBaseContract.DataStats.STAT53,
                DataBaseContract.DataStats.STAT54,
                DataBaseContract.DataStats.STAT55,
                DataBaseContract.DataStats.STAT56,
                DataBaseContract.DataStats.STAT57,
                DataBaseContract.DataStats.STAT58,
                DataBaseContract.DataStats.STAT60
        };


        selection = DataBaseContract.DataStats.COLUMN_PLAYER_ID + " = ?";
        selectionArgs = new String[]{Game.playerId};

        try {

            cursor = myDataBase.query(
                    DataBaseContract.DataStats.TABLE_NAME,          // The table to query
                    projection,                                // The columns to return
                    selection,                                 // The columns for the WHERE clause
                    selectionArgs,                                      // The values for the WHERE clause
                    null,                                      // don't group the rows
                    null,                                      // don't filter by row groups
                    null                                       // don't sort
            );

            while (cursor.moveToNext()) {
                try {
                    stats[0] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT0));
                    stats[1] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT1));
                    stats[2] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT2));
                    stats[3] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT3));
                    stats[4] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT4));
                    stats[5] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT5));
                    stats[6] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT6));
                    stats[7] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT7));
                    stats[8] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT8));
                    stats[9] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT9));
                    stats[10] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT10));
                    stats[11] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT11));
                    stats[12] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT12));
                    stats[13] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT13));
                    stats[14] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT14));
                    stats[15] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT15));
                    stats[16] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT16));
                    stats[17] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT17));
                    stats[18] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT18));
                    stats[19] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT19));
                    stats[20] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT20));
                    stats[21] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT21));
                    stats[22] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT22));
                    stats[23] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT23));
                    stats[24] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT24));
                    stats[25] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT25));
                    stats[26] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT26));
                    stats[27] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT27));
                    stats[28] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT28));
                    stats[29] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT29));
                    stats[30] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT30));
                    stats[31] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT31));
                    stats[32] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT32));
                    stats[33] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT33));
                    stats[34] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT34));
                    stats[35] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT35));
                    stats[36] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT36));
                    stats[37] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT37));
                    stats[38] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT38));
                    stats[39] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT39));
                    stats[40] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT40));
                    stats[41] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT41));
                    stats[42] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT42));
                    stats[43] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT43));
                    stats[44] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT44));
                    stats[45] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT45));
                    stats[46] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT46));
                    stats[47] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT47));
                    stats[48] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT48));
                    stats[49] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT49));
                    stats[50] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT50));
                    stats[51] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT51));
                    stats[52] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT52));
                    stats[53] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT53));
                    stats[54] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT54));
                    stats[55] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT55));
                    stats[56] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT56));
                    stats[57] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT57));
                    stats[58] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT58));
                    stats[59] = cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataStats.STAT60));
                    saveGameBuilder.setStats(stats);
                } catch (Exception e) {
                    Log.e(TAG, "Erro ao buscar os dados da tabela STATS");
                    Log.e(TAG, e.getMessage());
                    saveGameBuilder.setStats(new long[Stats.STATS_DATABASE_SIZE]);
                }

                break;
            }
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        saveGameBuilder.setStats(stats);


        close();

        return saveGameBuilder.build();
    }


    public void setLevelPoints(int number, int v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_POINTS, v);

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
        String [] whereArgs = new String []{Game.playerId, String.valueOf(number)};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);
    }
    
    public void setLevelStars(int number, int v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_STARS, v);

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
        String [] whereArgs = new String []{Game.playerId, String.valueOf(number)};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);
    }
    
    public void setLevelUnlocked(int number, boolean v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_UNLOCKED, v ? 1 : 0);

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
        String [] whereArgs = new String []{Game.playerId, String.valueOf(number)};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);

    }
    
    public void setLevelSeen(int number, boolean v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_SEEN, v ? 1 : 0);

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
        String [] whereArgs = new String []{Game.playerId, String.valueOf(number)};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);
    }
    
    public void setTutorialSeen(int number, boolean v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataTutorials.COLUMN_SEEN, v ? 1 : 0);
        String selection = DataBaseContract.DataTutorials.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
            DataBaseContract.DataTutorials.TABLE_NAME,
            values,
            selection,
            selectionArgs);
    }

    public void setGroupSeen(int number, boolean v){
        myDataBase = getWritable();
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataGroups.COLUMN_SEEN, v ? 1 : 0);

        String selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
        String [] whereArgs = new String []{Game.playerId, String.valueOf(number)};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);
    }
    
    public void saveDataFromSaveGame(SaveGame saveGame){

        //Log.e(TAG, "saveDataFromSaveGame");
        //logAllDatabase();


        if (!saveGameExists(Game.playerId)){
            //Log.e(TAG, "!saveGameExists(Game.playerId) ------ criando novo");
            createNewSaveGame(saveGame);
            return;
        }


        myDataBase = getWritable();

        //Log.e(TAG, "saveDataFromSaveGame");

        ContentValues values = new ContentValues();

        String selection = DataBaseContract.Data.COLUMN_PLAYER_ID + " = ?";
        String[] whereArgs = new String []{Game.playerId};

        try{
            values.put(DataBaseContract.Data.COLUMN_DATE, saveGame.date);
            values.put(DataBaseContract.Data.COLUMN_CURRENT_LEVEL, saveGame.currentLevelNumber);
            values.put(DataBaseContract.Data.COLUMN_CURRENT_GROUP, saveGame.currentGroupNumber);
            values.put(DataBaseContract.Data.COLUMN_MUSIC, saveGame.music ? 1 : 0);
            values.put(DataBaseContract.Data.COLUMN_SOUND, saveGame.sound ? 1 : 0);
            values.put(DataBaseContract.Data.COLUMN_VIBRATION, saveGame.vibration ? 1 : 0);
            values.put(DataBaseContract.Data.COLUMN_GOOGLE_OPTION, saveGame.googleOption);
            values.put(DataBaseContract.Data.COLUMN_BALL_VELOCITY, saveGame.ballVelocity);
            values.put(DataBaseContract.Data.COLUMN_GROUP_MENU_TRANSLATE_X, saveGame.currentGroupMenuTranslateX);
            values.put(DataBaseContract.Data.COLUMN_LEVEL_MENU_TRANSLATE_X, saveGame.currentLevelMenuTranslateX);
            values.put(DataBaseContract.Data.COLUMN_TUTORIAL_MENU_TRANSLATE_X, saveGame.currentTutorialMenuTranslateX);
            values.put(DataBaseContract.Data.COLUMN_LAST_STARS, saveGame.lastStars);
            values.put(DataBaseContract.Data.COLUMN_LEVELS_PLAYED, saveGame.levelsPlayed);
            values.put(DataBaseContract.Data.COLUMN_ORIENTATION_INVERTED, saveGame.orientationInverted ? 1 : 0);
            values.put(DataBaseContract.Data.COLUMN_SAVE_MENU_SEEN, saveGame.saveMenuSeen ? 1 : 0);
            values.put(DataBaseContract.Data.COLUMN_LAST_LEVEL_PLAYED, saveGame.lastLevelPlayed);
        
        selection = DataBaseContract.Data.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{Game.playerId};


        myDataBase.update(
            DataBaseContract.Data.TABLE_NAME,
            values,
            selection,
            whereArgs);

        }catch(SQLiteException e){
            Log.e(TAG, "não foi possível salvar no banco de dados os dados");
        }

        for (int i = 0; i < saveGame.levelsPoints.length; i++){

            values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_POINTS, saveGame.levelsPoints[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_STARS, saveGame.levelsStars[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_SEEN, saveGame.levelsSeen[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_UNLOCKED, saveGame.levelsUnlocked[i]);
            selection = DataBaseContract.Data.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataLevels.COLUMN_NUMBER + " = ?";
            whereArgs = new String []{Game.playerId, String.valueOf(i+1)};

            myDataBase.update(
                    DataBaseContract.DataLevels.TABLE_NAME,
                    values,
                    selection,
                    whereArgs);
        }

        for (int i = 0; i < saveGame.tutorialsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataTutorials.COLUMN_SEEN, saveGame.tutorialsSeen[i]);
            selection = DataBaseContract.DataTutorials.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataTutorials.COLUMN_NUMBER + " = ?";
            whereArgs = new String []{Game.playerId, String.valueOf(i+1)};
            myDataBase.update(
                    DataBaseContract.DataTutorials.TABLE_NAME,
                    values,
                    selection,
                    whereArgs);
        }

        for (int i = 0; i < saveGame.groupsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataGroups.COLUMN_SEEN, saveGame.groupsSeen[i]);
            selection = DataBaseContract.DataGroups.COLUMN_PLAYER_ID + " = ? AND " + DataBaseContract.DataGroups.COLUMN_NUMBER + " = ?";
            whereArgs = new String []{Game.playerId, String.valueOf(i+1)};
            myDataBase.update(
                    DataBaseContract.DataGroups.TABLE_NAME,
                    values,
                    selection,
                    whereArgs);
        }

        String query = "SELECT googleOption FROM data";
        Cursor cursor = getWritable().rawQuery(query, null);
        cursor.moveToFirst();

        values = new ContentValues();
        values.put(DataBaseContract.DataStats.STAT0, saveGame.stats[0]);
        values.put(DataBaseContract.DataStats.STAT1, saveGame.stats[1]);
        values.put(DataBaseContract.DataStats.STAT2, saveGame.stats[2]);
        values.put(DataBaseContract.DataStats.STAT3, saveGame.stats[3]);
        values.put(DataBaseContract.DataStats.STAT4, saveGame.stats[4]);
        values.put(DataBaseContract.DataStats.STAT5, saveGame.stats[5]);
        values.put(DataBaseContract.DataStats.STAT6, saveGame.stats[6]);
        values.put(DataBaseContract.DataStats.STAT7, saveGame.stats[7]);
        values.put(DataBaseContract.DataStats.STAT8, saveGame.stats[8]);
        values.put(DataBaseContract.DataStats.STAT9, saveGame.stats[9]);
        values.put(DataBaseContract.DataStats.STAT10, saveGame.stats[10]);
        values.put(DataBaseContract.DataStats.STAT11, saveGame.stats[11]);
        values.put(DataBaseContract.DataStats.STAT12, saveGame.stats[12]);
        values.put(DataBaseContract.DataStats.STAT13, saveGame.stats[13]);
        values.put(DataBaseContract.DataStats.STAT14, saveGame.stats[14]);
        values.put(DataBaseContract.DataStats.STAT15, saveGame.stats[15]);
        values.put(DataBaseContract.DataStats.STAT16, saveGame.stats[16]);
        values.put(DataBaseContract.DataStats.STAT17, saveGame.stats[17]);
        values.put(DataBaseContract.DataStats.STAT18, saveGame.stats[18]);
        values.put(DataBaseContract.DataStats.STAT19, saveGame.stats[19]);
        values.put(DataBaseContract.DataStats.STAT20, saveGame.stats[20]);
        values.put(DataBaseContract.DataStats.STAT21, saveGame.stats[21]);
        values.put(DataBaseContract.DataStats.STAT22, saveGame.stats[22]);
        values.put(DataBaseContract.DataStats.STAT23, saveGame.stats[23]);
        values.put(DataBaseContract.DataStats.STAT24, saveGame.stats[24]);
        values.put(DataBaseContract.DataStats.STAT25, saveGame.stats[25]);
        values.put(DataBaseContract.DataStats.STAT26, saveGame.stats[26]);
        values.put(DataBaseContract.DataStats.STAT27, saveGame.stats[27]);
        values.put(DataBaseContract.DataStats.STAT28, saveGame.stats[28]);
        values.put(DataBaseContract.DataStats.STAT29, saveGame.stats[29]);
        values.put(DataBaseContract.DataStats.STAT30, saveGame.stats[30]);
        values.put(DataBaseContract.DataStats.STAT31, saveGame.stats[31]);
        values.put(DataBaseContract.DataStats.STAT32, saveGame.stats[32]);
        values.put(DataBaseContract.DataStats.STAT33, saveGame.stats[33]);
        values.put(DataBaseContract.DataStats.STAT34, saveGame.stats[34]);
        values.put(DataBaseContract.DataStats.STAT35, saveGame.stats[35]);
        values.put(DataBaseContract.DataStats.STAT36, saveGame.stats[36]);
        values.put(DataBaseContract.DataStats.STAT37, saveGame.stats[37]);
        values.put(DataBaseContract.DataStats.STAT38, saveGame.stats[38]);
        values.put(DataBaseContract.DataStats.STAT39, saveGame.stats[39]);
        values.put(DataBaseContract.DataStats.STAT40, saveGame.stats[40]);
        values.put(DataBaseContract.DataStats.STAT41, saveGame.stats[41]);
        values.put(DataBaseContract.DataStats.STAT42, saveGame.stats[42]);
        values.put(DataBaseContract.DataStats.STAT43, saveGame.stats[43]);
        values.put(DataBaseContract.DataStats.STAT44, saveGame.stats[44]);
        values.put(DataBaseContract.DataStats.STAT45, saveGame.stats[45]);
        values.put(DataBaseContract.DataStats.STAT46, saveGame.stats[46]);
        values.put(DataBaseContract.DataStats.STAT47, saveGame.stats[47]);
        values.put(DataBaseContract.DataStats.STAT48, saveGame.stats[48]);
        values.put(DataBaseContract.DataStats.STAT49, saveGame.stats[49]);
        values.put(DataBaseContract.DataStats.STAT50, saveGame.stats[50]);
        values.put(DataBaseContract.DataStats.STAT51, saveGame.stats[51]);
        values.put(DataBaseContract.DataStats.STAT52, saveGame.stats[52]);
        values.put(DataBaseContract.DataStats.STAT53, saveGame.stats[53]);
        values.put(DataBaseContract.DataStats.STAT54, saveGame.stats[54]);
        values.put(DataBaseContract.DataStats.STAT55, saveGame.stats[55]);
        values.put(DataBaseContract.DataStats.STAT56, saveGame.stats[56]);
        values.put(DataBaseContract.DataStats.STAT57, saveGame.stats[57]);
        values.put(DataBaseContract.DataStats.STAT58, saveGame.stats[58]);
        values.put(DataBaseContract.DataStats.STAT59, saveGame.stats[59]);
        values.put(DataBaseContract.DataStats.COLUMN_PLAYER_ID, saveGame.playerId);

        selection = DataBaseContract.DataStats.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{Game.playerId};

        myDataBase.update(
                DataBaseContract.DataStats.TABLE_NAME,
                values,
                selection,
                whereArgs);

        close();

        logAllDatabase();
    }

    public void createNewSaveGame(SaveGame saveGame) {

        //Log.e(TAG, "createNewSaveGame");
        //logAllDatabase();

        myDataBase = getWritable();

        ContentValues values = new ContentValues();
        values.put(DataBaseContract.Data.COLUMN_DATE, saveGame.date);
        values.put(DataBaseContract.Data.COLUMN_CURRENT_LEVEL, saveGame.currentLevelNumber);
        values.put(DataBaseContract.Data.COLUMN_CURRENT_GROUP, saveGame.currentGroupNumber);
        values.put(DataBaseContract.Data.COLUMN_MUSIC, saveGame.music ? 1 : 0);
        values.put(DataBaseContract.Data.COLUMN_SOUND, saveGame.sound ? 1 : 0);
        values.put(DataBaseContract.Data.COLUMN_VIBRATION, saveGame.vibration ? 1 : 0);
        values.put(DataBaseContract.Data.COLUMN_GOOGLE_OPTION, saveGame.googleOption);
        values.put(DataBaseContract.Data.COLUMN_BALL_VELOCITY, saveGame.ballVelocity);
        values.put(DataBaseContract.Data.COLUMN_GROUP_MENU_TRANSLATE_X, saveGame.currentGroupMenuTranslateX);
        values.put(DataBaseContract.Data.COLUMN_LEVEL_MENU_TRANSLATE_X, saveGame.currentLevelMenuTranslateX);
        values.put(DataBaseContract.Data.COLUMN_TUTORIAL_MENU_TRANSLATE_X, saveGame.currentTutorialMenuTranslateX);
        values.put(DataBaseContract.Data.COLUMN_LAST_STARS, saveGame.lastStars);
        values.put(DataBaseContract.Data.COLUMN_LEVELS_PLAYED, saveGame.levelsPlayed);
        values.put(DataBaseContract.Data.COLUMN_PLAYER_ID, saveGame.playerId);
        values.put(DataBaseContract.Data.COLUMN_ORIENTATION_INVERTED, saveGame.orientationInverted ? 1 : 0);
        values.put(DataBaseContract.Data.COLUMN_SAVE_MENU_SEEN, saveGame.saveMenuSeen ? 1 : 0);
        values.put(DataBaseContract.Data.COLUMN_LAST_LEVEL_PLAYED, saveGame.lastLevelPlayed);
        myDataBase.insert(DataBaseContract.Data.TABLE_NAME, null, values);

        for (int i = 0; i < saveGame.levelsPoints.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_POINTS, saveGame.levelsPoints[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_STARS, saveGame.levelsStars[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_SEEN, saveGame.levelsSeen[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_UNLOCKED, saveGame.levelsUnlocked[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_NUMBER, String.valueOf(i + 1));
            values.put(DataBaseContract.DataLevels.COLUMN_PLAYER_ID, saveGame.playerId);
            myDataBase.insert(DataBaseContract.DataLevels.TABLE_NAME, null, values);
        }

        for (int i = 0; i < saveGame.tutorialsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataTutorials.COLUMN_NUMBER, String.valueOf(i + 1));
            values.put(DataBaseContract.DataTutorials.COLUMN_SEEN, saveGame.tutorialsSeen[i]);
            values.put(DataBaseContract.DataTutorials.COLUMN_PLAYER_ID, saveGame.playerId);
            myDataBase.insert(DataBaseContract.DataTutorials.TABLE_NAME, null, values);

        }

        for (int i = 0; i < saveGame.groupsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataGroups.COLUMN_NUMBER, String.valueOf(i + 1));
            values.put(DataBaseContract.DataGroups.COLUMN_SEEN, saveGame.groupsSeen[i]);
            values.put(DataBaseContract.DataTutorials.COLUMN_PLAYER_ID, saveGame.playerId);
            myDataBase.insert(DataBaseContract.DataTutorials.TABLE_NAME, null, values);
        }

        values = new ContentValues();
        values.put(DataBaseContract.DataStats.STAT0, saveGame.stats[0]);
        values.put(DataBaseContract.DataStats.STAT1, saveGame.stats[1]);
        values.put(DataBaseContract.DataStats.STAT2, saveGame.stats[2]);
        values.put(DataBaseContract.DataStats.STAT3, saveGame.stats[3]);
        values.put(DataBaseContract.DataStats.STAT4, saveGame.stats[4]);
        values.put(DataBaseContract.DataStats.STAT5, saveGame.stats[5]);
        values.put(DataBaseContract.DataStats.STAT6, saveGame.stats[6]);
        values.put(DataBaseContract.DataStats.STAT7, saveGame.stats[7]);
        values.put(DataBaseContract.DataStats.STAT8, saveGame.stats[8]);
        values.put(DataBaseContract.DataStats.STAT9, saveGame.stats[9]);
        values.put(DataBaseContract.DataStats.STAT10, saveGame.stats[10]);
        values.put(DataBaseContract.DataStats.STAT11, saveGame.stats[11]);
        values.put(DataBaseContract.DataStats.STAT12, saveGame.stats[12]);
        values.put(DataBaseContract.DataStats.STAT13, saveGame.stats[13]);
        values.put(DataBaseContract.DataStats.STAT14, saveGame.stats[14]);
        values.put(DataBaseContract.DataStats.STAT15, saveGame.stats[15]);
        values.put(DataBaseContract.DataStats.STAT16, saveGame.stats[16]);
        values.put(DataBaseContract.DataStats.STAT17, saveGame.stats[17]);
        values.put(DataBaseContract.DataStats.STAT18, saveGame.stats[18]);
        values.put(DataBaseContract.DataStats.STAT19, saveGame.stats[19]);
        values.put(DataBaseContract.DataStats.STAT20, saveGame.stats[20]);
        values.put(DataBaseContract.DataStats.STAT21, saveGame.stats[21]);
        values.put(DataBaseContract.DataStats.STAT22, saveGame.stats[22]);
        values.put(DataBaseContract.DataStats.STAT23, saveGame.stats[23]);
        values.put(DataBaseContract.DataStats.STAT24, saveGame.stats[24]);
        values.put(DataBaseContract.DataStats.STAT25, saveGame.stats[25]);
        values.put(DataBaseContract.DataStats.STAT26, saveGame.stats[26]);
        values.put(DataBaseContract.DataStats.STAT27, saveGame.stats[27]);
        values.put(DataBaseContract.DataStats.STAT28, saveGame.stats[28]);
        values.put(DataBaseContract.DataStats.STAT29, saveGame.stats[29]);
        values.put(DataBaseContract.DataStats.STAT30, saveGame.stats[30]);
        values.put(DataBaseContract.DataStats.STAT31, saveGame.stats[31]);
        values.put(DataBaseContract.DataStats.STAT32, saveGame.stats[32]);
        values.put(DataBaseContract.DataStats.STAT33, saveGame.stats[33]);
        values.put(DataBaseContract.DataStats.STAT34, saveGame.stats[34]);
        values.put(DataBaseContract.DataStats.STAT35, saveGame.stats[35]);
        values.put(DataBaseContract.DataStats.STAT36, saveGame.stats[36]);
        values.put(DataBaseContract.DataStats.STAT37, saveGame.stats[37]);
        values.put(DataBaseContract.DataStats.STAT38, saveGame.stats[38]);
        values.put(DataBaseContract.DataStats.STAT39, saveGame.stats[39]);
        values.put(DataBaseContract.DataStats.STAT40, saveGame.stats[40]);
        values.put(DataBaseContract.DataStats.STAT41, saveGame.stats[41]);
        values.put(DataBaseContract.DataStats.STAT42, saveGame.stats[42]);
        values.put(DataBaseContract.DataStats.STAT43, saveGame.stats[43]);
        values.put(DataBaseContract.DataStats.STAT44, saveGame.stats[44]);
        values.put(DataBaseContract.DataStats.STAT45, saveGame.stats[45]);
        values.put(DataBaseContract.DataStats.STAT46, saveGame.stats[46]);
        values.put(DataBaseContract.DataStats.STAT47, saveGame.stats[47]);
        values.put(DataBaseContract.DataStats.STAT48, saveGame.stats[48]);
        values.put(DataBaseContract.DataStats.STAT49, saveGame.stats[49]);
        values.put(DataBaseContract.DataStats.STAT50, saveGame.stats[50]);
        values.put(DataBaseContract.DataStats.STAT51, saveGame.stats[51]);
        values.put(DataBaseContract.DataStats.STAT52, saveGame.stats[52]);
        values.put(DataBaseContract.DataStats.STAT53, saveGame.stats[53]);
        values.put(DataBaseContract.DataStats.STAT54, saveGame.stats[54]);
        values.put(DataBaseContract.DataStats.STAT55, saveGame.stats[55]);
        values.put(DataBaseContract.DataStats.STAT56, saveGame.stats[56]);
        values.put(DataBaseContract.DataStats.STAT57, saveGame.stats[57]);
        values.put(DataBaseContract.DataStats.STAT58, saveGame.stats[58]);
        values.put(DataBaseContract.DataStats.STAT59, saveGame.stats[59]);
        values.put(DataBaseContract.DataStats.COLUMN_PLAYER_ID, saveGame.playerId);
        myDataBase.insert(DataBaseContract.DataStats.TABLE_NAME, null, values);


        close();

        //Log.e(TAG, "createNewSaveGame depois");
        logAllDatabase();

    }

    public void updatePlayerId(String newPlayerId, String oldPlayerId, SaveGame saveGame) {

        //Log.e(TAG, "updatePlayerId");
        //logAllDatabase();

        myDataBase = getWritable();

        ContentValues values = new ContentValues();

        String selection = DataBaseContract.Data.COLUMN_PLAYER_ID + " = ?";
        String[] whereArgs = new String []{Game.playerId};

        try{
            values.put(DataBaseContract.Data.COLUMN_PLAYER_ID, newPlayerId);
            selection = DataBaseContract.Data.COLUMN_PLAYER_ID + " = ?";
            whereArgs = new String []{oldPlayerId};

            myDataBase.update(
                    DataBaseContract.Data.TABLE_NAME,
                    values,
                    selection,
                    whereArgs);

        }catch(SQLiteException e){
            Log.e(TAG, "não foi possível salvar no banco de dados os dados");
        }


        values = new ContentValues();
        values.put(DataBaseContract.DataLevels.COLUMN_PLAYER_ID, newPlayerId);
        selection = DataBaseContract.DataLevels.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{oldPlayerId};

        myDataBase.update(
                DataBaseContract.DataLevels.TABLE_NAME,
                values,
                selection,
                whereArgs);



        values = new ContentValues();
        values.put(DataBaseContract.DataTutorials.COLUMN_PLAYER_ID, newPlayerId);
        selection = DataBaseContract.DataTutorials.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{oldPlayerId};

        myDataBase.update(
                DataBaseContract.DataTutorials.TABLE_NAME,
                values,
                selection,
                whereArgs);



        values = new ContentValues();
        values.put(DataBaseContract.DataGroups.COLUMN_PLAYER_ID, newPlayerId);
        selection = DataBaseContract.DataGroups.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{oldPlayerId};

        myDataBase.update(
                DataBaseContract.DataGroups.TABLE_NAME,
                values,
                selection,
                whereArgs);



        values = new ContentValues();
        values.put(DataBaseContract.DataStats.STAT0, saveGame.stats[0]);
        values.put(DataBaseContract.DataStats.STAT1, saveGame.stats[1]);
        values.put(DataBaseContract.DataStats.STAT2, saveGame.stats[2]);
        values.put(DataBaseContract.DataStats.STAT3, saveGame.stats[3]);
        values.put(DataBaseContract.DataStats.STAT4, saveGame.stats[4]);
        values.put(DataBaseContract.DataStats.STAT5, saveGame.stats[5]);
        values.put(DataBaseContract.DataStats.STAT6, saveGame.stats[6]);
        values.put(DataBaseContract.DataStats.STAT7, saveGame.stats[7]);
        values.put(DataBaseContract.DataStats.STAT8, saveGame.stats[8]);
        values.put(DataBaseContract.DataStats.STAT9, saveGame.stats[9]);
        values.put(DataBaseContract.DataStats.STAT10, saveGame.stats[10]);
        values.put(DataBaseContract.DataStats.STAT11, saveGame.stats[11]);
        values.put(DataBaseContract.DataStats.STAT12, saveGame.stats[12]);
        values.put(DataBaseContract.DataStats.STAT13, saveGame.stats[13]);
        values.put(DataBaseContract.DataStats.STAT14, saveGame.stats[14]);
        values.put(DataBaseContract.DataStats.STAT15, saveGame.stats[15]);
        values.put(DataBaseContract.DataStats.STAT16, saveGame.stats[16]);
        values.put(DataBaseContract.DataStats.STAT17, saveGame.stats[17]);
        values.put(DataBaseContract.DataStats.STAT18, saveGame.stats[18]);
        values.put(DataBaseContract.DataStats.STAT19, saveGame.stats[19]);
        values.put(DataBaseContract.DataStats.STAT20, saveGame.stats[20]);
        values.put(DataBaseContract.DataStats.STAT21, saveGame.stats[21]);
        values.put(DataBaseContract.DataStats.STAT22, saveGame.stats[22]);
        values.put(DataBaseContract.DataStats.STAT23, saveGame.stats[23]);
        values.put(DataBaseContract.DataStats.STAT24, saveGame.stats[24]);
        values.put(DataBaseContract.DataStats.STAT25, saveGame.stats[25]);
        values.put(DataBaseContract.DataStats.STAT26, saveGame.stats[26]);
        values.put(DataBaseContract.DataStats.STAT27, saveGame.stats[27]);
        values.put(DataBaseContract.DataStats.STAT28, saveGame.stats[28]);
        values.put(DataBaseContract.DataStats.STAT29, saveGame.stats[29]);
        values.put(DataBaseContract.DataStats.STAT30, saveGame.stats[30]);
        values.put(DataBaseContract.DataStats.STAT31, saveGame.stats[31]);
        values.put(DataBaseContract.DataStats.STAT32, saveGame.stats[32]);
        values.put(DataBaseContract.DataStats.STAT33, saveGame.stats[33]);
        values.put(DataBaseContract.DataStats.STAT34, saveGame.stats[34]);
        values.put(DataBaseContract.DataStats.STAT35, saveGame.stats[35]);
        values.put(DataBaseContract.DataStats.STAT36, saveGame.stats[36]);
        values.put(DataBaseContract.DataStats.STAT37, saveGame.stats[37]);
        values.put(DataBaseContract.DataStats.STAT38, saveGame.stats[38]);
        values.put(DataBaseContract.DataStats.STAT39, saveGame.stats[39]);
        values.put(DataBaseContract.DataStats.STAT40, saveGame.stats[40]);
        values.put(DataBaseContract.DataStats.STAT41, saveGame.stats[41]);
        values.put(DataBaseContract.DataStats.STAT42, saveGame.stats[42]);
        values.put(DataBaseContract.DataStats.STAT43, saveGame.stats[43]);
        values.put(DataBaseContract.DataStats.STAT44, saveGame.stats[44]);
        values.put(DataBaseContract.DataStats.STAT45, saveGame.stats[45]);
        values.put(DataBaseContract.DataStats.STAT46, saveGame.stats[46]);
        values.put(DataBaseContract.DataStats.STAT47, saveGame.stats[47]);
        values.put(DataBaseContract.DataStats.STAT48, saveGame.stats[48]);
        values.put(DataBaseContract.DataStats.STAT49, saveGame.stats[49]);
        values.put(DataBaseContract.DataStats.STAT50, saveGame.stats[50]);
        values.put(DataBaseContract.DataStats.STAT51, saveGame.stats[51]);
        values.put(DataBaseContract.DataStats.STAT52, saveGame.stats[52]);
        values.put(DataBaseContract.DataStats.STAT53, saveGame.stats[53]);
        values.put(DataBaseContract.DataStats.STAT54, saveGame.stats[54]);
        values.put(DataBaseContract.DataStats.STAT55, saveGame.stats[55]);
        values.put(DataBaseContract.DataStats.STAT56, saveGame.stats[56]);
        values.put(DataBaseContract.DataStats.STAT57, saveGame.stats[57]);
        values.put(DataBaseContract.DataStats.STAT58, saveGame.stats[58]);
        values.put(DataBaseContract.DataStats.STAT59, saveGame.stats[59]);
        values.put(DataBaseContract.DataStats.COLUMN_PLAYER_ID, saveGame.playerId);

        values.put(DataBaseContract.DataStats.COLUMN_PLAYER_ID, newPlayerId);
        selection = DataBaseContract.DataStats.COLUMN_PLAYER_ID + " = ?";
        whereArgs = new String []{oldPlayerId};

        myDataBase.update(
                DataBaseContract.DataStats.TABLE_NAME,
                values,
                selection,
                whereArgs);

        close();

        //Log.e(TAG, "updatePlayerId depois");
        logAllDatabase();


    }
}
