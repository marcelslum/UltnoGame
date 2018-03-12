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

    public static int soundTextBoxAppear;
    public static int soundBarSize;
    public static int soundWind;
    public static int soundSuccess1;
    public static int soundSuccess2;
    //public static int soundMenuIconDrop1;
    public static int soundMenuIconDrop2;
    public static int soundStarsUp;
    public static int soundDuplicateBall;
    //public static MediaPlayer music;
    public static int soundWin1;
    public static int soundWin2;

    public static LoopMediaPlayer loopMenu;
    public static LoopMediaPlayer loop;


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
        soundWin1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win0_powerup17, 1);
        soundWin2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win1_powerup16, 1);
        soundTextBoxAppear = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.textboxappear, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        soundSuccess1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success1, 1);
        soundSuccess2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success2, 1);
        //soundMenuIconDrop1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bells1, 1);
        soundMenuIconDrop2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bells9, 1);
        soundStarsUp = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.starsup, 1);
        soundDuplicateBall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.duplicateball, 1);
    }

    public static int play(int id, float left, float right, int loop){

        // todo carregar save game no splash para ativar a opção de vibrar no menu

        if (SaveGame.saveGame == null || SaveGame.saveGame.sound) {
            if (soundPool != null) {
                return soundPool.play(id, left * 1f, right * 1f, 0, loop, 1);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static void pauseAll(){
        if (soundPool != null) {
            soundPool.autoPause();
        }

        if (loop != null){
            loop.pause();
        }
    }
    
    public static checkLoopPlaying(){
        if (Game.gameState == Game.GAME_STATE_JOGAR){
            if (loop == null){
                Log.e(TAG, "loop nulo, criando novo");
                loadPool();
                return;
            }
            
            try {
                loop.play();
            } catch (Exception e) {
                Log.e(TAG, "loop play falhou, criando novo");
                loop = null;
                loadPool();
                return;
            }
            
            try {
                loop.isPLaying();
            } catch (Exception e) {
                Log.e(TAG, "loop isPlaying falhou, criando novo");
                loop = null;
                loadPool();
            }
        }
    }
    
    public static loadPool(){
        int loopChoose = (SaveGame.saveGame.currentLevelNumber-1) % 3;
        Log.e(TAG, "loopChoose "+ loopChoose);
        switch (loopChoose){
            case 0:
                Sound.loop = LoopMediaPlayer.create(Game.mainActivity, R.raw.m1_hypnotic_puzzle2, R.raw.m3_hypnotic_puzzle4, 0.8f);
                break;
            case 1:
                Sound.loop = LoopMediaPlayer.create(Game.mainActivity, R.raw.m2_hypnotic_puzzle3, R.raw.m4_hypnotic_puzzle, 0.8f);
                break;
            case 2:
                Sound.loop = LoopMediaPlayer.create(Game.mainActivity, R.raw.m10_mellow_puzzler, 0.8f);
                break;
            default:
                Sound.loop = LoopMediaPlayer.create(Game.mainActivity, R.raw.m1_hypnotic_puzzle2, R.raw.m3_hypnotic_puzzle4, 0.8f);
                break;
        }
    }

}
