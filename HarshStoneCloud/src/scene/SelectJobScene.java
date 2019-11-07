package scene;

import Controller.PathBuilder;
import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.Hero.Hero;
import io.CommandSolver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;
import scene.MapScene;
import scene.MenuScene;
import scene.Scene;
import scene.SelectJobSceneState;
import scene.SelectJobSceneState.*;
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
    private BufferedImage talkchart1;
    private BufferedImage talkchart2;
    private BufferedImage talkchart3;
    private BufferedImage talkchart4;
    private BufferedImage talkchart5;
    private BufferedImage talkchart6;
    private BufferedImage talkchart7;
    private BufferedImage talkchart8;
    private BufferedImage talkchart9;
    private BufferedImage talkchart10;
    private DelayCounter delaycounter;
    private ArrayList<BufferedImage> npctalkcharts;
    private ArrayList<BufferedImage> herotalkcharts;
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
     int upY ;
    int downY;
    private boolean jobSelected;
    private boolean storyBegin;
    private boolean storyEnd;
    private SelectJobSceneState selectjobscenestate;
    private Hero[] heros;


    
    
    public SelectJobScene(SceneController scenecontroller) {
        super(scenecontroller);
        
        upY = 430;
        downY = 250;
        selectjobscenestate = new normal();
        storyEnd = false;

        jobSelected = false;
        back = new Button(1800, 1000, 85, 50, "BACK");
        next = new Button(750, 550, 220, 50, "CONTINUE");

//        actor = new Actor(450, 0, 128, 128, 0, "巫師");
//        actor2 = new Actor(1150, 0, 128, 128, 0, "戰士");
        job1 = new Hero(Global.JOB1X, Global.JOBY, 128, 128, "Actor1", 0, 0);
        job2 = new Hero(Global.JOB2X, Global.JOBY, 128, 128, "Actor1", 0, 7);
        npc = new Hero(Global.NPCX, Global.JOBY, 128, 128, "Actor1", 0, 4);

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
        
        talkchart1 = irc.tryGetImage("/resources/Incidence/TALK1.png");
        talkchart2 = irc.tryGetImage("/resources/Incidence/TALK99.png");
        talkchart3 = irc.tryGetImage("/resources/Incidence/TALK3.png");
        talkchart4 = irc.tryGetImage("/resources/Incidence/TALK4.png");
        talkchart5 = irc.tryGetImage("/resources/Incidence/TALK5.png");
        talkchart6 = irc.tryGetImage("/resources/Incidence/TALK6.png");
        talkchart7 = irc.tryGetImage("/resources/Incidence/TALK7.png");
        talkchart8 = irc.tryGetImage("/resources/Incidence/TALK8.png");
        talkchart9 = irc.tryGetImage("/resources/Incidence/TALK9.png");
        
        npctalkcharts = new ArrayList<BufferedImage>();
        herotalkcharts = new ArrayList<BufferedImage>();
        npctalkcharts.add(talkchart1);
        herotalkcharts.add(talkchart2);
        npctalkcharts.add(talkchart3);
        herotalkcharts.add(talkchart4);
        
  
        
        
        img = irc.tryGetImage("/resources/Map/mapOrigin.png");
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (job1screen.isCollision(e.getX(), e.getY()) & !storyBegin) {

                        job1screen.setIsClicked(true);
                        heroSelected = job1;
                        Global.hero = job1;
                        heros[1] = null;
                        jobSelected = true;
                        storyBegin = true;
                        selectjobscenestate = new selectJob();
                        

//                        scenecontroller.changeScene(new MapScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (job2screen.isCollision(e.getX(), e.getY()) & !storyBegin) {

                        job2screen.setIsClicked(true);
                        heroSelected = job2;

                        Global.hero = job2;
                        heros[0] = null;
                        storyBegin = true;
                        jobSelected = true;
                        selectjobscenestate = new selectJob();

//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new MenuScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
                    }
                    
                    if (jobSelected & next.isCollision(e.getX(), e.getY())) {
                        if (selectjobscenestate instanceof beginTalk1) {
                            System.out.print("事件1發生!!!");
                            selectjobscenestate = new beginTalk2();
                            return;
                        }
                    
                        else if (selectjobscenestate instanceof beginTalk2) {
                            System.out.print("事件2發生!!!");
                            selectjobscenestate = new beginTalk3();
                            return;
                        }
                    
                        else if (selectjobscenestate instanceof beginTalk3) {
                            System.out.print("事件3發生!!!");
                            selectjobscenestate = new beginTalk4();
                            return;
                        }
                    
                   
                        else if (selectjobscenestate instanceof beginTalk4) {
                            System.out.print("事件4發生!!!");
                            selectjobscenestate = new beginTalk5();
                            return;
                        }
                    
                   
                        else if (selectjobscenestate instanceof beginTalk5) {
                            System.out.print("事件5發生!!!");
                            selectjobscenestate = new beginTalk6();
                            return;
                        }
                    
                   
                        else if (selectjobscenestate instanceof beginTalk6) {
                            System.out.print("事件6發生!!!");
                            selectjobscenestate = new beginTalk7();
                            return;
                        }
                    
                    
                        else if (selectjobscenestate instanceof beginTalk7) {
                            System.out.print("事件7發生!!!");
                            selectjobscenestate = new beginTalk8();
                            return;
                        }
                    
                    
                        else if (selectjobscenestate instanceof beginTalk8) {
                            System.out.print("事件8發生!!!");
                            selectjobscenestate = new beginTalk9();
                            return;
                        }
                        
                        else if (selectjobscenestate instanceof beginTalk9) {
                            System.out.print("事件9發生!!!");
                            selectjobscenestate = new talkEnd();
                            return;
                        }
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

    public Hero getHeroSelected() {
        return heroSelected;
    }

    public void setStoryEnd(boolean storyEnd) {
        this.storyEnd = storyEnd;
    }

    public Hero[] getHeros() {
        return heros;
    }

    public void setSelectJobSceneState(SelectJobSceneState selectjobscenestate) {
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
        if (storyEnd) { //npc往回走出螢幕
            if (heroSelected.getWidth() < 50) {
                Global.hero = heroSelected;
                scenecontroller.changeScene(new MapScene(scenecontroller));
                Global.hero.sethealth(99);
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
        } else {
            if (job1screen.getIsClicked()) {
                g.drawImage(job1Screen1, 350, upY, 391, 322, null);
                g.drawImage(job1Screen2, 350, downY, 390, 184, null);

            }
            if (job2screen.getIsClicked()) {
                g.drawImage(job2Screen1, 1150, upY, 391, 322, null);
                g.drawImage(job2Screen2, 1150, downY, 390, 184, null);
            }

        }
        back.paint(g);

        for (Hero temp : heros) {
            if (temp != null) {
                temp.paint(g);
            }
        }

        if (selectjobscenestate instanceof beginTalk1) {
            g.drawImage(talkchart1, 1060, 170, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk2) {
            g.drawImage(talkchart2, 400, 170, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk3) {
            g.drawImage(talkchart3, 1060, 170, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk4) {
            g.drawImage(talkchart4, 400, 170, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk5) {
            g.drawImage(talkchart5, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk6) {
            g.drawImage(talkchart6, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk7) {
            g.drawImage(talkchart7, 400, 150, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk8) {
            g.drawImage(talkchart8, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (selectjobscenestate instanceof beginTalk9) {
            g.drawImage(talkchart9, 400, 150, 337, 230, null);
            next.paint(g);
        }
    }
}
