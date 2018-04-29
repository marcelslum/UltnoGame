package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;
public class ParticleGenerator extends Entity {
    
    static int numberOfParticles = 500;
    Particle[] particlesArray = new Particle[numberOfParticles];
    boolean isActive;
    TextureData [] texturesData;


    public static final String TAG = "Particle Generator";
    static ParticleGenerator[] pgArray;
    static int lastParticleGenerator = -1;


    public static ParticleGenerator getNew(float x, float y){
        lastParticleGenerator += 1;
        if (lastParticleGenerator == 10){
            lastParticleGenerator = 0;
        }

        ParticleGenerator pg = pgArray[lastParticleGenerator];

        pg.reset();


        pg.x = x;
        pg.y = y;
        return pg;
    }


    public static void loadParticleGenerators(){

        lastParticleGenerator = -1;
        pgArray  = new ParticleGenerator[10];
        for (int i = 0; i < pgArray.length; i++) {
            pgArray[i] = new ParticleGenerator("explode", 0, 0,
                    TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_1_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_2_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_EXPLOSION_RED_3_ID));

            for (int j = 0; j < numberOfParticles;j++) {
                float vx = Utils.getRandonFloat(-4.2f, 4.2f);
                float vy = Utils.getRandonFloat(-4.2f, 4.2f);
                float velocity_variation_x = Utils.getRandonFloat(-0.1f, 0.1f);
                float velocity_variation_y = Utils.getRandonFloat(-0.1f, 0.1f);
                float alpha_decay = Utils.getRandonFloat(0.02f, 0.01f);
                float size = Utils.getRandonFloat(0.5f, 5f);

                float textureMapFilter = Utils.getRandonFloat(0f, 1f);

                TextureData td;
                if (textureMapFilter < 0.33f) {
                    td = pgArray[i].texturesData[0];
                } else if (textureMapFilter < 0.66f) {
                    td = pgArray[i].texturesData[1];
                } else {
                    td = pgArray[i].texturesData[2];
                }

                pgArray[i].particlesArray[j] = new Particle(0, 0, vx, vy, velocity_variation_x, velocity_variation_y, alpha_decay, size, td);
            }
        }
    }


    ParticleGenerator(String name, float x, float y, TextureData td1, TextureData td2, TextureData td3) {
        super(name, x, y, Entity.TYPE_PARTICLE);
        program = Game.vertex_e_uv_com_alpha_program;
        textureId = Texture.TEXTURES;
        texturesData = new TextureData []{td1, td2, td3};
        //generate();
    }

    public void setTexturesData(TextureData td1, TextureData td2, TextureData td3){
        texturesData = new TextureData []{td1, td2, td3};
        for (int j = 0; j < numberOfParticles;j++) {
            float textureMapFilter = Utils.getRandonFloat(0f, 1f);
            if (textureMapFilter < 0.33f) {
                particlesArray[j].textureData = texturesData[0];
            } else if (textureMapFilter < 0.66f) {
                particlesArray[j].textureData = texturesData[1];
            } else {
                particlesArray[j].textureData = texturesData[2];
            }
        }
    }

    public void activate(){
        setDrawInfo();
        isVisible = true;
        isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }


    public void reset() {
        for (int i = 0; i < numberOfParticles; i++) {
            particlesArray[i].initX = 0;
            particlesArray[i].initY = 0;
            particlesArray[i].vx = Utils.getRandonFloat(-4.2f, 4.2f);
            particlesArray[i].vy = Utils.getRandonFloat(-4.2f, 4.2f);
            particlesArray[i].alpha = 1f;
        }
    }


    public void generate(){

        /*
        Log.e(TAG, "generate");

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

            Log.e(TAG, "addParticle");

        }
        */
    }

    public void prepareRender(float[] matrixView, float[] matrixProjection){
        if (isActive) {

            //Log.e(TAG, "prepareRender "+x);

            if (!MyGLRenderer.tick) {
                updateDrawInfo();
            }
            super.prepareRender(matrixView, matrixProjection);
        }
    }

    private void updateDrawInfo() {

        boolean ended = true;
        for (int i = 0; i < particlesArray.length;i++) {

            Particle p = particlesArray[i];
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



        for (int i = 0; i < particlesArray.length;i++) {
            Particle p = particlesArray[i];
            if (p == null) {
                return;
            }
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

    private static class Particle{
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
