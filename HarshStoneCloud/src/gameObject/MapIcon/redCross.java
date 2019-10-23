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

    protected boolean isClicked;
    protected BufferedImage redCross;

    public redCross(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        isClicked = true;
        this.redCross = irc.getInstance().tryGetImage(PathBuilder.getMap(ImagePath.REDCROSS));
    }

    public void setClick() {
        this.isClicked = true;
    }

    @Override
    public void paint(Graphics g) {
        if (isClicked) {
            g.drawImage(redCross, x, y, width, height, null);
        }
    }
    @Override
    public String toString(){
      String str ="";
      return str+= x +" "+ y ;
    }
    
}
