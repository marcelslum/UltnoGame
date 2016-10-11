package com.marcelslum.ultnogame;

/**
 * Created by marcel on 11/10/2016.
 */

public class Achievements {
    
    public static final ACHIEVEMENT_TYPE_INCREMENTAL = 0;
    public static final ACHIEVEMENT_TYPE_NORMAL = 0;
    
    ArrayList<Achievement> achievements;

    public static init(){
        achievements = new ArraList<>;
    }

    public static addAchievement(type int)



    private class Achievement(){
        int type;
        String name;
        int id;
        int totalSteps;
        int stepsConquered;

        Achievement(String name, int id){
            this.name = name;
            this.id = id;
            this.totalSteps = 1;
            this.type = ACHIEVEMENT_TYPE_NORMAL;
            checkStorage();
        }


        Achievement(String name, int id, int totalSteps){
            this.name = name;
            this.id = id;
            this.totalSteps = totalSteps;
            if (totalSteps > 1){
                this.type = ACHIEVEMENT_TYPE_INCREMENTAL;
            } else {
                this.type = ACHIEVEMENT_TYPE_NORMAL;
            }
            checkStorage();
        }
        
        private void checkStorage(){
            if (!Storage.contains(id)){
                Storage.setInt(id, 0);
            } else {
                stepsConquered = Storage.getInt(id);
            }
        }
        
        

    }

}
