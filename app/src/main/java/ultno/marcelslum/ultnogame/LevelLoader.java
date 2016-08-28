package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {
    public static void loadLevel(Game game, int levelNumber) {
        Level.LevelBuilder levelBuilder = new Level.LevelBuilder();
        levelBuilder
                .game(game)
                .setBallsX_B1(0.3f)
                .setBallsY_B1(0.3f)
                .setBarsX_B1(0.3f)
                .setTargetsWidth(0.0895f)
                .setTargetsHeight(0.04f)
                .setTargetsDistance(0.001f)
                .setTargetsPadding(0.00225f);
        if (levelNumber == 1) {
            levelBuilder.setTargetsMap(
                    new int[][]{
                            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                    })
                    .setTargetsStates(new int[]{0, 1})
                    .setObstaclesQuantity(0);
        }

        if (levelNumber >= 2) {
            levelBuilder
                    .setBallsAngleToRotate_BD_2(2f)
                    .setBallsMaxAngle_BD_55(1.182f)
                    .setBallsMinAngle_BD_35(0.715f)
                    .setBallsVelocityVariation_BD_0_1(2f)
                    .setBallsVelocityMax_BD_1_5(1.467f)
                    .setBallsVelocityMin_BD_0_8(0.875f)
                    .setBarsWidth_BD_0_22(1.364f)
                    .setBarsX_B1(0.35f)
                    .setBarsVX_BD_0_0045(1.222f);
            if (levelNumber == 2) {
                levelBuilder.setTargetsMap(
                        new int[][]{
                                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0}
                        })
                        .setTargetsStates(new int[]{0, 1})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 3) {
            levelBuilder
                    .setBallsX_B1(0.2f)
                    .setBallsY_B1(0.4f)
                    .setBallsVX(1.071f)
                    .setBallsVY(1.071f)
                    .setBallsAngleToRotate_BD_2(1f)
                    .setBallsMaxAngle_BD_55(1f)
                    .setBallsMinAngle_BD_35(1f)
                    .setBallsVelocityVariation_BD_0_1(1f)
                    .setBallsVelocityMax_BD_1_5(1f)
                    .setBallsVelocityMin_BD_0_8(1f)
                    .setBarsWidth_BD_0_22(0.95f)
                    .setBarsX_B1(0.3f)
                    .setBarsVX_BD_0_0045(1.111f)
                    .setTargetsWidth(0.0891f)
                    .setTargetsDistance(0.0014f);
            if (levelNumber == 3) {
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
                                {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0}
                        })
                        .setTargetsStates(new int[]{0, 1})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 4) {
            levelBuilder
                    .setBallsX_B1(0.1f)
                    .setBallsY_B1(0.6f)
                    .setBallsAngleToRotate_BD_2(1.1f)
                    .setBarsWidth_BD_0_22(0.818f)
                    .setBarsVX_BD_0_0045(1.111f)
                    .setTargetsWidth(0.075176f);
            if (levelNumber == 4) {
                levelBuilder.setTargetsMap(
                        new int[][]{
                                {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
                                {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                                {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0}
                        })
                        .setTargetsStates(new int[]{0, 1})
                        .setObstaclesQuantity(1)
                        .setObstaclesX(0.4f)
                        .setObstaclesY(0.4f)
                        .setObstaclesWidth(0.2f)
                        .setObstaclesHeight(0.04f);
            }
        }


        if (levelNumber >= 5) {
            if (levelNumber == 5) {
                levelBuilder
                        .setBallsX_B1(0.2f)
                        .setBallsY_B1(0.4f)
                        .setBallsVX(1.143f)
                        .setBallsVY(1.143f)
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
                                })
                .setTargetsStates(new int[]{0, 1})
                .setTargetsWidth(0.0891f)
                .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 6) {
                levelBuilder
                        .setBallsMaxAngle_BD_55(1.0036f)
                        .setBallsMinAngle_BD_35(0.943f)
                        .setBallsVelocityVariation_BD_0_1(1.1f)
                        .setBallsVelocityMax_BD_1_5(1.6f)
                        .setBallsVelocityMin_BD_0_8(0.938f)
                        .setBarsWidth_BD_0_22(0.773f);
            if (levelNumber == 6) {
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
                        .setTargetsStates(new int[]{0, 1})
                        .setObstaclesQuantity(1)
                        .setObstaclesX(0.46f)
                        .setObstaclesY(0.2f)
                        .setObstaclesWidth(0.08f)
                        .setObstaclesHeight(0.6f);
            }
        }

        if (levelNumber >= 7) {
            if (levelNumber == 7) {
                levelBuilder
                        .setTargetsMap(
                                new int[][]{
                                        {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                        {3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3}
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 8) {
                levelBuilder
                        .setBallsVX(1.214f)
                        .setBallsVY(1.214f)
                        .setBallsMaxAngle_BD_55(58f)
                        .setBallsMinAngle_BD_35(32f);
            if (levelNumber == 8) {
                levelBuilder
                        .setTargetsMap(
                                new int[][]{
                                        {1, 1, 2, 1, 3, 2, 3, 1, 1, 1, 1},
                                        {2, 2, 1, 1, 1, 3, 1, 1, 2, 2, 2},
                                        {1, 1, 2, 1, 3, 2, 3, 1, 1, 1, 1}
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(2)
                        .setObstaclesX(0.31f, 0.64f)
                        .setObstaclesY(0.2f, 0.2f)
                        .setObstaclesWidth(0.02f, 0.02f)
                        .setObstaclesHeight(0.3f, 0.3f);
            }
        }

        if (levelNumber >= 9) {
            if (levelNumber == 9) {
                levelBuilder
                        .setTargetsMap(
                                new int[][]{
                                        {1, 1, 2, 4, 3, 0, 3, 1, 2, 1, 1},
                                        {2, 2, 1, 1, 1, 0, 1, 4, 1, 2, 2},
                                        {1, 1, 2, 1, 3, 0, 3, 1, 2, 1, 1},
                                        {1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1}
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 10) {
            if (levelNumber == 10) {
                float obstaclesPosX = 0.105f;
                float[] opa = new float[4];
                for (int i = 0; i < opa.length; i++) {
                    opa[i] = obstaclesPosX + ((float) i * 0.185f);
                }
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
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(5)
                        .setObstaclesX(opa[0], opa[0], opa[1], opa[1], opa[2], opa[2], opa[3], opa[3])
                        .setObstaclesY(0.12f, 0.29f, 0.12f, 0.29f, 0.12f, 0.29f, 0.12f, 0.29f)
                        .setObstaclesWidth(0.05f, 0.06f, 0.05f, 0.06f, 0.05f, 0.06f, 0.05f, 0.06f)
                        .setObstaclesHeight(0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 0.2f);
            }
        }

        if (levelNumber >= 11) {
                levelBuilder.setBarsVX_BD_0_0045(1.133f);
            if (levelNumber == 11) {
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
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 12) {
            levelBuilder.setBallsY_B1(1.156f);
            if (levelNumber == 12) {
                levelBuilder
                        .setTargetsMap(
                                new int[][]{
                                        {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                                        {0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0},
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 13) {
            if (levelNumber == 13) {
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
                                        {7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                                        {0, 7, 0, 0, 0, 7, 0, 0, 0, 7, 0},
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        if (levelNumber >= 14) {
            if (levelNumber == 14) {
                levelBuilder
                        .setTargetsMap(
                                new int[][]{
                                 // TODO
                                })
                        .setTargetsStates(new int[]{0, 1, 2, 3})
                        .setObstaclesQuantity(0);
            }
        }

        game.levelObject = levelBuilder.build();
    }
}
/*

    ballsInitialX(new float[] {
        0.15f
    }).ballsInitialY(new float[]{0.6f}).ballsDesiredVelocityX(new float[]{0.0036f}).ballsDesiredVelocityY(new float[]{0.00564705882352941f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{55f}).ballsMinAngle(new float[]{35f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.5f}).ballsVelocityMinByInitialVelocity(new float[]{0.8f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.18f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.075176f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();
        game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
                                    {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
                                    {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0}
                            }
                    );
                }

                @Override
                public void createObstacles() {
                    innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                            gX*0.4f, gY*0.4f,
                            gX*0.2f, gY*0.04f
                            ));
                }

                @Override
                public void createWindows() {

                }
            });
            break;

        case 5:
            game.levelObject = new LevelBuilder().number(5).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.010f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0032f}).ballsDesiredVelocityY(new float[]{0.0056470588f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{55f}).ballsMinAngle(new float[]{35f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.5f}).ballsVelocityMinByInitialVelocity(new float[]{0.8f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.18f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {

                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,1,1,1,0,1,0,1,1,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {1,1,1,1,1,1,1,1,1,1,1}
                            }
                    );
                }

                @Override
                public void createObstacles() {
                }

                @Override
                public void createWindows() {

                }
            });
            break;

        case 6:
            game.levelObject = new LevelBuilder().number(6).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.010f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0034f}).ballsDesiredVelocityY(new float[]{0.006f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{57f}).ballsMinAngle(new float[]{33f}).ballsVelocityVariation(new float[]{0.11f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,0,0,0,0,0,0,0,1,1,1},
                                    {1,0,0,0,0,0,0,0,0,1,1},
                                    {1,0,0,0,0,0,0,0,0,0,1},
                                    {0,0,0,0,0,0,0,0,0,1,1},
                                    {0,1,0,0,0,0,0,0,1,1,1},
                                    {0,1,0,0,0,0,0,1,1,1,1},
                                    {0,0,0,0,0,0,1,0,1,0,1},
                                    {1,0,0,0,0,0,1,1,1,1,1},
                                    {1,0,0,0,0,0,1,1,1,1,1}
                            }
                    );
                }
                @Override
                public void createObstacles() {
                    innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                            1f + innerGame.gameAreaResolutionX*0.46f,
                            innerGame.gameAreaResolutionY*0.2f,
                            innerGame.gameAreaResolutionX*0.08f,
                            innerGame.gameAreaResolutionY*0.05f
                    ));
                }

                @Override
                public void createWindows() {

                }
            });
            break;

        case 7:
            game.levelObject = new LevelBuilder().number(7).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.010f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0034f}).ballsDesiredVelocityY(new float[]{0.006f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{57f}).ballsMinAngle(new float[]{33f}).ballsVelocityVariation(new float[]{0.11f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,3,3,3,3,3,3,3,3,3,3},
                                    {3,3,0,0,0,0,0,0,0,3,3}
                            }
                    );
                }
                @Override
                public void createObstacles(){}

                @Override
                public void createWindows() {}
            });
            break;

        case 8:
            game.levelObject = new LevelBuilder().number(8).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.010f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {1,1,2,1,3,2,3,1,1,1,1},
                                    {2,2,1,1,1,3,1,1,2,2,2},
                                    {1,1,2,1,3,2,3,1,1,1,1}
                            }
                    );
                }
                @Override
                public void createObstacles(){
                    innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                            1f + innerGame.gameAreaResolutionX*0.31f,
                            innerGame.gameAreaResolutionY*0.2f,
                            innerGame.gameAreaResolutionX*0.02f,
                            innerGame.gameAreaResolutionY*0.3f
                    ));

                    innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                            1f + innerGame.gameAreaResolutionX*0.64f,
                            innerGame.gameAreaResolutionY*0.2f,
                            innerGame.gameAreaResolutionX*0.02f,
                            innerGame.gameAreaResolutionY*0.3f
                    ));
                }

                @Override
                public void createWindows() {}
            });
            break;

        case 9:
            game.levelObject = new LevelBuilder().number(9).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.010f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1, 2, 3},
                            new int [][]{
                                    {1,1,2,4,3,0,3,1,2,1,1},
                                    {2,2,1,1,1,0,1,4,1,2,2},
                                    {1,1,2,1,3,0,3,1,2,1,1},
                                    {1,1,1,1,1,4,1,1,1,1,1}
                            }
                    );
                }
                @Override
                public void createObstacles(){
                }

                @Override
                public void createWindows() {}
            });
            break;

        case 10:
            game.levelObject = new LevelBuilder().number(10).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.01f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1, 2, 3},
                            new int [][]{
                                    {0,0,0,1,1,0,1,1,0,0,0},
                                    {0,0,0,1,1,0,1,1,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {3,2,1,1,0,0,0,1,1,2,3},
                                    {2,2,0,1,4,0,4,1,0,2,2},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {1,1,0,1,1,0,1,1,0,1,1}
                            }
                    );
                }
                @Override
                public void createObstacles(){

                    float posX = 0.1f;

                    for (int i = 0; i < 5; i++) {
                        innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                                1f + innerGame.gameAreaResolutionX * posX + innerGame.gameAreaResolutionX*0.005f,
                                innerGame.gameAreaResolutionY * 0.12f,
                                innerGame.gameAreaResolutionX * 0.05f,
                                innerGame.gameAreaResolutionY * 0.2f
                        ));
                        innerGame.addObstacle(new Obstacle("obstacle", innerGame,
                                1f + innerGame.gameAreaResolutionX * posX + innerGame.gameAreaResolutionX*0.005f,
                                innerGame.gameAreaResolutionY * 0.29f,
                                innerGame.gameAreaResolutionX * 0.06f,
                                innerGame.gameAreaResolutionY * 0.2f
                        ));
                        posX += 0.185f;
                    }
                }

                @Override
                public void createWindows() {}
            });
            break;

        case 11:
            game.levelObject = new LevelBuilder().number(11).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.01f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.0051f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1, 2, 3},
                            new int [][]{
                                    {0,3,0,1,0,0,0,1,0,3,0},
                                    {1,1,0,1,0,0,0,1,0,1,1},
                                    {1,3,0,1,0,0,0,1,0,3,1},
                                    {1,1,0,0,0,0,0,0,0,1,1},
                                    {1,3,2,1,0,0,0,1,2,3,1},
                                    {3,2,2,1,0,0,0,1,2,2,3},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,4,0,4,0,0,0,4,0,4,0}
                            }
                    );
                }
                @Override
                public void createObstacles(){
                }

                @Override
                public void createWindows() {}
            });
            break;

        case 12:
            game.levelObject = new LevelBuilder().number(12).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.01f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.0051f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1, 2, 3},
                            new int [][]{
                                    {0,1,0,1,0,1,0,1,0,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {1,0,1,0,0,0,0,0,1,0,1},
                                    {0,1,0,1,0,0,0,1,0,1,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,4,0,0,0,0},
                            }
                    );
                }
                @Override
                public void createObstacles(){
                }

                @Override
                public void createWindows() {}
            });
            break;

        case 13:
            game.levelObject = new LevelBuilder().number(12).game(game).ballsQuantity(1).minBallsNotInvencibleAlive(1).ballsRadius(new float[]{0.01f}).ballsInitialX(new float[]{0.2f}).ballsInitialY(new float[]{0.4f}).ballsDesiredVelocityX(new float[]{0.0035f}).ballsDesiredVelocityY(new float[]{0.0061764706f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{58f}).ballsMinAngle(new float[]{32f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.6f}).ballsVelocityMinByInitialVelocity(new float[]{0.75f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.17f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.0052f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.0891f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1, 2, 3},
                            new int [][]{
                                    {1,1,1,1,1,1,1,1,1,1,1},
                                    {2,0,2,0,2,0,2,0,2,0,2},
                                    {1,0,1,0,1,0,1,0,1,0,1},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,7,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,7,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0},
                                    {7,0,0,0,0,0,0,0,0,0,7},
                                    {0,7,0,0,0,7,0,0,0,7,0},
                            }
                    );
                }
                @Override
                public void createObstacles(){}

                @Override
                public void createWindows() {}
            });
            break;
        }
    }


    public static void  createTarget(int iX, int iY, int type, int[] states){
        if (type == 0){
            return;
        }

    }

    public static void createLevelTargets(int [] states, int [][] map){


    }

    */

