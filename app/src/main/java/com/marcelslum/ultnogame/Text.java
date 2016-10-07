package com.marcelslum.ultnogame;


public class Text extends Entity{

    public final static int TEXT_ALIGN_LEFT = 0;
    public final static int TEXT_ALIGN_CENTER = 1;
    public final static int TEXT_ALIGN_RIGHT = 2;


    public String text;
    public float size;

    public float width;
    public Font font;

    private int indexVertices;
    private int indexIndices;
    private int indexUvs;
    private int indexColors;
    public float[] charData;
    public int align;
    //public float[] colorData2;
    
    public Text(String name, float x, float y, float size, String text, Font font, Color color, int align) {
        super(name, x, y);
        this.text = text;
        this.size = size;
        this.color = color;
        this.font = font;
        this.program = font.program;
        this.textureId = font.textureId;
        this.align = align;
        this.charData = new float[7];
        this.setDrawInfo();

    }

    public Text(String name, float x, float y, float size, String text, Font font, Color color) {
        super(name, x, y);
        this.text = text;
        this.size = size;
        this.color = color;
        this.font = font;
        this.program = this.font.program;
        this.textureId = font.textureId;
        this.align = TEXT_ALIGN_LEFT;

        this.charData = new float[7];
        this.setDrawInfo();
    }

    public Text(String name, float x, float y, float size, String text, Font font) {
        super(name, x, y);
        this.text = text;
        this.size = size;

        this.color = new Color(0f,0f,0f,1f);
        this.font = font;
        this.program = this.font.program;
        this.textureId = font.textureId;
        this.align = TEXT_ALIGN_LEFT;

        this.charData = new float[7];
        this.setDrawInfo();
    }

    public void setText(String text){
        this.text = text;
        this.setDrawInfo();
    }

    public void setDrawInfo(){
        float xOffset = 0f;
        if (align == TEXT_ALIGN_RIGHT) {
            xOffset = -calculateWidth();
        } else if (align == TEXT_ALIGN_CENTER){
            xOffset = -(calculateWidth()/2);
        }

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

        convertTextToTriangleInfo(xOffset);
        
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);

        width = calculateWidth();
    }

    private void convertTextToTriangleInfo(float xOffset){
        // Get attributes from text object
        float cursor = 0 + xOffset;

        float proportion = size/font.lineHeight;

        for(int j=0; j<text.length(); j++){
            char c = text.charAt(j);
            int c_val = (int)c;

            if (charData == null){
                charData = new float[7];
            }

            float indx = font.getCharToIndex(c_val, charData);

            if(indx==-1.0f) {
                cursor += (size);
                continue;
            }

            float x = charData[0]/font.textureSize;
            float x2 = (charData[0] + charData[2])/font.textureSize;
            float y = charData[1]/font.textureSize;
            float y2 = (charData[1] + charData[3])/font.textureSize;

            // Creating the triangle information
            float[] vec = new float[12];
            float[] uv = new float[8];
            float[] colors;

            float destLeft   = cursor + (charData[4]*proportion);
            float destTop    = 0 + (charData[5]*proportion) - (size*0.1f);
            float destRight  = destLeft + (charData[2]*proportion);
            float destBottom = destTop + (charData[3]*proportion)- (size*0.1f);

            vec[0] = cursor;
            vec[1] = destBottom;
            vec[2] = 0f;
            vec[3] = cursor;
            vec[4] = destTop;
            vec[5] = 0f;
            vec[6] = destRight;
            vec[7] = destTop;
            vec[8] = 0f;
            vec[9] = destRight;
            vec[10] = destBottom;
            vec[11] = 0f;

/*
            vec[0] = cursor;
            vec[1] = 0 + size;
            vec[2] = 0f;
            vec[3] = cursor;
            vec[4] = 0;
            vec[5] = 0f;
            vec[6] = cursor + (size*(charData[2]/charData[3]));
            vec[7] = 0;
            vec[8] = 0f;
            vec[9] = cursor + (size*(charData[2]/charData[3]));
            vec[10] = 0 + size;
            vec[11] = 0f;
*/
            colors = new float[]
                    {   this.color.r, this.color.g, this.color.b, this.color.a,
                        this.color.r, this.color.g, this.color.b, this.color.a,
                        this.color.r, this.color.g, this.color.b, this.color.a,
                        this.color.r, this.color.g, this.color.b, this.color.a
                    };

            uv[0] = x;
            uv[1] = y2;
            uv[2] = x;
            uv[3] = y;
            uv[4] = x2;
            uv[5] = y;
            uv[6] = x2;
            uv[7] = y2;

            short[] inds = {0, 1, 2, 0, 2, 3};

            // Add our triangle information to our collection for 1 render call.
            addCharRenderInformation(vec, colors, uv, inds);

            // Calculate the new position
            cursor +=  charData[6] * proportion;
            if (c_val == 32){
                cursor += size*0.15f;
            }
        }
    }

    public float calculateWidth(){
        float cursor = 0;

        for(int j=0; j<text.length(); j++)
        {
            char c = text.charAt(j);
            int c_val = (int)c;

            if (charData == null){
                charData = new float[7];
            }

            float indx = font.getCharToIndex(c_val, charData);

            if(indx==-1.0f) {
                // unknown character, we will add a space for it to be save.
                cursor += (size);
                continue;
            }

            cursor += (size/font.lineHeight) * charData[6];
        }
        return cursor;
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

    public void setX(float x) {
        this.x = x;
        setDrawInfo();
    }

    public void setColor(Color color) {
        this.color = color;
        this.setDrawInfo();
    }

    @Override
    public float getWidth() {
        return width;
    }

    public float getTransformedWidth(){
        return width * accumulatedScaleX;
    }

    @Override
    public float getHeight() {
        return size*1.5f;
    }

    public void reduceWidth(float desiredWidth) {
        int length = text.length();
        Text textForMeasure;
        float widthOfText;
        int contador = 1;
        for (int i = 0; i < length; i++) {
            //Log.e("text", "testando "+text.substring(0, length - contador));
            textForMeasure = new Text("text", 0f, 0f, size, text.substring(0, length - contador), Game.font, color);
            widthOfText = textForMeasure.calculateWidth();
            //Log.e("text", "width "+widthOfText);
            if (widthOfText < desiredWidth) {
                //Log.e("text", "texto final ");
                setText(text.substring(0, length - contador).concat("..."));
                break;
            }
            contador += 1;
        }
    }
}