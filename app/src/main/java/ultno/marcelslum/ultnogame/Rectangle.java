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
    float z = 0f;

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
        this.z = z;
        setDrawInfo();
    }

    public void setDrawInfo(){
        initializeData(12, 6, 0, 16);
        
        Utils.insertRectangleVerticesData(verticesData, 0,  0f, width, 0f, height, z);
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
            points.add(new Vector(width*scaleX, 0));
            points.add(new Vector(width*scaleX, height*scaleY));
            points.add(new Vector(0, height*scaleY));

            float difWidth = ((width*scaleX) - width)/2f;
            float difHeight = ((height*scaleY) - height)/2f;

            //Log.e("rectangle", "setSatData" + " difWidth "+difWidth + " difHeight "+difHeight);

            if (this.name == "obstacle") {
                Log.e("rectangle ", "setSatData " + (x - difWidth));
            }

            this.polygonData = new SatPolygon(new Vector(x - difWidth, y - difHeight), points);
        } else {
            this.polygonData.pos.x = x;
            this.polygonData.pos.y = y;
        }
    }

    @Override
    public float getMiddlePointX() {
        return width/2;
    }

    @Override
    public float getMiddlePointY() {
        return height/2;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void setScale(float sx, float sy){
        scaleX = sx;
        scaleY = sy;
        polygonData = null;
    }

    @Override
    public void updateQuatreeData() {

        float difWidth = (width*scaleX) - width;
        float difHeight = (height*scaleY) - height;



        this.quadtreeData.setX(x - difWidth/2f);
        this.quadtreeData.setY(y - difWidth/2f);
        this.quadtreeData.setWidth(width*scaleX);
        this.quadtreeData.setHeight(height*scaleY);
    }
}
