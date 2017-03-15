package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcel on 12/10/2016.
 */

public class LoadFromSnapshotAsyncTask extends AsyncTask<String,Integer,Snapshots.OpenSnapshotResult> {



    private static final String TAG = "LoadFromSnapshotAsync";

    @Override
    protected void onPreExecute(){

    }
    @Override
    protected Snapshots.OpenSnapshotResult doInBackground(String... params) {
        Log.e(TAG, "doInBackground");
        Log.e(TAG, "Abrindo Snapshot");

        if (!Game.mainActivity.mGoogleApiClient.isConnected()){
            Log.e(TAG, "Google não conectado, retornando nulo");
            return null;
        }


        Snapshots.OpenSnapshotResult result = Games.Snapshots.open(Game.mainActivity.mGoogleApiClient,
                MySnapshots.SNAPSHOT_FILE_NAME, true).await(5000L, TimeUnit.MILLISECONDS);
        return result;
    }

    @Override
    protected void onPostExecute(Snapshots.OpenSnapshotResult result){
        Log.e(TAG, "returning onPostExecute()");

        if (!result.getStatus().isSuccess()) {
            Log.e(TAG, "Error while loading: " + result.getStatus().getStatusCode());
            SaveGame.onFailLoadFromSnapshot();
            return;
        }

        if (result == null){
            Log.e(TAG, "result nulo ");
            SaveGame.onFailLoadFromSnapshot();
            return;
        }


        Snapshot snapshot = result.getSnapshot();
        byte[] data;
        try {
            data = snapshot.getSnapshotContents().readFully();
        } catch (IOException e) {
            Log.e(TAG, "Error while reading Snapshot.", e);
            data = null;
        }
        
        Log.e(TAG, data.toString() + " data " + data + " size "+  data.toString().length());
        Log.e(TAG, "testando outra fora de conversão" + new String(data) +".");

        String stringData = new String(data);

        if (stringData.equals("") || stringData.length() < 20){
            SaveGame.onFailLoadFromSnapshot();

        } else {
            SaveGame.onLoadFromSnapshot(stringData);
        }

        // TODO retirar ao final

        /*

        SaveGame.saveGame.starsLevels[0] = 5;
        SaveGame.saveGame.starsLevels[1] = 5;
        SaveGame.saveGame.starsLevels[2] = 5;
        SaveGame.saveGame.starsLevels[3] = 5;
        SaveGame.saveGame.starsLevels[4] = 5;
        SaveGame.saveGame.starsLevels[5] = 5;

*/

        //SaveGame.saveGame.secretLevelsSeen[0] = false;
        //SaveGame.saveGame.secretLevelsSeen[1] = false;
        //SaveGame.saveGame.secretLevelsSeen[2] = false;
        //SaveGame.saveGame.secretLevelsSeen[3] = false;
        //SaveGame.saveGame.secretLevelsSeen[4] = false;

        //SaveGame.saveGame.secretLevelsUnlocked[0] = false;
        //SaveGame.saveGame.secretLevelsUnlocked[1] = false;
        //SaveGame.saveGame.secretLevelsUnlocked[2] = false;
        //SaveGame.saveGame.secretLevelsUnlocked[3] = false;
        //SaveGame.saveGame.secretLevelsUnlocked[4] = false;


        //SaveGame.saveGame.tutorialsViwed[0] = true;
        //SaveGame.saveGame.tutorialsViwed[1] = true;
        //SaveGame.saveGame.tutorialsViwed[2] = true;
        //SaveGame.saveGame.tutorialsViwed[3] = true;
        //SaveGame.saveGame.tutorialsViwed[4] = true;

        SaveGame.save();

    }

    protected void onProgressUpdate(){
        //Codigo
    }
}
