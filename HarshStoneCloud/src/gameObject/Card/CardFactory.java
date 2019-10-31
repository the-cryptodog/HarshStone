/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import gameObject.Card.Card;
import Controller.ImageResourceController;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.FrozenEffect;
import gameObject.PoisonEffect;
import gameObject.WeakEffect;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import utils.Global;

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
        protected String name;
        protected int serialnumber;
    
    
    }
    
    private BufferedReader br;
    private ArrayList<String> carddata;
    
    
    public CardFactory(){
       carddata = readCardData();
    
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
    

    

//    public ArrayList<CardData> spiltCardData(ArrayList<String> str){
//        ArrayList<CardData> carddata = new ArrayList<CardData>();
//        for(int i = 0; i < str.size();i++){
//            String[] temp = str.get(i).split(",");
//            
//        
//        }
//        
//        
//        return carddata;
//    }

    
    
    //generate card
    public Card genCard(int serialnumber){
        
        String[] temp = carddata.get(serialnumber).split(",");
        
        Card card = new Card(0,0,Global.CARDDECKWIDTH,Global.CARDHEIGHT,temp[1],Integer.valueOf(temp[3]));
        card.setSkill(Integer.valueOf(temp[2]));
        if(Integer.valueOf(temp[4]) != 0){
      
            card = new DamageEffect(card, Integer.valueOf(temp[4]));
        }
        if(Integer.valueOf(temp[5]) != 0){
            card = new DefenceEffect(card, Integer.valueOf(temp[5]));
        }
        if(Integer.valueOf(temp[6]) != 0){
            card = new WeakEffect(card, Integer.valueOf(temp[6]));
        }
        if(Integer.valueOf(temp[7]) != 0){
            card = new PoisonEffect(card, Integer.valueOf(temp[7]));
        }
        if(Integer.valueOf(temp[8]) != 0){
            card = new FrozenEffect(card, Integer.valueOf(temp[8]));
        }
        card.setAttack(Integer.valueOf(temp[4]));
        card.setDefense(Integer.valueOf(temp[5]));
        card.setWeak(Integer.valueOf(temp[6]));
        card.setPoision(Integer.valueOf(temp[7]));
        card.setFrozen(Integer.valueOf(temp[8]));
        return card;
    
    }
    
}
