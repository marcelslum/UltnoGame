package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoals {

    public ArrayList<LevelGoal> levelGoals;
    int timesWhereAngleDecreased = 0;
    int timesWhereAngleIncreased = 0;

    int timesWhereAngleDecreasedOnlyWithBarMovement = 0;
    int timesWhereAngleIncreasedOnlyWithBarMovement = 0;

    int timesWhereAngleDecreasedOnlyWithBarInclination = 0;
    int timesWhereAngleIncreasedOnlyWithBarInclination = 0;

    int timesWhereAngleIncreasedWithBarMovementAndInclination = 0;
    int timesWhereAngleDecreasedWithBarMovementAndInclination = 0;

    int timesOfAccelerationWithBarIncreasingAngle = 0;
    int timesOfDecelerationWithBarDecreasingAngle = 0;

    int timesOfAccelerationWithoutReachingMinAngle = 0;
    int timesOfDecelerationWithoutReachingMaxAngle = 0;

    int timesOfAccelerate = 0;
    int timesOfDecelerate = 0;
    int timesOfChangeBallSpeedInARow = 0;
    int timesOfObstacleHit = 0;
    int timesOfCollisionBetweenBalls = 0;
    int timesOfBallReachedWithMaximunBarSpped = 0;

    public int firstTimeLivingBalls = 0;
    
    public final static String TAG = "LevelGoals";

    public LevelGoals() {
        levelGoals = new ArrayList<>();
    }

    public void ballReachedWithMaximunBarSpped(){
        
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"ballReachedWithMaximunBarSpped");
        
        timesOfBallReachedWithMaximunBarSpped += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.REACH_BALL_WITH_MAXIMUN_BAR_SPEED && timesOfBallReachedWithMaximunBarSpped == lg.value && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            }
        }
    }

    public void notifyBallsAlive(int number, int time){
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.KEEP_N_LIVING_BALLS && lg.value == number && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText + " " + lg.value);
            }

            if (lg.type == LevelGoal.KEEP_N_LIVING_BALLS_FOR_N_SECONDS && !lg.achieved){

                if (number >= lg.value){
                    if (firstTimeLivingBalls == 0){
                        firstTimeLivingBalls = time;
                    } else {
                        if (time - firstTimeLivingBalls >= lg.value2){
                            lg.setAchieved();
                            Game.messages.showMessage(lg.messageText + " " + lg.value);
                        }
                    }
                } else {
                    if (firstTimeLivingBalls > 0){
                        firstTimeLivingBalls = 0;
                    }
                }
            }
        }
    }

    public void hitObstacle(){

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"hitObstacle");
        
        timesOfObstacleHit += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.HIT_OBSTACLE_N_TIMES){
                if (timesOfObstacleHit == lg.value && !lg.achieved) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText+" "+lg.value);
                } else if (timesOfObstacleHit < lg.value && !lg.achieved){
                    Game.messages.showMessage(lg.messageText + " " + timesOfObstacleHit+" / "+lg.value);
                }
            }
        }
    }

    public void hitAnotherBall(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"hitAnotherBall");
        timesOfCollisionBetweenBalls += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.CAUSE_N_COLLISIONS_BETWEEN_BALLS && !lg.achieved){
                if (timesOfCollisionBetweenBalls == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText+" "+lg.value);
                } else if (timesOfCollisionBetweenBalls < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfCollisionBetweenBalls+" / "+lg.value);
                }
            }
        }
    }

    public void setFinish(int seconds){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"setFinish");
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.JUST_FINISH && !lg.achieved){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.FINISH_IN_N_SECONDS && seconds <= lg.value && !lg.achieved){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.FINISH_LEVEL_WITHOUT_CHANGE_SPEED && timesOfAccelerate == 0 && timesOfDecelerate == 0 && !lg.achieved){
                lg.setAchieved();
            }
        }
    }

    public void notifyMaxAngleReached(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMaxAngleReached");
        timesOfDecelerationWithoutReachingMaxAngle = 0;

        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.REACH_MAXIMUN_ANGLE && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            }
        }
    }

    public void notifyMinAngleReached(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMinAngleReached");
        timesOfAccelerationWithoutReachingMinAngle = 0;

        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.REACH_MINIMUN_ANGLE && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            }
        }
    }

    public void increaseAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"increaseAngle");
        timesWhereAngleIncreased += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.INCREASE_ANGLE_N_TIMES && !lg.achieved){
                if (timesWhereAngleIncreased == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleIncreased < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleIncreased +" / "+lg.value);
                }
            }
        }
    }

    public void decreaseAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"decreaseAngle");
        timesWhereAngleDecreased += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECREASE_ANGLE_N_TIMES && !lg.achieved){
                if (timesWhereAngleDecreased == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleDecreased < lg.value && !lg.achieved){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleDecreased +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleIncreasedOnlyWithBarMovement(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleIncreasedOnlyWithBarMovement");
        increaseAngle();
        timesWhereAngleIncreasedOnlyWithBarMovement += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES && !lg.achieved){
                if (timesWhereAngleIncreasedOnlyWithBarMovement == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleIncreasedOnlyWithBarMovement < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleIncreasedOnlyWithBarMovement +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleDecreasedOnlyWithBarMovement(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleDecreasedOnlyWithBarMovement");
        decreaseAngle();
        timesWhereAngleDecreasedOnlyWithBarMovement += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_MOVEMENT_N_TIMES && !lg.achieved){
                if (timesWhereAngleDecreasedOnlyWithBarMovement == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleDecreasedOnlyWithBarMovement < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleDecreasedOnlyWithBarMovement +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleIncreasedOnlyWithBarInclination(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleIncreasedOnlyWithBarInclination");
        increaseAngle();
        timesWhereAngleIncreasedOnlyWithBarInclination += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.INCREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES && !lg.achieved){
                if (timesWhereAngleIncreasedOnlyWithBarInclination == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleIncreasedOnlyWithBarInclination < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleIncreasedOnlyWithBarInclination +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleDecreasedOnlyWithBarInclination(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleDecreasedOnlyWithBarInclination");
        decreaseAngle();
        timesWhereAngleDecreasedOnlyWithBarInclination += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECREASE_ANGLE_ONLY_WITH_BAR_INCLINATION_N_TIMES && !lg.achieved){
                if (timesWhereAngleDecreasedOnlyWithBarInclination == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleDecreasedOnlyWithBarInclination < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleDecreasedOnlyWithBarInclination +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleIncreasedWithBarMovementAndInclination(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleIncreasedWithBarMovementAndInclination");
        increaseAngle();
        timesWhereAngleIncreasedWithBarMovementAndInclination += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.INCREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES && !lg.achieved){
                if (timesWhereAngleIncreasedWithBarMovementAndInclination == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleIncreasedWithBarMovementAndInclination < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleIncreasedWithBarMovementAndInclination +" / "+lg.value);
                }
            }
        }
    }

    public void notifyAngleDecreasedWithBarMovementAndInclination(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyAngleDecreasedWithBarMovementAndInclination");
        increaseAngle();
        timesWhereAngleDecreasedWithBarMovementAndInclination += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECREASE_ANGLE_WITH_BAR_MOVEMENT_AND_INCLINATION_N_TIMES && !lg.achieved){
                if (timesWhereAngleDecreasedWithBarMovementAndInclination == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesWhereAngleDecreasedWithBarMovementAndInclination < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesWhereAngleDecreasedWithBarMovementAndInclination +" / "+lg.value);
                }
            }
        }
    }


    public void accelerateWithBarIncreasingAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"accelerateWithBarIncreasingAngle");
        timesOfAccelerationWithBarIncreasingAngle += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.ACCELERATE_WITH_BAR_INCREASING_ANGLE_N_TIMES && !lg.achieved){
                if (timesOfAccelerationWithBarIncreasingAngle == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesOfAccelerationWithBarIncreasingAngle < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerationWithBarIncreasingAngle +" / "+lg.value);
                }
            }
        }
    }

    public void decelerateWithBarDecreasingAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"decelerateWithBarDecreasingAngle");
        timesOfDecelerationWithBarDecreasingAngle += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECELERATE_WITH_BAR_DECREASING_ANGLE_N_TIMES && !lg.achieved){
                if (timesOfDecelerationWithBarDecreasingAngle == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesOfDecelerationWithBarDecreasingAngle < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerationWithBarDecreasingAngle +" / "+lg.value);
                }
            }
        }
    }

    public void decelerateWithoutReachMaxAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"decelerateWithoutReachMaxAngle");
        timesOfDecelerationWithoutReachingMaxAngle += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE && !lg.achieved){
                if (timesOfDecelerationWithoutReachingMaxAngle == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesOfDecelerationWithoutReachingMaxAngle < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerationWithoutReachingMaxAngle +" / "+lg.value);
                }
            }
        }
    }

    public void accelerateWithoutReachMinAngle(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"accelerateWithoutReachMinAngle");
        timesOfAccelerationWithoutReachingMinAngle += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE && !lg.achieved){
                if (timesOfAccelerationWithoutReachingMinAngle == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesOfAccelerationWithoutReachingMinAngle < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerationWithoutReachingMinAngle +" / "+lg.value);
                }
            }
        }
    }

    public void notifyMaxVelocityReached(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMaxVelocityReached");
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.ACCELERATE_MAXIMUN && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            }
        }
    }

    public void notifyMinVelocityReached(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMinVelocityReached");
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECELERATE_MINIMUN && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            }
        }
    }

    public void notifyNotSpeedChange(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyNotSpeedChange");
        timesOfChangeBallSpeedInARow = 0;
    }

    public void accelerate(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"accelerate");
        timesOfAccelerate += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.ACCELERATE_N_TIMES && !lg.achieved){
                if (timesOfAccelerate == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerate+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfAccelerate < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerate+" / "+lg.value);
                }
            }
        }
        speedChange();
        accelerateWithoutReachMinAngle();
    }

    public void decelerate(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"decelerate");
        timesOfDecelerate += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECELERATE_N_TIMES && !lg.achieved){
                if (timesOfDecelerate == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerate+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfDecelerate < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerate+" / "+lg.value);
                }
            }
        }
        speedChange();
        decelerateWithoutReachMaxAngle();
    }

    public void speedChange(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"speedChange");
        timesOfChangeBallSpeedInARow += 1;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.CHANGE_BALL_SPEED_N_TIMES_IN_A_ROW && !lg.achieved){
                if (timesOfChangeBallSpeedInARow == lg.value) {
                    lg.setAchieved();
                    Game.messages.showMessage(lg.messageText + " " + lg.value);
                } else if (timesOfChangeBallSpeedInARow < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfChangeBallSpeedInARow+" / "+lg.value);
                }
            }
        }
    }

    public void addLevelGoal(int numberOfStars, int type, int value){
        this.levelGoals.add(new LevelGoal(numberOfStars, type, value));
    }

    public int getStarsAchieved() {
        int stars = 0;
        for (int i = 0; i < levelGoals.size(); i++){
            if (levelGoals.get(i).achieved){
                stars += levelGoals.get(i).numberOfStars;
            }
        }
        if (stars > 5){
            stars = 5;
        }
        return stars;
    }

    public void clearAchievements() {
        for (int i = 0; i < levelGoals.size(); i++){
            levelGoals.get(i).achieved = false;
            timesWhereAngleDecreased = 0;
            timesWhereAngleIncreased = 0;
            timesOfAccelerate = 0;
            timesOfDecelerate = 0;
            timesOfChangeBallSpeedInARow = 0;
            timesOfObstacleHit = 0;
            timesOfCollisionBetweenBalls = 0;
            timesOfBallReachedWithMaximunBarSpped = 0;

            timesWhereAngleDecreasedOnlyWithBarMovement = 0;
            timesWhereAngleIncreasedOnlyWithBarMovement = 0;

            timesWhereAngleDecreasedOnlyWithBarInclination = 0;
            timesWhereAngleIncreasedOnlyWithBarInclination = 0;

            timesWhereAngleIncreasedWithBarMovementAndInclination = 0;
            timesWhereAngleDecreasedWithBarMovementAndInclination = 0;

            timesOfAccelerationWithBarIncreasingAngle = 0;
            timesOfDecelerationWithBarDecreasingAngle = 0;

            timesOfAccelerationWithoutReachingMinAngle = 0;
            timesOfDecelerationWithoutReachingMaxAngle = 0;

        }
    }
}
