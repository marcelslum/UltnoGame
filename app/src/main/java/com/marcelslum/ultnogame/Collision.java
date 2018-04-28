package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

public abstract class Collision {

    public static final int NUMBER_OF_ITERATIONS = 1; // SEMPRE DEVE SER 1????

    private Collision(){}
    public static boolean collided;

    // sat data
    //private static SatPolygon polygon1;
    //private static SatPolygon polygon2;
    //private static SatCircle circle1;
    //private static SatCircle circle2;

    private static float [] velocities = new float[4];

    private static float aPreviousX;
    private static float aPreviousY;
    private static float bPreviousX;
    private static float bPreviousY;

    private static boolean isHadCollision;
    private static SatResponse response;
    private static boolean onQuarentine;
    private static boolean aType;
    private static boolean bType;
    private static float max;
    private static int maxIndex;
    private static int quantityPassagens;
    private static float aDiferencaPosicaoX;
    private static float aDiferencaPosicaoY;
    private static float bDiferencaPosicaoX;
    private static float bDiferencaPosicaoY;
    private static float porcentagem;
    private static float porcentagemAplicadaNaPassagem;
    private static float aPosAConsiderarX;
    private static float aPosAConsiderarY;
    private static float bPosAConsiderarX;
    private static float bPosAConsiderarY;

    private static boolean escapeByCenter;
    private static boolean checkBWeight;
    private static Bar bar1;
    private static Bar bar2;

    private static boolean bTarget = false;

    private static final String TAG = "Collision";

