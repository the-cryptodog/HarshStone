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
import gameObject.Crystal;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
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
    private int x1;
    private int y1;
    private int xdelta;
    private int ydelta;
    private boolean cardclicked;
    private Card selectedcard;
    private Card discardcard;
    private Monster orc;
    private Monster cultist;
    private Monster monster1;
    private Monster monster4;
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
    private BufferedImage endImage;
    private BufferedImage columns[];
    private BufferedImage column1;
    private BufferedImage column2;
    private BufferedImage column3;
    private Crystal crystal;
    private Poison ttt;
    private Hero hero;
    private Award heroawards;
    private Card selectedRareCard;

    public MainScene(SceneController scenecontroller, MapScene mapScene) {
        super(scenecontroller);
//        System.out.println(PathBuilder.getNumber(ImagePath.NUMBER2));
//        number = ImageResourceController.getInstance().tryGetImage("/resources/Number/Number2.png");
        crystal = new Crystal(Global.HEROX-32, Global.HEROY+192, 80, 80, "tt");
        gameWin = false;
        gameOver = false;
        font1 = new Font("TimesRoman", Font.BOLD + Font.ITALIC, 14);
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
        columns = new BufferedImage[3];

        hero = Global.hero;
        Global.hero.setX(-70);
        Global.hero.setY(Global.HEROY);
        Global.hero.setWidth(Global.HEROWIDTH);
        Global.hero.setHeight(Global.HEROXHEIGHT);

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
        backtothefuture = new Button(1700, 650, 184, 42, "ROUNDSTART");

        hero = Global.hero;
        Global.hero.setX(-70);
        Global.hero.setY(Global.HEROY);
        Global.hero.setWidth(Global.HEROWIDTH);
        Global.hero.setHeight(Global.HEROXHEIGHT);

        drawcarddeck = hero.getHeroDeck();
        heroawards = new Award(150, 90, 1770, 990, "AWARD");

        orc = new Monster(Global.MONSTERX, Global.MONSTERY, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人1", 14, 1, (int) (Math.random() * 8), (int) (Math.random() * 8)); // 創建第一隻怪物 // 最後兩個參數為腳色變換跟技能光影挑選
        cultist = new Monster(Global.MONSTERX, Global.MONSTERY2, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人2", 10, 2, (int) (Math.random() * 8), (int) (Math.random() * 8));// 創建第二隻怪物\
        monster1 = new Monster(Global.MONSTERX, Global.MONSTERY3, Global.MONSTERWIDTH, Global.MONSTERHEIGHT,
                "獸人3", 17, 3, (int) (Math.random() * 8), (int) (Math.random() * 8));// 創建第三隻怪物\

        turnstartmonsters = new ArrayList();
        monsters = new ArrayList();
        monsters.add(orc);
        monsters.add(cultist);
        monsters.add(monster1);

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

        System.out.print(orc.toString());
        System.out.print(cultist.toString());
        System.out.print(monster1.toString());

        System.out.println(drawcarddeck.toString());
        System.out.println(handdeck.toString());
        System.out.println(discarddeck.toString());
        for (Monster monster : monsters) {
            monster.setHero(hero);
        }

        column1 = irc.tryGetImage("/resources/Monster/COLUMN.png");
        column2 = irc.tryGetImage("/resources/Monster/COLUMN.png");
        column3 = irc.tryGetImage("/resources/Monster/COLUMN.png");
        columns[0] = column1;
        columns[1] = column2;
        columns[2] = column3;

        endImage = irc.tryGetImage("/resources/Background/ENDSCENE.png");

        img = irc.tryGetImage("/resources/Background/BACKGROUND1.jpg");
        img2 = irc.tryGetImage("/resources/Background/BACKGROUND2.jpg");
        img3 = irc.tryGetImage("/resources/Background/BACKGROUND3.jpg");
        img4 = irc.tryGetImage("/resources/Background/BACKGROUND4.jpg");
        img5 = irc.tryGetImage("/resources/Background/BACKGROUND5.jpg");

        switch (Global.CURRENTSTAGE) {
            case 1:
                currentImg = img;
                break;
            case 2:
                currentImg = img2;
                break;
            case 3:
                currentImg = img3;
                break;
            case 4:
                currentImg = img4;
                break;
            case 5:
                currentImg = img5;

        }

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
                            for (int i = 0; i < monsters.size(); i++) {
                                System.out.println(selectedcard.toString());
                                if (monsters.get(i).isCollision(selectedcard)) {
                                    System.out.print(selectedcard.toString());
                                    // 卡排放到怪物上的動畫
                                    Monster temp1 = monsters.get(i);
                                    if (skillboard.skillCheck(selectedcard.getSkilltype())) {
                                        skillboard.getCardSkill(selectedcard.getSkilltype()).setY(monsters.get(i).getY());
                                        skillboard.getCardSkill(selectedcard.getSkilltype()).setSkillend(false);
                                    } else {
                                                System.out.print("vvvvvv"+selectedcard.getSkilltype()+"vvvvvv");
                                        Skill tmp = skillFactory.genSkill(selectedcard.getSkilltype());
                                        skillboard.addCardSkill(tmp);
                                        tmp.setY(monsters.get(i).getY());
                                        tmp.setSkillend(false);
                                    }//檢測MainScene的卡片技能區，如已有實體則使用，如無則新增//                                                              
                                    selectedcard.action(hero, temp1);
                                    temp1.updateNumberIcon();
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
                    if (next.isCollision(e.getX(), e.getY()) &&  !(handdeck.getCards().get(4).getCardMoveState() instanceof MoveToHandDeck)) {
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
                    }
                    if (heroawards.getButton().isCollision(e.getX(), e.getY())) {

                        scenecontroller.changeScene(mapScene);
                        selectedRareCard.getCardIconHelper().setAf(1);
                        selectedRareCard.setWidth(Global.CARDWIDTH);
                        selectedRareCard.setHeight(Global.CARDHEIGHT);
                        Global.hero.getHeroDeck().getCards().add(selectedRareCard);

                    }

                    if (exit.isCollision(e.getX(), e.getY())) {
//                        gameOver = true;
                        scenecontroller.changeScene(new EndScene(scenecontroller));
                    }

                    if (backtothefuture.isCollision(e.getX(), e.getY()) && useheroskill == false) {
                        useheroskill = true;
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
                            if(handdeck.getCards().get(i).getCardMoveState() instanceof MoveStop)
                            handdeck.getCards().get(i).setY(700);
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
        
        if (hero.gethealth() < 0) {
            scenecontroller.changeScene(new EndScene(scenecontroller));
        }

        hero.move();
        //把牌組整理好帶回map 判斷條件原為人物往右走出畫面(hero.getX() > Global.JWIDTH)
        if (monsters.size() == 0) {
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

        if (delaycounter.delayupdate()) {
            int temp = handdeck.getCards().size();
            for (int i = 0; i < temp; i++) {
                handdeck.getCards().get(i).move();
                if(handdeck.getCards().get(temp-1).getX() == handdeck.getCards().get(temp-1).getOrginalX()){
                    
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
        if(monsters.size()==0){
            Global.CURRENTSTAGE++;
        }
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
//        if (hero.getX() > Global.JWIDTH) {
//            int temp = handdeck.getCards().size();
//            discardCard(drawcarddeck, handdeck, discarddeck, temp);
//            temp = discarddeck.getCards().size();
//            for(int i = 0; i < temp; i++){
//                drawcarddeck.getCards().add(discarddeck.getCards().get(i));
//            }
//            for(int i = 0; i < temp; i++){
//                discarddeck.getCards().remove(0);
//            }
//            hero.setHeroDeck(drawcarddeck);
//            
//            Global.CURRENTSTAGE++;
//            scenecontroller.changeScene(mapScene);
//        }
    }

    @Override
    public void paint(Graphics g) {

//        g.setFont(font1);
//        g.drawString("14pt bold & italic times Roman", 5, 92);
        g.drawImage(currentImg, 0, 0, 1920, 1080, null);
        hero.paint(g);
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).paint(g);
        }
        
        crystal.paint(g);

//        g.setColor(Color.red);
//        g.drawRect(800,500,300,25);
//        g.fillRect(800, 500, (int)temp1, 25);
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

        back.paint(g);
        exit.paint(g);
        backtothefuture.paint(g);
        drawcarddeck.paint(g);
        discarddeck.paint(g);

        if (gameWin) {
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
                    g.drawImage(columns[i], monsters.get(i).getX(), monsters.get(i).getY(), null);
                    break;
                }
            }
        }
    }
}
