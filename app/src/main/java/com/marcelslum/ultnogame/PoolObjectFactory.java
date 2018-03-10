package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

public interface PoolObjectFactory<Type> {
    public Type newObject();
}
