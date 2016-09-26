package ultno.marcelslum.ultnogame;

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

    public final static int TEXTURE_BUTTONS_AND_BALLS = 0;
    public final static int TEXTURE_FONT = 1;
    public final static int TEXTURE_TARGETS = 2;
    public final static int TEXTURE_BARS = 3;
    public final static int TEXTURE_BACKGROUND = 4;
    public final static int TEXTURE_NUMBERS_EXPLOSION_OBSTACLE = 5;
    public final static int TEXTURE_TITTLE = 6;
    public final static int TEXTURE_SPECIAL_BALL = 7;
    public final static int TEXTURE_OBSTACLE = 8;

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

    Texture(int id, String resourceIdentifier){
        this.id = id;
        this.resoureIdentifier = resourceIdentifier;

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
        resoureIdentifierId = Game.context.getResources().getIdentifier(this.resoureIdentifier, null, Game.context.getPackageName());
        bitmap = BitmapFactory.decodeResource(Game.context.getResources(), resoureIdentifierId);


        //Picasso.with(Game.context).load(resoureIdentifierId).into(bitmap);

    }

    public static Texture getTextureById(int id){
        for (int i = 0; i < textures.size(); i++){
            if (textures.get(i).id == id){
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

    public int bind(){
        if (bounded){
            //Log.e("texture", "textureBounded "+id);
            return textureUnit;
        }

        textureUnit = getFreeTextureUnit();
        Log.e("texture", "texture id "+id +" novo textureUnit "+textureUnit);

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

        //Log.e("texture", "nova textura "+id);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        return textureUnit;
    }
}
