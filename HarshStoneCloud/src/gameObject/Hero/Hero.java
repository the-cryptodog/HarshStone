/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Hero;

import Controller.PathBuilder;
import gameObject.Card.Card;
import gameObject.Card.CardDeck;
import gameObject.Card.CardDeck.WarriorDeck;
import gameObject.GameObject;
import gameObject.Hero.HeroState.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Hero extends GameObject implements Serializable{
    private static final long serialVersionUID = 44444;
    protected int health;
    private static final int[] ACT = {0, 1, 2, 1};
    protected int direction;
    protected int actor;
    protected int act;
    protected int originalx;
    protected int originaly;
    protected boolean moved;
    protected HeroHelper herohelper;
    protected DelayCounter delaycounter;
    protected int delay;
    protected CardDeck herodeck;
    private int defense;
    private int lasthealth;
    private boolean recordhealth;
    private HeroState heroState;

    public Hero(int x, int y, int width, int height, String name, int health, int actor) {

        super(x, y, width, height, name);
        this.health = health;
        direction = 2;
        defense = 0;
        originalx = x;
        originaly = y;
        this.actor = actor;
        moved = false;
        image = irc.tryGetImage(PathBuilder.getHero(ImagePath.ACTOR1));
//        herodeck = new WarriorDeck(x, y, width, height, name);

        herodeck = new WarriorDeck(40, 700, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "牌組");
        herohelper = new HeroHelper(actor);

        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });
        heroState = new NoMove();
//        Global.hero = this;
    }

    public Hero(int x, int y, int width, int height, String name, int health) {
        super(x, y, width, height, name);
        this.health = health;
        this.act = 5;
        image = irc.tryGetImage(PathBuilder.getHero(ImagePath.ACTOR1));
        //need to be changed
        herodeck = new WarriorDeck(400, 700, Global.CARDDECKWIDTH, Global.CARDDECKHEIGHT, "牌組");
    }

    public void recover() {
        health = lasthealth;
    }

    public boolean getRecordHealth() {
        return recordhealth;
    }

    public void setState(HeroState heroState) {
        this.heroState = heroState;
    }

    public HeroState getState() {
        return heroState;
    }

    public void setRecordHealth(boolean recordhealth) {
        this.recordhealth = recordhealth;
    }

    public CardDeck getHeroDeck() {
        return herodeck;
    }

    public void setHeroDeck(CardDeck herodeck) {
        this.herodeck = herodeck;
    }
    
    public void saveCardDeckRecord() {
        herodeck.createCardRecord();
    }
    
    public void saveHeroRecord(){
        
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("HeroRecord.txt"));
            bw.write("" + health);
            bw.newLine();
            bw.write("" + actor);
            bw.newLine();
            bw.write("" + Global.CURRENTSTAGE);
            bw.flush();
            bw.close();
        }   
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static Hero loadHeroRecord(){
        ArrayList<String> str = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("HeroRecord.txt"));
            str.add(br.readLine());
           
         while(br.ready()){
                str.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        }
        Hero temp = new Hero(Global.JOB1X, Global.JOBY, 128, 128, "Actor1", Integer.valueOf(str.get(0)), Integer.valueOf(str.get(1)));
        Global.CURRENTSTAGE = Integer.valueOf(str.get(2));
        return temp;
        
    }
    
    
    public int gethealth() {
        return health;
    }

    public void sethealth(int health) {
        this.health = health;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void changeDirection(int direction) {
        this.direction = direction;
    }

    /**
     *
     * @param width
     */
    @Override
    public void setWidth(int width) {
        this.width = width;
    }
    @Override
    public void setHeight(int height) {
        this.height = height;
    }


    public void update() {
        if (delaycounter.delayupdate()) {
            act = ++act % 4;
        }
    }

    public void move() {
//        int tempx;
//        if(!moved){
//            if(direction == 1){    
//                tempx = x - 30;
//                if(tempx <= 180){
//                    x = 180;
//                    direction = 2;
//                }
//                else{
//                    x = tempx;
//                }
//            }
//            else{
//                tempx = x + 30;
//                if(tempx >= originalx){
//                    x = originalx;
//                    direction = 1;
//                    moved = true;
//                }
//                else{
//                    x = tempx;
//                }
//            }
//    
//        }
        if (delaycounter.delayupdate()) {
            heroState.action(this);
        }
        
        
//        if (recordhealth == false) {
//            lasthealth = health;
//            recordhealth = false;
//        }

    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean getMoved() {
        return moved;
    }

    public void paint(Graphics g) {
        update();
        herohelper.paint(g, x, y, width, height, ACT[act], direction, health);
        
    }
}
