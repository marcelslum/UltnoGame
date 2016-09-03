package ultno.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 25/08/2016.
 */
public class Obstacle extends Rectangle{

    Obstacle(String name, Game game, float x, float y, float width, float height) {
        super(name, game, x, y, width, height, Game.OBSTACLES_WEIGHT, new Color(1.0f, 1.0f, 1.0f, 1.0f));
        this.textureUnit = Game.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE;
        this.program = this.game.imageProgram;
        this.setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);

        Utils.insertRectangleVerticesData(verticesData,0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvDataNumbersExplosion(uvsData, 0, 30);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
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
