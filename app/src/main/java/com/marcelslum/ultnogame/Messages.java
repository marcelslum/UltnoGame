package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class Messages extends Entity {

    public Messages() {
        super("messages", Game.resolutionX * 0.97f, Game.gameAreaResolutionY * 0.6f);
        isVisible = true;
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


        Log.e("messages", "numberOfActiveTexts "+numberOfActiveTexts);

        final Text textObject;
        if (childToReplace != -1){

            textObject = new Text("text", x, childs.get(childToReplace).y,
                    Game.gameAreaResolutionY * 0.045f, messageText, Game.font, new Color (0.3f, 0.3f, 0.3f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs.set(childToReplace, textObject);
        } else {
            textObject = new Text("text", x, y + (numberOfActiveTexts * Game.gameAreaResolutionY * 0.07f),
                    Game.gameAreaResolutionY * 0.05f, messageText, Game.font, new Color (0.3f, 0.3f, 0.3f, 1f), Text.TEXT_ALIGN_RIGHT);
            childs.add(textObject);
        }

        textObject.isVisible = true;

        Sound.play(Sound.soundTextBoxAppear, 0.3f, 0.3f, 0);

        Animation anim1 = Utils.createAnimation3v(textObject, "translateX", "translateX", 2225,
                0f, Game.resolutionX, 0.1f, 0f, 0.9f, -Game.resolutionX * 0.05f, false, true);
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                Utils.createAnimation2v(textObject, "translateX", "translateX", 225,
                        0f, -Game.resolutionX * 0.05f, 1f,  Game.resolutionX, false, true).start();
                Sound.play(Sound.soundTextBoxAppear, 0.1f, 0.1f, 0);
            }
        });
        anim1.start();

        Utils.createSimpleAnimation(textObject, "alpha", "alpha", 2500, 1f, 0.5f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                textObject.isVisible = false;
            }
        }).start();
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isVisible) {
                childs.get(i).render(matrixView, matrixProjection);
            }
        }
    }



}
