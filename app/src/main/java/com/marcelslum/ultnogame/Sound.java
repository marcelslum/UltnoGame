package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * Created by marcel on 17/09/2016.
 */
public class Sound {

    public static String TAG = "Sound";

    public static SoundPool soundPool;
    //public static int soundBallHit;
    //public static int soundDestroyTarget;
    //public static int soundBallFall;
    //public static int soundMenuSelectBig;
    //public static int soundMenuSelectSmall;
    //public static int soundBlueBallExplosion;
    //public static int soundSuccess;
    //public static int soundSuccess1;
    //public static int soundSuccess2;
    //public static int soundExplosion;
    //public static int soundGameOver;
    //public static int soundMenuIconDrop2;
    //public static int soundStarsUp;
    //public static int soundWin1;
    //public static int soundWin2;
    //public static int soundCounter;
    public static int soundScore;
    public static int soundBarSize;
    public static int soundWind;
    public static int soundDuplicateBall;
    public static int soundAlarm;

    static AudioData adSuccess1;
    static AudioData adGameOver;
    static AudioData adStarsUp;
    static AudioData adTextBoxAppear;
    static AudioData adWin1;
    static AudioData adWin2;
    static AudioData adBlueBallExplosion;
    static AudioData adSuccess2;
    static AudioData adMenuIconDrop;
    static AudioData adMenuSmall;
    static AudioData adMenuBig;
    static AudioData adCounter;
    static AudioData adExplosion;
    static AudioData adBallHit;
    static AudioData adBallFall;
    static AudioData adDestroyTarget;

    public static LoopMediaPlayer loop;

    public static AudioTrack mAudioTrack1;
    public static AudioTrack mAudioTrack2;
    public static AudioTrack mAudioTrack3;
    public static AudioTrack mAudioTrack4;

    public static AudioTrack mAudioTrack10;
    public static AudioTrack mAudioTrack11;
    public static AudioTrack mAudioTrack12;
    public static AudioTrack mAudioTrack13;
    public static AudioTrack mAudioTrack14;
    public static AudioTrack mAudioTrack15;
    public static AudioTrack mAudioTrack16;
    public static AudioTrack mAudioTrack17;
    public static AudioTrack mAudioTrack18;
    public static AudioTrack mAudioTrack19;
    public static AudioTrack mAudioTrack20;


    public Sound(){
    }

