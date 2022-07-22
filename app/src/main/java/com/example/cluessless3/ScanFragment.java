package com.example.cluessless3;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ScanFragment extends Fragment {

    public Button btnScan;
    private Button btn_saveToDatabase;
    public ImageView imageScan;
    private Drawable drawable;
    private static final int Image_Capture_Code = 1;
    private Exception exception;
    private UploadTask.TaskSnapshot taskSnapshot;
    private Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    //keep track of cropping intent
    final int PIC_CROP = 2;


    //taking photo
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan,container,false);
        btnScan = (Button) view.findViewById(R.id.btn_scan_now);
        imageScan = (ImageView) view.findViewById(R.id.product_image);

        //onclick
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent scan = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(scan,Image_Capture_Code);

            }
        });

        return view;
    }



    public void onActivityResult(int request, int resultC, Intent data){
        if(request == Image_Capture_Code){
            if(resultC == RESULT_OK){
                try{
                    //get picture URI
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    performCrop();
                }
                catch(Exception e){

                }

            }
            else if (request == PIC_CROP) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = extras.getParcelable("data");

                //display bitmap
                imageScan.findViewById(R.id.product_image);
                imageScan.setImageBitmap(bitmap);

            }
            else if(resultC == RESULT_CANCELED){
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(imageUri, "image/*");
            cropIntent.putExtra("Crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);

            //get data
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);


        } catch (ActivityNotFoundException anfe){
            String errorMessage = ("Not working");
            Toast toast = Toast.makeText(getActivity() , errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }






}