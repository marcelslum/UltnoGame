package ultno.marcelslum.ultnogame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRenderer implements GLSurfaceView.Renderer {

    public Game gi;
    int[] texturenames;

    // Our matrices
    private final float[] matrixProjection = new float[16];
    private final float[] matrixView = new float[16];
    private final float[] matrixProjectionAndView = new float[16];

    // Geometric variables
    public static float vertices[];
    public static short indices[];
    public static float uvs[];
    public FloatBuffer vertexBuffer;
    public ShortBuffer drawListBuffer;
    public FloatBuffer uvBuffer;
    public Sprite sprite;
    public TextManager tm;


    public Point testPoint;
    // Our screenresolution
    float	mScreenWidth;
    float	mScreenHeight;

    float	effectiveScreenWidth;
    float	effectiveScreenHeight;

    float 	ssu = 1.0f;
    float 	ssx = 1.0f;
    float 	ssy = 1.0f;
    float 	swp = 320.0f;
    float 	shp = 480.0f;

    float testeValue = 10f;

    public Program imageProgram;
    public Program textProgram;
    public Program solidProgram;

    public float fps = 30f;
    public float frameDuration = 1000f/fps;

    // Misc
    Context mContext;
    long mLastTime;
    int mProgram;
    public float screenOffSetX;
    public float screenOffSetY;


    public GLRenderer(Context c)
    {
        mContext = c;
        mLastTime = System.currentTimeMillis() + 100;
        this.gi = Game.getInstance();



    }

    public void onPause()
    {
		/* Do stuff to pause the renderer */
    }

    public void onResume()
    {
		/* Do stuff to resume the renderer */
        mLastTime = System.currentTimeMillis();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        Log.e("GlRenderer", "criando superfície--------------------------");

        // Set the clear color to black
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1);



        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        this.gi.soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(5).build();




        this.gi.soundBallHit = this.gi.soundPool.load(mContext, R.raw.ballhit, 1);
        this.gi.soundCounter = this.gi.soundPool.load(mContext, R.raw.counter, 1);
        this.gi.soundDestroyTarget = this.gi.soundPool.load(mContext, R.raw.destroytarget, 1);
        this.gi.soundMusic = this.gi.soundPool.load(mContext, R.raw.music, 1);
        this.gi.soundScore = this.gi.soundPool.load(mContext, R.raw.score, 1);



        // Setup our scaling system
        SetupScaling();



        GLES20.glEnable(GLES20.GL_BLEND);
        //GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        this.gi.imageProgram = new Program(GraphicTools.vs_Image, GraphicTools.fs_Image);
        this.gi.textProgram = new Program(GraphicTools.vs_Text, GraphicTools.fs_Text);
        this.gi.solidProgram = new Program(GraphicTools.vs_SolidColor, GraphicTools.fs_SolidColor);

        //GLES20.glUseProgram(imageProgram.get());
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        // We need to know the current width and height.
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, (int)mScreenWidth, (int)mScreenHeight);



        effectiveScreenWidth = (int)mScreenWidth;
        effectiveScreenHeight = (int)mScreenHeight;

        this.screenOffSetX = 0;
        this.screenOffSetY = 0;

        if (mScreenWidth/mScreenHeight > 1.5){
            effectiveScreenWidth = (effectiveScreenHeight/2)*3;
            screenOffSetX = (mScreenWidth - effectiveScreenWidth)/2;
        } else if (mScreenWidth/mScreenHeight < 1.5){
            effectiveScreenHeight = (effectiveScreenWidth/3)*2;
            screenOffSetY = (mScreenHeight - effectiveScreenHeight)/2;
        }

        Log.e("screenOffSetX ", " "+screenOffSetX);
        Log.e("screenOffSetY ", " "+screenOffSetY);

        Log.e("effectiveScreenHeight ", " "+effectiveScreenHeight);
        Log.e("effectiveScreenWidth ", " "+effectiveScreenWidth);

        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            matrixProjection[i] = 0.0f;
            matrixView[i] = 0.0f;
            matrixProjectionAndView[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM(matrixProjection, 0, 0f, mScreenWidth, mScreenHeight, 0.0f, 0.1f, 50f);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(matrixView, 0, -screenOffSetX, 0f, 1f, -screenOffSetX, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(matrixProjectionAndView, 0, matrixProjection, 0, matrixView, 0);

        // Setup our scaling system
        SetupScaling();

        Font font = new Font();

        this.gi.addText(new Text("titulo", this.gi, 0f, 0f, 300f, "ULTNO", font));
        this.gi.texts.get(0).program = this.textProgram;
        this.gi.texts.get(0).textureUnit = 1;

        this.gi.gameAreaResolutionX = this.effectiveScreenWidth;
        this.gi.gameAreaResolutionY = this.effectiveScreenHeight * 0.85f;

        this.gi.resolutionX = this.effectiveScreenWidth;
        this.gi.resolutionY = this.effectiveScreenHeight;

        SetupTextures();

        this.gi.createEntities();

    }



    @Override
    public void onDrawFrame(GL10 unused) {

        // Get the current time
        long now = System.currentTimeMillis();

        // We should make sure we are valid and sane
        if (mLastTime > now) return;

        // Get the amount of time the last frame took.
        long elapsed = now - mLastTime;

        for (int i = 0; i < this.gi.interactionListeners.size(); i++){
            this.gi.interactionListeners.get(i).verify();
        }

        //GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        //GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        //Log.e("gl renderer", "onDrawFrame2");
        //Log.e("glr", "vx "+this.gi.balls.get(0).vx+" vy "+ this.gi.balls.get(0).vy);


        for (int i = 0; i < this.gi.balls.size(); i++){
            Ball ball = this.gi.balls.get(i);

            // TODO this.balls[i].verifyAcceleration();

            ball.clearCollisionData();
            ball.vx = (ball.dvx * (float)elapsed)/frameDuration;
            ball.vy = (ball.dvy * (float)elapsed)/frameDuration;

            ball.translate(ball.vx, ball.vy, true);

            //Log.e("glrenderer", "ball.vx "+ball.vx+ " ball.vy "+ball.vy);

            this.gi.quad.insert(ball);
        }


        if (this.gi.bars != null){
            if (this.gi.bars.size() > 0){
                if (this.gi.button1Left.isPressed){
                    this.gi.bars.get(0).vx = -(this.gi.bars.get(0).dvx * (float)elapsed)/frameDuration;
                } else if (this.gi.button1Right.isPressed){
                    this.gi.bars.get(0).vx = (this.gi.bars.get(0).dvx * (float)elapsed)/frameDuration;
                } else {
                    this.gi.bars.get(0).vx = 0f;
                }

                this.gi.bars.get(0).translate(this.gi.bars.get(0).vx, 0, true);

                if (this.gi.bars.size() == 2){
                    if (this.gi.button2Left.isPressed){
                        this.gi.bars.get(1).vx = -(this.gi.bars.get(1).dvx * (float)elapsed)/frameDuration;
                    } else if (this.gi.button2Right.isPressed){
                        this.gi.bars.get(1).vx = (this.gi.bars.get(1).dvx * (float)elapsed)/frameDuration;
                    } else {
                        this.gi.bars.get(1).vx = 0f;
                    }
                    this.gi.bars.get(0).translate(this.gi.bars.get(0).vx, 0, true);
                }
            }
        }


        this.gi.quad.insert(this.gi.bordaE);
        this.gi.quad.insert(this.gi.bordaD);
        this.gi.quad.insert(this.gi.bordaC);
        this.gi.quad.insert(this.gi.bordaB);

        for (int i = 0; i < this.gi.targets.size(); i++){
            this.gi.quad.insert(this.gi.targets.get(i));
        }

        for (int i = 0; i < this.gi.bars.size(); i++){
            this.gi.quad.insert(this.gi.bars.get(i));
        }

        //Log.e("gl renderer", "onDrawFrame4");

        boolean isHaveCollision;
        isHaveCollision = this.gi.checkCollision(this.gi.balls, true, true);
        isHaveCollision = this.gi.checkCollision(this.gi.bars, true, true);

        this.gi.quad.clear();

        //Log.e("pos bola sat", "x "+this.gi.balls.get(0).circleData.pos.x+ " y "+this.gi.balls.get(0).circleData.pos.y+ "radium "+ this.gi.balls.get(0).circleData.r);

        if (isHaveCollision) {
            //Log.e("have collision", " colisão ----------------- "+"  response x"+ this.gi.balls.get(0).lastCollisionResponse.get(0).x+"  response y"+ this.gi.balls.get(0).lastCollisionResponse.get(0).y);
            //Log.e("bola", "  "+ this.gi.balls.get(0).y + (this.gi.balls.get(0).radium*2));
            //Log.e("bola", "  "+ this.gi.bordaB.y);
            //Log.e("bola", "  response x"+ this.gi.balls.get(0).lastCollisionResponse.get(0).x);
            //Log.e("bola", "  response y"+ this.gi.balls.get(0).lastCollisionResponse.get(0).y);
        } else {
            //Log.e("have collision", " não ");
        }

        //Log.e("gl renderer", "onDrawFrame5");

        for (int i = 0; i < this.gi.balls.size(); i++){

            if (this.gi.balls.get(i).isCollided){
                this.gi.balls.get(i).onCollision();
            }
        }

        //Log.e("gl renderer", "onDrawFrame6");
        //this.gi.balls.get(0).translate(1f,1f);

        //Log.e("render ball", " ");

        this.gi.background.move(1);
        this.gi.background.render(matrixView, matrixProjection);

        for (int i = 0; i < this.gi.balls.size(); i++){
            this.gi.balls.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < this.gi.targets.size(); i++){
            this.gi.targets.get(i).render(matrixView, matrixProjection);
        }

        for (int i = 0; i < this.gi.bars.size(); i++){
            this.gi.bars.get(i).render(matrixView, matrixProjection);
        }


        // Render our example
        //Render(matrixProjectionAndView);

        // Render the text
        //Log.e("render text", " ");

        //this.gi.texts.get(0).scale(0.05f,0.08f);
        //this.gi.texts.get(0).rotate(0.1f);
        //this.gi.texts.get(0).translate(-0.1f, -0.2f);
        //this.gi.texts.get(0).render(matrixView, matrixProjection);

        this.gi.bordaE.render(matrixView, matrixProjection);
        this.gi.bordaD.render(matrixView, matrixProjection);
        this.gi.bordaC.render(matrixView, matrixProjection);
        this.gi.bordaB.render(matrixView, matrixProjection);

        this.gi.button1Left.render(matrixView, matrixProjection);
        this.gi.button1Right.render(matrixView, matrixProjection);



        //Log.e("render test", "1 ");
        for (int i = 0; i < this.gi.targets.size(); i++){

            //Log.e("render test", "2 "+this.gi.targets.get(i).showPointsState);
            if (this.gi.targets.get(i).showPointsState == Entity.SHOW_POINTS_ON){
                this.gi.targets.get(i).renderPoints(matrixView, matrixProjection);
                //Log.e("render test", "2 ");
            }
        }

        this.gi.scorePanel.render(matrixView, matrixProjection);

        //Log.e("render test", "3 ");

        // Save the current time to see how long it took :).
        mLastTime = now;
    }

    public void SetupScaling()
    {
        // The screen resolutions
        swp = (int) (mContext.getResources().getDisplayMetrics().widthPixels);
        Log.e("swp", " "+swp);

        shp = (int) (mContext.getResources().getDisplayMetrics().heightPixels);
        Log.e("shp", " "+shp);

        // Orientation is assumed portrait
        ssx = swp / 480.0f;
        ssy = shp / 320.0f;

        // Get our uniform scaler
        if(ssx > ssy)
            ssu = ssy;
        else
            ssu = ssx;
        Log.e("ssu", " "+ssu);
    }


    public void SetupTextures()
    {

        // Generate Textures, if more needed, alter these numbers.

        //////Log.e("setup textures ", "1");

        texturenames = new int[7];
        GLES20.glGenTextures(7, texturenames, 0);

        //Log.e("setup textures ", "1");

        // Retrieve our image from resources.


        // TEXTURA 00: BOLA
        int id = mContext.getResources().getIdentifier("drawable/texturas4", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");

        // Temporary create a bitmap
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), id);

        //Log.e("setup textures ", "1");

        // Bind texture to texturename
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[0]);

        //Log.e("setup textures ", "1");

        // Set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);

        //Log.e("setup textures ", "1");

        // We are done using the bitmap so we should recycle it.
        bmp.recycle();

        //Log.e("setup textures ", "1");


        // TEXTURA 01 : TEXTO
        id = mContext.getResources().getIdentifier("drawable/jetset", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 1);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[1]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();



        // TEXTURA 02 : ALVOS
        id = mContext.getResources().getIdentifier("drawable/alvos", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 2);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[2]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();

        // TEXTURA 03 : BARRA
        id = mContext.getResources().getIdentifier("drawable/barras", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 3);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[3]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();

        // TEXTURA 04 : BOTÕES
        id = mContext.getResources().getIdentifier("drawable/botoes", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 4);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[4]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();

        // TEXTURA 05 : BACKGROUND
        id = mContext.getResources().getIdentifier("drawable/background3", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 5);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[5]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();

        // TEXTURA 06 : NUMEROS
        id = mContext.getResources().getIdentifier("drawable/numeros256", null, mContext.getPackageName());

        //Log.e("setup textures ", "1");
        bmp = BitmapFactory.decodeResource(mContext.getResources(), id);
        //Log.e("setup textures ", "1");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + 6);
        //Log.e("setup textures ", "1");
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texturenames[6]);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        //Log.e("setup textures ", "1");
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //Log.e("setup textures ", "1");
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
        //Log.e("setup textures ", "1");
        bmp.recycle();
    }

}
