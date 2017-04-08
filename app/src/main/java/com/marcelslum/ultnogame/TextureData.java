package com.marcelslum.ultnogame;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Marcel on 24/03/2017.
 */

public class TextureData {

    public static float textureSize = 2048f;

    public static TextureData currentTextureData;
    public static HashMap<Integer, String> map;

    public static ArrayList<TextureData> texturesData;

    public static final String TAG = "TextureData";

    public static final int TEXTURE_TARGET_BLACK_ID = 0;
    public static final int TEXTURE_TARGET_BLUE_ID = 1;
    public static final int TEXTURE_TARGET_GREEN_ID = 2;
    public static final int TEXTURE_TARGET_RED_ID = 3;
    public static final int TEXTURE_ARROW_ID = 4;
    public static final int TEXTURE_ARROW_DOWN_ID = 5;
    public static final int TEXTURE_ARROW_LEFT_ID = 6;
    public static final int TEXTURE_ARROW_RIGHT_ID = 7;
    public static final int TEXTURE_ARROW_UP_ID = 8;
    public static final int TEXTURE_BALL_BLACK_ID = 9;
    public static final int TEXTURE_BALL_BLUE_ID = 10;
    public static final int TEXTURE_BALL_COLLISION_ID = 11;
    public static final int TEXTURE_BALL_GREEN_ID = 12;
    public static final int TEXTURE_BALL_ORANGE_ID = 13;
    public static final int TEXTURE_BALL_PINK_ID = 14;
    public static final int TEXTURE_BALL_PURPLE_ID = 15;
    public static final int TEXTURE_BALL_RED_ID = 16;
    public static final int TEXTURE_BALL_YELLOW_ID = 17;
    public static final int TEXTURE_BAR_BLACK_ID = 18;
    public static final int TEXTURE_BAR_BLUE_ID = 19;
    public static final int TEXTURE_BAR_GREEN_ID = 20;
    public static final int TEXTURE_BAR_ORANGE_ID = 21;
    public static final int TEXTURE_BAR_PINK_ID = 22;
    public static final int TEXTURE_BAR_PURPLE_ID = 23;
    public static final int TEXTURE_BAR_RED_ID = 24;
    public static final int TEXTURE_BAR_TOP_ID = 25;
    public static final int TEXTURE_BAR_YELLOW_ID = 26;
    public static final int TEXTURE_BE2_ID = 27;
    public static final int TEXTURE_BE3_ID = 28;
    public static final int TEXTURE_BE4_ID = 29;
    public static final int TEXTURE_BE5_ID = 30;
    public static final int TEXTURE_BE6_ID = 31;
    public static final int TEXTURE_BE7_ID = 32;
    public static final int TEXTURE_BE8_ID = 33;
    public static final int TEXTURE_BE9_ID = 34;
    public static final int TEXTURE_BE10_ID = 35;
    public static final int TEXTURE_BE11_ID = 36;
    public static final int TEXTURE_BE12_ID = 37;
    public static final int TEXTURE_BUTTON_BAR_LEFT_ID = 38;
    public static final int TEXTURE_BUTTON_BAR_LEFT_PRESS_ID = 39;
    public static final int TEXTURE_BUTTON_BAR_RIGHT_ID = 40;
    public static final int TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID = 41;
    public static final int TEXTURE_ARROW_DOWN_PRESS_ID = 42;
    public static final int TEXTURE_ARROW_LEFT_PRESS_ID = 43;
    public static final int TEXTURE_ARROW_RIGHT_PRESS_ID = 44;
    public static final int TEXTURE_ARROW_UP_PRESS_ID = 45;
    public static final int TEXTURE_EXPLOSION_BLUE_1_ID = 46;
    public static final int TEXTURE_EXPLOSION_BLUE_2_ID = 47;
    public static final int TEXTURE_EXPLOSION_BLUE_3_ID = 48;
    public static final int TEXTURE_EXPLOSION_RED_1_ID = 49;
    public static final int TEXTURE_EXPLOSION_RED_2_ID = 50;
    public static final int TEXTURE_EXPLOSION_RED_3_ID = 51;
    public static final int TEXTURE_JEFT_SET_ID = 52;
    public static final int TEXTURE_PANEL_BLUE_ID = 53;
    public static final int TEXTURE_PANEL_INVENCIBLE_ID = 54;
    public static final int TEXTURE_PANEL_BLACK_ID = 55;
    public static final int TEXTURE_PARTICLE_BALL_ID = 56;
    public static final int TEXTURE_POINT0_ID = 57;
    public static final int TEXTURE_POINT1_ID = 58;
    public static final int TEXTURE_POINT2_ID = 59;
    public static final int TEXTURE_POINT3_ID = 60;
    public static final int TEXTURE_POINT4_ID = 61;
    public static final int TEXTURE_POINT5_ID = 62;
    public static final int TEXTURE_POINT6_ID = 63;
    public static final int TEXTURE_POINT7_ID = 64;
    public static final int TEXTURE_POINT8_ID = 65;
    public static final int TEXTURE_POINT9_ID = 66;
    public static final int TEXTURE_POINT_PANEL0_ID = 67;
    public static final int TEXTURE_POINT_PANEL1_ID = 68;
    public static final int TEXTURE_POINT_PANEL2_ID = 69;
    public static final int TEXTURE_POINT_PANEL3_ID = 70;
    public static final int TEXTURE_POINT_PANEL4_ID = 71;
    public static final int TEXTURE_POINT_PANEL5_ID = 72;
    public static final int TEXTURE_POINT_PANEL6_ID = 73;
    public static final int TEXTURE_POINT_PANEL7_ID = 74;
    public static final int TEXTURE_POINT_PANEL8_ID = 75;
    public static final int TEXTURE_POINT_PANEL9_ID = 76;
    public static final int TEXTURE_STAR_OFF_ID = 77;
    public static final int TEXTURE_STAR_SHINE_ID = 78;
    public static final int TEXTURE_TITTLE_ID = 79;
    public static final int TEXTURE_WINDOW_ID = 80;
    public static final int TEXTURE_BUTTON_GROUP_LEADERBOARD_ID = 81;
    public static final int TEXTURE_BUTTON_GROUP_LEADERBOARD_PRESS_ID = 82;
    public static final int TEXTURE_BE1_ID = 83;
    
    
    public static final int TEXTURE_BACK_BLACK = 200;
    public static final int TEXTURE_BACK_BLUE = 201;
    public static final int TEXTURE_BACK_GREEN = 202;
    public static final int TEXTURE_BACK_RED = 203;
    public static final int TEXTURE_BACK_GRAY1 = 204;
    public static final int TEXTURE_BACK_GRAY2 = 205;
    public static final int TEXTURE_BACK_GRAY3 = 206;
    public static final int TEXTURE_BACK_GRAY4 = 207;
    public static final int TEXTURE_BACK_GRAY5 = 208;
    
