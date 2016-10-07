package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 25/09/2016.
 */
public class List extends Entity{

    public Button arrowUp;
    public Button arrowDown;
    public Button arrowBack;
    ArrayList<RankingItem> itens;
    float width;
    float height;
    int numberOfLines;
    ArrayList<Text> textsPosition;
    ArrayList<Text> textsNames;
    ArrayList<Text> textsPoints;
    float size;
    public OnBack onBack;
    int showingItem = 0;
    int page = 0;
    ArrayList<Rectangle> rectangles;

    List(String name, float x, float y, float width, float height, int numberOfLines) {
        super(name, x, y);
        this.width = width;
        this.height = height;
        this.numberOfLines = numberOfLines;
        itens = new ArrayList<>();
        textsPoints = new ArrayList<>();
        textsNames = new ArrayList<>();
        textsPosition = new ArrayList<>();

        size = height/ (numberOfLines+1);

        float buttonSize = size*0.90f;
        final List innerList = this;

        arrowUp = new Button("arrowUp", x + width - size, y, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowUp.setTextureMap(16);
        arrowUp.textureMapUnpressed = 16;
        arrowUp.textureMapPressed = 8;
        arrowUp.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerList.isBlocked){
                    innerList.listUp();
                }
            }
        });
        addChild(arrowUp);

        arrowDown = new Button("arrowDown", x + width - size, y + height - size*2, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowDown.setTextureMap(15);
        arrowDown.textureMapUnpressed = 15;
        arrowDown.textureMapPressed = 7;
        arrowDown.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerList.isBlocked){
                    innerList.listDown();
                }
            }
        });
        addChild(arrowDown);

        arrowBack = new Button("arrowBack", x, y + height - size, buttonSize, buttonSize, Texture.TEXTURE_BUTTONS_AND_BALLS, 1.2f);
        arrowBack.setTextureMap(13);
        arrowBack.textureMapUnpressed = 13;
        arrowBack.textureMapPressed = 5;
        addChild(arrowBack);

        arrowBack.setOnPress(new Button.OnPress() {
            @Override
            public void onPress() {
                if (!innerList.isBlocked){
                    if (onBack != null){
                        Sound.play(Sound.soundMenuSelectBig, 1, 1, 0);
                        onBack.onBack();
                    }
                }
            }
        });


        float yPosition;
        rectangles = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++){
            if ((i % 2) == 1){
                yPosition = (i * size) + y;
                rectangles.add(new Rectangle("frame", x, yPosition, width - size, size, -1, new Color(0.8f, 0.8f, 0.8f, 1.0f)));
            }
        }


    }

    private void listUp() {
        Sound.play(Sound.soundMenuSelectSmall, 1, 1, 0);
        showingItem -= numberOfLines;
        if (showingItem < 0){
            showingItem = 0;
        }
        //Log.e("list", "show item "+(showingItem+1));
        showItem(showingItem+1);
    }

    public void setOnBack(OnBack onBack){
        this.onBack = onBack;
    }

    private void listDown() {
        Sound.play(Sound.soundMenuSelectSmall, 1, 1, 0);
        showingItem += numberOfLines;
        if (showingItem > itens.size()-1){
            showingItem = itens.size()-1;
        }
        Log.e("list", "show item "+(showingItem+1));
        showItem(showingItem+1);
    }

    interface OnBack{
        void onBack();
    }

    @Override
    public void setDrawInfo() {
        textsPoints.clear();
        textsNames.clear();
        textsPosition.clear();

        float padd = size * 0.1f;
        float fontSize = size * 0.8f;

        float yPosition;
        float maxPositionWidth = 0f;
        int firstItemToAdd = (page * numberOfLines);
        for (int i = 0; i < numberOfLines; i++){
            yPosition = (i * size)+ padd + y;
            if (itens.size() > (i+firstItemToAdd)){
                textsPosition.add(new Text("t", x + padd, yPosition, fontSize, Integer.toString(itens.get(i+firstItemToAdd).position), Game.font, new Color(0f, 0f, 0f, 1f)));
                float textWidth = textsPosition.get(i).calculateWidth();
                if (textWidth > maxPositionWidth){
                    maxPositionWidth = textWidth;
                }
            }
        }

        float maxPointsWidth = 0f;
        for (int i = 0; i < numberOfLines; i++){
            yPosition = (i * size)+padd + y;
            if (itens.size() > (i+firstItemToAdd)){
                textsPoints.add(new Text("t", x + width - padd - size, yPosition, fontSize, Integer.toString(itens.get(i+firstItemToAdd).points), Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_RIGHT));
                float textWidth = textsPoints.get(i).calculateWidth();
                if (textWidth > maxPointsWidth){
                    maxPointsWidth = textWidth;
                }
            }
        }

        float maxNameWidth = width - maxPointsWidth - maxPositionWidth - (size*2);

        for (int i = 0; i < numberOfLines; i++){
            yPosition = (i * size)+padd + y;
            if (itens.size() > (i+firstItemToAdd)){

                Text name = new Text("t", x + (padd*5) + maxPositionWidth, yPosition, fontSize, itens.get(i+firstItemToAdd).name, Game.font, new Color(0f, 0f, 0f, 1f));
                if (name.calculateWidth() > maxNameWidth){
                    name.reduceWidth(maxNameWidth);
                }
                textsNames.add(name);
            }
        }
    }

    public void addItem(RankingItem itemList){
        itens.add(itemList);
        setDrawInfo();
    }

    @Override
    public void verifyListener() {
        if (isBlocked){
            return;
        }
        super.verifyListener();
        arrowDown.verifyListener();
        arrowUp.verifyListener();
        arrowBack.verifyListener();
    }

    public void showItem(int item){
        showingItem = item-1;
        if (showingItem > itens.size()){
            showingItem = itens.size() - 1;
        }

        page = (int)Math.floor(showingItem / numberOfLines);

        setDrawInfo();


        if (((page*numberOfLines) + numberOfLines)>itens.size()){
            arrowDown.clearDisplay();
            arrowDown.isBlocked = true;
        } else {
            arrowDown.display();
            arrowDown.isBlocked = false;
        }

        if (page == 0){
            arrowUp.clearDisplay();
            arrowUp.isBlocked = true;
        } else {
            arrowUp.display();
            arrowUp.isBlocked = false;
        }
    }


    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        verifyAnimations();
        if (!isVisible) {
            return;
        }

        int rectanglesShowing = 0;
        for (int i = 0; i < textsPosition.size(); i++){
            if ((i % 2) == 1){
                rectangles.get(rectanglesShowing).prepareRender(matrixView, matrixProjection);
                rectanglesShowing += 1;
            }
        }

        for (int i = 0; i < textsPosition.size(); i++){
            textsPosition.get(i).prepareRender(matrixView, matrixProjection);
        }
        for (int i = 0; i < textsNames.size(); i++){
            textsNames.get(i).prepareRender(matrixView, matrixProjection);
        }
        for (int i = 0; i < textsPoints.size(); i++){
            textsPoints.get(i).prepareRender(matrixView, matrixProjection);
        }



        arrowUp.alpha = alpha;
        arrowUp.prepareRender(matrixView, matrixProjection);

        arrowDown.alpha = alpha;
        arrowDown.prepareRender(matrixView, matrixProjection);

        arrowBack.alpha = alpha;
        arrowBack.prepareRender(matrixView, matrixProjection);
    }
}
