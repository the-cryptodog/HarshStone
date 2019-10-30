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
        tenDigit.setX(x+55);
        tenDigit.setY(y+130);
        tenDigit.paint(g);
        }
        
        digit.setX(x+80);
        digit.setY(y+110);
        digit.paint(g);

        g.drawImage(sword, x+5, y+100, null)    ;
        g.drawImage(shield, x+5, y+100, null)    ;
    }
}
