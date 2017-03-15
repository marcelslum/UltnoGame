package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {

    public static void loadLevel(int levelNumber) {

        Log.e("LevelLoader", "levelNumber "+levelNumber);

        Level.LevelBuilder levelBuilder = new Level.LevelBuilder();
        
        if (levelNumber < 1000) {
            levelBuilder
                    .setBallsQuantity(LevelLoaderData.ballsQuantity[levelNumber - 1])
                    .setMinBallsAlive(LevelLoaderData.minBallsAlive[levelNumber - 1])
                    .setBallsRadius(LevelLoaderData.ballsRadius[levelNumber - 1])
                    .setBallsX(LevelLoaderData.ballsX[levelNumber - 1])
                    .setBallsY(LevelLoaderData.ballsY[levelNumber - 1])
                    .setBallsVX(LevelLoaderData.ballsVX[levelNumber - 1])
                    .setBallsVY(LevelLoaderData.ballsVY[levelNumber - 1])
                    .setBallsTextureMap(LevelLoaderData.ballsTextureMap[levelNumber - 1])
                    .setBallsInvencible(LevelLoaderData.ballsInvencible[levelNumber - 1])
                    .setBallsAngleToRotate(LevelLoaderData.ballsAngleToRotate[levelNumber - 1])
                    .setBallsMaxAngle(LevelLoaderData.ballsMaxAngle[levelNumber - 1])
                    .setBallsMinAngle(LevelLoaderData.ballsMinAngle[levelNumber - 1])
                    .setBallsVelocityMax(LevelLoaderData.ballsMaxVel[levelNumber - 1])
                    .setBallsVelocityMin(LevelLoaderData.ballsMinVel[levelNumber - 1])
                    .setBallsVelocityVariation(LevelLoaderData.ballsVelocityVariation[levelNumber - 1])
                    .setBallsFree(LevelLoaderData.ballsFree[levelNumber - 1])
                    .setBarsQuantity(LevelLoaderData.barsQuantity[levelNumber - 1])
                    .setBarsWidth(LevelLoaderData.barsWidth[levelNumber - 1])
                    .setBarsHeight(LevelLoaderData.barsHeight[levelNumber - 1])
                    .setBarsX(LevelLoaderData.barsX[levelNumber - 1])
                    .setBarsY(LevelLoaderData.barsY[levelNumber - 1])
                    .setBarsVX(LevelLoaderData.barsVX[levelNumber - 1])
                    .setBarsVY(LevelLoaderData.barsVY[levelNumber - 1])
                    .setTargetWidth(LevelLoaderData.targetWidth[levelNumber - 1])
                    .setTargetHeight(LevelLoaderData.targetHeight[levelNumber - 1])
                    .setTargetDistance(LevelLoaderData.targetDistance[levelNumber - 1])
                    .setTargetPadd(LevelLoaderData.targetPadd[levelNumber - 1])
                    .setBarsScaleVariationOff()
                    .setObstaclesScaleVariationOff()
                    .setObstaclesPositionVariationOff()
                    .setWindType(Level.WIND_TYPE_NO)
                    .setSpecialBallPercentage(0f)
                    .setFakeBallPercentage(0f)
                    .setObstaclesQuantity(0)
                    .setWindowsQuantity(0)
                    .setTutorialAttached(Tutorial.TUTORIAL_INICIO)
                    .setBallsTargetsAppend(new ArrayList<int[]>())
                    .setTargetsStates(new int[]{0, 1, 2, 3});
        } else {

            levelBuilder
                    .setBallsQuantity(LevelLoaderData.ballsQuantitySecret[levelNumber - 1000])
                    .setMinBallsAlive(LevelLoaderData.minBallsAliveSecret[levelNumber - 1000])
                    .setBallsRadius(LevelLoaderData.ballsRadiusSecret[levelNumber - 1000])
                    .setBallsX(LevelLoaderData.ballsXSecret[levelNumber - 1000])
                    .setBallsY(LevelLoaderData.ballsYSecret[levelNumber - 1000])
                    .setBallsVX(LevelLoaderData.ballsVXSecret[levelNumber - 1000])
                    .setBallsVY(LevelLoaderData.ballsVYSecret[levelNumber - 1000])
                    .setBallsTextureMap(LevelLoaderData.ballsTextureMapSecret[levelNumber - 1000])
                    .setBallsInvencible(LevelLoaderData.ballsInvencibleSecret[levelNumber - 1000])
                    .setBallsAngleToRotate(LevelLoaderData.ballsAngleToRotateSecret[levelNumber - 1000])
                    .setBallsMaxAngle(LevelLoaderData.ballsMaxAngleSecret[levelNumber - 1000])
                    .setBallsMinAngle(LevelLoaderData.ballsMinAngleSecret[levelNumber - 1000])
                    .setBallsVelocityMax(LevelLoaderData.ballsMaxVelSecret[levelNumber - 1000])
                    .setBallsVelocityMin(LevelLoaderData.ballsMinVelSecret[levelNumber - 1000])
                    .setBallsVelocityVariation(LevelLoaderData.ballsVelocityVariationSecret[levelNumber - 1000])
                    .setBallsFree(LevelLoaderData.ballsFreeSecret[levelNumber - 1000])
                    .setBarsQuantity(LevelLoaderData.barsQuantitySecret[levelNumber - 1000])
                    .setBarsWidth(LevelLoaderData.barsWidthSecret[levelNumber - 1000])
                    .setBarsHeight(LevelLoaderData.barsHeightSecret[levelNumber - 1000])
                    .setBarsX(LevelLoaderData.barsXSecret[levelNumber - 1000])
                    .setBarsY(LevelLoaderData.barsYSecret[levelNumber - 1000])
                    .setBarsVX(LevelLoaderData.barsVXSecret[levelNumber - 1000])
                    .setBarsVY(LevelLoaderData.barsVYSecret[levelNumber - 1000])
                    .setTargetWidth(LevelLoaderData.targetWidthSecret[levelNumber - 1000])
                    .setTargetHeight(LevelLoaderData.targetHeightSecret[levelNumber - 1000])
                    .setTargetDistance(LevelLoaderData.targetDistanceSecret[levelNumber - 1000])
                    .setTargetPadd(LevelLoaderData.targetPaddSecret[levelNumber - 1000])
                    .setBarsScaleVariationOff()
                    .setObstaclesScaleVariationOff()
                    .setObstaclesPositionVariationOff()
                    .setWindType(Level.WIND_TYPE_NO)
                    .setSpecialBallPercentage(0f)
                    .setFakeBallPercentage(0f)
                    .setInvertedButtons(false)
                    .setObstaclesQuantity(0)
                    .setWindowsQuantity(0)
                    .setTutorialAttached(Tutorial.TUTORIAL_INICIO)
                    .setBallsTargetsAppend(new ArrayList<int[]>())
                    .setTargetsStates(new int[]{0, 1, 2, 3});
            
            
        }

        // INÍCIO
        // ---------- LEVEL1
        int l = 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                    new int[][]{
                        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
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

        // ---------- LEVEL15
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
                                    {4, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 2, 1, 0, 0, 0, 1, 2, 0, 1},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
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
        // ---------- LEVEL24
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.28f, 0.5f)
                    .setObstaclesY(0.0925f, 0.2597f)
                    .setObstaclesWidth(0.22f, 0.22f)
                    .setObstaclesHeight(0.035f, 0.035f)
                    .setTargetsMap(
                            new int[][]{
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {2,0,0,0,0,0,0,3,0,0,2},
                                    {2,0,0,0,0,0,0,3,0,0,2},
                                    {2,0,0,0,0,0,0,3,0,0,2},
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {0,3,0,2,0,0,0,0,0,2,0},
                                    {0,3,0,2,0,0,0,0,0,2,0},
                                    {0,3,0,2,0,0,0,0,0,2,0},
                                    {1,1,1,1,1,1,1,1,1,1,1}
                            })
                    .setWindType(Level.WIND_TYPE_LEFT);
        }

        // ---------- LEVEL25
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


        // ---------- LEVEL26
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


        // LEVEL 27
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                                    {4, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
                                    {1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 1},
                                    {1, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0},
                                    {1, 0, 2, 1, 0, 0, 0, 1, 2, 0, 1},
                                    {1, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4},
                                    {1, 1, 1, 1, 3, 0, 3, 1, 1, 1, 1},
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
                    )
                    .setWindType(Level.WIND_TYPE_RIGHT);
        }

        // FANTASMA
        // ---------- LEVEL28
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

        // ---------- LEVEL29
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
                                    {0, 5, 0, 0, 0, 7, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {0, 1, 0, 0, 0, 7, 0, 0, 0, 1, 0},
                            }
                    );
        }

        // ---------- LEVEL30
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
        // ---------- LEVEL31
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
        
        
        // ---------- LEVEL32
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
        
        
        // ---------- LEVEL33
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
        // ---------- LEVEL34
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
        
        
        // ---------- LEVEL35
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
        
        // ---------- LEVEL36
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


        // barra com tamanho dinamico
        // ---------- LEVEL37
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
                                    .setWidthVelocity(0.005f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.7f)
                                    .setMaxWidth_BI(1.5f)
                                    .setMinHeight_BI(1f)
                                    .setMaxHeight_BI(1f)
                    );
        }

        // ---------- LEVEL38
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
                                    .setWidthVelocity(0.0051f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.7f)
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

        // ---------- LEVEL 39
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
                                    .setWidthVelocity(0.0052f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.65f)
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

        // ---------- LEVEL 40
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
                                    .setWidthVelocity(0.0053f)
                                    .setHeightVelocity(0f)
                                    .setMinWidth_BI(0.625f)
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

        // barra diminuindo
        // ---------- LEVEL41
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
                    .setSpecialBallPercentage(0.4f);

        }


        // ---------- LEVEL42
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
                    .setSpecialBallPercentage(0.395f)
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
        // ---------- LEVEL43

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
                    .setSpecialBallPercentage(0.39f);

        }

        // ---------- LEVEL44
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
                    .setSpecialBallPercentage(0.385f);
        }

        // BOLA FALSA
        // ---------- LEVEL45
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                    })
            .setFakeBallPercentage(0.7f);
        }

        // ---------- LEVEL46
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                            {0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                            {0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0},
                            {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1},
                            {1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
                            {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1},
                            {1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0},
                            {1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                    })
            .setFakeBallPercentage(0.55f);
        }

        // ---------- LEVEL 47
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {1, 0, 2, 1, 3, 0, 3, 1, 1, 0, 1},
                                    {0, 2, 0, 1, 0, 3, 0, 1, 0, 2, 0},
                                    {1, 0, 2, 1, 3, 0, 3, 1, 1, 0, 1},
                                    {3, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3},
                                    {0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0},
                                    {3, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3},
                                    {0, 2, 1, 0, 0, 0, 0, 0, 1, 2, 0},
                                    {2, 0, 1, 0, 0, 0, 0, 0, 1, 0, 2},
                                    {0, 2, 1, 0, 0, 0, 0, 0, 1, 2, 0},
                            })
                    .setFakeBallPercentage(0.6f)
                    .setObstaclesQuantity(2)
                    .setObstaclesX(0.31f, 0.64f)
                    .setObstaclesY(0.2f, 0.2f)
                    .setObstaclesWidth(0.02f, 0.02f)
                    .setObstaclesHeight(0.3f, 0.3f)
                    .setWindowsQuantity(0);
        }

        // ---------- LEVEL 48
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                            new int[][]{
                                    {4, 2, 0, 1, 0, 2, 0, 1, 0, 2, 1},
                                    {0, 2, 1, 0, 0, 2, 0, 0, 1, 2, 0},
                                    {1, 0, 0, 1, 0, 2, 0, 1, 0, 0, 1},
                                    {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
                                    {1, 0, 1, 1, 1, 4, 1, 1, 1, 0, 1},
                                    {1, 0, 0, 1, 0, 3, 0, 1, 0, 0, 1},
                                    {1, 0, 1, 1, 0, 3, 0, 1, 1, 0, 1},
                                    {0, 2, 0, 0, 1, 0, 1, 0, 0, 2, 0},
                                    {0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0},
                            })
                    .setFakeBallPercentage(0.6f)
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

        // ---------- LEVEL49
        l += 1;
        if (levelNumber == l) {
            levelBuilder
                    .setFakeBallPercentage(0.6f)
                    .setTargetsMap(
                            new int[][]{
                                    {0, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0},
                                    {2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
                                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                                    {5, 5, 0, 0, 0, 7, 0, 0, 0, 5, 5},
                                    {5, 5, 0, 0, 0, 0, 0, 0, 0, 5, 5},
                                    {0, 5, 0, 0, 0, 7, 0, 0, 0, 5, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                            }
                    );
        }

        // ESPELHO
        // ---------- LEVEL50
        l += 1;
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
        
        //LEVEL 73 - BASEADO NO 39
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
                    .setObstaclesX(0.5506666667f, 0.275f, 0.6f)
                    .setObstaclesY(0.0242647059f, 0.5045382353f, 0.65f)
                    .setObstaclesWidth(0.1f, 0.1266666667f, 0.1266666667f)
                    .setObstaclesHeight(0.04f, 0.0610294118f, 0.0610294118f);
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
