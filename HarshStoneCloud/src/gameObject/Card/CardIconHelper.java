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
public class CardIconHelper {

    private NumberIcon digit;
    private NumberIcon digit2;
    private NumberIcon tenDigit;
    private BufferedImage sword;
    private BufferedImage shield;
    private int atk;
    private int def;
    int [] = 
    int digitX;
    int digitY;

    /**
     *
     * @param card
     */
    public CardIconHelper(Card card) {
        atk = card.getAttack();
        def = card.getDefense();
        digitX = card.getX() + (Global.CARDWIDTH / 2);
        digitY = card.getY() + (Global.CARDHEIGHT / 2);
        digit = new NumberIcon(digitX, digitY, 55, 65, "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + 32, 55, 35, "個位數", 0);
        tenDigit = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);
        sword = ImageResourceController.getInstance().tryGetImage("/resources/Icon/attack2.png");
        shield = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Defense2.png");
    }

    public void paint(Graphics g, int x, int y, int width, int height, int attack, int defense) {
            
        
        if (atk == 0 && def < 10) {
            digit.setNumber(def);
            digit.paint(g);
            g.drawImage(shield, x + 30, y + 125, 25, 25, null);
        }
        if (def == 0 && atk < 10) {
            digit.setNumber(atk);
              digit.paint(g);
            g.drawImage(sword, x + 30, y + 150, 25, 25, null);
        }
        if (attack > 9 || defense > 9) {
            if (attack > 9) {
                tenDigit.setNumber(atk / 10);
            }
            if (defense > 9) {
                tenDigit.setNumber(def / 10);
            }
            tenDigit.setX(x + 5);
            tenDigit.setY(y + 130);
            tenDigit.paint(g);
        }
        if (atk != 0 && atk != 0) {
            g.drawImage(shield, x + 30, y + 125, 25, 25, null);
            g.drawImage(sword, x + 80, y + 125, 25, 25, null);
            digit.setWidth(27);
            digit.setHeight(30);
            digit2.paint(g);
        } else {
            digit.setX(x + 70);
            digit.setY(y + 115);
            digit.paint(g);
        }
    }
}
