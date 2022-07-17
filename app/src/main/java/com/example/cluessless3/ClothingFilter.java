package com.example.cluessless3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ClothingFilter extends AppCompatActivity implements View.OnClickListener{

    Camera mCamera;
    private final int PERMISSION_CONSTANT = 1000;
    HorizontalScrollView horizontalScrollView;

    @Override
    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_tab2);
        ImageView ivCapture = (ImageView)findViewById(R.id.ivCapture);
        ImageView ivFilter =(ImageView) findViewById(R.id.ivFilter);
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.filterLayout)

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private  void checkPermissionAndGive(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) && if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean b = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED;
        }
    }

}
