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
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 12));
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
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(3, LevelGoal.FINISH_LEVEL_WITHOUT_CHANGE_SPEED, 0));
        }

        ln += 1; // ---------- LEVEL 22
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
        }

        ln += 1; // ---------- LEVEL 23
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
        }

        ln += 1; // ---------- LEVEL 24
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 6));
        }

        ln += 1; // ---------- LEVEL 25
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
        }

        ln += 1; // ---------- LEVEL 26
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
        }

        ln += 1; // ---------- LEVEL 27
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 3));
        }

        ln += 1; // ---------- LEVEL 28
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 120));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 29
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 120));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 30
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
        }

        ln += 1; // ---------- LEVEL 31
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 240));
            lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 6));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_RIGHT_BORDER_TOUCH, 0));
        }

        ln += 1; // ---------- LEVEL 32
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE, 15));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 7));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 5));
        }

        ln += 1; // ---------- LEVEL 33
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 10));
        }

        ln += 1; // ---------- LEVEL 34
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 6));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 6));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 35
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 6));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 6));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 36
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 3));
        }

        ln += 1; // ---------- LEVEL 37
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 3, 6));
        }

        ln += 1; // ---------- LEVEL 38
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 39
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
        }

        ln += 1; // ---------- LEVEL 40
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 90));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 10));
        }

        // BARRA DIMINUINDO
        ln += 1; // ---------- LEVEL 41
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 125));
            lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 3, 8));
        }
        
        ln += 1; // ---------- LEVEL 42
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0)); 
        }
        
        ln += 1; // ---------- LEVEL 43
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES, 3));
        }
        
        ln += 1; // ---------- LEVEL 44
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES, 3));
        }

        // BOLA FALSA
        ln += 1; // ---------- LEVEL 45
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(4, LevelGoal.FINISH_IN_N_SECONDS, 200));
        }

        ln += 1; // ---------- LEVEL 46
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(3, LevelGoal.FINISH_IN_N_SECONDS, 200));
        }

        ln += 1; // ---------- LEVEL 47
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 20));
        }

        ln += 1; // ---------- LEVEL 48
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 20));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
        }

        ln += 1; // ---------- LEVEL 49
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(2, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 20));
        }
        
        // ESPELHO
        ln += 1; // ---------- LEVEL 50
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 10));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
        }
        
        ln += 1; // ---------- LEVEL 51
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 10));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }
        
        ln += 1; // ---------- LEVEL 52
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
        }
        
        ln += 1; // ---------- LEVEL 53
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
        }
        
        // MURO E DUAS BARRAS
        ln += 1; // ---------- LEVEL 54
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 330));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS, 5)); 
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 10));
        }
        
        ln += 1; // ---------- LEVEL 55
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 35));
           lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
        }
        
        ln += 1; // ---------- LEVEL 56
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 40));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 8));
        }
        
        ln += 1; // ---------- LEVEL 57
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
        }
        
        
        ln += 1; // ---------- LEVEL 58
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 4));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 4)); 
        }
        
        // MURO E DUAS BOLAS
        ln += 1; // ---------- LEVEL 59
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
        }
        
        ln += 1; // ---------- LEVEL 60
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }
        
        ln += 1; // ---------- LEVEL 61
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 5));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
        }
        
        ln += 1; // ---------- LEVEL 62
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 5));
           lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
        }

        ln += 1; // ---------- LEVEL 63
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 12));
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_N_TIMES, 12));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 5));
        }
        
        ln += 1; // ---------- LEVEL 64
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.PREVENT_RIGHT_BORDER_TOUCH, 0));
        }
        
        ln += 1; // ---------- LEVEL 65
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES, 10));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
           lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
        }
        
        ln += 1; // ---------- LEVEL 66
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
        }
        
        ln += 1; // ---------- LEVEL 67
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
        }
        
        ln += 1; // ---------- LEVEL 68
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE, 10));
           lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
        }
        
        ln += 1; // ---------- LEVEL 69
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 5, 7));
        }

        ln += 1; // ---------- LEVEL 70
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 5, 8));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE, 10));
        }
        
        ln += 1; // ---------- LEVEL ++++
        if (levelNumber >= ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_N_TIMES, 4));
        }

        return lg;
    }

}
