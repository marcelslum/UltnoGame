package com.marcelslum.ultnogame;

import android.os.AsyncTask;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;

import java.util.concurrent.TimeUnit;

/**
 * Created by marcel on 12/10/2016.
 */

public class OpenSnapshotAsyncTask extends AsyncTask<String,Integer,Snapshots.OpenSnapshotResult> {

    private final static String SNAPSHOT_FILE_NAME = "ultnoSavedGame";

    private static final String TAG = "OpenSnapshotAsyncTask";

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
        Snapshot toWrite = MySnapshots.processSnapshotOpenResult(result, 0);
        MySnapshots.writeSnapshot(toWrite);

    }
    protected void onProgressUpdate(){
        //Codigo
    }
}
