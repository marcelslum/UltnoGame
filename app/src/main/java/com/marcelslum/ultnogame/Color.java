package com.marcelslum.ultnogame;

/**
 * Created by marcel on 07/08/2016.
 */
public class Color {

    static Color transparente = new Color(1f, 1f, 1f, 0f);
    static Color azul = new Color(0f, 0f, 0.7f, 1f);
    static Color azulClaro = new Color(0.5f, 0.5f, 1f, 1f);
    static Color preto = new Color(0f, 0f, 0f, 1f);
    static Color cinza1 = new Color(0.2f, 0.2f, 0.2f, 1f);
    static Color cinza2 = new Color(0.4f, 0.4f, 0.4f, 1f);
    static Color cinza3 = new Color(0.6f, 0.6f, 0.6f, 1f);
    static Color cinza4 = new Color(0.8f, 0.8f, 0.8f, 1f);
    static Color branco = new Color(1f, 1f, 1f, 1f);

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
}
