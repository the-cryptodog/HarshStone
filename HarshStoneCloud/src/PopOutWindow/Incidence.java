/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PopOutWindow;

import Controller.PathBuilder;
import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.GameObject;
import io.CommandSolver;
import io.CommandSolver.MouseCommandListener;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import scene.MapScene;
import scene.MenuScene;
import scene.Scene;
import scene.SelectJobSceneState;
import values.ImagePath;

/**
 *
 * @author User
 */
public class Incidence extends GameObject {
    
    
    protected CommandSolver.MouseCommandListener mousecommandlistener;
    private BufferedImage img;
    private Button exit;
    private boolean startPressed;

    public Incidence(int x, int y , int width, int height, String name) {
        super(x,y,width,height,name);
        img = irc.tryGetImage(PathBuilder.getIncidence("/"+name+".png"));
        exit = new Button(900, 450, 108, 40, "BACK");


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
