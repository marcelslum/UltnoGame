package ultno.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 25/08/2016.
 */
public class Obstacle extends Rectangle{

    public float sizeOfSquares;



    Obstacle(String name, Game game, float x, float y, float width, float height) {
        super(name, game, x, y, width, height, Game.OBSTACLES_WEIGHT, new Color(1.0f, 1.0f, 1.0f, 1.0f));
        this.textureUnit = Game.TEXTURE_TITTLE;
        this.program = this.game.imageProgram;
        setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);

        sizeOfSquares = (getWidth()/getTransformedWidth())* (game.resolutionX/10f);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertObstacleUvData(uvsData, 0, getTransformedWidth()/sizeOfSquares, getTransformedHeight()/sizeOfSquares);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    public void updateUvInfo(){
        Utils.insertObstacleUvData(uvsData, 0, getTransformedWidth()/sizeOfSquares, getTransformedHeight()/sizeOfSquares);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
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
        if (scaleVariationData != null) {
            ScaleVariationData s = scaleVariationData;
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
                this.accumulatedTranslateX += responseX;
                this.accumulatedTranslateY += responseY;
            }
        } else {
            this.accumulatedTranslateX += responseX;
            this.accumulatedTranslateY += responseY;
        }
        this.checkTransformations(false);
    }

}