    public static boolean checkCollision(ArrayList<? extends PhysicalObject> aEntities, Quadtree quad, int bWeight, boolean respond, boolean updateData, boolean verifyCenter){
        //if (polygon1 == null){
        //    initData();
        //}

        collided = false;

        isHadCollision = false;

        if (response == null) {
            response = new SatResponse();
        }

        for (int iLoop = 0; iLoop < NUMBER_OF_ITERATIONS; iLoop++){
            // entidades a
            for (int aCount = 0; aCount < aEntities.size(); aCount++){
                PhysicalObject a =  aEntities.get(aCount);
                collided = false;

                quad.retrieve(a);
                //out = quad.retrieve(a);

                //Log.e(TAG, " a.name " + a.name);

                // roda pelas entidades extraidas e verifica a colisão
                for (int bCount = 0; bCount <= Quadtree.outsInsertIndex; bCount++) {


                    // se ambas as entidades forem barras, faz uma checagem mais simples
                    if (a.type == Entity.TYPE_BAR && Quadtree.outs[bCount] != null && Quadtree.outs[bCount].type == Entity.TYPE_BAR) {

                        if (a.positionX < Quadtree.outs[bCount].positionX) {
                            bar1 = (Bar) a;
                            bar2 = (Bar) Quadtree.outs[bCount];

                            float differenceBetweenBars = (bar2.positionX - (bar1.getTransformedWidth() + bar1.positionX));

                            //Log.e(TAG, "differenceBetweenBars " + differenceBetweenBars);

                            if (differenceBetweenBars < 0) {

                                bar1.accumulatedTranslateX += (differenceBetweenBars / 2f);
                                bar2.accumulatedTranslateX += ((differenceBetweenBars * -1f) / 2f);
                                bar1.checkTransformations(false);
                                bar2.checkTransformations(false);

                            }
                        }
                    }
                    else
                    {


                        escapeByCenter = false;
                        if (verifyCenter && Quadtree.outs[bCount] != null) {
                            if (a.maxWidth != 0f && Quadtree.outs[bCount].maxWidth != 0f) {
                                if (a.centerX > Quadtree.outs[bCount].centerX) {
                                    if (a.centerX - Quadtree.outs[bCount].centerX > a.maxWidth + Quadtree.outs[bCount].maxWidth) {
                                        escapeByCenter = true;
                                    }
                                } else {
                                    if (Quadtree.outs[bCount].centerX - a.centerX > a.maxWidth + Quadtree.outs[bCount].maxWidth) {
                                        escapeByCenter = true;
                                    }
                                }
                                if (!escapeByCenter) {

                                    if (a.centerY > Quadtree.outs[bCount].centerY) {
                                        if (a.centerY - Quadtree.outs[bCount].centerY > a.maxHeight + Quadtree.outs[bCount].maxHeight) {
                                            escapeByCenter = true;
                                        }
                                    } else {
                                        if (Quadtree.outs[bCount].centerY - a.centerY > a.maxHeight + Quadtree.outs[bCount].maxHeight) {
                                            escapeByCenter = true;
                                        }
                                    }


                                }
                            }
                        }


                        //if (escapeByCenter){
                        //    Log.e(TAG, "escape by center" + a.name + " - " + Quadtree.outs[bCount].name);
                        //}

                        if (Quadtree.outs[bCount] != null && !escapeByCenter) {

                            //Log.e(TAG, " Quadtree.outs " + bCount);

                            PhysicalObject b = (PhysicalObject) Quadtree.outs[bCount];

                            //Log.e(TAG, " b.name " + b.name);

                            onQuarentine = false;

                            if (a.type == Entity.TYPE_BALL && b.type == Entity.TYPE_BALL) {

                                Ball ball1 = (Ball) a;
                                Ball ball2 = (Ball) b;

                                if (ball1.quarentineBalls != null) {
                                    for (int q = 0; q < ball1.quarentineBalls.size(); q++) {
                                        if (ball1.quarentineBalls.get(q) == ball2) {
                                            //Log.e(TAG, "dispensando bola por estar em quarentena " + ball1.quarentineBallsState.get(q));
                                            onQuarentine = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            // verifica o peso da entidade b
                            // se a variável bWeight for 0, pula essa verificação
                            checkBWeight = true;
                            if (bWeight != 0) {
                                if (bWeight != b.getWeight()) {
                                    checkBWeight = false;
                                }
                            }

                            // verifica se a entidade não é a mesma e se elas são colidíveis e se o peso é compatível
                            if ((a.isSolid && b.isSolid) && (b != a) && checkBWeight && !onQuarentine) {

                                // seta os dados da entidade 'a' e da entidade 'b'
                                a.setSatData();
                                b.setSatData();

                                // zera os dados a serem usados nesta passagem
                                // transfere os dados da entidade para o objeto do game

                                //type false: polygon true: circle
                                aType = false;
                                bType = false;

                                if (a.circleData != null) {
                                    //circle1.pos.x = a.circleData.pos.x;
                                    //circle1.pos.y = a.circleData.pos.y;
                                    //circle1.r = a.circleData.r;
                                    aType = true;
                                } else {
                                    //polygon1.pos.x = a.polygonData.pos.x;
                                    //polygon1.pos.y = a.polygonData.pos.y;
                                    //polygon1.copyDataFromAnotherPolygon(a.polygonData);
                                }

                                if (b.circleData != null) {
                                    //circle2.pos.x = b.circleData.pos.x;
                                    //circle2.pos.y = b.circleData.pos.y;
                                    //circle2.r = b.circleData.r;
                                    bType = true;
                                } else {
                                    //polygon2.pos.x = b.polygonData.pos.x;
                                    //polygon2.pos.y = b.polygonData.pos.y;
                                    //polygon2.copyDataFromAnotherPolygon(b.polygonData);
                                }


                                //Log.e("pos bola sat cc2", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                                velocities[0] = Math.abs(a.vx);
                                velocities[1] = Math.abs(a.vy);
                                velocities[2] = Math.abs(b.vx);
                                velocities[3] = Math.abs(b.vy);


                                //if (a.name == "bar" && b.name == "bar"){
                                //Log.e(TAG, " velocities "+velocities[0]+" - "+velocities[1]+" - "+velocities[2]+" - "+velocities[3]);
                                //}


                                max = -100000;
                                maxIndex = -1;
                                for (int n = 0; n < 4; n++) {
                                    if (velocities[n] > max) {
                                        maxIndex = n;
                                        max = velocities[n];
                                    }
                                }

                                //if (a.name == "bar" && b.name == "bar"){
                                //    //Log.e(TAG, " maxIndex "+maxIndex);
                                //    //Log.e(TAG, " max "+max);
                                //    //Log.e(TAG, " velocities[maxIndex]/5f "+velocities[maxIndex]/2f);
                                //    //Log.e(TAG, " Math.round(velocities[maxIndex]/5f) "+Math.round(velocities[maxIndex]/5f));
                                //}


                                // definir quantas passagens serão realidades, com base na maior velocidade
                                // quanto maior o número divisor, menor o número de passagens
                                quantityPassagens = Math.round(velocities[maxIndex] / 1.8f);
                                if (quantityPassagens == 0) {
                                    quantityPassagens = 1;
                                }


                                //Log.e("Game", " a.previousPositionX "+a.previousPositionX);
                                //Log.e("Game", " a.previousPositionY "+a.previousPositionY);
                                //Log.e("Game", " b.previousPositionX "+b.previousPositionX);
                                //Log.e("Game", " b.previousPositionY "+b.previousPositionY);

                                aPreviousX = a.previousPositionX;
                                aPreviousY = a.previousPositionY;
                                bPreviousX = b.previousPositionX;
                                bPreviousY = b.previousPositionY;

                                //if (a.name == "ball"){
                                //    //Log.e(TAG, " quantityPassagens "+quantityPassagens);
                                //}

                                //Log.e("Game", " a.previousPositionX 2"+aPreviousX);
                                //Log.e("Game", " a.previousPositionY 2"+aPreviousY);
                                //Log.e("Game", " b.previousPositionX 2"+bPreviousX);
                                //Log.e("Game", " b.previousPositionY 2"+bPreviousY);

                                // //Log.e("pos bola sat cc3", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                                // calcula a diferença entre as posições


                                aDiferencaPosicaoX = a.positionX - aPreviousX;
                                aDiferencaPosicaoY = a.positionY - aPreviousY;

                                bDiferencaPosicaoX = b.positionX - bPreviousX;
                                bDiferencaPosicaoY = b.positionY - bPreviousY;

                                // calcula a porcentagem de cada passada;
                                porcentagem = (100f / quantityPassagens) / 100f;

                                //if (a.name == "bar" && b.name == "bar"){
                                //    //Log.e(TAG, " quantityPassagens "+quantityPassagens+" porcentagem "+porcentagem);
                                //}


                                //Log.e("Game", " quantityPassagens "+quantityPassagens+" porcentagem "+porcentagem);

                                aPosAConsiderarX = -1000f;
                                aPosAConsiderarY = -1000f;
                                bPosAConsiderarX = -1000f;
                                bPosAConsiderarY = -1000f;


                                //if (b.name == "obstacle"){
                                //    //Log.e("game", " x"+ this.polygon2.pos.x);
                                //}

                                //Log.e("pos bola sat cc4", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                                // itera pelas passagens, checando se há colisão

                                for (int ip = 0; ip < quantityPassagens; ip++) {

                                    response.clear();
                                    porcentagemAplicadaNaPassagem = porcentagem * (float) (ip + 1);
                                    //Log.e("Game", "passagem "+ip+" porcentagem "+porcentagem);

                                    aPosAConsiderarX = aPreviousX + (aDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                    aPosAConsiderarY = aPreviousY + (aDiferencaPosicaoY * porcentagemAplicadaNaPassagem);
                                    //Log.e("Game", "passagem "+ip+" a.previousPositionY "+ aPreviousY +" aDiferencaPosicaoX "+aDiferencaPosicaoX +  " porcentagemAplicadaNaPassagem "+porcentagemAplicadaNaPassagem);
                                    //Log.e("Game", "passagem "+ip+" aPosAConsiderarX "+ aPosAConsiderarX +" aPosAConsiderarY "+aPosAConsiderarY);
                                    //Log.e("Game", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);

                                    bPosAConsiderarX = bPreviousX + (bDiferencaPosicaoX * porcentagemAplicadaNaPassagem);
                                    bPosAConsiderarY = bPreviousY + (bDiferencaPosicaoY * porcentagemAplicadaNaPassagem);

                                    //Log.e("test posicoes", "a.y"+aPosAConsiderarY + " b.y"+bPosAConsiderarY);


                                    if (!aType) {
                                        a.polygonData.pos.x = aPosAConsiderarX;
                                        a.polygonData.pos.y = aPosAConsiderarY;
                                    } else {
                                        a.circleData.pos.x = aPosAConsiderarX;
                                        a.circleData.pos.y = aPosAConsiderarY;
                                    }

                                    if (!bType) {
                                        b.polygonData.pos.x = bPosAConsiderarX;
                                        b.polygonData.pos.y = bPosAConsiderarY;
                                    } else {
                                        b.circleData.pos.x = bPosAConsiderarX;
                                        b.circleData.pos.y = bPosAConsiderarY;
                                    }


                                    //Log.e("pos bola sat cc5", "x "+this.balls.get(0).circleData.pos.x+ " y "+this.balls.get(0).circleData.pos.y+ " radius "+ this.balls.get(0).circleData.r);

                                    if (!aType) {
                                        if (!bType) {
                                            collided = Sat.getInstance().testPolygonPolygon(a.polygonData, b.polygonData, response);
                                        } else {
                                            collided = Sat.getInstance().testPolygonCircle(a.polygonData, b.circleData, response);
                                        }
                                    } else {
                                        if (!bType) {
                                            collided = Sat.getInstance().testCirclePolygon(a.circleData, b.polygonData, response);
                                        } else {
                                            collided = Sat.getInstance().testCircleCircle(a.circleData, b.circleData, response);
                                        }

                                    }

                                    // se houver registro de colisão, mas se a resposta foi zerada, retorna para uma próxima verificação
                                    if (collided) {
                                        if (!(response.overlapV.x == 0 && response.overlapV.y == 0)) {
                                            break;
                                        }
                                    }
                                }

                                //if (b.name == "obstacle"){
                                //    //Log.e("game", " x"+ this.polygon2.pos.x);
                                //}

                                if (collided && !(response.overlapV.x == 0 && response.overlapV.y == 0)) {

                                    isHadCollision = true;

                                    if (respond) {
                                        a.isCollided = true;
                                        b.isCollided = true;
                                        // informa a resposta a ser exercida no objeto "a"
                                        respond(a, b, -response.overlapV.x * 1.001f, -response.overlapV.y * 1.001f, aPosAConsiderarX, aPosAConsiderarY, bPosAConsiderarX, bPosAConsiderarY);
                                    }

                                    if (updateData) {
                                        // grava a resposta exercida no objeto "a"
                                        a.collisionsData.add(new CollisionData(
                                                b,
                                                -response.overlapV.x * 1.001f,
                                                -response.overlapV.y * 1.001f,
                                                -response.overlapN.x,
                                                -response.overlapN.y,
                                                b.weight
                                        ));

                                        // grava a resposta exercida no objeto "b"
                                        b.collisionsData.add(new CollisionData(
                                                a,
                                                response.overlapV.x * 1.0001f,
                                                response.overlapV.y * 1.0001f,
                                                response.overlapN.x,
                                                response.overlapN.y,
                                                a.weight
                                        ));
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

    public static void initData(){
        /*
        ArrayList<Vector> points = new ArrayList<>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        Collision.polygon1 = new SatPolygon(new Vector(0, 0), points);

        ArrayList<Vector> points2 = new ArrayList<>();
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        points.add(new Vector(0, 0));
        Collision.polygon2 = new SatPolygon(new Vector(0, 0), points2);

        Collision.circle1 = new SatCircle(new Vector(0,0),0);
        Collision.circle2 = new SatCircle(new Vector(0,0),0);
        */
    }

    public static void respond(PhysicalObject a, PhysicalObject b, float responseX, float responseY, float ax, float ay, float bx, float by) {
        /*
        //Log.e("physicalObject",
                " ax "+ax+
                " ay "+ay+
                " posX "+a.positionX+
                " posY "+a.positionY+
                " a weight "+a.getWeight()+
                " bx "+bx+
                " by "+by+
                " posX "+b.positionX+
                " posY "+b.positionY+
                " b weight "+b.getWeight()
        );
       if (name == "ball"){
            if (other.name == "obstacle"){
                //Log.e("physicalObject", "objetos colididos com a bola que colide com o obstaculo");
                //Log.e("physicalObject", "responseX "+responseX);
                //Log.e("physicalObject", "responseY "+responseY);
                for (int i = 0; i < other.lastCollisionObjects.size(); i++){
                    //Log.e("physicalObject", other.lastCollisionObjects.get(i).name);
                    //Log.e("physicalObject", "response objeto x"+other.lastCollisionResponse.get(i).x);
                    //Log.e("physicalObject", "response objeto y"+other.lastCollisionResponse.get(i).y);
                }
            }
        }
        */

        //Log.e("Collision", a.name + " contra " + b.name);

        a.accumulatedTranslateX += ax - a.positionX;
        a.accumulatedTranslateY += ay - a.positionY;
        b.accumulatedTranslateX += bx - b.positionX;
        b.accumulatedTranslateY += by - b.positionY;

        if (a.getWeight() > b.getWeight()) {// Move the other object out of us
            //Log.e("Collision", a.name + " A mais pesado que " + b.name + " rX "+responseX + " rY " + responseY);

            float tResponseX = 0f;
            float tResponseY = 0f;
            for (int i = 0; i < b.collisionsData.size(); i++){
                if (b.collisionsData.get(i).object != a) {

                    if (b.collisionsData.get(i).object.getWeight() > a.getWeight()) {
                        //Log.e("Collision", "objeto mais pesado "+b.collisionsData.get(i).object.name +" oferece força de x " + b.collisionsData.get(i).normalX + " y " + b.collisionsData.get(i).normalY);
                        if ((b.collisionsData.get(i).responseX < 0 && responseX > 0)||
                            (b.collisionsData.get(i).responseX > 0 && responseX < 0)){
                            tResponseX = responseX;
                            responseX = 0f;
                        }
                        if ((b.collisionsData.get(i).responseY < 0 && responseY > 0)||
                                (b.collisionsData.get(i).responseY > 0 && responseY < 0)){
                            tResponseY = responseY;
                            responseY = 0f;
                        }
                    }
                }
            }
            if (!(tResponseX == 0f && tResponseY == 0)) {
                b.respondToCollision(-tResponseX, -tResponseY);
            }
            a.respondToCollision(responseX, responseY);
        } else if (a.getWeight() < b.getWeight()) {        // Move us out of the other object
            //Log.e("Collision", a.name + " mais leve que " + b.name + " rX "+responseX + " rY " + responseY);
            //Log.e("PhysicalObject", "outro "+b.name+" mais pesado "+responseX+ " "+responseY );

            float tResponseX = 0f;
            float tResponseY = 0f;
            for (int i = 0; i < a.collisionsData.size(); i++){
                if (a.collisionsData.get(i).object != b) {
                    if (a.collisionsData.get(i).object.getWeight() > b.getWeight()) {
                        //Log.e("Collision", "B objeto mais pesado "+a.collisionsData.get(i).object.name +" oferece força de x " + a.collisionsData.get(i).normalX + " y " + a.collisionsData.get(i).normalY);
                        if ((a.collisionsData.get(i).responseX < 0 && responseX > 0)||
                                (a.collisionsData.get(i).responseX > 0 && responseX < 0)){
                            tResponseX = responseX;
                            responseX = 0f;
                        }
                        if ((a.collisionsData.get(i).responseY < 0 && responseY > 0)||
                                (a.collisionsData.get(i).responseY > 0 && responseY < 0)){
                            tResponseY = responseY;
                            responseY = 0f;
                        }
                    }
                }
            }
            if (!(tResponseX == 0f && tResponseY == 0)) {
                b.respondToCollision(-tResponseX, -tResponseY);
            }
            a.respondToCollision(responseX, responseY);
        } else if (a.getWeight() == b.getWeight()){        // Move equally out of each other
            //Log.e("Collision", a.name + " mesmo peso que " + b.name);
            float aResponseX = responseX/2f;
            float aResponseY = responseY/2f;
            float bResponseX = -responseX/2f;
            float bResponseY = -responseY/2f;
            for (int i = 0; i < a.collisionsData.size(); i++){
                if (a.collisionsData.get(i).object != b) {
                    if (a.collisionsData.get(i).object.getWeight() > a.getWeight()) {
                        if ((a.collisionsData.get(i).responseX < 0 && aResponseX > 0)||
                                (a.collisionsData.get(i).responseX > 0 && aResponseX < 0)){
                            aResponseX = 0f;
                            bResponseX *= 2f;
                        }
                        if ((a.collisionsData.get(i).responseY < 0 && aResponseY > 0)||
                                (a.collisionsData.get(i).responseY > 0 && aResponseY < 0)){
                            aResponseY = 0f;
                            bResponseY *= 2f;
                        }
                    }
                }
            }
            for (int i = 0; i < b.collisionsData.size(); i++){
                if (b.collisionsData.get(i).object != a) {
                    if (b.collisionsData.get(i).object.getWeight() > b.getWeight()) {
                        if ((b.collisionsData.get(i).responseX < 0 && bResponseX > 0)||
                                (b.collisionsData.get(i).responseX > 0 && bResponseX < 0)){
                            aResponseX *= 2f;
                            bResponseX = 0f;
                        }
                        if ((b.collisionsData.get(i).responseY < 0 && bResponseY > 0)||
                                (b.collisionsData.get(i).responseY > 0 && bResponseY < 0)){
                            aResponseY *= 2f;
                            bResponseY = 0f;
                        }
                    }
                }
            }
            a.respondToCollision(aResponseX, aResponseY);
            b.respondToCollision(bResponseX, bResponseY);
        }
    }
}
