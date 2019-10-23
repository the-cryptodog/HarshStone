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
public class WeakEffect extends CardEffect{
    private Card card;
    public WeakEffect(Card card) {
        super(card);
        this.card = card;
    }
    @Override
    public String toString() {
        return card.toString()+ "，造成虛弱";
    }
}
