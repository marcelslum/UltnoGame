package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Button extends Entity{

    public float height;
    public float width;
    OnPress onPress;
    OnUnpress onUnpress;
    OnPress2 onPress2;
    int textureMapPressed;
    int textureMapUnpressed;
    int textureMap;
    float textureSize = 1024f;
    InteractionListener listener;

    Button(String name, Game game, float x, float y, float width, float height, int textureUnit) {
        super(name, game, x, y);
        this.height = height;
        this.width = width;
        this.isCollidable = false;
        this.isVisible = true;
        this.isMovable = false;
        this.isSolid =  false;

        this.textureUnit = textureUnit;
        this.program = this.game.imageProgram;

        listener = new InteractionListener("listenerButton"+this.name, x, y, width, height, 5000, this, game);
        final Button finalButton =  this;
        listener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                finalButton.setPressed();
            }

            @Override
            public void onUnpress() {
                finalButton.setUnpressed();
            }
        });
        this.game.addInteracionListener(listener);
    }

    public void setTextureMap(int textureMap){
        this.textureMap = textureMap;
        this.setDrawInfo();
    }

    public void setPressed() {
        this.isPressed = true;
        setTextureMap(textureMapPressed);
        if (this.onPress != null){
            this.onPress.onPress();
        }
        if (this.onPress2 != null){
            this.onPress2.onPress2();
        }
    }

    public void setUnpressed() {
        this.isPressed = false;
        setTextureMap(textureMapUnpressed);
        if (this.onUnpress != null){
            this.onUnpress.onUnpress();
        }
    }



    public interface OnPress{
        public void onPress();
    }

    public interface OnUnpress{
        public void onUnpress();
    }

    public interface OnPress2{
        public void onPress2();
    }

    public void setOnPress(OnPress2 onPress2){
        this.onPress2 = onPress2;
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
