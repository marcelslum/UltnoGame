package ultno.marcelslum.ultnogame;


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

    public Tutorial(){

    }

    public void show() {
        this.textBox.isMovable = false;
        this.isBlocked = true;
        this.textBox.alpha = 0;

        if (this.onShowBeforeAnim != null) {
            this.onShowBeforeAnim.onShowBeforeAnim();

            this.textBox.display();

            ArrayList<float[]> valuesAnimation1 = new ArrayList<float[]>();
            valuesAnimation1.add(new float[]{0, -500});
            valuesAnimation1.add(new float[]{1, 0});
            Animation animationTextBoxTranslate = new Animation(this.textBox, "textBoxTranslateX1", "'translateX'", 300, valuesAnimation1, false, true);
            final Tutorial self = this;
            animationTextBoxTranslate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    self.isBlocked = false;
                    self.textBox.displayArrow = true;
                    if (self.onShowAfterAnim != null) {
                        self.onShowAfterAnim.onShowAfterAnim();
                    }
                }
            });
            animationTextBoxTranslate.start();

            ArrayList<float[]> valuesAnimation2 = new ArrayList<float[]>();
            valuesAnimation2.add(new float[]{0, 0});
            valuesAnimation2.add(new float[]{1, 1});
            Animation animationTextBoxAlpha = new Animation(this.textBox, "alpha", "'alpha'", 300, valuesAnimation2, false, true);
            animationTextBoxAlpha.start();
        }
    }

    public void unshow(){
        this.isBlocked = true;
        if (this.onUnshowBeforeAnim != null)
            this.onUnshowBeforeAnim.onUnshowBeforeAnim();

        ArrayList<float[]> valuesAnimation = new ArrayList<float[]>();
        valuesAnimation.add(new float[]{0, 1});
        valuesAnimation.add(new float[]{1, 0});
        Animation animationTextBoxAlpha = new Animation(this.textBox, "alpha", "'alpha'", 50, valuesAnimation, false, true);
        final Tutorial self = this;
            animationTextBoxAlpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    self.textBox.clearDisplay();
                    self.isBlocked = false;
                    if (self.onUnshowAfterAnim != null){
                        self.onUnshowAfterAnim.onUnshowAfterAnim();
                    }
                    if (self.onUnshowAfterAnim2 != null){
                        self.onUnshowAfterAnim2.onUnshowAfterAnim2();
                    }
                }
            });
        animationTextBoxAlpha.start();
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
