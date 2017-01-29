package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class MessageStar extends Entity {

    static MessageStar messageStars;


    ArrayList<Image> stars;
    float size;
    boolean isShowing;
    boolean newShowing;
    int nextTotalStars;
    int nextNewStars;


    public MessageStar(String name, float size, float x, float y) {
        super(name, x, y);
        this.size = size;
        stars = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Image star = new Image("starMessageStar"+i, x,
                    y + ((4 - i) * size * 1.2f),
                    size, size, Texture.TEXTURE_BUTTONS_BALLS_STARS,
                    (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (128f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);
            stars.add(star);
            addChild(star);
        }
        isVisible = false;
        isShowing = false;
        newShowing = false;
    }


    public void showAndGoAllGray(int totalStars){
        clearAnimations();
        display();
        for (int i = 0; i < 5; i++){
            stars.get(i).clearAnimations();
        }

        for (int i = 0; i < totalStars; i++){
            stars.get(i).setUvData((0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f);
        }


        for (int i = 0; i < 5; i++) {
            final Image star = stars.get(i);

            Animation ab;
            if (i < totalStars) {
                ab = Utils.createAnimation4v(star, "translateX", "translateX", 750, 0f, Game.resolutionX * 0.5f, 0.33f + (1 * 0.02f), 0f, 0.66f, 0f, 1f, size * 0.5f, false, true);

                final Animation a2 = Utils.createAnimation2v(star, "scaleX2", "scaleX", 250, 0f, 0f, 1f, 1f, false, true);
                final Animation ab2 = Utils.createAnimation2v(star, "translateX2", "translateX", 250, 0f, size * 0.5f, 1f, 0f, false, true);

                Animation a = Utils.createAnimation4v(star, "scaleX", "scaleX", 750, 0f, 1f, 0.33f, 1f, 0.66f, 1f, 1f, 0f, false, true);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        a2.start();
                        ab2.start();
                        star.setUvData((0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (128f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);
                    }
                });
                a.start();

            } else {
                ab = Utils.createAnimation4v(star, "translateX", "translateX", 750, 0f, Game.resolutionX * 0.5f, 0.33f + (1 * 0.02f), 0f, 0.66f, 0f, 1f, 0f, false, true);
            }
            ab.start();
        }
    }


    public void show(int totalStars, int newStars, boolean stay){


        Log.e(TAG, "show Message Stars");

        if (!isShowing){
            isShowing = true;
            Sound.play(Sound.soundSuccess1, 0.5f, 0.5f, 0);
        } else {
            newShowing = true;
            nextTotalStars = totalStars;
            nextNewStars = newStars;
            return;
        }

        Log.e(TAG, "showing");

        display();

        clearAnimations();

        for (int i = 0; i < 5; i++){
            stars.get(i).clearAnimations();
            stars.get(i).animTranslateX = Game.resolutionX;
        }

        for (int i = 0; i < totalStars; i++){
            stars.get(i).setUvData((0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f);
        }


        if (newStars > 0) {
            // adiciona animação na primeira estrela
            Animation animStars = Utils.createAnimation3v(stars.get(totalStars - 1 - 0), "alpha", "alpha", 500, 0f, 1f, 0.5f, 0.6f, 1f, 1f, true, false);
            // adiciona animação às outras estrelas, por isso começa no i=1
            for (int i = 1; i < newStars; i++) {
                animStars.addAttachedEntities(stars.get(totalStars - 1 - i));
            }
            animStars.start();
        }

        final MessageStar ms = this;
        final int innerNextTotalStars = nextTotalStars;
        final int innerNextNewStars = nextNewStars;

        if (stay){

            Sound.play(Sound.soundTextBoxAppear, 0.5f, 0.5f, 0);


            Utils.createAnimation4v(stars.get(0), "translateX", "translateX", 2000, 0f, Game.resolutionX, 0.2f, 0f, 0.8f, 0f, 0f, 0f, false, true).start();

            isShowing = false;

            Utils.createAnimation4v(stars.get(1), "translateX1", "translateX", 2000, 0f,
                    Game.resolutionX, 0.2f + (1 * 0.02f), 0f,  0.8f + (1 * 0.01f),0f,  1f, 0, false, true).start();
            Utils.createAnimation4v(stars.get(2), "translateX2", "translateX", 2000, 0f,
                    Game.resolutionX, 0.2f + (2 * 0.03f), 0f,  0.8f + (2 * 0.01f),0f,  1f, 0, false, true).start();
            Utils.createAnimation4v(stars.get(3), "translateX3", "translateX", 2000, 0f,
                    Game.resolutionX, 0.2f + (3 * 0.04f), 0f,  0.8f + (3 * 0.01f),0f,  1f, 0, false, true).start();
            Utils.createAnimation4v(stars.get(4), "translateX4", "translateX", 2000, 0f,
                    Game.resolutionX, 0.2f + (4 * 0.05f), 0f,  0.8f + (4 * 0.01f),0f,  1f, 0, false, true).start();


        } else {

            Animation anim = Utils.createAnimation4v(stars.get(0), "translateX", "translateX", 2000, 0f, Game.resolutionX * 0.5f, 0.2f, 0f, 0.8f, 0f, 1f, Game.resolutionX, false, true);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    if (ms.newShowing) {
                        ms.newShowing = false;
                        ms.show(innerNextTotalStars, innerNextNewStars, false);
                    } else {
                        ms.isShowing = false;
                        clearDisplay();
                    }
                }
            });
            anim.start();

            Utils.createAnimation4v(stars.get(1), "translateX1", "translateX", 2000, 0f,
                    Game.resolutionX * 0.5f, 0.2f + (1 * 0.02f), 0f,  0.8f + (1 * 0.01f),0f,  1f, Game.resolutionX, false, true).start();
            Utils.createAnimation4v(stars.get(2), "translateX2", "translateX", 2000, 0f,
                    Game.resolutionX * 0.5f, 0.2f + (2 * 0.02f), 0f,  0.8f + (2 * 0.01f),0f,  1f, Game.resolutionX, false, true).start();
            Utils.createAnimation4v(stars.get(3), "translateX3", "translateX", 2000, 0f,
                    Game.resolutionX * 0.5f, 0.2f + (3 * 0.02f), 0f,  0.8f + (3 * 0.01f),0f,  1f, Game.resolutionX, false, true).start();
            Utils.createAnimation4v(stars.get(4), "translateX4", "translateX", 2000, 0f,
                    Game.resolutionX * 0.5f, 0.2f + (4 * 0.02f), 0f,  0.8f + (4 * 0.01f),0f,  1f, Game.resolutionX, false, true).start();
        }
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        for (int i = 0; i < 5; i++){
            stars.get(i).render(matrixView, matrixProjection);
        }
    }

    public void reset() {
        for (int i = 0; i < 5; i++){
            stars.get(i).setUvData((0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (128f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);
        }
    }

    public static void initMessageStars() {
        float messageStarsSize = Game.gameAreaResolutionY*0.05f;
        MessageStar.messageStars = new MessageStar("messageStars", messageStarsSize, Game.resolutionX - (messageStarsSize * 1.4f), Game.resolutionX * 0.05f);
    }
}