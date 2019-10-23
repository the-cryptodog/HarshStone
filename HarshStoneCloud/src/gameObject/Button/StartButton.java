/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Button;

import Controller.PathBuilder;
import gameObject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class StartButton extends Button {

    protected BufferedImage PressedImage;
    protected boolean isclicked;

    public StartButton(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        this.image = irc.getInstance().tryGetImage(PathBuilder.getButton(ImagePath.START1));
        this.PressedImage = irc.getInstance().tryGetImage(PathBuilder.getButton(ImagePath.START2));
        isclicked = false;
    }

    public void setIsClicked(boolean isclicked) {
        this.isclicked = isclicked;
    }

    public boolean getIsClicked() {
        return isclicked;
    }

    public void paint(Graphics g) {
        if (!isclicked) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.drawImage(PressedImage , x, y, width, height, null);
        }
    }
}
