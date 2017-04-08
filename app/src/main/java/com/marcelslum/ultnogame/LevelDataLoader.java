package com.marcelslum.ultnogame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class LevelDataLoader {

    private static final String TAG = "LevelDataLoader";

    public static void initLevelsData(){

        LevelsGroupData.levelsGroupData = new ArrayList<>();

        int levelsIntroduced = 0;

        // introduz os grupos
        for (int i = 0; i < Game.groupsDataBaseData.size(); i++) {

            GroupDataBaseData d = Game.groupsDataBaseData.get(i);
            String groupName;
            TextureData td;

            switch (d.number){
                case 1:
                    groupName = Game.getContext().getResources().getString(R.string.group1name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G1_ID);
                    break;
                case 2:
                    groupName = Game.getContext().getResources().getString(R.string.group2name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G2_ID);
                    break;
                case 3:
                    groupName = Game.getContext().getResources().getString(R.string.group3name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G3_ID);
                    break;
                case 4:
                    groupName = Game.getContext().getResources().getString(R.string.group4name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G4_ID);
                    break;
                case 5:
                    groupName = Game.getContext().getResources().getString(R.string.group5name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G5_ID);
                    break;
                case 6:
                    groupName = Game.getContext().getResources().getString(R.string.group6name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G6_ID);
                    break;
                case 7:
                    groupName = Game.getContext().getResources().getString(R.string.group7name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G7_ID);
                    break;
                case 8:
                    groupName = Game.getContext().getResources().getString(R.string.group8name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G8_ID);
                    break;
                case 9:
                    groupName = Game.getContext().getResources().getString(R.string.group9name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G9_ID);
                    break;
                case 10:
                    groupName = Game.getContext().getResources().getString(R.string.group10name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G10_ID);
                    break;
                case 11:
                    groupName = Game.getContext().getResources().getString(R.string.group11name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G11_ID);
                    break;
                case 12:
                    groupName = Game.getContext().getResources().getString(R.string.group12name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G12_ID);
                    break;
                case 13:
                    groupName = Game.getContext().getResources().getString(R.string.group13name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G13_ID);
                    break;
                case 14:
                    groupName = Game.getContext().getResources().getString(R.string.group14name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G14_ID);
                    break;
                case 15:
                    groupName = Game.getContext().getResources().getString(R.string.group15name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G15_ID);
                    break;
                case 16:
                    groupName = Game.getContext().getResources().getString(R.string.group16name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G16_ID);
                    break;
                case 17:
                    groupName = Game.getContext().getResources().getString(R.string.group17name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G17_ID);
                    break;
                case 18:
                    groupName = Game.getContext().getResources().getString(R.string.group18name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G18_ID);
                    break;
                case 19:
                    groupName = Game.getContext().getResources().getString(R.string.group19name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G19_ID);
                    break;
                case 20:
                    groupName = Game.getContext().getResources().getString(R.string.group20name);
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G20_ID);
                    break;
                default:
                    groupName = "";
                    td = TextureData.getTextureDataById(TextureData.TEXTURE_G1_ID);
                    break;
            }

            LevelsGroupData.levelsGroupData.add(
                    new LevelsGroupData(groupName,
                            d.number,
                            levelsIntroduced + 1,
                            levelsIntroduced + d.levels,
                            d.stars_to_unlock,
                            LevelsGroupData.getLevelsConqueredStars(levelsIntroduced + 1, levelsIntroduced + d.levels),
                            Texture.TEXTURE_ICONS,
                            td
                            )
            );
            levelsIntroduced += d.levels;
        }





        // introduz os levels
        for (int i = 0; i < Game.levelsDataBaseData.size(); i++){

            LevelDataBaseData d = Game.levelsDataBaseData.get(i);

            LevelsGroupData g = LevelsGroupData.levelsGroupData.get(0);

            for (int m = 0; m < LevelsGroupData.levelsGroupData.size(); m++){
                if (LevelsGroupData.levelsGroupData.get(m).number == d.group){
                    g = LevelsGroupData.levelsGroupData.get(m);
                    break;
                }
            }

            int textureDataId;

            switch (d.number) {
                case 1: textureDataId = TextureData.TEXTURE_l1; break;
                case 2: textureDataId = TextureData.TEXTURE_l2; break;
                case 3: textureDataId = TextureData.TEXTURE_l3; break;
                case 4: textureDataId = TextureData.TEXTURE_l4; break;
                case 5: textureDataId = TextureData.TEXTURE_l5; break;
                case 6: textureDataId = TextureData.TEXTURE_l6; break;
                case 7: textureDataId = TextureData.TEXTURE_l7; break;
                case 8: textureDataId = TextureData.TEXTURE_l8; break;
                case 9: textureDataId = TextureData.TEXTURE_l9; break;
                case 10: textureDataId = TextureData.TEXTURE_l10; break;
                case 11: textureDataId = TextureData.TEXTURE_l11; break;
                case 12: textureDataId = TextureData.TEXTURE_l12; break;
                case 13: textureDataId = TextureData.TEXTURE_l13; break;
                case 14: textureDataId = TextureData.TEXTURE_l14; break;
                case 15: textureDataId = TextureData.TEXTURE_l15; break;
                case 16: textureDataId = TextureData.TEXTURE_l16; break;
                case 17: textureDataId = TextureData.TEXTURE_l17; break;
                case 18: textureDataId = TextureData.TEXTURE_l18; break;
                case 19: textureDataId = TextureData.TEXTURE_l19; break;
                case 20: textureDataId = TextureData.TEXTURE_l20; break;
                case 21: textureDataId = TextureData.TEXTURE_l21; break;
                case 22: textureDataId = TextureData.TEXTURE_l22; break;
                case 23: textureDataId = TextureData.TEXTURE_l23; break;
                case 24: textureDataId = TextureData.TEXTURE_l24; break;
                case 25: textureDataId = TextureData.TEXTURE_l25; break;
                case 26: textureDataId = TextureData.TEXTURE_l26; break;
                case 27: textureDataId = TextureData.TEXTURE_l27; break;
                case 28: textureDataId = TextureData.TEXTURE_l28; break;
                case 29: textureDataId = TextureData.TEXTURE_l29; break;
                case 30: textureDataId = TextureData.TEXTURE_l30; break;
                case 31: textureDataId = TextureData.TEXTURE_l31; break;
                case 32: textureDataId = TextureData.TEXTURE_l32; break;
                case 33: textureDataId = TextureData.TEXTURE_l33; break;
                case 34: textureDataId = TextureData.TEXTURE_l34; break;
                case 35: textureDataId = TextureData.TEXTURE_l35; break;
                case 36: textureDataId = TextureData.TEXTURE_l36; break;
                case 37: textureDataId = TextureData.TEXTURE_l37; break;
                case 38: textureDataId = TextureData.TEXTURE_l38; break;
                case 39: textureDataId = TextureData.TEXTURE_l39; break;
                case 40: textureDataId = TextureData.TEXTURE_l40; break;
                case 41: textureDataId = TextureData.TEXTURE_l41; break;
                case 42: textureDataId = TextureData.TEXTURE_l42; break;
                case 43: textureDataId = TextureData.TEXTURE_l43; break;
                case 44: textureDataId = TextureData.TEXTURE_l44; break;
                case 45: textureDataId = TextureData.TEXTURE_l45; break;
                case 46: textureDataId = TextureData.TEXTURE_l46; break;
                case 47: textureDataId = TextureData.TEXTURE_l47; break;
                case 48: textureDataId = TextureData.TEXTURE_l48; break;
                case 49: textureDataId = TextureData.TEXTURE_l49; break;
                case 50: textureDataId = TextureData.TEXTURE_l50; break;
                case 51: textureDataId = TextureData.TEXTURE_l51; break;
                case 52: textureDataId = TextureData.TEXTURE_l52; break;
                case 53: textureDataId = TextureData.TEXTURE_l53; break;
                case 54: textureDataId = TextureData.TEXTURE_l54; break;
                case 55: textureDataId = TextureData.TEXTURE_l55; break;
                case 56: textureDataId = TextureData.TEXTURE_l56; break;
                case 57: textureDataId = TextureData.TEXTURE_l57; break;
                case 58: textureDataId = TextureData.TEXTURE_l58; break;
                case 59: textureDataId = TextureData.TEXTURE_l59; break;
                case 60: textureDataId = TextureData.TEXTURE_l60; break;
                case 61: textureDataId = TextureData.TEXTURE_l61; break;
                case 62: textureDataId = TextureData.TEXTURE_l62; break;
                case 63: textureDataId = TextureData.TEXTURE_l63; break;
                case 64: textureDataId = TextureData.TEXTURE_l64; break;
                case 65: textureDataId = TextureData.TEXTURE_l65; break;
                case 66: textureDataId = TextureData.TEXTURE_l66; break;
                case 67: textureDataId = TextureData.TEXTURE_l67; break;
                case 68: textureDataId = TextureData.TEXTURE_l68; break;
                case 69: textureDataId = TextureData.TEXTURE_l69; break;
                case 70: textureDataId = TextureData.TEXTURE_l70; break;
                case 71: textureDataId = TextureData.TEXTURE_l71; break;
                case 72: textureDataId = TextureData.TEXTURE_l72; break;
                case 73: textureDataId = TextureData.TEXTURE_l73; break;
                case 74: textureDataId = TextureData.TEXTURE_l74; break;
                case 75: textureDataId = TextureData.TEXTURE_l75; break;
                case 76: textureDataId = TextureData.TEXTURE_l76; break;
                case 77: textureDataId = TextureData.TEXTURE_l77; break;
                case 78: textureDataId = TextureData.TEXTURE_l78; break;
                case 79: textureDataId = TextureData.TEXTURE_l79; break;
                case 80: textureDataId = TextureData.TEXTURE_l80; break;
                case 81: textureDataId = TextureData.TEXTURE_l81; break;
                case 82: textureDataId = TextureData.TEXTURE_l82; break;
                case 83: textureDataId = TextureData.TEXTURE_l83; break;
                case 84: textureDataId = TextureData.TEXTURE_l84; break;
                case 85: textureDataId = TextureData.TEXTURE_l85; break;
                case 86: textureDataId = TextureData.TEXTURE_l86; break;
                case 87: textureDataId = TextureData.TEXTURE_l87; break;
                case 88: textureDataId = TextureData.TEXTURE_l88; break;
                case 89: textureDataId = TextureData.TEXTURE_l89; break;
                case 90: textureDataId = TextureData.TEXTURE_l90; break;
                case 91: textureDataId = TextureData.TEXTURE_l91; break;
                case 92: textureDataId = TextureData.TEXTURE_l92; break;
                case 93: textureDataId = TextureData.TEXTURE_l93; break;
                case 94: textureDataId = TextureData.TEXTURE_l94; break;
                case 95: textureDataId = TextureData.TEXTURE_l95; break;
                case 96: textureDataId = TextureData.TEXTURE_l96; break;
                case 97: textureDataId = TextureData.TEXTURE_l97; break;
                case 98: textureDataId = TextureData.TEXTURE_l98; break;
                case 99: textureDataId = TextureData.TEXTURE_l99; break;
                case 100: textureDataId = TextureData.TEXTURE_l100; break;
                default: textureDataId = TextureData.TEXTURE_l1; break;
            }

            Log.e(TAG, " "+d.number + " "+textureDataId+ " ");

            if (d.number < 101) {
                g.addLevel(
                        Game.getContext().getResources().getString(R.string.messageCurrentLevel) + String.valueOf(d.number),
                        d.number,
                        Texture.TEXTURE_ICONS,
                        TextureData.getTextureDataById(textureDataId)
                );
            }
        }

    }
}
