/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.ImageResourceController;
import Controller.SceneController;
import gameObject.Button.StartButton;
import gameObject.Card;
import gameObject.CardFactory;
import gameObject.Cultist;
import gameObject.DamageEffect;
import gameObject.Hero.Hero;
import gameObject.Monster.Monster;
import gameObject.Orc;
import gameObject.Skill.Skill;
import gameObject.全力迎戰;
import gameObject.旋風斬;
import io.CommandSolver;
import io.CommandSolver.MouseCommandListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import utils.DelayCounter;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class MainScene extends Scene {

    private ArrayList<Card> deck;
    private ArrayList<Monster> monsters;
    private BufferedImage img;
    private int x1;
    private int y1;
    private int xdelta;
    private int ydelta;
    private 全力迎戰 card1;
    private 旋風斬 card2;
    private Card card3;
    private boolean cardclicked;
    private Card selectedcard;
    private Card discardcard;
    private StartButton next;
    private Monster orc;
    private Hero hero;
    private Monster cultist;
    private Monster monster;
    private Timer timer;
    private DelayCounter delaycounter;
    private int selectedmonster;
    private Skill skill;

    
    private CardFactory cardfactory;
    

    public MainScene(SceneController scenecontroller) {
        super(scenecontroller);
        xdelta = 0;
        ydelta = 0;
        cardclicked = false;
        selectedcard = null;
        discardcard = null;
        card1 = new 全力迎戰(30, 20, 141, 195, "全力迎戰", 3);
        card2 = new 旋風斬(181, 20, 141, 195, "旋風斬", 1);
        card3 = new Card(30, 700, 150, 210, "破解系統", 2);
        card3 = new DamageEffect(card3, 5);
        
        cardfactory = new CardFactory();
        
        
        deck = new ArrayList();
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(cardfactory.genCard(0));
        
        next = new StartButton(1200, 800, 200, 100, "下一步");
        hero = new Hero(Global.HEROX, Global.HEROY, Global.HEROWIDTH, Global.HEROXHEIGHT, " ", 100, 5);
        orc = new Monster(Global.MONSTERX, 50, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 100, 6);
        cultist = new Monster(Global.MONSTERX, 300, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 100, 3);
        monster = new Monster(Global.MONSTERX, 500, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 100, 2);
        monsters = new ArrayList();
        monsters.add(orc);
        monsters.add(cultist);
        monsters.add(monster);
        //10/22
        for(Monster monster: monsters){
            monster.setHero(hero);
        }
        
        
        img = irc.tryGetImage("/resources/Background/背景1.jpg");
        selectedmonster = 0;

        delaycounter = new DelayCounter(5, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });

        mousecommandlistener = new MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
                    if (selectedcard != null) {
                        for (int i = 0; i < monsters.size(); i++) {
                            if (monsters.get(i).isCollision(selectedcard)) {
                                selectedcard.action(hero, monsters.get(i));
                                discardcard = selectedcard;
                                break;
                            }
                        }
                    }
                    selectedcard = null;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("clicked");
                    if (next.isCollision(e.getX(), e.getY())) {
//                        scenecontroller.changeScene(new SecondScene(scenecontroller));
                        next.setIsClicked(true);

                    }

                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    if (selectedcard == null) {
                        for (int i = 0; i < deck.size(); i++) {
                            if (deck.get(i).isCollision(e.getX(), e.getY())) {
                                selectedcard = deck.get(i);
                                xdelta = deck.get(i).getDeltaX(e.getX());
                                ydelta = deck.get(i).getDeltaY(e.getY());
                                deck.get(i).setClicked(true);
                                sceneEnd();
                            }
                        }
                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                    if (selectedcard != null) {
                        selectedcard.setX(e.getX() - xdelta);
                        selectedcard.setY(e.getY() - ydelta);
                    }
                }
            }

        };
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public StartButton getNext() {
        return next;
    }

    public Monster getMonster() {
        return orc;
    }

    @Override
    public void sceneBegin() {

    }

    @Override
    public void sceneUpdate() {
        for(Monster monster: monsters){
            monster.update();
        }
        if(delaycounter.delayupdate() && discardcard != null){
            discardcard.move();
        }
        
        if(delaycounter.delayupdate() && next.getIsClicked()){
            for(Monster monster: monsters){
                if(!monster.getMoved()){ 
                    monster.move();
                    break;
                }
            }
            if(monsters.get(monsters.size() - 1).getMoved()){
               
                for(Monster monster : monsters){
                    monster.setMoved(false);
                }
                next.setIsClicked(false);               
            }
            
            
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(img, 0, 0, 1680, 1050, null);
        hero.paint(g);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).paint(g);
        }

//        g.setColor(Color.red);
//        g.drawRect(800,500,300,25);
//        float temp1 = (float)cultist.gethealth()/100 * 300;
//        g.fillRect(800, 500, (int)temp1, 25);
        next.paint(g);
        for (int i = 0; i < deck.size(); i++) {
            deck.get(i).paint(g);
        }

    }

}
