package com.example.david.blank_education;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by 123 on 2015/11/14.
 */
public class LifeFragment extends Fragment {
    private View parentView;
    private ImageButton func_1, fun_2, func_3, func_4;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_life, container, false);
        return parentView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        TextView title = (TextView)getActivity().findViewById(R.id.textview_main_title_text);
        title.setText(getString(R.string.life_title));
    }
}
