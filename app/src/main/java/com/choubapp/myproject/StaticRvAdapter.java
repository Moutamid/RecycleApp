package com.choubapp.myproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder>{

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView = updateRecyclerView;

    }

    @NonNull
    @Override
    // inflate the layout item xml & pass it to view holder
    //used bind the layout.xml file
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view); //pass the view to view holder
        return staticRVViewHolder;
    }

    @Override
    // set the data in the view's with the help of view holder
    // used to bind the two variables (image and text).
    public void onBindViewHolder(@NonNull StaticRvAdapter.StaticRVViewHolder holder, int position) {
        // set the data in Recycle view
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if(check){

            ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
            items.add(new DynamicRVModel("Printed Paper","(Glossy and Non-Glossy)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Flyer","(Glossy and Non-Glossy)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Brochure","(Glossy and Non-Glossy)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Magazine","(Glossy and Non-Glossy)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Envelope","(With and Without Plastic Window)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Paper Packaging","(e.g. Printed Paper Box)", R.drawable.recyclable));
            items.add(new DynamicRVModel("Writing Paper",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Newspaper",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Telephone Directory",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Red Packet",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Name Card",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Calendar",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Greeting Card",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Gift Wrapping Paper",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Shredded Paper",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Paper Receipt",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Carton Box","Please Flatten", R.drawable.recyclable));
            items.add(new DynamicRVModel("Egg Tray",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Paper Towel & Toiler Roll Tube",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Paper Bag",".", R.drawable.recyclable));
            items.add(new DynamicRVModel("Tissue Box","Please Flatten", R.drawable.recyclable));
            items.add(new DynamicRVModel("Beverage Carton ","Please empty and rinse ", R.drawable.recyclable));
            items.add(new DynamicRVModel("Book","Donate if it can be reused", R.drawable.recyclable));
            items.add(new DynamicRVModel("Used Paper Disposables","(e.g. Paper Cup, Plate)", R.drawable.nonrecyclable));
            items.add(new DynamicRVModel("Tissue Paper",".", R.drawable.nonrecyclable));
            items.add(new DynamicRVModel("Paper Towel",".", R.drawable.nonrecyclable));
            items.add(new DynamicRVModel("Toilet Paper",".", R.drawable.nonrecyclable));
            items.add(new DynamicRVModel("Disposable Wooden Chopstick",".", R.drawable.nonrecyclable));
            items.add(new DynamicRVModel("Wax Paper",".", R.drawable.nonrecyclable));

            updateRecyclerView.callback(position, items);

            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position==0){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Printed Paper","(Glossy and Non-Glossy)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Flyer","(Glossy and Non-Glossy)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Brochure","(Glossy and Non-Glossy)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Magazine","(Glossy and Non-Glossy)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Envelope","(With and Without Plastic Window)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Paper Packaging","(e.g. Printed Paper Box)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Writing Paper",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Newspaper",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Telephone Directory",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Red Packet",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Name Card",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Calendar",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Greeting Card",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Gift Wrapping Paper",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Shredded Paper",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Paper Receipt",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Carton Box","Please Flatten", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Egg Tray",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Paper Towel & Toiler Roll Tube",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Paper Bag",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Tissue Box","Please Flatten", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Beverage Carton ","Please empty and rinse ", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Book","Donate if it can be reused", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Used Paper Disposables","(e.g. Paper Cup, Plate)", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Tissue Paper",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Paper Towel",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Toilet Paper",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Disposable Wooden Chopstick",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Wax Paper",".", R.drawable.nonrecyclable));

                    updateRecyclerView.callback(position, items);

                }

                else if (position==1){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("CD & CD Casing",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Bag",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Film/Flexible Packaging","(e.g. Magazine Wrapper, Bubble Wrap)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Clothes Hanger",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Container - Food","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Bottle - Beverage","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Bottle - Non-Food","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Plastic Packaging","(e.g. Sliced Bread Bag, Egg Trays)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Polystyrene Foam Product","(e.g. Styrofoam Cup)", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Plastic Disposables","(e.g. Cutlery & Crockery", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Plastic Packaging with Foil","(e.g. Potato Chip Bags)", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Oxo- and Biodegradable Bags",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Drinking Straw",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Cassette & Video Tape",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Melamine Products","(e.g. Melamine Cups, Melamine Plates )", R.drawable.nonrecyclable));

                    updateRecyclerView.callback(position, items);
                }

                else if (position==2){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Glassware","(e.g. Cup, Plate)", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Glass Bottle - Beverage","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Glass Bottle - Food","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Glass Bottle - Cosmetic","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Medicine & Supplement Bottle","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Borosilicate/Pyrex Glassware","(e.g. Bakeware)", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Windows",".", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Mirror","Donate if it can be reused", R.drawable.nonrecyclable));
                    items.add(new DynamicRVModel("Ceramic Product","(e.g.Ceramic Plate, Tea Pot)", R.drawable.nonrecyclable));

                    updateRecyclerView.callback(position, items);
                }

                else if (position==3){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Light Bulb","Recycled at specific collection points", R.drawable.recyclable));

                    updateRecyclerView.callback(position, items);
                }

                else if (position==4){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("Metal Bottle Cap",".", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Metal Can - Beverage","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Metal Can - Food","Please empty & rinse", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Aerosol Can","Please empty contents", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Metal Container - Non-Food","Please empty contents", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Clean Aluminium Tray & Foil","Please empty contents", R.drawable.recyclable));



                    updateRecyclerView.callback(position, items);
                }

                else if (position==5){

                    ArrayList<DynamicRVModel> items = new ArrayList<DynamicRVModel>();
                    items.add(new DynamicRVModel("IT Accessories","Recycled at specific collection points", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Electronic Waste","Recycled at specific collection points", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Rechargeable Battery","Recycled at specific collection points", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Small Household Appliances","Recycled at specific collection points", R.drawable.recyclable));
                    items.add(new DynamicRVModel("Household Battery","Dispose as general waste", R.drawable.nonrecyclable));

                    updateRecyclerView.callback(position, items);
                }
            }
        });
// Background colour change when clicked
        if (select){
            if (position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            select=false;
        }
        else {

            if (row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);

            }

            else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv__selected_bg);
            }

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.paper);
            textView = itemView.findViewById(R.id.text1);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }
    }

}
