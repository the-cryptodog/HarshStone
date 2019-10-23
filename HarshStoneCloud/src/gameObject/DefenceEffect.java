/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

/**
 *
 * @author frank61003
 */
public class DefenceEffect extends CardEffect{
    private Card card;

    public DefenceEffect(Card card) {
        super(card);
        this.card = super.card;
    }
    
    @Override
    public String toString() {
        return card.toString()+ "，防禦";
    }
}
