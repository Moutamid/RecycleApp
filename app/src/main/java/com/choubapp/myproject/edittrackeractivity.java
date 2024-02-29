package com.choubapp.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class edittrackeractivity extends AppCompatActivity {

    Intent data;
    TextInputLayout editmaterialtype, edititem, editdate, editnotes;
    Button update_btn;
    Calendar calendar;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittrackeractivity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edittrackeractivity);

        editmaterialtype=findViewById(R.id.editmaterialtype);
        edititem=findViewById(R.id.edititem);
        editdate=findViewById(R.id.editdate);
        editnotes=findViewById(R.id.editnotes);
        update_btn=findViewById(R.id.save_btn);
        data=getIntent();

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

                editdate.getEditText().setText(sdf.format(calendar.getTime()));
            }

        };

        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(edittrackeractivity.this, date1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newmaterial=editmaterialtype.getEditText().getText().toString();
                String newitem=edititem.getEditText().getText().toString();
                String newdate=editdate.getEditText().getText().toString();
                String newnotes=editnotes.getEditText().getText().toString();


                if(newmaterial.isEmpty() || newitem.isEmpty() || newdate.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    DocumentReference documentReference=firebaseFirestore.
                            collection("trackers").document(firebaseUser.getUid()).
                            collection("mytrackers").document
                            (data.getStringExtra("trackerId"));
                    Map<String,Object> tracker=new HashMap<>();
                    tracker.put("material",newmaterial);
                    tracker.put("item",newitem);
                    tracker.put("date",newdate);
                    tracker.put("note",newnotes);
                    documentReference.set(tracker).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "UPDATED", Toast.LENGTH_SHORT).show();

                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


        String trackermaterial = data.getStringExtra("material");
        String trackeritem = data.getStringExtra("item");
        String trackerdate = data.getStringExtra("date");
        String trackernote = data.getStringExtra("notes");
        editmaterialtype.getEditText().setText(trackermaterial);
        edititem.getEditText().setText(trackeritem);
        editdate.getEditText().setText(trackerdate);
        editnotes.getEditText().setText(trackernote);

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