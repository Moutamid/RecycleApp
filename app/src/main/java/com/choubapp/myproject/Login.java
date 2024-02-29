package com.choubapp.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {

    Button login_btn;
    TextView callSignUp;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout regEmail,regPassword1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callSignUp = findViewById(R.id.signup);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        regEmail = findViewById(R.id.reg_email);
        regPassword1 = findViewById(R.id.reg_password1);
        login_btn = findViewById(R.id.Login_btn);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(Login.this,main_menu.class);
                    startActivity(intent);
                }
            }
        };


        callSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
//
//                Pair[] pairs = new Pair[7];
//
//                pairs[0] = new Pair<View,String>(image,"logo_image");
//                pairs[1] = new Pair<View,String>(logoText,"logo_text");
//                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
//                pairs[3] = new Pair<View,String>(regEmail,"username_tran");
//                pairs[4] = new Pair<View,String>(regPassword1,"password_tran");
//                pairs[5] = new Pair<View,String>(login_btn,"button_tran");
//                pairs[6] = new Pair<View,String>(callSignUp,"login_signup_tran");

//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this);
                    startActivity(intent);
//                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });
    }
    private void perforLogin() {
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword1.getEditText().getText().toString();
        if (email.isEmpty()){
            regEmail.setError("Field cannot be empty");
            regEmail.requestFocus();
            return;
        }
        if (!email.matches(emailPattern)){
            regEmail.setError("Enter valid email address");
            regEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            regPassword1.setError("Field cannot be empty");
            regPassword1.requestFocus();
            return;
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        loginUser();
                    }
                    else{
                        new AlertDialog.Builder(Login.this)
                                .setTitle("Your email or password is incorrect")
                                .setMessage("Please try again")
                                .setPositiveButton("OK",null)
                                .show();
                    }

                }
            });
        }

    }


    public void loginUser () {

        Intent intent = new Intent(Login.this,main_menu.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View,String>(image,"logo_image");
        pairs[1] = new Pair<View,String>(logoText,"logo_text");
        pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
        pairs[3] = new Pair<View,String>(regEmail,"username_tran");
        pairs[4] = new Pair<View,String>(regPassword1,"password_tran");
        pairs[5] = new Pair<View,String>(login_btn,"button_tran");
        pairs[6] = new Pair<View,String>(callSignUp,"login_signup_tran");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
            startActivity(intent, options.toBundle());
        }

    }

    @Override
    protected void onStart (){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

}
