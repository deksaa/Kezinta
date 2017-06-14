package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by destan on 28.05.2017.
 */

public class FragmentMenu extends Fragment {

    ImageView backImage,singleImage,multiImage;

    MenuActivity menuActivity;

    Intent goToGameActivity;

    LinearLayout singleOption, multiOption, backOption;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

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

    private void goToGamePlay(int flag){
        if(flag == 0)
            goToGameActivity = new Intent(getActivity(), SingleActivity.class);
        else
            goToGameActivity = new Intent(getActivity(),MultiActivity.class);

        getActivity().finish();
        startActivity(goToGameActivity);
    }

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

    private void showLanguageOptions(){
        final Dialog dialogue = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        dialogue.setContentView(R.layout.dialogue_options);
        final ImageView langTr = (ImageView) dialogue.findViewById(R.id.turkishOption);
        final ImageView langEn = (ImageView) dialogue.findViewById(R.id.englishOption);
        dialogue.setCancelable(true);
        dialogue.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.i("Menu Fragment:", "onCreateView is worked.");

        View view = inflater.inflate(R.layout.fragmentmenu, container, false);

        singleOption = (LinearLayout) view.findViewById(R.id.singleLayoutIn);
        multiOption = (LinearLayout) view.findViewById(R.id.multiLayoutIn);
        backOption = (LinearLayout) view.findViewById(R.id.goBackLayout);

        backImage = (ImageView) view.findViewById(R.id.back);
        singleImage = (ImageView) view.findViewById(R.id.singleImage);
        multiImage = (ImageView) view.findViewById(R.id.multiImage);

        menuActivity = new MenuActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Menu Fragment:", "onViewCreated is worked.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Menu Fragment:", "onStart is worked.");

        singleOption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        //menuActivity.applyColorFilter(singleImage, true);
                        applyColorFilter(singleImage,singleOption,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        //menuActivity.applyColorFilter(singleImage, false);
                        applyColorFilter(singleImage,singleOption,false);
                        //Here for new activity
                        //goToGamePlay(0);
                        showLanguageOptions();
                        break;
                    }
                }
                return true;
            }
        });

        multiOption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        applyColorFilter(multiImage,multiOption,true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        applyColorFilter(multiImage,multiOption,false);
                        //Here for new activity
                        //goToGamePlay(1);
                        break;
                    }
                }
                return true;
            }
        });

        backOption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        menuActivity.applyColorFilter(backImage, true);
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        menuActivity.applyColorFilter(backImage, false);
                        removeFragmentMenu();
                        break;
                    }
                }
                return true;
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Menu Fragment:", "onPause is worked.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Menu Fragment:", "onStop is worked.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Menu Fragment:", "onDestroyView is worked.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Menu Fragment:", "onDetach is worked.");
    }
}

