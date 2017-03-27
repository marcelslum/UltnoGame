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




     public ArrayList<BallDataBaseData> getBalls(int level){
         myDataBase = openDataBase();

          String[] projection = {
                 DataBaseContract.Balls.COLUMN_RADIUS,
                 DataBaseContract.Balls.COLUMN_X,
                 DataBaseContract.Balls.COLUMN_Y,
                 DataBaseContract.Balls.COLUMN_VX,
                 DataBaseContract.Balls.COLUMN_VY,
                 DataBaseContract.Balls.COLUMN_TEXTURE_MAP,
                 DataBaseContract.Balls.COLUMN_INVENCIBLE,
                 DataBaseContract.Balls.COLUMN_ANGLE_TO_ROTATE,
                 DataBaseContract.Balls.COLUMN_MAX_AGLE,
                 DataBaseContract.Balls.COLUMN_MIN_ANGLE,
                 DataBaseContract.Balls.COLUMN_VELOCITY_VARIATION,
                 DataBaseContract.Balls.COLUMN_MAX_VELOCITY,
                 DataBaseContract.Balls.COLUMN_MIN_VELOCITY,
                 DataBaseContract.Balls.COLUMN_FREE
             };

             String selection =
                     DataBaseContract.Balls.COLUMN_LEVEL + " = "+level;

             //String[] selectionArgs = {String.valueOf(levelNumber)};
             Cursor cursor = myDataBase.query(
                     DataBaseContract.Balls.TABLE_NAME,        // The table to query
                     projection,                               // The columns to return
                     selection,                                // The columns for the WHERE clause
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
            levelsPoints[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_POINTS));
            levelsStars[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_STARS));
            levelsUnlocked[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_UNLOCKED)) == 1 ? true : false;
            levelsSeen[i] = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataLevels.COLUMN_SEEN)) == 1 ? true : false;
         }

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

         int[] tutorialsSeen = new int[Tutorial.NUMBER_OF_TUTORIALS];





        return list;
     }
     
     public ArrayList<BarDataBaseData> getBars(int level){
          myDataBase = openDataBase();
          String[] projection = {
                 DataBaseContract.Bars.COLUMN_WIDTH,
                 DataBaseContract.Bars.COLUMN_HEIGHT,
                 DataBaseContract.Bars.COLUMN_X,
                 DataBaseContract.Bars.COLUMN_Y,
                 DataBaseContract.Bars.COLUMN_VX
                 
             };

             String selection =
                     DataBaseContract.Bars.COLUMN_LEVEL + " = "+level;

             //String[] selectionArgs = {String.valueOf(levelNumber)};
             Cursor cursor = myDataBase.query(
                     DataBaseContract.Bars.TABLE_NAME,        // The table to query
                     projection,                               // The columns to return
                     selection,                                // The columns for the WHERE clause
                     null,                            // The values for the WHERE clause
                     null,                                     // don't group the rows
                     null,                                     // don't filter by row groups
                     null                                      // don't sort
             );

          ArrayList<BarDataBaseData> list = new ArrayList<>();
          while(cursor.moveToNext()){
               BarDataBaseData b = new BarDataBaseData(
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Bars.COLUMN_WIDTH)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Bars.COLUMN_HEIGHT)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Bars.COLUMN_X)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Bars.COLUMN_Y)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Bars.COLUMN_VX))
               );
               list.add(b);  
          }
          close();
  		return list;
     }
     
     public ArrayList<TargetDataBaseData> getTargets(int id){
         myDataBase = openDataBase();
          String[] projection = {
                 DataBaseContract.Targets.COLUMN_WIDTH,
                 DataBaseContract.Targets.COLUMN_HEIGHT,
                 DataBaseContract.Targets.COLUMN_DISTANCE,
                 DataBaseContract.Targets.COLUMN_PADD
                 
             };

             String selection =
                     DataBaseContract.Targets._ID + " = "+id;

             //String[] selectionArgs = {String.valueOf(levelNumber)};
             Cursor cursor = myDataBase.query(
                     DataBaseContract.Targets.TABLE_NAME,        // The table to query
                     projection,                               // The columns to return
                     selection,                                // The columns for the WHERE clause
                     null,                            // The values for the WHERE clause
                     null,                                     // don't group the rows
                     null,                                     // don't filter by row groups
                     null                                      // don't sort
             );

          ArrayList<TargetDataBaseData> list = new ArrayList<>();
          while(cursor.moveToNext()){
               TargetDataBaseData b = new TargetDataBaseData(
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Targets.COLUMN_WIDTH)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Targets.COLUMN_HEIGHT)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Targets.COLUMN_DISTANCE)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Targets.COLUMN_PADD))
               );
               list.add(b);
          }
          close();
  		return list;
     }



    public ArrayList<GroupDataBaseData> getGroupsDataBaseData(){
        myDataBase = openDataBase();
        String[] projection = {
                DataBaseContract.Groups.COLUMN_NUMBER,
                DataBaseContract.Groups.COLUMN_LEVELS,
                DataBaseContract.Groups.COLUMN_STARS_TO_UNLOCK
        };


        Cursor cursor = myDataBase.query(
                DataBaseContract.Groups.TABLE_NAME,      // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        ArrayList<GroupDataBaseData> list = new ArrayList<>();
        while(cursor.moveToNext()){
            GroupDataBaseData b = new GroupDataBaseData(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Groups.COLUMN_NUMBER)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Groups.COLUMN_LEVELS)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Groups.COLUMN_STARS_TO_UNLOCK))
            );
            list.add(b);
        }
        close();
        return list;
    }

    public ArrayList<LevelDataBaseData> getLevelsDataBaseData(){
        myDataBase = openDataBase();
        String[] projection = {
                DataBaseContract.Levels.COLUMN_NUMBER,
                DataBaseContract.Levels.COLUMN_GROUP,
                DataBaseContract.Levels.COLUMN_MIN_BALLS_ALIVE
        };

        Cursor cursor = myDataBase.query(
                DataBaseContract.Levels.TABLE_NAME,      // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        ArrayList<LevelDataBaseData> list = new ArrayList<>();
        while(cursor.moveToNext()){
            LevelDataBaseData b = new LevelDataBaseData(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Levels.COLUMN_NUMBER)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Levels.COLUMN_GROUP)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Levels.COLUMN_MIN_BALLS_ALIVE))
            );
            list.add(b);
        }
        close();
        return list;
    }
}
