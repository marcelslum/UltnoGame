package com.marcelslum.ultnogame;

import android.content.res.Resources;
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
    static long numeroBolasFalsasAtingidas;
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

    static long totalPontosInclusiveRepetidosVitoria;
    static long totalPontosInclusiveRepetidosDerrota;
    static long totalEstrelasInclusiveRepetidos;
    static long numeroTotalLevelsFinalizadosDerrota;
    static long numeroTotalLevelsFinalizadosVitoria;
    static long numeroTotalAlvosAtingidosLevelsFinalizadosVitoria;
    static long numeroTotalAlvosAtingidosLevelsFinalizadosDerrota;

    static long tempoVelocidadeMediaBaixa;
    static long tempoAnguloMedioBaixo;
    static long tempoVelocidadeMediaAlta;
    static long tempoAnguloMedioAlto;


    // FIM DOS DADOS ARMAZENADOS

    static int ultimoNumeroBolasVivas = 0;
    static long ultimoNumeroBolasVivasTempoDeRegistro;
    static String TAG = "Stats";


    static int currentStatsSheet = 0;


    public final static int TEMPO_JOGO = 1;
    public final static int MEDIA_PONTOS = 2;
    public final static int MEDIA_ESTRELAS = 3;
    public final static int MEDIA_ALVOS = 4;
    public final static int VELOCIDADE_ANGULO = 5;
    public final static int VELOCIDADE = 6;
    public final static int TEMPO_VELOCIDADE = 7;
    public final static int ANGULO_AUMENTADO = 8;
    public final static int ANGULO_DIMINUIDO = 9;
    public final static int TEMPO_ANGULO = 10;
    public final static int TEMPO_BOLAS = 11;
    public final static int ALVOS_ATINGIDOS = 12;
    public final static int OUTROS_DADOS = 13;
    public final static int TODOS_DADOS = 14;
    public final static int NUMBER_OF_STATS_SHEETS = 14;


    public static void
    collectBallData(Ball ball) {

        if (ball.isFake){
            return;
        }

        Log.e(TAG, "coletando dados da bola");

        if (ball.initTempoVelocidadeMediaAlta != -1) {
            ball.tempoVelocidadeMediaAlta += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMediaAlta);
        }

        if (ball.initTempoVelocidadeMediaBaixa != -1) {
            ball.tempoVelocidadeMediaBaixa += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMediaBaixa);

        }
        
        if (ball.initTempoVelocidadeMaxima != -1) {
            ball.tempoVelocidadeMaxima += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMaxima);
        }
        if (ball.initTempoVelocidadeMinima != -1) {
            ball.tempoVelocidadeMinima += (TimeHandler.timeOfLevelPlay - ball.initTempoVelocidadeMinima);
        }

        

        if (ball.initTempoAnguloMedioAlto != -1) {

            ball.tempoAnguloMedioAlto += (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMedioAlto);

        }

        if (ball.initTempoAnguloMedioBaixo != -1) {
            Log.e(TAG, "tempoAnguloMedioBaixo adicionando " + (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMedioBaixo));
            ball.tempoAnguloMedioBaixo += (TimeHandler.timeOfLevelPlay - ball.initTempoAnguloMedioBaixo);

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
        tempoVelocidadeMediaAlta += ball.tempoVelocidadeMediaAlta;
        tempoAnguloMedioAlto += ball.tempoAnguloMedioAlto;
        tempoVelocidadeMediaBaixa += ball.tempoVelocidadeMediaBaixa;
        tempoAnguloMedioBaixo += ball.tempoAnguloMedioBaixo;
    }

    public static void saveData() {

        if (!Training.training) {
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
            SaveGame.saveGame.stats[12] += Utils.converterMilisegundosEmSegundos(tempoVelocidadeMinima);
            SaveGame.saveGame.stats[13] += Utils.converterMilisegundosEmSegundos(tempoVelocidadeMaxima);
            SaveGame.saveGame.stats[14] += Utils.converterMilisegundosEmSegundos(tempoAnguloMinimo);
            SaveGame.saveGame.stats[15] += Utils.converterMilisegundosEmSegundos(tempoAnguloMaximo);
            SaveGame.saveGame.stats[16] += Utils.converterMilisegundosEmSegundos(tempoVelocidadeMedia);
            SaveGame.saveGame.stats[17] += Utils.converterMilisegundosEmSegundos(tempoAnguloMedio);
            SaveGame.saveGame.stats[18] += Utils.converterMilisegundosEmSegundos(tempo1Bola);
            SaveGame.saveGame.stats[19] += Utils.converterMilisegundosEmSegundos(tempo2Bolas);
            SaveGame.saveGame.stats[20] += Utils.converterMilisegundosEmSegundos(tempo3Bolas);
            SaveGame.saveGame.stats[21] += Utils.converterMilisegundosEmSegundos(tempo4Bolas);
            SaveGame.saveGame.stats[22] += Utils.converterMilisegundosEmSegundos(tempo5Bolas);
            SaveGame.saveGame.stats[23] += Utils.converterMilisegundosEmSegundos(tempo6Bolas);
            SaveGame.saveGame.stats[24] += Utils.converterMilisegundosEmSegundos(tempo7Bolas);
            SaveGame.saveGame.stats[25] += Utils.converterMilisegundosEmSegundos(tempo8Bolas);
            SaveGame.saveGame.stats[26] += Utils.converterMilisegundosEmSegundos(tempo9Bolas);
            SaveGame.saveGame.stats[27] += Utils.converterMilisegundosEmSegundos(tempo10OuMaisBolas);
            SaveGame.saveGame.stats[28] += numeroBolasFalsasAtingidas;
            SaveGame.saveGame.stats[29] += Utils.converterMilisegundosEmSegundos(tempoJogadoVitoria);
            SaveGame.saveGame.stats[30] += Utils.converterMilisegundosEmSegundos(tempoJogadoDerrota);
            SaveGame.saveGame.stats[31] += atingirBolaSemMudarVelocidade;
            SaveGame.saveGame.stats[32] += alvosVerdesAtingidos;
            SaveGame.saveGame.stats[33] += alvosAzuisAtingidos;
            SaveGame.saveGame.stats[34] += alvosPretosAtingidos;
            SaveGame.saveGame.stats[35] += alvosFantasmasAtingidos;
            SaveGame.saveGame.stats[36] += alvosVermelhosAtingidos;
            SaveGame.saveGame.stats[37] += obstaculosAtingidos;
            SaveGame.saveGame.stats[38] += colisaoEntreBolas;
            SaveGame.saveGame.stats[39] += Utils.converterMilisegundosEmSegundos(tempoJogadoNaoCompletado);
            SaveGame.saveGame.stats[40] += totalPontosInclusiveRepetidosVitoria;
            SaveGame.saveGame.stats[41] += totalPontosInclusiveRepetidosDerrota;
            SaveGame.saveGame.stats[42] += totalEstrelasInclusiveRepetidos;
            SaveGame.saveGame.stats[43] += numeroTotalLevelsFinalizadosVitoria;
            SaveGame.saveGame.stats[44] += numeroTotalLevelsFinalizadosDerrota;
            SaveGame.saveGame.stats[45] += numeroTotalAlvosAtingidosLevelsFinalizadosVitoria;
            SaveGame.saveGame.stats[46] += numeroTotalAlvosAtingidosLevelsFinalizadosDerrota;
            SaveGame.saveGame.stats[47] += Utils.converterMilisegundosEmSegundos(tempoVelocidadeMediaBaixa);
            SaveGame.saveGame.stats[48] += Utils.converterMilisegundosEmSegundos(tempoAnguloMedioBaixo);
            SaveGame.saveGame.stats[49] += Utils.converterMilisegundosEmSegundos(tempoVelocidadeMediaAlta);
            SaveGame.saveGame.stats[50] += Utils.converterMilisegundosEmSegundos(tempoAnguloMedioAlto);
        }

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
        numeroBolasFalsasAtingidas = 0;
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

        totalPontosInclusiveRepetidosVitoria = 0;
        totalPontosInclusiveRepetidosDerrota = 0;
        totalEstrelasInclusiveRepetidos = 0;
        numeroTotalLevelsFinalizadosDerrota = 0;
        numeroTotalLevelsFinalizadosVitoria = 0;
        numeroTotalAlvosAtingidosLevelsFinalizadosVitoria = 0;
        numeroTotalAlvosAtingidosLevelsFinalizadosDerrota = 0;

    }

    public static void showCurrentStat() {

        Game.statsGraphs.clear();

        MessagesHandler.messageStatTittle.display();

        MessagesHandler.messageStatDescricao = new TextView("messageStatDescricao", Game.resolutionX * 0.49f,
                Game.resolutionY * 0.88f,
                Game.resolutionX * 0.75f,
                Game.resolutionY * 0.4f,
                Game.gameAreaResolutionY*0.035f,
                Game.font, Color.cinza20, Text.TEXT_ALIGN_CENTER, 0.4f);


        MessagesHandler.messageStatDescricao.display();


        float graficoX = 0f;
        float graficoY = Game.resolutionY * 0.28f;
        float graficoAltura = Game.resolutionY * 0.58f;
        float graficoComprimento = Game.resolutionX * 0.95f;

        Color corExplicacaoGrafico = Color.azul40;


        if (currentStatsSheet == TEMPO_JOGO){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.tempo_de_jogo));
            MessagesHandler.messageStatDescricao.addText("Tempo jogado de acordo com o modo em que o nível é finalizado", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Vitória", (double)SaveGame.saveGame.stats[29]);
            statsGraph.addData("Derrota", (double)SaveGame.saveGame.stats[30]);
            statsGraph.addData("Não completado", (double)SaveGame.saveGame.stats[39]);
            statsGraph.addData("Total", (double)(SaveGame.saveGame.stats[29]+SaveGame.saveGame.stats[30]+SaveGame.saveGame.stats[39]));

            statsGraph.make(false, true, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == MEDIA_PONTOS){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.pontos_stat_titulo));
            MessagesHandler.messageStatDescricao.addText("Média de pontos conquistados por nível completado.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Em vitórias", (double)SaveGame.saveGame.stats[40]/SaveGame.saveGame.stats[43]);
            statsGraph.addData("Em derrotas", (double)SaveGame.saveGame.stats[41]/SaveGame.saveGame.stats[44]);
            statsGraph.addData("Média total", ((double)SaveGame.saveGame.stats[40] + (double)SaveGame.saveGame.stats[41])/(SaveGame.saveGame.stats[43]+SaveGame.saveGame.stats[44]));

            statsGraph.make(true, false, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == MEDIA_ESTRELAS){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.estrelas_titulo));
            MessagesHandler.messageStatDescricao.addText("Média de estrelas conquistas em níveis finalizados com vitória.", corExplicacaoGrafico);


            StatsGraph statsGraph = new StatsGraph("statGraph", Game.resolutionX * 0.3f, graficoY, Game.resolutionX * 0.4f, graficoAltura);

            statsGraph.addData("Média de estrelas", (double)SaveGame.saveGame.stats[42]/SaveGame.saveGame.stats[43]);

            statsGraph.make(true, false, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == MEDIA_ALVOS){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.media_alvos));
            MessagesHandler.messageStatDescricao.addText("Média de alvos abatidos por nível completado.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Em vitórias", (double)SaveGame.saveGame.stats[45]/SaveGame.saveGame.stats[43]);
            statsGraph.addData("Em derrotas", (double)SaveGame.saveGame.stats[46]/SaveGame.saveGame.stats[44]);
            statsGraph.addData("Média total", ((double)SaveGame.saveGame.stats[45] + (double)SaveGame.saveGame.stats[46])/(SaveGame.saveGame.stats[43]+SaveGame.saveGame.stats[44]));

            statsGraph.make(true, false, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == VELOCIDADE_ANGULO){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet1));
            MessagesHandler.messageStatDescricao.addText("Número de vezes em que o ângulo e a velocidade da bola foi aumentada ou diminuída", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Velocidade Aumentada", (float)SaveGame.saveGame.stats[2]);
            statsGraph.addData("Velocidade Diminuída", (float)SaveGame.saveGame.stats[3]);
            statsGraph.addData("Ângulo Aumentado", (float)SaveGame.saveGame.stats[0]);
            statsGraph.addData("Ângulo Diminuído", (float)SaveGame.saveGame.stats[1]);

            statsGraph.make(true, false, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == VELOCIDADE){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.velocidade_titulo_stat));
            MessagesHandler.messageStatDescricao.addText("Número de vezes em que a velocidade foi alterada ou permaneceu igual no contato com a barra", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Velocidade Aumentada", (float)SaveGame.saveGame.stats[2]);
            statsGraph.addData("Velocidade Diminuída", (float)SaveGame.saveGame.stats[3]);
            statsGraph.addData("Velocidade Inalterada", (float)SaveGame.saveGame.stats[31]);

            statsGraph.make(true, false, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == TEMPO_VELOCIDADE){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.tempoVelocidade));
            MessagesHandler.messageStatDescricao.addText("Tempo em que as bolas foram mantidas nas velocidades média, máxima ou mínima.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Velocidade mínima", (double)SaveGame.saveGame.stats[12]);
            statsGraph.addData("Velocidade média baixa", (double)SaveGame.saveGame.stats[47]);
            statsGraph.addData("Velocidade média alta", (double)SaveGame.saveGame.stats[49]);
            statsGraph.addData("Velocidade máxima", (double)SaveGame.saveGame.stats[13]);

            statsGraph.make(false, true, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == ANGULO_AUMENTADO){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet2));
            MessagesHandler.messageStatDescricao.addText("Número de vezes em que o ângulo foi aumentado, conforme tipo do movimento", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph",  graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Qualquer movimento", (float)SaveGame.saveGame.stats[0]);
            statsGraph.addData("Inclinação da barra", (float)SaveGame.saveGame.stats[4]);
            statsGraph.addData("Movimento da barra", (float)SaveGame.saveGame.stats[6]);
            statsGraph.addData("Inclinação e movimento", (float)SaveGame.saveGame.stats[8]);
            statsGraph.make(true, false, true, 1f, 1f);
            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == ANGULO_DIMINUIDO) {

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet3));
            MessagesHandler.messageStatDescricao.addText("Número de vezes em que o ângulo foi diminuído, conforme tipo do movimento", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph",  graficoX, graficoY, graficoComprimento, graficoAltura);
            statsGraph.addData("Qualquer movimento", (float)SaveGame.saveGame.stats[1]);
            statsGraph.addData("Inclinação da barra", (float)SaveGame.saveGame.stats[5]);
            statsGraph.addData("Movimento da barra", (float)SaveGame.saveGame.stats[7]);
            statsGraph.addData("Inclinação e movimento", (float)SaveGame.saveGame.stats[9]);
            statsGraph.make(true, false, true, 1f, 1f);
            Game.statsGraphs.add(statsGraph);


        } else if (currentStatsSheet == TEMPO_ANGULO){

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.tempoAngulo));
            MessagesHandler.messageStatDescricao.addText("Tempo em que as bolas foram mantidas nos ângulos médio, máximo ou mínimo.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph", graficoX, graficoY, graficoComprimento, graficoAltura);

            statsGraph.addData("Ângulo mínimo", (double)SaveGame.saveGame.stats[14]);
            statsGraph.addData("Ângulo médio baixo", (double)SaveGame.saveGame.stats[48]);
            statsGraph.addData("Ângulo médio alto", (double)SaveGame.saveGame.stats[50]);
            statsGraph.addData("Ângulo máximo", (double)SaveGame.saveGame.stats[15]);

            statsGraph.make(false, true, true, 1f, 1f);

            Game.statsGraphs.add(statsGraph);

        } else if (currentStatsSheet == TEMPO_BOLAS) {

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.statSheet4));
            MessagesHandler.messageStatDescricao.addText("Tempo que foram mantidas bolas vivas, conforme número de bolas", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph",  graficoX, graficoY, graficoComprimento, graficoAltura);
            statsGraph.addData("1", (float)SaveGame.saveGame.stats[18]);
            statsGraph.addData("2", (float)SaveGame.saveGame.stats[19]);
            statsGraph.addData("3", (float)SaveGame.saveGame.stats[20]);
            statsGraph.addData("4", (float)SaveGame.saveGame.stats[21]);
            statsGraph.addData("5+", (float)(SaveGame.saveGame.stats[22] + SaveGame.saveGame.stats[23] + SaveGame.saveGame.stats[24] + SaveGame.saveGame.stats[25] + SaveGame.saveGame.stats[26] + SaveGame.saveGame.stats[27]));
            statsGraph.make(false, true, false, 1f, 1f);
            Game.statsGraphs.add(statsGraph);


        } else if (currentStatsSheet == ALVOS_ATINGIDOS) {

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.alvos_atingidos));
            MessagesHandler.messageStatDescricao.addText("Número de alvos atingidos, de acordo com o tipo.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph",  graficoX, graficoY, graficoComprimento, graficoAltura);
            statsGraph.addData("Pretos", (float)SaveGame.saveGame.stats[34]);
            statsGraph.addData("Azuis", (float)SaveGame.saveGame.stats[33]);
            statsGraph.addData("Verdes", (float)SaveGame.saveGame.stats[32]);
            statsGraph.addData("Vermelhos", (float)SaveGame.saveGame.stats[36]);
            statsGraph.addData("Fantasmas", (float)(SaveGame.saveGame.stats[35]));

            statsGraph.make(true, false, true, 1f, 1f);
            Game.statsGraphs.add(statsGraph);


        } else if (currentStatsSheet == OUTROS_DADOS) {

            if (MessagesHandler.statsTextView != null) {
                MessagesHandler.statsTextView.clearDisplay();
            }

            MessagesHandler.messageStatTittle.setText(Game.mainActivity.getResources().getString(R.string.dados_diveros));
            MessagesHandler.messageStatDescricao.addText("Outros dados diversos.", corExplicacaoGrafico);

            StatsGraph statsGraph = new StatsGraph("statGraph",  graficoX, graficoY, graficoComprimento, graficoAltura);
            statsGraph.addData("Obstáculos atingidos", (float)SaveGame.saveGame.stats[37]);
            statsGraph.addData("Colisão entre bolas", (float)SaveGame.saveGame.stats[38]);
            statsGraph.addData("Bolas falsas atingidas", (float)SaveGame.saveGame.stats[28]);
            statsGraph.make(true, false, true, 1f, 1f);
            Game.statsGraphs.add(statsGraph);


        } else if (currentStatsSheet == TODOS_DADOS) {

            Game.showBlackFrameTransition(700);

            MessagesHandler.messageStatTittle.clearDisplay();
            MessagesHandler.messageStatDescricao.clearDisplay();

            MessagesHandler.statsTextView = new TextView("statsTextView", Game.resolutionX * 0.1f,
                    Game.resolutionY * 0.2f,
                    Game.resolutionX * 0.8f,
                    Game.resolutionY * 0.8f,
                    Game.gameAreaResolutionY*0.05f,
                    Game.font, new Color(0f, 0f, 0f, 1f), Text.TEXT_ALIGN_LEFT, 0.4f);

            Resources resources = Game.getContext().getResources();
            MessagesHandler.statsTextView.addText(resources.getString(R.string.estatisticaTitulo), Color.azul);
            MessagesHandler.statsTextView.addText(".", Color.transparente);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat0) + " " + SaveGame.saveGame.stats[0], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat1)+ " " + SaveGame.saveGame.stats[1], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat2)+ " " + SaveGame.saveGame.stats[2], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat3)+ " " + SaveGame.saveGame.stats[3], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat4)+ " " + SaveGame.saveGame.stats[4], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat5)+ " " + SaveGame.saveGame.stats[5], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat6)+ " " + SaveGame.saveGame.stats[6], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat7)+ " " + SaveGame.saveGame.stats[7], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat8)+ " " + SaveGame.saveGame.stats[8], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat9)+ " " + SaveGame.saveGame.stats[9], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat10)+ " " + SaveGame.saveGame.stats[10], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat11)+ " " + SaveGame.saveGame.stats[11], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat12)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[12]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat13)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[13]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat14)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[14]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat15)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[15]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat16)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[16]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat17)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[17]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat18)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[18]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat19)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[19]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat20)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[20]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat21)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[21]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat22)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[22]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat23)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[23]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat24)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[24]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat25)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[25]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat26)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[26]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat27)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[27]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat28)+ " " + SaveGame.saveGame.stats[28], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat29)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[29]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat30)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[30]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat31)+ " " + SaveGame.saveGame.stats[31], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat32)+ " " + SaveGame.saveGame.stats[32], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat33)+ " " + SaveGame.saveGame.stats[33], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat34)+ " " + SaveGame.saveGame.stats[34], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat35)+ " " + SaveGame.saveGame.stats[35], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat36)+ " " + SaveGame.saveGame.stats[36], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat37)+ " " + SaveGame.saveGame.stats[37], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat38)+ " " + SaveGame.saveGame.stats[38], Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);
            MessagesHandler.statsTextView.addText(resources.getString(R.string.stat39)+ " " + Utils.getTimeTextFromMiliSeconds(SaveGame.saveGame.stats[39]), Color.cinza20);

            MessagesHandler.statsTextView.display();


        }





    }
}
