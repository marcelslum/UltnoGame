package com.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 26/01/2017.
 */

public class LevelDataLoader {

    public static void initLevelsData(){

        LevelsGroupData.levelsGroupData = new ArrayList<>();

        int nl = 1;

        //GRUPO 1
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

        //GRUPO 2
        levelsQuantity = 4;  // starst to unlock 7
        l = new LevelsGroupData("Obstáculo", nl, nl+levelsQuantity-1, 12, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 2);
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

        //GRUPO 3
        levelsQuantity = 4;
        l = new LevelsGroupData("Cor", nl, nl+levelsQuantity-1, 26, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
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

        //GRUPO 4
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

        //GRUPO 5
        levelsQuantity = 4;
        l = new LevelsGroupData("Deslizamento", nl, nl+levelsQuantity-1, 20, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
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

        //GRUPO 6
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

        //GRUPO 7
        levelsQuantity = 2;
        l = new LevelsGroupData("Vento", nl, nl+levelsQuantity-1, 25, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL24
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL25
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL26
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL27
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        //GRUPO 8
        levelsQuantity = 3;
        l = new LevelsGroupData("Fantasma", nl, nl+levelsQuantity-1, 30, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL28
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL29
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL30
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 3);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 9
        levelsQuantity = 3;
        l = new LevelsGroupData("Invencibilidade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
                // ---------- LEVEL31
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
                // ---------- LEVEL32
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
                // ---------- LEVEL33
                nl += 1;
                l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 10
        levelsQuantity = 3;
            l = new LevelsGroupData("Prisão", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL34
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL35
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL36
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 11
        levelsQuantity = 4;
            l = new LevelsGroupData("Encolhimento", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL37
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL38
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL39
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL40
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 12
        levelsQuantity = 4;
        l = new LevelsGroupData("Comida", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL41
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL42
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL43
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL44
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 13
        levelsQuantity = 5;
        l = new LevelsGroupData("Falsidade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL45
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL46
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL47
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL48
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL49
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 14
        levelsQuantity = 4;
        l = new LevelsGroupData("Espelho", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL50
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL51
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL52
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL53
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 15
        levelsQuantity = 5;
        l = new LevelsGroupData("Muro", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL54
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL55
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL56
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL57
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL58
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        //GRUPO 16
        levelsQuantity = 6;
        l = new LevelsGroupData("Divisão", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL59
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL60
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL61
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL62
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL63
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL64
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 17
        levelsQuantity = 6;
        l = new LevelsGroupData("Liberdade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL65
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL66
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL67
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL68
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL69
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL70
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

        //GRUPO 18
        levelsQuantity = 8;
        l = new LevelsGroupData("Grade", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL71
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL72
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL73
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL74
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL75
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL76
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL77
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL78
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
        
        //GRUPO 19
        levelsQuantity = 12;
        l = new LevelsGroupData("Rapidez", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL79
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL80
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL81
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL82
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL83
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL84
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL85
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL86
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL87
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL88
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL89
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL90
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);
        
         //GRUPO 20
        levelsQuantity = 12;
        l = new LevelsGroupData("Loucura", nl, nl+levelsQuantity-1, 35, LevelsGroupData.getLevelsConqueredStars(nl, nl+levelsQuantity-1), Texture.TEXTURE_GROUP_ICONS, 3);
            // ---------- LEVEL91
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 1);
            // ---------- LEVEL92
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL93
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL94
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL95
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL96
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL97
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL98
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL99
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
            // ---------- LEVEL100
            nl += 1;
            l.addLevel("Nível "+String.valueOf(nl), nl, Texture.TEXTURE_LEVEL_ICONS, 2);
        LevelsGroupData.levelsGroupData.add(l);

    }
}
