/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Hero;

/**
 *
 * @author User
 */
public interface HeroState {

    public void action(Hero hero);

    public class jobSelected implements HeroState {

        @Override
        public void action(Hero hero) {
            hero.setX(hero.getX() + 20);
        }
    }

    public class action implements HeroState {

        @Override
        public void action(Hero hero) {
            
        }
    }
    
}
