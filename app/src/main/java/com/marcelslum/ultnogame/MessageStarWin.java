package com.marcelslum.ultnogame;

import java.util.ArrayList;

public class MessageStarWin extends MessageStar {

    static MessageStarWin messageStarsWin;

    public MessageStarWin(String name, float size, float x, float y) {
        super(name, size, x, y);

        stars = new ArrayList<>();
        childs.clear();

        for (int i = 0; i < 5; i++){
            Image star = new Image("starMessageStar"+i,
                    x - (size * 1.2f * 2.5f) + (i * size * 1.2f),
                    y, size, size, Texture.TEXTURE_BUTTONS_BALLS_STARS,
                    (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (128f + 1.5f) / 1024f, (256f - 1.5f) / 1024f);
            stars.add(star);
            addChild(star);
        }
    }

    public void setY(float v) {
        y = v;
        for (int i = 0; i < 5; i++){
            stars.get(i).y = v;
        }
    }

    public static void initMessageStarsWin() {
        float messageStarsSize = Game.gameAreaResolutionY*0.05f;
        MessageStarWin.messageStarsWin = new MessageStarWin("messageStarsWin", messageStarsSize, Game.resolutionX * 0.5f, Game.gameAreaResolutionY*0.62f);
    }
}