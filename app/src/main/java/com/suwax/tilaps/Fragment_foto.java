package com.suwax.tilaps;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;


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
    //

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

    //

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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



        mTextureView = (TextureView) fragment.findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);





        return fragment;

    }


    public void onImageButtonClickShot()
    {
        if(int_shot == 0){
            imageButton_shot.setImageResource(R.drawable.ic_shot_play_svg);
            int_shot = 1;
        }else{
            imageButton_shot.setImageResource(R.drawable.ic_shot_stop_svg);
            int_shot = 0;
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







    // ниже представлены методы интерфейса TextureView.SurfaceTextureListener
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Surface surface = new Surface(surfaceTexture);

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
        }
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

}
