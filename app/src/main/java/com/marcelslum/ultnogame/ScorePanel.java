package com.marcelslum.ultnogame;


import android.util.Log;

/**
 * Created by marcel on 12/08/2016.
 */
public class ScorePanel extends Entity {


    static final String TAG = "ScorePanel";


    public float size;
    public int value;
    public int animDuration;
    public int animLastValue;
    public long animStartTime;
    private boolean animStarted;

    private final static float textureSize = 1024f;
    private final static float[] columns = new float[]{142f, 284f, 426f, 568f, 710f, 852f, 994f};
    private final static float[] lines = new float[]{256f, 512f, 779f};
    private boolean displayMessage;
    private Text messageText;

    ScorePanel(String name, float x, float y, float size) {
        super(name, x, y, Entity.TYPE_PANEL);

        this.x -= (size * 0.55294f) * 3.5f;

        this.size = size;
        isSolid = false;
        isCollidable = false;
        isVisible = true;
        alpha = 1;
        textureId = Texture.TEXTURES;
        program = Game.imageProgram;
        value = 0;
        setDrawInfo();

        messageText = Game.textPool.get();
        messageText.setData("text", x + (getWidth()*0.8f), y - size*0.3f, size*1.5f, ".", Game.font, new Color(1.0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT);
        addChild(messageText);


        setListener(new InteractionListener("listener" + name, this.x, y, size * 3, size, 5000, this, new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (Game.gameState == Game.GAME_STATE_JOGAR) {
                    Game.setGameState(Game.GAME_STATE_VITORIA);
                }
            }

            @Override
            public void onUnpress() {

            }
        }));

    }

    @Override
    public float getWidth() {
        return size * 0.55294f * 7;
    }

    @Override
    public float getHeight() {
        return size;
    }

    public void setDrawInfo() {

        String valueString = String.valueOf(value);

        int valueLength = valueString.length();

        initializeData(12 * 7, 6 * 7, 8 * 7, 0);

        float width = size * 0.55294f;

        float xOfTriangle = 0f;

        for (int i = 0; i < 7; i++) {

            int subInteger;

            if (i < (7 - valueLength)) {
                subInteger = 0;
            } else {


                String subString = valueString.substring(i - (7 - valueLength), i + 1 - (7 - valueLength));

                //Log.e("point", "subString "+subString);

                subInteger = Integer.parseInt(subString);

                //Log.e("point", "subInteger "+subInteger);
            }

            Utils.insertRectangleVerticesData(verticesData, i * 12, xOfTriangle, xOfTriangle + width, 0f, size, 0f);
            xOfTriangle += width - 1f;

            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            switch (subInteger) {
                case 1:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL1_ID));
                    break;
                case 2:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL2_ID));
                    break;
                case 3:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL3_ID));
                    break;
                case 4:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL4_ID));
                    break;
                case 5:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL5_ID));
                    break;
                case 6:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL6_ID));
                    break;
                case 7:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL7_ID));
                    break;
                case 8:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL8_ID));
                    break;
                case 9:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL9_ID));
                    break;
                case 0:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL0_ID));
                    break;
            }

        }

        verticesBuffer = Utils.generateOrUpdateFloatBuffer(verticesData, verticesBuffer);
        indicesBuffer = Utils.generateOrUpdateShortBuffer(indicesData, indicesBuffer);
        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }



    public void showMessage(String message, int duration) {
        displayMessage = true;

        Log.e(TAG, "showMessage "+ message);
        //Log.e(TAG, "x + (getWidth()*0.8f) "+ (x + (getWidth()*0.8f)));
        //Log.e(TAG, "y - size*0.3f "+ (y - size*0.3f));
        //Log.e(TAG, "size*1.5f "+ (size*1.5f));


        messageText.setAlpha(1.0f);

        //childs.clear();
        //messageText = Game.textPool.get();
        //messageText.setData("textScorePanel", x + (getWidth()*0.8f), y - size*0.3f, size*1.5f, message, Game.font, new Color(1.0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT);
        //addChild(messageText);

        messageText.setText(message);

        final ScorePanel innerScorePanel = this;
        Utils.createSimpleAnimation(messageText, "translateX", "translateX", duration, 0f, Game.gameAreaResolutionX*0.05f).start();
        Utils.createSimpleAnimation(messageText, "translateY", "translateY", duration, 0f, -Game.gameAreaResolutionX*0.05f).start();

        messageText.reduceAlpha(duration, 0f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerScorePanel.displayMessage = false;
                innerScorePanel.messageText.cleanAnimations();
            }
        });

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
                float valueToDisplay = animLastValue + ((value - animLastValue)*((float)delta / (float)animDuration));
                //Log.e("scorepanel", "valueToDisplay "+valueToDisplay );
                changeDisplayValue((int)valueToDisplay);

            } else {
                animStarted = false;
                changeDisplayValue(value);
            }
        }
        super.render(matrixView, matrixProjection);


    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {
        super.prepareRender(matrixView, matrixProjection);
        if (displayMessage && messageText != null){
            messageText.prepareRender(matrixView, matrixProjection);
        }
    }

    public void changeDisplayValue(int valueToDisplay){
        String valueString = String.valueOf(valueToDisplay);
        int valueLength = valueString.length();
        for (int i = 0; i < 7;i++){
            int subInteger;
            if (i < (7-valueLength)) {
                subInteger = 0;
            } else {
                String subString = valueString.substring(i - (7 - valueLength), i + 1- (7 - valueLength));
                subInteger = Integer.parseInt(subString);
            }
            //if (subInteger == 0){
            //    subInteger = 10;
            //}

            switch (subInteger) {
                case 1:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL1_ID));
                    break;
                case 2:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL2_ID));
                    break;
                case 3:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL3_ID));
                    break;
                case 4:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL4_ID));
                    break;
                case 5:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL5_ID));
                    break;
                case 6:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL6_ID));
                    break;
                case 7:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL7_ID));
                    break;
                case 8:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL8_ID));
                    break;
                case 9:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL9_ID));
                    break;
                case 0:
                    Utils.insertRectangleUvData(uvsData, i * 8, TextureData.getTextureDataById(TextureData.TEXTURE_POINT_PANEL0_ID));
                    break;
            }
        }

        uvsBuffer = Utils.generateOrUpdateFloatBuffer(uvsData, uvsBuffer);
    }

    public void setValue(int newValue, boolean animatePanel, int duration, boolean playSound){

        if (playSound){
            Sound.play(Sound.soundScore, 1, 1, 0);
        }

        //Log.e("setValue", "animate "+animatePanel );
        if (animatePanel){
            //Log.e("setValue", "animate2 "+animatePanel );
            animStartTime = System.currentTimeMillis();
            animStarted = true;
            animLastValue = this.value;
            animDuration = duration;
        } else {
            changeDisplayValue(newValue);
        }
        value = newValue;
    }
}
