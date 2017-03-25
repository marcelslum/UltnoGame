package com.marcelslum.ultnogame;

public class GroupDataBaseData {

    public int number, levels, stars_to_unlock;
    public final static String TAG = "GroupDataBaseData";

    public GroupDataBaseData(int number, int levels, int stars_to_unlock) {
        this.number = number;
        this.levels = levels;
        this.stars_to_unlock = stars_to_unlock;
    }
}
