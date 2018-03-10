package com.marcelslum.ultnogame;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.EventsClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by marcel on 10/10/2016.
 */
public class GoogleAPI {

    private static final int RC_LEADERBOARD_UI = 9004;
    private static final int RC_ACHIEVEMENTS_UI = 9005;
    public static final int RC_SIGN_IN = 9001;

    public static AchievementsClient mAchievementsClient;
    public static LeaderboardsClient mLeaderboardsClient;
    public static EventsClient mEventsClient;
    public static PlayersClient mPlayersClient;
    public static GoogleSignInClient mGoogleSignInClient;

    public static void showAchievements() {
        if (Game.mainActivity.isSignedIn()){
            mAchievementsClient.getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                @Override
                public void onSuccess(Intent intent) {
                    Game.mainActivity.startActivityForResult(intent, RC_ACHIEVEMENTS_UI);
                }
            });
        }
    }

    public static void showLeaderboards(String id) {
        if (Game.mainActivity.isSignedIn()){
            mLeaderboardsClient.getLeaderboardIntent(id)
                    .addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            Game.mainActivity.startActivityForResult(intent, RC_LEADERBOARD_UI);
                        }
                    });
        }
    }

    public static void unlockAchievement(String  id) {
        if (Game.mainActivity.isSignedIn()) {
            mAchievementsClient.unlock(id);
        }
    }

    public static void increment(String  id, int value) {

        if (Game.mainActivity.isSignedIn()) {
            mAchievementsClient.increment(id, value);
        }
    }

    public static void submitScore(String id, long value){

        if (Game.mainActivity.isSignedIn()) {
            mLeaderboardsClient.submitScore(id, value);
        }

    }


}
