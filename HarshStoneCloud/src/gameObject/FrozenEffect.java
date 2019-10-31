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
 * @author frank61003
 */
public class FrozenEffect extends CardEffect{
    private Card card;
    protected int frozenstate;
    
    public FrozenEffect(Card card,int frozenstate) {
        super(card);
        this.card = card;
        this.frozenstate = frozenstate;
    }
    @Override
    public String toString() {
        return card.toString()+ "，造成虛弱";
    }

    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        System.out.println( monster.name + "獲得冰凍" );
    }
}
    

