package com.example.destan.kezintagame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.zagum.switchicon.SwitchIconView;
import com.tomer.fadingtextview.FadingTextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    int duration;
    float screenWidth;
    boolean musicFlag;
    float inputTextSize;

    ArrayList wordCollection;//It stores the all of words
    List<String> wordListView;//It stores the all of the word that come from user
    List<ImageView> keyboardImageViews;
    ArrayList<ImageView> inputImages;

    MediaPlayer music;

    TextView inputText;

    FadingTextView fTextViewInfos;

    LinearLayout keyboardView;
    LinearLayout inputView;

    LinearLayout backGround;

    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;

    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init(){

        //inputText = (TextView)findViewById(R.id.textInput);
        //inputTextSize = inputText.getTextSize();

        switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);

        fTextViewInfos = (FadingTextView) findViewById(R.id.fadingTextView);

        keyboardView = (LinearLayout)findViewById(R.id.keyboardLayout);
        inputView = (LinearLayout)findViewById(R.id.inputLayout);

        keyboardImageViews = new ArrayList<>();

        backGround = (LinearLayout)findViewById(R.id.activity_main);

        inputText = (TextView)findViewById(R.id.inputText);

        keyboardImageViews.add((ImageView)findViewById(R.id.q));
        keyboardImageViews.add((ImageView)findViewById(R.id.w));
        keyboardImageViews.add((ImageView)findViewById(R.id.e));
        keyboardImageViews.add((ImageView)findViewById(R.id.r));
        keyboardImageViews.add((ImageView)findViewById(R.id.t));
        keyboardImageViews.add((ImageView)findViewById(R.id.y));
        keyboardImageViews.add((ImageView)findViewById(R.id.u));
        keyboardImageViews.add((ImageView)findViewById(R.id.ii));
        keyboardImageViews.add((ImageView)findViewById(R.id.o));
        keyboardImageViews.add((ImageView)findViewById(R.id.p));
        keyboardImageViews.add((ImageView)findViewById(R.id.gi));
        keyboardImageViews.add((ImageView)findViewById(R.id.ui));
        keyboardImageViews.add((ImageView)findViewById(R.id.a));
        keyboardImageViews.add((ImageView)findViewById(R.id.s));
        keyboardImageViews.add((ImageView)findViewById(R.id.d));
        keyboardImageViews.add((ImageView)findViewById(R.id.f));
        keyboardImageViews.add((ImageView)findViewById(R.id.g));
        keyboardImageViews.add((ImageView)findViewById(R.id.h));
        keyboardImageViews.add((ImageView)findViewById(R.id.j));
        keyboardImageViews.add((ImageView)findViewById(R.id.k));
        keyboardImageViews.add((ImageView)findViewById(R.id.l));
        keyboardImageViews.add((ImageView)findViewById(R.id.si));
        keyboardImageViews.add((ImageView)findViewById(R.id.i));
        keyboardImageViews.add((ImageView)findViewById(R.id.z));
        keyboardImageViews.add((ImageView)findViewById(R.id.x));
        keyboardImageViews.add((ImageView)findViewById(R.id.c));
        keyboardImageViews.add((ImageView)findViewById(R.id.v));
        keyboardImageViews.add((ImageView)findViewById(R.id.b));
        keyboardImageViews.add((ImageView)findViewById(R.id.n));
        keyboardImageViews.add((ImageView)findViewById(R.id.m));
        keyboardImageViews.add((ImageView)findViewById(R.id.oi));
        keyboardImageViews.add((ImageView)findViewById(R.id.ci));

        wordCollection = new ArrayList<>();
        wordListView = new ArrayList<>();
        inputImages = new ArrayList<>();

        inputTextSize= inputText.getTextSize();

        musicFlag = true;

        readFromRaw();
    }

    //Check the word if it exist and it's not repeated
    public boolean checkWord(String word){

        if(wordCollection.contains(word) && !wordListView.contains(word))
            return true;
        else
            return false;

    }


    //Read the .txt file line by line and store in wordCollection which type is ArrayList
    public void readFromRaw(){

        try   {
            InputStream fis = this.getResources().openRawResource(R.raw.turkish_db);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            while ((line = br.readLine()) != null)
                wordCollection.add(line);

            //listWords.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wordCollection));
        }
        catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private String backSpace(String str){
        char[] ch = str.toCharArray();
        str = "";
        for(int i = 0; i < ch.length - 1 ; i++)
            str += ch[i];

        return str;
    }

    private void increaseTextSize(TextView tv){
        float currentTextSize = inputText.getTextSize();
        if(currentTextSize < inputTextSize)
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,currentTextSize + 3.5F);
    }

    private void decreaseTextSize(TextView tv){
        float currentTextSize = tv.getTextSize();
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,currentTextSize - 3.5F);
    }

    private String filterForChars(String s){

        if(s.equals("ii"))
            return "ı".toUpperCase();
        else if(s.equals("gi"))
            return "ğ".toUpperCase();
        else if(s.equals("ui"))
            return "ü".toUpperCase();
        else if(s.equals("si"))
            return "ş".toUpperCase();
        else if(s.equals("oi"))
            return "ö".toUpperCase();
        else if (s.equals("ci"))
            return "ç".toUpperCase();
        else if(s.equals("i"))
            return "İ";
        else
            return s.toUpperCase();
    }

    private void refreshInputText(String s){
        if(inputText.getText().toString().length() < 15){
            inputText.setText(inputText.getText().toString() + s);
            decreaseTextSize(inputText);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        this.init();

        inputText.setText("");

        switchIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon1.switchState();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (switchIcon1.isIconEnabled())
                            backGround.setBackgroundDrawable((getResources().getDrawable(R.drawable.gradientbg)));
                        else
                            backGround.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_line));
                    }
                });
            }
        });

        switchIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon2.switchState();
                if(!switchIcon2.isIconEnabled() && music.isPlaying()){
                    duration = music.getCurrentPosition();
                    music.pause();
                }else{
                    music.seekTo(duration);
                    music.start();
                }

            }
        });

        //This listener is used to delete last character.
        inputText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.i("User Action","Delete the last char");
                    inputText.setText(backSpace(inputText.getText().toString()));
                    increaseTextSize(inputText);
            }
        });


        for (final ImageView Image : keyboardImageViews){
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
    protected void onStart(){
        super.onStart();
        Log.i("OnStart Method worked.","MusicFlag value:" + musicFlag);
        music = MediaPlayer.create(MainActivity.this,R.raw.gamemod2);
        music.setLooping(true);
        if(musicFlag)
            music.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("OnResume Method worked.","MusicFlag value:" + musicFlag);
        if(musicFlag) {
            music.seekTo(duration);
            music.start();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("OnPause Method worked.","MusicFlag value:" + musicFlag);
        duration = music.getCurrentPosition();
        music.pause();
        if(!switchIcon2.isIconEnabled())
            musicFlag = false;
        else
            musicFlag = true;
    }


    @Override
    public void onBackPressed(){
        Log.i("OnBackPressed worked.","Now go to MainActivity.");
        Intent goToMenuActivity = new Intent(MainActivity.this, MenuActivity.class);
        MainActivity.this.finish();
        startActivity(goToMenuActivity);
    }
}

