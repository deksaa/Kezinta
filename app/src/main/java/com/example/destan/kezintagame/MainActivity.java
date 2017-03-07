package com.example.destan.kezintagame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.zagum.switchicon.SwitchIconView;
import com.tomer.fadingtextview.FadingTextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.datatype.Duration;

import static android.R.attr.key;


public class MainActivity extends Activity {

    String guess = "";
    String word = "";
    String lastCharOfWord ="k";

    //Keyboard keys are declareting.
    TextView keyboardKeyQ;
    TextView keyboardKeyW;
    TextView keyboardKeyE;
    TextView keyboardKeyR;
    TextView keyboardKeyT;
    TextView keyboardKeyY;
    TextView keyboardKeyU;
    TextView keyboardKeyI;
    TextView keyboardKeyO;
    TextView keyboardKeyP;
    TextView keyboardKeyĞ;
    TextView keyboardKeyÜ;
    TextView keyboardKeyA;
    TextView keyboardKeyS;
    TextView keyboardKeyD;
    TextView keyboardKeyF;
    TextView keyboardKeyG;
    TextView keyboardKeyH;
    TextView keyboardKeyJ;
    TextView keyboardKeyK;
    TextView keyboardKeyL;
    TextView keyboardKeyŞ;
    TextView keyboardKeyİ;
    TextView keyboardKeyZ;
    TextView keyboardKeyX;
    TextView keyboardKeyC;
    TextView keyboardKeyV;
    TextView keyboardKeyB;
    TextView keyboardKeyN;
    TextView keyboardKeyM;
    TextView keyboardKeyÖ;
    TextView keyboardKeyÇ;

    List<TextView> keyboardTextViews;

    ArrayList wordCollection;//It stores the all of words
    List<String> wordListView;//It stores the all of the word that come from user

    MediaPlayer music;

    TextView inputText;

    FadingTextView fTextViewInfos;
    int duration;

    LinearLayout keyboardView;

    private SwitchIconView switchIcon1;
    private SwitchIconView switchIcon2;

    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init(){

        //textGuess = (TextView) findViewById(R.id.textView);
        //gridKeyboard = (GridView) findViewById(R.id.gridView);
        //listViewInputs = (ListView) findViewById(R.id.listView1);



        keyboardTextViews = new ArrayList<>();

//        for(int i = 0 ; i < keyboardView.getChildCount();i++) {
//            Log.d("Flag", " " + keyboardView.getChildAt(i).getId());
//            if (keyboardView.getChildAt(i) instanceof TextView) {
//                keyboardTextViews.add((TextView) findViewById(keyboardView.getChildAt(i).getId()));
//
//            }
//        }


        keyboardTextViews.add((TextView)findViewById(R.id.textView1));
        keyboardTextViews.add((TextView)findViewById(R.id.textView2));
        keyboardTextViews.add((TextView)findViewById(R.id.textView3));
        keyboardTextViews.add((TextView)findViewById(R.id.textView4));
        keyboardTextViews.add((TextView)findViewById(R.id.textView5));
        keyboardTextViews.add((TextView)findViewById(R.id.textView6));
        keyboardTextViews.add((TextView)findViewById(R.id.textView7));
        keyboardTextViews.add((TextView)findViewById(R.id.textView8));
        keyboardTextViews.add((TextView)findViewById(R.id.textView9));
        keyboardTextViews.add((TextView)findViewById(R.id.textView10));
        keyboardTextViews.add((TextView)findViewById(R.id.textView11));
        keyboardTextViews.add((TextView)findViewById(R.id.textView12));
        keyboardTextViews.add((TextView)findViewById(R.id.textView13));
        keyboardTextViews.add((TextView)findViewById(R.id.textView14));
        keyboardTextViews.add((TextView)findViewById(R.id.textView15));
        keyboardTextViews.add((TextView)findViewById(R.id.textView16));
        keyboardTextViews.add((TextView)findViewById(R.id.textView17));
        keyboardTextViews.add((TextView)findViewById(R.id.textView18));
        keyboardTextViews.add((TextView)findViewById(R.id.textView19));
        keyboardTextViews.add((TextView)findViewById(R.id.textView20));
        keyboardTextViews.add((TextView)findViewById(R.id.textView21));
        keyboardTextViews.add((TextView)findViewById(R.id.textView22));
        keyboardTextViews.add((TextView)findViewById(R.id.textView23));
        keyboardTextViews.add((TextView)findViewById(R.id.textView24));
        keyboardTextViews.add((TextView)findViewById(R.id.textView25));
        keyboardTextViews.add((TextView)findViewById(R.id.textView26));
        keyboardTextViews.add((TextView)findViewById(R.id.textView27));
        keyboardTextViews.add((TextView)findViewById(R.id.textView28));
        keyboardTextViews.add((TextView)findViewById(R.id.textView29));
        keyboardTextViews.add((TextView)findViewById(R.id.textView30));
        keyboardTextViews.add((TextView)findViewById(R.id.textView31));
        keyboardTextViews.add((TextView)findViewById(R.id.textView32));
//
//
//
//
//        keyboardKeyQ = (TextView)findViewById(R.id.textView1);
//        keyboardKeyW = (TextView)findViewById(R.id.textView2);
//        keyboardKeyE = (TextView)findViewById(R.id.textView3);
//        keyboardKeyR = (TextView)findViewById(R.id.textView4);
//        keyboardKeyT = (TextView)findViewById(R.id.textView5);
//        keyboardKeyY = (TextView)findViewById(R.id.textView6);
//        keyboardKeyU = (TextView)findViewById(R.id.textView7);
//        keyboardKeyI = (TextView)findViewById(R.id.textView8);
//        keyboardKeyO = (TextView)findViewById(R.id.textView9);
//        keyboardKeyP = (TextView)findViewById(R.id.textView10);
//        keyboardKeyĞ = (TextView)findViewById(R.id.textView11);
//        keyboardKeyÜ = (TextView)findViewById(R.id.textView12);
//        keyboardKeyA = (TextView)findViewById(R.id.textView13);
//        keyboardKeyS = (TextView)findViewById(R.id.textView14);
//        keyboardKeyD = (TextView)findViewById(R.id.textView15);
//        keyboardKeyF = (TextView)findViewById(R.id.textView16);
//        keyboardKeyG = (TextView)findViewById(R.id.textView17);
//        keyboardKeyH = (TextView)findViewById(R.id.textView18);
//        keyboardKeyJ = (TextView)findViewById(R.id.textView19);
//        keyboardKeyK = (TextView)findViewById(R.id.textView20);
//        keyboardKeyL = (TextView)findViewById(R.id.textView21);
//        keyboardKeyŞ = (TextView)findViewById(R.id.textView22);
//        keyboardKeyİ = (TextView)findViewById(R.id.textView23);
//        keyboardKeyZ = (TextView)findViewById(R.id.textView24);
//        keyboardKeyX = (TextView)findViewById(R.id.textView25);
//        keyboardKeyC = (TextView)findViewById(R.id.textView26);
//        keyboardKeyV = (TextView)findViewById(R.id.textView27);
//        keyboardKeyB = (TextView)findViewById(R.id.textView28);
//        keyboardKeyN = (TextView)findViewById(R.id.textView29);
//        keyboardKeyM = (TextView)findViewById(R.id.textView30);
//        keyboardKeyÖ = (TextView)findViewById(R.id.textView31);
//        keyboardKeyÇ = (TextView)findViewById(R.id.textView32);
//
//


        wordCollection = new ArrayList<>();
        wordListView = new ArrayList<>();

        readFromRaw();
        generateWord();
        findLastChar(wordListView);

    }

