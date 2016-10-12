package com.marcelslum.ultnogame;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by marcel on 11/10/2016.
 */

public class MyAchievement{
        int type;
        String name;
        String id;
        int totalSteps;
        int currentSteps;

        MyAchievement(String name, String id){
            this.name = name;
            this.id = id;
            this.totalSteps = 1;
            this.type = MyAchievements.TYPE_STANDARD;
        }


        MyAchievement(String name, String id, int totalSteps){
            this.name = name;
            this.id = id;
            this.totalSteps = totalSteps;
            if (totalSteps > 1){
                this.type = MyAchievements.TYPE_INCREMENTAL;
            } else {
                this.type = MyAchievements.TYPE_STANDARD;
            }
        }

        public void increment(GoogleApiClient mGoogleApiClient, int value){
            if (type == MyAchievements.TYPE_STANDARD){
                if  (currentSteps == 0){
                    currentSteps += 1;
                    GooglePlayGames.unlockAchievement(mGoogleApiClient, id);
                }
            } else {
                if (currentSteps < totalSteps){
                    currentSteps += value;
                    GooglePlayGames.increment(mGoogleApiClient, id, value);
                }
            }
        }

        public void setCurrentSteps(int currentSteps){
            this.currentSteps = currentSteps;
        }
}
