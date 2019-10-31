/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import gameObject.GameObject;
import gameObject.NumberIcon;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class CardIconHelper{

    private NumberIcon digit;
    private NumberIcon tenDigit;
    private BufferedImage sword;
    private BufferedImage shield;
    int digitX;
    int digitY;

    /**
     *
     * @param card
     */
    public CardIconHelper(Card card) {
        digitX = card.getX() + (Global.CARDWIDTH / 2);
        digitY = card.getY() + (Global.CARDHEIGHT / 2);
        digit = new NumberIcon(digitX, digitY, 55, 65, "個位數", 0);
        tenDigit = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);
        sword = ImageResourceController.getInstance().tryGetImage("/resources/Icon/attack2.png");
        shield = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Defense2.png");
    }

    public void paint(Graphics g, int x, int y, int width, int height, int attack, int defense) {
   
        if(attack >9 || defense>9){
        tenDigit.setX(x+5);
        tenDigit.setY(y+130);
        tenDigit.paint(g);
        }
        
        digit.setX(x+70);
        digit.setY(y+115);
        digit.paint(g);
        if(attack==0){
        g.drawImage(shield, x+30, y+125, 50,50,null)    ;
        }
        if(defense==0){
        g.drawImage(sword, x+30, y+125,50,50, null)    ;
        }
        if(attack !=0 && defense !=0){
            g.drawImage(shield, x+30, y+125, 50,50,null)    ;
            g.drawImage(sword, x+30, y+125,50,50, null)    ;
        } 
    }
}