    //Generate first word for start
    public void generateWord(){

        Random rnd = new Random();
        int size = wordCollection.size();

        do{
            word = (String) wordCollection.get(rnd.nextInt(size));
        }while(word.contains(" "));

        wordListView.add(word);

    }

    //Check the word if it exist and it's not repeated
    public boolean checkWord(String word){

        if(wordCollection.contains(word) && !wordListView.contains(word))
            return true;
        else
            return false;

    }


    //Find the last char of last word.
    public String findLastChar(List<String> list){

        String word = list.get(list.size()-1);
        lastCharOfWord = word.substring(word.length()-1);
        return lastCharOfWord;

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

    /*runOnUiThread(new Runnable(){
            @Override
            public void run(){
                // change UI elements here
            }
        });*/

    public String backSpace(String str){
        char[] ch = str.toCharArray();
        str = "";
        for(int i = 0; i < ch.length - 1 ; i++)
            str += ch[i];

        return str;
    }

//    public void keyboardListener(final TextView t){
//        t.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputText.setText(t.getText().toString());
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        inputText = (TextView)findViewById(R.id.textInput);
        switchIcon1 = (SwitchIconView) findViewById(R.id.switchIconView1);
        switchIcon2 = (SwitchIconView) findViewById(R.id.switchIconView2);
        fTextViewInfos = (FadingTextView) findViewById(R.id.fadingTextView);
        keyboardView = (LinearLayout)findViewById(R.id.keyboardLayout);

        this.init();


        switchIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchIcon1.switchState();
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
                inputText.setText(backSpace(inputText.getText().toString()));
            }
        });

        //This listener is used to listen keys to write on board.
        for (final TextView textView : keyboardTextViews){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("debug",textView.getText().toString());
                    inputText.setText(inputText.getText().toString() + textView.getText().toString());
                }
            });
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
    }

    @Override
    protected void onStart(){
        super.onStart();
        music = MediaPlayer.create(MainActivity.this,R.raw.gamemod2);
        music.setLooping(true);
        music.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        duration = music.getCurrentPosition();
        music.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        music.seekTo(duration);
        music.start();
    }
}
