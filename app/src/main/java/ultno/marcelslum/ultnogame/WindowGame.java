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
        
        quantityX = Math.floor(game.gameAreaResolutionX / (size * (1 - distance))) + 1;
        
        
        
        
        
        
        for (int i = 0; i < quantityOfLines; i++){
            linesY[i] = i * (size * (1 - distance)); 
        }
    }
}
