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
    private int[] digitDeal;
    private int digitX;
    private int digitY;
    private float af; //放大係數

    /**
     *
     * @param card
     */
    public CardIconHelper(Card card) {

        twoEffect = new ArrayList<>();
        atr = new ArrayList<>();
        af = 1;

        atr.add(card.getAttack());
        atr.add(card.getDefense());
        atr.add(card.getWeak());
        atr.add(card.getPoison());
        atr.add(card.getFrozen());
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
                        twoEffect.add(poison);
                        break;
                    case 4:
                        twoEffect.add(frozen);
                        break;
                    case 5:
                        twoEffect.add(heal);
                }
            }
        }

        for (int i = 0; i < atr.size(); i++) {
            if (atr.get(i) == 0) {
                atr.remove(i);
                i--;
            }
        }

        System.out.println("技能種類有" + twoEffect.size() + "種");
        System.out.print("技能1傷害" + atr.get(0));

        digitX = (int) (card.getX() + (Global.CARDWIDTH / 2) * af);
        digitY = (int) (card.getY() + (Global.CARDHEIGHT / 2) * af);

        digit = new NumberIcon(digitX, digitY, (int) (44 * af), (int) (52 * af), "個位數", 0);
        tenDigit3 = new NumberIcon(digitX, digitY, (int) (44 * af), (int) (52 * af), "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + (int) (32 * af), (int) (55 * af), (int) (35 * af), "個位數", 0);
        digit2 = new NumberIcon(digitX, digitY + (int) (32 * af), (int) (55 * af), (int) (35 * af), "個位數", 0);
        tenDigit = new NumberIcon(digitX - (int) (40 * af), digitY, (int) (55 * af), (int) (65 * af), "十位數", 1);
        tenDigit2 = new NumberIcon(digitX - (int) (40 * af), digitY, (int) (55 * af), (int) (65 * af), "十位數", 1);

    }

    public void setAf(float af) {
        this.af = af;
    }

    public void paint(Graphics g, int x, int y, int width, int height) {

        if (twoEffect.size() < 2) { //單效果
            g.drawImage(twoEffect.get(0), x + (int) ((Global.DIGIT1X + 25) * af), y + (int) ((125 * Global.LARGEN) * af),
                    (int) (((50 * Global.LARGEN) - 20) * af),
                    (int) (((50 * Global.LARGEN) - 20) * af), null);
            if (atr.get(0) < 10) {
                digit.setX((int) (x + (Global.DIGIT3X) * af));
                digit.setY((int) (y + ((Global.CARDHEIGHT / 2) + 38) * af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.setNumber(atr.get(0));
                digit.paint(g);
            } else {
                digit.setX((int) (x + (Global.DIGIT4X - 20) * af));
                digit.setY((int) (y + ((Global.CARDHEIGHT / 2) + 38) * af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.setNumber(atr.get(0) % 10);

                tenDigit3.setWidth((int) (44 * af));
                tenDigit3.setHeight((int) (52 * af));
                tenDigit3.setX((int) (x + (Global.DIGIT3X - 20) * af));
                tenDigit3.setX((int) (x + (Global.DIGIT3X - 20) * af));
                tenDigit3.setY((int) (y + ((Global.CARDHEIGHT / 2) + 38) * af));

                tenDigit3.setNumber(atr.get(0) / 10);
                digit.paint(g);
                tenDigit3.paint(g);
            }
        }
        if (twoEffect.size() >= 2) {//雙效果的情形 
            g.drawImage(twoEffect.get(0), x + (int) (43 * af), y + (int) (150 * af),
                    (int) (((26 * Global.LARGEN) - 5) * af),
                    (int) (((26 * Global.LARGEN) - 5) * af), null);
            g.drawImage(twoEffect.get(1), x + (int) (113 * af), y + (int) (150 * af),
                    (int) (((26 * Global.LARGEN) - 5) * af),
                    (int) (((26 * Global.LARGEN) - 5) * af), null);

            if (atr.get(0) < 10 && atr.get(1) < 10) { //單單
                digit.setNumber(atr.get(0));
                digit.setX((int)(x + (Global.DIGIT2X - 10)*af));
                digit.setY((int)(y + ((Global.CARDHEIGHT / 2) +50)*af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.paint(g);
//=--------------------------------------------------------------------
                digit2.setNumber(atr.get(1));
                digit2.setX((int)(x + (Global.DIGIT4X - 30)*af));
                digit2.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit2.setWidth((int) (44 * af));
                digit2.setHeight((int) (52 * af));
                digit2.paint(g);

            }
            if (atr.get(0) < 10 && atr.get(1) >= 10) { //單雙           

                digit.setNumber(atr.get(0));           //個位數
                digit.setX((int)(x + (Global.DIGIT2X - 10)*af));
                digit.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.paint(g);

                //=--------------------------------------------------------------------    //單雙            
                tenDigit2.setNumber(atr.get(1) / 10);
                tenDigit2.setX((int)(x + (Global.DIGIT2X - 10)*af));
                tenDigit2.setY((int)(y + ((Global.CARDHEIGHT / 2) +50)*af));
                tenDigit2.setWidth((int) (44 * af));
                tenDigit2.setHeight((int) (52 * af));
                tenDigit2.paint(g);
                //=-------------------------------------------------------------------- //單雙         

                digit2.setNumber(atr.get(1) % 10);
                digit2.setX((int)(x + (Global.DIGIT4X - 18)*af));
                digit2.setY((int)(y + ((Global.CARDHEIGHT / 2) +50)*af));
                digit2.setWidth((int) (44 * af));
                digit2.setHeight((int) (52 * af));
                digit2.paint(g);

            }

            if (atr.get(0) >= 10 && atr.get(1) < 10) { //雙單

                tenDigit.setNumber(atr.get(0) / 10);
                tenDigit.setX((int)(x +( Global.DIGIT1X + 10)*af));
                tenDigit.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                tenDigit.setWidth((int) (44 * af));
                tenDigit.setHeight((int) (52 * af));
                tenDigit.paint(g);
                //=--------------------------------------------------------------------    //雙單
                digit.setNumber(atr.get(0) % 10);           //個位數
                digit.setX((int)(x + (Global.DIGIT2X + 5)*af));
                digit.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.paint(g);
                //=-------------------------------------------------------------------- //雙單
                digit2.setNumber(atr.get(1) % 10);
                digit2.setX((int)(x + (Global.DIGIT4X - 30)*af));
                digit2.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit2.setWidth((int) (44 * af));
                digit2.setHeight((int) (52 * af));
                digit2.paint(g);

            }
            if (atr.get(0) >= 10 && atr.get(1) >= 10) { //雙雙

                tenDigit.setNumber(atr.get(0) / 10);
                tenDigit.setX((int)(x +(Global.DIGIT1X + 10)*af));
                tenDigit.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                tenDigit.setWidth((int) (44 * af));
                tenDigit.setHeight((int) (52 * af));
                tenDigit.paint(g);
                //=--------------------------------------------------------------------    //雙雙
                digit.setNumber(atr.get(0) % 10);           //個位數
                digit.setX((int)(x + (Global.DIGIT2X + 5)*af));
                digit.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit.setWidth((int) (44 * af));
                digit.setHeight((int) (52 * af));
                digit.paint(g);
                //=--------------------------------------------------------------------    //雙雙
                tenDigit2.setNumber(atr.get(1) / 10);
                tenDigit2.setX((int)(x + (Global.DIGIT3X - 10)*af));
                tenDigit2.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                tenDigit2.setWidth((int) (44 * af));
                tenDigit2.setHeight((int) (52 * af));
                tenDigit2.paint(g);

                //=-------------------------------------------------------------------- //雙雙
                digit2.setNumber(atr.get(1) % 10);
                digit2.setX((int)(x + (Global.DIGIT4X - 13)*af));
                digit2.setY((int)(y + ((Global.CARDHEIGHT / 2) + 50)*af));
                digit2.setWidth((int) (44 * af));
                digit2.setHeight((int) (52 * af));
                digit2.paint(g);

            }
        }
    }

}
