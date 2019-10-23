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
public class DefenceEffect extends CardEffect{
    private Card card;
    protected int defence; 
    
    public DefenceEffect(Card card, int defence) {
        super(card);
        this.card = super.card;
        this.defence = defence;
    }
    
    @Override
    public String toString() {
        return card.toString()+ "，防禦";
    }
    
    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        System.out.println("獲得防禦力" + defence);
    }
}
