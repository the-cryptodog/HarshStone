/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Button.Button;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class MenuScene extends Scene {

    private BufferedImage img;
    private Button start;
    private Button title;
    private Button exit;
    private Button newGameButton;
    private Button continueButton;

    private boolean startPressed;

    //開始遊戲(進入選角畫面)
    //結束遊戲(關閉視窗)
    public MenuScene(SceneController scenecontroller) {
        super(scenecontroller);
        startPressed = false;

//        start = new StartButton(690, 300, 200, 100, "開始遊戲");
        newGameButton = new Button(690, 380, 300, 95, "NEWGAME");
        title = new Button(498, 0, 670, 215, "TITLE");
        continueButton = new Button(690, 450, 300, 95, "CONTINUE");
        exit = new Button(690,620, 300, 95, "EXIT");
        img = irc.tryGetImage("/resources/Background/背景7.jpg");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (newGameButton.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new SelectJobScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {              
                    if (newGameButton.isCollision(e.getX(), e.getY())) {
                        startPressed = true;
                        System.out.println(startPressed);
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
        g.drawImage(img, 0, 0, 1920, 1050, null);

//        if (!startPressed) {
//            start.paintUnpressed(g);
//        } else {
//            start.paintPressed(g);
//        }
        title.paint(g);
        exit.paint(g);
        continueButton.paint(g);
        newGameButton.paint(g);
    }

}
