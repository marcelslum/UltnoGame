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
    
    Game innerGame = game;

    switch (levelNumber){
            case 1:
                TextBox l1tb1 = new TextBox("textBox", game, 50f, 50f, game.resolutionX*0.6f, game.resolutionX*0.03f, game.context.getResources().getString(R.string.l1t1));
                Tutorial l1t1 = new Tutorial(l1tb1);
                l1t1.setOnShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                    @Override
                    public void onShowBeforeAnim() {
                        innerGame.balls.get(0).clearDisplay();
                        innerGame.bars.get(0).isMovable = true;
                    }
                });
                game.levelObject.tutorials.add(l1t1);
                
                TextBox l1tb2 = new TextBox("textBox", game, 50f, 50f, game.resolutionX*0.6f, game.resolutionX*0.03f, game.context.getResources().getString(R.string.l1t2));
                Tutorial l1t2 = new Tutorial(l1tb2);
                l1t2.setOnShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                    @Override
                    public void onShowBeforeAnim() {
                        innerGame.balls.get(0).display();
                        innerGame.bars.get(0).isMovable = false;
                        innerGame.bars.get(0).x = innerGame.levelObject.barsInitialX[0]];
                        innerGame.bars.get(0).y = innerGame.levelObject.barsInitialY[0]];
                    }
                });
                game.levelObject.tutorials.add(l1t2);
                
                
                
                
                break;
                
            case 2:

                break;
                
        }
    }
}
