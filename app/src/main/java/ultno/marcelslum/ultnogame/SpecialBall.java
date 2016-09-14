package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 13/09/2016.
 */
public class SpecialBall extends Circle{
    int textureMap;
    int textureSituation;

    public SpecialBall(String name, float x, float y, float radius) {
        super(name, x, y, radius, 0);
        program = Game.specialBallProgram;
        isCollidable = false;
        isSolid = false;
        isVisible = true;
        textureId = Game.TEXTURE_SPECIAL_BALL;
        textureMap = 0;
        textureSituation = 0;
        setDrawInfo();
    }

    public void setDrawInfo(){
        verticesData = new float[12];
        indicesData = new short[6];
        uvsData =  new float[8];
        colorsData = new float[16];

        Utils.insertRectangleVerticesData(verticesData, 0, 0f - radius, radius, 0f - radius, radius, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleColorsData(colorsData, 0, 1.0f, 1.0f, 1.0f, 0.0f);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);

        setUvData();
    }

    public void updateDrawInfo(){

        if (textureSituation == 1) {
            textureMap += 1;
            if (textureMap > 11) {
                textureMap = 0;
            }
            setUvData();
            textureSituation = 0;
        } else {
            textureSituation += 1;
        }

    }

    public void setUvData(){
        if (textureMap < 9){
            Utils.y1 = (0f + 1f)/1024f;
            Utils.y2 = (128f - 1f)/1024f;
        } else {
            Utils.y1 = (128f + 1f)/1024f;
            Utils.y2 = (256f - 1f)/1024f;
        }
        if (textureMap == 1 || textureMap == 9){
            Utils.x1 = (0f + 1f)/1024f;
            Utils.x2 = (128f - 1f)/1024f;
        } else if (textureMap == 2 || textureMap == 10){
            Utils.x1 = (128f + 1f)/1024f;
            Utils.x2 = (256f - 1f)/1024f;
        } else if (textureMap == 3 || textureMap == 11){
            Utils.x1 = (256f + 1f)/1024f;
            Utils.x2 = (384f - 1f)/1024f;
        } else if (textureMap == 4 || textureMap == 12){
            Utils.x1 = (384f + 1f)/1024f;
            Utils.x2 = (512f - 1f)/1024f;
        } else if (textureMap == 5 || textureMap == 13){
            Utils.x1 = (512f + 1f)/1024f;
            Utils.x2 = (640f - 1f)/1024f;
        } else if (textureMap == 6 || textureMap == 14){
            Utils.x1 = (640f + 1f)/1024f;
            Utils.x2 = (768f - 1f)/1024f;
        } else if (textureMap == 7 || textureMap == 15){
            Utils.x1 = (768f + 1f)/1024f;
            Utils.x2 = (896f - 1f)/1024f;
        } else if (textureMap == 8 || textureMap == 16){
            Utils.x1 = (896f + 1f)/1024f;
            Utils.x2 = (1024f - 1f)/1024f;
        }
        Utils.insertRectangleUvData(uvsData, 0);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {
        updateDrawInfo();
        super.prepareRender(matrixView, matrixProjection);
    }
}