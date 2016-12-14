package com.marcelslum.ultnogame;

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
                numberOfActiveTexts += 0;
            } else {
                childToReplace = i;
                break;
            }
        }

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

        Utils.createAnimation4v(textObject, "translateX", "translateX", 2500,
                0f, Game.resolutionX, 0.1f, 0f, 0.9f, 0f, 1f, Game.resolutionX, false, true).start();
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
