package com.marcelslum.ultnogame;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
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
    static AudioData adMenuIconDrop;
    static AudioData adMenuSmall;
    static AudioData adMenuBig2;
    static AudioData adMenuBigTest;
    static AudioData adCounter;
    static AudioData adExplosion;
    static AudioData adAngleChange;

    static AudioData adBallHit;
    static AudioData adBallFall;
    static AudioData adDestroyTarget;
    //static AudioData adSuccess2;

    //public static LoopMediaPlayer loop;

    public static MediaPlayer [] mediaPlayer = new MediaPlayer[6];

    public static AudioTrack mAudioTrack1;
    public static AudioTrack mAudioTrack2;
    public static AudioTrack mAudioTrack3;
    public static AudioTrack mAudioTrack11;
    public static AudioTrack mAudioTrack12;
    public static AudioTrack mAudioTrack13;
    public static AudioTrack mAudioTrack14;
    public static AudioTrack mAudioTrack15;

    static int duplicateBallId = -1;
    static int barSizeId = -1;

    public Sound(){

    }

    public static void init(){

        //Log.e(TAG, "init sounds");

        adCounter = new AudioData("counter_mono.wav", 0.4f,1);
        adExplosion = new AudioData("explosion_mono.wav", 0.4f,1);
        adBlueBallExplosion = new AudioData("blueballexplosion_mono.wav", 0.5f,1);


        adSuccess1 = new AudioData("success1_mono.wav", 0.35f, 2);
        adGameOver = new AudioData("gameover2_mono.wav", 0.8f,2);
        adMenuIconDrop = new AudioData("bells9_mono.wav", 0.1f,2);

        adMenuSmall = new AudioData("menuselectsmall.wav", 0.4f,3);
        adMenuBig2 = new AudioData("menuselectbig1.wav", 0.3f,3);
        adMenuBigTest = new AudioData("menuselectbig1.wav", 0f,3);
        adStarsUp = new AudioData("starsup.wav", 0.45f,3);
        adAngleChange = new AudioData("anglePlus.wav", 0.2f,3);


        adWin1 = new AudioData("win0_powerup17_mono.wav", 0.4f,4);
        adWin2 = new AudioData("win1_powerup16_mono.wav", 0.4f,4);
        //adSuccess2 = new AudioData("success2.wav", 0.5f,-1);

        adTextBoxAppear = new AudioData("textboxappear_mono.wav", 0.2f,5);


        adBallHit =  new AudioData("ballhit1stereo.wav", 0.4f,0);
        adBallFall =  new AudioData("ballfall.wav", 0.4f,0);
        adDestroyTarget =  new AudioData("destroy_target.wav", 0.4f,0);

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

    public static void setMusicCurrentPart(int i) {

        musicCurrentPart = i;
    }

    public static void setMusicCurrentGlobalPart(int i) {

        musicCurrentGlobalPart = i;

    }

    public static void setMusicCurrentSubPart(int i) {

        musicCurrentSubPart = i;
    }

    public static float getMusicVolume() {

        return musicVolume;
    }

    public void playSuccess1(){

        if (!SaveGame.saveGame.sound){
            return;
        }

        adSuccess1.musicPartNumber = 0;
        if (AsyncTasks.asyncPlaySucces1 == null || AsyncTasks.asyncPlaySucces1.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlaySucces1 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess1);
        }
    }

    public void playGameOver(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adGameOver.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayGameOver == null || AsyncTasks.asyncPlayGameOver.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayGameOver = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adGameOver);
        }
    }

    public void playStarsUp(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adStarsUp.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayStarsUp == null || AsyncTasks.asyncPlayStarsUp.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayStarsUp = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adStarsUp);
        }
    }

    public void playTextBoxAppear(){
        if (!SaveGame.saveGame.sound){
            return;
        }

        adTextBoxAppear.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayTextBoxAppear == null || AsyncTasks.asyncPlayTextBoxAppear.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayTextBoxAppear = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adTextBoxAppear);
        }
    }

    public void playWin1(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adWin1.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayWin == null || AsyncTasks.asyncPlayWin.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayWin = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin1);
        }
    }

    public void playWin2(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adWin2.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayWin2 == null || AsyncTasks.asyncPlayWin2.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayWin2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adWin2);
        }
    }

    public void playBlueBallExplosion(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adBlueBallExplosion.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayBlueBallExplosion == null || AsyncTasks.asyncPlayBlueBallExplosion.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayBlueBallExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adBlueBallExplosion);
        }
    }

    // TODO não devia tocar em algum lugar?
    public void playSuccess2(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        /*
        adSuccess2.musicPartNumber = 0;
        if (AsyncTasks.asyncPlaySucces2 == null || AsyncTasks.asyncPlaySucces2.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlaySucces2 = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adSuccess2);
        }
        */
    }

    public void playMenuIconDrop(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adMenuIconDrop.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuIconDrop == null || AsyncTasks.asyncPlayMenuIconDrop.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlayMenuIconDrop = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuIconDrop);
        }
    }

    public void playMenuSmall(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adMenuSmall.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuSmall == null || AsyncTasks.asyncPlayMenuSmall.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlayMenuSmall = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuSmall);
        }
    }

    public void playAngleChange(){
        if (!SaveGame.saveGame.sound){
            return;
        }

        //Log.e(TAG, "playAngleChange");
        adAngleChange.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayAngleChange == null || AsyncTasks.asyncPlayAngleChange.getStatus() == AsyncTask.Status.FINISHED) {
            AsyncTasks.asyncPlayAngleChange = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adAngleChange);
        }
    }

    public void playPlayMenuBig(){
        if (!SaveGame.saveGame.sound){
            return;
        }

        adMenuBig2.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuBig == null || AsyncTasks.asyncPlayMenuBig.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayMenuBig = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuBig2);
        }

    }

    public void playPlayMenuBigTest(){
        if (!SaveGame.saveGame.sound){
            return;
        }

        adMenuBigTest.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayMenuBigTest == null || AsyncTasks.asyncPlayMenuBigTest.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayMenuBigTest = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adMenuBigTest);
        }

    }

    public void playCounter(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adCounter.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayCounter == null || AsyncTasks.asyncPlayCounter.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayCounter = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adCounter);
        }
    }

    public void playExplosion(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        adExplosion.musicPartNumber = 0;
        if (AsyncTasks.asyncPlayExplosion == null || AsyncTasks.asyncPlayExplosion.getStatus() == AsyncTask.Status.FINISHED){
            AsyncTasks.asyncPlayExplosion = new PlayAudio().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, adExplosion);
        }
    }

    public static void loadStaticGameAudioTracks(){

        if (mAudioTrack1 == null) {
            mAudioTrack1 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, adBallHit.music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack1.write(adBallHit.music, 0, adBallHit.music.length);
        } else {
            mAudioTrack1.stop();
            mAudioTrack1.reloadStaticData();
        }



        if (mAudioTrack2 == null) {
            mAudioTrack2 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, adDestroyTarget.music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack2.write(adDestroyTarget.music, 0, adDestroyTarget.music.length);
        } else {
            mAudioTrack2.stop();
            mAudioTrack2.reloadStaticData();
        }

        if (mAudioTrack3 == null) {
            mAudioTrack3 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_OUT_STEREO,
                    AudioFormat.ENCODING_PCM_16BIT, adBallFall.music.length,
                    AudioTrack.MODE_STATIC);

            mAudioTrack3.write(adBallFall.music, 0, adBallFall.music.length);
        } else {
            mAudioTrack3.stop();
            mAudioTrack3.reloadStaticData();
        }
    }

    public static void playStaticAudioTrack(AudioTrack at, float volume){
        if (!SaveGame.saveGame.sound){
            return;
        }

        if (at.getState() == AudioTrack.STATE_UNINITIALIZED || at.getState() == AudioTrack.STATE_NO_STATIC_DATA){
            //Log.e(TAG, "Audio não inicializado.");

        } else {
            at.stop();
            at.reloadStaticData();
            at.setVolume(volume);
            at.play();
        }
    }

    public static void playBallHit(){

        if (!SaveGame.saveGame.sound){
            return;
        }
        playStaticAudioTrack(mAudioTrack1, 0.6f);
    }

    public static void playDestroyTarget(){

        if (!SaveGame.saveGame.sound){
            return;
        }
        playStaticAudioTrack(mAudioTrack2, 0.8f);
    }

    public static void playBallFall(){
        if (!SaveGame.saveGame.sound){
            return;
        }
        playStaticAudioTrack(mAudioTrack3, 0.8f);
    }

    public static void playDuplicateBall(float volume){
        if (!SaveGame.saveGame.sound){
            return;
        }
        if (duplicateBallId == -1){
            duplicateBallId = playSoundPool(Sound.soundDuplicateBall, volume, volume, 0);
        } else {
            soundPool.stop(duplicateBallId);
            duplicateBallId = playSoundPool(Sound.soundDuplicateBall, volume, volume, 0);
        }
    }

    public static void playBarSize(float volume){
        if (!SaveGame.saveGame.sound){
            return;
        }
        if (barSizeId == -1){
            barSizeId = playSoundPool(Sound.soundBarSize, volume, volume, 0);
        } else {
            soundPool.stop(barSizeId);
            barSizeId = playSoundPool(Sound.soundBarSize, volume, volume, 0);
        }
    }

    public static int playSoundPool(int id, float left, float right, int loop){
        if (SaveGame.saveGame == null || SaveGame.saveGame.sound) {
                if (soundPool != null) {
                    //Log.e(TAG, "Tocando o som " + id + "volume " + left + " ; "+ right);
                        return soundPool.play(id, left * 1f, right * 1f, 0, loop, 1);
                } else {
                    //Log.e(TAG, "Não tocando o som. SoundPool nulo.");
                    return -1;
                }
        } else {
            //Log.e(TAG, "Não tocando o som. SaveGame.saveGame.sound = false.");
            return -1;
        }

    }



    public static void pauseAll(){
        if (soundPool != null) {
            soundPool.autoPause();
        }

        pauseMusic();

    }

    static boolean musicaPaused = false;

    public static void pauseMusic(){
        if (mediaPlayer[currentMediaNumber] != null && mediaPlayer[currentMediaNumber].isPlaying()) {
            mediaPlayer[currentMediaNumber].pause();
            musicaPaused = true;
        }
    }

    public static void stopAndReleaseTrack(AudioTrack at){
        if (at != null){
            at.stop();
            at.release();
        }
    }

    public static void releaseAll(){
        if (soundPool != null) {
            soundPool.release();
        }

        stopAndReleaseMusic();

        stopAndReleaseTrack(mAudioTrack1);
        stopAndReleaseTrack(mAudioTrack2);
        stopAndReleaseTrack(mAudioTrack3);
        stopAndReleaseTrack(mAudioTrack11);
        stopAndReleaseTrack(mAudioTrack12);
        stopAndReleaseTrack(mAudioTrack13);
        stopAndReleaseTrack(mAudioTrack14);
        stopAndReleaseTrack(mAudioTrack15);
    }


    public static int returningFromPause = 6;

    public static int checkLoopNumber = 0;


    public static void checkLoopPlaying(){

        if (!SaveGame.saveGame.music){
            return;
        }

        if (GameStateHandler.gameState == GameStateHandler.GAME_STATE_JOGAR){


            if (returningFromPause <= 1){
                //Log.e(TAG, "escapando da verificar do looping " + returningFromPause);
                returningFromPause +=1;
                return;
            }

            boolean anyMediaPlaying = false;

            try {

                if (mediaPlayer[currentMediaNumber] != null && mediaPlayer[currentMediaNumber].isPlaying()){
                    Log.e(TAG, "isPLaying " + currentMediaNumber);
                    anyMediaPlaying = true;
                }

            } catch (Exception e){
                Log.e(TAG, "error on check loop playing " + e.getMessage());
            }

            if (!anyMediaPlaying){
                if (checkLoopNumber == 0){
                    checkLoopNumber = 1;
                } else if (checkLoopNumber == 1){
                    checkLoopNumber = 0;
                    stopAndReleaseMusic();
                    Game.sound.playMusic();
                }
            }
             else {
                if (checkLoopNumber == 1){
                    checkLoopNumber = 0;
                }
            }
        }

        /* retirar -------------
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
        */


    }

    public static int currentMediaNumber = 0;

    private static float musicVolume = 1f;

    public static void setMusicVolume(float volume){

        musicVolume = volume;

        if (volume <= 0f){
            stopAndReleaseMusic();
        }

        if (mediaPlayer[currentMediaNumber] != null){
            mediaPlayer[currentMediaNumber].setVolume(musicVolume, musicVolume);
        }

        if (mediaPlayer[getNextMediaPlayer()] != null){
            mediaPlayer[getNextMediaPlayer()].setVolume(musicVolume, musicVolume);
        }

    }

    public void initializeMediaPlayers(){

        mediaPlayer[0] = new MediaPlayer();
        mediaPlayer[1] = new MediaPlayer();
        mediaPlayer[2] = new MediaPlayer();
        mediaPlayer[3] = new MediaPlayer();
        mediaPlayer[4] = new MediaPlayer();
        mediaPlayer[5] = new MediaPlayer();

    }


    public static int getNextMediaPlayer(){

        if (currentMediaNumber == 2) {
            return 0;
        } else {
            return currentMediaNumber + 1;
        }
        //return currentMediaNumber != 5 ? currentMediaNumber + 1 : 0;
    }

    public static int getNextNextMediaPlayer(){

        if (currentMediaNumber == 5) return 1;
        if (currentMediaNumber == 4) return 0;
        return currentMediaNumber + 2;
    }

    public static int getLastMediaPlayerForRelease(){

        if (currentMediaNumber == 0){
            return 4;
        } else if (currentMediaNumber == 1){
            return 5;
        } else if (currentMediaNumber == 2){
            return 0;
        } else if (currentMediaNumber == 3){
            return 1;
        } else if (currentMediaNumber == 4){
            return 2;
        } else if (currentMediaNumber == 5){
            return 3;
        }

        return 0;

    }


    public static void stopAndReleaseMusic(){

        try {
            if (mediaPlayer[0] != null){
                mediaPlayer[0].stop();
                mediaPlayer[0].reset();
                mediaPlayer[0].release();
                mediaPlayer[0] = null;
            }
        } catch (Exception e) {
        }

        try {
            if (mediaPlayer[1] != null){
                mediaPlayer[1].stop();
                mediaPlayer[1].reset();
                mediaPlayer[1].release();
                mediaPlayer[1] = null;
            }
        } catch (Exception e) {
        }

        try {
            if (mediaPlayer[2] != null){
                mediaPlayer[2].stop();
                mediaPlayer[2].reset();
                mediaPlayer[2].release();
                mediaPlayer[2] = null;
            }
        } catch (Exception e) {
        }


        try {
            if (mediaPlayer[3] != null){
                mediaPlayer[3].stop();
                mediaPlayer[3].reset();
                mediaPlayer[3].release();
                mediaPlayer[3] = null;
            }
        } catch (Exception e) {
        }

        try {
            if (mediaPlayer[4] != null){
                mediaPlayer[4].stop();
                mediaPlayer[4].reset();
                mediaPlayer[4].release();
                mediaPlayer[4] = null;
            }
        } catch (Exception e) {
        }

        try {
            if (mediaPlayer[5] != null){
                mediaPlayer[5].stop();
                mediaPlayer[5].reset();
                mediaPlayer[5].release();
                mediaPlayer[5] = null;
            }
        } catch (Exception e) {
        }


    }

    public void playMusic(){

        Log.e(TAG, "playMusic");

        if (mediaPlayer[currentMediaNumber] == null){

            Log.e(TAG, "mediaPlayer[currentMediaNumber] == null");

            musicaPaused = false;
            stopAndReleaseMusic();
            initializeMediaPlayers();
        }

        if (musicaPaused){

            Log.e(TAG, "musicaPaused");

            musicaPaused = false;
            mediaPlayer[currentMediaNumber].start();
            return;
        }

        currentMediaNumber = 0;

        AssetFileDescriptor afd;

        try {

            afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
            mediaPlayer[currentMediaNumber].setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mediaPlayer[currentMediaNumber].prepareAsync();
            mediaPlayer[currentMediaNumber].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.reset();
                    currentMediaNumber = getNextMediaPlayer();
                    createNextMediaPlayer();
                }
            });
            mediaPlayer[currentMediaNumber].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setVolume(musicVolume, musicVolume);
                    mediaPlayer.start();
                    createSecondMediaPlayer();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

            stopAndReleaseMusic();
            playMusic();


        }

    }

    public void createSecondMediaPlayer(){

        Log.e(TAG, "criando segundo media player: " + getNextMediaPlayer());

        AssetFileDescriptor afd;

        try {

            afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
            mediaPlayer[getNextMediaPlayer()].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer[getNextMediaPlayer()].prepareAsync();
            mediaPlayer[getNextMediaPlayer()].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.e(TAG, "onCompletion : " + currentMediaNumber);
                    mediaPlayer.reset();
                    currentMediaNumber = getNextMediaPlayer();
                    createNextMediaPlayer();
                }
            });

            mediaPlayer[getNextMediaPlayer()].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.e(TAG, "setando proximo next : " + getNextMediaPlayer());
                    if (mediaPlayer[currentMediaNumber] != null && mediaPlayer[currentMediaNumber].isPlaying()) {
                        mediaPlayer[getNextMediaPlayer()].setVolume(musicVolume, musicVolume);
                        mediaPlayer[currentMediaNumber].setNextMediaPlayer(mediaPlayer[getNextMediaPlayer()]);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

            stopAndReleaseMusic();
            playMusic();
        }

    }


    public void createNextMediaPlayer(){

        Log.e(TAG, "criando proximo media player: " + getNextMediaPlayer());

        AssetFileDescriptor afd;

        try {

            afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());

            mediaPlayer[getNextMediaPlayer()].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer[getNextMediaPlayer()].prepareAsync();
            mediaPlayer[getNextMediaPlayer()].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Log.e(TAG, "onCompletion : " + currentMediaNumber);
                    mediaPlayer.reset();
                    currentMediaNumber = getNextMediaPlayer();
                    createNextMediaPlayer();
                }
            });

            mediaPlayer[getNextMediaPlayer()].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.e(TAG, "setando proximo next : " + getNextMediaPlayer());
                    if (mediaPlayer[currentMediaNumber] != null && mediaPlayer[currentMediaNumber].isPlaying()) {
                        mediaPlayer[getNextMediaPlayer()].setVolume(musicVolume, musicVolume);
                        mediaPlayer[currentMediaNumber].setNextMediaPlayer(mediaPlayer[getNextMediaPlayer()]);
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();

            stopAndReleaseMusic();
            playMusic();
        }


        /*
        if (!mediaPlayerPrepared[getNextNextMediaPlayer()]) {


            try {
                afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());

                mediaPlayer[getNextNextMediaPlayer()].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

                mediaPlayer[getNextNextMediaPlayer()].prepareAsync();

                mediaPlayer[getNextNextMediaPlayer()].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //Log.e(TAG, "setando proximo (next next : " + getNextNextMediaPlayer());
                        if (mediaPlayer[getNextMediaPlayer()] != null) {
                            mediaPlayer[getNextNextMediaPlayer()].setVolume(musicVolume, musicVolume);
                            mediaPlayer[getNextMediaPlayer()].setNextMediaPlayer(mediaPlayer[getNextNextMediaPlayer()]);
                        }
                    }
                });

                mediaPlayer[currentMediaNumber].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Log.e(TAG, "next next completado ");
                        mp.release();
                        currentMediaNumber = getNextMediaPlayer();
                        createNextMediaPlayer();

                    }
                });

                mediaPlayerPrepared[getNextNextMediaPlayer()] = true;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        */

    }

    public static int musicCurrentPart;
    public static final int MUSIC_PRE_INTRO = 9;
    public static final int MUSIC_INTRO = 10;
    public static final int MUSIC_INTRO_TO_PART_A = 11;
    public static final int MUSIC_PART_A = 12;
    public static final int MUSIC_PART_B = 13;
    public static final int MUSIC_PART_C = 14;
    public static final int MUSIC_PART_A_TO_PART_A = 15;
    public static final int MUSIC_PART_A_TO_PART_B = 16;
    public static final int MUSIC_PART_B_TO_PART_B = 17;
    public static final int MUSIC_PART_B_TO_PART_C = 18;
    public static final int MUSIC_PART_C_TO_PART_C = 19;

    public static int musicCurrentGlobalPart;
    public static final int MUSIC_GLOBAL_PART_A = 50;
    public static final int MUSIC_GLOBAL_PART_B = 51;
    public static final int MUSIC_GLOBAL_PART_C = 52;

    public static int musicCurrentSubPart;
    public static final int MUSIC_SUB_PART_A_A1 = 20;
    public static final int MUSIC_SUB_PART_A_A2 = 21;
    public static final int MUSIC_SUB_PART_A_A3 = 22;
    public static final int MUSIC_SUB_PART_B_A1 = 23;
    public static final int MUSIC_SUB_PART_B_A2 = 24;
    public static final int MUSIC_SUB_PART_B_A3 = 25;
    public static final int MUSIC_SUB_PART_C_A1 = 26;
    public static final int MUSIC_SUB_PART_C_A2 = 27;
    public static final int MUSIC_SUB_PART_C_A3 = 28;

    public static boolean musicMelodyMode = false;

    public static String getNextMusicFileName(){

        int levelNumber = SaveGame.saveGame.currentLevelNumber;

        int musicNumber = 2;
        if (levelNumber % 2 == 1){
            musicNumber = 1;
        }

        //Log.e(TAG, "---- ANTES " + "GlobalPart " + musicCurrentGlobalPart + "; SubPart " + musicCurrentSubPart + "; Part " + musicCurrentPart + "; melody " + musicMelodyMode + "; musica " + musicNumber);

        String nomeDoArquivo = "00-Intro.ogg";

        if (musicNumber == 2){
            nomeDoArquivo = "[Track02]-00-Intro.ogg";
        }


        if (musicCurrentPart == MUSIC_PRE_INTRO){
            musicCurrentPart = MUSIC_INTRO;
            return nomeDoArquivo;
        }

        // se estiver na introdução, parteA, B ou C, deverá decidir qual será a próxima parte global
        if (musicCurrentPart == MUSIC_INTRO || musicCurrentPart == MUSIC_PART_A || musicCurrentPart == MUSIC_PART_B || musicCurrentPart == MUSIC_PART_C) {
            //Log.e(TAG, "// se estiver na introdução, parteA, B ou C, deverá decidir qual será a próxima parte global");

            float percentageOfTargets = 1f;
            if (Game.numberOfTargets > 0) {
                percentageOfTargets = (float) Game.numberOfTargetsAlives / (float) Game.numberOfTargets;
            }

            int nextGlobalPart;

            // CALCULA A PROXIMA PARTE GLOBAL, DE ACORDO COM O NUMERO DE ALVOS
            if (musicCurrentPart == MUSIC_INTRO){
                // se for introdução independe do número de alvos, sempre vai para parte global A
                nextGlobalPart = MUSIC_GLOBAL_PART_A;
            } else {
                // se não for transição, calcula de acordo com o número de alvos
                if (percentageOfTargets > 0.9f) {
                    nextGlobalPart = MUSIC_GLOBAL_PART_A;
                } else if (percentageOfTargets > 0.45f) {
                    nextGlobalPart  = MUSIC_GLOBAL_PART_B;
                } else {
                    nextGlobalPart = MUSIC_GLOBAL_PART_C;
                    //como não há transição direta da fase A para C, ajusta a próxima parte para B
                    if (musicCurrentGlobalPart == MUSIC_GLOBAL_PART_A){
                        nextGlobalPart = MUSIC_GLOBAL_PART_B;
                    }
                }
            }

            //Log.e(TAG, "nextGlobalPart " + nextGlobalPart);


            // CALCULA A PROXIMA SUBPARTE, RANDOMICAMENTE
            int nextSubPart = 0;

            float random = Utils.getRandonFloat(0f, 1f);

            if (musicNumber == 1) {

                if (nextGlobalPart == MUSIC_GLOBAL_PART_A) {

                    if (random > 0.66f) {
                        nextSubPart = MUSIC_SUB_PART_A_A1;
                    } else if (random > 0.33f) {
                        nextSubPart = MUSIC_SUB_PART_A_A2;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_A_A3;
                    }

                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_B) {
                    if (random > 0.66f) {
                        nextSubPart = MUSIC_SUB_PART_B_A1;
                    } else if (random > 0.33f) {
                        nextSubPart = MUSIC_SUB_PART_B_A2;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_B_A3;
                    }

                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_C) {
                    if (random > 0.66f) {
                        nextSubPart = MUSIC_SUB_PART_C_A1;
                    } else if (random > 0.33f) {
                        nextSubPart = MUSIC_SUB_PART_C_A2;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_C_A3;
                    }
                }
            } else {

                if (nextGlobalPart == MUSIC_GLOBAL_PART_A) {
                    if (random < 0.5f) {
                        nextSubPart = MUSIC_SUB_PART_A_A1;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_A_A2;
                    }

                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_B) {
                    if (random < 0.5f) {
                        nextSubPart = MUSIC_SUB_PART_B_A1;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_B_A2;
                    }
                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_C) {
                    if (random < 0.5f) {
                        nextSubPart = MUSIC_SUB_PART_C_A1;
                    } else {
                        nextSubPart = MUSIC_SUB_PART_C_A2;
                    }
                }

            }

           // Log.e(TAG, "nextSubPart " + nextSubPart);


            // DEFINE A VARIAVEL GLOBAL PART
            musicCurrentGlobalPart = nextGlobalPart;


            // DEFINE A PROXIMA PARTE DE ACORDO COM OS DADOS CALCULADOS ACIMA
            if (musicCurrentPart == MUSIC_INTRO){
                if (musicNumber == 1) {
                    musicCurrentPart = MUSIC_INTRO_TO_PART_A;
                } else {
                    musicCurrentPart = MUSIC_PART_A;
                }
            } else if (musicCurrentPart == MUSIC_PART_A) {
                if (nextGlobalPart == MUSIC_GLOBAL_PART_A){
                    musicCurrentPart = MUSIC_PART_A_TO_PART_A;
                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_B){
                    musicCurrentPart = MUSIC_PART_A_TO_PART_B;
                    if (nextSubPart == MUSIC_SUB_PART_B_A2 || nextSubPart == MUSIC_SUB_PART_B_A3){
                        //Log.e(TAG, "ajeitando sub parte na transição para variacao A1");
                        nextSubPart = MUSIC_SUB_PART_B_A1;
                    }
                }
            } else if (musicCurrentPart == MUSIC_PART_B || musicCurrentPart == MUSIC_PART_A_TO_PART_B) {
                if (nextGlobalPart == MUSIC_GLOBAL_PART_B){
                    musicCurrentPart = MUSIC_PART_B_TO_PART_B;
                } else if (nextGlobalPart == MUSIC_GLOBAL_PART_C){
                    musicCurrentPart = MUSIC_PART_B_TO_PART_C;

                    if (nextSubPart == MUSIC_SUB_PART_C_A2 || nextSubPart == MUSIC_SUB_PART_C_A3){
                       // Log.e(TAG, "ajeitando sub parte na transição para variacao A1");
                        nextSubPart = MUSIC_SUB_PART_C_A1;
                    }

                }
            } else if (musicCurrentPart == MUSIC_PART_C || musicCurrentPart == MUSIC_PART_B_TO_PART_C) {
                    musicCurrentPart = MUSIC_PART_C_TO_PART_C;
            }

            //Log.e(TAG, "musicCurrentPart " + musicCurrentPart);

            boolean transicaoSaida = false;

            // DEFINE O ARQUIVO DE ACORDO COM A SITUAÇÃO DEFINIDA ACIMA
            // DEFINE TAMBÉM A SUBPARTE

            // INTRODUÇÃO PARA PARTE A
            if (musicCurrentPart == MUSIC_INTRO_TO_PART_A){

                if (nextSubPart == MUSIC_SUB_PART_A_A1){
                    nomeDoArquivo = "00tr-Intro-Aa1.ogg";
                } else if (nextSubPart == MUSIC_SUB_PART_A_A2){
                    nomeDoArquivo = "00tr-Intro-Aa2.ogg";
                } else if (nextSubPart == MUSIC_SUB_PART_A_A3){
                    nomeDoArquivo = "00tr-Intro-Aa3.ogg";
                }

            } else if (musicCurrentPart == MUSIC_PART_A_TO_PART_A){

                // PARTE A PARA A
                if (musicCurrentSubPart == MUSIC_SUB_PART_A_A1){
                    if (nextSubPart == MUSIC_SUB_PART_A_A1){
                        nomeDoArquivo = musicNumber == 1 ? "01tr-Aa1-Aa1.ogg" : "[Track02]-01tr-Aa1-Aa1.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A2){
                        nomeDoArquivo = musicNumber == 1 ? "01tr-Aa1-Aa2.ogg" : "[Track02]-01tr-Aa1-Aa2.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A3){
                        nomeDoArquivo = "01tr-Aa1-Aa3.ogg";
                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A2){
                    if (nextSubPart == MUSIC_SUB_PART_A_A1){
                        nomeDoArquivo = musicNumber == 1 ? "01tr-Aa2-Aa1.ogg" : "[Track02]-01tr-Aa2-Aa1.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A2){
                        nomeDoArquivo = musicNumber == 1 ? "01tr-Aa2-Aa2.ogg" : "[Track02]-01tr-Aa2-Aa2.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A3){
                        nomeDoArquivo = "01tr-Aa2-Aa3.ogg";
                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A3){
                    if (nextSubPart == MUSIC_SUB_PART_A_A1){
                        nomeDoArquivo = "01tr-Aa3-Aa1.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A2){
                        nomeDoArquivo = "01tr-Aa3-Aa2.ogg";
                    } else if (nextSubPart == MUSIC_SUB_PART_A_A3){
                        nomeDoArquivo = "01tr-Aa3-Aa3.ogg";
                    }
                }

            } else if (musicCurrentPart == MUSIC_PART_A_TO_PART_B) {

                // SE FOR ENTRAR TRANSIÇÃO PARA PARTE A OU B, DECIDE SE VAI ENTRAR A VARIAÇÃO MELODY PARA O SEGUNDO TRACK


                float melodyVariation = 0.3f - (0.3f * ((float)levelNumber / 100f));

                //Log.e(TAG, "melodyVariation " + melodyVariation);

                if (Utils.getRandonFloat(0f, 1f) < (0.6f + melodyVariation)){
                    musicMelodyMode = false;
                } else {
                    musicMelodyMode = true;
                }

                // PARTE A PARA B
                if (musicMelodyMode) {
                    if (musicCurrentSubPart == MUSIC_SUB_PART_A_A1) {
                        nomeDoArquivo = musicNumber == 1 ? "01z-Transition-Aa1-Ba1.ogg" : "[Track02]-01z_melody-Transition-Aa1-Ba1.ogg";
                    } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A2) {
                        nomeDoArquivo = musicNumber == 1 ? "01z-Transition-Aa2-Ba1.ogg" : "[Track02]-01z_melody-Transition-Aa2-Ba1.ogg";
                    } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A3) {
                        nomeDoArquivo = "01z-Transition-Aa3-Ba1.ogg";
                    }
                } else {
                    if (musicCurrentSubPart == MUSIC_SUB_PART_A_A1) {
                        nomeDoArquivo = musicNumber == 1 ? "01z-Transition-Aa1-Ba1.ogg" : "[Track02]-01z-Transition-Aa1-Ba1.ogg";
                    } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A2) {
                        nomeDoArquivo = musicNumber == 1 ? "01z-Transition-Aa2-Ba1.ogg" : "[Track02]-01z-Transition-Aa2-Ba1.ogg";
                    } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A3) {
                        nomeDoArquivo = "01z-Transition-Aa3-Ba1.ogg";
                    }
                }
            } else if (musicCurrentPart == MUSIC_PART_B_TO_PART_B){

                // SE FOR ENTRAR TRANSIÇÃO PARA PARTE B OU C, DECIDE SE VAI ENTRAR A VARIAÇÃO MELODY


                float melodyVariation = 0.3f - (0.3f * ((float) levelNumber / 100f));
                //Log.e(TAG, "melodyVariation " + melodyVariation);

                if (Utils.getRandonFloat(0f, 1f) < (0.6f + melodyVariation)){
                    if (musicMelodyMode){
                        transicaoSaida = true;
                    }
                    musicMelodyMode = false;
                } else {
                    musicMelodyMode = true;
                }

                // PARTE B PARA B
                if (musicCurrentSubPart == MUSIC_SUB_PART_B_A1){
                    if (nextSubPart == MUSIC_SUB_PART_B_A1){
                        if (musicMelodyMode){
                            nomeDoArquivo = musicNumber == 1 ? "02tr_melody-Ba1-Ba1.ogg" :"[Track02]-02tr_melody-Ba1-Ba1.ogg";
                        } else {
                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba1-Ba1.ogg" : "[Track02]-02trRtn_melody-Ba1-Ba1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba1-Ba1.ogg" : "[Track02]-02tr-Ba1-Ba1.ogg";
                            }

                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_B_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "02tr_melody-Ba1-Ba2.ogg" : "[Track02]-02tr_melody-Ba1-Ba2.ogg";
                        } else {

                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba1-Ba2.ogg" : "[Track02]-02trRtn_melody-Ba1-Ba2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba1-Ba2.ogg" : "[Track02]-02tr-Ba1-Ba2.ogg";
                            }

                        }
                    } else if (nextSubPart == MUSIC_SUB_PART_B_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "02tr_melody-Ba1-Ba3.ogg";
                        } else {
                            nomeDoArquivo = "02tr-Ba1-Ba3.ogg";
                        }

                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_B_A2){
                    if (nextSubPart == MUSIC_SUB_PART_B_A1){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "02tr_melody-Ba2-Ba1.ogg" : "[Track02]-02tr_melody-Ba2-Ba1.ogg";
                        } else {

                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba2-Ba1.ogg" : "[Track02]-02trRtn_melody-Ba2-Ba1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba2-Ba1.ogg" : "[Track02]-02tr-Ba2-Ba1.ogg";
                            }

                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_B_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "02tr_melody-Ba2-Ba2.ogg" : "[Track02]-02tr_melody-Ba2-Ba2.ogg";
                        } else {

                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba2-Ba2.ogg" : "[Track02]-02trRtn_melody-Ba2-Ba2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02tr-Ba2-Ba2.ogg" : "[Track02]-02tr-Ba2-Ba2.ogg";
                            }

                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_B_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "02tr_melody-Ba2-Ba3.ogg";
                        } else {
                            nomeDoArquivo = "02tr-Ba2-Ba3.ogg";
                        }

                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_B_A3){
                    if (nextSubPart == MUSIC_SUB_PART_B_A1){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "02tr_melody-Ba3-Ba1.ogg";
                        } else {
                            nomeDoArquivo = "02tr-Ba3-Ba1.ogg";
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_B_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "02tr_melody-Ba3-Ba2.ogg";
                        } else {
                            nomeDoArquivo = "02tr-Ba3-Ba2.ogg";
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_B_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "02tr_melody-Ba3-Ba3.ogg";
                        } else {
                            nomeDoArquivo = "02tr-Ba3-Ba3.ogg";
                        }

                    }
                }
            } else if (musicCurrentPart == MUSIC_PART_B_TO_PART_C) {
                // PARTE B PARA C

                // SE FOR ENTRAR TRANSIÇÃO PARA PARTE B OU C, DECIDE SE VAI ENTRAR A VARIAÇÃO MELODY


                if (!musicMelodyMode) {
                    float melodyVariation = 0.3f - (0.3f * ((float) levelNumber / 100f));
                    //Log.e(TAG, "melodyVariation " + melodyVariation);

                    if (Utils.getRandonFloat(0f, 1f) < (0.55f + melodyVariation)){
                        musicMelodyMode = false;
                    } else {
                        musicMelodyMode = true;
                    }
                }

                if (musicCurrentSubPart == MUSIC_SUB_PART_B_A1) {
                    if (musicMelodyMode) {
                        nomeDoArquivo = musicNumber == 1 ? "02z_melody-Transition-Ba1-Ca1.ogg" : "[Track02]-02z_melody-Transition-Ba1-Ca1.ogg";
                    } else {
                        nomeDoArquivo = musicNumber == 1 ? "02z-Transition-Ba1-Ca1.ogg" : "[Track02]-02z-Transition-Ba1-Ca1.ogg";
                    }
                    
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_B_A2) {
                    if (musicMelodyMode) {
                        nomeDoArquivo = musicNumber == 1 ? "02z_melody-Transition-Ba2-Ca1.ogg" : "[Track02]-02z_melody-Transition-Ba2-Ca1.ogg";
                    } else {
                        nomeDoArquivo = musicNumber == 1 ? "02z-Transition-Ba2-Ca1.ogg" : "[Track02]-02z-Transition-Ba2-Ca1.ogg";
                    }
                    
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_B_A3) {
                    if (musicMelodyMode) {
                        nomeDoArquivo = "02z_melody-Transition-Ba3-Ca1.ogg";
                    } else {
                        nomeDoArquivo = "02z-Transition-Ba3-Ca1.ogg";
                    }
                    
                }

            } else if (musicCurrentPart == MUSIC_PART_C_TO_PART_C){
                // PARTE C PARA C


                // SE FOR ENTRAR TRANSIÇÃO PARA PARTE B OU C, DECIDE SE VAI ENTRAR A VARIAÇÃO MELODY

                float melodyVariation = 0.3f - (0.3f * ((float) levelNumber / 100f));
                //Log.e(TAG, "melodyVariation " + melodyVariation);
                if (Utils.getRandonFloat(0f, 1f) < (0.5f + melodyVariation)){
                    if (musicMelodyMode){
                        transicaoSaida = true;
                    }
                    musicMelodyMode = false;
                } else {
                    musicMelodyMode = true;
                }


                if (musicCurrentSubPart == MUSIC_SUB_PART_C_A1){
                    if (nextSubPart == MUSIC_SUB_PART_C_A1){
                        if (musicMelodyMode){
                            nomeDoArquivo = musicNumber == 1 ? "03tr_melody-Ca1-Ca1.ogg" : "[Track02]-03tr_melody-Ca1-Ca1.ogg";
                        } else {
                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca1-Ca1.ogg" : "[Track02]-03trRtn_melody-Ca1-Ca1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca1-Ca1.ogg" : "[Track02]-03tr-Ca1-Ca1.ogg";
                            }

                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_C_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "03tr_melody-Ca1-Ca2.ogg" : "[Track02]-03tr_melody-Ca1-Ca2.ogg";
                        } else {
                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca1-Ca2.ogg" : "[Track02]-03trRtn_melody-Ca1-Ca2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca1-Ca2.ogg" : "[Track02]-03tr-Ca1-Ca2.ogg";
                            }


                        }
                    } else if (nextSubPart == MUSIC_SUB_PART_C_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "03tr_melody-Ca1-Ca3.ogg";
                        } else {
                            nomeDoArquivo = "03tr-Ca1-Ca3.ogg";
                        }

                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_C_A2){
                    if (nextSubPart == MUSIC_SUB_PART_C_A1){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "03tr_melody-Ca2-Ca1.ogg" : "[Track02]-03tr_melody-Ca2-Ca1.ogg";
                        } else {
                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca2-Ca1.ogg" : "[Track02]-03trRtn_melody-Ca2-Ca1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca2-Ca1.ogg" : "[Track02]-03tr-Ca2-Ca1.ogg";
                            }
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_C_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = musicNumber == 1 ? "03tr_melody-Ca2-Ca2.ogg" : "[Track02]-03tr_melody-Ca2-Ca2.ogg";
                        } else {
                            if (transicaoSaida){
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca2-Ca2.ogg" : "[Track02]-03trRtn_melody-Ca2-Ca2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03tr-Ca2-Ca2.ogg" : "[Track02]-03tr-Ca2-Ca2.ogg";
                            }
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_C_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "03tr_melody-Ca2-Ca3.ogg";
                        } else {
                            nomeDoArquivo = "03tr-Ca2-Ca3.ogg";
                        }

                    }
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_C_A3){
                    if (nextSubPart == MUSIC_SUB_PART_C_A1){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "03tr_melody-Ca3-Ca1.ogg";
                        } else {
                            nomeDoArquivo = "03tr-Ca3-Ca1.ogg";
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_C_A2){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "03tr_melody-Ca3-Ca2.ogg";
                        } else {
                            nomeDoArquivo = "03tr-Ca3-Ca2.ogg";
                        }

                    } else if (nextSubPart == MUSIC_SUB_PART_C_A3){
                        if (musicMelodyMode) {
                            nomeDoArquivo = "03tr_melody-Ca3-Ca3.ogg";
                        } else {
                            nomeDoArquivo = "03tr-Ca3-Ca3.ogg";
                        }
                    }
                }
            }


            // para track02, como não tem transição da introdução para a parte A, pula direto para a parte A
            if (musicCurrentPart == MUSIC_PART_A){
                if (musicCurrentSubPart == MUSIC_SUB_PART_A_A1) {
                    nextSubPart = MUSIC_SUB_PART_A_A1;
                    nomeDoArquivo = "[Track02]-01-Aa1.ogg";
                } else if (musicCurrentSubPart == MUSIC_SUB_PART_A_A2) {
                    nextSubPart = MUSIC_SUB_PART_A_A2;
                    nomeDoArquivo = "[Track02]-01-Aa2.ogg";
                }
            }


            // DEFINE A VARIAVEL CURRENT SUB PART
            musicCurrentSubPart = nextSubPart;

        } else {

            //Log.e(TAG, "// SE ESTIVER NUMA TRANSIÇÃO, PULA PARA A PROXIMA PARTE");

        // SE ESTIVER NUMA TRANSIÇÃO, PULA PARA A PROXIMA PARTE

            switch (musicCurrentPart){
                case MUSIC_INTRO_TO_PART_A:
                    musicCurrentPart = MUSIC_PART_A;
                    break;
                case MUSIC_PART_A_TO_PART_A:
                    musicCurrentPart = MUSIC_PART_A;
                    break;
                case MUSIC_PART_A_TO_PART_B:
                    musicCurrentPart = MUSIC_PART_B;
                    break;
                case MUSIC_PART_B_TO_PART_B:
                    musicCurrentPart = MUSIC_PART_B;
                    break;
                case MUSIC_PART_B_TO_PART_C:
                    musicCurrentPart = MUSIC_PART_C;
                    break;
                case MUSIC_PART_C_TO_PART_C:
                    musicCurrentPart = MUSIC_PART_C;
                    break;
            }

            switch (musicCurrentPart){
                case MUSIC_PART_A:
                    switch (musicCurrentSubPart){
                        case MUSIC_SUB_PART_A_A1:
                            nomeDoArquivo = musicNumber == 1 ? "01-Aa1.ogg" : "[Track02]-01-Aa1.ogg";
                            break;
                        case MUSIC_SUB_PART_A_A2:
                            nomeDoArquivo = musicNumber == 1 ? "01-Aa2.ogg" : "[Track02]-01-Aa2.ogg";
                            break;
                        case MUSIC_SUB_PART_A_A3:
                            nomeDoArquivo = "01-Aa3.ogg";
                            break;
                    }
                    break;
                case MUSIC_PART_B:
                    switch (musicCurrentSubPart){
                        case MUSIC_SUB_PART_B_A1:
                            if (musicMelodyMode){
                                nomeDoArquivo = musicNumber == 1 ? "02melody-Ba1.ogg" : "[Track02]-02melody-Ba1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02-Ba1.ogg" : "[Track02]-02-Ba1.ogg";
                            }

                            break;
                        case MUSIC_SUB_PART_B_A2:
                            if (musicMelodyMode){
                                nomeDoArquivo = musicNumber == 1 ? "02melody-Ba2.ogg" : "[Track02]-02melody-Ba2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "02-Ba2.ogg" : "[Track02]-02-Ba2.ogg";
                            }

                            break;
                        case MUSIC_SUB_PART_B_A3:
                            if (musicMelodyMode){
                                nomeDoArquivo = "02melody-Ba3.ogg";
                            } else {
                                nomeDoArquivo = "02-Ba3.ogg";
                            }

                            break;
                    }
                    break;
                case MUSIC_PART_C:
                    switch (musicCurrentSubPart){
                        case MUSIC_SUB_PART_C_A1:
                            if (musicMelodyMode){
                                nomeDoArquivo = musicNumber == 1 ? "03melody-Ca1.ogg" : "[Track02]-03melody-Ca1.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03-Ca1.ogg" : "[Track02]-03-Ca1.ogg";
                            }

                            break;
                        case MUSIC_SUB_PART_C_A2:
                            if (musicMelodyMode){
                                nomeDoArquivo = musicNumber == 1 ? "03melody-Ca2.ogg" : "[Track02]-03melody-Ca2.ogg";
                            } else {
                                nomeDoArquivo = musicNumber == 1 ? "03-Ca2.ogg" : "[Track02]-03-Ca2.ogg";
                            }

                            break;
                        case MUSIC_SUB_PART_C_A3:
                            if (musicMelodyMode){
                                nomeDoArquivo = "03melody-Ca3.ogg";
                            } else {
                                nomeDoArquivo = "03-Ca3.ogg";
                            }

                            break;
                    }
                    break;
            }
        }

        //Log.e(TAG, "---- DEPOIS " + "GlobalPart " + musicCurrentGlobalPart + "; SubPart " + musicCurrentSubPart + "; Part " + musicCurrentPart + "; melody " + musicMelodyMode);
        Log.e(TAG, "nomeDoPróximoArquivo " + nomeDoArquivo);

        return nomeDoArquivo;

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
                Log.e(TAG, "Erro ao abrir o arquivo "+ fileName);
                e.printStackTrace();
            }
        }
    }

    private class PlayAudio extends AsyncTask<AudioData, Integer, Integer> {
        byte[] musicPart = new byte[1024];
        public AudioTrack track;
        int lastMusicPart = -1;

        int minSize = AudioTrack.getMinBufferSize(44100,
                AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT);

        int minSize_22050_or_mono = AudioTrack.getMinBufferSize(22050,
                AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT);


        protected Integer doInBackground(AudioData... data) {

            try {

                if (data[0].audioTrackNumber == -1) {

                    track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                            AudioFormat.CHANNEL_OUT_STEREO,
                            AudioFormat.ENCODING_PCM_16BIT, minSize,
                            AudioTrack.MODE_STREAM);
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        if (mAudioTrack11 == null){

                            mAudioTrack11 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_MONO,
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
                    } else if (data[0].audioTrackNumber == 2) {
                        //Log.e(TAG, "Reutilizando audio track "+data[0].fileName);
                        if (mAudioTrack12 == null){

                            mAudioTrack12 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_MONO,
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
                    } else if (data[0].audioTrackNumber == 3) {
                        if (mAudioTrack13 == null){

                            mAudioTrack13 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_STEREO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack13.pause();
                                mAudioTrack13.flush();
                            } else {
                                mAudioTrack13.stop();
                            }
                        }
                    } else if (data[0].audioTrackNumber == 4) {
                        if (mAudioTrack14 == null){

                            mAudioTrack14 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_MONO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack14.pause();
                                mAudioTrack14.flush();
                            } else {
                                mAudioTrack14.stop();
                            }
                        }
                    } else if (data[0].audioTrackNumber == 5) {
                        if (mAudioTrack15 == null){

                            mAudioTrack15 = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                                    AudioFormat.CHANNEL_OUT_MONO,
                                    AudioFormat.ENCODING_PCM_16BIT, minSize,
                                    AudioTrack.MODE_STREAM);
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mAudioTrack15.pause();
                                mAudioTrack15.flush();
                            } else {
                                mAudioTrack15.stop();
                            }
                        }
                    }

                }

                if (data[0].audioTrackNumber == -1) {
                    track.setVolume(data[0].volume);
                    track.play();
                } else {
                    if (data[0].audioTrackNumber == 1) {
                        mAudioTrack11.setVolume(data[0].volume);
                        mAudioTrack11.play();
                    } else
                    if (data[0].audioTrackNumber == 2) {
                        mAudioTrack12.setVolume(data[0].volume);
                        mAudioTrack12.play();
                    } else
                    if (data[0].audioTrackNumber == 3) {
                        mAudioTrack13.setVolume(data[0].volume);
                        mAudioTrack13.play();
                    } else
                    if (data[0].audioTrackNumber == 4) {
                        mAudioTrack14.setVolume(data[0].volume);
                        mAudioTrack14.play();
                    } else
                    if (data[0].audioTrackNumber == 5) {
                        mAudioTrack15.setVolume(data[0].volume);
                        mAudioTrack15.play();
                    }
                }

                boolean continuePlaying = true;
                byte[] musicPartRemain = new byte[1];
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
                            track.setVolume(data[0].volume);
                            track.play();
                        } else {
                            if (data[0].audioTrackNumber == 1) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack11.pause();
                                    mAudioTrack11.flush();
                                }
                                else {
                                    mAudioTrack11.stop();
                                }
                                mAudioTrack11.setVolume(data[0].volume);
                                mAudioTrack11.play();
                            } else
                            if (data[0].audioTrackNumber == 2) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack12.pause();
                                    mAudioTrack12.flush();
                                }
                                else {
                                    mAudioTrack12.stop();
                                }
                                mAudioTrack12.setVolume(data[0].volume);
                                mAudioTrack12.play();
                            } else
                            if (data[0].audioTrackNumber == 3) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack13.pause();
                                    mAudioTrack13.flush();
                                }
                                else {
                                    mAudioTrack13.stop();
                                }
                                mAudioTrack13.setVolume(data[0].volume);
                                mAudioTrack13.play();
                            } else
                            if (data[0].audioTrackNumber == 4) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack14.pause();
                                    mAudioTrack14.flush();
                                }
                                else {
                                    mAudioTrack14.stop();
                                }
                                mAudioTrack14.setVolume(data[0].volume);
                                mAudioTrack14.play();
                            } else
                            if (data[0].audioTrackNumber == 5) {
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    mAudioTrack15.pause();
                                    mAudioTrack15.flush();
                                }
                                else {
                                    mAudioTrack15.stop();
                                }
                                mAudioTrack15.setVolume(data[0].volume);
                                mAudioTrack15.play();
                            }
                        }
                    }

                    //Log.e(TAG, "musicPartNumber " + data[0].musicPartNumber);

                    //Log.e(TAG, "musicPàrt " + new String(musicPart, StandardCharsets.UTF_8));

                    int i;
                    for( i = 0; i < musicPart.length; i++ )
                    {
                        if (i + (musicPart.length * data[0].musicPartNumber) < data[0].music.length) {
                            musicPart[i] = data[0].music[i + (musicPart.length * data[0].musicPartNumber)];
                        } else {
                            //Log.e(TAG, "ultimo lote: tamanho: " + (i - 1));
                            musicPartRemain = new byte[i - 1];
                            for (int j = 0; j < i - 1; j++) {
                                musicPartRemain[j] = musicPart[j];
                            }
                            continuePlaying = false;
                            break;
                        }
                    }

                    data[0].musicPartNumber += 1;
                    lastMusicPart = data[0].musicPartNumber;

                        if (data[0].audioTrackNumber == -1) {
                            if (musicPartRemain.length == 1) {
                                track.write(musicPart, 0, i);
                            } else {
                                track.write(musicPartRemain, 0, i);
                            }
                        } else {
                            if (data[0].audioTrackNumber == 1) {
                                if (musicPartRemain.length == 1) {
                                    mAudioTrack11.write(musicPart, 0, i);
                                } else {
                                    mAudioTrack11.write(musicPartRemain, 0, i);
                                }
                            } else
                            if (data[0].audioTrackNumber == 2) {
                                if (musicPartRemain.length == 1) {
                                    mAudioTrack12.write(musicPart, 0, i);
                                } else {
                                    mAudioTrack12.write(musicPartRemain, 0, i);
                                }
                            } else
                            if (data[0].audioTrackNumber == 3) {
                                if (musicPartRemain.length == 1) {
                                    mAudioTrack13.write(musicPart, 0, i);
                                } else {
                                    mAudioTrack13.write(musicPartRemain, 0, i);
                                }
                            } else
                            if (data[0].audioTrackNumber == 4) {
                                if (musicPartRemain.length == 1) {
                                    mAudioTrack14.write(musicPart, 0, i);
                                } else {
                                    mAudioTrack14.write(musicPartRemain, 0, i);
                                }
                            } else
                            if (data[0].audioTrackNumber == 5) {
                                if (musicPartRemain.length == 1) {
                                    mAudioTrack15.write(musicPart, 0, i);
                                } else {
                                    mAudioTrack15.write(musicPartRemain, 0, i);
                                }
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
                            mAudioTrack11.pause();
                            mAudioTrack11.flush();
                        }
                        else {
                            mAudioTrack11.stop();
                        }
                    } else
                    if (data[0].audioTrackNumber == 2) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack12.pause();
                            mAudioTrack12.flush();
                        }
                        else {
                            mAudioTrack12.stop();
                        }
                    } else
                    if (data[0].audioTrackNumber == 3) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack13.pause();
                            mAudioTrack13.flush();
                        }
                        else {
                            mAudioTrack13.stop();
                        }
                    } else
                    if (data[0].audioTrackNumber == 4) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack14.pause();
                            mAudioTrack14.flush();
                        }
                        else {
                            mAudioTrack14.stop();
                        }
                    } else
                    if (data[0].audioTrackNumber == 5) {
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mAudioTrack15.pause();
                            mAudioTrack15.flush();
                        }
                        else {
                            mAudioTrack15.stop();
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

    public static void loadMusic(){

        currentMediaNumber = 0;
        mediaPlayer[currentMediaNumber] = new MediaPlayer();
        AssetFileDescriptor afd;
        try {
            afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
            mediaPlayer[currentMediaNumber].setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mediaPlayer[currentMediaNumber].prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer[currentMediaNumber].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //Log.e(TAG + "loadMusic", "onPrepared ");
                mediaPlayer[getNextMediaPlayer()] = new MediaPlayer();
                AssetFileDescriptor afd;
                try {
                    afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
                    mediaPlayer[getNextMediaPlayer()].setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mediaPlayer[getNextMediaPlayer()].prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer[currentMediaNumber].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Log.e(TAG + "loadMusic", "setOnCompletionListener ");
                        mp.reset();
                        currentMediaNumber = getNextMediaPlayer();
                        Game.sound.createNextMediaPlayer();

                    }
                });

                mediaPlayer[getNextMediaPlayer()].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //Log.e(TAG + "loadMusic", "getNextMediaPlayer onPrepared");
                        mediaPlayer[currentMediaNumber].setNextMediaPlayer(mediaPlayer[getNextMediaPlayer()]);
                    }
                });
            }
        });



        /*

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

        */
    }

    /*

    private class PlayMusic extends AsyncTask<Void, Integer, Integer> {

        AssetFileDescriptor afd;

        protected Integer doInBackground(Void... data) {

            if (!SaveGame.saveGame.music) {
                return 0;
            }

            if (mediaPlayer[currentMediaNumber] != null) {
                mediaPlayer[currentMediaNumber].start();
            } else {
                currentMediaNumber = 0;
                mediaPlayer[currentMediaNumber] = new MediaPlayer();
                try {
                    afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
                    mediaPlayer[currentMediaNumber].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mediaPlayer[currentMediaNumber].prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                mediaPlayer[currentMediaNumber].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //Log.e(TAG, "onPrepared ");
                        mp.start();
                        createNextMediaPlayer();
                    }
                });

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
            try {
                if (afd != null) {
                    afd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopAndReleaseMusic();
        }
    }

    private class CreateNextMediaPlayer extends AsyncTask<Void, Integer, Integer> {

        AssetFileDescriptor afd;

        protected Integer doInBackground(Void... data) {

            if (mediaPlayer[getLastMediaPlayerForRelease()] != null){
                //Log.e(TAG, "apagando media player anterior : " + getLastMediaPlayerForRelease());
                mediaPlayer[getLastMediaPlayerForRelease()].reset();
                mediaPlayer[getLastMediaPlayerForRelease()].release();
                mediaPlayer[getLastMediaPlayerForRelease()] = null;
            }

            mediaPlayer[getNextMediaPlayer()] = new MediaPlayer();
            try {
                afd = Game.mainActivity.getAssets().openFd(getNextMusicFileName());
                mediaPlayer[getNextMediaPlayer()].setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                mediaPlayer[getNextMediaPlayer()].prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer[getNextMediaPlayer()].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //Log.e(TAG, "setando proximo : " + getNextMediaPlayer());
                    if (mediaPlayer[currentMediaNumber] != null && mediaPlayer[currentMediaNumber].isPlaying()) {
                        mediaPlayer[currentMediaNumber].setNextMediaPlayer(mediaPlayer[getNextMediaPlayer()]);
                    }
                }
            });

            mediaPlayer[currentMediaNumber].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //Log.e(TAG, "media completado ");
                    currentMediaNumber = getNextMediaPlayer();
                    createNextMediaPlayer();

                }
            });
            return 0;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Integer result) {
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            try {
                if (afd != null) {
                    afd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            stopAndReleaseMusic();
        }
    }

    */

}