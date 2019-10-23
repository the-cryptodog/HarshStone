/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Monster.Monster;
import Controller.ImageResourceController;
import Controller.PathBuilder;
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
    
    
    public Card(int x, int y, int width, int height, String name, int cost) {
        super(x, y, width, height, name);
        this.cost = cost;
        description= "";
        clicked = false;
//        image = irc.getInstance().tryGetImage(PathBuilder.getImage(path));
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

    public boolean getClicked(){
        return clicked;
    }
    
    public void action(Hero hero, Monster monster){
        
    }
    
    public void move(){
        int deltax = 0;
        int deltay = 0;
        if(x > 30 || y < 600){    
 
            if(x > 30){
                x = x - 30;
            }
            if(y < 600){
                y += 30;
            }
            
           
        }
        
        
        
    }
    
    
    public void paint(Graphics g){
        g.drawImage(irc.getInstance().tryGetImage(PathBuilder.getImage("/"+name+".png")), x, y, width, height, null);
    
    }
    
    
    public Card getThis(){
        return this;
    }
    
    public String toString() {
        
        return description;
    }
    
    
    
    
    
    
    
}
