package com.marcelslum.ultnogame;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.games.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



public class MyVIewModel extends ViewModel {

    public MutableLiveData<PlayerData> playerData;

    final static String TAG = "MyVIewModel";

    public MyVIewModel() {
        playerData = new MutableLiveData<>();
    }



    public void loadPlayerData(final Context context){

        Log.e(TAG, "LoadPlayerData");

        GoogleAPI.mPlayersClient.getCurrentPlayer()
                .addOnCompleteListener(new OnCompleteListener<Player>() {
                    @Override
                    public void onComplete(@NonNull Task<Player> task) {
                        if (task.isSuccessful()) {

                            Log.e(TAG, "player name atualizado para " + task.getResult().getDisplayName());
                            GoogleAPI.playerName = task.getResult().getName();
                            GoogleAPI.playerId = task.getResult().getPlayerId();

                            Uri uri = task.getResult().getIconImageUri();
                            Log.e(TAG, "uri " + uri.getPath());
                            ImageManager.create(context)
                                    .loadImage(new ImageManager.OnImageLoadedListener() {
                                        @Override
                                        public void onImageLoaded(Uri uri, Drawable drawable, boolean b) {

                                            Log.e(TAG, "onImageLoaded ");

                                            playerData.setValue(new PlayerData(GoogleAPI.playerName, GoogleAPI.playerId, drawable));
                                            Game.forUpdateNamePlayer = true;

                                        }


                                    }, uri);
                        }
                    }
                });

    }


    class PlayerData{

        String name;
        String playerId;
        Drawable icon;

        public PlayerData(String name, String playerId, Drawable icon) {
            this.name = name;
            this.playerId = playerId;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlayerId() {
            return playerId;
        }

        public void setPlayerId(String playerId) {
            this.playerId = playerId;
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }
    }

}
