/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Card.Card;
import gameObject.Card.CardEffect;
import gameObject.Hero.Hero;
import gameObject.Monster.Monster;

/**
 *
 * @author User
 */
public class HealEffect extends CardEffect{
    
    private Card card;
    protected int heal; 
    public HealEffect(Card card, int heal) {
        super(card);
        this.card = super.card;
        this.heal = heal;
    }
    
    @Override
    public String toString() {
        return card.toString()+ "，治癒";
    }
    
    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        hero.sethealth(hero.gethealth()+ heal);
        if(hero.gethealth()>100){
        hero.sethealth(100);    
        }
        
        System.out.println("獲得治癒" + heal);
    }
}
