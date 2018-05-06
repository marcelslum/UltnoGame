package com.marcelslum.ultnogame;

import android.util.Log;

public class Stats {

    public static int STATS_DATABASE_SIZE = 60;

    // DADOS ARMAZENADOS

    static long anguloAumentado;
    static long anguloDiminuido;
    static long velocidadeAumentada;
    static long velocidadeDiminuida;
    static long anguloAumentadoInclinacao;
    static long anguloDiminuidoInclinacao;
    static long anguloAumentadoMovimento;
    static long anguloDiminuidoMovimento;
    static long anguloAumentadoMovimentoInclinacao;
    static long anguloDiminuidoMovimentoInclinacao;
    static long velocidadeAumentadaAnguloAumentadoInclinacao;
    static long velocidadeDiminuidaAnguloDiminuidoInclinacao;

    static long tempoVelocidadeMinima;
    static long tempoVelocidadeMaxima;
    static long tempoAnguloMinimo;
    static long tempoAnguloMaximo;
    static long tempoVelocidadeMedia;
    static long tempoAnguloMedio;

    static long tempo1Bola;
    static long tempo2Bolas;
    static long tempo3Bolas;
    static long tempo4Bolas;
    static long tempo5Bolas;
    static long tempo6Bolas;
    static long tempo7Bolas;
    static long tempo8Bolas;
    static long tempo9Bolas;
    static long tempo10OuMaisBolas;
    static long numeroBolasFalsasAtinjidas;
    static long tempoJogadoVitoria;
    static long tempoJogadoDerrota;

    static long atingirBolaSemMudarVelocidade;

    static long alvosVerdesAtingidos;
    static long alvosAzuisAtingidos;
    static long alvosPretosAtingidos;
    static long alvosFantasmasAtingidos;
    static long alvosVermelhosAtingidos;
    static long obstaculosAtingidos;
    static long colisaoEntreBolas;

    static long tempoJogadoNaoCompletado;

    // FIM DOS DADOS ARMAZENADOS

    static int ultimoNumeroBolasVivas = 0;
    static long ultimoNumeroBolasVivasTempoDeRegistro;
    static String TAG = "Stats";


    static int currentStatsSheet = 0;

    public final static int VELOCIDADE = 1;
    public final static int ANGULO_AUMENTADO = 2;
    public final static int ANGULO_DIMINUIDO = 3;
    public final static int TEMPO_BOLAS = 4;
    public final static int NUMBER_OF_STATS_SHEETS = 4;


