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

            int textureMap;
            switch (subInteger){
                case 1:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT1;
                    break;
                case 2:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT2;
                    break;
                case 3:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT3;
                    break;
                case 4:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT4;
                    break;
                case 5:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT5;
                    break;
                case 6:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT6;
                    break;
                case 7:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT7;
                    break;
                case 8:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT8;
                    break;
                case 9:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT9;
                    break;
                case 0:
                    textureMap = TEXTURE_MAP_NUMBERS_POINT0;
                    break;
            }
            Utils.insertRectangleUvDataNumbersAndExplosion(this.uvsData, 0 + (i * 8), textureMap);
        }
        
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }

   

}
