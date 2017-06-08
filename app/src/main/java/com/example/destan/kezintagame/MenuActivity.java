package com.example.destan.kezintagame;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
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
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends FragmentActivity implements
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {

    AlphaAnimation alphaAnimation;

    ImageView playButton, noAdsButton, shareButton, rankButton, rateButton, logo;

    RotateAnimation rotateRight;
    RotateAnimation rotateLeft;

    List<ImageView> imageViews = new ArrayList<>();

    FragmentMenu fragmentMenu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public GoogleApiClient mGoogleApiClient;

    private static int RC_SIGN_IN = 9001;

    private boolean mUserRequestedSignIn = false;

    private void init() {
        Log.i("init():","init() is worked.");

        fragmentMenu = new FragmentMenu();

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

        //logo.setVisibility(View.INVISIBLE);
        //playButton.setVisibility(View.INVISIBLE);
        //noAdsButton.setVisibility(View.INVISIBLE);
        //shareButton.setVisibility(View.INVISIBLE);
        //rankButton.setVisibility(View.INVISIBLE);
    }

    private void startRotateAnimation(ImageView imageView) {
        Log.i("startRotateAnim():","startRotateAnimation() is worked.");
        rotateRight = new RotateAnimation(-2, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateLeft = new RotateAnimation(2, 2, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        rotateRight.setDuration(500);
        rotateLeft.setDuration(500);

        rotateLeft.setRepeatMode(Animation.REVERSE);
        rotateRight.setRepeatMode(Animation.REVERSE);

        rotateLeft.setRepeatCount(Animation.INFINITE);
        rotateRight.setRepeatCount(Animation.INFINITE);

        imageView.setAnimation(rotateRight);

        imageView.setDrawingCacheEnabled(true);
    }

    private void startIntroAnimation(int duration) {
        Log.i("startIntroAnim():","startIntroAnimation() is worked.");
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

    public void applyColorFilter(ImageView image,boolean applied){
        Log.i("applyColorFilter():","applyColorFilter() is worked.");
        if(applied) {
            image.getDrawable().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_ATOP);
            image.invalidate();
        }else{
            image.getDrawable().clearColorFilter();
            image.invalidate();
        }
    }

    private void getFragmentMenu(){
        Log.i("getFragmentMenu():","getFragmentMenu() is worked.");
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainMenu,fragmentMenu,"Fragment_Menu");
        fragmentTransaction.commit();
    }

    private void getShareMenu(){
        Log.i("getShareMenu():","getShareMenu() is worked.");
        try{
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Kezinta - A new word game");
            String sAux = "\nKezinta on Google Play Store\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(shareIntent, "Choose one for 'Kezinta'"));
        }catch (Exception e){
            Log.e("getShareMenu()",e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        Log.i("onCreate():","onCreate() is worked.");

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .build();

        this.init();

        startRotateAnimation(playButton);

        playButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("playButton:","playButton is touched.");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView)v,false);
                        getFragmentMenu();
                        break;
                    }
                }
                return true;
            }
        });

        noAdsButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("noAdsButtonButton:","noAdsButton is touched.");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        Toast.makeText(MenuActivity.this,
                                       "User:" + Games.Players
                                               .getCurrentPlayer(mGoogleApiClient)
                                               .getDisplayName(),Toast.LENGTH_LONG).show();
                        break;
                    }
                }

                return true;
            }
        });

        rankButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("rankButton:","rankButton is touched.");
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
                Log.i("rateButton:","rateButton is touched.");
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
            Log.i("shareButton:","shareButton is touched.");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter((ImageView) v,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter((ImageView) v,false);
                        getShareMenu();
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
        Log.i("onStart():","onStart() is worked.");
        mGoogleApiClient.connect();
        //startIntroAnimation(1200);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("onPause():","onPause() is worked.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("onResume():","onResume() is worked.");
    }

    @Override
    public void onBackPressed(){
        if(fragmentMenu.isVisible()){
            fragmentMenu.removeFragmentMenu();
        }else{
            final Dialog dialogue = new Dialog(MenuActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
            dialogue.setContentView(R.layout.dialogue);
            final ImageView exitImage = (ImageView)dialogue.findViewById(R.id.exitAppView);
            final ImageView stayImage = (ImageView)dialogue.findViewById(R.id.stayInAppView);
            dialogue.setCancelable(true);
            dialogue.show();

            exitImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i("exitImage:","exitImage is touched.");
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            applyColorFilter(exitImage,true);
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            applyColorFilter(exitImage,false);
                            MenuActivity.this.finish();
                            break;
                        }
                    }
                    return true;
                }
            });

            stayImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    Log.i("stayImage:","stayImage is touched.");
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            applyColorFilter(stayImage,true);
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL: {
                            applyColorFilter(stayImage,false);
                            dialogue.dismiss();
                            break;
                        }
                    }
                    return true;
                }
            });
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("onConnected():","onConnected() is worked.");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("onConnectionSus():","onConnectionSuspended() is worked.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
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
