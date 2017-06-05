package com.example.destan.kezintagame;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by destan on 06.06.2017.
 */

public class WordListFragment extends Fragment {

    private static final String DEBUG_TAG = "Word List Fragment";

    WordListFragment wordListFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
/*
    public void removeFragmentMenu(){
        try {
            fragmentManager = getFragmentManager();
            menuActivity.fragmentMenu = (FragmentMenu) fragmentManager.findFragmentByTag("Fragment_Menu");
            fragmentTransaction = fragmentManager.beginTransaction();
            if (menuActivity.fragmentMenu != null)
                fragmentTransaction.remove(menuActivity.fragmentMenu).commit();
        }catch (NullPointerException npe){
            Log.e("removeFragmentMenu():",npe.getMessage());
        }
    }
*/
/*
    private void goToGamePlay(){
        Intent goToMainActivity = new Intent(getActivity(), MainActivity.class);
        getActivity().finish();
        startActivity(goToMainActivity);
        //getActivity().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
*/
/*
    private void applyColorFilter(ImageView view,LinearLayout layout,Boolean applied){
        if(applied) {
            view.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
            view.invalidate();
            layout.setBackgroundColor(Color.parseColor("#dacb54"));
            layout.invalidate();
        }else{
            view.getDrawable().clearColorFilter();
            view.invalidate();
            layout.setBackgroundColor(getResources().getColor(R.color.MainBackground));
            layout.invalidate();
        }
    }
*/

    private void init(){
        //...
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.i(DEBUG_TAG, "onCreateView is worked.");

        View view = inflater.inflate(R.layout.word_list_fragment, container, false);

        ListView wordList = (ListView)view.findViewById(R.id.wordList);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(DEBUG_TAG, "onViewCreated is worked.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG, "onStart is worked.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG, "onPause is worked.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG, "onStop is worked.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(DEBUG_TAG, "onDestroyView is worked.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(DEBUG_TAG, "onDetach is worked.");
    }
}
