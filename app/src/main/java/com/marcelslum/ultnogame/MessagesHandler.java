package com.marcelslum.ultnogame;

import android.content.res.Resources;
import android.util.Log;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class MessagesHandler {
    public static Text messageInGame;
    static Text messageMenu;
    static Text messageSubMenu;
    static Text messageGameOver;
    static Text messagePreparation;
    static Text messageMaxScoreTotal;
    static Text messageGoogleLogged;
    static Text messageContinue;
    static Text messageBack;
    static Text messageConqueredStarsTotal;
    static Image starForMessage;
    static Text messageTime;
    static Text messageCurrentLevel;
    static Text messageBeta;
    static Text messageGroupsUnblocked;
    static TextBox bottomTextBox;

    static float yOfMessageBackAndContinue;


    public static void initMessages(){
        
        Game.aboutTextView = new TextView("aboutTextView", Game.resolutionX * 0.1f,
                                          Game.resolutionY * 0.2f,
                                          Game.resolutionX * 0.8f,
                                          Game.resolutionY * 0.8f,
                                          Game.gameAreaResolutionY*0.05f,
                                          Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);


        Resources resources = Game.getContext().getResources();

        Game.aboutTextView.addText(resources.getString(R.string.sobre1), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre1b), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre2), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre3), Color.cinza1);
        Game.aboutTextView.addText(resources.getString(R.string.sobre3b), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre4), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre5), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre6), Color.cinza1);
        Game.aboutTextView.addText(resources.getString(R.string.sobre7), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre8), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre9b), Color.cinza1);
        Game.aboutTextView.addText(resources.getString(R.string.sobre10), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre10a), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre11), Color.cinza1);
        Game.aboutTextView.addText(resources.getString(R.string.sobre11b),Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12a), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12b), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre13), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre14), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre15), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre16), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre17), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre18), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre19), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre20), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre21), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre22), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre23), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre24), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre25), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre26), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre27), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre28), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre29), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre30), Color.cinza2);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);

        Game.notConnectedTextView = new TextView("about", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.02f,
                Game.resolutionX * 0.94f,
                Game.resolutionY,
                Game.gameAreaResolutionY*0.038f,
                Game.font, new Color(0.85f, 0.85f, 0.85f, 1f), Text.TEXT_ALIGN_CENTER, 0.25f);

        Game.notConnectedTextView.addText(resources.getString(R.string.messageNaoConectado1), Color.cinza4);

        yOfMessageBackAndContinue = Game.resolutionY*0.898f;

        MessageStar.initMessageStars();

        messageGameOver = new Text("messageGameOver",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.2f, Game.gameAreaResolutionY*0.17f,
                resources.getString(R.string.messageGameOver), Game.font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageMenu = new Text("messageMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.18f, Game.gameAreaResolutionY*0.08f, ".", Game.font, Color.azul);

        messageSubMenu = new Text("messageSubMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.28f, Game.gameAreaResolutionY*0.05f, ".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f));

        messageGroupsUnblocked = new Text("messageGroupsUnblocked",
                Game.gameAreaResolutionX*0.5f, Game.resolutionY*0.6f, Game.gameAreaResolutionY*0.08f,
                resources.getString(R.string.messageGroupsUnblocked),
                Game.font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);

        ArrayList<float[]> valuesAnimationGameOver = new ArrayList<>();
        valuesAnimationGameOver.add(new float[]{0f,1f});
        valuesAnimationGameOver.add(new float[]{0.55f,2f});
        valuesAnimationGameOver.add(new float[]{0.85f,3f});
        Animation animMessageGameOver = new Animation(messageGameOver, "numberForAnimation", "numberForAnimation", 4000, valuesAnimationGameOver, true, false);
        animMessageGameOver.setOnChangeNotFluid(new Animation.OnChange() {
            @Override
            public void onChange() {
                if (messageGameOver.numberForAnimation == 1f){
                    messageGameOver.setColor(Color.pretoCheio);
                } else if (messageGameOver.numberForAnimation == 2f) {
                    messageGameOver.setColor(Color.verdeCheio);
                } else if (messageGameOver.numberForAnimation == 3f) {
                    messageGameOver.setColor(Color.azulCheio);
                }
            }
        });
        animMessageGameOver.start();

        messagePreparation = new Text("messagePreparation",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.3f, Game.gameAreaResolutionY*0.4f,
                " ", Game.font, Color.vermelhoCheio, Text.TEXT_ALIGN_CENTER);

        messageInGame = new Text("messageInGame",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.25f, Game.gameAreaResolutionY*0.14f,
                resources.getString(R.string.pause), Game.font, Color.pretoCheio,Text.TEXT_ALIGN_CENTER);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                Game.resolutionX*0.02f, Game.resolutionY - (Game.resolutionY * 0.06f), Game.resolutionY*0.03f,
                resources.getString(R.string.messageMaxScoreTotal) +"\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()), Game.font, new Color(0f, 0f, 0f, 0.5f));
        
       

        messageGoogleLogged = new Text("messageGoogleLogged",
                Game.resolutionX*0.98f, Game.resolutionY - (Game.resolutionY * 0.06f), Game.resolutionY*0.03f,
                ".", Game.font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_RIGHT);

        messageConqueredStarsTotal = new Text("messageConqueredStarsTotal",
                Game.resolutionX*0.895f, Game.resolutionY*0.3f, Game.resolutionY*0.05f,
                resources.getString(R.string.messageConqueredStarsTotal) +"\u0020"+ NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal), Game.font, new Color(1f, 1f, 0f, 1f));

        messageConqueredStarsTotal.addShadow(new Color(0.6f, 0.6f, 0.6f, 1f));

        starForMessage = new Image("frame", Game.resolutionX*0.85f, MessagesHandler.messageConqueredStarsTotal.y - (Game.resolutionY*0.05f*0.02f),
                Game.resolutionY*0.05f, Game.resolutionY*0.05f, Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_STAR_SHINE_ID));

        Utils.createAnimation2v(starForMessage, "rotate", "rotate", 10000, 0f, 0f, 1f, 360f, true, true).start();
        Utils.createAnimation2v(starForMessage, "translateX", "translateX", 10000, 0f, 0f, 1f, -Game.resolutionX*0.001f, true, true).start();

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

        setMessageTime();

        MessagesHandler.messageCurrentLevel = new Text("messageCurrentLevel",
                Game.resolutionX*0.987f, Game.gameAreaResolutionY*0.885f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageCurrentLevel.setAlpha(0.7f);



        MessagesHandler.messageBack = new Text("messageBack",
                Game.resolutionX*0.095f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.voltar), Game.font, new Color(0.5f, 0.5f, 0.5f, 1f), Text.TEXT_ALIGN_LEFT);


        MessagesHandler.messageContinue = new Text("messageContinue",
                Game.resolutionX*0.91f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.continuar), Game.font, new Color(0.5f, 0.5f, 0.5f, 1f), Text.TEXT_ALIGN_RIGHT);

    }

    public static void setMessageTime(){
        MessagesHandler.messageTime = new Text("messageTime",
                Game.resolutionX*0.99f, Game.gameAreaResolutionY*0.81f, Game.resolutionY*0.051f,"00:00", Game.font, new Color(0.7f, 0.7f, 0.7f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTime.addShadow(new Color(0.4f, 0.4f, 0.4f, 1f));
    }

    final static String TAG = "MessagesHandler";

    public static void setBottomMessage(String text, int duration){

        if (bottomTextBox == null){
            return;
        }

        //Log.e(TAG, "showing bottom text: " + text);

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

            bottomTextBox.setText(text, Color.cinza1, false, null);
            bottomTextBox.display();
            bottomTextBox.setPositionY(Game.resolutionY - bottomTextBox.height);
            bottomTextBox.isBlocked = false;
            messageMaxScoreTotal.y = Game.resolutionY - bottomTextBox.height - (Game.resolutionY * 0.06f);
            messageGoogleLogged.y =  Game.resolutionY - bottomTextBox.height - (Game.resolutionY * 0.06f);
        } else {
            if (!previousText.equals("...")){
                appearOrDesapear = true;
            }
            bottomTextBox.setText("...",Color.cinza1, false, null);
            bottomTextBox.isBlocked = true;
            bottomTextBox.setPositionY(Game.resolutionY*2);
            messageMaxScoreTotal.y = Game.resolutionY - (Game.resolutionY * 0.06f);
            messageGoogleLogged.y =  Game.resolutionY - (Game.resolutionY * 0.06f);
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
