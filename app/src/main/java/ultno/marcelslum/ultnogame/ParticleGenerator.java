package ultno.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 19/08/2016.
 */
public class ParticleGenerator extends Entity {
    
    int numberOfParticles = 300;
    ArrayList<Particle> particlesArray;
    boolean isActive;

    ParticleGenerator(String name, Game game, float x, float y) {
        super(name, game, x, y);
        program = game.imageColorizedProgram;
        textureUnit = game.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE;

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
            float vx = Utils.getRandonFloat(-1.1f, 1.1f);
            float vy = Utils.getRandonFloat(-1.1f, .1f);
            float velocity_variation_x = Utils.getRandonFloat(-0.1f, 0.1f);
            float velocity_variation_y = Utils.getRandonFloat(-0.1f, 0.1f);
            float alpha_decay = Utils.getRandonFloat(0.01f, 0.005f);
            float size = Utils.getRandonFloat(1f, 7f);
            int textureMap;
            float textureMapFilter = Utils.getRandonFloat(0f, 1f);
            if (textureMapFilter < 0.2f) {
                textureMap = Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR1;
            } else if (textureMapFilter < 0.4f) {
                textureMap = Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR2;
            } else if (textureMapFilter < 0.6f) {
                textureMap = Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR3;
            } else if (textureMapFilter < 0.8f) {
                textureMap = Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR4;
            } else {
                textureMap = Game.TEXTURE_MAP_NUMBERS_EXPLODE_COLOR5;
            }
            
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
            //Log.e("particle", "updateDraw da particula "+i);
            Particle p = particlesArray.get(i);
            p.x += p.vx;
            p.y += p.vy;
            p.vx += p.velocity_variation_x;
            p.vy += p.velocity_variation_y;
            p.alpha -= p.alpha_decay;
            if(p.alpha < 0f) p.alpha = 0f;
            Utils.insertRectangleVerticesData(this.verticesData, 0 + (i * 12), p.x, p.x + p.size, p.y, p.y + p.size, 0f);
            Utils.insertRectangleColorsData(colorsData, 0 + (i * 16), new Color(0f, 0f, 0f, p.alpha));
        }
        verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }


    @Override
    public void setDrawInfo() {
        initializeData(12*numberOfParticles, 6*numberOfParticles, 8*numberOfParticles, 16*numberOfParticles);
        for (int i = 0; i < numberOfParticles;i++) {
            Particle p = particlesArray.get(i);
            Utils.insertRectangleVerticesData(verticesData, 0 + (i * 12), 0, p.size, 0f, p.size, 0f);
            Utils.insertRectangleIndicesData(indicesData, 0 + (i * 6), 0 + (i * 4));
            Utils.insertRectangleUvDataNumbersExplosion(uvsData, 0 + (i * 8), p.textureMap);
            Utils.insertRectangleColorsData(colorsData, 0 + (i * 16), new Color(0f, 0f, 0f, p.alpha));
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
            this.alpha = 0.85f;
            this.alpha_decay = alpha_decay;
            this.size = size;
            this.textureMap = textureMap;
        }
    }
}