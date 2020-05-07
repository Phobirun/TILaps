package com.suwax.tilaps;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Fragment_calculator extends Fragment {


    private LinearLayout linerLayout_interval;
    private LinearLayout linerLayout_lenght;
    private LinearLayout linerLayout_quantity;
    private LinearLayout linerLayout_frequency;
    private LinearLayout linerLayout_duration;
    private LinearLayout linerLayout_size;
    private LinearLayout linerLayout_amount;

    private ImageButton imageButton_interval_minus;
    private ImageButton imageButton_lenght_minus;
    private ImageButton imageButton_quantity_minus;
    private ImageButton imageButton_frequency_minus;
    private ImageButton imageButton_duration_minus;
    private ImageButton imageButton_size_minus;
    private ImageButton imageButton_amount_minus;

    private ImageButton imageButton_interval_plus;
    private ImageButton imageButton_lenght_plus;
    private ImageButton imageButton_quantity_plus;
    private ImageButton imageButton_frequency_plus;
    private ImageButton imageButton_duration_plus;
    private ImageButton imageButton_size_plus;
    private ImageButton imageButton_amount_plus;

    private CheckBox checkBox_interval;
    private CheckBox checkBox_lenght;
    private CheckBox checkBox_quantity;
    private CheckBox checkBox_frequency;
    private CheckBox checkBox_duration;
    private CheckBox checkBox_size;
    private CheckBox checkBox_amount;

    private EditText editText_interval;
    private EditText editText_lenght;
    private EditText editText_quantity;
    private EditText editText_frequency;
    private EditText editText_duration;
    private EditText editText_size;
    private EditText editText_amount;

    private TextView textView_size;

    private View fragment;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {

        //Устанавливаем нашему классу внешний вид с fragment1.xml:
        fragment = inflater.inflate(R.layout.fragment_calculator,container,false);


        linerLayout_interval = fragment.findViewById(R.id.linerLayout_interval);
        linerLayout_lenght = fragment.findViewById(R.id.linerLayout_lenght);
        linerLayout_quantity = fragment.findViewById(R.id.linerLayout_quantity);
        linerLayout_frequency = fragment.findViewById(R.id.linerLayout_frequency);
        linerLayout_duration = fragment.findViewById(R.id.linerLayout_duration);
        linerLayout_size = fragment.findViewById(R.id.linerLayout_size);
        linerLayout_amount = fragment.findViewById(R.id.linerLayout_amount);

        imageButton_interval_minus = fragment.findViewById(R.id.imageButton_interval_minus);
        imageButton_lenght_minus = fragment.findViewById(R.id.imageButton_lenght_minus);
        imageButton_quantity_minus = fragment.findViewById(R.id.imageButton_quantity_minus);
        imageButton_frequency_minus = fragment.findViewById(R.id.imageButton_frequency_minus);
        imageButton_duration_minus = fragment.findViewById(R.id.imageButton_duration_minus);
        imageButton_size_minus = fragment.findViewById(R.id.imageButton_size_minus);
        imageButton_amount_minus = fragment.findViewById(R.id.imageButton_amount_minus);

        imageButton_interval_plus = fragment.findViewById(R.id.imageButton_interval_plus);
        imageButton_lenght_plus = fragment.findViewById(R.id.imageButton_lenght_plus);
        imageButton_quantity_plus = fragment.findViewById(R.id.imageButton_quantity_plus);
        imageButton_frequency_plus = fragment.findViewById(R.id.imageButton_frequency_plus);
        imageButton_duration_plus = fragment.findViewById(R.id.imageButton_duration_plus);
        imageButton_size_plus = fragment.findViewById(R.id.imageButton_size_plus);
        imageButton_amount_plus = fragment.findViewById(R.id.imageButton_amount_plus);

        checkBox_interval = fragment.findViewById(R.id.checkBox_interval);
        checkBox_lenght = fragment.findViewById(R.id.checkBox_lenght);
        checkBox_quantity = fragment.findViewById(R.id.checkBox_quantity);
        checkBox_frequency = fragment.findViewById(R.id.checkBox_frequency);
        checkBox_duration = fragment.findViewById(R.id.checkBox_duration);
        checkBox_size = fragment.findViewById(R.id.checkBox_size);
        checkBox_amount = fragment.findViewById(R.id.checkBox_amount);

        editText_interval = fragment.findViewById(R.id.editText_interval);
        editText_lenght = fragment.findViewById(R.id.editText_lenght);
        editText_quantity = fragment.findViewById(R.id.editText_quantity);
        editText_frequency = fragment.findViewById(R.id.editText_frequency);
        editText_duration = fragment.findViewById(R.id.editText_duration);
        editText_size = fragment.findViewById(R.id.editText_size);
        editText_amount = fragment.findViewById(R.id.editText_amount);

        textView_size = fragment.findViewById(R.id.textView_size);


        checkBox_interval.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_lenght.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_quantity.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_frequency.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_duration.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_size.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});
        checkBox_amount.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {Сompletion();}});

        checkBox_interval.setChecked(true);
        checkBox_lenght.setChecked(true);
        checkBox_frequency.setChecked(true);
        checkBox_size.setChecked(true);

        imageButton_interval_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_interval,-1);}});
        imageButton_lenght_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_lenght,-1);}});
        imageButton_quantity_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_quantity,-1);}});
        imageButton_frequency_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_frequency,-1);}});
        imageButton_duration_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_duration,-1);}});
        imageButton_size_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_size,-1);}});
        imageButton_amount_minus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_amount,-1);}});

        imageButton_interval_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_interval,1);}});
        imageButton_lenght_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_lenght,1);}});
        imageButton_quantity_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_quantity,1);}});
        imageButton_frequency_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_frequency,1);}});
        imageButton_duration_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_duration,1);}});
        imageButton_size_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_size,1);}});
        imageButton_amount_plus.setOnClickListener(new View.OnClickListener() {public void onClick(View v) {ButtonNumber(editText_amount,1);}});


        editText_interval.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_lenght.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_frequency.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_duration.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_size.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});
        editText_amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {public void onFocusChange(View v, boolean hasFocus) {Calculation();}});







        return fragment;
    }

    public void Calculation(){
        if(editText_interval.length()==0){editText_interval.setText("0");}
        if(editText_lenght.length()==0){editText_lenght.setText("0");}
        if(editText_quantity.length()==0){editText_quantity.setText("0");}
        if(editText_frequency.length()==0){editText_frequency.setText("0");}
        if(editText_duration.length()==0){editText_duration.setText("0");}
        if(editText_size.length()==0){editText_size.setText("0");}
        if(editText_amount.length()==0){editText_amount.setText("0");}



        if(Сompletion()){
            if (checkBox_interval.isChecked() && checkBox_lenght.isChecked() && //1
                    checkBox_frequency.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,3);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_interval.isChecked() && checkBox_quantity.isChecked() && //2
                    checkBox_frequency.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_lenght.isChecked() && checkBox_quantity.isChecked() && //3
                    checkBox_frequency.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,1);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_interval.isChecked() && checkBox_lenght.isChecked() && //4
                    checkBox_frequency.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,3);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_interval.isChecked() && checkBox_quantity.isChecked() && //5
                    checkBox_frequency.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_lenght.isChecked() && checkBox_quantity.isChecked() && //6
                    checkBox_frequency.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,1);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,3);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_interval.isChecked() && checkBox_lenght.isChecked() && //7
                    checkBox_duration.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,3);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_interval.isChecked() && checkBox_quantity.isChecked() && //8
                    checkBox_duration.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_lenght.isChecked() && checkBox_quantity.isChecked() && //9
                    checkBox_duration.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,1);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_interval.isChecked() && checkBox_lenght.isChecked() && //10
                    checkBox_duration.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,3);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_interval.isChecked() && checkBox_quantity.isChecked() && //11
                    checkBox_duration.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_lenght.isChecked() && checkBox_quantity.isChecked() && //12
                    checkBox_duration.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,1);
                MathCalculationTwo(editText_quantity,editText_frequency,editText_duration,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }



            else if (checkBox_interval.isChecked() && checkBox_frequency.isChecked() && //13
                    checkBox_duration.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_quantity, editText_frequency,editText_duration,4);
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_lenght.isChecked() && checkBox_frequency.isChecked() && //14
                    checkBox_duration.isChecked() && checkBox_size.isChecked()){
                MathCalculationOne(editText_quantity, editText_frequency,editText_duration,4);
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,5);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,3);
            }else if (checkBox_interval.isChecked() && checkBox_frequency.isChecked() && //15
                    checkBox_duration.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_quantity, editText_frequency,editText_duration,4);
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,2);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }else if (checkBox_lenght.isChecked() && checkBox_frequency.isChecked() && //16
                    checkBox_duration.isChecked() && checkBox_amount.isChecked()){
                MathCalculationOne(editText_quantity, editText_frequency,editText_duration,4);
                MathCalculationOne(editText_interval, editText_lenght,editText_quantity,5);
                MathCalculationThree(editText_quantity,editText_size,editText_amount,2);
            }




        }

        textView_size.setText(getString(R.string.total_size)+": "+editText_amount.getText());

    }


    private void MathCalculationOne(EditText one, EditText two, EditText three, int formula_id){
        switch (formula_id){
            case 1:{
                one.setText(""+(Float.valueOf(three.getText()+"")/60/Float.valueOf(two.getText()+"")));
                break;
            }
            case 2:{
                two.setText(""+(Float.valueOf(three.getText()+"")*Float.valueOf(one.getText()+"")/60));
                break;
            }
            case 3:{
                three.setText(""+(Float.valueOf(two.getText()+"")*60/Float.valueOf(one.getText()+"")));
                break;
            }
            case 4:{
                one.setText(""+(Float.valueOf(three.getText()+"")*Float.valueOf(two.getText()+"")));
                break;
            }
            case 5:{
                one.setText(""+(Float.valueOf(two.getText()+"")*60/Float.valueOf(three.getText()+"")));
                break;
            }

            default:break;
        }

    }

    private void MathCalculationTwo(EditText one, EditText two, EditText three, int formula_id){
        switch (formula_id){
            case 2:{
                Log.v("one","true");
                two.setText(""+(Float.valueOf(one.getText()+"")/Float.valueOf(three.getText()+"")));
                break;
            }
            case 3:{
                Log.v("two","true");
                three.setText(""+(Float.valueOf(one.getText()+"")/Float.valueOf(two.getText()+"")));
                break;
            }

            default:break;
        }
    }

    private void MathCalculationThree(EditText one, EditText two, EditText three, int formula_id){
        switch (formula_id){
            case 2:{
                two.setText(""+(Float.valueOf(three.getText()+"")/Float.valueOf(one.getText()+"")));
                break;
            }
            case 3:{
                three.setText(""+(Float.valueOf(one.getText()+"")*Float.valueOf(two.getText()+"")));
                break;
            }

            default:break;
        }
    }


    public void ButtonNumber(EditText editText,int number){
        if(editText.length() == 0 || (Float.valueOf(editText.getText()+"")) <= 0){
            editText.setText("0");
            if(number>0){

                editText.setText("1.0");
            }
        }else{
            if(Float.valueOf(editText.getText() + "") + number >= 0) {
                editText.setText((Float.valueOf(editText.getText() + "") + number) + "");
            }else{
                editText.setText("0");
            }
        }
        Calculation();
    }

    public boolean Сompletion(){
        boolean completion = true;
        if(checkBox_amount.isChecked()==false && checkBox_size.isChecked()==false ){
            completion = false;
            linerLayout_amount.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_size.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
        }else if(checkBox_amount.isChecked()==true && checkBox_size.isChecked()==true ){
            completion = false;
            linerLayout_amount.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_size.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
        } else {
            linerLayout_amount.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
            linerLayout_size.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
        }

        if(checkBox_duration.isChecked()==false && checkBox_frequency.isChecked()==false ){
            completion = false;
            linerLayout_duration.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_frequency.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
        }else if(checkBox_duration.isChecked()==true && checkBox_frequency.isChecked()==true &&
                ((checkBox_interval.isChecked()==false && checkBox_quantity.isChecked()==true && checkBox_lenght.isChecked()==true) ||
                        (checkBox_interval.isChecked()==true && checkBox_quantity.isChecked()==false && checkBox_lenght.isChecked()==true) ||
                        (checkBox_interval.isChecked()==true && checkBox_quantity.isChecked()==true && checkBox_lenght.isChecked()==false))
        ){
            completion = false;
            linerLayout_duration.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_frequency.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
        } else {
            linerLayout_duration.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
            linerLayout_frequency.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
        }


        if((checkBox_interval.isChecked()==false && checkBox_quantity.isChecked()==true && checkBox_lenght.isChecked()==true) ||
                (checkBox_interval.isChecked()==true && checkBox_quantity.isChecked()==false && checkBox_lenght.isChecked()==true) ||
                (checkBox_interval.isChecked()==true && checkBox_quantity.isChecked()==true && checkBox_lenght.isChecked()==false) ||
                (checkBox_interval.isChecked()==true && checkBox_quantity.isChecked()==false && checkBox_lenght.isChecked()==false && checkBox_duration.isChecked()==true && checkBox_frequency.isChecked()==true ) ||
                (checkBox_interval.isChecked()==false && checkBox_quantity.isChecked()==false && checkBox_lenght.isChecked()==true && checkBox_duration.isChecked()==true && checkBox_frequency.isChecked()==true )){
            linerLayout_interval.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
            linerLayout_quantity.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
            linerLayout_lenght.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundloyout));
        } else {
            completion = false;
            linerLayout_interval.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_quantity.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
            linerLayout_lenght.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.error));
        }





        return completion;
    }

}
