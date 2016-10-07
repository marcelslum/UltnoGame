package com.marcelslum.ultnogame;

public class Circle extends PhysicalObject {
    float radius;
    float[] verticesData;

    Circle(String name, float x, float y, float radius, int weight){
        super(name, x, y, weight);
        this.radius = radius;
    }

    @Override
    public void updateQuatreeData() {
        float size = getTransformedRadius();
        this.quadtreeData.setX(positionX - size/2f);
        this.quadtreeData.setY(positionY - size/2f);
        this.quadtreeData.setWidth(size*2f);
        this.quadtreeData.setHeight(size*2f);
    }

    @Override
    public void setSatData(){
        if(circleData == null) {
            //Log.e("setSatData", "setando data no circle");
            //Log.e("setSatData", "radius "+this.radius);
            circleData = new SatCircle(new Vector(positionX, positionY), getTransformedRadius());
        } else {
            circleData.pos.x = positionX;
            circleData.pos.y = positionY;
            circleData.r = getTransformedRadius();
        }
    }

    public float getTransformedRadius() {
        return radius * accumulatedScaleX * accumulatedScaleY;
    }


}
