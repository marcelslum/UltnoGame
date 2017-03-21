package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 21/03/2017.
 */

public class ObjectPool<T extends ObjectPool.Pooled> {
    private PooledCreator mCreator;
    private Pooled[] mObjects;
    private int mFreeIndex;

    public ObjectPool(int poolSize, PooledCreator creator) {
        mCreator = creator;
        mObjects = new Pooled[poolSize];
        mFreeIndex = -1;
    }

    public T borrow() {
        if (mFreeIndex < 0) {
            return (T) mCreator.create();
        }

        return (T) mObjects[mFreeIndex--];
    }

    public void release(Pooled obj) {
        if (obj == null) {
            return;
        }

        if (mFreeIndex >= mObjects.length - 1) {
            return;
        }

        mObjects[++mFreeIndex] = obj;
    }

    public interface Pooled {
    }

    public interface PooledCreator {
        Pooled create();
    }
}
