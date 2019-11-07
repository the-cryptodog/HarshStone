/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameObject;

import utils.Global;

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
            numbericon.speedy = -1000;
            
            
            switch(temp){
                case 1:
                    numbericon.numbericonmovestate = new NumberMoveStyle1();
                    System.out.println("NumberMoveStyle1");
                    numbericon.speedy = 30;
                    break;
                case 2:
                    numbericon.numbericonmovestate = new NumberMoveStyle2();
                    System.out.println("NumberMoveStyle2");
                    numbericon.speedy = 40;
                    break;
                case 3:
                    numbericon.numbericonmovestate = new NumberMoveStyle3();
                    System.out.println("NumberMoveStyle3");
                    numbericon.speedy = 30;
                    break;
            

            }
        }
    }
    
    public class NumberMoveStyle1 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
//            if(numbericon.speedy < -50){
//                numbericon.speedy = 50;
//            }
            System.out.println("NumberMoveStyle1");
            System.out.println(numbericon.speedy);
            numbericon.x += 8;
            
                numbericon.y -= numbericon.speedy;
                numbericon.speedy -= 5;
            

                if(numbericon.y - numbericon.orginaly > 0){
                    numbericon.setNumberIconMoveState(new NumberMoveStop());
                }
            
                
                    
            
        }
    }
    
    public class NumberMoveStyle2 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
//            if(numbericon.speedy < -60){
//                numbericon.speedy = 60;
//            }
            System.out.println("NumberMoveStyle2");
            System.out.println(numbericon.speedy);
            numbericon.x += 8;
                     
            numbericon.y -= numbericon.speedy;
            numbericon.speedy -= 10;
            if(numbericon.y - numbericon.orginaly > 0){
                numbericon.setNumberIconMoveState(new NumberMoveStop());
            }
            
           
        }
    }
    
    public class NumberMoveStyle3 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon) {
//            if(numbericon.speedy < -30){
//                numbericon.speedy = 30;
//            }
            
            numbericon.x += 7;
            System.out.println("NumberMoveStyle3");
            System.out.println(numbericon.speedy);
            
            numbericon.y -= numbericon.speedy;
            numbericon.speedy -= 5;

            if(numbericon.y - numbericon.orginaly > 0){
                numbericon.setNumberIconMoveState(new NumberMoveStop());
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
