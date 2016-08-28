package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 11/08/2016.
 */
public class Background extends Entity {

    //cor chanel painting 0,21 0,71 -1,87 -2,05 3,79 3e3e3eff
    // air spray

    public static final Color BACKGROUND_COLOR_GREEN = new Color(141f/255f, 209f/255f, 211f/255f, 1f);
    public static final Color BACKGROUND_COLOR_RED = new Color(193f/255f, 173f/255f, 170f/255f, 1f);
    public static final Color BACKGROUND_COLOR_YELLOW = new Color(235f/255f, 229f/255f, 66f/255f, 1f);
    public static final Color BACKGROUND_COLOR_BLUE = new Color(201f/255f, 136f/255f, 255f/255f, 1f);

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
    Rectangle rectangle;

    Background(String name, Game game, float x, float y, float width, float height, Color color) {
        super(name, game, x, y);
        this.width = width;
        this.height = height;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        textureUnit = Game.TEXTURE_BACKGROUND;
        program = game.imageColorizedFxProgram;
        this.alpha = 0.6f;

        uvXUp = true;
        uvYUp = true;

        uvxMin = 0.001f;
        uvxMax = 0.999f;
        uvyMin = 0.001f;
        uvyMax = 0.585937f;

        uvWidth = 0.5f;
        uvHeight = 0.4f;
        uvx1 = 0.401f;
        uvx2 = uvx1 + uvWidth;
        uvy1 = 0.2f;
        uvy2 = uvy1 + uvHeight;

        rectangle = new Rectangle("rectangleBack", game, -10f, -10f, width + 20f, height + 20f, -0.7f, 0, color);
        rectangle.isMovable = false;
        rectangle.isCollidable = false;

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

        Utils.insertRectangleVerticesData(this.verticesData, 0, -10f, width+20, -10f, height+20, -0.5f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        Utils.insertRectangleUvData(this.uvsData, 0, uvx1, uvx2, uvy1, uvy2);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);

    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {
        rectangle.render(matrixView, matrixProjection);
        super.render(matrixView, matrixProjection);
    }
}
