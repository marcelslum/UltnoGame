package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Storage {
    private static Storage ourInstance = new Storage();
    public static SharedPreferences storage;
    private static Context context;
    final static String STORAGE_FILE_NAME = "ultno.marcelslum.ultnogame.storage2";

    public static Storage getInstance() {
        return ourInstance;
    }

    private Storage() {
    }

    
    public static void setInt(String key, int value){
          SharedPreferences.Editor editor = storage.edit();
          editor.putInt(key, value);
          editor.apply();
    }
    
    public static int getInt(String key){
        return storage.getInt(key, -1);
    }
    
    public static void setBoolean(String key, boolean value){
          SharedPreferences.Editor editor = storage.edit();
          editor.putBoolean(key, value);
          editor.apply();
    }
    
    public static boolean getBoolean(String key){
        return storage.getBoolean(key, false);
    }
    
    public static boolean contains(String key){
        return storage.contains(key);
    }
    
    public static void initializeStorage(Context context, int quantityOfLevels){
        storage = context.getSharedPreferences(STORAGE_FILE_NAME, 0);
        for (int i = 0; i < quantityOfLevels; i++){
            int levelToTest = i + 1;
            if (!Storage.contains("tutorial"+ levelToTest +"visto")) {
                Log.e("Storage", "not contains tutorial visto level " + levelToTest);
                Storage.setBoolean("tutorial" + levelToTest + "visto", false);
            }
            if (!Storage.contains("score"+levelToTest)) {
                Log.e("Storage", "score level " + levelToTest);
                Storage.setInt("score" + levelToTest, 0);
            }
        }
        
        if (!Storage.contains("actualLevel"))
                Storage.setInt("actualLevel", 1);
        
        if (!Storage.contains("maxLevel"))
                Storage.setInt("maxLevel", 1);
    }
    
    public static int getMaxLevel(){
        return getInt("maxLevel");
    }
    
    public static void setMaxLevel(int value){
        setInt("maxLevel", value);
    }
    
    public static int getActualLevel(){
        return  getInt("actualLevel");
    }
    
    public static void setActualLevel(int value){
        setInt("actualLevel", value);
    }
    
    public static int getLevelMaxScore(int levelNumber){
        return  getInt("score"+levelNumber);
    }
    
    public static void setLevelMaxScore(int levelNumber, int value){
        setInt("score"+levelNumber, value);
    }
    
    public static boolean getLevelTutorialSaw(int levelNumber){
        Log.e("Storage", "tutorial saw level "+levelNumber+ ": " +(getBoolean("tutorial"+ levelNumber +"visto")));
        return  getBoolean("tutorial"+ levelNumber +"visto");
    }
    
    public static void setLevelTutorialSaw(int levelNumber, boolean value){
        Storage.setBoolean("tutorial"+ levelNumber +"visto", value);
    }
    
}
