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
public class WeakEffect extends CardEffect{
    private Card card;
    protected int weakstate;
    
    public WeakEffect(Card card,int weakstate) {
        super(card);
        this.card = card;
        this.weakstate = weakstate;
    }
    @Override
    public String toString() {
        return card.toString()+ "，造成虛弱";
    }

    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        monster.setAttack((int)(monster.getAttack()*0.75));
        System.out.println( monster.name + "獲得虛弱" );
    }
}
