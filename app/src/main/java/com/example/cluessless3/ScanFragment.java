package com.example.cluessless3;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.example.cluessless3.R.string.imageCropError;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScanFragment extends Fragment {

    private static final Object TAG_IMAGE = "";
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
    public RequestQueue queue;
    public Context crop;


    //taking photo
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan,container,false);
        btnScan = (Button) view.findViewById(R.id.btn_scan_now);
        imageScan = (ImageView) view.findViewById(R.id.product_image);


        queue = Volley.newRequestQueue(crop);

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
                    cropImageRequest(imageScan);
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
    };

    private StringRequest cropImageRequest(ImageView imageCrop) {
        final String API = "&api_key=AIzaSyBFEuwHn5rqcrNYMMK22afZihf025dZ-Vc";
        final ImageView image_view = null;
        final String URL_PREFIX = "https://rapidapi.com/blacktrees/api/image-cropper";
        final String DATA_SOURCE = "&ds=Standard Reference";

        String url = URL_PREFIX + API + image_view;

        return new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response).getJSONObject("image");
                            int maxItems = result.getInt("end");
                            JSONArray resultList = result.getJSONArray("item");
                        } catch (JSONException e) {
                            Toast.makeText(crop.getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(crop.getApplicationContext(), getString(imageCropError), Toast.LENGTH_LONG).show();
                    }
                });
    }

        private void btnSearchClickEventHandler(View view){
            queue.cancelAll(TAG_IMAGE);

            ImageView imageCrop = null;
            StringRequest stringRequest = cropImageRequest(imageCrop);
            queue.add(stringRequest);


        }


       /* private void saveImageToDatabase(StorageReference storageRef ) {

           storageReference = firebaseStorage.getReference();

            btn_saveToDatabase Onclick(){
                // Create a reference to "mountains.jpg"
                StorageReference mountainsRef = storageRef.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
                StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
                mountainsRef.getName().equals(mountainImagesRef.getName());    // true
                mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

            }*/



        }
