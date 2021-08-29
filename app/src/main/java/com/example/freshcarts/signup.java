package com.example.freshcarts;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.example.freshcarts.ui.login.LoginActivityfrash;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    Button login_, register_;
    TextInputLayout fullnamesign_var, emailsign_var, phonenumber_var, passwordsign_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth fauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        register_ = findViewById(R.id.Registersignup);
        login_ = findViewById(R.id.loginbtn1);
        fullnamesign_var = findViewById(R.id.Fullname_signup);
        emailsign_var = findViewById(R.id.email_signup_field);
        passwordsign_var = findViewById(R.id.password_singup_field);
        phonenumber_var = findViewById(R.id.phone_number_signup_field);
        fauth = FirebaseAuth.getInstance();

        register_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname_ = fullnamesign_var.getEditText().getText().toString();
                String email_ = emailsign_var.getEditText().getText().toString();
                String password_ = passwordsign_var.getEditText().getText().toString();
                String phonenumber_ = phonenumber_var.getEditText().getText().toString();


                if (!fullname_.isEmpty()) {
                    fullnamesign_var.setError(null);
                    fullnamesign_var.setErrorEnabled(false);
                } else {
                    fullnamesign_var.setError("Enter full name");
                }


                if (!phonenumber_.isEmpty()) {
                    phonenumber_var.setError(null);
                    phonenumber_var.setErrorEnabled(false);
                } else {
                    phonenumber_var.setError("Enter phone number");
                }


                if (!password_.isEmpty()) {
                    passwordsign_var.setError(null);
                    passwordsign_var.setErrorEnabled(false);
                } else {
                    passwordsign_var.setError("Enter password");
                }


                if (email_.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
                    emailsign_var.setError(null);
                    emailsign_var.setErrorEnabled(false);

                } else {
                    emailsign_var.setError("Please enter valid email-id");
                }


                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("datauser");


                String fullname_s = fullnamesign_var.getEditText().getText().toString();
                String email_s = emailsign_var.getEditText().getText().toString();
                String password_s = passwordsign_var.getEditText().getText().toString();
                String phonenumber_s = phonenumber_var.getEditText().getText().toString();

                storing_data storing_data = new storing_data(fullname_s, email_s, password_s, phonenumber_s);
                reference.child(fullname_s).setValue(storing_data);


                }


        });


//                Toast.makeText(getApplicationContext(),"registeration successfull",Toast.LENGTH_SHORT).show();


//                fauth.createUserWithEmailAndPassword(email_,password_).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(register_.this,"user created" , Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),Mainactivity3.class));
//
//                        }else {Toast.makeText(register_.this,"errror !!!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                })
//





    }
}