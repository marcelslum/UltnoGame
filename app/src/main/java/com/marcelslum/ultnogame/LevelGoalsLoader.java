package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoalsLoader {

    public static ArrayList<LevelGoal> getLevelGoals(int levelNumber){
        ArrayList<LevelGoal> lg = new ArrayList<>();
        switch (levelNumber){
            case 1:
                lg.add(new LevelGoal(3, LevelGoal.JUST_FINISH, 0));
                lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 1));
                break;
            case 2:
                lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
                lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 3));
                lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
                lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
                lg.add(new LevelGoal(1, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 6));
                break;
            case 3:
                lg.add(new LevelGoal(3, LevelGoal.JUST_FINISH, 0));
                lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 2));
                break;
            default:
                lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
                break;
        }
        return lg;
    }

}
