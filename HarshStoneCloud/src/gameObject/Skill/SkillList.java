/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Skill;

import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author User
 */
public class SkillList {

    private ArrayList<Skill> monsterSkillList;
    private ArrayList<Skill> cardSkillList;
    private Skill skilltoUse;
    

    public SkillList() {
        monsterSkillList = new ArrayList<>();
        cardSkillList = new ArrayList<>();
    }

    public void addMonsterSkill(Skill skill) { 
// 儲存三個技能(每一隻怪各一個=種技能)的陣列(只打英雄)
//技能由Main工廠讀取並創造，再由怪物拿取
        skill.setX(Global.HEROX - Global.SKILLDELTA);
        skill.setY(Global.HEROY - Global.SKILLDELTA);
        monsterSkillList.add(skill);
    }
    
    public void addCardSkill(Skill skill) { 
        // 卡片的技能光影集合,共三有十種技能光影 ;
        skill.setX(Global.MONSTERX);
        cardSkillList.add(skill);
    }


    public boolean skillCheck(int skillIndex) {//傳入卡片自帶的技能編號
        for (int i = 0; i < cardSkillList.size(); i++) {
            if (cardSkillList.get(i).getSkillIndex()== skillIndex) {
                return true;
            }
        }
        return false;
    }
    
    public Skill getCardSkill(int skillIndex) {//傳入卡片自帶的技能編號
        for (int i = 0; i < cardSkillList.size(); i++) {
            if (cardSkillList.get(i).getSkillIndex()== skillIndex) {
               return cardSkillList.get(i) ;
            }
        }
        return null;
    }
        
  

    public ArrayList<Skill>  getMonsterSkillList() {
        return monsterSkillList;
    }
    
    public ArrayList<Skill>  getCardSkillList() {
        return cardSkillList;
    }
    
    public Skill monsterAttack(int index) {
        return monsterSkillList.get(index);
    }


}
