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
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import scene.LastSceneState.*;
import utils.DelayCounter;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class LastScene extends Scene{
    protected Hero hero;
    protected Hero npc;
    protected BufferedImage background1;
    protected BufferedImage background2;
    protected BufferedImage background3;
    protected BufferedImage conclusion;
    protected LastSceneState lastscenestate;
    private DelayCounter delaycounter;
    private Button next;
    private Button backtomenu;
    private BufferedImage talkchart21;
    private BufferedImage talkchart22;
    private BufferedImage talkchart23;
    private BufferedImage talkchart24;
    private BufferedImage talkchart25;
    private BufferedImage talkchart26;
    private BufferedImage talkchart27;
    private BufferedImage talkchart28;
    private BufferedImage talkchart29;
    private BufferedImage talkchart30;
    private BufferedImage talkchart31;
    private BufferedImage talkchart32;
    private int blockx;
    
    
    public LastScene(SceneController scenecontroller) {
        super(scenecontroller);
        hero = Global.hero;
        hero.setDefense(0);
        hero.setX(-100);
        hero.setY(300);
        npc = new Hero(Global.NPCX, Global.JOBY, 128, 128, "Actor3", 0, 4);
        background1 = irc.tryGetImage("/resources/Incidence/DARKEN3.png");
        background2 = irc.tryGetImage("/resources/Background/ENDSCENE.png");
        background3 = irc.tryGetImage("/resources/Background/BACKGROUND6.png");
        conclusion = irc.tryGetImage("/resources/Incidence/CONCLUSION.png");
        
        next = new Button(750, 550, 220, 50, "CONTINUE");
        backtomenu = new Button(900, 860, 210, 52, "BACKTOMENU");
        lastscenestate = new moveHero();
                delaycounter = new DelayCounter(5, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });

        
        talkchart21 = irc.tryGetImage("/resources/Incidence/TALK21.png");
        talkchart22 = irc.tryGetImage("/resources/Incidence/TALK22.png");
        talkchart23 = irc.tryGetImage("/resources/Incidence/TALK23.png");
        talkchart24 = irc.tryGetImage("/resources/Incidence/TALK24.png");
        talkchart25 = irc.tryGetImage("/resources/Incidence/TALK25.png");
        talkchart26 = irc.tryGetImage("/resources/Incidence/TALK26.png");
        talkchart27 = irc.tryGetImage("/resources/Incidence/TALK99.png");
        talkchart28 = irc.tryGetImage("/resources/Incidence/TALK27.png");
        talkchart29 = irc.tryGetImage("/resources/Incidence/TALK28.png");
        talkchart30 = irc.tryGetImage("/resources/Incidence/TALK7.png");
        talkchart31 = irc.tryGetImage("/resources/Incidence/TALK29.png");
        talkchart32 = irc.tryGetImage("/resources/Incidence/TALK30.png");
        
 
        
        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    
                    
                    if (next.isCollision(e.getX(), e.getY())) {
                        
                        if (lastscenestate instanceof LastSceneState.beginTalk1) {
                            System.out.print("事件1發生!!!");
                            lastscenestate = new moveNpc();
                            return;
                        }
                    
                        else if (lastscenestate instanceof LastSceneState.beginTalk2) {
                            System.out.print("事件2發生!!!");
                            lastscenestate = new beginTalk3();
                            return;
                        }
                    
                        else if (lastscenestate instanceof LastSceneState.beginTalk3) {
                            System.out.print("事件3發生!!!");
                            lastscenestate = new beginTalk4();
                            return;
                        }
                    
                   
                        else if (lastscenestate instanceof LastSceneState.beginTalk4) {
                            System.out.print("事件4發生!!!");
                            lastscenestate = new beginTalk5();
                            return;
                        }
                    
                   
                        else if (lastscenestate instanceof LastSceneState.beginTalk5) {
                            System.out.print("事件5發生!!!");
                            lastscenestate = new beginTalk6();
                            return;
                        }
                    
                   
                        else if (lastscenestate instanceof LastSceneState.beginTalk6) {
                            System.out.print("事件6發生!!!");
                            lastscenestate = new beginTalk7();
                            return;
                        }
                    
                    
                        else if (lastscenestate instanceof LastSceneState.beginTalk7) {
                            System.out.print("事件7發生!!!");
                            lastscenestate = new beginTalk8();
                            return;
                        }
                    
                    
                        else if (lastscenestate instanceof LastSceneState.beginTalk8) {
                            System.out.print("事件8發生!!!");
                            lastscenestate = new beginTalk9();
                            return;
                        }
                        
                        else if (lastscenestate instanceof LastSceneState.beginTalk9) {
                            System.out.print("事件9發生!!!");
                            lastscenestate = new beginTalk10();
                            return;
                        }
                        else if (lastscenestate instanceof LastSceneState.beginTalk10) {
                            System.out.print("事件10發生!!!");
                            lastscenestate = new beginTalk11();
                            return;
                        }
                        else if (lastscenestate instanceof LastSceneState.beginTalk11) {
                            System.out.print("事件11發生!!!");
                            lastscenestate = new beginTalk12();
                            return;
                        }
                        else if (lastscenestate instanceof LastSceneState.beginTalk12) {
                            System.out.print("事件12發生!!!");
                            lastscenestate = new changeBackGround();
                            return;
                        }
                    }
                    
                    
                    if(backtomenu.isCollision(e.getX(), e.getY())){
                        if (lastscenestate instanceof changeBackGround){
                            lastscenestate = new conclusion();
                        
                        }
                        else if (lastscenestate instanceof conclusion){
                            
                            scenecontroller.changeScene(new MenuScene(scenecontroller));
                        }
                    
                    
                    
                    
                    
                    
                    
                    
                    }
                    
                    
                    
                    
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    
                }
                if (state == CommandSolver.MouseState.MOVED) {
                    
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                }
            }
        };
    }
    
    
    public void setLastSceneState(LastSceneState lastscenestate){
        this.lastscenestate = lastscenestate;
    }
    
    

    @Override
    public void sceneBegin() {
        
    }

    @Override
    public void sceneUpdate() {
        if(delaycounter.delayupdate()){
            lastscenestate.action(this);
        }
    }

    @Override
    public void sceneEnd() {
       
    }

    @Override
    public void paint(Graphics g) {
        
        g.drawImage(background1, 0, 0, 1920, 1080, null);
                
        hero.paint(g);
        npc.paint(g);
        
        if (lastscenestate instanceof LastSceneState.beginTalk1) {
            g.drawImage(talkchart21, 400, 170, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk2) {
            g.drawImage(talkchart22, 1060, 170, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk3) {
            g.drawImage(talkchart23, 400, 170, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk4) {
            g.drawImage(talkchart24, 1060, 170, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk5) {
            g.drawImage(talkchart25, 400, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk6) {
            g.drawImage(talkchart26, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk7) {
            g.drawImage(talkchart27, 400, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk8) {
            g.drawImage(talkchart28, 400, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk9) {
            g.drawImage(talkchart29, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk10) {
            g.drawImage(talkchart30, 400, 150, 337, 230, null);
            next.paint(g);
        }
        
        else if (lastscenestate instanceof LastSceneState.beginTalk11) {
            g.drawImage(talkchart31, 1060, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.beginTalk12) {
            g.drawImage(talkchart32, 400, 150, 337, 230, null);
            next.paint(g);
        }
        else if (lastscenestate instanceof LastSceneState.changeBackGround) {
            g.drawImage(background2, 0, 0, 1920, 1080, null);
            backtomenu.paint(g);
        }
        else if(lastscenestate instanceof LastSceneState.conclusion) {
            g.drawImage(background3, 0, 0, 1920, 1080, null);
            g.drawImage(conclusion, 320, 310, 1281, 461, null);
            backtomenu.paint(g);
        }
    }
    
}
