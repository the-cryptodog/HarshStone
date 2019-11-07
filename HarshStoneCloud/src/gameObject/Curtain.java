/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.PathBuilder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import Controller.ImageResourceController;
import Controller.PathBuilder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author User
 */
public class Curtain extends GameObject {
    //  protected int[] Act = {0,1,2,3,4,5};

    protected DelayCounter delaycounter;
    protected int count;
    private BufferedImage curtain1;
    private BufferedImage curtain2;
    private BufferedImage curtain3;
    private BufferedImage curtain4;
    protected BufferedImage[] curtains;
    protected int time;

    public Curtain(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);

        curtains = new BufferedImage[4];
        time = 0;
        count = 0;
        curtain1 = irc.tryGetImage("/resources/Incidence/DARKEN1.png");
        curtain2 = irc.tryGetImage("/resources/Incidence/DARKEN2.png");
        curtain3 = irc.tryGetImage("/resources/Incidence/DARKEN3.png");
        curtain4 = irc.tryGetImage("/resources/Incidence/DARKEN4png");

        curtains[0] = curtain1;
        curtains[1] = curtain2;
        curtains[2] = curtain3;
        curtains[3] = curtain4;

        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });
    }

    public void positionSetter(GameObject gameobject) {
        this.x = gameobject.getX() - 15;
        this.y = gameobject.getY() - 15;
        this.width = gameobject.getWidth() + 15;
        this.height = gameobject.getHeight() + 15;

    }

    public void move() {
        if (time < 2) {
            if (delaycounter.delayupdate()) {
                count = ++count % 4;
                time++;
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(curtains[count], x, y, width, height, null);
    }
}
