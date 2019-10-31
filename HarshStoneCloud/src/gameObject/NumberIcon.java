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
public class NumberIcon extends GameObject{
    protected int number;
    protected DelayCounter delaycounter;
    
    public NumberIcon(int x, int y, int width, int height, String name, int number){
        super(x, y, width, height, name);
//        image = irc.tryGetImage(PathBuilder.getNumber(ImagePath.NUMBER));
        image = irc.tryGetImage("/resources/Number/Number4.png");
        this.number = number;
        delaycounter = new DelayCounter(6, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4; 
            }
        });
    }
    
    
    
    
    public int getNumber(){
        return number;
    }
    
    public void setWidth(int width){
        this.width= width;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public void setNumber(int number){
         this.number = number;
    }
    
   
    
    public void paint(Graphics g){
        int dx = number % 10;
        int dy = number / 10;
        
        
//        update();
//        if(skillend == false){
            g.drawImage(image, x, y, x + width, y + height, 
                    dx * Global.NUMBER_X_OFFSET,  dy * Global.NUMBER_Y_OFFSET,
                    dx * Global.NUMBER_X_OFFSET + 110, dy * Global.NUMBER_Y_OFFSET + 130, null);
//        }
//        if(act == 5){
//           skillend = true;
//           act = 0;
//        }
         
    }
    
    

}
