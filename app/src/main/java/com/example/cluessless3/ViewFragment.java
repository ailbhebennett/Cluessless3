package com.example.cluessless3;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.storage.UploadTask;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.videoio.VideoCapture;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ViewFragment extends Fragment{

    private static final Object CV_CAP_ANY =0 ;
    private static final int CV_CAP_PROP_FRAME_WIDTH = 0;
    private static final int CV_CAP_PROP_FRAME_HEIGHT =0 ;
    private static final int CV_WINDOW_AUTOSIZE = 0;
    private static View imageScan;
    public Button btnScan;
    private static final int Image_Capture_Code = 1;
    private Exception exception;
    private UploadTask.TaskSnapshot taskSnapshot;
    public OpenCVLoader mOpenCvCameraView;


    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded");
                    mOpenCvCameraView.
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_6, this, mLoaderCallback);
    }


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

    private int Detection(String[] args) {
        VideoCapture cap = new VideoCapture((Integer) CV_CAP_ANY);
        cap.set(CV_CAP_PROP_FRAME_WIDTH, 320);
        cap.set(CV_CAP_PROP_FRAME_HEIGHT, 240);
        if (!cap.isOpened()) {
            return -1;
        }

        Mat img = new Mat();
        HOGDescriptor hog = new HOGDescriptor();
        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());

        private static void namedWindow("video capture", CV_WINDOW_AUTOSIZE, cap){
        while (true) {
        if (!img.datas)
                continue;

            ArrayList<Rect> found = new ArrayList<Rect>();
            ArrayList<Rect> found_filtered = new ArrayList<Rect>();
            hog.detectMultiScale(img, found, 0, Size(8, 8), Size(32, 32), 1.05, 2);


            public static void onActivityResult(int request, int resutlC, Intent data){
                if (request == Image_Capture_Code) {
                    if (resutlC == RESULT_OK) {
                        imageScan.setDrawingCacheEnabled(true);
                        imageScan.buildDrawingCache();
                        Bitmap bitmap = ((BitmapDrawable) imageScan.getDrawable()).getBitmap();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] datas = baos.toByteArray();

                    } else if (resutlC == RESULT_CANCELED) {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        }
        }
    }
}