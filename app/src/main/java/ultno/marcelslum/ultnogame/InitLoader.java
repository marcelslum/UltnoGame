private class InitLoader extends AsyncTask<URL, Integer, Long> {
      protected Long doInBackground(URL... urls) {
         
      Game.initData();
      Storage.initializeStorage(context, quantityOfLevels);
      Game.maxLevel = Storage.getMaxLevel();
      Game.difficulty = Storage.getDificulty();
      Game.changeDifficulty(difficulty);
      Game.levelNumber = Storage.getActualLevel();
      Game.initTime = Utils.getTime();
      Game.initTextures();
      Sound.init();
      Game.initFont();
      Game.initMenus();
      Game.initTexts();
      Game.initEdges();
      Game.frame = new Rectangle("frame", 0f, 0f, resolutionX, resolutionY, -1, new Color(0f, 0f, 0f, 1f));
      Game.frame.clearDisplay();
      Game.frame.alpha = 0f;

      return totalSize;
     }

     protected void onProgressUpdate(Integer... progress) {
         setProgressPercent(progress[0]);
     }

     protected void onPostExecute(Long result) {
         showDialog("Downloaded " + result + " bytes");
     }
 }
