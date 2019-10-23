/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Monster.Monster;
import Controller.PathBuilder;
import java.awt.Color;
import java.awt.Graphics;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Orc extends Monster{

    public Orc(int x, int y, int width, int height, String name, int health) {
        super(x, y, width, height, name, health);
        image = irc.tryGetImage(PathBuilder.getMonster(ImagePath.ORC));
    }

    
    
    
    
    public void paint(Graphics g){
        g.drawImage(image, x, y, width, height, null);
        g.setColor(Color.red);
        g.drawRect(800, 200,300,25);
        float temp = (float)health/100 * 300;
        g.fillRect(800, 200, (int)temp, 25);
    }
}
