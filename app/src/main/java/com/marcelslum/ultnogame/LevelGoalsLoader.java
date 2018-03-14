package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoalsLoader {


    private static ArrayList<Integer> tipsViewed;
    final static String TAG = "LevelGoalsLoader";
    
    public static String getLevelTip(int levelNumber){


        if (tipsViewed == null){
            tipsViewed = new ArrayList<>();
        }

        String tip = "";

        ArrayList<Integer> possibleTipsList = new ArrayList(){};
        int[] possibleTips;

        switch (levelNumber){
            case 1:
                possible
                possibleTips = new int[]{5,5,5,7,8,8,8,1,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,28};
                break;

            case 2:
                possibleTips = new int[]{6,6,6,7,9,9,9,1,2,3,3,3,3,3,3,3,3,4,10,11,12,13,14,15,16,17,28};
                break;
                
            case 3:
                possibleTips = new int[]{6,6,6,7,9,9,9,1,2,3,3,3,3,3,3,3,3,4,10,11,12,13,14,15,16,17,28};
                break;
                
            case 4:
                possibleTips = new int[]{5,5,5,7,8,8,8,1,2,3,3,3,3,3,3,4,10,11,12,13,14,15,16,17,28};
                break;
                           
            case 5:
                possibleTips = new int[]{1,2,3,4,10,11,12,13,14,15,16,17,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,18,19,22,23,24,25,26,27,28,29};
                break;
                
            case 6:
                possibleTips = new int[]{1,2,3,4,10,11,12,13,14,15,16,17,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,20,21,22,23,24,25,26,27,28,29};
                break;
                
            case 7:
                possibleTips = new int[]{1,2,3,4,10,11,12,13,14,15,16,17,20,21,20,21,22,23,24,25,26,27,28,29};
                break;
                
            case 8:
                possibleTips = new int[]{1,2,3,4,10,11,12,13,14,15,16,17,18,19,18,19,22,23,24,25,26,27,28,29};
                break;
                
            default:
                possibleTips = new int[]{1,2,3,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
                break;
        }
        
        for (int i = 0; i < possibleTips.length; i++){
               possibleTips.add(possibleTips[i]);
        }
        
        
        if (levelNumber >= 9){
            possibleTips.add(30);
            possibleTips.add(31);
            possibleTips.add(32);
        }
        
        if (levelNumber >= 9){// TODO bolas invencíveis
            possibleTips.add(33);
        }
        
        if (levelNumber >= 9){// TODO comida
            possibleTips.add(34);
        }
        
        if (levelNumber >= 9){// TODO falsidade
            possibleTips.add(35);
            possibleTips.add(36);
        }
        
        if (levelNumber >= 9){// TODO espelho
            possibleTips.add(37);
        }
        
        if (levelNumber >= 9){// TODO divisão
            possibleTips.add(38);
        }
        
        if (levelNumber >= 9){// TODO liberdade
            possibleTips.add(39);
        }
        
        

        double random = (double)Utils.getRandonFloat(0f, ((float)possibleTips.length)-0.0001f);
        int tipNumber = (int) Math.floor(random);
        //Log.e(TAG, "random "+ random);
        //Log.e(TAG, "tipNumber "+ tipNumber);

        boolean newTip = false;
        int count = 0;

        boolean allTipsViwed = true;

        for (int i = 0; i < possibleTips.length; i++) {
            boolean viwed = false;
            for (int j = 0; j < tipsViewed.size(); j++) {
                if (tipsViewed.get(j) == possibleTips[i]){
                    viwed = true;
                    break;
                }
            }

            if (viwed == false){
                allTipsViwed = false;
            }
        }

        //Log.e(TAG, "allTipsViwed "+ allTipsViwed);

        if (allTipsViwed){
            for (int i = tipsViewed.size() - 1; i >= 0; i--) {
                for (int j = 0; j < possibleTips.length; j++) {
                    if (possibleTips[j] == tipsViewed.get(i)){
                        tipsViewed.remove(i);
                        break;
                    }
                }
            }
        }

        //Log.e(TAG, "tipsViewed.size "+ tipsViewed.size());

        do{
            if (count > possibleTips.length){
                tipsViewed.clear();
            }

            boolean tipViewed = false;

            for (int i = 0; i < tipsViewed.size(); i++) {
                if(tipsViewed.get(i) == possibleTips[tipNumber]){
                    random = (double)Utils.getRandonFloat(0f, ((float)possibleTips.length)-0.0001f);
                    tipNumber = (int) Math.floor(random);
                    //Log.e(TAG, "fazendo nova tentativa ");
                    //Log.e(TAG, "random "+ random);
                    //Log.e(TAG, "tipNumber "+ tipNumber);
                    tipViewed = true;
                    break;
                }
            }

            if (!tipViewed){
                newTip = true;
            }

            count += 1;
            if (count > 100){
                newTip = true;
            }
        } while (!newTip);

        //Log.e(TAG, "tipChoose "+ possibleTips[tipNumber]);

        tipsViewed.add(possibleTips[tipNumber]);


        String text = "tipsViwed: ";
        for (int i = 0; i < tipsViewed.size(); i++) {
            text += tipsViewed.get(i).toString() + ", ";
        }

        Log.e(TAG, text);



        switch (possibleTips[tipNumber]){
            case 1:
                tip = Game.getContext().getResources().getString(R.string.tipLevel1);
                break;
            case 2:
                tip = Game.getContext().getResources().getString(R.string.tipLevel2);
                break;
            case 3:
                tip = Game.getContext().getResources().getString(R.string.tipLevel3);
                break;
            case 4:
                tip = Game.getContext().getResources().getString(R.string.tipLevel4);
                break;
            case 5:
                tip = Game.getContext().getResources().getString(R.string.tipLevel5);
                break;
            case 6:
                tip = Game.getContext().getResources().getString(R.string.tipLevel6);
                break;
            case 7:
                tip = Game.getContext().getResources().getString(R.string.tipLevel7);
                break;
            case 8:
                tip = Game.getContext().getResources().getString(R.string.tipLevel8);
                break;
            case 9:
                tip = Game.getContext().getResources().getString(R.string.tipLevel9);
                break;
        }
        return tip;
    }
    
    
    
    public static ArrayList<LevelGoal> getLevelGoals(int levelNumber){
        ArrayList<LevelGoal> lg = new ArrayList<>();

        if (Game.sempreGanharTodasEstrelas){
            lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
            return lg;
        }

        int ln = 1;  // ---------- LEVEL 1
        if (levelNumber == ln) {
            //lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 2
        if (levelNumber == ln) {
            //lg.add(new LevelGoal(5, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECELERATE_N_TIMES, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 3
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 4
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 5
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_N_TIMES, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 6
        if (levelNumber == ln) {
            lg.add(new LevelGoal(2, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(3, LevelGoal.INCREASE_ANGLE_N_TIMES, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 7
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_N_TIMES, 5));
            lg.add(new LevelGoal(3, LevelGoal.REACH_MAXIMUN_ANGLE, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 8
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 5));
            lg.add(new LevelGoal(3, LevelGoal.REACH_MINIMUN_ANGLE, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 9
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 10
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 10));
            return lg;
        }

        ln += 1; // ---------- LEVEL 11
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 3));
            return lg;
        }

        ln += 1; // ---------- LEVEL 12
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 3));
            return lg;
        }

        ln += 1; // ---------- LEVEL 13
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 8));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 14
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW, 12));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 15
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 16
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 17
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 12));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 18
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 19
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 12));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 8));
            return lg;
        }

        ln += 1; // ---------- LEVEL 20
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 8));
            return lg;
        }

        ln += 1; // ---------- LEVEL 21
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(3, LevelGoal.FINISH_LEVEL_WITHOUT_CHANGE_SPEED, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 22
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
            return lg;
        }

        ln += 1; // ---------- LEVEL 23
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(3, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 10));
            return lg;
        }

        ln += 1; // ---------- LEVEL 24
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 25
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 26
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
            return lg;
        }

        ln += 1; // ---------- LEVEL 27
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 3));
            return lg;
        }

        ln += 1; // ---------- LEVEL 28
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 120));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 29
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 120));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 30
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            return lg;
            
        }

        ln += 1; // ---------- LEVEL 31
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 240));
            lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 4));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_RIGHT_BORDER_TOUCH, 0));
            return lg;
        }

        ln += 1; // ---------- LEVEL 32
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE, 15));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 7));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 33
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 10));
            return lg;
        }

        ln += 1; // ---------- LEVEL 34
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 6));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 6));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 35
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 6));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 6));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 36
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0));
            lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 3));
            return lg;
        }

        ln += 1; // ---------- LEVEL 37
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 3, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 38
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(3, LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 39
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 20));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 5));
            return lg;
        }

        ln += 1; // ---------- LEVEL 40
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 90));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 10));
            return lg;
        }

        // BARRA DIMINUINDO
        ln += 1; // ---------- LEVEL 41
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 125));
            lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 3, 8));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 42
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
            lg.add(new LevelGoal(1, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
            lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 25));
            lg.add(new LevelGoal(1, LevelGoal.PREVENT_LEFT_BORDER_TOUCH, 0)); 
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 43
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES, 3));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 44
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES, 3));
            return lg;
        }

        // BOLA FALSA
        ln += 1; // ---------- LEVEL 45
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(4, LevelGoal.FINISH_IN_N_SECONDS, 200));
            return lg;
        }

        ln += 1; // ---------- LEVEL 46
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(3, LevelGoal.FINISH_IN_N_SECONDS, 200));
            return lg;
        }

        ln += 1; // ---------- LEVEL 47
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(4, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 20));
            return lg;
        }

        ln += 1; // ---------- LEVEL 48
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 18));
            lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
            return lg;
        }

        ln += 1; // ---------- LEVEL 49
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 4));
            lg.add(new LevelGoal(2, LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL, 20));
            return lg;
        }
        
        // ESPELHO
        ln += 1; // ---------- LEVEL 50
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 10));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_MINIMUN, 0));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 51
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 10));
            lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 52
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 53
        if (levelNumber == ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            lg.add(new LevelGoal(2, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES, 5));
            return lg;
        }
        
        // MURO E DUAS BARRAS
        ln += 1; // ---------- LEVEL 54
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 330));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS, 5)); 
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 10));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 55
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 35));
           lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES, 5));
            return lg;
        }
        
        
        ln += 1; // ---------- LEVEL 56
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 40));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 8));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 57
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS, 4));
            return lg;
        }
        
        
        ln += 1; // ---------- LEVEL 58
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES, 4));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 4)); 
            return lg;
        }
        
        // MURO E DUAS BOLAS
        ln += 1; // ---------- LEVEL 59
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 60
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 61
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_IN_A_ROW, 5));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 62
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW, 5));
           lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
            return lg;
        }

        ln += 1; // ---------- LEVEL 63
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.DECREASE_ANGLE_N_TIMES, 12));
           lg.add(new LevelGoal(1, LevelGoal.INCREASE_ANGLE_N_TIMES, 12));
           lg.add(new LevelGoal(2, LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS, 5));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 64
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.PREVENT_RIGHT_BORDER_TOUCH, 0));
            return lg;
        }
        
        // DUAS BOLAS LIVRES
        ln += 1; // ---------- LEVEL 65
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_N_TIMES, 10));
           lg.add(new LevelGoal(1, LevelGoal.DECELERATE_MINIMUN, 0));
           lg.add(new LevelGoal(2, LevelGoal.ACCELERATE_MAXIMUN, 0));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 66
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES, 8));
           lg.add(new LevelGoal(2, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 67
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(3, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 4, 6));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 68
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE, 10));
           lg.add(new LevelGoal(1, LevelGoal.HIT_OBSTACLE_N_TIMES, 30));
            return lg;
        }
        
        ln += 1; // ---------- LEVEL 69
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(2, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 5, 7));
            return lg;
        }

        ln += 1; // ---------- LEVEL 70
        if (levelNumber == ln) {
           lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
           lg.add(new LevelGoal(1, LevelGoal.FINISH_IN_N_SECONDS, 200));
           lg.add(new LevelGoal(2, LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS, 5, 8));
           lg.add(new LevelGoal(1, LevelGoal.ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE, 10));
            return lg;
        }
        
        // WINDOW
        //?????????????????
        // continuar a fazer os objetivos
        ln += 1; // ---------- LEVEL ++++
        if (levelNumber >= ln) {
            lg.add(new LevelGoal(1, LevelGoal.JUST_FINISH, 0));
            lg.add(new LevelGoal(2, LevelGoal.DECELERATE_N_TIMES, 6));
            lg.add(new LevelGoal(2, LevelGoal.INCREASE_ANGLE_N_TIMES, 4));
            return lg;
        }

        return lg;
    }

}
