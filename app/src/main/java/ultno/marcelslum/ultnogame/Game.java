package ultno.marcelslum.ultnogame;

import android.media.SoundPool;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Game {

    public static ArrayList<Target> targets;
    public static ArrayList<Ball> balls;
    public static ArrayList<Text> texts;
    public static ArrayList<TouchEvent> touchEvents;
    public static Quadtree quad;
    SoundPool soundPool;
    int soundBallHit;
    int soundCounter;
    int soundDestroyTarget;
    int soundMusic;
    int soundScore;

    Font font;

    public static ScorePanel scorePanel;


    private static Game ourInstance = new Game();
    public float gameAreaResolutionX;
    public float gameAreaResolutionY;

    public float resolutionX;
    public float resolutionY;


    public Rectangle bordaC;
    public Rectangle bordaE;
    public Rectangle bordaD;
    public Rectangle bordaB;

    public Button button1Left;
    public Button button1Right;
    public Button button2Left;
    public Button button2Right;
    public ButtonOnOff soundButton;
    public ButtonOnOff musicButton;

    boolean soundOn;
    boolean musicOn;
    boolean isBlocked;

    Level levelObject;
    int levelNumber = 1;



    public ArrayList<InteractionListener> interactionListeners;
    public int volume;
    public String gameState;
    public Audio music;
    public Entity gameArea;
    public ArrayList<Bar> bars;
    public ArrayList<Menu> menus;
    public ArrayList<Selector> selectors;
    public float [] barsInitialPositionX = new float[10];
    public float [] barsInitialPositionY = new float[10];
    public float [] barsDesiredVelocityX = new float[10];
    public float [] barsDesiredVelocityY = new float[10];
    public float[] ballsInitialPositionX = new float[10];
    public float[] ballsInitialPositionY = new float[10];
    public float[] ballsDesiredVelocityX = new float[10];
    public float[] ballsDesiredVelocityY = new float[10];

    SatPolygon polygon1;
    SatPolygon polygon2;
    SatCircle circle1;
    SatCircle circle2;
    public boolean ballFall;
    public Program imageProgram;
    public Program textProgram;
    public Program solidProgram;
    public Background background;
    public ObjectivePanel objectivePanel;


    public static Game getInstance() {

        if (targets == null){

        }

        return ourInstance;
    }

    private Game() {
        targets = new ArrayList<Target>();
        balls = new ArrayList<Ball>();
        touchEvents = new ArrayList<TouchEvent>();
        texts = new ArrayList<Text>();
        bars = new ArrayList<Bar>();
        menus = new ArrayList<>();
        selectors = new ArrayList<>();

        barsInitialPositionX = new float[10];
        barsInitialPositionY = new float[10];
        barsDesiredVelocityX = new float[10];
        barsDesiredVelocityY = new float[10];

        ArrayList<Vector> points = new ArrayList<Vector>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        this.polygon1 = new SatPolygon(new Vector(0, 0), points);

        ArrayList<Vector> points2 = new ArrayList<Vector>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        this.polygon2 = new SatPolygon(new Vector(0, 0), points2);

        this.circle1 = new SatCircle(new Vector(0,0),0);
        this.circle2 = new SatCircle(new Vector(0,0),0);

   }

    public void addTarget(Target target){

        Log.e("game", "adiciona target");
        this.targets.add(target);
    }

    public void addBall(Ball ball){
        this.balls.add(ball);
    }

    public void addText(Text text){
        this.texts.add(text);
    }

    public void addBar(Bar bar){
        this.bars.add(bar);
    }

    public void blockAndWaitTouchRelease(){
    }

    public void setGameState(String state){
    }

    public void clearGameEntities() {
    }

    public void addInteracionListener(InteractionListener listener) {
        if (this.interactionListeners == null){
            this.interactionListeners = new ArrayList<InteractionListener>();
        }

        for (int i = 0; i < this.interactionListeners.size(); i++){
            if (this.interactionListeners.get(i).name == listener.name){
                this.interactionListeners.set(i, listener);
                return;
            }
        }

        this.interactionListeners.add(listener);
    }

    public boolean checkCollision(ArrayList<? extends Entity> aEntities, boolean respondToCollision, boolean updateLastCollisionResponse){

        boolean collided = false;
        boolean isHadCollision = false;
        ArrayList<Entity> out;
        SatResponse response = new SatResponse();

        for (int iLoop = 0; iLoop < 1; iLoop++){

            // entidades a
            for (int aCount = 0; aCount < aEntities.size(); aCount++){
                PhysicalObject a =  (PhysicalObject) aEntities.get(aCount);

                //Log.e("game ", "começando a verficiar  "+a.isMovable);


                if (a.isMovable) {
                    out = null;
                    collided = false;

                    out = this.quad.retrieve(a);

                    //Log.e("game ", "quad size "+out.size());


                    //String texto = " ";
                    //for (int i = 0; i < out.size(); i++){
                    //    texto = texto + out.get(i).name + " - ";
                    //}




                    //if (b.name == "bordaB"){
                    //    Log.e("posicao bola x Borda B", " "+ a.y + " - "+ b.y);
                    //}
                    //Log.e("quantity of outs", " "+ out.size());

                    // roda pelas entidades extraidas e verifica a colisão
                    for (int bCount = 0; bCount < out.size(); bCount++){
                        PhysicalObject b = (PhysicalObject)out.get(bCount);

                        if (b.name == "bordaB"){
                        //    Log.e("posicao bola x Borda B", " "+ a.y + " - "+ b.y);
                        }


                        //Log.e("game ", "testando "+b.name);

                        // verifica se a entidade não é a mesma e se elas são colidíveis
                        if ((a.isSolid && b.isSolid) && (a.isMovable || b.isMovable) && (b != a)){

                            // seta os dados da entidade 'a' e da entidade 'b'
                            a.setSatData();
                            b.setSatData();

                            // zera os dados a serem usados nesta passagem

                            // transfere os dados da entidade para o objeto do game


                            //type false: polygon true: circle
                            boolean aType = false;
                            boolean bType = false;

                            //Log.e("pos bola sat cc", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

                            if (a.circleData != null){
                                this.circle1.pos.x = a.circleData.pos.x;
                                this.circle1.pos.y = a.circleData.pos.y;
                                this.circle1.r = a.circleData.r;
                                aType = true;
                            } else {
                                this.polygon1.pos.x = a.polygonData.pos.x;
                                this.polygon1.pos.y = a.polygonData.pos.y;
                                this.polygon1.setPoints(a.polygonData.points);
                            }

                            if (b.circleData != null){
                                this.circle2.pos.x = b.circleData.pos.x;
                                this.circle2.pos.y = b.circleData.pos.y;
                                this.circle2.r = b.circleData.r;
                                bType = true;
                            } else {
                                this.polygon2.pos.x = b.polygonData.pos.x;
                                this.polygon2.pos.y = b.polygonData.pos.y;
                                this.polygon2.setPoints(b.polygonData.points);

                            }

                            float [] velocities = new float[4];

                            //Log.e("pos bola sat cc2", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

                            velocities[0] = Math.abs(a.vx);
                            velocities[1] = Math.abs(a.vy);
                            velocities[2] = Math.abs(b.vx);
                            velocities[3] = Math.abs(b.vy);

                            float max = -100000;
                            int maxIndex = -1;
                            for (int n = 0; n < 4; n++){
                                if (velocities[n] > max){
                                    maxIndex = n;
                                    max = velocities[n];
                                }
                            }

                            int quantityPassagens;

                            // defina quantas passagens serão realidades, com base na maior velocidade
                            quantityPassagens = Math.round(velocities[maxIndex]/2) ;
                            if (quantityPassagens == 0){
                                quantityPassagens = 1;
                            }

                            //Log.e("Game", " a.previousX "+a.previousX);
                            //Log.e("Game", " a.previousY "+a.previousY);
                            //Log.e("Game", " b.previousX "+b.previousX);
                            //Log.e("Game", " b.previousY "+b.previousY);

                            float aPreviousX = a.previousX;
                            float aPreviousY = a.previousY;
                            float bPreviousX = b.previousX;
                            float bPreviousY = b.previousY;

                            //Log.e("Game", " a.previousX 2"+aPreviousX);
                            //Log.e("Game", " a.previousY 2"+aPreviousY);
                            //Log.e("Game", " b.previousX 2"+bPreviousX);
                            //Log.e("Game", " b.previousY 2"+bPreviousY);

                            // Log.e("pos bola sat cc3", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);
                            
                            // calcula a diferença entre as posições
                            float aDiferencaPosicaoX = a.x - aPreviousX;
                            float aDiferencaPosicaoY = a.y - aPreviousY;

                            float bDiferencaPosicaoX = b.x - bPreviousX;
                            float bDiferencaPosicaoY = b.y - bPreviousY;

                            // calcula a porcentagem de cada passada;
                            float porcentagem = (100f/quantityPassagens)/100f;
                            //Log.e("Game", " quantityPassagens "+quantityPassagens+" porcentagem "+porcentagem);
                            float porcentagemAplicadaNaPassagem;
                            float aPosAConsiderarX = -1000f;
                            float aPosAConsiderarY = -1000f;
                            float bPosAConsiderarX = -1000f;
                            float bPosAConsiderarY = -1000f;

                            //Log.e("pos bola sat cc4", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

                            // itera pelas passagens, chegando se há colisão

                            for (int ip = 0; ip < quantityPassagens; ip++){

                                response.clear();
                                porcentagemAplicadaNaPassagem = porcentagem * (float)(ip+1);
                                //Log.e("Game", "passagem "+ip+" porcentagem "+porcentagem);

                                aPosAConsiderarX = aPreviousX + (aDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                aPosAConsiderarY = aPreviousY + (aDiferencaPosicaoY * porcentagemAplicadaNaPassagem);
                                //Log.e("Game", "passagem "+ip+" a.previousY "+ aPreviousY +" aDiferencaPosicaoX "+aDiferencaPosicaoX +  " porcentagemAplicadaNaPassagem "+porcentagemAplicadaNaPassagem);
                                //Log.e("Game", "passagem "+ip+" aPosAConsiderarX "+ aPosAConsiderarX +" aPosAConsiderarY "+aPosAConsiderarY);
                                //Log.e("Game", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);

                                bPosAConsiderarX = bPreviousX + (bDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                bPosAConsiderarY = bPreviousY + (bDiferencaPosicaoY * porcentagemAplicadaNaPassagem);

                                //Log.e("test posicoes", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);


                                if (aType == false){
                                    this.polygon1.pos.x = aPosAConsiderarX;
                                    this.polygon1.pos.y = aPosAConsiderarY;
                                } else {
                                    this.circle1.pos.x = aPosAConsiderarX;
                                    this.circle1.pos.y = aPosAConsiderarY;
                                }

                                if (bType == false){
                                    this.polygon2.pos.x = bPosAConsiderarX;
                                    this.polygon2.pos.y = bPosAConsiderarY;
                                } else {
                                    this.circle2.pos.x = bPosAConsiderarX;
                                    this.circle2.pos.y = bPosAConsiderarY;
                                }


                                //Log.e("pos bola sat cc5", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radium "+ this.balls.get(0).circleData.r);

                                if (aType == false){
                                    if (bType == false) {
                                        collided = Sat.getInstance().testPolygonPolygon(polygon1, polygon2, response);
                                    } else {
                                        collided = Sat.getInstance().testPolygonCircle(polygon1, circle2, response);
                                    }
                                } else {
                                    if (bType == false) {
                                        collided = Sat.getInstance().testCirclePolygon(circle1, polygon2, response);
                                    } else {
                                        collided = Sat.getInstance().testCircleCircle(circle1, circle2, response);
                                    }

                                }

                                // se houver registro de colisão, mas se a resposta foi zerada, retorna para uma próxima verificação
                                if (collided) {
                                    if (response.overlapV.x == 0 &&  response.overlapV.y == 0){
                                        continue;
                                    } else {
                                        break;
                                    }
                                }
                            }

                            if (collided && !(response.overlapV.x == 0 && response.overlapV.y == 0)){

                                isHadCollision = true;

                                if (respondToCollision){
                                    a.isCollided = true;
                                    b.isCollided = true;
                                    a.respondToCollision(b, -response.overlapV.x, -response.overlapV.y, aPosAConsiderarX, aPosAConsiderarY, bPosAConsiderarX, bPosAConsiderarY);
                                }

                                if (updateLastCollisionResponse){
                                    a.lastCollisionResponse.add(new Vector(-response.overlapV.x,-response.overlapV.y));

                                    boolean exists = false;
                                    for (int i = 0; i < a.lastCollisionObjects.size(); i++){
                                        if (a.lastCollisionObjects.get(i) == b){
                                            exists = true;
                                            break;
                                        }
                                    }

                                    if (!exists){
                                        //console.log("inserindo entidade ", b.name, " na entidade ", a.name);
                                        a.lastCollisionObjects.add(b);
                                    }

                                    b.lastCollisionResponse.add(new Vector(response.overlapV.x,response.overlapV.y));

                                    exists = false;
                                    for (int i = 0; i < b.lastCollisionObjects.size(); i++){
                                        if (b.lastCollisionObjects.get(i) == a){
                                            exists = true;
                                            break;
                                        }
                                    }

                                    if (!exists){
                                        b.lastCollisionObjects.add(a);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        return isHadCollision;
    }


    public void createEntities() {
        Log.e("game", "1");
        loadLevel(this.levelNumber);
        this.levelObject.loadEntities();
    }

    public void loadLevel(int levelNumber){
        switch (levelNumber){
            case 1:
                levelObject = new Level(levelNumber, this);
                levelObject.ballsQuantity = 1;
                levelObject.minBallsNotInvencibleAlive = 1;
                levelObject.ballsRadiusByResolution = new float[]{0.010f, 0.010f};
                levelObject.ballsInitialXByResolution = new float[]{0.3f, 0.5f};
                levelObject.ballsInitialYByResolution = new float[]{0.5f, 0.5f};
                levelObject.ballsDesiredVelocityXByResolution = new float[]{0.003f, 0.003f};
                levelObject.ballsDesiredVelocityYByResolution = new float[]{0.00529412f, 0.00529412f};
                levelObject.ballsColor = new Color[] {new Color(1f, 1f, 1f, 1f),new Color(1f, 1f, 1f, 1f)};
                levelObject.ballsInvencible = new boolean[]{false, false};
                levelObject.ballsAngleToRotate = new float[]{2f, 2f};
                levelObject.ballsMaxAngle = new float[]{55f, 55f};
                levelObject.ballsMinAngle = new float[]{35f, 35f};
                levelObject.ballsVelocityVariation = new float[]{0.1f, 0.1f};
                levelObject.ballsVelocityMaxByInitialVelocity = new float[]{1.5f, 1.5f};
                levelObject.ballsVelocityMinByInitialVelocity = new float[]{0.8f, 0.8f};
                levelObject.ballsTargetsAppend = (ArrayList<Target>[]) new ArrayList[10];
                levelObject.ballsFree = new boolean[]{true, true};
                levelObject.barsQuantity = 1;
                levelObject.barsSizeXByResolution = new float[]{0.22f};//0.22f};//
                levelObject.barsSizeYByResolution = new float[]{0.0175f};//0.0125f};//
                levelObject.barsInitialXByResolution = new float[]{0.3f};//0.3f
                levelObject.barsInitialYByResolution = new float[]{0.024f};//0.014f};
                levelObject.barsDesiredVelocityXByResolution = new float[]{0.0045f};
                levelObject.barsDesiredVelocityYByResolution = new float[]{0f};

                levelObject.quantityTargetsX = 10;//10ocupa 11 espaços
                levelObject.quantityTargetsY = 2;
                levelObject.targetSizeXByResolution = 0.0895f;
                levelObject.targetSizeYByResolution = 0.04f;
                levelObject.targetsDistanceByXResolution = 0.001f;
                levelObject.targetsPaddingByXResolution = 0.00225f;

                final float quantityTargetsY = levelObject.quantityTargetsY;
                final float quantityTargetsX = levelObject.quantityTargetsX;
                final float targetsDistanceByXResolution = levelObject.targetsDistanceByXResolution;
                final float targetsPaddingByXResolution = levelObject.targetsPaddingByXResolution;
                final float targetSizeXByResolution = levelObject.targetSizeXByResolution;
                final float targetSizeYByResolution = levelObject.targetSizeYByResolution;
                final float gameAreaResolutionX = this.gameAreaResolutionX;
                final float gameAreaResolutionY = this.gameAreaResolutionY;
                final Game game = this;

                Log.e("game", "2");
                levelObject.setEntitiesCreator(new Level.EntitiesCreator() {
                    @Override
                    public void createTargets() {

                        Log.e("game", "p4");
                        for (int iY = 0; iY < quantityTargetsY;iY++){
                            for (int iX = 0; iX < quantityTargetsX; iX++) {
                                if (!(iY == 0 && iX == 0)){

                                    float xInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iX * ((gameAreaResolutionX * targetSizeXByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));
                                    float yInitial = (gameAreaResolutionX * targetsPaddingByXResolution) + (iY * ((gameAreaResolutionY * targetSizeYByResolution) + (gameAreaResolutionX * targetsDistanceByXResolution)));

                                    Target target = new Target("target", game, xInitial, yInitial,
                                            gameAreaResolutionX * targetSizeXByResolution,
                                            gameAreaResolutionY * targetSizeYByResolution, 9
                                            );
                                    target.isMovable = false;
                                    target.alpha = 1;
                                    target.states = new int[]{0,1};
                                    target.currentState = 1;
                                    game.addTarget(target);
                                    Log.e("game", "p5");
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

                Log.e("game", "3");
                break;
        }
    }
}