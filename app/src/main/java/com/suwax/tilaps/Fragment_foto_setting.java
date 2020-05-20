package com.suwax.tilaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_foto_setting extends Fragment {

    Button button_shot_count;
    Button button_shot_interval;
    EditText editText_shot_count;
    EditText editText_shot_interval;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {

        //Устанавливаем нашему классу внешний вид с fragment1.xml:
        View fragment = inflater.inflate(R.layout.fragment_foto_setting,container,false);
        editText_shot_count = fragment.findViewById(R.id.editText_shot_count);
        editText_shot_interval = fragment.findViewById(R.id.editText_shot_interval);
        button_shot_interval = fragment.findViewById(R.id.button_shot_interval);
        button_shot_count = fragment.findViewById(R.id.button_shot_count);

        editText_shot_count.setText(((MainActivity)getActivity()).getShot()+"");
        editText_shot_interval.setText(((MainActivity)getActivity()).getShotTime()+"");

        button_shot_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((MainActivity)getActivity()).setShot(Integer.valueOf(editText_shot_count.getText()+""));
                    Toast.makeText(getContext(),"Кол-во кадров установлено",Toast.LENGTH_LONG).show();
                }catch (Exception e){}
            }
        });


        button_shot_interval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((MainActivity) getActivity()).setShotTime(Integer.valueOf((editText_shot_interval.getText().toString())));
                    Toast.makeText(getContext(), "Интервал установлен", Toast.LENGTH_LONG).show();
                }catch (Exception e){}
            }
        });


        return fragment;
    }

}
