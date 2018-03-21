package com.marcelslum.ultnogame;

import android.opengl.GLES20;

public class WindNoShader extends Entity{

    public boolean isActive;
    int quantityOfWaves;
    //int density;
    //float frequenciaCentral;
    float height;
    float width;
    boolean toRight;
    int soundStreamId;
    float waveWidth;
    float waveHeight;

    TextureData [] wavesTextureData;
    float [] waveX;
    float [] waveY;
    float [] waveVx;
    
    //float randonWaveTexture;
    //float randonWaveY;
    //float randonWaveVx;

    public WindNoShader(String name, float x, float y, float height, float width, float waveWidth, boolean toRight) {
        super(name, x, y, Entity.TYPE_WIND);
        this.width = width;
        this.height = height;
        this.toRight = toRight;
        this.waveWidth = waveWidth;
        this.waveHeight = waveWidth * 0.48f;
        
        
        textureId = Texture.TEXTURES;
        program = Game.vertex_e_uv_com_alpha_program;
        isActive = false;
        isSolid = false;
        isCollidable = false;
        quantityOfWaves = 40;
        
        wavesTextureData = new TextureData[quantityOfWaves];
        waveX = new float[quantityOfWaves];
        waveY = new float[quantityOfWaves];
        waveVx = new float[quantityOfWaves];
        
     
        for (int i = 0; i < quantityOfWaves; i++) {
            
            float randonWaveTexture = Utils.getRandonFloat(0.0f, 1.0f);
            float randonWaveY = Utils.getRandonFloat(0.0f, 1.0f);
            float randonWaveVx = Utils.getRandonFloat(8.0f, 12.0f);
            if (!toRight){
                randonWaveVx *= -1;
            }
            
            
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
            
            if (toRight){
                waveX[i] = -(width * randonWaveY * 2);
            } else {
                waveX[i] = width + (width * randonWaveY * 2);
            }
            
            waveVx[i] = randonWaveVx;
        }
        
        setDrawInfo();
    }

    @Override
    public void checkTransformations(boolean updatePrevious) {
        super.checkTransformations(updatePrevious);
        changeDrawInfo();
    }

    public void changeDrawInfo(){
        if (isActive){
            for (int i = 0; i < quantityOfWaves; i++){
                waveX[i] += waveVx[i];
                if (waveX[i] > width * 1.1f && toRight){
                    float randonWaveY = Utils.getRandonFloat(0.0f, 1.0f);

                    float randonWaveVx = Utils.getRandonFloat(8.0f, 12.0f);
                    waveX[i] = -(width * randonWaveY * 2);
                    
                    waveY[i] = randonWaveY * height;
                    waveVx[i] = randonWaveVx;
                }


                if (waveX[i] < -width * 1.1f && !toRight){

                    float randonWaveY = Utils.getRandonFloat(0.0f, 1.0f);
                    float randonWaveVx = Utils.getRandonFloat(8.0f, 12.0f) * -1f;
                    waveX[i] = width + (width * randonWaveY * 2);
                    waveY[i] = randonWaveY * height;
                    waveVx[i] = randonWaveVx;
                }

                Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                    waveX[i],
                                                    waveX[i] + waveWidth,
                                                    waveY[i], 
                                                    waveY[i] + waveHeight
                                                    );
            }
            
            verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                            verticesBuffer, GLES20.GL_STATIC_DRAW);
            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        }
    }

    public void setDrawInfo(){
        
        if (vbo == null || vbo.length == 0){
            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
        
        initializeData(8 * quantityOfWaves, 6 * quantityOfWaves, 12 * quantityOfWaves, 0);
        
        for (int i = 0; i < quantityOfWaves; i++){
            
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                waveX[i],
                                                waveX[i] + waveWidth,
                                                waveY[i], 
                                                waveY[i] + waveHeight
                                                );
            
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, wavesTextureData[i], 0.06f);
        }
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                        indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        uvsBuffer.limit(0);
        uvsBuffer = null;

    }

    public void stop(){
        Sound.soundPool.stop(soundStreamId);
        isActive = false;
        alpha = 0f;
    }

    public void init(){
        soundStreamId = Sound.playSoundPool(Sound.soundWind, 0.6f, 0.6f, -1);
        isActive = true;
        alpha = 1f;
    }

}
