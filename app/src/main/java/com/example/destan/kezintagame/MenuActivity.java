package com.example.destan.kezintagame;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    /*private MediaPlayer music;
    private int duration;
    private boolean musicFlag;*/

    AlphaAnimation alphaAnimation;

    ImageView playButton, noAdsButton, shareButton, rankButton, rateButton, logo;

    RotateAnimation rotateRight;
    RotateAnimation rotateLeft;

    List<ImageView> imageViews = new ArrayList<>();

    public GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;

    private boolean mUserRequestedSignIn = false;


    private void init() {
        logo = (ImageView) findViewById(R.id.logo);
        playButton = (ImageView) findViewById(R.id.playButton);
        noAdsButton = (ImageView) findViewById(R.id.noAdsButton);
        shareButton = (ImageView) findViewById(R.id.shareButton);
        rankButton = (ImageView) findViewById(R.id.rankButton);
        rateButton = (ImageView) findViewById(R.id.rateButton);

        imageViews.add(logo);
        imageViews.add(playButton);
        imageViews.add(shareButton);
        imageViews.add(rankButton);
        imageViews.add(rateButton);
        imageViews.add(noAdsButton);

        logo.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        noAdsButton.setVisibility(View.INVISIBLE);
        shareButton.setVisibility(View.INVISIBLE);
        rankButton.setVisibility(View.INVISIBLE);
    }

    private void startRotateAnimation(ImageView imageView) {
        rotateRight = new RotateAnimation(-2, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateLeft = new RotateAnimation(2, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateRight.setDuration(500);
        rotateLeft.setDuration(500);

        rotateLeft.setRepeatMode(Animation.REVERSE);
        rotateRight.setRepeatMode(Animation.REVERSE);

        rotateLeft.setRepeatCount(Animation.INFINITE);
        rotateRight.setRepeatCount(Animation.INFINITE);

        imageView.setAnimation(rotateRight);
    }

    private void startIntroAnimation(int duration) {
        for (final ImageView imageView : imageViews) {
            imageView.setVisibility(View.VISIBLE);
            alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(duration);
            imageView.startAnimation(alphaAnimation);
            if (imageView.equals(playButton))
                startRotateAnimation(playButton);
            duration += 500;
        }
    }

    private void applyColorFilter(ImageView image,boolean applied){
        if(applied) {
            image.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
            image.invalidate();
        }else{
            image.getDrawable().clearColorFilter();
            image.invalidate();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .build();
        init();


        playButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView)v,false);
                        Intent goToMainActivity = new Intent(MenuActivity.this, MainActivity.class);
                        MenuActivity.this.finish();
                        startActivity(goToMainActivity);
                        break;
                    }
                }

                return true;
            }
        });

        noAdsButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        break;
                    }
                }

                return true;
            }
        });

        rankButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        break;
                    }
                }

                return true;
            }
        });

        rateButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        break;
                    }
                }
                return true;
            }
        });

        shareButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        break;
                    }
                }

                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        startIntroAnimation(1200);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Status:", "Error...");
        Log.e("Message:", "ERROR CODE:" + connectionResult.getErrorCode());

        if (!mUserRequestedSignIn) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                } catch (IntentSender.SendIntentException e) {
                    Log.e("Error:", e.getMessage());
                }
            }
        }
    }
}
