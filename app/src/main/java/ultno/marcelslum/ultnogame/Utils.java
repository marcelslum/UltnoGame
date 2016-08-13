package ultno.marcelslum.ultnogame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 * Created by marcel on 02/08/2016.
 */
public class Utils {
    private static Utils ourInstance = new Utils();
    public static float x1;
    public static float x2;
    public static float y1;
    public static float y2;
    public static float z = 0f;

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public static boolean isInsideBounds(float testX, float testY, float x, float y, float width, float height){
        if (testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height)) {
            return true;
        } else {
            return false;
        }
    }

    public static long getTime(){
        return System.currentTimeMillis();
    }
    
    
    public static FloatBuffer generateFloatBuffer(float[] data) {
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        ByteBuffer factory = ByteBuffer.allocateDirect(data.length * 4);
        factory.order(ByteOrder.nativeOrder());
        // allocates the memory from the byte buffer
        FloatBuffer buffer = factory.asFloatBuffer();
        // fill the vertexBuffer with the vertices
        buffer.put(data);
        // set the cursor position to the beginning of the buffer
        buffer.position(0);
        return buffer;
    }

    public static ShortBuffer generateShortBuffer(short[] data) {
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        ByteBuffer factory = ByteBuffer.allocateDirect(data.length * 4);
        factory.order(ByteOrder.nativeOrder());
        // allocates the memory from the byte buffer
        ShortBuffer buffer = factory.asShortBuffer();
        // fill the vertexBuffer with the vertices
        buffer.put(data);
        // set the cursor position to the beginning of the buffer
        buffer.position(0);
        return buffer;
    }
    
    public static void insertRectangleVerticesData(float[] array, int startIndex){
        array[0 + (startIndex)] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = z;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = z;
        array[6 + (startIndex)] = x2;
        array[7 + (startIndex)] = y1;
        array[8 + (startIndex)] = z;
        array[9 + (startIndex)] = x1;
        array[10 + (startIndex)] = y1;
        array[11 + (startIndex)] = z;
    }
    
    public static void insertRectangleVerticesData(float[] array, int startIndex, float x1, float x2, float y1, float y2, float z){
        array[0 + (startIndex)] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = z;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = z;
        array[6 + (startIndex)] = x2;
        array[7 + (startIndex)] = y1;
        array[8 + (startIndex)] = z;
        array[9 + (startIndex)] = x1;
        array[10 + (startIndex)] = y1;
        array[11 + (startIndex)] = z;
    }
    
      public static void insertRectangleIndicesData(short[] array, int startIndex, int startValue){
        array[0 + (startIndex)] = (short)(0 + (startValue));
        array[1 + (startIndex)] = (short)(1 + (startValue));
        array[2 + (startIndex)] = (short)(2 + (startValue));
        array[3 + (startIndex)] = (short)(0 + (startValue));
        array[4 + (startIndex)] = (short)(2 + (startValue));
        array[5 + (startIndex)] = (short)(3 + (startValue));
    }
    
    public static void insertRectangleColorsData(float[] array, int startIndex, Color color){
        array[0 + (startIndex)] = color.r;
        array[1 + (startIndex)] = color.g;
        array[2 + (startIndex)] = color.b;
        array[3 + (startIndex)] = color.a;
        array[4 + (startIndex)] = color.r;
        array[5 + (startIndex)] = color.g;
        array[6 + (startIndex)] = color.b;
        array[7 + (startIndex)] = color.a;
        array[8 + (startIndex)] = color.r;
        array[9 + (startIndex)] = color.g;
        array[10 + (startIndex)] = color.b;
        array[11 + (startIndex)] = color.a;
        array[12 + (startIndex)] = color.r;
        array[13 + (startIndex)] = color.g;
        array[14 + (startIndex)] = color.b;
        array[15 + (startIndex)] = color.a;
    }

    public static void insertRectangleUvData(float[] array, int startIndex){
            array[0 + (startIndex)] = x1;
            array[1 + (startIndex)] = 1f-y1;
            array[2 + (startIndex)] = x2;
            array[3 + (startIndex)] = 1-y1;
            array[4 + (startIndex)] = x2;
            array[5 + (startIndex)] = 1-y2;
            array[6 + (startIndex)] = x1;
            array[7 + (startIndex)] = 1-y2;
    }
    
    public static void insertRectangleUvData(float[] array, int startIndex, float x1, float x2, float y1, float y2){
            array[0 + (startIndex)] = x1;
            array[1 + (startIndex)] = 1f-y1;
            array[2 + (startIndex)] = x2;
            array[3 + (startIndex)] = 1-y1;
            array[4 + (startIndex)] = x2;
            array[5 + (startIndex)] = 1-y2;
            array[6 + (startIndex)] = x1;
            array[7 + (startIndex)] = 1-y2;
    }
    
    
    public void setTexture(String identifier, int [] arrayOfNames, int number, Context context){
        int id = context.getResources().getIdentifier(identifier, null, context.getPackageName());
        
        // Temporary create a bitmap
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), id);

        //Log.e("setup textures ", "1");

        // Bind texture to texturename
        if (number == 0){
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, arrayOfNames[0]);
        } else {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + number);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, arrayOfNames[number]);
        }

        // Set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);

        bmp.recycle();
    }
}
