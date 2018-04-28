package com.marcelslum.ultnogame;


import android.opengl.GLES20;
import android.util.Log;

class TargetGroup extends Entity{

    static final String TAG = "TargetGroup";
    private static final int SIZEOF_FLOAT = 4;
    private static final int SIZEOF_SHORT = 2;

    public int [] vbo = new int[3];
    public int [] ibo = new int[1];



    TargetGroup(){
        super("targetGroup", 0f, 0f, Entity.TYPE_TARGET_GROUP);
        textureId = Texture.TEXTURES;
        program = Game.imageColorizedProgram;
        setDrawInfo();
    }
    
    public void setDrawInfo(){

        GLES20.glGenBuffers(3, vbo, 0);
        GLES20.glGenBuffers(1, ibo, 0);

        initializeData(12 * Game.targets.size(), 6 * Game.targets.size(), 8 * Game.targets.size(), 16 * Game.targets.size());

        for (int i = 0; i < Game.targets.size(); i++){

            Utils.insertRectangleVerticesData(verticesData, i * 12, Game.targets.get(i).x,
                    Game.targets.get(i).x + Game.targets.get(i).width, Game.targets.get(i).y, Game.targets.get(i).y + Game.targets.get(i).height, 0f);

            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            if (Game.targets.get(i).type == Target.TARGET_RED){
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_RED_ID));
            } else if (Game.targets.get(i).type == Target.TARGET_BLUE){
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_BLUE_ID));
            } else if (Game.targets.get(i).type == Target.TARGET_GREEN){
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_GREEN_ID));
            } else if (Game.targets.get(i).type == Target.TARGET_BLACK){
                Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_TARGET_BLACK_ID));
            }

            /*
            float percentage;
            if (Utils.getTime() - Game.targets.get(i).timeOfLastDecay < 300){
                percentage = (float)(Utils.getTime() - Game.targets.get(i).timeOfLastDecay)/300f;
            } else {
                percentage = 0;
            }


            float finalPorcentage = ((float)Math.pow(((percentage)-0.5f),2)*-1) + 0.25f;

            if (finalPorcentage != 0) {
                if (Game.targets.get(i).type == Target.TARGET_BLUE) {
                    Utils.insertRectangleColorsData(colorsData, i * 16, finalPorcentage / 4f, finalPorcentage / 2f, finalPorcentage / 4f, Game.targets.get(i).alpha);
                } else if (Game.targets.get(i).type == Target.TARGET_BLACK) {
                    Utils.insertRectangleColorsData(colorsData, i * 16, finalPorcentage / 2f, finalPorcentage / 2f, finalPorcentage, Game.targets.get(i).alpha);
                } else {
                    Utils.insertRectangleColorsData(colorsData, i * 16, 0f, 0f, 0f, Game.targets.get(i).alpha);
                }
            } else {
                if (SaveGame.saveGame.currentLevelNumber >= 1000) {
                    Utils.insertRectangleColorsData(colorsData, i * 16, Utils.getRandonFloat(-0.05f, 0.15f), Utils.getRandonFloat(-0.05f, 0.15f), Utils.getRandonFloat(-0.05f, 0.15f), Game.targets.get(i).alpha);
                } else {
                    Utils.insertRectangleColorsData(colorsData, i * 16, 0f, 0f, 0f, Game.targets.get(i).alpha);
                }
            }
            */
            Utils.insertRectangleColorsData(colorsData, i * 16, 0f, 0f, 0f, Game.targets.get(i).alpha);
        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
        colorsBuffer = Utils.generateOrUpdateFloatBuffer(colorsData, colorsBuffer);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, colorsBuffer.capacity() * SIZEOF_FLOAT,
                        colorsBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer.capacity() * SIZEOF_SHORT,
                        indicesBuffer, GLES20.GL_STATIC_DRAW);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);

        verticesBuffer.limit(0);
        verticesBuffer = null;
        uvsBuffer.limit(0);
        uvsBuffer = null;
        colorsBuffer.limit(0);
        colorsBuffer = null;
        indicesBuffer.limit(0);
        indicesBuffer = null;
    }
	
     public void checkBufferChange() {

        //int colorChangeCount = 0;

        for (int i = 0; i < Game.targets.size(); i++) {
            int numberOfAnimations = Game.targets.get(i).checkAnimations();
            //if (numberOfAnimations > 0){
            //    //Log.e(TAG, "target x/y " +Game.targets.get(i).x+"/"+Game.targets.get(i).y+ " numberOfAnimations " + numberOfAnimations);
            //}

            if (Game.targets.get(i).colorChangeFlag) {

                //colorChangeCount += 1;

                float alphaMultiply = 0;
                if (Game.targets.get(i).isVisible){
                    alphaMultiply = 1f;
                }

                //Log.e(TAG, "target x/y " +Game.targets.get(i).x+"/"+Game.targets.get(i).y+" alpha " +  Game.targets.get(i).alpha + " : " +Game.targets.get(i).ghostAlpha + " : " + alphaMultiply);

                //for (int j = 0; j < Game.targets.get(i).animations.size(); j++) {
                //    if (Game.targets.get(i).animations.get(j).started){
                //        //Log.e(TAG, "animation started "+Game.targets.get(i).animations.get(j).name);
                //    }
                //}


                Utils.insertRectangleColorsData(Game.targets.get(i).colorsData, 0, 0f, 0f, 0f, Game.targets.get(i).alpha * Game.targets.get(i).ghostAlpha * alphaMultiply);
                colorsBuffer = Utils.generateOrUpdateFloatBuffer(Game.targets.get(i).colorsData, colorsBuffer);

                /*
                if (colorsBuffer == null || colorsBuffer.capacity() != Game.targets.get(i).colorsData.length) {

                    //Log.e(TAG, "Utils.generateOrUpdateFloatBuffer");

                    colorsBuffer = Utils.generateOrUpdateFloatBuffer(Game.targets.get(i).colorsData, colorsBuffer);
                } else {

                    //Log.e(TAG, "Utils.updateFloatBuffer");
                    Utils.updateFloatBuffer(Game.targets.get(i).colorsData, colorsBuffer);
                }
                */

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
                GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER,
                        i * (colorsBuffer.capacity() * SIZEOF_FLOAT),
                        colorsBuffer.capacity() * SIZEOF_FLOAT,
                        colorsBuffer);

                Game.targets.get(i).colorChangeFlag = false;
            }


            if (Game.targets.get(i).uvChangeFlag) {
                uvsBuffer = Utils.generateOrUpdateFloatBuffer(Game.targets.get(i).uvsData, uvsBuffer);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER,
                        i * (uvsBuffer.capacity() * SIZEOF_FLOAT),
                        uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer);

                Game.targets.get(i).uvChangeFlag = false;
            }

        }

        //if (colorChangeCount > 0) {
        //    //Log.e(TAG, "colorChangeFlag count " + colorChangeCount);
        //}

    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {

        if (MyGLRenderer.tick){
            checkBufferChange(); // TODO verificar se dá para otimizar
        }
	    
        if (!isVisible){
            return;
        }

        setMatrixModel();

        GLES20.glUseProgram(program.get());

        int av4_verticesHandle = GLES20.glGetAttribLocation(program.get(), "av4_vertices");
        int av2_uvHandle = GLES20.glGetAttribLocation(program.get(), "av2_uv");
        int av4_colorsHandle = GLES20.glGetAttribLocation(program.get(), "av4_colors" );

        int um4_projectionHandle = GLES20.glGetUniformLocation(program.get(), "um4_projection");
        int um4_viewHandle = GLES20.glGetUniformLocation(program.get(), "um4_view");
        int um4_modelHandle = GLES20.glGetUniformLocation(program.get(), "um4_model");
        int us_textureHandle = GLES20.glGetUniformLocation(this.program.get(), "us_texture");

        GLES20.glUniformMatrix4fv(um4_projectionHandle, 1, false, matrixProjection, 0);
        GLES20.glUniformMatrix4fv(um4_viewHandle, 1, false, matrixView, 0);
        GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, matrixModel, 0);

         if (textureId != -1) {
            GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
        }

        int uf_alphaHandle = GLES20.glGetUniformLocation(program.get(), "uf_alpha");
        GLES20.glUniform1f(uf_alphaHandle, 1f);


        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
        GLES20.glEnableVertexAttribArray(av2_uvHandle);
        GLES20.glVertexAttribPointer(av2_uvHandle, 2, GLES20.GL_FLOAT, false, 0, 0);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
        GLES20.glEnableVertexAttribArray(av4_colorsHandle);
        GLES20.glVertexAttribPointer(av4_colorsHandle, 4, GLES20.GL_FLOAT, false, 0, 0);
       
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indicesData.length, GLES20.GL_UNSIGNED_SHORT, 0);
        
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        
        
        
        
        // function for deleting buffers
        //final int[] buffersToDelete = new int[] { mCubePositionsBufferIdx, mCubeNormalsBufferIdx,
        //mCubeTexCoordsBufferIdx };
        //GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);
        

    }
    
    
}
