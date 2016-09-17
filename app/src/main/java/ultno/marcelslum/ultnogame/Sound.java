package ultno.marcelslum.ultnogame;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Created by marcel on 17/09/2016.
 */
public abstract class Sound {

    public static SoundPool soundPool;
    public static int soundBallHit;
    public static int soundCounter;
    public static int soundDestroyTarget;
    public static int soundScore;
    public static int soundAlarm;
    public static int soundBallFall;
    public static int soundBlueBallExplosion1;
    public static int soundBlueBallExplosion2;
    public static int soundExplosion1;
    public static int soundExplosion2;
    public static int soundGameOver;
    public static int soundMenuSelectBig;
    public static int soundMenuSelectSmall;
    public static int soundWin;
    public static int soundTextBoxAppear;
    public static int soundBarSize;
    public static int soundWind;
    public static MediaPlayer music;


    public static void init(){
        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(8).build();

        soundBallHit = soundPool.load(Game.context, R.raw.ballhit, 1);
        soundCounter = soundPool.load(Game.context, R.raw.counter, 1);
        soundDestroyTarget = soundPool.load(Game.context, R.raw.destroytarget, 1);
        soundAlarm = soundPool.load(Game.context, R.raw.alarm, 1);
        soundBallFall = soundPool.load(Game.context, R.raw.ballfall, 1);
        soundBlueBallExplosion1 = soundPool.load(Game.context, R.raw.blueballexplosion1, 1);
        soundBlueBallExplosion2 = soundPool.load(Game.context, R.raw.blueballexplosion2, 1);
        soundExplosion1 = soundPool.load(Game.context, R.raw.explosion1, 1);
        soundExplosion2 = soundPool.load(Game.context, R.raw.explosion21, 1);
        soundGameOver = soundPool.load(Game.context, R.raw.gameover2, 1);
        soundMenuSelectBig = soundPool.load(Game.context, R.raw.menuselectbig, 1);
        soundMenuSelectSmall = soundPool.load(Game.context, R.raw.menuselectsmall, 1);
        soundScore = soundPool.load(Game.context, R.raw.score, 1);
        soundWin = soundPool.load(Game.context, R.raw.win, 1);
        soundTextBoxAppear = soundPool.load(Game.context, R.raw.textboxappear, 1);
        soundBarSize = soundPool.load(Game.context, R.raw.bar, 1);
        soundWind = soundPool.load(Game.context, R.raw.wind, 1);

    }

    public static int play(int id, float left, float right, int loop){
        return soundPool.play(id, left * 0.01f*(float) Game.volume, right * 0.01f * (float) Game.volume, 0, loop, 1);
    }





}
