package ultno.marcelslum.ultnogame;

import android.util.Log;

/**
 * Created by marcel on 13/09/2016.
 */
public class SpecialBall extends Circle{

    public SpecialBall(String name, float x, float y, float radius) {
        super(name, x, y, radius, 0);
        program = Game.specialBallProgram;
        isCollidable = false;
        isSolid = false;
        isVisible = true;
        setDrawInfo();
    }

    public void setDrawInfo(){
        verticesData = new float[12];
        indicesData = new short[6];
        colorsData = new float[16];

        Utils.insertRectangleVerticesData(verticesData, 0, 0f - radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleColorsData(colorsData, 0, (positionX + Game.screenOffSetX)/Game.effectiveScreenWidth, (positionY + Game.screenOffSetY)/Game.effectiveScreenHeight, radius, 0.0f);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }

    public void updateDrawInfo(){

        //Log.e("specialball", "updating special ball "+positionX+" "+positionY);

        Utils.insertRectangleColorsData(colorsData, 0, positionX, positionY, radius, 0.0f);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {
        updateDrawInfo();
        super.prepareRender(matrixView, matrixProjection);
    }
}
