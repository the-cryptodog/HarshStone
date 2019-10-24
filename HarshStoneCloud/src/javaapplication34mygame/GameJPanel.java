/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication34mygame;

import Controller.ImageResourceController;
import Controller.SceneController;
import gameObject.Card.Card;
import gameObject.DamageEffect;
import gameObject.Hero.Hero;
import io.CommandSolver.CommandWrapper;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import scene.MainScene;
import scene.MenuScene;
import scene.SecondScene;


/**
 *
 * @author frank61003
 */
public class GameJPanel extends JPanel{
    private ImageResourceController irc;
    
    private int xdelta;
    private int ydelta;
    private boolean cardclicked;
    private SceneController scenecontroller;
    private MainScene mainscene;
    private MenuScene menuscene;
    
    public class MyListener extends MouseAdapter{
        
        @Override
        public void mousePressed(MouseEvent e){
            for(int i = 0; i < mainscene.getDeck().size();i++){
                if(mainscene.getDeck().get(i).isCollision(e.getX(),e.getY())){
                    for(int j = 0; j < mainscene.getDeck().size();j++){
                        if(mainscene.getDeck().get(i).getClicked() == false){
                            xdelta = mainscene.getDeck().get(i).getDeltaX(e.getX());
                            ydelta = mainscene.getDeck().get(i).getDeltaY(e.getY());
                            mainscene.getDeck().get(i).setClicked(true);
                        }
                    }
                }
            }
            
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            if(mainscene.getNext().isCollision(e.getX(), e.getY())){
                scenecontroller.changeScene(new SecondScene(scenecontroller));
            }
            for(int i = 0; i < mainscene.getDeck().size();i++){
                if(mainscene.getDeck().get(i).isCollision(e.getX(),e.getY())){
            
                   
                }
            }
            
            
            
        }
        
        @Override
        public void mouseDragged(MouseEvent e){
            for(int i = 0; i < mainscene.getDeck().size();i++){
                if(mainscene.getDeck().get(i).isCollision(e.getX(),e.getY()) && mainscene.getDeck().get(i).getClicked()){
                    mainscene.getDeck().get(i).setX(e.getX() - xdelta);
                    mainscene.getDeck().get(i).setY(e.getY() - ydelta);
                }
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e){
            for(int i = 0; i < mainscene.getDeck().size();i++){
                 mainscene.getDeck().get(i).setClicked(false);
                }
        }     
    }
          
    
    
    public GameJPanel(){
        xdelta = 0;
        ydelta =0;
        cardclicked = false;
        scenecontroller = new SceneController();
        menuscene = new MenuScene(scenecontroller);
        scenecontroller.changeScene(menuscene);
//        this.addMouseListener(new MyListener());
//        this.addMouseMotionListener(new MyListener());
        
        
        
    
    }
    
//    public void update(){
//        Card 全力迎戰 = new Card(20,40,375,518,"全力迎戰");
//        全力迎戰.irc.tryGetImage("/resources/Image/全力迎戰.png");
//        deck.add(全力迎戰);
//    }
    
    
//    public void changeX(int x){
//        if(this.x > x){
//            xdir = 1;
//        }
//        else{
//            xdir = 0;
//        }
//    }
//    
//    public void changeY(int y){
//        if(this.y > y){
//            ydir = 1;
//        }
//        else{
//            ydir = 0;
//        }
//    }
    
    
    
    public void update(CommandWrapper commands){
        scenecontroller.sceneUpdate(commands);   
    }

    
    
    
    
    @Override
    public void paint(Graphics g){
//        card1.paint(g);
//        card2.paint(g);
//        try{
//            g.drawImage(ImageIO.read(getClass().getResource("/resources/Background/背景1.jpg")), 0, 0, 1280, 1080, null);
//        }catch(IOException e){
//        
//        }
//        for(int i = 0; i < deck.size(); i++){
//            deck.get(i).paint(g);
//        }
        scenecontroller.paint(g);
//        
        
    }   
}
