package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 04/08/2016.
 */
public class Selector extends Entity{
    public float maxWidth;
    public float mainTextWidth;
    Menu menuRelated;
    String[] values;
    int selectedValue;
    float size;
    String text;
    public Text [] textsObjects;
    public Text mainTextObject;
    Font font;
    Button arrowUp;
    Button arrowDown;
    Button arrowBack;
    private OnChange onChange;
    private OnConclude onConclude;


    Selector(String name, float x, float y, float size, String text, String [] values, Font font){
        super(name, x, y, Entity.TYPE_SELECTOR);
        this.size = size;
        this.text = text;
        this.values = values;
        this.font = font;

        isBlocked = true;
        isVisible = false;

        textsObjects = new Text [values.length];
        selectedValue = 0;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;

        maxWidth = 0f;

        if (!text.equals("")){
            mainTextObject = new Text("selector"+text+"Text", x, y, size, text, this.font);
            mainTextWidth = (mainTextObject.calculateWidth()) + (size*0.75f);
            addChild(mainTextObject);
        } else {
            mainTextWidth = 0f;
        }

        for (int i = 0; i < values.length; i++){
            textsObjects[i] = new Text("selector"+values[i]+"Text", 0f, y, size, values[i], this.font);
            float width = textsObjects[i].calculateWidth();
            textsObjects[i].setX(mainTextWidth + x - (width/2));
            if (width > maxWidth) maxWidth = width;
            addChild(textsObjects[i]);
        }

        float buttonSize = size*0.90f;
        final Selector innerSelector = this;

        arrowUp = new Button("arrowUp", mainTextWidth + x - (buttonSize/2), y - (buttonSize), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_UP_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_UP_ID));
        arrowUp.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    Game.vibrate(Game.VIBRATE_SMALL);
                    innerSelector.levelUp();
                }
            }
        });
        arrowUp.setPersistent(50);
        addChild(arrowUp);

        arrowDown = new Button("arrowDown", mainTextWidth + x -(buttonSize/2), y + size + (buttonSize*0.6f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_DOWN_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_DOWN_PRESS_ID));
        arrowDown.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    Game.vibrate(Game.VIBRATE_SMALL);
                    innerSelector.levelDown();
                }
            }
        });
        arrowDown.setPersistent(50);
        addChild(arrowDown);

        float arrowBackX;
        if (text.equals("")) {
            arrowBackX = x - (buttonSize * 1.5f) - (maxWidth/2);
        } else {
            arrowBackX = x - (buttonSize * 1.5f);
        }

        arrowBack = new Button("arrowBack", arrowBackX, y + (buttonSize * 0.4f), buttonSize, buttonSize, Texture.TEXTURES, 1.2f,
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID),
                TextureData.getTextureDataById(TextureData.TEXTURE_ARROW_LEFT_ID));
        addChild(arrowBack);


        arrowBack.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    Game.vibrate(Game.VIBRATE_SMALL);
                    innerSelector.backToMenu();
                    if (onConclude != null){
                        onConclude.onConclude();
                    }
                }
            }
        });
    }

    public void setOnChange(OnChange onChange){
        this.onChange = onChange;
    }

    interface OnChange{
        void onChange();
    }

    public void setOnConclude(OnConclude onConclude){
        this.onConclude = onConclude;
    }

    interface OnConclude{
        void onConclude();
    }

    public void setSelectedValue(int selectedValue){
        this.selectedValue = selectedValue;
    }

    public void setSelectedByString(String selectedValue){
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i].equals(selectedValue)){
                setSelectedValue(i);
                return;
            }
        }
    }

    public void levelDown() {
        if (this.selectedValue != 0){
            Game.sound.playMenuSmall();

            //Sound.playSoundPool(Sound.soundMenuSelectSmall, 1, 1, 0);
            this.selectedValue -=1;

            this.verifyOnChangeComplete();
        }
    }

    public void levelUp(){
        if (this.selectedValue < (this.values.length-1)){
            //Sound.playSoundPool(Sound.soundMenuSelectSmall, 1, 1, 0);
            Game.sound.playMenuSmall();
            this.selectedValue +=1;
            this.verifyOnChangeComplete();
        }
    }

    public void verifyOnChangeComplete(){

        if (this.onChange != null){
            onChange.onChange();
        }
    }

    @Override
    public void verifyListener() {
        super.verifyListener();
        if (!isBlocked) {
            arrowDown.verifyListener();
            arrowUp.verifyListener();
            arrowBack.verifyListener();
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){

        if (!isVisible)
            return;

        if (mainTextObject != null){
            mainTextObject.alpha = alpha;
            mainTextObject.render(matrixView, matrixProjection);
        }

        //Log.e(TAG, "selectedValue "+selectedValue);
        //Log.e(TAG, "textsObjects.length "+textsObjects.length);

        //for (int i = 0; i < textsObjects.length; i++) {
        //    //Log.e(TAG, "texto "+i + ": "+textsObjects[i].text);
        //}

        textsObjects[selectedValue].alpha = alpha;
        textsObjects[selectedValue].render(matrixView, matrixProjection);


        if (selectedValue != (this.values.length-1)){
            arrowUp.alpha = alpha;
            arrowUp.render(matrixView, matrixProjection);
        }
        if (selectedValue != 0){
            arrowDown.alpha = alpha;
            arrowDown.render(matrixView, matrixProjection);
        }

        arrowBack.alpha = alpha;
        arrowBack.render(matrixView, matrixProjection);
    }

    public void fromMenu(Menu menu){

        menuRelated = menu;
        menu.block();
        alpha = 0f;
        isVisible = true;

        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0f,1f});
        valuesAnimation.add(new float[]{1f,0.15f});
        Animation reduceAlphaAnim = new Animation(menuRelated, "reduceAlphaAnim", "alpha", 500, valuesAnimation, false, true);
        reduceAlphaAnim.start();

        ArrayList<float[]> valuesAnimation2 = new ArrayList<>();
        valuesAnimation2.add(new float[]{0f,0.15f});
        valuesAnimation2.add(new float[]{1f,1f});
        Animation increaseAlphaAnim = new Animation(this, "increaseAlphaAnim", "alpha", 500, valuesAnimation2, false, true);
        final Selector innerSelector = this;
        increaseAlphaAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerSelector.isBlocked = false;
            }
        });
        increaseAlphaAnim.start();
    }

    public void setNotVisible(){
        isVisible = false;
        isBlocked = true;
    }

    public void backToMenu(){

        //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
        isBlocked = true;
        Game.sound.playPlayMenuBig();

        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0f,0.3f});
        valuesAnimation.add(new float[]{1f,1f});
        Animation increaseAlphaAnim = new Animation(menuRelated, "increaseAlphaAnim", "alpha", 500, valuesAnimation, false, true);
        increaseAlphaAnim.start();

        ArrayList<float[]> valuesAnimation2 = new ArrayList<>();
        valuesAnimation2.add(new float[]{0f,1f});
        valuesAnimation2.add(new float[]{1f,0f});
        Animation reduceAlphaAnim = new Animation(this, "reduceAlphaAnim", "alpha", 500, valuesAnimation2, false, true);
        final Menu innerMenu = menuRelated;
        final Selector innerSelector2 = this;
        reduceAlphaAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerMenu.unblock();
                innerSelector2.isVisible = false;
            }
        });
        reduceAlphaAnim.start();
    }
}
