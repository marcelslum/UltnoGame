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

import java.util.ArrayList;

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


    public static ArrayList<AchievementData> achievementsData = new ArrayList<>();

    public static class AchievementData{
        public String name;
        public String id;
        public int currentSteps;
        public int totalSteps;
        public int type;

        public static final int TYPE_INCREMENTAL = 0;
        public static final int TYPE_NOT_INCREMENTAL = 1;

        public AchievementData(String name, String id, int currentSteps, int totalSteps, int type) {
            this.name = name;
            this.id = id;
            this.currentSteps = currentSteps;
            this.totalSteps = totalSteps;
            this.type = type;
        }
    }

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

            final String innerId = id;

            AchievementData ad = null;
            boolean unlock = false;

            for (int i = 0; i < achievementsData.size(); i++) {
                if (achievementsData.get(i).id.equals(id) && (achievementsData.get(i).currentSteps < achievementsData.get(i).totalSteps)){
                    ad = achievementsData.get(i);
                    ad.currentSteps += 1;
                    unlock = true;
                    Log.e(TAG, "Conquista "+ad.name + " step "+ ad.currentSteps + " total " + ad.totalSteps);
                    break;
                }
            }

            if (!unlock) return;

            final AchievementData innerAd = ad;

            mAchievementsClient.unlockImmediate(id).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                        mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
                            @Override
                            public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {



                                if (innerAd != null && innerAd.currentSteps == innerAd.totalSteps) {
                                    mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                                            AnnotatedData<AchievementBuffer> ad = task.getResult();
                                            AchievementBuffer ab = ad.get();
                                            for (int i = 0; i < ab.getCount(); i++) {
                                                if (ab.get(i).getAchievementId().equals(innerId) && ab.get(i).getState() == Achievement.STATE_UNLOCKED) {
                                                    Game.messagesToDisplay.add(Game.getContext().getResources().getString(R.string.conquistaDesbloqueada) + " " + innerAd.name);
                                                    Game.sound.playSuccess1();
                                                }
                                            }
                                            ab.release();
                                        }
                                    });
                                }
                            }
                        });
                }
            });
        }
    }


    public static void loadAchievements(){

        if (mAchievementsClient == null){
            return;
        }

        mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
            @Override
            public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                AnnotatedData<AchievementBuffer> ad = task.getResult();
                AchievementBuffer ab = ad.get();
                achievementsData.clear();
                for (int i = 0; i < ab.getCount(); i++) {
                    if (ab.get(i).getType() == Achievement.TYPE_INCREMENTAL) {
                        achievementsData.add(new AchievementData(ab.get(i).getName(), ab.get(i).getAchievementId(), ab.get(i).getCurrentSteps(), ab.get(i).getTotalSteps(), AchievementData.TYPE_INCREMENTAL));
                    } else {
                        achievementsData.add(new AchievementData(ab.get(i).getName(), ab.get(i).getAchievementId(), ab.get(i).getState() == Achievement.STATE_UNLOCKED ? 1 : 0, 1, AchievementData.TYPE_INCREMENTAL));
                    }
                }
                ab.release();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Não foi possível carregar os achievements do servidor");
                achievementsData.clear();
            }
        });
    }

    public static void increment(String  id, int value) {

        if (Game.mainActivity.isSignedIn()) {

            final String innerId = id;
            final int innerValue = value;

            boolean increment = false;

            AchievementData ad = null;
            for (int i = 0; i < achievementsData.size(); i++) {
                if (achievementsData.get(i).id.equals(id) && (achievementsData.get(i).currentSteps < achievementsData.get(i).totalSteps)){
                    ad = achievementsData.get(i);
                    ad.currentSteps += value;
                    Log.e(TAG, "Conquista "+ad.name + " step "+ ad.currentSteps + " total " + ad.totalSteps);
                    increment = true;
                    break;
                }
            }

            final AchievementData innerAd = ad;

            // TODO Fazer um cache das conquistas antes do level e só verificar se a conquista foi desbloqueada quanto os passos forem realmente concluidos
            if (increment) {
                mAchievementsClient.incrementImmediate(innerId, innerValue).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {

                        if (innerAd != null && innerAd.currentSteps == innerAd.totalSteps) {
                            mAchievementsClient.load(true).addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
                                @Override
                                public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {
                                    AnnotatedData<AchievementBuffer> ad = task.getResult();
                                    AchievementBuffer ab = ad.get();
                                    for (int i = 0; i < ab.getCount(); i++) {
                                        if (ab.get(i).getAchievementId().equals(innerId) && ab.get(i).getState() == Achievement.STATE_UNLOCKED) {
                                            Game.messagesToDisplay.add(Game.getContext().getResources().getString(R.string.conquistaDesbloqueada) + " " + innerAd.name);
                                            Game.sound.playSuccess1();
                                        }
                                    }
                                    ab.release();
                                }
                            });
                        }
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
