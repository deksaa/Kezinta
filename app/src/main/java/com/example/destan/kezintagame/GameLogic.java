package com.example.destan.kezintagame;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destan on 08.06.2017.
 */

public class GameLogic {

    private String word;
    private int score;
    private char lastChar;

    public GameLogic(String word){
        LetterScoreTable.setLetterValues();
        setWord(word);
        calculateScore();
        findLastChar();
    }

    public String getWord() {
        return word;
    }

    private void setWord(String word) {
        this.word = word;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public char getLastChar() {
        return lastChar;
    }

    private void setLastChar(char lastChar) {
        this.lastChar = lastChar;
    }

    private void calculateScore(){
        int score = 0;
        char[] chars = this.word.toCharArray();
        for(int i = 0 ; i < chars.length ; i++)
            score += LetterScoreTable.getValue(chars[i]);
        setScore(score);
    }

    private void findLastChar(){
        char[] chars = this.word.toCharArray();
        int size = chars.length;
        setLastChar(chars[size - 1]);
    }

    public static boolean isOverlap(GameLogic gameLogic,List<GameLogic> list){
        String s = gameLogic.getWord();
        boolean flag = false;
        Log.e("String s:",s.toString());
        for (GameLogic object:list) {
            Log.e("Object:",object.getWord());
            if(object.getWord().equalsIgnoreCase(s)) {
                flag = !flag;
            }
        }
        return flag;
    }

}
