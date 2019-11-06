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
    public void action(NumberIcon numbericon1,NumberIcon numbericon2);
    
    
    
    public class decideMove implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon1, NumberIcon numbericon2) {
            int temp=(int)(Math.random()*3);
            switch(temp){
                case 1:
                numbericon1.numbericonmovestate = new moveStyle1();
                numbericon2.numbericonmovestate = new moveStyle1();
                
                case 2:
                numbericon1.numbericonmovestate = new moveStyle2();
                numbericon2.numbericonmovestate = new moveStyle2();
                    
                case 3:
                numbericon1.numbericonmovestate = new moveStyle3();
                numbericon2.numbericonmovestate = new moveStyle3();
            
            
            }
        }
    }
    
    public class moveStyle1 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon1, NumberIcon numbericon2) {

            numbericon1.x += 15;
            numbericon2.x += 15;
            if( numbericon1.x - numbericon1.orginalx < 60){
                numbericon1.y += 15;
            
            }
            
                    
                    
            
        }
    }
    
    public class moveStyle2 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon1, NumberIcon numbericon2) {
            int temp=(int)(Math.random()*3);
           
        }
    }
    
    public class moveStyle3 implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon1, NumberIcon numbericon2) {
            int temp=(int)(Math.random()*3);
            
        }
    }
    
    public class moveStop implements NumberIconMoveState{

        @Override
        public void action(NumberIcon numbericon1, NumberIcon numbericon2) {
            
        }
    }
    
    
}
