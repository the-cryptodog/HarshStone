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
public class DamageEffect extends CardEffect {

    private Card card;
    protected int damage;

    public DamageEffect(Card card, int damage) {
        super(card);
        this.card = super.card;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return card.toString() + "傷害" + damage;
    }

    @Override
    public void action(Hero hero, Monster monster) {
        card.action(hero, monster);
        System.out.println("最初血量" + monster.gethealth());
        int temp =  monster.getDefense() - damage;
       //怪物護甲還有剩,血量不用動
        if(temp>0){
            monster.setDefense(temp);
        }else{
            monster.setDefense(0);
            monster.sethealth(monster.gethealth() + temp);
        }
        
        System.out.println("傷害" + damage);
        System.out.println("剩餘血量" + monster.gethealth() + " " + monster.gethealth() / (float) 100);

    }

}