    public static final int TEXTURE_CLOUD1 = 300;
    public static final int TEXTURE_CLOUD2 = 301;
    public static final int TEXTURE_CLOUD3 = 302;
    public static final int TEXTURE_CLOUD4 = 303;
    public static final int TEXTURE_CLOUD5 = 304;
    public static final int TEXTURE_CLOUD6 = 305;

    public static final int TEXTURE_TUTORIAL1_ID = 1001;
    public static final int TEXTURE_TUTORIAL2_ID = 1002;
    public static final int TEXTURE_TUTORIAL3_ID = 1003;
    public static final int TEXTURE_TUTORIAL4_ID = 1004;
    public static final int TEXTURE_TUTORIAL5_ID = 1005;
    public static final int TEXTURE_TUTORIAL6_ID = 1006;
    public static final int TEXTURE_TUTORIAL7_ID = 1007;
    public static final int TEXTURE_TUTORIAL8_ID = 1008;
    public static final int TEXTURE_TUTORIAL9_ID = 1009;
    public static final int TEXTURE_TUTORIAL10_ID = 1010;
    public static final int TEXTURE_TUTORIAL11_ID = 1011;
    public static final int TEXTURE_TUTORIAL12_ID = 1012;
    public static final int TEXTURE_TUTORIAL13_ID = 1013;
    public static final int TEXTURE_TUTORIAL14_ID = 1014;
    public static final int TEXTURE_TUTORIAL15_ID = 1015;
    public static final int TEXTURE_TUTORIAL16_ID = 1016;
    public static final int TEXTURE_TUTORIAL17_ID = 1017;
    public static final int TEXTURE_TUTORIAL18_ID = 1018;
    public static final int TEXTURE_TUTORIAL19_ID = 1019;
    public static final int TEXTURE_TUTORIAL20_ID = 1020;
    public static final int TEXTURE_TUTORIAL21_ID = 1021;
    public static final int TEXTURE_TUTORIAL22_ID = 1022;
    public static final int TEXTURE_TUTORIAL23_ID = 1023;
    public static final int TEXTURE_TUTORIAL24_ID = 1024;
    public static final int TEXTURE_TUTORIAL25_ID = 1025;
    public static final int TEXTURE_TUTORIAL26_ID = 1026;
    public static final int TEXTURE_TUTORIAL27_ID = 1027;
    public static final int TEXTURE_TUTORIAL28_ID = 1028;
    public static final int TEXTURE_TUTORIAL29_ID = 1029;




