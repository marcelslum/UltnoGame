package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class Messages extends Entity {

    ArrayList<Entity> childs2;

    public Messages() {
        super("messages", Game.resolutionX * 0.97f, Game.gameAreaResolutionY * 0.7f, Entity.TYPE_PANEL);
        isVisible = true;
        childs2 = new ArrayList<>();
    }

    public void showMessage(String messageText){
        int numberOfActiveTexts = 0;
        int childToReplace = -1;
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isVisible) {
                numberOfActiveTexts += 1;
            } else {
                childToReplace = i;
                break;
            }
        }

        //Log.e("messages", "numberOfActiveTexts "+numberOfActiveTexts);

        final Text textObject;
        final Text textObject2;
        if (childToReplace != -1){

            textObject = Game.textPool.get();
            textObject.setData("text", x, childs.get(childToReplace).y,
                    Game.gameAreaResolutionY * 0.045f, messageText, Game.font, new Color (1f, 1f, 0f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs.set(childToReplace, textObject);

            textObject2 = Game.textPool.get();
            textObject2.setData("text2", x + (Game.gameAreaResolutionY * 0.045f * 0.07f), childs.get(childToReplace).y + (Game.gameAreaResolutionY * 0.045f * 0.07f),
                    Game.gameAreaResolutionY * 0.045f, messageText, Game.font, new Color (0.6f, 0.6f, 0f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs2.set(childToReplace, textObject2);

        } else {
            textObject = Game.textPool.get();
            textObject.setData("text", x, y - (numberOfActiveTexts * Game.gameAreaResolutionY * 0.07f),
                    Game.gameAreaResolutionY * 0.045f, messageText, Game.font, new Color (1f, 1f, 0f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs.add(textObject);

            textObject2 = Game.textPool.get();
            textObject2.setData("text2", x + (Game.gameAreaResolutionY * 0.045f * 0.07f), y - (numberOfActiveTexts * Game.gameAreaResolutionY * 0.07f) + (Game.gameAreaResolutionY * 0.045f * 0.07f),
                    Game.gameAreaResolutionY * 0.045f, messageText, Game.font, new Color (0.6f, 0.6f, 0f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs2.add(textObject2);
        }

        textObject.isVisible = true;
        textObject2.isVisible = true;

        Sound.play(Sound.soundTextBoxAppear, 0.3f, 0.3f, 0);

        Animation anim1 = Utils.createAnimation3v(textObject, "translateX", "translateX", 2225,
                0f, Game.resolutionX, 0.1f, 0f, 0.9f, -Game.resolutionX * 0.05f, false, true);
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {

                Animation a2 = Utils.createAnimation2v(textObject, "translateX2", "translateX", 225,
                        0f, -Game.resolutionX * 0.05f, 1f,  Game.resolutionX, false, true);
                a2.addAttachedEntities(textObject2);
                a2.start();

                Sound.play(Sound.soundTextBoxAppear, 0.1f, 0.1f, 0);
            }
        });
        anim1.start();

        Utils.createAnimation3v(textObject2, "translateX", "translateX", 2225,
                0f, Game.resolutionX, 0.1f, 0f, 0.9f, -Game.resolutionX * 0.05f, false, true).start();

        textObject2.alpha = 1f;

        Utils.createSimpleAnimation(textObject, "alpha", "alpha", 2500, 1f, 0.6f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                textObject.isVisible = false;
                textObject2.isVisible = false;
            }
        }).start();
    }

    @Override
    public void checkTransformations(boolean updatePrevious) {
        if (childs2 != null) {
            for (int i = 0; i < childs2.size(); i++) {
                childs2.get(i).checkTransformations(updatePrevious);
            }
        }
        super.checkTransformations(updatePrevious);
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {
        if (childs2 != null) {
            for (int i = 0; i < childs2.size(); i++) {
                for (int a = 0; a < childs2.get(i).animations.size(); a++) {

                    if (childs2.get(i).animations.get(a).started) {
                        //Log.e(TAG, childs2.get(i).animations.get(a).name);
                        childs2.get(i).animations.get(a).doAnimation();
                    }
                }
            }
        }
        super.prepareRender(matrixView, matrixProjection);
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isVisible) {
                childs2.get(i).render(matrixView, matrixProjection);
                childs.get(i).render(matrixView, matrixProjection);
            }

        }
    }
}
