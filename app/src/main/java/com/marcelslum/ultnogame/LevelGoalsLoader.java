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
            lg.add(new LevelGoal(3, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 4));

        }

        ln += 1; // ---------- LEVEL 2
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECELERATE_N_TIMES, 4));

        }

        ln += 1; // ---------- LEVEL 3
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 4
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 5
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_N_TIMES, 4));
        }

        ln += 1; // ---------- LEVEL 6
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.INCREASE_ANGLE_N_TIMES, 4));
        }

        ln += 1; // ---------- LEVEL 7
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_N_TIMES, 5));
            lg.add(new LevelGoal(3, LevelGoal.REACH_MAXIMUN_ANGLE, 0));
        }

        ln += 1; // ---------- LEVEL 8
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 5));
            lg.add(new LevelGoal(3, LevelGoal.REACH_MINIMUN_ANGLE, 0));
        }

        ln += 1; // ---------- LEVEL 9
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 6));
        }

        ln += 1; // ---------- LEVEL 10
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 10));
        }

        ln += 1; // ---------- LEVEL 11
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 3));
        }

        ln += 1; // ---------- LEVEL 12
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 3));
        }

        ln += 1; // ---------- LEVEL 13
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 8));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 14
        if (levelNumber == ln) {
            lg.add(new LevelGoal(3, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 12));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 15
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 16
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
        }

        ln += 1; // ---------- LEVEL 17
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 12));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 6));
        }

        ln += 1; // ---------- LEVEL 18
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 6));
        }

        ln += 1; // ---------- LEVEL 19
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 12));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 8));
        }

        ln += 1; // ---------- LEVEL 20
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 8));
        }

        ln += 1; // ---------- LEVEL 21
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(3, LevelGoal.FINISH_LEVEL_WITHOUT_CHANGE_SPEED, 0));
        }

        ln += 1; // ---------- LEVEL 22
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
        }

        ln += 1; // ---------- LEVEL 23
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
        }

        ln += 1; // ---------- LEVEL 24
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
        }

        ln += 1; // ---------- LEVEL 25
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 3));
        }



        ln += 1; // ---------- LEVEL ++++
        if (levelNumber >= ln) {
            lg.add(new LevelGoal(3, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_N_TIMES, 4));
        }

        return lg;
    }

}
