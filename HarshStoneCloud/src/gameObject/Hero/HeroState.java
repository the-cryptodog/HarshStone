/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Hero;

import gameObject.Monster.MonsterState;
import scene.MapScene;
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
            if (hero.getX() > 307) {
                hero.changeDirection(Global.LEFT);
                hero.setX(hero.getX() - 10);
            }else
            hero.changeDirection(Global.RIGHT);
        }
    }

    public class beginTalk implements HeroState {

          @Override
        public void action(Hero hero) {
            if (hero.getX() > 1440) {
                hero.setX(hero.getX() - 10);
            }
        }   
    }
    public class talkEnd implements HeroState {

          @Override
        public void action(Hero hero) {
            if (hero.getX() < 2080) {
                 hero.changeDirection(Global.RIGHT);
                hero.setX(hero.getX() + 10);
            }
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
            if (tempx >= Global.HEROX) {
                hero.setX(Global.HEROX);
                hero.setState(new NoMove());
            } else {
                hero.setX(tempx);
            }
        }
    }

    public class MoveHeroLeave implements HeroState {

        @Override
        public void action(Hero hero) {
            int tempx;

            tempx = hero.getX() + Global.HEROMOVERANGE;
            if (hero.getX() <= Global.JWIDTH) {
                hero.setX(tempx);
            }
        }
    }

}
