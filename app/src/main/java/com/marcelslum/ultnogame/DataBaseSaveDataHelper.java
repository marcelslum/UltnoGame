package com.marcelslum.ultnogame;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class DataBaseSaveDataHelper extends DataBaseHelper {

    private static DataBaseSaveDataHelper mInstance = null;

    private final String TAG = "DataBaseSaveDataHelper";
    private final String DB_NAME = "save.db";

    private DataBaseSaveDataHelper(Context context) {
        super(context, DB_NAME, Integer.valueOf(context.getResources().getString(R.string.databaseLevelVersion)));
    }

     public static DataBaseLevelDataHelper getInstance(Context ctx){
        if (mInstance == null){
            mInstance = new DataBaseSaveDataHelper(ctx)
        }
        return mInstance
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
         int[] levelsUnlocked = new int[Level.NUMBER_OF_LEVELS];
         int[] levelsSeen = new int[Level.NUMBER_OF_LEVELS];

         int i = 0;
         while(cursor.moveToNext()){
             if (i = levelsPoints.lenght){
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
        String[] projection = {
                 DataBaseContract.DataTutorials.COLUMN_NUMBER,
                 DataBaseContract.DataTutorials.COLUMN_SEEN
             };

         cursor = myDataBase.query(
                 DataBaseContract.DataTutorials.TABLE_NAME,        // The table to query
                 projection,                               // The columns to return
                 null,                                // The columns for the WHERE clause
                 null,                            // The values for the WHERE clause
                 null,                                     // don't group the rows
                 null,                                     // don't filter by row groups
                 null                                      // don't sort
        );

        int[] tutorialsSeen = new int[Tutorial.NUMBER_OF_TUTORIALS];
 
        i = 0;
        while(cursor.moveToNext()){
             if (i = levelsPoints.lenght){
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
        saveGameBuilder.setTutorialsSeen(tutorialsSeen);
        
        
        // DADOS GERAIS
        
        String[] projection = {
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
            DataBaseContract.Data.COLUMN_LEVELS_PLAYED
        };
        
        String selection =
                     DataBaseContract.Targets._ID + " = 1";

         cursor = myDataBase.query(
                 DataBaseContract.Data.TABLE_NAME,          // The table to query
                 projection,                                // The columns to return
                 selection,                                 // The columns for the WHERE clause
                 null,                                      // The values for the WHERE clause
                 null,                                      // don't group the rows
                 null,                                      // don't filter by row groups
                 null                                       // don't sort
        );
        
        while(cursor.moveToNext()){
            saveGameBuilder
                .setDate(cursor.getLong(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_DATE)))
                .setMusic(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_MUSIC)) == 1 ? true : false)
                .setSound(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_SOUND)) == 1 ? true : false)
                .setVibration(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_VIBRATION)) == 1 ? true : false)
                .setCurrentLevelNumber(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_CURRENT_LEVEL)))
                .setCurrentGroupNumber(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_CURRENT_GROUP)))
                .setCurrentGroupMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_GROUP_MENU_TRANSLATE_X)))
                .setCurrentLevelMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_LEVEL_MENU_TRANSLATE_X)))
                .setCurrentTutorialMenuTranslateX(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_TUTORIAL_MENU_TRANSLATE_X)))
                .setLastStars(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_LAST_STARS)))
                .setNewGroupsSeen(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_NEW_GROUPS_SEEN)) == 1 ? true : false)
                .setLevelsPlayed(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_LEVELS_PLAYED)))
                break;
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
    
    public void saveDataFromSaveGame(SaveGame saveGame){
        myDataBase = getWritable();  
        ContentValues values = new ContentValues();
            values.put(DataBaseContract.DataTutorials.COLUMN_DATE, saveGame.date);
            values.put(DataBaseContract.DataTutorials.COLUMN_CURRENT_LEVEL, saveGame.currentLevelNumber);
            values.put(DataBaseContract.DataTutorials.COLUMN_CURRENT_GROUP, saveGame.currentGroupNumber);
            values.put(DataBaseContract.DataTutorials.COLUMN_MUSIC, saveGame.music ? 1 : 0);
            values.put(DataBaseContract.DataTutorials.COLUMN_SOUND, saveGame.sound ? 1 : 0);
            values.put(DataBaseContract.DataTutorials.COLUMN_VIBRATION, saveGame.vibration ? 1 : 0);
            values.put(DataBaseContract.DataTutorials.COLUMN_GROUP_MENU_TRANSLATE_X, saveGame.currentGroupMenuTranslateX);
            values.put(DataBaseContract.DataTutorials.COLUMN_LEVEL_MENU_TRANSLATE_X, saveGame.currentLevelMenuTranslateX);
            values.put(DataBaseContract.DataTutorials.COLUMN_TUTORIAL_MENU_TRANSLATE_X, saveGame.currentTutorialMenuTranslateX);
            values.put(DataBaseContract.DataTutorials.COLUMN_LAST_STARS, saveGame.lastStars);
            values.put(DataBaseContract.DataTutorials.COLUMN_NEW_GROUPS_SEEN, saveGame.newGroupsSeen ? 1 : 0);
            values.put(DataBaseContract.DataTutorials.COLUMN_LEVELS_PLAYED, saveGame.levelsPlayed);
        
        String selection = DataBaseContract.Data.COLUMN_ID + " LIKE 1";
        
        myDataBase.update(
            DataBaseContract.Data.TABLE_NAME,
            values,
            selection,
            null);
    }
   
}
