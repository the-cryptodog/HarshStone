/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.PathBuilder;
import java.awt.Graphics;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class 全力迎戰 extends Card{

    public 全力迎戰(int x, int y, int width, int height, String name,int cost) {
        super(x, y, width, height, name, cost);
        image = irc.getInstance().tryGetImage(PathBuilder.getImage(ImagePath.全力迎戰));
    }

    
    
    @Override
    public void paint(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    
    }
    
    
    
    
    
}
