package com.marcelslum.ultnogame;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;

import java.io.IOException;

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
        Log.e(TAG, "Abrindo Snapshot");
        Snapshots.OpenSnapshotResult result = Games.Snapshots.open(Game.mainActivity.mGoogleApiClient,
                MySnapshots.SNAPSHOT_FILE_NAME, true).await();
        return result;
    }

    @Override
    protected void onPostExecute(Snapshots.OpenSnapshotResult result){

        if (!result.getStatus().isSuccess()) {
            Log.e(TAG, "Error while loading: " + result.getStatus().getStatusCode());
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
    }
    protected void onProgressUpdate(){
        //Codigo
    }
}
