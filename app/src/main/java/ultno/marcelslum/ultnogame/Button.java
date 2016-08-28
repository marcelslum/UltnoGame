package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Button extends Entity{

    public float height;
    public float width;
    OnPress onPress;
    OnUnpress onUnpress;
    int textureMapPressed;
    int textureMapUnpressed;
    int textureMap;

    Button(String name, Game game, float x, float y, float width, float height, int textureUnit, float listenerScale) {
        super(name, game, x, y);
        this.height = height;
        this.width = width;
        this.isCollidable = false;
        this.isVisible = true;
        this.isMovable = false;
        this.isSolid =  false;

        this.textureUnit = textureUnit;
        this.program = this.game.imageProgram;

        float lw = width * listenerScale;
        float lh = height * listenerScale;
        float lx = this.x - (lw - width);
        float ly = this.y - (lh - height);

        final Button finalButton =  this;
        setListener(new InteractionListener("listenerButton"+this.name, lx, ly, lw, lh, 5000, this, game,
            new InteractionListener.PressListener() {
                @Override
                public void onPress() {
                    finalButton.setPressed();
                    if (finalButton.onPress != null){
                        finalButton.onPress.onPress();
                    }
                }

                @Override
                public void onUnpress() {
                    finalButton.setUnpressed();
                    if (finalButton.onUnpress != null){
                        finalButton.onUnpress.onUnpress();
                    }
                }
            }
        ));
    }
    
    public void setTextureMap(int textureMap){
        this.textureMap = textureMap;
        this.setDrawInfo();
    }

    public void setPressed() {
        this.isPressed = true;
        setTextureMap(textureMapPressed);
    }

    public void setUnpressed() {
        this.isPressed = false;
        setTextureMap(textureMapUnpressed);
    }

    public void setOnPress(OnPress onPress){
        this.onPress = onPress;
    }

    public void setOnUnpress(OnUnpress onUnpress){
        this.onUnpress = onUnpress;
    }

    public interface OnPress{
        public void onPress();
    }

    public interface OnUnpress{
        public void onUnpress();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 12, 0);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        Utils.insertRectangleUvDataButtonsAndBalls(this.uvsData, 0, textureMap);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }
}
