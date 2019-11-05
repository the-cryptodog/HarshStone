/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopUpWindow;

import Controller.PathBuilder;
import gameObject.Button.Button;
import gameObject.GameObject;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 *
 * @author User
 */
public class Incidence extends GameObject {
    
    
    protected CommandSolver.MouseCommandListener mousecommandlistener;
    private BufferedImage img;
    private Button exit;

    public Incidence(int x, int y , int width, int height, String name) {
        super(x,y,width,height,name);
        img = irc.tryGetImage(PathBuilder.getIncidence("/"+name+".png"));
        exit = new Button(x+50, y+250, 108, 40, "BACK");


    }
    public Button getButton(){
        return exit;
      
    }
    public void setCommandListener(CommandSolver.MouseCommandListener mcl){
        this.mousecommandlistener = mcl;
    }

  
    public void paint(Graphics g) {
        g.drawImage(img, 384, 216, 1536, 864, 0, 0, 1920, 1080, null);
        exit.paint(g);
    }
}
