/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Jobs;

import Controller.PathBuilder;
import gameObject.Button.Button;
import gameObject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class JobIcon extends GameObject {

    private BufferedImage jobIcon;
    private int actorPosition;
     private JobIconHelper jobIconHelper;
    protected DelayCounter delayCounter;
    protected int direction;
    private int act;
    private static final int[] ACT = {0, 1, 2, 1};

    public JobIcon(int x, int y, int width, int height, String name, int act) {
        super(x, y, width, height, name);
        jobIconHelper = new  JobIconHelper(act);
        jobIcon = irc.getInstance().tryGetImage(PathBuilder.getJobs(ImagePath.ACTOR1));
        direction = Global.DOWN;
        delayCounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });
    }
    
        public void changeDirection(int direction) {
        this.direction = direction;
    }
        
        
    public void move() {
        if (delayCounter.delayupdate()) {
            act = ++act % 4;          
        }  
    }
   

    public void paint(Graphics g){
       jobIconHelper.paint(g, x, y, width, height, ACT[act],direction);
    }
}
