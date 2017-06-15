package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.ResultCallback;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zagum.switchicon.SwitchIconView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.example.games.basegameutils.BaseGameUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

public class MultiActivity extends FragmentActivity {

        /*
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnInvitationReceivedListener, OnTurnBasedMatchUpdateReceivedListener{


    int duration;
    boolean musicFlag;

    ArrayList wordCollection;//It stores the all of words
    List<String> wordListView;//It stores the all of the word that come from user
    List<ImageView> keyboardImageViews;
    ArrayList<ImageView> inputImages;

    List<GameLogic> words;
    ListView wordList;

    MediaPlayer music;

    LinearLayout keyboardView;
    LinearLayout backGround;
    LinearLayout inputWordLayout;
    LinearLayout wordsLayout;

    AutofitTextView newInputTextView;

    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;

    MenuActivity menuActivity;

    WordAdapter wordAdapter;

    AlertDialog mAlertDialog;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private TurnBasedMatch mTurnBasedMatch;
    public TurnBasedMatch mMatch;

    public GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 9001;
    final static int RC_SELECT_PLAYERS = 10000;
    final static int RC_LOOK_AT_MATCHES = 10001;

    public boolean isDoingTurn = false;

    private boolean mResolvingConnectionFailure = false;

    // Has the user clicked the sign-in button?
    private boolean mSignInClicked = false;

    // Automatically start the sign-in flow when the Activity starts
    private boolean mAutoStartSignInFlow = true;

    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init() {

        words = new ArrayList<>();

        switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);

        keyboardView = (LinearLayout) findViewById(R.id.keyboardLayout);
        backGround = (LinearLayout) findViewById(R.id.activity_main);
        inputWordLayout = (LinearLayout) findViewById(R.id.inputLayout);
        wordsLayout = (LinearLayout) findViewById(R.id.wordsLayout);

        keyboardImageViews = new ArrayList<>();

        newInputTextView = (AutofitTextView) findViewById(R.id.inputText);

        keyboardImageViews.add((ImageView) findViewById(R.id.q));
        keyboardImageViews.add((ImageView) findViewById(R.id.w));
        keyboardImageViews.add((ImageView) findViewById(R.id.e));
        keyboardImageViews.add((ImageView) findViewById(R.id.r));
        keyboardImageViews.add((ImageView) findViewById(R.id.t));
        keyboardImageViews.add((ImageView) findViewById(R.id.y));
        keyboardImageViews.add((ImageView) findViewById(R.id.u));
        keyboardImageViews.add((ImageView) findViewById(R.id.ii));
        keyboardImageViews.add((ImageView) findViewById(R.id.o));
        keyboardImageViews.add((ImageView) findViewById(R.id.p));
        keyboardImageViews.add((ImageView) findViewById(R.id.gi));
        keyboardImageViews.add((ImageView) findViewById(R.id.ui));
        keyboardImageViews.add((ImageView) findViewById(R.id.a));
        keyboardImageViews.add((ImageView) findViewById(R.id.s));
        keyboardImageViews.add((ImageView) findViewById(R.id.d));
        keyboardImageViews.add((ImageView) findViewById(R.id.f));
        keyboardImageViews.add((ImageView) findViewById(R.id.g));
        keyboardImageViews.add((ImageView) findViewById(R.id.h));
        keyboardImageViews.add((ImageView) findViewById(R.id.j));
        keyboardImageViews.add((ImageView) findViewById(R.id.k));
        keyboardImageViews.add((ImageView) findViewById(R.id.l));
        keyboardImageViews.add((ImageView) findViewById(R.id.si));
        keyboardImageViews.add((ImageView) findViewById(R.id.i));
        keyboardImageViews.add((ImageView) findViewById(R.id.z));
        keyboardImageViews.add((ImageView) findViewById(R.id.x));
        keyboardImageViews.add((ImageView) findViewById(R.id.c));
        keyboardImageViews.add((ImageView) findViewById(R.id.v));
        keyboardImageViews.add((ImageView) findViewById(R.id.b));
        keyboardImageViews.add((ImageView) findViewById(R.id.n));
        keyboardImageViews.add((ImageView) findViewById(R.id.m));
        keyboardImageViews.add((ImageView) findViewById(R.id.oi));
        keyboardImageViews.add((ImageView) findViewById(R.id.ci));

        wordCollection = new ArrayList<>();
        wordListView = new ArrayList<>();
        inputImages = new ArrayList<>();

        wordList = (ListView)findViewById(R.id.wordList);

        musicFlag = true;

        menuActivity = new MenuActivity();

        readFromRaw();
    }

    //Read the .txt file line by line and store in wordCollection which type is ArrayList
    public void readFromRaw() {

        try {
            InputStream fis = this.getResources().openRawResource(R.raw.turkish_words);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            while ((line = br.readLine()) != null)
                wordCollection.add(line);

            //listWords.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordCollection));
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private String backSpace(String str) {
        char[] ch = str.toCharArray();
        str = "";
        for (int i = 0; i < ch.length - 1; i++)
            str += ch[i];

        return str;
    }

    private String filterForChars(String s) {

        if (s.equals("ii"))
            return "ı".toUpperCase();
        else if (s.equals("gi"))
            return "ğ".toUpperCase();
        else if (s.equals("ui"))
            return "ü".toUpperCase();
        else if (s.equals("si"))
            return "ş".toUpperCase();
        else if (s.equals("oi"))
            return "ö".toUpperCase();
        else if (s.equals("ci"))
            return "ç".toUpperCase();
        else if (s.equals("i"))
            return "İ";
        else
            return s.toUpperCase();
    }

    private void refreshInputText(String s) {
        if (newInputTextView.getText().toString().length() < 15) {
            newInputTextView.setText(newInputTextView.getText().toString() + s);
            //decreaseTextSize(inputText);
        }

    }

    private void showCustomToast(String message) {
        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_id));

        // The actual toast generated here.
        Toast toast = new Toast(MultiActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView t = (TextView) layout.findViewById(R.id.info);
        t.setText(message);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.setView(layout);
        toast.show();
    }

    public void setMusicLevel(float percent) {
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int setVolume = (int) (maxVolume * percent);
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, setVolume, 0);
    }

    private void scrollMyListViewToBottom() {
        wordList.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                wordList.setSelection(wordAdapter.getCount() - 1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */
        /*
                        this  OnConnectionFailedListener )
 /*
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .build();

        this.init();




        wordAdapter = new WordAdapter(MultiActivity.this,words);
        wordList.setAdapter(wordAdapter);

        setMusicLevel(0.3f);

        switchIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon1.switchState();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (switchIcon1.isIconEnabled()) {
                            backGround.setBackgroundDrawable((getResources().getDrawable(R.color.MainBackground)));
                            inputWordLayout.setBackgroundColor(getResources().getColor(R.color.black_overlay));
                            wordsLayout.setBackgroundColor(getResources().getColor(R.color.black_overlay));
                        } else {
                            backGround.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_line));
                            inputWordLayout.setBackgroundColor(getResources().getColor(R.color.gray_overlay));
                            wordsLayout.setBackgroundColor(getResources().getColor(R.color.gray_overlay));
                        }
                    }
                });
            }
        });

        switchIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon2.switchState();
                if (!switchIcon2.isIconEnabled() && music.isPlaying()) {
                    duration = music.getCurrentPosition();
                    music.pause();
                } else {
                    music.seekTo(duration);
                    music.start();
                }

            }
        });

    }
*/

