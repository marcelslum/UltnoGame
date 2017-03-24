public class TargetDataBaseData{
    public float width;
    public float height;
    public float distance;
    public float padd;
    public final static String TAG = "TargetDataBaseData";
    
    public void logData(){
        Log.e(TAG, "width "+width);   
        Log.e(TAG, "height "+height);   
        Log.e(TAG, "distance "+distance);   
        Log.e(TAG, "padd "+padd);    
    }
}