    public static void init(){

        adCounter = new AudioData("counter.wav", 0.5f,1);
        adExplosion = new AudioData("explosion.wav", 0.5f,1);
        adBlueBallExplosion = new AudioData("blueballexplosion.wav", 0.5f,1);

        adSuccess1 = new AudioData("success1.wav", 0.5f, 2);
        adGameOver = new AudioData("gameover2.wav", 0.5f,2);
        adStarsUp = new AudioData("starsup.wav", 0.5f,2);
        adMenuIconDrop = new AudioData("bells9.wav", 0.5f,2);

        adMenuSmall = new AudioData("menuselectsmall.wav", 0.5f,3);
        adMenuBig = new AudioData("menuselectbig.wav", 0.5f,3);
        adTextBoxAppear = new AudioData("textboxappear.wav", 0.5f,3);


        adWin1 = new AudioData("win0_powerup17.wav", 0.5f,-1);
        adWin2 = new AudioData("win1_powerup16.wav", 0.5f,-1);
        adSuccess2 = new AudioData("success2.wav", 0.5f,-1);



        adBallFall = new AudioData("ballfall.wav", 0.5f,-1); // todo retirar
        adBallHit = new AudioData("ballhit1stereo.wav", 0.5f,-1); // todo retirar
        adDestroyTarget = new AudioData("destroy_target.wav", 0.5f,-1); // todo retirar

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
        //loadMenuAudioTracks();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(8).build();
        soundAlarm = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.alarm, 1);
        soundScore = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.score, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        soundDuplicateBall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.duplicateball, 1);

        //soundCounter = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.counter, 1);
        //soundMenuIconDrop2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bells9, 1);
        //soundSuccess1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success1, 1);
        //soundSuccess2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.success2, 1);
        //soundBlueBallExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.blueballexplosion, 1);
        //soundExplosion = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.explosion, 1);
        //soundGameOver = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.gameover2, 1);
        //soundWin1 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win0_powerup17, 1);
        //soundWin2 = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.win1_powerup16, 1);
        //soundStarsUp = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.starsup, 1);
    }

    static AsyncTask asyncPlaySucces1;
    public void playSucces1(){
        adSuccess1.musicPartNumber = 0;
        if (asyncPlaySucces1 == null || asyncPlaySucces1.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlaySucces1 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess1);
        }
    }

    static AsyncTask asyncPlayGameOver;
    public void playGameOver(){
        adGameOver.musicPartNumber = 0;
        if (asyncPlayGameOver == null || asyncPlayGameOver.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayGameOver = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adGameOver);
        }
    }

    static AsyncTask asyncPlayStarsUp;
    public void playStarsUp(){
        adStarsUp.musicPartNumber = 0;
        if (asyncPlayStarsUp == null || asyncPlayStarsUp.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayStarsUp = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adStarsUp);
        }
    }

    static AsyncTask asyncPlayTextBoxAppear;
    public void playTextBoxAppear(){
        adTextBoxAppear.musicPartNumber = 0;
        if (asyncPlayTextBoxAppear == null || asyncPlayTextBoxAppear.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayTextBoxAppear = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adTextBoxAppear);
        }
    }

    static AsyncTask asyncPlayWin;
    public void playWin1(){
        adWin1.musicPartNumber = 0;
        if (asyncPlayWin == null || asyncPlayWin.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayWin = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin1);
        }
    }

    static AsyncTask asyncPlayWin2;
    public void playWin2(){
        adWin2.musicPartNumber = 0;
        if (asyncPlayWin2 == null || asyncPlayWin2.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayWin2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin2);
        }
    }

    static AsyncTask asyncPlayBlueBallExplosion;
    public void playBlueBallExplosion(){
        adBlueBallExplosion.musicPartNumber = 0;
        if (asyncPlayBlueBallExplosion == null || asyncPlayBlueBallExplosion.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayBlueBallExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adBlueBallExplosion);
        }
    }

    static AsyncTask asyncPlaySucces2;
    public void playSuccess2(){
        adSuccess2.musicPartNumber = 0;
        if (asyncPlaySucces2 == null || asyncPlaySucces2.getStatus() == AsyncTask.Status.FINISHED) {
            asyncPlaySucces2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess2);
        }
    }

    static AsyncTask asyncPlayMenuIconDrop;
    public void playMenuIconDrop(){
        adMenuIconDrop.musicPartNumber = 0;
        if (asyncPlayMenuIconDrop == null || asyncPlayMenuIconDrop.getStatus() == AsyncTask.Status.FINISHED) {
            asyncPlayMenuIconDrop = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuIconDrop);
        }
    }

    static AsyncTask asyncPlayMenuSmall;
    public void playMenuSmall(){
        adMenuSmall.musicPartNumber = 0;
        if (asyncPlayMenuIconDrop == null || asyncPlayMenuSmall.getStatus() == AsyncTask.Status.FINISHED) {
            asyncPlayMenuSmall = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuSmall);
        }
    }

    static AsyncTask asyncPlayMenuBig;
    public void playPlayMenuBig(){
        adMenuBig.musicPartNumber = 0;
        if (asyncPlayMenuBig == null || asyncPlayMenuBig.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayMenuBig = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuBig);
        }
    }

    static AsyncTask asyncPlayCounter;
    public void playCounter(){
        adCounter.musicPartNumber = 0;
        if (asyncPlayCounter == null || asyncPlayCounter.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayCounter = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adCounter);
        }
    }

    static AsyncTask asyncPlayExplosion;
    public void playExplosion(){
        adExplosion.musicPartNumber = 0;
        if (asyncPlayExplosion == null || asyncPlayExplosion.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adExplosion);
        }
    }

    public static void loadStaticGameAudioTracks(){

        ByteBuffer pcm;
        byte[] music;
        try {
            InputStream input = Game.mainActivity.getResources().getAssets().open("ballhit1stereo.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrack1 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack1.write(music, 0, music.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream input = Game.mainActivity.getResources().getAssets().open("destroy_target.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrack2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack2.write(music, 0, music.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream input = Game.mainActivity.getResources().getAssets().open("ballfall.wav");
            WavToPCM.WavInfo info = WavToPCM.readHeader(input);
            pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
            music = pcm.array();

            mAudioTrack3 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack3.write(music, 0, music.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playStaticAudioTrack(AudioTrack at, float volume){

        if (at.getState() == AudioTrack.STATE_UNINITIALIZED || at.getState() == AudioTrack.STATE_NO_STATIC_DATA){
            Log.e(TAG, "Audio não inicializado.");
        } else {
            at.stop();
            at.reloadStaticData();
            at.setVolume(volume);
            at.play();
        }
    }


    static AsyncTask asyncPlayDestroyTarget;
    public void playDestroyTarget2(){
        adDestroyTarget.musicPartNumber = 0;
        if (asyncPlayDestroyTarget == null || asyncPlayDestroyTarget.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayDestroyTarget = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adDestroyTarget);
        }
    }

    static AsyncTask asyncPlayBallFall;
    public void playBallFall2(){
        adBallFall.musicPartNumber = 0;
        if (asyncPlayBallFall == null || asyncPlayBallFall.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayBallFall = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adBallFall);
        }
    }

    static AsyncTask asyncPlayBallHit;
    public void playBallHit2(){
        adBallHit.musicPartNumber = 0;
        if (asyncPlayBallHit == null || asyncPlayBallHit.getStatus() == AsyncTask.Status.FINISHED){
            asyncPlayBallHit = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adBallHit);
        }
    }

    public static void playBallHit(){
        //Game.sound.playBallHit2();
        playStaticAudioTrack(mAudioTrack1, 0.8f);
    }

    public static void playDestroyTarget(){
        //Game.sound.playDestroyTarget2();
        playStaticAudioTrack(mAudioTrack2, 0.8f);
    }

    public static void playBallFall(){
        //Game.sound.playBallFall2();
        playStaticAudioTrack(mAudioTrack3, 0.8f);
    }




    public static int playSoundPool(int id, float left, float right, int loop){

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
        // todo pausar asyncs tasks
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
                //Log.e(TAG, "loop playSoundPool falhou, criando novo");
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
        public byte[] music;
        public AudioTrack audioTrack;
        public int musicPartNumber = 0;
        public int audioTrackNumber = -1;

        public AudioData(String fileName, float volume, int audioTrackNumber) {
            this.fileName = fileName;
            this.volume = volume;
            this.audioTrack = null;
            this.audioTrackNumber = audioTrackNumber;

            ByteBuffer pcm;
            InputStream input = null;
            try {
                input = Game.getContext().getAssets().open(fileName);
                WavToPCM.WavInfo info = WavToPCM.readHeader(input);
                pcm = ByteBuffer.wrap(WavToPCM.readWavPcm(info, input));
                music = pcm.array();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class PlayAudio extends AsyncTask<AudioData, Integer, Integer> {
        byte[] musicPart = new byte[512];
        public AudioTrack track;
        int lastMusicPart = -1;

        protected Integer doInBackground(AudioData... data) {
            try {

                if (data[0].audioTrackNumber == -1) {

                    int minSize = AudioTrack.getMinBufferSize(44100,
                            AudioFormat.CHANNEL_OUT_STEREO,
                            AudioFormat.ENCODING_PCM_16BIT);

                    track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                            AudioFormat.CHANNEL_OUT_STEREO,
                            AudioFormat.ENCODING_PCM_16BIT, minSize,
                            AudioTrack.MODE_STREAM);
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
                        if (mAudioTrack10 == null){

                            int minSize = AudioTrack.getMinBufferSize(44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT);

                            mAudioTrack10 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack10.pause();
                                mAudioTrack10.flush();
                            } else {
                                mAudioTrack10.stop();
                            }
                        }
                    }

                    if (data[0].audioTrackNumber == 2) {
                        Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
                        if (mAudioTrack11 == null){

                            int minSize = AudioTrack.getMinBufferSize(44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT);

                            mAudioTrack11 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack11.pause();
                                mAudioTrack11.flush();
                            } else {
                                mAudioTrack11.stop();
                            }
                        }
                    }

                    if (data[0].audioTrackNumber == 3) {
                        Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
                        if (mAudioTrack12 == null){

                            int minSize = AudioTrack.getMinBufferSize(44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT);

                            mAudioTrack12 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack12.pause();
                                mAudioTrack12.flush();
                            } else {
                                mAudioTrack12.stop();
                            }
                        }
                    }

                }

                if (data[0].audioTrackNumber == -1) {
                    track.play();
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        mAudioTrack10.play();
                    }
                    if (data[0].audioTrackNumber == 2) {
                        mAudioTrack11.play();
                    }
                    if (data[0].audioTrackNumber == 3) {
                        mAudioTrack12.play();
                    }
                }

                boolean continuePlaying = true;
                while(continuePlaying)
                {
                    if (lastMusicPart >= 0 && data[0].musicPartNumber < lastMusicPart){
                        Log.e(TAG, "Reiniciando som "+data[0].fileName);
                        if (data[0].audioTrackNumber == -1) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                track.pause();
                                track.flush();
                            }
                            else {
                                track.stop();
                            }
                            track.play();
                        } else {
                            if (data[0].audioTrackNumber == 1) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack10.pause();
                                    mAudioTrack10.flush();
                                }
                                else {
                                    mAudioTrack10.stop();
                                }
                                mAudioTrack10.play();
                            }

                            if (data[0].audioTrackNumber == 2) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack11.pause();
                                    mAudioTrack11.flush();
                                }
                                else {
                                    mAudioTrack11.stop();
                                }
                                mAudioTrack11.play();
                            }

                            if (data[0].audioTrackNumber == 3) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack12.pause();
                                    mAudioTrack12.flush();
                                }
                                else {
                                    mAudioTrack12.stop();
                                }
                                mAudioTrack12.play();
                            }
                        }
                    }

                    int i;
                    for( i = 0; i < musicPart.length; i++ )
                    {
                        if (i + (musicPart.length * data[0].musicPartNumber) < data[0].music.length) {
                            musicPart[i] = data[0].music[i + (musicPart.length * data[0].musicPartNumber)];
                        } else {
                            continuePlaying = false;
                        }
                    }
                    data[0].musicPartNumber += 1;
                    lastMusicPart = data[0].musicPartNumber;


                    if (data[0].audioTrackNumber == -1) {
                        track.write(musicPart, 0, i);
                    } else {
                        if (data[0].audioTrackNumber == 1) {
                            mAudioTrack10.write(musicPart, 0, i);
                        }

                        if (data[0].audioTrackNumber == 2) {
                            mAudioTrack11.write(musicPart, 0, i);
                        }

                        if (data[0].audioTrackNumber == 3) {
                            mAudioTrack12.write(musicPart, 0, i);
                        }
                    }
                }

                if (data[0].audioTrackNumber == -1) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        track.pause();
                        track.flush();
                    }
                    else {
                        track.stop();
                    }
                    track.release();
                    track = null;
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack10.pause();
                            mAudioTrack10.flush();
                        }
                        else {
                            mAudioTrack10.stop();
                        }
                    }

                    if (data[0].audioTrackNumber == 2) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack11.pause();
                            mAudioTrack11.flush();
                        }
                        else {
                            mAudioTrack11.stop();
                        }
                    }

                    if (data[0].audioTrackNumber == 3) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack12.pause();
                            mAudioTrack12.flush();
                        }
                        else {
                            mAudioTrack12.stop();
                        }
                    }
                }

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
            if (track != null){
                if (track.getState() == AudioTrack.STATE_INITIALIZED) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        track.pause();
                        track.flush();
                    }
                    else {
                        track.stop();
                    }
                    track.release();
                    track = null;
                }
            }
        }
    }
}

