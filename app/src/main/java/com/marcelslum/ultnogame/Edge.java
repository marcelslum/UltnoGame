package com.marcelslum.ultnogame;

/**
 * Created by marcel on 27/09/2016.
 */
public class Edge extends Rectangle{
    Edge(String name, float x, float y, int type, float width, float height, Color color) {
        super(name, x, y, type, width, height, Game.BORDA_WEIGHT, color);
        isMovable = false;
        isVisible = true;
        isCollidable = false;
        program = Game.solidProgram;

        centerX = positionX + (width/2f);
        centerY = positionY + (height/2f);
        maxWidth = width;
        maxHeight = height;

    }
}
