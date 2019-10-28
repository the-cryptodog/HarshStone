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

    public interface ButtonListener {

        public void onClick(int x, int y);

        public void hover(int x, int y);

    }

    private ButtonListener buttonListener;

    protected BufferedImage[] image;
    protected BufferedImage image2;
    protected BufferedImage currentImage;
    protected boolean isclicked;

    public Button(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        image = new BufferedImage[2];
        
        image[0] = irc.getInstance().tryGetImage(PathBuilder.getButton("/" + name + ".png"));
        
        if (irc.getInstance().tryGetImage(PathBuilder.getButton("/" + name + "2.png")) != null) {
            System.out.print("dddd");
            image[1] = irc.getInstance().tryGetImage(PathBuilder.getButton("/" + name + "2.png"));
        } else {
            image[1] = irc.getInstance().tryGetImage(PathBuilder.getButton("/" + name + "png"));
        }
        currentImage = image[0];
    }

    public void click(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.onClick(x, y);
    }

    public void hover(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.hover(x, y);

    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public void paint(Graphics g) {
        g.drawImage(currentImage, x, y, width, height, null);


    }

    @Override
    public boolean isCollision(int x, int y) {
        if (x < this.x || x > this.x + width) {
            currentImage = image[0];
            return false;
        }
        if (y < this.y || y > this.y + height) {
            currentImage = image[0];
            return false;
        }
        currentImage = image[1];
        return true;

    }


    

    public void setIsClicked(boolean isclicked) {
        this.isclicked = isclicked;
    }

    public boolean getIsClicked() {
        return isclicked;
    }

}
