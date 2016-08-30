package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 26/08/2016.
 */
public class WindowGame extends Entity{
    
    int quantityOfLines;
    float distance;
    float height;
    float size;
    float [] linesY;
    int quantityX;
    
    public WindowGame(String name, Game game, float x, float y, int quantityOfLines, float height, float distance, ) {
        super(name, game, x, y);
        this.quantityOfLines = quantityOfLines;
        this.distance = distance;
        this.height = height;
        
        size = height/(quantityOfLines - (distance * quantityOfLines) + distance);
        linesY = new float[quantityOfLines];
        
        quantityX = Math.floor((game.gameAreaResolutionX*1.5f) / (size * (1 - distance)))+1;
        
        for (int i = 0; i < quantityOfLines; i++){
            linesY[i] = i * (size * (1 - distance)); 
        }
        
        setDrawInfo();
    }
    
    public void setDrawInfo(){
        
        int quantityOfSquares = quantityX * quantityOfLines;
        initializeData(12*quantityOfSquares, 6*quantityOfSquares, 8*quantityOfSquares, 0);
        
        float xPosition = 0f;
        float yPosition = 0f

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
        
    }
}
