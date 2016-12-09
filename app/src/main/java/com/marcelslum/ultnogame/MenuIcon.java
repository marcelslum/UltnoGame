package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/12/2016.
 */

public class MenuIcon extends Entity{

    ArrayList<Button> icons;
    ArrayList<Text> texts;
    Texture iconsTexture;
    float size;
    private static final String TAG = "MenuIcon";


    public MenuIcon(String name, float x, float y, float size) {
        super(name, x, y);
        icons = new ArrayList<>();
        texts = new ArrayList<>();
        this.size = size;
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){
        //Log.e("menu", "render menu icon");
        for (int i = 0; i < icons.size();i++){
            icons.get(i).render(matrixView, matrixProjection);
        }
    }

    public void blockAllIcons(){
        for (int i = 0; i < icons.size();i++){
            icons.get(i).block();
        }
    }

    public void unblockAllIcons(){
        for (int i = 0; i < icons.size();i++){
            icons.get(i).unblock();
        }
    }

    @Override
    public void display() {
        super.display();
        for (int i = 0; i < icons.size();i++){
            icons.get(i).display();
        }
    }

    @Override
    public void clearDisplay() {
        super.clearDisplay();
        for (int i = 0; i < icons.size();i++){
            icons.get(i).clearDisplay();
        }
    }


    public void addOption(int id, int textureUnit, int textureMap, Animation.AnimationListener onSelect){

        float positionX = (size * 0.1f) + (icons.size() * size * 1.1f);
        Button button = new Button(Integer.toString(id), positionX, y, size, size, textureUnit, 1, Button.BUTTON_TYPE_256);
        button.setTextureMap(textureMap);
        button.textureMapUnpressed = textureMap;
        button.textureMapPressed = textureMap;
        final Button innerButton = button;
        final MenuIcon innerMenuIcon = this;
        final Animation.AnimationListener innerOnSelect = onSelect;
        button.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                Log.e(TAG, "pressionado botão do menu de mundo");
                innerMenuIcon.blockAllIcons();
                ArrayList<float[]> valuesAnimation = new ArrayList<>();
                valuesAnimation.add(new float[]{0f,1f});
                valuesAnimation.add(new float[]{0.07f,0.82f});
                valuesAnimation.add(new float[]{1f,1f});

                Animation animScaleX = new Animation(innerButton,
                        "encolherX", "scaleX", 400, valuesAnimation, false, true);
                animScaleX.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        innerMenuIcon.clearDisplay();
                    }
                });
                animScaleX.start();

                Animation animScaleY = new Animation(innerButton,
                        "encolherY", "scaleY", 400, valuesAnimation, false, true);
                animScaleY.setAnimationListener(innerOnSelect);
                animScaleY.start();

                float translateSize = size * 0.09f;
                Utils.createAnimation3v(innerButton, "x", "translateX", 400, 0f, 0f, 0.07f, translateSize, 1f, 0f, false, true).start();
                Utils.createAnimation3v(innerButton, "y", "translateY", 400, 0f, 0f, 0.07f, translateSize, 1f, 0f, false, true).start();

            }
        });

        button.setMoveListener(new InteractionListener.MoveListener() {
            @Override
            public void onMoveDown() {
                Log.e(TAG, "onMoveDown");
            }

            @Override
            public void onMove(TouchEvent touch, long startTime) {
                Log.e(TAG, "onMove");
                innerMenuIcon.move(touch.x - touch.previousX);
            }

            @Override
            public void onMoveUp(TouchEvent touch, long startTime) {
                Log.e(TAG, "onMoveUp");
            }
        });

        icons.add(button);
        addChild(button);

    }

    private void move(float iconTranslateX) {
        Log.e(TAG, "movendo "+ iconTranslateX);

        float padd = size * 0.1f;

        Button lastIcon = icons.get(icons.size()-1);
        if (lastIcon.positionX + size + iconTranslateX < Game.resolutionX - padd){
            iconTranslateX = (Game.resolutionX - padd) - (lastIcon.positionX + size);
            Log.e(TAG, "ultimo icone na borda - iconTranslateX "+iconTranslateX);
        }



        for (int i = 0; i < icons.size(); i++){
            Button icon = icons.get(i);
            icon.translate(iconTranslateX, 0f);



            if (i == 0){
                if (icon.positionX + iconTranslateX > padd){
                    icon.translate(padd - icon.positionX, 0f);
                    Log.e(TAG, "primeiro icone maior que padd");
                    Log.e(TAG, "icon.positionX "+ icon.positionX);
                    Log.e(TAG, "iconTranslateX "+ iconTranslateX);
                    Log.e(TAG, "padd "+ padd);
                    return;
                }
            }
        }
    }

    @Override
    public void block() {
        super.block();
        blockAllIcons();
    }

    @Override
    public void unblock() {
        super.unblock();
        unblockAllIcons();
    }
}
