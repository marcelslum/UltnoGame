package ultno.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 04/08/2016.
 */
public class Selector extends Entity{
    public float maxWidth;
    public float mainTextWidth;
    Menu menuRelated;
    String[] values;
    int selectedValue;
    float size;
    String text;
    SelectorListener myListener;
    public Audio audioSmall;
    public Audio audioLarge;
    public Text [] textsObjects;
    public Text mainTextObject;
    Font font;
    Button arrowUp;
    Button arrowDown;
    Button arrowBack;


    Selector(String name, Game game, float x, float y, float size, String text, String [] values, Font font){
        super(name, game, x, y);
        this.size = size;
        this.text = text;
        this.values = values;
        this.font = font;

        textsObjects = new Text [values.length];

        this.maxWidth = 0f;

        if (text != ""){
            mainTextObject = new Text("selector"+text+"Text", game, this.x, this.y, this.size, text, this.font);
            mainTextWidth = (mainTextObject.calculateWidth()) + (size*0.75f);
        } else {
            mainTextWidth = 0f;
        }

        for (int i = 0; i < values.length; i++){
            textsObjects[i] = new Text("selector"+values[i]+"Text", game, 0f, this.y, this.size, values[i], this.font);
            float width = textsObjects[i].calculateWidth();
            textsObjects[i].setX(mainTextWidth + x - (width/2));
            if (width > maxWidth) maxWidth = width;
        }

        float buttonSize = size*0.75f;
        final Selector innerSelector = this;

        arrowUp = new Button("arrowUp", this.game, mainTextWidth + x - (buttonSize/2), y -(buttonSize*1.1f), buttonSize, buttonSize);
        arrowUp.setTextureMap(16);
        arrowUp.textureMapUnpressed = 16;
        arrowUp.textureMapPressed = 16;

        InteractionListener newListener = new InteractionListener(name,
                mainTextWidth + x - (buttonSize/2),
                y -(buttonSize*1.1f),
                buttonSize,
                buttonSize,
                500, this, game);

        newListener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    innerSelector.levelUp();
                }
            }
            @Override
            public void onUnpress() {
            }
        });
        this.game.addInteracionListener(newListener);

        arrowDown = new Button("arrowDown", this.game, mainTextWidth + x -(buttonSize/2), y + size + (buttonSize*0.2f), buttonSize, buttonSize);
        arrowDown.setTextureMap(15);
        arrowDown.textureMapUnpressed = 15;
        arrowDown.textureMapPressed = 15;

        InteractionListener newListener2 = new InteractionListener(name,
                mainTextWidth + x - (buttonSize/2),
                y + size + (buttonSize*0.2f),
                buttonSize,
                buttonSize,
                500, this, game);

        newListener2.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    innerSelector.levelDown();
                }
            }
            @Override
            public void onUnpress() {
            }
        });
        this.game.addInteracionListener(newListener2);

        float arrowBackX;
        if (text != "") {
            arrowBackX = x - (buttonSize * 1.5f) - (maxWidth/2);
        } else {
            arrowBackX = x - (buttonSize * 1.5f);
        }

        arrowBack = new Button("arrowBack", this.game, arrowBackX, y + (((size*1.1f)- buttonSize) / 2), buttonSize, buttonSize);
        arrowBack.setTextureMap(13);
        arrowBack.textureMapUnpressed = 13;
        arrowBack.textureMapPressed = 13;

        InteractionListener newListener3 = new InteractionListener(name,
                arrowBackX,
                y + (((size*1.1f)- buttonSize) / 2),
                buttonSize,
                buttonSize,
                500, this, game);

        newListener3.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (!innerSelector.isBlocked){
                    innerSelector.backToMenu();
                }
            }
            @Override
            public void onUnpress() {
            }
        });
        this.game.addInteracionListener(newListener3);
        
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

    @Override
    public void render(float[] matrixView, float[] matrixProjection){
        //Log.e("menu", "render menu");

        if (this.mainTextObject != null){
            this.mainTextObject.render(matrixView, matrixProjection);
        }

        this.textsObjects[0].render(matrixView, matrixProjection);

        this.arrowDown.render(matrixView, matrixProjection);
        this.arrowUp.render(matrixView, matrixProjection);
        this.arrowBack.render(matrixView, matrixProjection);
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
