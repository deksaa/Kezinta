package com.example.destan.kezintagame;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by destan on 28.05.2017.
 */

public class FragmentMenu extends Fragment {

    LinearLayout singleOption,multiOption,backOption;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.i("Menu Fragment:","onCreateView is worked.");

        View view = inflater.inflate(R.layout.fragmentmenu,container,false);
        singleOption = (LinearLayout)view.findViewById(R.id.singleLayout);
        multiOption = (LinearLayout)view.findViewById(R.id.multiLayout);
        backOption = (LinearLayout)view.findViewById(R.id.goBackLayout);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
