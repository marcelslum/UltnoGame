package com.marcelslum.ultnogame;

/**
 * Created by marcel on 11/10/2016.
 */

public class Achievements {
    
    public static final TYPE_INCREMENTAL = Game.Achievement.TYPE_INCREMENTAL;
    public static final TYPE_STANDARD = Game.Achievement.TYPE_STANDARD;
    
    ArrayList<MyAchievement> achievements;

    public static init(){
        achievements = new ArraList<>;
    }
    
    public static MyAchievement getById(String id){
        for (int i = 0; i < achievements.size(); i++){
            if (id == achievements.get(i).id){
                return achievements.get(i);
            }
        }
        return null;
    }
    
    public static MyAchievement getById(int id){
        for (int i = 0; i < achievements.size(); i++){
            if (Game.mainActivity.getResources.getString(id) == achievements.get(i).id){
                return achievements.get(i);
            }
        }
        return null;
    }

    public static add(Games.Achievement ach){
        
        int type = ach.getType();
        if (type = TYPE_STANDARD){
            MyAchievement a = new MyAchievement(ach.getName, ach.getAchievementId);
            if (ach.getState() == Games.Achievement.STATE_UNLOCKED){
                a.setCurrentSteps = 1;
            } else {
                a.setCurrentSteps = 0;
            }
        } else {
            MyAchievement a = new MyAchievement(ach.getName, ach.getAchievementId, ach.getTotalSteps());
            if (ach.getState() == Games.Achievement.STATE_UNLOCKED){
                a.currentSteps = a.totalSteps;   
            } else {
                a.currentSteps = a.getCurrentSteps;                
            }
        }
        
        if (achievements == null){
            achievements = new ArrayList<MyAchievement>;   
        }
        
        achievements.add(a);
    }

    private class MyAchievement(){
        int type;
        String name;
        String id;
        int totalSteps;
        int currentSteps;

        MyAchievement(String name, String id){
            this.name = name;
            this.id = id;
            this.totalSteps = 1;
            this.type = TYPE_STANDARD;
        }


        MyAchievement(String name, String id, int totalSteps){
            this.name = name;
            this.id = id;
            this.totalSteps = totalSteps;
            if (totalSteps > 1){
                this.type = TYPE_INCREMENTAL;
            } else {
                this.type = TYPE_STANDARD;
            }
        }
        
        public void increment(GoogleApiClient mGoogleApiClient, int value){
            if (type == TYPE_STANDARD){
                if  (currentSteps == 0){
                    currentSteps += 1;
                    GooglePlayGames.unlockAchievement(mGoogleApiClient, id);
                }
            } else {
                if (currentSteps < totalSteps){
                    currentSteps += value;
                    GooglePlayGames.setSteps(mGoogleApiClient, id, currentSteps);
                }
            }
        }
        
        public void setCurrentSteps(int currentSteps){
            this.currentSteps = currentSteps;
        }
       
    }

}
