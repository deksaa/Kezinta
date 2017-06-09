package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.provider.UserDictionary;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by destan on 06.06.2017.
 */

public class WordAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<GameLogic> mWords;

    public WordAdapter(Activity context, List<GameLogic> words) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mWords = words;
    }

    @Override
    public int getCount() {
        return mWords.size();
    }

    @Override
    public GameLogic getItem(int position) {
        return mWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;

        rowView = mInflater.inflate(R.layout.row, null);

        TextView textView = (TextView) rowView.findViewById(R.id.word);

        GameLogic word = mWords.get(position);

        textView.setText(word.getWord());

        return rowView;
    }
}
