package ultno.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 04/08/2016.
 */
public class Selector extends Entity{
    Menu menuRelated;
    String[] values;
    int selectedValue;
    float size;
    String text;
    SelectorListener myListener;
    public Audio audioSmall;
    public Audio audioLarge;


    Selector(String name, Game game, float x, float y, float size, String text, String [] values){
        super(name, game, x, y);
        this.size = size;
        this.text = text;
        this.values = values;

        // add arrow listeners
    }

    public void setListener(SelectorListener listener){
        this.myListener = listener;
    }

    public void fireOnChoice(){
        if (this.myListener != null){
            this.myListener.onChoice();
        }
    }

    interface SelectorListener{
        public void onChoice();
        public void onChange();
    }

    public void levelDown() {
        if (this.selectedValue != 0){
            this.audioSmall.play();
            this.selectedValue -=1;
            this.display();
            this.verifyOnChangeComplete();
        }
    }

    public void levelUp(){
        if (this.selectedValue < (this.values.length-1)){
            this.audioSmall.play();
            this.selectedValue +=1;
            this.display();
            this.verifyOnChangeComplete();
        }
    }

    public void verifyOnChangeComplete(){
        if (this.myListener != null){
            myListener.onChange();
        }
    }

    public void setAudioSmall(Audio audioSmall) {
        this.audioSmall = audioSmall;
    }

    public void setAudioLarge(Audio audioLarge) {
        this.audioLarge = audioLarge;
    }

    public void backToMenu(){
        this.isBlocked = true;
        final Selector innerSelector = this;
        ArrayList<float[]> valuesAnimationSelector = new ArrayList<float[]>();
        valuesAnimationSelector.add(new float[]{0,1});
        valuesAnimationSelector.add(new float[]{1,0});
        Animation anim = new Animation(this,"alphaBackToMenu","alpha",300,valuesAnimationSelector,false,true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerSelector.clearDisplay();
            }
        });
        anim.start();
        this.game.blockAndWaitTouchRelease();
        this.menuRelated.fromSelector(this);
    }

    public void updateListenersData(){
        for (int i = 0; i < this.listeners.size(); i++){
            InteractionListener listener = this.listeners.get(i);
            switch (listener.name) {
                case "arrowBack":
                    //var menuWidth = this.menuRelated.getSelectedItem().textWidth;
                    //listener.position.x = this.position.x - menuWidth;
                    //listener.position.y = this.position.y - this.size;
                    //listener.size.x = menuWidth + this.textWidth + (this.size * 0.7);
                    //listener.size.y = this.size;
                    break;
                case "arrowUp":
                    //UTILS.setSizeByPoints(this.arrowUp.points, listener.size);
                    //listener.size.x *= 2;
                    //listener.size.y *= 2;
                    //listener.position.x = this.arrowUp.position.x - (listener.size.x*0.3);
                    //listener.position.y = this.arrowUp.position.y - (listener.size.y*0.3);
                    break;
                case "arrowDown":
                    //UTILS.setSizeByPoints(this.arrowDown.points, listener.size);
                    //listener.size.x *= 2;
                    //listener.size.y *= 2;
                    //listener.position.x = this.arrowDown.position.x - (listener.size.x*0.5);
                    //listener.position.y = this.arrowDown.position.y - (listener.size.y*0.4);
                    break;
            }

        }
    }

}
