package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 27/09/2016.
 */
public class Edge extends Rectangle{
    Edge(String name, float x, float y, float width, float height) {
        super(name, x, y, width, height, Game.BORDA_WEIGHT, new Color(0,0,0,1));
        isMovable = false;
        isVisible = true;
        isCollidable = false;
        program = Game.solidProgram;
    }
}
