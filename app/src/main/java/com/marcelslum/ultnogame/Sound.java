package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by marcel on 17/09/2016.
 */
public class Sound {

    public static SoundPool soundPool;

    //public static int soundBallHit;
    //public static int soundDestroyTarget;
    //public static int soundBallFall;

    //public static int soundMenuSelectBig;
    //public static int soundMenuSelectSmall;
    //public static int soundBlueBallExplosion;


    //public static int soundSuccess;
    public static int soundSuccess1;
    //public static int soundSuccess2;
    public static int soundCounter;
    public static int soundScore;
    public static int soundAlarm;
    //public static int soundExplosion;
    //public static int soundGameOver;
    public static int soundTextBoxAppear;
    public static int soundBarSize;
    public static int soundWind;
    //public static int soundMenuIconDrop2;
    public static int soundStarsUp;
    public static int soundDuplicateBall;
    public static int soundWin1;
    public static int soundWin2;

    public static LoopMediaPlayer loop;

    public static String TAG = "Sound";


    public static AudioTrack mAudioTrack_BallHit_Counter_win1;
    public static AudioTrack mAudioTrackBallFall_menuselectsmall_blueBallExplosion;
    public static AudioTrack mAudioTrack_DestroyTarget_menuselectbig_win2;
    public static AudioTrack mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2;
    public static AudioTrack mAudioTrack_5;
    public static MediaPlayer mediaPlayerPrincipal;


    public Sound(){

    }

