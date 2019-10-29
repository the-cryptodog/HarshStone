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
public interface SelectJobSceneState {
    public void action(SelectJobScene scene);
    
    public class normal implements SelectJobSceneState{

        @Override
        public void action(SelectJobScene scene) {
            
        }
    }
    
    
    
    
    public class selectJob1 implements SelectJobSceneState{

        @Override
        public void action(SelectJobScene scene) {
            Hero job1 = scene.getJob1();
            if (job1.getX() > 307) {
                job1.changeDirection(Global.LEFT);
                job1.setX(job1.getX() - 10);
            }else{
                job1.changeDirection(Global.RIGHT);
            }
            if (scene.upY < 1400 || scene.downY > -430) {
                    scene.upY += 30;
                    scene.downY -= 30;
            }
    
    
    
    
        }
    }   
    
    
    
    
    
    
    
    
    
    
}
