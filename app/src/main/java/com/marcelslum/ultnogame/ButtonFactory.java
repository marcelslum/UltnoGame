package com.marcelslum.ultnogame;

/**
 * Created by Marcel on 31/03/2017.
 */

class ButtonFactory implements PoolObjectFactory<Button> {
    @Override
    public Button newObject() {
        return new Button();
    }
}
