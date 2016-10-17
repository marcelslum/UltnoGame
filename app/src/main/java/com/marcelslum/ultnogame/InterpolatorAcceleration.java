package com.marcelslum.ultnogame;


import android.view.animation.Interpolator;

/**
 * Created by marcel on 17/10/2016.
 */

public class InterpolatorAcceleration implements Interpolator{
        public InterpolatorAcceleration() {}
        public float getInterpolation(float t) {
            float x=2.0f*t-1.0f;
            return 0.5f*(x*x*x + 1.0f);
        }
}
