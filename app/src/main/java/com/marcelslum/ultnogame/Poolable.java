package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

public interface Poolable<Type> {
    public void setPoolID(final int id);
    public int getPoolID();
    public Type get();
    public void clean();
}
