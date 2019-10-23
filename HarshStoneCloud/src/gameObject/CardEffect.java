/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Hero.Hero;
import gameObject.Monster.Monster;

/**
 *
 * @author frank61003
 */
public abstract class CardEffect extends Card{
    
    protected Card card;
    public CardEffect(Card card) {
        super(card.x, card.y, card.width, card.height, card.name, card.cost);
        this.card = card;  
    }
    
    public void action(Hero hero, Monster monster){
        
    
    
    }
    
    
}
