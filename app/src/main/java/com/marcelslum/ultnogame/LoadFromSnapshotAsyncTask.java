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

    private final static String SNAPSHOT_FILE_NAME = "ultnoSavedGame";

    private static final String TAG = "LoadFromSnapshotAsync";

    @Override
    protected void onPreExecute(){

    }
    @Override
    protected Snapshots.OpenSnapshotResult doInBackground(String... params) {
        Snapshots.OpenSnapshotResult result = Games.Snapshots.open(Game.mainActivity.mGoogleApiClient,
                SNAPSHOT_FILE_NAME, true).await();
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
        
        Log.e(TAG, data.toString() + " data " + data);
        

        if (data != null || data.){
            SaveGame.onLoadFromSnapshot(data.toString());
        } else {
            SaveGame.onFailLoadFromSnapshot();
        }
        

    }
    protected void onProgressUpdate(){
        //Codigo
    }
}
