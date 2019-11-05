/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.Skill;

import gameObject.Card.Card;
import gameObject.DamageEffect;
import gameObject.DefenceEffect;
import gameObject.WeakEffect;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author User
 */
public class SkillFactory {

    private class SkillData {

        protected String description;
        protected int cost;
        protected BufferedImage image;
        protected String path;
        protected String name;
        protected int serialnumber;

    }

    private BufferedReader br;
    private ArrayList<String> skilldata;

    public SkillFactory() {
        this.skilldata = readSkillData(); //讀取技能檔案儲存在工廠中
    }

    public ArrayList<String> readSkillData() {

        ArrayList<String> str = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("SkillData.txt"));

            while (br.ready()) {
                str.add(br.readLine());
            }
            br.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
//            Logger.getLogger(JavaApplication40Filemanage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

//    public ArrayList<CardData> spiltCardData(ArrayList<String> str){
//        ArrayList<CardData> carddata = new ArrayList<CardData>();
//        for(int i = 0; i < str.size();i++){
//            String[] temp = str.get(i).split(",");
//            
//        
//        }
//        
//        
//        return carddata;
//    }
    //generate card
    public Skill genSkill(int serialnumber) {
        String[] temp = skilldata.get(serialnumber).split(",");
        Skill tmp = new Skill(Global.HEROX, Global.HEROY, Global.SKILLHEIGHT, Global.SKILLWIDTH,
        String.valueOf(temp[1]), Integer.valueOf(temp[2]));
        tmp.setIndex(Integer.valueOf(temp[0]));
        return tmp;

    }
}

