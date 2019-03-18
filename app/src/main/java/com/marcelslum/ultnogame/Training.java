package com.marcelslum.ultnogame;

import android.util.Log;

public class Training {


    public static boolean training = false;
    public static int trainingNumber = 0;
    public static long trainingBarCollisionInit = Long.MAX_VALUE;
    public static int tentativaCertaTreinamento = 0;
    public static boolean treinamentoSucesso = false;
    public static int TREINAMENTO_AUMENTAR_VELOCIDADE = 1;
    public static int TREINAMENTO_DIMINUIR_VELOCIDADE = 3;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO = 5;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO = 7;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO = 9;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO = 11;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO = 13;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO = 15;
    public static int TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO = 17;
    public static int TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO = 19;
    public static int TREINAMENTO_AUMENTAR_VELOCIDADE_OPOSTO = 2;
    public static int TREINAMENTO_DIMINUIR_VELOCIDADE_OPOSTO = 4;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO_OPOSTO = 6;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO_OPOSTO = 8;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_OPOSTO = 10;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_OPOSTO = 12;
    public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO = 14;
    public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO = 16;
    public static int TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO_OPOSTO = 18;
    public static int TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO_OPOSTO = 20;

    static void resetTrainingEntities(){
        /*
        public static int TREINAMENTO_AUMENTAR_VELOCIDADE = 1; ok
        public static int TREINAMENTO_DIMINUIR_VELOCIDADE = 3; ok
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO = 5; ok
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO = 7;ok
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO = 9;
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO = 11;
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO = 13; ok
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO = 15;ok
        public static int TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO = 17;ok
        public static int TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO = 19; ok

        public static int TREINAMENTO_AUMENTAR_VELOCIDADE_OPOSTO = 2;
        public static int TREINAMENTO_DIMINUIR_VELOCIDADE_OPOSTO = 4;
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO_OPOSTO = 6;
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO_OPOSTO = 8;
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_OPOSTO = 10;
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_OPOSTO = 12;
        public static int TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO = 14;
        public static int TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO = 16;
        public static int TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO_OPOSTO = 18;
        public static int TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO_OPOSTO = 20;

        */

        Game.balls.get(0).isCollidable = true;
        Game.balls.get(0).isMovable = true;
        Game.balls.get(0).isAlive = true;
        Game.balls.get(0).increaseAlpha(500,1f);

        Game.balls.get(0).accumulatedTranslateX = 0f;
        Game.balls.get(0).accumulatedTranslateY = 0f;
        Game.bars.get(0).accumulatedTranslateX = 0f;

        Game.balls.get(0).dvx = Game.balls.get(0).initialDVX;
        Game.balls.get(0).dvy = Game.balls.get(0).initialDVY;
        Game.bars.get(0).dvx = Game.bars.get(0).initialDVX;


        Game.bars.get(0).isMovable = true;

        // TREINAMENTO DIRETO
        if (trainingNumber == TREINAMENTO_AUMENTAR_VELOCIDADE
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO
                || trainingNumber == TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO){
            Game.balls.get(0).x = Game.resolutionX * 0.05f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.bars.get(0).x = Game.resolutionX * 0.7f;
        }

        if (trainingNumber == TREINAMENTO_DIMINUIR_VELOCIDADE
                || trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO
                || trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO
                || trainingNumber == TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO){
            Game.balls.get(0).x = Game.resolutionX * 0.05f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.bars.get(0).x = Game.resolutionX * 0.5f;
        }

        if (trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO){
            Game.balls.get(0).x = Game.resolutionX * 0.05f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.bars.get(0).x = Game.resolutionX * 0.4f;
        }

        //TREINAMENTO OPOSTO
        if (trainingNumber == TREINAMENTO_AUMENTAR_VELOCIDADE_OPOSTO
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_MOVIMENTO_OPOSTO
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO
                || trainingNumber == TREINAMENTO_AUMENTAR_VELOCIDADE_AUMENTANDO_ANGULO_COM_INCLINACAO_OPOSTO){
            Game.balls.get(0).x = Game.resolutionX * 0.93f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.balls.get(0).dvx *= -1;
            Game.bars.get(0).x = Game.resolutionX * 0.04f;
        }

        if (trainingNumber == TREINAMENTO_DIMINUIR_VELOCIDADE_OPOSTO
                || trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_MOVIMENTO_OPOSTO
                || trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_E_MOVIMENTO_OPOSTO
                || trainingNumber == TREINAMENTO_DIMINUIR_VELOCIDADE_DIMINUINDO_ANGULO_COM_INCLINACAO_OPOSTO){
            Game.balls.get(0).x = Game.resolutionX * 0.93f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.balls.get(0).dvx *= -1;
            Game.bars.get(0).x = Game.resolutionX * 0.3f;
        }

        if (trainingNumber == TREINAMENTO_AUMENTAR_ANGULO_COM_INCLINACAO_OPOSTO
                || trainingNumber == TREINAMENTO_DIMINUIR_ANGULO_COM_INCLINACAO_OPOSTO){
            Game.balls.get(0).x = Game.resolutionX * 0.93f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.balls.get(0).dvx *= -1;
            Game.bars.get(0).x = Game.resolutionX * 0.25f;
        }

        if (trainingNumber > 20){
            Game.balls.get(0).x = Game.resolutionX * 0.93f;
            Game.balls.get(0).y = Game.gameAreaResolutionY * 0.1f;
            Game.balls.get(0).dvx *= -1;
            Game.bars.get(0).x = Game.resolutionX * 0.25f;
        }

        Game.balls.get(0).checkTransformations(false);
        Game.bars.get(0).checkTransformations(false);

    }

