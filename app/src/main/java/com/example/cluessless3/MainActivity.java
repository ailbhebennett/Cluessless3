package com.example.cluessless3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    //Declare required variables
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button uIButton;
    private static final int pic_id = 123;
    private ImageView imageId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        imageId = (ImageView) findViewById(R.id.product_image);

        //Request camera permissions
        int MY_PERMISSIONS_REQUEST_CAMERA = 333;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }

        }//Obtained camera permissions

        uIButton = (Button) findViewById(R.id.btn_scan_now);

        uIButton.setOnClickListener(new BtnClick());



        //Tabbed Layout
        /*viewPager =(ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabView);

        tabLayout.addTab(tabLayout.newTab().setText("Scan Clothing"));
        tabLayout.addTab(tabLayout.newTab().setText("View Clothing"));
        tabLayout.addTab(tabLayout.newTab().setText("Saved Clothes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("TAG", "tab position:" + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }
        });*/

    }

    private class BtnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent cintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            onActivityResult(pic_id, 0,cintent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id){
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            imageId.setImageBitmap(photo);


        }
    }



}