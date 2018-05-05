package com.marcelslum.ultnogame;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.os.Vibrator;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.marcelslum.ultnogame.GoogleAPI.mSnapshotsClient;

public class MainActivity extends FragmentActivity implements
        SensorEventListener
        {
            // Request code for saving the game to a snapshot.
            private static final int RC_SAVE_SNAPSHOT = 9004;

            private static final int RC_LOAD_SNAPSHOT = 9005;

    public static SensorManager mSensorManager;
    public static Sensor mAccelerometer;

    public MyGLSurface myGlSurface;
    private InterstitialAd interstitialWithVideo;
    private InterstitialAd interstitialNoVideo;
    private int interstitialActualMode = 0;
    public final static int INTERSTITIAL_MODE_WITH_VIDEO = 1;
    public final static int INTERSTITIAL_MODE_NO_VIDEO = 2;

    AdView mAdView;
    boolean isBannerLoaded = false;
    boolean isInterstitialLoaded = false;
    private final static String TAG = "MainActivity";
    public boolean isPaused = false;

    private boolean mResolvingError = false;
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    // progress dialog we display while we're loading state from the cloud
    ProgressDialog mLoadingDialog = null;



    static String mCurrentSaveName;

    /*
    * This callback will be triggered after you call startActivityForResult from the
    * showSavedGamesUI method.
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {

        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == GoogleAPI.RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                onConnected(account);

            } catch (ApiException apiException) {
                String message = apiException.getMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }

                onDisconnected();

                Game.showMessageNotConnectedOnGoogle = true;
            }
        }

        if (requestCode == GoogleAPI.RC_SAVED_GAMES) {

            if (intent != null) {
                if (intent.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA)) {
                    // Load a snapshot.
                    SnapshotMetadata snapshotMetadata =
                            intent.getParcelableExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA);
                    mCurrentSaveName = snapshotMetadata.getUniqueName();
                    loadFromSnapshot(snapshotMetadata);


                    // Load the game data from the Snapshot
                    // ...
                } else if (intent.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW)) {
                    // Create a new snapshot named with a unique string
                    mCurrentSaveName = "teste"+System.nanoTime();
                    saveSnapshot(null);
                }
            }
        }
    }

    @Override
    protected void onStop() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
        super.onStop();
    }


    void loadFromSnapshot(final SnapshotMetadata snapshotMetadata) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(getString(R.string.carregando_snapshot));
        }

        mLoadingDialog.show();

        waitForClosedAndOpen(snapshotMetadata)
                .addOnSuccessListener(new OnSuccessListener<SnapshotsClient.DataOrConflict<Snapshot>>() {
                    @Override
                    public void onSuccess(SnapshotsClient.DataOrConflict<Snapshot> result) {

                        // if there is a conflict  - then resolve it.
                        Snapshot snapshot = processOpenDataOrConflict(RC_LOAD_SNAPSHOT, result, 0);

                        if (snapshot == null) {
                            Log.w(TAG, "Conflict was not resolved automatically, waiting for user to resolve.");
                        } else {
                            try {
                                readSavedGame(snapshot);
                                Log.i(TAG, "Snapshot loaded.");
                            } catch (IOException e) {
                                Game.myGlSurface.showBottomMessage(Game.getContext().getResources().getString(R.string.erro_ao_salvar), 4000);
                                Log.e(TAG, "Error while reading snapshot contents: " + e.getMessage());
                            }
                        }

                        SnapshotCoordinator.getInstance().discardAndClose(mSnapshotsClient, snapshot)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "There was a problem discarding the snapshot!");
                                    }
                                });

                        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                            mLoadingDialog.dismiss();
                            mLoadingDialog = null;
                        }
                    }
                });
    }

    static SaveGame saveGameFromCloud;

    private void readSavedGame(Snapshot snapshot) throws IOException {

        Log.e(TAG, "read saveg game");

        String stringToSave = new String(snapshot.getSnapshotContents().readFully());
        Log.e(TAG, stringToSave);
        saveGameFromCloud = SaveGame.getSaveGameFromJson(stringToSave);

        Game.myGlSurface.setMenuCarregarMessage();
    }



    /**
     * Conflict resolution for when Snapshots are opened.
     *
     * @param requestCode - the request currently being processed.  This is used to forward on the
     *                    information to another activity, or to send the result intent.
     * @param result      The open snapshot result to resolve on open.
     * @param retryCount  - the current iteration of the retry.  The first retry should be 0.
     * @return The opened Snapshot on success; otherwise, returns null.
     */
    Snapshot processOpenDataOrConflict(int requestCode,
                                       SnapshotsClient.DataOrConflict<Snapshot> result,
                                       int retryCount) {

        retryCount++;

        if (!result.isConflict()) {
            return result.getData();
        }

        Log.i(TAG, "Open resulted in a conflict!");

        SnapshotsClient.SnapshotConflict conflict = result.getConflict();
        final Snapshot snapshot = conflict.getSnapshot();
        final Snapshot conflictSnapshot = conflict.getConflictingSnapshot();

        ArrayList<Snapshot> snapshotList = new ArrayList<Snapshot>(2);
        snapshotList.add(snapshot);
        snapshotList.add(conflictSnapshot);

        // Display both snapshots to the user and allow them to select the one to resolve.
        selectSnapshotItem(requestCode, snapshotList, conflict.getConflictId(), retryCount);

        // Since we are waiting on the user for input, there is no snapshot available; return null.
        return null;
    }


    private void selectSnapshotItem(int requestCode,
                                    ArrayList<Snapshot> items,
                                    String conflictId,
                                    int retryCount) {

        ArrayList<SnapshotMetadata> snapshotList = new ArrayList<SnapshotMetadata>(items.size());
        for (Snapshot m : items) {
            snapshotList.add(m.getMetadata().freeze());
        }

        /*
        Intent intent = new Intent(this, SelectSnapshotActivity.class);
        intent.putParcelableArrayListExtra(SelectSnapshotActivity.SNAPSHOT_METADATA_LIST,
                snapshotList);

        intent.putExtra(SelectSnapshotActivity.CONFLICT_ID, conflictId);
        intent.putExtra(SelectSnapshotActivity.RETRY_COUNT, retryCount);

        Log.d(TAG, "Starting activity to select snapshot");
        startActivityForResult(intent, requestCode);
        */
    }


    void saveSnapshot(final SnapshotMetadata snapshotMetadata) {

        if (mLoadingDialog == null) {
            mLoadingDialog = new ProgressDialog(this);
            mLoadingDialog.setMessage(getString(R.string.salvando_snapshot));
        }

        mLoadingDialog.show();

        waitForClosedAndOpen(snapshotMetadata)
                .addOnCompleteListener(new OnCompleteListener<SnapshotsClient.DataOrConflict<Snapshot>>() {
                    @Override
                    public void onComplete(@NonNull Task<SnapshotsClient.DataOrConflict<Snapshot>> task) {
                        SnapshotsClient.DataOrConflict<Snapshot> result = task.getResult();
                        Snapshot snapshotToWrite = processOpenDataOrConflict(RC_SAVE_SNAPSHOT, result, 0);

                        if (snapshotToWrite == null) {
                            // No snapshot available yet; waiting on the user to choose one.
                            return;
                        }

                        Log.d(TAG, "Writing data to snapshot: " + snapshotToWrite.getMetadata().getUniqueName());
                        writeSnapshot(snapshotToWrite)
                                .addOnCompleteListener(new OnCompleteListener<SnapshotMetadata>() {
                                    @Override
                                    public void onComplete(@NonNull Task<SnapshotMetadata> task) {
                                        if (task.isSuccessful()) {
                                            Game.myGlSurface.showBottomMessage(Game.getContext().getResources().getString(R.string.jogo_salvo), 4000);
                                            Log.e(TAG, "Snapshot saved!");
                                        } else {
                                            Game.myGlSurface.showBottomMessage(Game.getContext().getResources().getString(R.string.erro_ao_salvar), 4000);
                                            Log.e(TAG, "Houve um erro");
                                        }

                                        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                                            mLoadingDialog.dismiss();
                                            mLoadingDialog = null;
                                        }

                                    }
                                });
                    }
                });
    }

    /**
     * Gets a screenshot to use with snapshots. Note that in practice you probably do not want to
     * use this approach because tablet screen sizes can become pretty large and because the image
     * will contain any UI and layout surrounding the area of interest.
     */
    Bitmap getScreenShot() {


        int lastLevelUnlockeced = -1;
        for (int i = SaveGame.saveGame.levelsUnlocked.length - 1; i >= 0; i--) {
            if (SaveGame.saveGame.levelsUnlocked[i]){
                lastLevelUnlockeced = i + 1;
                break;
            }
        }

        Log.e(TAG, "lastLevelUnlockeced " + lastLevelUnlockeced);


        LevelsGroupData gd = null;
        for (int i = 0; i < LevelsGroupData.levelsGroupData.size(); i++) {
            if (lastLevelUnlockeced >= LevelsGroupData.levelsGroupData.get(i).firstLevel && lastLevelUnlockeced <= LevelsGroupData.levelsGroupData.get(i).finalLevel){
                gd = LevelsGroupData.levelsGroupData.get(i);
            }
        }

        Log.e(TAG, "gd " + gd.number);

        int levelNumber = (SaveGame.saveGame.lastLevelPlayed >= 1 && SaveGame.saveGame.lastLevelPlayed <= 100) ? SaveGame.saveGame.lastLevelPlayed : 1;

        TextureData td = TextureData.getTextureDataById(LevelDataLoader.getTextureData(levelNumber));


        Log.e(TAG, "x "+gd.textureData.x);
        Log.e(TAG, "y "+gd.textureData.y);
        Log.e(TAG, "w "+gd.textureData.w);
        Log.e(TAG, "h "+gd.textureData.h);
        Log.e(TAG, "x "+(int) (gd.textureData.x * 2048f));
        Log.e(TAG, "y "+(int) (gd.textureData.y * 2048f));
        Log.e(TAG, "w "+(int) ((gd.textureData.x + gd.textureData.w) * 2048f));
        Log.e(TAG, "h "+(int)  ((gd.textureData.h + gd.textureData.y) * 2048f));



        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap resourceBitmap = null;
        Bitmap groupCroppedBitmap = null;
        Bitmap levelCroppedBitmap = null;


        try {
            resourceBitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.icons, options);

            groupCroppedBitmap = Bitmap.createBitmap(resourceBitmap, (int) (gd.textureData.x * 2048f), (int) (gd.textureData.y * 2048f), (int) (gd.textureData.w * 2048f), (int) (gd.textureData.h * 2048f));
            levelCroppedBitmap = Bitmap.createBitmap(resourceBitmap, (int) (td.x * 2048f), (int) (td.y * 2048f), (int) (td.w * 2048f), (int) (td.h * 2048f));
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        // cria um bitmap vazio para desenho
        Bitmap finalBitmap;
        if (groupCroppedBitmap != null) {
            finalBitmap = Bitmap.createBitmap((int) (groupCroppedBitmap.getWidth() * 2f), (int) (groupCroppedBitmap.getHeight() * 1.2f), groupCroppedBitmap.getConfig());
        } else {
            finalBitmap = Bitmap.createBitmap((int) (256), (int) (164), Bitmap.Config.ARGB_8888);
        }

        // desenha a cor de fundo
        Canvas c = new Canvas();
        c.setBitmap(finalBitmap);
        Paint p1 = new Paint();
        p1.setARGB(255, (int) (Color.branco.r * 255f), (int) (Color.branco.g * 255f), (int) (Color.branco.b * 255f));
        c.drawRect(0f, 0f, finalBitmap.getWidth(), finalBitmap.getWidth(), p1);

        Paint p = new Paint();

        // desenha o grupo
        if (levelCroppedBitmap != null) {
            c.drawBitmap(levelCroppedBitmap, (int) (finalBitmap.getWidth() * 0.1f), finalBitmap.getHeight() * 0.1f, p);
        }

        // desenha o grupo
        if (groupCroppedBitmap != null) {
            c.drawBitmap(groupCroppedBitmap, (int) (finalBitmap.getWidth() * 0.4f), groupCroppedBitmap.getHeight() * 0.2f, p);
        }


        // libera os recursos
        resourceBitmap.recycle();
        groupCroppedBitmap.recycle();
        levelCroppedBitmap.recycle();


        //if (Game.playerIcon != null){
        //    return Game.playerIcon;
        //} else {
            return finalBitmap;
        //}
    }

    /**
     * Generates metadata, takes a screenshot, and performs the write operation for saving a
     * snapshot.
     */
    private Task<SnapshotMetadata> writeSnapshot(Snapshot snapshot) {
        // Set the data payload for the snapshot.
        snapshot.getSnapshotContents().

                writeBytes(SaveGame.getJSONFromSaveGame(SaveGame.saveGame).getBytes());


        DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        // Save the snapshot.
        SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()
                .setCoverImage(getScreenShot())
                .setDescription(getResources().getString(R.string.conquistadas) + " " + SaveGame.getTotalStars(SaveGame.saveGame) + " " + getResources().getString(R.string.estrelas))
                .build();
        return SnapshotCoordinator.getInstance().commitAndClose(mSnapshotsClient, snapshot, metadataChange);
    }


    private Task<SnapshotsClient.DataOrConflict<Snapshot>> waitForClosedAndOpen(final SnapshotMetadata snapshotMetadata) {

        final boolean useMetadata = snapshotMetadata != null && snapshotMetadata.getUniqueName() != null;
        if (useMetadata) {
            Log.i(TAG, "Opening snapshot using metadata: " + snapshotMetadata);
        } else {
            Log.i(TAG, "Opening snapshot using currentSaveName: " + mCurrentSaveName);
        }

        final String filename = useMetadata ? snapshotMetadata.getUniqueName() : mCurrentSaveName;

        return SnapshotCoordinator.getInstance()
                .waitForClosed(filename)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Não foi possível salvar. There was a problem waiting for the file to close!");
                    }
                })
                .continueWithTask(new Continuation<Result, Task<SnapshotsClient.DataOrConflict<Snapshot>>>() {
                    @Override
                    public Task<SnapshotsClient.DataOrConflict<Snapshot>> then(@NonNull Task<Result> task) throws Exception {
                        Task<SnapshotsClient.DataOrConflict<Snapshot>> openTask = useMetadata
                                ? SnapshotCoordinator.getInstance().open(mSnapshotsClient, snapshotMetadata)
                                : SnapshotCoordinator.getInstance().open(mSnapshotsClient, filename, true);
                        return openTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                if (useMetadata){
                                    Log.e(TAG, "Não foi possível salvar. R.string.error_opening_metadata");
                                } else {
                                    Log.e(TAG, "Não foi possível salvar. R.string.error_opening_filename");
                                }
                            }
                        });
                    }
                });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         /*Inicia o hardware*/
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Game.vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Splash.timesGoogle = 0;

        /*Inicia o banco de Level Data*/
        try {
            DataBaseLevelDataHelper.getInstance(this).prepareDatabase();
            if (!Game.forDebugDeleteDatabaseAndStorage) {
                Game.groupsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getGroupsDataBaseData();
                Game.levelsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getLevelsDataBaseData();
            }
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        /*Inicia o banco de Save Data*/
        try {
            DataBaseSaveDataHelper.getInstance(this).prepareDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        Storage.init(this);

        if (Game.forDebugDeleteDatabaseAndStorage){
            Game.forDebugDeleteDatabaseAndStorage = false;
            try {
                DataBaseLevelDataHelper.getInstance(this).prepareDatabase();
                Game.groupsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getGroupsDataBaseData();
                Game.levelsDataBaseData = DataBaseLevelDataHelper.getInstance(this).getLevelsDataBaseData();
                DataBaseSaveDataHelper.getInstance(this).prepareDatabase();
                Storage.init(this);
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }

        }

        SaveGame.load();


        

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //Log.e(TAG, "maxMemory "+maxMemory);

        mResolvingError = savedInstanceState != null
                && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);

        Game.mainActivity = this;
        Game.forInitGame = true;

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        if (SaveGame.saveGame.orientationInverted) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }



        // Create the client used to sign in to Google services.

        GoogleSignInOptions signInOption =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                        // Add the APPFOLDER scope for Snapshot support.
                        //.requestScopes(Drive.SCOPE_APPFOLDER)
                        .requestScopes(Drive.SCOPE_APPFOLDER)
                        .build();

        GoogleAPI.mGoogleSignInClient = GoogleSignIn.getClient(this,
                new GoogleSignInOptions.Builder(signInOption).build());

        // Fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                    // TODO: The system bars are visible. Make any desired
                    if (Game.gameState == Game.GAME_STATE_JOGAR) {
                        Game.setGameState(Game.GAME_STATE_PAUSE);
                    }
                } else {
                    // TODO: The system bars are NOT visible. Make any desired
                }
            }
        });

        // SURFACE VIEW
        myGlSurface = new MyGLSurface(this);
        myGlSurface.setPreserveEGLContextOnPause(true);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.gamelayout);
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.addView(myGlSurface, glParams);

	// INICIA O BANNER
        mAdView = new AdView(this);
        mAdView.setAdUnitId("ca-app-pub-2413920269734587/4375714557");
        mAdView.setAdSize(AdSize.SMART_BANNER);
		mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
				isBannerLoaded = true;
				showAdView();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
				isBannerLoaded = false;
            }
        });

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layout.addView(mAdView, adParams);
	
        // INICIA A PROPAGANDA INTERSTITIAL
        interstitialWithVideo = new InterstitialAd(MainActivity.this);
        interstitialWithVideo.setAdUnitId("ca-app-pub-2413920269734587/2998542956");

        interstitialNoVideo = new InterstitialAd(MainActivity.this);
        interstitialNoVideo.setAdUnitId("ca-app-pub-2413920269734587/7806070555");

        AdListener adListener = new AdListener() {
            @Override
            public void onAdLoaded() {
            }
            @Override
            public void onAdClosed() {
                Game.returningFromInterstitialFlag = true;
                loadInterstitialAd();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }
            @Override
            public void onAdLeftApplication() {
            }
            @Override
            public void onAdOpened() {
            }
        };

        interstitialWithVideo.setAdListener(adListener);
        interstitialNoVideo.setAdListener(adListener);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_RESOLVING_ERROR, mResolvingError);
    }

    public boolean isSignedIn() {
        if (GoogleSignIn.getLastSignedInAccount(this) != null && SaveGame.saveGame.googleOption == 1){
            //Log.e(TAG, "isSignedIn true");
            return true;
        } else {
            //Log.e(TAG, "isSignedIn false");
            return false;
        }
    }

    public boolean isGooglePlayAvailable() {
        boolean googlePlayStoreInstalled;
        int val = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext());
        googlePlayStoreInstalled = val == ConnectionResult.SUCCESS;


        //Log.e(TAG, "googlePlayStoreInstalled val "+ val);
        //Log.e(TAG, "googlePlayStoreInstalled "+ googlePlayStoreInstalled);

        return googlePlayStoreInstalled;
    }

    public void signInSilently() {
        Log.e(TAG, "signInSilently()");

        GoogleAPI.mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "signInSilently(): success");
                            onConnected(task.getResult());
                        } else {
                            Log.e(TAG, "signInSilently(): failure", task.getException());
                            onDisconnected();
                        }
                    }
                });
    }

    public void startSignInIntent() {
        startActivityForResult(GoogleAPI.mGoogleSignInClient.getSignInIntent(), GoogleAPI.RC_SIGN_IN);
    }

    public void signOut() {
        Log.d(TAG, "signOut()");

        if (MenuHandler.menuOptions != null){
            MenuHandler.menuOptions.getMenuOptionByName("google").setText(getResources().getString(R.string.logarGoogle));
        }

        if (!isSignedIn()) {
            Log.w(TAG, "signOut() called, but was not signed in!");
            return;
        }

        GoogleAPI.mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        boolean successful = task.isSuccessful();
                        Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));
                        onDisconnected();
                    }
                });
    }


    public void updatePlayerInfo(){

        if (SaveGame.saveGame.googleOption != 1 && !isSignedIn()){
            return;
        }

        GoogleAPI.mPlayersClient.getCurrentPlayer()
                .addOnCompleteListener(new OnCompleteListener<Player>() {
                    @Override
                    public void onComplete(@NonNull Task<Player> task) {
                        if (task.isSuccessful()) {
                            GoogleAPI.playerName = task.getResult().getName()+"!";
                            Uri uri = task.getResult().getIconImageUri();
                            Log.e(TAG, "uri " + uri.getPath());
                            ImageManager.create(Game.mainActivity)
                                    .loadImage(new ImageManager.OnImageLoadedListener() {
                                        @Override
                                        public void onImageLoaded(Uri uri, Drawable drawable, boolean b) {
                                            GoogleAPI.playerIcon = Utils.drawableToBitmap(drawable);
                                            GoogleAPI.playerIconImage = new ImageBitmap("playerIconImage", Game.resolutionX * 0.862f, Game.resolutionY * 0.75f, Game.resolutionX * 0.12f, Game.resolutionX * 0.12f, GoogleAPI.playerIcon);
                                        }
                                    }, uri);

                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleLogado) + "\u0020" + GoogleAPI.playerName);
                            }
                        } else {
                            GoogleAPI.playerName = ".";
                            signOut();
                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleErroLogar));
                            }

                        }
                    }
                });
    }



    private void onConnected(final GoogleSignInAccount googleSignInAccount) {

        //Log.e(TAG, "onConnected");

        GoogleAPI.mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
        GoogleAPI.mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
        mSnapshotsClient = Games.getSnapshotsClient(this, googleSignInAccount);
        GoogleAPI.mEventsClient = Games.getEventsClient(this, googleSignInAccount);
        GoogleAPI.mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);


        GoogleAPI.mPlayersClient.getCurrentPlayer()
                .addOnCompleteListener(new OnCompleteListener<Player>() {
                    @Override
                    public void onComplete(@NonNull Task<Player> task) {
                        if (task.isSuccessful()) {
                            //Log.e(TAG, "player name atualizado para " + task.getResult().getDisplayName());
                            GoogleAPI.playerName = task.getResult().getName()+"!";

                            Uri uri = task.getResult().getIconImageUri();
                            Log.e(TAG, "uri " + uri.getPath());
                            ImageManager.create(Game.mainActivity)
                                    .loadImage(new ImageManager.OnImageLoadedListener() {
                                        @Override
                                        public void onImageLoaded(Uri uri, Drawable drawable, boolean b) {
                                            GoogleAPI.playerIcon = Utils.drawableToBitmap(drawable);
                                        }
                                    }, uri);

                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleLogado) + "\u0020" + GoogleAPI.playerName);
                            }
                        } else {
                            //Log.e(TAG, "Não foi possível carregar o nome do jogador");
                            GoogleAPI.playerName = ".";
                            signOut();
                            if (MessagesHandler.messageGoogleLogged != null) {
                                MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleErroLogar));
                            }

                        }
                    }
                });
    }

    public boolean checkGoogleConnection(){
        //Log.e(TAG, "checkGoogleConnection");
        if (isGooglePlayAvailable() && isSignedIn()){
            //Log.e(TAG, "checking");
            if (GoogleAPI.playerName.equals(".") || GoogleAPI.playerName.equals("-")){
                //Log.e(TAG, "possible not connected - erro");
                signOut();
                if (MessagesHandler.messageGoogleLogged != null) {
                    MessagesHandler.messageGoogleLogged.setText(getResources().getString(R.string.googleErroLogar));
                }
                return false;
            }
        }
        return true;
    }

    private void onDisconnected() {

        Log.d(TAG, "onDisconnected()");

        GoogleAPI.mAchievementsClient = null;
        GoogleAPI.mLeaderboardsClient = null;
        GoogleAPI.mPlayersClient = null;

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

	public void setFullScreen() {
        final Activity fa = this;
        runOnUiThread(new Runnable() {
            public void run() {
            int uiOptions = fa.getWindow().getDecorView().getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 18) {
                uiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            fa.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
	}

    @Override
    protected void onPause() {

        DataBaseLevelDataHelper.getInstance(this).close();
        DataBaseSaveDataHelper.getInstance(this).close();

        isPaused = true;
        //Log.e("MainActivity", "onPause()");
        //if (Game.gameState != Game.GAME_STATE_INTERSTITIAL) {
        //    SaveGame.saveGame.save();
        //}

        if (myGlSurface != null){
            myGlSurface.onPause();
        }

        if (mAdView != null) {
            mAdView.pause();
        }

        if (Game.gameState == Game.GAME_STATE_PREPARAR){
            Game.initPausedFlag = true;
        }

        mSensorManager.unregisterListener(this);

        Sound.pauseAll();
	    AsyncTasks.cancelAll();
        super.onPause();
    }

    @Override
    public void onBackPressed() {

        // TODO REVISAR E COMPLETAR

        if (Game.gameState == Game.GAME_STATE_OPCOES_GAME) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_SOBRE){
                    Game.setGameState(Game.GAME_STATE_OPCOES);
	} else if (Game.gameState == Game.GAME_STATE_JOGAR) {
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_MENU_PRINCIPAL) {
            onPause();
            moveTaskToBack(true);
        } else if (Game.gameState == Game.GAME_STATE_PAUSE){
            Game.increaseAllGameEntitiesAlpha(500);
            MessagesHandler.messageInGame.reduceAlpha(500,0f);
            MenuHandler.menuInGame.reduceAlpha(500,0f, new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd() {
                    Game.setGameState(Game.GAME_STATE_PRE_JOGAR);
                }
            });
        } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_PAUSE){
            Game.setGameState(Game.GAME_STATE_PAUSE);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA){
            Game.setGameState(Game.GAME_STATE_VITORIA_COMPLEMENTACAO);
        } else if (Game.gameState == Game.GAME_STATE_VITORIA_COMPLEMENTACAO){
            Game.setGameState(Game.GAME_STATE_INTERSTITIAL);
        } else if (Game.gameState == Game.GAME_STATE_SELECAO_LEVEL){
            Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
        } else if (Game.gameState == Game.GAME_STATE_SELECAO_GRUPO) {
            Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
        }else if (Game.gameState == Game.GAME_STATE_PREPARAR){
            Game.initPausedFlag = true;
        }else if (Game.gameState == Game.GAME_STATE_DERROTA){
            if (SaveGame.saveGame.currentLevelNumber < 1000){
                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
            } else {
                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
            }
        } else if (Game.gameState == Game.GAME_STATE_OBJETIVO_LEVEL){
            if (SaveGame.saveGame.currentLevelNumber < 1000){
                Game.setGameState(Game.GAME_STATE_SELECAO_LEVEL);
            } else {
                Game.setGameState(Game.GAME_STATE_SELECAO_GRUPO);
            }
	    } else if (Game.gameState != Game.GAME_STATE_INTRO){
            Game.setGameState(Game.GAME_STATE_MENU_PRINCIPAL);
	    }
    }

    @Override
    protected void onResume() {

        //Log.e("MainActivity", "onResume()");

        super.onResume();

        isPaused = false;

        setFullScreen();

        if (SaveGame.saveGame != null && SaveGame.saveGame.googleOption == 1) {
            signInSilently();
        }

        if (myGlSurface != null){
            myGlSurface.onResume();
        }

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);

		showAdView();
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }

        Sound.releaseAll();
        super.onDestroy();
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        Acelerometer.updateValue(event.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void hideAdView(){
        
        if (Game.notConnectedTextView != null) {
            Game.notConnectedTextView.clearDisplay();
            Game.topFrame.clearDisplay();
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdView.pause();
                mAdView.setVisibility(View.GONE);
            }
        });
    }
    
	public void loadBannerAd(){
	
		if (isBannerLoaded){
			return;
		}

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdRequest adRequestBanner = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
                        .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
                        .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
                        .build();
                mAdView.loadAd(adRequestBanner);
            }
        });


    }

    public void loadInterstitialAd(){

        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9BDF327E8C4CD72B8C5DC02B20DD551B")
                .addTestDevice("AB221C24C4F00E7425323CFD691D8964")
                .addTestDevice("843225C5776838E9FBAEE4A8D8414389")
                .build();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ConnectionHandler.isConnectionWifi()) {
                    //Log.e(TAG, "Conectado ao Wifi - carregando interstitialWithVideo");
                    interstitialActualMode = INTERSTITIAL_MODE_WITH_VIDEO;
                    interstitialWithVideo.loadAd(adRequest);

                } else {
                    //Log.e(TAG, "Não conectado ao Wifi - carregando interstitialNoVideo");
                    interstitialActualMode = INTERSTITIAL_MODE_NO_VIDEO;
                    interstitialNoVideo.loadAd(adRequest);
                }
            }
        });
    }

    public void showAdView(){
    
    	if (Game.gameState == Game.GAME_STATE_JOGAR){
			return;
		}
		
		if (isBannerLoaded){
			if (Game.notConnectedTextView != null) {
				Game.notConnectedTextView.clearDisplay();
				Game.topFrame.clearDisplay();
			}

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
				mAdView.resume();
				mAdView.setVisibility(View.VISIBLE);
				}
			});
		} else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                mAdView.setVisibility(View.GONE);
                }
            });

			
			if (Game.notConnectedTextView != null) {
			
				ConnectionHandler.checkInternetConnection();
			
				if (ConnectionHandler.internetState == ConnectionHandler.INTERNET_STATE_NOT_CONNECTED) {
					Game.notConnectedTextView.display();
					Game.topFrame.display();
				} else {
					Game.notConnectedTextView.clearDisplay();
					Game.topFrame.clearDisplay();
				}
				
			} 
			// como não esta carregado, faz um nova tentativa de carregar, para mostrar na próxima vez
			loadBannerAd();
		}
    }

    public void showInterstitial() {
        runOnUiThread(new Runnable() {
            public void run() {
                    InterstitialAd interstitialAd;

                    if (interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO) {
                        //Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_WITH_VIDEO");
                        interstitialAd = interstitialWithVideo;
                    } else if (interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO){
                        //Log.e("MainActivity", "interstitialActualMode == INTERSTITIAL_MODE_NO_VIDEO");
                        interstitialAd = interstitialNoVideo;
                    } else {
                        interstitialAd = interstitialNoVideo;
                    }

                    // se é nulo vai para o menu de seleção de nível
                    if (interstitialAd == null){
                        Game.returningFromInterstitialFlag = true;
                        return;
                    }

                    // verifica se está carregada
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        Log.e(getLocalClassName(), "Propaganda não carregada");
                        hideAdView();
                        isBannerLoaded = false;
                        Game.returningFromInterstitialFlag = true;
                        loadInterstitialAd();
                    }

                }
        });
    }
}
