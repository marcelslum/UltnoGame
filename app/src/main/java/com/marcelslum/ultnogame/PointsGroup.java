package com.marcelslum.ultnogame;
import android.opengl.GLES20;

class PointsGroup extends Entity{

    static final String TAG = "PointsGroup";

    public static int [] vbo;
    public static int [] ibo;

    public float[] individualUvsData;
    public float[] individualColorsData;
	
	public int [] pointsChar;
	public float [] pointsX;
	public float [] pointsY;

    public final int BYTES_PER_FLOAT = 4;
    public final int BYTES_PER_SHORT = 2;
	
    PointsGroup(){
	super("pointsGroup", 0f, 0f, Entity.TYPE_POINT);
	textureId = Texture.TEXTURES;
	program = Game.vertex_e_uv_com_alpha_program;
	setDrawInfo();
	    
    	pointsChar = new int [500];
	pointsX = new float[500];
	pointsY = new float[500];
	pointsAlpha = new float[500]
	    
	    
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
		float width;
		
        for (int i = 0; i < Game.targets.size(); i++) {
		Target t = Game.targets.get(i);
		if (t.pointsToShow > 0){
			unidade = floorMod(t.pointsToShow, 10);
			dezena = floor(floorMod(t.pointsToShow, 100) / 10);
			centena = floor(t.pointsToShow/100);
		}
		
		 
		width = t.pointsSize * 0.55294f;
		
		initX = t.pointX;
		
		// CENTENA
		if (centena > 0){
			pointsChar[lastPointIndex] = centena;
			pointsX[lastPointIndex] = initX;
			pointsY[lastPointIndex] = t.pointY;
			if (centena == 1){
			 	initX += width*0.5f;	
			} else {
				initX += width;
			}
			lastPointIndex += 1;
		}
		
		// DEZENA
		pointsChar[lastPointIndex] = dezena;
		pointsX[lastPointIndex] = initX;
		pointsY[lastPointIndex] = t.pointY;
			if (dezena == 1){
			 	initX += width*0.5f;	
			} else {
				initX += width;
			}
		lastPointIndex += 1;
		
		// UNIDADE
		pointsChar[lastPointIndex] = unidade;
		pointsX[lastPointIndex] = initX;
		pointsY[lastPointIndex] = t.pointY;
		lastPointIndex += 1;
		



            if (Game.targets.get(i).colorChangeFlag) {

                float alphaMultiply = 0;
                if (Game.targets.get(i).isVisible){
                    alphaMultiply = 1f;
                }

                Utils.insertRectangleColorsData(Game.targets.get(i).colorsData, 0, 0f, 0f, 0f, Game.targets.get(i).alpha * Game.targets.get(i).ghostAlpha * alphaMultiply);

                if (colorsBuffer == null || colorsBuffer.capacity() != Game.targets.get(i).colorsData.length) {
                    colorsBuffer = Utils.generateOrUpdateFloatBuffer(Game.targets.get(i).colorsData, colorsBuffer);
                } else {
                    Utils.updateFloatBuffer(Game.targets.get(i).colorsData, colorsBuffer);
                }

                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[2]);
                GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER,
                        i * (colorsBuffer.capacity() * SIZEOF_FLOAT),
                        colorsBuffer.capacity() * SIZEOF_FLOAT,
                        colorsBuffer);
            }


            if (Game.targets.get(i).uvChangeFlag) {
                uvsBuffer = Utils.generateOrUpdateFloatBuffer(Game.targets.get(i).uvsData, uvsBuffer);
                GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[1]);
                GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER,
                        i * (uvsBuffer.capacity() * SIZEOF_FLOAT),
                        uvsBuffer.capacity() * SIZEOF_FLOAT,
                        uvsBuffer);
            }

        }

    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {

	checkBufferChange();   
	    
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
