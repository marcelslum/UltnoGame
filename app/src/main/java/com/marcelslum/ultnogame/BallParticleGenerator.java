package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class BallParticleGenerator extends Entity {
    
    int maxNumberOfParticles = 35;
    ArrayList<Particle> particlesArray;
    boolean isActive = true;
    boolean isVisible = true;
    
    BallParticleGenerator(String name, float x, float y) {
        super(name, x, y, Entity.TYPE_PARTICLE);
        program = Game.imageColorizedProgram;
        textureId = Texture.TEXTURE_NUMBERS_EXPLOSION;
        particlesArray= new ArrayList<>();
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

        // TODO criar pool de particulas

        for (int i = 0; i < quantityOfParticles;i++) {
            float vx = Utils.getRandonFloat(-0.4f, 0.4f);
            float vy = Utils.getRandonFloat(-0.4f, 0.4f);
            float velocity_variation_x = Utils.getRandonFloat(-0.08f, 0.08f);
            float velocity_variation_y = Utils.getRandonFloat(-0.08f, 0.08f);
            float alpha_decay = Utils.getRandonFloat(0.01f, 0.05f);
            float size = Utils.getRandonFloat(radius/2f, radius*3f);
            int textureMap = Texture.TEXTURE_MAP_NUMBERS_EXPLODE_BALL;
            
            float px = Utils.getRandonFloat(x - radius, x + radius);
            float py = Utils.getRandonFloat(y - radius, y + radius);
            
            Particle particle = new Particle(px, py, vx, vy, velocity_variation_x,
                velocity_variation_y, alpha_decay, size, textureMap);
            particlesArray.add(particle);
            
            if (particlesArray.size() > maxNumberOfParticles)
                particlesArray.remove(0);
        }
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
                Utils.insertRectangleUvDataNumbersExplosion(uvsData, i * 8, p.textureMap);
            }
        }

        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
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
        int textureMap;

        public Particle(float initX, float initY, float vx, float vy, 
            float velocity_variation_x, float velocity_variation_y, float alpha_decay, float size, int textureMap) {
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
            this.textureMap = textureMap;
        }
    }
}
