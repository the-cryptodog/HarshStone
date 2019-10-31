/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Card.CardEffect;
import gameObject.Card.Card;
import gameObject.Hero.Hero;
import gameObject.Monster.Frozen;
import gameObject.Monster.Monster;
import gameObject.Monster.MonsterAbnormalState;
import gameObject.Monster.Weak;

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
        if(monster.getWeak() == 0){
            monster.getMonsterAbnormalStates().add(new Weak(0,0,32,32,"",weakstate));
            monster.setWeak(weakstate);
        }
        else{
            int temp = monster.getMonsterAbnormalStates().size();
            MonsterAbnormalState temp1;
            for(int i = 0; i < temp; i++){
                if(monster.getMonsterAbnormalStates().get(i) instanceof Weak){
                    temp1 = monster.getMonsterAbnormalStates().get(i);
                    int temp2 = temp1.getContinueTurn()+ weakstate;
                    if(temp2 > 9){
                        temp2 = 9;
                    }
                    temp1.setContinueTurn(temp2);
                    monster.setPoison(temp2);
                    break;
                }
            }
        }
        System.out.println( monster.name + "獲得虛弱" );
    }
}
