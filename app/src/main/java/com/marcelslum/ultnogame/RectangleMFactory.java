package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

public class RectangleMFactory implements PoolObjectFactory<RectangleM>{
    @Override
    public RectangleM newObject() {
        return new RectangleM();
    }

}
