package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

public class NodeFactory  implements PoolObjectFactory<Node>{
    @Override
    public Node newObject() {
        return new Node();
    }

}
