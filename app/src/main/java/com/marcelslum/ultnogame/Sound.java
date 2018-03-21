package com.marcelslum.ultnogame;

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

    public static LoopMediaPlayer loop;

    public static AudioTrack mAudioTrack1;
    public static AudioTrack mAudioTrack2;
    public static AudioTrack mAudioTrack3;
    public static AudioTrack mAudioTrack10;
    public static AudioTrack mAudioTrack11;
    public static AudioTrack mAudioTrack12;
    public static AudioTrack mAudioTrack13;

    public Sound(){
    }

    public static void init(){

        adCounter = new AudioData("counter.wav", 0.5f,1);
        adExplosion = new AudioData("explosion.wav", 0.5f,1);
        adBlueBallExplosion = new AudioData("blueballexplosion.wav", 0.5f,1);
        adTextBoxAppear = new AudioData("textboxappear.wav", 0.5f,1);

        adSuccess1 = new AudioData("success1.wav", 0.5f, 2);
        adGameOver = new AudioData("gameover2.wav", 0.5f,2);
        adStarsUp = new AudioData("starsup.wav", 0.5f,2);
        adMenuIconDrop = new AudioData("bells9.wav", 0.5f,2);

        adMenuSmall = new AudioData("menuselectsmall.wav", 0.5f,3);
        adMenuBig = new AudioData("menuselectbig.wav", 0.5f,3);

        adWin1 = new AudioData("win0_powerup17.wav", 0.5f,-1);
        adWin2 = new AudioData("win1_powerup16.wav", 0.5f,-1);
        adSuccess2 = new AudioData("success2.wav", 0.5f,-1);


        AudioAttributes audioAttrib = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(8).build();
        soundAlarm = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.alarm, 1);
        soundScore = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.score, 1);
        soundBarSize = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.bar, 1);
        soundWind = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.wind, 1);
        soundDuplicateBall = soundPool.load(Game.mainActivity.getApplicationContext(), R.raw.duplicateball, 1);
    }

    public void playSucces1(){
        adSuccess1.musicPartNumber = 0;
        if (AsyncTasks.asyncPlaySucces1 == null || AsyncTasks.asyncPlaySucces1.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlaySucces1 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess1);
        }
    }

    public void playGameOver(){
        adGameOver.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayGameOver == null || AsyncTasks.asyncPlayGameOver.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayGameOver = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adGameOver);
        }
    }

    public void playStarsUp(){
        adStarsUp.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayStarsUp == null || AsyncTasks.asyncPlayStarsUp.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayStarsUp = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adStarsUp);
        }
    }

    public void playTextBoxAppear(){
        adTextBoxAppear.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayTextBoxAppear == null || AsyncTasks.asyncPlayTextBoxAppear.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayTextBoxAppear = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adTextBoxAppear);
        }
    }

    public void playWin1(){
        adWin1.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayWin == null || AsyncTasks.asyncPlayWin.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayWin = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin1);
        }
    }

    public void playWin2(){
        adWin2.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayWin2 == null || AsyncTasks.asyncPlayWin2.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayWin2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin2);
        }
    }

    public void playBlueBallExplosion(){
        adBlueBallExplosion.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayBlueBallExplosion == null || AsyncTasks.asyncPlayBlueBallExplosion.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayBlueBallExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adBlueBallExplosion);
        }
    }

    // TODO não devia tocar em algum lugar?
    public void playSuccess2(){
        adSuccess2.musicPartNumber = 0;
        if (AsyncTasks.asyncPlaySucces2 == null || AsyncTasks.asyncPlaySucces2.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlaySucces2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess2);
        }
    }

    public void playMenuIconDrop(){
        adMenuIconDrop.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuIconDrop == null || AsyncTasks.asyncPlayMenuIconDrop.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlayMenuIconDrop = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuIconDrop);
        }
    }

    public void playMenuSmall(){
        adMenuSmall.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuIconDrop == null || AsyncTasks.asyncPlayMenuSmall.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlayMenuSmall = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuSmall);
        }
    }

    public void playPlayMenuBig(){
        adMenuBig.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuBig == null || AsyncTasks.asyncPlayMenuBig.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayMenuBig = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuBig);
        }
    }

    public void playCounter(){
        adCounter.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayCounter == null || AsyncTasks.asyncPlayCounter.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayCounter = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adCounter);
        }
    }

    public void playExplosion(){
        adExplosion.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayExplosion == null || AsyncTasks.asyncPlayExplosion.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adExplosion);
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

    public static void playBallHit(){
        playStaticAudioTrack(mAudioTrack1, 0.8f);
    }

    public static void playDestroyTarget(){
        playStaticAudioTrack(mAudioTrack2, 0.8f);
    }

    public static void playBallFall(){
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

        int minSize = AudioTrack.getMinBufferSize(44100,
                AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT);

        protected Integer doInBackground(AudioData... data) {

            //Log.e(TAG, "minSize "+minSize);

            try {

                if (data[0].audioTrackNumber == -1) {

                    track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                            AudioFormat.CHANNEL_OUT_STEREO,
                            AudioFormat.ENCODING_PCM_16BIT, minSize,
                            AudioTrack.MODE_STREAM);
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        //Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
                        if (mAudioTrack10 == null){

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
                        //Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
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
                        //Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
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
                        //Log.e(TAG, "Reiniciando som "+data[0].fileName);
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