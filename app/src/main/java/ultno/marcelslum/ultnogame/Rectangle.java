package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class Rectangle extends PhysicalObject {
    float width;
    float height;
    ScaleVariationData scaleVariationData;

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
            float transformedWidth = getTransformedWidth();
            float transformedHeight = getTransformedHeight();
            ArrayList<Vector> points = new ArrayList<Vector>();
            points.add(new Vector(0, 0));
            points.add(new Vector(transformedWidth, 0));
            points.add(new Vector(transformedWidth, transformedHeight));
            points.add(new Vector(0, transformedHeight));
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

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
    
    
    public void setScaleVariation(ScaleVariationDataBuilder builder){
        scaleVariationData = builder.build();
    }
    
    public void stopScaleVariation(){
        if (scaleVariationData != null) {
            scaleVariationData.isActive = false;
        }
    }

    public void initScaleVariation(){
        if (scaleVariationData != null) {
            scaleVariationData.isActive = true;
        }
    }
    

    @Override
    public void checkTransformations(boolean updatePrevious) {

        if (scaleVariationData != null){
            ScaleVariationData s = scaleVariationData;
            if (s.isActive){
                if (s.widthVelocity > 0f){
                    if (s.increaseWidth){
                        scaleX += s.widthVelocity;
                        if (accumulatedScaleX + scaleX > ((width*s.maxWidth_BI)/width)){
                            scaleX -= s.widthVelocity*2;
                            s.increaseWidth = false;
                        }
                    } else {
                        scaleX -= s.widthVelocity;
                        if (accumulatedScaleX + scaleX < ((width*s.minWidth_BI)/width)){
                            scaleX += s.widthVelocity*2;
                            s.increaseWidth = true;
                        }
                    }
                }

                if (s.heightVelocity > 0f){
                    if (s.increaseHeight){
                        scaleY += s.heightVelocity;
                        if (accumulatedScaleY + scaleY > ((height*s.maxHeight_BI)/height)){
                            scaleY -= s.heightVelocity*2;
                            s.increaseHeight = false;
                        }
                    } else {
                        scaleY -= s.heightVelocity;
                        if (accumulatedScaleY + scaleY < ((height*s.minHeight_BI)/height)){
                            scaleY += s.heightVelocity*2;
                            s.increaseHeight = true;
                        }
                    }
                }
            }
        }
        super.checkTransformations(updatePrevious);
    }

    @Override
    public void updateQuatreeData() {
        quadtreeData.setX(positionX);
        quadtreeData.setY(positionY);
        quadtreeData.setWidth(getTransformedWidth());
        quadtreeData.setHeight(getTransformedHeight());
    }
}
