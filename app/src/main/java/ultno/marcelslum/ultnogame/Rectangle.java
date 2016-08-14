package ultno.marcelslum.ultnogame;


import android.graphics.PointF;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Rectangle extends PhysicalObject {
    float width;
    float height;

    Rectangle(String name, Game game, float x, float y, float width, float height, int weight, Color color){
        super(name, game, x, y, weight);
        this.color = color;
        this.width = width;
        this.height = height;
        setDrawInfo();
    }

    public void setDrawInfo(){
        
        initializeData(12, 6, 0, 16);
        
        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, 0f);
       verticesBuffer = Utils.generateFloatBuffer(verticesData);

        Utils.insertRectangleIndicesData(indicesData, 0, 0);
        indicesBuffer = Utils.generateShortBuffer(indicesData);

        Utils.insertRectangleColorsData(colorsData, 0, color);
        colorsBuffer = Utils.generateFloatBuffer(colorsData);
    }

    @Override
    public void setSatData(){
        if(this.polygonData == null) {
            ArrayList<Vector> points = new ArrayList<Vector>();
            points.add(new Vector(0, 0));
            points.add(new Vector(width, 0));
            points.add(new Vector(width, height));
            points.add(new Vector(0, height));
            this.polygonData = new SatPolygon(new Vector(x, y), points);
        } else {
            this.polygonData.pos.x = x;
            this.polygonData.pos.y = y;
        }
    }

    @Override
    public float getMiddlePointX() {
        return this.width/2;
    }

    @Override
    public float getMiddlePointY() {
        return this.height/2;
    }

    @Override
    public void updateQuatreeData() {
        this.quadtreeData.setX(x);
        this.quadtreeData.setY(y);
        this.quadtreeData.setWidth(width);
        this.quadtreeData.setHeight(height);
    }

}
