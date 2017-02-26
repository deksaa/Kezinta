package com.example.destan.kezintagame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class SplashScreen extends Activity {

    int j=0;

    boolean splashLoaded = false;

    ImageView[] imageViews = new ImageView[7];
    Animation[] animations = new Animation[7];

    Runnable runnable;
    Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        imageViews[0] = (ImageView)findViewById(R.id.imageView1);
        imageViews[1] = (ImageView)findViewById(R.id.imageView2);
        imageViews[2] = (ImageView)findViewById(R.id.imageView3);
        imageViews[3] = (ImageView)findViewById(R.id.imageView4);
        imageViews[4] = (ImageView)findViewById(R.id.imageView5);
        imageViews[5] = (ImageView)findViewById(R.id.imageView6);
        imageViews[6] = (ImageView)findViewById(R.id.imageView7);

        imageViews[0].setVisibility(View.INVISIBLE);
        imageViews[1].setVisibility(View.INVISIBLE);
        imageViews[2].setVisibility(View.INVISIBLE);
        imageViews[3].setVisibility(View.INVISIBLE);
        imageViews[4].setVisibility(View.INVISIBLE);
        imageViews[5].setVisibility(View.INVISIBLE);
        imageViews[6].setVisibility(View.INVISIBLE);

        animations[0] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[1] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[2] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[3] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[4] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[5] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
        animations[6] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);

        animations[0].setAnimationListener(LastAnimationListener);
        animations[3].setAnimationListener(LastAnimationListener);
        animations[6].setAnimationListener(LastAnimationListener);

        startMyAnimation(1000);

    }

    Animation.AnimationListener LastAnimationListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
            //getActionBar().setBackgroundDrawable(Dr);
            if(animations[0].equals(animation)){
                ObjectAnimator colorFade = ObjectAnimator.ofObject(findViewById(R.id.activity_splash),
                        "backgroundColor", new ArgbEvaluator(), Color.argb(0,0,0,0), Color.argb(255,158,158,158));
                colorFade.setDuration(1300);
                colorFade.start();
            }

            if(animations[3].equals(animation)){
                ObjectAnimator colorFade = ObjectAnimator.ofObject(findViewById(R.id.activity_splash),
                        "backgroundColor", new ArgbEvaluator(), Color.argb(255,158,158,158), Color.argb(255,158,158,150));
                colorFade.setDuration(1000);
                colorFade.start();
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(animations[6].equals(animation)) {
                Intent goToMainActivity = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.finish();
                startActivity(goToMainActivity);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    public void startMyAnimation(final long duration){

        runnable = new Runnable() {
            int i=0;
            @Override
            public void run() {
                if(i != imageViews.length){
                        imageViews[i].setVisibility(View.VISIBLE);
                        animations[i].setDuration(duration);
                        imageViews[i].startAnimation(animations[i]);
                        i++;
                        handler.postDelayed(runnable, duration / 6);
                }else
                    handler.removeCallbacks(runnable);
            }
        };runnable.run();
    }
}
