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

    public void insertUvData(float[] array, int startIndex){

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

        float x1 = 0f;
        float x2 = 0f;
        float y1 = 0f;
        float y2 = 0f;

        if (textureMap < 5){
            y1 = 0.005f;
            y2 = 0.2495f;
        } else if (textureMap < 9){
            y1 = 0.2505f;
            y2 = 0.4995f;
        } else if (textureMap < 13){
            y1 = 0.5005f;
            y2 = 0.7495f;
        } else if (textureMap < 17){
            y1 = 0.7505f;
            y2 = 0.9995f;
        }


        if (textureMap == 1 || textureMap == 5 || textureMap == 9 || textureMap == 13){
            x1 = 0.005f;
            x2 = 0.2495f;
        } else if (textureMap == 2 || textureMap == 6 || textureMap == 10 || textureMap == 14){
            x1 = 0.2505f;
            x2 = 0.4995f;
        } else if (textureMap == 3 || textureMap == 7 || textureMap == 11 || textureMap == 15){
            x1 = 0.501f;
            x2 = 0.7495f;
        } else if (textureMap == 4 || textureMap == 8 || textureMap == 12 || textureMap == 16){
            x1 = 0.7505f;
            x2 = 0.9995f;
        }

        array[0 + (startIndex)] = x1;
        array[1 + (startIndex)] = 1f-y1;
        array[2 + (startIndex)] = x2;
        array[3 + (startIndex)] = 1f-y1;
        array[4 + (startIndex)] = x2;
        array[5 + (startIndex)] = 1f-y2;
        array[6 + (startIndex)] = x1;
        array[7 + (startIndex)] = 1f-y2;
    }

    public void setDrawInfo(){
        this.verticesData = new float[12];
        this.insertVerticesData(this.verticesData,0);
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);

        this.indicesData = new short[6];
        this.insertIndicesData(this.indicesData, 0, 0);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);

        this.uvsData = new float[12];
        this.insertUvData(this.uvsData, 0);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }


    public void insertVerticesData(float[] array, int startIndex){
        float x1 = 0f;
        float x2 = this.width;
        float y1 = 0f;
        float y2 = this.height;

        array[0 + (startIndex)] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = 0.0f;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = 0.0f;
        array[6 + (startIndex)] = x2;
        array[7 + (startIndex)] = y1;
        array[8 + (startIndex)] = 0.0f;
        array[9 + (startIndex)] = x1;
        array[10 + (startIndex)] = y1;
        array[11 + (startIndex)] = 0.0f;
    }

    public void insertIndicesData(short[] array, int startIndex, int startValue){
        array[0 + (startIndex)] = (short)(0 + (startValue));
        array[1 + (startIndex)] = (short)(1 + (startValue));
        array[2 + (startIndex)] = (short)(2 + (startValue));
        array[3 + (startIndex)] = (short)(0 + (startValue));
        array[4 + (startIndex)] = (short)(2 + (startValue));
        array[5 + (startIndex)] = (short)(3 + (startValue));
    }

}
