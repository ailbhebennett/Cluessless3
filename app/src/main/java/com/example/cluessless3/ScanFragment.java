package com.example.cluessless3;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cluessless3.databinding.FragmentScanBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class ScanFragment extends Fragment{

    private static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private static final int CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE = 2;
    private static final int CAMERA_CAPTURE = 3;
    public Button btn_scan;
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
    private ViewGroup rootView;
    private static final int Image_capture_Code = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scan, container,false);
        btn_scan = (Button) view.findViewById(R.id.btn_scan_now);
        imageScan = (ImageView) view.findViewById(R.id.product_image);

        /*btn_scan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File picture= null;

                try{
                    picture = File.createTempFile("temp",".jpg",getContext().getCacheDir());
                    cInt.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(),BuildConfig.APPLICATION_ID+".provider",picture));
                }
                catch(IOException e) {
                    e.printStackTrace();

                }
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 3 && resultCode == RESULT_OK){
            Log.d("text", "onActivityResult" + uri);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(uri, "image/");
            cropIntent.putExtra("crop", true);
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            cropIntent.putExtra("return-data", true);

            startActivityForResult(cropIntent, PIC_CROP);

        } catch (ActivityNotFoundException anfe) {
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }


        /*public void uploadImage(View v){
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


            }*/
        return view;
    }

}
