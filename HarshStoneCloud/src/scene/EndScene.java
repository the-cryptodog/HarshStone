/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;


import Controller.SceneController;
import gameObject.Button.Button;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class EndScene extends Scene{

    private BufferedImage img;
    private Button backToMenu;
    private SelectJobSceneState menuscenestate;
    private boolean startPressed;


    public EndScene(SceneController scenecontroller){
          super(scenecontroller);
          img = irc.tryGetImage("/resources/Background/ENDSCENE.png");
          backToMenu = new Button(900, 860, 108, 40, "BACK");
          mousecommandlistener = new CommandSolver.MouseCommandListener(){

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == CommandSolver.MouseState.CLICKED){
                       if(backToMenu.isCollision(e.getX(), e.getY())){
                            scenecontroller.changeScene(new MenuScene(scenecontroller));
                       }
                }
            }
        };
    }

    @Override
    public void sceneBegin() {
    }

    @Override
    public void sceneUpdate() {

    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
        backToMenu.paint(g);
    }
    
}
