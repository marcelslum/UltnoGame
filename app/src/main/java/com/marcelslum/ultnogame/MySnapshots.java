package com.marcelslum.ultnogame;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

import java.util.Calendar;

/**
 * Created by marcel on 12/10/2016.
 */

public class MySnapshots {

    public static String TAG = "MySnapshots";
    final static String SNAPSHOT_FILE_NAME = "ultno.SavedGame";


    /**
     * Conflict resolution for when Snapshots are opened.
     * @param result The open snapshot result to resolve on open.
     * @return The opened Snapshot on success; otherwise, returns null.
     */
    public static Snapshot processSnapshotOpenResult(Snapshots.OpenSnapshotResult result, int retryCount){
        Snapshot mResolvedSnapshot = null;
        retryCount++;
        int status = result.getStatus().getStatusCode();

        Log.i(TAG, "Save Result status: " + status);

        if (status == GamesStatusCodes.STATUS_OK) {
            return result.getSnapshot();
        } else if (status == GamesStatusCodes.STATUS_SNAPSHOT_CONTENTS_UNAVAILABLE) {
            return result.getSnapshot();
        } else if (status == GamesStatusCodes.STATUS_SNAPSHOT_CONFLICT){
            Snapshot snapshot = result.getSnapshot();
            Snapshot conflictSnapshot = result.getConflictingSnapshot();

            // Resolve between conflicts by selecting the newest of the conflicting snapshots.
            mResolvedSnapshot = snapshot;

            if (snapshot.getMetadata().getLastModifiedTimestamp() <
                    conflictSnapshot.getMetadata().getLastModifiedTimestamp()){
                mResolvedSnapshot = conflictSnapshot;
            }

            Snapshots.OpenSnapshotResult resolveResult = Games.Snapshots.resolveConflict(
                    Game.mainActivity.mGoogleApiClient, result.getConflictId(), mResolvedSnapshot)
                    .await();

            if (retryCount < 5){
                return processSnapshotOpenResult(resolveResult, retryCount);
            }else{
                String message = "Could not resolve snapshot conflicts";
                Log.e(TAG, message);
            }

        }
        // Fail, return null.
        return null;
    }
}
