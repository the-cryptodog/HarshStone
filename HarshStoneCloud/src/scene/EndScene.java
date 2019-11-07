/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;


import Controller.PathBuilder;
import Controller.SceneController;
import gameObject.Button.Button;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;

/**
 *
 * @author User
 */
public class EndScene extends Scene{

    private BufferedImage img;
    private Button backToMenu;
    private SelectJobSceneState menuscenestate;
    private boolean startPressed;
    private AudioClip sound;


    public EndScene(SceneController scenecontroller){
          super(scenecontroller);
          img = irc.tryGetImage("/resources/Background/ENDSCENE.png");
          backToMenu = new Button(900, 860, 210, 52, "BACKTOMENU");
          sound = acrc.tryGetAudioClip(PathBuilder.getAudio("/Delusion.mp3"));
          sound.play();
          
          mousecommandlistener = new CommandSolver.MouseCommandListener(){
    
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if(state == CommandSolver.MouseState.CLICKED){
                       if(backToMenu.isCollision(e.getX(), e.getY())){
                           sound.stop();
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
