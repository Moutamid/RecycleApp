package com.choubapp.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class addnote extends AppCompatActivity {

    TextInputLayout material, item, date, note;
    Button save_btn;
    Calendar calendar;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_addnote);

        material=findViewById(R.id.materialtype);
        item=findViewById(R.id.item);
        date=findViewById(R.id.date);
        note=findViewById(R.id.notes);
        save_btn=findViewById(R.id.save_btn);

       firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        //calendar
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }
            private void updateCalendar() {
                String Format = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);

                date.getEditText().setText(sdf.format(calendar.getTime()));
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(addnote.this, date1, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // main code to store data
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String material1 = material.getEditText().getText().toString();
                String item1 = item.getEditText().getText().toString();
                String date1 = date.getEditText().getText().toString();
                String note1 = note.getEditText().getText().toString();

                if (material1.isEmpty()){
                    material.setError("Field cannot be empty");
                    material.requestFocus();
                    return;
                }
                if (item1.isEmpty()){
                    item.setError("Field cannot be empty");
                    item.requestFocus();
                    return;
                }
                if (date1.isEmpty()){
                    date.setError("Field cannot be empty");
                    date.requestFocus();
                    return;
                }

                else
                {
                    // store data in firestore using doc
                    DocumentReference documentReference=firebaseFirestore.collection("trackers").
                            document(firebaseUser.getUid()).collection("mytrackers").document();
                    Map<String, Object> note=new HashMap<>();
                    note.put("material",material1);
                    note.put("item",item1);
                    note.put("date",date1);
                    note.put("note",note1);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(getApplicationContext(), "Created successfully",
                                    Toast.LENGTH_SHORT).show();
                            finish();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), e.toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed(); //toolbar (action bar)
        }
        return super.onOptionsItemSelected(item);
    }
    public void back(View view) {
        onBackPressed();
    }
}