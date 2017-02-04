package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 07/12/2016.
 */


// por enquanto, só serve para deslocamento horizontal e cuja largura seja igual ao tamanho da tela

public class MenuIcon extends Entity{

    ArrayList<Button> icons;
    ArrayList<Text> texts;
    ArrayList<Text> texts2;
    ArrayList<Text> innerTexts;

    ArrayList<MenuIconGraph> graph;
    float size;
    private static final String TAG = "MenuIcon";
    boolean desacelerationActivated = false;
    float lastMovement;
    private boolean cancelNextPress;
    Rectangle beggining;
    Rectangle ending;
    public int iconNumberToShow = -1;

    public MenuIcon(String name, float x, float y, float size) {
        super(name, x, y);
        icons = new ArrayList<>();
        texts = new ArrayList<>();
        texts2 = new ArrayList<>();
        innerTexts = new ArrayList<>();
        graph = new ArrayList<>();
        this.size = size;

        float padd = size * 0.1f;

        beggining = new Rectangle("beginning", 0, y, padd * 0.5f, size, 0, new Color(0.4f, 0.4f, 0.4f, 1f));
        beggining.alpha = 0f;
        addChild(beggining);
        ending = new Rectangle("ending", Game.resolutionX - (padd * 0.5f), y, padd * 0.5f, size, 0, new Color(0.4f, 0.4f, 0.4f, 1f));
        ending.alpha = 0f;
        addChild(ending);

    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){
        //Log.e("menu", "render MenuIcon");
        //checkAnimations();

        if (!isVisible){
            return;
        }

        for (int i = 0; i < icons.size();i++){
            icons.get(i).render(matrixView, matrixProjection);
        }
        for (int i = 0; i < texts.size();i++){
            //Log.e("menu", "render text");
            texts.get(i).render(matrixView, matrixProjection);
        }
        for (int i = 0; i < texts2.size();i++){
            texts2.get(i).render(matrixView, matrixProjection);
        }
        for (int i = 0; i < innerTexts.size();i++){
            innerTexts.get(i).render(matrixView, matrixProjection);
        }
        for (int i = 0; i < graph.size();i++){
            graph.get(i).render(matrixView, matrixProjection);
        }

        beggining.render(matrixView, matrixProjection);
        ending.render(matrixView, matrixProjection);

    }

    public void blockAllIcons(){
        for (int i = 0; i < icons.size();i++){
            icons.get(i).block();
        }
    }

    public void unblockAllIcons(){
        for (int i = 0; i < icons.size();i++){
            icons.get(i).unblock();
        }
    }

    @Override
    public void display() {
        Log.e(TAG, "display do menuIcon de nome "+name);
        super.display();
        for (int i = 0; i < icons.size();i++){
            icons.get(i).display();
        }
        for (int i = 0; i < texts.size();i++){
            texts.get(i).display();
        }
        for (int i = 0; i < texts2.size();i++){
            texts2.get(i).display();
        }
        for (int i = 0; i < innerTexts.size();i++){
            innerTexts.get(i).display();
        }
        for (int i = 0; i < graph.size();i++){
            graph.get(i).display();
        }
    }

