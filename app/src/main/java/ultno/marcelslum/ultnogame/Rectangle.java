package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

public class Rectangle extends PhysicalObject {
    public float width;
    public float height;
    public ScaleVariationData scaleVariationData;
    public PositionVariationData positionVariationData;

    Rectangle(String name, float x, float y, float width, float height, int weight, Color color){
        super(name, x, y, weight);
        this.program = Game.solidProgram;
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
            ArrayList<Vector> points = new ArrayList<>();
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

    public void setPositionVariation(PositionVariationDataBuilder builder){
        positionVariationData = builder.build();
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

    public void stopPositionVariation(){
        if (positionVariationData != null) {
            positionVariationData.isActive = false;
        }
    }

    public void initPositionVariation(){
        if (positionVariationData != null) {
            positionVariationData.isActive = true;
        }
    }
    

    @Override
    public void checkTransformations(boolean updatePrevious) {

        if (positionVariationData != null){
            PositionVariationData p = positionVariationData;
            if (p.isActive){
                if (p.xVelocity > 0f){
                    if (p.increaseX){
                        translateX += p.xVelocity * Game.gameAreaResolutionX;
                        if ((x + accumulatedTranslateX + translateX + getTransformedWidth())>(p.maxX*Game.gameAreaResolutionX)){
                            translateX -= p.xVelocity * Game.gameAreaResolutionX * 2;    
                            p.increaseX = false;
                        }
                    } else {
                        translateX -= p.xVelocity * Game.gameAreaResolutionX;
                        if ((x + accumulatedTranslateX + translateX)<(p.minX*Game.gameAreaResolutionX)){
                            translateX += p.xVelocity * Game.gameAreaResolutionX * 2;
                            p.increaseX = true;
                        }
                    }
                }

                if (p.yVelocity > 0f){
                    if (p.increaseY){
                        translateY += p.yVelocity * Game.gameAreaResolutionY;
                        if ((y + accumulatedTranslateY + translateY + getTransformedHeight())>(p.maxY*Game.gameAreaResolutionY)){
                            translateY -= p.yVelocity * Game.gameAreaResolutionY * 2;
                            p.increaseY = false;
                        }
                    } else {
                        translateY -= p.yVelocity * Game.gameAreaResolutionY;
                        if ((y + accumulatedTranslateY + translateY)<(p.minY*Game.gameAreaResolutionY)){
                            translateY += p.yVelocity * Game.gameAreaResolutionY * 2;
                            p.increaseY = true;
                        }
                    }
                }
            }
        }
        

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

    public void respondToCollision(float responseX, float responseY){
        //Log.e("physical", "respond to collision " +responseX);
        PositionVariationData p;
        ScaleVariationData s;

        boolean decreaseX = false;
        boolean increaseX = false;
        boolean decreaseY = false;
        boolean increaseY = false;

        if (positionVariationData != null){
            p = positionVariationData;
            if (p.isActive) {
                if (responseX < 0f && p.increaseX){
                    decreaseX = true;
                } else if (responseX > 0f && !p.increaseX){
                    increaseX = true;
                }
                if (responseY < 0f && p.increaseY){
                    decreaseY = true;
                } else if (responseY > 0f && !p.increaseY){
                    increaseY = true;
                }
            }
        }

        if (positionVariationData != null) {
            p = positionVariationData;
            if (decreaseX) {
                positionX -= p.xVelocity * Game.gameAreaResolutionX;
                p.increaseX = false;
            }
            if (increaseX) {
                positionX += p.xVelocity * Game.gameAreaResolutionX;
                p.increaseX = true;
            }
            if (decreaseY) {
                positionY -= p.yVelocity * Game.gameAreaResolutionY;
                p.increaseY = false;
            }
            if (increaseY) {
                positionY += p.yVelocity * Game.gameAreaResolutionY;
                p.increaseY = true;
            }
        }

        if (scaleVariationData != null) {
            s = scaleVariationData;
            if (s.isActive) {
                if (responseX != 0f){
                    scaleX -= Math.abs(responseX) / getTransformedWidth();
                    s.increaseWidth = false;
                }

                if (responseY != 0f){
                    scaleY -= Math.abs(responseY) / getTransformedHeight();
                    s.increaseHeight = false;
                }
            } else {
                this.accumulatedTranslateX += responseX;
                this.accumulatedTranslateY += responseY;
            }
        } else {
            this.accumulatedTranslateX += responseX;
            this.accumulatedTranslateY += responseY;
        }

        this.checkTransformations(false);
    }
}
