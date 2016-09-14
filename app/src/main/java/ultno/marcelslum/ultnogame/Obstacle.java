package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 25/08/2016.
 */
public class Obstacle extends Rectangle{

    public float sizeOfSquares;

    Obstacle(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, Game.OBSTACLES_WEIGHT, new Color(1.0f, 1.0f, 1.0f, 1.0f));
        this.textureId = Game.TEXTURE_TITTLE;
        this.program = Game.imageProgram;
        setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);

        sizeOfSquares = (getWidth()/getTransformedWidth())* (Game.resolutionX/10f);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertObstacleUvData(uvsData, 0, getTransformedWidth()/sizeOfSquares, getTransformedHeight()/sizeOfSquares);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    public void updateUvInfo(){
        Utils.insertObstacleUvData(uvsData, 0, getTransformedWidth()/sizeOfSquares, getTransformedHeight()/sizeOfSquares);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }


    @Override
    public void checkTransformations(boolean updatePrevious) {

        // verifica antes de "checkTransformations" se haverá alteração na escala
        boolean changeUfInfo = false;

        if (scaleVariationData != null){
            if (scaleVariationData.isActive){
                if (!(scaleVariationData.widthVelocity == 0f && scaleVariationData.heightVelocity == 0f)){
                    changeUfInfo = true;
                }
            }
        }

        super.checkTransformations(updatePrevious);
        //Log.e("obstacle", "change draw info"+changeDrawInfo);
        // se houve alteração na escala, "updateUvInfo" para alterar os dados dos quadrados internos
        if (changeUfInfo) {
            updateUvInfo();
        }
    }



}
