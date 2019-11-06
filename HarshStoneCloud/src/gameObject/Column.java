/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.PathBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import values.ImagePath;

/**
 *
 * @author User
 */
public class Column extends GameObject {
    //  protected int[] Act = {0,1,2,3,4,5};

    protected DelayCounter delaycounter;
    protected int count;
    protected BufferedImage column1;
    protected BufferedImage column2;
    protected BufferedImage column3;
    protected BufferedImage column4;
    protected BufferedImage[] columns;

    public Column(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);

        columns = new BufferedImage[4];
        count = 0;
        column1 = irc.tryGetImage(PathBuilder.getMonster("/COLUMN1.png"));
        column2 = irc.tryGetImage(PathBuilder.getMonster("/COLUMN2.png"));
        column3 = irc.tryGetImage(PathBuilder.getMonster("/COLUMN3.png"));
        column4 = irc.tryGetImage(PathBuilder.getMonster("/COLUMN4.png"));

        columns[0] = column1;
        columns[1] = column2;
        columns[2] = column3;
        columns[3] = column4;

        delaycounter = new DelayCounter(10, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });
    }

    public void positionSetter(GameObject gameobject) {
        this.x=  gameobject.getX()-15;
        this.y= gameobject.getY()-15;
        this.width = gameobject.getWidth()+15; 
        this.height = gameobject.getHeight()+15;

    }

    public void move() {
        if (delaycounter.delayupdate()) {
            count = ++count % 4;
        }
    }

    public void paint(Graphics g) {
        g.drawImage(columns[count], x, y, width, height, null);
    }
}

