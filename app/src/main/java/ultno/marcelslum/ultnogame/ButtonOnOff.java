package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 07/08/2016.
 */
public class ButtonOnOff extends Button{

    public boolean on;

    ButtonOnOff(String name, Game game, float x, float y, float width, float height){
        super(name, game, x, y, width, height);
        this.on = false;
    }


}
