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
        float size = getTransformedWidht();
        this.quadtreeData.setX(positionX - size/2f);
        this.quadtreeData.setY(positionY - size/2f);
        this.quadtreeData.setWidth(size);
        this.quadtreeData.setHeight(size);
    }

    @Override
    public void setSatData(){
        if(circleData == null) {
            //Log.e("setSatData", "setando data no circle");
            //Log.e("setSatData", "radius "+this.radius);
            circleData = new SatCircle(new Vector(positionX, positionY), radius);
        } else {
            circleData.pos.x = positionX;
            circleData.pos.y = positionY;
            circleData.r = radius;
        }
    }

    @Override
    public float getWidth() {
        return radius*2f;
    }

    @Override
    public float getHeight() {
        return radius*2f;
    }
    
    @Override
    public float getTransformedWidth() {
        return (radius*2f)*accumulatedScaleX;
    }

    @Override
    public float getTransformedHeight() {
        return (radius*2f)*accumulatedScaleX;
    }

}
