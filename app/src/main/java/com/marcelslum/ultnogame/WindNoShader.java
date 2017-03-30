package com.marcelslum.ultnogame;

public class WindNoShader extends Entity{

    public boolean isActive;
    int quantityOfWaves;
    int density;
    float frequenciaCentral;
    Wave[]waves;
    float height;
    float width;
    boolean toRight;
    int soundStreamId;
    float waveSize;
    
    int [] wavesTextureData;
    float [] waveX;
    float [] waveY;
    float [] waveVx;

    public WindNoShader(String name, float x, float y, float height, float width, float waveSize, boolean toRight) {
        super(name, x, y, Entity.TYPE_WIND);
        this.width = width;
        this.height = height;
        this.toRight = toRight;
        this.waveSize = saveSize;
        
        textureId = Texture.TEXTURES;
        program = Game.vertex_e_uv_com_alpha_program;
        isActive = false;
        isSolid = false;
        isCollidable = false;
        quantityOfWaves = 20;
        
        wavesTextureData = new int[quantityOfWaves];
        waveX = new float[quantityOfWaves];
        waveY = new float[quantityOfWaves];
        waveVx = new float[quantityOfWaves];
        
     
        for (int i = 0; i < quantityOfWaves; i++) {
            
            float randonWaveTexture = Utils.getRandonFloat(0.0f, 1.0f);
            float randonWaveY = Utils.getRandonFloat(0.0f, 1.0f);
            float randonWaveVx = Utils.getRandonFloat(1.0f, 3.0f);
            
            if (randonWaveTexture < 0.16f){
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD1);
            } else if (randonWaveTexture < 0.33f) {
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD2);
            } else if (randonWaveTexture < 0.5f) {
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD3);
            } else if (randonWaveTexture < 0.66f) {
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD4);
            } else if (randonWaveTexture < 0.83f){
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD5);
            } else {
                wavesTextureData[i] = TextureData.getTextureDataById(TextureData.TEXTURE_CLOUD6);
            }
            
            waveY[i] = randonWaveY * height;
            
            waveX[i] = - (waveSize + (waveSize * 2 * randonWaveY));
            
            waveVx[i] = randonWaveVx;
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

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
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
