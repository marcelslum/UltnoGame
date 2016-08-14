package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Button extends Entity{

    public float height;
    public float width;
    InteractionListener listener;
    OnPress onPress;
    OnUnpress onUnpress;
    int textureMapPressed;
    int textureMapUnpressed;
    int textureMap;

    Button(String name, Game game, float x, float y, float width, float height) {
        super(name, game, x, y);
        this.height = height;
        this.width = width;
        this.isCollidable = false;
        this.isVisible = true;
        this.isMovable = false;
        this.isSolid =  false;

        this.textureUnit = 4;
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

    public void prepareUvData(int textureMap){
        /*
        array[0 + (startIndex)] = 0f; x//
        array[1 + (startIndex)] = 1f; y//
        array[2 + (startIndex)] = 1f; x//
        array[3 + (startIndex)] = 1f; y//
        array[4 + (startIndex)] = 1f; x//
        array[5 + (startIndex)] = 0f; y//
        array[6 + (startIndex)] = 0f; x//
        array[7 + (startIndex)] = 0f; y//
        */
        //0,7998046875

        Utils.x1 = 0f;
        Utils.x2 = 0f;
        Utils.y1 = 0f;
        Utils.y2 = 0f;

        if (textureMap < 5){
            Utils.y1 = 0.005f;
            Utils.y2 = 0.2495f;
        } else if (textureMap < 9){
            Utils.y1 = 0.2505f;
            Utils.y2 = 0.4995f;
        } else if (textureMap < 13){
            Utils.y1 = 0.5005f;
            Utils.y2 = 0.7495f;
        } else if (textureMap < 17){
            Utils.y1 = 0.7505f;
            Utils.y2 = 0.9995f;
        }


        if (textureMap == 1 || textureMap == 5 || textureMap == 9 || textureMap == 13){
            Utils.x1 = 0.005f;
            Utils.x2 = 0.2495f;
        } else if (textureMap == 2 || textureMap == 6 || textureMap == 10 || textureMap == 14){
            Utils.x1 = 0.2505f;
            Utils.x2 = 0.4995f;
        } else if (textureMap == 3 || textureMap == 7 || textureMap == 11 || textureMap == 15){
            Utils.x1 = 0.501f;
            Utils.x2 = 0.7495f;
        } else if (textureMap == 4 || textureMap == 8 || textureMap == 12 || textureMap == 16){
            Utils.x1 = 0.7505f;
            Utils.x2 = 0.9995f;
        }
    }

    public void setDrawInfo(){
        initializeData(12, 6, 12, 0);

        Utils.insertRectangleVerticesData(verticesData, 0, 0f, width, 0f, height, 0f);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        Utils.insertRectangleIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        this.prepareUvData(textureMap);
        Utils.insertRectangleUvData(this.uvsData, 0);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }
}
