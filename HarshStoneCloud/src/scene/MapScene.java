/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.MapIcon.MapPath;
import gameObject.MapIcon.redCross;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class MapScene extends Scene {

    private final int[] xEdge = {0, 495, 695, 982, 1285};
    private boolean stagePassed;
    private BufferedImage map;
    private BufferedImage cover;
    private redCross[] redCross;
    private ArrayList<redCross> redCrossList;
    private int currentStage;
    private int[][] redCrossSetter = {{435, 505},
    /*{x座標/y座標} */ {645, 335}, {645, 610},
    {936, 310}, {936, 500}, {931, 658},
    {1235, 327}, {1225, 596},
    {1436, 532}};
    private int[][] availableRoute = {{4, 5}, {2, 5, 8, 9}, {3, 6, 8, 9}};
    private int currentRedCross;

    public MapScene(SceneController scenecontroller) {
        super(scenecontroller);

        stagePassed = false;

        System.out.println("stage=" + currentStage);
        redCrossList = new ArrayList<redCross>();
//        redCrossList.add(new redCross(455,505,50,50,"ddddd"));
        for (int i = 0; i < redCrossSetter.length; i++) {
            redCrossList.add(new redCross(redCrossSetter[i][0], redCrossSetter[i][1], 50, 50, "REDCROSS"));
            redCrossList.get(i).getTagList().add(i);
        }
        currentRedCross = -1;
        redCrossList.get(0).getTagList().add(1);
        redCrossList.get(0).getTagList().add(2);

        redCrossList.get(1).getTagList().add(3);
        redCrossList.get(1).getTagList().add(4);
        redCrossList.get(2).getTagList().add(5);
        redCrossList.get(3).getTagList().add(6);
        redCrossList.get(4).getTagList().add(7);
        redCrossList.get(5).getTagList().add(7);
        redCrossList.get(6).getTagList().add(8);
        redCrossList.get(7).getTagList().add(8);

        System.out.println(redCrossList.get(0).toString());
        System.out.print(redCrossList.get(0) instanceof redCross);
        System.out.print(redCrossList.size());

        map = irc.tryGetImage("/resources/Map/map.png");
        cover = irc.tryGetImage("/resources/Map/mapOrigin.png");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }
//&& redCrossList.get(currentRedCross).checkTag(i)
                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    System.out.println(redCrossList.get(0).checkTag(0));
                    if (redCrossList.get(0).isCollision(e.getX(), e.getY())) {
                        stagePassed = false;
                        currentRedCross = redCrossList.get(0).getTagList().get(0);
                        redCrossList.get(0).setIsClicked(true);
                        scenecontroller.changeScene(new MainScene(scenecontroller, getThis()));
                    }
                    for (int i = 0; i < redCrossList.size(); i++) {
                        if (redCrossList.get(i).isCollision(e.getX(), e.getY())
                                && redCrossList.get(currentRedCross).checkTag(i)) {
                            System.out.print("ddggggg4444");
                            stagePassed = false;
                            currentRedCross = redCrossList.get(i).getTagList().get(0);
                            redCrossList.get(i).setIsClicked(true);
                            scenecontroller.changeScene(new MainScene(scenecontroller, getThis()));

                        }
                    }
//                      startPressed = true;

                }
//                    if (socerer .isCollision(e.getX(), e.getY())) {
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
//                    }
//                }

                if (state == CommandSolver.MouseState.PRESSED) {
//                    if (socerer .isCollision(e.getX(), e.getY())) {

//                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {

                }
            }
        };
    }

    public MapScene getThis() {
        return this;
    }

    @Override
    public void sceneBegin() {
        stagePassed = true;
        Global.CURRENTSTAGE++;
    }

    @Override
    public void sceneUpdate() {
        
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(map, 0, 0, 1920, 1080, null);

        for (int i = 0; i < redCrossList.size(); i++) {
//                     System.out.print(i);
            if (stagePassed) {
                redCrossList.get(i).paint(g);
            }
        }
//        redCrossList.get(0).paint(g);
        switch (Global.CURRENTSTAGE) {
            case 1:
                g.drawImage(cover, 495, 0, 1920, 1080, 495, 0, 1920, 1080, null);
                break;
            case 2:
                g.drawImage(cover, 695, 0, 1920, 1080, 695, 0, 1920, 1080, null);
                break;
            case 3:
                g.drawImage(cover, 982, 0, 1920, 1080, 982, 0, 1920, 1080, null);
                break;
            case 4:
                g.drawImage(cover, 1285, 0, 1920, 1080, 1275, 0, 1920, 1080, null);
                break;
        }
    }
}
