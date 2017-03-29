package com.marcelslum.ultnogame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by marcel on 14/09/2016.
 */
public class Texture {

    //public final static int TEXTURE_BUTTONS_BALLS_STARS = 0;
    //public final static int TEXTURE_FONT = 1;
    //public final static int TEXTURE_TARGETS = 2;
    //public final static int TEXTURE_BARS = 3;
    public final static int TEXTURE_BACKGROUND = 4;
    //public final static int TEXTURE_NUMBERS_EXPLOSION = 5;
    //public final static int TEXTURE_TITTLE = 6;
    //public final static int TEXTURE_SPECIAL_BALL = 7;
    //public final static int TEXTURE_LEVEL_ICONS = 8;
    //////public final static int TEXTURE_TUTORIALS1 = 9;
    //public final static int TEXTURE_TUTORIALS2 = 10;
    //public final static int TEXTURE_TUTORIALS3 = 13;
    ////public final static int TEXTURE_TUTORIALS4 = 14;
    //public final static int TEXTURE_TUTORIAL_ICONS = 11;
    //public final static int TEXTURE_GROUP_ICONS = 12;

    public final static int TEXTURES = 100;
    public final static int TEXTURE_ICONS = 101;
    public final static int TEXTURE_TUTORIALS = 102;

    public static int MAX_TEXTURES = 8;

    public static int lastTextureUsed = -1;

    public static ArrayList<Texture> textures;
    
    public static int textureSize = 2048;

    public static int[] textureNames;
    public static boolean[] textureNamesUsed;
    String resoureIdentifier;
    int resoureIdentifierId;
    int textureUnit;
    int id;
    static Bitmap bitmap;
    boolean bounded = false;
    public static final String TAG = "Texture";

    public static void clear(){
        textureNames = null;
        textureNamesUsed = null;
    }

    public static void init() {
        Log.e(TAG, "initTextures");
        Texture.textures.add(new Texture(Texture.TEXTURE_ICONS, "drawable/icons"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TUTORIALS, "drawable/tutorials"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BACKGROUND, "drawable/finalback1c"));
    }

    Texture(int id, String resourceIdentifier){
        this.id = id;
        this.resoureIdentifier = resourceIdentifier;
        try {
            resoureIdentifierId = Game.mainActivity.getApplicationContext().getResources().getIdentifier(this.resoureIdentifier, null, Game.mainActivity.getApplicationContext().getPackageName());
            bitmap = BitmapFactory.decodeResource(Game.mainActivity.getApplicationContext().getResources(), resoureIdentifierId);
        }
        catch (Exception e) {
            Log.e(TAG, "Erro ao criar a textura", e);
        }
        bind();
    }

    public static Texture getTextureById(int id){
        //Log.e("texture", "getTextureById "+id);
        for (int i = 0; i < textures.size(); i++){
            if (textures.get(i).id == id){
                //Log.e("texture", " return "+textures.get(i).textureId);
                if (!textures.get(i).bounded){
                    textures.get(i).bind();
                }
                return textures.get(i);
            }
        }
        return null;
    }

    public static Texture getTextureByTextureUnit(int textureUnit){
        for (int i = 0; i < textures.size(); i++){
            if (textures.get(i).textureUnit == textureUnit){
                return textures.get(i);
            }
        }
        return null;
    }

    public static void config(){
        GLES20.glGenTextures(Texture.MAX_TEXTURES, textureNames, 0);
    }

    public static int getFreeTextureUnit(){
        //Log.e("texture", "getFreeTextureUnit");
        for (int i = 0; i < MAX_TEXTURES; i++){
            //Log.e("texture", "used "+i+" "+textureNamesUsed[i]);
            if (!textureNamesUsed[i]){
                textureNamesUsed[i] = true;
                return i;
            }
        }

        lastTextureUsed += 1;
        if (lastTextureUsed == MAX_TEXTURES){
            lastTextureUsed = 0;
        }

        Log.e("texture", "lastTextureUsed "+lastTextureUsed);

        getTextureByTextureUnit(lastTextureUsed).bounded = false;
        getTextureByTextureUnit(lastTextureUsed).textureUnit = -1;
        return lastTextureUsed;
    }

