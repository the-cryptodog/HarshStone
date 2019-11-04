/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Card;

import gameObject.Card.Card;
import gameObject.Hero.Hero;
import gameObject.Monster.Monster;

/**
 *
 * @author frank61003
 */
public abstract class CardEffect extends Card{
    
    protected Card card;
    public CardEffect(Card card) {
        super(card.getX(), card.getY(), card.getWidth(), card.getHeight(), card.getName(), card.getSerialNumber(), card.cost);
        this.card = card;  
    }
    
    public void action(Hero hero, Monster monster){
    
    }
    
    
}
