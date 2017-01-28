package com.marcelslum.ultnogame;

/**
 * Created by marcel on 26/01/2017.
 */

public class TimeHandler {

    static long timeOfLevelPlay = 0;
    static int secondsOfLevelPlay = 0;
    static boolean timeOfLevelPlayBlocked = true;
    static long lastSeconds = 0;

    public static int stopTimeOfLevelPlay(){
        timeOfLevelPlayBlocked = true;
        return secondsOfLevelPlay;
    }

    public static void resumeTimeOfLevelPlay(){
        timeOfLevelPlayBlocked = false;
    }

    public static void updateTimeOfLevelPlay(long timeElapsed){
        if (timeOfLevelPlayBlocked){
            return;
        }

        timeOfLevelPlay += timeElapsed;

        secondsOfLevelPlay = (int)((float) timeOfLevelPlay / 1000f);

        if (lastSeconds != secondsOfLevelPlay){
            lastSeconds = secondsOfLevelPlay;

            long displaySeconds;
            long displayMinutes = 0;

            if (secondsOfLevelPlay > 59) {
                displayMinutes = (long)((float) secondsOfLevelPlay/60f);
                displaySeconds = secondsOfLevelPlay % 60;

            } else {
                displaySeconds = secondsOfLevelPlay;
            }

            String displaySecondsString;
            String displayMinutesString;

            if (displayMinutes < 10){
                displayMinutesString = "0"+String.valueOf(displayMinutes);
            } else {
                displayMinutesString = String.valueOf(displayMinutes);
            }

            if (displaySeconds < 10){
                displaySecondsString = "0"+String.valueOf(displaySeconds);
            } else {
                displaySecondsString = String.valueOf(displaySeconds);
            }

            MessagesHandler.messageTime.setText(displayMinutesString+":"+displaySecondsString);
            if (MessagesHandler.messageTime.animTranslateX != 0){
                MessagesHandler.messageTime.clearAnimations();
            }
        }
    }
}
