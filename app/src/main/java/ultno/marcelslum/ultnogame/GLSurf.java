package ultno.marcelslum.ultnogame;


import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class GLSurf extends GLSurfaceView {

    private MultisampleConfigChooser mConfigChooser;
    private final GLRenderer mRenderer;

    public GLSurf(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //setEGLConfigChooser(mConfigChooser = new MultisampleConfigChooser());


        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        getHolder().setFormat(PixelFormat.RGBA_8888);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new GLRenderer(context);
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mRenderer.onPause();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mRenderer.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (Game.touchEvents == null) Game.touchEvents = new ArrayList<>();

        float screenOffSetX = mRenderer.screenOffSetX;
        float screenOffSetY = mRenderer.screenOffSetY;

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //Log.e("GLSurf", "ACTION_DOWN "+ pointerId);
                // We have a new pointer. Lets add it to the list of pointers

                Game.touchEvents.add(new TouchEvent(pointerId, event.getX()-screenOffSetX,event.getY()-screenOffSetY));
                break;

            case MotionEvent.ACTION_MOVE: // a pointer was moved

                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {



                        if (Game.touchEvents.get(i2).id == event.getPointerId(i)) {

                            //Log.e("GLSurf", "ACTION_MOVE "+ event.getPointerId(i));


                            Game.touchEvents.get(i2).x = event.getX(i)-screenOffSetX;
                            Game.touchEvents.get(i2).y = event.getY(i)-screenOffSetY;
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                //Log.e("GLSurf", "action up"+ event.getPointerId(pointerId));

                for (int i2 = 0; i2 < Game.touchEvents.size();i2++) {
                    if (Game.touchEvents.get(i2).id == event.getPointerId(pointerId)) {
                        Game.touchEvents.remove(i2);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //Log.e("GLSurf", "action up"+ event.getPointerId(pointerId));

                Game.touchEvents.clear();
                break;
            }
        return true;
    }

}

