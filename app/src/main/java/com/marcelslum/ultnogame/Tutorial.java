package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    public ArrayList<Frame> frames;
    int currentFrame;


    public Tutorial(){
        frames = new ArrayList<>();
    }

    public void addFrame(Image image, String text, float y, float size){
        frames.add(new Frame(image, text, y, size));

    }



    public class Frame{
        Text text;
        Image image;
        TextBox textBox;
        Frame(Image image, String text, float y, float size){
            textBox = new TextBoxBuilder("textBox")
                    .position(Game.resolutionX * 0.5f, y)
                    .width(Game.resolutionX * 0.9f)
                    .size(size)
                    .text(text)
                    .withoutArrow()
                    .isHaveFrame(false)
                    .build();

            this.image = image;
        }
    }










    public void show(int soundId) {
        Sound.play(soundId, 1f, 1f, 0);
        isBlocked = true;
        textBox.alpha = 0f;

        if (onShowBeforeAnim != null) {
            onShowBeforeAnim.onShowBeforeAnim();
        }
            textBox.display();
            
            Animation anim = Utils.createSimpleAnimation(textBox, "textBoxTranslateX1", "translateX", 300, -Game.resolutionX *2, 0f);
            final Tutorial self = this;
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    self.isBlocked = false;
                    if (self.onShowAfterAnim != null) {
                        self.onShowAfterAnim.onShowAfterAnim();
                    }
                }
            });
            anim.start();

        Utils.createSimpleAnimation(textBox, "alpha", "alpha", 300, 0f, 1f).start();
    }

    public void unshow(){

        //Log.e("tutorial", "unshow tutorial "+textBox.name);

        isBlocked = true;
        textBox.arrowContinuar.isBlocked = true;

        if (onUnshowBeforeAnim != null)
            onUnshowBeforeAnim.onUnshowBeforeAnim();

        final Tutorial self = this;
        Animation anim = Utils.createSimpleAnimation(this.textBox, "alpha", "alpha", 500, 1f, 0f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                Log.e("tutorial", "onAnimationEnd alphaUnshow ");
                self.textBox.clearDisplay();
                if (self.onUnshowAfterAnim != null){
                    self.isBlocked = true;
                    self.onUnshowAfterAnim.onUnshowAfterAnim();
                }
                if (self.onUnshowAfterAnim2 != null){
                    self.onUnshowAfterAnim2.onUnshowAfterAnim2();
                }
            }
        });
        anim.start();
    }

    public interface OnShowBeforeAnim{
        void onShowBeforeAnim();
    }

    public interface OnShowAfterAnim{
        void onShowAfterAnim();
    }

    public interface OnUnshowBeforeAnim{
        void onUnshowBeforeAnim();
    }


    public interface OnUnshowAfterAnim2{
        void onUnshowAfterAnim2();
    }

    public interface OnUnshowAfterAnim{
        void onUnshowAfterAnim();
    }


    public void setOnShowBeforeAnim(OnShowBeforeAnim v) {
        this.onShowBeforeAnim = v;
    }

    public void setOnShowAfterAnim(OnShowAfterAnim v) {
        this.onShowAfterAnim = v;
    }

    public void setOnUnshowBeforeAnim(OnUnshowBeforeAnim v) {
        this.onUnshowBeforeAnim = v;
    }

    public void setOnUnshowAfterAnim(OnUnshowAfterAnim v) {
        this.onUnshowAfterAnim = v;
    }

    public void setOnUnshowAfterAnim2(OnUnshowAfterAnim2 v) {
        this.onUnshowAfterAnim2 = v;
    }

    public static class TutorialBuilder {

        private final TextBox textBox;
        private OnShowBeforeAnim onShowBeforeAnim;
        private OnShowAfterAnim onShowAfterAnim;
        private OnUnshowBeforeAnim onUnshowBeforeAnim;
        private OnUnshowAfterAnim onUnshowAfterAnim;
        
        public TutorialBuilder(TextBox textBox) {
            this.textBox = textBox;
        }
        
        public TutorialBuilder onShowBeforeAnim(OnShowBeforeAnim onShowBeforeAnim){
            this.onShowBeforeAnim = onShowBeforeAnim;
            return this;
        }
        
        public TutorialBuilder onShowAfterAnim(OnShowAfterAnim onShowAfterAnim){
            this.onShowAfterAnim = onShowAfterAnim;
            return this;
        }
        
        public TutorialBuilder onUnshowBeforeAnim(OnUnshowBeforeAnim onUnshowBeforeAnim){
            this.onUnshowBeforeAnim = onUnshowBeforeAnim;
            return this;
        }
        
        public TutorialBuilder onUnshowAfterAnim(OnUnshowAfterAnim onUnshowAfterAnim){
            this.onUnshowAfterAnim = onUnshowAfterAnim;
            return this;
        }

        public Tutorial build(){
            return new Tutorial(this);
        }
    }
    
    
    

}
