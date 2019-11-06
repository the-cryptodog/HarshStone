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

        digit = new NumberIcon(0, 0, "個位數", atr.get(0), 0.4f);
        if (atr.size() > 1) {
            digit2 = new NumberIcon(0, 0, "個位數", atr.get(1), 0.4f);
        }
    }

    public void setAf(float af) {
        this.af = af;
    }

    public void paint(Graphics g, int x, int y, int width, int height) {

        if (twoEffect.size() < 2) { //單效果
            g.drawImage(twoEffect.get(0), x + (int) ((Global.DIGIT1X + 20) * af), y + (int) ((135 * Global.LARGEN) * af),
                    (int) (((50 * Global.LARGEN) - 27) * af),
                    (int) (((50 * Global.LARGEN) - 27) * af), null);
            digit.setY((int) (y + (180 * af))-15);
            digit.setX((int) (x + (Global.DIGIT2X) * af)-15);
            digit.setRate(af/2.3f);
            digit.paint(g);
        }
        if (twoEffect.size() >= 2) {//雙效果的情形 
            g.drawImage(twoEffect.get(0), x + (int) (43 * af), y + (int) (150 * af),
                    (int) (((26 * Global.LARGEN) - 5) * af),
                    (int) (((26 * Global.LARGEN) - 5) * af), null);
            g.drawImage(twoEffect.get(1), x + (int) (113 * af), y + (int) (150 * af),
                    (int) (((26 * Global.LARGEN) - 5) * af),
                    (int) (((26 * Global.LARGEN) - 5) * af), null);
            digit.setY((int) (y + (180 * af)));
            digit.setX((int) (x + (Global.DIGIT1X) * af));
            digit.setRate(af/2.5f);
            digit.paint(g);
            digit2.setY((int) (y + (180 * af)));
            digit2.setX((int) (x + (Global.DIGIT2X) * af));
            digit2.setRate(af/2.5f);
            digit2.paint(g);
        }
    }
}
