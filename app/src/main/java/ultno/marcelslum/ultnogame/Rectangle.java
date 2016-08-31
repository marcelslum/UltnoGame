package ultno.marcelslum.ultnogame;


import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Rectangle extends PhysicalObject {
    float width;
    float height;

    Rectangle(String name, Game game, float x, float y, float width, float height, int weight, Color color){
        super(name, game, x, y, weight);
        this.program = game.solidProgram;
        this.color = color;
        this.width = width;
        this.height = height;
        setDrawInfo();
    }

    Rectangle(String name, Game game, float x, float y, float width, float height, float z, int weight, Color color){
        super(name, game, x, y, weight);
        this.program = game.solidProgram;
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
            float width = getTransformedWidth();
            float height = getTransformedHeight();
            ArrayList<Vector> points = new ArrayList<Vector>();
            points.add(new Vector(0, 0));
            points.add(new Vector(width, 0));
            points.add(new Vector(width, height));
            points.add(new Vector(0, height));
            polygonData = new SatPolygon(new Vector(positionX, positionY), points);
            
        } else {
            polygonData.pos.x = positionX;
            polygonData.pos.y = positionY;
        }
    }

    @Override
    public float getTransformedWidth() {
        return width * accumulatedScaleX;
    }

    @Override
    public float getTransformedHeight() {
        return height * accumulatedScaleY;
    }

    @Override
    public void updateQuatreeData() {
        quadtreeData.setX(positionX);
        quadtreeData.setY(positionY);
        quadtreeData.setWidth(getTransformedWidth());
        quadtreeData.setHeight(getTransformedHeight());
    }
}
