/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import gameObject.MapIcon.redCross;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author User
 */
public class StageController {

    private ArrayList<redCross> redCrossList;
    private int[][] redCrossSetter = {{495,505},
     /*{x座標/y座標} */                            {695,344},{695,618},
                                                           {982,330},{982,520},{982,678},
                                                           {1305,342},{1305,606},
                                                           {1464,552}};
                                                            
    private int stage[]= {1,2,3,2,1}; //{關卡階段/階段關卡數}
  
    public StageController() {
        for (int i = 0; i < Global.STAGENUMBER; i++) {
            redCrossList.add(new redCross
                (redCrossSetter[i][0],redCrossSetter[i][1],50,50,"紅叉叉"));
        }
    }

//    public int getStage() {
//        return stage;
//    }
//
//    public stageUpdate() {
//
//    }
}
