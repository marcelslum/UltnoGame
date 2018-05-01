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
        Log.e(TAG, "Upgrade version");
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
                DataBaseContract.DataLevels.COLUMN_NUMBER + " = " + l;

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

    public SaveGame getSaveGame(){
        openDataBase();

        
        SaveGameBuilder saveGameBuilder = new SaveGameBuilder();
        
        // DADOS LEVELS
       String[] projection = {
                 DataBaseContract.DataLevels.COLUMN_NUMBER,
                 DataBaseContract.DataLevels.COLUMN_POINTS,
                 DataBaseContract.DataLevels.COLUMN_STARS,
                 DataBaseContract.DataLevels.COLUMN_UNLOCKED,
                 DataBaseContract.DataLevels.COLUMN_SEEN,
             };

         Cursor cursor = myDataBase.query(
                 DataBaseContract.DataLevels.TABLE_NAME,        // The table to query
                 projection,                               // The columns to return
                 null,                                // The columns for the WHERE clause
                 null,                            // The values for the WHERE clause
                 null,                                     // don't group the rows
                 null,                                     // don't filter by row groups
                 null                                      // don't sort
        );
       

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

        // DADOS TUTORIALS
        String[] projection2 = {
                 DataBaseContract.DataTutorials.COLUMN_NUMBER,
                 DataBaseContract.DataTutorials.COLUMN_SEEN
             };

         cursor = myDataBase.query(
                 DataBaseContract.DataTutorials.TABLE_NAME,        // The table to query
                 projection2,                               // The columns to return
                 null,                                // The columns for the WHERE clause
                 null,                            // The values for the WHERE clause
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

        // DADOS TUTORIALS
        String[] projection4 = {
                DataBaseContract.DataGroups.COLUMN_NUMBER,
                DataBaseContract.DataGroups.COLUMN_SEEN
        };

        cursor = myDataBase.query(
                DataBaseContract.DataGroups.TABLE_NAME,        // The table to query
                projection4,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
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
        
        
        // DADOS GERAIS
        
        String[] projection3 = {
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
        
        String selection =
                     DataBaseContract.Targets._ID + " = 1";

         cursor = myDataBase.query(
                 DataBaseContract.Data.TABLE_NAME,          // The table to query
                 projection3,                                // The columns to return
                 selection,                                 // The columns for the WHERE clause
                 null,                                      // The values for the WHERE clause
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



        // DADOS GERAIS, CAMPOS NOVOS

        String[] projection5 = {
                DataBaseContract.Data.COLUMN_SAVE_MENU_SEEN
        };
        String selection2 =
                DataBaseContract.Targets._ID + " = 1";
        try {

            cursor = myDataBase.query(
                    DataBaseContract.Data.TABLE_NAME,          // The table to query
                    projection3,                                // The columns to return
                    selection,                                 // The columns for the WHERE clause
                    null,                                      // The values for the WHERE clause
                    null,                                      // don't group the rows
                    null,                                      // don't filter by row groups
                    null                                       // don't sort
            );

            while (cursor.moveToNext()) {
                saveGameBuilder.setBallVelocity(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Data.COLUMN_BALL_VELOCITY)));
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

        return saveGameBuilder.build();
    }


    public void setLevelPoints(int number, int v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_POINTS, v);
        String selection = DataBaseContract.DataLevels.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
            DataBaseContract.DataLevels.TABLE_NAME,
            values,
            selection,
            selectionArgs);
    }
    
    public void setLevelStars(int number, int v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_STARS, v);
        String selection = DataBaseContract.DataLevels.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
            DataBaseContract.DataLevels.TABLE_NAME,
            values,
            selection,
            selectionArgs);
    }
    
    public void setLevelUnlocked(int number, boolean v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_UNLOCKED, v ? 1 : 0);
        String selection = DataBaseContract.DataLevels.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
            DataBaseContract.DataLevels.TABLE_NAME,
            values,
            selection,
            selectionArgs);
    }
    
    public void setLevelSeen(int number, boolean v){
        myDataBase = getWritable();        
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_SEEN, v ? 1 : 0);
        String selection = DataBaseContract.DataLevels.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
            DataBaseContract.DataLevels.TABLE_NAME,
            values,
            selection,
            selectionArgs);
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
        String selection = DataBaseContract.DataGroups.COLUMN_NUMBER + " LIKE ?";
        String[] selectionArgs = new String[]{Integer.toString(number)};
        myDataBase.update(
                DataBaseContract.DataGroups.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
    
    public void saveDataFromSaveGame(SaveGame saveGame){
        myDataBase = getWritable();

        Log.e(TAG, "saveDataFromSaveGame");

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
        
        String selection = DataBaseContract.Data._ID + " LIKE 1";
        
        myDataBase.update(
            DataBaseContract.Data.TABLE_NAME,
            values,
            selection,
            null);

        try{
            values = new ContentValues();
            values.put(DataBaseContract.Data.COLUMN_ORIENTATION_INVERTED, saveGame.orientationInverted ? 1 : 0);
            selection = DataBaseContract.Data._ID + " LIKE 1";
            myDataBase.update(DataBaseContract.Data.TABLE_NAME,values, selection,null);
        }catch(SQLiteException e){
            Log.e(TAG, "não foi possível salvar no banco de dados a opção orientationInverted");
        }


        for (int i = 0; i < saveGame.levelsPoints.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataLevels.COLUMN_POINTS, saveGame.levelsPoints[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_STARS, saveGame.levelsStars[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_SEEN, saveGame.levelsSeen[i]);
            values.put(DataBaseContract.DataLevels.COLUMN_UNLOCKED, saveGame.levelsUnlocked[i]);
            selection = DataBaseContract.Data._ID + " LIKE " + String.valueOf(i + 1);
            myDataBase.update(
                    DataBaseContract.DataLevels.TABLE_NAME,
                    values,
                    selection,
                    null);
        }

        for (int i = 0; i < saveGame.tutorialsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataTutorials.COLUMN_SEEN, saveGame.tutorialsSeen[i]);
            selection = DataBaseContract.Data._ID + " LIKE " + String.valueOf(i + 1);
            myDataBase.update(
                    DataBaseContract.DataTutorials.TABLE_NAME,
                    values,
                    selection,
                    null);
        }

        for (int i = 0; i < saveGame.groupsSeen.length; i++){
            values = new ContentValues();
            values.put(DataBaseContract.DataGroups.COLUMN_SEEN, saveGame.groupsSeen[i]);
            selection = DataBaseContract.Data._ID + " LIKE " + String.valueOf(i + 1);
            myDataBase.update(
                    DataBaseContract.DataGroups.TABLE_NAME,
                    values,
                    selection,
                    null);
        }

        String query = "SELECT googleOption FROM data";
        Cursor cursor = getWritable().rawQuery(query, null);
        cursor.moveToFirst();

        //Log.e(TAG, "saveDataFromSaveGame googleOption da base"+ cursor.getInt(0));
    }
   
}
