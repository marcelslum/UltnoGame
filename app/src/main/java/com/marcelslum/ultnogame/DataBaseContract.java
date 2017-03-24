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
    
    
}
