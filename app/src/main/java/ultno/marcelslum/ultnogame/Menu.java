package ultno.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 04/08/2016.
 */

class Menu extends Entity{
    public ArrayList<MenuOption> menuOptions;
    public float size;
    public int selectedOption;
    public int optionsIds = 0;
    public final float bottomPad = 0.1f;
    public Font font;


    public Menu(String name, Game game, float x, float y, float size, Font font){
        super(name, game, x, y);
        this.font = font;
        this.size = size;
        isBlocked = true;
        isVisible = false;
        menuOptions = new ArrayList<MenuOption>();
    }

    public void block(){
        this.game.blockAndWaitTouchRelease();
        this.isBlocked = true;
    }

    public void unblock(){
        this.game.blockAndWaitTouchRelease();
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
        game.soundPool.play(game.soundMenuSelectBig, 1, 1, 0, 0, 1);
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
        Animation animationSelector = new Animation(this, "alphaSelector", "alpha", 800, valuesAnimationSelector, false, true);
        animationMenu.start();
    }

    public void fromSelector(Selector selector){
        ArrayList<float[]> valuesAnimationMenu = new ArrayList<float[]>();
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
        game.soundPool.play(game.soundMenuSelectBig, 1, 1, 0, 0, 1);
    }

    public MenuOption getMenuOptionByName(String name){
        for (int i = 0; i < this.menuOptions.size();i++){
            if (this.menuOptions.get(i).name == name){
                return this.menuOptions.get(i);
            }
        }
        return null;
    }

    public MenuOption addMenuOption(String name, String text, MenuOption.OnChoice onChoice){
        float optionY = this.y + (optionsIds * (size *(1.01f+bottomPad)));
        this.optionsIds += 1;
        MenuOption newMenuOption = new MenuOption(this.optionsIds, name, text, game, font, size, x, optionY);
        addChild(newMenuOption.textObject);
        newMenuOption.setOnChoice(onChoice);
        this.menuOptions.add(newMenuOption);

        final Menu innerMenu = this;
        final String innerName = name;
        final Text innerText = newMenuOption.textObject;
        final int innerId = this.optionsIds;

        setListener(new InteractionListener(name,
                newMenuOption.x - (newMenuOption.width/2),
                optionY,
                newMenuOption.width,
                newMenuOption.size,
                500, this, game,
                    new InteractionListener.PressListener() {
                        @Override
                        public void onPress() {
                            //Log.e("Menu", "interaction menu");
                            if (!innerMenu.isBlocked){
                                innerMenu.game.blockAndWaitTouchRelease();
                                game.soundPool.play(game.soundMenuSelectBig, 1, 1, 0, 0, 1);

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
                                    if (innerMenu.menuOptions.get(i).name == innerName){
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

