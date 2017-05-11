package com.example.uc_viewPager.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 碎片7
 */
public class MyFragment7 extends Fragment {


    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(40);
        textView.setText("碎片 7");
        textView.setTextSize(40);
        textView.setBackgroundColor(Color.LTGRAY);
        return textView;
    }


}
