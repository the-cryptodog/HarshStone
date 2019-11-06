/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gameObject.Card.CardDeck;
import gameObject.Hero.Hero;

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

    public static final int ANIMATION_DELAY = FRAME_PER_SECOND / 15;

    //血條偏移量
    public static final int HEALTHX = 15;

    //怪物x位置
    public static final int MONSTERX = 1400;
    public static final int MONSTERY = 20;
    public static final int MONSTERY2 = 250;
    public static final int MONSTERY3 = 480;
    public static final int MONSTERWIDTH = 160;
    public static final int MONSTERHEIGHT = 160;

    //主角位置參數
    public static final int HEROX = 300;
    public static final int HEROY = 180;
    public static final int HEROWIDTH = 160;
    public static final int HEROXHEIGHT = 160;
    
    //主角初始位置參數(選角畫面)
    public static final int JOB1X = 485;
    public static final int JOB2X = 1285;
    public static final int JOBY = 370;
    public static final int NPCX = 2080;

    //主角
    public static Hero hero;

    //動畫位置參數
    public static final int SKILLWIDTH = 192;
    public static final int SKILLHEIGHT = 192;
    public static final int SKILLDELTA = 16;
    

    //移動量
    public static final int MOVERANGE = 50;
    public static final int HEROMOVERANGE = 40;

    //關卡數量
    public static final int STAGENUMBER = 9;

    //目前關卡
    public static int CURRENTSTAGE = 1;
    
    //過關判定
    public static boolean STAGE1CLEAR = false;
    public static boolean STAGE2CLEAR = false;
    public static boolean STAGE3CLEAR = false;
    public static boolean STAGE4CLEAR = false;
    public static boolean STAGE5CLEAR = false;
    
    //Card largenIndex
    public static final float LARGEN = 1.3f;
    
    //Card 
    public static final int CARDWIDTH = (int)(141*LARGEN);
    public static final int CARDHEIGHT =(int)(195*LARGEN);

    //CardDeck
    public static final int CARDDECKWIDTH = (int) (141 * 1.3);
    public static final int CARDDECKHEIGHT = (int) (195 * 1.3);
    public static final int CARDDECKBOTTOM = 700;
    
    //RareCard
    public static final int AWARDCARDX1 =300;
    public static final int AWARDCARDX2 = 750;
    public static final int AWARDCARDX3 = 1200;
    public static final int AWARDCARDY = (int) (141 * 1.3);
  

    //HeroDeck
    public static CardDeck HERODECK;

    //Numberimage
    public static final int NUMBER_X_OFFSET = 110;
    public static final int NUMBER_Y_OFFSET = 130;
    public static final int NUMBER_DELTAX = 30;
    

    //MonsterStateIcon
    public static final int ICON_X_OFFSET = 60;
    public static final int ICON_Y_OFFSET = 60;
    
    //怪物總數
     public static final int MONSTERNUM = 3;


    //技能代號
    public static final String SKILL1 = "Sword1";
    public static final String SKILL2 = "Sword2";
    public static final String SKILL3 = "Sword3";
    public static final String SKILL4 = "Sword4";
    public static final String SKILL5 = "Sword5";
    public static final String SKILL6 = "Sword6";
    public static final String SKILL7 = "Sword7";
    public static final String SKILL8 = "Sword8";
    public static final String SKILL9 = "Sword9";
    public static final String SKILL10 = "Sword10";
    
    //技能動作次數
    public static final int SKILLACT1 = 10;
    public static final int SKILLACT2 = 15;
    public static final int SKILLACT3 = 15;
    public static final int SKILLACT4 = 15;
    public static final int SKILLACT5 = 25;
    public static final int SKILLACT6 = 20;
    public static final int SKILLACT7 = 20;
    public static final int SKILLACT8 = 25;
    public static final int SKILLACT9 = 30;
    public static final int SKILLACT10 = 20;

    //背景圖長寬
    public static final int JWIDTH = 1920;
    public static final int JHEIGHT = 1080;
    
    //背景轉場常數
    public static final int XSPEED = 16;
    public static final int YSPEED = 9;
    
    //j箭頭跳動常數
    public static final int ARROWSPEED = 10;
    
    
    //卡片數值X常數
    public static final int DIGIT1X=( CARDWIDTH/4-CARDWIDTH/5);
     public static final int DIGIT2X= ( CARDWIDTH/4);
     public static final int DIGIT3X= (( CARDWIDTH/4)*3-CARDWIDTH/5);
     public static final int DIGIT4X= (( CARDWIDTH/4)*3);


     //戰鬥畫面配置參數
    public static final int DISCARDDECKX = 1700;
    
     //獎勵面板長寬
    public static final int AWARDWIDTH = 1317;
    public static final int AWARDHEIGHT = 848;
     //卡片放大檢視係數
     public static final float INSPECTSIZE =1.3f;
     //卡片獎勵放大
     public static final float AWARDSIZE =1.8f;
     //卡片放大檢視長寬
     public static final int INSPECTCARDWIDTH = (int)(INSPECTSIZE*CARDWIDTH);
     public static final int INSPECTCARDHEIGHT =(int)(INSPECTSIZE*CARDHEIGHT);
     
     
    //Boss位置參數
     public static final float BOSSRATE = 1.5f;
     public static final int BOSSX = 1400;
     public static final int BOSSY = 200;
     public static final int BOSSWIDTH = (int)(MONSTERWIDTH*BOSSRATE);
     public static final int BOSSHEIGHT = (int)(MONSTERHEIGHT*BOSSRATE);
     
     
}
