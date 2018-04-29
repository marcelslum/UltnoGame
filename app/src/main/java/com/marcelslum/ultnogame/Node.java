package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Marcel on 22/03/2017.
 */


class Node implements Poolable<Node>{
    public float x, y, w, h;
    public int depth;
    public int maxDepth;
    public int maxChildren;
    public ArrayList<Entity> children;
    public ArrayList<Entity> stuckChildren;
    public Node[] nodes;
    private final static int TOP_LEFT = 0;
    private final static int TOP_RIGHT = 1;
    private final static int BOTTOM_LEFT = 2;
    private final static int BOTTOM_RIGHT = 3;

    private int poolID;

    public Node(int depth, int maxDepth, int maxChildren){

        this.depth = depth;
        this.maxDepth = maxDepth;
        this.maxChildren = maxChildren;
        children = new ArrayList<>();
        stuckChildren = new ArrayList<>();
    }

    public Node(){
    }

    public void setData(float x, float y, float w, float h, int depth, int maxDepth, int maxChildren){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.depth = depth;
        this.maxDepth = maxDepth;
        this.maxChildren = maxChildren;
        this.children = new ArrayList<>();//Game.arrayListPool.get();//new ArrayList<>();
        this.stuckChildren = new ArrayList<>();//Game.arrayListPool.get();//new ArrayList<>();
    }

    public void insert(Entity item){

        ////System.out.println("inserindo item "+item.name);


        PhysicalObject pItem = (PhysicalObject)item;
        RectangleM itemBounds = pItem.getQuadtreeData();
        if (nodes != null){
            int index = findIndex(itemBounds.x, itemBounds.y);

            ////System.out.println("achado o index "+index +" para o item "+item.name);

            Node node = nodes[index];
            if (itemBounds.x >= node.x &&
                    itemBounds.x + itemBounds.width <= node.x + node.w &&
                    itemBounds.y >= node.y &&
                    itemBounds.y + itemBounds.height <= node.y + node.h)
            {
                node.insert(item);
            } else {

                stuckChildren.add(item);
            }
            return;
        }

        children.add(item);

        if (!(depth >= maxDepth) && children.size() > maxChildren){
            subdivide();
            for (int i = 0; i < children.size(); i++){
                insert(children.get(i));
            }
            children.clear();
        }
    }

    //public ArrayList<Entity> retrieve(Entity item){
    public void retrieve(Entity item){

        PhysicalObject pItem = (PhysicalObject)item;
        RectangleM itemBounds = pItem.getQuadtreeData();

        if (nodes != null){
            int index = this.findIndex(itemBounds.x, itemBounds.y);

            //if (item.type == Entity.TYPE_BALL)
            //System.out.println("index no retrieve do item " + item.name + " index: " + index + " nível: "+ this.depth);
            Node node = nodes[index];
            if (itemBounds.x >= node.x &&
                    itemBounds.x + itemBounds.width <= node.x + node.w &&
                    itemBounds.y >= node.y &&
                    itemBounds.y + itemBounds.height <= node.y + node.h)
            {
                //if (item.type == Entity.TYPE_BALL)
                //System.out.println("ativando função recursiva");
                node.retrieve(item);
            } else {
                //if (item.type == Entity.TYPE_BALL)
                //System.out.println("item está em mais de um node");

                if (itemBounds.x <= this.nodes[TOP_RIGHT].x){
                    if (itemBounds.y <= this.nodes[BOTTOM_LEFT].y){
                        this.nodes[TOP_LEFT].getAllContent(item);
                    }
                    if (itemBounds.y + itemBounds.height > this.nodes[BOTTOM_LEFT].y){
                        this.nodes[BOTTOM_LEFT].getAllContent(item);
                    }
                }

                if (itemBounds.x + itemBounds.width > this.nodes[TOP_RIGHT].x){
                    if (itemBounds.y <= this.nodes[BOTTOM_RIGHT].y){
                        this.nodes[TOP_RIGHT].getAllContent(item);
                    }
                    if (itemBounds.y + itemBounds.height > this.nodes[BOTTOM_RIGHT].y){
                        this.nodes[BOTTOM_RIGHT].getAllContent(item);
                    }
                }

            }
        }

        for (int i = 0; i < stuckChildren.size(); i++){
            //if (item.type == Entity.TYPE_BALL)
                //System.out.println("adicionando " + stuckChildren.get(i).name);

            if (preCheck(item, stuckChildren.get(i))) {
                Quadtree.outs[Quadtree.outsInsertIndex] = stuckChildren.get(i);
                Quadtree.outsInsertIndex += 1;
            }
        }

        for (int i = 0; i < children.size(); i++){
            //if (item.type == Entity.TYPE_BALL)
                //System.out.println("adicionando " + children.get(i).name);
            if (preCheck(item, children.get(i))) {
                Quadtree.outs[Quadtree.outsInsertIndex] = children.get(i);
                Quadtree.outsInsertIndex += 1;
            }
        }


        //outs.addAll(stuckChildren);
        //outs.addAll(children);

        //return outs;
    }


