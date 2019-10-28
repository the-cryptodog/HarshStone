/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import Controller.PathBuilder;
import gameObject.GameObject;
import gameObject.Hero.Hero;
import gameObject.NumberIcon;
import gameObject.Skill.Skill;
import java.awt.Graphics;
import utils.DelayCounter;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Monster extends GameObject {

    protected int health;
    private static final int[] ACT = {0, 1, 2, 1};

    protected int direction;
    protected int act;
    protected int originalx;
    protected int originaly;
    protected int originalhealth;
    private int lasthealth;
    private boolean recordhealth;
    protected boolean moved;
    protected boolean useskill;
    protected MonsterHelper monsterhelper;
    protected DelayCounter delaycounter;
    protected int delay;
    protected MonsterState monsterstate;
    protected Hero hero;
    protected int defense;
    private int attack;
    private NumberIcon number1;
    private NumberIcon number2;
    private int yPosition;
    private int skillIndex;
    private Skill selfSkill;

    public Monster(int x, int y, int width, int height, String name, int health, int yPosition, int act, int skillIndex) {
        super(x, y, width, height, name);
        this.health = health;
        originalhealth = health;
        direction = 1;
        originalx = x;
        originaly = y;
        defense = 0;
        attack = 0;
        moved = false;
        useskill = false;
        image = irc.tryGetImage(PathBuilder.getMonster(ImagePath.MONSTER1));
        recordhealth = false;
        this.yPosition = yPosition;
        this.skillIndex = skillIndex;

        monsterhelper = new MonsterHelper(act);
        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });

        monsterstate = new MonsterState.DecideMove();
        number1 = new NumberIcon(x + width, y + 100, 50, 100, "", 0);
        number2 = new NumberIcon(x + width + 50, y + 100, 50, 100, "", 0);
    }

    //10/22
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Monster(int x, int y, int width, int height, String name, int health) {
        super(x, y, width, height, name);
        this.health = health;
        this.act = 0;
        image = irc.tryGetImage(PathBuilder.getMonster(ImagePath.MONSTER1));

    }

    public void recover() {
        health = lasthealth;
    }

    public boolean getRecordHealth() {
        return recordhealth;
    }

    public void setRecordHealth(boolean recordhealth) {
        this.recordhealth = recordhealth;
    }

    public void setSkill(Skill selfskill) {
        this.selfSkill = selfskill;
    }

    public int getskillIndex() {
        return skillIndex;
    }
    public Skill getSelfSkill (){
        return selfSkill;
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

    public int getAttack() {
        return attack;
    }

    public int getYposition() {
        return yPosition;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setMonsterState(MonsterState monsterstate) {
        this.monsterstate = monsterstate;
    }

    public MonsterState getMonsterState() {
        return monsterstate;
    }
    
    
    public void changeDirection(int direction) {
        this.direction = direction;
    }

    public void update() {
        if (delaycounter.delayupdate()) {

            act = ++act % 4;

        }
    }

    
    
    public void move() {
        int tempx;
//        if(!moved){
//            if(direction == 1){    
//                tempx = x - 30;
//                
//                if(skill.getSkillend()){
//                    useskill = false;
//                    direction = 2;
//                    skill.setSkillend(false);    
//                }                    
//                
//                
//                if(tempx <= Global.HEROX +Global.HEROWIDTH + 2 * Global.HEALTHX){
//                    x = Global.HEROX + Global.HEROWIDTH +2 * Global.HEALTHX;
//                    useskill = true;
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
        if (recordhealth == false) {
            lasthealth = health;
            recordhealth = false;
        }

        monsterstate.action(this, hero);
        if (attack > 0) {
            number1.setNumberIcon(attack / 10);
            number2.setNumberIcon(attack % 10);

        } else if (defense > 0) {
            number1.setNumberIcon(defense / 10);
            number2.setNumberIcon(defense % 10);

        }

    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean getMoved() {
        return moved;
    }

    public int getDirection() {
        return direction;
    }

    public String toString() {
        return "攻擊力" + attack + "防禦力" + defense;

    }

    public void paint(Graphics g) {
        if (act == 5) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            monsterhelper.paint(g, x, y, width, height, ACT[act], direction, health, originalhealth, attack, defense);
            number1.setX(x + width);
            number2.setX(x + width);
            update();

//            number1.paint(g);
//            number2.paint(g);

            if (monsterstate instanceof MonsterState.Attack) {
                selfSkill.paint(g);//畫出攻擊技能
            }

        }

    }
}
