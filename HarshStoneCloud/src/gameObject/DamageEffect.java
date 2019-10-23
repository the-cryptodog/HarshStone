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
        System.out.println("最初血量" + monster.gethealth());
        monster.sethealth(monster.gethealth() - damage);
        System.out.println("傷害" + damage);
        System.out.println("剩餘血量" + monster.gethealth() + " " + monster.gethealth() / (float) 100);

    }

}
