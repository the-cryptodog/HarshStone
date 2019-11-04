/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.AudioClipResourceController;
import Controller.AudioResourceController;
import Controller.ImageResourceController;
import Controller.SceneController;
import java.awt.Graphics;
import io.CommandSolver;
import java.awt.event.MouseAdapter;



/**
 *
 * @author frank61003
 */

public abstract class Scene{
    

    protected CommandSolver.MouseCommandListener mousecommandlistener;
    protected SceneController scenecontroller;
    protected ImageResourceController irc;
    protected AudioResourceController arc;
    protected AudioClipResourceController acrc;
    public abstract void sceneBegin();
    public abstract void sceneUpdate();
    public abstract void sceneEnd();
    public abstract void paint(Graphics g);
    public CommandSolver.MouseCommandListener getMouseCommandListener(){
            return mousecommandlistener;   
    }

    
    public Scene(SceneController scenecontroller){
     
        this.scenecontroller = scenecontroller;
        irc = ImageResourceController.getInstance();
        arc = AudioResourceController.getInstance();
        acrc = AudioClipResourceController.getInstance();
        
    }
    
    
    
//    public Scene(SceneController scenecontroller){
//        this.scenecontroller = scenecontroller;
//    }
    
    
    


}
