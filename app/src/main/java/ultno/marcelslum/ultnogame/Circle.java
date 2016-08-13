package ultno.marcelslum.ultno;

import android.util.Log;

/**
 * Created by marcel on 02/08/2016.
 */
public class Circle extends PhysicalObject {
    float radium;
    float[] verticesData;

    Circle(String name, Game game, float x, float y, float radium, int weight){
        super(name, game, x, y, weight);
        this.radium = radium;

    }

    @Override
    public void updateQuatreeData() {
        this.quadtreeData.setX(x - radium);
        this.quadtreeData.setY(y - radium);
        this.quadtreeData.setWidth(radium*2);
        this.quadtreeData.setHeight(radium*2);
    }

    @Override
    public void setSatData(){
        if(this.circleData == null) {
            //Log.e("setSatData", "setando data no circle");
            //Log.e("setSatData", "radium "+this.radium);
            this.circleData = new SatCircle(new Vector(this.x, this.y), this.radium);
        } else {
            this.circleData.pos.x = this.x;
            this.circleData.pos.y = this.y;
            this.circleData.r = this.radium;
        }
    }

    @Override
    public float getMiddlePointX() {
        return this.radium;
    }

    @Override
    public float getMiddlePointY() {
        return this.radium;
    }

}
