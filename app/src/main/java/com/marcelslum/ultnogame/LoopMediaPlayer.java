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

    private MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    public static LoopMediaPlayer create(Context context, int resId) {
        return new LoopMediaPlayer(context, resId);
    }

    private LoopMediaPlayer(Context context, int resId) {
        mContext = context;
        mResId = resId;

        mCurrentPlayer = MediaPlayer.create(mContext, mResId);
        mCurrentPlayer.setVolume(0.8f, 0.8f);

        createNextMediaPlayer();
    }

    public void addSecondRes(int resId){
        currentResource = 1;
        alternate = true;
        mResId2 = resId;
    }

    private void createNextMediaPlayer() {
        if (alternate) {
            Log.e(TAG, "alternate == true");
            if (currentResource == 1) {
                mNextPlayer = MediaPlayer.create(mContext, mResId2);
                currentResource = 2;
            } else if (currentResource == 2) {
                mNextPlayer = MediaPlayer.create(mContext, mResId);
                currentResource = 1;
            }
        } else {
            Log.e(TAG, "alternate == false");
            mNextPlayer = MediaPlayer.create(mContext, mResId);
        }

        mNextPlayer.setVolume(0.8f, 0.8f);

        mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
        mCurrentPlayer.setOnCompletionListener(onCompletionListener);

        mCurrentPlayer.setVolume(0.8f, 0.8f);
    }

    public void stopAndRelease(){
        Log.e(TAG, "stopAndRelease");
        if (mCurrentPlayer != null) {
            try {
                mCurrentPlayer.stop();
                mCurrentPlayer.release();
            } catch (Exception e) {
            }

        }
        mNextPlayer = null;
    }

    public void pause(){
        Log.e(TAG, "pause");
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
        Log.e(TAG, "play");
        if (mCurrentPlayer != null) {
            mCurrentPlayer.start();
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
            mCurrentPlayer = mNextPlayer;

            createNextMediaPlayer();

            Log.d(TAG, String.format("Loop #%d", ++mCounter));
        }
    };
}