    public static void
    collectBallData(Ball ball) {

        if (ball.isFake){
            return;
        }

        Log.e(TAG, "coletando dados da bola");


        Log.e(TAG, "initTempoVelocidadeMedia " + ball.initTempoVelocidadeMedia);

        if (ball.initTempoVelocidadeMedia != -1) {
            Log.e(TAG, "tempoVelocidadeMedia adicionando " + (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMedia));
            ball.tempoVelocidadeMedia += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMedia);
            Log.e(TAG, "tempoVelocidadeMedia depois " + tempoVelocidadeMedia);
        }
        if (ball.initTempoVelocidadeMaxima != -1) {
            ball.tempoVelocidadeMaxima += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMaxima);
        }
        if (ball.initTempoVelocidadeMinima != -1) {
            ball.tempoVelocidadeMinima += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMinima);
        }


        Log.e(TAG, "initTempoAnguloMedio " + ball.initTempoAnguloMedio);

        if (ball.initTempoAnguloMedio != -1) {
            Log.e(TAG, "tempoAnguloMedio adicionando " + (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMedio));
            ball.tempoAnguloMedio += (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMedio);
            Log.e(TAG, "tempoAnguloMedio depois " + tempoAnguloMedio);
        }
        if (ball.initTempoAnguloMaximo != -1) {
            ball.tempoAnguloMaximo += (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMaximo);
        }
        if (ball.initTempoAnguloMinimo != -1) {
            ball.tempoAnguloMinimo += (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMinimo);
        }

        tempoVelocidadeMinima += ball.tempoVelocidadeMinima;
        tempoVelocidadeMaxima += ball.tempoVelocidadeMaxima;
        tempoAnguloMinimo += ball.tempoAnguloMinimo;
        tempoAnguloMaximo += ball.tempoAnguloMaximo;
        tempoVelocidadeMedia += ball.tempoVelocidadeMedia;
        tempoAnguloMedio += ball.tempoAnguloMedio;
    }

    public static void saveData() {
        SaveGame.saveGame.stats[0] += anguloAumentado;
        SaveGame.saveGame.stats[1] += anguloDiminuido;
        SaveGame.saveGame.stats[2] += velocidadeAumentada;
        SaveGame.saveGame.stats[3] += velocidadeDiminuida;
        SaveGame.saveGame.stats[4] += anguloAumentadoInclinacao;
        SaveGame.saveGame.stats[5] += anguloDiminuidoInclinacao;
        SaveGame.saveGame.stats[6] += anguloAumentadoMovimento;
        SaveGame.saveGame.stats[7] += anguloDiminuidoMovimento;
        SaveGame.saveGame.stats[8] += anguloAumentadoMovimentoInclinacao;
        SaveGame.saveGame.stats[9] += anguloDiminuidoMovimentoInclinacao;
        SaveGame.saveGame.stats[10] += velocidadeAumentadaAnguloAumentadoInclinacao;
        SaveGame.saveGame.stats[11] += velocidadeDiminuidaAnguloDiminuidoInclinacao;
        SaveGame.saveGame.stats[12] += tempoVelocidadeMinima;
        SaveGame.saveGame.stats[13] += tempoVelocidadeMaxima;
        SaveGame.saveGame.stats[14] += tempoAnguloMinimo;
        SaveGame.saveGame.stats[15] += tempoAnguloMaximo;
        SaveGame.saveGame.stats[16] += tempoVelocidadeMedia;
        SaveGame.saveGame.stats[17] += tempoAnguloMedio;
        SaveGame.saveGame.stats[18] += tempo1Bola;
        SaveGame.saveGame.stats[19] += tempo2Bolas;
        SaveGame.saveGame.stats[20] += tempo3Bolas;
        SaveGame.saveGame.stats[21] += tempo4Bolas;
        SaveGame.saveGame.stats[22] += tempo5Bolas;
        SaveGame.saveGame.stats[23] += tempo6Bolas;
        SaveGame.saveGame.stats[24] += tempo7Bolas;
        SaveGame.saveGame.stats[25] += tempo8Bolas;
        SaveGame.saveGame.stats[26] += tempo9Bolas;
        SaveGame.saveGame.stats[27] += tempo10OuMaisBolas;
        SaveGame.saveGame.stats[28] += numeroBolasFalsasAtinjidas;
        SaveGame.saveGame.stats[29] += tempoJogadoVitoria;
        SaveGame.saveGame.stats[30] += tempoJogadoDerrota;
        SaveGame.saveGame.stats[31] += atingirBolaSemMudarVelocidade;
        SaveGame.saveGame.stats[32] += alvosVerdesAtingidos;
        SaveGame.saveGame.stats[33] += alvosAzuisAtingidos;
        SaveGame.saveGame.stats[34] += alvosPretosAtingidos;
        SaveGame.saveGame.stats[35] += alvosFantasmasAtingidos;
        SaveGame.saveGame.stats[36] += alvosVermelhosAtingidos;
        SaveGame.saveGame.stats[37] += obstaculosAtingidos;
        SaveGame.saveGame.stats[38] += colisaoEntreBolas;
        SaveGame.saveGame.stats[39] += tempoJogadoNaoCompletado;

        clearData();
    }

    public static void clearData() {

        ultimoNumeroBolasVivas = 0;
        ultimoNumeroBolasVivasTempoDeRegistro = -1;

        anguloAumentado = 0;
        anguloDiminuido = 0;
        velocidadeAumentada = 0;
        velocidadeDiminuida = 0;
        anguloAumentadoInclinacao = 0;
        anguloDiminuidoInclinacao = 0;
        anguloAumentadoMovimento = 0;
        anguloDiminuidoMovimento = 0;
        anguloAumentadoMovimentoInclinacao = 0;
        anguloDiminuidoMovimentoInclinacao = 0;
        velocidadeAumentadaAnguloAumentadoInclinacao = 0;
        velocidadeDiminuidaAnguloDiminuidoInclinacao = 0;
        tempoVelocidadeMinima = 0;
        tempoVelocidadeMaxima = 0;
        tempoAnguloMinimo = 0;
        tempoAnguloMaximo = 0;
        tempoVelocidadeMedia = 0;
        tempoAnguloMedio = 0;
        tempo1Bola = 0;
        tempo2Bolas = 0;
        tempo3Bolas = 0;
        tempo4Bolas = 0;
        tempo5Bolas = 0;
        tempo6Bolas = 0;
        tempo7Bolas = 0;
        tempo8Bolas = 0;
        tempo9Bolas = 0;
        tempo10OuMaisBolas = 0;
        numeroBolasFalsasAtinjidas = 0;
        tempoJogadoVitoria = 0;
        tempoJogadoDerrota = 0;
        atingirBolaSemMudarVelocidade = 0;
        alvosVerdesAtingidos = 0;
        alvosAzuisAtingidos = 0;
        alvosPretosAtingidos = 0;
        alvosFantasmasAtingidos = 0;
        alvosVermelhosAtingidos = 0;
        obstaculosAtingidos = 0;
        colisaoEntreBolas = 0;
        tempoJogadoNaoCompletado = 0;
    }

    public static void showCurrentStat() {

        Game.statsGraphs.clear();

        MessagesHandler.messageStatTittle.display();


        if (currentStatsSheet == VELOCIDADE){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet1));

            

            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.05f, Game.gameAreaResolutionY * 0.2f, Game.resolutionX * 0.95f, Game.gameAreaResolutionY * 0.75f);

            statsGraph.addData("Velocidade Aumentada", (float)SaveGame.saveGame.stats[2]);
            statsGraph.addData("Velocidade Diminuida", (float)SaveGame.saveGame.stats[3]);

            statsGraph.make(true, false);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == ANGULO_AUMENTADO){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet2));

            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.05f, Game.gameAreaResolutionY * 0.2f, Game.resolutionX * 0.95f, Game.gameAreaResolutionY * 0.75f);

            statsGraph.addData("Ângulo aumentado", (float)SaveGame.saveGame.stats[0]);
            statsGraph.addData("Ângulo aumentado pela inclinação", (float)SaveGame.saveGame.stats[4]);
            statsGraph.addData("Ângulo aumentado pelo movimento", (float)SaveGame.saveGame.stats[6]);
            statsGraph.addData("Ângulo aumentado pelo movimento e inclinação", (float)SaveGame.saveGame.stats[8]);
            statsGraph.make(true, false);
            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == ANGULO_DIMINUIDO) {

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet3));

            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.05f, Game.gameAreaResolutionY * 0.2f, Game.resolutionX * 0.95f, Game.gameAreaResolutionY * 0.75f);
            statsGraph.addData("Ângulo diminuído", (float)SaveGame.saveGame.stats[1]);
            statsGraph.addData("Ângulo diminuído pela inclinação", (float)SaveGame.saveGame.stats[5]);
            statsGraph.addData("Ângulo diminuído pelo movimento", (float)SaveGame.saveGame.stats[7]);
            statsGraph.addData("Ângulo diminuído pelo movimento e inclinação", (float)SaveGame.saveGame.stats[9]);
            statsGraph.make(true, false);
            Game.statsGraphs.add(statsGraph);


        } else if (currentStatsSheet == TEMPO_BOLAS) {

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet3));

            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.05f, Game.gameAreaResolutionY * 0.2f, Game.resolutionX * 0.95f, Game.gameAreaResolutionY * 0.75f);
            statsGraph.addData("1", (float)SaveGame.saveGame.stats[18]);
            statsGraph.addData("2", (float)SaveGame.saveGame.stats[19]);
            statsGraph.addData("3", (float)SaveGame.saveGame.stats[20]);
            statsGraph.addData("4", (float)SaveGame.saveGame.stats[21]);
            statsGraph.addData("5", (float)SaveGame.saveGame.stats[22]);
            statsGraph.addData("6", (float)SaveGame.saveGame.stats[23]);
            statsGraph.addData("7", (float)SaveGame.saveGame.stats[24]);
            statsGraph.addData("8", (float)SaveGame.saveGame.stats[25]);
            statsGraph.addData("9", (float)SaveGame.saveGame.stats[26]);
            statsGraph.addData("10+", (float)SaveGame.saveGame.stats[27]);
            statsGraph.make(false, true);
            Game.statsGraphs.add(statsGraph);


        }





    }
}
