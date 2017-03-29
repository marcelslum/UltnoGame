package com.marcelslum.ultnogame;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class DataBaseHelper extends SQLiteOpenHelper {

    private final static String TAG = "DataBaseLevelDataHelper";
    protected String DB_PATH;// = "/data/data/com.marcelslum.ultno/databases/";
    protected String DB_NAME;
    protected SQLiteDatabase myDataBase;
    protected Context myContext;
    protected int version;

    public DataBaseHelper(Context context, String dbName, int version) {
        super(context, dbName, null, version);
        this.myContext = context;
        this.version = version;
        this.DB_NAME = dbName;
        this.DB_PATH = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
    }

    public void prepareDatabase() throws IOException {

        //deleteDataBase();

        boolean dbExist = checkDataBase();
        SQLiteDatabase db_Read = null;
        if(dbExist){
           Log.e(TAG, DB_NAME + " banco de dados já existe");
           int currentDBVersion = getVersionId();
           Log.e(TAG, DB_NAME + " currentDBVersion "+currentDBVersion);
              if (version > currentDBVersion) {
                  Log.d(TAG, DB_NAME + " Database version is higher than old.");
                  deleteDataBase();
                     try {
                         copyDataBase();
                      } catch (IOException e) {
                          Log.e(TAG, e.getMessage());
                      }
              }
        }else{
            db_Read = getReadableDatabase();
            db_Read.close();
            try {
                Log.e(TAG, DB_NAME + " copiando database");
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }
     
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            Log.e(TAG, DB_NAME + " verificando se existe o banco de dados na pasta ");
            checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            Log.e(TAG, DB_NAME + " banco de dados não existe ainda ");
        }
        if(checkDB != null){
            Log.e(TAG, DB_NAME + " banco de dados já existe ");
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }
     
    private void copyDataBase() throws IOException{
           //Open your local db as the input stream
           InputStream myInput = myContext.getAssets().open(DB_NAME);
           //Open the empty db as the output stream
           OutputStream myOutput = new FileOutputStream(DB_PATH);
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
        File file = new File(DB_PATH);
        if(file.exists()) {
              file.delete();
              Log.d(TAG, "Banco de dados " + DB_NAME + " deletado.");
        }
    }
     
     public SQLiteDatabase openDataBase() throws SQLException {
         if (myDataBase == null || !myDataBase.isOpen()){
            myDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
         }
         return myDataBase;
     }
    
     public SQLiteDatabase getWritable() throws SQLException {
         openDataBase();
         myDataBase = getWritableDatabase();
         return myDataBase;
     }
     
     protected int getVersionId() {
        openDataBase();
        String query = "SELECT version_id FROM dbVersion";
        Cursor cursor = myDataBase.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);
        return v;
     }

    public boolean isNew() {
        openDataBase();
        String query = "SELECT isNew FROM dbVersion";
        Cursor cursor = myDataBase.rawQuery(query, null);
        cursor.moveToFirst();
        int v =  cursor.getInt(0);

        return v == 1 ? true : false;
    }
     
     @Override
    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }
     
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
     
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
