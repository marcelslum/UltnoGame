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
        final float gX = game.gameAreaResolutionX;
        final float gY = game.gameAreaResolutionY;
        float size = gX*0.03f;
        float width = gX*0.6f;
        float x = gX*0.2f;
        float y = gY*0.2f;

        switch (levelNumber){
            case 1:
                // L1T1
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox1", g)
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l1t1))
                                .withArrow(gX *0.4f, gY *0.9f)
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).clearDisplay();
                                    g.bars.get(0).isMovable = true;
                                }
                            }
                        )
                        .build()
                );
                
                // L1T2
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox2", g)
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l1t2))
                                .withArrow(gX *0.7f, gY *0.85f)
                                .build()
                        )
                       .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).display();
                                    g.balls.get(0).x = gX * 0.7f;
                                    g.balls.get(0).y = gY * 0.85f;
                                    g.bars.get(0).isMovable = false;
                                    g.bars.get(0).x = g.levelObject.barsInitialPositionX.get(0);
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    ArrayList<float[]> values = new ArrayList<>();
                                        values.add(new float[]{0f,0f});
                                        values.add(new float[]{0.3f,gX * 0.15f});
                                    new Animation(g.balls.get(0), "translateX", 
                                        "translateX", 2000, values, true, false).start();
                                    
                                    ArrayList<float[]> values = new ArrayList<>();
                                        values.add(new float[]{0f,0f});
                                        values.add(new float[]{0.3f,gX * 0.14f});
                                    new Animation(g.balls.get(0), "translateY", 
                                        "translateY", 2000, values, true, false).start();
                                        
                                    // TODO xVermelho
                                    /*
                                        xVermelho.position = V(self.gameArea.resolution.x * 0.75, self.gameArea.resolution.y * 0.85);
                                        xVermelho.size= V(self.gameArea.resolution.x * 0.06, self.gameArea.resolution.y * 0.09);
                                        xVermelho.alpha = 0;
                                        xVermelho.display();
                                        ANIM.createAnimation(xVermelho, 'animXVermelho', 'alpha', 1500, [[0,0],[0.5,0.4],[1,0]], true, true, 0).start();
                                    */
                                    
                                }
                            }
                        )
                        .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                            @Override
                            public void onUnshowBeforeAnim() {
                                    g.resetAllAnimations();
                                    // TODO xVermelho clearDisplay()
                                }
                            }
                        )
                        .build()
                        
                // L1T3
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox3", g)
                                .position(x, y*2.5f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l1t2))
                                .withArrow(gX *0.5f, gY *0.1f)
                                .build()
                        )
                       .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).x = gX * 0.4f;
                                    g.balls.get(0).y = gY * 0.15f;
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    createAnimation2v(g.balls.get(0), "translateX", "translateX", 2000, 
                                        0f, 0f, 0.3f, gX * 0.15f, true, true).start();
                                    createAnimation2v(g.balls.get(0), "translateY", "translateY", 2000, 
                                        0f, 0f, 0.3f, gY * 0.14f, true, true).start();
                                }
                            }
                        )
                        .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                            @Override
                            public void onUnshowBeforeAnim() {
                                    g.resetAllAnimations();
                                    // TODO xVermelho clearDisplay()
                                }
                            }
                        )
                        .build()
                        
                );
                
                break;

                /*
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
                */
                
        }
    }
}
