/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.ImageResourceController;
import Controller.SceneController;
import gameObject.Button.Button;
import gameObject.Card.CardDeck;
import gameObject.Card.CardDeck.WarriorDeck;
import gameObject.Hero.Hero;
import gameObject.MapIcon.MapPath;
import gameObject.MapIcon.redCross;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;


/**
 *
 * @author User
 */
public class MapScene extends Scene {

    private final int[] xEdge = {0, 495, 695, 982, 1285};
    private boolean stagePassed;
    private BufferedImage map;
    private BufferedImage cover;
    private BufferedImage pointer;
    private redCross[] redCross;
    private ArrayList<redCross> redCrossList;
    private int currentStage;
    private int[][] redCrossSetter = {{435, 505},
    /*{x座標/y座標} */ {645, 335}, {645, 610},
    {936, 310}, {936, 500}, {931, 658},
    {1235, 327}, {1225, 596},
    {1436, 532}};
    private int[][] availableRoute = {{4, 5}, {2, 5, 8, 9}, {3, 6, 8, 9}};
    private int currentRedCross;
    private int coverx;
    private Hero hero;
    private Scene incidence;
    private Button save;
//    private int y;
//    private int speed;

    public MapScene(SceneController scenecontroller) {
        super(scenecontroller);
//        y=400;
//        y=speed=10;
        stagePassed = false;
        coverx = 495;
        System.out.println("stage=" + currentStage);
        redCrossList = new ArrayList<redCross>();
//        redCrossList.add(new redCross(455,505,50,50,"ddddd"));
        for (int i = 0; i < redCrossSetter.length; i++) {
            redCrossList.add(new redCross(redCrossSetter[i][0], redCrossSetter[i][1], 50, 50, "REDCROSS"));
            redCrossList.get(i).getTagList().add(i);
        }
        currentRedCross = -1;
        redCrossList.get(0).getTagList().add(1);
        redCrossList.get(0).getTagList().add(2);

        redCrossList.get(1).getTagList().add(3);
        redCrossList.get(1).getTagList().add(4);
        redCrossList.get(2).getTagList().add(5);
        redCrossList.get(3).getTagList().add(6);
        redCrossList.get(4).getTagList().add(7);
        redCrossList.get(5).getTagList().add(7);
        redCrossList.get(6).getTagList().add(8);
        redCrossList.get(7).getTagList().add(8);
        save = new Button(50, 230, 220, 50, "CONTINUE");
        System.out.println(redCrossList.get(0).toString());
        System.out.print(redCrossList.get(0) instanceof redCross);
        System.out.print("Global.CURRENTSTAGE = " + Global.CURRENTSTAGE);

        map = irc.tryGetImage("/resources/Map/map.png");
        cover = irc.tryGetImage("/resources/Map/mapOrigin.png");
        pointer = irc.tryGetImage("/resources/Map/POINTER.png");

        mousecommandlistener = new CommandSolver.MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                if (state == CommandSolver.MouseState.RELEASED) {
                    System.out.println("release");
//                    startPressed = false;

                }
//&& redCrossList.get(currentRedCross).checkTag(i)
                if (state == CommandSolver.MouseState.CLICKED) {
                    System.out.println("CLick");
                    System.out.println(redCrossList.get(0).checkTag(0));
                    if (redCrossList.get(0).isCollision(e.getX(), e.getY())&&Global.CURRENTSTAGE<2) {                    
                        currentRedCross = redCrossList.get(0).getTagList().get(0);
                        redCrossList.get(0).setIsClicked(true);
                        stagePassed = false;
                        scenecontroller.changeScene(new MainScene(scenecontroller, getThis()));
                    }
                    for (int i = 0; i < redCrossList.size(); i++) {
                        if (redCrossList.get(i).isCollision(e.getX(), e.getY())
                                && redCrossList.get(currentRedCross).checkTag(i)) {
                            currentRedCross = redCrossList.get(i).getTagList().get(0);
                            
                            scenecontroller.changeScene(new MainScene(scenecontroller, getThis()));
//                            stagePassed = true;
                            redCrossList.get(i).setIsClicked(true);       
                        }
                    }
                    
                    if(save.isCollision(e.getX(), e.getY())){
//                         FileOutputStream fos;
//                        try{
//                            fos = new FileOutputStream("Hero.ser");
//                            ObjectOutputStream oos = new ObjectOutputStream(fos);
//                            oos.writeObject(hero);
//                            fos.close();
//                            oos.close();
//                        }catch(FileNotFoundException ex){
//                            System.out.println("1");
//                            ex.printStackTrace();
//                        }catch(IOException ex){
//                            System.out.println("2");
//                            ex.printStackTrace();
//                        }
                        
                        
//                        WarriorDeck ss = new WarriorDeck(0,0,0,0,"3");
//                        ss.createCardRecord();
                        Global.hero.getHeroDeck().createCardRecord();
                        Global.hero.saveHeroRecord();
                        saveRedCrossList();
                        saveCurrentRedCross();
//                        CardDeck carddeck = hero.getHeroDeck();
//                        carddeck.createCardRecord();
                    }
                    


                }
//                    if (socerer .isCollision(e.getX(), e.getY())) {
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
//                      startPressed = true;
//                        scenecontroller.changeScene(new MainScene(scenecontroller));
//                    }
//                }

                if (state == CommandSolver.MouseState.PRESSED) {
//                    if (socerer .isCollision(e.getX(), e.getY())) {

//                    }
                }

                if (state == CommandSolver.MouseState.DRAGGED) {

                }
            }
        };
    }

    public MapScene getThis() {
        return this;
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }
    
