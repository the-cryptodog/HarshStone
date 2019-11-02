/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.AudioResourceController;
import Controller.PathBuilder;
import Controller.SceneController;
import PopOutWindow.Incidence;
import gameObject.Button.Button;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Arrays.stream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import static sun.audio.AudioPlayer.player;
import utils.Global;
import values.ImagePath;


/**
 *
 * @author User
 */
public class MenuScene extends Scene {

    private BufferedImage img;
    private ArrayList<Button> buttons;
    private SelectJobSceneState menuscenestate;
    private boolean startPressed;
    private Incidence incidence;
    private File background;

    private Clip clip;
    



    //開始遊戲(進入選角畫面)
    //結束遊戲(關閉視窗)
    public MenuScene(SceneController scenecontroller) {
        super(scenecontroller);
//        try {
//            
//            
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
//            AudioFormat audioFormat = audioInputStream.getFormat();
//            int bufferSize = (int) Math.min(audioInputStream.getFrameLength() * audioFormat.getFrameSize(), Integer.MAX_VALUE); //緩衝大小，如果音訊檔案不大，可以全部存入緩衝空間。這個數值應該要按照用途來決定
//            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat, bufferSize);
//            Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);
//            clip.open(audioInputStream);
//
//            clip.start();
//        } catch (LineUnavailableException ex) {
//            
//        }
//        clip = arc.tryGetAudio("/src/resources/Audio/This.wav");
//        PathBuilder.getAudio(ImagePath.VILIFIED)
        
        try{
            URL url;
            url = getClass().getResource("/resources/Audio/This.wav");
            System.out.println(url);
            System.out.println(url.getPath());
            File file = new File(url.getPath());
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
//            AudioResourceController.AudioKeyPair key = new AudioResourceController.AudioKeyPair("/resources/Audio/test.wav", clip);
//            audioList.add(key);
            clip.open(stream);
            
            clip.start();
            
   
        }catch(MalformedURLException ex){
            System.out.println("1");
        }catch(IOException ex){
            System.out.println("2");
        }catch(UnsupportedAudioFileException ex){
            System.out.println("3");
        }catch(LineUnavailableException ex){
            System.out.println("4");
        }
        
        
        
        
        
        
        
        
        
        
        
        
        startPressed = false;
        Global.CURRENTSTAGE = 1;
//        start = new StartButton(690, 300, 200, 100, "開始遊戲");
        buttons = new ArrayList<>();
        buttons.add(new Button(50, 50, 220, 50, "NEWGAME"));
        buttons.add(new Button(50, 110, 220, 50, "CONTINUE"));
        buttons.add(new Button(50, 170, 85, 50, "EXIT"));
        img = irc.tryGetImage("/resources/Background/MENU.png");

        
        
        mousecommandlistener = new CommandSolver.MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }

                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    if (buttons.get(0).isCollision(e.getX(), e.getY())) {
                        scenecontroller.changeScene(new SelectJobScene(scenecontroller));
                    }
                    if (buttons.get(1).isCollision(e.getX(), e.getY())) {
                               incidence = new Incidence(384, 216, 1152, 648, "AWARD");
                               incidence.setCommandListener(mousecommandlistener);
                            if (incidence.getButton().isCollision(e.getX(), e.getY())) {
                               incidence = null;
                    }
                    }
//上面兩行為測試用，按CONTINUE可以直接進入戰鬥

                    
                    
                }

                if (state == CommandSolver.MouseState.PRESSED) {
                    if (buttons.get(0).isCollision(e.getX(), e.getY())) {
                        startPressed = true;
                        System.out.println(startPressed);
                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {
                    System.out.print(" drag!!!!");
                }
                if (state == CommandSolver.MouseState.ENTERED) {

                }
                if (state == CommandSolver.MouseState.EXITED) {

                    }
                
                if (state == CommandSolver.MouseState.MOVED) {
                    for (int i = 0; i < buttons.size(); i++) {
                        if (buttons.get(i).isCollision(e.getX(), e.getY())) {
//                            System.out.println("hover!!!!!!!");
//                            buttons.get(i).hover(e.getX(), e.getY());
                        }
                    }
                }
            }
        };
    }

    @Override

    public void sceneBegin() {
        
        
        
        
//        clip.start();
    }

    @Override
    public void sceneUpdate() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(img, 0, 0, 1920, 1050, null);
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paint(g);
        }
        if(incidence!=null){
            incidence.paint(g);
        }
    }
}
