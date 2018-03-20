package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by marcel on 11/03/2018.
 */

public class LoopMediaPlayer {

    public static final String TAG = LoopMediaPlayer.class.getSimpleName();

    private Context mContext = null;
    private int mResId = 0;
    private int mResId2 = 0;
    private int mCounter = 1;
    boolean alternate = false;
    int currentResource;
    float volume;

    private MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    public static LoopMediaPlayer create(Context context, int resId, float volume) {
        return new LoopMediaPlayer(context, resId, volume);
    }

    public static LoopMediaPlayer create(Context context, int resId, int resId2, float volume) {
        return new LoopMediaPlayer(context, resId, resId2, volume);
    }

    private LoopMediaPlayer(Context context, int resId, float volume) {
        mContext = context;
        mResId = resId;
        this.volume = volume;
        currentResource = 1;
        alternate = false;

        mCurrentPlayer = MediaPlayer.create(mContext, mResId);
        mCurrentPlayer.setVolume(volume, volume);

        createNextMediaPlayer();
    }

    private LoopMediaPlayer(Context context, int resId1, int resId2, float volume) {
        mContext = context;
        mResId = resId1;
        mResId2 = resId2;
        this.volume = volume;
        currentResource = 1;
        alternate = true;
        mCurrentPlayer = MediaPlayer.create(mContext, mResId);
        createNextMediaPlayer();
    }

    private void createNextMediaPlayer() {
        if (alternate) {
            //Log.e(TAG, "alternate == true");
            if (currentResource == 1) {
                mNextPlayer = MediaPlayer.create(mContext, mResId2);
                currentResource = 2;
            } else if (currentResource == 2) {
                mNextPlayer = MediaPlayer.create(mContext, mResId);
                currentResource = 1;
            }
        } else {
            //Log.e(TAG, "alternate == false");

            if (mNextPlayer != null){
                mNextPlayer.release();
                mNextPlayer = null;
            }

            mNextPlayer = MediaPlayer.create(mContext, mResId);
        }

        mNextPlayer.setVolume(volume, volume);
        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);
        mCurrentPlayer.setVolume(volume, volume);
    }

    public void stopAndRelease(){
        //Log.e(TAG, "stopAndRelease");
        if (mCurrentPlayer != null) {
            try {
                mCurrentPlayer.stop();
            } catch (Exception e) {
            }

            try {
                mCurrentPlayer.release();
            } catch (Exception e) {
            }


        }

        if (mNextPlayer != null) {
            try {
                mNextPlayer.stop();
            } catch (Exception e) {
            }

            try {
                mNextPlayer.release();
            } catch (Exception e) {
            }
        }

        mCurrentPlayer = null;
        mNextPlayer = null;
    }

    public void pause(){
        //Log.e(TAG, "pause");
        if (mCurrentPlayer != null) {
            try {
                if (mCurrentPlayer.isPlaying()) {
                    mCurrentPlayer.pause();
                }
            } catch (Exception e) {
            }
        }
    }

    public void play(){
        //Log.e(TAG, "play");
        if (mCurrentPlayer != null) {

            try {
                mCurrentPlayer.start();
            } catch (Exception e) {
            }
        }
    }

    public void isPlaying(){
        //Log.e(TAG, "play");
        if (mCurrentPlayer != null) {
            mCurrentPlayer.isPlaying();
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
            mCurrentPlayer = mNextPlayer;
            createNextMediaPlayer();
        }
    };
}