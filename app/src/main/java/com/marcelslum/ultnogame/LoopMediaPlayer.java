package com.marcelslum.ultnogame;

import android.content.Context;
import android.media.MediaPlayer;

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
    static float volume;
    static int currentPlayer;

    private static MediaPlayer mPlayer1 = null;
    private static MediaPlayer mPlayer2 = null;

    public static LoopMediaPlayer create(Context context, int resId, float volume) {
        return new LoopMediaPlayer(context, resId, volume);
    }

    public static LoopMediaPlayer create(Context context, int resId, int resId2, float volume) {
        return new LoopMediaPlayer(context, resId, resId2, volume);
    }

    private LoopMediaPlayer(Context context, int resId, float _volume) {
        mContext = context;
        mResId = resId;
        volume = _volume;
        currentResource = 1;
        alternate = false;

        currentPlayer = 1;
        mPlayer1 = MediaPlayer.create(mContext, mResId);
        mPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayer1.setVolume(volume, volume);
            }
        });

        createNextMediaPlayer();
    }

    private LoopMediaPlayer(Context context, int resId1, int resId2, float _volume) {
        mContext = context;
        mResId = resId1;
        mResId2 = resId2;
        volume = _volume;
        currentResource = 1;
        alternate = true;
        currentPlayer = 1;
        mPlayer1 = MediaPlayer.create(mContext, mResId);
        mPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayer1.setVolume(volume, volume);
            }
        });
        createNextMediaPlayer();
    }

    private void createNextMediaPlayer() {

        if (alternate) {

            if (currentResource == 1) {

                if (currentPlayer == 1) {
                    mPlayer2 = MediaPlayer.create(mContext, mResId2);
                } else if (currentPlayer == 2) {
                    mPlayer1 = MediaPlayer.create(mContext, mResId2);
                }

                currentResource = 2;

            } else if (currentResource == 2) {

                if (currentPlayer == 1) {
                    mPlayer2 = MediaPlayer.create(mContext, mResId);
                } else if (currentPlayer == 2) {
                    mPlayer1 = MediaPlayer.create(mContext, mResId);
                }

                currentResource = 1;
            }
        } else {

            if (currentPlayer == 1) {
                mPlayer2 = MediaPlayer.create(mContext, mResId);
            } else if (currentPlayer == 2) {
                mPlayer1 = MediaPlayer.create(mContext, mResId);
            }

        }

        if (currentPlayer == 1) {
            mPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setVolume(volume, volume);
                    mPlayer1.setNextMediaPlayer(mPlayer2);
                }
            });
        } else if (currentPlayer == 2) {
            mPlayer1 = MediaPlayer.create(mContext, mResId);
        }



        mPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(volume, volume);
                mPlayer1.setNextMediaPlayer(mPlayer2);
            }
        });

        mPlayer1.setOnCompletionListener(onCompletionListener);
    }

    public void stopAndRelease(){
        //Log.e(TAG, "stopAndRelease");
        if (mPlayer1 != null) {
            try {
                mPlayer1.stop();
            } catch (Exception e) {
            }

            try {
                mPlayer1.release();
            } catch (Exception e) {
            }


        }

        if (mPlayer2 != null) {
            try {
                mPlayer2.stop();
            } catch (Exception e) {
            }

            try {
                mPlayer2.release();
            } catch (Exception e) {
            }
        }

        mPlayer1 = null;
        mPlayer2 = null;
    }

    public void pause(){
        //Log.e(TAG, "pause");


        if (mPlayer1 != null) {
            try {
                if (mPlayer1.isPlaying()) {
                    mPlayer1.pause();
                }
            } catch (Exception e) {
            }
        }
    }

    public void play(){
        //Log.e(TAG, "playSoundPool");
        if (mPlayer1 != null) {

            try {
                mPlayer1.start();
            } catch (Exception e) {
            }
        }
    }

    public void isPlaying(){
        //Log.e(TAG, "playSoundPool");
        if (mPlayer1 != null) {
            mPlayer1.isPlaying();
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
            mPlayer1 = mPlayer2;
            createNextMediaPlayer();
        }
    };
}