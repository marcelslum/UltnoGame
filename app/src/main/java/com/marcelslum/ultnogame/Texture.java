package com.marcelslum.ultnogame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by marcel on 14/09/2016.
 */
public class Texture {

    public final static int TEXTURE_BUTTONS_BALLS_STARS = 0;
    public final static int TEXTURE_FONT = 1;
    public final static int TEXTURE_TARGETS = 2;
    public final static int TEXTURE_BARS = 3;
    public final static int TEXTURE_BACKGROUND = 4;
    public final static int TEXTURE_NUMBERS_EXPLOSION = 5;
    public final static int TEXTURE_TITTLE = 6;
    public final static int TEXTURE_SPECIAL_BALL = 7;
    public final static int TEXTURE_LEVEL_ICONS = 8;
    public final static int TEXTURE_TUTORIALS1 = 9;
    public final static int TEXTURE_TUTORIALS2 = 10;
    public final static int TEXTURE_TUTORIAL_ICONS = 11;
    public final static int TEXTURE_GROUP_ICONS = 12;



    public static int MAX_TEXTURES = 8;

    public static int lastTextureUsed = -1;

    public static ArrayList<Texture> textures;

    public static int[] textureNames;
    public static boolean[] textureNamesUsed;
    String resoureIdentifier;
    int resoureIdentifierId;
    int textureUnit;
    int id;
    Bitmap bitmap;
    boolean bounded = false;
    public static final String TAG = "Texture";

    public static void clear(){
        textureNames = null;
        textureNamesUsed = null;
    }

    public static void init() {
        Log.e(TAG, "initTextures");
        Texture.textures.add(new Texture(Texture.TEXTURE_BUTTONS_BALLS_STARS, "drawable/buttons_balls_stars"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TARGETS, "drawable/targets"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BARS, "drawable/bars"));
        Texture.textures.add(new Texture(Texture.TEXTURE_NUMBERS_EXPLOSION, "drawable/numbers_explosion"));
        Texture.textures.add(new Texture(Texture.TEXTURE_SPECIAL_BALL, "drawable/special_ball"));
        Texture.textures.add(new Texture(Texture.TEXTURE_BACKGROUND, "drawable/finalback1c"));
        Texture.textures.add(new Texture(Texture.TEXTURE_LEVEL_ICONS, "drawable/level_icons"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TUTORIAL_ICONS, "drawable/tutorial_icons"));
        Texture.textures.add(new Texture(Texture.TEXTURE_GROUP_ICONS, "drawable/group_icons"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TUTORIALS1, "drawable/tutorials1"));
        Texture.textures.add(new Texture(Texture.TEXTURE_TUTORIALS2, "drawable/tutorials2"));
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
        resoureIdentifierId = Game.mainActivity.getApplicationContext().getResources().getIdentifier(this.resoureIdentifier, null, Game.mainActivity.getApplicationContext().getPackageName());
        bitmap = BitmapFactory.decodeResource(Game.mainActivity.getApplicationContext().getResources(), resoureIdentifierId);

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
