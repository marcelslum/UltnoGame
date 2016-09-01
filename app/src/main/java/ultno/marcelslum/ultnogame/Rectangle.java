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
    boolean changeSize = false;
    boolean changeSizeStarted = false;
    boolean increaseSizeX;
    boolean increaseSizeY;
    float maxSizeByInitial;
    float minSizeByInitial;
    private float sizeVariationVelocityX;
    private float sizeVariationVelocityY;

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
    
    
    public void setSizeVariation(float sizeVariationVelocityX, float sizeVariationVelocityY, boolean increaseSizeX, boolean increaseSizeY, float minSizeByInitial, float maxSizeByInitial){
        changeSize = true;
        changeSizeStarted = false;
        this.sizeVariationVelocityX = sizeVariationVelocityX;
        this.sizeVariationVelocityY = sizeVariationVelocityY;
        this.increaseSizeX = increaseSizeX;
        this.increaseSizeY = increaseSizeY;
        this.minSizeByInitial = minSizeByInitial;
        this.maxSizeByInitial = maxSizeByInitial;
    }
    
    public void stopSizeVariation(){
        changeSizeStarted = false;
    }
    public void initSizeVariation(){
        changeSizeStarted = true;
    }
    

    @Override
    public void checkTransformations(boolean updatePrevious) {
        if (changeSize == true && changeSizeStarted){
            if (sizeVariationVelocityX > 0f){
                if (increaseSizeX){
                    scaleX += sizeVariationVelocityX;
                    if (accumulatedScaleX + scaleX > ((width*maxSizeByInitial)/width)){
                        scaleX -= sizeVariationVelocityX*2;
                        increaseSizeX = false;
                    }
                } else {
                    scaleX -= sizeVariationVelocityX;
                    if (accumulatedScaleX + scaleX < ((width*minSizeByInitial)/width)){
                        scaleX += sizeVariationVelocityX*2;
                        increaseSizeX = true;
                    }
                }
            }

            if (sizeVariationVelocityY > 0f){
                if (increaseSizeY){
                    scaleY += sizeVariationVelocityY;
                    if (accumulatedScaleY + scaleY > ((height*maxSizeByInitial)/height)){
                        scaleY -= sizeVariationVelocityY*2;
                        increaseSizeY = false;
                    }
                } else {
                    scaleY -= sizeVariationVelocityY;
                    if (accumulatedScaleY + scaleY > ((height*minSizeByInitial)/height)){
                        scaleY += sizeVariationVelocityY*2;
                        increaseSizeY = true;
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
