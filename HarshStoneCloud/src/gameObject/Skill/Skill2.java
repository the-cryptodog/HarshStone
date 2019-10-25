/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Skill;

import Controller.PathBuilder;
import gameObject.GameObject;
import java.awt.Graphics;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class Skill2 extends GameObject{
     protected int act;
//    protected int[] Act = {0,1,2,3,4,5};
    protected boolean skillend;
    protected DelayCounter delaycounter;
    
    public Skill2(int x, int y, int width, int height, String name){
        super(x, y, width, height, name);
        image = irc.tryGetImage(PathBuilder.getSkill(ImagePath.SWORD2));
        act = 0;
        skillend = false;
        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4; 
            }
        });
    }   
   
    public void update(){
        if(delaycounter.delayupdate()){
            act = ++act % 15;
        }
        
    }
    public boolean getSkillend(){
        if(skillend == true){
            skillend = false;
            return true;
        }
        return skillend;
    }
    
    public void setSkillend(boolean skillend){
        this.skillend = skillend;
    }
    
    
    
    public void paint(Graphics g){
        int dx = act % 5;
        int dy = act / 5;
        update();
        if(skillend == false){
            g.drawImage(image, x, y, x + width, y + height,
                    dx * Global.SKILLIMG_X_OFFSET,  dy * Global.SKILLIMG_Y_OFFSET,
                    dx * Global.SKILLIMG_X_OFFSET + 192, dy * Global.SKILLIMG_Y_OFFSET + 192, null);
        }
        if(act == 14){
           skillend = true;
           act = 0;
        }
        
    }
}
