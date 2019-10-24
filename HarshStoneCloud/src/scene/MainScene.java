/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.ImageResourceController;
import Controller.SceneController;
import gameObject.Button.Button;

import gameObject.Card.Card;
import gameObject.Card.CardDeck;
import gameObject.Card.CardFactory;
import gameObject.Cultist;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.Hero.Hero;
import gameObject.Monster.Monster;
import gameObject.Orc;
import gameObject.Skill.Skill;
import io.CommandSolver;
import io.CommandSolver.MouseCommandListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private Card card1;
    private Card card2;
    private Card card3;
    private boolean cardclicked;
    private Card selectedcard;
    private Card discardcard;
    private Button next;
    private Monster orc;
    private Hero hero;
    private Monster cultist;
    private Monster monster;
    private Timer timer;
    private DelayCounter delaycounter;
    private int selectedmonster;
    private Skill skill;
    private Boolean heroturn;
    private int crystal;
    private CardDeck drawcarddeck;
    private CardDeck discarddeck;
    private CardDeck handdeck;
    private Button back;
    private MapScene mapScene;
    private CardFactory cardfactory;

    public MainScene(SceneController scenecontroller, MapScene mapScene) {
        super(scenecontroller);
        xdelta = 0;
        ydelta = 0;
        cardclicked = false;
        heroturn = true;
        crystal = 3;
//        drawcarddeck = new CardDeck(1400,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"抽牌推");
       
        discarddeck = new CardDeck(40,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"棄牌推");
        handdeck = new CardDeck(1600,700,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"手牌");

        selectedcard = null;
        discardcard = null;
        cardfactory = new CardFactory();
        card1 = cardfactory.genCard(0);
        card2 = cardfactory.genCard(1);
        card3 = cardfactory.genCard(2);

        deck = new ArrayList();
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.add(cardfactory.genCard(0));


        deck.add(new DefenceEffect(new DamageEffect(new Card(40, 700, 150, 210, "破解系統", 2), 2), 3));
        back = new Button(1400, 600, 160, 80, "EXIT");
        next = new Button(1400, 800, 160, 80, "ROUNDSTART");

        hero = new Hero(Global.HEROX, Global.HEROY, Global.HEROWIDTH, Global.HEROXHEIGHT, " ", 100, 5);
        drawcarddeck = hero.getHeroDeck();
        orc = new Monster(Global.MONSTERX, 50, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 30, 6);
        cultist = new Monster(Global.MONSTERX, 300, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 20, 3);
        monster = new Monster(Global.MONSTERX, 500, Global.MONSTERWIDTH, Global.MONSTERHEIGHT, "獸人", 17, 2);
        monsters = new ArrayList();
        monsters.add(orc);
        monsters.add(cultist);
        monsters.add(monster);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        //10/22
        for (Monster monster : monsters) {
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
                        next.setIsClicked(true);
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(mapScene);

                    }
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

                if (state == CommandSolver.MouseState.PRESSED) {
                    System.out.println("PRESS");
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
                                        System.out.println("Dragggdhhjhjhjhhhdddggg");

                    if (selectedcard != null) {
                        selectedcard.setX(e.getX() - xdelta);
                        selectedcard.setY(e.getY() - ydelta);
                    }
                }
            }

        };
    }

    public void drawCard(CardDeck drawcarddeck,CardDeck handdeck,CardDeck discarddeck) {
        for(int i = 0; i < handdeck.getCards().size();i++){
            discarddeck.getCards().add(handdeck.getCards().get(i));
        }
        System.out.println(1);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
         System.out.println();
        for(int i = 0; i< 5;i++){
               handdeck.getCards().remove(0);
        }
        System.out.println(2);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        if(drawcarddeck.getCards().size() < 5){
            
            for(int i = 0; i< discarddeck.getCards().size();i++){
                drawcarddeck.getCards().add(discarddeck.getCards().get(i));
            }
        System.out.println(3);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
//        //確認每張牌都被刪掉
            for(int i = 0; i< discarddeck.getCards().size();i++){
                discarddeck.getCards().remove(0);
            }
        
        }
        System.out.println(4);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
////        drawcarddeck.shuffle();
        for(int i = 0; i < 5; i++){
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        System.out.println(5);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        for(int i = 0; i < 5; i++){
            drawcarddeck.getCards().remove(0);
        }
        System.out.println(6);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
    }
    
    
    
    
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Button getNext() {
        return next;
    }

    public Monster getMonster() {
        return orc;
    }

    @Override
    public void sceneBegin() {
        for(int i = 0; i < 5; i++){
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        for(int i = 0; i < 5; i++){
            drawcarddeck.getCards().remove(0);
        }
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
    }

    @Override
    public void sceneUpdate() {
        for (Monster monster : monsters) {
            monster.update();
        }
        if (delaycounter.delayupdate() && discardcard != null) {
            discardcard.move();
        }

        if (delaycounter.delayupdate() && next.getIsClicked()) {
            for (Monster monster : monsters) {
                if (!monster.getMoved()) {
                    monster.move();
                    break;
                }
            }
            if (monsters.get(monsters.size() - 1).getMoved()) {

                for (Monster monster : monsters) {
                    monster.setMoved(false);
                }
                next.setIsClicked(false);
                drawCard(drawcarddeck,handdeck,discarddeck);
                
    
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
//        g.fillRect(800, 500, (int)temp1, 25);
        next.paint(g);
        for (int i = 0; i < deck.size(); i++) {
            deck.get(i).paint(g);
        }

//        Graphics2D g2 = (Graphics2D) g;
//        g2.drawString("There is no spoon.", 200, 400);
        g.setColor(Color.red);
        for (int i = 0; i < 5; i++) {
            g.drawRect(300 + (Global.CARDWIDTH + 50) * i, 700, Global.CARDWIDTH, Global.CARDHEIGHT);
            
        }
        
        back.paint(g);
        drawcarddeck.paint(g);
        discarddeck.paint(g);
    }

}
