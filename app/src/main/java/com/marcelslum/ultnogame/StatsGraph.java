package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public class StatsGraph extends Entity{


    ArrayList<Rectangle> linhas;
    ArrayList<Rectangle> dados;
    ArrayList<Double> valoresDouble;
    ArrayList<Text> rotulos;
    ArrayList<String> linhasString;
    ArrayList<String> colunasString;
    ArrayList<TextBox> linhasTextBox;
    ArrayList<TextBox> colunasTextBox;
    Rectangle fundo;

    Rectangle linhaC;
    Rectangle linhaM;
    Rectangle linhaB;



    float width;
    float height;

    static String TAG = "StatsGraph";

    StatsGraph(String name, float x, float y, float width, float height) {
        super(name, x, y, Entity.TYPE_OTHER);

        this.width = width;
        this.height = height;

        linhas = new ArrayList<>();
        dados = new ArrayList<>();
        valoresDouble = new ArrayList<>();
        rotulos = new ArrayList<>();
        linhasString = new ArrayList<>();
        colunasString = new ArrayList<>();
        linhasTextBox = new ArrayList<>();
        colunasTextBox = new ArrayList<>();
    }


    public void addData(String name, double value){
        colunasString.add(name);
        valoresDouble.add(value);
    }

    public void make(boolean exibirNomeLinhas, boolean valoresEmTempo, boolean exibirValoresEmInteger, float variacaoTamanhoTextoColuna, float variacaoPosicaoTextoColuna){

        double maiorValorDouble = 0;
        if (valoresDouble.size() > 0) {
            for (int i = 0; i < valoresDouble.size(); i++) {
                Log.e(TAG, "valor " + valoresDouble.get(i));
                if (valoresDouble.get(i) > maiorValorDouble) maiorValorDouble = valoresDouble.get(i);
            }
        }




        float comprimentoTextoLinhas = width * 0.1f;
        float tamanhoTextoColunas = height * 0.2f;
        float comprimentoRetanguloDados = (width * 0.9f) / (float) valoresDouble.size();
        float alturaColunas = height - tamanhoTextoColunas;

        fundo = new Rectangle("fundo",
                0,
                y - (Game.resolutionY * 0.06f),
                Rectangle.TYPE_OTHER,
                Game.resolutionX,
                height + (Game.resolutionY * 0.06f),
                -1,
                Color.cinza85);


        linhaC = new Rectangle("linhaC",
                0,
                fundo.y,
                Rectangle.TYPE_OTHER,
                Game.resolutionX,
                (Game.resolutionY * 0.01f),
                -1,
                Color.cinza83);

        linhaB = new Rectangle("linhaB",
                0,
                fundo.y + fundo.height - ((Game.resolutionY * 0.009f)),
                Rectangle.TYPE_OTHER,
                Game.resolutionX,
                (Game.resolutionY * 0.01f),
                -1,
                Color.cinza83);


        for (int i = 0; i < valoresDouble.size(); i++) {

            double valorASerConsiderado = valoresDouble.get(i);
            if (valorASerConsiderado == 0f) valorASerConsiderado = 0.01f;




            if (valoresEmTempo){
                valorASerConsiderado = valorASerConsiderado;
                if (maiorValorDouble != 0) {
                    maiorValorDouble = maiorValorDouble;
                }
            } else if (exibirValoresEmInteger){
                valorASerConsiderado = Math.floor(valorASerConsiderado);
                if (maiorValorDouble != 0) {
                    maiorValorDouble = Math.floor(maiorValorDouble);
                }
            }

            float alturaDoValor = (float)(alturaColunas * (valorASerConsiderado / maiorValorDouble));

            if (maiorValorDouble == 0){
                alturaDoValor = 0.1f;
            }

            Color color;
            if (Stats.currentStatsSheet == Stats.ALVOS_ATINGIDOS){
                switch (i) {
                    case 0:
                        color = Color.cinza10;
                        break;
                    case 1:
                        color = Color.azul40;
                        break;
                    case 2:
                        color = Color.verde40;
                        break;
                    case 3:
                        color = Color.vermelho40;
                        break;
                    case 4:
                        color = Color.cinza40;
                        break;
                    default:
                        color = new Color(Utils.getRandonFloat(0f, 1f), Utils.getRandonFloat(0f, 1f), Utils.getRandonFloat(0f, 1f), 1f);
                        break;
                }

            } else {
                switch (i) {
                    case 0:
                        color = Color.azul;
                        break;
                    case 1:
                        color = Color.verde40;
                        break;
                    case 2:
                        color = Color.vermelhoClaro;
                        break;
                    case 3:
                        color = Color.verdeClaro;
                        break;
                    case 4:
                        color = Color.cinza40;
                        break;
                    case 5:
                        color = Color.roxoCheio;
                        break;
                    case 6:
                        color = Color.amareloCheio;
                        break;
                    case 7:
                        color = Color.laranjaCheio;
                        break;
                    case 8:
                        color = Color.azul40;
                        break;
                    case 9:
                        color = Color.rosaCheio;
                        break;
                    case 10:
                        color = Color.vermelhoCheio;
                        break;
                    default:
                        color = new Color(Utils.getRandonFloat(0f, 1f), Utils.getRandonFloat(0f, 1f), Utils.getRandonFloat(0f, 1f), 1f);
                        break;
                }
            }


            Rectangle dado = new Rectangle("dado"+i,
                    x + (comprimentoTextoLinhas + (comprimentoRetanguloDados * i) + (comprimentoRetanguloDados * 0.1f)),
                    (float)(y + (alturaColunas - alturaDoValor)),
                    Rectangle.TYPE_OTHER,
                    (float)(comprimentoRetanguloDados - (comprimentoRetanguloDados * 0.2f)),
                    (float)alturaDoValor,
                    -1,
                    color);

            if (valoresDouble.get(i) != 0) {
                Utils.createAnimation2v(dado, "animDadoScale", "scaleY", 500, 0f, 0f, 1f, 1f, false, true).start();
                Utils.createAnimation2v(dado, "animDadoTranslate", "translateY", 500, 0f, (dado.height / 2f), 1f, 1f, false, true).start();
            }

            //dado.logDataForDebug("dado ");
            /*
            dado.setMultiColor(
                    Color.branco,
                    color,
                    color,
                    color
            );

            dado.addTopRectangle(0.9f, Color.vermelhoCheio, Color.transparente, Color.vermelhoCheio, Color.transparente,
                    0.1f, Game.resolutionX * 0.002f, Game.resolutionX * 0.0021f, Color.pretoCheio);
            */

            dados.add(dado);

            TextBox textBox = new TextBoxBuilder("textBox"+i)
                    .position(dado.x + (((dado.width * 0.8f) / 2f)*variacaoPosicaoTextoColuna), y + alturaColunas)
                    .width(comprimentoRetanguloDados * 0.9f)
                    .size(Game.gameAreaResolutionY*0.033f * variacaoTamanhoTextoColuna)
                    .text(colunasString.get(i))
                    .setTextAlign(Text.TEXT_ALIGN_CENTER)
                    .isHaveFrame(false)
                    .isHaveArrowContinue(false)
                    .setTextColor(Color.cinza20)
                    .build();

            colunasTextBox.add(textBox);

            float textoRotuloAltura = Game.gameAreaResolutionY*0.038f;


            String textoAExibir = valoresDouble.get(i).toString();

            if (valoresEmTempo){
                textoAExibir = Utils.getTimeTextFromSeconds((long)(Math.floor(valoresDouble.get(i))));
                //textoAExibir = Utils.getTimeTextFromMiliSeconds((long)(Math.floor(valoresDouble.get(i))));
            } else if (exibirValoresEmInteger){
                textoAExibir = String.valueOf((int)(Math.floor(valoresDouble.get(i))));
            }


            Text rotulo = new Text("rotulo"+i,
                    textBox.x + (comprimentoRetanguloDados * 0.05f),
                    dado.y - (textoRotuloAltura * 1.5f),
                    textoRotuloAltura,
                    textoAExibir,
                    Game.font,
                    Color.pretoCheio,
                    Text.TEXT_ALIGN_CENTER);

            if (valoresDouble.get(i) != 0d || valoresDouble.get(i) != 0) {
                Utils.createAnimation2v(rotulo, "animDadoTranslate", "translateY", 500, 0f, dado.height, 1f, 1f, false, true).start();
                Utils.createAnimation2v(rotulo, "animDadoAlpha", "alpha", 500, 0f, 0f, 1f, 1f, false, true).start();
            }

            rotulos.add(rotulo);
        }


        Log.e(TAG, "maiorValorDouble "+maiorValorDouble);

        if (maiorValorDouble != 0d) {

            float[] valoresLinhas;
            if (maiorValorDouble <= 10) {
                valoresLinhas = new float[]{0f, 2f, 4f, 6f, 8f, 10};
            } else if (maiorValorDouble <= 20) {
                valoresLinhas = new float[]{0f, 5f, 10f, 15f, 20f};
            } else if (maiorValorDouble <= 50) {
                valoresLinhas = new float[]{0f, 10f, 20f, 30f, 40f, 50f};
            } else if (maiorValorDouble <= 100) {
                valoresLinhas = new float[]{0f, 20f, 40f, 60f, 80f, 100f};
            } else if (maiorValorDouble <= 1000) {
                valoresLinhas = new float[]{0f, 200f, 400f, 600f, 800f, 1000f};
            } else if (maiorValorDouble <= 10000) {
                valoresLinhas = new float[]{0f, 2000f, 4000f, 6000f, 8000f, 10000f};
            } else if (maiorValorDouble <= 100000) {
                valoresLinhas = new float[]{0f, 20000f, 40000f, 60000f, 80000f, 100000f};
            } else if (maiorValorDouble <= 1000000) {
                valoresLinhas = new float[]{0f, 2000000f, 4000000f, 6000000f, 8000000f, 10000000f};
            } else {
                valoresLinhas = new float[]{0f, 200f, 400f, 600f, 800f, 1000f};
            }


            linhaM = new Rectangle("linhaM",
                    x + comprimentoTextoLinhas,
                    y + alturaColunas - (Game.gameAreaResolutionY * 0.005f),
                    Rectangle.TYPE_OTHER,
                    width - comprimentoTextoLinhas,
                    Game.gameAreaResolutionY * 0.0075f,
                    -1,
                    Color.cinza50);

            for (int i = 0; i < valoresLinhas.length; i++) {

                if (valoresLinhas[i] > maiorValorDouble) {
                    break;
                }

                double maiorValorASerConsiderado = maiorValorDouble;
                if (maiorValorASerConsiderado == 0) {
                    maiorValorASerConsiderado = 0.1f;
                }

                double posicaoLinha = y + alturaColunas - (alturaColunas * (valoresLinhas[i] / maiorValorASerConsiderado)) - (Game.gameAreaResolutionY * 0.005f);
                double alturaTextoLinha = Game.gameAreaResolutionY * 0.035f;

                Rectangle dado = new Rectangle("linha" + i,
                        x + comprimentoTextoLinhas,
                        (float) posicaoLinha,
                        Rectangle.TYPE_OTHER,
                        width - comprimentoTextoLinhas,
                        Game.gameAreaResolutionY * 0.005f,
                        -1,
                        Color.cinza60);
                linhas.add(dado);

                //dado.logDataForDebug("linha ");

                if (exibirNomeLinhas) {
                    TextBox textBox = new TextBoxBuilder("textBoxLinha" + i)
                            .position(x + (comprimentoTextoLinhas * 0.5f),
                                    (float) (posicaoLinha - (alturaTextoLinha * 1.5f)))
                            .width(comprimentoTextoLinhas)
                            .size((float) alturaTextoLinha)
                            .text(String.valueOf((int) valoresLinhas[i]))
                            .setTextAlign(Text.TEXT_ALIGN_RIGHT)
                            .isHaveFrame(false)
                            .isHaveArrowContinue(false)
                            .setTextColor(Color.cinza60)
                            .build();

                    linhasTextBox.add(textBox);
                }
            }
        } else { //maiorValorDouble != 0


            linhaM = new Rectangle("linhaM",
                    x + comprimentoTextoLinhas,
                    y + (height - tamanhoTextoColunas) - (Game.gameAreaResolutionY * 0.005f),
                    Rectangle.TYPE_OTHER,
                    width - comprimentoTextoLinhas,
                    Game.gameAreaResolutionY * 0.0075f,
                    -1,
                    Color.cinza40);


            for (int i = 0; i < 4; i++) {
                double posicaoLinha = y + (height - tamanhoTextoColunas) - ((height - tamanhoTextoColunas) * ((float)i / 4)) - (Game.gameAreaResolutionY * 0.005f);
                Rectangle dado = new Rectangle("linha" + i,
                        x + comprimentoTextoLinhas,
                        (float) posicaoLinha,
                        Rectangle.TYPE_OTHER,
                        width - comprimentoTextoLinhas,
                        Game.gameAreaResolutionY * 0.005f,
                        -1,
                        Color.cinza60);
                linhas.add(dado);
            }
        }
    }


    @Override
    public void checkTransformations(boolean updatePrevious) {

        if (!isVisible) return;
        if (dados == null || dados.size() <= 0) return;


        fundo.checkTransformations(false);
        linhaC.checkTransformations(false);
        linhaM.checkTransformations(false);
        linhaB.checkTransformations(false);

        for (int i = 0; i < dados.size(); i++) {
            dados.get(i).checkTransformations(false);

        }

        for (int i = 0; i < linhas.size(); i++) {
            linhas.get(i).checkTransformations(false);

        }

        for (int i = 0; i < linhasTextBox.size(); i++) {
            linhasTextBox.get(i).checkTransformations(false);

        }

        for (int i = 0; i < colunasTextBox.size(); i++) {
            colunasTextBox.get(i).checkTransformations(false);

        }

        for (int i = 0; i < rotulos.size(); i++) {
            rotulos.get(i).checkTransformations(false);

        }
    }

    @Override
    public void prepareRender(float[] matrixView, float[] matrixProjection) {

        if (!isVisible)return;

        fundo.checkAnimations();
        fundo.render(matrixView, matrixProjection);


        for (int i = 0; i < linhas.size(); i++) {
            linhas.get(i).checkAnimations();
            linhas.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < dados.size(); i++) {
            dados.get(i).checkAnimations();
            dados.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < linhasTextBox.size(); i++) {
            linhasTextBox.get(i).checkAnimations();
            linhasTextBox.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < colunasTextBox.size(); i++) {
            colunasTextBox.get(i).checkAnimations();
            colunasTextBox.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < rotulos.size(); i++) {
            rotulos.get(i).checkAnimations();
            rotulos.get(i).render(matrixView, matrixProjection);
        }

        linhaC.checkAnimations();
        linhaC.render(matrixView, matrixProjection);
        linhaM.checkAnimations();
        linhaM.render(matrixView, matrixProjection);
        linhaB.checkAnimations();
        linhaB.render(matrixView, matrixProjection);

    }
}
