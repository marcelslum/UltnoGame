package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader2 {

    public static void loadLevel(int levelNumber, Level.LevelBuilder levelBuilder) {

        Log.e("LevelLoader", "levelNumber "+levelNumber);

        int l = 50;
        // ESPELHO
        // ---------- LEVEL50
        if (levelNumber == l) {
            levelBuilder = new Level.LevelBuilder()
                    .setInvertedButtons(true)
                    .setTargetsMap(
                            new int[][]{
                                    {0, 3, 0, 1, 0, 0, 0, 1, 0, 3, 0},
                                    {1, 4, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                                    {1, 3, 0, 1, 0, 0, 0, 1, 0, 3, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 3, 2, 1, 0, 0, 0, 1, 2, 3, 1},
                                    {3, 2, 2, 1, 0, 0, 0, 1, 2, 2, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0}
                            }
                    );
        }

        // ---------- LEVEL51
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {1, 0, 1, 1, 1, 3, 1, 1, 1, 0, 1},
                            {0, 1, 0, 0, 0, 2, 0, 0, 0, 1, 0},
                            {0, 0, 1, 0, 0, 3, 0, 0, 1, 0, 0},
                            {0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0},
                            {0, 0, 0, 1, 1, 3, 1, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
                            {0, 0, 0, 1, 1, 3, 1, 0, 0, 0, 0},
                            {0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0},
                            {0, 0, 1, 0, 0, 3, 0, 0, 1, 0, 0},
                            {0, 1, 0, 0, 0, 2, 0, 0, 0, 1, 0},
                            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                    })
                    .setSpecialBallPercentage(0.42f)
                    .setInvertedButtons(true)
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.000255f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true));

        }

        // ---------- LEVEL52
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                                    {3, 2, 3, 2, 3, 1, 3, 2, 3, 2, 3},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4},
                                    {3, 2, 3, 2, 3, 1, 3, 2, 3, 2, 3}
                            }
                    )
                    .setInvertedButtons(true)
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

        //LEVEL 53
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 4, 3, 3, 3, 3, 3, 3, 3, 4, 1},
                                    {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
                                    {0, 1, 2, 0, 0, 0, 0, 0, 2, 1, 1},
                                    {1, 1, 2, 1, 0, 0, 0, 1, 1, 1, 1},
                                    {3, 3, 3, 3, 1, 0, 1, 3, 3, 3, 3},
                            })
                    .setInvertedButtons(true)
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.45f, 0.2f, 0.7f)
                    .setObstaclesY(0.175f, 0.65f, 0.65f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.1f)
                    .setObstaclesHeight(0.18f, 0.18f, 0.18f)
                    .setObstaclesPositionVariation(
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
                                    .setyVelocity(0f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f)
                    );
        }


        // MURO E DUAS BARRAS
        // ---------- LEVEL54
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
                    .setObstaclesHeight(0.4705882353f);
        }

        // ---------- LEVEL55
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

        // ---------- LEVEL56
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

        // ---------- LEVEL57
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

        // ---------- LEVEL58
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
                    .setObstaclesHeight(1-0.577f);
        }

        // muro e duas bolas
        // ---------- LEVEL59
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
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.001f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(1-0.002f);
        }

        // ---------- LEVEL60
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
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.001f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(1-0.002f);
        }

        // ---------- LEVEL61
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
                    .setObstaclesX(0.47f, 0.17f, 0.69f)
                    .setObstaclesY(0.06f, 0.3740852941f, 0.3740852941f)
                    .setObstaclesWidth(0.001f,0.1433333333f, 0.1433333333f)
                    .setObstaclesHeight(1-0.002f, 0.058f, 0.058f)
                
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

        // ---------- LEVEL62
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
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.001f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(1-0.002f);
        }

        // ---------- LEVEL63
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
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.001f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(1-0.002f);
        }
        
        // ---------- LEVEL64
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
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.001f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(1-0.002f)
                    .setWindType(Level.WIND_TYPE_RIGHT);
        }
        

        // DUAS BOLAS LIVRES
        // ---------- LEVEL65
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1},
                            });
        }
        
        // ---------- LEVEL66
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 2, 2, 2, 4, 3, 1, 2, 2, 2, 0},
                                    {1, 1, 4, 1, 1, 3, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 3, 1, 0, 1, 0, 1},
                                    {1, 0, 0, 0, 1, 3, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 4, 0, 1, 0, 1, 0, 1, 0, 1},
                            });
        }

        // ---------- LEVEL67
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {4, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 4, 0, 1, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                                    {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                                    {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                            });
        }

        // ---------- LEVEL68
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 4, 1, 0, 0, 0, 0, 0, 1, 0},
                                    {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                    {3, 0, 1, 0, 2, 1, 0, 1, 2, 0, 1},
                                    {3, 0, 1, 0, 0, 3, 3, 3, 0, 0, 1},
                                    {1, 0, 1, 0, 0, 3, 3, 3, 0, 0, 4},
                                    {1, 0, 1, 0, 2, 1, 0, 1, 2, 0, 1},
                                    {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1},
                                    {1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.5506666667f, 0.5066666667f)
                    .setObstaclesY(0.0242647059f, 0.5919117647f)
                    .setObstaclesWidth(0.1f, 0.1266666667f)
                    .setObstaclesHeight(1-0.0610294118f, 0.0610294118f)
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
                            null)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0015f)
                                    .setyVelocity(0f));
        }

        // ---------- LEVEL69
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
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.1123333333f, 0.7483333333f)
                    .setObstaclesY(0.2137205882f, 0.2137205882f)
                    .setObstaclesWidth(0.0472416667f, 0.0472416667f)
                    .setObstaclesHeight(0.0895735294f, 0.0895735294f)


                    .setObstaclesPositionVariation(
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

        // ---------- LEVEL70
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
                    .setObstaclesX(0.8383333333f)
                    .setObstaclesY(0.2989705882f)
                    .setObstaclesWidth(0.0471666667f)
                    .setObstaclesHeight(0.1666176471f)
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
                                    .setMaxHeight_BI(1f));
        }

        // GRADE
        // ---------- LEVEL71 (BASEADO NO 50)
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                                    {0, 3, 1, 2, 0, 0, 0, 2, 1, 1, 0},
                                    {0, 0, 1, 2, 1, 0, 4, 2, 1, 0, 0},
                                    {0, 0, 2, 1, 1, 0, 2, 1, 1, 0, 0},
                                    {0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0},
                                    {1, 0, 0, 3, 2, 0, 2, 1, 0, 0, 1},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 2, 0, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.17f, 0.69f)
                    .setObstaclesY(0.3740852941f, 0.3740852941f)
                    .setObstaclesWidth(0.1433333333f, 0.1433333333f)
                    .setObstaclesHeight(0.058f, 0.058f)

                    .setObstaclesPositionVariation(
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
                                    .setyVelocity(0f))
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.15f)
                    .setWindowsY(0.15f)
                    .setWindowsHeight(0.3f)
                    .setWindowsQuantityOfLines(3)
                    .setWindowsVelocity(0.002f);
        }
        
        // ---------- LEVEL72 (BASEADO NO 41)
        l += 1;
        if (levelNumber == l) {

            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{8, 11, 12, 16, 17, 22});

            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
                                    {0, 0, 1, 0, 1, 3, 0, 1, 0, 0, 0},
                                    {1, 0, 1, 0, 3, 0, 0, 1, 0, 0, 1},
                                    {1, 0, 1, 2, 0, 0, 1, 1, 0, 0, 1},
                                    {1, 0, 3, 0, 0, 0, 3, 0, 0, 0, 1},
                                    {1, 1, 0, 4, 0, 2, 0, 3, 0, 1, 1},
                                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 1, 0},
                                    {0, 0, 0, 1, 2, 0, 0, 0, 0, 1, 0},
                                    {0, 0, 0, 0, 1, 3, 0, 0, 0, 1, 0},
                                    {0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0},
                                    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                    {0, 0, 0, 1, 0, 3, 0, 0, 0, 0, 0},
                            })
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.175f)
                    .setWindowsY(0.2f)
                    .setWindowsHeight(0.3f)
                    .setWindowsQuantityOfLines(3)
                    .setWindowsVelocity(0.0023f);
        }
        
        //LEVEL73 - BASEADO NO 39
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 2, 1, 1, 1, 4, 1, 1, 1, 2, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 2, 2, 1, 1, 4, 1, 1, 1, 2, 2},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true)
                        )
                    .setSpecialBallPercentage(0.4f)
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.2f)
                    .setWindowsY(0.25f)
                    .setWindowsHeight(0.325f)
                    .setWindowsQuantityOfLines(3)
                    .setWindowsVelocity(-0.0026f);

        }
        
        //LEVEL 74 - BASEADO NO 33
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{0, 1, 2, 3, 7, 8});
            targetsAppend.add(new int[]{10, 11, 16, 17, 24, 25});

            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 0, 0, 0, 0, 0, 1, 2, 1},
                                    {2, 2, 2, 3, 3, 3, 0, 0, 1, 2, 1},
                                    {0, 0, 1, 0, 0, 4, 0, 0, 1, 2, 1},
                                    {1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 4, 0, 0, 1, 2, 1},
                                    {0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1},
                                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
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
                                    .setyVelocity(0.0017f)
                    )
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.225f)
                    .setWindowsY(0.3f)
                    .setWindowsHeight(0.35f)
                    .setWindowsQuantityOfLines(4)
                    .setWindowsVelocity(0.0027f)
                    .setWindType(Level.WIND_TYPE_RIGHT);
        }
        
       // LEVEL 75 - BASEADO NO 53
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 5, 5, 5, 0, 0, 1, 5, 5, 5, 0},
                                    {0, 5, 5, 5, 0, 0, 0, 5, 5, 5, 0},
                                    {2, 0, 3, 1, 1, 0, 1, 0, 1, 1, 2},
                                    {1, 1, 0, 2, 1, 0, 1, 2, 3, 1, 1},
                                    {1, 1, 0, 2, 1, 0, 1, 2, 0, 1, 1},
                                    {2, 1, 0, 1, 1, 0, 1, 1, 0, 1, 2},
                                    {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
                                    {0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1},
                                    {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                                    {1, 2, 3, 1, 0, 0, 1, 1, 3, 2, 0},
                                    {1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0},
                                    {5, 5, 0, 0, 0, 0, 5, 5, 0, 0, 0},
                                    {5, 5, 0, 0, 0, 0, 5, 5, 0, 0, 0}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.47f)
                    .setObstaclesY(0.04f)
                    .setObstaclesWidth(0.06f)
                    .setObstaclesHeight(0.2f)
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage(0.4f)
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.25f)
                    .setWindowsY(0.25f)
                    .setWindowsHeight(0.35f)
                    .setWindowsQuantityOfLines(4)
                    .setWindowsVelocity(-0.003f);
        }
        
       // LEVEL 76 - BASEADO NO 46
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{14, 22, 23, 29, 30, 38});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 5, 0, 0, 0, 5, 0, 0, 1, 5, 1},
                                    {1, 5, 1, 1, 4, 5, 1, 0, 1, 5, 1},
                                    {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
                                    {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {4, 1, 1, 1, 1, 1, 0, 0, 4, 1, 1},
                                    {0, 0, 2, 2, 2, 2, 3, 5, 1, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 3, 5, 1, 0, 0},
                                    {1, 5, 3, 1, 5, 3, 3, 0, 1, 2, 2},
                                    {1, 5, 3, 1, 5, 3, 3, 0, 1, 2, 2},
                                    {1, 0, 3, 1, 0, 3, 3, 1, 1, 0, 0},
                                    {1, 0, 3, 1, 0, 3, 3, 1, 1, 0, 0},
                                    {1, 1, 3, 1, 1, 3, 0, 0, 0, 0, 0},
                                    {1, 1, 3, 1, 1, 3, 0, 0, 0, 0, 0},
   
                            })
                    .setWindowsQuantity(2)
                    .setWindowsDistance(0.2f, 0.2f)
                    .setWindowsY(0.05f, 0.45f)
                    .setWindowsHeight(0.2f, 0.2f)
                    .setWindowsQuantityOfLines(3, 3)
                    .setWindowsVelocity(-0.003f, -0.0029f);
        }
        
       // LEVEL 77 - BASEADO NO 57
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 4, 1, 0, 0, 0, 0, 0, 3, 0},
                                    {2, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                    {3, 0, 1, 0, 2, 1, 0, 1, 2, 0, 1},
                                    {3, 0, 1, 0, 0, 3, 3, 3, 0, 0, 1},
                                    {2, 0, 1, 0, 0, 3, 3, 3, 0, 0, 4},
                                    {1, 0, 1, 0, 2, 1, 0, 1, 2, 0, 1},
                                    {1, 0, 0, 1, 1, 0, 0, 0, 1, 3, 1},
                                    {2, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                                    {2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.5506666667f, 0.275f, 0.6f)
                    .setObstaclesY(0.0242647059f, 0.5045382353f, 0.65f)
                    .setObstaclesWidth(0.1f, 0.1266666667f, 0.1266666667f)
                    .setObstaclesHeight(0.04f, 0.0610294118f, 0.0610294118f)
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
                            null,
                            null)
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
                                    .setyVelocity(0f))
                 .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage(0.4f)
                    .setWindowsQuantity(2)
                    .setWindowsDistance(0.2f, 0.2f)
                    .setWindowsY(0.05f, 0.4f)
                    .setWindowsHeight(0.2f, 0.2f)
                    .setWindowsQuantityOfLines(2, 2)
                    .setWindowsVelocity(0.0029f, -0.003f);
        }
        
       // LEVEL 78 - BASEADO NO 60
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 4, 1, 2, 0, 1, 1, 4, 1, 2},
                                    {1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 2},
                                    {1, 1, 1, 1, 2, 0, 1, 1, 1, 1, 2},
                                    {2, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0},
                                    {2, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0},
                                    {2, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {0, 0, 2, 0, 3, 0, 3, 0, 0, 0, 2},
                                    {0, 0, 2, 0, 3, 0, 3, 0, 0, 0, 2},
                                    {0, 0, 2, 0, 3, 0, 3, 0, 0, 0, 2},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage(0.4f)
                    .setWindType(Level.WIND_TYPE_LEFT)
                    .setWindowsQuantity(2)
                    .setWindowsDistance(0.22f, 0.22f)
                    .setWindowsY(0.1f, 0.5f)
                    .setWindowsHeight(0.25f, 0.2f)
                    .setWindowsQuantityOfLines(2, 2)
                    .setWindowsVelocity(-0.003f, 0.0029f);
        }
        
        //VELOCIDADE
        // LEVEL 79
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 2, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0},
                                    {2, 0, 0, 0, 0, 0, 1, 2, 2, 1, 0},
                                    {0, 0, 0, 0, 0, 1, 3, 0, 0, 3, 1},
                                    {1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0},
                                    {4, 0, 2, 0, 0, 0, 0, 1, 1, 0, 0},
                                    {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 2, 0, 0, 1, 0, 1, 1, 0, 1},
                                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                    {0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 0, 0, 3, 1, 0, 0, 0, 0, 0},
                                    {0, 3, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0}
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.2765066667f, 0.7275566667f)
                    .setObstaclesY(0.0832029412f, 0.3970882353f)
                    .setObstaclesWidth(0.08705f, 0.08705f)
                    .setObstaclesHeight(0.0605f, 0.0605f);
  
        }
        
        // LEVEL80
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 4},
                                    {1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1},
                                    {1, 1, 1, 0, 3, 4, 3, 0, 1, 1, 1},
                                    {2, 2, 2, 3, 0, 3, 0, 3, 2, 2, 2},
                                    {2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2},
                                    {1, 1, 1, 5, 5, 3, 5, 5, 1, 1, 1},
                                    {0, 1, 1, 5, 5, 7, 5, 5, 0, 1, 1},
                                    {1, 0, 1, 5, 6, 6, 6, 5, 1, 0, 1},
                                    {0, 0, 0, 5, 1, 5, 1, 5, 0, 0, 1},
                                    {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1}
                            })
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.15f, 0.45f, 0.75f)
                    .setObstaclesY(0.5470588235f, 0.5470588235f, 0.5470588235f)
                    .setObstaclesWidth(0.1f, 0.1f, 0.1f)
                    .setObstaclesHeight(0.083f, 0.083f, 0.083f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0018f),
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0017f));
  
        }
        
        // LEVEL 81
        l += 1;
        if (levelNumber == l) {
           ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{8, 9, 11, 12, 16, 17});
            targetsAppend.add(new int[]{19, 20, 27, 28, 35, 36});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                {3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 4},
                                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                                {2, 3, 2, 3, 2, 3, 0, 0, 0, 0, 4},
                                {0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0},
                                {1, 1, 1, 2, 3, 2, 3, 2, 3, 2, 3},
                                {1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
                                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                                {2, 3, 2, 0, 0, 0, 0, 0, 1, 1, 1},
                                {1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                                {3, 2, 3, 1, 0, 0, 0, 0, 1, 0, 1},
                                {0, 0, 0, 2, 3, 2, 1, 1, 1, 0, 1},
                                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.6816666667f)
                    .setObstaclesY(0.0841088235f)
                    .setObstaclesWidth(0.0895f)
                    .setObstaclesHeight(0.0695029412f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.011f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f))
                .setWindType(Level.WIND_TYPE_RIGHT);
        }

        // LEVEL82
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 0, 0, 0, 5, 5, 5, 0, 0, 0, 0},
                                    {0, 0, 0, 2, 1, 0, 1, 3, 0, 0, 0},
                                    {0, 0, 2, 2, 0, 0, 0, 3, 4, 0, 0},
                                    {0, 2, 2, 0, 0, 4, 0, 0, 3, 3, 0},
                                    {1, 0, 0, 0, 1, 1, 1, 0, 0, 3, 3},
                                    {1, 0, 0, 2, 1, 3, 1, 3, 0, 0, 1},
                                    {5, 0, 2, 1, 0, 4, 0, 1, 3, 0, 5},
                                    {5, 2, 1, 0, 1, 3, 1, 0, 1, 3, 5},
                                    {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                                    {5, 0, 1, 0, 1, 3, 1, 0, 1, 0, 5},
                                    {5, 1, 0, 1, 0, 0, 0, 1, 0, 1, 5},
                                    {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1}
                            })
                    .setSpecialBallPercentage(0.63f)
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true));
        }

        // LEVEL 83
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 3, 0, 1, 0, 3, 1, 1, 1},
                                    {1, 1, 1, 3, 0, 1, 0, 3, 1, 4, 1},
                                    {1, 4, 1, 3, 1, 1, 1, 3, 1, 1, 1},
                                    {2, 2, 1, 3, 1, 1, 1, 3, 1, 2, 2},
                                    {2, 2, 0, 1, 1, 1, 1, 1, 0, 2, 2},
                                    {1, 3, 0, 1, 1, 1, 1, 1, 0, 3, 1},
                                    {0, 3, 0, 0, 1, 0, 1, 0, 0, 3, 1},
                                    {0, 3, 0, 0, 1, 0, 1, 0, 0, 3, 1},
                                    {0, 3, 0, 0, 1, 0, 0, 0, 0, 3, 1},
                                    {1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 3, 1, 0, 0, 0, 0, 1, 1},
                                    {0, 0, 0, 3, 1, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0}
                            })
                    .setBarsScaleVariation(
                        new ScaleVariationDataBuilder()
                                .setIsActive(true)
                                .setIncreaseWidth(true)
                                .setIncreaseHeight(false)
                                .setWidthVelocity(0.0055f)
                                .setHeightVelocity(0f)
                                .setMinWidth_BI(0.625f)
                                .setMaxWidth_BI(1.5f)
                                .setMinHeight_BI(1f)
                                .setMaxHeight_BI(1f),
                        new ScaleVariationDataBuilder()
                                .setIsActive(true)
                                .setIncreaseWidth(true)
                                .setIncreaseHeight(false)
                                .setWidthVelocity(0.0055f)
                                .setHeightVelocity(0f)
                                .setMinWidth_BI(0.625f)
                                .setMaxWidth_BI(1.5f)
                                .setMinHeight_BI(1f)
                                .setMaxHeight_BI(1f));
        }

        // LEVEL 84
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{27, 35, 36, 44});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 1, 1, 2, 0, 2, 1, 1, 1, 0},
                                    {0, 3, 1, 2, 0, 2, 0, 3, 1, 1, 0},
                                    {0, 1, 1, 2, 1, 0, 4, 3, 1, 1, 0},
                                    {0, 1, 3, 1, 1, 2, 3, 1, 1, 1, 0},
                                    {1, 1, 0, 2, 1, 0, 1, 3, 0, 1, 1},
                                    {1, 1, 0, 3, 2, 2, 3, 1, 0, 1, 1},
                                    {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 3, 2, 1, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                            })
                    .setObstaclesQuantity(1)
                    .setObstaclesX(0.1705833333f)
                    .setObstaclesY(0.3567647059f)
                    .setObstaclesWidth(0.1433333333f)
                    .setObstaclesHeight(0.0588235294f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.002f))
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.15f)
                    .setWindowsY(0.15f)
                    .setWindowsHeight(0.3f)
                    .setWindowsQuantityOfLines(3)
                    .setWindowsVelocity(0.002f);
        }

        // LEVEL 85
        l += 1;
        if (levelNumber == l) {
            ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{14, 22, 23, 29, 30, 38});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 0, 0, 0, 1, 0, 0, 3, 1, 1},
                                    {1, 1, 2, 1, 4, 1, 1, 0, 3, 1, 1},
                                    {1, 0, 2, 1, 1, 0, 1, 1, 3, 0, 1},
                                    {1, 0, 2, 0, 1, 0, 1, 1, 3, 0, 1},
                                    {1, 1, 2, 0, 1, 1, 1, 0, 1, 1, 1},
                                    {4, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                                    {0, 0, 2, 2, 2, 2, 3, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 3, 1, 1, 0, 0},
                                    {1, 1, 3, 0, 1, 0, 3, 0, 1, 2, 2},
                                    {1, 1, 3, 1, 1, 0, 3, 0, 1, 2, 2},
                                    {1, 0, 3, 1, 1, 0, 3, 1, 1, 1, 1},
                                    {1, 0, 3, 0, 0, 0, 3, 1, 1, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 1}

                            });
        }

        // LEVEL 86
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 2, 1, 4, 2, 1, 1, 2, 1, 1},
                                    {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1},
                                    {1, 1, 3, 1, 1, 3, 1, 1, 3, 1, 1},
                                    {1, 1, 3, 1, 1, 3, 1, 4, 3, 1, 1},
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 3, 0, 0, 0, 0, 0, 3, 1, 1},
                                    {1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1},
                                    {1, 1, 2, 0, 0, 0, 0, 0, 2, 1, 1}
                            })
                    .setFakeBallPercentage(0.6f)
                    .setWindType(Level.WIND_TYPE_LEFT);
        }

        // ---------- LEVEL87
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 4, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                                    {0, 0, 1, 1, 0, 2, 2, 1, 1, 0, 1},
                                    {0, 1, 0, 2, 1, 2, 2, 1, 2, 0, 1},
                                    {0, 1, 0, 0, 3, 3, 3, 3, 0, 0, 1},
                                    {0, 1, 0, 0, 3, 3, 3, 3, 0, 0, 4},
                                    {0, 1, 0, 2, 1, 0, 0, 1, 2, 0, 1},
                                    {0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1},
                                    {0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            })
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.5506666667f, 0.4f)
                    .setObstaclesY(0.0242647059f, 0.63f)
                    .setObstaclesWidth(0.1f, 0.1266666667f)
                    .setObstaclesHeight(0.0610294118f, 0.0610294118f)
                    .setObstaclesScaleVariation(
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
                            null)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.002f)
                                    .setyVelocity(0f));
        }

        // ---------- LEVEL 88
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 2, 1, 3, 0, 3, 1, 1, 3, 1},
                                    {0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0},
                                    {1, 1, 2, 1, 3, 0, 3, 1, 1, 1, 1},
                                    {3, 2, 3, 0, 0, 0, 0, 0, 3, 2, 3},
                                    {0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0},
                                    {3, 2, 3, 0, 0, 0, 0, 0, 3, 3, 3},
                                    {0, 2, 1, 0, 0, 0, 0, 0, 1, 2, 0},
                                    {2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2},
                                    {0, 2, 1, 0, 0, 0, 0, 0, 1, 2, 0},
                            })

                    .setObstaclesQuantity(4)
                    .setObstaclesX(0.1745833333f, 0.31f, 0.64f, 0.80425f)
                    .setObstaclesY(0.41225f, 0.2f, 0.2f, 0.41225f)
                    .setObstaclesWidth(0.02f, 0.02f, 0.02f, 0.02f)
                    .setObstaclesHeight(0.3f, 0.3f, 0.3f, 0.3f);
        }

        //LEVEL89
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 2, 1, 1, 1, 4, 1, 1, 1, 2, 0},
                                    {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1},
                                    {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
                                    {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                                    {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {2, 2, 1, 1, 1, 4, 1, 1, 2, 2, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0},
                                    {0, 1, 1, 1, 1, 3, 1, 1, 1, 1, 0}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true)
                    )
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.15f)
                    .setWindowsY(0.15f)
                    .setWindowsHeight(0.35f)
                    .setWindowsQuantityOfLines(5)
                    .setWindowsVelocity(0.002f);
        }

        //LEVEL90
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}});
        }

        // MISTURA

        //LEVEL91
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1,0,1,0,1,0,1,0,1,0,4},
                                    {0,3,1,5,1,3,1,5,1,3,0},
                                    {0,3,1,5,1,3,1,5,1,3,0},
                                    {1,0,1,1,1,0,1,1,1,0,1},
                                    {0,2,1,1,1,4,1,1,1,2,0},
                                    {0,2,1,1,1,4,1,1,1,2,0},
                                    {1,2,1,0,1,0,1,0,1,2,3},
                                    {1,0,1,1,1,0,1,1,1,0,3},
                                    {0,1,1,1,1,2,1,1,1,1,0},
                                    {0,1,1,1,1,2,1,1,1,1,0},
                                    {0,1,1,1,1,2,1,1,1,1,0},
                                    {0,1,1,0,1,0,1,0,1,1,0},
                                    {2,0,1,3,1,0,1,3,1,0,2},
                                    {2,3,1,3,1,3,1,3,1,3,2},
                                    {2,3,1,0,1,3,1,0,1,3,2}
                                    })
                    .setSpecialBallPercentage(0.63f)
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setWindType(Level.WIND_TYPE_RIGHT)
                    .setFakeBallPercentage(0.6f);
        }

        //LEVEL92
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1,0,0,0,0,0,0,0,0,0,4},
                                    {0,3,0,0,1,3,1,7,7,3,0},
                                    {0,3,0,0,1,3,1,7,7,3,0},
                                    {1,0,0,0,5,0,5,7,7,0,1},
                                    {0,2,0,0,5,4,5,0,0,2,0},
                                    {0,2,0,0,5,5,5,0,0,2,0},
                                    {1,2,0,0,0,0,0,0,0,2,3},
                                    {1,0,0,0,1,0,1,0,0,0,3},
                                    {0,1,7,7,1,2,1,0,0,1,0},
                                    {0,1,7,7,1,2,1,0,0,1,0},
                                    {0,1,7,7,1,2,1,0,0,1,0},
                                    {0,1,0,0,0,0,0,0,0,0,2},
                                    {2,0,0,0,3,0,3,0,0,0,2},
                                    {2,3,0,0,3,3,3,0,0,3,2},
                                    {2,3,0,0,0,3,0,0,0,3,2}

                            })

                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f))
                    .setWindType(Level.WIND_TYPE_LEFT)
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.1980166667f, 0.6550883333f)
                    .setObstaclesY(0.1708205882f, 0.3708205882f)
                    .setObstaclesWidth(0.151815f, 0.151815f)
                    .setObstaclesHeight(0.0924088235f, 0.0924088235f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0018f)
                                    .setyVelocity(0f),
                            null
                            )
                    .setObstaclesScaleVariation(
                            null,
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setIncreaseHeight(true)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                            )
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.15f)
                    .setWindowsY(0.3f)
                    .setWindowsHeight(0.3f)
                    .setWindowsQuantityOfLines(1)
                    .setWindowsVelocity(-0.002f)
                    .setInvertedButtons(true);
        }

        //LEVEL93
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0,1,1,0,0,3,0,0,1,1,0},
                                    {2,0,1,0,1,4,2,0,1,0,2},
                                    {2,3,1,0,0,1,0,0,1,3,2},
                                    {2,3,1,1,1,0,1,1,1,3,2},
                                    {0,0,2,0,1,0,1,0,2,0,0},
                                    {0,0,2,3,1,0,1,3,2,0,0},
                                    {0,0,2,3,1,0,1,3,2,0,0},
                                    {0,0,2,3,1,0,1,3,2,0,0},
                                    {0,0,2,3,1,0,1,3,2,0,0},
                                    {0,0,2,3,1,0,1,3,2,0,0},
                                    {0,0,2,0,1,0,1,0,2,0,0},
                                    {2,3,1,1,1,0,1,1,1,3,2},
                                    {2,3,1,0,0,3,0,0,1,3,2},
                                    {2,0,1,0,1,4,2,0,1,0,2},
                                    {0,1,1,0,0,1,0,0,1,1,0}
                            })

                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.0005366667f, 0.0698133333f, 0.4791083333f, 0.892615f, 0.9639133333f)
                    .setObstaclesY(0.5624235294f, 0.1993205882f, 0.1993205882f, 0.1993205882f, 0.5624235294f)
                    .setObstaclesWidth(0.0355566667f, 0.0355566667f, 0.0355566667f, 0.0355566667f, 0.0355566667f)
                    .setObstaclesHeight(0.1996f, 0.1996f, 0.1996f, 0.1996f, 0.1996f)
                    .setWindowsQuantity(2)
                    .setWindowsDistance(0.15f, 0.15f)
                    .setWindowsY(0.3f, 0.5f)
                    .setWindowsHeight(0.15f, 0.15f)
                    .setWindowsQuantityOfLines(1)
                    .setWindowsVelocity(0.002f, 0.003f)
                    .setFakeBallPercentage(0.6f);


        }

        //LEVEL94
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1,1,1,3,2,3,2,3,1,1,1},
                                    {1,0,1,1,1,3,1,1,1,0,1},
                                    {1,0,0,1,1,1,1,1,0,0,1},
                                    {1,0,0,0,1,1,1,0,0,0,1},
                                    {1,0,0,0,1,1,1,0,0,0,1},
                                    {1,4,0,0,1,1,1,0,0,4,1},
                                    {1,1,1,0,1,0,1,0,1,1,1},
                                    {1,1,1,1,1,0,1,1,1,1,1},
                                    {1,1,1,1,1,0,1,1,1,1,1},
                                    {1,1,3,1,1,1,1,1,3,1,1},
                                    {1,3,0,3,1,1,1,3,0,3,1},
                                    {1,0,0,1,1,2,1,1,0,0,3},
                                    {3,0,3,1,1,2,1,1,3,0,1},
                                    {1,3,1,1,1,2,1,1,1,3,1},
                                    {1,1,1,0,1,2,1,0,1,1,1},
                                    {1,1,0,0,1,1,1,0,0,1,1}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f))
                    .setObstaclesQuantity(4)
                    .setObstaclesX(0.1011766667f, 0.2636766667f, 0.477565f, 0.8091283333f)
                    .setObstaclesY(0.1023264706f, 0.1454617647f, 0.1454617647f, 0.1023264706f)
                    .setObstaclesWidth(0.089315f, 0.089315f, 0.089315f, 0.089315f)
                    .setObstaclesHeight(0.0924088235f, 0.0924088235f, 0.0924088235f, 0.0924088235f)
                    .setObstaclesPositionVariation(
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0018f)
                                    .setyVelocity(0f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0018f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0018f)
                                    .setyVelocity(0f),
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0f)
                                    .setyVelocity(0.0018f)
                    );
        }

        //LEVEL95
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0,0,2,0,0,0,0,0,0,3,0},
                                    {0,2,5,2,2,0,0,0,3,5,3},
                                    {0,5,5,2,5,2,1,0,5,5,0},
                                    {2,5,2,5,5,1,0,4,5,3,0},
                                    {0,2,2,5,2,0,0,0,3,0,0},
                                    {0,5,0,2,1,0,1,3,0,0,0},
                                    {0,5,1,0,0,1,3,0,3,0,0},
                                    {0,1,5,1,0,0,0,2,0,0,0},
                                    {0,5,5,0,0,3,2,3,2,1,0},
                                    {1,5,1,1,0,0,3,0,1,5,1},
                                    {0,1,1,5,1,2,0,2,5,5,0},
                                    {0,0,5,5,0,1,2,1,5,1,0},
                                    {0,1,5,1,1,5,1,0,1,0,0},
                                    {0,0,1,0,5,5,0,0,0,0,0},
                                    {0,0,0,1,5,1,0,0,0,0,0},
                                    {0,0,0,0,1,0,0,0,0,0,0}
                            })
                    .setWindType(Level.WIND_TYPE_RIGHT)
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage(0.63f)
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.15f)
                    .setWindowsY(0.3f)
                    .setWindowsHeight(0.3f)
                    .setWindowsQuantityOfLines(3)
                    .setWindowsVelocity(-0.003f);
        }

        //LEVEL96
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {0,0,0,0,0,1,0,0,0,0,0},
                                    {0,0,0,0,0,2,0,0,0,0,0},
                                    {0,3,0,0,0,2,0,0,0,3,0},
                                    {3,3,3,0,1,2,1,0,3,3,0},
                                    {0,3,3,0,1,2,1,0,3,3,3},
                                    {0,3,3,3,1,1,1,3,3,3,0},
                                    {0,1,3,1,1,1,1,1,3,1,0},
                                    {0,1,1,1,1,0,1,1,1,1,0},
                                    {0,1,1,1,1,0,1,1,1,1,0},
                                    {0,1,1,2,1,0,1,2,1,1,0},
                                    {0,1,2,2,0,0,0,2,2,1,0},
                                    {0,2,2,2,1,1,1,2,2,2,0},
                                    {0,2,2,2,1,1,1,2,2,2,0},
                                    {0,2,2,1,1,1,1,1,2,2,0},
                                    {0,2,1,1,1,3,1,1,1,2,0},
                                    {0,1,1,3,2,3,2,3,1,1,0}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f))
                    .setFakeBallPercentage(0.6f)
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.01481f, 0.6418083333f, 0.9226166667f)
                    .setObstaclesY(0.2617941176f, 0.0169117647f, 0.0169117647f)
                    .setObstaclesWidth(0.0582483333f, 0.089315f, 0.089315f)
                    .setObstaclesHeight(0.2294647059f, 0.0924088235f, 0.0924088235f)
                    .setObstaclesPositionVariation(
                            null,
                            new PositionVariationDataBuilder()
                                    .setIsActive(true)
                                    .setMaxX(0.99f)
                                    .setMinX(0.01f)
                                    .setMaxY(0.9f)
                                    .setMinY(0.01f)
                                    .setxVelocity(0.0018f)
                                    .setyVelocity(0f),
                            null
                    )
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setIncreaseHeight(true)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            null,
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setIncreaseHeight(true)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    );
        }
        
        
       //LEVEL97
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                {0,0,0,0,1,1,1,0,0,0,1},
                                {0,0,0,0,1,1,1,1,1,1,1},
                                {0,1,1,1,1,1,1,1,1,1,1},
                                {0,1,1,1,1,1,1,1,1,1,1},
                                {0,1,1,1,1,1,1,1,1,1,1},
                                {0,1,1,1,1,1,1,1,1,1,1},
                                {0,1,1,1,1,2,1,1,1,1,1},
                                {0,1,0,0,1,2,1,1,1,1,1},
                                {0,1,0,0,1,2,1,1,7,1,1},
                                {0,1,1,0,1,1,1,1,7,1,1},
                                {0,1,1,0,1,1,1,1,7,1,1},
                                {0,1,1,0,1,1,1,1,7,1,1},
                                {0,1,1,0,1,1,1,1,1,1,1},
                                {5,5,5,5,1,1,1,1,1,1,1},
                                {5,5,5,5,1,1,1,1,1,1,1},
                                {1,1,1,1,1,1,1,0,0,0,1}
                            })
                    .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true),
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setWidthVelocity(0.00025f)
                                    .setMinWidth_BI(0.05f)
                                    .setMaxWidth_BI(1.5f)
                                    .setAlwaysDecrease(true))
                    .setSpecialBallPercentage(0.63f)
                    .setObstaclesQuantity(3)
                    .setObstaclesX(0.0194166667f, 0.2012816667f, 0.2012816667f)
                    .setObstaclesY(0.4820941176f, 0.0161058824f, 0.0.3107764706f)
                    .setObstaclesWidth(0.0530716667f, 0.0530716667f, 0.0530716667f)
                    .setObstaclesHeight(0.0581470588f, 0.0581470588f, 0.0581470588f)
                    .setWindowsQuantity(1)
                    .setWindowsDistance(0.20f)
                    .setWindowsY(0.25f)
                    .setWindowsHeight(0.5f)
                    .setWindowsQuantityOfLines(5)
                    .setWindowsVelocity(0.003f);
        }
        
        //LEVEL98
        l += 1;
        if (levelNumber == l) {
             ArrayList<int[]> targetsAppend = new ArrayList<>();
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{});
            targetsAppend.add(new int[]{46, 54, 55, 62, 63, 71, 72, 80, 81, 88});
            levelBuilder
                    .setBallsTargetsAppend(targetsAppend)
                    .setTargetsMap(
                            new int[][]{
                                
                                {1,1,1,1,1,1,1,1,3,1,1}
                                {1,3,1,1,4,1,1,3,1,1,0}
                                {0,1,3,1,1,1,3,1,1,0,0}
                                {0,1,1,3,1,3,1,1,0,0,1}
                                {1,0,1,1,3,1,1,0,0,1,1}
                                {1,0,1,1,1,1,0,0,1,1,1}
                                {1,1,0,1,1,0,0,1,1,1,1}
                                {1,1,0,1,1,0,4,1,2,1,1}
                                {1,1,0,1,1,0,1,1,2,1,1}
                                {1,1,0,1,1,0,0,1,1,1,1}
                                {1,0,1,1,1,1,0,0,1,1,1}
                                {1,0,1,1,2,1,1,0,0,1,1}
                                {0,1,1,2,1,2,1,1,0,0,1}
                                {0,1,2,1,1,1,2,1,1,0,0}
                                {1,2,1,0,0,0,1,2,1,1,0}
                                {1,1,1,0,0,0,1,1,2,1,1}
                            })
                     .setBarsScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(true)
                                    .setIncreaseHeight(false)
                                    .setWidthVelocity(0.0055f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            null)
                    .setFakeBallPercentage(0.6f)
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.46793f, 0.476205f)
                    .setObstaclesY(0.2871058824f, 0.7357411765f)
                    .setObstaclesWidth(0.0500933333f, 0.0368833333f)
                    .setObstaclesHeight(0.1012323529f, 0.0552588235f)
                    .setObstaclesScaleVariation(
                            new ScaleVariationDataBuilder()
                                    .setIsActive(true)
                                    .setIncreaseWidth(false)
                                    .setIncreaseHeight(true)
                                    .setWidthVelocity(0f)
                                    .setHeightVelocity(0.012f)
                                    .setMinWidth_BI(1f)
                                    .setMaxWidth_BI(100000f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f),
                            null);
        }

        if (levelNumber == 1000) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 0},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1},
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                            });
        }

        Level.levelObject = levelBuilder.build();
    }
}
