package com.marcelslum.ultnogame;

import android.app.Application;

public final class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //FontsOverride.setDefaultFont(this, "SERIF", "jetset.ttf");
    }
}