    public static AudioData menuBigAudioData;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    public static void init(){

        //Log.e(TAG, "init loading sounds");

        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();


        menuBigAudioData = new AudioData("menuselectbig.wav", 1f);

        //soundBallHit = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballhit, 1);
        //soundDestroyTarget = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.destroy_target, 1);
        //soundBallFall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.ballfall, 1);
        //soundMenuSelectBig = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectbig, 1);
        //soundMenuSelectSmall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.menuselectsmall, 1);

        loadMenuAudioTracks();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(8).build();

        //soundCounter = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.counter, 1);
        //soundMenuIconDrop2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bells9, 1);
        //soundSuccess1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success1, 1);
        //soundSuccess2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success2, 1);

        soundAlarm = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.alarm, 1);
        //soundBlueBallExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.blueballexplosion, 1);
        //soundExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.explosion, 1);
        //soundGameOver = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.gameover2, 1);
        soundScore = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.score, 1);
        //soundWin1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win0_powerup17, 1);
        //soundWin2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win1_powerup16, 1);
        soundTextBoxAppear = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.textboxappear, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        //soundStarsUp = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.starsup, 1);
        soundDuplicateBall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.duplicateball, 1);

    }

    public static int audioTrackState = -1;
    public static final int AUDIO_TRACK_STATE_MENU = 0;
    public static final int AUDIO_TRACK_STATE_GAME = 1;
    public static final int AUDIO_TRACK_STATE_WIN = 2;

    public static void loadMenuAudioTracks(){

        if (mAudioTrack_BallHit_Counter_win1 != null) {
            mAudioTrack_BallHit_Counter_win1.release();
            mAudioTrack_BallHit_Counter_win1 = null;
        }
        if (mAudioTrack_DestroyTarget_menuselectbig_win2 != null) {
            mAudioTrack_DestroyTarget_menuselectbig_win2.release();
            mAudioTrack_DestroyTarget_menuselectbig_win2 = null;
        }
        if (mAudioTrackBallFall_menuselectsmall_blueBallExplosion != null) {
            mAudioTrackBallFall_menuselectsmall_blueBallExplosion.release();
            mAudioTrackBallFall_menuselectsmall_blueBallExplosion = null;
        }

        if (mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 != null) {
            mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.release();
            mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = null;
        }

        /*


        if (audioTrackState != AUDIO_TRACK_STATE_MENU || mAudioTrack_BallHit_Counter_win1 == null || mAudioTrack_DestroyTarget_menuselectbig_win2 == null || mAudioTrackBallFall_menuselectsmall_blueBallExplosion == null) {
            audioTrackState = AUDIO_TRACK_STATE_MENU;


            if (mAudioTrack_BallHit_Counter_win1 != null) {
                mAudioTrack_BallHit_Counter_win1.release();
            }
            if (mAudioTrack_DestroyTarget_menuselectbig_win2 != null) {
                mAudioTrack_DestroyTarget_menuselectbig_win2.release();
            }
            if (mAudioTrackBallFall_menuselectsmall_blueBallExplosion != null) {
                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.release();
            }

            if (mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 != null) {
                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.release();
            }

            ByteBuffer pcm;
            byte[] music;
            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("counter.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_BallHit_Counter_win1 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_BallHit_Counter_win1.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("menuselectbig.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_DestroyTarget_menuselectbig_win2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_DestroyTarget_menuselectbig_win2.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("menuselectsmall.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("bells9.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        */

    }

    public static void loadAfterGameAudioTracks(){
        if (mediaPlayerPrincipal != null) {
            mediaPlayerPrincipal.release();
        }

        if (mAudioTrack_BallHit_Counter_win1 != null) {
            mAudioTrack_BallHit_Counter_win1.release();
            mAudioTrack_BallHit_Counter_win1 = null;
        }
        if (mAudioTrack_DestroyTarget_menuselectbig_win2 != null) {
            mAudioTrack_DestroyTarget_menuselectbig_win2.release();
            mAudioTrack_DestroyTarget_menuselectbig_win2 = null;
        }
        if (mAudioTrackBallFall_menuselectsmall_blueBallExplosion != null) {
            mAudioTrackBallFall_menuselectsmall_blueBallExplosion.release();
            mAudioTrackBallFall_menuselectsmall_blueBallExplosion = null;
        }

        if (mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 != null) {
            mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.release();
            mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = null;
        }

        /*
        if (audioTrackState != AUDIO_TRACK_STATE_WIN || mAudioTrack_BallHit_Counter_win1 == null || mAudioTrack_DestroyTarget_menuselectbig_win2 == null || mAudioTrackBallFall_menuselectsmall_blueBallExplosion == null) {
            audioTrackState = AUDIO_TRACK_STATE_WIN;

            if (mAudioTrack_BallHit_Counter_win1 != null) {
                mAudioTrack_BallHit_Counter_win1.release();
            }
            if (mAudioTrack_DestroyTarget_menuselectbig_win2 != null) {
                mAudioTrack_DestroyTarget_menuselectbig_win2.release();
            }
            if (mAudioTrackBallFall_menuselectsmall_blueBallExplosion != null) {
                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.release();
            }

            if (mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 != null) {
                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.release();
            }

            ByteBuffer pcm;
            byte[] music;
            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("win0_powerup17_22050.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_BallHit_Counter_win1 = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_BallHit_Counter_win1.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("win1_powerup16_22050.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_DestroyTarget_menuselectbig_win2 = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_DestroyTarget_menuselectbig_win2.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("blueballexplosion22050.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("success2_22050.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        */
    }

    public static void loadGameAudioTracks(){

        if (mediaPlayerPrincipal != null){
            mediaPlayerPrincipal.release();
            mediaPlayerPrincipal = null;
        }

        mediaPlayerPrincipal = MediaPlayer.create(Game.mainActivity, R.raw.textboxappear);
        mediaPlayerPrincipal.setVolume(0.2f, 0.2f);

        if (audioTrackState != AUDIO_TRACK_STATE_GAME || mAudioTrack_BallHit_Counter_win1 == null || mAudioTrack_DestroyTarget_menuselectbig_win2 == null || mAudioTrackBallFall_menuselectsmall_blueBallExplosion == null) {
            audioTrackState = AUDIO_TRACK_STATE_GAME;

            if (mAudioTrack_BallHit_Counter_win1 != null) {
                mAudioTrack_BallHit_Counter_win1.release();
                mAudioTrack_BallHit_Counter_win1 = null;
            }
            if (mAudioTrack_DestroyTarget_menuselectbig_win2 != null) {
                mAudioTrack_DestroyTarget_menuselectbig_win2.release();
                mAudioTrack_DestroyTarget_menuselectbig_win2 = null;
            }
            if (mAudioTrackBallFall_menuselectsmall_blueBallExplosion != null) {
                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.release();
                mAudioTrackBallFall_menuselectsmall_blueBallExplosion = null;
            }

            if (mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 != null) {
                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.release();
                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = null;
            }

            ByteBuffer pcm;
            byte[] music;
            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("ballhit1stereo.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_BallHit_Counter_win1 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_BallHit_Counter_win1.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("destroy_target.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_DestroyTarget_menuselectbig_win2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_DestroyTarget_menuselectbig_win2.write(music, 0, music.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("ballfall.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrackBallFall_menuselectsmall_blueBallExplosion.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                InputStream input = Game.mainActivity.getResources().getAssets().open("explosion.wav");
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, music.length,
                        AudioTrack.MODE_STATIC);

                mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2.write(music, 0, music.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSucces1(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("success1.wav", 1f));
        //new AudioTrackThread(Game.mainActivity, "success1.wav", 1f).run();

        /*

        Log.e(TAG, "playSucces1");
        ByteBuffer pcm;
        byte[] music;
        try {

            if (mAudioTrack_5 != null && mAudioTrack_5.getState() == AudioTrack.STATE_INITIALIZED){
                mAudioTrack_5.stop();
                mAudioTrack_5.release();
            }

            InputStream input = Game.mainActivity.getResources().getAssets().open("success1_22050.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrack_5 = new AudioTrack(AudioManager.STREAM_MUSIC, 22050,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);
            mAudioTrack_5.write(music, 0, music.length);
            playAudioTrack(mAudioTrack_5, 0.8f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public void playGameOver(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("gameover2.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "gameover2.wav", 1f).run();
        /*
        Log.e(TAG, "playGameOver");
        ByteBuffer pcm;
        byte[] music;
        try {
            if (mAudioTrack_5 != null && mAudioTrack_5.getState() == AudioTrack.STATE_INITIALIZED){
                mAudioTrack_5.stop();
                mAudioTrack_5.release();
            }

            InputStream input = Game.mainActivity.getResources().getAssets().open("gameover2.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrack_5 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);
            mAudioTrack_5.write(music, 0, music.length);
            playAudioTrack(mAudioTrack_5, 0.8f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
    }

    public void playStarsUp(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("starsup.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "starsup.wav", 1f).run();

        /*

        Log.e(TAG, "playSucces1");
        ByteBuffer pcm;
        byte[] music;
        try {

            if (mAudioTrack_5 != null && mAudioTrack_5.getState() == AudioTrack.STATE_INITIALIZED){
                mAudioTrack_5.stop();
                mAudioTrack_5.release();
            }


            InputStream input = Game.mainActivity.getResources().getAssets().open("starsup.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();



            mAudioTrack_5 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);
            mAudioTrack_5.write(music, 0, music.length);
            playAudioTrack(mAudioTrack_5, 0.8f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        */
    }

    public static void playTextBoxAppear(){
        if (mediaPlayerPrincipal != null){
            Log.e(TAG, "playTextBoxAppear");
            mediaPlayerPrincipal.seekTo(0);
            mediaPlayerPrincipal.start();
        } else {
            Log.e(TAG, "playTextBoxAppear não tocando, nulo");
        }
    }

    public static void playMediaPlayerPrincipal(int resourceId){
        if (mediaPlayerPrincipal != null){
            if (mediaPlayerPrincipal.isPlaying()) {
                mediaPlayerPrincipal.stop();
                mediaPlayerPrincipal.release();
                mediaPlayerPrincipal = null;
            }
        }
        mediaPlayerPrincipal = MediaPlayer.create(Game.mainActivity, resourceId);

        mediaPlayerPrincipal.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayerPrincipal.setVolume(1f, 1f);
                mediaPlayerPrincipal.start();
            }
        });

        mediaPlayerPrincipal.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayerPrincipal.release();
                mediaPlayerPrincipal = null;
            }
        });
    }


    public void playBallFall(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("ballfall.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "ballfall.wav", 1f).run();

        /*

        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playBallFall State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);
        */
    }

    public void playWin1(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("win0_powerup17.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "win0_powerup17.wav", 1f).run();


        /*
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playWin1 State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrack_BallHit_Counter_win1, 0.5f);
        */
    }

    public void playWin2(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("win1_powerup16.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "win1_powerup16.wav", 1f).run();

        /*
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playWin2 State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrack_DestroyTarget_menuselectbig_win2, 0.5f);
        */
    }

    public void playBlueBallExplosion(){


        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("blueballexplosion.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "blueballexplosion.wav", 1f).run();

        /*
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playBlueBallExplosion State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);

        */
    }

    public static void playAudioTrack(AudioTrack at, float volume){

        if (at.getState() == AudioTrack.STATE_UNINITIALIZED || at.getState() == AudioTrack.STATE_NO_STATIC_DATA){
            Log.e(TAG, "Audio não inicializado.");
        } else {
            at.stop();
            at.flush();
            at.setVolume(volume);
            at.play();
        }
    }

    public static void playSuccess2(){
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playSuccess2 State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2, 0.8f);
    }

    public void playMenuIconDrop(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("bells9.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "bells9.wav", 1f).run();

        /*

        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks game não carregados. playMenuIconDrop State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2, 0.3f);

        */
    }

    public void playExplosion(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("explosion.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "explosion.wav", 1f).run();

        /*
        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playExplosion State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2, 0.8f);

        */
    }

    public static void playDestroyTarget(){
        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playDestroyTarget State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrack_DestroyTarget_menuselectbig_win2, 0.8f);
    }


    public static void playBallHit(){
        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playBallHit State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrack_BallHit_Counter_win1, 0.8f);
    }

    public void playMenuSmall(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("menuselectsmall.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "menuselectsmall.wav", 1f).run();

        /*

        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. playMenuSmall State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);

        */
    }



    public void playPlayMenuBig(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,  new AudioData("menuselectbig.wav", 1f));

        //new Thread(new AudioTrackThread(Game.mainActivity, "menuselectbig.wav", 1f)).start();

        /*

        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. playPlayMenuBig State != AUDIO_TRACK_STATE_MENU");
            return;
        }



        playAudioTrack(mAudioTrack_DestroyTarget_menuselectbig_win2, 0.8f);

        */
    }


    public  void playCounter(){

        new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new AudioData("counter.wav", 1f));

        //new AudioTrackThread(Game.mainActivity, "menuselectbig.wav", 1f).run();

        /*

        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrack_BallHit_Counter_win1, 0.8f);

        */
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

        if (loop != null){
            loop.stopAndRelease();
        }

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

    private static class AudioData{
        public String fileName;
        public float volume;

        public AudioData(String fileName, float volume) {


            this.fileName = fileName;
            this.volume = volume;
        }

    }



    private class PlayAudio extends AsyncTask<AudioData, Integer, Integer> {
        private String fileName;
        private Context context;
        private float volume;

        private byte[] music;
        byte[] musicPart = new byte[512];
        AudioTrack track;

        protected Integer doInBackground(AudioData... data) {
            this.fileName = data[0].fileName;
            this.volume = data[0].volume;
            this.context = Game.getContext();
            try {

                int minSize = AudioTrack.getMinBufferSize(44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT);


                track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                        AudioFormat.CHANNEL_OUT_STEREO,
                        AudioFormat.ENCODING_PCM_16BIT, minSize,
                        AudioTrack.MODE_STREAM);


                ByteBuffer pcm;
                InputStream input = context.getAssets().open(fileName);
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();

                track.play();

                boolean continuePlaying = true;
                int musicPartNumber = 0;
                while(continuePlaying)
                {
                    int i;
                    for( i = 0; i < musicPart.length; i++ )
                    {
                        if (i + (musicPart.length * musicPartNumber) < music.length) {
                            musicPart[i] = music[i + (musicPart.length * musicPartNumber)];
                        } else {
                            continuePlaying = false;
                        }
                    }
                    musicPartNumber += 1;
                    track.write(musicPart, 0, i);
                }


                track.release();
                track = null;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

            return 0;

        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Integer result) {
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.e(TAG, "onCancelled thread "+ fileName);
            if (track != null){
                if (track.getState() == AudioTrack.STATE_INITIALIZED) {
                    track.stop();
                }
                track.release();
                track = null;
            }


        }
    }



    class AudioTrackThread implements Runnable {
        private String fileName;
        private Context context;
        private float volume;
        private byte[] music;
        byte[] musicPart = new byte[512];
        AudioTrack track;

        AudioTrackThread(Context context, String fileName, float volume) {
            this.context = context;
            this.fileName = fileName;
            this.volume = volume;

        }


        @Override
        public void run() {
            //android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_AUDIO);
            try {
                playWaveFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void playWaveFile() throws IOException {
            ByteBuffer pcm;
            InputStream input = context.getAssets().open(fileName);
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            int minSize = AudioTrack.getMinBufferSize(44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT);


            track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                    AudioTrack.MODE_STREAM);

            track.play();
            handleSound();
        }

        private void handleSound()
        {
            boolean continuePlaying = true;
            int musicPartNumber = 0;
            while(continuePlaying)
            {
                int i;
                for( i = 0; i < musicPart.length; i++ )
                {
                    if (i + (musicPart.length * musicPartNumber) < music.length) {
                        musicPart[i] = music[i + (musicPart.length * musicPartNumber)];
                    } else {
                        continuePlaying = false;
                    }
                }
                musicPartNumber += 1;
                track.write(musicPart, 0, i);
            }


            track.release();
            track = null;


        }
    }


}
