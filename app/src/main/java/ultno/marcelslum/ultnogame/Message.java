package ultno.marcelslum.ultnogame;


import java.util.ArrayList;

/**
 * Created by marcel on 07/08/2016.
 */
public class Message extends Entity {
    String text;
    Text textObject;
    float size;

    public Message(String name, Game game, String text, float x, float y, float size, Color color, int align) {
        super(name, game, x, y);
        this.size = size;
        this.text = text;
        textObject = new Text("text", game, x, y, size, text, game.font, color, align);
        addChild(textObject);
        isVisible = false;
    }

    public void show(int duration){
        isVisible = true;
        final Message self = this;
        Utils.createSimpleAnimation(textObject, "alpha", "alpha", duration, 1f, 0f, new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd() {
                self.isVisible = false;
            }
        }).start();
    }

    public void render(float[] matrixView, float[] matrixProjection) {
        textObject.render(matrixView, matrixProjection);
    }
}
