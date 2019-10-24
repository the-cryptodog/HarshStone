/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class MonsterHelper {
    private BufferedImage img;
    private int monsterposition;
    public MonsterHelper(int monster){
        img = getMonster(monster);
        monsterposition = monster % 8;
    }
    
    
    private BufferedImage getMonster(int monster){
        ImageResourceController irc = ImageResourceController.getInstance();
        if(monster >= 0 || monster < 8){
            return irc.tryGetImage(PathBuilder.getMonster(ImagePath.MONSTER1));
        }
        if(monster >= 8 || monster < 16){
            return irc.tryGetImage(PathBuilder.getMonster(ImagePath.MONSTER2));
        }
        
        return null;
    
    }
    
    public void paint(Graphics g, int x, int y, int width, int height, int act, int direction, int health,int originalhealth){
        if(img == null){
            return;
        }
        int dx = 96 * (monsterposition % 4);
        int dy = 128 * (monsterposition / 4);
        
         g.drawImage(img, x, y, x + width, y + height, dx + act * Global.IMG_X_OFFSET, dy + direction * Global.IMG_Y_OFFSET, dx + 32 + act*Global.IMG_X_OFFSET, dy + 32 + direction * Global.IMG_Y_OFFSET, null);
         g.setColor(Color.red);
            g.drawRect(x - Global.HEALTHX , y+height, width + 2*Global.HEALTHX,25);
            float temp1 = (float)health/originalhealth * (width + 2 * Global.HEALTHX);
            g.fillRect(x - Global.HEALTHX , y+height, (int)temp1, 25);    
                
    
    }
}
