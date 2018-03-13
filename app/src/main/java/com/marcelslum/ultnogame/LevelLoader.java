package com.marcelslum.ultnogame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {

    public static void loadLevel(int levelNumber) {

        Log.e("LevelLoader", "levelNumber "+levelNumber);

        Level.LevelBuilder levelBuilder = new Level.LevelBuilder();

        ArrayList<BallDataBaseData> ballDataBaseData = DataBaseLevelDataHelper.getInstance(Game.mainActivity).getBalls(levelNumber);
        ArrayList<BarDataBaseData> barDataBaseData = DataBaseLevelDataHelper.getInstance(Game.mainActivity).getBars(levelNumber);
        ArrayList<TargetDataBaseData> targetlDataBaseData = DataBaseLevelDataHelper.getInstance(Game.mainActivity).getTargets(levelNumber <= 100 ? 1 : 2);

        int minBallsAlive = 1;
        for (int i = 0; i < Game.levelsDataBaseData.size(); i++){
            if (Game.levelsDataBaseData.get(i).number == levelNumber){
                minBallsAlive = Game.levelsDataBaseData.get(i).min_balls_alive;
            }
        }

        levelBuilder
                .setMinBallsAlive(minBallsAlive)
                .setBallDataBaseData(ballDataBaseData)
                .setBarDataBaseData(barDataBaseData)
                .setTargetDataBaseData(targetlDataBaseData)
                         .setBarsScaleVariationOff()
                .setObstaclesScaleVariationOff()
                .setObstaclesPositionVariationOff()
                .setWindType(Level.WIND_TYPE_NO)
                .setInvertedButtons(false)
                .setSpecialBallPercentage(0f)
                .setFakeBallPercentage(0f)
                .setObstaclesQuantity(0)
                .setWindowsQuantity(0)
                .setBallsTargetsAppend(new ArrayList<int[]>())
                .setTargetsStates(new int[]{0, 1, 2, 3});

        if (levelNumber >= 50){
            LevelLoader2.loadLevel(levelNumber, levelBuilder);
            return;
        }

        // INÍCIO
        // ---------- LEVEL1
        int l = 1;
        if (levelNumber == l) {
            levelBuilder
                    .setTargetsMap(
                    new int[][]{
                            //{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
                        {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                    });
        }

        // ---------- LEVEL2
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            //{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0}
                    });
        }


        // ---------- LEVEL3
        l += 1;
        if (levelNumber == l) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                         //   {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
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
                                    {1, 1, 2, 1, 3, 0, 3, 1, 2, 1, 1},
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
                                    {0, 4, 0, 1, 1, 0, 1, 1, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                    {3, 2, 1, 1, 0, 0, 0, 1, 1, 2, 3},
                                    {2, 2, 0, 1, 2, 0, 4, 1, 0, 2, 2},
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
                                        {0, 3, 0, 1, 0, 0, 0, 1, 0, 4, 0},
                                        {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
                                        {1, 3, 0, 1, 0, 0, 0, 1, 0, 3, 1},
                                        {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                                        {1, 3, 2, 1, 0, 0, 0, 1, 2, 3, 1},
                                        {3, 2, 2, 1, 0, 0, 0, 1, 2, 2, 3},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 4, 0, 3, 0, 0, 0, 4, 0, 3, 0}
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
                                    {1, 4, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                                    {1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
                                    {0, 0, 2, 0, 0, 0, 0, 0, 2, 1, 1},
                                    {1, 2, 2, 0, 0, 0, 0, 0, 1, 4, 1},
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

        Level.levelObject = levelBuilder.build();
    }
}
