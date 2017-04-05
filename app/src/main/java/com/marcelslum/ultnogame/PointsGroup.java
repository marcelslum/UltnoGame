package com.marcelslum.ultnogame;
import android.opengl.GLES20;
import android.util.Log;

class PointsGroup extends Entity{

    static final String TAG = "PointsGroup";

	public int [] pointsChar;
	public float [] pointsX;
	public float [] pointsY;
    public float [] pointsAlpha;

    public float pointSize;
    public float pointWidth;
	
    PointsGroup(){

        super("pointsGroup", 0f, 0f, Entity.TYPE_POINT);
        textureId = Texture.TEXTURES;
        program = Game.vertex_e_uv_com_alpha_program;
	    
    	pointsChar = new int [500];
        pointsX = new float[500];
        pointsY = new float[500];
        pointsAlpha = new float[500];

    }
    
    public void setDrawInfo(){

        if (vbo == null || vbo.length == 0){
            vbo = new int[2];
            ibo = new int[1];
            GLES20.glGenBuffers(2, vbo, 0);
            GLES20.glGenBuffers(1, ibo, 0);
        }
    }
	
	public void checkBufferChange() {

	    int lastPointIndex = 0;
		int unidade;
		int dezena;
		int centena;
		float initX;
		
        for (int i = 0; i < Game.targets.size(); i++) {
            Target t = Game.targets.get(i);

            t.animatePoints();

            if (i == 0) {
                pointSize = t.pointSize;
                pointWidth = pointSize * 0.55294f;
            }

            //Log.e(TAG, "points do Show " + t.pointsToShow);

            if (t.pointsToShow > 0) {

                unidade = t.pointsToShow - ((int) Math.floor(t.pointsToShow / 10) * 10);
                dezena = (t.pointsToShow - unidade - ((int) Math.floor((t.pointsToShow - unidade) / 100) * 100))/10;
                centena = (int) Math.floor(t.pointsToShow / 100);

                //Log.e(TAG, "points do Show " + t.pointsToShow + " u " + unidade + " d " + dezena + " c " + centena);

                initX = t.pointX;

                // CENTENA
                if (centena > 0) {
                    pointsChar[lastPointIndex] = centena;
                    pointsX[lastPointIndex] = initX;
                    pointsY[lastPointIndex] = t.pointY;
                    pointsAlpha[lastPointIndex] = t.pointAlpha;
                    if (centena == 1) {
                        initX += pointWidth * 0.5f;
                    } else {
                        initX += pointWidth;
                    }
                    lastPointIndex += 1;
                }

                // DEZENA
                pointsChar[lastPointIndex] = dezena;
                pointsX[lastPointIndex] = initX;
                pointsY[lastPointIndex] = t.pointY;
                pointsAlpha[lastPointIndex] = t.pointAlpha;
                if (dezena == 1) {
                    initX += pointWidth * 0.5f;
                } else {
                    initX += pointWidth;
                }
                lastPointIndex += 1;

                // UNIDADE
                pointsChar[lastPointIndex] = unidade;
                pointsX[lastPointIndex] = initX;
                pointsY[lastPointIndex] = t.pointY;
                pointsAlpha[lastPointIndex] = t.pointAlpha;
                lastPointIndex += 1;
            }
        }

        //Log.e(TAG, "lastPointIndex "+ lastPointIndex);

        //for (int i = 0; i < lastPointIndex; i++){

            //Log.e(TAG, "pointsChar "+ pointsChar[i]);
            //Log.e(TAG, "pointsX "+ pointsX[i]);
            //Log.e(TAG, "pointsY "+ pointsY[i]);
            //Log.e(TAG, "pointsAlpha "+ pointsAlpha[i]);
            //Log.e(TAG, "pointWidth "+ pointWidth);
            //Log.e(TAG, "pointSize "+ pointSize);
        //}

        initializeData(8 * lastPointIndex, 6 * lastPointIndex, 12 * lastPointIndex, 0);

        for (int i = 0; i < lastPointIndex; i++){

            if (pointsChar[i] == 1) {
                Utils.insertRectangleVerticesDataXY(verticesData, i * 8, pointsX[i], pointsX[i]+(pointWidth*0.5f), pointsY[i], pointsY[i] + pointSize);
            } else {
                Utils.insertRectangleVerticesDataXY(verticesData, i * 8, pointsX[i], pointsX[i]+pointWidth, pointsY[i], pointsY[i] + pointSize);
            }

            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);


            switch (pointsChar[i]){
                case 1:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT1_ID), pointsAlpha[i]);
                    break;
                case 2:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT2_ID), pointsAlpha[i]);
                    break;
                case 3:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT3_ID), pointsAlpha[i]);
                    break;
                case 4:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT4_ID), pointsAlpha[i]);
                    break;
                case 5:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT5_ID), pointsAlpha[i]);
                    break;
                case 6:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT6_ID), pointsAlpha[i]);
                    break;
                case 7:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT7_ID), pointsAlpha[i]);
                    break;
                case 8:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT8_ID), pointsAlpha[i]);
                    break;
                case 9:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT9_ID), pointsAlpha[i]);
                    break;
                case 0:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT0_ID), pointsAlpha[i]);
                    break;
                default:
                    Utils.insertRectangleUvAndAlphaData(uvsData, i * 12, TextureData.getTextureDataById(TextureData.TEXTURE_POINT0_ID), pointsAlpha[i]);
                    break;
            }
        }

        /*
        if (verticesData != null) {
            for (int i = 0; i < verticesData.length; i++) {
                //Log.e(TAG, "vertice " + i + " " + verticesData[i]);
            }

            for (int i = 0; i < indicesData.length; i++) {
                //Log.e(TAG, "indicesData " + i + " " + indicesData[i]);
            }

            for (int i = 0; i < uvsData.length; i++) {
                //Log.e(TAG, "uvsData " + i + " " + uvsData[i]);
            }
        }
        */



        if (lastPointIndex > 0){
            verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
            indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
            uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);

            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                    verticesBuffer, GLES20.GL_STATIC_DRAW);

            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
            GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                    uvsBuffer, GLES20.GL_STATIC_DRAW);


            GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
            GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                    indicesBuffer, GLES20.GL_STATIC_DRAW);

            GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
            GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {

	    checkBufferChange();

        if (verticesData == null || verticesData.length == 0){
            return;
        }
	    
        if (!isVisible){
            return;
        }

        super.render(matrixView, matrixProjection);

    }
}
