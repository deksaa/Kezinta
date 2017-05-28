package com.example.destan.kezintagame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by destan on 28.05.2017.
 */

public class FragmentMenu extends Fragment {

    ImageView backImage;

    MenuActivity menuActivity;

    LinearLayout singleOption, multiOption, backOption;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public void removeFragmentMenu(){
        fragmentManager = getFragmentManager();
        FragmentMenu f = (FragmentMenu) fragmentManager.findFragmentByTag("Fragment_Menu");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(f != null)
            fragmentTransaction.remove(f).commit();
        else
            Toast.makeText(getActivity(),"Fragment not found",Toast.LENGTH_LONG).show();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.i("Menu Fragment:", "onCreateView is worked.");

        View view = inflater.inflate(R.layout.fragmentmenu, container, false);
        singleOption = (LinearLayout) view.findViewById(R.id.singleLayout);
        multiOption = (LinearLayout) view.findViewById(R.id.multiLayout);
        backOption = (LinearLayout) view.findViewById(R.id.goBackLayout);
        backImage = (ImageView) view.findViewById(R.id.back);

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

