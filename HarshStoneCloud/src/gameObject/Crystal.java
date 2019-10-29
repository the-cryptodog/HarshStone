/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import java.awt.Graphics;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Crystal extends GameObject{
//  protected int[] Act = {0,1,2,3,4,5};
    protected DelayCounter delaycounter;
    
    public Crystal(int x, int y, int width, int height, String name){
        super(x, y, width, height, name);
        image = ImageResourceController.getInstance().tryGetImage("/resources/Number/Number1.png");

//        image = irc.tryGetImage(PathBuilder.getIcon(ImagePath.CRYSTALICON));
        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4; 
            }
        });
    }   
   
    
 
    
    
    
    public void paint(Graphics g){

        g.drawImage(image, x, y, width, height, null);
    }
        
        
}


