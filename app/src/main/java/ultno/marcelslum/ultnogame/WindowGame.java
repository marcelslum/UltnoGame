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
    
    
    

    public WindowGame(String name, Game game, float x, float y, int quantityOfLines, float height, float distance, ) {
        super(name, game, x, y);
        this.quantityOfLines = quantityOfLines;
        this.distance = distance;
        this.height = height;
        
        
        this.size = height/(quantityOfLines - (distance * quantityOfLines) + distance);
        
        linesY = new float[quantityOfLines];
        for (int i = 0; i < quantityOfLines; i++){
            linesY[i] = i * (size * (1 - distance)); 
            
            
        }
        
    }
}
