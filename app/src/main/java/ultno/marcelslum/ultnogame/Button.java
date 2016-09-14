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

    Button(String name, float x, float y, float width, float height, int textureUnit, float listenerScale) {
        super(name, x, y);
        this.height = height;
        this.width = width;
        isCollidable = false;
        isVisible = true;
        isMovable = false;
        isSolid =  false;

        this.textureId = textureUnit;
        program = Game.imageProgram;

        float lw = width * listenerScale;
        float lh = height * listenerScale;
        float lx = this.x - (lw - width);
        float ly = this.y - (lh - height);

        final Button finalButton =  this;
        setListener(new InteractionListener("listenerButton"+this.name, lx, ly, lw, lh, 5000, this,
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
    
    public void setTextureMap(int _textureMap){
        textureMap = _textureMap;
        setDrawInfo();
    }

    public void setPressed() {
        isPressed = true;
        setTextureMap(textureMapPressed);
    }

    public void setUnpressed() {
        isPressed = false;
        setTextureMap(textureMapUnpressed);
    }

    public void setOnPress(OnPress _onPress){
        onPress = _onPress;
    }

    public void setOnUnpress(OnUnpress _onUnpress){
        onUnpress = _onUnpress;
    }

    public interface OnPress{
        void onPress();
    }

    public interface OnUnpress{
        void onUnpress();
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
