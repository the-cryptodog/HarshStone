/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Monster;

import gameObject.Hero.Hero;

/**
 *
 * @author frank61003
 */
public class MonsterAbnormalState {
    private int continueturn;
    
    public MonsterAbnormalState(int continueturn){
        this.continueturn = continueturn;
    }
    
    public int getContinueTurn(){
        return continueturn;
    
    }
    
    
    
    
    public void action(Monster monster, Hero hero){
    
    
    
    }
    
    
    
    public class Frozen extends MonsterAbnormalState{

        
        public Frozen(int continueturn){
            super(continueturn);
        
        }
        
        
        
        
        @Override
        public void action(Monster monster, Hero hero) {
            
        }
    
    
    }
    
    
    
    
    
    
    
}
