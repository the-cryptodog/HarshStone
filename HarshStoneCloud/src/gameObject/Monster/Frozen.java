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
public class Frozen extends MonsterAbnormalState{

        
        public Frozen(int x,int y, int width, int height, String name,int continueturn){
            super(x,y,width,height,name,continueturn);
            image = irc.tryGetImage("/resources/Icon/Frozen1.png");
            numbericon = new NumberIcon(x+width,y," ",continueturn, 0.25f);
        }
        
        @Override
        public void action(Monster monster, Hero hero) {
            monster.setAttack(0);
            monster.setDefense(0);
            if(continueturn > 1){
                monster.monsterstate = new MonsterState.EndMove();
            }
            else{
                monster.monsterstate = new MonsterState.MoveLeft();
                monster.attack = (int)(Math.random() * 10 + 5);
            }
            continueturn--;
            monster.setFrozen(continueturn);
        }
    
        public void paint(Graphics g){
            numbericon.setX(x+width);
            numbericon.setY(y);
            numbericon.setNumber(this.continueturn);
            g.drawImage(image, x, y, width, height, null);
            numbericon.paint(g);
        }
    
}
