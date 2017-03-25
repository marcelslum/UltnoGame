package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class LevelDataLoader {

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

            g.addLevel(
                Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(d.number),
                d.number,
                Texture.TEXTURE_ICONS,
                TextureData.getTextureDataById(TextureData.TEXTURE_G1_ID)
            );
        }

        /*
        //GRUPO 1
        int levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group1name), 1,  nl, nl+levelsQuantity-1, starsToUnblock[0], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL1
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL2
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL3
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL4
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 2
        levelsQuantity = 4;  // starst to unlock 7
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group2name), 2, nl, nl+levelsQuantity-1, starsToUnblock[1], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL5
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 4);
                // ---------- LEVEL6
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 5);
                // ---------- LEVEL7
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 6);
                // ---------- LEVEL8
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 6);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 3
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group3name), 3, nl, nl+levelsQuantity-1, starsToUnblock[2], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL9
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 7);
                // ---------- LEVEL10
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 8);
                // ---------- LEVEL11
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 8);
                // ---------- LEVEL12
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 8);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 4
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group4name), 4, nl, nl+levelsQuantity-1, starsToUnblock[3], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL13
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 9);
                // ---------- LEVEL14
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 10);
                // ---------- LEVEL15
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 11);
                // ---------- LEVEL16
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 11);
                LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 5
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group5name), 5, nl, nl+levelsQuantity-1, starsToUnblock[4], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL17
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 12);
                // ---------- LEVEL18
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 13);
                // ---------- LEVEL19
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 14);
                // ---------- LEVEL20
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 15);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 6
        levelsQuantity = 3;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group6name), 6, nl, nl+levelsQuantity-1, starsToUnblock[6], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL21
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL22
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL23
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 7
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group7name), 7, nl, nl+levelsQuantity-1, starsToUnblock[6], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL24
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL25
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL26
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL27
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        //GRUPO 8
        levelsQuantity = 3;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group8name), 8, nl, nl+levelsQuantity-1, starsToUnblock[7], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL28
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL29
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL30
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 9
        levelsQuantity = 3;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group9name), 9, nl, nl+levelsQuantity-1, starsToUnblock[8], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
                // ---------- LEVEL31
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
                // ---------- LEVEL32
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
                // ---------- LEVEL33
                nl += 1;
                l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 10
        levelsQuantity = 3;
            l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group10name), 10, nl, nl+levelsQuantity-1, starsToUnblock[9], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL34
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL35
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL36
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 11
        levelsQuantity = 4;
            l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group11name), 11, nl, nl+levelsQuantity-1, starsToUnblock[10], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL37
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL38
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL39
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL40
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 12
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group12name), 12, nl, nl+levelsQuantity-1, starsToUnblock[11], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL41
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL42
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL43
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL44
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 13
        levelsQuantity = 5;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group13name), 13, nl, nl+levelsQuantity-1, starsToUnblock[12], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL45
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL46
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL47
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL48
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL49
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 14
        levelsQuantity = 4;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group14name), 14, nl, nl+levelsQuantity-1, starsToUnblock[13], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL50
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL51
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL52
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL53
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 15
        levelsQuantity = 5;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group15name), 15, nl, nl+levelsQuantity-1, starsToUnblock[14], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL54
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL55
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL56
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL57
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL58
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        //GRUPO 16
        levelsQuantity = 6;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group16name), 16, nl, nl+levelsQuantity-1, starsToUnblock[15], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL59
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL60
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL61
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL62
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL63
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL64
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 17
        levelsQuantity = 6;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group17name), 17, nl, nl+levelsQuantity-1, starsToUnblock[16], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL65
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL66
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL67
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL68
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL69
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL70
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 18
        levelsQuantity = 8;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group18name), 18, nl, nl+levelsQuantity-1, starsToUnblock[17], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL71
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL72
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL73
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL74
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL75
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL76
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL77
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL78
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        
        //GRUPO 19
        levelsQuantity = 12;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group19name), 19, nl, nl+levelsQuantity-1, starsToUnblock[18], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL79
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL80
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL81
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL82
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL83
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL84
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL85
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL86
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL87
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL88
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL89
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL90
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
         //GRUPO 20
        levelsQuantity = 10;
        l = new LevelsGroupData(Game.getContext().getResources().getString(R.string.group20name), 20, nl, nl+levelsQuantity-1, starsToUnblock[19], LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_ICONS, 3);
            // ---------- LEVEL91
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 1);
            // ---------- LEVEL92
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL93
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL94
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL95
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL96
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL97
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL98
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL99
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
            // ---------- LEVEL100
            nl += 1;
            l.addLevel(Game.getContext().getResources().getString(R.string.messageCurrentLevel)+String.valueOf(nl), nl, Texture.TEXTURE_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        */

    }
}
