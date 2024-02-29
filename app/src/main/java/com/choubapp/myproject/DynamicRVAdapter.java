package com.choubapp.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.DynamicRVHolder>{

    public ArrayList<DynamicRVModel> dynamicRVModels;

    public DynamicRVAdapter(ArrayList<DynamicRVModel> dynamicRVModels){
        this.dynamicRVModels = dynamicRVModels;

    }

    public class DynamicRVHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView, detailsView;


        public DynamicRVHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.name);
            detailsView = itemView.findViewById(R.id.details);
        }
    }

    @NonNull
    @Override
    public DynamicRVAdapter.DynamicRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout, parent, false);
        DynamicRVHolder dynamicRVHolder = new DynamicRVHolder(view);
        return dynamicRVHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRVAdapter.DynamicRVHolder holder, int position) {
        DynamicRVModel currentItem = dynamicRVModels.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getName());
        holder.detailsView.setText(currentItem.getDetails());

    }

    @Override
    public int getItemCount() {
        return dynamicRVModels.size();
    }

}