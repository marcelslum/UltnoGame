package ultno.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 02/08/2016.
 */
public class LevelLoader {
    private static LevelLoader ourInstance = new LevelLoader();

    public static LevelLoader getInstance() {
        return ourInstance;
    }

    private LevelLoader() {
    }

    public static void loadLevel(Game game, int levelNumber){

        final float gameAreaResolutionX = game.gameAreaResolutionX;
        final float gameAreaResolutionY = game.gameAreaResolutionY;
        final Game innerGame = game;
    
    switch (levelNumber){
            case 1:
                Level l = new Level(levelNumber, game);
                game.levelObject = l;
                l.ballsQuantity = 1;
                l.minBallsNotInvencibleAlive = 1;
                l.ballsRadiusByResolution = new float[]{0.010f, 0.010f};
                l.ballsInitialXByResolution = new float[]{0.3f, 0.5f};
                l.ballsInitialYByResolution = new float[]{0.5f, 0.5f};
                l.ballsDesiredVelocityXByResolution = new float[]{0.003f, 0.003f};
                l.ballsDesiredVelocityYByResolution = new float[]{0.00529412f, 0.00529412f};
                l.ballsColor = new Color[] {new Color(1f, 1f, 1f, 1f),new Color(1f, 1f, 1f, 1f)};
                l.ballsInvencible = new boolean[]{false, false};
                l.ballsAngleToRotate = new float[]{2f, 2f};
                l.ballsMaxAngle = new float[]{55f, 55f};
                l.ballsMinAngle = new float[]{35f, 35f};
                l.ballsVelocityVariation = new float[]{0.1f, 0.1f};
                l.ballsVelocityMaxByInitialVelocity = new float[]{1.5f, 1.5f};
                l.ballsVelocityMinByInitialVelocity = new float[]{0.8f, 0.8f};
                l.ballsTargetsAppend = new ArrayList<ArrayList<Target>>();
                l.ballsFree = new boolean[]{true, true};
                l.barsQuantity = 1;
                l.barsSizeXByResolution = new float[]{0.22f};//0.22f};//
                l.barsSizeYByResolution = new float[]{0.0175f};//0.0125f};//
                l.barsInitialXByResolution = new float[]{0.3f};//0.3f
                l.barsInitialYByResolution = new float[]{0.024f};//0.014f};
                l.barsDesiredVelocityXByResolution = new float[]{0.0045f};
                l.barsDesiredVelocityYByResolution = new float[]{0f};

                l.quantityTargetsX = 10;//10ocupa 11 espaços
                l.quantityTargetsY = 2;
                l.targetSizeXByResolution = 0.0895f;
                l.targetSizeYByResolution = 0.04f;
                l.targetsDistanceByXResolution = 0.001f;
                l.targetsPaddingByXResolution = 0.00225f;

                final float quantityTargetsY = l.quantityTargetsY;
                final float quantityTargetsX = l.quantityTargetsX;
                final float targetsDistanceByXResolution = l.targetsDistanceByXResolution;
                final float targetsPaddingByXResolution = l.targetsPaddingByXResolution;
                final float targetSizeXByResolution = l.targetSizeXByResolution;
                final float targetSizeYByResolution = l.targetSizeYByResolution;

                l.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {
                        for (int iY = 0; iY < quantityTargetsY;iY++){
                            for (int iX = 0; iX < quantityTargetsX; iX++) {
                                if (!(iY == 0 && iX == 0)){

                                    float xInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iX * ((gameAreaResolutionX * targetSizeXByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));
                                    float yInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iY * ((gameAreaResolutionY * targetSizeYByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));

                                    Target target = new Target("target", innerGame, xInitial, yInitial,
                                            gameAreaResolutionX * targetSizeXByResolution,
                                            gameAreaResolutionY * targetSizeYByResolution, 9
                                            );
                                    target.isMovable = false;
                                    target.alpha = 1;
                                    target.states = new int[]{0,1};
                                    target.currentState = 1;
                                    innerGame.addTarget(target);
                                }
                            }
                        }
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
                1, 1,                                               // quantidade de balls e minimo de balls vivas
                new float[]{0.010f},                                // raio da bola
                new float[]{0.3f}, new float[]{0.72f},              // posicao inicial
                new float[]{0.003f*2},new float[]{0.00529412f*2},   // velocidade
                new Color[] {new Color(1f, 1f, 1f, 1f)},            // cor
                new boolean[]{false},                               // invencível
                new float[]{5f},new float[]{65f}, new float[]{25f},// angulos de rotacao
                new float[]{0.25f},                                 // variação de velocidade na rotação
                new float[]{2.2f}, new float[]{0.7f},               // velocidade máxima e mínima
                new ArrayList<ArrayList<Target>>(),                 // targets apensados
                new boolean[]{true},                                // bola livre
                1,                                                  // quantidade de bars
                new float[]{0.26f}, new float[]{0.0175f},           // tamanho da barra
                new float[]{0.35f}, new float[]{0.014f},            // posicao da barra
                new float[]{0.005f}, new float[]{0f},               // velocidade da barra
                11, 2,                                              // quantidade de targets
                0.0895f, 0.04f,                                     // tamanho dos targets
                0.001f, 0.00225f                                     //distancia e padding dos targets
                );

                game.levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {
                    
                        int [][] map = {
                            {1,1,1,1,1,1,1,1,1,1,1},
                            {0,0,1,1,1,0,1,1,1,0,0}
                        };
                    
                        for (int iY = 0; iY < innerGame.levelObject.quantityTargetsY;iY++){
                            for (int iX = 0; iX < innerGame.levelObject.quantityTargetsX; iX++) {
                                if (map[iX][iY] == 1){

                                    float xInitial = (gameAreaResolutionX * innerGame.levelObject.targetsPaddingByXResolution) +
                                            (iX * ((gameAreaResolutionX * innerGame.levelObject.targetSizeXByResolution) +
                                            (gameAreaResolutionX * innerGame.levelObject.targetsDistanceByXResolution)));
                                    float yInitial = (gameAreaResolutionX * innerGame.levelObject.targetsPaddingByXResolution) +
                                            (iY * ((gameAreaResolutionY * innerGame.levelObject.targetSizeYByResolution) +
                                            (gameAreaResolutionX * innerGame.levelObject.targetsDistanceByXResolution)));

                                    Target target = new Target("target", innerGame, xInitial, yInitial,
                                            gameAreaResolutionX * innerGame.levelObject.targetSizeXByResolution,
                                            gameAreaResolutionY * innerGame.levelObject.targetSizeYByResolution, 9
                                            );
                                    target.isMovable = false;
                                    target.alpha = 1;
                                    target.states = new int[]{0,1};
                                    target.currentState = 1;
                                    innerGame.addTarget(target);
                                }
                            }
                        }
                    }

                    @Override
                    public void createObstacles() {

                    }

                    @Override
                    public void createWindows() {

                    }
                });
                break;
                
        }
    }
}