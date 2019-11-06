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
    protected int tendigits;
    protected int digits;
    protected float rate;
    protected DelayCounter delaycounter;
    protected NumberIconMoveState numbericonmovestate;
    
    public NumberIcon(int x, int y, String name, int number,float rate){
        super(x, y, (int)((2 * Global.NUMBER_X_OFFSET - Global.NUMBER_DELTAX) * rate ), (int)(Global.NUMBER_Y_OFFSET *rate ), name);
//        image = irc.tryGetImage(PathBuilder.getNumber(ImagePath.NUMBER));
        image = irc.tryGetImage("/resources/Number/Number4.png");
        this.number = number;
        tendigits = number / 10;
        digits = number % 10;
        this.rate = rate;
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
    public void setRate(float rate){
         this.rate = rate;
        
    }
   
    
    public void paint(Graphics g){
        
        int dx = digits % 10;
        int dy = digits / 10;
        if(tendigits == 0){
            g.drawImage(image, x + (int)((Global.NUMBER_X_OFFSET - Global.NUMBER_DELTAX) / 2 * rate), y,
                                   x + (int)(((Global.NUMBER_X_OFFSET - Global.NUMBER_DELTAX) / 2 + Global.NUMBER_X_OFFSET) * rate), y +(int)(Global.NUMBER_Y_OFFSET*rate), 
                                   dx * Global.NUMBER_X_OFFSET,  dy * Global.NUMBER_Y_OFFSET,
                                   dx * Global.NUMBER_X_OFFSET + 110, dy * Global.NUMBER_Y_OFFSET + 130, null);
         
        }
        
        else{
            int dx1 = tendigits % 10;
            int dy1 = tendigits / 10;
            
            g.drawImage(image, x, y, x + (int)(Global.NUMBER_X_OFFSET * rate), y  +(int)(Global.NUMBER_Y_OFFSET*rate), 
            dx1 * Global.NUMBER_X_OFFSET,  dy1 * Global.NUMBER_Y_OFFSET,
            dx1 * Global.NUMBER_X_OFFSET + 110, dy1 * Global.NUMBER_Y_OFFSET + 130, null);
        
        
            g.drawImage(image, x + (int)((Global.NUMBER_X_OFFSET - Global.NUMBER_DELTAX) * rate), y, x + (int)((2 * Global.NUMBER_X_OFFSET - Global.NUMBER_DELTAX) * rate), y  + (int)(Global.NUMBER_Y_OFFSET*rate), 
            dx * Global.NUMBER_X_OFFSET,  dy * Global.NUMBER_Y_OFFSET,
            dx * Global.NUMBER_X_OFFSET + 110, dy * Global.NUMBER_Y_OFFSET + 130, null);
        
        
        }
        
        
        
        
        
        
        
    }
    
    

}
