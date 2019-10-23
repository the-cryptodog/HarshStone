/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import Controller.ImageResourceController;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author frank61003
 */
//為何不能靜態
public class CardFactory {
    private class CardData{
        protected String description;
        protected int cost;
        protected BufferedImage image;
        protected String path;
        protected int x;
        protected int y;
        protected int height;
        protected int width;
        protected String name;
    }
    
    private BufferedReader br;
    private ArrayList<CardData> carddata;
    
    
    public CardFactory(){
       
    
    
    
    }
    
    
    public ArrayList<String> readCardData(){
        ArrayList<String> str = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("CardData.txt"));
            
            while(br.ready()){
                str.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
//            Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }
    
    public ArrayList<CardData> spiltCardData(ArrayList<String> str){
        ArrayList<CardData> carddata = new ArrayList<CardData>();
        for(int i = 0; i < str.size();i++){
            
        
        
        }
	System.out.print("456");

        return carddata;
    }
    
    
    
    
    
    
}
