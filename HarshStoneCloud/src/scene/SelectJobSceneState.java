/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import gameObject.Hero.Hero;
import gameObject.Hero.HeroState;
import utils.Global;

/**
 *
 * @author frank61003
 */
public interface SelectJobSceneState {

    public void action(SelectJobScene scene);

    public class normal implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {

        }
    }

    public class selectJob implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            Hero heroSelected = scene.getHeroSelected();

            if (heroSelected.getX() > 320) {
                heroSelected.changeDirection(Global.LEFT);
                heroSelected.setX(heroSelected.getX() - 20);
            } else {
                heroSelected.changeDirection(Global.RIGHT);
            }

            if (scene.upY < 1400 || scene.downY > -430) {
                scene.upY += 30;
                scene.downY -= 30;
            }
            Hero npc = scene.getHeros()[2];
            if (npc.getX() > 1440) {
                npc.setX(npc.getX() - 10);
            } else {
                scene.setSelectJobSceneState(new beginTalk1());
            }
        }
    }

    public class beginTalk1 implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            Hero npc = scene.getHeros()[2];
            if (npc.getX() > 1440) {
                npc.setX(npc.getX() - 20);
            }
            if(npc.getX() <= 1440){
                scene.setSelectJobSceneState(new beginTalk2());
            
            }
        }
    }
    
    public class beginTalk2 implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            
            

        }
    }
    
    public class beginTalk3 implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
           

        }
    }
    
    
    

    public class talkEnd implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            Hero npc = scene.getHeros()[2];
            if (npc.getX() < 2000) {
                npc.changeDirection(Global.RIGHT);
                npc.setX(npc.getX() + 10);
                Hero job = scene.getHeroSelected();
                job.changeDirection(Global.RIGHT);
            }else{
             scene.setStoryEnd(true);
             scene.setSelectJobSceneState(new goToMap());
            }
        }
    }

    public class goToMap implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            Hero heroSelected = scene.getHeroSelected();

            if (heroSelected.getHeight() > 0) {
                heroSelected.setHeight(heroSelected.getHeight() - 3);
                heroSelected.setWidth(heroSelected.getWidth() - 3);
                if (heroSelected.getX() < 440) {
                    heroSelected.setX(heroSelected.getX() + 4);
                } else {
                    heroSelected.changeDirection(Global.UP);
                }
                if (heroSelected.getY() < 540) {
                    heroSelected.setY(heroSelected.getY() + 5);
                }
            }
        }
    }
}