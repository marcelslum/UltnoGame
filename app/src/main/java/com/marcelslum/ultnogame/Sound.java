package com.marcelslum.ultnogame;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
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

    }

    public static void loadAfterGameAudioTracks(){
        if (mediaPlayerPrincipal != null) {
            mediaPlayerPrincipal.release();
        }

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


    public static void playSucces1(){
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
    }

    public static void playGameOver(){
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
    }

    public static void playStarsUp(){



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


    public static void playBallFall(){
        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playBallFall State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);
    }

    public static void playWin1(){
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playWin1 State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrack_BallHit_Counter_win1, 0.5f);
    }

    public static void playWin2(){
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playWin2 State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrack_DestroyTarget_menuselectbig_win2, 0.5f);
    }

    public static void playBlueBallExplosion(){
        if (audioTrackState != AUDIO_TRACK_STATE_WIN){
            Log.e(TAG, "Audio tracks game não carregados. playBlueBallExplosion State != AUDIO_TRACK_STATE_WIN");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);
    }

    public static void playAudioTrack(AudioTrack at, float volume){
        if (at.getState() == AudioTrack.STATE_UNINITIALIZED || at.getState() == AudioTrack.STATE_NO_STATIC_DATA){
            Log.e(TAG, "Audio não inicializado.");
        } else {
            at.stop();
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

    public static void playMenuIconDrop(){
        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks game não carregados. playMenuIconDrop State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2, 0.3f);
    }

    public static void playExplosion(){
        if (audioTrackState != AUDIO_TRACK_STATE_GAME){
            Log.e(TAG, "Audio tracks game não carregados. playExplosion State != AUDIO_TRACK_STATE_GAME");
            return;
        }
        playAudioTrack(mAudioTrack_soundMenuIconDrop2_explosion_soundSuccess2, 0.8f);
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

    public static void playMenuSmall(){
        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. playMenuSmall State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrackBallFall_menuselectsmall_blueBallExplosion, 0.8f);
    }

    public static void playPlayMenuBig(){
        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. playPlayMenuBig State != AUDIO_TRACK_STATE_MENU");
            return;
        }



        playAudioTrack(mAudioTrack_DestroyTarget_menuselectbig_win2, 0.8f);
    }


    public static void playCounter(){
        if (audioTrackState != AUDIO_TRACK_STATE_MENU){
            Log.e(TAG, "Audio tracks menu não carregados. State != AUDIO_TRACK_STATE_MENU");
            return;
        }
        playAudioTrack(mAudioTrack_BallHit_Counter_win1, 0.8f);
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

}
