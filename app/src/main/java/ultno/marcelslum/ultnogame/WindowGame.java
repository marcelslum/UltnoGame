package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 26/08/2016.
 */
public class WindowGame extends PhysicalObject{
    
    int quantityOfLines;
    float distance;
    float height;
    float size;
    float [] linesY;
    int quantityX;
    float maxDistance;
    
    
    public WindowGame(String name, Game game, float x, float y, int quantityOfLines, float height, float distance, float velocity) {
        super(name, game, x, y, 0);
        this.quantityOfLines = quantityOfLines;
        this.distance = distance;
        this.height = height;
        this.dvx = velocity;
        this.vx = velocity;
        isCollidable = false;
        isSolid = false;
        isMovable = true;
        
        
        size = height/(quantityOfLines - (distance * quantityOfLines) + distance);
        linesY = new float[quantityOfLines];
        
        quantityX = Math.floor((game.gameAreaResolutionX*1.5f) / (size * (1 - distance)))+1;
        
        for (int i = 0; i < quantityOfLines; i++){
            linesY[i] = i * (size * (1 - distance)); 
        }
        
        maxDistance = (size * (1f-distance))*4f);
        
        if (velocity >= 0){
            x = -maxDistance;
        } else {
            x = 0f;
        }
        
        setDrawInfo();
    }
    
    public void setDrawInfo(){
        int quantityOfSquares = quantityX * quantityOfLines;
        initializeData(12*quantityOfSquares, 6*quantityOfSquares, 8*quantityOfSquares, 0);
        
        float xPosition = 0f;
        float yPosition = 0f;

        float distanceToDraw = size * (1-distance);
        
        for (int iX = 0; iX < quantityX, iX++){
            for (int iY = 0; iY < quantityOfLines, iY++){
                xPosition = iX * distanceToDraw;
                yPosition = iY * distanceToDraw;
                if (iY%2 == 0){ // se é par
                    if (iY%4 == 0){
                        if (iX%2 != 0){
                                Utils.insertRectangleVerticesData(this.verticesData, 0 + (numberOfTriangles * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                        }
                    } else {
                        if (iX%2 == 0){
                                Utils.insertRectangleVerticesData(this.verticesData, 0 + (numberOfTriangles * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                        }
                    }
                }
            }
        }
        
        for (int iX = 0; iX < quantityX, iX++){
            for (int iY = 0; iY < quantityOfLines, iY++){
                xPosition = iX * distanceToDraw;
                yPosition = (iY - 1) * distanceToDraw;
                if (iY%2 != 0){
                    Utils.insertRectangleVerticesData(this.verticesData, 0 + (numberOfTriangles * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                }
            }
        }
        
        for (int iX = 0; iX < quantityX, iX++){
            for (int iY = 0; iY < quantityOfLines, iY++){
                xPosition = iX * distanceToDraw;
                yPosition = iY * distanceToDraw;
                if (iY%2 == 0){ // se é par
                    if (iY%4 == 0){
                        if (iX%2 == 0){
                                Utils.insertRectangleVerticesData(this.verticesData, 0 + (numberOfTriangles * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                        }
                    } else {
                        if (iX%2 != 0){
                                Utils.insertRectangleVerticesData(this.verticesData, 0 + (numberOfTriangles * 12), xPosition, xPosition+size, yPosition, yPosition + size, 0f);
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < quantityOfSquares; i++){
            Utils.insertRectangleIndicesData(this.indicesData, 0 + (i * 6), 0 + (i * 4));
            Utils.insertRectangleUvDataNumbersExplosion(this.uvsData, 0 + (i * 8), 30);
        }
        
        this.verticesBuffer = Utils.generateFloatBuffer(this.verticesData);
        this.indicesBuffer = Utils.generateShortBuffer(this.indicesData);
        this.uvsBuffer = Utils.generateFloatBuffer(this.uvsData);
    }
    
    public void move(){
        if (isMovable){
            x += vx;
            
            if (vx > 0){
                if (x > 0){
                    x = x - maxDistance;
                }
            } else {
                if (x < maxDistance){
                    x = 0 - x + maxDistance;
                }
            }
        }
    }
}
