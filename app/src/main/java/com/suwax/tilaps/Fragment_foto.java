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
import android.widget.RelativeLayout;
import java.io.IOException;


public class Fragment_foto extends Fragment implements TextureView.SurfaceTextureListener {

    private static final String TAG = MainActivity.class.getName();

    private static final String FILE_NAME = "Animation7529.mp4"; // ваш файл

    private MediaPlayer mMediaPlayer;
    private TextureView mTextureView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {


        View fragment = inflater.inflate(R.layout.fragment_foto,container,false);

        mTextureView = (TextureView) fragment.findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);




        return fragment;

    }



    private void updateTextureViewSize(int viewWidth, int viewHeight) {
        mTextureView.setLayoutParams(new RelativeLayout.LayoutParams(viewWidth, viewHeight));
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
