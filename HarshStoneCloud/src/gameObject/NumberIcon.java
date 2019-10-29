/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.PathBuilder;
import java.awt.Graphics;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class NumberIcon extends GameObject{
    protected int number;
    protected int[] Act = {1,2,3,4,5, 6, 7, 8, 9, 0};
    protected DelayCounter delaycounter;
    
    public NumberIcon(int x, int y, int width, int height, String name, int number){
        super(x, y, width, height, name);
//        image = irc.tryGetImage(PathBuilder.getNumber(ImagePath.NUMBER));
        this.number = number;
        delaycounter = new DelayCounter(6, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4; 
            }
        });
    }
    
    
    
    
    public int getNumberIcon(){
        return number;
    }
    
    public void setNumberIcon(int number){
         this.number = number;
    }
    
    
   
    
    
   
    
    public void paint(Graphics g){
        int dx = Act[number] % 5;
        int dy = Act[number] / 5;
        
        
        
//        update();
//        if(skillend == false){
//            g.drawImage(image, x, y, x + width, y + height, dx * Global.NUMBER_X_OFFSET,  dy * Global.NUMBER_Y_OFFSET, dx * Global.NUMBER_X_OFFSET + 63, dy * Global.NUMBER_Y_OFFSET + 70, null);
//        }
//        if(act == 5){
//           skillend = true;
//           act = 0;
//        }
         
    }
    
    

}
