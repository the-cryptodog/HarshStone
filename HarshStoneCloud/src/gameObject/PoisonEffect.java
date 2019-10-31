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
import gameObject.Monster.MonsterAbnormalState;
import gameObject.Monster.Poison;
import java.util.ArrayList;

/**
 *
 * @author frank61003
 */
public class PoisonEffect extends CardEffect{
    private Card card;
    protected int poisonstate;
    
    public PoisonEffect(Card card,int poisonstate) {
        super(card);
        this.card = card;
        this.poisonstate = poisonstate;
        
    }
    @Override
    public String toString() {
        return card.toString()+ "，造成中毒";
    }

    @Override
    public void action(Hero hero, Monster monster) {

        
        monster.getMonsterAbnormalStates().add(new Poison(0,0,0,0,"",poisonstate));
        System.out.println( monster.name + "獲得中毒" );
    }

}