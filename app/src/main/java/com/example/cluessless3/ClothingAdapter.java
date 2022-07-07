package com.example.cluessless3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClothingAdapter extends RecyclerView.Adapter<ClothingAdapter.MyViewHolder> {

    Context context;

    ArrayList<ClothingModel> list;
    private Object View;

    public ClothingAdapter(Context context, ArrayList<ClothingModel> list) {
        this.context = context;
        this.list = list;
    }

    public ClothingAdapter(ArrayList<ClothingModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rvcard_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ClothingModel clothingmodel = list.get(position);
        holder.clothingName.setText(clothingmodel.getClothingName());
        holder.clothingDescription.setText(clothingmodel.getClothingDescription());
        
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clothingName, clothingDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            clothingName = itemView.findViewById(R.id.clothingName_txt);
            clothingDescription = itemView.findViewById(R.id.clothingDescription_txt);

        }
    }

}
