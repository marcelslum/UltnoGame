package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 25/08/2016.
 */
public class Obstacle extends Rectangle{

    Obstacle(String name, Game game, float x, float y, float width, float height) {
        super(name, game, x, y, width, height, 10, new Color(1.0f, 1.0f, 1.0f, 1.0f));
        this.textureUnit = Game.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE;
        this.program = this.game.imageProgram;
        this.setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 8, 0);

        Utils.insertRectangleVerticesData(verticesData,0, 0f, width, 0f, height, 0f);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleUvDataNumbersExplosion(uvsData, 0, 30);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

}
