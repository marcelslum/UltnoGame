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
}
