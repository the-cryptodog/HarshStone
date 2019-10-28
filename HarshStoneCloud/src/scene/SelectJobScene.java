/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.Hero.Hero;
import gameObject.Jobs.Sorcerer;
import gameObject.Jobs.Warrior;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author frank61003
 */
public class SelectJobScene extends Scene {

    private BufferedImage img;
    private BufferedImage socererName;
    private BufferedImage warriorName;
    private Hero hero;

    private Button socerer;
    private Button warrior;
    private Button back;

    public SelectJobScene(SceneController scenecontroller) {
        super(scenecontroller);

        back = new Button(1800, 1000, 85, 50, "BACK");
        socerer = new Button(450, 450, 260, 260, "巫師");
        warrior = new Button(1150, 450, 260, 260, "戰士");
        socererName = irc.tryGetImage("/resources/Jobs/JOB1.png");
        warriorName = irc.tryGetImage("/resources/Jobs/JOB2.png");
        img = irc.tryGetImage("/resources/Map/mapOrigin.png");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (socerer .isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new  MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (warrior .isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new  MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if ( back .isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new  MenuScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    if (socerer .isCollision(e.getX(), e.getY())) {
                        
                    }
                }
                if (state == CommandSolver.MouseState.MOVED) {
                    if (socerer .isCollision(e.getX(), e.getY())) {
                        
                    }
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
        g.drawImage(img, 0, 0, 1920, 1080, null);
         socerer.paint(g);
         warrior.paint(g);
         back.paint(g);
         g.drawImage(socererName ,480, 400,168,63, null);
         g.drawImage(warriorName , 1180, 400,193,63, null);
    }
}


