package com.marcelslum.ultnogame;

import java.util.ArrayList;

public class BallParticleGenerator extends Entity {
    
    int number_of_particles = 30;
    boolean isActive = true;
    boolean isVisible = true;
    
    float [] px;
    float [] py;
    float [] pvx;
    float [] pvy;
    float [] palpha;
    float [] palpha_decay;
    float [] psize;
    


   BallParticleGenerator(String name, float x, float y) {
        
        super(name, x, y, Entity.TYPE_PARTICLE);
        program = Game.imageColorizedProgram;
        textureId = Texture.TEXTURES;
        
        px = new float[number_of_particles];
        py = new float[number_of_particles];
        pvx = new float[number_of_particles];
        pvy = new float[number_of_particles];
        palpha = new float[number_of_particles];
        palpha_decay = new float[number_of_particles];
        psize = new float[number_of_particles];

        for (int i = 0; i < number_of_particles; i++){
            palpha[i] = 0f;
        }
        
    }
    
    public void setDrawInfo(){
        
        if (vbo == null || vbo.length == 0){
            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
        
        initializeData(8 * number_of_particles, 6 * number_of_particles, 12 * number_of_particles, 0);
        
        for (int i = 0; i < number_of_particles; i++){
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                px[i],
                                                px[i] + psize,
                                                py[i], 
                                                py[i] + psize);
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_PARTICLE_BALL_ID), palpha[i]);
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
        
        indicesBuffer.limit(0);
        indicesBuffer = null;
        
    }

    public void activate(){
        this.isVisible = true;
        this.isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }


    // recebe a posição central do circulo
    public void addParticles(float x, float y, float radius, int quantity){
        int particlesToCreate = quantity;
        for (int i = 0; i < number_of_particles; i++){
            if (palpha[i] == 0f){
                particlesToCreate -= 1;
                px[i] = Utils.getRandonFloat(x - radius, x + radius);
                py[i] = Utils.getRandonFloat(y - radius, y + radius);
                pvx[i] = Utils.getRandonFloat(-0.4f, 0.4f);
                pvy[i] =  Utils.getRandonFloat(-0.4f, 0.4f);
                pvvx[i] = Utils.getRandonFloat(-0.08f, 0.08f);
                pvvy[i] = Utils.getRandonFloat(-0.08f, 0.08f);
                palpha[i] = 1f;
                palpha_decay[i] = Utils.getRandonFloat(0.01f, 0.05f);
                size[i] = radius;
            }
            if (particlesToCreate == 0){
                break;
            }
        }        
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){
        if (isActive) {
            updateDrawInfo();
            super.prepareRender(matrixView, matrixProjection);
        }
    }

    private void updateDrawInfo() {

        for (int i = 0; i < number_of_particles; i++){
                if (palpha > 0f){
                px[i] += pvx[i];
                py[i] += pvy[i];
                pvx[i] += pvvx[i];
                pvy[i] += pvvy[i];
                palpha[i] -= palpha_decay[i];
                if(palpha[i] < 0f) {palpha[i] = 0f;}
                Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                    px[i],
                                                    px[i] + psize,
                                                    py[i], 
                                                    py[i] + psize);
                Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_PARTICLE_BALL_ID), palpha[i]);
            }
        }
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer, GLES20.GL_STATIC_DRAW);


        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

    }
}