    public void appear(){
        
        if (iconNumberToShow != -1){
            moveToIcon(iconNumberToShow);
        }
        
        Sound.play(Sound.soundMenuIconDrop, 0.5f, 0.5f, 0);
        blockAllIcons();
        display();
        final MenuIcon innerMenuIcon = this;
        for (int i = 0; i < icons.size(); i++){
            icons.get(i).animTranslateX = (Game.resolutionX * 0.5f) + (Game.resolutionX * i * 0.1f);
            icons.get(i).animTranslateY = (Game.resolutionX * 0.5f) + (Game.resolutionX * i * 0.1f);
            if (i == 0) {
                Utils.createSimpleAnimation(icons.get(i), "a" + i, "animTranslateX", 500, (Game.resolutionX * 0.5f) + (-Game.resolutionX * Utils.getRandonFloat(0f, 1f)), 0f, new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        innerMenuIcon.unblock();
                    }
                }).start();
            } else {
                Utils.createSimpleAnimation(icons.get(i), "a" + i, "animTranslateX", 500, (Game.resolutionX * 0.5f) + (-Game.resolutionX * Utils.getRandonFloat(0f, 1f)), 0).start();
            }
            Utils.createSimpleAnimation(icons.get(i), "a" + i, "animTranslateY", 500, (-Game.resolutionX * 0.5f) + (-Game.resolutionX * Utils.getRandonFloat(0f, 1f)), 0).start();
        }

        for (int i = 0; i < texts.size(); i++) {
            texts.get(i).alpha = 0f;
            texts.get(i).increaseAlpha(1200, 1f);
        }
        for (int i = 0; i < texts2.size(); i++) {
            texts2.get(i).alpha = 0f;
            texts2.get(i).increaseAlpha(1200, 1f);
        }
        for (int i = 0; i < graph.size(); i++) {
            graph.get(i).alpha = 0f;
            graph.get(i).increaseAlpha(1200, 1f);
        }
        for (int i = 0; i < innerTexts.size(); i++) {
            innerTexts.get(i).alpha = 0f;
            innerTexts.get(i).increaseAlpha(1200, 1f);
        }

    }


    @Override
    public void clearDisplay() {
        Log.e(TAG, "clear display do menuIcon de nome "+name);
        super.clearDisplay();
        for (int i = 0; i < icons.size();i++){
            icons.get(i).clearDisplay();
        }
        for (int i = 0; i < texts.size();i++){
            texts.get(i).clearDisplay();
        }
        for (int i = 0; i < texts2.size();i++){
            texts2.get(i).clearDisplay();
        }
        for (int i = 0; i < innerTexts.size();i++){
            innerTexts.get(i).clearDisplay();
        }
        for (int i = 0; i < graph.size();i++){
            graph.get(i).clearDisplay();
        }
    }

    public void addText(int number, String name, String text, float textSize, float paddFromBottom, Color color){
        float padd = size * 0.1f;

        int numberOfIconsAdded = icons.size();

        float positionX = x + padd + ((numberOfIconsAdded - 1) * size * 1.1f);
        float centerPosition = positionX + (size/2);
        //Log.e(TAG, "adicionando texto ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);

        Text t = new Text(name, centerPosition, y + size + paddFromBottom, textSize, text, Game.font, color, Text.TEXT_ALIGN_CENTER);
        if (number == 1){
            texts.add(t);
        } else {
            //Log.e(TAG, "adicionando texto 2 ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);
            texts2.add(t);
        }
        addChild(t);
    }

    public void addInnerText(String name, String text, float textSize, float paddFromBottom, Color color){
        float padd = size * 0.1f;

        int numberOfIconsAdded = icons.size();

        float positionX = x + padd + ((numberOfIconsAdded - 1) * size * 1.1f);
        float centerPosition = positionX + (size/2);
        //Log.e(TAG, "adicionando texto ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);

        final Text t = new Text(name, centerPosition, y + size - paddFromBottom - textSize, textSize, text, Game.font, color, Text.TEXT_ALIGN_CENTER);
        innerTexts.add(t);
        Utils.createAnimation3v(t, "alphaOscilation", "alpha", 3000, 0f, 1f, 0.7f, 0.7f, 1f, 1f, true, true).start();

        ArrayList<float[]> valuesAnim = new ArrayList<>();
        valuesAnim.add(new float[]{0f,1f});
        valuesAnim.add(new float[]{0.4f,2f});
        valuesAnim.add(new float[]{0.6f,3f});
        Animation animOption = new Animation(t, "numberForAnimation", "numberForAnimation", 2000, valuesAnim, true, false);
        animOption.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (t.numberForAnimation == 1f){
                    t.setColor(new Color(0.9f, 0.9f, 0f, 1f));
                } else if (t.numberForAnimation == 2f) {
                    t.setColor(new Color(1f, 0f, 0f, 1f));
                } else if (t.numberForAnimation == 3f) {
                    t.setColor(new Color(0f, 0f, 1f, 1f));
                }
            }
        });
        animOption.start();


        addChild(t);
    }



    public void addGraph(String name, float paddFromBottom, float height, int type){
        float padd = size * 0.1f;
        float positionX = x + padd + (graph.size() * size * 1.1f);
        positionX += size * 0.15f;



        MenuIconGraph g = new MenuIconGraph(name, positionX, y + size + paddFromBottom, size * 0.7f, height, type);
        graph.add(g);
        childs.add(g);
    }

    public void addOption(int id, int textureUnit, int textureMap, Animation.AnimationListener onSelect, boolean optionBlocked){
        
        if (listener == null){
            final MenuIcon innerMenuIcon = this;
            setListener(new InteractionListener(this.name, x, y, Game.resolutionX, size*1.25f, 5000, this));
            listener.setPressListener(new InteractionListener.PressListener() {
                @Override
                public void onPress() {}
                @Override
                public void onUnpress() {}
            });
            listener.setMoveListener(new InteractionListener.MoveListener() {
                @Override
                public void onMoveDown() {
                    //Log.e(TAG, "onMoveDown");
                    if (innerMenuIcon.desacelerationActivated){
                        innerMenuIcon.desacelerationActivated = false;
                        innerMenuIcon.cancelNextPress = true;
                    }
                }

                @Override
                public void onMove(TouchEvent touch, long startTime) {
                    //Log.e(TAG, "onMove");
                    innerMenuIcon.move(touch.x - touch.previousX);
                    lastMovement = touch.x - touch.previousX;
                }

                @Override
                public void onMoveUp(TouchEvent touch, long startTime) {
                    //Log.e(TAG, "onMoveUp");
                    desacelerationActivated = true;
                }
            });

        }

        float positionX = getPositionXFromIconNumber(icons.size()+1);
        Button button = new Button(Integer.toString(id), positionX, y, size, size, textureUnit, 1, Button.BUTTON_TYPE_256);
        button.setTextureMap(textureMap);
        button.textureMapUnpressed = textureMap;
        button.textureMapPressed = textureMap;
        final Button innerButton = button;
        final MenuIcon innerMenuIcon = this;
        final Animation.AnimationListener innerOnSelect = onSelect;
        final boolean innerOptionBlocked = optionBlocked;
        button.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (innerMenuIcon.cancelNextPress){
                    innerMenuIcon.cancelNextPress =  false;
                    //Log.e(TAG, "press cancelado");
                } else {

                    if (!innerOptionBlocked){
                        innerMenuIcon.blockAllIcons();
                    }

                    Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                    //Log.e(TAG, "press botão do menu");

                    ArrayList<float[]> valuesAnimation = new ArrayList<>();
                    valuesAnimation.add(new float[]{0f, 1f});
                    valuesAnimation.add(new float[]{0.07f, 0.82f});
                    valuesAnimation.add(new float[]{1f, 1f});

                    Animation animScaleX = new Animation(innerButton, "encolherX", "scaleX", 400, valuesAnimation, false, true);
                    animScaleX.start();

                    Animation animScaleY = new Animation(innerButton, "encolherY", "scaleY", 401, valuesAnimation, false, true);

                    animScaleY.setAnimationListener(innerOnSelect);
                    animScaleY.start();

                    float translateSize = size * 0.09f;
                    Utils.createAnimation3v(innerButton, "x", "translateX", 400, 0f, 0f, 0.07f, translateSize, 1f, 0f, false, true).start();
                    Utils.createAnimation3v(innerButton, "y", "translateY", 400, 0f, 0f, 0.07f, translateSize, 1f, 0f, false, true).start();
                    
                    Game.vibrate(Game.VIBRATE_SMALL);
                    
                }
            }
        });
        button.setMoveListener(new InteractionListener.MoveListener() {
            @Override
            public void onMoveDown() {

            }

            @Override
            public void onMove(TouchEvent touch, long startTime) {

            }

            @Override
            public void onMoveUp(TouchEvent touch, long startTime) {

            }
        });
        icons.add(button);
        addChild(button);

    }
    
    // primeiro icone é número 0
    public float getPositionXFromIconNumber(int number){
        float padd = size * 0.1f;
        return x + padd + ((number-1) * size * 1.1f);
    }

    @Override
    public void checkTransformations(boolean updatePrevious) {
        if (desacelerationActivated){
            desacelerate();
        }
        super.checkTransformations(updatePrevious);
    }

    private void desacelerate() {
        if (lastMovement < 0) {
            if (lastMovement + 0.8f < 0) {
                move(lastMovement + 0.8f);
                lastMovement += 0.8f;
            } else {
                desacelerationActivated = false;
            }
        } else if (lastMovement > 0) {
            if (lastMovement - 0.8f > 0) {
                move(lastMovement - 0.8f);
                lastMovement -= 0.8f;
            } else {
                desacelerationActivated = false;
            }
        }
    }
    
    private void moveToIcon(int iconNumber){
        for (int i = 0; i < icons.size(); i++){
            icons.get(i).accumulatedTranslateX = 0f;
        }
        
        for (int i = 0; i < texts.size(); i++){
            texts.get(i).accumulatedTranslateX = 0f;
        }

        for (int i = 0; i < texts2.size(); i++){
            texts2.get(i).accumulatedTranslateX = 0f;
        }

        for (int i = 0; i < innerTexts.size(); i++){
            innerTexts.get(i).accumulatedTranslateX = 0f;
        }

        for (int i = 0; i < graph.size(); i++){
            //Log.e(TAG, "ativando translate do graph " + i);
            graph.get(i).accumulatedTranslateX = 0f;
        }
        
         if (icons.size() > iconNumber){
            move(getPositionXFromIconNumber(iconNumber));
        }
    }

    private void move(float iconTranslateX) {
        //Log.e(TAG, "movendo "+ iconTranslateX);
        float padd = size * 0.1f;

        Button lastIcon = icons.get(icons.size()-1);
        if (lastIcon.positionX + size + iconTranslateX < Game.resolutionX - padd){
            iconTranslateX = (Game.resolutionX - padd) - (lastIcon.positionX + size);
            //Log.e(TAG, "ultimo icone na borda - iconTranslateX "+iconTranslateX);
            if (desacelerationActivated){
                desacelerationActivated = false;
            }
            Utils.createSimpleAnimation(ending, "alpha", "alpha", 1200, 1f, 0f).start();
        }

        Button firstIcon = icons.get(0);
        if (firstIcon.positionX + iconTranslateX > padd){
            iconTranslateX = padd - firstIcon.positionX;
            //Log.e(TAG, "primeiro icone na borda - iconTranslateX "+iconTranslateX);
            if (desacelerationActivated){
                desacelerationActivated = false;
            }
            Utils.createSimpleAnimation(beggining, "alpha", "alpha", 1200, 1f, 0f).start();
        }

        for (int i = 0; i < icons.size(); i++){
            icons.get(i).translate(iconTranslateX, 0f);
        }

        for (int i = 0; i < texts.size(); i++){
            texts.get(i).translate(iconTranslateX, 0f);
        }

        for (int i = 0; i < texts2.size(); i++){
            texts2.get(i).translate(iconTranslateX, 0f);
        }

        for (int i = 0; i < innerTexts.size(); i++){
            innerTexts.get(i).translate(iconTranslateX, 0f);
        }

        for (int i = 0; i < graph.size(); i++){
            //Log.e(TAG, "ativando translate do graph " + i);
            graph.get(i).translate(iconTranslateX, 0f);
        }
    }

    @Override
    public void block() {
        super.block();
        blockAllIcons();
    }

    @Override
    public void unblock() {
        super.unblock();
        unblockAllIcons();
    }
}
