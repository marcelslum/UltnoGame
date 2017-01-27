package com.marcelslum.ultnogame;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class MessageHandle {
    public static Text messageInGame;
    static Text messageMenu;
    static Text messageGameOver;
    static Text messagePreparation;
    static Text messageMaxScoreTotal;
    static Text messageConqueredStarsTotal;
    static Image starForMessage;
    static Text messageSplash1;
    static Text messageSplash2;
    static Text messageTime;
    static TextBox bottomTextBox;


    public static void initMessages(){

        float messageStarsSize = Game.gameAreaResolutionY*0.05f;
        Game.messageStars = new MessageStar("messageStar", messageStarsSize, Game.resolutionX - (messageStarsSize * 1.4f), Game.resolutionX * 0.05f);

        Game.messageStarsWin = new MessageStarWin("messageStar", messageStarsSize, Game.resolutionX * 0.5f, Game.gameAreaResolutionY*0.55f);

        messageGameOver = new Text("messageGameOver",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.2f, Game.gameAreaResolutionY*0.17f,
                Game.getContext().getResources().getString(R.string.messageGameOver), Game.font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageMenu = new Text("messageMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.145f, Game.gameAreaResolutionY*0.1f, ".", Game.font, new Color(0.2f, 0.2f, 0.2f, 1f));

        ArrayList<float[]> valuesAnimationGameOver = new ArrayList<>();
        valuesAnimationGameOver.add(new float[]{0f,1f});
        valuesAnimationGameOver.add(new float[]{0.55f,2f});
        valuesAnimationGameOver.add(new float[]{0.85f,3f});
        Animation animMessageGameOver = new Animation(messageGameOver, "numberForAnimation", "numberForAnimation", 4000, valuesAnimationGameOver, true, false);
        animMessageGameOver.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (messageGameOver.numberForAnimation == 1f){
                    messageGameOver.setColor(new Color(0f, 0f, 0f, 1f));
                } else if (messageGameOver.numberForAnimation == 2f) {
                    messageGameOver.setColor(new Color(1f, 0f, 0f, 1f));
                } else if (messageGameOver.numberForAnimation == 3f) {
                    messageGameOver.setColor(new Color(0f, 0f, 1f, 1f));
                }
            }
        });
        animMessageGameOver.start();

        messagePreparation = new Text("messagePreparation",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.3f, Game.gameAreaResolutionY*0.4f,
                " ", Game.font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageInGame = new Text("messageInGame",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.25f, Game.gameAreaResolutionY*0.14f,
                Game.getContext().getResources().getString(R.string.pause), Game.font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);

        //messageCurrentLevel = new Text("messageCurrentLevel", resolutionX*0.05f, resolutionY*0.72f, resolutionY*0.036f, getContext().getResources().getString(messageCurrentLevel) +"\u0020\u0020"+ Integer.toString(SaveGame.saveGame.currentLevelNumber) + " - " + getContext().getResources().getString(R.string.messageMaxScoreLevel) +"\u0020\u0020"+ SaveGame.saveGame.pointsLevels[SaveGame.saveGame.currentLevelNumber-1], font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_LEFT);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                Game.resolutionX*0.05f, Game.resolutionY*0.84f, Game.resolutionY*0.036f,
                Game.getContext().getResources().getString(R.string.messageMaxScoreTotal) +"\u0020\u0020"+ NumberFormat.getInstance().format(ScoreHandle.getMaxScoreTotal()), Game.font, new Color(0f, 0f, 0f, 0.5f));

        messageConqueredStarsTotal = new Text("messageConqueredStarsTotal",
                Game.resolutionX*0.9f, Game.resolutionY*0.15f, Game.resolutionY*0.05f,
                Game.getContext().getResources().getString(R.string.messageConqueredStarsTotal) +"\u0020"+ NumberFormat.getInstance().format(StarsHandle.conqueredStarsTotal), Game.font, new Color(0f, 0f, 0f, 0.5f));

        starForMessage = new Image("frame", Game.resolutionX*0.85f, Game.resolutionY*0.15f, Game.resolutionY*0.05f, Game.resolutionY*0.05f, Texture.TEXTURE_BUTTONS_BALLS_STARS, (0f + 1.5f)/1024f, (128f - 1.5f)/1024f, (0f + 1.5f)/1024f, (128f - 1.5f)/1024f);

        bottomTextBox = new TextBoxBuilder("bottomTextBox")
                .position(Game.resolutionX*0.05f, Game.resolutionY*0.9f)
                .width(Game.resolutionX*0.9f)
                .size(Game.resolutionY*0.032f)
                .text("...")
                .withoutArrow()
                .isHaveFrame(true)
                .isHaveArrowContinue(false)
                .frameType(TextBoxBuilder.FRAME_TYPE_SOLID)
                .build();
    }

    public static void setBottomMessage(String text, int duration){
        float previousPosition = bottomTextBox.y;
        String previousText = bottomTextBox.text;

        if (previousText.equals("...")|| previousText.equals("")){
            previousPosition = Game.resolutionY * 2;
        }

        boolean appearOrDesapear = false;
        if (!text.equals("")){
            if (bottomTextBox.y == Game.resolutionY*2 || !bottomTextBox.isVisible){
                appearOrDesapear = true;
            }

            bottomTextBox.setText(text);
            bottomTextBox.display();
            bottomTextBox.setPositionY(Game.resolutionY - bottomTextBox.height);
            bottomTextBox.isBlocked = false;
            messageMaxScoreTotal.y = Game.resolutionY - bottomTextBox.height - (Game.resolutionY * 0.08f);
        } else {
            if (!previousText.equals("...")){
                appearOrDesapear = true;
            }
            bottomTextBox.setText("...");
            bottomTextBox.isBlocked = true;
            bottomTextBox.setPositionY(Game.resolutionY*2);
            messageMaxScoreTotal.y = Game.resolutionY - (Game.resolutionY * 0.08f);
        }

        float difference = previousPosition - bottomTextBox.y;
        if (difference != 0) {
            Utils.createSimpleAnimation(bottomTextBox, "translateY", "translateY", 200, difference, 0.0f).start();
        }

        if (appearOrDesapear){
            //Sound.play(Sound.soundTextBoxAppear, 0.8f, 0.8f, 0);
        }

        if (duration > 100){
            Utils.createSimpleAnimation(bottomTextBox, "alpha", "alpha", duration, 1f, 0.8f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    setBottomMessage("", 0);
                }
            }).start();
        }
    }

}