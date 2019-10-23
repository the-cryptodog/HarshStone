/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import gameObject.Hero.Hero;
import utils.Global;

/**
 *
 * @author frank61003
 */
public interface MonsterState {
    public void action(Monster monster, Hero hero);
    
    
    public class MoveLeft implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 
            int tempx;
            
            tempx = monster.getX() - Global.MOVERANGE;
            monster.direction = 1;
            if(tempx <= Global.HEROX +Global.HEROWIDTH + 2 * Global.HEALTHX){
                monster.setX( Global.HEROX + Global.HEROWIDTH +2 * Global.HEALTHX);
                
                monster.monsterstate = new Attack();
            }
            else{
                monster.setX(tempx);
            }
            
            
        }
    }
    
    
    public class MoveRight implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 
            int tempx;
            monster.direction = 2;
            tempx = monster.getX() + Global.MOVERANGE;
            if(tempx >= Global.MONSTERX){
                monster.setX(Global.MONSTERX);
                monster.direction = 1;
                monster.moved = true;
                monster.monsterstate = new MoveLeft();
            }
            else{
                monster.setX(tempx);
            }
            
        }
    }
    
    
    
    //Monster Attack
    public class Attack implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 

            if(monster.getSkill().getSkillend()){
                int temp = (int)(Math.random() * 10 + 5);
                hero.sethealth(hero.gethealth() - temp);
                monster.monsterstate = new MoveRight();
            }
            
       }
    }
}
    
