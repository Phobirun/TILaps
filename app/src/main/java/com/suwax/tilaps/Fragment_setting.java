package com.suwax.tilaps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_setting extends Fragment {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "format";
    private SharedPreferences mSettings;

    RadioButton radioButton_raw;
    RadioButton radioButton_jpg;
    RadioButton radioButton_rawjpg;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {


        View fragment = inflater.inflate(R.layout.fragment_setting,container,false);
        radioButton_raw = fragment.findViewById(R.id.radioButton_raw);
        radioButton_jpg = fragment.findViewById(R.id.radioButton_jpg);
        radioButton_rawjpg = fragment.findViewById(R.id.radioButton_raw_jpg);

        radioButton_raw.setOnClickListener(radioButtonClickListener);
        radioButton_jpg.setOnClickListener(radioButtonClickListener);
        radioButton_rawjpg.setOnClickListener(radioButtonClickListener);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        try {
            if (mSettings.getInt(APP_PREFERENCES_COUNTER, 0) == 0) {
                radioButton_jpg.performClick();
            } else if (mSettings.getInt(APP_PREFERENCES_COUNTER, 0) == 1) {
                radioButton_raw.performClick();
            } else {
                radioButton_rawjpg.performClick();
            }
        }catch (Exception e){
            radioButton_jpg.performClick();
        }


        return fragment;
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            SharedPreferences.Editor editor = mSettings.edit();


            switch (rb.getId()) {
                case R.id.radioButton_raw: {

                    editor.putInt(APP_PREFERENCES_COUNTER, 1).apply();
                }
                    break;
                case R.id.radioButton_jpg: {

                    editor.putInt(APP_PREFERENCES_COUNTER, 0).apply();
                }
                    break;
                case R.id.radioButton_raw_jpg: {

                    editor.putInt(APP_PREFERENCES_COUNTER, 2).apply();
                }
                    break;

                default:
                    break;
            }
        }
    };
}
