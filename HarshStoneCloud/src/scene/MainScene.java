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
import gameObject.Card.CardMoveState.MoveToHandDeck;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.Hero.Hero;
import gameObject.Hero.HeroState.MoveHeroLeave;
import gameObject.Hero.HeroState.MoveHeroRight;
import gameObject.Monster.Monster;
import gameObject.Monster.Monster2;
import gameObject.Monster.MonsterState;
import gameObject.Monster.MonsterState.DecideMove;
import gameObject.Skill.Skill;
import gameObject.Skill.SkillFactory;
import gameObject.Skill.SkillList;
import io.CommandSolver;
import io.CommandSolver.MouseCommandListener;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private SkillList skillboard; // 技能板
    private SkillFactory skillFactory;
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
    private Monster monster1;
    private Monster2 dedmon;
    private Timer timer;
    private DelayCounter delaycounter;
    private int selectedmonster;
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
    private Button exit;
    private Button exit2;
    private boolean allmonsterend;
    private boolean gameOver;
    private boolean gameWin;
    private BufferedImage endImage;

    public MainScene(SceneController scenecontroller, MapScene mapScene) {
        super(scenecontroller);

        gameWin = false;
        gameOver = false;
        font1 = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 14);
        xdelta = 0;
        ydelta = 0;
        cardclicked = false;
        heroturn = true;
        crystal = 3;
        cardlimit = 5;
        allmonsterend = false;
        skillboard = new SkillList();
        skillFactory = new SkillFactory();
        this.mapScene = mapScene;

