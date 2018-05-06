package com.marcelslum.ultnogame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by marcel on 02/08/2016.
 */
public abstract class Utils {
    public static float x1;
    public static float x2;
    public static float y1;
    public static float y2;
    public static float z = 0f;

    public final static int BYTES_PER_FLOAT = 4;
    public final static int BYTES_PER_SHORT = 2;

    public static final String TAG = "Utils";

    public static float getVectorMagnitude(float x, float y){
        return (float)Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public static void resetAlphaCleanAnimationAndDisplay(Entity object){
        object.cleanAnimations();
        object.alpha = 1f;
        object.display();
    }
    
    public static double getVectorMagnitude(double x, double y){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    
    public static double getXRotatedFromDegrees(double x, double y, double angle){
        return x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle));
    }

    public static double getYRotatedFromDegrees(double x, double y, double angle){
        return x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle));
    }
    
    public static double getXRotatedFromRad(double x, double y, double angle){
        return x * Math.cos(angle) - y * Math.sin(angle);
    }

    public static double getYRotatedFromRad(double x, double y, double angle){
        return x * Math.sin(angle) + y * Math.cos(angle);
    }
   
    public static boolean isPointInsideBounds(float testX, float testY, float x, float y, float width, float height){
        if (testX >= x && testX <= (x + width) && testY >= y && testY <= (y + height)) {
            return true;
        } else {
            return false;
        }
    }


    public static float getRandonFloat(float min, float max){

        return min + ((float)Math.random() * ((max - min)));
    }

    public static long getTimeMilliPrecision(){
        return Game.currentFrameMilliPrecision;
        //return System.currentTimeMillis();
    }

    public static long getTimeNano(){
        return Game.currentFrameNanoPrecision;
        //return System.currentTimeMillis();
    }

    public static String readJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = Game.getContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    
    public static FloatBuffer generateFloatBuffer(float[] data) {
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        ByteBuffer factory = ByteBuffer.allocateDirect(data.length * BYTES_PER_FLOAT);
        factory.order(ByteOrder.nativeOrder());
        // allocates the memory from the byte buffer
        FloatBuffer buffer = factory.asFloatBuffer();
        // fill the vertexBuffer with the vertices
        buffer.put(data);
        // set the cursor position to the beginning of the buffer
        buffer.position(0);
        return buffer;
    }

    public static FloatBuffer generateOrUpdateFloatBuffer(float[] data, FloatBuffer floatBuffer) {
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        if (floatBuffer == null || floatBuffer.capacity() != data.length){
            floatBuffer = Utils.generateFloatBuffer(data);
        } else {
            Utils.updateFloatBuffer(data, floatBuffer);
        }
        return floatBuffer;
    }

    public static ShortBuffer generateOrUpdateShortBuffer(short[] data, ShortBuffer shortBuffer) {
        // a float has 4 bytes so we allocate for each coordinate 4 bytes
        if (shortBuffer == null || shortBuffer.capacity() != data.length){
            shortBuffer = Utils.generateShortBuffer(data);
        } else {
            Utils.updateShortBuffer(data, shortBuffer);
        }
        return shortBuffer;
    }

    public static void updateFloatBuffer(float[] data, FloatBuffer buffer) {
            buffer.position(0);
            buffer.put(data);
            buffer.position(0);
    }

    public static void updateShortBuffer(short[] data, ShortBuffer buffer) {
        //Log.e(TAG, " atualizando buffer ");
        buffer.position(0);
        buffer.put(data);
        buffer.position(0);
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Log.e(TAG, "drawable instanceof BitmapDrawable");
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        Log.e(TAG, "width " + width);
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        Log.e(TAG, "height " + height);
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private static ShortBuffer generateShortBuffer(short[] data) {
        ByteBuffer factory = ByteBuffer.allocateDirect(data.length * BYTES_PER_SHORT);
        factory.order(ByteOrder.nativeOrder());
        // allocates the memory from the byte buffer
        ShortBuffer buffer = factory.asShortBuffer();
        // fill the vertexBuffer with the vertices
        buffer.put(data);
        // set the cursor position to the beginning of the buffer
        buffer.position(0);
        return buffer;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdfDate.format(Calendar.getInstance().getTime());
        return date;
    }


    public static String getTimeTextFromMiliSeconds(long time){

        long secondsRaw = (long)((double) time / 1000d);

        long minutes = (long)((double) secondsRaw/60d);

        long seconds = secondsRaw % 60;

        long hour = 0;

        if (minutes > 60){
            long minutesRaw = minutes;
            hour = (long)((double) minutesRaw/60d);
            minutes = minutesRaw % 60;
        }

        long days = 0;
        if (hour > 24){
            long hourRaw = hour;
            days = (long)((double) hour/24d);
            hour = hourRaw % 24;
        }

        if (days > 0){
            return days + "d " + hour + "h " + minutes + "min " + seconds + "s ";
        } else if (hour > 0){
            return hour + "h " + minutes + "min " + seconds + "s ";
        } else if (minutes > 0){
            return  minutes + "min " + seconds + "s ";
        } else {
            return  seconds + "s ";
        }
    }

    
    public static void insertRectangleVerticesData(float[] array, int startIndex){
        array[startIndex] = x1;
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
        array[startIndex] = x1;
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

    public static void insertRectangleVerticesDataAndLog(float[] array, int startIndex, float x1, float x2, float y1, float y2, float z){

        Log.e(TAG, x1 + "; " + x2 + "; " + y1 + "; " + y2);


        array[startIndex] = x1;
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
    
    public static void insertRectangleVerticesDataXY(float[] array, int startIndex, float x1, float x2, float y1, float y2){
        array[startIndex] = x1;
        array[1 + (startIndex)] = y2;
        array[2 + (startIndex)] = x2;
        array[3 + (startIndex)] = y2;
        array[4 + (startIndex)] = x2;
        array[5 + (startIndex)] = y1;
        array[6 + (startIndex)] = x1;
        array[7 + (startIndex)] = y1;
    }

    public static void insertLineVerticesData(float[] array, int startIndex, float x1, float y1, float x2, float y2, float z){
        array[startIndex] = x1;
        array[1 + (startIndex)] = y1;
        array[2 + (startIndex)] = z;
        array[3 + (startIndex)] = x2;
        array[4 + (startIndex)] = y2;
        array[5 + (startIndex)] = z;
    }

    public static void insertLineIndicesData(short[] array, int startIndex, int startValue){
        array[startIndex] = (short)(startValue);
        array[1 + (startIndex)] = (short)(1 + (startValue));
    }


    public static void insertRectangleIndicesData(short[] array, int startIndex, int startValue){
        array[startIndex] = (short)(0 + (startValue));
        array[1 + (startIndex)] = (short)(1 + (startValue));
        array[2 + (startIndex)] = (short)(2 + (startValue));
        array[3 + (startIndex)] = (short)(startValue);
        array[4 + (startIndex)] = (short)(2 + (startValue));
        array[5 + (startIndex)] = (short)(3 + (startValue));
    }
    
    public static void insertRectangleColorsData(float[] array, int startIndex, Color color){
        array[startIndex] = color.r;
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



    public static void insertRectangleColorsData(float[] array, int startIndex, Color colorTopLeft, Color colorTopRight, Color colorBottomLeft, Color colorBottomRight){
        array[startIndex] = colorBottomLeft.r;
        array[1 + (startIndex)] = colorBottomLeft.g;
        array[2 + (startIndex)] = colorBottomLeft.b;
        array[3 + (startIndex)] = colorBottomLeft.a;
        array[4 + (startIndex)] = colorBottomRight.r;
        array[5 + (startIndex)] = colorBottomRight.g;
        array[6 + (startIndex)] = colorBottomRight.b;
        array[7 + (startIndex)] = colorBottomRight.a;
        array[8 + (startIndex)] = colorTopRight.r;
        array[9 + (startIndex)] = colorTopRight.g;
        array[10 + (startIndex)] = colorTopRight.b;
        array[11 + (startIndex)] = colorTopRight.a;
        array[12 + (startIndex)] = colorTopLeft.r;
        array[13 + (startIndex)] = colorTopLeft.g;
        array[14 + (startIndex)] = colorTopLeft.b;
        array[15 + (startIndex)] = colorTopLeft.a;
    }


    public static void insertRectangleColorsData(float[] array, int startIndex, float r, float g, float b, float a){
        array[startIndex] = r;
        array[1 + (startIndex)] = g;
        array[2 + (startIndex)] = b;
        array[3 + (startIndex)] = a;
        array[4 + (startIndex)] = r;
        array[5 + (startIndex)] = g;
        array[6 + (startIndex)] = b;
        array[7 + (startIndex)] = a;
        array[8 + (startIndex)] = r;
        array[9 + (startIndex)] = g;
        array[10 + (startIndex)] = b;
        array[11 + (startIndex)] = a;
        array[12 + (startIndex)] = r;
        array[13 + (startIndex)] = g;
        array[14 + (startIndex)] = b;
        array[15 + (startIndex)] = a;
    }

    public static void insertLineColorsData(float[] array, int startIndex, Color color){
        array[startIndex] = color.r;
        array[1 + (startIndex)] = color.g;
        array[2 + (startIndex)] = color.b;
        array[3 + (startIndex)] = color.a;
        array[4 + (startIndex)] = color.r;
        array[5 + (startIndex)] = color.g;
        array[6 + (startIndex)] = color.b;
        array[7 + (startIndex)] = color.a;
    }

    public static void insertRectangleUvData(float[] array, int startIndex){
            array[startIndex] = x1;
            array[1 + (startIndex)] = y1;
            array[2 + (startIndex)] = x2;
            array[3 + (startIndex)] = y1;
            array[4 + (startIndex)] = x2;
            array[5 + (startIndex)] = y2;
            array[6 + (startIndex)] = x1;
            array[7 + (startIndex)] = y2;
    }
    
    public static void insertRectangleUvAndAlphaData(float[] array, int startIndex, float alpha){
            array[startIndex] = x1;
            array[1 + (startIndex)] = y1;
            array[2 + (startIndex)] = alpha;
            array[3 + (startIndex)] = x2;
            array[4 + (startIndex)] = y1;
            array[5 + (startIndex)] = alpha;
            array[6 + (startIndex)] = x2;
            array[7 + (startIndex)] = y2;
            array[8 + (startIndex)] = alpha;
            array[9 + (startIndex)] = x1;
            array[10 + (startIndex)] = y2;
            array[11 + (startIndex)] = alpha;
    }
    

    public static void insertRectangleUvData(float[] array, int startIndex, TextureData td){
        x1 = td.x;
        x2 = td.x + td.w;
        y2 = td.y;
        y1 = td.y + td.h;
        insertRectangleUvData(array, startIndex);
    }
    
    public static void insertRectangleUvAndAlphaData(float[] array, int startIndex, TextureData td, float alpha){
        if (td != null){
            x1 = td.x;
            x2 = td.x + td.w;
            y2 = td.y;
            y1 = td.y + td.h;
            insertRectangleUvAndAlphaData(array, startIndex, alpha);
        } else {
            array[2 + (startIndex)] = alpha;
            array[5 + (startIndex)] = alpha;
            array[8 + (startIndex)] = alpha;
            array[11 + (startIndex)] = alpha;   
        }
    }
    
   public static float[] getUvData256(int textureMap){
       
        float [] data = new float [] {0f, 256f, 512f, 768f, 1024f};
       
        if (textureMap < 5){
            Utils.y1 = (data[0] + 2f)/1024f;
            Utils.y2 = (data[1] - 2f)/1024f;
        } else if (textureMap < 9){
            Utils.y1 = (data[1] + 2f)/1024f;
            Utils.y2 = (data[2] - 2f)/1024f;
        } else if (textureMap < 13){
            Utils.y1 = (data[2] + 2f)/1024f;
            Utils.y2 = (data[3] - 2f)/1024f;
        } else if (textureMap < 17){
            Utils.y1 = (data[3] + 2f)/1024f;
            Utils.y2 = (data[4] - 2f)/1024f;
        }

        if (textureMap == 1 || textureMap == 5 || textureMap == 9 || textureMap == 13){
            Utils.x1 = (data[0] + 2f)/1024f;
            Utils.x2 = (data[1] - 2f)/1024f;
        } else if (textureMap == 2 || textureMap == 6 || textureMap == 10 || textureMap == 14){
            Utils.x1 = (data[1] + 2f)/1024f;
            Utils.x2 = (data[2] - 2f)/1024f;
        } else if (textureMap == 3 || textureMap == 7 || textureMap == 11 || textureMap == 15){
            Utils.x1 = (data[2] + 2f)/1024f;
            Utils.x2 = (data[3] - 2f)/1024f;
        } else if (textureMap == 4 || textureMap == 8 || textureMap == 12 || textureMap == 16){
            Utils.x1 = (data[3] + 2f)/1024f;
            Utils.x2 = (data[4] - 2f)/1024f;
        }
       
       return new float[]{Utils.x1, Utils.x2, Utils.y1, Utils.y2};
       
    }

    public static void insertRectangleUvData(float[] array, int startIndex, float x1, float x2, float y1, float y2){
            array[startIndex] = x1;
            array[1 + (startIndex)] = y1;
            array[2 + (startIndex)] = x2;
            array[3 + (startIndex)] = y1;
            array[4 + (startIndex)] = x2;
            array[5 + (startIndex)] = y2;
            array[6 + (startIndex)] = x1;
            array[7 + (startIndex)] = y2;
    }

    public static Animation createSimpleAnimation(Entity object, String name, String parameter, int duration, float v1, float v2){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{0f,v1});
        values.add(new float[]{1f,v2});
        return new Animation(object, name, parameter, duration, values, false, true);
    }
    
    public static Animation createAnimation2v(Entity object, String name, String parameter, int duration, float t1, float v1, float t2, float v2, boolean isInfinite, boolean isFluid){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{t1,v1});
        values.add(new float[]{t2,v2});
        return new Animation(object, name, parameter, duration, values, isInfinite, isFluid);
    }
    
    public static Animation createAnimation3v(Entity object, String name, String parameter, int duration, float t1, float v1, float t2, float v2, float t3, float v3, boolean isInfinite, boolean isFluid){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{t1,v1});
        values.add(new float[]{t2,v2});
        values.add(new float[]{t3,v3});
        return new Animation(object, name, parameter, duration, values, isInfinite, isFluid);
    }
    
    public static Animation createAnimation4v(Entity object, String name, String parameter, int duration, float t1, float v1, float t2, float v2, float t3, float v3, float t4, float v4, boolean isInfinite, boolean isFluid){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{t1,v1});
        values.add(new float[]{t2,v2});
        values.add(new float[]{t3,v3});
        values.add(new float[]{t4,v4});
        return new Animation(object, name, parameter, duration, values, isInfinite, isFluid);
    }
    
    public static Animation createAnimation5v(Entity object, String name, String parameter, int duration, float t1, float v1, float t2, float v2, float t3, float v3, float t4, float v4, float t5, float v5, boolean isInfinite, boolean isFluid){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{t1,v1});
        values.add(new float[]{t2,v2});
        values.add(new float[]{t3,v3});
        values.add(new float[]{t4,v4});
        values.add(new float[]{t5,v5});
        return new Animation(object, name, parameter, duration, values, isInfinite, isFluid);
    }

    public static Animation createAnimation6v(Entity object, String name, String parameter, int duration, float t1, float v1, float t2, float v2, float t3, float v3, float t4, float v4, float t5, float v5, float t6, float v6, boolean isInfinite, boolean isFluid){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{t1,v1});
        values.add(new float[]{t2,v2});
        values.add(new float[]{t3,v3});
        values.add(new float[]{t4,v4});
        values.add(new float[]{t5,v5});
        values.add(new float[]{t6,v6});
        return new Animation(object, name, parameter, duration, values, isInfinite, isFluid);
    }

    public static Animation createSimpleAnimation(Entity object, String name, String parameter, int duration, float v1, float v2, Animation.AnimationListener animationListener){
        ArrayList<float[]> values = new ArrayList<>();
        values.add(new float[]{0f,v1});
        values.add(new float[]{1f,v2});
        Animation anim = new Animation(object, name, parameter, duration, values, false, true);
        anim.setAnimationListener(animationListener);
        return anim;
    }
    
    public static String getStringResource(int tag){
        return Game.mainActivity.getApplicationContext().getResources().getString(tag);
    }


    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while (( line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        //Log.e("utils", text.toString());

        return text.toString();
    }

    public static boolean[] getHigher(boolean[] array1, boolean[] array2) {
        int size = getHigher(array1.length, array2.length);
        boolean[] result = new boolean[size];
        boolean v1;
        boolean v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = false;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = false;
            }

            result[i] = v1 || v2;
        }
        return result;
    }

    public static int[] getHigher(int[] array1, int[] array2) {
        int size = getHigher(array1.length, array2.length);
        int[] result = new int[size];
        int v1;
        int v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = 0;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = 0;
            }

            result[i] = getHigher(v1, v2);
        }
        return result;
    }

    public static long[] getHigher(long[] array1, long[] array2) {
        int size = getHigher(array1.length, array2.length);
        long[] result = new long[size];
        long v1;
        long v2;
        for (int i = 0; i < result.length; i++) {
            if (array1.length > i) {
                v1 = array1[i];
            } else {
                v1 = 0;
            }

            if (array2.length > i) {
                v2 = array2[i];
            } else {
                v2 = 0;
            }

            result[i] = getHigher(v1, v2);
        }
        return result;
    }

    public static int getHigher(int value1, int value2) {
        if (value1 == value2 || value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }

    public static boolean getHigher(boolean value1, boolean value2) {
        return value1 || value2;
    }

    public static long getHigher(long value1, long value2) {
        if (value1 == value2 || value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }

    public static float getHigher(float value1, float value2) {
        if (value1 == value2 || value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }
}
