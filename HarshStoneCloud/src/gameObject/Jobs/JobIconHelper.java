/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Jobs;

import Controller.ImageResourceController;
import Controller.PathBuilder;

import java.awt.*;
import java.awt.image.*;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class JobIconHelper {

    private BufferedImage img;
    private int actorPosition;


    public JobIconHelper(int actor) {
        img = getActor(actor);//依照使用者指定的Actor去抓圖
        //計算這個actor在圖中的位置
        actorPosition = actor % 8;
    
    }

    private BufferedImage getActor(int actor) {
        ImageResourceController irc = ImageResourceController.getInstance();
        if (actor >= 0 && actor < 8) {
            return irc.tryGetImage(PathBuilder.getJobs(ImagePath.ACTOR1));
        }
        return null;
    }
    
    public void paint(Graphics g, int x, int y, int width, int height, int act, int direction) {
        if(img == null){
            return ;
        }       
        int cx = 96 * (actorPosition % 4);  // 0 1 2 3 4 5 6 7
        int cy = 128 * (actorPosition / 4); // 1或2 換句話說y非0即128
        
        g.drawImage(img, x, y, x + width, y + height,        
                cx + act * Global.IMG_X_OFFSET, cy + 0 + direction * Global.IMG_Y_OFFSET,
                cx +32 + act * Global.IMG_X_OFFSET, cy  + 32 + direction * Global.IMG_Y_OFFSET, null);
    }
}
