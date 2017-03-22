package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

public interface Pool<Type> {
    public void recycle(final Poolable<Type> data);
    public Type get();
    public void setFactory(final PoolObjectFactory<Type> factory);
    public void reset();
    public String debug();
}
