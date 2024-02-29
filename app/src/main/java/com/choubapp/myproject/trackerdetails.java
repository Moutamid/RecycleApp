package com.choubapp.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class trackerdetails extends AppCompatActivity {

   private TextView materialtypedetails, itemdetails, datedetails, notesdetails;
   Button edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackerdetails);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trackerdetails);


        materialtypedetails=findViewById(R.id.materialtypedetails);
        itemdetails=findViewById(R.id.itemdetails);
        datedetails=findViewById(R.id.datedetails);
        notesdetails=findViewById(R.id.notesdetails);
        edit_btn=findViewById(R.id.edit_btn);



        Intent data=getIntent();

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),edittrackeractivity.class);
                // pass data
                intent.putExtra("material",data.getStringExtra("material"));
                intent.putExtra("item",data.getStringExtra("item"));
                intent.putExtra("date",data.getStringExtra("date"));
                intent.putExtra("notes",data.getStringExtra("notes"));
                intent.putExtra("trackerId",data.getStringExtra("trackerId"));
                v.getContext().startActivity(intent);

            }
        });

        materialtypedetails.setText(data.getStringExtra("material"));
        itemdetails.setText(data.getStringExtra("item"));
        datedetails.setText(data.getStringExtra("date"));
        notesdetails.setText(data.getStringExtra("notes"));


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
        onBackPressed();
    }
}