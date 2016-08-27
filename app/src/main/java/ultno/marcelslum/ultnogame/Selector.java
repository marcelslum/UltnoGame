package ultno.marcelslum.ultnogame;


import android.util.Log;

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
    public Text [] textsObjects;
    public Text mainTextObject;
    Font font;
    Button arrowUp;
    Button arrowDown;
    Button arrowBack;
    private OnChange onChange;


    Selector(String name, Game game, float x, float y, float size, String text, String [] values, Font font){
        super(name, game, x, y);
        this.size = size;
        this.text = text;
        this.values = values;
        this.font = font;

        isBlocked = true;
        isVisible = false;

        textsObjects = new Text [values.length];
        selectedValue = 0;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;

        maxWidth = 0f;

        if (text != ""){
            mainTextObject = new Text("selector"+text+"Text", game, x, y, size, text, this.font);
            mainTextWidth = (mainTextObject.calculateWidth()) + (size*0.75f);
        } else {
            mainTextWidth = 0f;
        }

        for (int i = 0; i < values.length; i++){
            //Log.e("selector", " "+values[i]);
            textsObjects[i] = new Text("selector"+values[i]+"Text", game, 0f, y, size, values[i], this.font);
            float width = textsObjects[i].calculateWidth();
            textsObjects[i].setX(mainTextWidth + x - (width/2));
            if (width > maxWidth) maxWidth = width;
        }

        float buttonSize = size*0.90f;
        final Selector innerSelector = this;

        arrowUp = new Button("arrowUp", this.game, mainTextWidth + x - (buttonSize/2), y -(buttonSize*1.1f), buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowUp.setTextureMap(16);
        arrowUp.textureMapUnpressed = 16;
        arrowUp.textureMapPressed = 8;
        arrowUp.listener.setPressListener(new InteractionListener.PressListener() {
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

        arrowDown = new Button("arrowDown", this.game, mainTextWidth + x -(buttonSize/2), y + size + (buttonSize*0.2f), buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowDown.setTextureMap(15);
        arrowDown.textureMapUnpressed = 15;
        arrowDown.textureMapPressed = 7;
        arrowDown.listener.setPressListener(new InteractionListener.PressListener() {
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

        float arrowBackX;
        if (text == "") {
            arrowBackX = x - (buttonSize * 1.5f) - (maxWidth/2);
        } else {
            arrowBackX = x - (buttonSize * 1.5f);
        }

        arrowBack = new Button("arrowBack", this.game, arrowBackX, y + (((size*1.1f)- buttonSize) / 2), buttonSize, buttonSize, Game.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowBack.setTextureMap(13);
        arrowBack.textureMapUnpressed = 13;
        arrowBack.textureMapPressed = 5;


        arrowBack.listener.setPressListener(new InteractionListener.PressListener() {
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

    }

    public void setOnChange(OnChange onChange){
        this.onChange = onChange;
    }

    interface OnChange{
        public void onChange();
    }

    public void setSelectedValue(int selectedValue){
        this.selectedValue = selectedValue;
    }

    public void setSelectedByString(String selectedValue){
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == selectedValue){
                this.selectedValue = i;
                return;
            }
        }
    }

    public void levelDown() {
        if (this.selectedValue != 0){
            game.soundPool.play(game.soundMenuSelectSmall, 1, 1, 0, 0, 1);
            this.selectedValue -=1;

            this.verifyOnChangeComplete();
        }
    }

    public void levelUp(){
        if (this.selectedValue < (this.values.length-1)){
            game.soundPool.play(game.soundMenuSelectSmall, 1, 1, 0, 0, 1);
            //this.audioSmall.play();
            this.selectedValue +=1;
            this.verifyOnChangeComplete();
        }
    }

    public void verifyOnChangeComplete(){
        if (this.onChange != null){
            onChange.onChange();
        }
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){

        if (this.mainTextObject != null){
            this.mainTextObject.alpha = alpha;
            this.mainTextObject.render(matrixView, matrixProjection);
        }

        this.textsObjects[selectedValue].alpha = alpha;
        this.textsObjects[selectedValue].render(matrixView, matrixProjection);


        if (this.selectedValue != (this.values.length-1)){
            this.arrowUp.alpha = alpha;
            this.arrowUp.render(matrixView, matrixProjection);
        }
        if (this.selectedValue != 0){
            this.arrowDown.alpha = alpha;
            this.arrowDown.render(matrixView, matrixProjection);
        }

        this.arrowBack.alpha = alpha;
        this.arrowBack.render(matrixView, matrixProjection);
    }

    public void fromMenu(Menu menu){
        menuRelated = menu;
        menu.isBlocked = true;
        this.alpha = 0f;
        this.isVisible = true;

        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0f,1f});
        valuesAnimation.add(new float[]{1f,0.3f});
        Animation reduceAlphaAnim = new Animation(menuRelated, "reduceAlphaAnim", "alpha", 500, valuesAnimation, false, true);
        reduceAlphaAnim.start();

        ArrayList<float[]> valuesAnimation2 = new ArrayList<>();
        valuesAnimation2.add(new float[]{0f,0f});
        valuesAnimation2.add(new float[]{1f,1f});
        Animation increaseAlphaAnim = new Animation(this, "increaseAlphaAnim", "alpha", 500, valuesAnimation2, false, true);
        final Selector innerSelector = this;
        increaseAlphaAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerSelector.isBlocked = false;
            }
        });
        increaseAlphaAnim.start();
    }

    public void backToMenu(){
        game.soundPool.play(game.soundMenuSelectBig, 1, 1, 0, 0, 1);
        isBlocked = true;

        ArrayList<float[]> valuesAnimation = new ArrayList<>();
        valuesAnimation.add(new float[]{0f,0.3f});
        valuesAnimation.add(new float[]{1f,1f});
        Animation increaseAlphaAnim = new Animation(menuRelated, "increaseAlphaAnim", "alpha", 500, valuesAnimation, false, true);
        increaseAlphaAnim.start();

        ArrayList<float[]> valuesAnimation2 = new ArrayList<>();
        valuesAnimation2.add(new float[]{0f,1f});
        valuesAnimation2.add(new float[]{1f,0f});
        Animation reduceAlphaAnim = new Animation(this, "reduceAlphaAnim", "alpha", 500, valuesAnimation2, false, true);
        final Menu innerMenu = menuRelated;
        final Selector innerSelector2 = this;
        reduceAlphaAnim .setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                innerMenu.isBlocked = false;
                innerSelector2.isVisible = false;
            }
        });
        reduceAlphaAnim.start();
    }
}
