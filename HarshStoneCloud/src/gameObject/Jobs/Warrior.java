/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Jobs;

import Controller.PathBuilder;
import gameObject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import values.ImagePath;

/**
 *
 * @author User
 */
public class Warrior extends GameObject {

    protected BufferedImage image;
    protected BufferedImage image2;
    protected boolean isclicked;

    public Warrior(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        this.image = irc.getInstance().tryGetImage(PathBuilder.getJobs(ImagePath.Warrior));
        this.image2 = irc.getInstance().tryGetImage(PathBuilder.getJobs(ImagePath.Warrior2));
    }

    public void isclicked() {
        isclicked = true;
    }

    public void paint(Graphics g) {
        if (isclicked) {
            g.drawImage(image2, x, y, width, height, null);
        } else {
            g.drawImage(image, x, y, width, height, null);
        }
    }
}
