package com.suwax.tilaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_menu extends Fragment {





    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Устанавливаем нашему классу внешний вид с fragment1.xml:
        View fragment = inflater.inflate(R.layout.fragment_menu,container,false);


        Button foto = (Button)fragment.findViewById(R.id.button_foto);
        foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_foto());
            }
        });

        Button video = (Button)fragment.findViewById(R.id.button_video);
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_video());
            }
        });

        Button foto_video = (Button)fragment.findViewById(R.id.button_foto_video);
        foto_video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_foto_video());
            }
        });

        Button gallery = (Button)fragment.findViewById(R.id.button_gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_gallery());
            }
        });

        Button calculator = (Button)fragment.findViewById(R.id.button_calculator);
        calculator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_calculator());
            }
        });

        Button setting = (Button)fragment.findViewById(R.id.button_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_setting());
            }
        });

        Button info = (Button)fragment.findViewById(R.id.button_info);
        info.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
                ChangingFragment(new Fragment_info());
            }
        });





        return fragment;//возврат фрагмента
    }

    public void ChangingFragment(Object o){
        ((MainActivity)getActivity()).fragmentActivityReplace(o);
    }

}
