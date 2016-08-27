package ultno.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {

    private static int state;
    private static int special;
    private static boolean isGhost;
    private static float gX;
    private static float gY;
    private static float targetX;
    private static float targetY;
    private static Game g;
    private static float targetWidth;
    private static float targetHeight;

    private static LevelLoader ourInstance = new LevelLoader();

    public static LevelLoader getInstance() {
        return ourInstance;
    }

    private LevelLoader() {
    }

    public static void loadLevel(Game game, int levelNumber){

        gX = game.gameAreaResolutionX;
        gY = game.gameAreaResolutionY;
        g = game;
        final Game innerGame = game;

    switch (levelNumber){
            case 1:
                game.levelObject = new Level(2, game,
                        1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                        new float[]{0.010f},                                // raio da bola
                        new float[]{0.3f}, new float[]{0.3f},              // posicao inicial
                        new float[]{0.003f},new float[]{0.00529412f},   // velocidade
                        new int[] {Ball.COLOR_BALL_BLACK},            // cor
                        new boolean[]{false},                               // invencível
                        new float[]{2f},new float[]{55f}, new float[]{35f},// angulos de rotacao
                        new float[]{0.1f},                                 // variação de velocidade na rotação
                        new float[]{1.5f}, new float[]{0.8f},               // velocidade máxima e mínima
                        new ArrayList<ArrayList<Target>>(),                 // targets apensados
                        new boolean[]{true},                                // bola livre
                        1,                                                  // quantidade de bars
                        new float[]{0.22f}, new float[]{0.0175f},           // tamanho da barra
                        new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                        new float[]{0.0045f}, new float[]{0f},               // velocidade da barra
                        0.0895f, 0.04f,                                     // tamanho dos targets
                        0.001f, 0.00225f                                     //distancia e padding dos targets
                );


                game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {
                        createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,1,1,1,1,1,1,1,1,1,0},
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
                
            case 2:
                game.levelObject = new Level(2, game, 
                1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                new float[]{0.010f},                                // raio da bola
                new float[]{0.3f}, new float[]{0.72f},              // posicao inicial
                new float[]{0.003f},new float[]{0.00529412f},   // velocidade
                new int[] {Ball.COLOR_BALL_BLACK},            // cor
                new boolean[]{false},                               // invencível
                new float[]{5f},new float[]{65f}, new float[]{25f},// angulos de rotacao
                new float[]{0.25f},                                 // variação de velocidade na rotação
                new float[]{2.2f}, new float[]{0.7f},               // velocidade máxima e mínima
                new ArrayList<ArrayList<Target>>(),                 // targets apensados
                new boolean[]{true},                                // bola livre
                1,                                                  // quantidade de bars
                new float[]{0.26f}, new float[]{0.0175f},           // tamanho da barra
                new float[]{0.35f}, new float[]{0.024f},            // posicao da barra
                new float[]{0.005f*1.5f}, new float[]{0f},               // velocidade da barra
                0.0895f, 0.04f,                                     // tamanho dos targets
                0.001f, 0.00225f                                     //distancia e padding dos targets
                );

                game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {
                        createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                {1,1,1,1,1,1,1,1,1,1,1},
                                {0,0,1,1,1,0,1,1,1,0,0}
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

        case 3:
            game.levelObject = new Level(2, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0036f},new float[]{0.00635294117647059f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2f},new float[]{55f}, new float[]{35f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.5f}, new float[]{0.8f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.18f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

            game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                @Override
                public void createTargets() {
                    createLevelTargets(new int[]{0, 1},
                            new int [][]{
                                    {0,1,0,0,0,1,0,1,0,0,0},
                                    {0,0,1,1,0,0,0,0,0,0,0},
                                    {0,0,1,1,1,0,0,0,0,0,0},
                                    {0,0,0,0,1,1,0,0,0,0,0},
                                    {1,0,1,0,1,0,0,0,0,0,0},
                                    {1,0,1,1,0,0,0,0,0,0,0},
                                    {1,0,1,0,0,0,1,0,0,0,0},
                                    {1,1,0,1,0,1,0,0,0,0,0},
                                    {0,0,0,1,0,0,1,1,0,0,0},
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

        case 4:
            game.levelObject = new Level(4, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.15f}, new float[]{0.6f},              // posicao inicial
                    new float[]{0.0036f},new float[]{0.00564705882352941f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{55f}, new float[]{35f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.5f}, new float[]{0.8f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.18f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.075176f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(5, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0032f},new float[]{0.0056470588f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{55f}, new float[]{35f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.5f}, new float[]{0.8f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.18f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(6, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0034f},new float[]{0.006f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{57f}, new float[]{33f},// angulos de rotacao
                    new float[]{0.11f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(7, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0034f},new float[]{0.006f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{57f}, new float[]{33f},// angulos de rotacao
                    new float[]{0.11f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(8, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(9, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.010f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(10, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.01f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(11, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.01f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.0051f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(12, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.01f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.0051f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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
            game.levelObject = new Level(12, game,
                    1, 1,                                               // quantidade de bolas e minimo de bolas vivas
                    new float[]{0.01f},                                // raio da bola
                    new float[]{0.2f}, new float[]{0.4f},              // posicao inicial
                    new float[]{0.0035f},new float[]{0.0061764706f},   // velocidade
                    new int[] {Ball.COLOR_BALL_BLACK},            // cor
                    new boolean[]{false},                               // invencível
                    new float[]{2.2f},new float[]{58f}, new float[]{32f},// angulos de rotacao
                    new float[]{0.1f},                                 // variação de velocidade na rotação
                    new float[]{1.6f}, new float[]{0.75f},               // velocidade máxima e mínima
                    new ArrayList<ArrayList<Target>>(),                 // targets apensados
                    new boolean[]{true},                                // bola livre
                    1,                                                  // quantidade de bars
                    new float[]{0.17f}, new float[]{0.0175f},           // tamanho da barra
                    new float[]{0.3f}, new float[]{0.024f},            // posicao da barra
                    new float[]{0.0052f}, new float[]{0f},               // velocidade da barra
                    0.0891f, 0.04f,                                     // tamanho dos targets
                    0.0014f, 0.00225f                                     //distancia e padding dos targets
            );

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

    public static void setTargetData(int iX, int iY, int type){
        setTargetPosition(iX, iY);
        setTargetSize();
        setTargetType(type);
    }

    public static void setTargetPosition(int iX, int iY){
        targetX = (gX * g.levelObject.targetsPaddingByXResolution) +
                (iX * ((gX * g.levelObject.targetSizeXByResolution) +
                        (gX * g.levelObject.targetsDistanceByXResolution)));
        targetY = (gX * g.levelObject.targetsPaddingByXResolution) +
                (iY * ((gY * g.levelObject.targetSizeYByResolution) +
                        (gX * g.levelObject.targetsDistanceByXResolution)));
    }

    public static void setTargetSize(){
        targetWidth = gX * g.levelObject.targetSizeXByResolution;
        targetHeight = gY * g.levelObject.targetSizeYByResolution;
    }

    public static void setTargetType(int type){
        special = 0;
        isGhost = false;
        state = 0;
        switch (type) {
            case 1:
                state = 1;
                break;
            case 2:
                state = 2;
                break;
            case 3:
                state = 3;
                break;
            case 4:
                special = 1;
                break;
            case 5:
                state = 1;
                isGhost = true;
                break;
            case 6:
                state = 2;
                isGhost = true;
                break;
            case 7:
                state = 3;
                isGhost = true;
                break;
        }
    }

    public static void  createTarget(int iX, int iY, int type, int[] states){
        if (type == 0){
            return;
        }
        setTargetData(iX, iY, type);
        g.addTarget(new Target("target", g,
                targetX, targetY, // posicao x e y
                targetWidth, // width
                targetHeight, 9, // height
                states, // states
                state,// currentState
                special, // special
                isGhost
            )
        );
    }

    public static void createLevelTargets(int [] states, int [][] map){
        for (int iY = 0; iY < map.length;iY++){
            for (int iX = 0; iX < map[iY].length; iX++) {
                if (map[iY][iX] == 1){
                    createTarget(iX, iY, map[iY][iX], states);
                }
            }
        }
    }

}
