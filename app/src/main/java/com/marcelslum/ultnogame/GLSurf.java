package com.marcelslum.ultnogame;


import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class GLSurf extends GLSurfaceView {

    private MyConfigChooser mConfigChooser;
    private final GLRenderer mRenderer;
    private final static String TAG = "GLSurf";

    public GLSurf(Context context) {
        super(context);

        Log.e("GLSURF", "createGlSurf");

        mRenderer = new GLRenderer(context, this);

        if ( detectOpenGLES30()  && Game.isOpenGL30)
        {
            // Tell the surface view we want to create an OpenGL ES 3.0-compatible
            // context, and set an OpenGL ES 3.0-compatible renderer.
            setEGLContextClientVersion (3);
            setRenderer (mRenderer);
        }
        else {

            // Create an OpenGL ES 2.0 context.
            setEGLContextClientVersion(2);
            //setEGLConfigChooser(new MyConfigChooser());

            setEGLConfigChooser(8, 8, 8, 8, 0, 0);
            getHolder().setFormat(PixelFormat.RGBA_8888);

            // Set the Renderer for drawing on the GLSurfaceView
            setRenderer(mRenderer);

            // Render the view only when there is a change in the drawing data
            setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        }
    }

    private boolean detectOpenGLES30() {
        ActivityManager am =
                ( ActivityManager ) Game.mainActivity.getSystemService ( Context.ACTIVITY_SERVICE );
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return ( info.reqGlEsVersion >= 0x30000 );
    }

    @Override
    public void onPause() {
        Log.e("GLSURF", "onPause");
        super.onPause();
        mRenderer.onPause();
    }

    @Override
    public void onResume() {
        Log.e("GLSURF", "onResume");
        super.onResume();


        mRenderer.onResume();
    }

    public void onCloseAd(){
        queueEvent(new Runnable() {
            // This method will be called on the rendering
            // thread:
            public void run() {
                Game.returnFromAd();
            }});
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Game.touchEvents == null) Game.touchEvents = new ArrayList<>();

        float screenOffSetX = mRenderer.screenOffSetX;
        float screenOffSetY = mRenderer.screenOffSetY;

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();
        int pointerIndex;
        int pointerId;


        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                pointerIndex = event.getActionIndex();
                pointerId = event.getPointerId(pointerIndex);
                //Log.e(TAG, "pointer index "+pointerIndex + "; pointerId "+pointerId);
                //Log.e("GLSurf", "ACTION_DOWN "+ pointerId);

                Game.touchEvents.add(new TouchEvent(pointerId, event.getX(pointerIndex)-screenOffSetX,event.getY(pointerIndex)-screenOffSetY));
                break;

            case MotionEvent.ACTION_MOVE: // a pointer was moved

                for (int i = 0; i < Game.touchEvents.size();i++) {

                    if (Game.touchEvents.get(i).type != TouchEvent.TOUCH_TYPE_UP) {

                        pointerIndex = event.findPointerIndex(Game.touchEvents.get(i).id);

                        float x = event.getX(pointerIndex) - screenOffSetX;
                        float y = event.getY(pointerIndex) - screenOffSetY;

                        //Log.e(TAG, "Verificando o touch de "+"pointer index "+pointerIndex + "; pointerId "+Game.touchEvents.get(i).id);

                        if (Game.touchEvents.get(i).x != x || Game.touchEvents.get(i).y != y) {
                            Game.touchEvents.get(i).move(event.getX(pointerIndex) - screenOffSetX, event.getY(pointerIndex) - screenOffSetY);
                            //Log.e(TAG, "identificado movimento");
                        }// else {
                        //    Log.e(TAG, "não identificado movimento");
                        //}
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                pointerIndex = event.getActionIndex();
                pointerId = event.getPointerId(pointerIndex);

                //Log.e(TAG, "pointer index "+pointerIndex + "; pointerId "+pointerId);
                //Log.e("GLSurf", "action pointer up"+ pointerId);

                for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
                    if (Game.touchEvents.get(i2).id == event.getPointerId(pointerIndex)) {
                        Game.touchEvents.get(i2).setType(TouchEvent.TOUCH_TYPE_UP);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:

                pointerIndex = event.getActionIndex();
                pointerId = event.getPointerId(pointerIndex);

                //Log.e(TAG, "pointer index "+pointerIndex + "; pointerId "+pointerId);
                //Log.e("GLSurf", "action up"+ pointerId);

                for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
                    if (Game.touchEvents.get(i2).id == event.getPointerId(pointerIndex)) {
                        Game.touchEvents.get(i2).setType(TouchEvent.TOUCH_TYPE_UP);
                    }
                }
                break;


            case MotionEvent.ACTION_CANCEL:
                pointerIndex = event.getActionIndex();
                pointerId = event.getPointerId(pointerIndex);

                //Log.e(TAG, "pointer index "+pointerIndex + "; pointerId "+pointerId);
                //Log.e("GLSurf", "ACTION_CANCEL "+ event.getPointerId(pointerIndex));

                Game.touchEvents.clear();
                break;
            }
        return true;
    }
}
