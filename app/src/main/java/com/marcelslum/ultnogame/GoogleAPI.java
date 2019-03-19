package com.marcelslum.ultnogame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by marcel on 10/10/2016.
 */


//g04537291955427667933 - MARCELSLUM
// g10188873398780278626 - RAIDING


public class GoogleAPI {

    public static final int RC_LEADERBOARD_UI = 9004;
    public static final int RC_ACHIEVEMENTS_UI = 9005;
    public static final int RC_SIGN_IN = 9001;
    public static final int RC_SAVED_GAMES = 9009;
    public static final String TAG = "GoogleApi";

    public static AchievementsClient mAchievementsClient;
    public static LeaderboardsClient mLeaderboardsClient;
    public static SnapshotsClient mSnapshotsClient;
    public static PlayersClient mPlayersClient;
    public static GoogleSignInClient mGoogleSignInClient;


    public static ArrayList<AchievementData> achievementsData = new ArrayList<>();
    public static String playerName = "-";
    public static String playerId;
    public static boolean isConnected;
    public static ImageBitmap playerIconImage = null;
    public static boolean disconnecting = false;
    public static boolean connecting = false;

    public static void hideGoogleInfo(){
        if (MessagesHandler.messageGoogleLogged != null) MessagesHandler.messageGoogleLogged.clearDisplay();
        if (playerIconImage != null) playerIconImage.clearDisplay();
    }

    public static void displayGoogleInfo(){
        if (MessagesHandler.messageGoogleLogged != null) MessagesHandler.messageGoogleLogged.display();
        if (playerIconImage != null) playerIconImage.display();
    }

    public static void connectGoogle(){
        Game.mainActivity.startSignInIntent();
    }

    public static void disconnectGoogle(){
        Game.mainActivity.signOut();
        MessagesHandler.setBottomMessage(Game.getContext().getResources().getString(R.string.message_google_desconectado), 4000);
    }

    public static void configureGoogleInfo(MyVIewModel.PlayerData playerData) {
        Log.e(TAG, "configureGoogleInfo2");
        Game.forUpdatePlayerData = true;
    }

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

    public static void showSnapshots() {
        if (Game.mainActivity.isSignedIn()){

            int maxNumberOfSavedGamesToShow = 5;
            Task<Intent> intentTask = mSnapshotsClient.getSelectSnapshotIntent(
                    Game.mainActivity.getResources().getString(R.string.ver_saves), true, true, maxNumberOfSavedGamesToShow);

            intentTask.addOnSuccessListener(new OnSuccessListener<Intent>() {
                @Override
                public void onSuccess(Intent intent) {
                    Game.mainActivity.startActivityForResult(intent, RC_SAVED_GAMES);
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
                    //Log.e(TAG, "Conquista "+ad.name + " step "+ ad.currentSteps + " total " + ad.totalSteps);
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

                        Log.e(TAG, "carregando achievement " + ab.get(i).getName() + " current steps: " + ab.get(i).getCurrentSteps() + " total steps: " + ab.get(i).getTotalSteps() + " ");

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
                //Log.e(TAG, "Não foi possível carregar os achievements do servidor");
                achievementsData.clear();
            }
        });
    }

    public static void increment(String  id, int value) {

        if (Game.mainActivity.isSignedIn()) {

            final String innerId = id;
            final int innerValue = value;

            for (int i = 0; i < achievementsData.size(); i++) {
                if (achievementsData.get(i).id.equals(id) && (achievementsData.get(i).currentSteps < achievementsData.get(i).totalSteps)){
                    AchievementData ad = achievementsData.get(i);
                    ad.currentSteps += value;
                    mAchievementsClient.increment(innerId, innerValue);
                    if (ad.currentSteps >= ad.totalSteps) {
                        Game.messagesToDisplay.add(Game.getContext().getResources().getString(R.string.conquistaDesbloqueada) + " " + ad.name+Game.getContext().getResources().getString(R.string.exclamacao_tripla));
                        Log.e(TAG, "conquistado achievement " + innerId);
                    }
                    break;
                }
            }
        }
    }

    public static void submitScore(final String id, long value){

        Log.e(TAG, "submitScore " + id + " pontuação " + value);

        if (Game.mainActivity.isSignedIn() && mLeaderboardsClient != null) {
           Log.e(TAG, "Game.mainActivity.isSignedIn() && mLeaderboardsClient != null submitScore "+value);
           mLeaderboardsClient.submitScoreImmediate(id, value).addOnSuccessListener(new OnSuccessListener<ScoreSubmissionData>() {
               @Override
               public void onSuccess(ScoreSubmissionData scoreSubmissionData) {
                   Log.e(TAG, "Score carregado");
               }
           });



        }

    }


}
