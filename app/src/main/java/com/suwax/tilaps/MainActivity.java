package com.suwax.tilaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "format";
    private SharedPreferences mSettings;

    public Object activeFragmentVisible;
    public int shot = 10;
    public int shotTime = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (savedInstanceState == null) {
            activeFragmentVisible = new Fragment_menu();
            getSupportFragmentManager().beginTransaction().add(R.id.container, new Fragment_menu()).commit();

        }

        //getting camera permissions
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        )
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }





        //setShot(100);
        //setShotTime(1);
    }


    //changing fragments
    public void fragmentActivityReplace(Object o){

        if(o.getClass().isInstance(new Fragment_foto())){

            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_foto()).commit();
            activeFragmentVisible = new Fragment_foto();
        }
        if(o.getClass().isInstance(new Fragment_video())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_video()).commit();
            activeFragmentVisible = new Fragment_video();
        }
        if(o.getClass().isInstance(new Fragment_foto_video())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_foto_video()).commit();
            activeFragmentVisible = new Fragment_foto_video();
        }
        if(o.getClass().isInstance(new Fragment_setting())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_setting()).commit();
            activeFragmentVisible = new Fragment_setting();
        }
        if(o.getClass().isInstance(new Fragment_gallery())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_gallery()).commit();
            activeFragmentVisible = new Fragment_gallery();
        }
        if(o.getClass().isInstance(new Fragment_calculator())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_calculator()).commit();
            activeFragmentVisible = new Fragment_calculator();
        }
        if(o.getClass().isInstance(new Fragment_info())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_info()).commit();
            activeFragmentVisible = new Fragment_info();
        }
        if(o.getClass().isInstance(new Fragment_foto_setting())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_foto_setting()).commit();
            activeFragmentVisible = new Fragment_foto_setting();
        }
        if(o.getClass().isInstance(new Fragment_video_setting())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_video_setting()).commit();
            activeFragmentVisible = new Fragment_video_setting();
        }


    }

    public int getShot(){
        return shot;
    }
    public void setShot(int s){
        shot = s;
    }

    public int getShotTime(){
        return shotTime;
    }
    public void setShotTime(int s){
        shotTime = s;
    }


    //back button
    public void onBackPressed() {
        if(activeFragmentVisible.getClass().isInstance(new Fragment_foto()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_video()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_foto_video()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_gallery()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_calculator()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_setting()) ||
                activeFragmentVisible.getClass().isInstance(new Fragment_info()) ){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_menu()).commit();
            activeFragmentVisible = new Fragment_menu();
        }
        else if(activeFragmentVisible.getClass().isInstance(new Fragment_menu())){

            this.finish();
            System.exit(0);
        }
        else if(activeFragmentVisible.getClass().isInstance(new Fragment_foto_setting())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_foto()).commit();
            activeFragmentVisible = new Fragment_foto();
        }
        else if(activeFragmentVisible.getClass().isInstance(new Fragment_video_setting())){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  new Fragment_video()).commit();
            activeFragmentVisible = new Fragment_video();
        }

    }

}

