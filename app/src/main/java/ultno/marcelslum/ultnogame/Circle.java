package ultno.marcelslum.ultnogame;


/**
 * Created by marcel on 02/08/2016.
 */
public class Circle extends PhysicalObject {
    float radius;
    float[] verticesData;

    Circle(String name, Game game, float x, float y, float radius, int weight){
        super(name, game, x, y, weight);
        this.radius = radius;

    }

    @Override
    public void updateQuatreeData() {
        this.quadtreeData.setX(x - radius);
        this.quadtreeData.setY(y - radius);
        this.quadtreeData.setWidth(radius *2);
        this.quadtreeData.setHeight(radius *2);
    }

    @Override
    public void setSatData(){
        if(this.circleData == null) {
            //Log.e("setSatData", "setando data no circle");
            //Log.e("setSatData", "radius "+this.radius);
            this.circleData = new SatCircle(new Vector(this.x, this.y), this.radius);
        } else {
            this.circleData.pos.x = this.x;
            this.circleData.pos.y = this.y;
            this.circleData.r = this.radius;
        }
    }

    @Override
    public float getMiddlePointX() {
        return this.radius;
    }

    @Override
    public float getMiddlePointY() {
        return this.radius;
    }

}
