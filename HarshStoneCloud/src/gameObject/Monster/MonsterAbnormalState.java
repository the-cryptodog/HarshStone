/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import gameObject.GameObject;
import gameObject.Hero.Hero;
import gameObject.NumberIcon;
import java.awt.Graphics;

/**
 *
 * @author frank61003
 */
public class MonsterAbnormalState extends GameObject{
    protected int continueturn;
    protected NumberIcon numbericon;
    
    public MonsterAbnormalState(int x,int y, int width, int height, String name,int continueturn){
        super(x, y, width, height, name);
        this.continueturn = continueturn;
    }
    
    public int getContinueTurn(){
        return continueturn;
    
    }
    
    
    public void action(Monster monster, Hero hero){
    
    
    
    }
    
    
    public void paint(Graphics g){

            g.drawImage(image, x, y, width, height, null);
            numbericon.paint(g);
        } 
//    public class Frozen extends MonsterAbnormalState{
//
//        
//        public Frozen(int continueturn){
//            super(continueturn);
//        }
//        
//        @Override
//        public void action(Monster monster, Hero hero) {
//            
//        }
//    
//    
//    }
    
    
    
    
    
    
}
