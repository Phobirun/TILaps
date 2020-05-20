package com.suwax.tilaps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Fragment_foto extends Fragment implements TextureView.SurfaceTextureListener {

    private static final String TAG = MainActivity.class.getName();

    private static final String FILE_NAME = "Animation7529.mp4"; // ваш файл
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "format";
    private SharedPreferences mSettings;



    private MediaPlayer mMediaPlayer;
    private LinearLayout mTextureView;
    private View fragment;
    boolean fragmentLoaded = false;

    //int_button
    private int int_shot = 0;
    private int int_shot_count = 0;
    private int int_shot_secussfull = 0;
    private int int_flash = 0;
    private int int_grid = 0;
    private int int_timer = 0;
    private int int_level = 0;
    private int int_setting = 0;
    private int int_number_camera = 0;
    private boolean bool_lock_iso = false;
    private boolean bool_lock_wb = false;
    private boolean bool_lock_s = false;
    private boolean bool_lock_mf = false;
    private int int_super = 0;
    private int int_multi = 0;
    private int int_hdr = 0;
    Timer mTimer;
    MyTimerTask mMyTimerTask;
    int timerDelay = 0;

    //components
    private ImageButton imageButton_shot;
    private ImageButton imageButton_flash;
    private ImageButton imageButton_grid;
    private ImageButton imageButton_timer;
    private ImageButton imageButton_level;
    private ImageButton imageButton_setting;
    private ImageButton imageButton_number_camera;
    private ImageButton imageButton_lock_iso;
    private ImageButton imageButton_lock_mf;
    private ImageButton imageButton_lock_wb;
    private ImageButton imageButton_lock_s;

    private LinearLayout linearLayout_top_element;
    private LinearLayout linearLayout_top_mode;
    private LinearLayout linearLayout_down_mode;
    private LinearLayout linearLayout_lock;
    private LinearLayout linearLayout_text;


    private Button button_super;
    private Button button_multi;
    private Button button_hdr;

    private RadioButton radioButton_iso;
    private RadioButton radioButton_s;
    private RadioButton radioButton_ev;
    private RadioButton radioButton_mf;
    private RadioButton radioButton_wb;
    private RadioGroup radioGroup;

    private TextView textView_center;
    private TextView textView_left;
    private TextView textView_right;
    private TextView textView_down_left;
    private TextView textView_down_right;

    private SeekBar seekBar_iso;
    private SeekBar seekBar_s;
    private SeekBar seekBar_ev;
    private SeekBar seekBar_mf;
    private SeekBar seekBar_wb;

    //seekbar_component
    private int seekbar_iso_max = 10;
    private int seekbar_iso_select = 0;
    private ArrayList<String> seekbar_iso_value = new ArrayList<>();

    private int seekbar_s_max = 10;
    private int seekbar_s_select = 0;
    private ArrayList<String> seekbar_s_value = new ArrayList<>();

    private int seekbar_ev_max = 10;
    private int seekbar_ev_select = 0;
    private ArrayList<String> seekbar_ev_value = new ArrayList<>();

    private int seekbar_mf_max = 10;
    private int seekbar_mf_select = 0;
    private ArrayList<String> seekbar_mf_value = new ArrayList<>();

    private int seekbar_wb_max = 10;
    private int seekbar_wb_select = 0;
    private ArrayList<String> seekbar_wb_value = new ArrayList<>();

    //private CameraServiceAdapter cameraService = new CameraServiceAdapter();
    Camera2RawFragment camera;

    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler = null;



    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GetParametrsForCamera();

        fragment = inflater.inflate(R.layout.fragment_foto,container,false);

        imageButton_shot = fragment.findViewById(R.id.imageButton_shot);
        imageButton_shot.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickShot(); } });

        imageButton_flash = fragment.findViewById(R.id.imageButton_flash);
        imageButton_flash.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickFlash(); } });

        imageButton_grid = fragment.findViewById(R.id.imageButton_grid);
        imageButton_grid.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickGrid(); } });

        imageButton_timer = fragment.findViewById(R.id.imageButton_timer);
        imageButton_timer.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickTimer(); } });

        imageButton_level = fragment.findViewById(R.id.imageButton_level);
        imageButton_level.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickLevel(); } });

        imageButton_setting = fragment.findViewById(R.id.imageButton_setting);
        imageButton_setting.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickSetting(); } });

        imageButton_number_camera = fragment.findViewById(R.id.imageButton_number_camera);
        imageButton_number_camera.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickNumberCamera(); } });

        imageButton_lock_iso = fragment.findViewById(R.id.imageButton_lock_iso);
        imageButton_lock_iso.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickLockIso(); } });

        imageButton_lock_mf = fragment.findViewById(R.id.imageButton_lock_mf);
        imageButton_lock_mf.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickLockMf(); } });

        imageButton_lock_wb = fragment.findViewById(R.id.imageButton_lock_wb);
        imageButton_lock_wb.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickLockWb(); } });

        imageButton_lock_s = fragment.findViewById(R.id.imageButton_lock_s);
        imageButton_lock_s.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onImageButtonClickLockS(); } });

        linearLayout_top_element = fragment.findViewById(R.id.linearLayout_top_element);
        linearLayout_top_mode = fragment.findViewById(R.id.linearLayout_top_mode);
        linearLayout_down_mode = fragment.findViewById(R.id.linearLayout_down_mode);
        linearLayout_lock = fragment.findViewById(R.id.linearLayout_lock);
        linearLayout_text = fragment.findViewById(R.id.linearLayout_text);


        button_super = fragment.findViewById(R.id.button_super);
        button_super.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onButtonClickSuper(); } });
        //button_super.setEnabled(false);//not implemented

        button_multi = fragment.findViewById(R.id.button_multi);
        button_multi.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onButtonClickMulti(); } });
        //button_multi.setEnabled(false);//not implemented

        button_hdr = fragment.findViewById(R.id.button_hdr);
        button_hdr.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onButtonClickHdr(); } });
        //button_hdr.setEnabled(false);//not implemented

        radioButton_iso = fragment.findViewById(R.id.radioButton_iso);
        radioButton_s = fragment.findViewById(R.id.radioButton_s);
        radioButton_ev = fragment.findViewById(R.id.radioButton_ev);
        radioButton_mf = fragment.findViewById(R.id.radioButton_mf);
        radioButton_wb = fragment.findViewById(R.id.radioButton_wb);
        radioGroup = fragment.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onClickRadioButton();
            }
        });



        textView_center = fragment.findViewById(R.id.textView_center);
        textView_left = fragment.findViewById(R.id.textView_left);
        textView_right = fragment.findViewById(R.id.textView_right);
        textView_down_left = fragment.findViewById(R.id.textView_down_left);
        textView_down_right = fragment.findViewById(R.id.textView_down_right);

        textView_left.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            if(radioButton_iso.isChecked()){
                onClickTextViewSeekBar(-1, seekBar_iso);
            }else if(radioButton_s.isChecked()){
                onClickTextViewSeekBar(-1, seekBar_s);
            }else if(radioButton_ev.isChecked()){
                onClickTextViewSeekBar(-1, seekBar_ev);
            }else if(radioButton_mf.isChecked()){
                onClickTextViewSeekBar(-1, seekBar_mf);
            }else if(radioButton_wb.isChecked()){
                onClickTextViewSeekBar(-1, seekBar_wb);
            }
        } });
        textView_right.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            if(radioButton_iso.isChecked()){
                onClickTextViewSeekBar(1, seekBar_iso);
            }else if(radioButton_s.isChecked()){
                onClickTextViewSeekBar(1, seekBar_s);
            }else if(radioButton_ev.isChecked()){
                onClickTextViewSeekBar(1, seekBar_ev);
            }else if(radioButton_mf.isChecked()){
                onClickTextViewSeekBar(1, seekBar_mf);
            }else if(radioButton_wb.isChecked()){
                onClickTextViewSeekBar(1, seekBar_wb);
            }


        } });

        seekBar_iso = fragment.findViewById(R.id.seekBar);
        seekBar_iso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarProgressChanged();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar_s = fragment.findViewById(R.id.seekBar2);
        seekBar_s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarProgressChanged();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar_ev = fragment.findViewById(R.id.seekBar3);
        seekBar_ev.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarProgressChanged();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar_mf = fragment.findViewById(R.id.seekBar4);
        seekBar_mf.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarProgressChanged();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seekBar_wb = fragment.findViewById(R.id.seekBar5);
        seekBar_wb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onSeekBarProgressChanged();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        radioButton_iso.setChecked(true);

        mTextureView = (LinearLayout) fragment.findViewById(R.id.textureViewContainer);
        camera=new Camera2RawFragment();

        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.textureViewContainer, camera).commit();

        Log.v("qwert",mTextureView.getLayoutDirection()+"");

        //mTextureView.set("18:8");


       // cameraService.startBackgroundThread();
        //cameraService.onCreate(getActivity(), mTextureView);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        fragmentLoaded=true;
        try {
            setFormatCameraSave(mSettings.getInt(APP_PREFERENCES_COUNTER, 0));
        }
        catch (Exception e){
            setFormatCameraSave(0);
        }
        return fragment;

    }
    public void setFormatCameraSave(int i){
        if(i==0){
            camera.setBoolRaw(false);
            camera.setBoolJpg(true);
        }else if(i==1){
            camera.setBoolRaw(true);
            camera.setBoolJpg(false);
        }else{
            camera.setBoolRaw(true);
            camera.setBoolJpg(true);
        }
    }


    public void onImageButtonClickShot(){
        int_shot_count = ((MainActivity)getActivity()).getShot();
        if(int_shot_count==0){
            int_shot_count = 1;
        }
        int_shot_secussfull = 0;

        if(int_shot == 0){
            imageButton_shot.setImageResource(R.drawable.ic_shot_play_svg);
            int_shot = 1;

            linearLayout_top_element.setVisibility(View.INVISIBLE);
            linearLayout_top_mode.setVisibility(View.INVISIBLE);
            linearLayout_down_mode.setVisibility(View.INVISIBLE);
            linearLayout_lock.setVisibility(View.INVISIBLE);
            linearLayout_text.setVisibility(View.VISIBLE);
            imageButton_number_camera.setVisibility(View.INVISIBLE);

            textView_down_left.setText("iso: "+seekbar_iso_value.get(seekbar_iso_select)+ "\ns: "+seekbar_s_value.get(seekbar_s_select)+ "\nmf: "+seekbar_mf_value.get(seekbar_mf_select)+ "\nwb: "+seekbar_wb_value.get(seekbar_wb_select));
            textView_down_right.setText("сделанно: \n"+int_shot_secussfull+"\nосталось: \n"+(int_shot_count-int_shot_secussfull));




            if (mTimer != null) {
                mTimer.cancel();
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    /*code*/mTimer = new Timer();
                    mMyTimerTask = new MyTimerTask();
                    mTimer.schedule(mMyTimerTask, 000, (((MainActivity)getActivity()).getShotTime() * 1000));
                }
            }, timerDelay); //specify the number of milliseconds



        }else{
            if (mTimer != null) {
                mTimer.cancel();
            }
            imageButton_shot.setImageResource(R.drawable.ic_shot_stop_svg);
            int_shot = 0;

            linearLayout_top_element.setVisibility(View.VISIBLE);
            linearLayout_top_mode.setVisibility(View.VISIBLE);
            linearLayout_down_mode.setVisibility(View.VISIBLE);
            linearLayout_lock.setVisibility(View.VISIBLE);
            linearLayout_text.setVisibility(View.INVISIBLE);
            imageButton_number_camera.setVisibility(View.VISIBLE);


        }
    }

    public void onImageButtonClickFlash()
    {
        if(int_flash == 0){
            imageButton_flash.setImageResource(R.drawable.ic_flash_enable_svg);
            camera.setFlash(1);
            int_flash = 1;
        }else if(int_flash == 1){
            imageButton_flash.setImageResource(R.drawable.ic_flash_full_svg);
            camera.setFlash(2);
            int_flash = 2;
        }else{
            imageButton_flash.setImageResource(R.drawable.ic_flash_disable_svg);
            camera.setFlash(0);
            int_flash = 0;
        }
    }

    public void onImageButtonClickGrid()
    {
        if(int_grid == 0){
            imageButton_grid.setImageResource(R.drawable.ic_grid_true_svg);
            int_grid = 1;
            camera.setGridLine(1);
        }else{
            imageButton_grid.setImageResource(R.drawable.ic_grid_false_svg);
            int_grid = 0;
            camera.setGridLine(0);
        }
    }


    public void onImageButtonClickTimer()
    {
        if(int_timer == 0){
            imageButton_timer.setImageResource(R.drawable.ic_timer_1_svg);
            timerDelay = 1000;
            int_timer = 1;
        }else if(int_timer == 1){
            imageButton_timer.setImageResource(R.drawable.ic_timer_3_svg);
            timerDelay = 3000;
            int_timer = 2;
        }else if(int_timer == 2){
            imageButton_timer.setImageResource(R.drawable.ic_timer_5_svg);
            timerDelay = 5000;
            int_timer = 3;
        }else if(int_timer == 3){
            imageButton_timer.setImageResource(R.drawable.ic_timer_10_svg);
            timerDelay = 10000;
            int_timer = 4;
        }else{
            imageButton_timer.setImageResource(R.drawable.ic_timer_0_svg);
            timerDelay = 0;
            int_timer = 0;
        }
    }

    public void onImageButtonClickLevel()
    {
        Toast.makeText(getContext(),R.string.no_support,Toast.LENGTH_LONG).show();
        /*
        if(int_level == 0){
            imageButton_level.setImageResource(R.drawable.ic_level_true_svg);
            int_level = 1;
        }else{
            imageButton_level.setImageResource(R.drawable.ic_level_false_svg);
            int_level = 0;
        }

         */
    }

    public void onImageButtonClickSetting()
    {
        if(int_setting == 0){
            imageButton_setting.setImageResource(R.drawable.ic_setting_true_svg);
            int_setting = 1;
            ChangingFragment(new Fragment_foto_setting());
        }else{
            imageButton_setting.setImageResource(R.drawable.ic_setting_false_svg);
            int_setting = 0;
        }
    }

    public void onImageButtonClickNumberCamera()
    {
        Toast.makeText(getActivity(),R.string.no_support,Toast.LENGTH_LONG).show();
        /*
        if(int_number_camera == 0){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_2_svg);
            int_number_camera = 1;
        }else if(int_number_camera == 1){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_3_svg);
            int_number_camera = 2;
        }else if(int_number_camera == 2){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_4_svg);
            int_number_camera = 3;
        }else if(int_number_camera == 3){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_5_svg);
            int_number_camera = 4;
        }else if(int_number_camera == 4){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_6_svg);
            int_number_camera = 5;
        }else if(int_number_camera == 5){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_7_svg);
            int_number_camera = 6;
        }else if(int_number_camera == 6){
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_8_svg);
            int_number_camera = 7;
        }else {
            imageButton_number_camera.setImageResource(R.drawable.ic_camera_icon_1_svg);
            int_number_camera = 0;
        }

         */
       // cameraService.ChangingCamera(int_number_camera);
    }

    public void onImageButtonClickLockIso()
    {
        if(!bool_lock_iso){
            imageButton_lock_iso.setImageResource(R.drawable.ic_lock_closed_iso_svg);
            bool_lock_iso = true;
        }else{
            seekbar_iso_select = 0;
            seekBar_iso.setProgress(seekbar_iso_select );
            imageButton_lock_iso.setImageResource(R.drawable.ic_lock_opened_iso_svg);
            bool_lock_iso = false;
        }
        setCameraAuto();
    }

    public void onImageButtonClickLockWb()
    {
        if(!bool_lock_wb){
            imageButton_lock_wb.setImageResource(R.drawable.ic_lock_closed_wb_svg);
            bool_lock_wb = true;
        }else{

            seekbar_wb_select = 0;
            seekBar_wb.setProgress(seekbar_wb_select );
            imageButton_lock_wb.setImageResource(R.drawable.ic_lock_opened_wb_svg);
            bool_lock_wb = false;
        }
        setCameraAuto();
    }

    public void onImageButtonClickLockMf()
    {
        if(!bool_lock_mf){
            imageButton_lock_mf.setImageResource(R.drawable.ic_lock_closed_mf_svg);
            bool_lock_mf = true;
        }else{

            seekbar_mf_select = 0;
            seekBar_mf.setProgress(seekbar_mf_select );
            imageButton_lock_mf.setImageResource(R.drawable.ic_lock_opened_mf_svg);
            bool_lock_mf =false;
        }
        setCameraAuto();
    }

    public void onImageButtonClickLockS()
    {
        if(!bool_lock_s){
            imageButton_lock_s.setImageResource(R.drawable.ic_lock_closed_s_svg);
            bool_lock_s = true;
        }else{

            seekbar_s_select = 0;
            seekBar_s.setProgress(seekbar_s_select );
            imageButton_lock_s.setImageResource(R.drawable.ic_lock_opened_s_svg);
            bool_lock_s = false;
        }
        Log.v("lkjhjk",""+bool_lock_s);
        setCameraAuto();
    }


    public void setCameraAuto(){


        int iso = 0;
        long exposureTime =0;

        int whiteBalance = 1;

        boolean manualFocus = false;
        double focusDistant = 0;
        int exposure = 0;

        if(bool_lock_iso){iso= getIsoValue(seekbar_iso_value.get(seekbar_iso_select),false) ;}else{iso = 0;}
        if(bool_lock_mf){manualFocus = true; focusDistant = getFocusValue(seekbar_mf_value.get(seekbar_mf_select),false);}else{manualFocus = false; focusDistant = 0;}
        if(bool_lock_s){exposureTime= getExposureTimeValue(seekbar_s_value.get(seekbar_s_select),false);}else{exposureTime= 0;}
        if(bool_lock_wb){whiteBalance= getWhiteBalanceValue(seekbar_wb_value.get(seekbar_wb_select),false);}else{whiteBalance = 1;}
        if(bool_lock_iso  && bool_lock_s ){exposure = 0;}else{exposure= getExposureValue(seekbar_ev_value.get(seekbar_ev_select),false);}


        camera.setLock(iso,exposureTime,whiteBalance,manualFocus,focusDistant,exposure);


    }

    public void onButtonClickSuper()
    {
        Toast.makeText(getActivity(),R.string.no_support,Toast.LENGTH_LONG).show();
        /*
        if(int_super == 0){
            button_super.setTextColor(getResources().getColor(R.color.colorAccent));
            int_super = 1;
        }else{
            button_super.setTextColor(getResources().getColor(R.color.text));
            int_super = 0;
        }

         */
    }

    public void onButtonClickMulti()
    {
        Toast.makeText(getActivity(),R.string.no_support,Toast.LENGTH_LONG).show();
        /*
        if(int_multi == 0){
            button_multi.setTextColor(getResources().getColor(R.color.colorAccent));
            int_multi = 1;
        }else{
            button_multi.setTextColor(getResources().getColor(R.color.text));
            int_multi = 0;
        }

         */
    }

    public void onButtonClickHdr()
    {
        Toast.makeText(getActivity(),R.string.no_support,Toast.LENGTH_LONG).show();
        /*
        if(int_hdr == 0){
            button_hdr.setTextColor(getResources().getColor(R.color.colorAccent));
            int_hdr = 1;
        }else{
            button_hdr.setTextColor(getResources().getColor(R.color.text));
            int_hdr = 0;
        }

         */
    }

    public void onClickTextViewSeekBar(int step, SeekBar seekBars){
        Log.v("text","error");
        if(seekBars.getProgress() == seekBars.getMax()){
            if(step<0){
                seekBars.setProgress(seekBars.getProgress()+step);
            }
        } else if (seekBars.getProgress() == 0){
            if(step>0){
                seekBars.setProgress(seekBars.getProgress()+step);
            }
        } else{
            seekBars.setProgress(seekBars.getProgress()+step);
        }
    }





    public void onSeekBarProgressChanged(){
        if(radioButton_iso.isChecked()){
            seekbar_iso_select = seekBar_iso.getProgress();
            textView_center.setText(String.valueOf("ISO: "+ (seekbar_iso_value.get(seekbar_iso_select))));
            if(fragmentLoaded) {
                camera.setIso(getIsoValue(seekbar_iso_value.get(seekbar_iso_select),true));
            }
        }else if(radioButton_s.isChecked()){
            seekbar_s_select = seekBar_s.getProgress();
            textView_center.setText(String.valueOf("s: "+ (seekbar_s_value.get(seekbar_s_select))));
            if(fragmentLoaded) {
                camera.setExposureTime(getExposureTimeValue(seekbar_s_value.get(seekbar_s_select),true));
            }

        }else if(radioButton_ev.isChecked()){
            seekbar_ev_select = seekBar_ev.getProgress();
            textView_center.setText(String.valueOf("ev: "+ (seekbar_ev_value.get(seekbar_ev_select))));
            if(fragmentLoaded) {
                camera.setExposureCompensation(getExposureValue(seekbar_ev_value.get(seekbar_ev_select),true));
            }

        }else if(radioButton_mf.isChecked()){
            seekbar_mf_select = seekBar_mf.getProgress();
            textView_center.setText(String.valueOf("mf: "+ (seekbar_mf_value.get(seekbar_mf_select))));


            if(fragmentLoaded) {
                if (getFocusValue(seekbar_mf_value.get(seekbar_mf_select),true) >= 0) {
                    camera.setManualFocus(true, getFocusValue(seekbar_mf_value.get(seekbar_mf_select),true));
                }else {
                    camera.setManualFocus(false, getFocusValue(seekbar_mf_value.get(seekbar_mf_select),true));
                }
            }


        }else if(radioButton_wb.isChecked()){
            seekbar_wb_select = seekBar_wb.getProgress();
            textView_center.setText(String.valueOf("wb: "+ (seekbar_wb_value.get(seekbar_wb_select))));
            if(fragmentLoaded) {
                camera.setWhiteBalance(getWhiteBalanceValue(seekbar_wb_value.get(seekbar_wb_select),true));
            }
        }
    }

    private void GetParametrsForCamera(){
        seekbar_iso_value.add("AUTO");
        seekbar_iso_value.add("50");
        seekbar_iso_value.add("100");
        seekbar_iso_value.add("200");
        seekbar_iso_value.add("300");
        seekbar_iso_value.add("400");
        seekbar_iso_value.add("500");
        seekbar_iso_value.add("640");
        seekbar_iso_value.add("800");
        seekbar_iso_value.add("1000");
        seekbar_iso_value.add("1200");
        seekbar_iso_value.add("1400");
        seekbar_iso_value.add("1600");
        seekbar_iso_value.add("1800");
        seekbar_iso_value.add("2000");
        seekbar_iso_value.add("2500");
        seekbar_iso_value.add("3200");
        seekbar_iso_value.add("6400");
        seekbar_iso_max = seekbar_iso_value.size()-1;

        seekbar_s_value.add("AUTO");
        seekbar_s_value.add("1/4000");
        seekbar_s_value.add("1/3000");
        seekbar_s_value.add("1/2000");
        seekbar_s_value.add("1/1500");
        seekbar_s_value.add("1/1200");
        seekbar_s_value.add("1/1000");
        seekbar_s_value.add("1/900");
        seekbar_s_value.add("1/800");
        seekbar_s_value.add("1/600");
        seekbar_s_value.add("1/500");
        seekbar_s_value.add("1/300");
        seekbar_s_value.add("1/250");
        seekbar_s_value.add("1/200");
        seekbar_s_value.add("1/150");
        seekbar_s_value.add("1/125");
        seekbar_s_value.add("1/100");
        seekbar_s_value.add("1/80");
        seekbar_s_value.add("1/60");
        seekbar_s_value.add("1/50");
        seekbar_s_value.add("1/30");
        seekbar_s_value.add("1/25");
        seekbar_s_value.add("1/15");
        seekbar_s_value.add("1/13");
        seekbar_s_value.add("1/10");
        seekbar_s_value.add("1/8");
        seekbar_s_value.add("1/6");
        seekbar_s_value.add("1/5");
        seekbar_s_value.add("1/4");
        seekbar_s_value.add("1/3");
        seekbar_s_value.add("1/2");
        seekbar_s_value.add("1.0");
        seekbar_s_value.add("1.2");
        seekbar_s_value.add("1.3");
        seekbar_s_value.add("1.5");
        seekbar_s_value.add("1.8");
        seekbar_s_value.add("2.0");
        seekbar_s_value.add("2.3");
        seekbar_s_value.add("2.5");
        seekbar_s_value.add("3.0");
        seekbar_s_value.add("3.2");
        seekbar_s_value.add("3.3");
        seekbar_s_value.add("3.5");
        seekbar_s_value.add("3.8");
        seekbar_s_value.add("4.0");

        seekbar_s_max = seekbar_s_value.size()-1;

        seekbar_ev_value.add("AUTO");
        seekbar_ev_value.add("-6");
        seekbar_ev_value.add("-5");
        seekbar_ev_value.add("-4");
        seekbar_ev_value.add("-3");
        seekbar_ev_value.add("-2");
        seekbar_ev_value.add("-1");
        seekbar_ev_value.add("0");
        seekbar_ev_value.add("1");
        seekbar_ev_value.add("2");
        seekbar_ev_value.add("3");
        seekbar_ev_value.add("4");
        seekbar_ev_value.add("5");
        seekbar_ev_value.add("6");
        seekbar_ev_max = seekbar_ev_value.size()-1;


        seekbar_mf_value.add("AUTO");
        seekbar_mf_value.add("0.0");
        seekbar_mf_value.add("0.1");
        seekbar_mf_value.add("0.2");
        seekbar_mf_value.add("0.3");
        seekbar_mf_value.add("0.4");
        seekbar_mf_value.add("0.5");
        seekbar_mf_value.add("0.6");
        seekbar_mf_value.add("0.7");
        seekbar_mf_value.add("0.8");
        seekbar_mf_value.add("0.9");

        seekbar_mf_value.add("1.0");
        seekbar_mf_value.add("1.1");
        seekbar_mf_value.add("1.2");
        seekbar_mf_value.add("1.3");
        seekbar_mf_value.add("1.4");
        seekbar_mf_value.add("1.5");
        seekbar_mf_value.add("1.6");
        seekbar_mf_value.add("1.7");
        seekbar_mf_value.add("1.8");
        seekbar_mf_value.add("1.9");

        seekbar_mf_value.add("2.0");
        seekbar_mf_value.add("2.1");
        seekbar_mf_value.add("2.2");
        seekbar_mf_value.add("2.3");
        seekbar_mf_value.add("2.4");
        seekbar_mf_value.add("2.5");
        seekbar_mf_value.add("2.6");
        seekbar_mf_value.add("2.7");
        seekbar_mf_value.add("2.8");
        seekbar_mf_value.add("2.9");

        seekbar_mf_value.add("3.0");
        seekbar_mf_value.add("3.1");
        seekbar_mf_value.add("3.2");
        seekbar_mf_value.add("3.3");
        seekbar_mf_value.add("3.4");
        seekbar_mf_value.add("3.5");
        seekbar_mf_value.add("3.6");
        seekbar_mf_value.add("3.7");
        seekbar_mf_value.add("3.8");
        seekbar_mf_value.add("3.9");

        seekbar_mf_value.add("4.0");
        seekbar_mf_value.add("4.1");
        seekbar_mf_value.add("4.2");
        seekbar_mf_value.add("4.3");
        seekbar_mf_value.add("4.4");
        seekbar_mf_value.add("4.5");
        seekbar_mf_value.add("4.6");
        seekbar_mf_value.add("4.7");
        seekbar_mf_value.add("4.8");
        seekbar_mf_value.add("4.9");

        seekbar_mf_value.add("5.0");
        seekbar_mf_value.add("5.1");
        seekbar_mf_value.add("5.2");
        seekbar_mf_value.add("5.3");
        seekbar_mf_value.add("5.4");
        seekbar_mf_value.add("5.5");
        seekbar_mf_value.add("5.6");
        seekbar_mf_value.add("5.7");
        seekbar_mf_value.add("5.8");
        seekbar_mf_value.add("5.9");

        seekbar_mf_value.add("6.0");
        seekbar_mf_value.add("6.1");
        seekbar_mf_value.add("6.2");
        seekbar_mf_value.add("6.3");
        seekbar_mf_value.add("6.4");
        seekbar_mf_value.add("6.5");
        seekbar_mf_value.add("6.6");
        seekbar_mf_value.add("6.7");
        seekbar_mf_value.add("6.8");
        seekbar_mf_value.add("6.9");

        seekbar_mf_value.add("7.0");
        seekbar_mf_value.add("7.1");
        seekbar_mf_value.add("7.2");
        seekbar_mf_value.add("7.3");
        seekbar_mf_value.add("7.4");
        seekbar_mf_value.add("7.5");
        seekbar_mf_value.add("7.6");
        seekbar_mf_value.add("7.7");
        seekbar_mf_value.add("7.8");
        seekbar_mf_value.add("7.9");

        seekbar_mf_value.add("8.0");
        seekbar_mf_value.add("8.1");
        seekbar_mf_value.add("8.2");
        seekbar_mf_value.add("8.3");
        seekbar_mf_value.add("8.4");
        seekbar_mf_value.add("8.5");
        seekbar_mf_value.add("8.6");
        seekbar_mf_value.add("8.7");
        seekbar_mf_value.add("8.8");
        seekbar_mf_value.add("8.9");

        seekbar_mf_value.add("9.0");
        seekbar_mf_value.add("9.1");
        seekbar_mf_value.add("9.2");
        seekbar_mf_value.add("9.3");
        seekbar_mf_value.add("9.4");
        seekbar_mf_value.add("9.5");
        seekbar_mf_value.add("9.6");
        seekbar_mf_value.add("9.7");
        seekbar_mf_value.add("9.8");
        seekbar_mf_value.add("9.9");
        seekbar_mf_value.add("10.0");

        seekbar_mf_max = seekbar_mf_value.size()-1;

        seekbar_wb_value.add("AUTO");
        seekbar_wb_value.add("1");
        seekbar_wb_value.add("2");
        seekbar_wb_value.add("3");
        seekbar_wb_value.add("4");
        seekbar_wb_value.add("5");
        seekbar_wb_value.add("6");
        seekbar_wb_value.add("7");
        seekbar_wb_value.add("8");
        seekbar_wb_max = seekbar_wb_value.size()-1;








    }


    public int getIsoValue(String str, boolean bool){
        int value = 0;
        if(str.equals("AUTO")){
            value = 0;
            if(bool) {
                bool_lock_iso = true;
                onImageButtonClickLockIso();
            }
        }else {
            value = Integer.valueOf(str);
            if(bool) {
                bool_lock_iso = false;
                onImageButtonClickLockIso();
            }
        }

        return value;
    }
    public int getExposureValue(String str,boolean bool){
        int value = 0;
        if(str.equals("AUTO")){
            value = 0;

        }else {
            value = Integer.valueOf(str);

        }

        return value;
    }

    public int getWhiteBalanceValue(String str,boolean bool){
        int value = 1;
        if(str.equals("AUTO")){
            value = 1;
            if(bool) {
                bool_lock_wb = true;
                onImageButtonClickLockWb();
            }
        }else if (str.equals("1")){
            value = 0;
            if(bool) {
                bool_lock_wb = false;
                onImageButtonClickLockWb();
            }
        }else {
            value = Integer.valueOf(str);
            if(bool) {
                bool_lock_wb = false;
                onImageButtonClickLockWb();
            }
        }

        return value;
    }


    public float getFocusValue(String str, boolean bool){
        float value = 1.0f;
        if(str.equals("AUTO")){
            value = -1.0f;
            if(bool) {
                bool_lock_mf = true;
                onImageButtonClickLockMf();
            }
        }else {
            value = Float.valueOf(str);
            if(bool) {
                bool_lock_mf = false;
                onImageButtonClickLockMf();
            }
        }

        return value;
    }



    public long getExposureTimeValue(String str, boolean bool){
        long value = 0;
        if(str.equals("AUTO")){
            value = 0;
            if(bool) {
                bool_lock_s = true;
                onImageButtonClickLockS();
            }
        }else {
            double a;
            if(((str.charAt(1)+"").equals('/'+""))){
                String c ="";
                for (int k = 2; k < str.length(); k++) {
                    c+=str.charAt(k);
                }
                a = 1.0/Integer.valueOf(c);


            } else{
                a = Double.valueOf(str);
            }


            value = camera.toNanoSec(a);
            Log.v("dfgh",""+value);
            if(bool) {
                bool_lock_s = false;
                onImageButtonClickLockS();
            }
        }

        return value;
    }


    public void onClickRadioButton(){
        if(radioButton_iso.isChecked()){
            seekBar_iso.setVisibility(View.VISIBLE);
            seekBar_s.setVisibility(View.INVISIBLE);
            seekBar_ev.setVisibility(View.INVISIBLE);
            seekBar_mf.setVisibility(View.INVISIBLE);
            seekBar_wb.setVisibility(View.INVISIBLE);

            textView_left.setText(seekbar_iso_value.get(1)+"");
            textView_right.setText(seekbar_iso_value.get(seekbar_iso_value.size()-1)+"");
            seekBar_iso.setMax(seekbar_iso_max);
            seekBar_iso.setProgress(seekbar_iso_select);
            textView_center.setText(String.valueOf("ISO: "+ (seekbar_iso_value.get(seekbar_iso_select))));
        }else if(radioButton_s.isChecked()){
            seekBar_iso.setVisibility(View.INVISIBLE);
            seekBar_s.setVisibility(View.VISIBLE);
            seekBar_ev.setVisibility(View.INVISIBLE);
            seekBar_mf.setVisibility(View.INVISIBLE);
            seekBar_wb.setVisibility(View.INVISIBLE);

            textView_left.setText(seekbar_s_value.get(1)+"");
            textView_right.setText(seekbar_s_value.get(seekbar_s_value.size()-1)+"");
            seekBar_s.setMax(seekbar_s_max);
            seekBar_s.setProgress(seekbar_s_select);
            textView_center.setText(String.valueOf("s: "+ (seekbar_s_value.get(seekbar_s_select))));
        }else if(radioButton_ev.isChecked()){
            seekBar_iso.setVisibility(View.INVISIBLE);
            seekBar_s.setVisibility(View.INVISIBLE);
            seekBar_ev.setVisibility(View.VISIBLE);
            seekBar_mf.setVisibility(View.INVISIBLE);
            seekBar_wb.setVisibility(View.INVISIBLE);

            textView_left.setText(seekbar_ev_value.get(1)+"");
            textView_right.setText(seekbar_ev_value.get(seekbar_ev_value.size()-1)+"");
            seekBar_ev.setMax(seekbar_ev_max);
            seekBar_ev.setProgress(seekbar_ev_select);
            textView_center.setText(String.valueOf("ev: "+ (seekbar_ev_value.get(seekbar_ev_select))));
        }else if(radioButton_mf.isChecked()){
            seekBar_iso.setVisibility(View.INVISIBLE);
            seekBar_s.setVisibility(View.INVISIBLE);
            seekBar_ev.setVisibility(View.INVISIBLE);
            seekBar_mf.setVisibility(View.VISIBLE);
            seekBar_wb.setVisibility(View.INVISIBLE);

            textView_left.setText(seekbar_mf_value.get(1)+"");
            textView_right.setText(seekbar_mf_value.get(seekbar_mf_value.size()-1)+"");
            seekBar_mf.setMax(seekbar_mf_max);
            seekBar_mf.setProgress(seekbar_mf_select);
            textView_center.setText(String.valueOf("mf: "+ (seekbar_mf_value.get(seekbar_mf_select))));
        }else if(radioButton_wb.isChecked()){
            seekBar_iso.setVisibility(View.INVISIBLE);
            seekBar_s.setVisibility(View.INVISIBLE);
            seekBar_ev.setVisibility(View.INVISIBLE);
            seekBar_mf.setVisibility(View.INVISIBLE);
            seekBar_wb.setVisibility(View.VISIBLE);


            textView_left.setText(seekbar_wb_value.get(1)+"");
            textView_right.setText(seekbar_wb_value.get(seekbar_wb_value.size()-1)+"");
            seekBar_wb.setMax(seekbar_wb_max);
            seekBar_wb.setProgress(seekbar_wb_select);
            textView_center.setText(String.valueOf("wb: "+ (seekbar_wb_value.get(seekbar_wb_select))));
        }
    }







    // ниже представлены методы интерфейса TextureView.SurfaceTextureListener
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Surface surface = new Surface(surfaceTexture);
/*
        try {
            AssetFileDescriptor afd = getActivity().getAssets().openFd(FILE_NAME);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer
                    .setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayer.setSurface(surface);
            mMediaPlayer.setLooping(true);

            mMediaPlayer.prepareAsync();

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }*/
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    public void ChangingFragment(Object o){
        ((MainActivity)getActivity()).fragmentActivityReplace(o);
    }


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {


            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    camera.takePicture();//---------------------------------------
                    int_shot_secussfull += 1;
                    textView_down_right.setText("сделанно: \n"+int_shot_secussfull+"\nосталось: \n"+(int_shot_count-int_shot_secussfull));
                    if (int_shot_secussfull >= int_shot_count){
                        mTimer.cancel();
                        onImageButtonClickShot();
                    }

                }
            });
        }
    }

}

