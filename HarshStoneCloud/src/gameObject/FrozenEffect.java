/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import gameObject.Card.Card;
import gameObject.Card.CardEffect;
import gameObject.Hero.Hero;
import gameObject.Monster.Frozen;
import gameObject.Monster.Monster;
import gameObject.Monster.MonsterAbnormalState;
import gameObject.Monster.Poison;

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
        if(monster.getFrozen() == 0){
            monster.getMonsterAbnormalStates().add(new Frozen(0,0,32,32,"",frozenstate));
            monster.setFrozen(frozenstate);
        }
        else{
            int temp = monster.getMonsterAbnormalStates().size();
            MonsterAbnormalState temp1;
            for(int i = 0; i < temp; i++){
                if(monster.getMonsterAbnormalStates().get(i) instanceof Frozen){
                    temp1 = monster.getMonsterAbnormalStates().get(i);
                    int temp2 = temp1.getContinueTurn() + frozenstate;
                    if(temp2 > 9){
                        temp2 = 9;
                    }
                    temp1.setContinueTurn(temp2);
                    monster.setPoison(temp2);
                    break;
                }
            
            }
        }
    }
}
    

