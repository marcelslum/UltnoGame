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
    }

    public static class DataLevels implements BaseColumns {
        public static final String TABLE_NAME = "levels";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_STARS = "stars";
        public static final String COLUMN_UNLOCKED = "unlocked";
        public static final String COLUMN_SEEN = "seen";
    }

    public static class DataTutorials implements BaseColumns {
        public static final String TABLE_NAME = "tutorials";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_POINTS = "seen";
    }
}
