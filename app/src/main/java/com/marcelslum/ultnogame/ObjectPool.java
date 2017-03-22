package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Marcel on 21/03/2017.
 */

public class ObjectPool<Type> implements Pool<Type> {

    private final Stack<Integer> freeObjectsID;
    private final ArrayList<Poolable<Type>> objects;
    private PoolObjectFactory<Type> factory;

    public ObjectPool() {
        freeObjectsID = new Stack<Integer>();
        objects = new ArrayList<Poolable<Type>>();
    }

    public void recycle(final Poolable<Type> data) {
        freeObjectsID.push(data.getPoolID());
    }

    @SuppressWarnings("unchecked")
    public Type get() {

        //Log.e("ObjectPool", "get new object ");

        if (freeObjectsID.isEmpty()) {

            //Log.e("ObjectPool", "freeObjectsID " + freeObjectsID.size());

            final Poolable<Type> obj = (Poolable<Type>) factory.newObject();
            obj.setPoolID(objects.size());

            //Log.e("ObjectPool", "objects " + objects.size());

            objects.add(obj);

            return obj.get();
        }

        int idReuse = freeObjectsID.pop();
        //Log.e("ObjectPool", "obj idReuse" + idReuse);

        final Poolable<Type> obj = objects.get(idReuse);

        obj.clean();

        return obj.get();
    }

    public void setFactory(final PoolObjectFactory<Type> factory) {
        this.factory = factory;
    }

    public void reset() {
        freeObjectsID.clear();
        final Iterator<Poolable<Type>> it = objects.iterator();

        while (it.hasNext()) {
            freeObjectsID.push(it.next().getPoolID());
        }
    }

    public String debug() {
        return "Current Pool Size: " + freeObjectsID.size();
    }
}
