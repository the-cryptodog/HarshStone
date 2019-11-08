/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Skill;

import Controller.PathBuilder;
import gameObject.GameObject;
import java.awt.Graphics;
import javafx.scene.media.AudioClip;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class Skill extends GameObject {

    protected int skillIndex;
    protected int act;
    protected int totalAct;
//    protected int[] Act = {0,1,2,3,4,5};
    protected boolean skillend;
    protected DelayCounter delaycounter;
    private AudioClip effectSound;

    public Skill(int x, int y, int width, int height, String name, int act, String audio) {
        super(x, y, width, height, name);

        image = irc.tryGetImage(PathBuilder.getSkill("/" + name + ".png"));
        effectSound = acrc.tryGetAudioClip(PathBuilder.getAudio("/" + audio + ".mp3"));

        this.act = 0;
        totalAct = act;
        skillend = true;
        delaycounter = new DelayCounter(4, new DelayCounter.Action() {

            @Override
            public void action() {
                int act = 0;
                act = ++act % 4;
            }
        });
    }

    public void update() {
        if (delaycounter.delayupdate()) {
            act = ++act % totalAct;
        }

    }

//    public void setAct(int totalAct){
//        this.totalAct= totalAct;
//    }
    public boolean getSkillend() {
        if (skillend == true) {
            skillend = false;
            return true;
        }
        return skillend;
    }

    public void setSkillend(boolean skillend) {
        this.skillend = skillend;
    }

    public String getSkillName() {
        return name;
    }

    public void setSkillIndex(int skillIndex) {
        this.skillIndex = skillIndex;
    }

    public int getSkillIndex() {
        return this.skillIndex;
    }

    public AudioClip getEffectSound() {
        return this.effectSound;
    }

    public void positionSetter(GameObject gameobject) {
        this.x = gameobject.getX() - 15;
        this.y = gameobject.getY() - 15;
        this.width = gameobject.getWidth() + 15;
        this.height = gameobject.getHeight() + 15;

    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        int dx = act % 5;
        int dy = act / 5;
        update();
        if (skillend == false) {
            g.drawImage(image, x, y, x + width, y + height,
                    dx * Global.SKILLIMG_X_OFFSET, dy * Global.SKILLIMG_Y_OFFSET,
                    dx * Global.SKILLIMG_X_OFFSET + 192, dy * Global.SKILLIMG_Y_OFFSET + 192, null);
        }
        if (act == totalAct - 1) {
            skillend = true;
            act = 0;
        }
    }
}
