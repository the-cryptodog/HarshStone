/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import gameObject.Hero.Hero;
import gameObject.NumberIcon;
import java.awt.Graphics;

/**
 *
 * @author frank61003
 */
public class Weak extends MonsterAbnormalState{

        
        public Weak(int x,int y, int width, int height, String name,int continueturn){
            super(x,y,width,height,name,continueturn);
            image = irc.tryGetImage("/resources/Icon/Weak1.png");
            numbericon = new NumberIcon(x+width,y,width,height," ",continueturn);
        }
        
        @Override
        public void action(Monster monster, Hero hero) {
            monster.attack = (int)(monster.attack * 0.75);
            continueturn--;
            monster.setWeak(continueturn);
        }
    
        public void paint(Graphics g){
            numbericon.setX(x+width);
            numbericon.setY(y);
            numbericon.setNumber(this.continueturn);
            g.drawImage(image, x, y, width, height, null);
            numbericon.paint(g);
        }
    
}
