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
public class Button extends GameObject {

    protected BufferedImage image;
    protected boolean isclicked; 

    public Button(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        this.image = irc.getInstance().tryGetImage(PathBuilder.getButton("/"+name+".png"));
    }

    public void paint(Graphics g) {
        g.drawImage(image, x, y, width, height, null);

    }
        public void setIsClicked(boolean isclicked){
        this.isclicked = isclicked;
    }
    public boolean getIsClicked(){
        return isclicked;
    }

    public static class StartButton {

        public StartButton() {
        }
    }
}
