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
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author User
 */
public class MenuScene extends Scene {

    private BufferedImage img;
    private ArrayList<Button> buttons;
    private SelectJobSceneState menuscenestate;
    private boolean startPressed;

    //開始遊戲(進入選角畫面)
    //結束遊戲(關閉視窗)
    public MenuScene(SceneController scenecontroller) {
        super(scenecontroller);
        startPressed = false;
        Global.CURRENTSTAGE = 1;
//        start = new StartButton(690, 300, 200, 100, "開始遊戲");
        buttons = new ArrayList<>();
        buttons.add(new Button(50, 50, 220, 50, "NEWGAME"));
        buttons.add(new Button(50, 110, 220, 50, "CONTINUE"));
        buttons.add(new Button(50, 170, 85, 50, "EXIT"));
        img = irc.tryGetImage("/resources/Background/MENU.png");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (buttons.get(0).isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new SelectJobScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    if (buttons.get(0).isCollision(e.getX(), e.getY())) {
                        startPressed = true;
                        System.out.println(startPressed);
                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                    System.out.print(" drag!!!!");
                }
                if (state == CommandSolver.MouseState.ENTERED) {

                }
                if (state == CommandSolver.MouseState.EXITED) {

                    }
                
                if (state == CommandSolver.MouseState.MOVED) {
                    for (int i = 0; i < buttons.size(); i++) {
                        if (buttons.get(i).isCollision(e.getX(), e.getY())) {
                            buttons.get(i).hover(e.getX(), e.getY());
                        }
                    }
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
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g);
        }

//        if (!startPressed) {
//            start.paintUnpressed(g);
//        } else {
//            start.paintPressed(g);
//        }
    }

}
