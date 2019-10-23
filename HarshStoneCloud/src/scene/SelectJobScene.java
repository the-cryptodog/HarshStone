/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Button.BackButton;
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
    private Sorcerer socerer;
    private Warrior warrior;
    private BackButton backButton;

    public SelectJobScene(SceneController scenecontroller) {
        super(scenecontroller);

        backButton = new BackButton(1400, 900, 300, 95, "返回按鈕");
        socerer = new Sorcerer(340, 425, 260, 260, "巫師");
        warrior = new Warrior(940, 425, 260, 260, "戰士");
        img = irc.tryGetImage("/resources/Background/背景3.jpg");
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
                    if ( backButton .isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new  MenuScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {
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
        g.drawImage(img, 0, 0, 1680, 1050, null);
         socerer.paint(g);
         warrior.paint(g);
         backButton.paint(g);
    }
}


