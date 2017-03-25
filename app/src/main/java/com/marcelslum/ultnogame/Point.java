package com.marcelslum.ultnogame;


public class Point extends Entity {

    private float size;
    private float value;

    //private final static float textureSize = 1024f;
    //private final static float [] columns = new float [] {142f,284f,426f, 568f, 710f, 852f, 994f};
    //private final static float [] lines = new float [] {257f,514f,771f};

    Point(String name, float x, float y, float size) {
        super(name, x, y, Entity.TYPE_POINT);
        this.size = size;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        alpha = 1;
        textureId = Texture.TEXTURES;
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


            
            if (subInteger == 1) {
                Utils.insertRectangleVerticesData(verticesData, i * 12, x, x+(width*0.5f), 0f, size, 0f);
                x += width*0.5f;
                //x += width*0.46199f;
            } else {
                Utils.insertRectangleVerticesData(verticesData, i * 12, x, x+width, 0f, size, 0f);
                x += width;
            }
            
            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            switch (subInteger){
                case 1:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT1_ID));
                    break;
                case 2:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT2_ID));
                    break;
                case 3:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT3_ID));
                    break;
                case 4:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT4_ID));
                    break;
                case 5:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT5_ID));
                    break;
                case 6:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT6_ID));
                    break;
                case 7:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT7_ID));
                    break;
                case 8:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT8_ID));
                    break;
                case 9:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT9_ID));
                    break;
                case 0:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT0_ID));
                    break;
            }
        }
        
        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }

   

}