    static void checkTrainingFinished(){

        Game.resultadoTreinamentoAnotado = false;

        if (treinamentoSucesso){

            treinamentoSucesso = false;

            tentativaCertaTreinamento += 1;

            if (tentativaCertaTreinamento < 1){

                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                    /*
                    MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.sucesso));
                    MessagesHandler.messageTrainingState.setColor(Color.azulCheio);
                    Utils.createAnimation3v(MessagesHandler.messageTrainingState, "alpha","alpha", 800, 0f, 1f, 0.5f, 0.7f, 1f, 1f, true, true).start();
                    */
                }
                if (MessagesHandler.messageTrainingState2 != null) {
                    MessagesHandler.messageTrainingState2.setText(Game.getContext().getResources().getString(R.string.tentativa) + " " + (tentativaCertaTreinamento) + " " + Game.getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
                }

                Game.stopAllGameEntities();
                GameStateHandler.setGameState(GameStateHandler.GAME_STATE_NOVA_TENTATIVA_TREINAMENTO);

            } else {

                if (MessagesHandler.messageTrainingState != null) {
                    MessagesHandler.messageTrainingState.clearDisplay();
                    /*
                    MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.sucesso));
                    MessagesHandler.messageTrainingState.setColor(Color.azulCheio);
                    Utils.createAnimation3v(MessagesHandler.messageTrainingState, "alpha","alpha", 800, 0f, 1f, 0.5f, 0.7f, 1f, 1f, true, true).start();
                    */
                }

                Game.stopAllGameEntities();
                //Game.reduceAllGameEntitiesAlpha(500);
                trainingNumber += 1;

                if (trainingNumber <= 1) {//20
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_MENU_DURANTE_TREINAMENTO);
                } else {
                    GameStateHandler.setGameState(GameStateHandler.GAME_STATE_FINAL_TREINAMENTO);
                }
            }
        } else {
            Game.stopAllGameEntities();


            if (MessagesHandler.messageTrainingState != null) {
                MessagesHandler.messageTrainingState.clearDisplay();
                /*
                MessagesHandler.messageTrainingState.setText(getContext().getResources().getString(R.string.errou));
                MessagesHandler.messageTrainingState.setColor(Color.vermelhoCheio);
                Utils.createAnimation3v(MessagesHandler.messageTrainingState, "alpha","alpha", 1000, 0f, 1f, 0.5f, 0.7f, 1f, 1f, true, true).start();
                */
            }
            if (MessagesHandler.messageTrainingState2 != null) {
                MessagesHandler.messageTrainingState2.setText(Game.getContext().getResources().getString(R.string.tentativa) + " " + (tentativaCertaTreinamento) + " " + Game.getContext().getResources().getString(R.string.de_como_em_1_de_3) + " " + 3);
            }


            GameStateHandler.setGameState(GameStateHandler.GAME_STATE_NOVA_TENTATIVA_TREINAMENTO);



        }
    }

    public final static String TAG = "Training";

    public static void setMenuDuranteTreinamentoMessage() {

        Log.e(TAG, "setMenuDuranteTreinamentoMessage number " + trainingNumber);

        switch (trainingNumber) {
            case 1:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento1), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento1b), Color.cinza20);
                break;
            case 2:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento2), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento2b), Color.cinza20);
                break;
            case 3:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento3), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento3b), Color.cinza20);
                break;
            case 4:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento4), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento4b), Color.cinza20);
                break;
            case 5:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento5), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento5b), Color.cinza20);
                break;
            case 6:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento6), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento6b), Color.cinza20);
                break;
            case 7:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento7), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento7b), Color.cinza20);
                break;
            case 8:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento8), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento8b), Color.cinza20);
                break;
            case 9:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento9), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento9b), Color.cinza20);
                break;
            case 10:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento10), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento10b), Color.cinza20);
                break;
            case 11:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento11), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento11b), Color.cinza20);
                break;
            case 12:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento12), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento12b), Color.cinza20);
                break;
            case 13:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento13), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento13b), Color.cinza20);
                break;
            case 14:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento14), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento14b), Color.cinza20);
                break;
            case 15:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento15), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento15b), Color.cinza20);
                break;
            case 16:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento16), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento16b), Color.cinza20);
                break;
            case 17:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento17), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento17b), Color.cinza20);
                break;
            case 18:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento18), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento18b), Color.cinza20);
                break;
            case 19:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento19), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento19b), Color.cinza20);
                break;
            case 20:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento20), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento20b), Color.cinza20);
                break;
            default:
                MessagesHandler.messageExplicacaoDuranteTreinamento.clearTexts();
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento21), Color.azul40, 1.2f);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(".", Color.transparente);
                MessagesHandler.messageExplicacaoDuranteTreinamento.addText(Game.getContext().getResources().getString(R.string.menuDuranteTrainamento21b), Color.cinza20);
                break;
        }
    }
}
