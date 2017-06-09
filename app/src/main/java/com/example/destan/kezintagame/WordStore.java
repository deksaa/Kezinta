package com.example.destan.kezintagame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by destan on 09.06.2017.
 */

public class WordStore {

    private static ArrayList<String> words = new ArrayList<>();

    public static void setStore(Context context,int resId){

            InputStream inputStream = context.getResources().openRawResource(resId);

            InputStreamReader inputreader = new InputStreamReader(inputStream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;

            try {
                while (( line = buffreader.readLine()) != null)
                    words.add(line);

            } catch (IOException e) {
                Log.e("WordStore:",e.getMessage());
            }
        }

    public static boolean isWordExist(String word){
        if(words.contains(word))
            return true;
        return false;
    }

    public static String getRandomWord(char firstChar){
        Random random = new Random();
        int order = random.nextInt(10 - 3 + 1) + 3;//[3,10]

        switch (order){
            case 3:
                return get3DigitWord(3,firstChar);

            case 4:
                return get4DigitWord(4,firstChar);

            case 5:
                return get5DigitWord(5,firstChar);

            case 6:
                return get6DigitWord(6,firstChar);

            case 7:
                return get7DigitWord(7,firstChar);

            case 8:
                return get8DigitWord(8,firstChar);

            case 9:
                return get9DigitWord(9,firstChar);

            case 10:
                return get10DigitWord(10,firstChar);

            default:
                return "Error";
        }
    }

    private static int getRandomInteger(int max){
        Random random = new Random();
        return random.nextInt(max + 1);
    }

    private static ArrayList<String> getCustomLengthWords(int digit,char firstChar){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < words.size() ; i++)
            if(words.get(i).length() == digit && words.get(i).startsWith(String.valueOf(firstChar)))
                list.add(words.get(i));

        return list;
    }

    private static String get3DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get4DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get5DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get6DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get7DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get8DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get9DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    private static String get10DigitWord(int digit,char firstChar){
        ArrayList<String> list = getCustomLengthWords(digit,firstChar);
        int randomOrder = getRandomInteger(list.size());
        return list.get(randomOrder);
    }

    }


