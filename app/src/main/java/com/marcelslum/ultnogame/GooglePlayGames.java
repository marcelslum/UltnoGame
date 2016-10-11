package com.marcelslum.ultnogame;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.achievement.Achievements;

/**
 * Created by marcel on 10/10/2016.
 */
public class GooglePlayGames {

    private static GooglePlayGames ourInstance = new GooglePlayGames();
    private static final int REQUEST_ACHIEVEMENTS = 2001;
    private static final int REQUEST_LEADERBOARD = 3001;

    public static void showAchievements(GoogleApiClient mGoogleApiClient, FragmentActivity fragmentActivity) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            // Call a Play Games services API method, for example:
            Intent intent = Games.Achievements.getAchievementsIntent(mGoogleApiClient);
            fragmentActivity.startActivityForResult(intent,
                    REQUEST_ACHIEVEMENTS);
        } else {
            // Alternative implementation (or warn user that they must
            // sign in to use this feature)
        }
    }

    public static void showLeaderboards(GoogleApiClient mGoogleApiClient, FragmentActivity fragmentActivity) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            fragmentActivity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                    Game.mainActivity.getResources().getString(R.string.leaderboard_ranking)), REQUEST_LEADERBOARD);
        } else {
            Game.setGameState(Game.GAME_STATE_INTRO);
        }
    }

    public static void submitScore(GoogleApiClient mGoogleApiClient, int score) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, Game.mainActivity.getResources().getString(R.string.leaderboard_ranking), score);
        } else {
            Game.setGameState(Game.GAME_STATE_INTRO);
        }
    }

    public static void connectMGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        Log.e("mainActivity", "mGoogleApiClient.connect();");
        mGoogleApiClient.connect();
    }

    public static void onRequestResult(int requestCode, int resultCode) {
        if(requestCode==REQUEST_ACHIEVEMENTS||requestCode==REQUEST_LEADERBOARD) {
            Log.e("MainActivity", "onActivityResult REQUEST_ACHIEVEMENTS or REQUEST_LEADERBOARD " + resultCode);
        }
    }

    public static void unlockAchievement(GoogleApiClient mGoogleApiClient, int id) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Achievements.unlock(mGoogleApiClient, Game.mainActivity.getResources().getString(id));
        } else {
            // Alternative implementation (or warn user that they must sign in to use this feature)
        }
    }

    public static void incrementAchievement(GoogleApiClient mGoogleApiClient, int id, int value) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Achievements.setStepsImmediate(mGoogleApiClient, Game.mainActivity.getResources().getString(id), value)
                    .setResultCallback(new  ResultCallback<Achievements.UpdateAchievementResult>() {
                        @Override
                        public void onResult(Achievements.UpdateAchievementResult result) {
                            if (result.getStatus().getStatusCode() == GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCKED) {
                                // play your sound. achievement was unlocked
                            }
                        }



                    });


        }
    }

}