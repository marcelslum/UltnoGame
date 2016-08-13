package ultno.marcelslum.ultno;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Iterator;
import java.util.Vector;

public class TextManager {

    private static final float RI_TEXT_UV_BOX_WIDTH = 0.0625f;
    private static final float RI_TEXT_TEXTURE_SIZE = 512.0f;
    private static final float RI_TEXT_WIDTH = 64.0f;
    private static final float RI_TEXT_SPACESIZE = 20f;

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer colorBuffer;
    private ShortBuffer drawListBuffer;

    private float[] vecs;
    private float[] uvs;
    private short[] indices;
    private float[] colors;

    private int index_vecs;
    private int index_indices;
    private int index_uvs;
    private int index_colors;

    private int texturenr;

    private float uniformscale;

    public static int[] l_size = {
            8, 8,	6,	9,	16,	13,	14,	16,	5,	8,	8,	8,	12,	6,	10,	6,
            13, 14,	8,	13,	14,	14,	13,	13,	13,	14,	14,	6,	6,	9,	12,	9,
            13, 21,	14,	14,	13,	13,	13,	13,	13,	14,	6,	11,	14,	11,	17,	14,
            14, 13,	14,	13,	13,	13,	14,	13,	17,	13,	13,	13,	8,	13,	7,	10,
            14, 6,	14,	13,	14,	14,	14,	10,	14,	13,	6,	5,	13,	6,	17,	14,
            14, 13,	14,	11,	14,	10,	14,	13,	17,	13,	14,	14,	10,	5,	10,	11,
            8, 13,	8,	8,	8,	8,	8,	8,	8,	8,	8,	8,	11,	21,	8,	8,
            8, 8,	6,	6,	11,	11,	6,	13,	16,	8,	19,	8,	11,	23,	8,	8,
            13, 8,	6,	13,	14,	15,	13,	8,	8,	12,	14,	8,	13,	8,	8,	14,
            8, 9,	12,	8,	8,	6,	13,	13,	8,	8,	8,	8,	13,	8,	8,	8,
            13, 13,	13,	13,	13,	13,	13,	21,	13,	13,	13,	13,	14,	6,	6,	10,
            11, 8,	13,	13,	13,	13,	13,	13,	8,	8,	13,	13,	13,	13,	13,	8,
            8, 13,	13,	13,	13,	13,	13,	23,	13,	13,	13,	13,	13,	6,	6,	8,
            11, 8,	13,	13,	13,	13,	13,	13,	13,	8,	13,	13,	13,	13,	13,	8
    };

    public Vector<Text> txtcollection;

    public TextManager()
    {
        // Create our container
        txtcollection = new Vector<Text>();

        // Create the arrays
        vecs = new float[3 * 10];
        colors = new float[4 * 10];
        uvs = new float[2 * 10];
        indices = new short[10];

        // init as 0 as default
        texturenr = 0;
    }

    public void addText(Text obj)
    {
        // Add text object to our collection
        txtcollection.add(obj);
    }

    public void setTextureID(int val)
    {
        texturenr = val;
    }


    public void AddCharRenderInformation(float[] vec, float[] cs, float[] uv, short[] indi)
    {
        // We need a base value because the object has indices related to
        // that object and not to this collection so basicly we need to
        // translate the indices to align with the vertexlocation in ou
        // vecs array of vectors.
        short base = (short) (index_vecs / 3);

        // We should add the vec, translating the indices to our saved vector
        for(int i=0;i<vec.length;i++)
        {
            vecs[index_vecs] = vec[i];
            index_vecs++;
        }

        // We should add the colors, so we can use the same texture for multiple effects.
        for(int i=0;i<cs.length;i++)
        {
            colors[index_colors] = cs[i];
            index_colors++;
        }

        // We should add the uvs
        for(int i=0;i<uv.length;i++)
        {
            uvs[index_uvs] = uv[i];
            index_uvs++;
        }

        // We handle the indices
        for(int j=0;j<indi.length;j++)
        {
            indices[index_indices] = (short) (base + indi[j]);
            index_indices++;
        }
    }

