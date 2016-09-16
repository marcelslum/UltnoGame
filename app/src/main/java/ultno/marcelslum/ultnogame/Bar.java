package ultno.marcelslum.ultnogame;


import android.util.Log;

/**
 * Created by marcel on 07/08/2016.
 */
public class Bar extends Rectangle{

    long startTimeSpecialBallAnim = 0;
    long specialBallAnimDuration = 1000;
    boolean specialBallAnimActive = false;

    Bar(String name, float x, float y, float width, float height){
        super(name, x, y, width, height, Game.BAR_WEIGHT, new Color(0.0f, 0.0f, 0.0f, 1.0f));

        this.program = Game.imageColorizedProgram;
        this.textureId = Texture.TEXTURE_BARS;
        this.isCollidable = true;
        this.isSolid = true;

        this.setDrawInfo();
    }

    public void insertUvData(float[] array, int startIndex){

        /*
        array[0 + (startIndex)] = 0f; x//
        array[1 + (startIndex)] = 1f; y//
        array[2 + (startIndex)] = 1f; x//
        array[3 + (startIndex)] = 1f; y//
        array[4 + (startIndex)] = 1f; x//
        array[5 + (startIndex)] = 0f; y//
        array[6 + (startIndex)] = 0f; x//
        array[7 + (startIndex)] = 0f; y//
        */
        //0,7998046875

        array[startIndex] = 0f;
        array[1 + (startIndex)] = 1-0.4931640625f;
        array[2 + (startIndex)] = 1f;
        array[3 + (startIndex)] = 1-0.4931640625f;
        array[4 + (startIndex)] = 1f;
        array[5 + (startIndex)] = 1-0.5615234375f;
        array[6 + (startIndex)] = 0f;
        array[7 + (startIndex)] = 1-0.5615234375f;
    }

    public void setDrawInfo(){
        verticesData = new float[12];
        insertVerticesData(this.verticesData,0);
        verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        indicesData = new short[6];
        insertIndicesData(this.indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        uvsData = new float[12];
        insertUvData(this.uvsData, 0);
        uvsBuffer = Utils.generateFloatBuffer(this.uvsData);

        colorsData = new float[16];
        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }


    public void insertVerticesData(float[] array, int startIndex){
        float x1 = 0f;
        float x2 = this.width;
        float y1 = 0f;
        float y2 = this.height;

        array[startIndex] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = 0.0f;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = 0.0f;
        array[6 + (startIndex)] = x2;
        array[7 + (startIndex)] = y1;
        array[8 + (startIndex)] = 0.0f;
        array[9 + (startIndex)] = x1;
        array[10 + (startIndex)] = y1;
        array[11 + (startIndex)] = 0.0f;
    }

    public void insertIndicesData(short[] array, int startIndex, int startValue){
        array[startIndex] = (short)(startValue);
        array[1 + (startIndex)] = (short)(1 + (startValue));
        array[2 + (startIndex)] = (short)(2 + (startValue));
        array[3 + (startIndex)] = (short)(startValue);
        array[4 + (startIndex)] = (short)(2 + (startValue));
        array[5 + (startIndex)] = (short)(3 + (startValue));
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        if (specialBallAnimActive){
            long elapsedTime = Utils.getTime() - startTimeSpecialBallAnim;
            if (elapsedTime < specialBallAnimDuration){
                color.r = 0.8f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
                color.g = 0.4f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
                color.b = 0.5f * (1.0f - ((float)elapsedTime/(float)specialBallAnimDuration));
            } else {
                specialBallAnimActive = false;
                color.r = 0f;
                color.g = 0f;
                color.b = 0f;
            }
            Log.e("bar", "color r "+color.r+ " g "+color.g+ " b "+color.b);
            Utils.insertRectangleColorsData(colorsData, 0, color);
            colorsBuffer = Utils.generateFloatBuffer(colorsData);
        }

        super.prepareRender(matrixView, matrixProjection);
    }

    public void specialBarScale() {
        scale(0.1f, 0.0f);
        startTimeSpecialBallAnim = Utils.getTime();
        specialBallAnimActive = true;
    }
}
