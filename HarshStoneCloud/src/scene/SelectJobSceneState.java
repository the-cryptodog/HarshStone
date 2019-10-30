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

        }
    }

    public class talkEnd implements SelectJobSceneState {

        @Override
        public void action(SelectJobScene scene) {
            Hero npc = scene.getHeros()[2];
            if (npc.getX() < 2080) {
                npc.changeDirection(Global.RIGHT);
                npc.setX(npc.getX() + 10);
                Hero job = scene.getHeroSelected();
                job.changeDirection(Global.RIGHT);
                if (job.getHeight() > 0) {
                    System.out.print("ddddddd");
                    job.setHeight(job.getHeight() - 2);
                    job.setWidth(job.getWidth() - 2);
                    if (job.getX() < 440) {
                        job.setX(job.getX() + 4);
                    } else {
                        job.changeDirection(Global.UP);
                    }
                    if (job.getY() < 545) {
                        job.setY(job.getY() + 4);
                    }
                }
            }
        }
    }
}
