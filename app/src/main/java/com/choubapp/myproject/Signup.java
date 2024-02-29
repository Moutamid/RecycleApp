package com.choubapp.myproject;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    //variable
    TextInputLayout regPassword1, regEmail, regPassword2;
    Button regBtn;
    TextView regToLoginBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        //Hooks to all xml elements in activity_sign_up.xml

        regPassword1 = findViewById(R.id.reg_password1);
        regEmail = findViewById(R.id.reg_email);
        regPassword2 = findViewById(R.id.reg_password2);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);

                Pair[] pairs = new Pair[5];

                pairs[0] = new Pair<View, String>(regPassword1, "reg_password1");
                pairs[1] = new Pair<View, String>(regEmail, "reg_email");
                pairs[2] = new Pair<View, String>(regPassword2, "reg_password2");
                pairs[3] = new Pair<View, String>(regBtn, "reg_btn");
                pairs[4] = new Pair<View, String>(regToLoginBtn, "reg_login_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword1.getEditText().getText().toString();
        String confirmpassword = regPassword2.getEditText().getText().toString();

        if (email.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            regEmail.requestFocus();
            return;
        }
        if (!email.matches(emailPattern)) {
            regEmail.setError("Enter valid email address");
            regEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            regPassword1.setError("Field cannot be empty");
            regPassword1.requestFocus();
            return;
        }
        if (password.length() < 6) {
            regPassword1.setError("Length of the password should be more than 6");
            regPassword1.requestFocus();
            return;
        }
        if (!password.equals(confirmpassword)) {
            regPassword2.setError("Password not match");
            regPassword2.requestFocus();
            return;
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        registerUser(); //next activity
                    }

                }
            });
        }
    }


    public void registerUser() {

        Intent intent = new Intent(Signup.this, main_menu.class);

        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View, String>(regPassword1, "reg_password1");
        pairs[1] = new Pair<View, String>(regEmail, "reg_email");
        pairs[2] = new Pair<View, String>(regPassword2, "reg_password2");
        pairs[3] = new Pair<View, String>(regBtn, "reg_btn");
        pairs[4] = new Pair<View, String>(regToLoginBtn, "reg_login_btn");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
            startActivity(intent, options.toBundle());
        }
    }
}

