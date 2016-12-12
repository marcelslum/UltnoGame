package com.marcelslum.ultnogame;

public class MessageStar extends Entity {
    Text text;
    Image star;
    float size;
    boolean isShowing;
    boolean newShowing;


    public MessageStar(String name, float size) {
        super(name, Game.resolutionX * 0.87f, Game.resolutionX * 0.1f);
        this.size = size;
        text = new Text("text", x + (size * 1.2f), y, size, " +1", Game.font, new Color(0.3f, 0.3f, 0.3f, 1f));

        star = new Image("star", x, y, size, size, Texture.TEXTURE_BUTTONS_AND_BALLS,
                (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f, (0f + 1.5f) / 1024f, (128f - 1.5f) / 1024f);
        addChild(text);
        addChild(star);
        isVisible = false;
        isShowing = false;
        newShowing = false;
    }

    public void show(){
        if (!isShowing){
            isShowing = true;
        } else {
            newShowing = true;
            return;
        }

        display();
        star.alpha = 1f;
        text.alpha = 1f;

        final Animation b1 = Utils.createAnimation2v(star, "translateY2", "translateY", 1500, 0f, 0f, 1f, -Game.resolutionX * 0.05f, false, true);
        final Animation b2 = Utils.createAnimation2v(star, "translateX", "translateX", 1500, 0f, 0f, 1f, Game.resolutionX * 0.05f, false, true);

        final Animation b3 = Utils.createAnimation2v(text, "translateY2", "translateY", 1500, 0f, 0f, 1f, -Game.resolutionX * 0.05f, false, true);
        final Animation b4 = Utils.createAnimation2v(text, "translateX", "translateX", 1500, 0f, 0f, 1f, Game.resolutionX * 0.05f, false, true);

        final Animation b5 = Utils.createAnimation2v(star, "alpha", "alpha", 1500, 0f, 1f, 1f, 0f, false, true);
        final Animation b6 = Utils.createAnimation2v(text, "alpha", "alpha", 1500, 0f, 1f, 1f, 0f, false, true);

        final MessageStar ms = this;

        b4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                if (ms.newShowing){
                    ms.newShowing = false;
                    ms.show();
                } else {
                    ms.isShowing = false;
                    clearDisplay();
                }
            }
        });

        Animation a = Utils.createAnimation2v(star, "translateY", "translateY", 250, 0f, -Game.resolutionX * 0.2f, 1f, 0f, false, true);
        a.setAnimationListener(new Animation.AnimationListener() {

            //TODO tocar som da moeda caindo

            @Override
            public void onAnimationEnd() {
                b1.start();
                b2.start();
                b3.start();
                b4.start();
                b5.start();
                b6.start();
            }
        });
        a.start();
        Utils.createAnimation2v(text, "translateY", "translateY", 250, 0f, -Game.resolutionX * 0.2f, 1f, 0f, false, true).start();
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        text.render(matrixView, matrixProjection);
        star.render(matrixView, matrixProjection);
    }
}