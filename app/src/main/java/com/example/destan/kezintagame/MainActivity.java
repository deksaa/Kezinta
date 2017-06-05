package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;
import com.github.zagum.switchicon.SwitchIconView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

public class MainActivity extends Activity {

    int duration;
    boolean musicFlag;

    ArrayList wordCollection;//It stores the all of words
    List<String> wordListView;//It stores the all of the word that come from user
    List<ImageView> keyboardImageViews;
    ArrayList<ImageView> inputImages;

    MediaPlayer music;

    LinearLayout keyboardView;
    LinearLayout backGround;
    LinearLayout inputWordLayout;
    LinearLayout wordsLayout;

    AutofitTextView newInputTextView;

    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;

    MenuActivity menuActivity;

    Thread thread;

    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init() {

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

        musicFlag = true;

        menuActivity = new MenuActivity();

        readFromRaw();
    }

    //Check the word if it exist and it's not repeated
    public boolean checkWord(String word) {

        if (wordCollection.contains(word) && !wordListView.contains(word))
            return true;
        else
            return false;

    }

    //Read the .txt file line by line and store in wordCollection which type is ArrayList
    public void readFromRaw() {

        try {
            InputStream fis = this.getResources().openRawResource(R.raw.turkish_db);
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
        Toast toast = new Toast(MainActivity.this);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        this.init();

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


        newInputTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(MainActivity.this,"Long Click",Toast.LENGTH_SHORT).show();
                showCustomToast("+25 Puan");
                //send word to wordsLayout.
                return true;
            }
        });

        //This listener is used to delete last character.
        newInputTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("User Action", "Delete the last char");
                if (newInputTextView.getText().toString().length() != 0)
                    newInputTextView.setText(backSpace(newInputTextView.getText().toString()));
                else
                    showCustomToast("Silinecek harf kalmadı");
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


/*

        ArrayAdapter<String> adapterGrid = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, letters);
        final ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this,R.layout.my_text,R.id.textItem,wordListView);

        gridKeyboard.setAdapter(adapterGrid);
        listViewInputs.setAdapter(adapterList);

        textGuess.setText("");

        gridKeyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (!letters[position].equals("←") && !letters[position].equals("↪")) {

                    guess += letters[position];
                    guess = guess.toLowerCase();
                    textGuess.setText(guess);


                } else if (letters[position].equals("←")) {

                    if (!guess.equals("")) {

                        guess = guess.replaceFirst(String.valueOf(guess.charAt(guess.length() - 1)), "");
                        textGuess.setText(guess);

                    } else {

                        Toast.makeText(MainActivity.this, "No letter to delete", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if(!guess.equals("")){
                        if(guess.startsWith(findLastChar(wordListView)) && checkWord(guess)){

                            wordListView.add(guess);
                            listViewInputs.setAdapter(adapterList);
                            textGuess.setText("");

                            Toast.makeText(MainActivity.this,
                                    "RIGHT " + "Last Char:" + findLastChar(wordListView),
                                     Toast.LENGTH_LONG).show();

                            guess = "";



                        }else{
                            Toast.makeText(MainActivity.this,
                                    "WRONG " + "Last Char:" + findLastChar(wordListView),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(MainActivity.this, "I can see no word", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        */


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("OnStart Method worked.", "MusicFlag value:" + musicFlag);
        music = MediaPlayer.create(MainActivity.this, R.raw.gamemod2);
        music.setLooping(true);
        if (musicFlag) {
            music.start();
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
    public void onBackPressed() {
        Log.i("OnBackPressed worked.", "Now go to MainActivity.");

        final Dialog dialogue = new Dialog(MainActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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
                        Intent goToMenuActivity = new Intent(MainActivity.this, MenuActivity.class);
                        MainActivity.this.finish();
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
}


