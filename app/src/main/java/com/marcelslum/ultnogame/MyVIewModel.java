package com.marcelslum.ultnogame;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.DialogInterface;
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

    ImageManager.OnImageLoadedListener onImageLoadedListener;

    public MyVIewModel() {
        playerData = new MutableLiveData<>();
    }

    ImageManager im;

    public void loadPlayerData(final Context context){

        //Log.e(TAG, "LoadPlayerData");

        onImageLoadedListener = new ImageManager.OnImageLoadedListener() {
            @Override
            public void onImageLoaded(Uri uri, Drawable drawable, boolean b) {
                //Log.e(TAG, "onImageLoaded ");
                playerData.setValue(new PlayerData(GoogleAPI.playerName, GoogleAPI.playerId, drawable));
            }
        };

        im = ImageManager.create(context);

        if (GoogleAPI.mPlayersClient != null) {
            GoogleAPI.mPlayersClient.getCurrentPlayer()
                    .addOnCompleteListener(new OnCompleteListener<Player>() {
                        @Override
                        public void onComplete(@NonNull Task<Player> task) {
                            if (task.isSuccessful()) {

                               // Log.e(TAG, "player name atualizado para " + task.getResult().getDisplayName());

                                String oldPlayerName = GoogleAPI.playerName;

                                GoogleAPI.playerName = task.getResult().getDisplayName();
                                GoogleAPI.playerId = task.getResult().getPlayerId();

                                String oldPlayerId = Storage.getString("playerId");

                                if (oldPlayerId.contains("provisorio")) {
                                   // Log.e(TAG, "currentPlayerId.contains(provisorio)");
                                    String oldName = Game.playerId;
                                    Game.playerId = GoogleAPI.playerId;
                                    Storage.setString("playerId", GoogleAPI.playerId);
                                    SaveGame.saveGame.updatePlayerId(GoogleAPI.playerId, oldName);
                                } else {
                                    if (!oldPlayerId.equals(GoogleAPI.playerId)){
                                        Game.playerId = GoogleAPI.playerId;
                                        Storage.setString("playerId", GoogleAPI.playerId);

                                        SaveGame.load();

                                        MainActivity m = Game.mainActivity;
                                        if (m != null){
                                            m.createMessageDialogBuilder(m.getResources().getString(R.string.conta_google_mudou_titulo),
                                                    m.getResources().getString(R.string.conta_google_mudou_mensagem) + " " + GoogleAPI.playerName + "."
                                                    + m.getResources().getString(R.string.conta_google_mudou_mensagem2) + " '" + oldPlayerName + "'."
                                                    + m.getResources().getString(R.string.conta_google_mudou_mensagem3))
                                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            })
                                            .show();
                                        }
                                    }
                                }

                                Uri uri = task.getResult().getIconImageUri();
                                //Log.e(TAG, "uri " + uri.getPath());
                                im.loadImage(onImageLoadedListener, uri);
                            } else {
                                playerData.setValue(null);
                            }
                        }
                    });
        } else {
            playerData.setValue(null);
        }
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
