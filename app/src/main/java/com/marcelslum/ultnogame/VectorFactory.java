package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

class VectorFactory implements PoolObjectFactory<Vector> {
    @Override
    public Vector newObject() {
        return new Vector();
    }
}
