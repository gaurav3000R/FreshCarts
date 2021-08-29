package com.example.freshcarts.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.freshcarts.Mainactivity3;
import com.example.freshcarts.R;
import com.example.freshcarts.signup;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.EventListener;

public class LoginActivityfrash extends AppCompatActivity {

    Button signupbtn,loginbtn;
    TextInputLayout username_var, password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        signupbtn = findViewById(R.id.signup);
        loginbtn = findViewById(R.id.login);
        username_var = findViewById(R.id.user_name_text_field);
        password_var = findViewById(R.id.password_text_field);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username_=username_var.getEditText().getText().toString();
                String password_=password_var.getEditText().getText().toString();




                if (!username_.isEmpty()) {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);

                    if (!password_.isEmpty()) {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);


                        final String username_data=username_var.getEditText().getText().toString();
                        final String password_data=password_var.getEditText().getText().toString();
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                         DatabaseReference databaseReference=firebaseDatabase.getReference("NAME");

                        Query check_username=databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                if (snapshot.exists()) {
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);


                                    if (passwordcheck.equals(password_data)) {
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivityfrash.this,Mainactivity3.class);
                                        startActivity(intent);
                                        finish();

                                        loginbtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(LoginActivityfrash.this,Mainactivity3.class);
                                                startActivity(intent);
                                            }
                                        });






                                    }else {password_var.setError("wrong password");}

                                }else {username_var.setError("invalid username");}
                    }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    } else {
                        password_var.setError("enter valid password");
                    }
                } else {
                    username_var.setError("enter valid username");
                }




            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivityfrash.this, signup.class);
                startActivity(intent);
            }
        });
























    }
}
