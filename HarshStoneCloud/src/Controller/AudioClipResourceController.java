/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javafx.scene.media.AudioClip;
        

/**
 *
 * @author frank61003
 */
public class AudioClipResourceController {
        public static class AudioKeyPair{
        private String path;
        private AudioClip audio;
        //Constructor
        public AudioKeyPair(String path, AudioClip audio){
            this.path = path;
            this.audio = audio;
        }
    }
    
    public static AudioClipResourceController audioController;
    private ArrayList<AudioKeyPair> audioList;
  
    //constructor
    private AudioClipResourceController(){
        audioList = new ArrayList<AudioKeyPair>();
    };
    
    //accessor
    public static AudioClipResourceController getInstance(){
        if(audioController == null){
           audioController = new AudioClipResourceController();
        }
        return audioController;
    }
    
    
    public void clearAudio(){
        audioList.clear();
    }
    
    public AudioClip tryGetAudioClip(String path){
        AudioClip audio = searchAudioClip(path);
        if(audio == null){
            audio = addAudioClip(path);
        }
        return audio;
    }
    
    private AudioClip addAudioClip(String path){
            URL url;
            url = getClass().getResource(path);
            AudioClip clip = new AudioClip(url.toString());
            AudioKeyPair key = new AudioKeyPair(path, clip);
            audioList.add(key);
            return clip;
       
        
    }
    private AudioClip searchAudioClip(String path){
        AudioKeyPair key = null;
        for(int i = 0; i < audioList.size(); i++){
            if(audioList.get(i).path.equals(path)){
                key = audioList.get(i);
                break;
            }
        }
        if(key == null){
            return null;
        }
        return key.audio;
    }
}
