package ultno.marcelslum.ultno;

import android.opengl.GLES20;

/**
 * Created by marcel on 05/08/2016.
 */
public class Program {
    String name;
    int identifier;

    Program(String vertexShader, String fragmentShader){
        // Create the shaders, images
        int vertexShaderToCompile = loadShader(GLES20.GL_VERTEX_SHADER, vertexShader);
        int fragmentShaderToCompile = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader);

        identifier = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(identifier, vertexShaderToCompile);   // add the vertex shader to program
        GLES20.glAttachShader(identifier, fragmentShaderToCompile); // add the fragment shader to program
        GLES20.glLinkProgram(identifier);                  // creates OpenGL ES program executables
    }

    public int get(){
        return identifier;
    }

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        // return the shader
        return shader;
    }
}
