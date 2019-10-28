/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.Hero.Hero;
import gameObject.Hero.HeroState;
import gameObject.Hero.HeroState.jobSelected;

import gameObject.Jobs.JobIcon;

import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class SelectJobScene extends Scene {

    private BufferedImage img;
    private BufferedImage job1Screen1;
    private BufferedImage job1Screen2;
    private BufferedImage job2Screen1;
    private BufferedImage job2Screen2;
    private BufferedImage socererName;
    private BufferedImage warriorName;
    private DelayCounter delaycounter;

    private Button back;
    private Hero job1;
    private Hero job2;
    private Button job1screen;
    private Button job2screen;
    int upY = 430;
    int downY = 250;

    public SelectJobScene(SceneController scenecontroller) {
        super(scenecontroller);

        back = new Button(1800, 1000, 85, 50, "BACK");

//        actor = new Actor(450, 0, 128, 128, 0, "巫師");
//        actor2 = new Actor(1150, 0, 128, 128, 0, "戰士");
        job1 = new Hero(495, 400, 128, 128, "Actor1", 0, 0);
        job2 = new Hero(1300, 400, 128, 128, "Actor1", 0, 7);
        job1.changeDirection(Global.DOWN);
        job2.changeDirection(Global.DOWN);

        job1Screen1 = irc.tryGetImage("/resources/Button/JOB1SCREEN-1.png");
        job1Screen2 = irc.tryGetImage("/resources/Button/JOB1SCREEN-2.png");
        job2Screen1 = irc.tryGetImage("/resources/Button/JOB2SCREEN-1.png");
        job2Screen2 = irc.tryGetImage("/resources/Button/JOB2SCREEN-2.png");

        job1screen = new Button(350, 250, 391, 506, "JOB1SCREEN");
        job2screen = new Button(1150, 250, 391, 506, "JOB2SCREEN");

        delaycounter = new DelayCounter(5, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });

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
                    if (job1screen.isCollision(e.getX(), e.getY())) {
                        job1screen.setIsClicked(true);
                        job1.setState(new jobSelected());
                        job1.changeDirection(Global.RIGHT);

//                        scenecontroller.changeScene(new MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (job2.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new MenuScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    if (job1.isCollision(e.getX(), e.getY())) {

                    }
                }
                if (state == CommandSolver.MouseState.MOVED) {
                    if (job2.isCollision(e.getX(), e.getY())) {

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
        if (delaycounter.delayupdate()) {
            job1.getState().action(job1);
            job2.move();
        }

    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, 1920, 1080, null);
        if (job1screen.getIsClicked() | job2screen.getIsClicked()) {
            if (job1screen.getIsClicked()) {
                System.out.print("英雄被點擊");

                if (upY < 1400 || downY > -430) {
                    upY += 30;
                    downY -= 30;
                    g.drawImage(job1Screen1, 350, upY, 391, 322, null);
                    g.drawImage(job1Screen2, 350, downY, 390, 184, null);

                }
            }
            if (job2screen.getIsClicked()) {
                System.out.print("英雄被點擊");

                if (upY < 1400 || downY > -430) {
                    upY += 30;
                    downY -= 30;
                    g.drawImage(job2Screen1, 1150, upY, 391, 322, null);
                    g.drawImage(job2Screen2, 1150, downY, 390, 184, null);
                }

            }
        } else {
            job1screen.paint(g);
            job2screen.paint(g);
        }
        back.paint(g);

        job2.paint(g);
    }
}
