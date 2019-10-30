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
        digit = new NumberIcon(digitX, digitY, 35, 70, "個位數", 0);
        tenDigit = new NumberIcon(digitX - 40, digitY, 35, 70, "十位數", 0);
        sword = ImageResourceController.getInstance().tryGetImage("/resources/Icon/attack2.png");
        shield = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Defense2.png");
    }

    public void paint(Graphics g, int x, int y, int width, int height, int attack, int defense) {
        digit.setX(x+25);
        digit.setY(y+130);
        tenDigit.setX(x+25);
        tenDigit.setY(y+130);
        
        digit.paint(g);
        tenDigit.paint(g);
        g.drawImage(sword, x+25, y+130, null)    ;
    }
}
