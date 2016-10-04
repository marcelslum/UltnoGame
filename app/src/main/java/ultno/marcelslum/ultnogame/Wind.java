package ultno.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 08/09/2016.
 */
public class Wind extends Entity{

    public boolean isActive;
    int quantityOfWaves;
    int density;
    float frequenciaCentral;
    Wave[]waves;
    float height;
    boolean rightDirection;
    int soundStreamId;


    public Wind(String name, float x, float y, float height, boolean toRight) {
        super(name, x, y);
        this.height = height;
        program = Game.windProgram;
        isActive = false;
        isSolid = false;
        isCollidable = false;

        density = 30;
        frequenciaCentral = 3.0f;

        quantityOfWaves = (int)Math.floor(height/(float)density);
        waves = new Wave[quantityOfWaves];

        rightDirection = toRight;

        float frequency;
        float initX;
        float finalX;
        float waveY;

        for (int i = 0; i < quantityOfWaves; i++) {

            frequency = frequenciaCentral + 0.8f * (float) Math.cos(Utils.getRandonFloat(0.0f, 360.0f));

            if (!rightDirection){
                frequency *= -1.0f;
            }

            if (i%4 == 1) {
                initX = 0f;
                finalX = 1f;
                waveY = (float)i/(float)quantityOfWaves;
            } else {
                initX = 0.8f*Utils.getRandonFloat(0.0f, 1.0f);
                finalX = initX + 0.2f + Utils.getRandonFloat(0.0f, 2.0f);
                if (i%4 == 0) {
                    waveY = Utils.getRandonFloat(0f,0.33f);
                } else if(i%4 == 2) {
                    waveY = Utils.getRandonFloat(0.34f, 0.66f);
                } else if(i%4 == 3) {
                    waveY = Utils.getRandonFloat(0.67f, 1f);
                } else {
                    waveY = 0f;
                }
            }
            waves[i] = new Wave(initX, finalX, waveY, frequency);
        }
        setDrawInfo();
    }

    public void setDrawInfo(){
        verticesData = new float[12*quantityOfWaves];
        indicesData = new short[6*quantityOfWaves];
        colorsData = new float[16*quantityOfWaves];

        //Log.e("wind", "quantityOfWaves "+quantityOfWaves);

        for (int i = 0; i < quantityOfWaves; i++){

            float y1 = (waves[i].y - 0.1f)*Game.resolutionY;
            float y2 = (waves[i].y + 0.1f)*Game.resolutionY;
            Utils.insertRectangleVerticesData(verticesData, i * 12,  0f, Game.resolutionX, y1, y2, 0.0f);

            //Log.e("wind", "onda y1 "+y1+" y2 "+y2+" f"+waves[i].frequency);
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            color = new Color(waves[i].initX, waves[i].finalX, waves[i].frequency, waves[i].y);

            Utils.insertRectangleColorsData(colorsData, i * 16, waves[i].initX, waves[i].finalX, waves[i].frequency, waves[i].y);
        }

        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);
        this.colorsBuffer = Utils.generateFloatBuffer(this.colorsData);
    }

    public void stop(){
        Sound.soundPool.stop(soundStreamId);
        isActive = false;
        alpha = 0f;
    }

    public void init(){
        soundStreamId = Sound.play(Sound.soundWind, 0.6f, 0.6f, -1);
        isActive = true;
        alpha = 1f;
    }

    public class Wave{
        float initX;
        float y;
        float finalX;
        float frequency;

        Wave(float initX, float finalX, float y, float frequency){
            this.initX = initX;
            this.finalX = finalX;
            this.y = y;
            this.frequency = frequency;
        }
    }
}
