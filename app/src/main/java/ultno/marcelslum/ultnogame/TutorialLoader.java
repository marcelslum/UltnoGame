package ultno.marcelslum.ultnogame;

/**
 * Created by marcel on 02/08/2016.
 */
public class TutorialLoader {
    private static TutorialLoader ourInstance = new TutorialLoader();

    public static TutorialLoader getInstance() {
        return ourInstance;
    }

    private TutorialLoader() {
    }

    public static void loadTutorial(Game game, int levelNumber){
    
    switch (levelNumber){
            case 1:

                TextBox tb = new TextBox("textBox", game, 50f, 50f, game.resolutionX*0.6f, game.resolutionX*0.03f, "Atinja o alvo com a bola para destruir o alvo que desaparecerá após ser atingido!!!");
                game.levelObject.tutorials.add(new Tutorial(tb));
                break;
                
            case 2:

                break;
                
        }
    }
}
