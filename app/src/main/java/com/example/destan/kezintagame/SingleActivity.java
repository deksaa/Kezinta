package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zagum.switchicon.SwitchIconView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitTextView;

public class SingleActivity extends Activity {


    int duration;
    int turnCounter;
    int userScore;
    int machineScore;
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

    TextView turnCounterText;
    TextView machineScoreText;
    TextView userScoreText;

    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;

    MenuActivity menuActivity;

    WordAdapter wordAdapter;


    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init() {

        words = new ArrayList<>();

        WordStore.setStore(getApplicationContext(),R.raw.turkish_words);

        switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);

        keyboardView = (LinearLayout) findViewById(R.id.keyboardLayout);
        backGround = (LinearLayout) findViewById(R.id.activity_main);
        inputWordLayout = (LinearLayout) findViewById(R.id.inputLayout);
        wordsLayout = (LinearLayout) findViewById(R.id.wordsLayout);

        keyboardImageViews = new ArrayList<>();

        newInputTextView = (AutofitTextView) findViewById(R.id.inputText);
        userScoreText = (TextView)findViewById(R.id.userScoreTextview);
        machineScoreText = (TextView)findViewById(R.id.machineScoreTextview);
        turnCounterText = (TextView)findViewById(R.id.turnCounterTextview);

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

        turnCounter = 0;

        wordCollection = new ArrayList<>();
        wordListView = new ArrayList<>();
        inputImages = new ArrayList<>();

        wordList = (ListView)findViewById(R.id.wordList);

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
        Toast toast = new Toast(SingleActivity.this);
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

    public void initialScoreTable(boolean isTurkish){
        if(isTurkish){
            userScoreText.setText("Sen:0");
            turnCounterText.setText("Sıra:0");
            machineScoreText.setText("Kezinta:0");
        }else{
            userScoreText.setText("You:0");
            turnCounterText.setText("Turn:0");
            machineScoreText.setText("Kezinta:0");
        }
    }

    public void setScoreTable(int score,int turn,boolean isUser){
        if(isUser)
           userScoreText.setText(String.format("Sen:%d",score));
        else
            machineScoreText.setText(String.format("Kezinta:%d",score));

        turnCounterText.setText(String.format("Sıra:%d",turn));
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

    private String getInput(){
        return newInputTextView.getText().toString().toLowerCase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        this.init();

        initialScoreTable(true);

        wordAdapter = new WordAdapter(SingleActivity.this,words);
        wordList.setAdapter(wordAdapter);

        setMusicLevel(0.3f);

        showCustomToast("Bir kelime girin.");

        switchIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon1.switchState();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (switchIcon1.isIconEnabled()) {
                            backGround.setBackgroundResource(R.drawable.background_day);
                            wordList.setBackgroundResource(R.drawable.background_day);
                            inputWordLayout.setBackgroundResource(R.drawable.background_day);
                            userScoreText.setTextColor(Color.parseColor("#01332E"));
                            turnCounterText.setTextColor(Color.parseColor("#01332E"));
                            machineScoreText.setTextColor(Color.parseColor("#01332E"));
                        } else {
                            backGround.setBackgroundResource(R.drawable.background_night);
                            wordList.setBackgroundResource(R.drawable.background_night);
                            inputWordLayout.setBackgroundResource(R.drawable.background_night);
                            userScoreText.setTextColor(Color.parseColor("#ffc400"));
                            turnCounterText.setTextColor(Color.parseColor("#ffc400"));
                            machineScoreText.setTextColor(Color.parseColor("#ffc400"));
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

                if(turnCounter == 0) {

                    if(!getInput().isEmpty() && WordStore.isWordExist(getInput())){
                            //User turn
                            turnCounter++;
                            words.add(new GameLogic(getInput()));
                            wordAdapter.notifyDataSetChanged();
                            newInputTextView.setText("");
                            userScore += words.get(words.size() - 1).getScore();
                            setScoreTable(userScore,turnCounter,true);
                            //End User turn.

                            //Kezinta turn.
                            ++turnCounter;
                            words.add(new GameLogic(WordStore.getRandomWord(words.get(words.size() - 1).getLastChar())));
                            machineScore += words.get(words.size() - 1).getScore();
                            setScoreTable(machineScore,turnCounter,false);
                            wordAdapter.notifyDataSetChanged();
                            //End Kezinta turn.
                    }else{
                        showCustomToast("Boş veya geçersiz kelime.");
                    }
                }

                else {
                    if (!getInput().isEmpty() && WordStore.isWordExist(getInput()) &&
                            getInput().startsWith(String.valueOf(words.get(words.size() - 1).getLastChar()))) {

                        //Checking overlapping.
                        if (!GameLogic.isOverlap(new GameLogic(getInput().toLowerCase()), words)) {
                            //User turn.
                            turnCounter++;
                            words.add(new GameLogic(getInput()));
                            wordAdapter.notifyDataSetChanged();
                            newInputTextView.setText("");
                            userScore += words.get(words.size() - 1).getScore();
                            setScoreTable(userScore, turnCounter, true);
                            scrollMyListViewToBottom();
                            //User turn end.
                        }
                        else
                        {
                            showCustomToast("Kelime tekrarı!");
                        }

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 500ms
                                //Kezinta turn.
                                turnCounter++;
                                words.add(new GameLogic(WordStore.getRandomWord(words.get(words.size() - 1).getLastChar())));
                                wordAdapter.notifyDataSetChanged();
                                machineScore += words.get(words.size() - 1).getScore();
                                setScoreTable(machineScore,turnCounter,false);
                                scrollMyListViewToBottom();
                                //Kezinta turn end.
                            }
                        }, 500);

                    }
                    else
                    {
                        showCustomToast("Geçersiz kelime.");
                    }
                }
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("OnStart Method worked.", "MusicFlag value:" + musicFlag);
        music = MediaPlayer.create(SingleActivity.this, R.raw.gamemod2);
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

        final Dialog dialogue = new Dialog(SingleActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
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
                        Intent goToMenuActivity = new Intent(SingleActivity.this, MenuActivity.class);
                        SingleActivity.this.finish();
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
