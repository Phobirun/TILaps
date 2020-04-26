package com.suwax.tilaps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_foto extends Fragment {


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {

        //Устанавливаем нашему классу внешний вид с fragment1.xml:
        View fragment = inflater.inflate(R.layout.fragment_foto,container,false);
        return fragment;
    }

}
