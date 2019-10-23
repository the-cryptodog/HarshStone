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
    private int[] redCrossSetter;
    private int stage; 
    

    public MapScene(SceneController scenecontroller) {
        super(scenecontroller);
        stage = 3;
        System.out.print("stage=" + stage);
    
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
                    if (socerer.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new MainScene(scenecontroller));
//                      startPressed = true;
//                    scenecontroller.changeScene(new MainScene(scenecontroller));
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

        switch (stage) {
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
                g.drawImage(cover, 1305, 0, 1920, 1080, 1305, 0, 1920, 1080, null);
                break;
        }
    }
}
