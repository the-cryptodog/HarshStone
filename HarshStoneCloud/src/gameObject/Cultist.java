/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Monster.Monster;
import Controller.PathBuilder;
import java.awt.Graphics;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Cultist extends Monster{
    public Cultist(int x, int y, int width, int height, String name, int health) {
        super(x, y, width, height, name, health);
        image = irc.tryGetImage(PathBuilder.getMonster(ImagePath.CULTIST));
    }

    
    
    
    
    public void paint(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    
    }
}
