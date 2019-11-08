/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopUpWindow;

import Controller.PathBuilder;
import gameObject.Button.Button;
import gameObject.Card.Card;
import gameObject.Card.CardFactory;
import gameObject.GameObject;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author User
 */
public class Award extends GameObject {

    protected CommandSolver.MouseCommandListener mousecommandlistener;
    private BufferedImage img;
    private CardFactory rareCardFactory;
    private ArrayList<Card> awards;
    private int count;
    private int[] rareCardIndex;
    private Button backToMap;
    private boolean cardSelected;
    private float awardSize;

    public Award(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);

        img = irc.tryGetImage(PathBuilder.getIncidence("/" + name + ".png"));
        awards = new ArrayList<>();
        rareCardFactory = new CardFactory();
        rareCardFactory.readRareCardData();
        rareCardIndex = new int[3];
        count = 0;
        awardSize = Global.AWARDSIZE;
        backToMap = new Button(1400, 860, 108, 40, "EXIT");
        cardSelected = false;

        System.out.print("目前關卡=" + Global.CURRENTSTAGE + ")");

        switch (Global.CURRENTSTAGE) {
            case 1:
                for (int i = 50; i < 53; i++) {
                    rareCardIndex[count++] = i;

                }
                break;
            case 2:
                for (int i = 53; i < 56; i++) {
                    rareCardIndex[count++] = i;

                }
                break;
            case 3:
                for (int i = 56; i < 59; i++) {
                    rareCardIndex[count++] = i;

                }
                break;
            case 4:
                for (int i = 59; i < 62; i++) {
                    System.out.println("count = " + count);
                    System.out.println("長度 = " + rareCardIndex.length);
                    rareCardIndex[count++] = i;
                }
                break;
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(awards.size());
            System.out.print("rareCardIndex[i] = " + rareCardIndex[i]);
            awards.add(rareCardFactory.genCard(rareCardIndex[i]));
            awards.get(i).setWidth((int) (Global.CARDWIDTH * awardSize));
            awards.get(i).setHeight((int) (Global.CARDHEIGHT * awardSize));
            awards.get(i).getCardIconHelper().setAf(awardSize);
            awards.get(i).setX(400 + i * 400);
            awards.get(i).setY(350);

//                    awards.get(i).setX();
        }
        count = 0;

    }

    public void setCommandListener(CommandSolver.MouseCommandListener mcl) {
        this.mousecommandlistener = mcl;
    }

    public ArrayList<Card> getAward() {
        return awards;
    }

    public void setSelected(boolean setSelected) {
        this.cardSelected = setSelected;
    }

    public Button getButton() {
        return backToMap;

    }

    public void paint(Graphics g) {
        g.drawImage(img, (1920 - Global.AWARDWIDTH) / 2, (1080 - Global.AWARDHEIGHT) / 2,
                Global.AWARDWIDTH, Global.AWARDHEIGHT, null);
        for (int i = 0; i < 3; i++) {
            if (Global.CURRENTSTAGE != 5) {
                awards.get(i).paint(g);
            }
        }
        if (cardSelected ||Global.CURRENTSTAGE == 5) {
            backToMap.paint(g);
        }
    }
}
