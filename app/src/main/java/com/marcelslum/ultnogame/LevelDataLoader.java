package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class LevelDataLoader {

    public static void initLevelsData(){

        LevelsGroupData.levelsGroupData = new ArrayList<>();

        int nl = 1;

        int levelsQuantity = 4;
        LevelsGroupData l = new LevelsGroupData("Início", nl, nl+levelsQuantity-1, 0, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 1);
        // ---------- LEVEL1
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL2
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL3
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        // ---------- LEVEL4
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;  // starst to unlock 7
        l = new LevelsGroupData("Obstáculos", nl, nl+levelsQuantity-1, 5, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 2);
        // ---------- LEVEL5
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 4);
        // ---------- LEVEL6
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 5);
        // ---------- LEVEL7
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 6);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 2;
        l = new LevelsGroupData("Cores", nl, nl+levelsQuantity-1, 10, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL8
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 7);
        // ---------- LEVEL9
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 8);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Explosão", nl, nl+levelsQuantity-1, 15, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL10
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 9);
        // ---------- LEVEL11
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 10);
        // ---------- LEVEL12
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 11);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
        l = new LevelsGroupData("Roda", nl, nl+levelsQuantity-1, 20, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL13
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 12);
        // ---------- LEVEL14
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 13);
        // ---------- LEVEL15
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 14);
        // ---------- LEVEL16
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 15);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Elástico", nl, nl+levelsQuantity-1, 20, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL17
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL18
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL19
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 2;
        l = new LevelsGroupData("Vento", nl, nl+levelsQuantity-1, 25, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL20
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL21
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Fantasma", nl, nl+levelsQuantity-1, 30, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL22
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL23
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL24
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 2;
        l = new LevelsGroupData("Invencibilidade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL25
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL26
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
    }

}