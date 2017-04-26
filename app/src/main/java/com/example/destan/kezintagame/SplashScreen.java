package com.example.destan.kezintagame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;


public class SplashScreen extends Activity {

    Runnable runnable;
    Handler handler;

    AlphaAnimation alphaAnimation;
    ImageView myLogo;

    boolean isStart;

    private void init(){
        handler = new Handler();
        myLogo = (ImageView)findViewById(R.id.myLogo);
    }

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    setContentView(R.layout.activity_splash);

    this.init();

    startAnimation(2000,5500);

}


private void startAnimation(final int animationDuration, final int totalDuration){
    runnable = new Runnable() {
        @Override
        public void run() {
            if(!isStart){
                alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration(animationDuration);
                myLogo.startAnimation(alphaAnimation);
                isStart = !isStart;
                handler.postDelayed(runnable, totalDuration - animationDuration);
            }else{
                handler.removeCallbacks(runnable);
                Intent goToMainActivity = new Intent(SplashScreen.this, MenuActivity.class);
                SplashScreen.this.finish();
                startActivity(goToMainActivity);
                }
            }
        };runnable.run();
    }
}



























//    int j=0;
//
//    boolean splashLoaded = false;
//
//    ImageView[] imageViews = new ImageView[7];
//    Animation[] animations = new Animation[7];
//
//    Runnable runnable;
//    Handler handler = new Handler();
//
//    int redValue,greenValue,blueValue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        setContentView(R.layout.activity_splash);
//
//        imageViews[0] = (ImageView)findViewById(R.id.imageView1);
//        imageViews[1] = (ImageView)findViewById(R.id.imageView2);
//        imageViews[2] = (ImageView)findViewById(R.id.imageView3);
//        imageViews[3] = (ImageView)findViewById(R.id.imageView4);
//        imageViews[4] = (ImageView)findViewById(R.id.imageView5);
//        imageViews[5] = (ImageView)findViewById(R.id.imageView6);
//        imageViews[6] = (ImageView)findViewById(R.id.imageView7);
//
//        imageViews[0].setVisibility(View.INVISIBLE);
//        imageViews[1].setVisibility(View.INVISIBLE);
//        imageViews[2].setVisibility(View.INVISIBLE);
//        imageViews[3].setVisibility(View.INVISIBLE);
//        imageViews[4].setVisibility(View.INVISIBLE);
//        imageViews[5].setVisibility(View.INVISIBLE);
//        imageViews[6].setVisibility(View.INVISIBLE);
//
//        animations[0] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[1] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[2] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[3] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[4] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[5] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//        animations[6] = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
//
//        animations[0].setAnimationListener(LastAnimationListener);
//        animations[1].setAnimationListener(LastAnimationListener);
//        animations[2].setAnimationListener(LastAnimationListener);
//        animations[3].setAnimationListener(LastAnimationListener);
//        animations[4].setAnimationListener(LastAnimationListener);
//        animations[5].setAnimationListener(LastAnimationListener);
//        animations[6].setAnimationListener(LastAnimationListener);
//
//        startMyAnimation(1000);
//    }
//
//    @Override
//    protected  void onStart(){
//        super.onStart();
//
//    }
//
//    Animation.AnimationListener LastAnimationListener = new Animation.AnimationListener() {
//
//        @Override
//        public void onAnimationStart(Animation animation) {
//            //getActionBar().setBackgroundDrawable(Dr);
////            if(animations[3].equals(animation)){
////                ObjectAnimator colorFade = ObjectAnimator.ofObject(findViewById(R.id.activity_splash),
////                        "backgroundColor", new ArgbEvaluator(), Color.argb(255,158,158,158), Color.argb(255,158,158,150));
////                colorFade.setDuration(1000);
////                colorFade.start();
//
//            int i = 0;
//            //255,249,168,37
//            if(animations[i++].equals(animation)) {
//                ObjectAnimator colorFade = ObjectAnimator.ofObject(findViewById(R.id.activity_splash),
//                        "backgroundColor",
//                        new ArgbEvaluator(),
//                        Color.argb(0, redValue, greenValue, blueValue),
//                        Color.argb(255, redValue += 41, greenValue += 28, blueValue += 6));
//
//                colorFade.setDuration(1300);
//                colorFade.start();
//            }
////            }
//        }
//
//        @Override
//        public void onAnimationEnd(Animation animation) {
//            if(animations[6].equals(animation)) {
//                Intent goToMainActivity = new Intent(SplashScreen.this, MenuActivity.class);
//                SplashScreen.this.finish();
//                startActivity(goToMainActivity);
//            }else{
//
//            }
//        }
//
//        @Override
//        public void onAnimationRepeat(Animation animation) {
//            //...
//        }
//    };
//
//    private void startMyAnimation(final long duration){
//
//        runnable = new Runnable() {
//            int i=0;
//            @Override
//            public void run() {
//                if(i != imageViews.length){
//                    imageViews[i].setVisibility(View.VISIBLE);
//                    animations[i].setDuration(duration);
//                    imageViews[i].startAnimation(animations[i]);
//                    i++;
//                    handler.postDelayed(runnable, duration / 6);
//                }else
//                    handler.removeCallbacks(runnable);
//
//            }
//        };runnable.run();
//    }
//}


//<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
//        xmlns:tools="http://schemas.android.com/tools"
//        android:id="@+id/activity_splash"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:orientation="horizontal"
//        android:paddingTop="100dp"
//        tools:context="com.example.destan.kezintagame.SplashScreen">
//
//<ImageView
//android:id="@+id/imageView1"
//        android:src="@drawable/k"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="50dp"/>
//
//<ImageView
//android:id="@+id/imageView2"
//        android:src="@drawable/e"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//<ImageView
//android:id="@+id/imageView3"
//        android:src="@drawable/z"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//<ImageView
//android:id="@+id/imageView4"
//        android:src="@drawable/i"
//        android:layout_height="40dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//<ImageView
//android:id="@+id/imageView5"
//        android:src="@drawable/n"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//<ImageView
//android:id="@+id/imageView6"
//        android:src="@drawable/t"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//<ImageView
//android:id="@+id/imageView7"
//        android:src="@drawable/a"
//        android:layout_height="50dp"
//        android:layout_width="30dp"
//        android:layout_marginLeft="10dp" />
//
//</LinearLayout>