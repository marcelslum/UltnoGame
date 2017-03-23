package com.marcelslum.ultnogame;

import java.util.ArrayList;

public class Quadtree {

    Node root;


    //public static ArrayList<Entity> outs;

    public static Entity [] outs;
    public static int outsInsertIndex = 0;

    public static Pool<Node> nodePool;
    public static Pool<RectangleM> rectangleMPool;

    public Quadtree(float x, float y, float w, float h, int maxDepth, int maxChildren){

        outs = new Entity[300];
        /*
        if (outs == null){
            outs = new ArrayList<Entity>();
        } else {
            outs.clear();
        }
        */

        nodePool = new ObjectPool<>();
        nodePool.setFactory(new NodeFactory());

        root = new Node();
        root.setData(x, y, w, h, 0, maxDepth, maxChildren);

    }

    public void insert(Entity item){
        root.insert(item);
    }

    public void clear(){
        this.root.clear();
    }

    public void retrieve(Entity item){
        for (int i = 0; i < outs.length; i++){
            outs[i] = null;
        }
        outsInsertIndex = 0;
        root.retrieve(item);

    }

}
