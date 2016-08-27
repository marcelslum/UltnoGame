package ultno.marcelslum.ultnogame;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

        final Timer timer = new Timer();
        final Game g = game;
        final float gX = game.gameAreaResolutionX;
        final float gY = game.gameAreaResolutionY;
        float size = gX*0.04f;
        float width = gX*0.7f;
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
                                    g.balls.get(0).isMovable = false;
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
                            .withArrow(gX *0.7f, gY *0.6f)
                            .build()
                    )
                   .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                        @Override
                        public void onShowBeforeAnim() {
                                g.balls.get(0).display();
                                g.balls.get(0).x = gX * 0.7f;
                                g.balls.get(0).y = gY * 0.7f;
                                g.bars.get(0).isMovable = false;
                                g.bars.get(0).x = g.levelObject.barsInitialXByResolution[0];
                            }
                        }
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                
                                Utils.createAnimation3v(g.balls.get(0), "translateX", "translateX", 2000,
                                    0f, 0f, 0.3f, gX * 0.2f, 1f, gX * 0.2f,  true, true).start();

                                Utils.createAnimation3v(g.balls.get(0), "translateY", "translateY", 2000,
                                    0f, 0f, 0.3f, gY * 0.285f, 1f, gY * 0.285f, true, true).start();
                                    
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
                                g.balls.get(0).resetAnimations();
                                // TODO xVermelho clearDisplay()
                            }
                        }
                    )
                    .build()
                );       
                // L1T3
                game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        new TextBox.TextBoxBuilder("textoBox3", g)
                            .position(x, y*2.5f)
                            .width(width)
                            .size(size)
                            .text(Utils.getStringResource(game, R.string.l1t3))
                            .withArrow(gX *0.59f, gY *0.15f)
                            .build()
                    )
                   .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                        @Override
                        public void onShowBeforeAnim() {
                                g.balls.get(0).x = gX * 0.4f;
                                g.balls.get(0).y = gY * 0.3f;
                            }
                        }
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                Utils.createAnimation3v(g.balls.get(0), "translateX", "translateX", 2000,
                                    0f, 0f, 0.3f, gX * 0.19f, 1f, gX * 0.19f, true, true).start();
                                Utils.createAnimation3v(g.balls.get(0), "translateY", "translateY", 2000,
                                    0f, 0f, 0.3f, -gY * 0.25f,  1f, -gY * 0.25f, true, true).start();
                                Utils.createAnimation3v(g.targets.get(5), "alpha", "alpha", 2000,
                                    0f, 1f, 0.35f, 1f, 0.5f, 0f, true, true).start();
                            }
                        }
                    )
                    .build()
                );
                
                // L1T4
                game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        new TextBox.TextBoxBuilder("textoBox4", g)
                            .position(x, y*2f)
                            .width(width)
                            .size(size)
                            .text(Utils.getStringResource(game, R.string.l1t4))
                            .withArrow(gX *0.49f, gY *0.95f)
                            .build()
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                g.balls.get(0).resetAnimations();
                                g.targets.get(5).resetAnimations();
                                g.targets.get(5).clearDisplay();
                                g.balls.get(0).x = gX * 0.59f;
                                g.balls.get(0).y = gY * 0.05f;
                                g.scorePanel.setValue(100, true, 500, false);
                            }
                        }
                    )
                    .build()
                );
                
                // L1T5
                game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        new TextBox.TextBoxBuilder("textoBox5", g)
                            .position(x, y*2f)
                            .width(width)
                            .size(size)
                            .text(Utils.getStringResource(game, R.string.l1t5))
                            .withArrow(gX *0.5f, gY *0.95f)
                            .build()
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                for (int i = 0; i < g.targets.size(); i++){
                                    if (i != 5){
                                        g.targets.get(i).reduceAlpha(500, 0f);
                                    }
                                }

                                g.balls.get(0).x = gX * 0.59f;
                                g.balls.get(0).y = gY * 0.05f;
                                g.scorePanel.setValue(900, true, 500, false);
                            }
                        }
                    )
                    .build()
                );
                
                // L1T6
                final TimerTask ttl1t6 = new TimerTask() {
                    @Override
                    public void run() {
                        g.scorePanel.setValue(g.scorePanel.value - 10, false, 1000, false);
                    };
                };

                game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        new TextBox.TextBoxBuilder("textoBox6", g)
                            .position(x, y*2f)
                            .width(width)
                            .size(size)
                            .text(Utils.getStringResource(game, R.string.l1t6))
                            .withArrow(gX *0.5f, gY *0.95f)
                            .build()
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                timer.scheduleAtFixedRate(ttl1t6, 0, 1000);
                            }
                        }
                    )
                    .build()
                );
                
                // L1T7
                game.levelObject.tutorials.add(
                    new Tutorial.TutorialBuilder(
                        new TextBox.TextBoxBuilder("textoBox7", g)
                            .position(x, y*2f)
                            .width(width)
                            .size(size)
                            .text(Utils.getStringResource(game, R.string.l1t7))
                            .withoutArrow()
                            .build()
                    )
                    .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                ttl1t6.cancel();
                                timer.cancel();
                                timer.purge();
                            }
                        }
                    )
                    .build()
                );
                break;
                
                case 2:
                // L2T1
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox1", g)
                                .position(x*0.5f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l2t1))
                                .withoutArrow()
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).isMovable = false;
                                    g.balls.get(0).x = gX * 0.3f;
                                    g.balls.get(0).y = gY * 0.72f;
                                    g.bars.get(0).x = gX * 0.56f;
                                }
                            }
                        )
                        .build()
                );
                
                // L2T2
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox2", g)
                                .position(x*0.25f, y*0.25f)
                                .width(width*1.25f)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l2t2))
                                .withoutArrow()
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).x = gX * 0.3f;
                                    g.balls.get(0).y = gY * 0.72f;
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                    Utils.createAnimation4v(g.balls.get(0), "translateX", "translateX", 3000,
                                        0f, 0f, 0.25f, gX*0.15f, 0.5f, gX*0.4f, 1f, gX*0.4f, true, true).start();
                                    Utils.createAnimation4v(g.balls.get(0), "translateY", "translateY", 3000,
                                        0f, 0f, 0.25f, gY*0.245f, 0.5f, -gY*0.3f, 1f, -gY*0.3f, true, true).start();
                                    Utils.createAnimation3v(g.bars.get(0), "translateX", "translateX", 3000,
                                        0f, 0f, 0.5f, -gX*0.4f, 1f, -gX*0.4f, true, true).start();
                                }
                            }
                        )
                        .build()
                );
                
                // L2T3
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox3", g)
                                .position(x*0.25f, y*0.25f)
                                .width(width*1.25f)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l2t3))
                                .withoutArrow()
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    g.balls.get(0).resetAnimations();
                                    g.bars.get(0).resetAnimations();
                                    g.balls.get(0).x = gX * 0.3f;
                                    g.balls.get(0).y = gY * 0.72f;
                                    g.bars.get(0).x = gX * 0.1f;
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                Utils.createAnimation4v(g.balls.get(0), "translateX", "translateX", 3000,
                                    0f, 0f, 0.25f, gX*0.15f, 0.5f, gX*0.2f, 1f, gX*0.2f, true, true).start();
                                Utils.createAnimation4v(g.balls.get(0), "translateY", "translateY", 3000,
                                    0f, 0f, 0.25f, gY*0.245f, 0.5f, gY*0.15f, 1f, gY*0.15f, true, true).start();
                                Utils.createAnimation3v(g.bars.get(0), "translateX", "translateX", 3000,
                                    0f, 0f, 0.5f, gX*0.4f, 1f, gX*0.4f, true, true).start();
                                }
                            }
                        )
                        .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                            @Override
                            public void onUnshowBeforeAnim() {
                                g.balls.get(0).resetAnimations();
                                g.bars.get(0).resetAnimations();
                                }
                            }
                        )
                        .build()
                );
                
                // L2T4
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox4", g)
                                .position(x*0.25f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l2t4))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );
                break;
                
                case 4:
                // L4T1
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textBox1", g)
                                .position(x*0.1f, y*0.1f)
                                .width(width*1.3f)
                                .size(size)
                                    .text(Utils.getStringResource(game, R.string.l4t1))
                                .withArrow(gX*0.5f, gY*0.35f)
                                .build()
                        )
                        .build()
                );
                break;
                
                case 7:
                // L7T1
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textBox1", g)
                                .position(x*0.5f, y*2f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l7t1))
                                .withArrow(gX*0.5f, gY*0.08f)
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                g.balls.get(0).clearDisplay();
                                g.targets.get(4).setUvInfo(Target.TARGET_GREEN);
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                    Utils.createAnimation3v(g.targets.get(4), "alpha", "alpha", 3000,
                                        0f, 1f, 0.75f, 0.99f, 1f, 0, true, true).start(); 
                                    ArrayList<float[]> valuesAnimTarget = new ArrayList<>();
                                        valuesAnimTarget.add(new float[]{0f,3f});
                                        valuesAnimTarget.add(new float[]{0.25f,2f});
                                        valuesAnimTarget.add(new float[]{0.5f,1f});
                                        valuesAnimTarget.add(new float[]{0.75f,0f});
                                    Animation animTarget = new Animation(g.targets.get(4), "animTarget", "numberForAnimation", 3000, 
                                        valuesAnimTarget, true, false);
                                        animTarget.setOnChangeNotFluid(new Animation.OnChange() {
                                            @Override
                                            public void onChange() {
                                                if (g.targets.get(4).numberForAnimation == 3f){
                                                    g.targets.get(4).setUvInfo(Target.TARGET_GREEN);
                                                } else if (g.targets.get(4).numberForAnimation == 2f) {
                                                    g.targets.get(4).setUvInfo(Target.TARGET_BLUE);
                                                } else if (g.targets.get(4).numberForAnimation == 1f) {
                                                    g.targets.get(4).setUvInfo(Target.TARGET_BLACK);
                                                }
                                            }
                                        });
                                        animTarget.start();
                                    }
                                }
                            )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                  @Override
                                  public void onShowBeforeAnim() {
                                      g.targets.get(4).resetAnimations();
                                      g.targets.get(4).alpha = 1f;
                                      g.targets.get(4).setUvInfo(Target.TARGET_BLACK);
                                  }
                              }
                        )
                        .build()
                );
                break;
                
                case 9:
                // L9T1
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox1", g)
                                .position(x*0.5f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l9t1))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );
                
                // L9T2
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox2", g)
                                .position(x*0.5f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l9t2))
                                .withArrow(gX*0.5f, gY*0.25f)
                                .build()
                        )
                        .build()
                );
                
                // L9T3
                game.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBox.TextBoxBuilder("textoBox3", g)
                                .position(x*0.5f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(game, R.string.l9t3))
                                .withoutArrow()
                                .build()
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                 @Override
                                 public void onShowAfterAnim() {
                                     float posX = gX * 0.6f;
                                     float posY = gY * 0.28f;

                                     final Ball ball1 = g.balls.get(0);
                                     ball1.x = posX;
                                     ball1.y = posY;

                                     float size = ball1.radius;

                                     final Ball ball2 = new Ball("ball2", g, posX - size * 2, posY - size * 2, size, 8);
                                     ball2.program = g.imageProgram;
                                     ball2.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
                                     ball2.setTextureMapAndUvData(Ball.COLOR_BALL_BLUE);
                                     ball2.isCollidable = false;
                                     ball2.alpha = 0f;
                                     ball2.display();

                                     final Ball ball3 = new Ball("ball2", g, posX + size * 2, posY - size * 2, size, 8);
                                     ball3.program = g.imageProgram;
                                     ball3.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
                                     ball3.setTextureMapAndUvData(Ball.COLOR_BALL_ORANGE);
                                     ball3.isCollidable = false;
                                     ball3.alpha = 0f;
                                     ball3.display();

                                     final Ball ball4 = new Ball("ball2", g, posX - size * 2, posY + size * 2, size, 8);
                                     ball4.program = g.imageProgram;
                                     ball4.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
                                     ball4.setTextureMapAndUvData(Ball.COLOR_BALL_RED);
                                     ball4.isCollidable = false;
                                     ball4.alpha = 0f;
                                     ball4.display();

                                     final Ball ball5 = new Ball("ball2", g, posX + size * 2, posY + size * 2, size, 8);
                                     ball5.program = g.imageProgram;
                                     ball5.textureUnit = Game.TEXTURE_BUTTONS_AND_BALLS;
                                     ball5.setTextureMapAndUvData(Ball.COLOR_BALL_PINK);
                                     ball5.isCollidable = false;
                                     ball5.alpha = 0f;
                                     ball5.display();

                                     g.addBall(ball2);
                                     g.addBall(ball3);
                                     g.addBall(ball4);
                                     g.addBall(ball5);

                                     Utils.createAnimation2v(ball1, "translateX", "translateX", 5000,
                                             0f, -gX * 0.15f, 0.2f, 0f, true, true).start();

                                     Utils.createAnimation2v(ball1, "translateY", "translateY", 5000,
                                             0f, -gY * 0.1f, 0.2f, 0f, true, true).start();

                                     Utils.createAnimation4v(ball1, "alpha", "alpha", 5000,
                                             0f, 1f, 0.2f, 1f, 0.25f, 0f, 1f, 0f, true, true).start();

                                     ArrayList<float[]> valuesAnimBall1 = new ArrayList<>();
                                     valuesAnimBall1.add(new float[]{0f, 1f});
                                     valuesAnimBall1.add(new float[]{0.1f, 0f});
                                     Animation animBall1 = new Animation(ball1, "color", "numberForAnimation", 3000,
                                             valuesAnimBall1, true, false);
                                     animBall1.setOnChangeNotFluid(new Animation.OnChange() {
                                         @Override
                                         public void onChange() {
                                             if (ball1.numberForAnimation == 1f) {
                                                 ball1.setTextureMapAndUvData(Ball.COLOR_BALL_BLACK);
                                             } else if (ball1.numberForAnimation == 0f) {
                                                 ball1.setTextureMapAndUvData(Ball.COLOR_BALL_RED);
                                             }
                                         }
                                     });
                                     animBall1.start();

                                     Utils.createAnimation4v(ball2, "alpha", "alpha", 5000,
                                             0f, 0f, 0.23f, 0f, 0.3f, 1f, 1f, 1f, true, true).start();
                                     Utils.createAnimation4v(ball3, "alpha", "alpha", 5000,
                                             0f, 0f, 0.23f, 0f, 0.3f, 1f, 1f, 1f, true, true).start();
                                     Utils.createAnimation4v(ball4, "alpha", "alpha", 5000,
                                             0f, 0f, 0.23f, 0f, 0.3f, 1f, 1f, 1f, true, true).start();
                                     Utils.createAnimation4v(ball5, "alpha", "alpha", 5000,
                                             0f, 0f, 0.23f, 0f, 0.3f, 1f, 1f, 1f, true, true).start();

                                     float desloc = ball1.radius * 4f;

                                     Utils.createAnimation3v(ball2, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, true, true).start();
                                     Utils.createAnimation3v(ball3, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, true, true).start();
                                     Utils.createAnimation3v(ball4, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, true, true).start();
                                     Utils.createAnimation3v(ball5, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, true, true).start();

                                     Utils.createAnimation3v(ball2, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, true, true).start();
                                     Utils.createAnimation3v(ball3, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, true, true).start();
                                     Utils.createAnimation3v(ball4, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, true, true).start();
                                     Utils.createAnimation3v(ball5, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, true, true).start();

                                     Utils.createAnimation4v(g.targets.get(35), "alpha", "alpha", 5000,
                                             0f, 1f, 0.1f, 1f, 0.2f, 0f, 1f, 0f, true, true).start();

                                 }
                             }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                    @Override
                                    public void onShowAfterAnim() {
                                    g.balls.get(0).resetAnimations();
                                    g.balls.get(1).resetAnimations();   
                                    g.balls.get(2).resetAnimations();   
                                    g.balls.get(3).resetAnimations();
                                    g.balls.get(4).resetAnimations();
                                        
                                    g.balls.get(0).clearDisplay();
                                    g.targets.get(35).resetAnimations();
                                    g.targets.get(35).alpha = 0f;
                                    
                                    float desloc = g.balls.get(1).radius * 4;
                                    g.balls.get(1).translate(-desloc, -desloc, false);
                                    g.balls.get(2).translate(desloc, -desloc, false);
                                    g.balls.get(3).translate(-desloc, desloc, false);
                                    g.balls.get(4).translate(desloc, desloc, false);
                                }
                            }
                        )
                        .build()
                );
                break;
        }
    }
}
