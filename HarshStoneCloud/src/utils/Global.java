/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gameObject.Card.CardDeck;

/**
 *
 * @author frank61003
 */
public class Global {
    //刷新時間
    public static final int FRAME_PER_SECOND = 60;
    public static final int MILLISEC_PER_FRAME = 1000 / FRAME_PER_SECOND;
    //畫面更新時間
    public static final int FRAME_LIMIT = 120;
    public static final int LIMIT_DELTA_TIME = 1000 / FRAME_LIMIT;
    
    //物件速度計算
    public static final int ACT_SPEED = 100;
    
    //圖片大小制定
    public static final int IMG_X_OFFSET = 32;
    public static final int IMG_Y_OFFSET = 32;
    
     //技能圖片大小制定
    public static final int SKILLIMG_X_OFFSET = 192;
    public static final int SKILLIMG_Y_OFFSET = 192;
    
    //上下左右常數制定
    public static final int UP = 3;
    public static final int DOWN = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    
    public static final int ANIMATION_DELAY = FRAME_PER_SECOND/15;
    
//血條偏移量
    public static final int HEALTHX = 15;
    
    //怪物x位置
    public static final int MONSTERX = 1400;
    public static final int MONSTERWIDTH = 160;
    public static final int MONSTERHEIGHT = 160;
    
    //主角位置參數
    public static final int HEROX = 300;
    public static final int HEROY = 400;
    public static final int HEROWIDTH = 160;
    public static final int HEROXHEIGHT = 160;
    
    //動畫位置參數
    
    public static final int SKILLWIDTH = 192;
    public static final int SKILLHEIGHT = 192;
    public static final int SKILLDELTA = 16;
    
    //移動量
    public static final int MOVERANGE = 50;


    
    //關卡數量
    public static final int STAGENUMBER = 9;
    
    //目前關卡
    public static int CURRENTSTAGE = 0;


    
    //Card 
    public static final int CARDWIDTH = 141;
    public static final int CARDHEIGHT = 195;
    
    
    //CardDeck
    public static final int CARDDECKWIDTH = (int)(141*1.1);
    public static final int CARDDECKHEIGHT = (int)(195*1.1);
    public static final int CARDDECKBOTTOM = 700;
    
    
    
    //HeroDeck
    public static CardDeck HERODECK;
    
    
    //Numberimage
    public static final int NUMBER_X_OFFSET = 213;
    public static final int NUMBER_Y_OFFSET = 214;
    
    //MonsterStateIcon
    public static final int ICON_X_OFFSET = 60;
    public static final int ICON_Y_OFFSET = 60;
    
}
