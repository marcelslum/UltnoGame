package ultno.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 19/08/2016.
 */
public class ParticleGenerator extends Entity {
    
    int numberOfParticles = 100;
    ArrayList<Particle> particlesArray;
    float size;
    boolean isActive;

    ParticleGenerator(String name, Game game, float x, float y) {
        super(name, game, x, y);
        this.program = game.imageAlphaArrayProgram;
        size = 3f;

        generate();
    }

    public void activate(){
        setDrawInfo();
        this.isVisible = true;
        this.isActive = true;
    }

    public void deactivate(){
        this.isActive = false;
    }

    public void generate(){
        particlesArray= new ArrayList<>();
        for (int i = 0; i < numberOfParticles;i++) {
            float vx = Utils.getRandonFloat(-5.1f, 5.1f);
            float vy = Utils.getRandonFloat(-5.1f, 5.1f);
            float vy = Utils.getRandonFloat(-5.1f, 5.1f);
            float velocity_variation_x = Utils.getRandonFloat(-0.2f, 0.2f);
            float velocity_variation_y = Utils.getRandonFloat(-0.2f, 0.2f);
            float alpha_decay = Utils.getRandonFloat(0.01f, 0.08f);
            float size = Utils.getRandonFloat(2f, 10f);
            Particle particle = new Particle(0, 0, vx, vy, velocity_variation_x, 
                velocity_variation_y, alpha_decay, size, textureMap);
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
        for (int i = 0; i < numberOfParticles;i++) {
            Log.e("particle", "updateDraw da particula "+i);
            Particle p = particlesArray.get(i);
            p.x += p.vx;
            p.y += p.vy;
            Utils.insertRectangleVerticesData(this.verticesData, 0 + (i * 12), p.x, p.x+size, p.y, p.y+size, 0f);
        }

        verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
    }


    @Override
    public void setDrawInfo() {
        initializeData(12*numberOfParticles, 6*numberOfParticles, 8*numberOfParticles, 0, 1*numberOfParticles);
        for (int i = 0; i < numberOfParticles;i++) {
            Utils.insertRectangleVerticesData(this.verticesData, 0 + (i * 12), 0, size, 0f, size, 0f);
            Utils.insertRectangleIndicesData(this.indicesData, 0 + (i * 6), 0 + (i * 4));
            Utils.insertRectangleUvDataNumbersAndExplosion(this.uvsData, 0 + (i * 8), textureMap);
            Utils.insertAlphaData(this.indicesData, 0 + i);
        }
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
        alphaBuffer = Utils.generateFloatBuffer(alphaData);
        
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
            this.alpha_decay = alpha_decay;
            this.size = size;
            this.textureMap = textureMap;
        }
    }
}
