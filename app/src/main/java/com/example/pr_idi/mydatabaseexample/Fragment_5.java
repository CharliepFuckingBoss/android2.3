package com.example.pr_idi.mydatabaseexample;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by carlota on 4/1/17.
 */

public class Fragment_5 extends Fragment {
    static final String MENU_ITEM = "About";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_5, container, false);

        TextView textView = (TextView) view.findViewById(R.id.textView2);
        String text = "<font color=#ffb266>Books</font> is the perfect App to manage all the books you want, with many functionallities availables.";
        textView.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        TextView textView2 = (TextView) view.findViewById(R.id.textView4);
        String text2 = "<font color=#ffb266>Books</font> is the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfie the perfect App to ma wjkef  jfiew if   jweijijwifio nage all the books you want, with many functionallities availables.";
        textView2.setText(Html.fromHtml(text2), TextView.BufferType.SPANNABLE);

        TextView textView3 = (TextView) view.findViewById(R.id.textView5);
        String text3 = "asoidi joasjkfj safjoasijo  dfjidijcfms iueuf ei io oi t, with many func k iw f tionallitienkjknj ihgio e riofew wh heirf we feoif s availables.";
        textView3.setText(Html.fromHtml(text3), TextView.BufferType.SPANNABLE);



        return view;
    }
}
