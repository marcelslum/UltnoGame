package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class Bar extends Rectangle{

    Bar(String name, Game game, float x, float y, float width, float height, int weight){
        super(name, game, x, y, width, height, weight, new Color(0,0,0,1));


        this.program = game.imageProgram;
        this.textureUnit = 3;
        this.isCollidable = true;
        this.isSolid = true;
        this.setDrawInfo();


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

        array[0 + (startIndex)] = 0f;
        array[1 + (startIndex)] = 1-0.4931640625f;
        array[2 + (startIndex)] = 1f;
        array[3 + (startIndex)] = 1-0.4931640625f;
        array[4 + (startIndex)] = 1f;
        array[5 + (startIndex)] = 1-0.5615234375f;
        array[6 + (startIndex)] = 0f;
        array[7 + (startIndex)] = 1-0.5615234375f;
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
