/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Hero;

import gameObject.Monster.MonsterState;
import utils.Global;

/**
 *
 * @author User
 */
public interface HeroState {

    public void action(Hero hero);

    public class jobSelected implements HeroState {

        @Override
        public void action(Hero hero) {
            hero.setX(hero.getX() + 20);
        }
    }

    public class NoMove implements HeroState {

        @Override
        public void action(Hero hero) {
            
        }
    }
    
    public class MoveHeroRight implements HeroState {

        @Override
        public void action(Hero hero) {
            int tempx;
            
            tempx = hero.getX() + Global.HEROMOVERANGE;
            hero.direction = 2;
            if(tempx >= Global.HEROX ){
                hero.setX( Global.HEROX);    
                hero.setState(new NoMove());
            }
            else{
                hero.setX(tempx);
            }
        }
    }
    
    public class MoveHeroLeave implements HeroState {

        @Override
        public void action(Hero hero) {
            int tempx;
            
            tempx = hero.getX() + Global.HEROMOVERANGE;
            if(hero.getX() <= Global.JFRAMEWIDTH ){
                hero.setX(tempx);
            }
        }
    }
    
    
    
    
    
}