    public static final int TEXTURE_l1 = 3001;
    public static final int TEXTURE_l2 = 3002;
    public static final int TEXTURE_l3 = 3003;
    public static final int TEXTURE_l4 = 3004;
    public static final int TEXTURE_l5 = 3005;
    public static final int TEXTURE_l6 = 3006;
    public static final int TEXTURE_l7 = 3007;
    public static final int TEXTURE_l8 = 3008;
    public static final int TEXTURE_l9 = 3009;
    public static final int TEXTURE_l10 = 3010;
    public static final int TEXTURE_l11 = 3011;
    public static final int TEXTURE_l12 = 3012;
    public static final int TEXTURE_l13 = 3013;
    public static final int TEXTURE_l14 = 3014;
    public static final int TEXTURE_l15 = 3015;
    public static final int TEXTURE_l16 = 3016;
    public static final int TEXTURE_l17 = 3017;
    public static final int TEXTURE_l18 = 3018;
    public static final int TEXTURE_l19 = 3019;
    public static final int TEXTURE_l20 = 3020;
    public static final int TEXTURE_l21 = 3021;
    public static final int TEXTURE_l22 = 3022;
    public static final int TEXTURE_l23 = 3023;
    public static final int TEXTURE_l24 = 3024;
    public static final int TEXTURE_l25 = 3025;
    public static final int TEXTURE_l26 = 3026;
    public static final int TEXTURE_l27 = 3027;
    public static final int TEXTURE_l28 = 3028;
    public static final int TEXTURE_l29 = 3029;
    public static final int TEXTURE_l30 = 3030;
    public static final int TEXTURE_l31 = 3031;
    public static final int TEXTURE_l32 = 3032;
    public static final int TEXTURE_l33 = 3033;
    public static final int TEXTURE_l34 = 3034;
    public static final int TEXTURE_l35 = 3035;
    public static final int TEXTURE_l36 = 3036;
    public static final int TEXTURE_l37 = 3037;
    public static final int TEXTURE_l38 = 3038;
    public static final int TEXTURE_l39 = 3039;
    public static final int TEXTURE_l40 = 3040;
    public static final int TEXTURE_l41 = 3041;
    public static final int TEXTURE_l42 = 3042;
    public static final int TEXTURE_l43 = 3043;
    public static final int TEXTURE_l44 = 3044;
    public static final int TEXTURE_l45 = 3045;
    public static final int TEXTURE_l46 = 3046;
    public static final int TEXTURE_l47 = 3047;
    public static final int TEXTURE_l48 = 3048;
    public static final int TEXTURE_l49 = 3049;
    public static final int TEXTURE_l50 = 3050;
    public static final int TEXTURE_l51 = 3051;
    public static final int TEXTURE_l52 = 3052;
    public static final int TEXTURE_l53 = 3053;
    public static final int TEXTURE_l54 = 3054;
    public static final int TEXTURE_l55 = 3055;
    public static final int TEXTURE_l56 = 3056;
    public static final int TEXTURE_l57 = 3057;
    public static final int TEXTURE_l58 = 3058;
    public static final int TEXTURE_l59 = 3059;
    public static final int TEXTURE_l60 = 3060;
    public static final int TEXTURE_l61 = 3061;
    public static final int TEXTURE_l62 = 3062;
    public static final int TEXTURE_l63 = 3063;
    public static final int TEXTURE_l64 = 3064;
    public static final int TEXTURE_l65 = 3065;
    public static final int TEXTURE_l66 = 3066;
    public static final int TEXTURE_l67 = 3067;
    public static final int TEXTURE_l68 = 3068;
    public static final int TEXTURE_l69 = 3069;
    public static final int TEXTURE_l70 = 3070;
    public static final int TEXTURE_l71 = 3071;
    public static final int TEXTURE_l72 = 3072;
    public static final int TEXTURE_l73 = 3073;
    public static final int TEXTURE_l74 = 3074;
    public static final int TEXTURE_l75 = 3075;
    public static final int TEXTURE_l76 = 3076;
    public static final int TEXTURE_l77 = 3077;
    public static final int TEXTURE_l78 = 3078;
    public static final int TEXTURE_l79 = 3079;
    public static final int TEXTURE_l80 = 3080;
    public static final int TEXTURE_l81 = 3081;
    public static final int TEXTURE_l82 = 3082;
    public static final int TEXTURE_l83 = 3083;
    public static final int TEXTURE_l84 = 3084;
    public static final int TEXTURE_l85 = 3085;
    public static final int TEXTURE_l86 = 3086;
    public static final int TEXTURE_l87 = 3087;
    public static final int TEXTURE_l88 = 3088;
    public static final int TEXTURE_l89 = 3089;
    public static final int TEXTURE_l90 = 3090;
    public static final int TEXTURE_l91 = 3091;
    public static final int TEXTURE_l92 = 3092;
    public static final int TEXTURE_l93 = 3093;
    public static final int TEXTURE_l94 = 3094;
    public static final int TEXTURE_l95 = 3095;
    public static final int TEXTURE_l96 = 3096;
    public static final int TEXTURE_l97 = 3097;
    public static final int TEXTURE_l98 = 3098;
    public static final int TEXTURE_l99 = 3099;
    public static final int TEXTURE_l100 = 3100;
    public static final int TEXTURE_l101 = 3101;
    public static final int TEXTURE_l102 = 3102;
    public static final int TEXTURE_l103 = 3103;
    public static final int TEXTURE_l104 = 3104;

