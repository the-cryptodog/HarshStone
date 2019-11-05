/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject.MapIcon;

import Controller.PathBuilder;
import gameObject.Button.Button;
import gameObject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import values.ImagePath;

/**
 *
 * @author User
 */
public class redCross extends GameObject implements Serializable{
    private static final long serialVersionUID = -671958543348052007L;
    ArrayList<Integer> tagList;
    private boolean isclicked;
    protected transient BufferedImage redCross;

    public redCross(int x, int y, int width, int height, String name) {
        super(x, y, width, height, name);
        isclicked = false;
        tagList = new ArrayList<>();
        this.redCross = irc.getInstance().tryGetImage(PathBuilder.getMap("/" + name + ".png"));
    }

    public void setIsClicked(boolean isclicked) {
        this.isclicked = isclicked;
    }

    public boolean getIsClicked() {
        return isclicked;
    }
    public ArrayList<Integer> getTagList(){
        return this.tagList;
    }
    public boolean checkTag(int tag){
        for(int i = 1 ; i <tagList.size(); i++){
            if(tag == tagList.get(i)){
                return true;
            }
        }
        return false;
    }

    public void setRedCross(){
        this.redCross = irc.getInstance().tryGetImage(PathBuilder.getMap("/" + name + ".png"));
    }
    
    
    
    
    public void paint(Graphics g) {
        if (isclicked) {
            g.drawImage(redCross, x, y, width, height, null);
        }
    }

    @Override
    public String toString() {
        String str = "";
        return str += x + " " + y;
    }

}
