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
    float [] size;
    


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
        size = new float[number_of_particles];

        for (int i = 0; i < number_of_particles; i++){
            palpha[i] = 0f;
        }
        
    }

    public void activate(){
        this.isVisible = true;
        this.isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }


    // recebe a posição central do circulo
    public void generate(float x, float y, float radius, int quantityOfParticles){
        int particlesToCreate = quantityOfParticles;
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
      

        

            Particle particle = particlePool.get();
            particle.setData(px, py, vx, vy, velocity_variation_x,
                velocity_variation_y, alpha_decay, size,
                    TextureData.getTextureDataById(TextureData.TEXTURE_PARTICLE_BALL_ID));

            particlesArray.add(particle);
            
        
        
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){
        if (isActive && particlesArray.size() > 0) {
            updateDrawInfo();
            //Log.e("ballParticleGenerator", "render");
            super.prepareRender(matrixView, matrixProjection);
        }
    }

    private void updateDrawInfo() {
        boolean createBuffers = false;


        if (colorsData == null || particlesArray.size() != (colorsData.length/16)){
            initializeData(12*particlesArray.size(), 6*particlesArray.size(), 8*particlesArray.size(), 16*particlesArray.size());
            createBuffers = true;
        }

        for (int i = 0; i < particlesArray.size();i++) {
            //Log.e("particle", "updateDraw da particula "+i);
            Particle p = particlesArray.get(i);
            
            p.x += p.vx;
            p.y += p.vy;
            p.vx += p.velocity_variation_x;
            p.vy += p.velocity_variation_y;
            p.alpha -= p.alpha_decay;
            if(p.alpha < 0f) p.alpha = 0f;

            //Log.e(TAG, " inserindo particle "+
            //        (p.x - p.size/2f) + " " +
            //        (p.x + p.size/2f) + " " +
            //        (p.y- p.size/2f) + " " +
            //        (p.y + p.size/2f));


            Utils.insertRectangleVerticesData(verticesData, i * 12, p.x - p.size/2f, p.x + p.size/2f, p.y- p.size/2f, p.y + p.size/2f, 0f);
            //Log.e("ballParticleGenerator", " "+p.x+" "+p.y+" "+p.size);

            if (createBuffers) {
                Utils.insertRectangleColorsData(colorsData, i * 16, new Color(0.1f, 0.1f, 0.1f, p.alpha));
                Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);
                Utils.insertRectangleUvData(uvsData, i * 8, p.textureData);
            }
        }

        //if (verticesBuffer == null) {
        //    verticesBuffer = Utils.generateFloatBuffer(verticesData);
        //} else {

        //}

        //Utils.updateFloatBuffer(verticesData, verticesBuffer);


        if (verticesBuffer == null || verticesBuffer.capacity() != verticesData.length){
            verticesBuffer = Utils.generateFloatBuffer(verticesData);
        } else {
            Utils.updateFloatBuffer(verticesData, verticesBuffer);
        }
        //verticesBuffer = Utils.generateFloatBuffer(verticesData);

        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);
    }

    private class ParticleFactory implements PoolObjectFactory<Particle>{
        @Override
        public Particle newObject() {
            return new Particle();
        }
    }

    private class Particle implements Poolable<Particle>{
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
        private int poolID;

        public Particle(){

        }

        public void setData(float initX, float initY, float vx, float vy,
            float velocity_variation_x, float velocity_variation_y, float alpha_decay, float size, TextureData textureData) {
            this.initX = initX;
            this.initY = initY;
            this.x = initX;
            this.y = initY;
            this.vx = vx;
            this.vy = vy;
            this.velocity_variation_x = velocity_variation_x;
            this.velocity_variation_y = velocity_variation_y;
            this.alpha = 0.2f;
            this.alpha_decay = alpha_decay;
            this.size = size;
            this.textureData = textureData;
        }

        @Override
        public void setPoolID(int id) {
            this.poolID = id;
        }

        @Override
        public int getPoolID() {
            return this.poolID;
        }

        @Override
        public Particle get() {
            return this;
        }

        @Override
        public void clean() {
            initX = 0;
            initY = 0;
            x = 0;
            y = 0;
            vx = 0;
            vy = 0;
            velocity_variation_x = 0;
            velocity_variation_y = 0;
            alpha = 0.2f;
            alpha_decay = 0;
            size = 0;
            textureData = null;

        }
    }
}
