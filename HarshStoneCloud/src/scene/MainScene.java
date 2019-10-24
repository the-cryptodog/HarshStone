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
import gameObject.Card.CardMoveState.EndTurnMove;
import gameObject.Card.CardMoveState.Movable;
import gameObject.Card.CardMoveState.MoveBack;
import gameObject.Card.CardMoveState.MoveToDiscard;
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
import java.awt.Font;
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
    private int cardlimit;
    private Font font1;
    
    public MainScene(SceneController scenecontroller, MapScene mapScene) {
        super(scenecontroller);
        font1 =new Font("TimesRoman", Font.BOLD+Font.ITALIC,14);
        xdelta = 0;
        ydelta = 0;
        cardclicked = false;
        heroturn = true;
        crystal = 3;
        cardlimit = 5;
//        drawcarddeck = new CardDeck(1400,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"抽牌推");
       
        discarddeck = new CardDeck(40,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"棄牌推");
        handdeck = new CardDeck(1600,700,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"手牌");

        selectedcard = null;
        discardcard = null;
        cardfactory = new CardFactory();
        

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
                                selectedcard.setCardMoveState(new MoveToDiscard());
                                break;
                            }
                            if(i == monsters.size()-1){
                                selectedcard.setCardMoveState(new MoveBack());
                            }
                        }
                        selectedcard = null;
                    }
                        
                }
                    

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("clicked");
                    if (next.isCollision(e.getX(), e.getY())) {
                        next.setIsClicked(true);
                    
                        int temp = handdeck.getCards().size();
                        for(int i = 0;i < temp; i++){
                            handdeck.getCards().get(i).setCardMoveState(new EndTurnMove());
                        }
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(mapScene);

                    }
                    if (selectedcard != null) {
                        for (int i = 0; i < monsters.size(); i++) {
                            if (monsters.get(i).isCollision(selectedcard)) {
                                selectedcard.action(hero, monsters.get(i));
                                selectedcard.setCardMoveState(new MoveToDiscard());
                                break;
                            }
                            if(i == monsters.size()-1){
                                selectedcard.setCardMoveState(new MoveBack());
                            
                            }
                        }
                        selectedcard = null;
                    }

                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    System.out.println("PRESS");
                    if (selectedcard == null) {
                        for (int i = 0; i < handdeck.getCards().size(); i++) {
                            if (handdeck.getCards().get(i).isCollision(e.getX(), e.getY())) {
                                Card temp = handdeck.getCards().get(i);
                                selectedcard = temp;
                                xdelta = temp.getDeltaX(e.getX());
                                ydelta = temp.getDeltaY(e.getY());
                                temp.setClicked(true);
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

    public void drawCard(CardDeck drawcarddeck,CardDeck handdeck,CardDeck discarddeck) {
        int temp =  handdeck.getCards().size();
        for(int i = 0; i < temp; i++){
            discarddeck.getCards().add(handdeck.getCards().get(i));
        }
        System.out.println(1);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
         System.out.println();
        
        for(int i = 0; i< temp;i++){
               handdeck.getCards().remove(0);
        }
        System.out.println(2);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        if(drawcarddeck.getCards().size() < 5){
            temp = discarddeck.getCards().size();
            for(int i = 0; i< temp;i++){
                drawcarddeck.getCards().add(discarddeck.getCards().get(i));
            }
        System.out.println(3);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
//        //確認每張牌都被刪掉
            
            for(int i = 0; i< temp; i++){
                discarddeck.getCards().remove(0);
            }
        
        }
        System.out.println(4);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        drawcarddeck.shuffle();
        for(int i = 0; i < cardlimit; i++){
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        setDeckPoisition();
        
        System.out.println(5);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        for(int i = 0; i < cardlimit; i++){
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
    //同時讓牌變成可動
    public void setDeckPoisition(){
        for(int i = 0 ; i < cardlimit;i++){
            Card temp = handdeck.getCards().get(i);
            temp.setX(300 + (Global.CARDWIDTH + 50) * i);
            temp.setY(Global.CARDDECKBOTTOM);
            temp.setOrginalX(300 + (Global.CARDWIDTH + 50) * i);
            temp.setOrginalY(Global.CARDDECKBOTTOM);
            temp.setCardMoveState(new Movable());
        }
    
    }
    
    
    
    @Override
    public void sceneBegin() {
        drawcarddeck.shuffle();
        for(int i = 0; i < cardlimit; i++){
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
            Card temp = handdeck.getCards().get(i);
            
            
        }
        for(int i = 0; i < cardlimit; i++){
            drawcarddeck.getCards().remove(0);
        }
        setDeckPoisition();
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
    }

    @Override
    public void sceneUpdate() {
        
        if (delaycounter.delayupdate()){
            for(int i = 0;i < handdeck.getCards().size(); i++){
                handdeck.getCards().get(i).move();
            }
            for(int i = 0;i < discarddeck.getCards().size(); i++){
                discarddeck.getCards().get(i).move();
            }
        }
        
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
        g.setFont(font1);
        g.drawString("14pt bold & italic times Roman",5,92);
        g.drawImage(img, 0, 0, 1680, 1050, null);
        hero.paint(g);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).paint(g);
        }

//        g.setColor(Color.red);
//        g.drawRect(800,500,300,25);
//        g.fillRect(800, 500, (int)temp1, 25);
        next.paint(g);
        g.setColor(Color.red);
        for (int i = 0; i < cardlimit; i++) {
            g.drawRect(300 + (Global.CARDWIDTH + 50) * i, Global.CARDDECKBOTTOM, Global.CARDWIDTH, Global.CARDHEIGHT);
            
        }
        
        for (int i = 0; i < handdeck.getCards().size(); i++) {
            handdeck.getCards().get(i).paint(g); 
        }
        
        back.paint(g);
        drawcarddeck.paint(g);
        discarddeck.paint(g);
    }

}
