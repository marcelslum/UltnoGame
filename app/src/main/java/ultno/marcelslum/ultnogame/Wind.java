package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 08/09/2016.
 */
public class Wind extends Rectangle{

    public boolean isActive;

    public Wind(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, -1, new Color(1f, 1f, 1f, 1f));
        program = Game.windProgram;
        isActive = false;
        isSolid = false;
        isCollided = false;
    }

    public void stop(){
        isActive = false;
    }

    public void init(){
        isActive = true;
    }
}
