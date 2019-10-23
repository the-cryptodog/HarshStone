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
public class 旋風斬 extends Card{
    public 旋風斬(int x, int y, int width, int height, String name, int cost) {
        super(x, y, width, height, name, cost);
    }

    
    
    @Override
    public void paint(Graphics g){
        g.drawImage(irc.getInstance().tryGetImage(PathBuilder.getImage(ImagePath.旋風斬)), x, y, width, height, null);
    
    }
}
