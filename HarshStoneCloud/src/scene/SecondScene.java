/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import io.CommandSolver;
import javax.imageio.ImageIO;

/**
 *
 * @author frank61003
 */
public class SecondScene extends Scene{
    
    public SecondScene(SceneController scenecontroller){
        super(scenecontroller);
        mousecommandlistener = new CommandSolver.MouseCommandListener(){

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == CommandSolver.MouseState.CLICKED){
                    System.out.println("clicked5");
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
        try{
            g.drawImage(ImageIO.read(getClass().getResource("/resources/Background/背景2.jpg")), 0, 0, 1280, 1080, null);
        }catch(IOException e){
        
        }
    }
    
}
