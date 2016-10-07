package com.marcelslum.ultnogame;

public class TargetBuilder {

    public final static int TARGET_TYPE_STATE_1 = 1;
    public final static int TARGET_TYPE_STATE_2 = 2;
    public final static int TARGET_TYPE_STATE_3 = 3;
    public final static int TARGET_TYPE_SPECIAL = 4;
    public final static int TARGET_TYPE_STATE_1_GHOST = 5;
    public final static int TARGET_TYPE_STATE_2_GHOST = 6;
    public final static int TARGET_TYPE_STATE_3_GHOST = 7;

    private String name;
    private Game game;
    private float x;
    private float y;
    private float width;
    private float height;
    private int weight;
    private int[] states;
    private int state;
    private int special;
    private boolean isGhost;
    private int type;

    public TargetBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TargetBuilder game(Game game) {
        this.game = game;
        return this;
    }

    public TargetBuilder x(float x) {
        this.x = x;
        return this;
    }

    public TargetBuilder y(float y) {
        this.y = y;
        return this;
    }

    public TargetBuilder width(float width) {
        this.width = width;
        return this;
    }

    public TargetBuilder height(float height) {
        this.height = height;
        return this;
    }

    public TargetBuilder weight(int weight) {
        this.weight = weight;
        return this;
    }

    public TargetBuilder states(int[] states) {
        this.states = states;
        return this;
    }

    public TargetBuilder type(int type) {
        this.type = type;
        return this;
    }

    public Target build() {
        special = 0;
        isGhost = false;
        state = 0;
        switch (type) {
            case 1:
                state = 1;
                break;
            case 2:
                state = 2;
                break;
            case 3:
                state = 3;
                break;
            case 4:
                special = 1;
                break;
            case 5:
                state = 1;
                isGhost = true;
                break;
            case 6:
                state = 2;
                isGhost = true;
                break;
            case 7:
                state = 3;
                isGhost = true;
                break;
        }

        return new Target(name, x, y, width, height, states, state, special, isGhost);
    }
}