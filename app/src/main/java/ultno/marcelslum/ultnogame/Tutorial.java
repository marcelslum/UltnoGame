package ultno.marcelslum.ultnogame;


import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Tutorial {

    OnShowBeforeAnim onShowBeforeAnim;
    OnShowAfterAnim onShowAfterAnim;
    OnUnshowBeforeAnim onUnshowBeforeAnim;
    OnUnshowAfterAnim2 onUnshowAfterAnim2;
    OnUnshowAfterAnim onUnshowAfterAnim;
    TextBox textBox;
    boolean isBlocked;

    public Tutorial(TextBox textBox){
        this.textBox = textBox;
    }

    public void show(SoundPool soundPool, int soundId) {
        Log.e("tutorial", "show tutorial "+textBox.name);
        soundPool.play(soundId, 1, 1, 0, 0, 1);
        isBlocked = true;
        textBox.alpha = 0f;


        if (onShowBeforeAnim != null) {
            onShowBeforeAnim.onShowBeforeAnim();
        }
            textBox.display();
            
            Animation anim = Utils.createSimpleAnimation(textBox, "textBoxTranslateX1", "translateX", 300, -textBox.game.resolutionX *2, 0f);
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

        Log.e("tutorial", "unshow tutorial "+textBox.name);

        isBlocked = true;
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
        public void onShowBeforeAnim();
    }

    public interface OnShowAfterAnim{
        public void onShowAfterAnim();
    }

    public interface OnUnshowBeforeAnim{
        public void onUnshowBeforeAnim();
    }


    public interface OnUnshowAfterAnim2{
        public void onUnshowAfterAnim2();
    }

    public interface OnUnshowAfterAnim{
        public void onUnshowAfterAnim();
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

}
