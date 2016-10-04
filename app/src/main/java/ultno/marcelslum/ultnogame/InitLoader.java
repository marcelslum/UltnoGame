private class InitLoader extends AsyncTask<void, void, void> {
      protected void doInBackground() {
                  
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
     }

     protected void onProgressUpdate() {
     }

     protected void onPostExecute() {
        Game.loaderConclude = true;
     }
 }
