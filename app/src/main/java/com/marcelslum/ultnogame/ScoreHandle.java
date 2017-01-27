package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class ScoreHandle {
    public static final int POINTS_DECAY = 1;
    static final long TIME_FOR_POINTS_DECAY = 2000;
    public static long maxScoreTotal;
    static ScorePanel scorePanel;

    static void setMaxScoreTotal(){
        long scoreTotal = getMaxScoreTotal();
        GooglePlayGames.submitScore(Game.mainActivity.mGoogleApiClient, Game.mainActivity.getResources().getString(R.string.leaderboard_ranking), scoreTotal);
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
}
