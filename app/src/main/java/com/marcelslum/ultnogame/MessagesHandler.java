package com.marcelslum.ultnogame;

import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class MessagesHandler {

    final static String TAG = "MessagesHandler";

    static Text messageInGame;
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
    static MyTextView messageMenuSaveNotSeen;
    static MyTextView messageMenuCarregarJogo;
    static MyTextView messageExplicacaoTreinamento;
    static MyTextView messageExplicacaoDuranteTreinamento;
    static MyTextView statsMyTextView;
    static Text messageStatTittle;
    static MyTextView messageStatDescricao;
    static MyTextView aboutMyTextView;
    static MyTextView explicacaoRankingEstatisticosMyTextView;
    static MyTextView notConnectedMyTextView;

    static float yOfMessageBackAndContinue;


    public static void initMessages(){

        aboutMyTextView = new MyTextView("aboutMyTextView", Game.resolutionX * 0.1f,
                                          Game.resolutionY * 0.2f,
                                          Game.resolutionX * 0.8f,
                                          Game.resolutionY * 0.8f,
                                          Game.gameAreaResolutionY*0.05f,
                                          Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);
        Game.adicionarEntidadeFixa(aboutMyTextView);

        Resources resources = Game.getContext().getResources();

        aboutMyTextView.addText(resources.getString(R.string.sobre1), Color.azul, 1.5f);
        aboutMyTextView.addText(resources.getString(R.string.sobre1b), Color.transparente);
        aboutMyTextView.addText(resources.getString(R.string.sobre2), Color.azul);
        aboutMyTextView.addText(resources.getString(R.string.sobre3), Color.cinza20, 1.3f);
        aboutMyTextView.addText(resources.getString(R.string.sobre3b), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre4), Color.transparente);
        aboutMyTextView.addText(resources.getString(R.string.sobre5), Color.azul);
        aboutMyTextView.addText(resources.getString(R.string.sobre6), Color.cinza20, 1.3f);
        aboutMyTextView.addText(resources.getString(R.string.sobre7), Color.transparente);
        aboutMyTextView.addText(resources.getString(R.string.sobre8), Color.azul);
        aboutMyTextView.addText(resources.getString(R.string.sobre9b), Color.cinza20);
        aboutMyTextView.addText(resources.getString(R.string.sobre10), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre10a), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre11), Color.cinza20);
        aboutMyTextView.addText(resources.getString(R.string.sobre11b),Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre12), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre12a), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre12b), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre13), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre14), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre15), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre16), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre17), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre18), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre19), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre20), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre21), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre22), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre23), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre24), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre25), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre26), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre27), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre28), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre29), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre30), Color.cinza40);
        aboutMyTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        aboutMyTextView.addText(resources.getString(R.string.sobre31), Color.transparente);
        aboutMyTextView.addText(resources.getString(R.string.sobre31), Color.transparente);

        explicacaoRankingEstatisticosMyTextView = new MyTextView("explicacaoRankingEstatisticosMyTextView", Game.resolutionX * 0.1f,
                Game.resolutionY * 0.2f,
                Game.resolutionX * 0.8f,
                Game.resolutionY * 0.8f,
                Game.gameAreaResolutionY*0.05f,
                Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);
        Game.adicionarEntidadeFixa(explicacaoRankingEstatisticosMyTextView);
        explicacaoRankingEstatisticosMyTextView.addText(resources.getString(R.string.sobreRankingEstatisticos1), Color.azul, 1.5f);
        explicacaoRankingEstatisticosMyTextView.addText(".", Color.transparente);
        explicacaoRankingEstatisticosMyTextView.addText(resources.getString(R.string.sobreRankingEstatisticos2), Color.azul);
        explicacaoRankingEstatisticosMyTextView.addText(resources.getString(R.string.sobreRankingEstatisticos3), Color.cinza20, 1.3f);
        explicacaoRankingEstatisticosMyTextView.addText(resources.getString(R.string.sobreRankingEstatisticos4), Color.cinza40);


        float fontSize = Game.gameAreaResolutionY*0.08f;
        messageMenuSaveNotSeen = new MyTextView("messageMenuSaveNotSeen", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.125f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.55f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);
        Game.adicionarEntidadeFixa(messageMenuSaveNotSeen);

        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen1), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen2), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen3), Color.cinza40);
        messageMenuSaveNotSeen.addText(".", Color.transparente);
        messageMenuSaveNotSeen.addText(Game.getContext().getResources().getString(R.string.messageMenuSaveNotSeen4), Color.cinza40);

        messageExplicacaoTreinamento = new MyTextView("messageExplicacaoTreinamento", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.15f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.58f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);
        Game.adicionarEntidadeFixa(messageExplicacaoTreinamento);

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

        messageExplicacaoDuranteTreinamento = new MyTextView("messageExplicacaoDuranteTreinamento", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.12f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.6f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);
        Game.adicionarEntidadeFixa(messageExplicacaoDuranteTreinamento);
        messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.explicacaoDuranteTreinamento1), Color.cinza40);

        messageMenuCarregarJogo = new MyTextView("messageMenuCarregarJogo", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.12f,
                Game.resolutionX * 1f,
                Game.resolutionY,
                fontSize * 0.6f,
                Game.font, new Color(0.3f, 0.3f, 1f, 1f), Text.TEXT_ALIGN_CENTER, 0.2f);
        Game.adicionarEntidadeFixa(messageMenuCarregarJogo);


        notConnectedMyTextView = new MyTextView("notConnectedMyTextView", Game.resolutionX * 0.5f,
                Game.resolutionY * 0.02f,
                Game.resolutionX * 0.94f,
                Game.resolutionY,
                Game.gameAreaResolutionY*0.038f,
                Game.font, new Color(0.85f, 0.85f, 0.85f, 1f), Text.TEXT_ALIGN_CENTER, 0.25f);
        Game.adicionarEntidadeFixa(notConnectedMyTextView);

        notConnectedMyTextView.addText(resources.getString(R.string.messageNaoConectado1), Color.cinza80);

        yOfMessageBackAndContinue = Game.resolutionY*0.898f;

        MessageStar.initMessageStars();

        messageMenu = new Text("messageMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.15f, Game.gameAreaResolutionY*0.08f, ".", Game.font, Color.azul);
        Game.adicionarEntidadeFixa(messageMenu);

        messageSubMenu = new Text("messageSubMenu",
                Game.gameAreaResolutionX*0.05f, Game.gameAreaResolutionY*0.25f, Game.gameAreaResolutionY*0.05f, ".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f));
        Game.adicionarEntidadeFixa(messageSubMenu);

        messageStatTittle  = new Text("messageStatTittle",
                Game.gameAreaResolutionX*0.5f, Game.resolutionY*0.12f, Game.gameAreaResolutionY*0.06f, ".", Game.font, Color.azul40, Text.TEXT_ALIGN_CENTER);
        Game.adicionarEntidadeFixa(messageStatTittle);
        messageStatTittle.addShadow(Color.azulClaro);
        messageStatTittle.clearDisplay();

        messageGroupsUnblocked = new Text("messageGroupsUnblocked",
                Game.gameAreaResolutionX*0.5f, Game.resolutionY*0.6f, Game.gameAreaResolutionY*0.08f,
                resources.getString(R.string.messageGroupsUnblocked),
                Game.font, new Color(0f, 0f, 0f, 1f),Text.TEXT_ALIGN_CENTER);
        Game.adicionarEntidadeFixa(messageGroupsUnblocked);

        messageGameOver = new Text("messageGameOver",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.2f, Game.gameAreaResolutionY*0.17f,
                resources.getString(R.string.messageGameOver), Game.font, new Color(1f, 0f, 0f, 1f), Text.TEXT_ALIGN_CENTER);
        Game.adicionarEntidadeFixa(messageGameOver);
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
        Game.adicionarEntidadeFixa(messagePreparation);

        messageInGame = new Text("messageInGame",
                Game.gameAreaResolutionX*0.5f, Game.gameAreaResolutionY*0.25f, Game.gameAreaResolutionY*0.14f,
                resources.getString(R.string.pause), Game.font, Color.pretoCheio,Text.TEXT_ALIGN_CENTER);
        Game.adicionarEntidadeFixa(messageInGame);

        messageMaxScoreTotal = new Text("messageMaxScoreTotal",
                Game.resolutionX*0.02f, Game.resolutionY - (Game.resolutionY * 0.06f), Game.resolutionY*0.03f,
                resources.getString(R.string.messageMaxScoreTotal) +"\u0020"+ NumberFormat.getInstance().format(ScoreHandler.getMaxScoreTotal()), Game.font, new Color(0f, 0f, 0f, 0.5f));
        Game.adicionarEntidadeFixa(messageMaxScoreTotal);

        messageGoogleLogged = new Text("messageGoogleLogged",
                Game.resolutionX*0.98f, Game.resolutionY - (Game.resolutionY * 0.06f), Game.resolutionY*0.03f,
                ".", Game.font, new Color(0f, 0f, 0f, 0.5f), Text.TEXT_ALIGN_RIGHT);
        Game.adicionarEntidadeFixa(messageGoogleLogged);

        messageConqueredStarsTotal = new Text("messageConqueredStarsTotal",
                Game.resolutionX*0.825f, Game.resolutionY*0.18f, Game.resolutionY*0.07f,
                resources.getString(R.string.messageConqueredStarsTotal) +"\u0020"+ NumberFormat.getInstance().format(StarsHandler.conqueredStarsTotal), Game.font, Color.amareloCheio);
        Game.adicionarEntidadeFixa(messageConqueredStarsTotal);
        messageConqueredStarsTotal.addShadow(Color.cinza70);

        starForMessage = new Image("starForMessage", Game.resolutionX*0.755f, MessagesHandler.messageConqueredStarsTotal.y + Game.resolutionY*0.012f,
                Game.resolutionY*0.07f, Game.resolutionY*0.07f, Texture.TEXTURES,
                TextureData.getTextureDataById(TextureData.TEXTURE_STAR_SHINE_ID));
        Game.adicionarEntidadeFixa(starForMessage);
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

        bottomTextBox.layer = Layers.LAYER9;
        Game.adicionarEntidadeFixa(bottomTextBox);

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
        Game.adicionarEntidadeFixa(messageCurrentLevel);

        MessagesHandler.messageTrainingState2 = new Text("messageTrainingState2",
                Game.resolutionX*0.975f, Game.gameAreaResolutionY*0.815f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTrainingState2.setAlpha(0.7f);
        Game.adicionarEntidadeFixa(messageTrainingState2);

        MessagesHandler.messageTrainingState = new Text("messageTrainingState",
                Game.resolutionX*0.98f, Game.gameAreaResolutionY*0.745f, Game.resolutionY*0.051f,".", Game.font, new Color(0.35f, 0.35f, 0.35f, 1f), Text.TEXT_ALIGN_RIGHT);
        MessagesHandler.messageTrainingState.setAlpha(0.7f);
        Game.adicionarEntidadeFixa(messageTrainingState);


        MessagesHandler.messageBack = new Text("messageBack",
                Game.resolutionX*0.095f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.voltar), Game.font, Color.cinza50, Text.TEXT_ALIGN_LEFT);
        Game.adicionarEntidadeFixa(messageBack);
        messageBack.addShadow(Color.cinza80.changeAlpha(0.5f));
        Utils.createAnimation3v(messageBack, "alpha", "alpha", 3000, 0f, 0.3f, 0.5f, 0.6f, 1f, 0.3f, true, true ).start();

        MessagesHandler.messageContinue = new Text("messageContinue",
                Game.resolutionX*0.91f, yOfMessageBackAndContinue, Game.resolutionY*0.033f,
                resources.getString(R.string.continuar), Game.font, Color.cinza50, Text.TEXT_ALIGN_RIGHT);
        Game.adicionarEntidadeFixa(messageContinue);
        messageContinue.addShadow(Color.cinza80.changeAlpha(0.5f));
        Utils.createAnimation3v(messageContinue, "alpha", "alpha", 3000, 0f, 0.3f, 0.5f, 0.6f, 1f, 0.3f, true, true ).start();


    }

    public static void setMessageTime(){

        if (messageTime == null) {
            messageTime = new Text("messageTime",
                    Game.resolutionX * 0.99f, Game.gameAreaResolutionY * 0.81f, Game.resolutionY * 0.051f, "00:00", Game.font, Color.cinza70, Text.TEXT_ALIGN_RIGHT);
            Game.adicionarEntidadeFixa(messageTime);
            messageTime.addShadow(Color.cinza50);
        } else {
            messageTime.setText("00:00");
        }

    }

    public static void setBottomMessage(final String text, final int duration){


        Game.mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (Game.mainActivity == null){
                    return;
                }
                LayoutInflater inflater = Game.mainActivity.getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast,
                        (ViewGroup) Game.mainActivity.findViewById(R.id.custom_toast_container));


                TextView textView = (TextView) layout.findViewById(R.id.text);
                textView.setText(text);
                Toast toast = new Toast(Game.mainActivity.getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();


            }
        });



        // todo deletar variavel bottomTextBox

        /*

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
        */
    }

}