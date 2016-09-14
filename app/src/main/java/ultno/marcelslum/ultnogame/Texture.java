package ultno.marcelslum.ultnogame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

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

    public static final int MAX_TEXTURES = 8;

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
            Texture.config();
        }
        resoureIdentifierId = Game.context.getResources().getIdentifier(this.resoureIdentifier, null, Game.context.getPackageName());
        bitmap = BitmapFactory.decodeResource(Game.context.getResources(), resoureIdentifierId);
    }

    public static Texture getTextureById(int id){
        for (int i = 0; i < textures.size(); i++){
            if (textures.get(i).id == id){
                return textures.get(i);
            }
        }
        return null;
    }

    public static void config(){
        GLES20.glGenTextures(Texture.MAX_TEXTURES, textureNames, 0);
    }

    public static int getFreeTextureUnit(){
        for (int i = 0; i < MAX_TEXTURES; i++){
            if (!textureNamesUsed[i]){
                return i;
            }
        }

        getTextureById(0).bounded = false;
        return 0;
    }

    public int bind(){
        if (bounded){
            return textureUnit;
        }

        textureUnit = getFreeTextureUnit();

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
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

        return textureUnit;
    }
}
