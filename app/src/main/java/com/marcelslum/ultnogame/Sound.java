package com.marcelslum.ultnogame;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;

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
    public static int soundSuccess1;
    public static int soundSuccess2;
    public static MediaPlayer music;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    //TODO
    public static void init(){
        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(16).build();

        soundBallHit = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballhit, 1);
        soundCounter = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.counter, 1);
        soundDestroyTarget = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.destroytarget, 1);
        soundAlarm = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.alarm, 1);
        soundBallFall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballfall, 1);
        soundBlueBallExplosion1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.blueballexplosion1, 1);
        soundBlueBallExplosion2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.blueballexplosion2, 1);
        soundExplosion1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.explosion1, 1);
        soundExplosion2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.explosion21, 1);
        soundGameOver = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.gameover2, 1);
        soundMenuSelectBig = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectbig, 1);
        soundMenuSelectSmall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectsmall, 1);
        soundScore = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.score, 1);
        soundWin = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win, 1);
        soundTextBoxAppear = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.textboxappear, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        soundSuccess1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success1, 1);
        soundSuccess2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success2, 1);
    }

    public static int play(int id, float left, float right, int loop){
        return soundPool.play(id, left * 1f, right * 1f, 0, loop, 1);
    }

    public static void pauseAll(){
        soundPool.autoPause();
        if (music != null && music.isPlaying()){
            Sound.music.pause();
        }
    }





}
