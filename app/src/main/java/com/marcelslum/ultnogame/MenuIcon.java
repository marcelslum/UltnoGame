package com.marcelslum.ultnogame;

import android.opengl.GLES20;
import android.util.Log;
import java.util.ArrayList;

/**
 * Created by marcel on 07/12/2016.
 */

// por enquanto, só serve para deslocamento horizontal e cuja largura seja igual ao tamanho da tela

public class MenuIcon extends Entity{

    public final static int MAX_NUMBER_OF_ELEMENTS = 30;

    public static Button [] icons;
    private static Text [] texts;
    private static Text [] texts2;
    private static Text [] innerTexts;

    private static boolean [] iconsMap;
    private static boolean [] textsMap;
    private static boolean [] texts2Map;
    private static boolean [] innerTextsMap;
    private static boolean [] graphMap;

    public static MenuIconGraph [] graph;

    private static Rectangle beggining;
    private static Rectangle ending;

    
    float size;
    private static final String TAG = "MenuIcon";
    boolean desacelerationActivated = false;
    float lastMovement;
    private boolean cancelNextPress;

    public int iconNumberToShow = -1;
    public float currentTranslateX = 0;

    int numberOfElements = 0;

    public MenuIcon(String name, float x, float y, float size) {
        super(name, x, y, Entity.TYPE_MENU);

        this.size = size;

        if (icons == null){
            icons = new Button[MAX_NUMBER_OF_ELEMENTS];
            texts = new Text[MAX_NUMBER_OF_ELEMENTS];
            texts2 = new Text[MAX_NUMBER_OF_ELEMENTS];
            innerTexts = new Text[MAX_NUMBER_OF_ELEMENTS];
            graph = new MenuIconGraph[MAX_NUMBER_OF_ELEMENTS];

            iconsMap = new boolean[MAX_NUMBER_OF_ELEMENTS];
            textsMap = new boolean[MAX_NUMBER_OF_ELEMENTS];
            texts2Map = new boolean[MAX_NUMBER_OF_ELEMENTS];
            innerTextsMap = new boolean[MAX_NUMBER_OF_ELEMENTS];
            graphMap = new boolean[MAX_NUMBER_OF_ELEMENTS];

            for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS; i++) {
                icons[i] = new Button();
                texts[i] = new Text();
                texts2[i] = new Text();
                innerTexts[i] = new Text();
            }

            float padd = size * 0.1f;
            beggining = new Rectangle("beginning", 0, y, Entity.TYPE_OTHER, padd * 0.5f, size, 0, new Color(0.4f, 0.4f, 0.4f, 1f));
            ending = new Rectangle("ending", Game.resolutionX - (padd * 0.5f), y, Entity.TYPE_OTHER, padd * 0.5f, size, 0, new Color(0.4f, 0.4f, 0.4f, 1f));
        }

        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS; i++) {
            iconsMap[i] = false;
            textsMap[i] = false;
            texts2Map[i] = false;
            innerTextsMap[i] = false;
            graphMap[i] = false;
        }

        beggining.alpha = 0f;
        ending.alpha = 0f;
        addChild(beggining);
        addChild(ending);

    }
    
    public void clear(){

        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS; i++) {

            iconsMap[i] = false;
            textsMap[i] = false;
            texts2Map[i] = false;
            innerTextsMap[i] = false;
            graphMap[i] = false;

            icons[i].clearDisplay();
            texts[i].clearDisplay();
            texts2[i].clearDisplay();
            innerTexts[i].clearDisplay();
            if (graph[i] != null) {
                graph[i].clearDisplay();
            }

            icons[i].accumulatedTranslateX = 0f;
            texts[i].accumulatedTranslateX = 0f;
            texts2[i].accumulatedTranslateX = 0f;
            innerTexts[i].accumulatedTranslateX = 0f;
            if (graph[i] != null) {
                graph[i].accumulatedTranslateX = 0f;
            }

            icons[i].cleanAnimations();
            texts[i].cleanAnimations();
            texts2[i].cleanAnimations();
            innerTexts[i].cleanAnimations();
            if (graph[i] != null) {
                //graph[i].cleanAnimations();
            }

        }

        currentTranslateX = 0;

        childs.clear();
        numberOfElements = 0;
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){
        //Log.e("menu", "render MenuIcon");

        if (!isVisible){
            return;
        }

        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS;i++){
            if (iconsMap[i] && icons[i] != null) {
                icons[i].render(matrixView, matrixProjection);
            }

            if (texts[i].shadowText != null  && textsMap[i]){
                texts[i].shadowText.render(matrixView, matrixProjection);
            }
            if (texts[i] != null && textsMap[i]) {
                texts[i].render(matrixView, matrixProjection);
            }

            if (texts2[i] != null && texts2Map[i]) {
                texts2[i].render(matrixView, matrixProjection);
            }

            if (innerTexts[i] != null && innerTextsMap[i]) {
                innerTexts[i].render(matrixView, matrixProjection);
            }

            if (graphMap[i] && graph[i] != null) {
                graph[i].render(matrixView, matrixProjection);
            }

        }

        beggining.render(matrixView, matrixProjection);
        ending.render(matrixView, matrixProjection);
    }

    public void blockAllIcons(){
        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS;i++){
            if (icons[i] != null) {
                icons[i].block();
            }
        }
    }

    public void unblockAllIcons(){
        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS;i++){
            if (icons[i] != null) {
                icons[i].unblock();
            }
        }
    }

    @Override
    public void display() {
        super.display();

        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS; i++) {
            if (iconsMap[i]) icons[i].display();
            if (textsMap[i]) texts[i].display();
            if (texts2Map[i]) texts2[i].display();
            if (innerTextsMap[i]) innerTexts[i].display();
            if (graphMap[i] && graph[i] != null) graph[i].display();
        }
    }

    public void appear(){

        blockAllIcons();

        for (int i = 0; i < MAX_NUMBER_OF_ELEMENTS; i++) {
            icons[i].accumulatedTranslateX = 0f;
            texts[i].accumulatedTranslateX = 0f;
            texts2[i].accumulatedTranslateX = 0f;
            innerTexts[i].accumulatedTranslateX = 0f;
            if (graph[i] != null) {
                graph[i].accumulatedTranslateX = 0f;
            }

            icons[i].cleanAnimationsNoChild();
            texts[i].cleanAnimationsNoChild();
            texts2[i].cleanAnimationsNoChild();
            innerTexts[i].cleanAnimationsNoChild();
            if (graph[i] != null) {
                graph[i].cleanAnimationsNoChild();
            }
        }


        display();


        final MenuIcon innerMenuIcon = this;

        move(currentTranslateX, false);

        for (int i = 0; i < numberOfElements; i++){

            if (iconsMap[i]) {
                Utils.createSimpleAnimation(icons[i], "a" + i, "animTranslateX", 500, (Game.resolutionX * 0.5f) + (-Game.resolutionX * Utils.getRandonFloat(0f, 1f)), 0).start();

                final Button innerIcon = icons[i];
                final int iconNumber = i;
                Animation a = Utils.createSimpleAnimation(icons[i], "a" + i, "animTranslateY", 500, (-Game.resolutionX * 0.5f) + (-Game.resolutionX * Utils.getRandonFloat(0f, 1f)), 0);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd() {
                        Utils.createAnimation5v(innerIcon, "a" + iconNumber, "animTranslateY",
                                3000,
                                0f, 0f,
                                0.2f + (0.03f * iconNumber), 0f,
                                0.23f + (0.03f * iconNumber), -Game.resolutionY * 0.05f,
                                0.37f + (0.03f * iconNumber), 0f,
                                1f, 0f,
                                true, true
                        ).start();

                        innerMenuIcon.unblock();
                        SaveGame.setAllSecretSeen();
                    }
                });
                a.start();
                Utils.createSimpleAnimation(icons[i], "a" + i, "alpha", 500, 0f, 1f).start();
            }

            if (textsMap[i]){
                texts[i].setAlpha(0f);
                Utils.createAnimation3v(texts[i], "a"+i, "alpha", 500, 0, 0f, 0.5f, 0f, 1f, 1f, false, true).start();
            }

            if (texts2Map[i]){
                texts2[i].alpha = 0f;
                Utils.createAnimation3v(texts2[i], "a"+i, "alpha", 500, 0, 0f, 0.5f, 0f, 1f, 1f, false, true).start();
            }

            if (innerTextsMap[i]){
                innerTexts[i].alpha = 0f;
                innerTexts[i].increaseAlpha(1200, 1f);
            }
            if (graphMap[i]){
                graph[i].increaseAlpha(500, 1f);
            }
        }
    }

    @Override
    public void clearDisplay() {
        super.clearDisplay();
        for (int i = 0; i < numberOfElements; i++) {
            icons[i].clearDisplay();
            texts[i].clearDisplay();
            texts2[i].clearDisplay();
            innerTexts[i].clearDisplay();
            if (graph[i] != null) graph[i].clearDisplay();
        }
    }

    public void addText(int position, int number, String name, String text, float textSize, float paddFromBottom, Color color){

        float padd = size * 0.1f;

        float positionX = x + padd + ((position - 1) * size * 1.1f);
        float centerPosition = positionX + (size/2);
        //Log.e(TAG, "adicionando texto ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);

        if (number == 1){
            //t.addShadow(new Color(0.7f, 0.7f, 0.7f, 0.9f));
            textsMap[position] = true;
            texts[position].setData(name, centerPosition, y + size + paddFromBottom, textSize, text, Game.font, color, Text.TEXT_ALIGN_CENTER);
            addChild(texts[position]);
        } else {
            //Log.e(TAG, "adicionando texto 2 ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);
            texts2Map[position] = true;
            texts2[position].setData(name, centerPosition, y + size + paddFromBottom, textSize, text, Game.font, color, Text.TEXT_ALIGN_CENTER);
            addChild(texts2[position]);
        }

    }

    public void addInnerText(int position, String name, String text, float textSize, float paddFromBottom, Color color){

        innerTextsMap[position] = true;

        float padd = size * 0.1f;

        float positionX = x + padd + ((position - 1) * size * 1.1f);
        float centerPosition = positionX + (size/2);
        //Log.e(TAG, "adicionando texto ao menu x " + centerPosition + " y " +  y + size + paddFromBottom);

        innerTexts[position].setData(name, centerPosition, y + size - paddFromBottom - textSize, textSize, text, Game.font, color, Text.TEXT_ALIGN_CENTER);

        Utils.createAnimation3v(innerTexts[position], "alphaOscilation", "alpha", 3000, 0f, 1f, 0.7f, 0.7f, 1f, 1f, true, true).start();

        ArrayList<float[]> valuesAnim = new ArrayList<>();
        valuesAnim.add(new float[]{0f,1f});
        valuesAnim.add(new float[]{0.4f,2f});
        valuesAnim.add(new float[]{0.6f,3f});

        final Text innerText = innerTexts[position];

        Animation animOption = new Animation(innerText, "numberForAnimation", "numberForAnimation", 2000, valuesAnim, true, false);
        animOption.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (innerText.numberForAnimation == 1f){
                    innerText.setColor(new Color(0.9f, 0.9f, 0f, 1f));
                } else if (innerText.numberForAnimation == 2f) {
                    innerText.setColor(new Color(1f, 0f, 0f, 1f));
                } else if (innerText.numberForAnimation == 3f) {
                    innerText.setColor(new Color(0f, 0f, 1f, 1f));
                }
            }
        });
        animOption.start();

        addChild(innerText);
    }



    public MenuIconGraph addGraph(int position, String name, float paddFromBottom, float height, int type){
        graphMap[position] = true;
        float padd = size * 0.1f;
        float positionX = x + padd + ((position-1) * size * 1.1f);
        positionX += size * 0.15f;
        graph[position] = new MenuIconGraph(name, positionX, y + size + paddFromBottom, size * 0.7f, height, type);
        childs.add(graph[position]);
        return graph[position];
    }

    public void addOption(int position, int id, int textureUnit, TextureData textureData, Animation.AnimationListener onSelect, boolean optionBlocked){


        if (numberOfElements < (position + 1)){
            numberOfElements = (position + 1);
        }

        iconsMap[position] = true;

        if (listener == null){
            final MenuIcon innerMenuIcon = this;
            setListener(new InteractionListener(this.name, x, y, Game.resolutionX, size*1.25f, 5000, this));

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
                    //Log.e(TAG, "onMove isBlocked "+isBlocked);
                    if (!isBlocked){
                        innerMenuIcon.move(touch.x - touch.previousX, true);
                        lastMovement = touch.x - touch.previousX;
                    }
                }

                @Override
                public void onMoveUp(TouchEvent touch, long startTime) {
                    //Log.e(TAG, "onMoveUp");
                    desacelerationActivated = true;
                }
            });

        }

        float positionX = getPositionXFromIconNumber(position);

        icons[position].setData(Integer.toString(id), positionX, y, size, size, textureUnit, 1,
                textureData,
                textureData);
        final Button innerButton = icons[position];
        final MenuIcon innerMenuIcon = this;
        final Animation.AnimationListener innerOnSelect = onSelect;
        final boolean innerOptionBlocked = optionBlocked;
        icons[position].setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (innerMenuIcon.cancelNextPress){

                    innerMenuIcon.cancelNextPress =  false;

                } else {

                    if (!innerOptionBlocked){
                        innerMenuIcon.blockAllIcons();
                    }

                    Game.sound.playPlayMenuBig();

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

        icons[position].setMoveListener(new InteractionListener.MoveListener() {
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


        addChild(icons[position]);
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection){

        if (isVisible){
            checkAnimations();
            render(matrixView, matrixProjection);
        }
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
                move(lastMovement + 0.8f, true);
                lastMovement += 0.8f;
            } else {
                desacelerationActivated = false;
            }
        } else if (lastMovement > 0) {
            if (lastMovement - 0.8f > 0) {
                move(lastMovement - 0.8f, true);
                lastMovement -= 0.8f;
            } else {
                desacelerationActivated = false;
            }
        }
    }

    public void move(float iconTranslateX, boolean updateCurrentTranslateX) {

        //Log.e(TAG, "movendo "+ iconTranslateX);
        float padd = size * 0.1f;

        Button lastIcon = icons[numberOfElements-1];
        if (lastIcon.positionX + size + iconTranslateX < Game.resolutionX - padd){
            iconTranslateX = (Game.resolutionX - padd) - (lastIcon.positionX + size);
            //Log.e(TAG, "ultimo icone na borda - iconTranslateX "+iconTranslateX);
            if (desacelerationActivated){
                desacelerationActivated = false;
            }
            //Utils.createSimpleAnimation(ending, "alpha", "alpha", 1200, 1f, 0f).start();
        }

        Button firstIcon = icons[0];

        //Log.e(TAG, " firstIcon.positionX "+firstIcon.positionX);
        //Log.e(TAG, " iconTranslateX "+iconTranslateX);
        //Log.e(TAG, " padd "+padd);

        if (firstIcon.positionX + iconTranslateX > padd){
            iconTranslateX = padd - firstIcon.positionX;
            //Log.e(TAG, "primeiro icone na borda - iconTranslateX "+iconTranslateX);
            if (desacelerationActivated){
                desacelerationActivated = false;
            }
            Utils.createSimpleAnimation(beggining, "alpha", "alpha", 1200, 1f, 0f).start();
        }

        //Log.e(TAG, "movendo "+ iconTranslateX);

        for (int i = 0; i < numberOfElements; i++){
            if (iconsMap[i]) icons[i].translate(iconTranslateX, 0f);

            if (textsMap[i]) texts[i].translate(iconTranslateX, 0f);
            if ((textsMap[i]) && texts[i].shadowText != null){
                texts[i].shadowText.translate(iconTranslateX, 0f);
            }
            if (texts2Map[i])  texts2[i].translate(iconTranslateX, 0f);

            if (innerTextsMap[i] && innerTexts[i] != null) {
                innerTexts[i].translate(iconTranslateX, 0f);
            }
            if (graphMap[i] && graph[i] != null) {
                graph[i].translate(iconTranslateX, 0f);
            }
        }
        
        if (updateCurrentTranslateX) {
            currentTranslateX += iconTranslateX;
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
