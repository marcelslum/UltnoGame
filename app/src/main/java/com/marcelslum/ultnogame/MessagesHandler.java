package com.marcelslum.ultnogame;

import android.content.res.Resources;
import android.icu.text.TimeZoneFormat;

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
    static Text messageTrainingState;
    static Text messageTrainingState2;
    static Text messageCurrentLevel;

    static Text messageBeta;
    static Text messageGroupsUnblocked;
    static TextBox bottomTextBox;
    static TextView messageMenuSaveNotSeen;
    static TextView messageMenuCarregarJogo;
    static TextView messageExplicacaoTreinamento;
    static TextView messageExplicacaoDuranteTreinamento;

    static float yOfMessageBackAndContinue;
    static TextView statsTextView;
    static Text messageStatTittle;


    public static void initMessages(){

        Game.aboutTextView = new TextView("aboutTextView", Game.resolutionX * 0.1f,
                                          Game.resolutionY * 0.2f,
                                          Game.resolutionX * 0.8f,
                                          Game.resolutionY * 0.8f,
                                          Game.gameAreaResolutionY*0.05f,
                                          Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);


        Resources resources = Game.getContext().getResources();

        Game.aboutTextView.addText(resources.getString(R.string.sobre1), Color.azul, 1.5f);
        Game.aboutTextView.addText(resources.getString(R.string.sobre1b), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre2), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre3), Color.cinza20, 1.3f);
        Game.aboutTextView.addText(resources.getString(R.string.sobre3b), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre4), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre5), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre6), Color.cinza20, 1.3f);
        Game.aboutTextView.addText(resources.getString(R.string.sobre7), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre8), Color.azul);
        Game.aboutTextView.addText(resources.getString(R.string.sobre9b), Color.cinza20);
        Game.aboutTextView.addText(resources.getString(R.string.sobre10), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre10a), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre11), Color.cinza20);
        Game.aboutTextView.addText(resources.getString(R.string.sobre11b),Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12a), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre12b), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre13), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre14), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre15), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre16), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre17), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre18), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre19), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre20), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre21), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre22), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre23), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre24), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre25), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre26), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre27), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre28), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre29), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre30), Color.cinza40);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        Game.aboutTextView.addText(resources.getString(R.string.sobre31), Color.transparente);


        float fontSize = Game.gameAreaResolutionY*0.08f;
        messageMenuSaveNotSeen = new TextView("messageMenuSaveNotSeen", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.125f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.55f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen1), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen2), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen3), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen4), Color.cinza40);


        messageExplicacaoTreinamento = new TextView("explicacaoTreinamento", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.15f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.58f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageExplicacaoTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoTreinamento1), Color.azul40, 1.8f);
        messageExplicacaoTreinamento.addText(".", Color.transparente);
        messageExplicacaoTreinamento.addText(".", Color.transparente);
        messageExplicacaoTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoTreinamento2), Color.cinza20);
        messageExplicacaoTreinamento.addText(".", Color.transparente);
        messageExplicacaoTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoTreinamento3), Color.cinza20);
        messageExplicacaoTreinamento.addText(".", Color.transparente);
        messageExplicacaoTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoTreinamento4), Color.cinza20);
        messageExplicacaoTreinamento.addText(".", Color.transparente);
        messageExplicacaoTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoTreinamento5), Color.azul40);

        messageExplicacaoTreinamento.clearDisplay();

        messageExplicacaoDuranteTreinamento = new TextView("explicacaoTreinamento", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.12f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.6f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);

        messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoDuranteTreinamento1), Color.cinza40);

        messageExplicacaoDuranteTreinamento.clearDisplay();

        messageMenuCarregarJogo = new TextView("messageMenuCarregarJogo", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.12f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.6f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);


        Game.notConnectedTextView = new TextView("about", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.02f,
                Game.resolutionX * 0.94f,
                Game.resolutionY,
                Game.gameAreaResolutionY*0.038f,
                Game.font, new Color(0.85f, 0.85f, 0.85f, 1f), Text.TEXT_ALIGN_CENTER, 0.25f);

        Game.notConnectedTextView.addText(resources.getString(R.string.messageNaoConectado1), Color.cinza80);

        yOfMessageBackAndContinue = Game.resolutionY*0.898f;

        MessageStar.initMessageStars();

        messageGameOver = new Text("messageGameOver",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.2f, Game.gameAreaResolutionY*0.17f,
                resources.getString(R.string.messageGameOver), Game.font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);

        messageMenu = new Text("messageMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.15f, Game.gameAreaResolutionY*0.08f, ".", Game.font, Color.azul);

        messageStatTittle  = new Text("messageStatTittle",
                Game.gameAreaResolutionX*0.5f, Game.resolutionY*0.9f, Game.gameAreaResolutionY*0.06f, ".", Game.font, Color.azul40, Text.TEXT_ALIGN_CENTER);
        messageStatTittle.clearDisplay();

        messageSubMenu = new Text("messageSubMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.25f, Game.gameAreaResolutionY*0.05f, ".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f));

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
                Game.resolutionX*0.825f, Game.resolutionY*0.18f, Game.resolutionY*0.07f,
                resources.getString(R.string.messageConqueredStarsTotal) +"\u0020"+ NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal), Game.font, Color.amareloCheio);

        messageConqueredStarsTotal.addShadow(Color.cinza70);

        starForMessage = new Image("frame", Game.resolutionX*0.755f, MessagesHandler.messageConqueredStarsTotal.y + Game.resolutionY*0.012f,
                Game.resolutionY*0.07f, Game.resolutionY*0.07f, Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_STAR_SHINE_ID));

        Utils.createAnimation2v(starForMessage, "rotate", "rotate", 10000, 0f, 0f, 1f, 360f, true, true).start();
        Utils.createAnimation2v(starForMessage, "translateX", "translateX", 10000, 0f, 0f, 1f, -Game.resolutionX*0.001f, true, true).start();

        bottomTextBox = new TextBoxBuilder("bottomTextBox")
                .position(Game.resolutionX*0.05f, Game.resolutionY*0.85f)
                .width(Game.resolutionX*0.9f)
                .size(Game.resolutionY*0.032f)
                .text("...")
                .withoutArrow()
                .setTextColor(Color.branco)
                .setShadowColor(Color.azul40)
                .setBorderColor(Color.pretoCheio)
                .isHaveFrame(true)
                .isHaveArrowContinue(false)
                .build();

        bottomTextBox.setMultiColor(
                    Color.azulMedio,
                    Color.azulClaro,
                    Color.azulMedio,
                    Color.azulMedio
            );

        /*

        bottomTextBox.frame.addTopRectangle(
                    0.9f,
                    Color.cinza20.changeAlpha(0.4f),
                    Color.cinza40.changeAlpha(0.4f),
                    Color.cinza20.changeAlpha(0.4f),
                    Color.cinza20.changeAlpha(0.4f),
                    .05f,
                    Game.gameAreaResolutionX * 0.003f,
                    Game.gameAreaResolutionX * 0.003f,
                    Color.verde40
            );
            */



        setMessageTime();

        MessagesHandler.messageCurrentLevel = new Text("messageCurrentLevel",
                Game.resolutionX*0.987f, Game.gameAreaResolutionY*0.885f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageCurrentLevel.setAlpha(0.7f);

        MessagesHandler.messageTrainingState2 = new Text("messageTrainingState2",
                Game.resolutionX*0.975f, Game.gameAreaResolutionY*0.815f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTrainingState2.setAlpha(0.7f);

        MessagesHandler.messageTrainingState = new Text("messageTrainingState",
                Game.resolutionX*0.98f, Game.gameAreaResolutionY*0.745f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTrainingState.setAlpha(0.7f);


        MessagesHandler.messageBack = new Text("messageBack",
                Game.resolutionX*0.095f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.voltar), Game.font, new Color(0.5f, 0.5f, 0.5f, 1f), Text.TEXT_ALIGN_LEFT);


        MessagesHandler.messageContinue = new Text("messageContinue",
                Game.resolutionX*0.91f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.continuar), Game.font, new Color(0.5f, 0.5f, 0.5f, 1f), Text.TEXT_ALIGN_RIGHT);


        messageBack.addShadow(Color.cinza80.changeAlpha(0.5f));
        messageContinue.addShadow(Color.cinza80.changeAlpha(0.5f));

        Utils.createAnimation3v(messageContinue, "alpha", "alpha", 3000, 0f, 0.3f, 0.5f, 0.6f, 1f, 0.3f, true, true ).start();
        Utils.createAnimation3v(messageBack, "alpha", "alpha", 3000, 0f, 0.3f, 0.5f, 0.6f, 1f, 0.3f, true, true ).start();

    }

    public static void setMessageTime(){
        MessagesHandler.messageTime = new Text("messageTime",
                Game.resolutionX*0.99f, Game.gameAreaResolutionY*0.81f, Game.resolutionY*0.051f,"00:00", Game.font, new Color(0.7f, 0.7f, 0.7f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTime.addShadow(new Color(0.4f, 0.4f, 0.4f, 1f));
    }

    final static String TAG = "MessagesHandler";

    public static void setBottomMessage(String text, int duration){

        if (bottomTextBox == null) return;

        float previousPosition = bottomTextBox.y;
        String previousText = bottomTextBox.text;

        if (previousText.equals("...")|| previousText.equals("")){
            previousPosition = Game.resolutionY * 2;
        }

        if (!text.equals("")){
            bottomTextBox.setText(text, Color.branco);
            bottomTextBox.display();
            bottomTextBox.setPositionY(Game.resolutionY - (bottomTextBox.height * 1.15f));
            bottomTextBox.isBlocked = false;
        } else {
            bottomTextBox.setText("...",Color.transparente);
            bottomTextBox.isBlocked = true;
            bottomTextBox.setPositionY(Game.resolutionY*2);
        }

        float difference = previousPosition - bottomTextBox.y;
        if (difference != 0) {
            Utils.createSimpleAnimation(bottomTextBox, "translateY", "translateY", 200, difference, 0.0f).start();
        }


        if (duration > 100){
            Utils.createSimpleAnimation(bottomTextBox, "alpha", "alpha", duration, 1f, 0.95f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    setBottomMessage("", 0);
                }
            }).start();
        }
    }

    public static void initStatsTextBox() {

        statsTextView = new TextView("statsTextView", Game.resolutionX * 0.1f,
                Game.resolutionY * 0.2f,
                Game.resolutionX * 0.8f,
                Game.resolutionY * 0.8f,
                Game.gameAreaResolutionY*0.05f,
                Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);

        Resources resources = Game.getContext().getResources();
        statsTextView.addText(resources.getString(R.string.estatisticaTitulo), Color.azul);
        statsTextView.addText(".", Color.transparente);
        statsTextView.addText(resources.getString(R.string.stat0) + " " + SaveGame.saveGame.stats[0], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat1)+ " " + SaveGame.saveGame.stats[1], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat2)+ " " + SaveGame.saveGame.stats[2], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat3)+ " " + SaveGame.saveGame.stats[3], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat4)+ " " + SaveGame.saveGame.stats[4], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat5)+ " " + SaveGame.saveGame.stats[5], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat6)+ " " + SaveGame.saveGame.stats[6], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat7)+ " " + SaveGame.saveGame.stats[7], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat8)+ " " + SaveGame.saveGame.stats[8], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat9)+ " " + SaveGame.saveGame.stats[9], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat10)+ " " + SaveGame.saveGame.stats[10], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat11)+ " " + SaveGame.saveGame.stats[11], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat12)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[12]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat13)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[13]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat14)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[14]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat15)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[15]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat16)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[16]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat17)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[17]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat18)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[18]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat19)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[19]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat20)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[20]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat21)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[21]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat22)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[22]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat23)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[23]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat24)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[24]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat25)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[25]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat26)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[26]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat27)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[27]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat28)+ " " + SaveGame.saveGame.stats[28], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat29)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[29]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat30)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[30]), Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat31)+ " " + SaveGame.saveGame.stats[31], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat32)+ " " + SaveGame.saveGame.stats[32], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat33)+ " " + SaveGame.saveGame.stats[33], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat34)+ " " + SaveGame.saveGame.stats[34], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat35)+ " " + SaveGame.saveGame.stats[35], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat36)+ " " + SaveGame.saveGame.stats[36], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat37)+ " " + SaveGame.saveGame.stats[37], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat38)+ " " + SaveGame.saveGame.stats[38], Color.cinza20);
        statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);

    }
}
