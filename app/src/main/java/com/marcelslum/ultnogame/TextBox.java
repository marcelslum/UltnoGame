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
    public boolean isHaveFrame = true;
    public boolean isHaveArrowContinue = true;
    public Button arrowContinuar;
    public Color textColor = new Color(0f, 0f, 0f, 0.9f);
    public Line arrow;
    public float arrowX;
    public float arrowY;
    public Entity frame;
    public float frameWidth;
    public int frameType;
    private OnPress onPress;

    public TextBox(TextBoxBuilder builder) {
        super(builder.name, builder.x, builder.y);
        width = builder.width;
        size = builder.size;
        isHaveArrow = builder.isHaveArrow;
        isHaveFrame = builder.isHaveFrame;
        isHaveArrowContinue = builder.isHaveArrowContinue;
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
        
        Text textForMeasure = new Text("text", 0f, 0f, size, text, Game.font, textColor);
        float widthOfText = textForMeasure.calculateWidth();

        // subdivide o texto em partes, conforme width máximo
        if (widthOfText > this.width) {
            String [] splitedString = text.split(" ");
            String stringToTest = splitedString[0];

            int elementToAdd = 1;
            Text lastText = new Text("text", 0f, 0f, size, ".", Game.font, textColor);

            int limite = 100;
            int contador = 0;

            do {
                do {
                    contador += 1;
                    textForMeasure = new Text("text"+contador, 0f, 0f, size, stringToTest, Game.font, textColor);
                    widthOfText = textForMeasure.calculateWidth();
                    if (widthOfText > (width*0.95f)) {
                        elementToAdd -= 1;
                        stringToTest = splitedString[elementToAdd];
                        elementToAdd += 1;
                        break;
                    }
                    lastText = textForMeasure;
                    if (elementToAdd > (splitedString.length-1)){
                        elementToAdd += 1;
                        break;
                    }
                    stringToTest = stringToTest + " " + splitedString[elementToAdd];
                    elementToAdd += 1;
                } while (widthOfText < (width*0.95f) && (splitedString.length+1) > elementToAdd && contador < limite);
                //Log.e("textBox", "adicionando texto: "+lastText.text);
                texts.add(lastText);
                //Log.e("textBox", "elementToAdd "+elementToAdd);

            } while ((splitedString.length+1) > elementToAdd && contador < limite);
        } else {
            texts.add(textForMeasure);
        }

        float textPadding = size * padding;
        float textY = this.y + (textPadding * 4);
        float textX = this.x + (textPadding * 4);

        for (int i = 0; i < this.texts.size(); i++){
            this.texts.get(i).x = textX;
            this.texts.get(i).y = textY;
            textY += size + textPadding;
            addChild(this.texts.get(i));
        }

        if (isHaveArrowContinue) {
            height = textY - y + (textPadding * 6);
        } else {
            height = textY - y + (textPadding * 3);
        }

        frameWidth = width + (textPadding*6);

        if (isHaveFrame){

            if (frameType == TextBoxBuilder.FRAME_TYPE_IMAGE) {
                frame = new Image("frame", x, y, frameWidth, height, Texture.TEXTURE_TITTLE, 0f, 1f, 0f, 550f / 1024f);
            } else if (frameType == TextBoxBuilder.FRAME_TYPE_SOLID) {
                frame = new Rectangle("frame", x, y, frameWidth, height, -1, new Color(0.7f, 0.7f, 0.7f, 1.0f));
            }
            addChild(frame);
        }
        
        if (isHaveArrowContinue){
            arrowContinuar = new Button("arrowContinuar", x + width - size*0.5f, textY - textPadding, size, size, Texture.TEXTURE_BUTTONS_AND_BALLS, 3f);
            arrowContinuar.setTextureMap(14);
            arrowContinuar.textureMapUnpressed = 14;
            arrowContinuar.textureMapPressed = 6;
            arrowContinuar.setOnPress(new Button.OnPress() {
                @Override
                public void onPress() {
                    Levels.levelObject.nextTutorial();
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
