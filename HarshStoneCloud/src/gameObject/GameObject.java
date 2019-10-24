package gameObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controller.ImageResourceController;
import java.awt.image.BufferedImage;

/**
 *
 * @author frank61003
 */
public abstract class GameObject {
    
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected String name;
    protected ImageResourceController irc;
    protected BufferedImage image;
    
    
    public GameObject(int x, int y, int width, int height, String name){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        irc = ImageResourceController.getInstance();
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public String getName(){
        return name;
    }
    
    
    public boolean isCollision(int x, int y){
        if(x < this.x || x > this.x + width){
            return false;
        
        }
        if(y < this.y || y > this.y + height){
            return false;
        }
        return true;
    }
    
    
    public boolean isCollision(GameObject gameobject){
        if(gameobject.x + gameobject.width < this.x || gameobject.x > this.x + width){
            return false;
        
        }
        if(gameobject.y + gameobject.height < this.y || gameobject.y > this.y + height){
            return false;
        }
        return true;
    }
}
