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
    }
    
    public int getDefense(){
        return defense;
    }
    
    public void steDefense(int defense){
        this.defense = defense;
    
    }
    
    public void setOrginalX(int originalx){
            this.originalx = originalx;
    }
    
    public int getOrginalX(){
        return  originalx;
    }
    
    
    public void setOrginalY(int originaly){
            this.originaly = originaly;
    }
    
    
    public int getOrginalY(){
        return originaly;
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
    
    }
    
    
    public Card getThis(){
        return this;
    }
    
    public String toString() {
        
        return description;
    }
    
    
    
    
    
    
    
}
