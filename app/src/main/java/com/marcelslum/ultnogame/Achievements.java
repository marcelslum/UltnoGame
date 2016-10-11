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
        
        

        Achievement(String name, int id){
            this.name = name;
            this.id = id;
            this.totalSteps = 1;
            this.type = ACHIEVEMENT_TYPE_NORMAL;
            
            verifyStorage();
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
        }
        
        

    }

}
