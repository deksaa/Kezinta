package com.example.destan.kezintagame;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends Activity {

    String guess = "";
    String word = "";
    String lastCharOfWord ="k";
    String[] letters = {"A", "B", "C", "Ç" ,"D", "E", "F", "G",
                        "Ğ", "H", "I", "İ" ,"J", "K", "L", "M",
                        "N", "O", "Ö", "P" ,"R", "S", "Ş", "T",
                        "U", "Ü", "V", "Y" ,"Z", "←", "↪"
                        };

    TextView textGuess;
    GridView gridKeyboard;
    ListView listViewInputs;

    ArrayList wordCollection;//It stores the all of words
    List<String> wordListView;//It stores the all of the word that come from user

    MediaPlayer music;
    long duration;

    //Initializing view components,ArrayLists and some methods(read from raw,generate random word for start,find last char of first word)
    public void init(){


        textGuess = (TextView) findViewById(R.id.textView);
        //gridKeyboard = (GridView) findViewById(R.id.gridView);
        listViewInputs = (ListView) findViewById(R.id.listView1);

        wordCollection = new ArrayList<>();
        wordListView = new ArrayList<>();

        readFromRaw();
        generateWord();
        findLastChar(wordListView);

        textGuess.setTextSize(TypedValue.COMPLEX_UNIT_SP, 28);
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*runOnUiThread(new Runnable(){
            @Override
            public void run(){
                // change UI elements here
            }
        });*/


        setContentView(R.layout.activity_main);
/*
        this.init();



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
        music = MediaPlayer.create(MainActivity.this,R.raw.menu);
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
        music.seekTo((int)duration);
        music.start();
    }
}
