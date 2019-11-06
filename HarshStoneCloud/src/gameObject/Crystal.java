/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    protected NumberIcon numbericon;
    protected BufferedImage empty;
    
    public Crystal(int x, int y, int width, int height, String name){
        super(x, y, width, height, name);

        image = irc.tryGetImage(PathBuilder.getIcon(ImagePath.CRYSTALICON));
        numbericon = new NumberIcon(x + width+20, y, name, 3,1);
         empty = irc.tryGetImage(PathBuilder.getIcon(ImagePath.CRYSTALEMPTY));
        delaycounter = new DelayCounter(10, new DelayCounter.Action() {
       
            @Override
            public void action() {
                int act = 0;
                act = ++act % 4; 
            }
        });
    }   
   
    public void setNumberIcon(int number){
        this.numbericon.setNumber(number);
    }
    
    public NumberIcon getNumberIcon(){
        return numbericon;
    }

    

    public void paint(Graphics g){
        

        numbericon.paint(g);   
        if(numbericon.getNumber()==0){
              g.drawImage(empty, x, y, width, height, null);
        }else
        g.drawImage(image, x, y, width, height, null);
    }
        
        
}


