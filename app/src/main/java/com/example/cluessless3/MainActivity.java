package com.example.cluessless3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.cluessless3.databinding.ActivityClothinglistBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.net.ConnectException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    DatabaseReference database;
    private static ClothingAdapter clothingAdapter;
    private static ArrayList<ClothingModel> list;
    static View.OnClickListener myOnClickListener;
    ActivityClothinglistBinding binding;


    String clothingName, clothingColour, clothingDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_clothingList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        list = new ArrayList<ClothingModel>();
        int i;
        for (i = 0, i < ClothingList.clothingNameArray.length, i++){
            list.add(new ClothingModel(ClothingModel.clothingName[i], ClothingModel.clothingDetails[i],ClothingModel.clothingColourArray[i]));
        }

        clothingAdapter = new ClothingAdapter(list);
        recyclerView.setAdapter(clothingAdapter);

        class MyOnClickListener implements  View.OnClickListener{
            private final Context context;

            private MyOnClickListener(Context context){
                this.context = context;
            }

            @Override
            public boolean onCreateOptionsMenu (Menu menu) {
                super.onCreateOptionsMenu(menu);
                super.onCreateOptionsMenu(menu);
                getMenuInflater().inflate(R.layout.main_menu, menu);
                return true;
            }

            @Override
            public void onClick(View view) {

            }
        }




    }}