 /*
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("OnStart Method worked.", "MusicFlag value:" + musicFlag);
        music = MediaPlayer.create(MultiActivity.this, R.raw.gamemod2);
        music.setLooping(true);
        if (musicFlag)
            music.start();

        mGoogleApiClient.connect();


        newInputTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(MainActivity.this,"Long Click",Toast.LENGTH_SHORT).show();
                //showCustomToast("+25 Puan");
                //send word to wordsLayout.
                /*
                if(!newInputTextView.getText().toString().isEmpty()) {
                    words.add(new Word(newInputTextView.getText().toString()));
                    wordAdapter.notifyDataSetChanged();
                    scrollMyListViewToBottom();
                }
                /*
                return true;
            }
        });
*/
        //This listener is used to delete last character.
            /*
        newInputTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("User Action", "Delete the last char");
                if (newInputTextView.getText().toString().length() != 0) {
                    newInputTextView.setText(backSpace(newInputTextView.getText().toString()));
                }
                else{
                    showCustomToast("Silinecek harf kalmadı");
                    if(mGoogleApiClient.isConnected())
                        showCustomToast("Baglı");
                    onQuickMatchClicked();
                }

            }
        });

        for (final ImageView Image : keyboardImageViews) {
            Image.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            ImageView view = (ImageView) v;
                            //overlay is black with transparency of 0x77 (119)
                            view.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
                            view.invalidate();
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            ImageView view = (ImageView) v;
                            refreshInputText(filterForChars(Image.getResources().getResourceEntryName(v.getId())));
                            //clear the overlay
                            view.getDrawable().clearColorFilter();
                            view.invalidate();
                            break;
                        }
                    }
                    return true;
                }
            });
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnResume Method worked.", "MusicFlag value:" + musicFlag);
        if (musicFlag) {
            music.seekTo(duration);
            music.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("OnPause Method worked.", "MusicFlag value:" + musicFlag);
        duration = music.getCurrentPosition();
        music.pause();
        if (!switchIcon2.isIconEnabled())
            musicFlag = false;
        else
            musicFlag = true;
    }

    @Override
    protected void onStop(){
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onBackPressed() {
        Log.i("OnBackPressed worked.", "Now go to MainActivity.");

        final Dialog dialogue = new Dialog(MultiActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        dialogue.setContentView(R.layout.dialogue);
        final ImageView exitImage = (ImageView) dialogue.findViewById(R.id.exitAppView);
        final ImageView stayImage = (ImageView) dialogue.findViewById(R.id.stayInAppView);
        dialogue.setCancelable(true);
        dialogue.show();
        setMusicLevel(0.1f);

        exitImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("exitImage:", "exitImage is touched.");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        menuActivity.applyColorFilter(exitImage, true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        menuActivity.applyColorFilter(exitImage, false);
                        dialogue.dismiss();
                        Intent goToMenuActivity = new Intent(MultiActivity.this, MenuActivity.class);
                        MultiActivity.this.finish();
                        startActivity(goToMenuActivity);
                        break;
                    }
                }
                return true;
            }
        });

        stayImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("stayImage:", "stayImage is touched.");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        menuActivity.applyColorFilter(stayImage, true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        menuActivity.applyColorFilter(stayImage, false);
                        setMusicLevel(0.3f);
                        dialogue.dismiss();
                        break;
                    }
                }
                return true;
            }
        });

    }

    // Handle notification events.
    @Override
    public void onInvitationReceived(Invitation invitation) {
        Toast.makeText(
                this,
                "An invitation has arrived from "
                        + invitation.getInviter().getDisplayName(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onInvitationRemoved(String invitationId) {
        Toast.makeText(this, "An invitation was removed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTurnBasedMatchReceived(TurnBasedMatch match) {
        Toast.makeText(this, "A match was updated.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTurnBasedMatchRemoved(String matchId) {
        Toast.makeText(this, "A match was removed.", Toast.LENGTH_SHORT).show();

    }

    public void showErrorMessage(TurnBasedMatch match, int statusCode, int stringId) {

        showWarning("Warning", getResources().getString(stringId));
    }

    public void showWarning(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(title).setMessage(message);

        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                    }
                });

        // create alert dialog
        mAlertDialog = alertDialogBuilder.create();

        // show it
        mAlertDialog.show();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d("INfo", "onConnected(): Connection successful");

        // Retrieve the TurnBasedMatch from the connectionHint
        if (connectionHint != null) {
            mTurnBasedMatch = connectionHint.getParcelable(Multiplayer.EXTRA_TURN_BASED_MATCH);

            if (mTurnBasedMatch != null) {
                if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
                    Log.e("INfo", "Warning: accessing TurnBasedMatch when not connected");
                }else{
                    onQuickMatchClicked();
                }

                updateMatch(mTurnBasedMatch);
                return;
            }
        }

        //setViewVisibility();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("MultiActivity:", "onConnectionSuspended():  Trying to reconnect.");
        mGoogleApiClient.connect();
        //setViewVisibility();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("MultiActivity:", "onConnectionSuspended():  Trying to reconnect.");
    }

    public void onQuickMatchClicked() {

        Bundle autoMatchCriteria = RoomConfig.createAutoMatchCriteria(
                1, 1, 0);

        TurnBasedMatchConfig tbmc = TurnBasedMatchConfig.builder()
                .setAutoMatchCriteria(autoMatchCriteria).build();

        //showSpinner();

        // Start the match
        ResultCallback<TurnBasedMultiplayer.InitiateMatchResult> cb = new ResultCallback<TurnBasedMultiplayer.InitiateMatchResult>() {
            @Override
            public void onResult(TurnBasedMultiplayer.InitiateMatchResult result) {
                processResult(result);
            }
        };
        Games.TurnBasedMultiplayer.createMatch(mGoogleApiClient, tbmc).setResultCallback(cb);
    }

    public void processResult(TurnBasedMultiplayer.UpdateMatchResult result) {
        TurnBasedMatch match = result.getMatch();
        //dismissSpinner();
        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }
        if (match.canRematch()) {
            //askForRematch();
        }

        isDoingTurn = (match.getTurnStatus() == TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN);

        if (isDoingTurn) {
            updateMatch(match);
            return;
        }

        //setViewVisibility();
    }

    private void processResult(TurnBasedMultiplayer.LeaveMatchResult result) {
        TurnBasedMatch match = result.getMatch();
        //dismissSpinner();
        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }
        isDoingTurn = (match.getTurnStatus() == TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN);
        showWarning("Left", "You've left this match.");
    }

    private void processResult(TurnBasedMultiplayer.InitiateMatchResult result) {
        TurnBasedMatch match = result.getMatch();
        //dismissSpinner();

        if (!checkStatusCode(match, result.getStatus().getStatusCode())) {
            return;
        }

        if (match.getData() != null) {
            // This is a game that has already started, so I'll just start
            updateMatch(match);
            return;
        }

        startMatch(match);
    }

    private void processResult(TurnBasedMultiplayer.CancelMatchResult result) {
        //dismissSpinner();

        if (!checkStatusCode(null, result.getStatus().getStatusCode())) {
            return;
        }

        isDoingTurn = false;

        showWarning("Match",
                "This match is canceled.  All other players will have their game ended.");
    }

    private boolean checkStatusCode(TurnBasedMatch match, int statusCode) {
        switch (statusCode) {
            case GamesStatusCodes.STATUS_OK:
                return true;
            case GamesStatusCodes.STATUS_NETWORK_ERROR_OPERATION_DEFERRED:
                // This is OK; the action is stored by Google Play Services and will
                // be dealt with later.
                Toast.makeText(
                        this,
                        "Stored action for later.  (Please remove this toast before release.)",
                        Toast.LENGTH_SHORT).show();
                // NOTE: This toast is for informative reasons only; please remove
                // it from your final application.
                return true;
            case GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER:
                showErrorMessage(match, statusCode, 1);
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_ALREADY_REMATCHED:
                showErrorMessage(match, statusCode,2);
                break;
            case GamesStatusCodes.STATUS_NETWORK_ERROR_OPERATION_FAILED:
                showErrorMessage(match, statusCode,3);
                break;
            case GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED:
                showErrorMessage(match, statusCode,4);
                break;
            case GamesStatusCodes.STATUS_INTERNAL_ERROR:
                showErrorMessage(match, statusCode,5);
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_INACTIVE_MATCH:
                showErrorMessage(match, statusCode,6);
                break;
            case GamesStatusCodes.STATUS_MATCH_ERROR_LOCALLY_MODIFIED:
                showErrorMessage(match, statusCode,7);
                break;
            default:
                showErrorMessage(match, statusCode, 8);
                Log.d("Error", "Did not have warning or string to deal with: "
                        + statusCode);
        }

        return false;
    }

    public void updateMatch(TurnBasedMatch match) {
        mMatch = match;

        int status = match.getStatus();
        int turnStatus = match.getTurnStatus();

        switch (status) {
            case TurnBasedMatch.MATCH_STATUS_CANCELED:
                showWarning("Canceled!", "This game was canceled!");
                return;
            case TurnBasedMatch.MATCH_STATUS_EXPIRED:
                showWarning("Expired!", "This game is expired.  So sad!");
                return;
            case TurnBasedMatch.MATCH_STATUS_AUTO_MATCHING:
                showWarning("Waiting for auto-match...",
                        "We're still waiting for an automatch partner.");
                return;
            case TurnBasedMatch.MATCH_STATUS_COMPLETE:
                if (turnStatus == TurnBasedMatch.MATCH_TURN_STATUS_COMPLETE) {
                    showWarning(
                            "Complete!",
                            "This game is over; someone finished it, and so did you!  There is nothing to be done.");
                    break;
                }

                // Note that in this state, you must still call "Finish" yourself,
                // so we allow this to continue.
                showWarning("Complete!",
                        "This game is over; someone finished it!  You can only finish it now.");
        }

        // OK, it's active. Check on turn status.
        switch (turnStatus) {
            case TurnBasedMatch.MATCH_TURN_STATUS_MY_TURN:
                //mTurnData = SkeletonTurn.unpersist(mMatch.getData());
                //setGameplayUI();
                return;
            case TurnBasedMatch.MATCH_TURN_STATUS_THEIR_TURN:
                // Should return results.
                showWarning("Alas...", "It's not your turn.");
                break;
            case TurnBasedMatch.MATCH_TURN_STATUS_INVITED:
                showWarning("Good inititative!",
                        "Still waiting for invitations.\n\nBe patient!");
        }

        //mTurnData = null;

        //setViewVisibility();
    }

    public void startMatch(TurnBasedMatch match) {
        //mTurnData = new SkeletonTurn();
        // Some basic turn data
        //mTurnData.data = "First turn";

        mMatch = match;

        String playerId = Games.Players.getCurrentPlayerId(mGoogleApiClient);
        String myParticipantId = mMatch.getParticipantId(playerId);

        //showSpinner();

        Games.TurnBasedMultiplayer.takeTurn(mGoogleApiClient, match.getMatchId(),
                null, myParticipantId).setResultCallback(
                new ResultCallback<TurnBasedMultiplayer.UpdateMatchResult>() {
                    @Override
                    public void onResult(TurnBasedMultiplayer.UpdateMatchResult result) {
                        processResult(result);
                    }
                });
    }
*/
}
