package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class ScoreHandler {
    public static final int POINTS_DECAY = 1;
    static final long TIME_FOR_POINTS_DECAY = 2000;
    public static long maxScoreTotal;
    static ScorePanel scorePanel;

    static void setMaxScoreTotal(){
        long scoreTotal = getMaxScoreTotal();
        maxScoreTotal = scoreTotal;
    }

    static long getMaxScoreTotal(){
        //Log.e("Game", "getMaxScoreTotal");
        long scoreTotal = 0;
        for (int i = 0; i < Level.maxNumberOfLevels; i++){
            scoreTotal += SaveGame.saveGame.pointsLevels[i];
            //Log.e("Game", "level "+(i+1)+ " pontos "+SaveGame.saveGame.pointsLevels[i]);
            //Log.e("Game", "scoreTotal "+scoreTotal);

        }

        for (int i = 0; i < Level.numberOfSecretLevels; i++){
            scoreTotal += SaveGame.saveGame.pointsSecretLevels[i];
            //Log.e("Game", "level "+(i+1)+ " pontos "+SaveGame.saveGame.pointsLevels[i]);
            //Log.e("Game", "scoreTotal "+scoreTotal);
        }

        maxScoreTotal = scoreTotal;
        //Log.e("Game", "score total retornando após calculo "+ scoreTotal);
        return scoreTotal;
    }

    static void verifyScoreDecay(){
        long time = Utils.getTime();
        if ((time - Game.initialTimePointsDecay)> TIME_FOR_POINTS_DECAY){
            if (scorePanel.value > POINTS_DECAY) {
                Game.initialTimePointsDecay = time;
                int value = scorePanel.value - POINTS_DECAY;
                scorePanel.setValue(value, false, 0, false);
            }
        }
    }

    public static void createScorePanel() {
        ScoreHandler.scorePanel = new ScorePanel("scorePanel",
                Game.gameAreaResolutionX * 0.5f, Game.gameAreaResolutionY * 1.047f, Game.resolutionY * 0.07f);
    }

    public static float getScorePanelX() {
        return ScoreHandler.scorePanel.x + (ScoreHandler.scorePanel.getWidth()*0.014f);
    }

    public static float getScorePanelWidth() {
        return ScoreHandler.scorePanel.getWidth() - (ScoreHandler.scorePanel.getWidth()*0.035f);
    }

    public static void submitScores() {

        GooglePlayGames.submitScore(Game.mainActivity.mGoogleApiClient, Game.mainActivity.getResources().getString(R.string.leaderboard_geral), maxScoreTotal);

        if (SaveGame.saveGame.currentLevelNumber >= 1000){
            return;
        }

        int totalPointsGroup = 0;
        for (int i = 0; i < Game.currentLevelsGroupDataSelected.levelsData.size(); i++){
                totalPointsGroup += SaveGame.saveGame.pointsLevels[Game.currentLevelsGroupDataSelected.levelsData.get(i).number-1];
        }

        String id;
        switch (Game.currentLevelsGroupDataSelected.number){
            case 1:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_1);
                break;
            case 2:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_2);
                break;
            case 3:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_3);
                break;
            case 4:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_4);
                break;
            case 5:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_5);
                break;
            case 6:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_6);
                break;
            case 7:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_7);
                break;
            case 8:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_8);
                break;
            case 9:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_9);
                break;
            case 10:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_10);
                break;
            case 11:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_11);
                break;
            case 12:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_12);
                break;
            case 13:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_13);
                break;
            case 14:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_14);
                break;
            case 15:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_15);
                break;
            case 16:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_16);
                break;
            case 17:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_17);
                break;
            case 18:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_18);
                break;
            case 19:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_19);
                break;
            case 20:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_20);
                break;
            default:
                id = Game.mainActivity.getResources().getString(R.string.leaderboard_1);
                break;
        }
        GooglePlayGames.submitScore(Game.mainActivity.mGoogleApiClient, id, totalPointsGroup);
    }
}
