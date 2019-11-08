/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import Controller.SceneController;
import PopUpWindow.Award;
import PopUpWindow.Incidence;
import gameObject.Button.Button;

import gameObject.Card.Card;
import gameObject.Card.CardDeck;
import gameObject.Card.CardFactory;
import gameObject.Card.CardMoveState;
import gameObject.Card.CardMoveState.DiscardRareCard;
import gameObject.Card.CardMoveState.EndTurnMove;
import gameObject.Card.CardMoveState.Movable;
import gameObject.Card.CardMoveState.MoveBack;
import gameObject.Card.CardMoveState.MoveStop;
import gameObject.Card.CardMoveState.MoveToDiscard;
import gameObject.Card.CardMoveState.MoveToHandDeck;
import gameObject.Column;
import gameObject.Crystal;
import gameObject.Curtain;
import gameObject.DamageEffect;
import gameObject.DefenseEffect;
import gameObject.Hero.Hero;
import gameObject.Hero.HeroState.MoveHeroLeave;
import gameObject.Hero.HeroState.MoveHeroRight;
import gameObject.Monster.Monster;

import gameObject.Monster.MonsterState;
import gameObject.Monster.MonsterState.DecideMove;
import gameObject.Monster.MonsterState.EndMove;
import gameObject.Monster.Poison;
import gameObject.NumberIcon;
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
import javafx.scene.media.AudioClip;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class MainScene extends Scene {

    private ArrayList<Card> deck;
    private ArrayList<Monster> turnstartmonsters;
    private ArrayList<Monster> monsters;
    private SkillList skillboard; // 技能板
    private SkillFactory skillFactory;
    private BufferedImage img;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage currentImg;
    private Curtain  curtains;
    private int xdelta;
    private int ydelta;
    private boolean cardclicked;
    private Card selectedcard;
    private Card discardcard;
    private Monster orc;
    private Monster cultist;
    private Monster monster1;
    private Monster forHeal;

    private Timer timer;
    private DelayCounter delaycounter;
    private int selectedmonster;
    private Boolean heroturn;
    private CardDeck drawcarddeck;
    private CardDeck discarddeck;
    private CardDeck handdeck;
    private Button back;
    private Button next;
    private Button exit;
    private Button backtothefuture;
    private MapScene mapScene;
    private CardFactory cardfactory;
    private int cardlimit;
    private Font font1;
    private boolean allmonsterend;
    private boolean gameOver;
    private boolean gameWin;
    private boolean useheroskill;
    private int winGameCount;
    private BufferedImage endImage;
    private Column column;
    private Crystal crystal;
    private Poison ttt;
    private Hero hero;
    private Award heroawards;
    private Card selectedRareCard;
    private AudioClip currentSountrack;
    private AudioClip soundtrack1;
    private AudioClip soundtrack2;
    private AudioClip soundtrack3;
    private AudioClip soundtrack4;
    private AudioClip soundtrack5;
    private AudioClip gameWinSound;
    private AudioClip shield;
    private Skill defense;
    private Skill harshStone;

    public MainScene(SceneController scenecontroller, MapScene mapScene) {
        super(scenecontroller);
//        System.out.println(PathBuilder.getNumber(ImagePath.NUMBER2));
//        number = ImageResourceController.getInstance().tryGetImage("/resources/Number/Number2.png");
        Global.background.stop();
        crystal = new Crystal(Global.HEROX - 32, Global.HEROY + 192, 80, 80, "水晶");
        gameWin = false;
        gameOver = false;
        xdelta = 0;
        ydelta = 0;
        cardclicked = false;
        heroturn = true;
        cardlimit = 5;
        allmonsterend = false;
        useheroskill = false;
        skillboard = new SkillList();
        skillFactory = new SkillFactory();
        this.mapScene = mapScene;
        winGameCount = 0;

        hero = Global.hero;
        Global.hero.setX(-400);
        Global.hero.setY(Global.HEROY);
        Global.hero.setWidth(Global.HEROWIDTH);
        Global.hero.setHeight(Global.HEROHEIGHT);

        //測試用暫時留著
//        hero = new Hero(-70, Global.HEROY, Global.HEROWIDTH, Global.HEROXHEIGHT, " ", 100, 5);
        drawcarddeck = hero.getHeroDeck();
//        drawcarddeck = new CardDeck(1400,690,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"抽牌推");
        discarddeck = new CardDeck(Global.DISCARDDECKX, 700, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "棄牌推");
        handdeck = new CardDeck(40, 490, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "手牌");

        selectedcard = null;
        discardcard = null;
        cardfactory = new CardFactory();

        exit = new Button(1700, 460, 108, 40, "EXIT");
        back = new Button(1700, 520, 108, 40, "BACK");
        next = new Button(1700, 580, 184, 42, "ROUNDSTART");
        backtothefuture = new Button(70, 640, 234, 42, "HARSHSTONE");

//        hero = Global.hero;
//        Global.hero.setX();
//        Global.hero.setY(Global.HEROY);
//        Global.hero.setWidth(Global.HEROWIDTH);
//        Global.hero.setHeight(Global.HEROXHEIGHT);
        drawcarddeck = hero.getHeroDeck();
        heroawards = new Award(150, 90, 1770, 990, "AWARD");
        turnstartmonsters = new ArrayList();
        monsters = new ArrayList();
        forHeal = new Monster(0, 0, 0, 0, "專門吃治療", 0, 0, 0, 0, true);
        defense = skillFactory.genSkill(11);
        harshStone = skillFactory.genSkill(12);



        //若不是魔王關創三隻怪
        if (Global.CURRENTSTAGE < 5) {
            orc = new Monster(Global.MONSTERX, Global.MONSTERY, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                    "獸人1", 14, 1, (int) (Math.random() * 8), (int) (Math.random() * 8), false); // 創建第一隻怪物 // 最後兩個參數為腳色變換跟技能光影挑選
            cultist = new Monster(Global.MONSTERX, Global.MONSTERY2, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                    "獸人2", 10, 2, (int) (Math.random() * 8), (int) (Math.random() * 8), false);// 創建第二隻怪物\
            monster1 = new Monster(Global.MONSTERX, Global.MONSTERY3, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                    "獸人3", 17, 3, (int) (Math.random() * 8), (int) (Math.random() * 8), false);// 創建第三隻怪物\
            monsters.add(orc);
            monsters.add(cultist);
            monsters.add(monster1);
        }

        if (Global.CURRENTSTAGE >= 5) {
            orc = new Monster(Global.BOSSX, Global.BOSSY, Global.BOSSWIDTH, Global.BOSSHEIGHT,
                    "獸人1", 70, 100, 13, (int) (Math.random() * 8), true); // 創建第一隻怪物 // 最後兩個參數為腳色變換跟技能光影挑選
            monsters.add(orc);
        }

        column = new Column(0, 0, 0, 0, "Column");

        //下段可用迴圈新增(此段為將技能新增至怪物技能列(總數3))  (技能由技能工廠產生)
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println("gggggggg = " + monsters.size());
            skillboard.addMonsterSkill(skillFactory.genSkill(monsters.get(i).getskillIndex()));
            skillboard.getMonsterSkillList().get(i).setY(Global.HEROY);
            System.out.println(i + "=" + monsters.get(i).getskillIndex());

        }
        //迴圈新增(此段為怪物輪流從技能列拿取自身技能)
        for (int i = 0; i < skillboard.getMonsterSkillList().size(); i++) {
            monsters.get(i).setSkill(skillboard.getMonsterSkillList().get(i));
        }

        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        for (Monster monster : monsters) {
            monster.setHero(hero);
        }
        

        
        
        endImage = irc.tryGetImage("/resources/Background/ENDSCENE.png");

        img = irc.tryGetImage("/resources/Background/BACKGROUND1.jpg");
        img2 = irc.tryGetImage("/resources/Background/BACKGROUND2.jpg");
        img3 = irc.tryGetImage("/resources/Background/BACKGROUND3.jpg");
        img4 = irc.tryGetImage("/resources/Background/BACKGROUND4.jpg");
        img5 = irc.tryGetImage("/resources/Background/BACKGROUND5.jpg");

        switch (Global.CURRENTSTAGE) {
            case 1:
                currentImg = img;
                soundtrack1 = acrc.tryGetAudioClip("/resources/Audio/Battle_01.wav");
                currentSountrack = soundtrack1;
                break;
            case 2:
                currentImg = img2;
                soundtrack2 = acrc.tryGetAudioClip("/resources/Audio/Battle_02.wav");
                currentSountrack = soundtrack2;
                break;
            case 3:
                currentImg = img3;
                soundtrack3 = acrc.tryGetAudioClip("/resources/Audio/Battle_03.wav");
                currentSountrack = soundtrack3;
                break;
            case 4:
                currentImg = img4;
                soundtrack4 = acrc.tryGetAudioClip("/resources/Audio/Battle_04.wav");
                currentSountrack = soundtrack4;
                break;
            case 5:
                currentImg = img5;
                soundtrack5 = acrc.tryGetAudioClip("/resources/Audio/Battle_05.mp3");
                currentSountrack = soundtrack5;

        }

        gameWinSound = acrc.tryGetAudioClip("/resources/Audio/WIN.wav");
        shield = acrc.tryGetAudioClip("/resources/Audio/12.mp3");
        currentSountrack.setVolume(0.2d);
        currentSountrack.play();

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
                if (state != null) {
                    System.out.println(state);
                } else {
                    System.out.println("沒狀態");
                }
                if (state == CommandSolver.MouseState.RELEASED) {

                    if (selectedcard != null) {
                        //水晶不夠無法施放
                        int temp = crystal.getNumberIcon().getNumber();
                        if (temp - selectedcard.getCost() < 0) {
                            selectedcard.setCardMoveState(new MoveBack());
                        } //                        else if (selectedcard.getY() < 560 && selectedcard.getDefense() > 0) {
                        //                            selectedcard.action(hero, new Monster(0, 0, 0, 0, "", 0));
                        //                            selectedcard.setCardMoveState(new MoveToDiscard());
                        //                            crystal.setNumberIcon(temp - selectedcard.getCost());
                        //                        }
                        else if (!(selectedcard.getCardMoveState() instanceof MoveToDiscard)) {
                              if (selectedcard.getY() < 590 ){
                                if (selectedcard.getHeal() != 0 )  {
                                    if (skillboard.skillCheck(selectedcard.getSkillIndex())) {
                                        System.out.print("vvvvvv" + "治療發生" + "vvvvvv");
                                        Skill tempSkill = skillboard.getCardSkill(selectedcard.getSkillIndex());
                                        tempSkill.positionSetter(hero);
                                        
                                        tempSkill.setSkillend(false);
                                        tempSkill.getEffectSound().play();
                                    } else {              
                                        Skill tmp = skillFactory.genSkill(selectedcard.getSkillIndex()-1);
                                        System.out.print(hero.getX() + " == x");
                                        tmp.positionSetter(hero);
                                        
                                        skillboard.addCardSkill(tmp);
                                        tmp.setSkillend(false);
                                        tmp.getEffectSound().play();
                                    }
                                    if (selectedcard.getDefense() != 0) {     
                                         defense.positionSetter(hero);
                                         defense.setSkillend(false);
                                         shield.play();
                                    }
                                    selectedcard.action(hero, forHeal);
                                    selectedcard.setCardMoveState(new MoveToDiscard());
                                    crystal.setNumberIcon(temp - selectedcard.getCost());
                                    selectedcard = null;
                                    return;
                                }
                                if (selectedcard.getDefense() != 0 && selectedcard.getSkillIndex() == 12) {                                             
                                    {
                                        defense.positionSetter(hero);
                                        defense.setSkillend(false);
                                        shield.play();
                                    }
                                    selectedcard.action(hero, forHeal);
                                    selectedcard.setCardMoveState(new MoveToDiscard());
                                    crystal.setNumberIcon(temp - selectedcard.getCost());
                                    selectedcard = null;
                                    return;
                                }
                            }
                            for (int i = 0; i < monsters.size(); i++) {
                                System.out.println(selectedcard.toString());
                                if (monsters.get(i).isCollision(selectedcard)) {
                                    System.out.print(selectedcard.toString());
                                    // 卡排放到怪物上的動畫
                                    Monster temp1 = monsters.get(i);
                                    if (skillboard.skillCheck(selectedcard.getSkillIndex())) {
                                        Skill tempSkill = skillboard.getCardSkill(selectedcard.getSkillIndex());
                                        tempSkill.setY(monsters.get(i).getY());
                                        tempSkill.positionSetter(temp1);
                                        tempSkill.setSkillend(false);
                                        tempSkill.getEffectSound().play();
                                    } else {
                                        System.out.print("vvvvvv" + selectedcard.getSkillIndex() + "vvvvvv");
                                        Skill tmp = skillFactory.genSkill(selectedcard.getSkillIndex());
                                        tmp.positionSetter(temp1);
                                        skillboard.addCardSkill(tmp);
                                        tmp.setSkillend(false);
                                        tmp.getEffectSound().play();
                                    }//檢測MainScene的卡片技能區，如已有實體則使用，如無則新增//                                                              
                                    selectedcard.action(hero, temp1);
                                    temp1.updateNumberIcon();
                                    if (selectedcard.getDefense() != 0) {
                                        defense.positionSetter(hero);
                                        defense.setSkillend(false);
                                        shield.play();
                                        System.out.print("vvvvvv盾牌vvvvvv");
                                    }
                                    selectedcard.setCardMoveState(new MoveToDiscard());
                                    crystal.setNumberIcon(temp - selectedcard.getCost());
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
                    if (next.isCollision(e.getX(), e.getY()) && !(handdeck.getCards().get(4).getCardMoveState() instanceof MoveToHandDeck)) {
                        next.setIsClicked(true);
                        int temp = handdeck.getCards().size();
                        for (int i = 0; i < temp; i++) {
                            handdeck.getCards().get(i).setCardMoveState(new EndTurnMove());
                        }
                    }
                    if (back.isCollision(e.getX(), e.getY())) {
                        gameWin = true;
//                        Global.CURRENTSTAGE++;
//                        scenecontroller.changeScene(mapScene);
                        heroawards = new Award(0, 0, 0, 0, "AWARD");
                        heroawards.setCommandListener(mousecommandlistener);
                    }
                    if (gameWin) {
                        for (int i = 0; i < heroawards.getAward().size(); i++) {
                            if (heroawards.getAward().get(i).isCollision(e.getX(), e.getY())) {
                                selectedRareCard = heroawards.getAward().get(i);
                                selectedRareCard.setClicked(true);

                                System.out.print("選定卡片!");

                                for (int j = 0; j < heroawards.getAward().size(); j++) {
                                    if (!heroawards.getAward().get(j).getClicked()) {
                                        System.out.print("丟棄卡片!");
                                        heroawards.getAward().get(j).setCardMoveState(new DiscardRareCard());
                                        heroawards.setSelected(true);
                                    }
                                }
//                            System.out.print("選定卡片!");
                                //                        Global.CURRENTSTAGE++;
//                        scenecontroller.changeScene(mapScene);
                            }
                        }
                        if(Global.CURRENTSTAGE == 1){
                            scenecontroller.changeScene(new LastScene(scenecontroller));
                        
                        
                        }
                        
                        
                        
                    }
                    if (heroawards.getButton().isCollision(e.getX(), e.getY())) {
                        currentSountrack.stop();
                        scenecontroller.changeScene(mapScene);
                        selectedRareCard.getCardIconHelper().setAf(1);
                        selectedRareCard.setWidth(Global.CARDWIDTH);
                        selectedRareCard.setHeight(Global.CARDHEIGHT);
                        Global.hero.getHeroDeck().getCards().add(selectedRareCard);
                        arrangeDeck();

                    }

                    if (exit.isCollision(e.getX(), e.getY())) {
//                        gameOver = true;
                        scenecontroller.changeScene(new EndScene(scenecontroller));
                    }

                    if (backtothefuture.isCollision(e.getX(), e.getY()) && useheroskill == false) {
                        harshStone.setX(450);
                        harshStone.setY(120);
                        harshStone.setWidth(800);
                        harshStone.setHeight(800);
                        harshStone.getEffectSound().play();
                        harshStone.setSkillend(false);
                        useheroskill = true;
                        Global.hero.setDefense(0);
                        crystal.getNumberIcon().setNumber(3);
                        transformTurnStartMonsters();
                        handdeck.cardTransform(drawcarddeck);
                        drawCard(drawcarddeck, handdeck, discarddeck, 5);
                    }

//                    if (exit2.isCollision(e.getX(), e.getY())) {
//                        scenecontroller.changeScene(new MenuScene(scenecontroller));
//                    }
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    System.out.println("PRESS");
                    if (selectedcard == null) {
                        for (int i = 0; i < handdeck.getCards().size(); i++) {
                            if (handdeck.getCards().get(i).getCardMoveState() instanceof MoveStop
                                    && handdeck.getCards().get(i).isCollision(e.getX(), e.getY())) {
                                Card temp = handdeck.getCards().get(i);
                                selectedcard = temp;
                                selectedcard.setX(selectedcard.getOrginalX());
                                selectedcard.setY(selectedcard.getOrginalY());
                                selectedcard.getCardIconHelper().setAf(1);
                                selectedcard.setWidth(Global.CARDWIDTH);
                                selectedcard.setHeight(Global.CARDHEIGHT);

                                xdelta = temp.getDeltaX(e.getX());
                                ydelta = temp.getDeltaY(e.getY());
                                temp.setClicked(true);
                            }
                        }
                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                    if (selectedcard != null) {
                        System.out.println("dragg");

                        selectedcard.setX(e.getX() - xdelta);
                        selectedcard.setY(e.getY() - ydelta);
                        System.out.print(selectedcard.getX());
                        System.out.print(selectedcard.getY());
                    }
                }
                if (state == CommandSolver.MouseState.MOVED) {
//                    if (selectedcard == null) {
                    for (int i = 0; i < handdeck.getCards().size(); i++) {
                        if (handdeck.getCards().get(i).getCardMoveState() instanceof MoveStop
                                && handdeck.getCards().get(i).isCollision(e.getX(), e.getY())) {
                            handdeck.getCards().get(i).getCardIconHelper().setAf(Global.INSPECTSIZE);
                            handdeck.getCards().get(i).setY(600);
                            handdeck.getCards().get(i).setWidth(Global.INSPECTCARDWIDTH);
                            handdeck.getCards().get(i).setHeight(Global.INSPECTCARDHEIGHT);
//                                sceneEnd();
                        } else {
                            if (handdeck.getCards().get(i).getCardMoveState() instanceof MoveStop) {
                                handdeck.getCards().get(i).setY(700);
                            }
                            handdeck.getCards().get(i).getCardIconHelper().setAf(1);
                            handdeck.getCards().get(i).setWidth(Global.CARDWIDTH);
                            handdeck.getCards().get(i).setHeight(Global.CARDHEIGHT);
                        }

                    }
//                    }
                }
            }
        };
    }

    //抽n張卡
    public void drawCard(CardDeck drawcarddeck, CardDeck handdeck, CardDeck discarddeck, int number) {
        int temp = discarddeck.getCards().size();

        if (drawcarddeck.getCards().size() < 5) {
            for (int i = 0; i < temp; i++) {
                drawcarddeck.getCards().add(discarddeck.getCards().get(i));
            }

//        //確認每張牌都被刪掉    
            discarddeck.cardRemove();

        }

        drawcarddeck.shuffle();
        for (int i = 0; i < number; i++) {
            handdeck.getCards().add(drawcarddeck.getCards().get(i));
        }
        setDeckPoisition();

        for (int i = 0; i < number; i++) {
            drawcarddeck.getCards().remove(0);
        }

    }

    //棄n張卡
    public void discardCard(CardDeck drawcarddeck, CardDeck handdeck, CardDeck discarddeck, int number) {
        for (int i = 0; i < number; i++) {
            discarddeck.getCards().add(handdeck.getCards().get(i));
        }

        for (int i = 0; i < number; i++) {
            handdeck.getCards().remove(0);
        }

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

    //複製回合開始怪物的陣列
    public void copyTurnStartMonsters() {
        int temp1 = monsters.size();
        for (int i = 0; i < temp1; i++) {
            Monster temp2 = monsters.get(i);
            System.out.print("第" + i + "隻怪血量" + temp2.gethealth());
            Monster clone = temp2.clone();
            System.out.print("複製怪血量" + clone.gethealth());
            clone.setSkill(temp2.getSelfSkill());
//            clone.setX(monsters.get(i).getX());
            turnstartmonsters.add(clone);
        }
    }

    //把複製的怪物陣列轉到monsters
    public void transformTurnStartMonsters() {
        int temp1 = monsters.size();
        int temp2 = turnstartmonsters.size();

        for (int i = 0; i < temp1; i++) {
            monsters.remove(0);
        }
        for (int i = 0; i < temp2; i++) {
            monsters.add(turnstartmonsters.get(i));
        }
    }

    //移除複製的怪物陣列
    public void removeTurnStartMonsters() {
        int temp = turnstartmonsters.size();
        for (int i = 0; i < temp; i++) {
            turnstartmonsters.remove(0);
        }
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
            temp.setOrginalX(300 + (Global.CARDWIDTH + 60) * i);
            temp.setOrginalY(Global.CARDDECKBOTTOM);
            temp.setHandDeckPoisition(i + 1);
            temp.setCardMoveState(new MoveToHandDeck());
        }

    }

    //把牌組整理好帶回map 判斷條件原為人物往右走出畫面(hero.getX() > Global.JWIDTH)
    public void arrangeDeck() {
        int temp = handdeck.getCards().size();
        discardCard(drawcarddeck, handdeck, discarddeck, temp);
        temp = discarddeck.getCards().size();
        for (int i = 0; i < temp; i++) {
            drawcarddeck.getCards().add(discarddeck.getCards().get(i));
        }
        for (int i = 0; i < temp; i++) {
            discarddeck.getCards().remove(0);
        }
        hero.setHeroDeck(drawcarddeck);
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
        removeTurnStartMonsters();
        copyTurnStartMonsters();
    }

    @Override
    public void sceneUpdate() {
//        if(winGameCount==1){
//            currentSountrack.stop();
//            gameWinSound.play();
//        }
        hero.move();
        if (hero.gethealth() < 0) {
            scenecontroller.changeScene(new EndScene(scenecontroller));
            currentSountrack.stop();
        }
        column.move();
       
        for (Monster monster : monsters) {
            monster.update();
        }
        //把牌組整理好帶回map 判斷條件原為人物往右走出畫面(hero.getX() > Global.JWIDTH)

        if (delaycounter.delayupdate()) {
            int temp = handdeck.getCards().size();
            for (int i = 0; i < temp; i++) {
                handdeck.getCards().get(i).move();
                if (handdeck.getCards().get(temp - 1).getX() == handdeck.getCards().get(temp - 1).getOrginalX()) {

                }
            }
            for (int i = 0; i < discarddeck.getCards().size(); i++) {
                discarddeck.getCards().get(i).move();
            }
        }
        if (delaycounter.delayupdate()) {
            for (int i = 0; i < heroawards.getAward().size(); i++) {
                heroawards.getAward().get(i).move();
            }
        }

        for (int i = 0; i < monsters.size(); i++) {

            if (monsters.get(i).gethealth() <= 0) {
                monsters.remove(monsters.get(i));
                i--;
                if (monsters.size() == 0) {
//                    hero.setState(new MoveHeroLeave());
                    gameWin = true;
                    currentSountrack.stop();
                    gameWinSound.play();

                }
            }
        }

        if (delaycounter.delayupdate() && discardcard != null) {
            discardcard.move();
        }

        if (delaycounter.delayupdate() && next.getIsClicked()) {
//            for (Monster monster : monsters) {
//                if (!monster.getMoved()) {
//                    monster.move();
//                    break;
//                }
//            }

            for (Monster monster : monsters) {
                if (!(monster.getMonsterState() instanceof MonsterState.EndMove)) {
                    monster.move();
                    allmonsterend = false;
                    break;
                } else {
                    allmonsterend = true;
                }
            }

            if (allmonsterend) {
                for (Monster temp : monsters) {
                    temp.setMoved(false);
                    temp.setMonsterState(new DecideMove());
                    temp.move();

                }

                if (!useheroskill) {
                    removeTurnStartMonsters();
                    copyTurnStartMonsters();
                }
                discardCard(drawcarddeck, handdeck, discarddeck, 5);
                System.out.println();
                System.out.println("棄牌堆" + discarddeck.getCards().size() + "張牌,抽牌堆" + drawcarddeck.getCards().size() + "張牌,手牌堆" + handdeck.getCards().size() + "張牌");
                drawCard(drawcarddeck, handdeck, discarddeck, 5);
                System.out.println("棄牌堆" + discarddeck.getCards().size() + "張牌,抽牌堆" + drawcarddeck.getCards().size() + "張牌,手牌堆" + handdeck.getCards().size() + "張牌");
                crystal.setNumberIcon(3);
                
                next.setIsClicked(false);
            }

        }
    }

    @Override
    public void sceneEnd() {
        if (monsters.size() == 0) {
            gameWinSound.stop();
            Global.CURRENTSTAGE++;
        }
    }

    @Override
    public void paint(Graphics g) {
    
        g.drawImage(currentImg, 0, 0, 1920, 1080, null);
        hero.paint(g);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).paint(g);
        }
          
        crystal.paint(g);
        next.paint(g);
//        g.setColor(Color.red);
//        for (int i = 0; i < cardlimit; i++) {
//            g.drawRect(300 + (Global.CARDWIDTH + 50) * i, Global.CARDDECKBOTTOM, Global.CARDWIDTH, Global.CARDHEIGHT);
//
//        }
       
        for (int i = 0; i < skillboard.getCardSkillList().size(); i++) {
            skillboard.getCardSkillList().get(i).paint(g);
        }
        for (int i = 0; i < handdeck.getCards().size(); i++) {
            handdeck.getCards().get(i).paint(g);
        }
        harshStone.paint(g);
        back.paint(g);
        exit.paint(g);
        backtothefuture.paint(g);
        drawcarddeck.paint(g);
        discarddeck.paint(g);

        if (gameWin) {
            int count = 0;
            while (count < 99999) {
                count++;
            }
            heroawards.paint(g);
        }
//            g.drawImage(number, 200, 200, 300, 300, null);
//        if (gameWin) {    
//            g.drawImage(winImage, 0, 0, 1920, 1080, null);
//             exit2.paint(g);
//        }

        for (int i = 0; i < monsters.size(); i++) {
            if (selectedcard != null) {
                if (selectedcard.isCollision(monsters.get(i))) {
                    column.positionSetter(monsters.get(i));
                    column.paint(g);
                    break;
                }
            }
        }
         defense.paint(g);

    }
}
