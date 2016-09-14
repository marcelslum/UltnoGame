package ultno.marcelslum.ultnogame;


public class Point extends Entity {

    private float size;
    private float value;

    //private final static float textureSize = 1024f;
    //private final static float [] columns = new float [] {142f,284f,426f, 568f, 710f, 852f, 994f};
    //private final static float [] lines = new float [] {257f,514f,771f};

    Point(String name, float x, float y, float size) {
        super(name, x, y);
        this.size = size;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        alpha = 1;
        textureId = Game.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE;
        program = Game.imageProgram;
    }

    public void setValue(int value){
        value = value;

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

            Utils.insertRectangleVerticesData(this.verticesData, i * 12, x, x+width, 0f, size, 0f);
            
            if (subInteger == 1) {
                x += width*0.46199f;
            } else {
                x += width;
            }
            
            Utils.insertRectangleIndicesData(this.indicesData, i * 6, i * 4);

            int textureMap = Game.TEXTURE_MAP_NUMBERS_POINT1;
            switch (subInteger){
                case 1:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT1;
                    break;
                case 2:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT2;
                    break;
                case 3:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT3;
                    break;
                case 4:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT4;
                    break;
                case 5:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT5;
                    break;
                case 6:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT6;
                    break;
                case 7:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT7;
                    break;
                case 8:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT8;
                    break;
                case 9:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT9;
                    break;
                case 0:
                    textureMap = Game.TEXTURE_MAP_NUMBERS_POINT0;
                    break;
            }
            Utils.insertRectangleUvDataNumbersExplosion(this.uvsData, i * 8, textureMap);
        }
        
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }

   

}
