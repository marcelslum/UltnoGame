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
import java.util.ArrayList;


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

    public static void insertLineVerticesData(float[] array, int startIndex, float x1, float y1, float x2, float y2, float z){
        array[0 + (startIndex)] = x1;
        array[1 + (startIndex)] = y1;
        array[2 + (startIndex)] = z;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = z;
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

    public static void insertLineColorsData(float[] array, int startIndex, Color color){
        array[0 + (startIndex)] = color.r;
        array[1 + (startIndex)] = color.g;
        array[2 + (startIndex)] = color.b;
        array[3 + (startIndex)] = color.a;
        array[4 + (startIndex)] = color.r;
        array[5 + (startIndex)] = color.g;
        array[6 + (startIndex)] = color.b;
        array[7 + (startIndex)] = color.a;
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
    
    
    public static void insertRectangleUvDataButtonsAndBalls(float[] array, int startIndex, int textureMap){
        
        if (textureMap < 9){
            Utils.y1 = (Game.textButtonsAndBallsColumnsAndLines[0] + 2f)/1024f;
            Utils.y2 = (Game.textButtonsAndBallsColumnsAndLines[1] - 2f)/1024f;
        } else if (textureMap < 17){
            Utils.y1 = (Game.textButtonsAndBallsColumnsAndLines[1] + 2f)/1024f;
            Utils.y2 = (Game.textButtonsAndBallsColumnsAndLines[2] - 2f)/1024f;
        } else if (textureMap < 21){
            Utils.y1 = (Game.textButtonsAndBallsColumnsAndLines[2] + 2f)/1024f;
            Utils.y2 = (Game.textButtonsAndBallsColumnsAndLines[4] - 2f)/1024f;
        } else if (textureMap < 25){
            Utils.y1 = (Game.textButtonsAndBallsColumnsAndLines[4] + 2f)/1024f;
            Utils.y2 = (Game.textButtonsAndBallsColumnsAndLines[6] - 2f)/1024f;
        } else {
            Utils.y1 = (Game.textButtonsAndBallsColumnsAndLines[6] + 2f)/1024f;
            Utils.y2 = (Game.textButtonsAndBallsColumnsAndLines[8] - 2f)/1024f;
        } 
        
        if (textureMap < 17){
            if (textureMap == 1 || textureMap == 9){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[0] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[1] - 2f)/1024f;
            } else if (textureMap == 2 || textureMap == 10){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[1] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[2] - 2f)/1024f;
            } else if (textureMap == 3 || textureMap == 11){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[2] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[3] - 2f)/1024f;
            } else if (textureMap == 4 || textureMap == 12){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[3] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[4] - 2f)/1024f;
            } else if (textureMap == 5 || textureMap == 13){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[4] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[5] - 2f)/1024f;
            } else if (textureMap == 6 || textureMap == 14){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[5] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[6] - 2f)/1024f;
            } else if (textureMap == 7 || textureMap == 15){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[6] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[7] - 2f)/1024f;
            } else if (textureMap == 8 || textureMap == 16){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[7] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[8] - 2f)/1024f;
            }
        } else {
             if (textureMap == 17 || textureMap == 21 || textureMap == 25){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[0] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[2] - 2f)/1024f;
            } else if (textureMap == 18 || textureMap == 22 || textureMap == 26){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[2] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[4] - 2f)/1024f;
            } else if (textureMap == 19 || textureMap == 23 || textureMap == 27){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[4] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[6] - 2f)/1024f;
            } else if (textureMap == 20 || textureMap == 24 || textureMap == 28){
                Utils.x1 = (Game.textButtonsAndBallsColumnsAndLines[6] + 2f)/1024f;
                Utils.x2 = (Game.textButtonsAndBallsColumnsAndLines[8] - 2f)/1024f;
            }
        }
        insertRectangleUvData(array, startIndex);
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
    
    public static void setBitmap(String identifier, Bitmap destination, mContext){
        int id = context.getResources().getIdentifier(identifier, null, context.getPackageName());
        destination = BitmapFactory.decodeResource(context.getResources(), id);
    }
    
    public static void setTexture(String identifier, int [] arrayOfNames, int number, Context context){
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

    public static Animation createSimpleAnimation(Entity object, String name, String parameter, int duration, float v1, float v2){

        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{0f,v1});
        values.add(new float[]{1f,v2});
        return new Animation(object, name, parameter, duration, values, false, true);

    }

    public static Animation createSimpleAnimation(Entity object, String name, String parameter, int duration, float v1, float v2, Animation.AnimationListener animationListener){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{0f,v1});
        values.add(new float[]{1f,v2});
        Animation anim = new Animation(object, name, parameter, duration, values, false, true);
        anim.setAnimationListener(animationListener);
        return anim;
    }


}
