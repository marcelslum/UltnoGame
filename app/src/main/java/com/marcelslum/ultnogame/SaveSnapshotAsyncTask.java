package com.marcelslum.ultnogame;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by marcel on 12/10/2016.
 */

public class SaveSnapshotAsyncTask extends AsyncTask<String,Integer,Integer> {

    private static final String TAG = "OpenSnapshotAsyncTask";

    @Override
    protected void onPreExecute(){

    }
    @Override
    protected Integer doInBackground(String... save) {
        Snapshots.OpenSnapshotResult result = Games.Snapshots.open(Game.mainActivity.mGoogleApiClient,
                MySnapshots.SNAPSHOT_FILE_NAME, true).await();

        Log.e(TAG, "Salvando na nuvem");
        Log.e(TAG, "save "+save);

        Snapshot toWrite = null;
        Snapshot mResolvedSnapshot = null;

        Snapshots.OpenSnapshotResult resolveResult = result;

        for (int i = 0; i < 5; i++) {
            int status = resolveResult.getStatus().getStatusCode();
            Log.e(TAG, "Save Result status: " + status);
            if (status == GamesStatusCodes.STATUS_OK) {
                Log.e(TAG, "Save Result status: " + "STATUS_OK");
                toWrite = resolveResult.getSnapshot();
                i += 5;
            } else if (status == GamesStatusCodes.STATUS_SNAPSHOT_CONTENTS_UNAVAILABLE) {
                Log.e(TAG, "Save Result status: " + "STATUS_SNAPSHOT_CONTENTS_UNAVAILABLE");
                toWrite = resolveResult.getSnapshot();
                i += 5;
            } else if (status == GamesStatusCodes.STATUS_SNAPSHOT_CONFLICT) {
                Log.e(TAG, "Save Result status: " + "STATUS_SNAPSHOT_CONFLICT");
                Snapshot snapshot = resolveResult.getSnapshot();
                Snapshot conflictSnapshot = result.getConflictingSnapshot();

                if (snapshot.getMetadata().getLastModifiedTimestamp() <
                        conflictSnapshot.getMetadata().getLastModifiedTimestamp()) {
                    mResolvedSnapshot = conflictSnapshot;
                }

                resolveResult = Games.Snapshots.resolveConflict(
                        Game.mainActivity.mGoogleApiClient, result.getConflictId(), mResolvedSnapshot)
                        .await();
            }
        }

        if (toWrite != null) {
            toWrite.getSnapshotContents().writeBytes(save[0].getBytes());

            // Save the snapshot.
            SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()
                    .setDescription("Modified data at: " + Calendar.getInstance().getTime())
                    .build();
            Games.Snapshots.commitAndClose(Game.mainActivity.mGoogleApiClient, toWrite, metadataChange);
        }
        return 1;
    }

    @Override
    protected void onPostExecute(Integer result){

    }

    protected void onProgressUpdate(){

    }
}