package com.suwax.tilaps;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.content.res.AssetFileDescriptor;
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

import java.io.IOException;
import java.util.ArrayList;


public class Fragment_foto extends Fragment implements TextureView.SurfaceTextureListener {

    private static final String TAG = MainActivity.class.getName();

    private static final String FILE_NAME = "Animation7529.mp4"; // ваш файл

    private MediaPlayer mMediaPlayer;
    private TextureView mTextureView;
    private View fragment;

    //int_button
    private int int_shot = 0;
    private int int_flash = 0;
    private int int_grid = 0;
    private int int_timer = 0;
    private int int_level = 0;
    private int int_setting = 0;
    private int int_number_camera = 0;
    private int int_lock_iso = 0;
    private int int_lock_wb = 0;
    private int int_lock_s = 0;
    private int int_lock_mf = 0;
    private int int_super = 0;
    private int int_multi = 0;
    private int int_hdr = 0;


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

    private SeekBar seekBar;

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

    private CameraServiceAdapter cameraService = new CameraServiceAdapter();


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
        button_super.setEnabled(false);//not implemented

        button_multi = fragment.findViewById(R.id.button_multi);
        button_multi.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onButtonClickMulti(); } });
        button_multi.setEnabled(false);//not implemented

        button_hdr = fragment.findViewById(R.id.button_hdr);
        button_hdr.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onButtonClickHdr(); } });
        button_hdr.setEnabled(false);//not implemented

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

        textView_left.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onClickTextViewSeekBar(-1); } });
        textView_right.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { onClickTextViewSeekBar(1); } });

        seekBar = fragment.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        mTextureView = (TextureView) fragment.findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);



        cameraService.startBackgroundThread();
        cameraService.onCreate(getActivity(), mTextureView);


        return fragment;

    }


    public void onImageButtonClickShot(){
        if(int_shot == 0){
            imageButton_shot.setImageResource(R.drawable.ic_shot_play_svg);
            int_shot = 1;

            linearLayout_top_element.setVisibility(View.INVISIBLE);
            linearLayout_top_mode.setVisibility(View.INVISIBLE);
            linearLayout_down_mode.setVisibility(View.INVISIBLE);
            linearLayout_lock.setVisibility(View.INVISIBLE);
            linearLayout_text.setVisibility(View.VISIBLE);
            imageButton_number_camera.setVisibility(View.INVISIBLE);

            textView_down_left.setText("iso: "+seekbar_iso_value.get(seekbar_iso_select)+ "\ns: "+seekbar_s_value.get(seekbar_s_select)+ "\nwb: "+seekbar_mf_value.get(seekbar_mf_select)+ "\nwb: "+seekbar_wb_value.get(seekbar_wb_select));
            textView_down_right.setText("сделанно: \n34540\nосталось: \n345678");
            for(int i = 0; i<10;i++) {
                cameraService.ShotCamera(int_number_camera);


            }
        }else{
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
            int_flash = 1;
        }else if(int_flash == 1){
            imageButton_flash.setImageResource(R.drawable.ic_flash_full_svg);
            int_flash = 2;
        }else{
            imageButton_flash.setImageResource(R.drawable.ic_flash_disable_svg);
            int_flash = 0;
        }
    }

    public void onImageButtonClickGrid()
    {
        if(int_grid == 0){
            imageButton_grid.setImageResource(R.drawable.ic_grid_true_svg);
            int_grid = 1;
        }else{
            imageButton_grid.setImageResource(R.drawable.ic_grid_false_svg);
            int_grid = 0;
        }
    }


    public void onImageButtonClickTimer()
    {
        if(int_timer == 0){
            imageButton_timer.setImageResource(R.drawable.ic_timer_1_svg);
            int_timer = 1;
        }else if(int_timer == 1){
            imageButton_timer.setImageResource(R.drawable.ic_timer_3_svg);
            int_timer = 2;
        }else if(int_timer == 2){
            imageButton_timer.setImageResource(R.drawable.ic_timer_5_svg);
            int_timer = 3;
        }else if(int_timer == 3){
            imageButton_timer.setImageResource(R.drawable.ic_timer_10_svg);
            int_timer = 4;
        }else{
            imageButton_timer.setImageResource(R.drawable.ic_timer_0_svg);
            int_timer = 0;
        }
    }

    public void onImageButtonClickLevel()
    {
        if(int_level == 0){
            imageButton_level.setImageResource(R.drawable.ic_level_true_svg);
            int_level = 1;
        }else{
            imageButton_level.setImageResource(R.drawable.ic_level_false_svg);
            int_level = 0;
        }
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
        cameraService.ChangingCamera(int_number_camera);
    }

    public void onImageButtonClickLockIso()
    {
        if(int_lock_iso == 0){
            imageButton_lock_iso.setImageResource(R.drawable.ic_lock_closed_iso_svg);
            int_lock_iso = 1;
        }else{
            imageButton_lock_iso.setImageResource(R.drawable.ic_lock_opened_iso_svg);
            int_lock_iso = 0;
        }
    }

    public void onImageButtonClickLockWb()
    {
        if(int_lock_wb == 0){
            imageButton_lock_wb.setImageResource(R.drawable.ic_lock_closed_wb_svg);
            int_lock_wb = 1;
        }else{
            imageButton_lock_wb.setImageResource(R.drawable.ic_lock_opened_wb_svg);
            int_lock_wb = 0;
        }
    }

    public void onImageButtonClickLockMf()
    {
        if(int_lock_mf == 0){
            imageButton_lock_mf.setImageResource(R.drawable.ic_lock_closed_mf_svg);
            int_lock_mf = 1;
        }else{
            imageButton_lock_mf.setImageResource(R.drawable.ic_lock_opened_mf_svg);
            int_lock_mf = 0;
        }
    }

    public void onImageButtonClickLockS()
    {
        if(int_lock_s == 0){
            imageButton_lock_s.setImageResource(R.drawable.ic_lock_closed_s_svg);
            int_lock_s = 1;
        }else{
            imageButton_lock_s.setImageResource(R.drawable.ic_lock_opened_s_svg);
            int_lock_s = 0;
        }
    }

    public void onButtonClickSuper()
    {
        if(int_super == 0){
            button_super.setTextColor(getResources().getColor(R.color.colorAccent));
            int_super = 1;
        }else{
            button_super.setTextColor(getResources().getColor(R.color.text));
            int_super = 0;
        }
    }

    public void onButtonClickMulti()
    {
        if(int_multi == 0){
            button_multi.setTextColor(getResources().getColor(R.color.colorAccent));
            int_multi = 1;
        }else{
            button_multi.setTextColor(getResources().getColor(R.color.text));
            int_multi = 0;
        }
    }

    public void onButtonClickHdr()
    {
        if(int_hdr == 0){
            button_hdr.setTextColor(getResources().getColor(R.color.colorAccent));
            int_hdr = 1;
        }else{
            button_hdr.setTextColor(getResources().getColor(R.color.text));
            int_hdr = 0;
        }
    }

    public void onClickTextViewSeekBar(int step){
        Log.v("text","error");
        if(seekBar.getProgress() == seekBar.getMax()){
            if(step<0){
                seekBar.setProgress(seekBar.getProgress()+step);
            }
        } else if (seekBar.getProgress() == 0){
            if(step>0){
                seekBar.setProgress(seekBar.getProgress()+step);
            }
        } else{
            seekBar.setProgress(seekBar.getProgress()+step);
        }
    }


    public void onSeekBarProgressChanged(){
        if(radioButton_iso.isChecked()){
            seekbar_iso_select = seekBar.getProgress();
            textView_center.setText(String.valueOf("ISO: "+ (seekbar_iso_value.get(seekbar_iso_select))));
        }else if(radioButton_s.isChecked()){
            seekbar_s_select = seekBar.getProgress();
            textView_center.setText(String.valueOf("s: "+ (seekbar_s_value.get(seekbar_s_select))));
        }else if(radioButton_ev.isChecked()){
            seekbar_ev_select = seekBar.getProgress();
            textView_center.setText(String.valueOf("ev: "+ (seekbar_ev_value.get(seekbar_ev_select))));
        }else if(radioButton_mf.isChecked()){
            seekbar_mf_select = seekBar.getProgress();
            textView_center.setText(String.valueOf("mf: "+ (seekbar_mf_value.get(seekbar_mf_select))));
        }else if(radioButton_wb.isChecked()){
            seekbar_wb_select = seekBar.getProgress();
            textView_center.setText(String.valueOf("wb: "+ (seekbar_wb_value.get(seekbar_wb_select))));
        }
    }

    private void GetParametrsForCamera(){
        seekbar_iso_value.add("q");
        seekbar_iso_value.add("w");
        seekbar_iso_value.add("e");
        seekbar_iso_value.add("r");
        seekbar_iso_value.add("t");
        seekbar_iso_max = seekbar_iso_value.size()-1;

        seekbar_s_value.add("a");
        seekbar_s_value.add("s");
        seekbar_s_value.add("d");
        seekbar_s_value.add("f");
        seekbar_s_value.add("g");
        seekbar_s_value.add("c");
        seekbar_s_max = seekbar_s_value.size()-1;

        seekbar_ev_value.add("z");
        seekbar_ev_value.add("x");
        seekbar_ev_value.add("v");
        seekbar_ev_value.add("b");
        seekbar_ev_max = seekbar_ev_value.size()-1;

        seekbar_mf_value.add("/");
        seekbar_mf_value.add(".");
        seekbar_mf_value.add(",");
        seekbar_mf_max = seekbar_mf_value.size()-1;

        seekbar_wb_value.add("'");
        seekbar_wb_value.add(";");
        seekbar_wb_value.add("l");
        seekbar_wb_value.add("k");
        seekbar_wb_value.add("j");
        seekbar_wb_value.add("m");
        seekbar_wb_value.add("n");
        seekbar_wb_max = seekbar_wb_value.size()-1;



    }

    public void onClickRadioButton(){
        if(radioButton_iso.isChecked()){
            textView_left.setText(seekbar_iso_value.get(0)+"");
            textView_right.setText(seekbar_iso_value.get(seekbar_iso_value.size()-1)+"");
            seekBar.setMax(seekbar_iso_max);
            seekBar.setProgress(seekbar_iso_select);
            textView_center.setText(String.valueOf("ISO: "+ (seekbar_iso_value.get(seekbar_iso_select))));
        }else if(radioButton_s.isChecked()){
            textView_left.setText(seekbar_s_value.get(0)+"");
            textView_right.setText(seekbar_s_value.get(seekbar_s_value.size()-1)+"");
            seekBar.setMax(seekbar_s_max);
            seekBar.setProgress(seekbar_s_select);
            textView_center.setText(String.valueOf("s: "+ (seekbar_s_value.get(seekbar_s_select))));
        }else if(radioButton_ev.isChecked()){
            textView_left.setText(seekbar_ev_value.get(0)+"");
            textView_right.setText(seekbar_ev_value.get(seekbar_ev_value.size()-1)+"");
            seekBar.setMax(seekbar_ev_max);
            seekBar.setProgress(seekbar_ev_select);
            textView_center.setText(String.valueOf("ev: "+ (seekbar_ev_value.get(seekbar_ev_select))));
        }else if(radioButton_mf.isChecked()){
            textView_left.setText(seekbar_mf_value.get(0)+"");
            textView_right.setText(seekbar_mf_value.get(seekbar_mf_value.size()-1)+"");
            seekBar.setMax(seekbar_mf_max);
            seekBar.setProgress(seekbar_mf_select);
            textView_center.setText(String.valueOf("mf: "+ (seekbar_mf_value.get(seekbar_mf_select))));
        }else if(radioButton_wb.isChecked()){
            textView_left.setText(seekbar_wb_value.get(0)+"");
            textView_right.setText(seekbar_wb_value.get(seekbar_wb_value.size()-1)+"");
            seekBar.setMax(seekbar_wb_max);
            seekBar.setProgress(seekbar_wb_select);
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

}
