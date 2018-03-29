package com.marcelslum.ultnogame;

/**
 * Created by marcel on 25/08/2016.
 */
public class Obstacle extends Rectangle{

    public float sizeOfSquares;

    Obstacle(String name, float x, float y, float width, float height) {
        super(name, x, y, Entity.TYPE_OBSTACLE, width, height, Game.OBSTACLES_WEIGHT, new Color(0.52f, 0.6f, 0.6f, 1.0f));
        this.program = Game.solidProgram;
        setDrawInfo();
    }

    public void updateUvInfo(){
        //Utils.insertObstacleUvData(uvsData, 0, getTransformedWidth()/sizeOfSquares, getTransformedHeight()/sizeOfSquares);
        //uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }


    @Override
    public void checkTransformations(boolean updatePrevious) {

        // verifica antes de "checkTransformations" se haverá alteração na escala
        boolean changeUfInfo = false;

        if (scaleVariationData != null){
            if (scaleVariationData.isActive){
                if (!(scaleVariationData.widthVelocity == 0f && scaleVariationData.heightVelocity == 0f)){
                    changeUfInfo = true;
                }
            }
        }

        super.checkTransformations(updatePrevious);
        //Log.e("obstacle", "change draw info"+changeDrawInfo);
        // se houve alteração na escala, "updateUvInfo" para alterar os dados dos quadrados internos
        if (changeUfInfo) {
            updateUvInfo();
        }
    }

    public void respondToCollision(float responseX, float responseY){
        //Log.e("physical", "respond to collision " +responseX);
        PositionVariationData p;
        ScaleVariationData s;

        boolean decreaseX = false;
        boolean increaseX = false;
        boolean decreaseY = false;
        boolean increaseY = false;

        if (positionVariationData != null){
            p = positionVariationData;
            if (p.isActive) {
                if (responseX < 0f && p.increaseX){
                    decreaseX = true;
                } else if (responseX > 0f && !p.increaseX){
                    increaseX = true;
                }
                if (responseY < 0f && p.increaseY){
                    decreaseY = true;
                } else if (responseY > 0f && !p.increaseY){
                    increaseY = true;
                }
            }
        }

        if (positionVariationData != null) {
            p = positionVariationData;
            if (decreaseX) {
                positionX -= p.xVelocity * Game.gameAreaResolutionX;
                p.increaseX = false;
            }
            if (increaseX) {
                positionX += p.xVelocity * Game.gameAreaResolutionX;
                p.increaseX = true;
            }
            if (decreaseY) {
                positionY -= p.yVelocity * Game.gameAreaResolutionY;
                p.increaseY = false;
            }
            if (increaseY) {
                positionY += p.yVelocity * Game.gameAreaResolutionY;
                p.increaseY = true;
            }
        }

        if (scaleVariationData != null) {
            s = scaleVariationData;
            if (s.isActive) {
                if (responseX != 0f){
                    scaleX -= Math.abs(responseX) / getTransformedWidth();
                    s.increaseWidth = false;
                }

                if (responseY != 0f){
                    scaleY -= Math.abs(responseY) / getTransformedHeight();
                    s.increaseHeight = false;
                }
            } else {
                accumulatedTranslateX += responseX;
                accumulatedTranslateY += responseY;
            }
        } else {
            accumulatedTranslateX += responseX;
            accumulatedTranslateY += responseY;
        }

        checkTransformations(false);
    }
}
