package com.marcelslum.ultnogame;

public class LevelDataBaseData {

    public int number, group, min_balls_alive;
    public final static String TAG = "LevelDataBaseData";

    public LevelDataBaseData(int number, int group, int min_balls_alive) {
        this.number = number;
        this.group = group;
        this.min_balls_alive = min_balls_alive;
    }
}
