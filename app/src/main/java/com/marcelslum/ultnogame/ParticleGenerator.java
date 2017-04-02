package com.marcelslum.ultnogame;

import android.opengl.GLES20;

import java.util.ArrayList;
public class ParticleGenerator extends Entity {
    
    int numberOfParticles = 500;
    ArrayList<Particle> particlesArray;
    boolean isActive;
    TextureData [] texturesData;

    ParticleGenerator(String name, float x, float y, TextureData td1, TextureData td2, TextureData td3) {
        super(name, x, y, Entity.TYPE_PARTICLE);
        program = Game.vertex_e_uv_com_alpha_program;
        textureId = Texture.TEXTURES;
        texturesData = new TextureData []{td1, td2, td3};
        generate();
    }

    public void activate(){
        setDrawInfo();
        isVisible = true;
        isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }

    public void generate(){
        particlesArray= new ArrayList<>();
        for (int i = 0; i < numberOfParticles;i++) {
            float vx = Utils.getRandonFloat(-2.1f, 2.1f);
            float vy = Utils.getRandonFloat(-2.1f, 2.1f);
            float velocity_variation_x = Utils.getRandonFloat(-0.1f, 0.1f);
            float velocity_variation_y = Utils.getRandonFloat(-0.1f, 0.1f);
            float alpha_decay = Utils.getRandonFloat(0.01f, 0.005f);
            float size = Utils.getRandonFloat(0.5f, 5f);

            float textureMapFilter = Utils.getRandonFloat(0f, 1f);

            TextureData td;
            if (textureMapFilter < 0.33f) {
                td = texturesData[0];
            } else if (textureMapFilter < 0.66f) {
                td = texturesData[1];
            } else {
                td = texturesData[2];
            }
            
            Particle particle = new Particle(0, 0, vx, vy, velocity_variation_x, 
                velocity_variation_y, alpha_decay, size, td);
            
            particlesArray.add(particle);
        }
    }

    public void prepareRender(float[] matrixView, float[] matrixProjection){
        if (isActive) {
            updateDrawInfo();
            super.prepareRender(matrixView, matrixProjection);
        }
    }

    private void updateDrawInfo() {
        boolean ended = true;
        for (int i = 0; i < numberOfParticles;i++) {
            Particle p = particlesArray.get(i);
            p.x += p.vx;
            p.y += p.vy;
            p.vx += p.velocity_variation_x;
            p.vy += p.velocity_variation_y;
            p.alpha -= p.alpha_decay;
            if(p.alpha < 0f) p.alpha = 0f;

            if (p.alpha > 0f){
                ended = false;
            }
            
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 
                                                    p.x,
                                                    p.x + p.size,
                                                    p.y, 
                                                    p.y + p.size);
            
            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, 
                                            null, p.alpha);

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
        
        if (ended){
            isActive = false;
        }
    }


    @Override
    public void setDrawInfo() {
        
        if (vbo == null || vbo.length == 0){
            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
        
        initializeData(8 * numberOfParticles, 6 * numberOfParticles, 12 * numberOfParticles, 0);
        
        for (int i = 0; i < numberOfParticles;i++) {
            Particle p = particlesArray.get(i);
            Utils.insertRectangleVerticesDataXY(verticesData, i * 8, 0, p.size, 0f, p.size);
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
            Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, p.textureData, p.alpha);
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
        
    }

    private class Particle{
        float initX;
        float initY;
        float x;
        float y;
        float vx;
        float vy;
        float size;
        float alpha;
        float alpha_decay;
        float velocity_variation_x;
        float velocity_variation_y;
        TextureData textureData;

        public Particle(float initX, float initY, float vx, float vy, 
            float velocity_variation_x, float velocity_variation_y, float alpha_decay, float size, TextureData textureData) {
            this.initX = initX;
            this.initY = initY;
            this.x = initX;
            this.y = initY;
            this.vx = vx;
            this.vy = vy;
            this.velocity_variation_x = velocity_variation_x;
            this.velocity_variation_y = velocity_variation_y;
            this.alpha = 0.85f;
            this.alpha_decay = alpha_decay;
            this.size = size;
            this.textureData = textureData;
        }
    }
}
