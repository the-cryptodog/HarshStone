/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Card.CardEffect;
import gameObject.Card.Card;
import gameObject.Hero.Hero;
import gameObject.Monster.Monster;

/**
 *
 * @author frank61003
 */
public class DefenseEffect extends CardEffect{
    private Card card;
    protected int defense; 
    
    public DefenseEffect(Card card, int defense) {
        super(card);
        this.card = super.card;
        this.defense = defense;
    }
    
    @Override
    public String toString() {
        return card.toString()+ "，防禦";
    }
    
    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        hero.setDefense(hero.getDefense() + defense);
        System.out.println("1.獲得防禦力" + defense);
        System.out.println("2.英雄防禦力" + hero.getDefense());
        System.out.println("2.英雄姓名" + hero.getName());
    }
}
