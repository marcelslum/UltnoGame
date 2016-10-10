package com.marcelslum.ultnogame;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

/**
 * Created by marcel on 10/10/2016.
 */
public class GooglePlayGames {

    private static GooglePlayGames ourInstance = new GooglePlayGames();
    private GoogleApiClient mGoogleApiClient;
    private FragmentActivity fragmentActivity;
    private static final int REQUEST_ACHIEVEMENTS = 2001;
    private static final int REQUEST_LEADERBOARD = 3001;

    public static GooglePlayGames getInstance() {
        return ourInstance;
    }

    private GooglePlayGames() {

    }

    public void init(GoogleApiClient _mGoogleApiClient, FragmentActivity _fragmentActivity) {
        mGoogleApiClient = _mGoogleApiClient;
        fragmentActivity = _fragmentActivity;
    }

    public void showAchievements() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            // Call a Play Games services API method, for example:
            Games.Achievements.unlock(mGoogleApiClient, "CgkIjNyO58cTEAIQAQ");

            Intent intent = Games.Achievements.getAchievementsIntent(mGoogleApiClient);

            fragmentActivity.startActivityForResult(intent,
                    REQUEST_ACHIEVEMENTS);
        } else {
            // Alternative implementation (or warn user that they must
            // sign in to use this feature)
        }
    }

    public void showLeaderboards() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            fragmentActivity.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient,
                    "CgkIjNyO58cTEAIQAg"), REQUEST_LEADERBOARD);
        } else {
            Log.e("mainActivity", "não conectado");
        }
    }

    public void submitScore() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, "CgkIjNyO58cTEAIQAg", 12333);
        } else {
            Log.e("mainActivity", "não conectado");

            // Alternative implementation (or warn user that they must sign in to use this feature)
        }
    }

    public void connectMGoogleApiClient() {
        Log.e("mainActivity", "mGoogleApiClient.connect();");
        mGoogleApiClient.connect();
    }

    public void onRequestResult(int requestCode, int resultCode) {
        if(requestCode==REQUEST_ACHIEVEMENTS||requestCode==REQUEST_LEADERBOARD) {
            Log.e("MainActivity", "onActivityResult REQUEST_ACHIEVEMENTS or REQUEST_LEADERBOARD " + resultCode);
        }
    }
}
