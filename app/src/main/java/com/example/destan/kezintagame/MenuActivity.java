package com.example.destan.kezintagame;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MenuActivity extends Activity {

    /*private MediaPlayer music;
    private int duration;
    private boolean musicFlag;*/

    AlphaAnimation alphaAnimation;

    ImageView playButton, noAdsButton, shareButton, rankButton, logo;

    RotateAnimation rotateRight;
    RotateAnimation rotateLeft;

    List<ImageView> imageViews = new ArrayList<>();


    private void init() {
        logo = (ImageView)findViewById(R.id.logo);
        playButton = (ImageView) findViewById(R.id.playButton);
        noAdsButton = (ImageView) findViewById(R.id.noAdsButton);
        shareButton = (ImageView) findViewById(R.id.shareButton);
        rankButton = (ImageView) findViewById(R.id.rankButton);

        imageViews.add(logo);
        imageViews.add(playButton);
        imageViews.add(noAdsButton);
        imageViews.add(shareButton);
        imageViews.add(rankButton);

        logo.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        noAdsButton.setVisibility(View.INVISIBLE);
        shareButton.setVisibility(View.INVISIBLE);
        rankButton.setVisibility(View.INVISIBLE);
    }

    private void startRotateAnimation(ImageView imageView){
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

    private void startIntroAnimation(int duration){
        for(final ImageView imageView : imageViews){
            imageView.setVisibility(View.VISIBLE);
            alphaAnimation = new AlphaAnimation(0.0f,1.0f);
            alphaAnimation.setDuration(duration);
            imageView.startAnimation(alphaAnimation);
            if(imageView.equals(playButton))
                startRotateAnimation(playButton);
            duration+=500;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        init();


        playButton.setOnTouchListener(new View.OnTouchListener() {

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
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        //music.stop();
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
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
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
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
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
                        ImageView view = (ImageView) v;
                        //overlay is black with transparency of 0x77 (119)
                        view.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
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



        @Override
        protected void onStart () {
            super.onStart();
            startIntroAnimation(1200);
        }
        @Override
        protected void onPause () {
            super.onPause();
        }

        @Override
        protected void onResume () {
            super.onResume();
        }
    }
