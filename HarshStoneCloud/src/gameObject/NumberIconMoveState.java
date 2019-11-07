/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

/**
 *
 * @author frank61003
 */
public interface NumberIconMoveState {
    public void action(NumberIcon numbericon);
    
    
    
    public class NumberDecideMove implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
            int temp=(int)(Math.random()*3);
            switch(temp){
                case 1:
                numbericon.numbericonmovestate = new NumberMoveStyle1();
                
                case 2:
                numbericon.numbericonmovestate = new NumberMoveStyle2();
                                    
                case 3:
                numbericon.numbericonmovestate = new NumberMoveStyle3();
               
            

            }
        }
    }
    
    public class NumberMoveStyle1 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {

            numbericon.x += 12;
            
            if( numbericon.x - numbericon.orginalx < 500){
                numbericon.y -= 22;
            }
            else{
                numbericon.y += 22;
                if(numbericon.y - numbericon.orginaly > 0){
                    numbericon.setNumberIconMoveState(new NumberMoveStop());
                }
            }
                    
                    
            
        }
    }
    
    public class NumberMoveStyle2 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
            numbericon.x += 15;
            
            if( numbericon.x - numbericon.orginalx < 450){
                numbericon.y -= 15;
            }
            else{
                numbericon.y += 15;
                if(numbericon.y - numbericon.orginaly > 0){
                    numbericon.setNumberIconMoveState(new NumberMoveStop());
                }
            }
           
        }
    }
    
    public class NumberMoveStyle3 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
            numbericon.x += 13;
            
            if( numbericon.x - numbericon.orginalx < 450){
                numbericon.y -= 19;
            }
            else{
                numbericon.y += 30;
                if(numbericon.y - numbericon.orginaly > 0){
                    numbericon.setNumberIconMoveState(new NumberMoveStop());
                }
            }
            
        }
    }
    
    public class NumberMoveStop implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
            
        }
    }
    
    public class NumberNoMove implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
            
        }
    }
    
}
