/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import gameObject.Hero.Hero;
import utils.Global;

/**
 *
 * @author frank61003
 */
public interface LastSceneState {
    public void action(LastScene scene);

    public class normal implements LastSceneState {

        @Override
        public void action(LastScene scene) {

        }
    }

    public class moveHero implements LastSceneState {

        @Override
        public void action(LastScene scene) {
            Hero heroSelected = scene.hero;
            heroSelected.sethealth(0);
            heroSelected.setY(Global.JOBY);
            heroSelected.setWidth(128);
             heroSelected.setHeight(128);
            if (heroSelected.getX() < 320) {
                heroSelected.changeDirection(Global.RIGHT);
                heroSelected.setX(heroSelected.getX() + 20);
            } else {
                scene.setLastSceneState(new beginTalk1());
            }
        }
    }

    public class beginTalk1 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
//            Hero npc = scene.getHeros()[2];
//            if (npc.getX() > 1440) {
//                npc.setX(npc.getX() - 20);
//            }
        }
    }
    
    public class moveNpc implements LastSceneState {

        @Override
        public void action(LastScene scene) {
            Hero npc = scene.npc;
            if (npc.getX() > 1350) {
                npc.setDirection(Global.LEFT);
                npc.setX(npc.getX() - 10);
            } else {
                scene.setLastSceneState(new beginTalk2());
            }
        }
    }
    
    
    
    
    public class beginTalk2 implements LastSceneState {

        @Override
        public void action(LastScene scene) {

        }
    }
    
    public class beginTalk3 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    
    public class beginTalk4 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    
    public class beginTalk5 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk6 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk7 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk8 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk9 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk10 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    

    public class beginTalk11 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    
    public class beginTalk12 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    public class beginTalk13 implements LastSceneState {

        @Override
        public void action(LastScene scene) {
           

        }
    }
    
    public class changeBackGround implements LastSceneState {

        @Override
        public void action(LastScene scene) {
            
        }
    }

    public class conclusion implements LastSceneState {

        @Override
        public void action(LastScene scene) {
            
        }
    }
}
