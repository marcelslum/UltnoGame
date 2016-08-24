package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 11/08/2016.
 */
public class Background extends Entity {

    float width;
    float height;
    float uvx1;
    float uvx2;
    float uvy1;
    float uvy2;
    float uvxMin;
    float uvxMax;
    float uvyMin;
    float uvyMax;
    float uvWidth;
    float uvHeight;
    boolean uvXUp;
    boolean uvYUp;

    Background(String name, Game game, float x, float y, float width, float height) {
        super(name, game, x, y);
        this.width = width;
        this.height = height;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        textureUnit = Game.TEXTURE_BACKGROUND;
        program = game.imageColorizedFxProgram;
        alpha = 1;

        uvXUp = true;
        uvYUp = true;

        uvxMin = 0.001f;
        uvxMax = 0.999f;
        uvyMin = 0.001f;
        uvyMax = 0.585937f;

        uvWidth = 0.3f;
        uvHeight = 0.2f;
        uvx1 = 0.401f;
        uvx2 = uvx1 + uvWidth;
        uvy1 = 0.2f;
        uvy2 = uvy1 + uvHeight;

        setDrawInfo();
    }

    public void move(int velocity) {

        float vel = (float)velocity/10000;
        
        if (uvXUp){
            uvx1 += vel;
            uvx2 = uvx1 + uvWidth;
            if (uvx2 >= uvxMax){
                uvXUp = false;
                uvx1 -= vel*2;
                uvx2 = uvx1 + uvWidth;
            }
        } else {
            uvx1 -= vel;
            uvx2 = uvx1 + uvWidth;
            if (uvx1 <= uvxMin){
                uvXUp = true;
                uvx1 += vel*2;
                uvx2 = uvx1 + uvWidth;
            }
        }

        if (uvYUp){
            uvy1 += vel;
            uvy2 = uvy1 + uvHeight;
            if (uvy2 >= uvyMax){
                uvYUp = false;
                uvy1 -= vel*2;
                uvy2 = uvy1 + uvHeight;
            }
        } else {
            uvy1 -= vel;
            uvy2 = uvy1 + uvHeight;

            if (uvy1 <= uvyMin){
                uvYUp = true;
                uvy1 += vel*2;
                uvy2 = uvy1 + uvHeight;
            }
        }

        Utils.insertRectangleUvData(this.uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }

    public void setDrawInfo() {
        
        initializeData(12, 6, 8, 0);

        Utils.insertRectangleVerticesData(this.verticesData, 0, 0f, width, 0f, height, -0.5f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        Utils.insertRectangleUvData(this.uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);

    }
}
