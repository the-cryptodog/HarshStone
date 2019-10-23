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
public class DelayCounter {
    public interface Action{
        public void action();
    }
    
    private int delay;
    private int count;
    private Action action;
    
    public DelayCounter(int delay, Action action){
        this.delay = delay;
        this.action = action;
        count = 0;
    }
    
    public boolean delayupdate(){
            count ++;
            if(count == delay){
                count = 0;
                return true;
            }
            return false;    
    }
    
    
    
    
}
