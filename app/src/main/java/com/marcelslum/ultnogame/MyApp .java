public final class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "jetset.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "jetset.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "jetset.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "jetset.ttf");
    }
}
