package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 11/08/2016.
 */
public class Point extends Entity {

    private float size;
    private float value;
    
    private final static float textureSize = 1024f;
    private final static float [] columns = new float [] {142f,284f,426f, 568f, 710f, 852f, 994f};
    private final static float [] lines = new float [] {257f,514f,771f};

    Point(String name, Game game, float x, float y, float size) {
        super(name, game, x, y);
        this.size = size;
        this.isSolid = false;
        this.isCollidable = false;
        this.isVisible = true;
        this.alpha = 1;
        this.textureUnit = Game.TEXTURE_NUMBERS;
        this.program = this.game.imageProgram;
    }

    public void setValue(int value){
        this.value = value;

        String valueString = String.valueOf(value);
        //Log.e("point", " "+valueString);
        int valueLength = valueString.length();

        //Log.e("point", " "+valueLength);
        initializeData(12*valueLength, 6*valueLength, 8*valueLength, 0);

        float width = size * 0.55294f;

        float x = 0f;

        for (int i = 0; i < valueString.length();i++){

            String subString = valueString.substring(i, i+1);

            //Log.e("point", "subString "+subString);

            int subInteger = Integer.parseInt(subString);

            //Log.e("point", "subInteger "+subInteger);

            Utils.insertRectangleVerticesData(this.verticesData, 0 + (i * 12), x, x+width, 0f, size, 0f);
            
            if (subInteger == 1) {
                x += width*0.46199f;
            } else {
                x += width;
            }

            Utils.insertRectangleIndicesData(this.indicesData, 0 + (i * 6), 0 + (i * 4));

            if (subInteger == 0){
                subInteger = 10;
            }

            prepareUvData(subInteger+10);
            Utils.insertRectangleUvData(this.uvsData, 0 + (i * 8));
        }
        
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }

    public void prepareUvData(int textureMap){



        Utils.y1 = 0f;
        Utils.y2 = 0f;

        if (textureMap < 8){
            Utils.y1 = 2f/textureSize;
            Utils.y2 = (lines[0]-2f)/textureSize;
        } else if (textureMap < 15){
            Utils.y1 = (lines[0]+2f)/textureSize;
            Utils.y2 = (lines[1]-2f)/textureSize;
        } else {
            Utils.y1 = (lines[1]+2f)/textureSize;
            Utils.y2 = (lines[2]-2f)/textureSize;
        }

        Utils.x1 = 0;
        Utils.x2 = 0;
    
        if (textureMap == 1 || textureMap == 8 || textureMap == 15){
            Utils.x1 = 2f/textureSize;
            Utils.x2 = (columns[0]-2f)/textureSize;
        } else if (textureMap == 2 || textureMap == 9 || textureMap == 16){
            Utils.x1 = (columns[0]+2f)/textureSize;
            Utils.x2 = (columns[1]-2f)/textureSize;
        } else if (textureMap == 3 || textureMap == 10 || textureMap == 17){
            Utils.x1 = (columns[1]+2f)/textureSize;
            Utils.x2 = (columns[2]-2f)/textureSize;
        } else if (textureMap == 4 || textureMap == 11 || textureMap == 18){
            Utils.x1 = (columns[2]+2f)/textureSize;
            Utils.x2 = (columns[3]-2f)/textureSize;
        } else if (textureMap == 5 || textureMap == 12 || textureMap == 19){
            Utils.x1 = (columns[3]+2f)/textureSize;
            Utils.x2 = (columns[4]-2f)/textureSize;
        } else if (textureMap == 6 || textureMap == 13 || textureMap == 20){
            Utils.x1 = (columns[4]+2f)/textureSize;
            Utils.x2 = (columns[5]-2f)/textureSize;
        } else if (textureMap == 7 || textureMap == 14){
            Utils.x1 = (columns[5]+2f)/textureSize;
            Utils.x2 = (columns[6]-2f)/textureSize;
        }
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
