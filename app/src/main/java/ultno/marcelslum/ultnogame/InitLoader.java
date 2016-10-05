package ultno.marcelslum.ultnogame;
import android.os.AsyncTask;
import android.util.Log;

public class InitLoader extends AsyncTask<Integer , Integer, Integer> {

    protected Integer doInBackground(Integer... i){
        Log.e("initLoader", "doInBackground");
        Game.initData();
        Storage.initializeStorage(Game.context, Game.quantityOfLevels);
        Game.maxLevel = Storage.getMaxLevel();
        Game.difficulty = Storage.getDificulty();
        Game.changeDifficulty(Game.difficulty);
        Game.levelNumber = Storage.getActualLevel();
        Game.initTime = Utils.getTime();
        Game.initTextures();
        Sound.init();
        Game.initMenus();
        Game.initTexts();
        Game.initEdges();
        Game.frame = new Rectangle("frame", 0f, 0f, Game.resolutionX, Game.resolutionY, -1, new Color(0f, 0f, 0f, 1f));
        Game.frame.clearDisplay();
        Game.frame.alpha = 0f;
        return 0;
    }


    protected void onProgressUpdate() {
    }


    protected void onPostExecute(Integer i) {
        Log.e("initloader", " onPostExecute");
        Game.loaderConclude = true;

     }
 }
