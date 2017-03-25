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

    public static final String TEXTURE_TARGET_BLACK_NAME = "alvo_black.png";
    public static final String TEXTURE_TARGET_BLUE_NAME = "alvo_blue.png";
    public static final String TEXTURE_TARGET_GREEN_NAME = "alvo_green.png";
    public static final String TEXTURE_TARGET_RED_NAME = "alvo_red.png";

    public static final String TEXTURE_ARROW_NAME = "arrow.png";

    public static final String TEXTURE_ARROW_DOWN_NAME = "arrow_down.png";
    public static final String TEXTURE_ARROW_LEFT_NAME = "arrow_left.png";
    public static final String TEXTURE_ARROW_RIGHT_NAME = "arrow_right.png";
    public static final String TEXTURE_ARROW_UP_NAME = "arrow_up.png";

    public static final String TEXTURE_BALL_BLACK_NAME = "ball_black.png";
    public static final String TEXTURE_BALL_BLUE_NAME = "ball_blue.png";
    public static final String TEXTURE_BALL_COLLISION_NAME = "ball_collision.png";
    public static final String TEXTURE_BALL_GREEN_NAME = "ball_green.png";
    public static final String TEXTURE_BALL_ORANGE_NAME = "ball_orange.png";
    public static final String TEXTURE_BALL_PINK_NAME = "ball_pink.png";
    public static final String TEXTURE_BALL_PURPLE_NAME = "ball_purple.png";
    public static final String TEXTURE_BALL_RED_NAME = "ball_red.png";
    public static final String TEXTURE_BALL_YELLOW_NAME = "ball_yellow.png";

    public static final String TEXTURE_BAR_BLACK_NAME = "bar_black.png";
    public static final String TEXTURE_BAR_BLUE_NAME = "bar_blue.png";
    public static final String TEXTURE_BAR_GREEN_NAME = "bar_green.png";
    public static final String TEXTURE_BAR_ORANGE_NAME = "bar_orange.png";
    public static final String TEXTURE_BAR_PINK_NAME = "bar_pink.png";
    public static final String TEXTURE_BAR_PURPLE_NAME = "bar_purple.png";
    public static final String TEXTURE_BAR_RED_NAME = "bar_red.png";
    public static final String TEXTURE_BAR_TOP_NAME = "bar_top.png";
    public static final String TEXTURE_BAR_YELLOW_NAME = "bar_yellow.png";

    public static final String TEXTURE_BE2_NAME = "be2.png";
    public static final String TEXTURE_BE3_NAME = "be3.png";
    public static final String TEXTURE_BE4_NAME = "be4.png";
    public static final String TEXTURE_BE5_NAME = "be5.png";
    public static final String TEXTURE_BE6_NAME = "be6.png";
    public static final String TEXTURE_BE7_NAME = "be7.png";
    public static final String TEXTURE_BE8_NAME = "be8.png";
    public static final String TEXTURE_BE9_NAME = "be9.png";
    public static final String TEXTURE_BE10_NAME = "be10.png";
    public static final String TEXTURE_BE11_NAME = "be11.png";
    public static final String TEXTURE_BE12_NAME = "be12.png";
    //todo falta be1

    public static final String TEXTURE_BUTTON_BAR_LEFT_NAME = "button_bar_left.png";
    public static final String TEXTURE_BUTTON_BAR_LEFT_PRESS_NAME = "button_bar_left_press.png";
    public static final String TEXTURE_BUTTON_BAR_RIGHT_NAME = "button_bar_right.png";
    public static final String TEXTURE_BUTTON_BAR_RIGHT_PRESS_NAME = "button_bar_right_press.png";

    public static final String TEXTURE_ARROW_DOWN_PRESS_NAME = "button_down_press.png";
    public static final String TEXTURE_ARROW_LEFT_PRESS_NAME = "button_left_press.png";
    public static final String TEXTURE_ARROW_RIGHT_PRESS_NAME = "button_right_press.png";
    public static final String TEXTURE_ARROW_UP_PRESS_NAME = "button_up_press.png";

    public static final String TEXTURE_EXPLOSION_BLUE_1_NAME = "explosion_blue_1.png";
    public static final String TEXTURE_EXPLOSION_BLUE_2_NAME = "explosion_blue_2.png";
    public static final String TEXTURE_EXPLOSION_BLUE_3_NAME = "explosion_blue_3.png";

    public static final String TEXTURE_EXPLOSION_RED_1_NAME = "explosion_red_1.png";
    public static final String TEXTURE_EXPLOSION_RED_2_NAME = "explosion_red_2.png";
    public static final String TEXTURE_EXPLOSION_RED_3_NAME = "explosion_red_3.png";

    public static final String TEXTURE_JEFT_SET_NAME = "jetset.png";

    public static final String TEXTURE_PANEL_BLUE_NAME = "panel_blue.png";
    public static final String TEXTURE_PANEL_INVENCIBLE_NAME = "panel_invencible.png";
    public static final String TEXTURE_PANEL_BLACK_NAME = "panel_black.png";

    public static final String TEXTURE_PARTICLE_BALL_NAME = "particle_ball.png";

    public static final String TEXTURE_POINT0_NAME = "point0.png";
    public static final String TEXTURE_POINT1_NAME = "point1.png";
    public static final String TEXTURE_POINT2_NAME = "point2.png";
    public static final String TEXTURE_POINT3_NAME = "point3.png";
    public static final String TEXTURE_POINT4_NAME = "point4.png";
    public static final String TEXTURE_POINT5_NAME = "point5.png";
    public static final String TEXTURE_POINT6_NAME = "point6.png";
    public static final String TEXTURE_POINT7_NAME = "point7.png";
    public static final String TEXTURE_POINT8_NAME = "point8.png";
    public static final String TEXTURE_POINT9_NAME = "point9.png";

    public static final String TEXTURE_POINT_PANEL0_NAME = "point_panel_0.png";
    public static final String TEXTURE_POINT_PANEL1_NAME = "point_panel_1.png";
    public static final String TEXTURE_POINT_PANEL2_NAME = "point_panel_2.png";
    public static final String TEXTURE_POINT_PANEL3_NAME = "point_panel_3.png";
    public static final String TEXTURE_POINT_PANEL4_NAME = "point_panel_4.png";
    public static final String TEXTURE_POINT_PANEL5_NAME = "point_panel_5.png";
    public static final String TEXTURE_POINT_PANEL6_NAME = "point_panel_6.png";
    public static final String TEXTURE_POINT_PANEL7_NAME = "point_panel_7.png";
    public static final String TEXTURE_POINT_PANEL8_NAME = "point_panel_8.png";
    public static final String TEXTURE_POINT_PANEL9_NAME = "point_panel_9.png";
    public static final String TEXTURE_STAR_OFF_NAME = "star_off.png";
    public static final String TEXTURE_STAR_SHINE_NAME = "star_shine.png";
    public static final String TEXTURE_TITTLE_NAME = "tittle.png";
    public static final String TEXTURE_WINDOW_NAME = "window.png";

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
        Log.e(TAG, id + " - "+ name + " = "+this.x+", "+this.y+", "+this.w+", "+this.h);
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

        JSONObject obj;

        try {
            obj = new JSONObject(Utils.readJSONFromAsset("texture_data.json"));
        } catch(JSONException e){
            return;
        }

        texturesData = new ArrayList<>();

        try {

            JSONArray arr = obj.getJSONArray("frames");

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
