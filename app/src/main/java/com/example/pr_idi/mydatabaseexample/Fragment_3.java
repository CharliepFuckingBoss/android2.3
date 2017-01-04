package com.example.pr_idi.mydatabaseexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by carlota on 3/1/17.
 */


public class Fragment_3 extends Fragment {
    static final String MENU_ITEM = "Main";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_3, container, false);
        return view;
    }


}