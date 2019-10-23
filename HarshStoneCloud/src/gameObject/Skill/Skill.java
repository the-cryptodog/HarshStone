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
 * @author frank61003
 */
public class Skill extends GameObject{
    protected int act;
//    protected int[] Act = {0,1,2,3,4,5};
    protected boolean skillend;
    protected DelayCounter delaycounter;
    
    public Skill(int x, int y, int width, int height, String name){
        super(x, y, width, height, name);
        image = irc.tryGetImage(PathBuilder.getSkill(ImagePath.SWORD1));
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
            act = ++act % 6;
        }
        
    }
    public boolean getSkillend(){
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
            System.out.println(act);
            g.drawImage(image, x, y, x + width, y + height, dx * Global.SKILLIMG_X_OFFSET,  dy * Global.IMG_Y_OFFSET, dx * Global.SKILLIMG_X_OFFSET + 192, dy * Global.IMG_Y_OFFSET + 192, null);
        }
        if(act == 5){
           skillend = true;
        }
        
    }
    
    
}
