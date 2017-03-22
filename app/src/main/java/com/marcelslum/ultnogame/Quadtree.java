package com.marcelslum.ultnogame;

import java.util.ArrayList;

public class Quadtree {

    Node root;

    public static ArrayList<Entity> outs;
    public static Pool<Node> nodePool;

    public Quadtree(RectangleM bounds, int maxDepth, int maxChildren){
        if (outs == null){
            outs = new ArrayList<Entity>();
        } else {
            outs.clear();
        }

        nodePool = new ObjectPool<Node>();
        nodePool.setFactory(new NodeFactory());

        root = new Node();
        root.setData(bounds, 0, maxDepth, maxChildren);

    }

    public void insert(Entity item){
        this.root.insert(item);
    }

    public void clear(){
        this.root.clear();
    }

    public ArrayList<Entity> retrieve(Entity item){

        outs = new ArrayList<>();
        this.root.retrieve(item, outs);



        return outs;
    }

}
