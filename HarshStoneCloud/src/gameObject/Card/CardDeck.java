/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import Controller.ImageResourceController;
import Controller.PathBuilder;
import gameObject.Card.Card;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class CardDeck {
    protected ArrayList<Card> cards;
    protected CardFactory cardfactory;
    protected BufferedImage image;
    
    
    
    public CardDeck(ArrayList<Card> cards){
        this.cards = cards;
        cardfactory = new CardFactory();
        image = ImageResourceController.getInstance().tryGetImage(PathBuilder.getMonster(ImagePath.MONSTER1));
    }
    
    public CardDeck(){
        
        cards = new ArrayList<Card>();
        cardfactory = new CardFactory();
    }
    
    
    
    
    
    public static class WarriorDeck extends CardDeck{
        public WarriorDeck(){
            super();
            for(int i = 0; i < 5;i++){
                cards.add(cardfactory.genCard(0));            
            }
            for(int i = 0; i < 4;i++){
                cards.add(cardfactory.genCard(2));            
            } 
            cards.add(cardfactory.genCard(4));
        }  
    }
    
    
    
    
    public void shuffle(){
        for(int i = 0; i < cards.size(); i++){
            int r = (int)(Math.random()*cards.size() - i) + i;
            swap(i,r);
        } 
    }
    
    
    public void swap(int i1, int i2){
        Card temp = cards.get(i1);
        cards.set(i1, cards.get(i2));
        cards.set(i2, temp);
    }
    
    public ArrayList<Card> getCards(){
        return cards;
    }
    
    //應該要有move
}
