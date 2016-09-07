package ultno.marcelslum.ultnogame;


import java.util.ArrayList;


class Menu extends Entity{
    public ArrayList<MenuOption> menuOptions;
    public float size;
    public int selectedOption;
    public int optionsIds = 0;
    public final float bottomPad = 0.1f;
    public Font font;


    public Menu(String name, float x, float y, float size, Font font){
        super(name, x, y);
        this.font = font;
        this.size = size;
        isBlocked = true;
        isVisible = false;
        menuOptions = new ArrayList<>();
    }

    public void block(){
        Game.blockAndWaitTouchRelease();
        this.isBlocked = true;
    }

    public void unblock(){
        Game.blockAndWaitTouchRelease();
        this.isBlocked = false;
    }

    public void appearAndUnblock(int duration){
        display();
        if (duration == 0) {
            alpha = 1f;
            unblock();
        } else {
            alpha = 0f;
            increaseAlpha(duration, 1f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    unblock();
                }
            });
        }
    }

    public void toSelector(Selector selector, String selectedValue){
        Game.soundPool.play(Game.soundMenuSelectBig, 0.01f* (float) Game.volume, 0.01f* (float) Game.volume, 0, 0, 1);
        this.block();
        this.display();
        selector.menuRelated = this;
        for (int i = 0; i < selector.values.length;i++){
            if (selector.values[i] == selectedValue){
                selector.selectedValue = i;
            }
        }

        selector.isBlocked = true;
        selector.display();

        ArrayList<float[]> valuesAnimationMenu = new ArrayList<float[]>();
        valuesAnimationMenu.add(new float[]{0,1});
        valuesAnimationMenu.add(new float[]{1,0.3f});
        Animation animationMenu = new Animation(this, "alphaMenu", "alpha", 500, valuesAnimationMenu, false, true);
        final Selector innerSelector = selector;
        animationMenu.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationEnd() {
                innerSelector.unblock();
            }
        });
        animationMenu.start();

        ArrayList<float[]> valuesAnimationSelector = new ArrayList<float[]>();
        valuesAnimationSelector.add(new float[]{0,0});
        valuesAnimationSelector.add(new float[]{1,1});
        animationMenu.start();
    }

    public void fromSelector(Selector selector){
        ArrayList<float[]> valuesAnimationMenu = new ArrayList<>();
        valuesAnimationMenu.add(new float[]{0,0.3f});
        valuesAnimationMenu.add(new float[]{1,1});
        Animation animationMenu = new Animation(this, "alphaMenu", "alpha", 300, valuesAnimationMenu, false, true);
        final Menu innerMenu = this;
        animationMenu.setAnimationListener(new Animation.AnimationListener() {
                                               @Override
                                               public void onAnimationEnd() {
                                                   innerMenu.unblock();
                                               }
                                           });
        animationMenu.start();
        Game.soundPool.play(Game.soundMenuSelectBig, 0.01f* (float) Game.volume, 0.01f* (float) Game.volume, 0, 0, 1);
    }

    public MenuOption getMenuOptionByName(String name){
        for (int i = 0; i < this.menuOptions.size();i++){
            if (this.menuOptions.get(i).name.equals(name)){
                return this.menuOptions.get(i);
            }
        }
        return null;
    }

    public MenuOption addMenuOption(String name, String text, MenuOption.OnChoice onChoice){
        float optionY = this.y + (optionsIds * (size *(1.01f+bottomPad)));
        this.optionsIds += 1;
        MenuOption newMenuOption = new MenuOption(optionsIds, name, text, font, size, x, optionY);
        addChild(newMenuOption.textObject);
        newMenuOption.setOnChoice(onChoice);
        this.menuOptions.add(newMenuOption);

        final Menu innerMenu = this;
        final String innerName = name;
        final Text innerText = newMenuOption.textObject;
        final int innerId = this.optionsIds;

        newMenuOption.textObject.setListener(new InteractionListener(name,
                newMenuOption.x - (newMenuOption.width/2),
                optionY,
                newMenuOption.width,
                newMenuOption.size,
                500, this,
                    new InteractionListener.PressListener() {
                        @Override
                        public void onPress() {
                            //Log.e("Menu", "interaction menu");
                            if (!innerMenu.isBlocked){
                                Game.blockAndWaitTouchRelease();
                                Game.soundPool.play(Game.soundMenuSelectBig, 0.01f* (float) Game.volume, 0.01f* (float) Game.volume, 0, 0, 1);

                                ArrayList<float[]> valuesAnimation = new ArrayList<>();
                                valuesAnimation.add(new float[]{0f,1f});
                                valuesAnimation.add(new float[]{0.07f,0.82f});
                                valuesAnimation.add(new float[]{1f,1f});
                                Animation animScaleX = new Animation(innerText,
                                        "encolherX", "scaleX", 400, valuesAnimation, false, true);
                                animScaleX.start();

                                ArrayList<float[]> valuesAnimation2 = new ArrayList<>();
                                valuesAnimation2.add(new float[]{0f,1f});
                                valuesAnimation2.add(new float[]{0.07f,0.2f});
                                valuesAnimation2.add(new float[]{1f,1f});

                                Animation animAlpha = new Animation(innerText,
                                        "encolherAlpha", "alpha", 400, valuesAnimation2, false, true);
                                animAlpha.start();


                                Animation animScaleY = new Animation(innerText,
                                        "encolherY", "scaleY", 400, valuesAnimation, false, true);
                                animScaleY.setAnimationListener(new Animation.AnimationListener(){
                                    @Override
                                    public void onAnimationEnd() {
                                        innerMenu.getMenuOptionByName(innerName).fireOnChoice();
                                    }
                                });
                                animScaleY.start();

                                for (int i = 0; i < innerMenu.menuOptions.size(); i++){
                                    if (innerMenu.menuOptions.get(i).name.equals(innerName)){
                                        innerMenu.selectedOption = innerId;
                                    }
                                }
                            }
                        }

                        @Override
                        public void onUnpress() {

                        }
                    }
        ));
        return newMenuOption;
    }

    @Override
    public void render(float[] matrixView, float[] matrixProjection){
        //Log.e("menu", "render menu");
        for (int i = 0; i < this.menuOptions.size();i++){
            this.menuOptions.get(i).textObject.alpha = alpha;
            this.menuOptions.get(i).textObject.render(matrixView, matrixProjection);
        }
    }

}

