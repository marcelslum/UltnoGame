package ultno.marcelslum.ultnogame;

public class Storage {
    private static Utils ourInstance = new Utils();
    SharedPreferences storage;
    final static String STORAGE_FILE_NAME = "ultno.marcelslum.ultnogame.storage"

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }
    
    public void init(){
        SharedPreferences storage = getSharedPreferences(STORAGE_FILE_NAME, 0);
    }
    
    public static void setInt(String key, int value){
        if (storage == null) init();
          SharedPreferences.Editor editor = storage.edit();
          editor.putInt(key, value);
          editor.apply();
    }
    
    public static int getInt(String key){
        if (storage == null) init();
        return storage.getInt(key, -1);
    }
    
    public static void setBoolean(String key, boolean value){
        if (storage == null) init();
          SharedPreferences.Editor editor = storage.edit();
          editor.putBoolean(key, value);
          editor.apply();
    }
    
    public static int getBoolean(String key){
        if (storage == null) init();
        return storage.getBoolean(key, false);
    }
    
    public static boolean contains(String key){
        if (storage == null) init();
        return storage.contains(String key);
    }
    
    public void initializeStorage(int quantityOfLevels){
        if (storage == null) init();
        for (var i = 0; i < quantityOfLevels; i++){
            int levelToTest = i + 1;
            if (!Storage.contains("tutorial"+ levelToTest +"visto"))
                Storage.setBoolean("tutorial"+ levelToTest +"visto", false);
            if (!Storage.contains("score"+levelToTest))
                Storage.setInt("score"+levelToTest, 0);
        }
        
        if (!Storage.contains("actualLevel"))
                Storage.setInt("actualLevel", 1);
        
        if (!Storage.contains("maxLevel"))
                Storage.setInt("maxLevel", 1);
    }
    
    public static int getMaxLevel(){
        return getInt("maxLevel");
    }
    
    public static int setMaxLevel(int value){
        setInt("maxLevel", value);
    }
    
    public static int getActualLevel(){
        return  getInt("actualLevel");
    }
    
    public static int setActualLevel(int value){
        setInt("actualLevel", value);
    }
    
    public static int getLevelMaxScore(int levelNumber){
        return  getInt("score"+levelNumber);
    }
    
    public static int setLevelMaxScore(int levelNumber, int value){
        setInt("score"+levelNumber, value);
    }
    
    public static boolean getLevelTutorialSaw(int levelNumber){
        return  getBoolean("tutorial"+ levelNumber +"visto");
    }
    
    public static boolean setLevelTutorialSaw(int levelNumber, boolean value){
        Storage.setBoolean("tutorial"+ levelNumber +"visto", false);
    }
    
}