    public static boolean verifyCenter = false;
    public static boolean preCheck = false;
    public static int bWeight = -1;
    public static String TAG = "Node";

    // faz uma checagem para ver se vai retornar o objeto mesmo
    // checa vários aspectos
    // checa o pesa
    // checa se não é o mesmo objeto
    // checa barra e bola com borda
    // checa o centro


    public static boolean preCheck(Entity a, Entity b){

        if (Game.debugCollisionEscape)Log.e(TAG, "init preCheck ´´´´´´´´´´´´´´´´´´");

        if (Game.debugCollisionEscape)Log.e(TAG, a.name +" contra " + b.name + "    a.type "+ a.type + " b.type " + b.type);

        if (a == b){
            if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - same object");
            return false;
        }

        if (b.weight != bWeight){
            if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - weight invalid");
            return false;
        }



        if (!preCheck){
            return true;
        }

        boolean escapeByCenter = false;

        if (a.type == Entity.TYPE_BAR){
            if (b.type == Entity.TYPE_BOTTOM_BORDER || b.type == Entity.TYPE_TOP_BORDER){
                if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - bar com borda cima ou baixo");
                return false;
            }
        }

        if (a.type == Entity.TYPE_BALL){

            if (b.type == Entity.TYPE_BOTTOM_BORDER){
                if (a.positionY - a.maxHeight > b.y){
                    if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - ball com borda B");
                    return false;
                }
            } else

            if (b.type == Entity.TYPE_TOP_BORDER){
                if (a.positionY + a.maxHeight < (b.y + b.maxHeight)){
                    if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - ball com borda C");
                    return false;
                }
            } else if (b.type == Entity.TYPE_LEFT_BORDER){
                if (a.positionX - a.maxWidth > b.x + b.maxHeight){
                    if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - ball com borda E");
                    return false;
                }
            } else if (b.type == Entity.TYPE_RIGHT_BORDER){
                if (a.positionX + a.maxWidth < b.x){
                    if (Game.debugCollisionEscape)Log.e(TAG, "escape on node - ball com borda D");
                    return false;
                }
            }
        }

        if (verifyCenter) {

            if (Game.debugCollisionEscape) Log.e(TAG, "verificando center "+ a.name + " com " + b.name);
            if (Game.debugCollisionEscape)Log.e(TAG, "dados a " + a.centerX + " - " + a.maxWidth + " - " + a.centerY + " - " + a.maxHeight);
            if (Game.debugCollisionEscape)Log.e(TAG, "dados b " + b.centerX + " - " + b.maxWidth + " - " + b.centerY + " - " + b.maxHeight);

            if (a.maxWidth != 0f && b.maxWidth != 0f) {
                if (a.centerX > b.centerX) {
                    if (a.centerX - a.maxWidth/2f > b.centerX + b.maxWidth/2f) {
                        escapeByCenter = true;
                    }
                } else {
                    if (b.centerX - b.maxWidth/2f  > (a.centerX + a.maxWidth/2f)) {
                        escapeByCenter = true;
                    }
                }

                if (!escapeByCenter && a.maxHeight != 0f && b.maxHeight != 0f) {
                    if (a.centerY > b.centerY) {
                        if (a.centerY - a.maxHeight/2f > (b.centerY + b.maxHeight/2f)) {
                            escapeByCenter = true;
                        }
                    } else {
                        if (b.centerY - b.maxHeight/2f > (a.centerY + a.maxHeight)) {
                            escapeByCenter = true;
                        }
                    }
                }
            }

            if (escapeByCenter){
                Log.e(TAG, "escape on node - escape center");
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public void getAllContent(Entity item){

        //System.out.println("getAllContent");
        if (nodes != null){
            for (int i = 0; i < nodes.length; i++){
                nodes[i].getAllContent(item);
            }
        }
        //System.out.println("getAllContent2");

        for (int i = 0; i < stuckChildren.size(); i++){
            //if (item.type == Entity.TYPE_BALL)
            //    System.out.println("adicionando " + children.get(i).name);
            if (preCheck(item, stuckChildren.get(i))) {
                Quadtree.outs[Quadtree.outsInsertIndex] = stuckChildren.get(i);
                Quadtree.outsInsertIndex += 1;
            }
        }

        for (int i = 0; i < children.size(); i++){
            //if (item.type == Entity.TYPE_BALL)
            //    System.out.println("adicionando " + children.get(i).name);
            if (preCheck(item, children.get(i))) {
                Quadtree.outs[Quadtree.outsInsertIndex] = children.get(i);
                Quadtree.outsInsertIndex += 1;
            }
        }


        //outs.addAll(this.stuckChildren);

        //System.out.println("getAllContent3");
        //outs.addAll(this.children);
    }

    public void clear() {
        //Log.e("clear1", " " + this.depth);
        stuckChildren.clear();
        //Log.e("clear2", " " + this.depth);
        children.clear();
        //Log.e("clear3", " " + this.depth);
        if (nodes != null) {
            for (int i = 0; i < nodes.length; i++) {
                nodes[i].clear();
                //Log.e("clear4", " " + this.depth);
            }
        }
        nodes = null;
    }

    public int findIndex(float _x, float _y){

        ////System.out.println("find index bounds.x "+bounds.x +" em relação ao node de bounds x "+this.bounds.x+ " e width" + this.bounds.width);

        boolean left;

        if (_x > this.x + (this.w / 2)){
            left = false;
        } else {
            left =  true;
        }

        boolean top;
        if (_y > this.y + (this.h / 2)){
            top = false;
        } else {
            top =  true;
        }

        int index = TOP_LEFT;
        if (left){
            if (!top){
                index = BOTTOM_LEFT;
            }
        } else {
            if (top){
                index = TOP_RIGHT;
            } else {
                index = BOTTOM_RIGHT;
            }
        }
        return index;
    }

    public void subdivide(){

        //System.out.println("subdividindo o nível " + this.depth);

        int depth = this.depth + 1;

        float bx = this.x;
        float by = this.y;

        float b_w_h = (this.w / 2);
        float b_h_h = (this.h / 2);
        float bx_b_w_h = bx + b_w_h;
        float by_b_h_h = by + b_h_h;

        nodes = new Node[4];

        //top left
        nodes[TOP_LEFT] = Quadtree.nodePool.get();
        nodes[TOP_LEFT].setData(bx, by, b_w_h, b_h_h, depth, maxDepth, maxChildren);

        //top right
        nodes[TOP_RIGHT] = Quadtree.nodePool.get();
        nodes[TOP_RIGHT].setData(bx_b_w_h, by, b_w_h, b_h_h, depth, maxDepth, maxChildren);

        //bottom left
        nodes[BOTTOM_LEFT] = Quadtree.nodePool.get();
        nodes[BOTTOM_LEFT].setData(bx, by_b_h_h, b_w_h, b_h_h, depth, maxDepth, maxChildren);

        //bottom right
        nodes[BOTTOM_RIGHT] = Quadtree.nodePool.get();
        nodes[BOTTOM_RIGHT].setData(bx_b_w_h, by_b_h_h, b_w_h, b_h_h, depth, maxDepth, maxChildren);
    }


    @Override
    public void setPoolID(int id) {
        poolID = id;
    }

    @Override
    public int getPoolID() {
        return poolID;
    }

    @Override
    public Node get() {
        return this;
    }

    @Override
    public void clean() {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        depth = 0;
        maxDepth = 0;
        maxChildren = 0;
        children.clear();
        stuckChildren.clear();
        if (nodes != null) {
            nodes[0] = null;
            nodes[1] = null;
            nodes[2] = null;
            nodes[3] = null;
        }
    }
}
