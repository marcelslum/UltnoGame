package com.marcelslum.ultnogame;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by marcel on 17/09/2016.
 */
public abstract class Sound {

    public static SoundPool soundPool;

    //public static int soundBallHit;
    //public static int soundDestroyTarget;
    //public static int soundBallFall;

    public static int soundCounter;
    public static int soundScore;
    public static int soundAlarm;
    public static int soundBlueBallExplosion;
    public static int soundExplosion;
    public static int soundGameOver;
    public static int soundMenuSelectBig;
    public static int soundMenuSelectSmall;
    public static int soundTextBoxAppear;
    public static int soundBarSize;
    public static int soundWind;
    public static int soundSuccess1;
    public static int soundSuccess2;
    public static int soundMenuIconDrop2;
    public static int soundStarsUp;
    public static int soundDuplicateBall;
    public static int soundWin1;
    public static int soundWin2;

    public static LoopMediaPlayer loop;

    public static String TAG = "Sound";


    public static AudioTrack mAudioTrackBallHit;
    public static AudioTrack mAudioTrackBallFall;
    public static AudioTrack mAudioTrackDestroyTarget;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public static void init(){

        //Log.e(TAG, "init loading sounds");

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();


        //soundBallHit = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballhit, 1);
        //soundDestroyTarget = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.destroy_target, 1);
        //soundBallFall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballfall, 1);

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        soundMenuSelectBig = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectbig, 1);
        soundMenuSelectSmall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectsmall, 1);
        soundMenuIconDrop2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bells9, 1);
        soundCounter = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.counter, 1);
        soundAlarm = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.alarm, 1);
        soundBlueBallExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.blueballexplosion, 1);
        soundExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.explosion, 1);
        soundGameOver = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.gameover2, 1);
        soundScore = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.score, 1);
        soundWin1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win0_powerup17, 1);
        soundWin2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win1_powerup16, 1);
        soundTextBoxAppear = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.textboxappear, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        soundSuccess1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success1, 1);
        soundSuccess2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success2, 1);
        soundStarsUp = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.starsup, 1);
        soundDuplicateBall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.duplicateball, 1);

        ByteBuffer pcm;
        byte[] music;
        try {

            InputStream input = Game.mainActivity.getResources().getAssets().open("ballhit1stereo.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrackBallHit = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrackBallHit.write(music, 0, music.length);


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream input = Game.mainActivity.getResources().getAssets().open("destroy_target.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrackDestroyTarget = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrackDestroyTarget.write(music, 0, music.length);




        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            InputStream input = Game.mainActivity.getResources().getAssets().open("ballfall.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrackBallFall = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrackBallFall.write(music, 0, music.length);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void playBallFall(){
        mAudioTrackBallFall.stop();
        mAudioTrackBallFall.play();

    }

    public static int playDestroyTarget(){
        mAudioTrackDestroyTarget.stop();
        mAudioTrackDestroyTarget.play();
        return 1;
    }


    public static int playBallHit(){
        mAudioTrackBallHit.stop();
        mAudioTrackBallHit.play();
        return 1;
    }

    public static int play(int id, float left, float right, int loop){

        if (SaveGame.saveGame == null || SaveGame.saveGame.sound) {
                if (soundPool != null) {
                    //Log.e(TAG, "Tocando o som " + id + "volume " + left + " ; "+ right);
                        return soundPool.play(id, left * 1f, right * 1f, 0, loop, 1);
                } else {
                    Log.e(TAG, "Não tocando o som. SoundPool nulo.");
                    return -1;
                }
        } else {
            Log.e(TAG, "Não tocando o som. SaveGame.saveGame.sound = false.");
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

    
    public static void checkLoopPlaying(){
        if (Game.gameState == Game.GAME_STATE_JOGAR){
            //Log.e(TAG, "check loop playing");
            if (loop == null){
                //Log.e(TAG, "loop nulo, criando novo");
                loadLoop();
                return;
            }
            
            try {
                loop.play();
            } catch (Exception e) {
                //Log.e(TAG, "loop play falhou, criando novo");
                loop = null;
                loadLoop();
                return;
            }
            
            try {
                loop.isPlaying();
            } catch (Exception e) {
                //Log.e(TAG, "loop isPlaying falhou, criando novo");
                loop = null;
                loadLoop();
            }
        }
    }
    
    public static void loadLoop(){
        int loopChoose = (SaveGame.saveGame.currentLevelNumber-1) % 3;
        //Log.e(TAG, "loopChoose "+ loopChoose);
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
