package com.marcelslum.ultnogame;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.achievement.Achievement;
import java.util.ArrayList;

/**
 * Created by marcel on 11/10/2016.
 */

public class MyAchievements {

    public static final String TAG = "MyAchievements";
    public static final int TYPE_INCREMENTAL = Achievement.TYPE_INCREMENTAL;
    public static final int TYPE_STANDARD = Achievement.TYPE_STANDARD;
    public static boolean loaded = false;
    
    static ArrayList<MyAchievement> achievements;

    public static MyAchievement getById(String id){

        if (achievements == null){
            achievements = new ArrayList<>();
        }

        for (int i = 0; i < achievements.size(); i++){

            Log.e("Achievements", i + " id " + achievements.get(i).id);

            if (id.equals(achievements.get(i).id)){
                Log.e("Achievements", i + " returning id " + achievements.get(i).id);
                return achievements.get(i);
            }
        }
        Log.e("Achievements", " returning null ");
        return null;
    }
    
    public static MyAchievement getById(int id){

        if (achievements == null){
            achievements = new ArrayList<>();
        }

        for (int i = 0; i < achievements.size(); i++){
            Log.e("Achievements", i + " id " + achievements.get(i).id);
            if (Game.mainActivity.getResources().getString(id).equals(achievements.get(i).id)){
                Log.e("Achievements", i + " returning id " + achievements.get(i).id);
                return achievements.get(i);
            }
        }

        Log.e("Achievements", " returning null ");

        return null;
    }



    public static void add(Achievement ach){
        MyAchievement a;

        if (getById(ach.getAchievementId()) != null){
            return;
        }

        Log.e(TAG, "1");
        int type = ach.getType();
        if (type == Achievement.TYPE_STANDARD){
            Log.e(TAG, "2 "+type);
            a = new MyAchievement(ach.getName(), ach.getAchievementId());
            Log.e(TAG, "3 name "+ach.getName() + " id "+ ach.getAchievementId());
            if (ach.getState() == Achievement.STATE_UNLOCKED){
                a.setCurrentSteps(1);
            } else {
                a.setCurrentSteps(0);
            }
            Log.e(TAG, "4");
        } else {

            a = new MyAchievement(ach.getName(), ach.getAchievementId(), ach.getTotalSteps());
            Log.e(TAG, "5 name "+ach.getName() + " id "+ ach.getAchievementId());
            Log.e(TAG, "5 total steps "+ach.getTotalSteps() + " currentSteps "+ ach.getCurrentSteps());
            if (ach.getState() == Achievement.STATE_UNLOCKED){
                a.currentSteps = a.totalSteps;   
            } else {
                a.currentSteps = ach.getCurrentSteps();
            }
            Log.e(TAG, "6");
        }
        
        if (achievements == null){
            achievements = new ArrayList<>();
        }
        achievements.add(a);
    }

    public static void increment(GoogleApiClient mGoogleApiClient, int id, int value) {

        MyAchievement a = getById(id);
        if (a != null){
            a.increment(mGoogleApiClient, value);
        } else {
            Log.e("MyAchievements", "nao foi possível incrementar a conquista, pois ela não foi encontrada");
        }


    }
}
