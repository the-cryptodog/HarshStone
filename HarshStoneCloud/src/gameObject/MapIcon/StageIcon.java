/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.MapIcon;

import Controller.PathBuilder;
import gameObject.Button.Button;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import values.ImagePath;

/**
 *
 * @author User
 */
public class StageIcon extends Button {

    protected boolean stagePassed;
    protected BufferedImage redCross;

    public StageIcon(int x, int y, int width, int height, String name) {

        super(x, y, width, height, name);
        stagePassed = false;
        System.out.print(name);
        this.image = irc.getInstance().tryGetImage(PathBuilder.getButton(ImagePath.STAGEICON));
        this.redCross = irc.getInstance().tryGetImage(PathBuilder.getButton(ImagePath.REDCROSS));

    }

    @Override
    public void paint(Graphics g) {
            g.drawImage(image, x, y, width, height, null);
            if (!stagePassed) {
            g.drawImage(redCross, x, y, width, height, null);
        }
    }
}
