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
    private NumberIcon tenDigit3;
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

           sword = ImageResourceController.getInstance().tryGetImage("/resources/Icon/attack2.png");
        shield = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Defense2.png");
        weak = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Weak2.png");
        frozen = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Frozen2.png");
        poison = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Poison2.png");
        heal = ImageResourceController.getInstance().tryGetImage("/resources/Icon/Heal2.png");

        for (int i = 0; i < atr.size(); i++) {      
            if (atr.get(i) != 0) {
                switch (i) {
                    case 0:
                        twoEffect.add(sword);
                          System.out.println("新增成功");
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
        
          System.out.println("技能種類有"+ twoEffect.size()+"種");   
          System.out.print(twoEffect.get(0));
         

        digitX = card.getX() + (Global.CARDWIDTH / 2);
        digitY = card.getY() + (Global.CARDHEIGHT / 2);

        digit = new NumberIcon(digitX, digitY, 44, 52, "個位數", 0);
        tenDigit3 = new NumberIcon(digitX, digitY, 44, 52, "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + 32, 55, 35, "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + 32, 55, 35, "個位數", 0);
        tenDigit = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);
        tenDigit2 = new NumberIcon(digitX - 40, digitY, 55, 65, "十位數", 1);

       

    }

    public void paint(Graphics g, int x, int y, int width, int height) {
  
        if (twoEffect.size() < 2) { //單效果
            g.drawImage(twoEffect.get(0), x+Global.DIGIT1X+20, y +(int)(125*Global.LARGEN),
                    (int)(50*Global.LARGEN)-10,
                    (int)(50*Global.LARGEN)-10, null);
            if (atr.get(0) < 10) {
                digit.setX(x+ Global.DIGIT3X);
                digit.setY(y + (Global.CARDHEIGHT / 2)+38);
                digit.setNumber(atr.get(0));
                digit.paint(g);
            } else {
                digit.setX(x+ Global.DIGIT4X-20);
                digit.setY(y + (Global.CARDHEIGHT / 2)+38);
                digit.setNumber(atr.get(0) % 10);
                tenDigit3.setX(x+ Global.DIGIT3X-20);
                tenDigit3.setY(y + (Global.CARDHEIGHT / 2)+38);

                tenDigit3.setNumber(atr.get(0) / 10);      
                 digit.paint(g);
                tenDigit3.paint(g);
            }
        }
        if (twoEffect.size() >= 2) {//雙效果的情形 
            g.drawImage(twoEffect.get(0), x + 23, y + 125, 26, 26, null);
            g.drawImage(twoEffect.get(1), x + 80, y + 125, 26, 26, null);
            
            if (atr.get(0) < 10 && atr.get(1) < 10) { //單單
                digit.setNumber(atr.get(0));           //個位數
                digit.setX(x + Global.DIGIT2X);
                digit.setY(y + (Global.CARDHEIGHT / 2)+40);
                digit.setWidth(33);
                digit.setHeight(39);
                digit.paint(g);
//=--------------------------------------------------------------------
                digit2.setNumber(atr.get(1));
                digit2.setX(x + Global.DIGIT4X);
                digit2.setY( y + (Global.CARDHEIGHT / 2)+40);
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
