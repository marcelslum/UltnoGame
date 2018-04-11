package com.marcelslum.ultnogame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.EventsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by marcel on 10/10/2016.
 */
public class GoogleAPI {

    private static final int RC_LEADERBOARD_UI = 9004;
    private static final int RC_ACHIEVEMENTS_UI = 9005;
    public static final int RC_SIGN_IN = 9001;
    public static final String TAG = "GoogleApi";

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

    static AchievementBuffer achievementBuffer;
    public static void loadAchievements(){
        mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
            @Override
            public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                AnnotatedData<AchievementBuffer> ad = task.getResult();
                achievementBuffer = ad.get();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                achievementBuffer = null;
            }
        });
    }

    public static void increment(String  id, int value) {

        if (Game.mainActivity.isSignedIn()) {

            final String innerId = id;
            final int innerValue = value;


            boolean increment = false;
            for (int i = 0; i < achievementBuffer.getCount(); i++) {
                if (achievementBuffer.get(i).getAchievementId().equals(id) && achievementBuffer.get(i).getState() != Achievement.STATE_UNLOCKED){
                    increment = true;
                    break;
                }
            }

            // TODO Fazer um cache das conquistas antes do level e só verificar se a conquista foi desbloqueada quanto os passos forem realmente concluidos
            if (increment) {
                mAchievementsClient.incrementImmediate(innerId, innerValue).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
                            @Override
                            public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                                AnnotatedData<AchievementBuffer> ad = task.getResult();
                                AchievementBuffer ab = ad.get();
                                for (int i = 0; i < ab.getCount(); i++) {
                                    if (ab.get(i).getAchievementId().equals(innerId) && ab.get(i).getState() == Achievement.STATE_UNLOCKED){
                                       Game.messagesToDisplay.add("Conquista debloqueada");
                                    }
                                }
                                ab.release();
                            }
                        });
                    }
                });
            }
        }
    }

    public static void submitScore(String id, long value){

        if (Game.mainActivity.isSignedIn()) {
            Log.e(TAG, "submitScore "+value);
            mLeaderboardsClient.submitScore(id, value);
        }

    }


}
