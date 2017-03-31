package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 22/03/2017.
 */

class TextFactory implements PoolObjectFactory<Text> {
    @Override
    public Text newObject() {
        return new Text();
    }
}