    public void PrepareDrawInfo()
    {
        // Reset the indices.
        index_vecs = 0;
        index_indices = 0;
        index_uvs = 0;
        index_colors = 0;

        // Get the total amount of characters
        int charcount = 0;
        for (Text txt : txtcollection) {
            if(txt!=null)
            {
                if(!(txt.text==null))
                {
                    charcount += txt.text.length();
                }
            }
        }

        // Create the arrays we need with the correct size.
        vecs = null;
        colors = null;
        uvs = null;
        indices = null;

        vecs = new float[charcount * 12];
        colors = new float[charcount * 16];
        uvs = new float[charcount * 8];
        indices = new short[charcount * 6];

    }

    public void PrepareDraw()
    {
        // Setup all the arrays
        PrepareDrawInfo();

        // Using the iterator protects for problems with concurrency
        for(Iterator<Text> it = txtcollection.iterator(); it.hasNext() ; )
        {
            Text txt = it.next();
            if(txt!=null)
            {
                if(!(txt.text==null))
                {
                    Log.e("size on prepare ", " "+txt.size);
                    convertTextToTriangleInfo(txt);
                }
            }
        }
    }

    public void Draw(float[] m)
    {
        // Set the correct shader for our grid object.
        GLES20.glUseProgram(GraphicTools.sp_Text);

        // The vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(vecs.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vecs);
        vertexBuffer.position(0);

        // The vertex buffer.
        ByteBuffer bb3 = ByteBuffer.allocateDirect(colors.length * 4);
        bb3.order(ByteOrder.nativeOrder());
        colorBuffer = bb3.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        // The texture buffer
        ByteBuffer bb2 = ByteBuffer.allocateDirect(uvs.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        textureBuffer = bb2.asFloatBuffer();
        textureBuffer.put(uvs);
        textureBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(indices);
        drawListBuffer.position(0);

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(GraphicTools.sp_Text, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the background coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                0, vertexBuffer);

        int mTexCoordLoc = GLES20.glGetAttribLocation(GraphicTools.sp_Text, "a_texCoord" );

        // Prepare the texturecoordinates
        GLES20.glVertexAttribPointer ( mTexCoordLoc, 2, GLES20.GL_FLOAT,
                false,
                0, textureBuffer);

        GLES20.glEnableVertexAttribArray ( mPositionHandle );
        GLES20.glEnableVertexAttribArray ( mTexCoordLoc );

        int mColorHandle = GLES20.glGetAttribLocation(GraphicTools.sp_Text, "a_Color");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mColorHandle);

        // Prepare the background coordinate data
        GLES20.glVertexAttribPointer(mColorHandle, 4,
                GLES20.GL_FLOAT, false,
                0, colorBuffer);

        // get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(GraphicTools.sp_Text, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        int mSamplerLoc = GLES20.glGetUniformLocation (GraphicTools.sp_Text, "s_texture" );

        // Set the sampler texture unit to our selected id
        GLES20.glUniform1i ( mSamplerLoc, texturenr);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indices.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTexCoordLoc);
        GLES20.glDisableVertexAttribArray(mColorHandle);

    }


    
    public float getWidth(Text val){
        float initialX = 0;
        String text = val.text;
        float size = val.size;

        for(int j=0; j<text.length(); j++) {
            // get ascii value
            char c = text.charAt(j);
            int c_val = (int) c;
            int indx = convertCharToIndex(c_val);

            if (indx == -1) {
                // unknown character, we will add a space for it to be save.
                initialX += (size / 2);
                continue;
            }

            initialX += ((l_size[indx]) * (size / (RI_TEXT_TEXTURE_SIZE * RI_TEXT_UV_BOX_WIDTH)));
        }
        return initialX;
    }

    public int convertCharToIndex(int c_val)
    {
        int indx = -1;

        // Retrieve the index
        if(c_val>64&&c_val<91) // A-Z
            indx = c_val - 31;
        else if(c_val>96&&c_val<123) // a-z
            indx = c_val - 31;
        else if(c_val>47&&c_val<58) // 0-9
            indx = c_val - 32;
        else if(c_val==43) // +
            indx = 12;
        else if(c_val==45) // -
            indx = 14;
        else if(c_val==33) // !
            indx = 2;
        else if(c_val==63) // ?
            indx = 32;
        else if(c_val==61) // =
            indx = 30;
        else if(c_val==58) // :
            indx = 27;
        else if(c_val==46) // .
            indx = 15;
        else if(c_val==44) // ,
            indx = 43;
        else if(c_val==42) // *
            indx = 44;
        else if(c_val==36) // $
            indx = 45;
        else if(c_val==36) // ,???????????
            indx = 13;
        else if(c_val==36) // ã
            indx = 13;
        else if(c_val==233) //é
            indx = 201;
        else if(c_val==36) // á
            indx = 13;
        else if(c_val==36) // à
            indx = 13;
        else if(c_val==36) // ,???????????
            indx = 13;
        else if(c_val==36) // ,???????????
            indx = 13;
        else if(c_val==36) // ,???????????
            indx = 13;

        return indx;
    }
    
    private void convertTextToTriangleInfo(Text val)
    {


        // Get attributes from text object
        float x = val.x;
        float y = val.y;
        String text = val.text;
        float size = val.size;

        Log.e("size ", " "+size);

        // Create
        for(int j=0; j<text.length(); j++)
        {
            // get ascii value
            char c = text.charAt(j);
            Log.e("char ", " "+ c);

            int c_val = (int)c;

            Log.e("char int", " "+ c_val);

            int indx = convertCharToIndex(c_val);

            Log.e("char indx", " "+ indx);

            if(indx==-1) {
                // unknown character, we will add a space for it to be save.
                x += (size/2);
                continue;
            }

            // Calculate the uv parts
            int row = indx / 16;
            int col = indx % 16;

            float v = row * RI_TEXT_UV_BOX_WIDTH;
            float v2 = v + RI_TEXT_UV_BOX_WIDTH;
            float u = col * RI_TEXT_UV_BOX_WIDTH;
            float u2 = u + RI_TEXT_UV_BOX_WIDTH;

            // Creating the triangle information
            float[] vec = new float[12];
            float[] uv = new float[8];
            float[] colors = new float[16];

            vec[0] = x;
            vec[1] = y + size;
            vec[2] = 0.95f;
            vec[3] = x;
            vec[4] = y;
            vec[5] = 0.95f;
            vec[6] = x + size;
            vec[7] = y;
            vec[8] = 0.95f;
            vec[9] = x + size;
            vec[10] = y + size;
            vec[11] = 0.95f;

            colors = new float[]
                    {val.colorData2[0], val.colorData2[1], val.colorData2[2], val.colorData2[3],
                            val.colorData2[0], val.colorData2[1], val.colorData2[2], val.colorData2[3],
                            val.colorData2[0], val.colorData2[1], val.colorData2[2], val.colorData2[3],
                            val.colorData2[0], val.colorData2[1], val.colorData2[2], val.colorData2[3]
                    };
            // 0.001f = texture bleeding hack/fix
            uv[0] = u+0.001f;
            uv[1] = v+0.001f;
            uv[2] = u+0.001f;
            uv[3] = v2-0.001f;
            uv[4] = u2-0.001f;
            uv[5] = v2-0.001f;
            uv[6] = u2-0.001f;
            uv[7] = v+0.001f;

            short[] inds = {0, 1, 2, 0, 2, 3};

            // Add our triangle information to our collection for 1 render call.
            AddCharRenderInformation(vec, colors, uv, inds);

            // Calculate the new position



            x += ((l_size[indx])*(size/(RI_TEXT_TEXTURE_SIZE*RI_TEXT_UV_BOX_WIDTH)));
        }
    }

}
