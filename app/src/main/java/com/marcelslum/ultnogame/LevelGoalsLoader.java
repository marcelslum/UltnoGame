package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoalsLoader {

    public static ArrayList<LevelGoal> getLevelGoals(int levelNumber){
        ArrayList<LevelGoal> lg = new ArrayList<>();

        int ln = 1;  // ---------- LEVEL 1
        if (levelNumber == ln) {
            lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
            //lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 3));
            //lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES, 3));
        }

        ln += 1; // ---------- LEVEL 2
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 3
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 4
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 6));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 5
        if (levelNumber >= ln) {
            lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
        }

        return lg;
    }

}
