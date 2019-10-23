/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author frank61003
 */
public class ImageResourceController {
    private static class KeyPair{
        private String path;
        private BufferedImage image;
    
        public KeyPair(String path, BufferedImage image){
            this.path = path;
            this.image = image;
        }
    }
    
    
    private static ImageResourceController irc;
    private ArrayList<KeyPair> imgPairs;
    //
    private ImageResourceController(){
        imgPairs = new ArrayList<>();
    }
    
    //單例模式
    public static ImageResourceController getInstance(){
        if(irc == null){
            irc = new ImageResourceController();
        }
        return irc;
    }
    //尋找鍵值對        
    private KeyPair findKeyPair(String path){
        for(int i = 0;i<imgPairs.size();i++){
            KeyPair pair = imgPairs.get(i);
        
            if(pair.path.equals(path)){
                return pair;
            }
        }
        return null;
    }
    //回傳尋找的圖
    public BufferedImage tryGetImage(String path){
        KeyPair pair = findKeyPair(path);
        if(pair == null){
            return addImage(path);
        }
        return pair.image;
    }
    
    
    //比對圖
    private BufferedImage addImage(String path){
        try{
            BufferedImage img = ImageIO.read(getClass().getResource(path));
            imgPairs.add(new KeyPair(path,img));
            return img;
        } catch (IOException e){
        
        }
        return tryGetImage(path);
    
    }
    
    
    
}
