package com.marcelslum.ultnogame;

import android.provider.BaseColumns;

public final class DataBaseContract {
    private DataBaseContract() {
    }

    public static class Balls implements BaseColumns {
        public static final String TABLE_NAME = "balls";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_RADIUS = "radius";
        public static final String COLUMN_X = "x";
        public static final String COLUMN_Y = "y";
        public static final String COLUMN_VX = "vx";
        public static final String COLUMN_VY = "vy";
        public static final String COLUMN_TEXTURE_MAP = "textureMap";
        public static final String COLUMN_INVENCIBLE = "invencible";
        public static final String COLUMN_ANGLE_TO_ROTATE = "angleToRotate";
        public static final String COLUMN_MAX_AGLE = "maxAngle";
        public static final String COLUMN_MIN_ANGLE = "minAngle";
        public static final String COLUMN_VELOCITY_VARIATION = "velocityVariation";
        public static final String COLUMN_MAX_VELOCITY = "maxVelocity";
        public static final String COLUMN_MIN_VELOCITY = "minVelocity";
        public static final String COLUMN_FREE = "free";
    }
    
    public static class Bars implements BaseColumns {
        public static final String TABLE_NAME = "bars";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_WIDTH = "width";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_X = "x";
        public static final String COLUMN_Y = "y";
        public static final String COLUMN_VX = "vx";
    }
    
    public static class Targets implements BaseColumns {
        public static final String TABLE_NAME = "targets";
        public static final String COLUMN_WIDTH = "width";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_PADD = "padd";
    }

    public static class Groups implements BaseColumns {
        public static final String TABLE_NAME = "groups";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_LEVELS = "levels";
        public static final String COLUMN_STARS_TO_UNLOCK = "starstounlock";
    }

    public static class Levels implements BaseColumns {
        public static final String TABLE_NAME = "levels";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_GROUP = "lgroup";
        public static final String COLUMN_MIN_BALLS_ALIVE = "minballsalive";
    }

    public static class Data implements BaseColumns {
        public static final String TABLE_NAME = "data";
        public static final String COLUMN_PLAYER_ID = "playerId";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_CURRENT_LEVEL = "current_level";
        public static final String COLUMN_CURRENT_GROUP = "current_group";
        public static final String COLUMN_MUSIC = "music";
        public static final String COLUMN_SOUND = "sound";
        public static final String COLUMN_VIBRATION = "vibration";
        public static final String COLUMN_GROUP_MENU_TRANSLATE_X = "group_menu_translate_x";
        public static final String COLUMN_LEVEL_MENU_TRANSLATE_X = "level_menu_translate_x";
        public static final String COLUMN_TUTORIAL_MENU_TRANSLATE_X = "tutorial_menu_translate_x";
        public static final String COLUMN_LAST_STARS = "last_stars";
        public static final String COLUMN_NEW_GROUPS_SEEN = "new_groups_seen";
        public static final String COLUMN_LEVELS_PLAYED = "levels_played";
        public static final String COLUMN_GOOGLE_OPTION = "googleOption";
        public static final String COLUMN_BALL_VELOCITY = "ballVelocity";
        public static final String COLUMN_ORIENTATION_INVERTED = "orientation_inverted";
        public static final String COLUMN_SAVE_MENU_SEEN = "save_menu_seen";
        public static final String COLUMN_LAST_LEVEL_PLAYED = "last_level_played";
        public static final String COLUMN_EXTRA_INT_1 = "extra_int_1";
        public static final String COLUMN_EXTRA_INT_2 = "extra_int_2";
        public static final String COLUMN_EXTRA_INT_3 = "extra_int_3";
        public static final String COLUMN_EXTRA_INT_4 = "extra_int_4";
        public static final String COLUMN_EXTRA_INT_5 = "extra_int_5";
        public static final String COLUMN_EXTRA_INT_6 = "extra_int_6";
        public static final String COLUMN_EXTRA_INT_7 = "extra_int_7";
        public static final String COLUMN_EXTRA_INT_8 = "extra_int_8";
        public static final String COLUMN_EXTRA_INT_9 = "extra_int_9";
        public static final String COLUMN_EXTRA_INT_10 = "extra_int_10";
        public static final String COLUMN_EXTRA_TEXT_1 = "extra_text_1";
        public static final String COLUMN_EXTRA_TEXT_2 = "extra_text_2";
        public static final String COLUMN_EXTRA_TEXT_3 = "extra_text_3";
        public static final String COLUMN_EXTRA_TEXT_4 = "extra_text_4";
        public static final String COLUMN_EXTRA_TEXT_5 = "extra_text_5";
    }

