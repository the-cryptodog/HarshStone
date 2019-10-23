/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author frank61003
 */
public class CommandList {
    private boolean clicked;
    private boolean released;
    private boolean pressed;
    private boolean dragged;
    protected int x;
    protected int y;
//    private boolean enter;

    public static interface KeyHandler{
        public void clicked(boolean isPressed,int x, int y);
        public void released(boolean isPressed,int x, int y);
        public void pressed(boolean isPressed,int x, int y);
        public void dragged(boolean isPressed,int x, int y);
//        public void enter(boolean isPressed);
    }


    public void setClicked(boolean clicked){
        this.clicked = clicked;
        System.out.println("clicked");
    }
    public void setReleased(boolean released){
        this.released = released;
        System.out.println("released");
    }
    public void setPressed(boolean pressed){
        this.pressed = pressed;
    }
    public void setDragged(boolean dragged){
        this.dragged = dragged;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    
    
    
    
    
    
//    public void setEnter(boolean right){
//        this.enter = enter;
//    }
    
    public void action(KeyHandler keyhandler){
        keyhandler.clicked(clicked, x, y);
        keyhandler.released(released, x, y);
        keyhandler.pressed(pressed, x, y);
        keyhandler.dragged(dragged, x, y);
//        keyhandler.right(enter);
    }
    
    
    public void clear(){
        clicked = false;
        released = false;
        pressed = false;
        dragged = false;
    }
}
