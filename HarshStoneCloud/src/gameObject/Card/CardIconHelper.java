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
import java.util.ArrayList;
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
    private NumberIcon tenDigit2;
    private BufferedImage sword;
    private BufferedImage shield;
    private BufferedImage weak;
    private BufferedImage frozen;
    private BufferedImage poison;
    private BufferedImage heal;
    private ArrayList<BufferedImage> twoEffect;
    private ArrayList<Integer> atr;
    int digitX;
    int digitY;

    /**
     *
     * @param card
     */
    public CardIconHelper(Card card) {

        twoEffect = new ArrayList<>();
        atr = new ArrayList<>();
        
        
        atr.add(card.getAttack());
        atr.add(card.getDefense());
        atr.add(card.getWeak());
        atr.add(card.getFrozen());
        atr.add(card.getPoison());
        atr.add(card.getHeal());



        for (int i = 0; i < atr.size(); i++) {      
            if (atr.get(i) != 0) {
                switch (i) {
                    case 0:
                        twoEffect.add(sword);
                        break;
                    case 1:
                        twoEffect.add(shield);
                        break;
                    case 2:
                        twoEffect.add(weak);
                        break;
                    case 3:
                        twoEffect.add(frozen);
                        break;
                    case 4:
                        twoEffect.add(poison);
                        break;
                    case 5:
                        twoEffect.add(heal);
                }
            }
        }
        
          System.out.print("技能種類=!!!!!!!!! "+ twoEffect.size());

        digitX = card.getX() + (Global.CARDWIDTH / 2);
        digitY = card.getY() + (Global.CARDHEIGHT / 2);

        digit = new NumberIcon(digitX, digitY, 55, 65, "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + 32, 55, 35, "個位數", 0);
        tenDigit = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);
        tenDigit2 = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);

        sword = ImageResourceController.getInstance().tryGetImage("/resources/Icon/attack2.png");
        shield = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Defense2.png");
        weak = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Weak2.png");
        frozen = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Frozen2.png");
        poison = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Poison2.png");
        heal = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Heal2.png");

    }

    public void paint(Graphics g, int x, int y, int width, int height) {
        if (twoEffect.size() < 2) { //單效果
            g.drawImage(twoEffect.get(0), x + 30, y + 125, 220, 220, null);
            if (atr.get(0) < 10) {
                digit.setX(x+ Global.DIGIT4X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setNumber(atr.get(0));
                digit.paint(g);
            } else {
                digit.setX(x+ Global.DIGIT3X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setNumber(atr.get(0) % 10);
                tenDigit.setX(x+ Global.DIGIT3X);
                tenDigit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setY(digitY);
                tenDigit.setNumber(atr.get(0) / 10);
                
                digit.paint(g);
                tenDigit.paint(g);
            }
        }
        if (twoEffect.size() >= 2) {//雙效果的情形 
            g.drawImage(twoEffect.get(0), x + 15, y + 125, 25, 25, null);
            g.drawImage(twoEffect.get(1), x + 85, y + 125, 25, 25, null);
            
            if (atr.get(0) < 10 && atr.get(1) < 10) { //單單
                digit.setNumber(atr.get(0));           //個位數
                digit.setX(x + Global.DIGIT2X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setWidth(33);
                digit.setHeight(39);
                digit.paint(g);
//=--------------------------------------------------------------------
                digit2.setNumber(atr.get(0));
                digit2.setX(x + Global.DIGIT4X);
                digit2.setY(x + y + (Global.CARDHEIGHT / 2));
                digit2.setWidth(33);
                digit2.setHeight(39);
                digit2.paint(g);

            }
            if (atr.get(0) < 10 && atr.get(1) >= 10) { //單雙           

                digit.setNumber(atr.get(0));           //個位數
                digit.setX(x + Global.DIGIT1X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setWidth(33);
                digit.setHeight(39);
                digit.paint(g);

                //=--------------------------------------------------------------------    //單雙            
                tenDigit2.setNumber(atr.get(0) / 10);
                tenDigit2.setX(x + Global.DIGIT2X);
                tenDigit2.setY(y + (Global.CARDHEIGHT / 2));
                tenDigit2.setWidth(22);
                tenDigit2.setHeight(26);
                tenDigit2.paint(g);
                //=-------------------------------------------------------------------- //單雙         

                digit2.setNumber(atr.get(0) % 10);
                digit2.setX(x + Global.DIGIT3X);
                digit2.setY(y + (Global.CARDHEIGHT / 2));
                digit2.setWidth(22);
                digit2.setHeight(26);
                digit2.paint(g);

            }

            if (atr.get(0) < 10 && atr.get(1) >= 10) { //雙單

                tenDigit.setNumber(atr.get(0) / 10);
                tenDigit.setX(x + Global.DIGIT1X);
                tenDigit.setY(y + (Global.CARDHEIGHT / 2));
                tenDigit.setWidth(22);
                tenDigit.setHeight(26);
                tenDigit.paint(g);
                //=--------------------------------------------------------------------    //雙單
                digit.setNumber(atr.get(0) % 10);           //個位數
                digit.setX(x + Global.DIGIT2X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setWidth(22);
                digit.setHeight(26);
                digit.paint(g);
                //=-------------------------------------------------------------------- //雙單
                digit2.setNumber(atr.get(0) % 10);
                digit2.setX(x + Global.DIGIT4X);
                digit2.setY(y + (Global.CARDHEIGHT / 2));
                digit2.setWidth(33);
                digit2.setHeight(39);
                digit2.paint(g);

            }
            if (atr.get(0) < 10 && atr.get(1) >= 10) { //雙雙

                tenDigit.setNumber(atr.get(0) / 10);
                tenDigit.setX(x + Global.DIGIT1X);
                tenDigit.setY(y + (Global.CARDHEIGHT / 2));
                tenDigit.setWidth(22);
                tenDigit.setHeight(26);
                tenDigit.paint(g);
                //=--------------------------------------------------------------------    //雙雙
                digit.setNumber(atr.get(0) % 10);           //個位數
                digit.setX(x + Global.DIGIT2X);
                digit.setY(y + (Global.CARDHEIGHT / 2));
                digit.setWidth(22);
                digit.setHeight(26);
                digit.paint(g);
                //=--------------------------------------------------------------------    //雙雙
                tenDigit2.setNumber(atr.get(1) / 10);
                tenDigit2.setX(x + Global.DIGIT3X);
                tenDigit2.setY(y + (Global.CARDHEIGHT / 2));
                tenDigit2.setWidth(22);
                tenDigit2.setHeight(26);
                tenDigit2.paint(g);

                //=-------------------------------------------------------------------- //雙雙
                digit2.setNumber(atr.get(1) % 10);
                digit2.setX(x + Global.DIGIT4X);
                digit2.setY(y + (Global.CARDHEIGHT / 2));
                digit2.setWidth(22);
                digit2.setHeight(26);
                digit2.paint(g);

            }
        }
    }

}
