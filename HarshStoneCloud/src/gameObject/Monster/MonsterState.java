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
    
    
    public class DecideMove implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 
            monster.setDefense(0);
            int temp;
            temp = (int)(Math.random()*10);
            
            if(temp >= 4){
                temp = (int)(Math.random() * 10 + 5);
                monster.setAttack(temp);
                monster.monsterstate = new MoveLeft();
            }
            else{
                temp = (int)(Math.random() * 6 + 3);
                monster.setDefense(temp);
                monster.moved = true;
                monster.monsterstate = new EndMove();
            }

        }
    }
    
    
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
                monster.monsterstate = new EndMove();
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

            if(monster.getSelfSkill().getSkillend()){
                int temp =  hero.getDefense() - monster.getAttack();
                //人物護甲還有剩,血量不用動
                if(temp > 0){
                    hero.setDefense(temp);
                }else{
                    hero.sethealth(hero.gethealth() + temp);
                    hero.setDefense(0);
                    monster.setAttack(0);
                    monster.monsterstate = new MoveRight();
                }    
            }    
       }
    }
    
    public class Defense implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 
            int temp = (int)(Math.random() * 6 + 3);
            monster.setDefense(temp);
            monster.monsterstate = new EndMove();   
       }
    }
     
     public class EndMove implements MonsterState{

        @Override
        public void action(Monster monster, Hero hero) { 
            
        }
    }
     
     
     
     
     
     
}
    
