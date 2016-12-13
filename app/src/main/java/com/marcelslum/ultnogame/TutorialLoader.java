package com.marcelslum.ultnogame;

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

    public static void loadTutorial(int levelNumber){

        final Timer timer = new Timer();
        final float gX = Game.gameAreaResolutionX;
        final float gY = Game.gameAreaResolutionY;
        float size = gX*0.035f;
        float width = gX*0.78f;
        float x = gX*0.1f;
        float y = gY*0.2f;

        switch (levelNumber){
            case 1:
                    // L1T1
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                    .position(x, y)
                                    .width(width)
                                    .size(size)
                                    .text(Utils.getStringResource( R.string.l1t1))
                                    .withArrow(gX *0.4f, gY *0.9f)
                                    .build()
                            )
                            .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                        Game.balls.get(0).clearDisplay();
                                        Game.balls.get(0).isMovable = false;
                                        Game.bars.get(0).isMovable = true;

                                    }
                                }
                            )
                            .build()
                    );

                    // L1T2
                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox2")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l1t2))
                                .withArrow(gX *0.65f, gY *0.62f)
                                .build()
                        )
                       .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    Game.balls.get(0).display();
                                    Game.balls.get(0).x = gX * 0.7f;
                                    Game.balls.get(0).y = gY * 0.7f;
                                    Game.bars.get(0).clearDisplay();
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {

                                    Utils.createAnimation3v(Game.balls.get(0), "translateX", "translateX", 2000,
                                        0f, 0f, 0.3f, gX * 0.2f, 1f, gX * 0.2f,  true, true).start();

                                    Utils.createAnimation3v(Game.balls.get(0), "translateY", "translateY", 2000,
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
                                    Game.balls.get(0).clearAnimations();
                                    // TODO xVermelho clearDisplay()
                                }
                            }
                        )
                        .build()
                    );
                    // L1T3
                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox3")
                                .position(x, y*2.5f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l1t3))
                                .withArrow(gX *0.59f, gY *0.15f)
                                .build()
                        )
                       .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    Game.balls.get(0).x = gX * 0.4f;
                                    Game.balls.get(0).y = gY * 0.3f;
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    Utils.createAnimation3v(Game.balls.get(0), "translateX", "translateX", 2000,
                                        0f, 0f, 0.3f, gX * 0.19f, 1f, gX * 0.19f, true, true).start();
                                    Utils.createAnimation3v(Game.balls.get(0), "translateY", "translateY", 2000,
                                        0f, 0f, 0.3f, -gY * 0.25f,  1f, -gY * 0.25f, true, true).start();
                                    Utils.createAnimation3v(Game.targets.get(5), "alpha", "alpha", 2000,
                                        0f, 1f, 0.35f, 1f, 0.5f, 0f, true, true).start();
                                }
                            }
                        )
                        .build()
                    );

                    // L1T4
                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox4")
                                .position(x, y*1.5f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l1t4))
                                .build()
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    Game.balls.get(0).clearAnimations();
                                    Game.targets.get(5).clearAnimations();
                                    Game.targets.get(5).clearDisplay();
                                    Game.balls.get(0).x = gX * 0.59f;
                                    Game.balls.get(0).y = gY * 0.05f;
                                    Game.scorePanel.setValue(100, true, 500, false);
                                }
                            }
                        )
                        .build()
                    );

                    // L1T5
                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox5")
                                .position(x, y*2f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l1t5))
                                .build()
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    for (int i = 0; i < Game.targets.size(); i++){
                                        if (i != 5){
                                            Game.targets.get(i).reduceAlpha(500, 0f);
                                        }
                                    }
                                    Game.balls.get(0).x = gX * 0.59f;
                                    Game.balls.get(0).y = gY * 0.05f;
                                    Game.scorePanel.setValue(900, true, 500, false);
                                }
                            }
                        )
                        .build()
                    );

                    // L1T6
                    final TimerTask ttl1t6 = new TimerTask() {
                        @Override
                        public void run() {
                            Game.scorePanel.setValue(Game.scorePanel.value - 10, false, 1000, false);
                        }
                    };

                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox6")
                                .position(x, y*2f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l1t6))
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
                    Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox7")
                                .position(x, y*2f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l1t7))
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
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                    .position(x, y)
                                    .width(width)
                                    .size(size)
                                    .text(Utils.getStringResource( R.string.l2t1))
                                    .withoutArrow()
                                    .build()
                            )
                            .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                        Game.bars.get(0).isMovable = false;
                                        Game.balls.get(0).isMovable = false;
                                        Game.balls.get(0).x = gX * 0.3f;
                                        Game.balls.get(0).y = gY * 0.72f;
                                        Game.bars.get(0).x = gX * 0.62f;
                                    }
                                }
                            )
                            .build()
                    );

                    // L2T2
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox2")
                                    .position(x, y)
                                    .width(width)
                                    .size(size)
                                    .text(Utils.getStringResource( R.string.l2t2))
                                    .withoutArrow()
                                    .build()
                            )
                            .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                        Game.balls.get(0).x = gX * 0.3f;
                                        Game.balls.get(0).y = gY * 0.73f;
                                    }
                                }
                            )
                            .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {

                                        Utils.createAnimation4v(Game.balls.get(0), "translateX", "translateX", 3000,
                                            0f, 0f, 0.25f, gX*0.15f, 0.5f, gX*0.6f, 1f, gX*0.6f, true, true).start();
                                        Utils.createAnimation4v(Game.balls.get(0), "translateY", "translateY", 3000,
                                            0f, 0f, 0.25f, gY*0.244f, 0.5f, -gY*0.32f, 1f, -gY*0.32f, true, true).start();
                                        Utils.createAnimation3v(Game.bars.get(0), "translateX", "translateX", 3000,
                                            0f, 0f, 0.5f, -gX*0.5f, 1f, -gX*0.5f, true, true).start();

                                        Game.ballDataPanel.clearDisplay();
                                        Animation animForPanel = Utils.createAnimation3v(Game.balls.get(0), "numberForAnimation", "numberForAnimation", 3000,
                                                0f, 1f, 0.25f, 2f, 1f, 3f, true, false);
                                        animForPanel.setOnChangeNotFluid(new Animation.OnChange() {
                                            @Override
                                            public void onChange() {
                                                if (Game.balls.get(0).numberForAnimation == 1f){
                                                    Game.ballDataPanel.setData(0.5f, 0f, false);
                                                } else if (Game.balls.get(0).numberForAnimation == 2f){
                                                    Game.ballDataPanel.setData(0.6f, 0f, true);
                                                }
                                            }
                                        });
                                        animForPanel.start();
                                    }

                                }
                            )
                            .build()
                    );

                    // L2T3
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textBox3")
                                            .position(x*0.5f, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource(R.string.l2t3))
                                            .withoutArrow()
                                            .build()
                            ).build()
                    );

                    // L2T4
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textBox4")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource( R.string.l2t4))
                                            .withArrow(gX *0.5f, gY *0.95f)
                                            .build()
                            )

                            .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {
                                    Game.ballDataPanel.display();
                                    Utils.createAnimation3v(Game.ballDataPanel, "alpha", "alpha", 500, 0f, 1f, 0.5f, 0.1f, 1f, 1f, true, true).start();
                                }
                            })
                            .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                                @Override
                                public void onUnshowBeforeAnim() {
                                    Game.ballDataPanel.clearAnimations();
                                }
                            })
                            .build()
                    );

                    // L2T5
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textBox5")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource( R.string.l2t5))
                                            .withoutArrow()
                                            .build()
                            )

                            .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                @Override
                                public void onShowAfterAnim() {

                                    // desliga a animação do painel em relação à bola
                                    Game.balls.get(0).getAnimationByName("numberForAnimation").setOnChangeNotFluid(new Animation.OnChange() {
                                        @Override
                                        public void onChange() {

                                        }
                                    });
                                }
                            })
                            .build()
                    );

                    // L2T6
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textoBox6")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource(R.string.l2t6))
                                            .withArrow(gX *0.5f, gY *0.95f)
                                            .build()
                            )
                            .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                    // cria uma animação que mostra o máximo e o mínimo
                                    Animation animPanel = Utils.createAnimation3v(Game.ballDataPanel, "numberForAnimation", "numberForAnimation", 1000,
                                            0f, 1f, 0.5f, 2f, 1f, 3f, true, false);
                                    animPanel.setOnChangeNotFluid(new Animation.OnChange() {
                                        @Override
                                        public void onChange() {
                                            if (Game.ballDataPanel.numberForAnimation == 1f){
                                                Game.ballDataPanel.setData(0.0f, 0f, false);
                                            } else if (Game.ballDataPanel.numberForAnimation == 2f){
                                                Game.ballDataPanel.setData(1f, 0f, false);
                                            }
                                        }
                                    });
                                    animPanel.start();
                                }
                            })

                            .onUnshowAfterAnim(new Tutorial.OnUnshowAfterAnim() {
                                @Override
                                public void onUnshowAfterAnim() {




                                    Game.ballDataPanel.clearAnimations();
                                    Game.ballDataPanel.setData(0f, 0f, false);
                                }
                            })

                            .build()
                    );

                    // L2T7
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textoBox7")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource(R.string.l2t7))
                                            .withoutArrow()
                                            .build()
                            )

                            .build()
                    );

                    // L2T8
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textBox8")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource(R.string.l2t8))
                                            .withoutArrow()
                                            .build()
                            )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                    Game.imageTutorialDown = new Image(
                                        "anguloDown", gX*0.348f, (gY * 0.975f) - (gX * 0.1f), gX * 0.20f, gX * 0.1f, Texture.TEXTURE_BARS, 1f/1024f, 255f/1024f, 898f/1024f, 1023f/1024f);
                                    Game.imageTutorialDown.display();
                                    Utils.createAnimation3v(Game.imageTutorialDown, "alpha", "alpha", 1500, 0f, 1f, 0.5f, 0.6f, 1f, 1f, true, true).start();
                                }
                            })
                        .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                            @Override
                            public void onUnshowBeforeAnim() {
                                Game.imageTutorialDown = null;
                            }
                        })
                        
                        .build()
                    );

                    // L2T9
                    Levels.levelObject.tutorials.add(
                            new Tutorial.TutorialBuilder(
                                    new TextBoxBuilder("textoBox9")
                                            .position(x, y)
                                            .width(width)
                                            .size(size)
                                            .text(Utils.getStringResource(R.string.l2t9))
                                            .withArrow(gX *0.5f, gY *0.95f)
                                            .build()
                            )
                            .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                @Override
                                public void onShowBeforeAnim() {
                                    Game.balls.get(0).getAnimationByName("numberForAnimation").setOnChangeNotFluid(new Animation.OnChange() {
                                        @Override
                                        public void onChange() {
                                            if (Game.balls.get(0).numberForAnimation == 1f){
                                                Game.ballDataPanel.setData(0.0f, 0.5f, false);
                                            } else if (Game.balls.get(0).numberForAnimation == 2f){
                                                Game.ballDataPanel.setData(0.0f, 0.43f, true);
                                            }
                                        }
                                    });
                                }
                            })
                            .onUnshowAfterAnim(new Tutorial.OnUnshowAfterAnim() {
                                @Override
                                public void onUnshowAfterAnim() {
                                    
                                    Game.balls.get(0).getAnimationByName("numberForAnimation").setOnChangeNotFluid(new Animation.OnChange() {
                                        @Override
                                        public void onChange() {
                                        }
                                    });
                                    Game.ballDataPanel.setData(0.0f, 0.0f, false);
                                }
                            })
                            .build()
                    );


                
                // L2T10
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox10")
                                    .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l2t10))
                                .withoutArrow()
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                    Game.balls.get(0).clearAnimations();
                                    Game.bars.get(0).clearAnimations();
                                    Game.balls.get(0).x = gX * 0.3f;
                                    Game.balls.get(0).y = gY * 0.72f;
                                    Game.bars.get(0).x = gX * 0.1f;
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                Utils.createAnimation4v(Game.balls.get(0), "translateX", "translateX", 3000,
                                    0f, 0f, 0.25f, gX*0.15f, 0.5f, gX*0.198f, 1f, gX*0.198f, true, true).start();
                                Utils.createAnimation4v(Game.balls.get(0), "translateY", "translateY", 3000,
                                    0f, 0f, 0.25f, gY*0.245f, 0.5f, gY*0.12f, 1f, gY*0.12f, true, true).start();
                                Utils.createAnimation3v(Game.bars.get(0), "translateX", "translateX", 3000,
                                    0f, 0f, 0.5f, gX*0.4f, 1f, gX*0.4f, true, true).start();

                                Animation animForPanel = Utils.createAnimation3v(Game.balls.get(0), "numberForAnimation", "numberForAnimation", 3000,
                                        0f, 1f, 0.25f, 2f, 1f, 3f, true, false);
                                animForPanel.setOnChangeNotFluid(new Animation.OnChange() {
                                    @Override
                                    public void onChange() {
                                        if (Game.balls.get(0).numberForAnimation == 1f){
                                            Game.ballDataPanel.setData(0.5f, 0.5f, false);
                                        } else if (Game.balls.get(0).numberForAnimation == 2f){
                                            Game.ballDataPanel.setData(0.4f, 0.6f, true);
                                        }
                                    }
                                });
                                animForPanel.start();
                            }
                        })
                        .build()
                );
                
                // L2T11
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox11")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l2t11))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );
                
                // L2T12
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox12")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l2t12))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );
                
                
                // L2T13
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox13")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l2t13))
                                .withoutArrow()
                                .build()
                        )
                    
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                    @Override
                                    public void onShowBeforeAnim() {
                                        Game.imageTutorialDown = new Image(
                                                "anguloUp", gX*0.348f, (gY * 0.975f) - (gX * 0.1f), gX * 0.20f, gX * 0.1f, Texture.TEXTURE_BARS, 1f/1024f, 255f/1024f, 770f/1024f, 895f/1024f);
                                        Game.imageTutorialDown.display();
                                        Utils.createAnimation3v(Game.imageTutorialDown, "alpha", "alpha", 1500, 0f, 1f, 0.5f, 0.6f, 1f, 1f, true, true).start();
                                    }
                        })
                        .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                            @Override
                            public void onUnshowBeforeAnim() {
                                Game.imageTutorialDown = null;
                                Game.balls.get(0).clearAnimations();
                                Game.bars.get(0).clearAnimations();
                            }
                        })

                        .build()
                );
                
                // L2T14
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox14")
                                .position(x*0.5f, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l2t14))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );

                // L2T15
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox15")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource(R.string.l2t15))
                                        .withoutArrow()
                                .build()
                        )
                        .build()
                );



                break;
                
                case 4:
                // L4T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox1")
                                .position(x, y*2.2f)
                                .width(width)
                                .size(size)
                                    .text(Utils.getStringResource( R.string.l4t1))
                                .withArrow(gX*0.5f, gY*0.34f)
                                .build()
                        )
                        .build()
                );
                break;
                
                case 7:
                // L7T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textBox1")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l7t1))
                                .withArrow(gX*0.5f, gY*0.08f)
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                            @Override
                            public void onShowBeforeAnim() {
                                Game.balls.get(0).clearDisplay();
                                Game.targets.get(4).setUvInfo(Target.TARGET_GREEN);
                                }
                            }
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                    Utils.createAnimation3v(Game.targets.get(4), "alpha", "alpha", 3000,
                                        0f, 1f, 0.75f, 0.99f, 1f, 0, true, true).start(); 
                                    ArrayList<float[]> valuesAnimTarget = new ArrayList<>();
                                        valuesAnimTarget.add(new float[]{0f,3f});
                                        valuesAnimTarget.add(new float[]{0.25f,2f});
                                        valuesAnimTarget.add(new float[]{0.5f,1f});
                                        valuesAnimTarget.add(new float[]{0.75f,0f});
                                    Animation animTarget = new Animation(Game.targets.get(4), "animTarget", "numberForAnimation", 3000, 
                                        valuesAnimTarget, true, false);
                                        animTarget.setOnChangeNotFluid(new Animation.OnChange() {
                                            @Override
                                            public void onChange() {
                                                if (Game.targets.get(4).numberForAnimation == 3f){
                                                    Game.targets.get(4).setUvInfo(Target.TARGET_GREEN);
                                                } else if (Game.targets.get(4).numberForAnimation == 2f) {
                                                    Game.targets.get(4).setUvInfo(Target.TARGET_BLUE);
                                                } else if (Game.targets.get(4).numberForAnimation == 1f) {
                                                    Game.targets.get(4).setUvInfo(Target.TARGET_BLACK);
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
                                      Game.targets.get(4).clearAnimations();
                                      Game.targets.get(4).alpha = 1f;
                                      Game.targets.get(4).setUvInfo(Target.TARGET_BLACK);
                                  }
                              }
                        )
                        .build()
                );
                break;
                
                case 9:
                // L9T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox1")
                                .position(x, y)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l9t1))
                                .withoutArrow()
                                .build()
                        )
                        .build()
                );
                
                // L9T2
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox2")
                                .position(x*0.5f, y*2f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l9t2))
                                .withArrow(gX*0.5f, gY*0.25f)
                                .build()
                        )
                        .build()
                );
                
                // L9T3
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                            new TextBoxBuilder("textoBox3")
                                .position(x*0.5f, y*2.5f)
                                .width(width)
                                .size(size)
                                .text(Utils.getStringResource( R.string.l9t3))
                                .withoutArrow()
                                .build()
                        )
                        .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                 @Override
                                 public void onShowBeforeAnim() {
                                     float posX = gX * 0.6f;
                                     float posY = gY * 0.28f;

                                     final Ball ball1 = Game.balls.get(0);
                                     ball1.x = posX;
                                     ball1.y = posY;

                                     float size = ball1.radius;

                                     final Ball ball2 = new Ball("ball2", posX - size * 2, posY - size * 2, size, Ball.COLOR_BALL_BLUE);
                                     ball2.isCollidable = false;
                                     ball2.alpha = 0f;
                                     ball2.display();

                                     final Ball ball3 = new Ball("ball2", posX + size * 2, posY - size * 2, size, Ball.COLOR_BALL_ORANGE);
                                     ball3.isCollidable = false;
                                     ball3.alpha = 0f;
                                     ball3.display();

                                     final Ball ball4 = new Ball("ball2", posX - size * 2, posY + size * 2, size, Ball.COLOR_BALL_RED);
                                     ball4.isCollidable = false;
                                     ball4.alpha = 0f;
                                     ball4.display();

                                     final Ball ball5 = new Ball("ball2", posX + size * 2, posY + size * 2, size, Ball.COLOR_BALL_PINK);
                                     ball5.isCollidable = false;
                                     ball5.alpha = 0f;
                                     ball5.display();

                                     Game.addBall(ball2);
                                     Game.addBall(ball3);
                                     Game.addBall(ball4);
                                     Game.addBall(ball5);

                                     Utils.createAnimation3v(ball1, "translateX", "translateX", 5000,
                                             0f, -gX * 0.15f, 0.2f, 0f, 1f, 0f, true, true).start();

                                     Utils.createAnimation4v(ball1, "translateY", "translateY", 5000,
                                             0f, -gY * 0.03f, 0.1f, -gY * 0.085f, 0.2f, 0f, 1f, 0f, true, true).start();

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

                                     Utils.createAnimation4v(ball2, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, 1f, -desloc,  true, true).start();
                                     Utils.createAnimation4v(ball3, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, 1f, desloc, true, true).start();
                                     Utils.createAnimation4v(ball4, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, 1f, -desloc, true, true).start();
                                     Utils.createAnimation4v(ball5, "translateX", "translateX", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, 1f, desloc, true, true).start();

                                     Utils.createAnimation4v(ball2, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, 1f, -desloc, true, true).start();
                                     Utils.createAnimation4v(ball3, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, -desloc, 1f, -desloc, true, true).start();
                                     Utils.createAnimation4v(ball4, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, 1f, desloc, true, true).start();
                                     Utils.createAnimation4v(ball5, "translateY", "translateY", 5000,
                                             0f, 0f, 0.25f, 0f, 0.4f, desloc, 1f, desloc, true, true).start();

                                     Utils.createAnimation4v(Game.targets.get(35), "alpha", "alpha", 5000,
                                             0f, 1f, 0.1f, 1f, 0.15f, 0f, 1f, 0f, true, true).start();

                                 }
                             }
                        )
                        .onUnshowAfterAnim(new Tutorial.OnUnshowAfterAnim() {
                                    @Override
                                    public void onUnshowAfterAnim() {
                                    Game.balls.get(0).clearAnimations();
                                    Game.balls.get(1).clearAnimations();
                                    Game.balls.get(2).clearAnimations();
                                    Game.balls.get(3).clearAnimations();
                                    Game.balls.get(4).clearAnimations();
                                        
                                    Game.balls.get(0).clearDisplay();
                                    Game.targets.get(35).clearAnimations();
                                    Game.targets.get(35).alpha = 0f;
                                    
                                    float desloc = Game.balls.get(1).radius * 4;
                                    Game.balls.get(1).translate(-desloc, -desloc);
                                    Game.balls.get(2).translate(desloc, -desloc);
                                    Game.balls.get(3).translate(-desloc, desloc);
                                    Game.balls.get(4).translate(desloc, desloc);
                                }
                            }
                        )
                        .build()
                );
                // L9T4
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox4")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t4))
                                        .withoutArrow()
                                        .build()
                        )
                                .build()
                );
                // L9T5
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox5")
                                        .position(x*0.5f, y*2f)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t5))
                                        .withArrow(gX*0.50f, gY*0.97f)
                                        .build()
                        )
                        .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                            @Override
                            public void onShowAfterAnim() {
                                Game.ballGoalsPanel.setValues(4, 1, 0);
                            }
                        })
                        .build()
                );

                // L6T6
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox6")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t6))
                                        .withArrow(gX*0.50f, gY*0.97f)
                                        .build()
                        )
                                .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                    @Override
                                    public void onShowAfterAnim() {

                                    }
                                })
                                .build()
                );
                // L6T7
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox7")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t7))
                                        .withArrow(gX*0.50f, gY*0.97f)
                                        .build()
                        )
                                .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                    @Override
                                    public void onShowAfterAnim() {

                                    }
                                })
                                .build()
                );

                // L6T8
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox8")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t8))
                                        .withArrow(gX*0.53f, gY*0.97f)
                                        .build()
                                )
                                .build()
                );


                // L6T9
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox9")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t9))
                                        .withArrow(gX*0.53f, gY*0.97f)
                                        .build()
                        )
                                .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                    @Override
                                    public void onShowAfterAnim() {

                                        Utils.createAnimation3v(Game.balls.get(2), "translateX", "translateX", 3000, 0f, 0f, 0.3f, Game.gameAreaResolutionX * 0.32f, 1f, Game.gameAreaResolutionX * 0.32f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(3), "translateX", "translateX", 3000, 0f, 0f, 0.25f, Game.gameAreaResolutionX * 0.25f, 1f, Game.gameAreaResolutionX * 0.25f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(4), "translateX", "translateX", 3000, 0f, 0f, 0.25f, Game.gameAreaResolutionX * 0.25f, 1f, Game.gameAreaResolutionX * 0.25f, true, true).start();

                                        Utils.createAnimation3v(Game.balls.get(2), "translateY", "translateY", 3000, 0f, 0f, 0.3f, Game.gameAreaResolutionY * 0.775f, 1f, Game.gameAreaResolutionY * 0.775f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(3), "translateY", "translateY", 3000, 0f, 0f, 0.25f, Game.gameAreaResolutionY * 0.63f, 1f, Game.gameAreaResolutionY * 0.63f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(4), "translateY", "translateY", 3000, 0f, 0f, 0.25f, Game.gameAreaResolutionY * 0.63f, 1f, Game.gameAreaResolutionY * 0.63f, true, true).start();

                                        Utils.createAnimation3v(Game.balls.get(2), "alpha", "alpha", 3000, 0f, 1f, 0.3f, 1f, 0.45f, 0f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(3), "alpha", "alpha", 3000, 0f, 1f, 0.25f, 1f, 0.4f, 0f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(4), "alpha", "alpha", 3000, 0f, 1f, 0.25f, 1f, 0.4f, 0f, true, true).start();

                                        ArrayList<float[]> valuesAnimPanel = new ArrayList<>();
                                        valuesAnimPanel.add(new float[]{0f,4f});
                                        valuesAnimPanel.add(new float[]{0.25f,2f});
                                        valuesAnimPanel.add(new float[]{0.3f,1f});
                                        valuesAnimPanel.add(new float[]{1f,1f});
                                        Animation animPanel = new Animation(Game.balls.get(2), "numberForAnimation", "numberForAnimation", 3000,
                                                valuesAnimPanel, true, false);
                                        animPanel.setOnChangeNotFluid(new Animation.OnChange() {
                                            @Override
                                            public void onChange() {
                                                if (Game.balls.get(2).numberForAnimation == 4f){
                                                    Game.ballGoalsPanel.setValues(4, 1, 0);
                                                } else if (Game.balls.get(2).numberForAnimation == 3f){
                                                    Game.ballGoalsPanel.setValues(3, 1, 0);
                                                } else if (Game.balls.get(2).numberForAnimation == 2f) {
                                                    Game.ballGoalsPanel.setValues(2, 1, 0);
                                                } else if (Game.balls.get(2).numberForAnimation == 1f) {
                                                    Game.ballGoalsPanel.setValues(1, 1, 0);
                                                }
                                            }
                                        });
                                        animPanel.start();
                                    }
                                })
                                .onUnshowAfterAnim(new Tutorial.OnUnshowAfterAnim() {
                                    @Override
                                    public void onUnshowAfterAnim() {
                                        Game.balls.get(2).clearAnimations();
                                        Game.balls.get(3).clearAnimations();
                                        Game.balls.get(4).clearAnimations();
                                        Game.ballGoalsPanel.setValues(4, 1, 0);
                                    }
                                })
                                .build()
                );

                // L6T10
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox10")
                                        .position(x, y*2.5f)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t10))
                                        .withoutArrow()
                                        .build()
                        )
                                .build()
                );

                // L9T11
                String textL9T11 = Utils.getStringResource(R.string.l9t11f);;
                // TODO definir pontuação

                /*
                if (SaveGame.saveGame.currentDifficulty == Game.DIFFICULTY_EASY){
                    textL9T11 = Utils.getStringResource(R.string.l9t11f);
                } else if (SaveGame.saveGame.currentDifficulty == Game.DIFFICULTY_NORMAL){
                    textL9T11 = Utils.getStringResource(R.string.l9t11n);
                } else if (SaveGame.saveGame.currentDifficulty == Game.DIFFICULTY_HARD){
                    textL9T11 = Utils.getStringResource(R.string.l9t11d);
                } else{
                    textL9T11 = Utils.getStringResource(R.string.l9t11i);
                }
                */

                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox11")
                                        .position(x, y*2.5f)
                                        .width(width)
                                        .size(size)
                                        .text(textL9T11)
                                        .withoutArrow()
                                        .build()
                                )
                                .onShowBeforeAnim(new Tutorial.OnShowBeforeAnim() {
                                    @Override
                                    public void onShowBeforeAnim() {
                                        Utils.createAnimation3v(Game.balls.get(4), "translateX", "translateX", 3000, 0f, 0f, 0.25f, Game.gameAreaResolutionX * 0.06f, 1f, Game.gameAreaResolutionX * 0.061f, true, true).start();
                                        Utils.createAnimation3v(Game.balls.get(4), "translateY", "translateY", 3000, 0f, 0f, 0.25f, -Game.gameAreaResolutionY * 0.16f, 1f, -Game.gameAreaResolutionY * 0.16f, true, true).start();
                                        Utils.createAnimation4v(Game.targets.get(37), "alpha", "alpha", 3000, 0f, 1f, 0.25f, 1f, 0.3f, 0f, 1f, 0f, true, true).start();


                                        ArrayList<float[]> valuesAnimPoints = new ArrayList<>();
                                        valuesAnimPoints.add(new float[]{0f,2f});
                                        valuesAnimPoints.add(new float[]{0.25f,1f});
                                        valuesAnimPoints.add(new float[]{1f,0f});
                                        Animation animPoints = new Animation(Game.balls.get(4), "numberForAnimation", "numberForAnimation", 3000,
                                                valuesAnimPoints, true, false);
                                        animPoints.setOnChangeNotFluid(new Animation.OnChange() {
                                            @Override
                                            public void onChange() {
                                                if (Game.balls.get(4).numberForAnimation == 2f){
                                                    Game.scorePanel.setValue(0, false, 0, false);
                                                }
                                                if (Game.balls.get(4).numberForAnimation == 1f){
                                                    Game.scorePanel.setValue(800, true, 1000, false);
                                                    Game.targets.get(37).showPoints(800);
                                                }
                                            }
                                        });
                                        animPoints.start();
                                    }
                                })
                                .onUnshowBeforeAnim(new Tutorial.OnUnshowBeforeAnim() {
                                    @Override
                                    public void onUnshowBeforeAnim() {
                                        Game.balls.get(4).clearAnimations();
                                        Game.targets.get(37).clearAnimations();
                                        Game.targets.get(37).reduceAlpha(100, 0f);
                                    }
                                })

                                .build()
                );

                // L6T12
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox12")
                                        .position(x, y*2.5f)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t12))
                                        .withoutArrow()
                                        .build()
                        )
                                .onShowAfterAnim(new Tutorial.OnShowAfterAnim() {
                                    @Override
                                    public void onShowAfterAnim() {
                                        for (int i = 0; i < Game.targets.size(); i++) {
                                            if (i != 37) {
                                                Game.targets.get(i).reduceAlpha(500, 0);
                                            }
                                        }

                                        Game.scorePanel.setValue(10000, true, 1000, false);

                                    ArrayList<float[]> valuesScoreAnim = new ArrayList<>();
                                        valuesScoreAnim.add(new float[]{0f,5f});
                                        valuesScoreAnim.add(new float[]{0.2f,4f});
                                        valuesScoreAnim.add(new float[]{0.4f,3f});
                                        valuesScoreAnim.add(new float[]{0.6f,2f});
                                        valuesScoreAnim.add(new float[]{0.8f,1f});
                                        valuesScoreAnim.add(new float[]{1f,0f});
                                    Animation animScore = new Animation(Game.balls.get(4), "numberForAnimation", "numberForAnimation",
                                            10000, valuesScoreAnim, false, false);
                                        animScore.setOnChangeNotFluid(new Animation.OnChange() {
                                        @Override
                                        public void onChange() {
                                            if (Game.balls.get(4).numberForAnimation == 4f){
                                                Game.scorePanel.setValue(7500, true, 1000, true);
                                                Game.scorePanel.showMessage("+ 50%", 1000);
                                                Game.ballGoalsPanel.setValues(3, 1, 0);
                                                Game.balls.get(3).clearDisplay();
                                            } else if (Game.balls.get(4).numberForAnimation == 3f){
                                                Game.scorePanel.setValue(11125, true, 1000, true);
                                                Game.scorePanel.showMessage("+ 50%", 1000);
                                                Game.ballGoalsPanel.setValues(2, 1, 0);
                                                Game.balls.get(2).clearDisplay();
                                            } else if (Game.balls.get(4).numberForAnimation == 2f){
                                                Game.scorePanel.setValue(16875, true, 1000, true);
                                                Game.scorePanel.showMessage("+ 50%", 1000);
                                                Game.ballGoalsPanel.setValues(1, 1, 0);
                                                Game.balls.get(1).clearDisplay();
                                            }
                                        }
                                    });
                                        animScore.start();
                                    }
                                })
                                .build()
                );

                // L9T13
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox13")
                                        .position(x, y*2.5f)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t13))
                                        .withoutArrow()
                                        .build()
                        )
                                .build()
                );

                // L6T14
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textoBox14")
                                        .position(x, y*2.5f)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l9t14))
                                        .withoutArrow()
                                        .build()
                        )
                                .build()
                );
                break;


            case 12:
                // L12T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l12t1))
                                        .build()
                        )
                                .build()
                );
                break;

            case 16:
                // L16T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l16t1))
                                        .build()
                        )
                                .build()
                );
                break;

            case 19:
                // L19T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l19t1))
                                        .build()
                        )
                                .build()
                );
                break;

            case 20:
                // L20T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l20t1))
                                        .build()
                        )
                                .build()
                );
                break;

            case 21:
                // L21T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l21t1))
                                        .build()
                        )
                                .build()
                );
                break;

            case 24:
                // L24T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x*0.25f, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l24t1))
                                        .build()
                        )
                                .build()
                );
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox2")
                                        .position(x*0.25f, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l24t2))
                                        .withArrow(gX*0.8f, gY*0.55f)
                                        .build()
                        )
                                .build()
                );
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox3")
                                        .position(x*0.5f, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l24t3))
                                        .withArrow(gX*0.45f, gY*0.97f)
                                        .build()
                        )
                                .build()
                );
                break;

            case 25:
                // L25T1
                Levels.levelObject.tutorials.add(
                        new Tutorial.TutorialBuilder(
                                new TextBoxBuilder("textBox1")
                                        .position(x, y)
                                        .width(width)
                                        .size(size)
                                        .text(Utils.getStringResource( R.string.l25t1))
                                        .build()
                        )
                                .build()
                );
                break;


        }
    }
}