    public void changeBitmap(String resoureIdentifier){
        this.resoureIdentifier = resoureIdentifier;
        
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        
        int sampleSize = 1;
        switch(Game.dpiClassification) {
           case DisplayMetrics.DENSITY_LOW:
               sampleSize = 4;
               Log.e(TAG, "low density");
               break;    
           case DisplayMetrics.DENSITY_MEDIUM:
               Log.e(TAG, "medium density");
               sampleSize = 4;
               break;                
           case DisplayMetrics.DENSITY_HIGH:
               Log.e(TAG, "high density");
               sampleSize = 2;
               break;    
           case DisplayMetrics.DENSITY_XHIGH:
               Log.e(TAG, "xhigh density");
               sampleSize = 2;
               break;                  
           case DisplayMetrics.DENSITY_XXHIGH:
               Log.e(TAG, "xxhigh density");
               sampleSize = 1;
               break;                  
           case DisplayMetrics.DENSITY_XXXHIGH:
               Log.e(TAG, "xxxhigh density");
               sampleSize = 1;
               break;      
        }
        
        textureSize = 2048 / sampleSize;
        
        //options.inSampleSize = sampleSize;
        
        resoureIdentifierId = Game.mainActivity.getApplicationContext().getResources().getIdentifier(this.resoureIdentifier, null, Game.mainActivity.getApplicationContext().getPackageName());
        bitmap = BitmapFactory.decodeResource(Game.mainActivity.getApplicationContext().getResources(), 
                                              resoureIdentifierId,
                                              options);

        if (textureUnit == 0){
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[0]);
        } else {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnit);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[textureUnit]);
        }

        // Set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_MIRRORED_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_MIRRORED_REPEAT);

        // Load the bitmap into the bound texture.
        Log.e("texture", "troca textura "+id+ " texture unit "+textureUnit);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
    }

    public int bind(){

        if (textureNames == null){
            int[] maxTextureUnits = new int[1];
            GLES20.glGetIntegerv(GLES20.GL_MAX_TEXTURE_IMAGE_UNITS, maxTextureUnits, 0);

            int[] maxTextureSize = new int[1];
            GLES20.glGetIntegerv(GLES20.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);

            Log.e("texture", "max texture units "+maxTextureUnits[0]);
            Log.e("texture", "max texture size "+maxTextureSize[0]);

            MAX_TEXTURES = maxTextureUnits[0];
            if (MAX_TEXTURES > 8){
                MAX_TEXTURES = 8;
            }
            textureNames = new int[Texture.MAX_TEXTURES];
            textureNamesUsed = new boolean[Texture.MAX_TEXTURES];
            Texture.config();
        }
        
        
        
        
        if (bounded){
            //Log.e("texture", "textureBounded "+id);
            return textureUnit;
        }

        if (bitmap.isRecycled() || bitmap == null){
            try {
                bitmap = BitmapFactory.decodeResource(Game.mainActivity.getApplicationContext().getResources(), resoureIdentifierId);
            }
            catch (Exception e) {
                Log.e(TAG, "Erro ao criar a textura", e);
            }
        }

        textureUnit = getFreeTextureUnit();
        Log.e("texture", "texture id "+id +" novo textureId "+textureUnit);

        bounded = true;

        // Bind texture to texturename
        if (textureUnit == 0){
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[0]);
        } else {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + textureUnit);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[textureUnit]);
        }

        // Set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_MIRRORED_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_MIRRORED_REPEAT);

        // Load the bitmap into the bound texture.

        Log.e("texture", "nova textura "+id);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();

        return textureUnit;
    }



}
