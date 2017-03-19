package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 14/12/2016.
 */

public class LevelGoals {

    public ArrayList<LevelGoal> levelGoals;

    long barMoveByWind = 0;
    boolean barMoveByWindLoose = false;

    boolean leftBorderTouch = false;
    boolean rightBorderTouch = false;

    int secretLevel4Step = 0;
    int secretLevel5Step = 0;

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

    int timesOfDecelerationInARow = 0;
    int timesOfAccelerationInARow = 0;

    int timesOfAccelerate = 0;
    int timesOfDecelerate = 0;
    int timesOfChangeBallSpeedInARow = 0;
    int timesOfObstacleHit = 0;
    int timesOfCollisionBetweenBalls = 0;
    int timesOfBallReachedWithMaximunBarSpped = 0;
    
    int timesOfFakeBallsHitted = 0;

    public int firstTimeLivingBalls = 0;
    public boolean timeLivingBallsMessage1 = false;
    public boolean timeLivingBallsMessage2 = false;
    public boolean listeningLivingBalls = false;
    
    public final static String TAG = "LevelGoals";
    private boolean warning60;
    private boolean warning30;
    private boolean warning10;

    public LevelGoals() {
        levelGoals = new ArrayList<>();
    }
    
    public void notifyFakeBallHited(){
        timesOfFakeBallsHitted += 1;

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyFakeBallHited vezes "+timesOfFakeBallsHitted);
        
        for (int i = 0; i < levelGoals.size(); i++) {
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL) {
                if (timesOfFakeBallsHitted == lg.value){
                    Game.messages.showMessage(lg.messageText);
                } else {
                    if (lg.value - timesOfFakeBallsHitted == 5){
                        Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelGoal34m2) +
                                                 " 5 " + Game.getContext().getResources().getString(R.string.levelGoal34m3));
                    } else if (lg.value - timesOfFakeBallsHitted == 10){
                        Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelGoal34m2) +
                                " 10 " + Game.getContext().getResources().getString(R.string.levelGoal34m3));
                    }
                }
            }
        }
    }
    

    public void notifySecretStepsToConquer(int i){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifySecretStepsToConquer "+i);
        if (i == 1){
            Sound.play(Sound.soundSecretPart, 1f, 1f, 0);
        } else if (i == 2){
            Sound.play(Sound.soundSecretPart, 0.8f, 0.8f, 0);
        } else if (i == 3){
            Sound.play(Sound.soundSecretPart, 0.6f, 0.6f, 0);
        } else if (i == 4){
            Sound.play(Sound.soundSecretPart, 0.4f, 0.4f, 0);
        } else if (i == 5){
            Sound.play(Sound.soundSecretPart, 0.2f, 0.2f, 0);
        }
    }



    public void notifySecretLevelUnblocked(int i){

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"secretLevelUnblocked "+i);
        if (i == 1){

            GooglePlayGames.unlockAchievement(Game.mainActivity.mGoogleApiClient,
                    Game.getContext().getResources().getString(R.string.achievement_segredo_1));

            Sound.play(Sound.soundSecretUnblocked, 1, 1, 0);
            Utils.createSimpleAnimation(Game.background, "rotate", "rotate", 1000, 0, 360).start();
            Utils.createSimpleAnimation(Game.background, "translateX", "translateX", 1000, 0, Game.background.height * 0.0001f).start();
            Game.messages.showMessage("???");
            SaveGame.saveGame.secretLevelsUnlocked[i-1] = true;
        } else if (i == 2){

            GooglePlayGames.unlockAchievement(Game.mainActivity.mGoogleApiClient,
                    Game.getContext().getResources().getString(R.string.achievement_segredo_2));

            Sound.play(Sound.soundSecretUnblocked, 1, 1, 0);
            Utils.createSimpleAnimation(Game.background, "rotate", "rotate", 1000, 0, 360).start();
            Game.messages.showMessage("???");
            SaveGame.saveGame.secretLevelsUnlocked[i-1] = true;
        } else if (i == 3){
            GooglePlayGames.unlockAchievement(Game.mainActivity.mGoogleApiClient,
                    Game.getContext().getResources().getString(R.string.achievement_segredo_3));
            Sound.play(Sound.soundSecretUnblocked, 1, 1, 0);
            Utils.createSimpleAnimation(Game.background, "rotate", "rotate", 1000, 0, 360).start();
            Game.messages.showMessage("???");
            SaveGame.saveGame.secretLevelsUnlocked[i-1] = true;
        } else if (i == 4){ // 0 + 1 + 2 - 3 - 4 + 5 - 6 + 7 - 8 - 9 + 10
            GooglePlayGames.unlockAchievement(Game.mainActivity.mGoogleApiClient,
                    Game.getContext().getResources().getString(R.string.achievement_segredo_4));
            Sound.play(Sound.soundSecretUnblocked, 1, 1, 0);
            Utils.createSimpleAnimation(Game.background, "rotate", "rotate", 1000, 0, 360).start();
            Game.messages.showMessage("???");
            SaveGame.saveGame.secretLevelsUnlocked[i-1] = true;



        }
    }

    public void notifyBarMoveLeft(){

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

    public void notifyTime(int seconds) {
        for (int i = 0; i < levelGoals.size(); i++) {
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.FINISH_IN_N_SECONDS) {
                if (seconds == lg.value){//??
                    Game.messages.showMessage(lg.messageText);
                } else {
                    if (lg.value - seconds < 60 && lg.value > 60 && !warning60){
                        Game.messages.showMessage(Game.getContext().getResources().getString(R.string.levelGoal1m3));
                        warning60 = true;
                    }

                    if (lg.value - seconds < 30 && !warning30){
                        Game.messages.showMessage("30 "+Game.getContext().getResources().getString(R.string.levelGoal1m2));
                        warning30 = true;
                    }

                    if (lg.value - seconds < 10 && !warning10){
                        Game.messages.showMessage("10 "+Game.getContext().getResources().getString(R.string.levelGoal1m2));
                        warning10 = true;
                    }
                }

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
                    if (!listeningLivingBalls){
                        Game.messages.showMessage(lg.messageText + " " + lg.value + Game.getContext().getResources().getString(R.string.levelGoal12m2));
                        firstTimeLivingBalls = time;
                        listeningLivingBalls = true;
                        timeLivingBallsMessage1 = false;
                        timeLivingBallsMessage2 = false;
                    } else {
                        if (time - firstTimeLivingBalls >= lg.value2){
                            lg.setAchieved();
                            Game.messages.showMessage(lg.messageText + " " + lg.value);
                        } else if (time - firstTimeLivingBalls >= Math.floor(lg.value2/2) && lg.value2 > 2){
                            if (timeLivingBallsMessage1) {
                                Game.messages.showMessage(lg.messageText + " " + lg.value + " - " + String.valueOf(lg.value2 - Math.floor(lg.value2 / 2)) +
                                        Game.getContext().getResources().getString(R.string.levelGoal12m3));
                            }
                        } else if (time - firstTimeLivingBalls >= Math.floor(lg.value2/4) && lg.value2 > 4){
                            if (timeLivingBallsMessage1) {
                                Game.messages.showMessage(lg.messageText + " " + lg.value + " - " + String.valueOf(lg.value2 - Math.floor(lg.value2 / 4)) +
                                        Game.getContext().getResources().getString(R.string.levelGoal12m3));
                            }
                        }
                    }
                } else {
                    if (listeningLivingBalls){
                        firstTimeLivingBalls = 0;
                        timeLivingBallsMessage1 = false;
                        timeLivingBallsMessage2 = false;
                        listeningLivingBalls = false;
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

            if (lg.type == LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS && !barMoveByWindLoose){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.PREVENT_BORDER_TOUCH && !leftBorderTouch && !rightBorderTouch){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.PREVENT_RIGHT_BORDER_TOUCH && !rightBorderTouch){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.PREVENT_LEFT_BORDER_TOUCH && !leftBorderTouch){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.FINISH_IN_N_SECONDS && seconds <= lg.value && !lg.achieved){
                lg.setAchieved();
            }

            if (lg.type == LevelGoal.FINISH_LEVEL_WITHOUT_CHANGE_SPEED && timesOfAccelerate == 0 && timesOfDecelerate == 0 && !lg.achieved){
                lg.setAchieved();
            }
            
            if (lg.type == LevelGoal.HIT_FAKE_BALL_WITH_BAR_UNTIL) {
                if (timesOfFakeBallsHitted < lg.value){
                    lg.setAchieved();
                }
            }
        }
    }

    public void notifyMaxAngleReached(){

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_inclinao_mxima), 1);

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMaxAngleReached");
        timesOfDecelerationWithoutReachingMaxAngle = 0;

        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.REACH_MAXIMUN_ANGLE && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            } else
            if (lg.type == LevelGoal.DECELERATE_N_TIMES_WITHOUT_REACHING_MAX_ANGLE && !lg.achieved){
                Game.messages.showMessage(lg.messageText + " " + timesOfDecelerationWithoutReachingMaxAngle +" / "+lg.value);
            }
        }
    }

    public void notifyMinAngleReached(){

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_inclinao_mnima), 1);

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyMinAngleReached");
        timesOfAccelerationWithoutReachingMinAngle = 0;

        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.REACH_MINIMUN_ANGLE && !lg.achieved){
                lg.setAchieved();
                Game.messages.showMessage(lg.messageText);
            } else
            if (lg.type == LevelGoal.ACCELERATE_N_TIMES_WITHOUT_REACHING_MIN_ANGLE && !lg.achieved){
                Game.messages.showMessage(lg.messageText + " " + timesOfAccelerationWithoutReachingMinAngle +" / "+lg.value);
            }
        }
    }

    public void increaseAngle(){

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_inclinao_positiva), 1);

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

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_inclinao_negativa), 1);

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

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_velocidade_mxima), 1);

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

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_velocidade_mnima), 1);

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
        secretLevel4Step = 0;
        timesOfChangeBallSpeedInARow = 0;
        timesOfAccelerationInARow = 0;
        timesOfDecelerationInARow = 0;
    }

    public void notifyBarMoveByWind(long time){

        if (barMoveByWindLoose){
            return;
        }

        Log.e(TAG, " NOTIFICANDO ->->->-> "+"barMoveByWindo "+time);

        barMoveByWind  = time;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.PREVENT_BAR_MOVE_BY_WIND_FOR_MORE_THAN_N_SECONDS){
                if (time > lg.value * 1000){
                    // mensagem de que o objetivo foi perdido
                    Log.e(TAG, "messageText "+lg.messageText);
                    Game.messages.showMessage(lg.messageText);
                    barMoveByWindLoose = true;
                }
            }
        }
    }

    public void accelerate(){
        Log.e(TAG, " NOTIFICANDO ->->->-> "+"accelerate");

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_acelerar),
                1);

        // 0 + 1 + 2 - 3 - 4 + 5 - 6 + 7 - 8 - 9 + 10
        if(secretLevel4Step == 0 || secretLevel4Step == 1 || secretLevel4Step == 4 || secretLevel4Step == 6 || secretLevel4Step == 9){
            secretLevel4Step += 1;
            notifySecretStepsToConquer(10 - secretLevel4Step);
            if (secretLevel4Step == 10){
                notifySecretLevelUnblocked(4);
            }
        } else {
            secretLevel4Step = 0;

        }

        timesOfAccelerate += 1;
        timesOfAccelerationInARow += 1;
        timesOfDecelerationInARow = 0;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.ACCELERATE_N_TIMES && !lg.achieved){
                if (timesOfAccelerate == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerate+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfAccelerate < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerate+" / "+lg.value);
                }
            } else if (lg.type == LevelGoal.ACCELERATE_N_TIMES_IN_A_ROW && !lg.achieved){
                if (timesOfAccelerationInARow == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerationInARow+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfAccelerationInARow < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfAccelerationInARow+" / "+lg.value);
                }
            }
        }
        speedChange();
    }

    public void decelerate(){

        GooglePlayGames.increment(Game.mainActivity.mGoogleApiClient,
                Game.getContext().getResources().getString(R.string.achievement_desacelerar), 1);


        Log.e(TAG, " NOTIFICANDO ->->->-> "+"decelerate");

        // 0 + 1 + 2 - 3 - 4 + 5 - 6 + 7 - 8 - 9 + 10
        if(secretLevel4Step == 2 || secretLevel4Step == 3 || secretLevel4Step == 5 || secretLevel4Step == 7 || secretLevel4Step == 8){
            secretLevel4Step += 1;
            notifySecretStepsToConquer(10 - secretLevel4Step);
        } else {
            secretLevel4Step = 0;

        }

        timesOfDecelerate += 1;
        timesOfDecelerationInARow += 1;
        timesOfAccelerationInARow = 0;
        for (int i = 0; i < levelGoals.size(); i++){
            LevelGoal lg = levelGoals.get(i);
            if (lg.type == LevelGoal.DECELERATE_N_TIMES && !lg.achieved){
                if (timesOfDecelerate == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerate+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfDecelerate < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerate+" / "+lg.value);
                }
            } else if (lg.type == LevelGoal.DECELERATE_N_TIMES_IN_A_ROW && !lg.achieved){
                if (timesOfDecelerationInARow == lg.value) {
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerationInARow+" / "+lg.value);
                    lg.setAchieved();
                } else if (timesOfDecelerationInARow < lg.value){
                    Game.messages.showMessage(lg.messageText + " " + timesOfDecelerationInARow+" / "+lg.value);
                }
            }
        }
        speedChange();
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

    public void notifyLeftBorderTouch() {
        if (!leftBorderTouch) {
            leftBorderTouch = true;
            Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyLeftBorderTouch ");
            for (int i = 0; i < levelGoals.size(); i++){
                LevelGoal lg = levelGoals.get(i);
                if (lg.type == LevelGoal.PREVENT_BORDER_TOUCH){
                        Game.messages.showMessage(lg.messageText);
                }
                if (lg.type == LevelGoal.PREVENT_LEFT_BORDER_TOUCH){
                    Game.messages.showMessage(lg.messageText);
                }
            }
        }
    }


    public void notifyRightBorderTouch() {
        if (!rightBorderTouch) {
            rightBorderTouch = true;
            Log.e(TAG, " NOTIFICANDO ->->->-> "+"notifyRightBorderTouch ");
            for (int i = 0; i < levelGoals.size(); i++){
                LevelGoal lg = levelGoals.get(i);
                if (lg.type == LevelGoal.PREVENT_BORDER_TOUCH){
                    Game.messages.showMessage(lg.messageText);
                }
                if (lg.type == LevelGoal.PREVENT_RIGHT_BORDER_TOUCH){
                    Game.messages.showMessage(lg.messageText);
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

            barMoveByWind = 0;
            barMoveByWindLoose = false;

            listeningLivingBalls = false;

            leftBorderTouch = false;
            rightBorderTouch = false;

            warning60 = false;
            warning30 = false;
            warning10 = false;

            timesWhereAngleDecreased = 0;
            timesWhereAngleIncreased = 0;
            timesOfAccelerate = 0;
            timesOfDecelerate = 0;
            timesOfChangeBallSpeedInARow = 0;
            timesOfObstacleHit = 0;
            timesOfCollisionBetweenBalls = 0;
            timesOfBallReachedWithMaximunBarSpped = 0;
            timesOfFakeBallsHitted = 0;

            secretLevel4Step = 0;
            secretLevel5Step = 0;

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