    public static final int TEXTURE_G1_ID = 2001;
    public static final int TEXTURE_G2_ID = 2002;
    public static final int TEXTURE_G3_ID = 2003;
    public static final int TEXTURE_G4_ID = 2004;
    public static final int TEXTURE_G5_ID = 2005;
    public static final int TEXTURE_G6_ID = 2006;
    public static final int TEXTURE_G7_ID = 2007;
    public static final int TEXTURE_G8_ID = 2008;
    public static final int TEXTURE_G9_ID = 2009;
    public static final int TEXTURE_G10_ID = 2010;
    public static final int TEXTURE_G11_ID = 2011;
    public static final int TEXTURE_G12_ID = 2012;
    public static final int TEXTURE_G13_ID = 2013;
    public static final int TEXTURE_G14_ID = 2014;
    public static final int TEXTURE_G15_ID = 2015;
    public static final int TEXTURE_G16_ID = 2016;
    public static final int TEXTURE_G17_ID = 2017;
    public static final int TEXTURE_G18_ID = 2018;
    public static final int TEXTURE_G19_ID = 2019;
    public static final int TEXTURE_G20_ID = 2020;


    String name;
    float x, y, w, h;
    int id;

    public TextureData(int id, String name, float x, float y, float w, float h) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        //Log.e(TAG, id + " - "+ name + " = "+this.x+", "+this.y+", "+this.w+", "+this.h);
    }

    public static TextureData getTextureDataByName(String n){
        if (texturesData == null){
            return null;
        }
        for (int i = 0; i < texturesData.size(); i++){
            if (texturesData.get(i).name.equals(n)) {
                currentTextureData = texturesData.get(i);
                return texturesData.get(i);
            }
        }
        return null;
    }

    public static TextureData getTextureDataById(int id){
        if (texturesData == null){
            return null;
        }
        for (int i = 0; i < texturesData.size(); i++){

            if (id == TEXTURE_l100){
               Log.e(TAG, "comparando "+ texturesData.get(i).id + " com "+id);
            }

            if (texturesData.get(i).id == id){
                currentTextureData = texturesData.get(i);
                return texturesData.get(i);
            }
        }
        return null;
    }

    public static void getTextureData() {
        map = new HashMap<>();
        map.put(TEXTURE_TARGET_BLACK_ID, "alvo_black.png"); 
        map.put(TEXTURE_TARGET_BLUE_ID, "alvo_blue.png"); 
        map.put(TEXTURE_TARGET_GREEN_ID, "alvo_green.png"); 
        map.put(TEXTURE_TARGET_RED_ID, "alvo_red.png"); 
        map.put(TEXTURE_ARROW_ID, "arrow.png"); 
        map.put(TEXTURE_ARROW_DOWN_ID, "arrow_down.png"); 
        map.put(TEXTURE_ARROW_LEFT_ID, "arrow_left.png"); 
        map.put(TEXTURE_ARROW_RIGHT_ID, "arrow_right.png"); 
        map.put(TEXTURE_ARROW_UP_ID, "arrow_up.png"); 
        map.put(TEXTURE_BALL_BLACK_ID, "ball_black.png"); 
        map.put(TEXTURE_BALL_BLUE_ID, "ball_blue.png"); 
        map.put(TEXTURE_BALL_COLLISION_ID, "ball_collision.png"); 
        map.put(TEXTURE_BALL_GREEN_ID, "ball_green.png"); 
        map.put(TEXTURE_BALL_ORANGE_ID, "ball_orange.png"); 
        map.put(TEXTURE_BALL_PINK_ID, "ball_pink.png"); 
        map.put(TEXTURE_BALL_PURPLE_ID, "ball_purple.png"); 
        map.put(TEXTURE_BALL_RED_ID, "ball_red.png"); 
        map.put(TEXTURE_BALL_YELLOW_ID, "ball_yellow.png"); 
        map.put(TEXTURE_BAR_BLACK_ID, "bar_black.png"); 
        map.put(TEXTURE_BAR_BLUE_ID, "bar_blue.png"); 
        map.put(TEXTURE_BAR_GREEN_ID, "bar_green.png"); 
        map.put(TEXTURE_BAR_ORANGE_ID, "bar_orange.png"); 
        map.put(TEXTURE_BAR_PINK_ID, "bar_pink.png"); 
        map.put(TEXTURE_BAR_PURPLE_ID, "bar_purple.png"); 
        map.put(TEXTURE_BAR_RED_ID, "bar_red.png"); 
        map.put(TEXTURE_BAR_TOP_ID, "bar_top.png"); 
        map.put(TEXTURE_BAR_YELLOW_ID, "bar_yellow.png"); 
        map.put(TEXTURE_BE1_ID, "be1.png"); 
        map.put(TEXTURE_BE2_ID, "be2.png"); 
        map.put(TEXTURE_BE3_ID, "be3.png");
        map.put(TEXTURE_BE4_ID, "be4.png"); 
        map.put(TEXTURE_BE5_ID, "be5.png"); 
        map.put(TEXTURE_BE6_ID, "be6.png"); 
        map.put(TEXTURE_BE7_ID, "be7.png"); 
        map.put(TEXTURE_BE8_ID, "be8.png"); 
        map.put(TEXTURE_BE9_ID, "be9.png"); 
        map.put(TEXTURE_BE10_ID, "be10.png"); 
        map.put(TEXTURE_BE11_ID, "be11.png"); 
        map.put(TEXTURE_BE12_ID, "be12.png"); 
        map.put(TEXTURE_BUTTON_BAR_LEFT_ID, "button_bar_left.png"); 
        map.put(TEXTURE_BUTTON_BAR_LEFT_PRESS_ID, "button_bar_left_press.png"); 
        map.put(TEXTURE_BUTTON_BAR_RIGHT_ID, "button_bar_right.png"); 
        map.put(TEXTURE_BUTTON_BAR_RIGHT_PRESS_ID, "button_bar_right_press.png"); 
        map.put(TEXTURE_ARROW_DOWN_PRESS_ID, "button_down_press.png"); 
        map.put(TEXTURE_ARROW_LEFT_PRESS_ID, "button_left_press.png"); 
        map.put(TEXTURE_ARROW_RIGHT_PRESS_ID, "button_right_press.png");
        map.put(TEXTURE_ARROW_UP_PRESS_ID, "button_up_press.png"); 
        map.put(TEXTURE_EXPLOSION_BLUE_1_ID, "explosion_blue_1.png"); 
        map.put(TEXTURE_EXPLOSION_BLUE_2_ID, "explosion_blue_2.png"); 
        map.put(TEXTURE_EXPLOSION_BLUE_3_ID, "explosion_blue_3.png"); 
        map.put(TEXTURE_EXPLOSION_RED_1_ID, "explosion_red_1.png"); 
        map.put(TEXTURE_EXPLOSION_RED_2_ID, "explosion_red_2.png"); 
        map.put(TEXTURE_EXPLOSION_RED_3_ID, "explosion_red_3.png"); 
        map.put(TEXTURE_JEFT_SET_ID, "jetset.png"); 
        map.put(TEXTURE_PANEL_BLUE_ID, "panel_blue.png"); 
        map.put(TEXTURE_PANEL_INVENCIBLE_ID, "panel_invencible.png"); 
        map.put(TEXTURE_PANEL_BLACK_ID, "panel_black.png"); 
        map.put(TEXTURE_PARTICLE_BALL_ID, "particle_ball.png"); 
        map.put(TEXTURE_POINT0_ID, "point0.png"); 
        map.put(TEXTURE_POINT1_ID, "point1.png"); 
        map.put(TEXTURE_POINT2_ID, "point2.png"); 
        map.put(TEXTURE_POINT3_ID, "point3.png"); 
        map.put(TEXTURE_POINT4_ID, "point4.png"); 
        map.put(TEXTURE_POINT5_ID, "point5.png"); 
        map.put(TEXTURE_POINT6_ID, "point6.png"); 
        map.put(TEXTURE_POINT7_ID, "point7.png"); 
        map.put(TEXTURE_POINT8_ID, "point8.png"); 
        map.put(TEXTURE_POINT9_ID, "point9.png"); 
        map.put(TEXTURE_POINT_PANEL0_ID, "point_panel_0.png"); 
        map.put(TEXTURE_POINT_PANEL1_ID, "point_panel_1.png"); 
        map.put(TEXTURE_POINT_PANEL2_ID, "point_panel_2.png"); 
        map.put(TEXTURE_POINT_PANEL3_ID, "point_panel_3.png"); 
        map.put(TEXTURE_POINT_PANEL4_ID, "point_panel_4.png"); 
        map.put(TEXTURE_POINT_PANEL5_ID, "point_panel_5.png"); 
        map.put(TEXTURE_POINT_PANEL6_ID, "point_panel_6.png"); 
        map.put(TEXTURE_POINT_PANEL7_ID, "point_panel_7.png"); 
        map.put(TEXTURE_POINT_PANEL8_ID, "point_panel_8.png"); 
        map.put(TEXTURE_POINT_PANEL9_ID, "point_panel_9.png"); 
        map.put(TEXTURE_STAR_OFF_ID, "star_off.png"); 
        map.put(TEXTURE_STAR_SHINE_ID, "star_shine.png"); 
        map.put(TEXTURE_TITTLE_ID, "tittle.png"); 
        map.put(TEXTURE_WINDOW_ID, "window.png");
        map.put(TEXTURE_BUTTON_GROUP_LEADERBOARD_ID, "button_leaderboard.png");
        map.put(TEXTURE_BUTTON_GROUP_LEADERBOARD_PRESS_ID, "button_leaderboard_pressed.png");
        
        map.put(TEXTURE_BACK_BLACK, "back_black.png");
        map.put(TEXTURE_BACK_BLUE, "back_blue.png");
        map.put(TEXTURE_BACK_GREEN, "back_green.png");
        map.put(TEXTURE_BACK_RED, "back_red.png");
        map.put(TEXTURE_BACK_GRAY1, "back_gray1.png");
        map.put(TEXTURE_BACK_GRAY2, "back_gray2.png");
        map.put(TEXTURE_BACK_GRAY3, "back_gray3.png");
        map.put(TEXTURE_BACK_GRAY4, "back_gray4.png");
        map.put(TEXTURE_BACK_GRAY5, "back_gray5.png");
        
        map.put(TEXTURE_CLOUD1, "nuvem1.png");
        map.put(TEXTURE_CLOUD2, "nuvem2.png");
        map.put(TEXTURE_CLOUD3, "nuvem3.png");
        map.put(TEXTURE_CLOUD4, "nuvem4.png");
        map.put(TEXTURE_CLOUD5, "nuvem5.png");
        map.put(TEXTURE_CLOUD6, "nuvem6.png");
        
        map.put(TEXTURE_TUTORIAL1_ID, "ti1.png");
        map.put(TEXTURE_TUTORIAL2_ID, "ti2.png");
        map.put(TEXTURE_TUTORIAL3_ID, "ti3.png");
        map.put(TEXTURE_TUTORIAL4_ID, "ti4.png");
        map.put(TEXTURE_TUTORIAL5_ID, "ti5.png");
        map.put(TEXTURE_TUTORIAL6_ID, "ti6.png");
        map.put(TEXTURE_TUTORIAL7_ID, "ti7.png");
        map.put(TEXTURE_TUTORIAL8_ID, "ti8.png");
        map.put(TEXTURE_TUTORIAL9_ID, "ti9.png");
        map.put(TEXTURE_TUTORIAL10_ID, "ti10.png");
        map.put(TEXTURE_TUTORIAL11_ID, "ti11.png");
        map.put(TEXTURE_TUTORIAL12_ID, "ti12.png");
        map.put(TEXTURE_TUTORIAL13_ID, "ti13.png");
        map.put(TEXTURE_TUTORIAL14_ID, "ti14.png");
        map.put(TEXTURE_TUTORIAL15_ID, "ti15.png");
        map.put(TEXTURE_TUTORIAL16_ID, "ti16.png");
        map.put(TEXTURE_TUTORIAL17_ID, "ti17.png");
        map.put(TEXTURE_TUTORIAL18_ID, "ti18.png");
        map.put(TEXTURE_TUTORIAL19_ID, "ti19.png");
        map.put(TEXTURE_TUTORIAL20_ID, "ti20.png");
        map.put(TEXTURE_TUTORIAL21_ID, "ti21.png");
        map.put(TEXTURE_TUTORIAL22_ID, "ti22.png");
        map.put(TEXTURE_TUTORIAL23_ID, "ti23.png");
        map.put(TEXTURE_TUTORIAL24_ID, "ti24.png");
        map.put(TEXTURE_TUTORIAL25_ID, "ti25.png");
        map.put(TEXTURE_TUTORIAL26_ID, "ti26.png");
        map.put(TEXTURE_TUTORIAL27_ID, "ti27.png");
        map.put(TEXTURE_TUTORIAL28_ID, "ti28.png");
        map.put(TEXTURE_TUTORIAL29_ID, "ti29.png");

        map.put(TEXTURE_G1_ID, "icong1.png");
        map.put(TEXTURE_G2_ID, "icong2.png");
        map.put(TEXTURE_G3_ID, "icong3.png");
        map.put(TEXTURE_G4_ID, "icong4.png");
        map.put(TEXTURE_G5_ID, "icong5.png");
        map.put(TEXTURE_G6_ID, "icong6.png");
        map.put(TEXTURE_G7_ID, "icong7.png");
        map.put(TEXTURE_G8_ID, "icong8.png");
        map.put(TEXTURE_G9_ID, "icong9.png");
        map.put(TEXTURE_G10_ID, "icong10.png");
        map.put(TEXTURE_G11_ID, "icong11.png");
        map.put(TEXTURE_G12_ID, "icong12.png");
        map.put(TEXTURE_G13_ID, "icong13.png");
        map.put(TEXTURE_G14_ID, "icong14.png");
        map.put(TEXTURE_G15_ID, "icong15.png");
        map.put(TEXTURE_G16_ID, "icong16.png");
        map.put(TEXTURE_G17_ID, "icong17.png");
        map.put(TEXTURE_G18_ID, "icong18.png");
        map.put(TEXTURE_G19_ID, "icong19.png");
        map.put(TEXTURE_G20_ID, "icong20.png");

        map.put(TEXTURE_l1, "iconl1.png");
        map.put(TEXTURE_l2, "iconl2.png");
        map.put(TEXTURE_l3, "iconl3.png");
        map.put(TEXTURE_l4, "iconl4.png");
        map.put(TEXTURE_l5, "iconl5.png");
        map.put(TEXTURE_l6, "iconl6.png");
        map.put(TEXTURE_l7, "iconl7.png");
        map.put(TEXTURE_l8, "iconl8.png");
        map.put(TEXTURE_l9, "iconl9.png");
        map.put(TEXTURE_l10, "iconl10.png");
        map.put(TEXTURE_l11, "iconl11.png");
        map.put(TEXTURE_l12, "iconl12.png");
        map.put(TEXTURE_l13, "iconl13.png");
        map.put(TEXTURE_l14, "iconl14.png");
        map.put(TEXTURE_l15, "iconl15.png");
        map.put(TEXTURE_l16, "iconl16.png");
        map.put(TEXTURE_l17, "iconl17.png");
        map.put(TEXTURE_l18, "iconl18.png");
        map.put(TEXTURE_l19, "iconl19.png");
        map.put(TEXTURE_l20, "iconl20.png");
        map.put(TEXTURE_l21, "iconl21.png");
        map.put(TEXTURE_l22, "iconl22.png");
        map.put(TEXTURE_l23, "iconl23.png");
        map.put(TEXTURE_l24, "iconl24.png");
        map.put(TEXTURE_l25, "iconl25.png");
        map.put(TEXTURE_l26, "iconl26.png");
        map.put(TEXTURE_l27, "iconl27.png");
        map.put(TEXTURE_l28, "iconl28.png");
        map.put(TEXTURE_l29, "iconl29.png");
        map.put(TEXTURE_l30, "iconl30.png");
        map.put(TEXTURE_l31, "iconl31.png");
        map.put(TEXTURE_l32, "iconl32.png");
        map.put(TEXTURE_l33, "iconl33.png");
        map.put(TEXTURE_l34, "iconl34.png");
        map.put(TEXTURE_l35, "iconl35.png");
        map.put(TEXTURE_l36, "iconl36.png");
        map.put(TEXTURE_l37, "iconl37.png");
        map.put(TEXTURE_l38, "iconl38.png");
        map.put(TEXTURE_l39, "iconl39.png");
        map.put(TEXTURE_l40, "iconl40.png");
        map.put(TEXTURE_l41, "iconl41.png");
        map.put(TEXTURE_l42, "iconl42.png");
        map.put(TEXTURE_l43, "iconl43.png");
        map.put(TEXTURE_l44, "iconl44.png");
        map.put(TEXTURE_l45, "iconl45.png");
        map.put(TEXTURE_l46, "iconl46.png");
        map.put(TEXTURE_l47, "iconl47.png");
        map.put(TEXTURE_l48, "iconl48.png");
        map.put(TEXTURE_l49, "iconl49.png");
        map.put(TEXTURE_l50, "iconl50.png");
        map.put(TEXTURE_l51, "iconl51.png");
        map.put(TEXTURE_l52, "iconl52.png");
        map.put(TEXTURE_l53, "iconl53.png");
        map.put(TEXTURE_l54, "iconl54.png");
        map.put(TEXTURE_l55, "iconl55.png");
        map.put(TEXTURE_l56, "iconl56.png");
        map.put(TEXTURE_l57, "iconl57.png");
        map.put(TEXTURE_l58, "iconl58.png");
        map.put(TEXTURE_l59, "iconl59.png");
        map.put(TEXTURE_l60, "iconl60.png");
        map.put(TEXTURE_l61, "iconl61.png");
        map.put(TEXTURE_l62, "iconl62.png");
        map.put(TEXTURE_l63, "iconl63.png");
        map.put(TEXTURE_l64, "iconl64.png");
        map.put(TEXTURE_l65, "iconl65.png");
        map.put(TEXTURE_l66, "iconl66.png");
        map.put(TEXTURE_l67, "iconl67.png");
        map.put(TEXTURE_l68, "iconl68.png");
        map.put(TEXTURE_l69, "iconl69.png");
        map.put(TEXTURE_l70, "iconl70.png");
        map.put(TEXTURE_l71, "iconl71.png");
        map.put(TEXTURE_l72, "iconl72.png");
        map.put(TEXTURE_l73, "iconl73.png");
        map.put(TEXTURE_l74, "iconl74.png");
        map.put(TEXTURE_l75, "iconl75.png");
        map.put(TEXTURE_l76, "iconl76.png");
        map.put(TEXTURE_l77, "iconl77.png");
        map.put(TEXTURE_l78, "iconl78.png");
        map.put(TEXTURE_l79, "iconl79.png");
        map.put(TEXTURE_l80, "iconl80.png");
        map.put(TEXTURE_l81, "iconl81.png");
        map.put(TEXTURE_l82, "iconl82.png");
        map.put(TEXTURE_l83, "iconl83.png");
        map.put(TEXTURE_l84, "iconl84.png");
        map.put(TEXTURE_l85, "iconl85.png");
        map.put(TEXTURE_l86, "iconl86.png");
        map.put(TEXTURE_l87, "iconl87.png");
        map.put(TEXTURE_l88, "iconl88.png");
        map.put(TEXTURE_l89, "iconl89.png");
        map.put(TEXTURE_l90, "iconl90.png");
        map.put(TEXTURE_l91, "iconl91.png");
        map.put(TEXTURE_l92, "iconl92.png");
        map.put(TEXTURE_l93, "iconl93.png");
        map.put(TEXTURE_l94, "iconl94.png");
        map.put(TEXTURE_l95, "iconl95.png");
        map.put(TEXTURE_l96, "iconl96.png");
        map.put(TEXTURE_l97, "iconl97.png");
        map.put(TEXTURE_l98, "iconl98.png");
        map.put(TEXTURE_l99, "iconl99.png");
        map.put(TEXTURE_l100, "iconl100.png");
        //map.put(TEXTURE_l101, "iconl100.png");//TODO
        //map.put(TEXTURE_l102, "iconl100.png");//TODO
        //map.put(TEXTURE_l103, "iconl100.png");//TODO
        //map.put(TEXTURE_l104, "iconl100.png");//TODO


        JSONObject obj;
        JSONArray arr;

        texturesData = new ArrayList<>();

        try {
            obj = new JSONObject(Utils.readJSONFromAsset("texture_data.json"));
            arr = obj.getJSONArray("frames");

            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject objFrame = arr.getJSONObject(i).getJSONObject("frame");
                String fileName = arr.getJSONObject(i).getString("filename");

                int id = -1;
                Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> pair = it.next();

                    if (pair.getValue().equals(fileName)){
                        id = pair.getKey();
                        break;
                    }
                }

                texturesData.add(new TextureData(
                        id,
                        fileName,
                        (float)objFrame.getInt("x")/textureSize,
                        (float)objFrame.getInt("y")/textureSize,
                        (float)objFrame.getInt("w")/textureSize,
                        (float)objFrame.getInt("h")/textureSize
                ));
            }

            obj = new JSONObject(Utils.readJSONFromAsset("tutorials_data.json"));
            arr = obj.getJSONArray("frames");
            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject objFrame = arr.getJSONObject(i).getJSONObject("frame");
                String fileName = arr.getJSONObject(i).getString("filename");

                int id = 0;
                Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> pair = it.next();

                    if (pair.getValue().equals(fileName)){
                        id = pair.getKey();
                        break;
                    }
                }

                texturesData.add(new TextureData(
                        id,
                        fileName,
                        (float)objFrame.getInt("x")/textureSize,
                        (float)objFrame.getInt("y")/textureSize,
                        (float)objFrame.getInt("w")/textureSize,
                        (float)objFrame.getInt("h")/textureSize
                ));
            }

            obj = new JSONObject(Utils.readJSONFromAsset("icons_data.json"));
            arr = obj.getJSONArray("frames");
            for (int i = 0; i < arr.length(); i++)
            {
                JSONObject objFrame = arr.getJSONObject(i).getJSONObject("frame");
                String fileName = arr.getJSONObject(i).getString("filename");

                int id = 0;
                Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, String> pair = it.next();

                    if (pair.getValue().equals(fileName)){
                        id = pair.getKey();
                        break;
                    }
                }
                texturesData.add(new TextureData(
                        id,
                        fileName,
                        (float)objFrame.getInt("x")/textureSize,
                        (float)objFrame.getInt("y")/textureSize,
                        (float)objFrame.getInt("w")/textureSize,
                        (float)objFrame.getInt("h")/textureSize
                ));
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
            Log.e(TAG, "Save data has a syntax error: ", ex);
            // Initializing with empty stars if the game file is corrupt.
            // NOTE: In your game, you want to try recovering from the snapshot payload.
            // TODO RECUPERAR DADOS DE SAHRED PREFERENCES
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Save data has an invalid number in it: ", ex);
        }
    }
}
