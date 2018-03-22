package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 10/12/2016.
 */

public class LevelGoalsPanel extends Entity{

    public float size;
    ArrayList<Line> lines;
    Color textColor = Color.pretoCheio;
    float maxTextWidth;
    boolean gray;

    LevelGoalsPanel(String name, float x, float y, float size, float maxTextWidth) {
        super(name, x, y, Entity.TYPE_PANEL);
        this.size = size;
        lines = new ArrayList<>();
        this.maxTextWidth = maxTextWidth;
        gray = false;
    }

    public void addLine(int quantityOfStars, boolean shineStars, String text){

        //Log.e("LevelGoalsPanel", "adicionando nova linha "+ text);

        float textY = y;
        if (lines.size()> 0) {
            textY = lines.get(lines.size()-1).getBottomY();
        }
        textY += size*0.6f;
        Line l = new Line(lines.size(), quantityOfStars, text, x, textY, textColor, size, maxTextWidth, shineStars);
        lines.add(l);
        for (int i = 0; i < l.texts.size(); i++){
            addChild(l.texts.get(i));
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection) {

        for (int l = 0; l < lines.size(); l++) {
            for (int i = 0; i < lines.get(l).texts.size(); i++) {
                lines.get(l).texts.get(i).render(matrixView, matrixProjection);
            }
        }

        for (int l = 0; l < lines.size(); l++) {
            for (int i = 0; i < lines.get(l).stars.size(); i++) {
                lines.get(l).stars.get(i).render(matrixView, matrixProjection);
            }
        }
    }

    public void appear() {
        display();
        Game.sound.playTextBoxAppear();


        //Sound.playSoundPool(Sound.soundMenuIconDrop, 1, 1, 0);
        float firstY = lines.get(0).texts.get(0).y;
        if (firstY < y + size*0.66f) {
            float lastY = lines.get(lines.size() - 1).texts.get(lines.get(lines.size() - 1).texts.size() - 1).y;
            if (lastY - firstY < Game.gameAreaResolutionY) {
                float translateY = (Game.gameAreaResolutionY - (lastY - firstY) - y) / 4f;
                for (int l = 0; l < lines.size(); l++) {
                    for (int i = 0; i < lines.get(l).texts.size(); i++) {
                        Text t = lines.get(l).texts.get(i);
                        t.y += translateY;
                    }
                }

                for (int l = 0; l < lines.size(); l++) {
                    for (int i = 0; i < lines.get(l).stars.size(); i++) {
                        Image star = lines.get(l).stars.get(i);
                        star.y += translateY;
                    }
                }
            }
        }

        for (int l = 0; l < lines.size(); l++) {
            for (int i = 0; i < lines.get(l).texts.size(); i++) {
                Text t = lines.get(l).texts.get(i);
                t.animTranslateX = - Game.resolutionX * 2f;
                Utils.createAnimation3v(t, "translateX", "translateX", 800 + (l * 50),
                        0f, - Game.resolutionX * 2f, 0.5f, 0f, 1f, 0f, false, true).start();
            }
        }

        for (int l = 0; l < lines.size(); l++) {
            for (int i = 0; i < lines.get(l).stars.size(); i++) {
                Image star = lines.get(l).stars.get(i);
                star.animTranslateY = - Game.resolutionY * 2f;
                Utils.createAnimation3v(star, "translateY", "translateY", 800 + (l * 50),
                        0f, - Game.resolutionY * 2f, 0.5f, - Game.resolutionY * 2f, 1f, 0f, false, true).start();
            }
        }
    }

    public void appearGray() {
        gray = true;
        appear();

        for (int l = 0; l < lines.size(); l++) {
            lines.get(l).changeShineStars(false);
        }
    }

    public void shineLines(boolean playSound) {
        if (gray) {
            if (playSound) {
                Game.sound.playSuccess1();
                //Sound.playSoundPool(Sound.soundSuccess1, 0.5f, 0.5f, 0);
            }
            gray = false;
            for (int l = 0; l < lines.size(); l++) {
                if (Level.levelObject.levelGoalsObject.levelGoals.get(l).achieved) {
                   if (lines.get(l).shineStars) {
                        for (int i = 0; i < lines.get(l).stars.size(); i++) {
                            Image star = lines.get(l).stars.get(i);

                            final Animation a2 = Utils.createAnimation2v(star, "scaleX2", "scaleX", 250, 0f, 0f, 1f, 1f, false, true);
                            final Animation ab2 = Utils.createAnimation2v(star, "translateX2", "translateX", 250, 0f, size, 1f, 0f, false, true);

                            final Line innerLine = lines.get(l);

                            Animation a = Utils.createAnimation2v(star, "scaleX", "scaleX", 250, 0f, 1f, 1f, 0f, false, true);
                            Animation ab = Utils.createAnimation2v(star, "translateX", "translateX", 250, 0f, 0f, 1f, size, false, true);
                            a.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationEnd() {
                                    innerLine.changeShineStars(true);
                                    a2.start();
                                    //ab2.start();
                                }
                            });
                            a.start();
                            //ab.start();
                        }
                    }
                }
            }
        }
    }

    public void appearGrayAndShine() {
        appearGray();
        final LevelGoalsPanel inner = this;

        Utils.createSimpleAnimation(this, "Timer", "Timer", 800, 1f, 1f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                inner.shineLines(false);
            }
        }).start();
    }


    class Line {
        int quantityOfStars;
        boolean shineStars;
        ArrayList<Text> texts;
        ArrayList<Image> stars;
        float y;

        Line(int numberForName, int quantityOfStars, String text, float textX, float y, Color textColor, float size, float maxWidth, boolean shineStars) {
            this.quantityOfStars = quantityOfStars;
            this.y = y;
            this.shineStars = shineStars;
            texts = Text.splitStringAtMaxWidth("line"+String.valueOf(numberForName), text, Game.font, textColor, size, maxWidth, Text.TEXT_ALIGN_LEFT);
            Text.doLinesWithStringCollection(texts, y, size, size * 0.2f, false);

            for (int i = 0; i < texts.size(); i++) {
                texts.get(i).x = textX;
            }

            stars = new ArrayList<>();
            Image im;
            float starX = x - (size * 2f);
            for (int i = 0; i < quantityOfStars; i++) {
                if (shineStars) {
                    im = new Image("star" + i, starX, y + (size * 0.1f), size, size, Texture.TEXTURES,
                            TextureData.getTextureDataById(TextureData.TEXTURE_STAR_SHINE_ID));
                } else {
                    im = new Image("star" + i, starX, y + (size * 0.1f), size, size, Texture.TEXTURES,
                            TextureData.getTextureDataById(TextureData.TEXTURE_STAR_OFF_ID));
                }
                stars.add(im);
                addChild(im);
                starX -= size;
            }
        }

        public float getBottomY(){
            if (texts.size() > 0){
                Text lastText = texts.get(texts.size()-1);
                return lastText.y + lastText.size;
            } else {
                return texts.get(0).y + texts.get(0).size;
            }
        }

        public void changeShineStars(boolean shine) {
            for (int i = 0; i < stars.size(); i++){
                if (shine){
                    stars.get(i).updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_STAR_SHINE_ID));
                } else {
                    stars.get(i).updateTextureData(TextureData.getTextureDataById(TextureData.TEXTURE_STAR_OFF_ID));
                }
            }
        }
    }
}
