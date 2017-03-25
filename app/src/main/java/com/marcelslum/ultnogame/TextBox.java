package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class TextBox extends Entity{
    public ArrayList<Text> texts;
    public float width;
    public float height;
    public float size;
    public String text;
    public float padding = 0.2f;
    public boolean isHaveArrow = false;
    public boolean isHaveFrame = false;
    public boolean isHaveMiniArrow = false;
    public boolean isHaveArrowContinue = false;
    public Button arrowContinuar;
    public Color textColor = new Color(0f, 0f, 0f, 0.9f);
    public Line arrow;
    public Image miniArrow;
    public float arrowX;
    public float arrowY;
    public Entity frame;
    public float frameWidth;
    public int frameType;
    private OnPress onPress;

    public TextBox(TextBoxBuilder builder) {
        super(builder.name, builder.x, builder.y, Entity.TYPE_TEXT_BOX);
        width = builder.width;
        size = builder.size;
        isHaveArrow = builder.isHaveArrow;
        isHaveFrame = builder.isHaveFrame;
        isHaveArrowContinue = builder.isHaveArrowContinue;
        isHaveMiniArrow = builder.isHaveMiniArrow;
        arrowX = builder.arrowX;
        arrowY = builder.arrowY;
        texts = new ArrayList<>();
        frameType = builder.frameType;
        setText(builder.text);
    }
    
    public void setText(String text){
        if (texts != null){
            texts = new ArrayList<>();
        }
        if (childs != null){
            childs.clear();
        }
        this.text = text;

        float widthToSplit = width * 0.9f;
        if (!isHaveArrowContinue) {widthToSplit = width;}

        texts = Text.splitStringAtMaxWidth(name, text, Game.font, textColor, size, widthToSplit);

        float textPadding = size * padding;

        Text.doLinesWithStringCollection(texts, y + (textPadding * 4), size, textPadding, false);

        float textX = x + (textPadding * 4);
        for (int i = 0; i < texts.size(); i++){
            texts.get(i).x = textX;
            addChild(texts.get(i));
        }

        float lastTextY = texts.get(texts.size() - 1).y;
        if (isHaveArrowContinue) {
            height = lastTextY - y + size + (textPadding * 6);
        } else {
            height = lastTextY - y + size + (textPadding * 3);
        }

        frameWidth = width + (textPadding*6);

        if (isHaveFrame){
            if (frameType == TextBoxBuilder.FRAME_TYPE_IMAGE) {
                frame = new Rectangle("frame", x, y, Entity.TYPE_OTHER, frameWidth, height, -1, new Color(0.7f, 0.7f, 0.7f, 1.0f));
                        //new Image("frame", x, y, frameWidth, height, Texture.TEXTURE_TITTLE, 0f, 1f, 0f, 550f / 1024f);
            } else if (frameType == TextBoxBuilder.FRAME_TYPE_SOLID) {
                frame = new Rectangle("frame", x, y, Entity.TYPE_OTHER, frameWidth, height, -1, new Color(0.7f, 0.7f, 0.7f, 1.0f));
            }
            addChild(frame);
        }
        
        if (isHaveArrowContinue){
            arrowContinuar = new Button("arrowContinuar", x + width - size*0.5f, lastTextY - textPadding, size, size, Texture.TEXTURES, 3f,
                    TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                    TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_PRESS_ID));
            arrowContinuar.setOnPress(new Button.OnPress() {
                @Override
                public void onPress() {
                    //TODO tirar a arrowcontinue???????????
                }
            });
            addChild(arrowContinuar);
        } else {
            if (getListener() == null) {
                setListener(new InteractionListener("listenerTextBox" + this.name, x, y, frameWidth, height, 5000, this,
                        new InteractionListener.PressListener() {
                            @Override
                            public void onPress() {
                                Log.e("textbox", "onPress textBox");
                                if (onPress != null) {
                                    onPress.onPress();
                                }
                            }

                            @Override
                            public void onUnpress() {
                            }
                        }
                ));
            } else {
                getListener().setPositionAndSize(x, y, frameWidth, height);
            }
        }
        
        if (isHaveArrow){
            appendArrow(arrowX, arrowY);
        }

        if (isHaveMiniArrow){
            appendMiniArrow(arrowX, arrowY);
        }
    }

    public void setOnPress(OnPress _onPress){
        onPress = _onPress;
    }

    public interface OnPress{
        void onPress();
    }

    public void setPositionY(float y) {
        this.y = y;
        float textPadding = size * padding;
        float textY = this.y + (textPadding * 4);
        for (int i = 0; i < this.texts.size(); i++){
            this.texts.get(i).y = textY;
            textY += size + textPadding;
        }

        if (isHaveFrame){
            frame.y = y;
        }

        if (isHaveArrowContinue){
            arrowContinuar.y = textY - textPadding;
        } else {
            getListener().y = y;
        }

        if (isHaveArrow){
            appendArrow(arrowX, arrowY);
        }
    }


    public void appendMiniArrow(float arrowX, float arrowY){
        isHaveMiniArrow = true;
        float arrowSize = size * 2f;
        miniArrow = new Image("miniArrow", arrowX - arrowSize, arrowY, arrowSize, arrowSize, Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_ID));
    }
    
    
    public void animateMiniArrow(float initialTranslateX, float difference){
        if (miniArrow != null){
            final Animation a2 = Utils.createAnimation4v(miniArrow, "animArrowTX", "translateX",
                    (int)(6000*Utils.getRandonFloat(0.7f, 1.3f)), 0f, 0f, 0.3f,
                    -difference/(1+Utils.getRandonFloat(1f, 2f)), 0.7f, difference/(1+Utils.getRandonFloat(1f, 2f)), 1f, 0f, true, true);
            final Animation a3 = Utils.createAnimation4v(miniArrow, "animArrowTY", "translateY",
                    (int)(5000*Utils.getRandonFloat(0.7f, 1.3f)), 0f,0f, 0.2f,
                    difference/(1+Utils.getRandonFloat(1f, 2f)), 0.7f,-difference/(1+Utils.getRandonFloat(1f, 2f)), 1f,0, true, true);
            Animation anim = Utils.createSimpleAnimation(miniArrow, "translateX", "translateX", 800, -initialTranslateX, 0f, new Animation.AnimationListener() {
                        @Override
                        public void onAnimationEnd() {
                            a2.start();
                            a3.start();
                        }
                    });
            anim.start();
            Utils.createSimpleAnimation(miniArrow, "translateY", "translateY", 800, initialTranslateX/3f, 0f).start();


        }
    }


    public void appendArrow(float arrowX, float arrowY){
        isHaveArrow = true;
        float initialX;
        float initialY;
        //Log.e("textbox appendArrow", " " + frame.x + " " + frame.y + " " + frame.width + " " + frame.height);
        if (arrowY > (y + height)){
            initialY = y + height;
            initialX = x + (frameWidth/2f);
        } else if (arrowY < frame.y){
            initialY = y;
            initialX = x + (frameWidth/2f);
        } else {
            initialY = y + (height/2f);
            if (arrowX < x){
                initialX = x;
            } else if (arrowX > (x+frameWidth)){
                initialX = x + frameWidth;
            } else {
                initialX = x;
            }
        }
        //Log.e("textbox appendArrow", " initialX " + initialX + " initialY " + initialY);
        arrow = new Line("line", initialX, initialY, arrowX, arrowY, textColor);
        addChild(arrow);
    }

    public void render(float[] matrixView, float[] matrixProjection){
        if (isHaveArrow && arrow != null){
            arrow.prepareRender(matrixView, matrixProjection);
        }

        if (isHaveMiniArrow && miniArrow != null){
            miniArrow.prepareRender(matrixView, matrixProjection);
        }

        if (isHaveFrame){
            frame.prepareRender(matrixView, matrixProjection);
        }

        if (isHaveArrowContinue){
            arrowContinuar.alpha = alpha * 0.6f;
            arrowContinuar.prepareRender(matrixView, matrixProjection);
        }
        
        for (int i = 0; i < this.texts.size();i++){
            this.texts.get(i).alpha = alpha;
            this.texts.get(i).prepareRender(matrixView, matrixProjection);
        }
    }
}
