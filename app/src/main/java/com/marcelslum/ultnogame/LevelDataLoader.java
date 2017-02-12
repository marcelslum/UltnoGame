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

        levelsQuantity = 4;  // starst to unlock 7
        l = new LevelsGroupData("Obstáculo", nl, nl+levelsQuantity-1, 5, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 2);
                // ---------- LEVEL5
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 4);
                // ---------- LEVEL6
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 5);
                // ---------- LEVEL7
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 6);
                // ---------- LEVEL8
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 6);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
        l = new LevelsGroupData("Cor", nl, nl+levelsQuantity-1, 5, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL9
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 7);
                // ---------- LEVEL10
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 8);
                // ---------- LEVEL11
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 8);
                // ---------- LEVEL12
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 8);
        LevelsGroupData.levelsGroupData.add(l);

        // EXPLOSÃO
        levelsQuantity = 4;
        l = new LevelsGroupData("Explosão", nl, nl+levelsQuantity-1, 5, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL13
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 9);
                // ---------- LEVEL14
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 10);
                // ---------- LEVEL15
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 11);
                // ---------- LEVEL16
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 11);
                LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
        l = new LevelsGroupData("Roda", nl, nl+levelsQuantity-1, 20, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL17
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 12);
                // ---------- LEVEL18
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 13);
                // ---------- LEVEL19
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 14);
                // ---------- LEVEL20
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 15);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Elástico", nl, nl+levelsQuantity-1, 20, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL21
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL22
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL23
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 2;
        l = new LevelsGroupData("Vento", nl, nl+levelsQuantity-1, 25, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL24
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL25
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Fantasma", nl, nl+levelsQuantity-1, 30, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL26
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL27
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL28
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
        l = new LevelsGroupData("Invencibilidade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL29
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL30
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL31
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 3;
            l = new LevelsGroupData("Prisão", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL32
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL33
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL34
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
            l = new LevelsGroupData("Encolhimento", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL35
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL36
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL37
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL38
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
        l = new LevelsGroupData("Comida", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL39
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL40
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL41
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL42
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        levelsQuantity = 4;
        l = new LevelsGroupData("Muro", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
        // ---------- LEVEL43
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
        // ---------- LEVEL44
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL45
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        // ---------- LEVEL46
        nl += 1;
        l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);



    }

}
