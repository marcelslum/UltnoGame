package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/02/2017.
 */

public class Acelerometer {

    public static final String TAG = "Accelerometer";

    static float y;
    static ArrayList<Float> lastY;
    static long lastTime;
    static int moveStatus = 0;
    static final int MOVE_LEFT = 1;
    static final int MOVE_RIGHT = 2;
    static final int MOVE_NO = 0;


    public static void updateValue(float value) {

        if (lastY == null){
            lastY = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                lastY.add(0f);
            }
        }

        lastY.add(value);
        lastY.remove(0);


        float soma = 0;
        for (int i = 0; i < 10; i++){
            soma += lastY.get(i);
        }
        float media1 = soma/10;

        soma = 0f;
        for (int i = 0; i < 10; i++){
            soma += lastY.get(i+10);
        }
        float media2 = soma/10;


            if (lastTime == 0) {
                lastTime = System.currentTimeMillis();
            }

            long time = System.currentTimeMillis();
            if (moveStatus == MOVE_NO) {
                if (media2 - media1 > 1.5f) {
                    //Log.e(TAG, " acelerometer detectando movimento para a esquerda ");
                    //Log.e(TAG, "media1: "+ media1 + "     media2: "+ media2 + "  difference: "+ (media2 - media1));
                    moveStatus = MOVE_LEFT;
                    rotateBars(5f);

                    if (Game.balls != null) {
                        for (int i = 0; i < Game.balls.size(); i++){


                            //Log.e(TAG, " time "+ time);
                            // Log.e(TAG, " lastBarCollisionTime "+Game.balls.get(i).lastBarCollisionTime);
                            // Log.e(TAG, " diferença de tempo "+(time - Game.balls.get(i).lastBarCollisionTime));

                            if (time - Game.balls.get(i).lastBarCollisionTime < Game.TIME_OF_BALL_LISTENER){
                                //Log.e(TAG, " notifyBarMovementAfterCollision(MOVE_LEFT)");
                                Game.balls.get(i).notifyBarMovementAfterCollision(MOVE_LEFT);
                            }
                        }
                    }
                    lastTime = time;
                } else if (media2 - media1 < -1.5f) {
                    // Log.e(TAG, " acelerometer detectando movimento para a direita ");
                    // Log.e(TAG, "media1: "+ media1 + "     media2: "+ media2 + "  difference: "+ (media2 - media1));
                    moveStatus = MOVE_RIGHT;
                    rotateBars(-5f);

                    if (Game.balls != null) {
                        for (int i = 0; i < Game.balls.size(); i++){
                            // Log.e(TAG, " time "+ time);
                            // Log.e(TAG, " lastBarCollisionTime "+Game.balls.get(i).lastBarCollisionTime);
                            // Log.e(TAG, " diferença de tempo "+(time - Game.balls.get(i).lastBarCollisionTime));
                            if (time - Game.balls.get(i).lastBarCollisionTime < Game.TIME_OF_BALL_LISTENER){
                                Game.balls.get(i).notifyBarMovementAfterCollision(MOVE_RIGHT);
                                //Log.e(TAG, " notifyBarMovementAfterCollision(MOVE_RIGHT)");
                            }
                        }
                    }

                    lastTime = time;
                }
            } else {
                if (time - lastTime > 800) {
                    // Log.e(TAG, " parado ");
                    moveStatus = MOVE_NO;
                }
            }
    }


    public static void rotateBars(float angle){
        if (Game.bars == null) return;
        for (int i = 0; i < Game.bars.size(); i++){
            Utils.createAnimation3v(Game.bars.get(i), "rotate"+i, "rotate", 500, 0f, 0f, 0.3f, angle, 1.0f, 0f, false, true).start();
        }
    }

}
