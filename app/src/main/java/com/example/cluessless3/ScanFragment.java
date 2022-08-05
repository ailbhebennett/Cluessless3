package com.example.cluessless3;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cluessless3.databinding.FragmentScanBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.UUID;


public class ScanFragment extends Fragment implements View.OnClickListener {

    private static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private static final int CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE = 0;
    private static final int CAMERA_CAPTURE = 0;
    public Button btnScan;
    private Button btn_crop_now;
    private static final int PIC_CROP = 2;

    public ImageView imageScan;
    private Drawable drawable;
    private Bitmap bitmap;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    private UploadTask.TaskSnapshot taskSnapshot;
    private FragmentScanBinding binding;
    private Uri uri;
    private ScanFragment scanFragment;

    //taking photo

    public void onClick(View view2){
        if (view2.getId() == R.id.btn_scan_now){
            int CAMERA_CAPTURE = 1;
            try{
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CAPTURE);

            }
            catch (ActivityNotFoundException anfe){
                String errorMessage = "Whoops - your device doesn't support capturing images!";
                Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_CAPTURE){
                uri = data.getData();
                performCrop();
            }
            else if(requestCode == PIC_CROP){
                Bundle extras = data.getExtras();
                bitmap = extras.getParcelable("data");

                ImageView picView = (ImageView) imageScan.findViewById(R.id.product_image);
                picView.setImageBitmap(bitmap);
            }
        }

    }

    private void performCrop() {
        try{
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(uri,"image/");
            cropIntent.putExtra("crop",true);
            cropIntent.putExtra("aspectX",1);
            cropIntent.putExtra("aspectY",1);
            cropIntent.putExtra("outputX",256);
            cropIntent.putExtra("outputY",256);
            cropIntent.putExtra("return-data",true);

            startActivityForResult(cropIntent, PIC_CROP);

        }
        catch (ActivityNotFoundException anfe){
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }


        public void uploadImage(){
            if(uri !=null){
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());

                storageReference.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(),"Uploaded", Toast.LENGTH_SHORT);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(getContext(),"oops, failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded" + (int)progress+"%");
                            }
                        });

            }
                {

                }


            }
        }



    }


}
