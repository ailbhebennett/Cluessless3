package com.example.cluessless3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ClothingFilter extends AppCompatActivity implements View.OnClickListener {

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