//        drawcarddeck = new CardDeck(1400,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"抽牌推");
        discarddeck = new CardDeck(1300, 700, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "棄牌推");
        handdeck = new CardDeck(40, 490, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "手牌");

        selectedcard = null;
        discardcard = null;
        cardfactory = new CardFactory();

        exit = new Button(1600, 660, 108, 40, "EXIT");
        back = new Button(1600, 730, 108, 40, "BACK");
        next = new Button(1600, 800, 184, 42, "ROUNDSTART");

        hero = new Hero(0, Global.HEROY, Global.HEROWIDTH, Global.HEROXHEIGHT, " ", 100, 5);
        drawcarddeck = hero.getHeroDeck();

        orc = new Monster(Global.MONSTERX, 100 * (1 * 2 - 1), Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人1", 30, 1, (int) (Math.random() * 8), (int) (Math.random() * 8)); // 創建第一隻怪物 // 最後兩個參數為腳色變換跟技能光影挑選
        cultist = new Monster(Global.MONSTERX, 100 * (2 * 2 - 1), Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人2", 20, 2, (int) (Math.random() * 8), (int) (Math.random() * 8));// 創建第二隻怪物\
        monster1 = new Monster(Global.MONSTERX, 100 * (3 * 2 - 1), Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人3", 17, 3, (int) (Math.random() * 8), (int) (Math.random() * 8));// 創建第三隻怪物\
        dedmon = new Monster2(Global.MONSTERX, 100 * (4 * 2 - 1), Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人3", 17, 3, (int) (Math.random() * 8), (int) (Math.random() * 8));// 創建第三隻怪物\


        monsters = new ArrayList();
        monsters.add(orc);
        monsters.add(cultist);
        monsters.add(monster1);

        //下段可用迴圈新增(此段為將技能新增至怪物技能列(總數3))  (技能由技能工廠產生)
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println("gggggggg = " + monsters.size());
            skillboard.addMonsterSkill(skillFactory.genSkill(monsters.get(i).getskillIndex()));
            System.out.println(i + "=" + monsters.get(i).getskillIndex());

        }
        //迴圈新增(此段為怪物輪流從技能列拿取自身技能)
        for (int i = 0; i < skillboard.getMonsterSkillList().size(); i++) {
            monsters.get(i).setSkill(skillboard.getMonsterSkillList().get(i));
        }

        System.out.print(orc.toString());
        System.out.print(cultist.toString());
        System.out.print(monster1.toString());

        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        for (Monster monster : monsters) {
            monster.setHero(hero);
        }

//        winImage = irc.tryGetImage("/resources/Background/ENDSCENE.png");
        endImage = irc.tryGetImage("/resources/Background/ENDSCENE.png");
        img = irc.tryGetImage("/resources/Background/BACKGROUND3.jpg");
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
                        if (selectedcard.getY() < 560 && selectedcard.getDefense() > 0) {
                            selectedcard.action(hero, new Monster(0, 0, 0, 0, "", 0));
                            selectedcard.setCardMoveState(new MoveToDiscard());
                        }
                        if (!(selectedcard.getCardMoveState() instanceof MoveToDiscard)) {
                            for (int i = 0; i < monsters.size(); i++) {
                                if (monsters.get(i).isCollision(selectedcard)) {
                                    // 卡排放到怪物上的動畫
                                    if (skillboard.skillCheck(selectedcard.getSkilltype())) {
                                        skillboard.getCardSkill(selectedcard.getSkilltype()).setSkillend(false);
                                    } else {
                                        Skill tmp = skillFactory.genSkill(selectedcard.getSkilltype());
                                        skillboard.addCardSkill(tmp, monsters.get(i).getYposition());
                                        tmp.setSkillend(false);
                                    }//檢測MainScene的卡片技能區，如已有實體則使用，如無則新增//                                                              
                                    selectedcard.action(hero, monsters.get(i));
                                    selectedcard.setCardMoveState(new MoveToDiscard());
                                    break;
                                }
                                if (i == monsters.size() - 1) {
                                    selectedcard.setCardMoveState(new MoveBack());
                                }

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
                        for (int i = 0; i < temp; i++) {
                            handdeck.getCards().get(i).setCardMoveState(new EndTurnMove());
                        }
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        gameWin = true;
                        Global.CURRENTSTAGE++;
                        scenecontroller.changeScene(mapScene);

                    }
//                    if (selectedcard != null) {
//                        for (int i = 0; i < monsters.size(); i++) {
//                            if (monsters.get(i).isCollision(selectedcard)) {
//                                selectedcard.action(hero, monsters.get(i));
//                                selectedcard.setCardMoveState(new MoveToDiscard());
//                                break;
//                            }
//                            if (i == monsters.size() - 1) {
//                                selectedcard.setCardMoveState(new MoveBack());
//
//                            }
//                        }
//                        selectedcard = null;
//                    }
                    if (exit.isCollision(e.getX(), e.getY())) {
                        gameOver = true;
                        sceneEnd();
                    }
//                    if (exit2.isCollision(e.getX(), e.getY())) {
//                        scenecontroller.changeScene(new MenuScene(scenecontroller));
//                    }
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
//                                sceneEnd();
                            }
                        }
                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                    if (selectedcard != null) {
                        selectedcard.setX(e.getX() - xdelta);
                        selectedcard.setY(e.getY() - ydelta);
                        System.out.print(selectedcard.getX());
                        System.out.print(selectedcard.getY());
                    }
                }
            }

        };
    }

    public void drawCard(CardDeck drawcarddeck, CardDeck handdeck, CardDeck discarddeck) {
        int temp = handdeck.getCards().size();
        for (int i = 0; i < temp; i++) {
            discarddeck.getCards().add(handdeck.getCards().get(i));
        }
        System.out.println(1);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();

        for (int i = 0; i < temp; i++) {
            handdeck.getCards().remove(0);
        }
        System.out.println(2);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        if (drawcarddeck.getCards().size() < 5) {
            temp = discarddeck.getCards().size();
            for (int i = 0; i < temp; i++) {
                drawcarddeck.getCards().add(discarddeck.getCards().get(i));
            }
            System.out.println(3);
            System.out.println(drawcarddeck.toString());
            System.out.println(handdeck.toString());
            System.out.println(discarddeck.toString());
            System.out.println();
//        //確認每張牌都被刪掉

            for (int i = 0; i < temp; i++) {
                discarddeck.getCards().remove(0);
            }

        }
        System.out.println(4);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        drawcarddeck.shuffle();
        for (int i = 0; i < cardlimit; i++) {
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        setDeckPoisition();

        System.out.println(5);
        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        System.out.println();
        for (int i = 0; i < cardlimit; i++) {
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
    public void setDeckPoisition() {
        for (int i = 0; i < cardlimit; i++) {
            Card temp = handdeck.getCards().get(i);
//            temp.setX(300 + (Global.CARDWIDTH + 50) * i);
//            temp.setY(Global.CARDDECKBOTTOM);
//            temp.setOrginalX(300 + (Global.CARDWIDTH + 50) * i);
//            temp.setOrginalY(Global.CARDDECKBOTTOM);
//            temp.setCardMoveState(new Movable());
            temp.setX(0);
            temp.setY(Global.CARDDECKBOTTOM);
            temp.setOrginalX(300 + (Global.CARDWIDTH + 50) * i);
            temp.setOrginalY(Global.CARDDECKBOTTOM);
            temp.setHandDeckPoisition(i+1);
            temp.setCardMoveState(new MoveToHandDeck());
        }  

    }

    @Override
    public void sceneBegin() {
        hero.setState(new MoveHeroRight());
        
        for (Monster monster : monsters) {
            if (monster.gethealth() <= 0) {
                monsters.remove(monster);
            }
            monster.move();
        }

        drawcarddeck.shuffle();
        for (int i = 0; i < cardlimit; i++) {
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        for (int i = 0; i < cardlimit; i++) {
            drawcarddeck.getCards().remove(0);
        }
        setDeckPoisition();

    }

    @Override
    public void sceneUpdate() {
        hero.move();
        if(hero.getX() > Global.JWIDTH){
            Global.CURRENTSTAGE++;
            scenecontroller.changeScene(mapScene);
        }
        
        if (delaycounter.delayupdate()) {
            for (int i = 0; i < handdeck.getCards().size(); i++) {
                handdeck.getCards().get(i).move();
            }
            for (int i = 0; i < discarddeck.getCards().size(); i++) {
                discarddeck.getCards().get(i).move();
            }
        }

        for (int i = 0; i < monsters.size(); i++) {
            

            if (monsters.get(i).gethealth() <= 0) {
                monsters.remove(monsters.get(i));
                i--;
                if (monsters.size() == 0) {
                    hero.setState(new MoveHeroLeave());
                }
            }
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

            for (Monster monster : monsters) {
                if (!(monster.getMonsterState() instanceof MonsterState.EndMove)) {
                    monster.move();
                    allmonsterend = false;
                    break;
                }
                else{
                    allmonsterend = true;
                }
            }
                
            if (allmonsterend) {
                for (Monster temp : monsters) {
                    temp.setMoved(false);
                    temp.setMonsterState(new DecideMove());
                    temp.move();
                }
                next.setIsClicked(false);
                drawCard(drawcarddeck, handdeck, discarddeck);
               
            }
            
        }
    }

    @Override
    public void sceneEnd() {

//        try {
//            Robot robot = new Robot();
//            BufferedImage image = robot.createScreenCapture(new Rectangle(1920, 1080));
//            image = image.getSubimage(0, 0, 1728, 972);
//            ImageIO.write(image, "png", new File("C:/Users/User/Documents/期中專案/HarshStoneCloud/src/resources/1.png"));
//            endImage = image;
//        } catch (AWTException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            
//        }
        exit2 = new Button(900, 860, 108, 40, "BACK");
    }

    @Override
    public void paint(Graphics g) {
//        g.setFont(font1);
//        g.drawString("14pt bold & italic times Roman", 5, 92);
        g.drawImage(img, 0, 0, 1920, 1080, null);

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

        for (int i = 0; i < skillboard.getCardSkillList().size(); i++) {
            skillboard.getCardSkillList().get(i).paint(g);
        }
        for (int i = 0; i < handdeck.getCards().size(); i++) {
            handdeck.getCards().get(i).paint(g);
        }

        back.paint(g);
        exit.paint(g);
        drawcarddeck.paint(g);
        discarddeck.paint(g);

        if (gameOver) {
            g.drawImage(endImage, 0, 0, 1920, 1080, null);
            exit2.paint(g);
        }
        
//        if (gameWin) {    
//            g.drawImage(winImage, 0, 0, 1920, 1080, null);
//             exit2.paint(g);
//        }


    }

}
