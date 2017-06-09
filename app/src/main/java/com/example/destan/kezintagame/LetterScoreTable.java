package com.example.destan.kezintagame;

import java.util.HashMap;

/**
 * Created by destan on 09.06.2017.
 */


public class LetterScoreTable {

    private static HashMap<Character,Integer> scores = new HashMap<>();

    public static void setLetterValues(){
        scores.put('a',new Integer(1));
        scores.put('b',new Integer(3));
        scores.put('c',new Integer(3));
        scores.put('ç',new Integer(5));
        scores.put('d',new Integer(2));
        scores.put('e',new Integer(1));
        scores.put('f',new Integer(4));
        scores.put('g',new Integer(2));
        scores.put('ğ',new Integer(7));
        scores.put('h',new Integer(4));
        scores.put('ı',new Integer(1));
        scores.put('i',new Integer(1));
        scores.put('j',new Integer(8));
        scores.put('k',new Integer(5));
        scores.put('l',new Integer(1));
        scores.put('m',new Integer(3));
        scores.put('n',new Integer(1));
        scores.put('o',new Integer(1));
        scores.put('ö',new Integer(5));
        scores.put('p',new Integer(3));
        scores.put('q',new Integer(10));
        scores.put('r',new Integer(1));
        scores.put('s',new Integer(1));
        scores.put('ş',new Integer(6));
        scores.put('t',new Integer(1));
        scores.put('u',new Integer(1));
        scores.put('ü',new Integer(4));
        scores.put('v',new Integer(4));
        scores.put('w',new Integer(4));
        scores.put('x',new Integer(8));
        scores.put('y',new Integer(4));
        scores.put('z',new Integer(10));
    }

    public static int getValue(Character character){
        return scores.get(character);
    }



}
