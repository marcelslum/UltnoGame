package com.marcelslum.ultnogame;


import java.util.ArrayList;


class Menu extends Entity{
    public ArrayList<MenuOption> menuOptions;
    public float size;
    public int selectedOption;
    public int optionsIds = 0;
    public final float bottomPad = 0.4f;
    public Font font;


    public Menu(String name, float x, float y, float size, Font font){
        super(name, x, y, Entity.TYPE_MENU);
        this.font = font;
        this.size = size;
        isBlocked = true;
        isVisible = false;
        menuOptions = new ArrayList<>();
    }

    public void block(){
        Game.blockAndWaitTouchRelease();
        this.isBlocked = true;

        for (int i = 0; i < menuOptions.size(); i++) {
            menuOptions.get(i).textObject.cleanAnimations();
        }

    }

    public void unblock(){
        Game.blockAndWaitTouchRelease();
        this.isBlocked = false;


        for (int i = 0; i < menuOptions.size(); i++) {

            Text t = menuOptions.get(i).textObject;

            Utils.createAnimation5v(t, "scaleXAnim","scaleX", 5000,
                    0f, 1f,
                    0.2f + (i * 0.03f), 1f,
                    0.27f + (i * 0.032f), 1.1f,
                    0.35f + (i * 0.035f), 1f,
                    1f, 1f,
                    true, true).start();


            Utils.createAnimation5v(t, "scaleYAnim","scaleY", 5000,
                    0f, 1f,
                    0.2f + (i * 0.03f), 1f,
                    0.27f + (i * 0.032f), 1.1f,
                    0.35f + (i * 0.035f), 1f,
                    1f, 1f,
                    true, true).start();

        }
    }

    @Override
    public void unblockAndDisplay() {
        appearAndUnblock(500);
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


        for (int i = 0; i < menuOptions.size(); i++) {

            Color color;
            Color shadow;
            if (lastMenuOptionColor == 0){
                lastMenuOptionColor += 1;
                color = Color.pretoCheio;
                shadow = Color.cinza40.changeAlpha(0.25f);
            } else if (lastMenuOptionColor == 1){
                lastMenuOptionColor += 1;
                color = Color.azul40;
                shadow = Color.azulCheio.changeAlpha(0.2f);
            } else if (lastMenuOptionColor == 2){
                lastMenuOptionColor += 1;
                color = Color.vermelho40;
                shadow = Color.vermelhoCheio.changeAlpha(0.2f);
            } else {
                lastMenuOptionColor = 0;
                color = Color.verde40;
                shadow = Color.verdeCheio.changeAlpha(0.2f);
            }

            menuOptions.get(i).textObject.setColor(color);
            menuOptions.get(i).textObject.addShadow(shadow);
        }


    }


    public void toSelector(Selector selector, String selectedValue){
        Game.sound.playPlayMenuBig();
        //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
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

        Game.sound.playPlayMenuBig();
        //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);
    }

    public MenuOption getMenuOptionByName(String name){
        for (int i = 0; i < this.menuOptions.size();i++){
            if (this.menuOptions.get(i).name.equals(name)){
                return this.menuOptions.get(i);
            }
        }
        return null;
    }


    static int lastMenuOptionColor = 0;

    public MenuOption addMenuOption(String name, String text, MenuOption.OnChoice onChoice){
        float optionY = this.y + (optionsIds * (size *(1.05f+bottomPad)));
        optionsIds += 1;

        Color color;
        Color shadow;
        if (lastMenuOptionColor == 0){
            lastMenuOptionColor += 1;
            color = Color.pretoCheio;
            shadow = Color.cinza40.changeAlpha(0.25f);
        } else if (lastMenuOptionColor == 1){
            lastMenuOptionColor += 1;
            color = Color.azul40;
            shadow = Color.azulCheio.changeAlpha(0.2f);
        } else if (lastMenuOptionColor == 2){
            lastMenuOptionColor += 1;
            color = Color.vermelho40;
            shadow = Color.vermelhoCheio.changeAlpha(0.2f);
        } else {
            lastMenuOptionColor = 0;
            color = Color.verde40;
            shadow = Color.verdeCheio.changeAlpha(0.2f);
        }


        MenuOption newMenuOption = new MenuOption(optionsIds, name, text, font, size, x, optionY, color, shadow);
        addChild(newMenuOption.textObject);
        newMenuOption.setOnChoice(onChoice);
        menuOptions.add(newMenuOption);

        final Menu innerMenu = this;
        final String innerName = name;
        final Text innerText = newMenuOption.textObject;
        final int innerId = optionsIds;

        newMenuOption.textObject.setListener(new InteractionListener(name,
                newMenuOption.x - (newMenuOption.width/2) - newMenuOption.width * 0.2f,
                optionY - newMenuOption.size * 0.15f,
                newMenuOption.width * 1.1f,
                newMenuOption.size * 1.3f,
                500, this,
                    new InteractionListener.PressListener() {
                        @Override
                        public void onPress() {
                            //Log.e("Menu", "interaction menu");
                            if (!innerMenu.isBlocked){

                                Game.vibrate(Game.VIBRATE_SMALL);
                                Game.blockAndWaitTouchRelease();
                                //Game.sound.playWin1();
                                Game.sound.playPlayMenuBig();
                                //Sound.playSoundPool(Sound.soundMenuSelectBig, 1, 1, 0);

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
            this.menuOptions.get(i).textObject.shadowText.alpha = alpha;
            this.menuOptions.get(i).textObject.shadowText.render(matrixView, matrixProjection);
            this.menuOptions.get(i).textObject.render(matrixView, matrixProjection);

        }
    }

}

