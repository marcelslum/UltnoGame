package com.marcelslum.ultnogame;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoal{
    int numberOfStars;
    int type;
    int value;
    int value2;
    boolean achieved;
    String text;
    String messageText;
    String messageTextDown;

    public static final int JUST_FINISH = 0;
    public static final int FINISH_IN_N_SECONDS = 1;
    public static final int ACCELERATE_MAXIMUN = 2;
    public static final int ACCELERATE_N_TIMES = 3;
    public static final int DECELERATE_MINIMUN = 4;
    public static final int DECELERATE_N_TIMES = 5;
    public static final int REACH_MAXIMUN_ANGLE = 6;
    public static final int REACH_MINIMUN_ANGLE = 7;
    public static final int DECREASE_ANGLE_N_TIMES = 8;
    public static final int INCREASE_ANGLE_N_TIMES = 9;
    public static final int KEEP_N_LIVING_BALLS = 11;
    public static final int KEEP_N_LIVING_BALLS_FOR_N_SECONDS = 12;
    public static final int CAUSE_N_COLLISIONS_BETWEEN_BALLS = 13;
    public static final int REACH_BALL_WITH_MAXIMUN_BAR_SPEED = 14;
    public static final int HIT_OBSTACLE_N_TIMES = 15;
    public static final int PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS = 16;
    public static final int FINISH_LEVEL_WITHOUT_CHANGE_SPEED = 17;
    public static final int CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW = 18;
    public static final int ACCELERATE_N_TIMES_IN_A_ROW = 19;
    public static final int DECELERATE_N_TIMES_IN_A_ROW = 20;

    public static final int DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES = 21;
    public static final int INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES = 22;

    public static final int DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES = 23;
    public static final int INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES = 24;

    public static final int DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES = 25;
    public static final int INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES = 26;

    public static final int ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES = 27;
    public static final int DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES = 28;

    public static final int ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE = 29;
    public static final int DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE = 30;


    public void setText() {
        if (type == JUST_FINISH) {
            text = Game.getContext().getResources().getString(R.string.levelGoal0);
        } else if (type == FINISH_IN_N_SECONDS) {

            int minutes = (int) Math.floor(value / 60);
            int seconds = value -(minutes * 60);

            text = Game.getContext().getResources().getString(R.string.levelGoal1a) + " " + String.valueOf(minutes) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal1b) + String.valueOf(seconds) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal1c);
        } else if (type == ACCELERATE_MAXIMUN) {
            text = Game.getContext().getResources().getString(R.string.levelGoal2);
        } else if (type == ACCELERATE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal3a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal3b);
        } else if (type == DECELERATE_MINIMUN) {
            text = Game.getContext().getResources().getString(R.string.levelGoal4);
        } else if (type == DECELERATE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal5a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal5b);
        } else if (type == FINISH_LEVEL_WITHOUT_CHANGE_SPEED) {
            text = Game.getContext().getResources().getString(R.string.levelGoal17);
        } else if (type == CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW) {
            text = Game.getContext().getResources().getString(R.string.levelGoal18a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal18b);
        } else if (type == REACH_MAXIMUN_ANGLE) {
            text = Game.getContext().getResources().getString(R.string.levelGoal6);
        } else if (type == REACH_MINIMUN_ANGLE) {
            text = Game.getContext().getResources().getString(R.string.levelGoal7);
        } else if (type == DECREASE_ANGLE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal8a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal8b);
        } else if (type == INCREASE_ANGLE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal9a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal9b);
        } else if (type == KEEP_N_LIVING_BALLS) {
            text = Game.getContext().getResources().getString(R.string.levelGoal11a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal11b);
        } else if (type == KEEP_N_LIVING_BALLS_FOR_N_SECONDS) {
            text = Game.getContext().getResources().getString(R.string.levelGoal12a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal12b) + " " + String.valueOf(value2) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal12c);
        } else if (type == CAUSE_N_COLLISIONS_BETWEEN_BALLS) {
            text = Game.getContext().getResources().getString(R.string.levelGoal13a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal13b);
        } else if (type == REACH_BALL_WITH_MAXIMUN_BAR_SPEED) {
            text = Game.getContext().getResources().getString(R.string.levelGoal14);
        } else if (type == HIT_OBSTACLE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal15a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal15b);
        } else if (type == PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS) {
            text = Game.getContext().getResources().getString(R.string.levelGoal16a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal16b);
        } else if (type == ACCELERATE_N_TIMES_IN_A_ROW) {
            text = Game.getContext().getResources().getString(R.string.levelGoal19a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal19b);
        } else if (type == DECELERATE_N_TIMES_IN_A_ROW) {
            text = Game.getContext().getResources().getString(R.string.levelGoal20a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal20b);
        } else if (type == DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal21a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal21b);
        } else if (type == INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal22a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal22b);
        } else if (type == DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal23a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal23b);
        } else if (type == INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal24a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal24b);
        } else if (type == DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal25a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal25b);
        } else if (type == INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal26a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal26b);
        } else if (type == ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal27a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal27b);
        } else if (type == DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES) {
            text = Game.getContext().getResources().getString(R.string.levelGoal28a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal28b);
        } else if (type == ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE) {
            text = Game.getContext().getResources().getString(R.string.levelGoal29a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal29b);
        } else if (type == DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE) {
            text = Game.getContext().getResources().getString(R.string.levelGoal30a) + " " + String.valueOf(value) + " " +
                    Game.getContext().getResources().getString(R.string.levelGoal30b);
        } else {
            text = "sem texto definido";
        }

    }

    public void setMessageText(){

        messageTextDown = ".";

        if (type == ACCELERATE_MAXIMUN){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal2m);
        } else if (type == DECELERATE_MINIMUN){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal4m);
        } else if (type == REACH_MAXIMUN_ANGLE){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal6m);
        } else if (type == REACH_MINIMUN_ANGLE){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal7m);
        } else if (type == REACH_BALL_WITH_MAXIMUN_BAR_SPEED){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal14m);
        } else if (type == ACCELERATE_N_TIMES){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal3m);
        } else if (type == DECELERATE_N_TIMES){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal5m);
        } else if (type == CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal18m);
        } else if (type == DECREASE_ANGLE_N_TIMES){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal8m);
        } else if (type == INCREASE_ANGLE_N_TIMES){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal9m);
        } else if (type == KEEP_N_LIVING_BALLS){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal11m);
        } else if (type == KEEP_N_LIVING_BALLS_FOR_N_SECONDS){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal12m);
        } else if (type == CAUSE_N_COLLISIONS_BETWEEN_BALLS){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal13m);
        } else if (type == HIT_OBSTACLE_N_TIMES){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal15m);
        } else if (type == PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS){

        } else if (type == ACCELERATE_N_TIMES_IN_A_ROW){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal3m);
        } else if (type == DECELERATE_N_TIMES_IN_A_ROW){
            messageText = Game.getContext().getResources().getString(R.string.levelGoal5m);
        } else if (type == DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal21m);
        } else if (type == INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal22m);
        } else if (type == DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal23m);
        } else if (type == INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal24m);
        } else if (type == DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal25m);
        } else if (type == INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal26m);
        } else if (type == ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal27m);
        } else if (type == DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal28m);
        } else if (type == ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal29m);

        } else if (type == DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE) {
            messageText = Game.getContext().getResources().getString(R.string.levelGoal30m);
        }
    }




    public LevelGoal(int numberOfStars, int type, int value) {
        this.numberOfStars = numberOfStars;
        this.type = type;
        this.value = value;
        this.value2 = 0;
        this.achieved = false;
        setText();
        setMessageText();

    }

    public LevelGoal(int numberOfStars, int type, int value, int value2) {

        this.numberOfStars = numberOfStars;
        this.type = type;
        this.value = value;
        this.value = value2;
        this.achieved = false;
        setText();
        setMessageText();
    }

    public void setAchieved(){
        achieved = true;

        int totalStars = 0;

        for (int i = 0; i < Level.levelObject.levelGoalsObject.levelGoals.size(); i++){
            if (Level.levelObject.levelGoalsObject.levelGoals.get(i).achieved){
                totalStars += Level.levelObject.levelGoalsObject.levelGoals.get(i).numberOfStars;
            }
        }

        MessageStar.messageStars.show(totalStars, numberOfStars, false);
    }

}