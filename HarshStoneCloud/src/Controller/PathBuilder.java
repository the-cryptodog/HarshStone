/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import values.ImagePath;

/**
 *
 * @author frank61003
 */
public class PathBuilder {
    public static String getImage(String path){
        return ImagePath.ROOT + ImagePath.IMAGE_ROOT + path;
    }
    public static String getMap(String path){
        return ImagePath.ROOT + ImagePath.MAP_ROOT + path;
    }
    
    public static String getButton(String path){
        return ImagePath.ROOT + ImagePath.BUTTON_ROOT + path;
    }
    
    public static String getJobs(String path){
        return ImagePath.ROOT + ImagePath.JOBS_ROOT + path;
    }
    
    public static String getMonster(String path){
        return ImagePath.ROOT + ImagePath.MONSTER_ROOT + path;
    }
    public static String getHero(String path) {
        return ImagePath.ROOT + ImagePath.ACTOR_ROOT + path;
    }
    public static String getSkill(String path){
        return ImagePath.ROOT + ImagePath.SKILL_ROOT + path;
    }
    
    public static String getIcon(String path){
       return ImagePath.ROOT + ImagePath.ICON_ROOT + path;
    }
    
    public static String getNumber(String path){
       return ImagePath.ROOT + ImagePath.NUMBER_ROOT + path;
    }
    public static String getIncidence(String path){
       return ImagePath.ROOT + ImagePath.INCIDENCE_ROOT + path;
    }
    public static String getAudio(String path){
       return ImagePath.ROOT + ImagePath.AUDIO_ROOT + path;
    }
    
}
