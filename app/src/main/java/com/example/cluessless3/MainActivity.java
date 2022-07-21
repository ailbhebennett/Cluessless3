package com.example.cluessless3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.net.ConnectException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declare required variables
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        //Request camera permissions
        int MY_PERMISSIONS_REQUEST_CAMERA=0;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA );
            }
        }//Obtained camera permissions

        //Tabbed Layout
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabView);

        tabLayout.addTab(tabLayout.newTab().setText("Scan CLothing"));
        tabLayout.addTab(tabLayout.newTab().setText("View Clothing"));
        tabLayout.addTab(tabLayout.newTab().setText("Saved Clothes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("TAG","tab position:"+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }
        });





    }}