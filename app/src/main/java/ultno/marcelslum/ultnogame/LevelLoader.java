package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {
    public static void loadLevel(Game game, int levelNumber) {
        Level.LevelBuilder levelBuilder;
        levelBuilder = new Level.LevelBuilder()
                .game(game)
                .setBallsQuantity(1)
                .setBallsAlive(1)
                .setBallsRadius_BD_0_01(1f)
                .setBallsX_B1(0.3f)
                .setBallsY_B1(0.3f)
                .setBallsVX_BD_0_003(1f)
                .setBallsVY_BD_0_005294(1f)
                .setBallsTextureMap(Ball.COLOR_BALL_BLACK)
                .setBallsInvencible(false)
                .setBallsAngleToRotate_BD_2_2(0.90f)
                .setBallsMaxAngle_BD_58(0.95f)
                .setBallsMinAngle_BD_32(1.09f)
                .setBallsVelocityVariation_BD_0_11(0.91f)
                .setBallsVelocityMax_BD_1_6(0.9375f)
                .setBallsVelocityMin_BD_0_75(1.0667f)
                .setBallsFree(true)
                .setBarsQuantity(1)
                .setBarsWidth_BD_0_22(1f)
                .setBarsHeight_BD_0_0175(1f)
                .setBarsX_B1(0.3f)
                .setBarsY_BD_0_024(1f)
                .setBarsVX_BD_0_0045(1f)
                .setBarsVY(new float[]{0f})
                .setTargetsWidth(0.0895f)
                .setTargetsHeight(0.04f)
                .setTargetsDistance(0.001f)
                .setTargetsPadding(0.00225f)
                .setTargetsMap(
                        new int[][]{
                                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                        })
                .setTargetsStates(new int[]{0, 1})
                .setObstaclesQuantity(0);


        if (levelNumber >= 2) {
            levelBuilder = new Level.LevelBuilder()
                    .setBallsAngleToRotate_BD_2_2(2.27f)
                    .setBallsMaxAngle_BD_58(1.12f)
                    .setBallsMinAngle_BD_32(0.78125f)
                    .setBallsVelocityVariation_BD_0_11(2.27f)
                    .setBallsVelocityMax_BD_1_6(1.375f)
                    .setBallsVelocityMin_BD_0_75(0.95f)
                    .setBarsWidth_BD_0_22(1.08f)
                    .setBarsX_B1(0.35f)
                    .setBarsVX_BD_0_0045(1f)
                    .setTargetsMap(
                            new int[][]{
                                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                    {0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0}
                            })
                    .setTargetsStates(new int[]{0, 1})
                    .setObstaclesQuantity(0);
        }

        if (levelNumber >= 3) {
            levelBuilder = new Level.LevelBuilder()
                    .setBallsVX_BD_0_003(1.05f)
                    .setBallsVY_BD_0_005294(1.05f)
                    .setBallsAngleToRotate_BD_2_2(0.9f)
                    .setBallsMaxAngle_BD_58(0.9f)
                    .setBallsMinAngle_BD_32(1.1f)
                    .setBallsVelocityVariation_BD_0_11(0.95f)
                    .setBallsVelocityMax_BD_1_6(0.95f)
                    .setBallsVelocityMin_BD_0_75(1.05f)
                    .setBarsWidth_BD_0_22(0.95f)
                    .setBarsX_B1(0.35f)
                    .setBarsVX_BD_0_0045(1.05f)
                    .setTargetsWidth(0.0891f)
                    .setTargetsDistance(0.0014f)
                    .setTargetsMap(
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

        if (levelNumber >= 4) {
            levelBuilder = new Level.LevelBuilder()
                    .setBallsVX_BD_0_003(1.05f)
                    .setBallsVY_BD_0_005294(1.05f)
                    .setBallsAngleToRotate_BD_2_2(0.9f)
                    .setBallsMaxAngle_BD_58(0.9f)
                    .setBallsMinAngle_BD_32(1.1f)
                    .setBallsVelocityVariation_BD_0_11(0.95f)
                    .setBallsVelocityMax_BD_1_6(0.95f)
                    .setBallsVelocityMin_BD_0_75(1.05f)
                    .setBarsWidth_BD_0_22(0.95f)
                    .setBarsX_B1(0.35f)
                    .setBarsVX_BD_0_0045(1.05f)
                    .setTargetsWidth(0.0891f)
                    .setTargetsDistance(0.0014f)
                    .setTargetsMap(
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
        game.levelObject = levelBuilder.build();
    }
}

/*
ballsInitialX(new float[]{0.15f}).ballsInitialY(new float[]{0.6f}).ballsDesiredVelocityX(new float[]{0.0036f}).ballsDesiredVelocityY(new float[]{0.00564705882352941f}).ballsTextureMap(new int[]{Ball.COLOR_BALL_BLACK}).ballsIsInvencible(new boolean[]{false}).ballsAngleToRotate(new float[]{2.2f}).ballsMaxAngle(new float[]{55f}).ballsMinAngle(new float[]{35f}).ballsVelocityVariation(new float[]{0.1f}).ballsVelocityMaxByInitialVelocity(new float[]{1.5f}).ballsVelocityMinByInitialVelocity(new float[]{0.8f}).ballsTargetsAppend(new ArrayList<ArrayList<Target>>()).ballsFree(new boolean[]{true}).barsQuantity(1).barsSizeX(new float[]{0.18f}).barsSizeY(new float[]{0.0175f}).barsInitialX(new float[]{0.3f}).barsInitialY(new float[]{0.024f}).barsDesiredVelocityX(new float[]{0.005f}).barsDesiredVelocityY(new float[]{0f}).targetWidth(0.075176f).targetHeight(0.04f).setTargetsDistanceByXResolution(0.0014f).setTargetsPaddingByXResolution(0.00225f).createLevel();

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

