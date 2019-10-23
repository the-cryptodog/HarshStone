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
public class redCross extends Button {
    
    private int x;
    private int y;
    protected boolean stagePassed;
    protected BufferedImage redCross;

    public redCross(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        stagePassed = false;
        this.redCross = irc.getInstance().tryGetImage(PathBuilder.getButton(ImagePath.REDCROSS));
    }

    public void cordSetter(int x,int y ) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        if(isclicked && stagePassed)
        g.drawImage(redCross, x, y, width, height, null);
    }
}
