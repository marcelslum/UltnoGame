package ultno.marcelslum.ultnogame;


import android.util.Log;

/**
 * Created by marcel on 12/08/2016.
 */
public class ScorePanel extends Entity{

    public float size;
    public int value;
    public int animDuration;
    public int animLastValue;
    public long animStartTime;
    private boolean animStarted;
    
    private final static float textureSize = 1024f;
    private final static float [] columns = new float [] {142f,284f,426f, 568f, 710f, 852f, 994f};
    private final static float [] lines = new float [] {256f,512f,779f};

    ScorePanel(String name, Game game, float x, float y, float size) {
        super(name, game, x, y);

        this.x -= (size * 0.55294f)*2.5f;

        this.size = size;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        alpha = 1;
        textureUnit = Game.TEXTURE_NUMBERS_EXPLOSION_OBSTACLE;
        program = this.game.imageProgram;
        value = 0;
        setDrawInfo();
    }

    @Override
    public float getWidth(){
        return size * 0.55294f * 5;
    }

    @Override
    public float getHeight(){
        return size;
    }

    public void setDrawInfo(){
        
        String valueString = String.valueOf((int)value);
        
        int valueLength = valueString.length();

        initializeData(12*5, 6*5, 8*5, 0);

        float width = size * 0.55294f;

        float xOfTriangle = 0f;

        for (int i = 0; i < 5;i++){

            int subInteger;

            if (i < (5-valueLength)) {
                subInteger = 0;
            } else {


                String subString = valueString.substring(i - (5 - valueLength), i + 1- (5 - valueLength));

                //Log.e("point", "subString "+subString);

                subInteger = Integer.parseInt(subString);

                //Log.e("point", "subInteger "+subInteger);
            }

            Utils.insertRectangleVerticesData(verticesData, 0 + (i * 12), xOfTriangle, xOfTriangle+width, 0f, size, 0f);
            xOfTriangle += width - 1f;
            
            Utils.insertRectangleIndicesData(indicesData, 0 + (i * 6), 0 + (i * 4));

            int textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE1;
                switch (subInteger){
                    case 1:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE1;
                        break;
                    case 2:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE2;
                        break;
                    case 3:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE3;
                        break;
                    case 4:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE4;
                        break;
                    case 5:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE5;
                        break;
                    case 6:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE6;
                        break;
                    case 7:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE7;
                        break;
                    case 8:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE8;
                        break;
                    case 9:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE9;
                        break;
                    case 0:
                        textureMap = Game.TEXTURE_MAP_NUMBERS_SCORE0;
                        break;
                }
                Utils.insertRectangleUvDataNumbersExplosion(this.uvsData, 0 + (i * 8), textureMap);
            
        }
        
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);

    }

    public void render(float[] matrixView, float[] matrixProjection){
        if (animStarted){
            //Log.e("scorepanel", "animstarted "+animStarted );
            int delta = (int) (System.currentTimeMillis() - animStartTime);
            //Log.e("scorepanel", "delta "+delta );
            if (animDuration > (delta)){
                //Log.e("scorepanel", "this.value "+this.value );
                //Log.e("scorepanel", "animLastValue "+animLastValue );
                //Log.e("scorepanel", "delta / animDuration "+ ((float)delta / (float)animDuration ));
                float valueToDisplay = animLastValue + ((this.value - animLastValue)*((float)delta / (float)animDuration));
                //Log.e("scorepanel", "valueToDisplay "+valueToDisplay );
                changeDisplayValue((int)valueToDisplay);

            } else {
                animStarted = false;
                changeDisplayValue(this.value);
            }

        }

        super.render(matrixView, matrixProjection);
    }

    public void changeDisplayValue(int valueToDisplay){
        String valueString = String.valueOf(valueToDisplay);
        int valueLength = valueString.length();
        for (int i = 0; i < 5;i++){
            int subInteger;
            if (i < (5-valueLength)) {
                subInteger = 0;
            } else {
                String subString = valueString.substring(i - (5 - valueLength), i + 1- (5 - valueLength));
                subInteger = Integer.parseInt(subString);
            }
            if (subInteger == 0){
                subInteger = 10;
            }
            prepareUvData(subInteger);
            Utils.insertRectangleUvData(uvsData, 0 + (i * 8));
        }
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }

    public void setValue(int newValue, boolean animatePanel, int duration, boolean playSound){
        Log.e("setValue", "animate "+animatePanel );
        if (animatePanel){
            Log.e("setValue", "animate2 "+animatePanel );
            this.animStartTime = System.currentTimeMillis();
            this.animStarted = true;
            this.animLastValue = this.value;
            this.animDuration = duration;
        } else {
            changeDisplayValue(newValue);
        }
        this.value = newValue;
    }

    public void prepareUvData(int textureMap){

        Utils.y1 = 0f;
        Utils.y2 = 0f;

        if (textureMap < 8){
            Utils.y1 = 0.001f;
            Utils.y2 = (lines[0]-1f)/textureSize;
        } else if (textureMap < 15){
            Utils.y1 = (lines[0]+1f)/textureSize;
            Utils.y2 = (lines[1]-1f)/textureSize;
        } else {
            Utils.y1 = (lines[1]+1f)/textureSize;
            Utils.y2 = (lines[2]-1f)/textureSize;
        }

        Utils.x1 = 0;
        Utils.x2 = 0;

        if (textureMap == 1 || textureMap == 8 || textureMap == 15){
            Utils.x1 = 0.001f;
            Utils.x2 = (columns[0]-1f)/textureSize;
        } else if (textureMap == 2 || textureMap == 9 || textureMap == 16){
            Utils.x1 = (columns[0]+1f)/textureSize;
            Utils.x2 = (columns[1]-1f)/textureSize;
        } else if (textureMap == 3 || textureMap == 10 || textureMap == 17){
            Utils.x1 = (columns[1]+1f)/textureSize;
            Utils.x2 = (columns[2]-1f)/textureSize;
        } else if (textureMap == 4 || textureMap == 11 || textureMap == 18){
            Utils.x1 = (columns[2]+1f)/textureSize;
            Utils.x2 = (columns[3]-1f)/textureSize;
        } else if (textureMap == 5 || textureMap == 12 || textureMap == 19){
            Utils.x1 = (columns[3]+1f)/textureSize;
            Utils.x2 = (columns[4]-1f)/textureSize;
        } else if (textureMap == 6 || textureMap == 13 || textureMap == 20){
            Utils.x1 = (columns[4]+1f)/textureSize;
            Utils.x2 = (columns[5]-1f)/textureSize;
        } else if (textureMap == 7 || textureMap == 14){
            Utils.x1 = (columns[5]+1f)/textureSize;
            Utils.x2 = (columns[6]-1f)/textureSize;
        }
    }
}
