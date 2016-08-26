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
    
    final Game g = game;
    float final gX = game.gameAreaResolutionX;
    float final gY = game.gameAreaResolutionY;
    float size = gX*0.03f;
    
    TextBox.TextBoxBuilder
        .game(game)
        .size(gX*0.03f);

    switch (levelNumber){
            case 1://1
            game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        TextBox.TextBoxBuilder.
                            .name("textoBox1")
                            .position(gX*0.2f, gY*0.2f)
                            .width(gX*0.5f)
                            .text(Utils.getStringResource(game, R.string.l1t1))
                            .withArrow(gX *0.5f, gY *0.9f)
                            .build();
                        )
                        .onShowBeforAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                    g.balls.get(0).clearDisplay();
                                    g.bars.get(0).isMovable = true;
                                }
                            }
                        )
                        .build();
                );
                
                TextBox l1tb2 = new TextBox("textBox2", game, 
                    gX*0.2f, gY*0.2f, 
                    game.resolutionX*0.5f, size, 
                    game.context.getResources().getString(R.string.l1t2));
                Tutorial l1t2 = new Tutorial(l1tb2);
                l1t2.setOnShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                    @Override
                    public void onShowBeforeAnim() {
                        innerGame.balls.get(0).display();
                        innerGame.bars.get(0).isMovable = false;
                        innerGame.bars.get(0).returnToInitialPosition();
                    }
                });
                game.levelObject.tutorials.add(l1t2);
                break;
                
            case 2:

                break;
                
        }
    }
}