    public static class DataStats implements BaseColumns {
        public static final String TABLE_NAME = "stats";
        public static final String COLUMN_PLAYER_ID = "playerId";
        public static final String STAT0 = "stat0";
        public static final String STAT1 = "stat1";
        public static final String STAT2 = "stat2";
        public static final String STAT3 = "stat3";
        public static final String STAT4 = "stat4";
        public static final String STAT5 = "stat5";
        public static final String STAT6 = "stat6";
        public static final String STAT7 = "stat7";
        public static final String STAT8 = "stat8";
        public static final String STAT9 = "stat9";
        public static final String STAT10 = "stat10";
        public static final String STAT11 = "stat11";
        public static final String STAT12 = "stat12";
        public static final String STAT13 = "stat13";
        public static final String STAT14 = "stat14";
        public static final String STAT15 = "stat15";
        public static final String STAT16 = "stat16";
        public static final String STAT17 = "stat17";
        public static final String STAT18 = "stat18";
        public static final String STAT19 = "stat19";
        public static final String STAT20 = "stat20";
        public static final String STAT21 = "stat21";
        public static final String STAT22 = "stat22";
        public static final String STAT23 = "stat23";
        public static final String STAT24 = "stat24";
        public static final String STAT25 = "stat25";
        public static final String STAT26 = "stat26";
        public static final String STAT27 = "stat27";
        public static final String STAT28 = "stat28";
        public static final String STAT29 = "stat29";
        public static final String STAT30 = "stat30";
        public static final String STAT31 = "stat31";
        public static final String STAT32 = "stat32";
        public static final String STAT33 = "stat33";
        public static final String STAT34 = "stat34";
        public static final String STAT35 = "stat35";
        public static final String STAT36 = "stat36";
        public static final String STAT37 = "stat37";
        public static final String STAT38 = "stat38";
        public static final String STAT39 = "stat39";
        public static final String STAT40 = "stat40";
        public static final String STAT41 = "stat41";
        public static final String STAT42 = "stat42";
        public static final String STAT43 = "stat43";
        public static final String STAT44 = "stat44";
        public static final String STAT45 = "stat45";
        public static final String STAT46 = "stat46";
        public static final String STAT47 = "stat47";
        public static final String STAT48 = "stat48";
        public static final String STAT49 = "stat49";
        public static final String STAT50 = "stat50";
        public static final String STAT51 = "stat51";
        public static final String STAT52 = "stat52";
        public static final String STAT53 = "stat53";
        public static final String STAT54 = "stat54";
        public static final String STAT55 = "stat55";
        public static final String STAT56 = "stat56";
        public static final String STAT57 = "stat57";
        public static final String STAT58 = "stat58";
        public static final String STAT59 = "stat59";
        public static final String STAT60 = "stat60";
    }

    public static class DataLevels implements BaseColumns {
        public static final String TABLE_NAME = "levels";
        public static final String COLUMN_PLAYER_ID = "playerId";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_STARS = "stars";
        public static final String COLUMN_UNLOCKED = "unlocked";
        public static final String COLUMN_SEEN = "seen";
    }

    public static class DataTutorials implements BaseColumns {
        public static final String TABLE_NAME = "tutorials";
        public static final String COLUMN_PLAYER_ID = "playerId";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_SEEN = "seen";
    }
    
    public static class DataGroups implements BaseColumns { // TODO criar no banco de dados
        public static final String TABLE_NAME = "groups";
        public static final String COLUMN_PLAYER_ID = "playerId";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_SEEN = "seen";
    }

    public static class DbVersion implements BaseColumns {
        public static final String TABLE_NAME = "dbVersion";
        public static final String VERSION = "version_id";
        public static final String IS_NEW = "isNew";
    }


  
}
