/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.FrozenEffect;
import gameObject.GameObject;
import gameObject.HealEffect;
import gameObject.PoisonEffect;
import gameObject.WeakEffect;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class CardDeckRecord {
   private class CardData{   
        protected int serialnumber;
    
    }
    
    private BufferedReader br;
    private ArrayList<String> carddeckdata;
    private ArrayList<Card> cards;
    private CardDeck carddeck;
    
    public CardDeckRecord(){
       carddeckdata = readCardDeckData();
       cards = new ArrayList<Card>();
       cards = genCardDeck();
       carddeck = new CardDeck(40,700,Global.CARDDECKWIDTH,Global.CARDDECKHEIGHT,"抽牌推", cards);
    }
    
    
    public ArrayList<String> readRareCardDeckData(){
        ArrayList<String> str = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("RareCardDeckData.txt"));
            
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
    public ArrayList<String> readCardDeckData(){
        ArrayList<String> str = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("CardDeckData.txt"));
            
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

    public ArrayList<Card> getCards(){
        return cards;
    }
    
    public CardDeck getCardDeck(){
        return carddeck;
    }
    
    
    
    
    
    //generate card
    public ArrayList<Card> genCardDeck(){
        
        CardFactory cardfactory = new CardFactory();
        int temp1 = carddeckdata.size();
        String[] temp2 = new String[temp1];
        for (int i = 0; i < temp1; i++){
           temp2[i] = carddeckdata.get(i);
           cards.add(cardfactory.genCard(Integer.valueOf(temp2[i]),false));            
        }
        return cards;
    }
    
    
    
    
}
