package com.example.cluessless3;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;


@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    //Declare required variables
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button uIButton;
    private static final int pic_id = 123;
    private ImageView imageId;
    private StorageReference storageRef;
    private String path;
    private UploadTask uploadTask;
    private Bitmap photo;
    private Uri mImageURI;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        uIButton = (Button) findViewById(R.id.btn_scan_now);
        imageId = (ImageView) findViewById(R.id.product_image);

        //Request camera permissions
        int MY_PERMISSIONS_REQUEST_CAMERA = 333;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }

        }//Obtained camera permissions


        uIButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(camera_intent, pic_id);

                storageRef = FirebaseStorage.getInstance().getReference();
            }
        });


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

    }//end onCreate

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageURI = data.getData();
            File file = new File(mImageURI.getPath());
            final String[] split = file.getPath().split("");
            Picasso.get().load(mImageURI).into(uploadToFirebase(mImageURI));
        }
    }


    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = this.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadToFirebase(Uri mImageURI){
        if(mImageURI !=null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            storageRef = storageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageURI));
            storageRef.putFile(mImageURI).addOnSuccessListener(
                    taskSnapshot -> {
                        progressDialog.dismiss();
                        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
                        storageRef.getDownloadUrl().addOnSuccessListener(uri ->{
                            Object upload = new Object(uri.toString());
                            String uploadId = databaseReference.push().getKey();
                            assert uploadId != null;
                            databaseReference.child(uploadId).setValue(upload);
                        });
                    }).addOnProgressListener(snapshot -> {
                        double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploaded" + (int) progress + "%");
            })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(this, "ERROR" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });



        }
    }

}

    //images/image.jpg