package ultno.marcelslum.ultno;

import java.util.ArrayList;

/**
 * Created by marcel on 04/08/2016.
 */

class Menu extends Entity{
    public ArrayList<MenuOption> menuOptions;
    public float size;
    public Audio audioSmall;
    public Audio audioLarge;
    public int selectedOption;
    public static int optionsIds;


    public Menu(String name, Game game, float x, float y, float size){
        super(name, game, x, y);
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

    public void appearAndUnblock(){
        this.display();
        this.unblock();
        this.alpha = 1;
    }

    public void toSelector(Selector selector, String selectedValue){
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
        this.audioLarge.play();
    }

    public MenuOption getMenuOptionByName(String name){
        for (int i = 0; i < this.menuOptions.size();i++){
            if (this.menuOptions.get(i).name == name){
                return this.menuOptions.get(i);
            }
        }
        return null;
    }

    public void addMenuOption(String name, String text){
        this.optionsIds += 1;
        this.menuOptions.add(new MenuOption(this.optionsIds, name, text));

        InteractionListener interactionListener = new InteractionListener(name, 0,0,0,0,500,this, this.game);

        final Menu innerMenu = this;
        final String innerName = name;
        final int innerId = this.optionsIds;
        interactionListener.setPressListener(new InteractionListener.PressListener() {
            @Override
            public void onPress() {
                if (!innerMenu.isBlocked){
                    innerMenu.game.blockAndWaitTouchRelease();
                    innerMenu.audioLarge.play();
                    innerMenu.getMenuOptionByName(innerName).fireOnChoice();

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
        });
        this.addListener(interactionListener);
    }

    public void setAudioLarge(Audio audio) {
        this.audioLarge = audio;
    }

    public void setAudioSmall(Audio audio) {
        this.audioSmall = audio;
    }


}

class MenuOption{
    public int id;
    public String name;
    public String text;
    public OnChoice myOnChoice;
    public float textWidth;
    public float x;
    public float y;
    public boolean isSelected;
    public float height;
    public float width;

    public MenuOption(int id, String name, String text){
        this.id = id;
        this.name = name;
        this.text = text;
        this.isSelected = false;
        this.x = 0;
        this.y = 0;
    }

    public void setOnChoice(OnChoice onChoice){
        this.myOnChoice = onChoice;
    }

    public void fireOnChoice(){
        if (this.myOnChoice != null){
            this.myOnChoice.onChoice();
        }
    }

    interface OnChoice{
        public void onChoice();
    }
}

