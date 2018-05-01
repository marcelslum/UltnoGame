package com.marcelslum.ultnogame;

/**
 * Created by marcel on 07/08/2016.
 */
public class Color {

    static final Color transparente = new Color(1f, 1f, 1f, 0f);
    static final Color zero = new Color (0f, 0f, 0f, 0f);

    static final Color azulCheio = new Color(0f, 0f, 1f, 1f);
    static final Color azul = new Color(0f, 0f, 1f, 1f);
    static final Color azulMedio = new Color(0f, 0.25f, 0.53f, 1f);
    static final Color azulClaro = new Color(0.48f, 0.66f, 0.87f, 1f);
    static final Color azul40 = new Color(0f, 0f, 0.4f, 1f);

    static final Color pretoCheio = new Color(0f, 0f, 0f, 1f);
    static final Color pretoCheioAlpha60 = new Color(0f, 0f, 0f, 0.6f);
    static final Color cinza10 = new Color(0.1f, 0.1f, 0.1f, 1f);
    static final Color cinza20 = new Color(0.2f, 0.2f, 0.2f, 1f);
    static final Color cinza30 = new Color(0.3f, 0.3f, 0.3f, 1f);
    static final Color cinza40 = new Color(0.4f, 0.4f, 0.4f, 1f);
    static final Color cinza50 = new Color(0.5f, 0.5f, 0.5f, 1f);
    static final Color cinza60 = new Color(0.6f, 0.6f, 0.6f, 1f);
    static final Color cinza70 = new Color(0.7f, 0.7f, 0.7f, 1f);
    static final Color cinza80 = new Color(0.8f, 0.8f, 0.8f, 1f);

    static final Color branco = new Color(1f, 1f, 1f, 1f);

    static final Color verdeCheio = new Color(0f, 1f, 0f, 1f);
    static final Color verdeClaro = new Color(0f, 0.8f, 0.4f, 1f);
    static final Color verde40 = new Color(0f, 0.4f, 0f, 1f);


    static final Color vermelhoCheio = new Color(1f, 0f, 0f, 1f);
    static final Color vermelhoClaro = new Color(0.8f, 0.36f, 0.36f, 1f);
    static final Color vermelho40 = new Color(0.4f, 0f, 0f, 1f);


    static final Color amareloCheio = new Color(1f, 1f, 0f, 1f);
    static final Color amareloClaro = new Color(0.93f, 0.92f, 0.55f, 1f);


    static final Color laranjaCheio = new Color (0.8f, 0.52f, 0f, 1f);
    static final Color laranjaClaro = new Color (0.93f, 0.6f, 0f, 1f);

    static final Color rosaCheio = new Color (0.8f, 0.41f, 79f, 1f);
    static final Color rosaClaro = new Color (1f, 0.8f, 0.8f, 1f);

    static final Color roxoCheio = new Color (0.49f, 0.15f, 0.8f, 1f);
    static final Color roxoClaro = new Color (0.62f, 0.47f, 0.93f, 1f);

    public float r;
    public float g;
    public float b;
    public float a;

    Color(){

    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color changeAlpha(float _alpha){
        return new Color(this.r, this.g, this.b, _alpha);
    }
}
