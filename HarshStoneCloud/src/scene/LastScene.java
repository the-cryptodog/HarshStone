/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;

import Controller.SceneController;
import gameObject.Hero.Hero;
import java.awt.Graphics;
import utils.Global;

/**
 *
 * @author frank61003
 */
public class LastScene extends Scene{
    private Hero hero;
    private Hero npc;
    public LastScene(SceneController scenecontroller) {
        super(scenecontroller);
        hero = Global.hero;
    }

    @Override
    public void sceneBegin() {
    }

    @Override
    public void sceneUpdate() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
    }
    
}
