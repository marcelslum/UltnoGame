package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

class TargetGroup extends Entity{

static final String TAG = "TargetGroup";
    
    public static int [] vbo = new int[4];

public ArrayList<TargetGroupData> targets;
    
    public static final int POSITION_DATA_SIZE = 3;
    public static final int TEXTURE_CORDINATE_DATA_ZIE = 2;
    public static final int COLOR_DATA_SIZE = 4;
    public final int BYTES_PER_FLOAT = 4;

  TargetGroup(){
      super("targetGroup", 0f, 0f, Entity.TYPE_TARGET_GROUP);
      textureId = Texture.TEXTURE_TARGETS;
      program = Game.imageColorizedProgram;
  }
    
    public void setDrawInfo(){
        
            GLES30.glGenBuffers (4, vbo, 0);
        
         
          initializeData(12 * targets.size(), 6 * targets.size(), 8 * targets.size(), 16 * targets.size());

          //Log.e(TAG, " desenhando targets "+targets.size());

            for (int i = 0; i < targets.size(); i++){
                //Log.e(TAG, " inserindo target "+
                //        targets.get(i).x + " " +
                //        (targets.get(i).x + targets.get(i).width) + " " +
                //        targets.get(i).y + " " +
                //        (targets.get(i).y + targets.get(i).height));

                Utils.insertRectangleVerticesData(verticesData, i * 12, targets.get(i).x,
                            targets.get(i).x + targets.get(i).width, targets.get(i).y, targets.get(i).y + targets.get(i).height, targets.get(i).alpha);

                Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

                if (targets.get(i).type == Target.TARGET_RED){
                      Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 1f/1024f, 206f/1024f);
                } else if (targets.get(i).type == Target.TARGET_BLUE){
                      Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 624f/1024f, 830f/1024f);
                } else if (targets.get(i).type == Target.TARGET_GREEN){
                      Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 208f/1024f, 414f/1024f);
                } else if (targets.get(i).type == Target.TARGET_BLACK){
                      Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 416f/1024f, 622f/1024f);
                }


                float finalPorcentage = ((float)Math.pow(((targets.get(i).lastDecayPercentage)-0.5f),2)*-1) + 0.25f;


                if (finalPorcentage != 0) {
                    if (targets.get(i).type == Target.TARGET_BLUE) {
                        Utils.insertRectangleColorsData(colorsData, i * 16, finalPorcentage / 4f, finalPorcentage / 2f, finalPorcentage / 4f, targets.get(i).alpha);
                    } else if (targets.get(i).type == Target.TARGET_BLACK) {
                        Utils.insertRectangleColorsData(colorsData, i * 16, finalPorcentage / 2f, finalPorcentage / 2f, finalPorcentage, targets.get(i).alpha);
                    } else {
                        Utils.insertRectangleColorsData(colorsData, i * 16, 0f, 0f, 0f, targets.get(i).alpha);
                    }
                } else {
                    if (SaveGame.saveGame.currentLevelNumber >= 1000) {
                        Utils.insertRectangleColorsData(colorsData, i * 16, Utils.getRandonFloat(-0.05f, 0.15f), Utils.getRandonFloat(-0.05f, 0.15f), Utils.getRandonFloat(-0.05f, 0.15f), targets.get(i).alpha);
                    } else {
                        Utils.insertRectangleColorsData(colorsData, i * 16, 0f, 0f, 0f, targets.get(i).alpha);
                    }
                }

            }

            verticesBuffer = Utils.generateFloatBuffer(verticesData);
            colorsBuffer = Utils.generateFloatBuffer(colorsData);
            indicesBuffer = Utils.generateShortBuffer(indicesData);
            uvsBuffer = Utils.generateFloatBuffer(uvsData);
        
        glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0])
        glBufferData(GLES20.GL_ARRAY_BUFFER, verticesBuffer.capacity() * SIZEOF_FLOAT,
                        verticesBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        
        
        
    }
    
    @Overrid
    public void render(){
        
        
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
        GLES20.glUniformMatrix4fv(um4_modelHandle, 1, false, this.matrixModel, 0);
        
         if (textureId != -1) {
            GLES20.glUniform1i(us_textureHandle, Texture.getTextureById(textureId).bind());
        }
            
            
            
            
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
        GLES20.glEnableVertexAttribArray(av4_verticesHandle);
        GLES20.glVertexAttribPointer(av4_verticesHandle, 3, GLES20.GL_FLOAT, false, 0, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        
        
        // function for deleting buffers
        //final int[] buffersToDelete = new int[] { mCubePositionsBufferIdx, mCubeNormalsBufferIdx,
        //mCubeTexCoordsBufferIdx };
        //GLES20.glDeleteBuffers(buffersToDelete.length, buffersToDelete, 0);
        

    }
    
    
}
