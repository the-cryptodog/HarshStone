/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.Hero.Hero;


import io.CommandSolver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import scene.SelectJobSceneState.beginTalk1;

import scene.SelectJobSceneState.normal;
import scene.SelectJobSceneState.selectJob;
import scene.SelectJobSceneState.talkEnd;

import utils.DelayCounter;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class SelectJobScene extends Scene {

    private BufferedImage img;
    private BufferedImage tmpIma;
    private BufferedImage job1Screen1;
    private BufferedImage job1Screen2;
    private BufferedImage job2Screen1;
    private BufferedImage job2Screen2;
    private BufferedImage DARKEN1;
    private BufferedImage DARKEN2;
    private BufferedImage DARKEN3;
    private BufferedImage DARKEN4;
    private BufferedImage talkchart;
    private DelayCounter delaycounter;
    private DelayCounter delayForEffect;
    private int sx1, sy1, sx2, sy2;

    private Button next;
    private Button back;
    private Hero job1;
    private Hero job2;
    private Hero npc;
    private Hero heroSelected;
    private Button job1screen;
    private Button job2screen;
    int upY = 430;
    int downY = 250;
    private boolean jobSelected;
    private boolean storyBegin;
    private boolean storyEnd;
    private SelectJobSceneState selectjobscenestate;
    private Hero[] heros;
    

    public SelectJobScene(SceneController scenecontroller) {
        super(scenecontroller);

        storyEnd = false;


        selectjobscenestate = new normal();
        storyEnd= false;

        jobSelected = false;
        back = new Button(1800, 1000, 85, 50, "BACK");

//        actor = new Actor(450, 0, 128, 128, 0, "巫師");
//        actor2 = new Actor(1150, 0, 128, 128, 0, "戰士");

        job1 = new Hero(Global.JOB1X, Global.JOBY, 128, 128, "Actor1", 0, 0);
        job2 = new Hero(Global.JOB2X,  Global.JOBY, 128, 128, "Actor1", 0, 7);
        npc = new Hero(Global.NPCX,  Global.JOBY, 128, 128, "Actor1", 0, 4);
        
        heros = new Hero[3];
        heros[0] = job1;
        heros[1] = job2;
        heros[2] = npc;
        


        job1.changeDirection(Global.DOWN);
        job2.changeDirection(Global.DOWN);
        npc.changeDirection(Global.LEFT);

        job1Screen1 = irc.tryGetImage("/resources/Button/JOB1SCREEN-1.png");
        job1Screen2 = irc.tryGetImage("/resources/Button/JOB1SCREEN-2.png");
        job2Screen1 = irc.tryGetImage("/resources/Button/JOB2SCREEN-1.png");
        job2Screen2 = irc.tryGetImage("/resources/Button/JOB2SCREEN-2.png");

        job1screen = new Button(350, 250, 391, 506, "JOB1SCREEN");
        job2screen = new Button(1150, 250, 391, 506, "JOB2SCREEN");
        next = new Button(750, 550, 54, 20, "EXIT");

        sx1 = 480;
        sy1 = 270;
        sx2 = 1440;
        sy2 = 810;

        delaycounter = new DelayCounter(5, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });

        DARKEN1 = irc.tryGetImage("/resources/Incidence/DARKEN1.png");
        DARKEN2 = irc.tryGetImage("/resources/Incidence/DARKEN2.png");
        DARKEN3 = irc.tryGetImage("/resources/Incidence/DARKEN3.png");
        DARKEN4 = irc.tryGetImage("/resources/Incidence/DARKEN4.png");
        talkchart = irc.tryGetImage("/resources/Incidence/TALKCHART.png");
        img = irc.tryGetImage("/resources/Map/mapOrigin.png");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (job1screen.isCollision(e.getX(), e.getY())& !storyBegin) {

                        job1screen.setIsClicked(true);
                        heroSelected = job1;
                        heros[1]=null;
                        jobSelected = true;
                        storyBegin = true;
                        selectjobscenestate = new selectJob();


//                        scenecontroller.changeScene(new MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (job2screen.isCollision(e.getX(), e.getY())& !storyBegin) {

                        job2screen.setIsClicked(true);
                        heroSelected = job2;
                        selectjobscenestate = new selectJob();
                        heros[0]=null;
                        storyBegin=true;
                        jobSelected = true;
                        
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new MenuScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (jobSelected & next.isCollision(e.getX(), e.getY())) {            
                        selectjobscenestate = new talkEnd();
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
    public Hero getHeroSelected(){
        return heroSelected;
    }
    public void setStoryEnd(boolean storyEnd){
       this.storyEnd = storyEnd;
    }
    
    public Hero[] getHeros(){
        return heros;
    }
    
    
    
    public void setSelectJobSceneState(SelectJobSceneState selectjobscenestate){
        this.selectjobscenestate = selectjobscenestate;
    }
    
    
    
    @Override
    public void sceneBegin() {
    }

    @Override
    public void sceneUpdate() {

//        if (jobSelected & !storyEnd) {//所選角色往回走就定位開啟對話
//            if(heroSelected.getX()<307){
//                  selectjobscenestate= new beginTalk1();
//            }
//        }
        if (delaycounter.delayupdate()) {

            selectjobscenestate.action(this);

        }
         if (storyEnd ) { //npc往回走出螢幕
             if(heroSelected.getWidth()<50){
            scenecontroller.changeScene(new MapScene(scenecontroller));
             }
        }
    }

    @Override
    public void sceneEnd() {

    }

    public void changePix(BufferedImage sourceImage) {
        tmpIma = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < sourceImage.getWidth(); i++) {
            for (int j = 0; j < sourceImage.getHeight(); j++) {
                Color color = new Color(sourceImage.getRGB(i, j));
                int tmp = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color tmpcol = new Color(tmp, tmp, tmp);
                tmpIma.setRGB(i, j, tmpcol.getRGB());
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(img, 0, 0, 1920, 1080, sx1, sy1, sx2, sy2, null);

        if (jobSelected & !storyEnd) {

            g.drawImage(DARKEN3, 0, 0, 1920, 1080, null);
        }

        if (jobSelected & storyEnd) {
            if (sx1 > 0 & sy1 > 0 || sx2 < 1920 & sy2 < 1080) {
                sx1 -= Global.XSPEED;
                sy1 -= Global.YSPEED;
                sx2 += Global.XSPEED;
                sy2 += Global.YSPEED;
                System.out.println(sx1 + " " + sy1 + " " + sx2 + " " + sy2 + " ");
            }
        }

        if (!jobSelected) {
            job1screen.paint(g);
            job2screen.paint(g);
        }
        else
        {
            if (job1screen.getIsClicked()) {
                g.drawImage(job1Screen1, 350, upY , 391, 322, null);
                g.drawImage(job1Screen2, 350, downY, 390, 184, null);
                
            }
            if (job2screen.getIsClicked()) {
                g.drawImage(job2Screen1, 1150, upY, 391, 322, null);
                g.drawImage(job2Screen2, 1150, downY, 390, 184, null);                
            }
//        }else {
//            job1screen.paint(g);
//            job2screen.paint(g);
        }
        back.paint(g);
//        
//        if(!job1screen.getIsClicked()){
//            job2.paint(g);
//        }
//        if(!job2screen.getIsClicked()){
//            job1.paint(g);
//        }
//        npc.paint(g);
        
        for(Hero temp : heros){
            if(temp != null){
                temp.paint(g);
            }
        }
        
        
        if(selectjobscenestate instanceof beginTalk1){
            g.drawImage(talkchart, 720, 190, null);

            next.paint(g);
        }
    }
}
