/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication34mygame;

import gameObject.Card;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.Hero.Hero;
import gameObject.WeakEffect;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import scene.SecondScene;
import utils.CommandList;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class JavaApplication34MyGame {

    /**
     * @param args the command line arguments
     */
    private static CommandList commands;
    private static CommandList currentcommands;
    private static CommandSolver commandsolver;
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jj = new JFrame();
        jj.setTitle("爐你所願");
        jj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jj.setSize(1920, 1080);
        GameJPanel jp = new GameJPanel();
        commandsolver = new CommandSolver.Builder(Global.MILLISEC_PER_FRAME * 2).enableMouseTrack(jp).gen();
        jj.add(jp);
        commandsolver.start();
        jj.setVisible(true);
        
        
        long startTime = System.currentTimeMillis();
        long lastTime = System.currentTimeMillis();
        long passedFrames = 0;
        
        while(true){
            
            long currentTime = System.currentTimeMillis();
            long totalTime = currentTime - startTime;
            long targetTotalFrames = totalTime / Global.MILLISEC_PER_FRAME;
           
                while(passedFrames < targetTotalFrames){
                    jp.update(commandsolver.update());
                    passedFrames++;
                }
                if((currentTime - lastTime) >= Global.MILLISEC_PER_FRAME){
                    lastTime = currentTime;
                    jj.repaint();
                }
        }     
    }
    
    
    
    
}
