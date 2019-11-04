/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Hero;

import gameObject.Monster.MonsterState;
import java.io.Serializable;
import scene.MapScene;
import utils.Global;

/**
 *
 * @author User
 */
public interface HeroState {

    public void action(Hero hero);


//    public class talkEnd implements HeroState {
//
//        @Override
//        public void action(Hero hero) {
//            if (hero.getX() < 2080) {
//                hero.changeDirection(Global.RIGHT);
//                hero.setX(hero.getX() + 10);
//            }
//        }
//    }

//    public class goToMap implements HeroState {
//
//        @Override
//        public void action(Hero hero) {
//            hero.changeDirection(Global.RIGHT);
//            if (hero.getHeight() > 0) {
//                hero.setHeight(hero.getHeight() - 2);
//                hero.setWidth(hero.getWidth() - 2);
//
//                if (hero.getX() < 440) {
//                    hero.setX(hero.getX() + 4);
//                }else{
//                              hero.changeDirection(Global.UP);
//                }
//                if (hero.getY() < 545) {
//                    hero.setY(hero.getY() + 4);
//                }
//            }
//        }
//    }

    public class NoMove implements HeroState, Serializable{

        @Override
        public void action(Hero hero) {
        }
    }

    public class MoveHeroRight implements HeroState, Serializable {

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
    
    public class MoveHeroLeave implements HeroState, Serializable {

        @Override
        public void action(Hero hero) {
            int tempx;
            
            tempx = hero.getX() + Global.HEROMOVERANGE+10;
            if(hero.getX() <= Global.JWIDTH ){
                hero.setX(tempx);
            }
        }
    }

}
