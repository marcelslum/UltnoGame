package com.marcelslum.ultnogame;

/**
 * Created by marcel on 13/09/2016.
 */
public class SpecialBall extends Circle{
    int textureMap;
    int textureSituation;
    boolean isDead = false;
    static int timeOfLastSpecialBall;
    

    public SpecialBall(String name, float x, float y, float radius) {
        super(name, x, y, Entity.TYPE_SPECIAL_BALL, radius, 0);
        program = Game.specialBallProgram;
        isCollidable = false;
        isSolid = false;
        isVisible = true;
        textureId = Texture.TEXTURES;
        textureMap = 0;
        textureSituation = 0;
        alpha = 1f;
        setDrawInfo();
    }

    public void setDrawInfo(){
        verticesData = new float[12];
        indicesData = new short[6];
        uvsData =  new float[8];
        //colorsData = new float[16];

        Utils.insertRectangleVerticesData(verticesData, 0, 0f - radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);

        //Utils.insertRectangleColorsData(colorsData, 0, 1f, 1f, 1f, 0f);
        //colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE12_ID));
        //setUvData();
    }

    public void updateDrawInfo(){

            textureMap += 1;
            if (textureMap > 11) {
                textureMap = 0;
            }

            switch (textureMap) {
                case 0:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE1_ID));
                    break;
                case 1:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE2_ID));
                    break;
                case 2:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE3_ID));
                    break;
                case 3:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE4_ID));
                    break;
                case 4:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE5_ID));
                    break;
                case 5:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE6_ID));
                    break;
                case 6:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE7_ID));
                    break;
                case 7:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE8_ID));
                    break;
                case 8:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE9_ID));
                    break;
                case 9:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE10_ID));
                    break;
                case 10:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE11_ID));
                    break;
                case 11:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE12_ID));
                    break;
                default:
                    updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_BE2_ID));
                    break;
                }
    }


    public void setUvData(){
        Utils.insertRectangleUvData(uvsData, 0, textureData);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }


    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {
        updateDrawInfo();
        super.prepareRender(matrixView, matrixProjection);
    }

    public void verifyBars() {

        if (positionY - radius > Game.bars.get(0).positionY + Game.bars.get(0).getTransformedHeight()){
            isVisible = false;
            dvy = 0f;
            isDead = true;
        }

        for(int i = 0; i < Game.bars.size(); i++){
            Bar bar = Game.bars.get(i);
            if (positionY + radius > bar.positionY){
                if ((positionX - radius < bar.positionX + bar.getTransformedWidth() && positionX - radius > bar.positionX)||
                    (positionX + radius > bar.positionX && positionX + radius < bar.positionX + bar.getTransformedWidth())){
                    isVisible = false;
                    dvy = 0f;
                    isDead = true;

                    bar.specialBarScale();
                    Sound.play(Sound.soundBarSize, 0.12f, 0.12f, 0);
                }
            }
        }


    }
}
