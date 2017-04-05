package com.example.destan.kezintagame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu2Activity extends Activity {

    private MediaPlayer music;
    private int duration;
    private boolean musicFlag;

    ImageView playButton, noAdsButton, settingsButton, shareButton, rankButton;

    private void init() {
        playButton = (ImageView) findViewById(R.id.playButton);
        noAdsButton = (ImageView) findViewById(R.id.noAdsButton);
        settingsButton = (ImageView) findViewById(R.id.settingsButton);
        shareButton = (ImageView) findViewById(R.id.shareButton);
        rankButton = (ImageView) findViewById(R.id.rankButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        init();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music.stop();
                Intent goToMainActivity = new Intent(Menu2Activity.this, MainActivity.class);
                Menu2Activity.this.finish();
                startActivity(goToMainActivity);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        music = MediaPlayer.create(Menu2Activity.this, R.raw.menumusic);
        music.start();
        music.setLooping(true);
    }
    @Override
    protected void onPause(){
        super.onPause();
        duration = music.getCurrentPosition();
        music.pause();
        musicFlag = true;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(musicFlag){
            music.seekTo(duration);
            music.start();
        }

    }
}