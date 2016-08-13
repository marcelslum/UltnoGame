package ultno.marcelslum.ultno;

public class Text extends Entity{

    public String text;
    public float size;

    public TextManager tm;
    public float width;
    public Font font;

    private int indexVertices;
    private int indexIndices;
    private int indexUvs;
    private int indexColors;
    public float[] charData;
    public float[] colorData2;

    public Text(String name, Game game, float x, float y, float size, String text, Font font, Color color) {
        super(name, game, x, y);
        this.text = text;
        this.size = size;
        this.color = color;
        this.font = font;

        this.charData = new float[]{0f, 0f, 0f, 0f,};
        //Log.e("this.color", " teste ");
        this.colorData2 = new float[]{0.9f, 0.5f, 0f, 0.2f,};
        //Log.e("this.color", " "+this.colorData2[1]);
        this.setDrawInfo();
    }

    public Text(String name, Game game, float x, float y, float size, String text, Font font) {
        super(name, game, x, y);
        this.text = text;
        this.size = size;

        this.color = new Color(1,1,1,1);
        this.font = font;

        this.charData = new float[]{0f, 0f, 0f, 0f};
        //Log.e("this.color", " teste ");
        this.colorData2 = new float[]{0f, 0f, 0f, 0.8f,};
        //Log.e("this.color", " "+this.colorData2[1]);
        this.setDrawInfo();
    }

    public void setDrawInfo(){

        indexVertices = 0;
        indexIndices = 0;
        indexUvs = 0;
        indexColors = 0;

        // Get the total amount of characters
        int charcount = this.text.length();

        verticesData = null;
        colorsData = null;
        uvsData = null;
        indicesData = null;
        
        initializeData(charcount * 12, charcount * 6, charcount * 8, charcount * 16);

        convertTextToTriangleInfo();
        
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);

        //Log.e("teste1115", " ");
    }

    private void convertTextToTriangleInfo()
    {
        // Get attributes from text object

        //Log.e("size ", " "+size);

        //Log.e("convertTextToTri", "1 ");

        float initialX = 0;

        // Create

        //Log.e("convertTextToTri", "1 ");
        for(int j=0; j<text.length(); j++)
        {
            // get ascii value
            char c = text.charAt(j);
            int c_val = (int)c;

            if (this.charData == null){
                this.charData = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
            }
            //Log.e("convertTextToTri", "2 ");

            //Log.e("this.color", " "+this.colorData2[1]);


            //Log.e("convertTextToTri", "3 ");
            float indx = font.getCharToIndex(c_val, this.charData);

            //Log.e("this.color", " "+this.colorData2[1]);

            if(indx==-1.0f) {
                // unknown character, we will add a space for it to be save.
                initialX += (size);
                continue;
            }

            float y = charData[1]/font.textureSize;
            float y2 = (charData[1] + charData[3])/font.textureSize;
            float x = charData[0]/font.textureSize;
            float x2 = (charData[0] + charData[2])/font.textureSize;;

            // Creating the triangle information
            float[] vec = new float[12];
            float[] uv = new float[8];
            float[] colors;
/*
            vec[0] = initialX;
            vec[1] = 0 + size;
            vec[2] = 0.95f;
            vec[3] = initialX;
            vec[4] = 0;
            vec[5] = 0.95f;
            vec[6] = initialX + size;
            vec[7] = 0;
            vec[8] = 0.95f;
            vec[9] = initialX + size;
            vec[10] = 0 + size;
            vec[11] = 0.95f;

*/
            vec[0] = initialX;
            vec[1] = 0 + size;
            vec[2] = 0.95f;
            vec[3] = initialX;
            vec[4] = 0;
            vec[5] = 0.95f;
            vec[6] = initialX + (size*(charData[2]/charData[3]));
            vec[7] = 0;
            vec[8] = 0.95f;
            vec[9] = initialX + (size*(charData[2]/charData[3]));
            vec[10] = 0 + size;
            vec[11] = 0.95f;

            colors = new float[]
                    {   this.colorData2[0], this.colorData2[1], this.colorData2[2], this.colorData2[3],
                        this.colorData2[0], this.colorData2[1], this.colorData2[2], this.colorData2[3],
                        this.colorData2[0], this.colorData2[1], this.colorData2[2], this.colorData2[3],
                        this.colorData2[0], this.colorData2[1], this.colorData2[2], this.colorData2[3]
                    };
            // 0.001f = texture bleeding hack/fix
            uv[0] = x+0.001f;
            uv[1] = y2+0.001f;
            uv[2] = x+0.001f;
            uv[3] = y-0.001f;
            uv[4] = x2-0.001f;
            uv[5] = y-0.001f;
            uv[6] = x2-0.001f;
            uv[7] = y2+0.001f;

            short[] inds = {0, 1, 2, 0, 2, 3};

            // Add our triangle information to our collection for 1 render call.


            for (int i = 0; i < colors.length; i++){
                //Log.e("colorsData111 "+i, " "+colors[i]);
            }

            addCharRenderInformation(vec, colors, uv, inds);

            // Calculate the new position
            //Log.e("x", " "+x);
            initialX += (size*(charData[2]/charData[3]))+(this.size*0.05);
        }
    }

    public float calculateWidth(Text val){
        float initialX = 0;
        String text = val.text;
        float size = val.size;

        for(int j=0; j<text.length(); j++) {
            // get ascii value
            char c = text.charAt(j);
            int c_val = (int) c;
            float indx = font.getCharToIndex(c_val, charData);

            if (indx == -1) {
                // unknown character, we will add a space for it to be save.
                initialX += (size / 2);
                continue;
            }

            initialX += charData[2]+(this.size*0.2);
        }
        return initialX;
    }

    public void addCharRenderInformation(float[] vec, float[] cs, float[] uv, short[] indi)
    {
        // We need a base value because the object has indices related to
        // that object and not to this collection so basicly we need to
        // translate the indices to align with the vertexlocation in ou
        // vecs array of vectors.
        short base = (short) (indexVertices / 3);

        // We should add the vec, translating the indices to our saved vector
        for(int i=0;i<vec.length;i++)
        {
            verticesData[indexVertices] = vec[i];
            indexVertices++;
        }

        // We should add the colors, so we can use the same texture for multiple effects.
        for(int i=0;i<cs.length;i++)
        {
            colorsData[indexColors] = cs[i];
            indexColors++;
        }

        // We should add the uvs
        for(int i=0;i<uv.length;i++)
        {
            uvsData[indexUvs] = uv[i];
            indexUvs++;
        }

        // We handle the indices
        for(int j=0;j<indi.length;j++)
        {
            indicesData[indexIndices] = (short) (base + indi[j]);
            indexIndices++;
        }
    }

}
