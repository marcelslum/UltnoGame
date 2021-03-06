package com.marcelslum.ultnogame;

import android.opengl.GLES20;

import java.util.ArrayList;

public class BallParticleGenerator extends Entity {
    
    int number_of_particles = 35;
    boolean isActive = true;
    boolean isVisible = true;
    
    float [] px;
    float [] py;
    float [] pvx;
    float [] pvy;
    float [] pvvx;
    float [] pvvy;
    float [] palpha;
    float [] palpha_decay;
    float [] psize;
    
    BallParticleGenerator(String name, float x, float y) {
        
        super(name, x, y, Entity.TYPE_PARTICLE);
        program = Game.vertex_e_uv_com_alpha_program_e_color;
        textureId = Texture.TEXTURES;
        
        px = new float[number_of_particles];
        py = new float[number_of_particles];
        pvx = new float[number_of_particles];
        pvy = new float[number_of_particles];
        pvvx = new float[number_of_particles];
        pvvy = new float[number_of_particles];
        palpha = new float[number_of_particles];
        palpha_decay = new float[number_of_particles];
        psize = new float[number_of_particles];

        for (int i = 0; i < number_of_particles; i++){
            palpha[i] = 0f;
        }

        setDrawInfo();
    }


    public void setColor(Color color){

        for (int i = 0; i < number_of_particles; i++){
            Utils.insertRectangleColorsData(colorsData, i * 16, color);
        }

        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        colorsBuffer.limit(0);
        colorsBuffer = null;

    }
    
    public void setDrawInfo(){
        
        if (vbo == null || vbo.length == 0){
            vbo = new int[3];
            ibo = new int[1];

            GLES20.glGenBuffers(3, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
        
        initializeData(8 * number_of_particles, 6 * number_of_particles, 12 * number_of_particles, 16 * number_of_particles);
        
        for (int i = 0; i < number_of_particles; i++){
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                px[i],
                                                px[i] + psize[i],
                                                py[i], 
                                                py[i] + psize[i]);
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_PARTICLE_BALL_ID), palpha[i]);
            Utils.insertRectangleColorsData(colorsData, i * 16, Color.amareloCheio);
        }
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                colorsBuffer, GLES20.GL_STATIC_DRAW);

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
                pvx[i] = Utils.getRandonFloat(-0.2f, 0.2f);
                pvy[i] =  Utils.getRandonFloat(-0.2f, 0.2f);
                pvvx[i] = Utils.getRandonFloat(-0.08f, 0.08f);
                pvvy[i] = Utils.getRandonFloat(-0.08f, 0.08f);
                palpha[i] = 0.5f;
                palpha_decay[i] = Utils.getRandonFloat(0.01f, 0.05f);
                psize[i] = radius;
            }
            if (particlesToCreate == 0){
                break;
            }
        }        
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){
        if (isActive) {

            if (MyGLRenderer.tick){
                updateDrawInfo();
            }


            super.prepareRender(matrixView, matrixProjection);
        }
    }

    private void updateDrawInfo() {

        for (int i = 0; i < number_of_particles; i++){
                if (palpha[i] > 0f){
                px[i] += pvx[i];
                py[i] += pvy[i];
                pvx[i] += pvvx[i];
                pvy[i] += pvvy[i];
                palpha[i] -= palpha_decay[i];
                if(palpha[i] < 0f) {palpha[i] = 0f;}
                Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                    px[i],
                                                    px[i] + psize[i],
                                                    py[i], 
                                                    py[i] + psize[i]);

                Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, null, palpha[i]);
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