//    public Hero getHero(){
//        return hero;
//    }
    
    
    public void saveRedCrossList(){
        FileOutputStream fos;
        try{
            fos = new FileOutputStream("RedCrossList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(redCrossList);
            fos.close();
            oos.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public void loadRedCrossList(){
       
    
        FileInputStream fis;
        try{
            fis = new FileInputStream("RedCrossList.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            redCrossList = (ArrayList<redCross>) ois.readObject();
            int temp = redCrossList.size();
            for(int i = 0; i < temp; i++){
                redCrossList.get(i).setImageResourceController();
                redCrossList.get(i).setRedCross();
            }
            fis.close();
            ois.close();
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void saveCurrentRedCross(){
         try{
                BufferedWriter bw = new BufferedWriter(new FileWriter("CurrentRedCross.txt"));
                bw.write("" + currentRedCross);
                bw.flush();
                bw.close();
        }   
        catch(IOException ex){
            ex.printStackTrace();
        }
       
    }
    
    public void loadCurrentRedCross(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("CurrentRedCross.txt"));
            String str = "";
            while(br.ready()){
                str += br.readLine();
            }
            currentRedCross = Integer.valueOf(str);
            br.close();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    
    
    
    
    
    
    
    
    @Override
    public void sceneBegin() {
//        if(Global.CURRENTSTAGE>0)
        stagePassed = true;
//顯示已經點過的叉叉
    }

    @Override
    public void sceneUpdate() {

    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(map, 0, 0, 1932, 1078, null);
//        save.paint(g);
        for (int i = 0; i < redCrossList.size(); i++) {
//                     System.out.print(i);
            if (stagePassed) {
                 redCrossList.get(i).paint(g);
            }
        }
//        if (y > 500 || y < 400) {
//                speed = (-1) * speed;
//            }
//        if (Global.CURRENTSTAGE == 1) {                    
//            y += speed;
//            g.drawImage(pointer, 435, y, 50, 50, null);
//       
//        }
//        redCrossList.get(0).paint(g);
        switch (Global.CURRENTSTAGE) {
            case 1:
                g.drawImage(cover, 495, 0, 1932, 1078, 495, 0, 1932, 1078, null);
                break;
            case 2:
                if (coverx <= 695) {
                    coverx += 2;
                }
                g.drawImage(cover, coverx, 0, 1932, 1078, coverx, 0, 1932, 1078, null);
                break;
            case 3:
                if (coverx <= 982) {
                    coverx += 2;
                }
                g.drawImage(cover, coverx, 0, 1932, 1078, coverx, 0, 1932, 1078, null);
                break;
            //原邊界為1285
            case 4:
                if (coverx <= 1287) {
                    coverx += 2;
                }
                g.drawImage(cover, coverx, 0, 1932, 1078, coverx, 0, 1932, 1078, null);
                break;
            case 5:
                if (coverx <= 1685) {
                    coverx += 2;
                }
                g.drawImage(cover, coverx, 0, 1932, 1078, coverx, 0, 1932, 1078, null);
                break;
        }
    }
}
