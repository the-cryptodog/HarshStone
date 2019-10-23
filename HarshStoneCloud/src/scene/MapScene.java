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


    private BufferedImage map;
    private BufferedImage cover;
    private redCross[] redCross;
    private ArrayList<redCross> redCrossList;
    private int currentStage;
    private int[][] redCrossSetter = {{435, 505},
    /*{x座標/y座標} */ {645, 335}, {645, 610},
    {936, 310}, {936, 500}, {931, 658},
    {1235, 327}, {1225,596},
    {1436, 532}};

    public MapScene(SceneController scenecontroller) {
        super(scenecontroller);

        currentStage = 0;
        System.out.println("stage=" + currentStage);
        redCrossList = new ArrayList<redCross>();
//        redCrossList.add(new redCross(455,505,50,50,"ddddd"));
        for (int i = 0; i < redCrossSetter.length; i++) { 
            redCrossList.add(new redCross(redCrossSetter[i][0], redCrossSetter[i][1], 50, 50, "紅叉"));      
        }
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

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    for (int i = 0; i < redCrossList.size(); i++) {
                        if (redCrossList.get(i).isCollision(e.getX(), e.getY())) {
                            redCrossList.get(i).setIsClicked(true);
                        }
                    }
//                      startPressed = true;
                    scenecontroller.changeScene(new MainScene(scenecontroller));
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

    @Override
    public void sceneBegin() {
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
        System.out.print(redCrossList.size());
        for (int i = 0; i < redCrossList.size(); i++) {
//                     System.out.print(i);
//
            redCrossList.get(i).paint(g);
        }
//        redCrossList.get(0).paint(g);
        switch (currentStage) {
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
