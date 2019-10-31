/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import gameObject.Monster.Monster;
import Controller.ImageResourceController;
import Controller.PathBuilder;
import gameObject.Card.CardMoveState.MoveStop;
import gameObject.GameObject;
import gameObject.Hero.Hero;
import gameObject.NumberIcon;
import gameObject.Skill.Skill;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Card extends GameObject{
    
    protected String description;
    protected boolean clicked;
    protected int cost;
    protected BufferedImage image;
    protected String path;
    protected int serialnumber;
    protected CardMoveState cardmovestate;
    protected int originalx;
    protected int originaly;
    private int defense;
    private int attack;
    private int weak;
    private int frozen;
    private int poison;
    private int skillIndex;
    private int handdeckpoisition;
    private CardIconHelper cardIconHelper;

    
    public Card(int x, int y, int width, int height, String name, int cost) {
        super(x, y, width, height, name);
        originalx = x;
        originaly = y;
        this.cost = cost;
        description= "";
        clicked = false;
        cardmovestate = new MoveStop();
        image = irc.getInstance().tryGetImage(PathBuilder.getImage("/"+name+".png"));
        defense = 0;
        attack = 0;
        //1~5為手牌位置
        handdeckpoisition = 0;
        cardIconHelper= new CardIconHelper(this);
        weak = 0;
        frozen = 0;
        poison = 0;
    }
    
    public int getAttack(){
        return attack;
    }
    
    public void setAttack(int attack){
        this.attack = attack;
    
    }
    public int getDefense(){
        return defense;
    }
    
    public void setDefense(int defense){
        this.defense = defense;
    
    }
    public void setSkill(int skillIndex){
       this.skillIndex = skillIndex;
    
    }

    public void setPoision(int poision){
        this.poison =poison;
    }
    
    public int getPoison(){
        return poison;
    }
    
    
    public void setWeak(int weak){
        this.weak = weak;
    }
    
    public int getWeak(){
        return weak;
    }
    public void setFrozen(int frozen){
        this.frozen = frozen;
    }
    
    public int getFrozen(){
        return frozen;
    }
    
    
     public int getOrginalX(){
        return  originalx;
    }
    
    
    
    public void setOrginalX(int originalx){
            this.originalx = originalx;
    }
    
    public int getSkilltype(){
        return skillIndex;
    }
    
    
    public void setOrginalY(int originaly){
            this.originaly = originaly;
    }
    
    
    public int getOrginalY(){
        return originaly;
    }
      
    
    public int getCost(){
        return cost;
    }
    
    
    public void setHandDeckPoisition(int handdeckpoisition){
        this.handdeckpoisition = handdeckpoisition;
    }
    
    public int getHandDeckPoisition(){
        return handdeckpoisition;
    }
    
    public ImageResourceController getIRC() {
        return irc;
    }
    
    public int getDeltaX(int x){
        int xdelta = x - this.x;
        return xdelta;
    }
    
    public int getDeltaY(int y){
        int ydelta = y - this.y;
        return ydelta;
    }
    
    
    public void setClicked(boolean x){
        clicked = x;
    }
    
    public boolean getUP(){
        if( y - originaly <0){
        
            return true;
        }
        return false;
    }
    
    public boolean getLeft(){
        if( x - originalx <0){
            return true;
        }
        return false;
    }
    
    
    public void setCardMoveState(CardMoveState cardmovestate){
        this.cardmovestate = cardmovestate;
    }
    public CardMoveState getCardMoveState(){
        return cardmovestate;
    }
    
    public boolean getClicked(){
        return clicked;
    }
    
    public void action(Hero hero, Monster monster){
        
    }
    
    public void move(){
        cardmovestate.move(this);
    }
    
    
    
    public void paint(Graphics g){
        g.drawImage(image, x, y, width, height, null);
        cardIconHelper.paint(g, this.x, this.y, width, height, attack, defense);
    }
    
    
    public Card getThis(){
        return this;
    }
    
    public String toString() {
        
        return description;
    }
    
    
    
    
    
    
    
}
