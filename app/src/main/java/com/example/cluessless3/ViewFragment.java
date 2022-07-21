package com.example.cluessless3;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ViewFragment extends Fragment {

    public Button btnScan;
    public ImageView imageScan;
    private static final int Image_Capture_Code = 1;
    private Exception exception;
    private UploadTask.TaskSnapshot taskSnapshot;

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

    public void onActivityResult(int request, int resutlC, Intent data){
        if(request == Image_Capture_Code){
            if(resutlC == RESULT_OK){
                imageScan.setDrawingCacheEnabled(true);
                imageScan.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) imageScan.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();

            }
            else if(resutlC == RESULT_CANCELED){
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }


    }
}