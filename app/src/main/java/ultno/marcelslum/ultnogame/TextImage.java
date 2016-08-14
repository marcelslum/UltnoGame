package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 14/08/2016.
 */
public class TextImage extends Entity{

    float width;
    float height;
    float x1, x2, y1, y2;

    TextImage(String name, Game game, float x, float y, float width, float height, int textureUnit, float x1, float x2, float y1, float y2){
        super(name, game, x, y);
        this.width = width;
        this.height = height;
        this.textureUnit = textureUnit;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.program = game.imageAlphaProgram;
        setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);

        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvData(uvsData, 0, x1, x2, y1, y2);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
}
