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
 * 碎片 1
 */
public class MyFragment extends Fragment {


    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        textView = new TextView(getActivity());
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLUE);
        textView.setTextSize(40);
        textView.setText("碎片 1");
        return textView;
    }


}
