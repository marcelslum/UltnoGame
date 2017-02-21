package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {

    static final int[] ballsQuantity = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,3,3,2,1,1,2,2,1,1,2,1,1,1,2};
    static final int[] minBallsAlive = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    static final float[][] ballsRadius = {{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.013f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.012f,0.012f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsX = {{0.1f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.1f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.25f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.1f,0.9f,0f,0f,0f,0f,0f,0f,0f,0f},{0.1f,0.9f,0f,0f,0f,0f,0f,0f,0f,0f},{0.25f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.1f,0.1375f,0.8615f,0f,0f,0f,0f,0f,0f,0f},{0.1f,0.092f,0.363f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0.406666666666667f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0.411666666666667f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0.6f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0.316666666666667f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0.7f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsY = {{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.6f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.45f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.7f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.6f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.6f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.55f,0.55f,0f,0f,0f,0f,0f,0f,0f,0f},{0.58f,0.6f,0f,0f,0f,0f,0f,0f,0f,0f},{0.6f,0.6f,0f,0f,0f,0f,0f,0f,0f,0f},{0.58f,0.4198529412f,0.4198529412f,0f,0f,0f,0f,0f,0f,0f},{0.58f,0.062f,0.148f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0.291176470588235f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0.255882352941176f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0.63f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0.176470588235294f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.63f,0.63f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsVX = {{0.0025f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0026f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0027f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0028f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0028f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.003f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0032f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0032f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0032f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0032f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0005f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0034f,0.0034f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsVY = {{0.00441176470588235f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00458823529411765f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00476470588235294f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00494117647058824f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00494117647058824f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00529411764705882f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00564705882352941f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00564705882352941f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00564705882352941f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00564705882352941f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.000882352941176471f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.006f,0.006f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final int[][] ballsTextureMap = {{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,26,26,0,0,0,0,0,0,0},{26,26,26,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,26,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{26,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
    static final boolean[][] ballsInvencible = {{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,true,true,false,false,false,false,false,false,false},{false,true,true,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,true,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false}};
    static final float[][] ballsAngleToRotate = {{2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.1f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.1f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{2.4f,2.4f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsMaxAngle = {{52f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{52f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{52.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{52.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{52.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{52.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{53.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,54f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,54f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{54f,54f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsMinAngle = {{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,37f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,37f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{37f,37f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsVelocityVariation = {{0.06f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0625f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.065f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0675f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.07f,0.07f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsMaxVel = {{1.35f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.36f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.37f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.38f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.4f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.5f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{1.55f,1.55f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] ballsMinVel = {{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.8f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.75f,0.75f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final boolean[][] ballsFree = {{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,true,false,false,false,false,false,false,false,false},{true,true,false,false,false,false,false,false,false,false},{true,true,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,true,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,false,false,false,false,false,false,false,false,false},{true,true,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false},{false,false,false,false,false,false,false,false,false,false}};
    static final int[] barsQuantity = {2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2};
    static final float[][] barsWidth = {{0.22f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.22f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.18f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.18f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.17f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.17f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0.165f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0.165f,0f,0f,0f,0f,0f,0f,0f,0f},{0.165f,0.165f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] barsHeight = {{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0.0175f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0.0175f,0f,0f,0f,0f,0f,0f,0f,0f},{0.0175f,0.0175f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] barsX = {{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.3f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0.7f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0.7f,0f,0f,0f,0f,0f,0f,0f,0f},{0.2f,0.7f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] barsY = {{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0.024f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0.024f,0f,0f,0f,0f,0f,0f,0f,0f},{0.024f,0.024f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] barsVX = {{0.003875f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.003952f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.003996f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004144f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004144f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.00444f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004736f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004736f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004736f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004736f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.000735f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0.004998f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0.004998f,0f,0f,0f,0f,0f,0f,0f,0f},{0.004998f,0.004998f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};
    static final float[][] barsVY = {{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f},{0f,0f,0f,0f,0f,0f,0f,0f,0f,0f}};


    static final float[] targetWidth = {0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f,0.0895f};
    static final float[] targetHeight = {0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f,0.04f};
    static final float[] targetDistance = {0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f,0.001f};
    static final float[] targetPadd = {0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f,0.00225f};


    public static void loadLevel(int levelNumber) {

        Level.LevelBuilder levelBuilder = new Level.LevelBuilder();
        levelBuilder
            .setBallsQuantity(ballsQuantity[levelNumber-1])
            .setMinBallsAlive(minBallsAlive[levelNumber-1])
            .setBallsRadius(ballsRadius[levelNumber-1])
            .setBallsX(ballsX[levelNumber-1])
            .setBallsY(ballsY[levelNumber-1])
            .setBallsVX(ballsVX[levelNumber-1])
            .setBallsVY(ballsVY[levelNumber-1])
            .setBallsTextureMap(ballsTextureMap[levelNumber-1])
            .setBallsInvencible(ballsInvencible[levelNumber-1])
            .setBallsAngleToRotate(ballsAngleToRotate[levelNumber-1])
            .setBallsMaxAngle(ballsMaxAngle[levelNumber-1])
            .setBallsMinAngle(ballsMinAngle[levelNumber-1])
            .setBallsVelocityMax(ballsMaxVel[levelNumber-1])
            .setBallsVelocityMin(ballsMinVel[levelNumber-1])
            .setBallsVelocityVariation(ballsVelocityVariation[levelNumber-1])
            .setBallsFree(ballsFree[levelNumber-1])
            .setBarsQuantity(barsQuantity[levelNumber-1])
            .setBarsWidth(barsWidth[levelNumber-1])
            .setBarsHeight(barsHeight[levelNumber-1])
            .setBarsX(barsX[levelNumber-1])
            .setBarsY(barsY[levelNumber-1])
            .setBarsVX(barsVX[levelNumber-1])
            .setBarsVY(barsVY[levelNumber-1])
            .setTargetWidth(targetWidth[levelNumber-1])
            .setTargetHeight(targetHeight[levelNumber-1])
            .setTargetDistance(targetDistance[levelNumber-1])
            .setTargetPadd(targetPadd[levelNumber-1])
            .setBarsScaleVariationOff()
            .setObstaclesScaleVariationOff()
            .setObstaclesPositionVariationOff()
            .setWindType(Level.WIND_TYPE_NO)
            .setSpecialBallPercentage0_1(0f)
            .setObstaclesQuantity(0)
            .setWindowsQuantity(0)
            .setTutorialAttached(Tutorial.TUTORIAL_INICIO)
            .setBallsTargetsAppend(new ArrayList<int[]>())
            .setTargetsStates(new int[]{0, 1, 2, 3});

        // INÍCIO
        // ---------- LEVEL1
        int l = 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                        //{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
                    });
        }

        // ---------- LEVEL2
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0}
                    });
        }


        // ---------- LEVEL3
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
                        {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                    });

                    //.setWindowsQuantity(0)
                    //.setWindowsHeight(0.5f)
                    //.setWindowsY(0.4f)
                    //.setWindowsDistance(0.2f)
                    //.setWindowsQuantityOfLines(6)
                    //.setWindowsVelocity(-0.003f)
                    //.setBarsScaleVariation(new ScaleVariationDataBuilder()
                    //        .setIsActive(false)
                    //        .setIncreaseWidth(false)
                    //        .setWidthVelocity(0.002f)
                    //        .setMinWidth_BI(0.5f)
                    //        .setMaxWidth_BI(2f));
        }

        // ---------- LEVEL4
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                            {1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                    });
        }


        // OBSTÁCULOS
        // ---------- LEVEL5
        l += 1;
        if (levelNumber >= l) {
            levelBuilder.setTutorialAttached(Tutorial.TUTORIAL_OBSTACULO);
        }
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {0,1,0,1,0,1,0,1,0,1,0},
                            {1,0,1,0,1,0,1,0,1,0,1},
                            {0,1,0,1,0,1,0,1,0,1,0}
                    })
                    .setTargetsStates(new int[]{0, 1})
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.25f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(0.1f);
        }

        // ---------- LEVEL6
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.35f, 0.5f)
                    .setObstaclesY(0.0925f, 0.2597f)
                    .setObstaclesWidth(0.15f, 0.15f)
                    .setObstaclesHeight(0.035f, 0.035f)
                    .setTargetsMap(
                            new int[][]{
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {1,1,1,1,1,1,1,1,1,1,1}
                            });
        }


        // ---------- LEVEL7
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.46f)
                    .setObstaclesY(0.2f)
                    .setObstaclesWidth(0.08f)
                    .setObstaclesHeight(0.6f);
        }

        // ---------- LEVEL8
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.45f, 0.2f, 0.7f)
                    .setObstaclesY(0.2f, 0.65f, 0.65f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.1f)
                    .setObstaclesHeight(0.2f, 0.2f, 0.2f);
        }

        // CORES
        // ---------- LEVEL9
        l += 1;
        if (levelNumber >= l) {
            levelBuilder
                    .setTutorialAttached(Tutorial.TUTORIAL_CORES);
        }
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                    {3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3}
                            });
        }


        // ---------- LEVEL 10
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 2, 1, 3, 2, 3, 1, 1, 1, 1},
                                    {2, 2, 1, 1, 1, 3, 1, 1, 2, 2, 2},
                                    {1, 1, 2, 1, 3, 2, 3, 1, 1, 1, 1}
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.31f, 0.64f)
                    .setObstaclesY(0.2f, 0.2f)
                    .setObstaclesWidth(0.02f, 0.02f)
                    .setObstaclesHeight(0.3f, 0.3f)
                    .setWindowsQuantity(0);
        }

        // ---------- LEVEL11
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 3, 0, 2, 0, 1, 0, 3, 0, 2},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 2},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                                    {1, 3, 0, 0, 0, 0, 0, 0, 2, 1, 3},
                                    {3, 2, 1, 3, 0, 0, 0, 2, 1, 3, 2},
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.45f, 0.18f, 0.68f)
                    .setObstaclesY(0.4f, 0.62f, 0.62f)
                    .setObstaclesWidth(0.1f, 0.14f, 0.14f)
                    .setObstaclesHeight(0.4f, 0.2f, 0.2f);
        }

        // ---------- LEVEL12
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 3, 0, 2, 0, 2, 0, 3, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {2, 2, 0, 0, 0, 0, 0, 0, 3, 3, 3},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                            })
                    .setObstaclesQuantity(5)
                    .setObstaclesX(0.05f, 0.25f, 0.45f, 0.65f, 0.85f)
                    .setObstaclesY(0.2f, 0.2f, 0.2f, 0.2f, 0.2f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.1f, 0.1f, 0.1f)
                    .setObstaclesHeight(0.08f, 0.08f, 0.08f, 0.08f, 0.08f);
        }

        // EXPLOSÃO
        // ---------- LEVEL 13
        l += 1;
        if (levelNumber >= l) {
            levelBuilder
                    .setTutorialAttached(Tutorial.TUTORIAL_EXPLOSAO);
        }
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 2, 4, 3, 0, 3, 1, 2, 1, 1},
                                    {2, 2, 1, 1, 1, 0, 1, 4, 1, 2, 2},
                                    {1, 1, 2, 1, 3, 0, 3, 1, 2, 1, 1},
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1}
                            });
        }


        // ---------- LEVEL 14
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {3, 2, 1, 1, 0, 0, 0, 1, 1, 2, 3},
                                    {2, 2, 0, 1, 4, 0, 4, 1, 0, 2, 2},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1}
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(     0.425f,     0.425f)
                    .setObstaclesY(     0.1206f,      0.29f)
                    .setObstaclesHeight(0.044f,      0.044f)
                    .setObstaclesWidth(0.15f ,      0.15f);
        }

        // ---------- LEVEL 15
        l += 1;
        if (levelNumber == l) {
                levelBuilder = new Level.LevelBuilder()
                        .setTargetsMap(
                                new int[][]{
                                        {0, 3, 0, 1, 0, 0, 0, 1, 0, 3, 0},
                                        {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                                        {1, 3, 0, 1, 0, 0, 0, 1, 0, 3, 1},
                                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                        {1, 3, 2, 1, 0, 0, 0, 1, 2, 3, 1},
                                        {3, 2, 2, 1, 0, 0, 0, 1, 2, 2, 3},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 4, 0, 4, 0, 0, 0, 4, 0, 4, 0}
                                }
                        );
        }

        // ---------- LEVEL16
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 4, 0, 0, 0, 0, 0, 0, 0, 4, 1},
                                    {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
                                    {0, 0, 2, 0, 0, 0, 0, 0, 2, 1, 1},
                                    {1, 4, 2, 0, 0, 0, 0, 0, 1, 4, 1},
                                    {3, 3, 3, 3, 0, 0, 0, 3, 3, 3, 3},
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.45f, 0.2f, 0.7f)
                    .setObstaclesY(0.175f, 0.65f, 0.65f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.1f)
                    .setObstaclesHeight(0.18f, 0.18f, 0.18f);
        }


        // ELÁSTICO
        // ---------- LEVEL 17
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 2, 1, 0, 0, 0, 1, 2, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                                    {3, 0, 0, 3, 0, 0, 0, 3, 0, 0, 3}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.333f)
                    .setObstaclesY(0.43529f)
                    .setObstaclesHeight(0.0735294118f)
                    .setObstaclesWidth(0.333f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.8f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.001f)
                                    .setyVelocity(0)
                    );
        }


        // ---------- LEVEL 18
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {4, 0, 1, 0, 4, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                                    {1, 0, 1, 0, 2, 0, 2, 0, 1, 0, 2},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}
                            })
                    .setTargetsStates(new int[]{0, 1, 2, 3})
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.375f, 0.375f)
                    .setObstaclesY(0.1558823529f, 0.5854588235f)
                    .setObstaclesWidth(0.25f, 0.25f)
                    .setObstaclesHeight(0.0588235294f, 0.0588235294f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.99f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.001f)
                                    .setyVelocity(0),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.99f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.001f)
                                    .setIncreaseX(false)
                                    .setyVelocity(0)
                    );
        }

        // ---------- LEVEL 19
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {1, 1, 3, 2, 3, 2, 3, 2, 3, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1},
                            })
                    .setTargetsStates(new int[]{0, 1, 2, 3})
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.475f)
                    .setObstaclesY(0.2038470588f)
                    .setObstaclesWidth(0.05f)
                    .setObstaclesHeight(0.1470588235f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.00125f)
                    );
        }


        // ---------- LEVEL 20
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {1, 1, 0, 1, 0, 4, 0, 1, 0, 1, 1},
                                    {0, 3, 1, 0, 1, 0, 1, 0, 1, 3, 0},
                                    {0, 0, 1, 1, 0, 4, 0, 1, 1, 0, 0},
                                    {1, 0, 0, 2, 1, 0, 1, 2, 0, 0, 1},
                                    {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1},
                                    {0, 1, 1, 0, 0, 4, 0, 0, 1, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},

                            })
                    .setTargetsStates(new int[]{0, 1, 2, 3})
                    .setObstaclesQuantity(2)

                    .setObstaclesX(0.125f, 0.7916666667f)
                    .setObstaclesY(0.5676470588f, 0.5676470588f)
                    .setObstaclesWidth(0.0833f, 0.0833f)
                    .setObstaclesHeight(0.1764705882f, 0.1764705882f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.00125f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.00125f)
                    );
        }

        // ELÁSTICO
        // ---------- LEVEL 21
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 2, 0, 2, 0, 2, 0, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},

                            })
                    .setTargetsStates(new int[]{0, 1, 2, 3})
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.4833333333f, 0.4583333333f)
                    .setObstaclesY(0.1661764706f, 0.4402882353f)
                    .setObstaclesWidth(0.0166666667f, 0.0833333333f)
                    .setObstaclesHeight(0.0588235294f, 0.0588235294f)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.00125f)
                                    .setyVelocity(0f))
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.1f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            null
                    );
        }


        // ---------- LEVEL 22
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 4, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},

                            })
                    .setTargetsStates(new int[]{0, 1, 2, 3})
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.55f, 0.35f)
                    .setObstaclesY(0.06675f, 0.3883470588f)
                    .setObstaclesWidth(0.1f, 0.1f)
                    .setObstaclesHeight(0.0605f, 0.0605f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.01f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.012f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    );
        }


        // ---------- LEVEL 23
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 0, 2, 0, 0, 1, 0, 0},
                                    {1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 0, 4, 0, 0, 1, 0, 0},
                                    {1, 0, 1, 1, 1, 4, 1, 1, 1, 0, 1},
                                    {1, 0, 1, 1, 0, 3, 0, 1, 1, 0, 1},
                                    {1, 0, 1, 1, 0, 3, 0, 1, 1, 0, 1},
                                    {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
                                    {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.1176466667f, 0.8428633333f)
                    .setObstaclesY(0.4158558824f, 0.4158558824f)
                    .setObstaclesWidth(0.0416666667f, 0.0416666667f)
                    .setObstaclesHeight(0.0882352941f, 0.0882352941f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(1f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(100000f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(1f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(100000f)
                    );
        }

        // VENTO
        // ---------- LEVEL 24
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
                                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {0, 1, 0, 1, 2, 0, 2, 1, 0, 1, 0},
                                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                            })
                    .setWindType(Level.WIND_TYPE_RIGHT);

        }


        // ---------- LEVEL25
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0},
                            })
                    .setWindType(Level.WIND_TYPE_LEFT);
        }

        // FANTASMA
        // ---------- LEVEL26
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {5, 0, 5, 0, 0, 0, 0, 0, 5, 0, 5},
                                    {0, 5, 0, 5, 0, 0, 0, 5, 0, 5, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
                            }
                    );
        }

        // ---------- LEVEL27
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                                    {0, 5, 0, 0, 0, 7, 0, 0, 0, 5, 0},
                            }
                    );
        }
        

        // ---------- LEVEL28
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {0, 0, 0, 2, 0, 4, 0, 2, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 7, 4, 7, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 6, 5, 6, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 5, 6, 5, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.00225f, 0.63575f)
                    .setObstaclesY(0.3380882353f,      0.3380882353f)
                    .setObstaclesHeight(0.1652941176f,      0.1652941176f)
                    .setObstaclesWidth(0.361f,      0.361f)
                    .setWindType(Level.WIND_TYPE_LEFT);
        }
        

        // INVENCIBILIDADE
        // ---------- LEVEL29
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                            }
                    );
        }
        
        
        // ---------- LEVEL30
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                                    {4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4},
                                    {3, 2, 3, 2, 3, 1, 3, 2, 3, 2, 3},
                            }
                    );
        }
        
        
        // ---------- LEVEL31
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {1, 1, 0, 1, 0, 4, 0, 1, 0, 1, 1},
                                    {0, 3, 1, 0, 1, 0, 1, 0, 1, 3, 0},
                                    {0, 0, 1, 1, 0, 4, 0, 1, 1, 0, 0},
                                    {1, 0, 0, 2, 1, 0, 1, 2, 0, 0, 1},
                                    {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1},
                                    {0, 1, 1, 0, 0, 4, 0, 0, 1, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                                    {0, 0, 0, 1, 5, 0, 5, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.125f, 0.7916666667f)
                    .setObstaclesY(0.5676470588f, 0.5676470588f)
                    .setObstaclesWidth(0.0833f, 0.0833f)
                    .setObstaclesHeight(0.1764705882f, 0.1764705882f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f)
                    );
        }
        
        
        
        // PRISÃO
        // ---------- LEVEL32
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{55, 63, 64, 70, 71, 78});
            targetsAppend.add(new int[]{61, 68, 69, 75, 76, 84});

            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},//53
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},

                            }
                    );
     
        }
        
        
        // ---------- LEVEL33
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{0, 1, 3, 4, 5});
            targetsAppend.add(new int[]{7, 8, 10, 11, 15, 16});

            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {2, 2, 2, 3, 3, 3, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 4, 0, 0, 1, 1, 1},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 1, 1, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 1, 1, 3, 3, 3},
                            }
                    )
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.275f)
                    .setObstaclesY(0.3705882353f)
                    .setObstaclesWidth(0.0916666667f)
                    .setObstaclesHeight(0.0705882353f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f)
                    );
        }
        
        // ---------- LEVEL34
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{28, 29, 30, 31, 32, 33, 34, 35, 36, 39, 41, 43, 44, 45, 46, 47, 48, 49, 50, 51});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            }
                    )
                    .setWindType(Level.WIND_TYPE_LEFT);
        }


        // ---------- LEVEL35
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {3, 2, 3, 2, 3, 4, 3, 2, 3, 2, 3},

                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.006f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.5f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    );
        }

        // ---------- LEVEL36
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},

                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.006f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.5f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    )
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.2925f, 0.655f)
                    .setObstaclesY(0.2647058824f, 0.2647058824f)
                    .setObstaclesWidth(0.0566666667f, 0.0566666667f)
                    .setObstaclesHeight(0.2058823529f, 0.2058823529f)
                    .setWindType(Level.WIND_TYPE_RIGHT);
        }

        // ---------- LEVEL 37
        l += 1;
        if (levelNumber == l) {

            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{18, 25, 26, 32, 33, 38});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 0, 2, 0, 0, 1, 0, 0},
                                    {1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 0, 4, 0, 0, 1, 0, 0},
                                    {1, 0, 1, 0, 1, 4, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 1, 0, 3, 0, 1, 1, 0, 1},
                                    {1, 0, 1, 1, 0, 3, 0, 1, 1, 0, 1},
                                    {0, 6, 0, 0, 6, 0, 6, 0, 0, 6, 0},
                                    {0, 7, 0, 0, 7, 0, 7, 0, 0, 7, 0},
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.006f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.5f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    )
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.1176466667f, 0.8428633333f)
                    .setObstaclesY(0.4158558824f, 0.4158558824f)
                    .setObstaclesWidth(0.0416666667f, 0.0416666667f)
                    .setObstaclesHeight(0.0882352941f, 0.0882352941f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.014f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(1f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(100000f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.013f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(1f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(100000f)
                    );
        }

        // ---------- LEVEL 38
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {4, 0, 3, 0, 4, 0, 1, 0, 3, 0, 1},
                                    {1, 0, 1, 0, 3, 0, 3, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 3, 0, 3, 0, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                                    {1, 0, 1, 0, 2, 0, 2, 0, 1, 0, 2},
                                    {1, 0, 3, 0, 1, 0, 1, 0, 3, 0, 1},
                                    {3, 0, 1, 0, 1, 0, 1, 0, 1, 0, 3}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.006f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.5f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    )
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.375f, 0.375f)
                    .setObstaclesY(0.1558823529f, 0.5854588235f)
                    .setObstaclesWidth(0.25f, 0.25f)
                    .setObstaclesHeight(0.0588235294f, 0.0588235294f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.99f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.99f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setIncreaseX(false)
                                    .setyVelocity(0)
                    );
        }

        // ---------- LEVEL39
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0},
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true)
                    )
                    .setSpecialBallPercentage0_1(0.4f);

        }


        // ---------- LEVEL40
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 3, 4, 0, 0, 0, 0, 0, 0, 4},
                                    {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 1, 3, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 3, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 4, 3, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 3, 1, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},

                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.55f, 0.35f, 0.45f)
                    .setObstaclesY(0.06675f, 0.3883470588f, 0.55f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.2f)
                    .setObstaclesHeight(0.0605f, 0.0605f, 0.0605f)
                    .setWindType(Level.WIND_TYPE_LEFT)
                    .setSpecialBallPercentage0_1(0.395f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.01f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.012f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            null
                    )
                    .setObstaclesPositionVariation(
                            null,
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f))
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.000255f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true));
        }
        // ---------- LEVEL41

        l += 1;
        if (levelNumber == l) {

            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{6, 8, 9, 12, 13, 16});

            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 1, 3, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 0, 3, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0},
                                    {1, 1, 0, 4, 0, 2, 0, 3, 0, 0, 0},
                                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0},
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00026f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage0_1(0.39f);

        }

        // ---------- LEVEL42

        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    
                    .setTargetsMap(
                            new int[][]{
                                    {0, 5, 0, 5, 0, 0, 0, 5, 0, 5, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 5, 4, 0, 0, 0, 0, 5, 0, 0},
                                    {0, 5, 0, 5, 0, 0, 0, 5, 0, 5, 0},
                                    {0, 5, 0, 5, 0, 0, 0, 0, 0, 5, 0},
                                    {0, 5, 0, 5, 0, 0, 4, 0, 0, 5, 0},
                                    {0, 5, 0, 6, 7, 6, 7, 6, 7, 5, 0},
                                    {0, 5, 0, 7, 6, 7, 6, 7, 6, 0, 0},
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00026f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage0_1(0.385f);
        }

        // MURO E DUAS BARRAS
        // ---------- LEVEL43

        l += 1;
        if (levelNumber == l) {
            levelBuilder

                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 3, 4, 3, 1, 3, 1, 1, 1},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 4, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.42f)
                    .setObstaclesY(0.5268088235f)
                    .setObstaclesWidth(0.16f)
                    .setObstaclesHeight(0.4705882353f)
                    ;
        }

        // ---------- LEVEL44
        l += 1;
        if (levelNumber == l) {
            levelBuilder

                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                                    {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
                                    {1, 1, 1, 0, 3, 4, 3, 0, 1, 1, 1},
                                    {2, 2, 2, 3, 0, 3, 0, 3, 2, 2, 2},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 5, 0, 4, 0, 5, 0, 0, 0},
                                    {0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0},
                                    {0, 0, 0, 5, 0, 0, 0, 5, 0, 0, 0},
                                    {0, 0, 0, 5, 1, 0, 1, 5, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.42f, 0.125f, 0.7916666667f)
                    .setObstaclesY(0.5768088235f, 0.5676470588f, 0.5676470588f)
                    .setObstaclesWidth(0.16f, 0.0833f, 0.0833f)
                    .setObstaclesHeight(1-0.577f, 0.1764705882f, 0.1764705882f)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f)
                    );

        }

        // ---------- LEVEL45
        l += 1;
        if (levelNumber == l) {
            levelBuilder

                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 4, 0, 1, 1, 1, 1, 1},
                                    {1, 1, 4, 1, 1, 0, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 4, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                                    {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},

                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.42f, 0.2925f, 0.655f)
                    .setObstaclesY(0.5768088235f, 0.2647058824f, 0.2647058824f)
                    .setObstaclesWidth(0.16f, 0.0566666667f, 0.0566666667f)
                    .setObstaclesHeight(1-0.577f, 0.2058823529f, 0.2058823529f)
                    .setObstaclesScaleVariation(
                            null,
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.01f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.01f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    );

        }

        // ---------- LEVEL46
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{14, 22, 23, 29, 30, 38});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 1, 4, 1, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {4, 1, 1, 1, 1, 1, 0, 0, 4, 1, 1},
                                    {0, 0, 2, 2, 2, 2, 3, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 3, 1, 1, 0, 0},
                                    {1, 1, 3, 0, 0, 0, 3, 0, 1, 2, 2},
                                    {1, 1, 3, 0, 0, 0, 3, 0, 1, 2, 2},
                                    {1, 0, 3, 0, 0, 0, 3, 1, 1, 0, 0},
                                    {1, 0, 3, 0, 0, 0, 3, 1, 1, 0, 0},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0},
   
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.5768088235f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.577f)
                    .setWindType(Level.WIND_TYPE_LEFT);
        }

        // ---------- LEVEL47
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{33, 41, 42, 47, 48, 53, 54, 59, 60, 68});
            targetsAppend.add(new int[]{37, 43, 44, 49, 50, 55, 56, 61, 62, 72});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                                    {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                                    {1, 0, 2, 0, 5, 0, 3, 0, 5, 0, 3},
                                    {2, 0, 2, 0, 5, 0, 3, 0, 5, 0, 1},
                                    {1, 0, 2, 0, 5, 0, 3, 0, 5, 0, 3},
                                    {2, 0, 2, 0, 5, 0, 3, 0, 5, 0, 1},
                                    {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                                    {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.5768088235f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.577f)
                    .setBarsScaleVariation(
                                new ScaleVariationDataBuilder()
                                        .setIsActive(true)
                                        .setIncreaseWidth(true)
                                        .setIncreaseHeight(false)
                                        .setWidthVelocity(0.006f)
                                        .setHeightVelocity(0f)
                                        .setMinWidth_BI(0.5f)
                                        .setMaxWidth_BI(1.5f)
                                        .setMinHeight_BI(1f)
                                        .setMaxHeight_BI(1f),
                                new ScaleVariationDataBuilder()
                                        .setIsActive(true)
                                        .setIncreaseWidth(true)
                                        .setIncreaseHeight(false)
                                        .setWidthVelocity(0.006f)
                                        .setHeightVelocity(0f)
                                        .setMinWidth_BI(0.5f)
                                        .setMaxWidth_BI(1.5f)
                                        .setMinHeight_BI(1f)
                                        .setMaxHeight_BI(1f)
                        );
        }

        // ---------- LEVEL48
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0},
                                    {0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0},
                                    {0, 0, 1, 2, 2, 0, 2, 2, 1, 0, 0},
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.01f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.02f);
        }

        // ---------- LEVEL49
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                                    {0, 1, 1, 2, 1, 0, 1, 2, 1, 1, 0},
                                    {0, 0, 1, 2, 2, 0, 2, 2, 1, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0},
                                    {0, 0, 0, 1, 2, 0, 2, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.01f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.02f);
        }

        // ---------- LEVEL50
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 3, 1, 2, 0, 0, 0, 2, 1, 1, 0},
                                    {0, 0, 1, 2, 1, 0, 2, 2, 1, 0, 0},
                                    {0, 0, 2, 1, 1, 0, 2, 1, 1, 0, 0},
                                    {0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0},
                                    {0, 0, 0, 3, 2, 0, 2, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.45f, 0.17f, 0.69f)
                    .setObstaclesY(0.01f, 0.69f, 0.69f)
                    .setObstaclesWidth(0.1f,0.31f, 0.31f)
                    .setObstaclesHeight(1-0.02f, 0.058f, 0.058f)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f));
        }

        // ---------- LEVEL51
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 2, 0, 0, 0, 2, 1, 4, 0},
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                                    {0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0},
                                    {0, 0, 0, 1, 1, 0, 1, 4, 0, 1, 0},
                                    {0, 0, 0, 0, 2, 0, 2, 0, 0, 1, 0},
                                    {0, 2, 0, 0, 1, 0, 1, 0, 0, 2, 0},
                                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.01f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.02f);
        }

        // ---------- LEVEL52
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 3, 0, 0, 0, 3, 1, 1, 1},
                                    {1, 1, 1, 3, 0, 0, 0, 3, 1, 1, 1},
                                    {1, 1, 1, 3, 1, 0, 1, 3, 1, 1, 1},
                                    {2, 2, 1, 3, 1, 0, 1, 3, 1, 2, 2},
                                    {2, 2, 0, 1, 1, 0, 1, 1, 0, 2, 2},
                                    {1, 3, 0, 1, 1, 0, 1, 1, 0, 3, 1},
                                    {0, 3, 0, 0, 1, 0, 1, 0, 0, 3, 1},
                                    {0, 3, 0, 0, 1, 0, 1, 0, 0, 3, 1},
                                    {0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.01f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.02f);
        }
        
        // ---------- LEVEL53
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {2, 0, 3, 1, 1, 0, 1, 0, 1, 1, 2},
                                    {1, 1, 0, 2, 1, 0, 1, 2, 3, 1, 1},
                                    {1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1},
                                    {2, 1, 0, 1, 1, 0, 1, 1, 0, 1, 2},
                                    {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
                                    {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
                                    {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                                    {1, 2, 3, 1, 0, 0, 1, 1, 3, 2, 0},
                                    {1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f)
                    .setObstaclesY(0.01f)
                    .setObstaclesWidth(0.1f)
                    .setObstaclesHeight(1-0.02f)
                    .setWindType(Level.WIND_TYPE_RIGHT);
        }
        
        // ---------- LEVEL54
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                    {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                                    {1, 1, 1, 2, 1, 0, 1, 1, 1, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 0, 1, 2, 1, 0, 1, 1, 0, 2, 1},
                                    {1, 1, 1, 2, 1, 0, 1, 1, 1, 2, 1},
                                    {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 4, 0, 0, 0, 0, 0, 4, 0, 0},
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f, 0.1123333333f, 0.7483333333f)
                    .setObstaclesY(0.01f, 0.2137205882f, 0.2137205882f)
                    .setObstaclesWidth(0.1f, 0.0472416667f, 0.0472416667f)
                    .setObstaclesHeight(1-0.02f, 0.0895735294f, 0.0895735294f)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f),
                             new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0015f));
                       
        }
        
         // ---------- LEVEL55
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 3, 2, 0, 0, 0, 2, 3, 0, 1},
                                    {1, 0, 3, 2, 0, 0, 0, 2, 3, 0, 1},
                                    {1, 0, 3, 2, 0, 0, 0, 2, 3, 0, 1},
                                    {1, 1, 3, 1, 1, 0, 1, 1, 3, 1, 1},
                                    {1, 1, 3, 1, 1, 0, 1, 1, 3, 1, 1},
                                    {1, 1, 3, 1, 1, 0, 1, 1, 3, 1, 1},
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.45f, 0.8383333333f
                    .setObstaclesY(0.01f, 0.2989705882f
                    .setObstaclesWidth(0.1f, 0.0471666667f
                    .setObstaclesHeight(1-0.02f, 0.1666176471f
                    .setBarsScaleVariation(
                                null, 
                                new ScaleVariationDataBuilder()
                                        .setIsActive(true)
                                        .setIncreaseWidth(true)
                                        .setIncreaseHeight(false)
                                        .setWidthVelocity(0.006f)
                                        .setHeightVelocity(0f)
                                        .setMinWidth_BI(0.5f)
                                        .setMaxWidth_BI(1.5f)
                                        .setMinHeight_BI(1f)
                                        .setMaxHeight_BI(1f))
                    .setWindType(Level.WIND_TYPE_LEFT);
                                 
        }

        Level.levelObject = levelBuilder.build();
    }
}
