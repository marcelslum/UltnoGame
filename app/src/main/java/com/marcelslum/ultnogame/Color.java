package com.marcelslum.ultnogame;

/**
 * Created by marcel on 07/08/2016.
 */
public class Color {

    static Color transparente = new Color(1f, 1f, 1f, 0f);
    static Color azul = new Color(0f, 0f, 0.7f, 1f);
    static Color azulClaro = new Color(0.5f, 0.5f, 1f, 1f);
    static Color pretoCheio = new Color(0f, 0f, 0f, 1f);
    static Color pretoCheioAlpha60 = new Color(0f, 0f, 0f, 0.6f);
    static Color cinza20 = new Color(0.2f, 0.2f, 0.2f, 1f);
    static Color cinza40 = new Color(0.4f, 0.4f, 0.4f, 1f);
    static Color cinza60 = new Color(0.6f, 0.6f, 0.6f, 1f);
    static Color cinza80 = new Color(0.8f, 0.8f, 0.8f, 1f);
    static Color branco = new Color(1f, 1f, 1f, 1f);
    static Color brancoAlpha0 = new Color(1f, 1f, 1f, 0f);
    static Color azulCheio = new Color(0f, 0f, 1f, 1f);
    static Color verdeCheio = new Color(0f, 1f, 0f, 1f);
    static Color vermelhoCheio = new Color(1f, 0f, 0f, 1f);
    static Color azul40 = new Color(0f, 0f, 0.4f, 1f);
    static Color verde40 = new Color(0f, 0.4f, 0f, 1f);
    static Color vermelho40 = new Color(0.4f, 0f, 0f, 1f);
    static Color amareloCheio = new Color(1f, 1f, 0f, 1f);
    static Color zero = new Color (0f, 0f, 0f, 0f);



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
