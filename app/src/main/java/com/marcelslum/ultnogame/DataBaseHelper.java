package com.marcelslum.ultnogame;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataBaseHelper extends SQLiteOpenHelper {
     
        private final static String TAG = "DataBaseHelper"
     
        //The Android's default system path of your application database.
        private static String DB_PATH;// = "/data/data/com.marcelslum.ultno/databases/";
        private static String DB_NAME = "ultno_alpha_test.db";
        private SQLiteDatabase myDataBase;
        private final Context myContext;
     
        /**
         * Constructor
         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
         * @param context
         */
        public DataBaseHelper(Context context) {
        	super(context, DB_NAME, null, context.getResources().getInteger(R.string.databaseVersion));
          myContext = context;  
          DB_PATH = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        }	
     
          /**
         * Creates a empty database on the system and rewrites it with your own database.
         * */
        public void prepareDatabase() throws IOException {
     
        	boolean dbExist = checkDataBase();
          SQLiteDatabase db_Read = null;
     
        	if(dbExist){
               Log.e(TAG, "banco de dados já existe");
        		//do nothing - database already exist
               
               int currentDBVersion = getVersionId();
                  if (DATABASE_VERSION > currentDBVersion) {
                      Log.d(TAG, "Database version is higher than old.");
                      deleteDb();
                         try {
                    	     copyDataBase();
        		          } catch (IOException e) {
    				          Log.e(TAG, e.getMessage());
    			          }
                  }
               
        	}else{
        		//By calling this method and empty database will be created into the default system path
                   //of your application so we are gonna be able to overwrite that database with our database.
            	db_Read = getReadableDatabase();
               db_Read.close();
     
            	try {
        			copyDataBase();
        		} catch (IOException e) {
            		Log.e(TAG, e.getMessage());
            	}
        	}
        }
     
        /**
         * Check if the database already exist to avoid re-copying the file each time you open the application.
         * @return true if it exists, false if it doesn't
         */
        private boolean checkDataBase(){
               SQLiteDatabase checkDB = null;
               try{
                    String myPath = DB_PATH + DB_NAME;
                    checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
                    Log.e(TAG, "verificando se existe o banco de dados na pasta " + myPath);
               }catch(SQLiteException e){
                    //database does't exist yet.
                    Log.e(TAG, "banco de dados não existe ainda ");
               }
               if(checkDB != null){
                    Log.e(TAG, "banco de dados já existe ");
                    checkDB.close();
               }
               return checkDB != null ? true : false;
        }
     
        /**
         * Copies your database from your local assets-folder to the just created empty database in the
         * system folder, from where it can be accessed and handled.
         * This is done by transfering bytestream.
         * */
        private void copyDataBase() throws IOException{
               //Open your local db as the input stream
               InputStream myInput = myContext.getAssets().open(DB_NAME);
               //Open the empty db as the output stream
               OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
               //transfer bytes from the inputfile to the outputfile
               byte[] buffer = new byte[1024];
               int length;
               while ((length = myInput.read(buffer))>0){
                    myOutput.write(buffer, 0, length);
               }
               //Close the streams
               myOutput.flush();
               myOutput.close();
               myInput.close();
        }
     
     
      public void deleteDataBase() {
            File file = new File(DB_PATH + DB_NAME);
            if(file.exists()) {
                  file.delete();
                  Log.d(TAG, "Banco de dados " + DB_NAME + " deletado.");
            }
      }
     
     public void openDataBase() throws SQLException {
          String myPath = DB_PATH + DB_NAME;
        	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
     
     }
     
     private int getVersionId() {
          openDataBase(); 
 		String query = "SELECT version_id FROM dbVersion";
  		Cursor cursor = myDataBase.rawQuery(query, null);
  		cursor.moveToFirst();
  		int v =  cursor.getInt(0);
  		db.close();
  		return v; 
     }
     
     @Override
    	public synchronized void close() {
        	    if(myDataBase != null)
        		    myDataBase.close();
        	    super.close();
    	}
     
    	@Override
    	public void onCreate(SQLiteDatabase db) {
     
    	}
     
    	@Override
    	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          //db.execSQL("DROP TABLE IF EXISTS " + SampleDBContract.Employer.TABLE_NAME);
          //onCreate(sqLiteDatabase); 
    	}
     
            // Add your public helper methods to access and get content from the database.
           // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
           // to you to create adapters for your views.
     
     
     public ArrayList<BallDataBaseData> getBalls(int level){
          openDataBase();
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
                     DataBaseContract.Balls.COLUMN_LEVEL + " = "+levelNumber;

             //String[] selectionArgs = {String.valueOf(levelNumber)};
             Cursor cursor = myDatabase.query(
                     DataBaseContract.Balls.TABLE_NAME,        // The table to query
                     projection,                               // The columns to return
                     selection,                                // The columns for the WHERE clause
                     null,                            // The values for the WHERE clause
                     null,                                     // don't group the rows
                     null,                                     // don't filter by row groups
                     null                                      // don't sort
             );

             
          ArrayList<BallDataBaseData> list = new ArrayList<>();
          while(cursor.moveToNext()){
               BallDataBaseData b = new BallDataBaseData(
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_RADIUS)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_X)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_X)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_VX)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_VY)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_TEXTURE_MAP)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_INVENCIBLE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_ANGLE_TO_ROTATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_MAX_AGLE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_MIN_ANGLE)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_VELOCITY_VARIATION)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_MAX_VELOCITY)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_MIN_VELOCITY)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_FREE))
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_X)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(DataBaseContract.Balls.COLUMN_X))
               )
               list.add(b);  
          }
          
          close();
  		return list
     }
     
     
    }
